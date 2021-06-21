package tamhoang.ldpro4.data;

import android.app.PendingIntent;
import android.app.RemoteInput;
import android.os.Bundle;

public class Contact {
    public String app;

    public String name;

    public PendingIntent pendingIntent;

    public Bundle remoteExtras;

    public RemoteInput remoteInput;

    public String getApp() {
        return this.app;
    }

    public String getName() {
        return this.name;
    }

    public PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public Bundle getRemoteExtras() {
        return this.remoteExtras;
    }

    public RemoteInput getRemoteInput() {
        return this.remoteInput;
    }

    public void setApp(String paramString) {
        this.app = paramString;
    }

    public void setName(String paramString) {
        this.name = paramString;
    }

    public void setPendingIntent(PendingIntent paramPendingIntent) {
        this.pendingIntent = paramPendingIntent;
    }

    public void setRemoteExtras(Bundle paramBundle) {
        this.remoteExtras = paramBundle;
    }

    public void setRemoteInput(RemoteInput paramRemoteInput) {
        this.remoteExtras = this.remoteExtras;
    }
}
