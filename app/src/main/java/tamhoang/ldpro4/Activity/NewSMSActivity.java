package tamhoang.ldpro4.Activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import tamhoang.ldpro4.receivers.DeliverReceiver;
import tamhoang.ldpro4.receivers.SentReceiver;

public class NewSMSActivity extends AppCompatActivity implements View.OnClickListener {
    BroadcastReceiver deliveryBroadcastReciever = (BroadcastReceiver)new DeliverReceiver();

    private String message;

    private String phoneNo;

    BroadcastReceiver sendBroadcastReceiver = (BroadcastReceiver)new SentReceiver();

    private EditText txtMessage;

    private EditText txtphoneNo;

    private void contactPicked(Intent paramIntent) {
        try {
            Uri uri = paramIntent.getData();
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            int i = cursor.getColumnIndex("data1");
            int j = cursor.getColumnIndex("display_name");
            String str = cursor.getString(i);
            cursor.getString(j);
            this.txtphoneNo.setText(str);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void init() {
        Button button = (Button)findViewById(2131230800);
        this.txtphoneNo = (EditText)findViewById(2131230912);
        this.txtMessage = (EditText)findViewById(2131230913);
        ((ImageButton)findViewById(2131230853)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                Intent intent = new Intent("android.intent.action.PICK", ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                NewSMSActivity.this.startActivityForResult(intent, 85);
            }
        });
        button.setOnClickListener(this);
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        if (paramInt2 == -1) {
            if (paramInt1 == 85)
                contactPicked(paramIntent);
        } else {
            Log.e("MainActivity", "Failed to pick contact");
        }
    }

    public void onClick(View paramView) {
        if (paramView.getId() == 2131230800) {
            this.phoneNo = this.txtphoneNo.getText().toString();
            this.message = this.txtMessage.getText().toString();
            String str = this.phoneNo;
            if (str != null && str.trim().length() > 0) {
                str = this.message;
                if (str == null || str.trim().length() <= 0)
                    this.txtMessage.setError(getString(2131624003));
            } else {
                this.txtphoneNo.setError(getString(2131624004));
            }
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131427454);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        if (paramMenuItem.getItemId() == 16908332)
            finish();
        return super.onOptionsItemSelected(paramMenuItem);
    }

    protected void onPause() {
        super.onPause();
        try {
            unregisterReceiver(this.sendBroadcastReceiver);
            unregisterReceiver(this.deliveryBroadcastReciever);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void pickContact(View paramView) {
        startActivityForResult(new Intent("android.intent.action.PICK", ContactsContract.CommonDataKinds.Phone.CONTENT_URI), 85);
    }
}
