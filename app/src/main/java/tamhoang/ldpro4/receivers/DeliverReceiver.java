package tamhoang.ldpro4.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import tamhoang.ldpro4.C0925R;

public class DeliverReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent arg1) {
        if (getResultCode() == 0) {
            Toast.makeText(context, C0925R.string.sms_not_delivered, 0).show();
        }
    }
}
