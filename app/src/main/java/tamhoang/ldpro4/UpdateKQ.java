package tamhoang.ldpro4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import tamhoang.ldpro4.data.Database;

public class UpdateKQ extends BroadcastReceiver {
    Database db;

    public void onReceive(Context paramContext, Intent paramIntent) {
        this.db = new Database(paramContext);
        String str = (new SimpleDateFormat("HH:mm")).format(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        if ((Integer.valueOf(str.substring(0, 2)).intValue() > 17 && Integer.valueOf(str.substring(3, 5)).intValue() > 18) || Integer.valueOf(str.substring(0, 2)).intValue() > 18) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(5, 0);
            simpleDateFormat.format(calendar.getTime());
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.add(5, -1);
            simpleDateFormat.format(calendar.getTime());
        }
    }
}
