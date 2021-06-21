package tamhoang.ldpro4;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.webkit.JavascriptInterface;

public class NotificationBindObject {
    private Context mContext;

    public NotificationBindObject(Context paramContext) {
        this.mContext = paramContext;
    }

    @JavascriptInterface
    public void showNotification(String paramString) {
        NotificationCompat.Builder builder = (new NotificationCompat.Builder(this.mContext)).setContentText(paramString).setAutoCancel(true);
        Intent intent = new Intent(this.mContext, MainActivity.class);
        intent.putExtra("EXTRA_FROM_NOTIFICATION", true);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this.mContext);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(intent);
        builder.setContentIntent(taskStackBuilder.getPendingIntent(0, 134217728));
        ((NotificationManager)this.mContext.getSystemService("notification")).notify(-1, builder.build());
    }
}
