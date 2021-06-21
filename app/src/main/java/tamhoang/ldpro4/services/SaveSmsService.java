package tamhoang.ldpro4.services;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import tamhoang.ldpro4.constants.SmsContract;

public class SaveSmsService extends IntentService {
    public SaveSmsService() {
        super("SaveService");
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        String senderNo = intent.getStringExtra("sender_no");
        String message = intent.getStringExtra("message");
        long time = intent.getLongExtra("date", 0);
        ContentValues values = new ContentValues();
        values.put("address", senderNo);
        values.put("body", message);
        values.put("date_sent", Long.valueOf(time));
        getContentResolver().insert(SmsContract.ALL_SMS_URI, values);
        sendBroadcast(new Intent("android.intent.action.MAIN").putExtra("new_sms", true));
    }
}
