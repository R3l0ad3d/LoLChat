package test.net.atshq.lolchat.ModelClass;

/**
 * Created by User on 3/8/2018.
 */

public class SearchModel {
    private String userId;
    private long priority;

    public SearchModel(String userId, long priority) {
        this.userId = userId;
        this.priority = priority;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }
}
