package tamhoang.ldpro4;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import org.xmlpull.v1.XmlSerializer;
import tamhoang.ldpro4.data.Database;

public class Login extends AppCompatActivity {
    public static String Imei = null;

    public static String serial = null;

    Database db;

    Intent intent;

    Button login;

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission((Context)this, "android.permission.READ_SMS") != 0 && !ActivityCompat.shouldShowRequestPermissionRationale((Activity)this, "android.permission.READ_SMS"))
            ActivityCompat.requestPermissions((Activity)this, new String[] { "android.permission.READ_SMS" }, 1);
    }

    public void Create_Table_database() {
        this.db.Creat_TinNhanGoc();
        this.db.Create_table_Chat();
        this.db.Creat_SoCT();
        this.db.Creat_So_Om();
        this.db.List_Khach_Hang();
        this.db.Bang_KQ();
        this.db.ThayThePhu();
        this.db.Another_setting();
        this.db.Creat_Chaytrang_acc();
        this.db.Creat_Chaytrang_ticket();
        try {
            Cursor cursor = this.db.GetData("Select * From so_om");
            int i = cursor.getCount();
            if (i < 1) {
                for (i = 0; i < 10; i++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    this();
                    stringBuilder.append("Insert into so_om Values (null, '0");
                    stringBuilder.append(i);
                    stringBuilder.append("', 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null)");
                    String str = stringBuilder.toString();
                    this.db.QueryData(str);
                }
                for (i = 10; i < 100; i++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    this();
                    stringBuilder.append("Insert into so_om Values (null, '");
                    stringBuilder.append(i);
                    stringBuilder.append("', 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null)");
                    String str = stringBuilder.toString();
                    this.db.QueryData(str);
                }
            }
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        } catch (SQLException sQLException) {}
        try {
            Cursor cursor = this.db.GetData("Select Om_Xi3 FROM So_om WHERE So = '05'");
            cursor.moveToFirst();
            if (cursor.getInt(0) == 0)
                this.db.QueryData("UPDATE So_om SET Om_Xi3 = 18, Om_Xi4 = 15 WHERE So = '05'");
        } catch (SQLException sQLException) {}
    }

    public boolean checkDefaultSettings() {
        boolean bool = false;
        if (Build.VERSION.SDK_INT >= 19)
            if (!Telephony.Sms.getDefaultSmsPackage(getApplicationContext()).equals(getApplicationContext().getPackageName())) {
                showAlertBox("Cm, "dthqultin nhmqultin nhth).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                        Intent intent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
                        intent.putExtra("package", Login.this.getPackageName());
                        Login.this.startActivity(intent);
                        Login.this.checkPermissions();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                        param1DialogInterface.dismiss();
                    }
                }).show().setCanceledOnTouchOutside(false);
                bool = false;
            } else {
                bool = true;
            }
        return bool;
    }

    public String getImei() {
        if (ActivityCompat.checkSelfPermission((Context)this, "android.permission.READ_PHONE_STATE") != -1)
            try {
                Imei = ((TelephonyManager)getSystemService("phone")).getDeviceId();
                serial = Settings.Secure.getString(getContentResolver(), "android_id");
            } catch (Exception exception) {
                exception.getMessage();
            }
        if (Imei != null) {
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter stringWriter = new StringWriter();
            try {
                xmlSerializer.setOutput(stringWriter);
                xmlSerializer.startDocument("UTF-8", Boolean.valueOf(true));
                String str = Imei;
                FileOutputStream fileOutputStream = openFileOutput("new.xml", 0);
                fileOutputStream.write(str.getBytes(), 0, str.length());
                fileOutputStream.close();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
                Toast.makeText((Context)this, "Loi tao file", 0).show();
            }
        } else {
            try {
                FileInputStream fileInputStream = openFileInput("new.xml");
                Imei = "";
                while (true) {
                    int i = fileInputStream.read();
                    if (i != -1) {
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(Imei);
                        stringBuilder.append((char)i);
                        Imei = stringBuilder.toString();
                        continue;
                    }
                    break;
                }
            } catch (FileNotFoundException fileNotFoundException) {
                checkDefaultSettings();
                fileNotFoundException.printStackTrace();
            } catch (IOException iOException) {}
        }
        return Imei;
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131427371);
        this.db = new Database((Context)this);
        this.login = (Button)findViewById(2131230822);
        final int[] contact = new int[1];
        final int[] storage = new int[1];
        final int[] receive_sms = new int[1];
        final int[] send_sms = new int[1];
        final int[] readCard = new int[1];
        arrayOfInt2[0] = ContextCompat.checkSelfPermission((Context)this, "android.permission.READ_CONTACTS");
        arrayOfInt1[0] = ContextCompat.checkSelfPermission((Context)this, "android.permission.READ_PHONE_STATE");
        arrayOfInt3[0] = ContextCompat.checkSelfPermission((Context)this, "android.permission.RECEIVE_SMS");
        arrayOfInt4[0] = ContextCompat.checkSelfPermission((Context)this, "android.permission.SEND_SMS");
        arrayOfInt5[0] = ContextCompat.checkSelfPermission((Context)this, "android.permission.WRITE_EXTERNAL_STORAGE");
        if (arrayOfInt2[0] == -1 || arrayOfInt1[0] == -1 || arrayOfInt3[0] == -1 || arrayOfInt5[0] == -1 || arrayOfInt4[0] == -1)
            ActivityCompat.requestPermissions((Activity)this, new String[] { "android.permission.INTERNET", "android.permission.READ_CONTACTS", "android.permission.RECEIVE_SMS", "android.permission.SEND_SMS", "android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE" }, 1);
        this.login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                contact[0] = ContextCompat.checkSelfPermission((Context)Login.this, "android.permission.READ_CONTACTS");
                storage[0] = ContextCompat.checkSelfPermission((Context)Login.this, "android.permission.READ_PHONE_STATE");
                receive_sms[0] = ContextCompat.checkSelfPermission((Context)Login.this, "android.permission.RECEIVE_SMS");
                send_sms[0] = ContextCompat.checkSelfPermission((Context)Login.this, "android.permission.SEND_SMS");
                readCard[0] = ContextCompat.checkSelfPermission((Context)Login.this, "android.permission.WRITE_EXTERNAL_STORAGE");
                if (contact[0] == 0 && storage[0] == 0 && receive_sms[0] == 0 && readCard[0] == 0 && send_sms[0] == 0) {
                    if (Login.this.getImei() != null) {
                        Login.this.intent = new Intent((Context)Login.this, MainActivity.class);
                        Login login = Login.this;
                        login.startActivities(new Intent[] { login.intent });
                    }
                } else {
                    ActivityCompat.requestPermissions((Activity)Login.this, new String[] { "android.permission.INTERNET", "android.permission.READ_CONTACTS", "android.permission.RECEIVE_SMS", "android.permission.SEND_SMS", "android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE" }, 1);
                }
            }
        });
        try {
            Create_Table_database();
            if (arrayOfInt2[0] == 0 && arrayOfInt1[0] == 0 && arrayOfInt3[0] == 0 && arrayOfInt5[0] == 0 && arrayOfInt4[0] == 0 && getImei() != null) {
                Intent intent = new Intent();
                this((Context)this, MainActivity.class);
                this.intent = intent;
                startActivities(new Intent[] { intent });
            }
        } catch (SQLException sQLException) {}
    }

    public AlertDialog.Builder showAlertBox(String paramString1, String paramString2) {
        return (new AlertDialog.Builder((Context)this)).setTitle(paramString1).setMessage(paramString2);
    }
}
