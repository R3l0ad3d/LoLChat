package test.net.atshq.lolchat.ModelClass;

import java.security.SecureRandom;

/**
 * Created by User on 3/8/2018.
 */

//it will be use further
public class ChatConversationModel {
    private String userId;
    private String message;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
