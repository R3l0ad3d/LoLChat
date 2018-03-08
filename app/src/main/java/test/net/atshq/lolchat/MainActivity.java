package test.net.atshq.lolchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import test.net.atshq.lolchat.Config.UserData;
import test.net.atshq.lolchat.ModelClass.UserInformationModel;

public class MainActivity extends AppCompatActivity {

    private UserData userData;

    private DatabaseReference reference;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reference = FirebaseDatabase.getInstance().getReference().child("root").child("user");

        init();
    }

    private void init() {
        userData = new UserData(this);

        String token = FirebaseInstanceId.getInstance().getToken();
        userData.setUserId(token);
        UserInformationModel uInfo = new UserInformationModel();
        uInfo.setUserId(token);
        reference.child(token).setValue(uInfo);
    }

    public void chatClick(View view) {
        //registration this device on online node
        //next page
        Intent intent = new Intent(this,ChatActivity.class);
        startActivity(intent);
    }
}
