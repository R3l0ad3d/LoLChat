package test.net.atshq.lolchat;

import android.databinding.DataBindingUtil;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

import test.net.atshq.lolchat.Adapter.RvConversationChatAdapter;
import test.net.atshq.lolchat.ModelClass.ChatConversationModel;
import test.net.atshq.lolchat.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {

    ActivityChatBinding binding;

    private RvConversationChatAdapter adapter;
    private List<ChatConversationModel> chatList;
    private RecyclerView.LayoutManager manager;

    //database entry
    private DatabaseReference chatRef= FirebaseDatabase.getInstance().getReference().child("chat");
    private DatabaseReference searchRef = FirebaseDatabase.getInstance().getReference().child("search");

    ChildEventListener childEventListenerforChat;
    ChildEventListener childEventListenerforSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        init();
    }

    private void init() {
        chatList = new ArrayList<>();
        adapter = new RvConversationChatAdapter(this, chatList);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);

        binding.rvChatConversation.setLayoutManager(manager);
        binding.rvChatConversation.setHasFixedSize(true);
        binding.rvChatConversation.setAdapter(adapter);


        setData();

    }

    private void setData() {
        ChatConversationModel mo = new ChatConversationModel();
        mo.setMessage("first item");
        mo.setUserId("joy");
        chatList.add(mo);
        for (int i = 0; i < 30; i++) {
            ChatConversationModel model = new ChatConversationModel();
            if (i % 2 == 0) {
                model.setMessage("Hello Akash");
                model.setUserId("joy");
            } else {
                model.setMessage("Hello joy");
                model.setUserId("akash");
            }
            chatList.add(model);
        }
        ChatConversationModel mod = new ChatConversationModel();
        mod.setMessage("last item");
        mod.setUserId("joy");
        chatList.add(mod);
        adapter.notifyDataSetChanged();
    }

    public void msgSendClick(View view) {
        ChatConversationModel model = new ChatConversationModel();
        if (!binding.etTextSubmit.getText().equals("")) {
            model.setMessage(binding.etTextSubmit.getText().toString());
            model.setUserId("joy");
            chatList.add(0, model);
            adapter.notifyDataSetChanged();

            binding.etTextSubmit.setText("");

            Toast.makeText(this, "data insert", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "empty found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_next){
            searchRef.child(FirebaseInstanceId.getInstance().getToken()).setValue("searching...");
            searchForChat();
        }
        return true;
    }

    private void searchForChat() {

        childEventListenerforChat = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final String[] partner = {""};
                if(dataSnapshot.getKey().equals(FirebaseInstanceId.getInstance().getToken())){ //new child create for my token
                    chatRef.child(FirebaseInstanceId.getInstance().getToken()).child("partner")
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    partner[0] = (String) dataSnapshot.getValue();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                    searchRef.removeEventListener(childEventListenerforSearch); //remove listener from search partner found
                    chatRef.removeEventListener(childEventListenerforChat); // remove this chat listener for get partner
                    chatStart(partner[0]);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

       childEventListenerforSearch = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String newCommer=dataSnapshot.getKey();
                chatRef.child(newCommer).child("partner").setValue(FirebaseInstanceId.getInstance().getToken());
                chatRef.child(FirebaseInstanceId.getInstance().getToken()).child("partner").child(newCommer);
                chatRef.child(newCommer).child("conversation");
                chatRef.child(FirebaseInstanceId.getInstance().getToken());
                searchRef.child(FirebaseInstanceId.getInstance().getToken()).removeValue();
                searchRef.removeEventListener(childEventListenerforSearch);
                chatRef.removeEventListener(childEventListenerforChat);

                chatStart(newCommer);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        chatRef.addChildEventListener(childEventListenerforChat);
        searchRef.addChildEventListener(childEventListenerforSearch);


    }

    private void chatStart(String newComer) {
        //chatRef.child()
    }
}
