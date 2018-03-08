package test.net.atshq.lolchat.Config;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by User on 3/8/2018.
 */

public class UserData {
    protected static final String USER_ID="user_id";
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Context mContext;

    public UserData(Context mContext) {
        this.mContext = mContext;
        sp= mContext.getSharedPreferences(USER_ID,Context.MODE_PRIVATE);
        editor=sp.edit();
    }

    public void setUserName(String userName){
        editor.putString("userName",userName);
        editor.commit();
    }
    public String getUserName(){
        return  sp.getString("userName","");
    }

    public void setUserId(String userId){
        editor.putString(USER_ID,userId);
        editor.commit();
    }
    public String getUserId(){
        return  sp.getString(USER_ID,"");
    }



}
