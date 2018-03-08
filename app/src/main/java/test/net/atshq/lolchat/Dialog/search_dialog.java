package test.net.atshq.lolchat.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import test.net.atshq.lolchat.R;

/**
 * Created by User on 3/8/2018.
 */

public class search_dialog {
    private Context context;
    private Dialog searchDialog;

    public search_dialog(Context context) {
        this.context = context;
        searchDialog = new Dialog(context);
        createDialog();
    }

    protected void createDialog(){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_dialog,null);
        searchDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        searchDialog.setContentView(view);
        searchDialog.setCancelable(true);

    }

    public void showDialog(){
        searchDialog.show();
    }

    public void dismisDialog(){
        searchDialog.dismiss();
    }

}
