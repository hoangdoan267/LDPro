package tamhoang.ldpro4;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ZBroadcast extends Service {
    String viewData;

    public IBinder onBind(Intent paramIntent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
        return 1;
    }
}
