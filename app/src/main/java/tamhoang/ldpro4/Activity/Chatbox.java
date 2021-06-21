package tamhoang.ldpro4.Activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Congthuc.BaseToolBarActivity;
import tamhoang.ldpro4.Congthuc.Congthuc;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.NotificationReader;
import tamhoang.ldpro4.data.Database;

public class Chatbox extends BaseToolBarActivity {
    boolean Running = true;

    JSONObject TinXuly = new JSONObject();

    String app_use;

    Database db;

    private List<String> gio_nhan = new ArrayList<String>();

    Handler handler;

    ListView listView;

    private List<String> mApp = new ArrayList<String>();

    private List<String> mID = new ArrayList<String>();

    private List<String> mID_TinNhan = new ArrayList<String>();

    private List<String> mSDT = new ArrayList<String>();

    private List<String> mSo_TinNhan = new ArrayList<String>();

    private List<String> mTenKH = new ArrayList<String>();

    private List<String> mXulytin = new ArrayList<String>();

    EditText messageS;

    private List<String> nd_goc = new ArrayList<String>();

    int position;

    private Runnable runnable = new Runnable() {
        public void run() {
            if (MainActivity.sms == true) {
                Chatbox.this.Xem_lv();
                MainActivity.sms = false;
            }
            Chatbox.this.handler.postDelayed(this, 1000L);
        }
    };

    ImageView send;

    String so_dienthoai;

    String ten_kh;

    private List<String> type_kh = new ArrayList<String>();

