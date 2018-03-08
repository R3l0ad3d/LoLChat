package test.net.atshq.lolchat.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

import test.net.atshq.lolchat.ModelClass.ChatConversationModel;
import test.net.atshq.lolchat.ModelClass.UserInformationModel;
import test.net.atshq.lolchat.R;
import test.net.atshq.lolchat.databinding.ConversationChatModelBinding;

/**
 * Created by User on 3/8/2018.
 */

public class RvConversationChatAdapter extends RecyclerView.Adapter<RvConversationChatAdapter.ViewHolder> {

    private Context context;
    private List<ChatConversationModel> conversationModels;
    private UserInformationModel userInfo; //userInfo need for detect chat info

    public RvConversationChatAdapter(Context context, List<ChatConversationModel> conversationModels) {
        this.context = context;
        this.conversationModels = conversationModels;
        userInfo = new UserInformationModel(); // get this user info from local database
        userInfo.setUserId("joy");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View chatView = inflater.inflate(R.layout.conversation_chat_model,parent,false); //if massage then show this view
        //View imageView=inflater.inflate(R.layout.conversation_image_model,parent,false); //if image then show this view
        return new ViewHolder(chatView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatConversationModel chat = conversationModels.get(position);

        if(chat.getUserId().equals(userInfo.getUserId())){
            holder.chatBinding.tvMsg.setGravity(Gravity.RIGHT);
            holder.chatBinding.tvMsg.setText(chat.getMessage());
        }else {
            holder.chatBinding.tvMsg.setGravity(Gravity.LEFT);
            holder.chatBinding.tvMsg.setText(chat.getMessage());
        }

    }




    @Override
    public int getItemCount() {
        return conversationModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ConversationChatModelBinding chatBinding;

        //public ConversationImageModelBinding imageBinding;
        public ViewHolder(View itemView) {
            super(itemView);
            chatBinding = DataBindingUtil.bind(itemView); //for message
            //imageBinding = DataBindingUtil.bind(itemView); //for image
        }
    }
}