    public void GuiTinTrucTiep(String paramString1, String paramString2, String paramString3, String paramString4) {
        Database database = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select * From tbl_kh_new Where ten_kh = '");
        stringBuilder.append(paramString3);
        stringBuilder.append("'");
        Cursor cursor = database.GetData(stringBuilder.toString());
        cursor.moveToFirst();
        if (cursor.getCount() > 0 && cursor.getInt(3) > 1) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Select max(so_tin_nhan) from tbl_tinnhanS WHERE ngay_nhan = '");
            stringBuilder2.append(paramString1);
            stringBuilder2.append("' AND ten_kh = '");
            stringBuilder2.append(paramString3);
            stringBuilder2.append("' AND type_kh = 2");
            String str = stringBuilder2.toString();
            Cursor cursor2 = this.db.GetData(str);
            cursor2.moveToFirst();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("Insert Into tbl_tinnhanS values (null, '");
            stringBuilder3.append(paramString1);
            stringBuilder3.append("', '");
            stringBuilder3.append(paramString2);
            stringBuilder3.append("', 2, '");
            stringBuilder3.append(paramString3);
            stringBuilder3.append("', '");
            stringBuilder3.append(cursor.getString(1));
            stringBuilder3.append("', '");
            stringBuilder3.append(cursor.getString(2));
            stringBuilder3.append("', ");
            stringBuilder3.append(cursor2.getInt(0) + 1);
            stringBuilder3.append(", '");
            stringBuilder3.append(paramString4.replaceAll("'", " ").trim());
            stringBuilder3.append("', '");
            stringBuilder3.append(paramString4.replaceAll("'", " ").trim());
            stringBuilder3.append("', '");
            stringBuilder3.append(paramString4.replaceAll("'", " ").trim());
            stringBuilder3.append("', 'ko',0, 0, 0, null)");
            paramString2 = stringBuilder3.toString();
            this.db.QueryData(paramString2);
            Database database1 = this.db;
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Select id From tbl_tinnhanS WHERE ngay_nhan = '");
            stringBuilder1.append(paramString1);
            stringBuilder1.append("' AND ten_kh = '");
            stringBuilder1.append(paramString3);
            stringBuilder1.append("' AND so_tin_nhan = ");
            stringBuilder1.append(cursor2.getInt(0) + 1);
            stringBuilder1.append(" And type_kh = 2");
            Cursor cursor1 = database1.GetData(stringBuilder1.toString());
            cursor1.moveToFirst();
            if (Congthuc.CheckDate(MainActivity.myDate)) {
                try {
                    this.db.Update_TinNhanGoc(cursor1.getInt(0), cursor.getInt(3));
                } catch (Exception exception) {
                    Database database2 = this.db;
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("Update tbl_tinnhanS set phat_hien_loi = 'ko' WHERE id = ");
                    stringBuilder3.append(cursor1.getInt(0));
                    database2.QueryData(stringBuilder3.toString());
                    StringBuilder stringBuilder4 = new StringBuilder();
                    stringBuilder4.append("Delete From tbl_soctS WHERE ngay_nhan = '");
                    stringBuilder4.append(paramString1);
                    stringBuilder4.append("' AND ten_kh = '");
                    stringBuilder4.append(paramString3);
                    stringBuilder4.append("' AND so_tin_nhan = ");
                    stringBuilder4.append(cursor2.getInt(0) + 1);
                    stringBuilder4.append(" And type_kh = 2");
                    paramString1 = stringBuilder4.toString();
                    this.db.QueryData(paramString1);
                    Toast.makeText((Context)this, "xra l, 1).show();
                }
            } else if (MainActivity.Acc_manager.length() == 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
                builder.setTitle("Thb);
                        builder.setMessage("Kitra knInternet!");
                builder.setNegativeButton(", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                    param1DialogInterface.cancel();
                }
            });
            builder.create().show();
        } else {
            try {
                AlertDialog.Builder builder = new AlertDialog.Builder();
                this((Context)this);
                builder.setTitle("Thb);
                        StringBuilder stringBuilder4 = new StringBuilder();
                this();
                stringBuilder4.append("hhsdphm\n\nHlihlhoS");
                stringBuilder4.append(MainActivity.listKH.getString("k_tra"));
                stringBuilder4.append(" gia h);
                        builder.setMessage(stringBuilder4.toString());
                DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                        param1DialogInterface.cancel();
                    }
                };
                super(this);
                builder.setNegativeButton(", onClickListener);
                        builder.create().show();
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        }
    }
    cursor.close();
}

    public void Xem_lv() {
        this.mID.clear();
        this.mTenKH.clear();
        this.gio_nhan.clear();
        this.type_kh.clear();
        this.nd_goc.clear();
        this.mApp.clear();
        this.mXulytin.clear();
        this.mID_TinNhan.clear();
        this.mSo_TinNhan.clear();
        new MainActivity();
        String str = MainActivity.Get_date();
        Database database = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select chat_database.*, tbl_tinnhanS.phat_hien_loi, tbl_tinnhanS.id, tbl_tinnhanS.so_tin_nhan From chat_database \nLEFT JOIN tbl_tinnhanS ON chat_database.ngay_nhan = tbl_tinnhanS.ngay_nhan AND chat_database.gio_nhan = tbl_tinnhanS.gio_nhan AND chat_database.ten_kh = tbl_tinnhanS.ten_kh AND chat_database.nd_goc = tbl_tinnhanS.nd_goc\nWhere chat_database.ten_kh = '");
        stringBuilder.append(this.ten_kh);
        stringBuilder.append("'  AND chat_database.ngay_nhan = '");
        stringBuilder.append(str);
        stringBuilder.append("' AND chat_database.del_sms = 1 ORDER by gio_nhan");
        Cursor cursor = database.GetData(stringBuilder.toString());
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                this.mID.add(cursor.getString(0));
                this.mTenKH.add(cursor.getString(4));
                this.mSDT.add(cursor.getString(5));
                this.gio_nhan.add(cursor.getString(2));
                this.type_kh.add(cursor.getString(3));
                this.nd_goc.add(cursor.getString(7));
                this.mApp.add(cursor.getString(6));
                if (cursor.isNull(9)) {
                    this.mXulytin.add("");
                    this.mID_TinNhan.add("");
                    this.mSo_TinNhan.add("");
                    continue;
                }
                this.mXulytin.add(cursor.getString(9));
                this.mID_TinNhan.add(cursor.getString(10));
                this.mSo_TinNhan.add(cursor.getString(11));
            }
            cursor.close();
        }
        this.listView.setAdapter((ListAdapter)new Chat((Context)this, 2131427441, this.mTenKH));
    }

    protected int getLayoutId() {
        return 2131427360;
    }

    public boolean onContextItemSelected(MenuItem paramMenuItem) {
        Intent intent;
        super.onContextItemSelected(paramMenuItem);
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService("clipboard");
        if (paramMenuItem.getTitle() == "Stin") {
            if (((String)this.mXulytin.get(this.position)).length() > 0) {
                intent = new Intent((Context)this, Activity_Tinnhan.class);
                intent.putExtra("m_ID", this.mID_TinNhan.get(this.position));
                startActivity(intent);
            }
        } else if (intent.getTitle() == "Xem chi ti) {
        if (((String)this.mXulytin.get(this.position)).indexOf("ok") == 0) {
            intent = new Intent((Context)this, Activity_CTTinnhan.class);
            intent.putExtra("m_ID", this.mID_TinNhan.get(this.position));
            intent.putExtra("type_kh", this.type_kh.get(this.position));
            startActivity(intent);
        }
    } else if (intent.getTitle() == "Copy") {
        clipboardManager.setPrimaryClip(ClipData.newPlainText("Tin nh, this.nd_goc.get(this.position)));
        Toast.makeText((Context)this, "copy vbnht, 1).show();
        } else if (intent.getTitle() == "X) {
        new MainActivity();
        MainActivity.Get_date();
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
        builder.setTitle("Xtin n);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param1DialogInterface, int param1Int) {
        if (((String)Chatbox.this.mXulytin.get(Chatbox.this.position)).length() > 0) {
        Database database2 = Chatbox.this.db;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Select * From tbl_tinnhanS where ID = ");
        stringBuilder1.append(Chatbox.this.mID_TinNhan.get(Chatbox.this.position));
        Cursor cursor = database2.GetData(stringBuilder1.toString());
        cursor.moveToFirst();
        database2 = Chatbox.this.db;
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("DELETE FROM tbl_tinnhanS WHERE ngay_nhan = '");
        stringBuilder3.append(cursor.getString(1));
        stringBuilder3.append("' AND ten_kh = '");
        stringBuilder3.append(cursor.getString(4));
        stringBuilder3.append("' AND so_tin_nhan = ");
        stringBuilder3.append(cursor.getString(7));
        stringBuilder3.append(" AND type_kh = ");
        stringBuilder3.append(cursor.getString(3));
        database2.QueryData(stringBuilder3.toString());
        database2 = Chatbox.this.db;
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append("DELETE FROM tbl_soctS WHERE ngay_nhan = '");
        stringBuilder3.append(cursor.getString(1));
        stringBuilder3.append("' AND ten_kh = '");
        stringBuilder3.append(cursor.getString(4));
        stringBuilder3.append("' AND so_tin_nhan = ");
        stringBuilder3.append(cursor.getString(7));
        stringBuilder3.append(" AND type_kh = ");
        stringBuilder3.append(cursor.getString(3));
        database2.QueryData(stringBuilder3.toString());
        Database database1 = Chatbox.this.db;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Update chat_database set del_sms = 0 WHERE ID = ");
        stringBuilder2.append(Chatbox.this.mID.get(Chatbox.this.position));
        database1.QueryData(stringBuilder2.toString());
        Chatbox.this.Xem_lv();
        Toast.makeText((Context)Chatbox.this, "x, 1).show();
        } else {
        Database database = Chatbox.this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Update chat_database set del_sms = 0 WHERE ID = ");
        stringBuilder.append(Chatbox.this.mID.get(Chatbox.this.position));
        database.QueryData(stringBuilder.toString());
        Chatbox.this.Xem_lv();
        }
        }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param1DialogInterface, int param1Int) {
        param1DialogInterface.cancel();
        }
        });
        builder.create().show();
        }
        return true;
        }

protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131427360);
        this.listView = (ListView)findViewById(2131230726);
        this.send = (ImageView)findViewById(2131231229);
        this.messageS = (EditText)findViewById(2131231107);
        new MainActivity();
        MainActivity.Get_date();
        this.TinXuly = new JSONObject();
        this.db = new Database((Context)this);
        Intent intent = getIntent();
        this.ten_kh = intent.getStringExtra("tenKH");
        this.so_dienthoai = intent.getStringExtra("so_dienthoai");
        this.app_use = intent.getStringExtra("app");
        this.send.setOnClickListener(new View.OnClickListener() {
public void onClick(View param1View) {
        String str = Chatbox.this.messageS.getText().toString();
        try {
        if (str.replace(" ", "").length() > 0) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        this();
        calendar.setTime(date);
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat();
        this("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat();
        this("HH:mm:ss");
        simpleDateFormat1.setTimeZone(TimeZone.getDefault());
        simpleDateFormat2.setTimeZone(TimeZone.getDefault());
        String str1 = simpleDateFormat1.format(calendar.getTime());
        String str2 = simpleDateFormat2.format(calendar.getTime());
        int i = Chatbox.this.app_use.indexOf("TL");
        if (i > -1) {
        try {
        str = Chatbox.this.messageS.getText().toString();
        MainActivity.sendMessage(Long.parseLong(Chatbox.this.so_dienthoai), str);
        Chatbox.this.GuiTinTrucTiep(str1, str2, Chatbox.this.ten_kh, str);
        Chatbox.this.messageS.setText("");
        } catch (Exception exception) {
        exception.printStackTrace();
        }
        } else {
        i = Chatbox.this.app_use.indexOf("sms");
        if (i == -1) {
        NotificationReader notificationReader = new NotificationReader();
        this();
        notificationReader.NotificationWearReader(Chatbox.this.ten_kh, str);
        str = Chatbox.this.messageS.getText().toString();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Insert into Chat_database Values( null,'");
        stringBuilder.append((String)exception);
        stringBuilder.append("', '");
        stringBuilder.append(str2);
        stringBuilder.append("', 2, '");
        stringBuilder.append(Chatbox.this.ten_kh);
        stringBuilder.append("', '");
        stringBuilder.append(Chatbox.this.so_dienthoai);
        stringBuilder.append("', '");
        stringBuilder.append(Chatbox.this.app_use);
        stringBuilder.append("','");
        stringBuilder.append(str);
        stringBuilder.append("',1)");
        String str3 = stringBuilder.toString();
        Chatbox.this.db.QueryData(str3);
        Chatbox.this.messageS.setText("");
        Chatbox.this.GuiTinTrucTiep((String)exception, str2, Chatbox.this.ten_kh, str);
        MainActivity.sms = true;
        Chatbox.this.Xem_lv();
        } else {
        str = Chatbox.this.messageS.getText().toString();
        Database database = Chatbox.this.db;
        StringBuilder stringBuilder = new StringBuilder();
        this();
        try {
        stringBuilder.append("Select * From tbl_kh_new Where ten_kh = '");
        stringBuilder.append(Chatbox.this.ten_kh);
        stringBuilder.append("'");
        Cursor cursor = database.GetData(stringBuilder.toString());
        cursor.moveToFirst();
        Chatbox.this.db.SendSMS(cursor.getString(1), str);
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append("Insert into Chat_database Values( null,'");
        stringBuilder1.append((String)exception);
        stringBuilder1.append("', '");
        stringBuilder1.append(str2);
        stringBuilder1.append("', 2, '");
        stringBuilder1.append(Chatbox.this.ten_kh);
        stringBuilder1.append("', '");
        stringBuilder1.append(Chatbox.this.so_dienthoai);
        stringBuilder1.append("', '");
        stringBuilder1.append(Chatbox.this.app_use);
        stringBuilder1.append("','");
        stringBuilder1.append(str);
        stringBuilder1.append("',1)");
        String str3 = stringBuilder1.toString();
        Chatbox.this.db.QueryData(str3);
        Chatbox.this.messageS.setText("");
        Chatbox.this.GuiTinTrucTiep((String)exception, str2, Chatbox.this.ten_kh, str);
        MainActivity.sms = true;
        Chatbox.this.Xem_lv();
        cursor.close();
        } catch (Exception exception1) {
        exception1.printStackTrace();
        }
        }
        }
        }
        } catch (Exception exception) {
        exception.printStackTrace();
        }
        }
        });
        this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
public boolean onItemLongClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
        Chatbox.this.position = param1Int;
        return false;
        }
        });
        Handler handler = new Handler();
        this.handler = handler;
        handler.postDelayed(this.runnable, 1000L);
        registerForContextMenu((View)this.listView);
        Xem_lv();
        }

public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo) {
        super.onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
        Database database = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select * From tbl_kh_new Where ten_kh = '");
        stringBuilder.append(this.ten_kh);
        stringBuilder.append("'");
        Cursor cursor = database.GetData(stringBuilder.toString());
        if (cursor.getCount() == 0) {
        paramContextMenu.add("Copy");
        paramContextMenu.add("X);
        } else {
        paramContextMenu.add("Stin");
        paramContextMenu.add("Xem chi ti);
        paramContextMenu.add("Copy");
        paramContextMenu.add("X);
        }
        cursor.close();
        }

public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacks(this.runnable);
        }

public void onResume() {
        Xem_lv();
        super.onResume();
        }

class Chat extends ArrayAdapter {
    public Chat(Context param1Context, int param1Int, List<String> param1List) {
        super(param1Context, param1Int, param1List);
    }

    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
        if (((String)Chatbox.this.type_kh.get(param1Int)).indexOf("2") > -1) {
            param1View = ((Activity)getContext()).getLayoutInflater().inflate(2131427442, null);
            TextView textView = (TextView)param1View.findViewById(2131230796);
            if (((String)Chatbox.this.mXulytin.get(param1Int)).indexOf("ok") == 0) {
                SpannableString spannableString = new SpannableString(Chatbox.this.nd_goc.get(param1Int));
                spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 0);
                textView.setText((CharSequence)spannableString);
            } else {
                textView.setText(Chatbox.this.nd_goc.get(param1Int));
            }
            ((TextView)param1View.findViewById(2131231283)).setText(Chatbox.this.gio_nhan.get(param1Int));
        }
        if (((String)Chatbox.this.type_kh.get(param1Int)).indexOf("1") > -1) {
            param1View = ((Activity)getContext()).getLayoutInflater().inflate(2131427441, null);
            TextView textView = (TextView)param1View.findViewById(2131230795);
            if (((String)Chatbox.this.mXulytin.get(param1Int)).indexOf("ok") == 0) {
                SpannableString spannableString = new SpannableString(Chatbox.this.nd_goc.get(param1Int));
                spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 0);
                textView.setText((CharSequence)spannableString);
            } else {
                textView.setText(Chatbox.this.nd_goc.get(param1Int));
            }
            ((TextView)param1View.findViewById(2131231282)).setText(Chatbox.this.gio_nhan.get(param1Int));
        }
        return param1View;
    }
}
}
