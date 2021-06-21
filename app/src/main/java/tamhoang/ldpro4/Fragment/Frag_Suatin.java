package tamhoang.ldpro4.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONException;
import tamhoang.ldpro4.Congthuc.Congthuc;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.data.Database;

public class Frag_Suatin extends Fragment {
    String CurDate;

    String Cur_date;

    Button btn_LoadTin;

    Button btn_suatin;

    Button btn_tai_All;

    Database db;

    EditText editTsuatin;

    boolean error;

    Handler handler;

    int lv_position = -1;

    ListView lv_suatin;

    public List<String> mApp = new ArrayList<String>();

    public List<String> mContact = new ArrayList<String>();

    public List<Integer> mID = new ArrayList<Integer>();

    public List<String> mMobile = new ArrayList<String>();

    public List<String> mND_DaSua = new ArrayList<String>();

    public List<String> mND_PhanTich = new ArrayList<String>();

    public List<String> mNgay = new ArrayList<String>();

    public List<String> mPhatHienLoi = new ArrayList<String>();

    public List<String> mSDT = new ArrayList<String>();

    public List<Integer> mSoTinNhan = new ArrayList<Integer>();

    public List<String> mTenKH = new ArrayList<String>();

    public List<String> mTinNhanGoc = new ArrayList<String>();

    public List<Integer> mTypeKH = new ArrayList<Integer>();

    public List<Integer> mType_kh = new ArrayList<Integer>();

    RadioButton radio_SuaTin;

    RadioButton radio_TaiTin;

    private Runnable runnable = new Runnable() {
        public void run() {
            new MainActivity();
            if (MainActivity.sms == true) {
                Frag_Suatin.this.xem_lv();
                MainActivity.sms = false;
            }
            Frag_Suatin.this.handler.postDelayed(this, 1000L);
        }
    };

    Spinner sp_TenKH;

    int spin_pointion = -1;

    String str;

    int type_kh;

    View v;

    private Runnable xulyTinnhan = new Runnable() {
        public void run() {
            Cursor cursor1;
            Cursor cursor2;
            StringBuilder stringBuilder1 = null;
            StringBuilder stringBuilder2 = null;
            Frag_Suatin.this.error = true;
            if (Frag_Suatin.this.editTsuatin.getText().toString().length() < 6) {
                Frag_Suatin.this.error = false;
            } else if (Frag_Suatin.this.lv_position >= 0 && Congthuc.CheckDate(MainActivity.myDate)) {
                stringBuilder1 = new StringBuilder();
                stringBuilder1.append("Update tbl_tinnhanS Set nd_phantich = '");
                stringBuilder1.append(Frag_Suatin.this.editTsuatin.getText().toString());
                stringBuilder1.append("', nd_sua = '");
                stringBuilder1.append(Frag_Suatin.this.editTsuatin.getText().toString());
                stringBuilder1.append("' WHERE id = ");
                stringBuilder1.append(Frag_Suatin.this.mID.get(Frag_Suatin.this.lv_position));
                String str = stringBuilder1.toString();
                Frag_Suatin.this.db.QueryData(str);
                Database database1 = Frag_Suatin.this.db;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Select type_kh From tbl_tinnhanS WHERE id = ");
                stringBuilder2.append(Frag_Suatin.this.mID.get(Frag_Suatin.this.lv_position));
                cursor1 = database1.GetData(stringBuilder2.toString());
                cursor1.moveToFirst();
                try {
                    Frag_Suatin.this.db.Update_TinNhanGoc(((Integer)Frag_Suatin.this.mID.get(Frag_Suatin.this.lv_position)).intValue(), cursor1.getInt(0));
                } catch (Exception exception) {
                    Database database = Frag_Suatin.this.db;
                    StringBuilder stringBuilder4 = new StringBuilder();
                    stringBuilder4.append("Update tbl_tinnhanS set phat_hien_loi = 'ko' WHERE id = ");
                    stringBuilder4.append(Frag_Suatin.this.mID.get(Frag_Suatin.this.lv_position));
                    database.QueryData(stringBuilder4.toString());
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("Delete From tbl_soctS WHERE ngay_nhan = '");
                    stringBuilder3.append(Frag_Suatin.this.mNgay.get(Frag_Suatin.this.lv_position));
                    stringBuilder3.append("' AND so_dienthoai = '");
                    stringBuilder3.append(Frag_Suatin.this.mSDT.get(Frag_Suatin.this.lv_position));
                    stringBuilder3.append("' AND so_tin_nhan = ");
                    stringBuilder3.append(Frag_Suatin.this.mSoTinNhan.get(Frag_Suatin.this.lv_position));
                    stringBuilder3.append(" AND type_kh = ");
                    stringBuilder3.append(cursor1.getString(0));
                    String str1 = stringBuilder3.toString();
                    Frag_Suatin.this.db.QueryData(str1);
                    Frag_Suatin.this.error = false;
                    Toast.makeText((Context)Frag_Suatin.this.getActivity(), "xra l, 1).show();
                }
                if (!Congthuc.CheckTime("18:30") && Frag_Suatin.this.Cur_date.contains(Frag_Suatin.this.CurDate))
                    Frag_Suatin.this.db.Gui_Tin_Nhan(((Integer)Frag_Suatin.this.mID.get(Frag_Suatin.this.lv_position)).intValue());
                Database database2 = Frag_Suatin.this.db;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Select * FROM tbl_tinnhanS Where id = ");
                stringBuilder.append(Frag_Suatin.this.mID.get(Frag_Suatin.this.lv_position));
                cursor2 = database2.GetData(stringBuilder.toString());
                cursor2.moveToFirst();
                if (cursor2.getString(11).indexOf("Khhi) > -1) {
                        String str1 = cursor2.getString(10).replace("ldpro", "<font color='#FF0000'>");
                Frag_Suatin.this.editTsuatin.setText((CharSequence)Html.fromHtml(str1));
                if (cursor2.getString(10).indexOf("ldpro") > -1)
                    try {
                        Frag_Suatin.this.editTsuatin.setSelection(str1.indexOf("<font"));
                    } catch (Exception exception) {}
                Frag_Suatin.this.error = false;
            } else {
                Frag_Suatin.this.editTsuatin.setText("");
                Frag_Suatin.this.xem_lv();
                if (Frag_Suatin.this.mND_DaSua.size() > 0) {
                    Frag_Suatin.this.lv_position = 0;
                    if (((String)Frag_Suatin.this.mPhatHienLoi.get(0)).indexOf("Khhi) > -1) {
                            String str1 = ((String)Frag_Suatin.this.mND_PhanTich.get(0)).replace("ldpro", "<font color='#FF0000'>");
                    Frag_Suatin.this.editTsuatin.setText((CharSequence)Html.fromHtml(str1));
                    int i = ((String)Frag_Suatin.this.mND_PhanTich.get(0)).indexOf("ldpro");
                    if (i > -1)
                        try {
                            Frag_Suatin.this.editTsuatin.setSelection(i);
                        } catch (Exception exception) {}
                    i = Frag_Suatin.this.mContact.indexOf(Frag_Suatin.this.mTenKH.get(0));
                    Frag_Suatin.this.sp_TenKH.setSelection(i);
                    Frag_Suatin.this.error = false;
                } else {
                    Frag_Suatin.this.editTsuatin.setText(Frag_Suatin.this.mND_DaSua.get(0));
                }
            } else {
                Frag_Suatin.this.lv_position = -1;
                Frag_Suatin.this.error = false;
            }
        }
    } else {
        Frag_Suatin.this.error = false;
        if (Congthuc.CheckDate(MainActivity.myDate)) {
            if (MainActivity.Acc_manager.length() == 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_Suatin.this.getActivity());
                builder.setTitle("Thb);
                        builder.setMessage("Kitra knInternet!");
                builder.setNegativeButton(", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                    param2DialogInterface.cancel();
                }
            });
            builder.create().show();
        } else {
            Frag_Suatin.this.Add_tin();
        }
    } else {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder();
            this((Context)Frag_Suatin.this.getActivity());
            builder.setTitle("Thb);
                    StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("hhsdphm\n\nHlihlhoS");
            stringBuilder.append(MainActivity.listKH.getString("k_tra"));
            stringBuilder.append(" gia h);
                    builder.setMessage(stringBuilder.toString());
            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                    param2DialogInterface.cancel();
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
        if (cursor1 != null)
                cursor1.close();
                if (cursor2 != null)
                cursor2.close();
                if (!Frag_Suatin.this.error) {
                Frag_Suatin.this.handler.removeCallbacks(Frag_Suatin.this.xulyTinnhan);
                } else {
                Frag_Suatin.this.handler.postDelayed(this, 300L);
                }
                }
                };

private void Add_tin() {
final MainActivity activity = new MainActivity();
        if (this.mContact.size() > 0 && this.editTsuatin.getText().toString().length() > 6) {
        new MainActivity();
final String mDate = MainActivity.Get_date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
final String mGionhan = simpleDateFormat.format(calendar.getTime());
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Select * From tbl_tinnhanS WHERE nd_goc = '");
        stringBuilder1.append(this.editTsuatin.getText().toString().replaceAll("'", "").trim());
        stringBuilder1.append("' AND ngay_nhan = '");
        stringBuilder1.append(str1);
        stringBuilder1.append("' AND so_dienthoai = '");
        stringBuilder1.append(this.mMobile.get(this.spin_pointion));
        stringBuilder1.append("'");
        String str2 = stringBuilder1.toString();
        this.str = str2;
        Cursor cursor1 = this.db.GetData(str2);
        cursor1.moveToFirst();
        Database database = this.db;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Select * From tbl_kh_new Where sdt = '");
        stringBuilder2.append(this.mMobile.get(this.spin_pointion));
        stringBuilder2.append("'");
final Cursor cur1 = database.GetData(stringBuilder2.toString());
        cursor2.moveToFirst();
        if (this.spin_pointion > -1 && cursor1.getCount() == 0) {
        if (cursor2.getInt(3) == 3) {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)getActivity());
        builder.setTitle("Chlotin nh);
        builder.setMessage("lkhvnhvchuythtin nhhay tin chuy);
        builder.setNeutralButton("Tin nh, new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param1DialogInterface, int param1Int) {
        Frag_Suatin.this.type_kh = 1;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Select max(so_tin_nhan) from tbl_tinnhanS WHERE ngay_nhan = '");
        stringBuilder1.append(mDate);
        stringBuilder1.append("' AND so_dienthoai = '");
        stringBuilder1.append(Frag_Suatin.this.mMobile.get(Frag_Suatin.this.spin_pointion));
        stringBuilder1.append("' AND type_kh = ");
        stringBuilder1.append(Frag_Suatin.this.type_kh);
        String str1 = stringBuilder1.toString();
        Cursor cursor1 = Frag_Suatin.this.db.GetData(str1);
        cursor1.moveToFirst();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Insert Into tbl_tinnhanS values (null, '");
        stringBuilder2.append(mDate);
        stringBuilder2.append("', '");
        stringBuilder2.append(mGionhan);
        stringBuilder2.append("', ");
        stringBuilder2.append(Frag_Suatin.this.type_kh);
        stringBuilder2.append(", '");
        stringBuilder2.append(Frag_Suatin.this.mContact.get(Frag_Suatin.this.spin_pointion));
        stringBuilder2.append("', '");
        stringBuilder2.append(Frag_Suatin.this.mMobile.get(Frag_Suatin.this.spin_pointion));
        stringBuilder2.append("', '");
        stringBuilder2.append(cur1.getString(2));
        stringBuilder2.append("', ");
        stringBuilder2.append(cursor1.getInt(0) + 1);
        stringBuilder2.append(", '");
        stringBuilder2.append(Frag_Suatin.this.editTsuatin.getText().toString().replace("'", " ").trim());
        stringBuilder2.append("', '");
        stringBuilder2.append(Frag_Suatin.this.editTsuatin.getText().toString().replace("'", " ").trim());
        stringBuilder2.append("', '");
        stringBuilder2.append(Frag_Suatin.this.editTsuatin.getText().toString().replace("'", " ").trim());
        stringBuilder2.append("', 'ko',0, 0, 0, null)");
        String str2 = stringBuilder2.toString();
        Frag_Suatin.this.db.QueryData(str2);
        Frag_Suatin.this.editTsuatin.setText("");
        Database database = Frag_Suatin.this.db;
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Select id From tbl_tinnhanS WHERE ngay_nhan = '");
        stringBuilder3.append(mDate);
        stringBuilder3.append("' AND so_dienthoai = '");
        stringBuilder3.append(Frag_Suatin.this.mMobile.get(Frag_Suatin.this.spin_pointion));
        stringBuilder3.append("' AND so_tin_nhan = ");
        stringBuilder3.append(cursor1.getInt(0) + 1);
        stringBuilder3.append(" AND type_kh = ");
        stringBuilder3.append(Frag_Suatin.this.type_kh);
        Cursor cursor2 = database.GetData(stringBuilder3.toString());
        cursor2.moveToFirst();
        if (Congthuc.CheckDate(MainActivity.myDate)) {
        try {
        Frag_Suatin.this.db.Update_TinNhanGoc(cursor2.getInt(0), cur1.getInt(3));
        } catch (Exception exception) {
        Database database1 = Frag_Suatin.this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Update tbl_tinnhanS set phat_hien_loi = 'ko' WHERE id = ");
        stringBuilder.append(cursor2.getInt(0));
        database1.QueryData(stringBuilder.toString());
        Frag_Suatin frag_Suatin = Frag_Suatin.this;
        stringBuilder = new StringBuilder();
        stringBuilder.append("Delete From tbl_soctS WHERE ngay_nhan = '");
        stringBuilder.append(mDate);
        stringBuilder.append("' AND so_dienthoai = '");
        stringBuilder.append(Frag_Suatin.this.mMobile.get(Frag_Suatin.this.spin_pointion));
        stringBuilder.append("' AND so_tin_nhan = ");
        stringBuilder.append(cursor1.getInt(0) + 1);
        stringBuilder.append(" AND type_kh = ");
        stringBuilder.append(Frag_Suatin.this.type_kh);
        frag_Suatin.str = stringBuilder.toString();
        Frag_Suatin.this.db.QueryData(Frag_Suatin.this.str);
        }
        } else if (MainActivity.Acc_manager.length() == 0) {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_Suatin.this.getActivity());
        builder.setTitle("Thb);
        builder.setMessage("Kitra knInternet!");
        builder.setNegativeButton(", new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param2DialogInterface, int param2Int) {
        param2DialogInterface.cancel();
        }
        });
        builder.create().show();
        } else {
        try {
        AlertDialog.Builder builder = new AlertDialog.Builder();
        this((Context)Frag_Suatin.this.getActivity());
        builder.setTitle("Thb);
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("hhsdphm\n\nHlihlhoS");
        stringBuilder.append(MainActivity.listKH.getString("k_tra"));
        stringBuilder.append(" gia h);
        builder.setMessage(stringBuilder.toString());
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param2DialogInterface, int param2Int) {
        param2DialogInterface.cancel();
        }
        };
        super(this);
        builder.setNegativeButton(", onClickListener);
        builder.create().show();
        } catch (JSONException jSONException) {
        jSONException.printStackTrace();
        }
        }
        cursor1.close();
        cur1.close();
        cursor2.close();
        param1DialogInterface.cancel();
        MainActivity.sms = true;
        }
        });
        builder.setPositiveButton("Tin Chuy, new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param1DialogInterface, int param1Int) {
        Frag_Suatin.this.type_kh = 2;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Select max(so_tin_nhan) from tbl_tinnhanS WHERE ngay_nhan = '");
        stringBuilder1.append(mDate);
        stringBuilder1.append("' AND so_dienthoai = '");
        stringBuilder1.append(Frag_Suatin.this.mMobile.get(Frag_Suatin.this.spin_pointion));
        stringBuilder1.append("' AND type_kh = ");
        stringBuilder1.append(Frag_Suatin.this.type_kh);
        String str1 = stringBuilder1.toString();
        Cursor cursor1 = Frag_Suatin.this.db.GetData(str1);
        cursor1.moveToFirst();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Insert Into tbl_tinnhanS values (null, '");
        stringBuilder2.append(mDate);
        stringBuilder2.append("', '");
        stringBuilder2.append(mGionhan);
        stringBuilder2.append("', ");
        stringBuilder2.append(Frag_Suatin.this.type_kh);
        stringBuilder2.append(", '");
        stringBuilder2.append(Frag_Suatin.this.mContact.get(Frag_Suatin.this.spin_pointion));
        stringBuilder2.append("', '");
        stringBuilder2.append(Frag_Suatin.this.mMobile.get(Frag_Suatin.this.spin_pointion));
        stringBuilder2.append("', '");
        stringBuilder2.append(cur1.getString(2));
        stringBuilder2.append("', ");
        stringBuilder2.append(cursor1.getInt(0) + 1);
        stringBuilder2.append(", '");
        stringBuilder2.append(Frag_Suatin.this.editTsuatin.getText().toString().replaceAll("'", " ").trim());
        stringBuilder2.append("', '");
        stringBuilder2.append(Frag_Suatin.this.editTsuatin.getText().toString().replaceAll("'", " ").trim());
        stringBuilder2.append("', '");
        stringBuilder2.append(Frag_Suatin.this.editTsuatin.getText().toString().replaceAll("'", " ").trim());
        stringBuilder2.append("', 'ko',0, 0, 0, null)");
        String str2 = stringBuilder2.toString();
        Frag_Suatin.this.db.QueryData(str2);
        Frag_Suatin.this.editTsuatin.setText("");
        Database database = Frag_Suatin.this.db;
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Select id From tbl_tinnhanS WHERE ngay_nhan = '");
        stringBuilder3.append(mDate);
        stringBuilder3.append("' AND so_dienthoai = '");
        stringBuilder3.append(Frag_Suatin.this.mMobile.get(Frag_Suatin.this.spin_pointion));
        stringBuilder3.append("' AND so_tin_nhan = ");
        stringBuilder3.append(cursor1.getInt(0) + 1);
        stringBuilder3.append(" AND type_kh = ");
        stringBuilder3.append(Frag_Suatin.this.type_kh);
        Cursor cursor2 = database.GetData(stringBuilder3.toString());
        cursor2.moveToFirst();
        if (Congthuc.CheckDate(MainActivity.myDate)) {
        try {
        Frag_Suatin.this.db.Update_TinNhanGoc(cursor2.getInt(0), cur1.getInt(3));
        } catch (Exception exception) {
        Database database1 = Frag_Suatin.this.db;
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_tinnhanS set phat_hien_loi = 'ko' WHERE id = ");
        stringBuilder4.append(cursor2.getInt(0));
        database1.QueryData(stringBuilder4.toString());
        Frag_Suatin frag_Suatin = Frag_Suatin.this;
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append("Delete From tbl_soctS WHERE ngay_nhan = '");
        stringBuilder5.append(mDate);
        stringBuilder5.append("' AND so_dienthoai = '");
        stringBuilder5.append(Frag_Suatin.this.mMobile.get(Frag_Suatin.this.spin_pointion));
        stringBuilder5.append("' AND so_tin_nhan = ");
        stringBuilder5.append(cursor1.getInt(0) + 1);
        stringBuilder5.append(" AND type_kh = ");
        stringBuilder5.append(Frag_Suatin.this.type_kh);
        frag_Suatin.str = stringBuilder5.toString();
        Frag_Suatin.this.db.QueryData(Frag_Suatin.this.str);
        Toast.makeText((Context)Frag_Suatin.this.getActivity(), "xra l, 1).show();
        }
        } else if (MainActivity.Acc_manager.length() == 0) {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_Suatin.this.getActivity());
        builder.setTitle("Thb);
        builder.setMessage("Kitra knInternet!");
        builder.setNegativeButton(", new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param2DialogInterface, int param2Int) {
        param2DialogInterface.cancel();
        }
        });
        builder.create().show();
        } else {
        try {
        AlertDialog.Builder builder = new AlertDialog.Builder();
        this((Context)Frag_Suatin.this.getActivity());
        builder.setTitle("Thb);
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("hhsdphm\n\nHlihlhoS");
        stringBuilder.append(MainActivity.listKH.getString("k_tra"));
        stringBuilder.append(" gia h);
        builder.setMessage(stringBuilder.toString());
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param2DialogInterface, int param2Int) {
        param2DialogInterface.cancel();
        }
        };
        super(this);
        builder.setNegativeButton(", onClickListener);
        builder.create().show();
        } catch (JSONException jSONException) {
        jSONException.printStackTrace();
        }
        }
        cursor1.close();
        cur1.close();
        cursor2.close();
        param1DialogInterface.cancel();
        MainActivity.sms = true;
        }
        });
        builder.create().show();
        } else {
        this.type_kh = cursor2.getInt(3);
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Select max(so_tin_nhan) from tbl_tinnhanS WHERE ngay_nhan = '");
        stringBuilder3.append(str1);
        stringBuilder3.append("' AND so_dienthoai = '");
        stringBuilder3.append(this.mMobile.get(this.spin_pointion));
        stringBuilder3.append("' AND type_kh = ");
        stringBuilder3.append(this.type_kh);
        String str = stringBuilder3.toString();
        Cursor cursor3 = this.db.GetData(str);
        cursor3.moveToFirst();
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append("Insert Into tbl_tinnhanS values (null, '");
        stringBuilder5.append(str1);
        stringBuilder5.append("', '");
        stringBuilder5.append(str3);
        stringBuilder5.append("', ");
        stringBuilder5.append(this.type_kh);
        stringBuilder5.append(", '");
        stringBuilder5.append(this.mContact.get(this.spin_pointion));
        stringBuilder5.append("', '");
        stringBuilder5.append(this.mMobile.get(this.spin_pointion));
        stringBuilder5.append("', '");
        stringBuilder5.append(cursor2.getString(2));
        stringBuilder5.append("', ");
        stringBuilder5.append(cursor3.getInt(0) + 1);
        stringBuilder5.append(", '");
        stringBuilder5.append(this.editTsuatin.getText().toString().replaceAll("'", " ").trim());
        stringBuilder5.append("', '");
        stringBuilder5.append(this.editTsuatin.getText().toString().replaceAll("'", " ").trim());
        stringBuilder5.append("', '");
        stringBuilder5.append(this.editTsuatin.getText().toString().replaceAll("'", " ").trim());
        stringBuilder5.append("', 'ko',0, 0, 0, null)");
        str3 = stringBuilder5.toString();
        this.db.QueryData(str3);
        this.editTsuatin.setText("");
        Database database1 = this.db;
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Select id From tbl_tinnhanS WHERE ngay_nhan = '");
        stringBuilder4.append(str1);
        stringBuilder4.append("' AND so_dienthoai = '");
        stringBuilder4.append(this.mMobile.get(this.spin_pointion));
        stringBuilder4.append("' AND so_tin_nhan = ");
        stringBuilder4.append(cursor3.getInt(0) + 1);
        stringBuilder4.append(" AND type_kh = ");
        stringBuilder4.append(this.type_kh);
        Cursor cursor4 = database1.GetData(stringBuilder4.toString());
        cursor4.moveToFirst();
        if (Congthuc.CheckDate(MainActivity.myDate)) {
        try {
        this.db.Update_TinNhanGoc(cursor4.getInt(0), cursor2.getInt(3));
        } catch (Exception exception) {
        Database database2 = this.db;
        StringBuilder stringBuilder7 = new StringBuilder();
        stringBuilder7.append("Update tbl_tinnhanS set phat_hien_loi = 'ko' WHERE id = ");
        stringBuilder7.append(cursor4.getInt(0));
        database2.QueryData(stringBuilder7.toString());
        StringBuilder stringBuilder6 = new StringBuilder();
        stringBuilder6.append("Delete From tbl_soctS WHERE ngay_nhan = '");
        stringBuilder6.append(str1);
        stringBuilder6.append("' AND so_dienthoai = '");
        stringBuilder6.append(this.mMobile.get(this.spin_pointion));
        stringBuilder6.append("' AND so_tin_nhan = ");
        stringBuilder6.append(cursor3.getInt(0) + 1);
        stringBuilder6.append(" AND type_kh = ");
        stringBuilder6.append(this.type_kh);
        str1 = stringBuilder6.toString();
        this.str = str1;
        this.db.QueryData(str1);
        Toast.makeText((Context)getActivity(), "xra l, 1).show();
        }
        } else if (MainActivity.Acc_manager.length() == 0) {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)getActivity());
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
        this((Context)getActivity());
        builder.setTitle("Thb);
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("hhsdphm\n\nHlihlhoS");
        stringBuilder.append(MainActivity.listKH.getString("k_tra"));
        stringBuilder.append(" gia h);
        builder.setMessage(stringBuilder.toString());
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
        MainActivity.sms = true;
        cursor3.close();
        cursor2.close();
        cursor4.close();
        }
        } else if (cursor1.getCount() > 0) {
        Toast.makeText((Context)getActivity(), "ctin ntrong CSDL!", 1).show();
        } else {
        Toast.makeText((Context)getActivity(), "Hchtkhh, 1).show();
        }
        xem_lv();
        if (cursor1 != null)
        cursor1.close();
        }
        }

public void control_RadioButton() {
        LinearLayout linearLayout1 = (LinearLayout)this.v.findViewById(2131231017);
        LinearLayout linearLayout2 = (LinearLayout)this.v.findViewById(2131231019);
        LinearLayout linearLayout3 = (LinearLayout)this.v.findViewById(2131230918);
        if (this.radio_SuaTin.isChecked()) {
        linearLayout3.setVisibility(0);
        linearLayout1.setVisibility(0);
        linearLayout2.setVisibility(0);
        this.btn_LoadTin.setVisibility(8);
        this.btn_suatin.setVisibility(0);
        this.editTsuatin.setVisibility(0);
        this.btn_tai_All.setVisibility(8);
        xem_lv();
        } else if (this.radio_TaiTin.isChecked()) {
        linearLayout3.setVisibility(0);
        linearLayout1.setVisibility(0);
        linearLayout2.setVisibility(0);
        this.btn_suatin.setVisibility(8);
        this.btn_LoadTin.setVisibility(0);
        this.editTsuatin.setVisibility(8);
        this.btn_tai_All.setVisibility(0);
        xem_lv();
        }
        }

public void getAllChat(int paramInt) {
        new MainActivity();
        String str1 = MainActivity.Get_date();
        boolean bool = false;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("DELETE FROM tbl_soctS WHERE ngay_nhan = '");
        stringBuilder2.append(str1);
        stringBuilder2.append("' AND so_dienthoai = '");
        stringBuilder2.append(this.mMobile.get(this.spin_pointion));
        stringBuilder2.append("'");
        String str3 = stringBuilder2.toString();
        this.db.QueryData(str3);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("DELETE FROM tbl_tinnhanS WHERE ngay_nhan = '");
        stringBuilder1.append(str1);
        stringBuilder1.append("' AND so_dienthoai = '");
        stringBuilder1.append(this.mMobile.get(this.spin_pointion));
        stringBuilder1.append("'");
        String str2 = stringBuilder1.toString();
        this.db.QueryData(str2);
        Database database = this.db;
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Select * From tbl_kh_new Where sdt = '");
        stringBuilder3.append(this.mMobile.get(this.spin_pointion));
        stringBuilder3.append("'");
        Cursor cursor1 = database.GetData(stringBuilder3.toString());
        cursor1.moveToFirst();
        if (paramInt == 1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select * From Chat_database Where ngay_nhan = '");
        stringBuilder.append(str1);
        stringBuilder.append("' AND ten_kh = '");
        stringBuilder.append(this.mContact.get(this.spin_pointion));
        stringBuilder.append("' and type_kh = 1");
        String str = stringBuilder.toString();
        } else if (paramInt == 2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select * From Chat_database Where ngay_nhan = '");
        stringBuilder.append(str1);
        stringBuilder.append("' AND ten_kh = '");
        stringBuilder.append(this.mContact.get(this.spin_pointion));
        stringBuilder.append("' and type_kh = 2");
        String str = stringBuilder.toString();
        } else if (paramInt == 3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select * From Chat_database Where ngay_nhan = '");
        stringBuilder.append(str1);
        stringBuilder.append("' AND ten_kh = '");
        stringBuilder.append(this.mContact.get(this.spin_pointion));
        stringBuilder.append("'");
        String str = stringBuilder.toString();
        } else {
        database = null;
        }
        Cursor cursor2 = this.db.GetData((String)database);
        SQLiteDatabase sQLiteDatabase = this.db.getWritableDatabase();
        DatabaseUtils.InsertHelper insertHelper = new DatabaseUtils.InsertHelper(sQLiteDatabase, "tbl_tinnhanS");
        sQLiteDatabase.beginTransaction();
        try {
        if (cursor2.getCount() > 0) {
        paramInt = bool;
        while (cursor2.moveToNext()) {
        paramInt++;
        insertHelper.prepareForInsert();
        insertHelper.bind(insertHelper.getColumnIndex("ngay_nhan"), str1);
        insertHelper.bind(insertHelper.getColumnIndex("gio_nhan"), cursor2.getString(2));
        insertHelper.bind(insertHelper.getColumnIndex("type_kh"), cursor2.getString(3));
        insertHelper.bind(insertHelper.getColumnIndex("ten_kh"), this.mContact.get(this.spin_pointion));
        insertHelper.bind(insertHelper.getColumnIndex("so_dienthoai"), this.mMobile.get(this.spin_pointion));
        insertHelper.bind(insertHelper.getColumnIndex("use_app"), cursor1.getInt(2));
        insertHelper.bind(insertHelper.getColumnIndex("so_tin_nhan"), paramInt);
        insertHelper.bind(insertHelper.getColumnIndex("nd_goc"), cursor2.getString(7));
        insertHelper.bind(insertHelper.getColumnIndex("nd_sua"), cursor2.getString(7));
        insertHelper.bind(insertHelper.getColumnIndex("nd_phantich"), cursor2.getString(7));
        insertHelper.bind(insertHelper.getColumnIndex("phat_hien_loi"), "ko");
        insertHelper.bind(insertHelper.getColumnIndex("tinh_tien"), 0);
        insertHelper.bind(insertHelper.getColumnIndex("ok_tn"), 0);
        insertHelper.bind(insertHelper.getColumnIndex("del_sms"), 0);
        insertHelper.execute();
        }
        }
        cursor2.close();
        sQLiteDatabase.setTransactionSuccessful();
        } catch (Exception exception) {
        exception.printStackTrace();
        } finally {}
        sQLiteDatabase.endTransaction();
        insertHelper.close();
        sQLiteDatabase.close();
        this.db.QueryData("Delete From tbl_tinnhanS where substr(nd_goc,0,7) = 'Ok Tin'");
        this.db.QueryData("Delete From tbl_tinnhanS where length(nd_goc) < 4");
        xem_lv();
        Toast.makeText((Context)getActivity(), "txong tin nh, 1).show();
        cursor1.close();
        }

public void getFullSms(String paramString) throws ParseException {
        // Byte code:
        //   0: ldc_w ' '
        //   3: astore_2
        //   4: ldc ''
        //   6: astore_3
        //   7: new tamhoang/ldpro4/MainActivity
        //   10: dup
        //   11: invokespecial <init> : ()V
        //   14: pop
        //   15: invokestatic Get_ngay : ()Ljava/lang/String;
        //   18: astore #4
        //   20: invokestatic Get_date : ()Ljava/lang/String;
        //   23: astore #5
        //   25: getstatic tamhoang/ldpro4/MainActivity.jSon_Setting : Lorg/json/JSONObject;
        //   28: ldc_w 'tin_trung'
        //   31: invokevirtual has : (Ljava/lang/String;)Z
        //   34: istore #6
        //   36: ldc '''
        //   38: astore #7
        //   40: iload #6
        //   42: ifne -> 121
        //   45: getstatic tamhoang/ldpro4/MainActivity.jSon_Setting : Lorg/json/JSONObject;
        //   48: ldc_w 'tin_trung'
        //   51: iconst_0
        //   52: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   55: pop
        //   56: aload_0
        //   57: getfield db : Ltamhoang/ldpro4/data/Database;
        //   60: astore #8
        //   62: new java/lang/StringBuilder
        //   65: astore #9
        //   67: aload #9
        //   69: invokespecial <init> : ()V
        //   72: aload #9
        //   74: ldc_w 'Update tbl_Setting set Setting = ''
        //   77: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   80: pop
        //   81: aload #9
        //   83: getstatic tamhoang/ldpro4/MainActivity.jSon_Setting : Lorg/json/JSONObject;
        //   86: invokevirtual toString : ()Ljava/lang/String;
        //   89: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   92: pop
        //   93: aload #9
        //   95: ldc '''
        //   97: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   100: pop
        //   101: aload #8
        //   103: aload #9
        //   105: invokevirtual toString : ()Ljava/lang/String;
        //   108: invokevirtual QueryData : (Ljava/lang/String;)V
        //   111: goto -> 121
        //   114: astore #8
        //   116: aload #8
        //   118: invokevirtual printStackTrace : ()V
        //   121: aload_0
        //   122: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   125: ldc_w 'android.permission.READ_SMS'
        //   128: invokestatic checkSelfPermission : (Landroid/content/Context;Ljava/lang/String;)I
        //   131: ifne -> 3815
        //   134: new java/text/SimpleDateFormat
        //   137: astore #8
        //   139: aload #8
        //   141: ldc_w 'yyyy-MM-dd'T'HH:mm:ss'
        //   144: invokespecial <init> : (Ljava/lang/String;)V
        //   147: new java/lang/StringBuilder
        //   150: astore #9
        //   152: aload #9
        //   154: invokespecial <init> : ()V
        //   157: aload #9
        //   159: aload #5
        //   161: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   164: pop
        //   165: aload #9
        //   167: ldc_w 'T00:00:00'
        //   170: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   173: pop
        //   174: aload #8
        //   176: aload #9
        //   178: invokevirtual toString : ()Ljava/lang/String;
        //   181: invokevirtual parse : (Ljava/lang/String;)Ljava/util/Date;
        //   184: astore #8
        //   186: new java/lang/StringBuilder
        //   189: astore #9
        //   191: aload #9
        //   193: invokespecial <init> : ()V
        //   196: aload #9
        //   198: ldc_w 'date>='
        //   201: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   204: pop
        //   205: aload #9
        //   207: aload #8
        //   209: invokevirtual getTime : ()J
        //   212: invokevirtual append : (J)Ljava/lang/StringBuilder;
        //   215: pop
        //   216: aload #9
        //   218: invokevirtual toString : ()Ljava/lang/String;
        //   221: astore #9
        //   223: aload_1
        //   224: ldc_w 'Full'
        //   227: invokevirtual indexOf : (Ljava/lang/String;)I
        //   230: istore #10
        //   232: iload #10
        //   234: iconst_m1
        //   235: if_icmple -> 346
        //   238: new java/lang/StringBuilder
        //   241: astore_1
        //   242: aload_1
        //   243: invokespecial <init> : ()V
        //   246: aload_1
        //   247: ldc_w 'DELETE FROM tbl_soctS WHERE ngay_nhan = ''
        //   250: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   253: pop
        //   254: aload_1
        //   255: aload #5
        //   257: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   260: pop
        //   261: aload_1
        //   262: ldc '''
        //   264: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   267: pop
        //   268: aload_1
        //   269: invokevirtual toString : ()Ljava/lang/String;
        //   272: astore_1
        //   273: aload_0
        //   274: getfield db : Ltamhoang/ldpro4/data/Database;
        //   277: aload_1
        //   278: invokevirtual QueryData : (Ljava/lang/String;)V
        //   281: new java/lang/StringBuilder
        //   284: astore_1
        //   285: aload_1
        //   286: invokespecial <init> : ()V
        //   289: aload_1
        //   290: ldc_w 'DELETE FROM tbl_tinnhanS WHERE ngay_nhan = ''
        //   293: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   296: pop
        //   297: aload_1
        //   298: aload #5
        //   300: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   303: pop
        //   304: aload_1
        //   305: ldc '''
        //   307: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   310: pop
        //   311: aload_1
        //   312: invokevirtual toString : ()Ljava/lang/String;
        //   315: astore_1
        //   316: aload_0
        //   317: getfield db : Ltamhoang/ldpro4/data/Database;
        //   320: aload_1
        //   321: invokevirtual QueryData : (Ljava/lang/String;)V
        //   324: aload_0
        //   325: getfield db : Ltamhoang/ldpro4/data/Database;
        //   328: ldc_w 'Select * From tbl_kh_new'
        //   331: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   334: astore_1
        //   335: goto -> 554
        //   338: astore_1
        //   339: goto -> 3804
        //   342: astore_1
        //   343: goto -> 3812
        //   346: new java/lang/StringBuilder
        //   349: astore_1
        //   350: aload_1
        //   351: invokespecial <init> : ()V
        //   354: aload_1
        //   355: ldc_w 'DELETE FROM tbl_soctS WHERE ngay_nhan = ''
        //   358: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   361: pop
        //   362: aload_1
        //   363: aload #5
        //   365: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   368: pop
        //   369: aload_1
        //   370: ldc_w '' AND so_dienthoai = ''
        //   373: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   376: pop
        //   377: aload_1
        //   378: aload_0
        //   379: getfield mMobile : Ljava/util/List;
        //   382: aload_0
        //   383: getfield spin_pointion : I
        //   386: invokeinterface get : (I)Ljava/lang/Object;
        //   391: checkcast java/lang/String
        //   394: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   397: pop
        //   398: aload_1
        //   399: ldc '''
        //   401: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   404: pop
        //   405: aload_1
        //   406: invokevirtual toString : ()Ljava/lang/String;
        //   409: astore_1
        //   410: aload_0
        //   411: getfield db : Ltamhoang/ldpro4/data/Database;
        //   414: aload_1
        //   415: invokevirtual QueryData : (Ljava/lang/String;)V
        //   418: new java/lang/StringBuilder
        //   421: astore_1
        //   422: aload_1
        //   423: invokespecial <init> : ()V
        //   426: aload_1
        //   427: ldc_w 'DELETE FROM tbl_tinnhanS WHERE ngay_nhan = ''
        //   430: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   433: pop
        //   434: aload_1
        //   435: aload #5
        //   437: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   440: pop
        //   441: aload_1
        //   442: ldc_w '' AND so_dienthoai = ''
        //   445: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   448: pop
        //   449: aload_1
        //   450: aload_0
        //   451: getfield mMobile : Ljava/util/List;
        //   454: aload_0
        //   455: getfield spin_pointion : I
        //   458: invokeinterface get : (I)Ljava/lang/Object;
        //   463: checkcast java/lang/String
        //   466: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   469: pop
        //   470: aload_1
        //   471: ldc '''
        //   473: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   476: pop
        //   477: aload_1
        //   478: invokevirtual toString : ()Ljava/lang/String;
        //   481: astore_1
        //   482: aload_0
        //   483: getfield db : Ltamhoang/ldpro4/data/Database;
        //   486: aload_1
        //   487: invokevirtual QueryData : (Ljava/lang/String;)V
        //   490: aload_0
        //   491: getfield db : Ltamhoang/ldpro4/data/Database;
        //   494: astore_1
        //   495: new java/lang/StringBuilder
        //   498: astore #11
        //   500: aload #11
        //   502: invokespecial <init> : ()V
        //   505: aload #11
        //   507: ldc_w 'Select * From tbl_kh_new Where sdt = ''
        //   510: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   513: pop
        //   514: aload #11
        //   516: aload_0
        //   517: getfield mMobile : Ljava/util/List;
        //   520: aload_0
        //   521: getfield spin_pointion : I
        //   524: invokeinterface get : (I)Ljava/lang/Object;
        //   529: checkcast java/lang/String
        //   532: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   535: pop
        //   536: aload #11
        //   538: ldc '''
        //   540: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   543: pop
        //   544: aload_1
        //   545: aload #11
        //   547: invokevirtual toString : ()Ljava/lang/String;
        //   550: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   553: astore_1
        //   554: new org/json/JSONObject
        //   557: astore #12
        //   559: aload #12
        //   561: invokespecial <init> : ()V
        //   564: aload_1
        //   565: invokeinterface moveToNext : ()Z
        //   570: istore #6
        //   572: ldc_w 'so_tn'
        //   575: astore #13
        //   577: ldc_w 'type_kh'
        //   580: astore #14
        //   582: iload #6
        //   584: ifeq -> 657
        //   587: new org/json/JSONObject
        //   590: astore #11
        //   592: aload #11
        //   594: invokespecial <init> : ()V
        //   597: aload #11
        //   599: ldc_w 'type_kh'
        //   602: aload_1
        //   603: iconst_3
        //   604: invokeinterface getString : (I)Ljava/lang/String;
        //   609: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   612: pop
        //   613: aload #11
        //   615: ldc_w 'ten_kh'
        //   618: aload_1
        //   619: iconst_0
        //   620: invokeinterface getString : (I)Ljava/lang/String;
        //   625: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   628: pop
        //   629: aload #11
        //   631: ldc_w 'so_tn'
        //   634: iconst_0
        //   635: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   638: pop
        //   639: aload #12
        //   641: aload_1
        //   642: iconst_1
        //   643: invokeinterface getString : (I)Ljava/lang/String;
        //   648: aload #11
        //   650: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   653: pop
        //   654: goto -> 564
        //   657: ldc_w 'content://sms'
        //   660: invokestatic parse : (Ljava/lang/String;)Landroid/net/Uri;
        //   663: astore_1
        //   664: aload_0
        //   665: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   668: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
        //   671: aload_1
        //   672: aconst_null
        //   673: aload #9
        //   675: aconst_null
        //   676: ldc_w 'date ASC'
        //   679: invokevirtual query : (Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   682: astore #15
        //   684: aload_0
        //   685: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   688: aload #15
        //   690: invokevirtual startManagingCursor : (Landroid/database/Cursor;)V
        //   693: aload #15
        //   695: invokeinterface getCount : ()I
        //   700: istore #16
        //   702: aload_0
        //   703: getfield db : Ltamhoang/ldpro4/data/Database;
        //   706: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
        //   709: astore #17
        //   711: ldc_w 'tin_trung'
        //   714: astore #18
        //   716: new android/database/DatabaseUtils$InsertHelper
        //   719: astore_1
        //   720: aload_1
        //   721: aload #17
        //   723: ldc_w 'tbl_tinnhanS'
        //   726: invokespecial <init> : (Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)V
        //   729: aload #15
        //   731: invokeinterface moveToFirst : ()Z
        //   736: istore #6
        //   738: ldc_w 'so_dienthoai'
        //   741: astore #19
        //   743: ldc_w 'ten_kh'
        //   746: astore #8
        //   748: ldc_w '3'
        //   751: astore #20
        //   753: ldc_w 'gio_nhan'
        //   756: astore #11
        //   758: ldc_w 'Ok Tin'
        //   761: astore #21
        //   763: ldc_w '1'
        //   766: astore #22
        //   768: ldc_w 'ngay_nhan'
        //   771: astore #23
        //   773: ldc_w '2'
        //   776: astore #9
        //   778: iload #6
        //   780: ifeq -> 2760
        //   783: aload #17
        //   785: invokevirtual beginTransaction : ()V
        //   788: aload #17
        //   790: astore #24
        //   792: iconst_0
        //   793: istore #25
        //   795: iload #25
        //   797: iload #16
        //   799: if_icmpge -> 2373
        //   802: aload #15
        //   804: aload #15
        //   806: ldc_w 'date'
        //   809: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
        //   814: invokeinterface getLong : (I)J
        //   819: invokestatic valueOf : (J)Ljava/lang/Long;
        //   822: astore #26
        //   824: new java/lang/StringBuilder
        //   827: astore #27
        //   829: aload #27
        //   831: invokespecial <init> : ()V
        //   834: new java/util/Date
        //   837: astore #28
        //   839: aload #28
        //   841: aload #26
        //   843: invokevirtual longValue : ()J
        //   846: invokespecial <init> : (J)V
        //   849: aload #27
        //   851: ldc_w 'dd/MM/yyyy HH:mm:ss'
        //   854: aload #28
        //   856: invokestatic format : (Ljava/lang/CharSequence;Ljava/util/Date;)Ljava/lang/CharSequence;
        //   859: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   862: pop
        //   863: aload #27
        //   865: aload_3
        //   866: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   869: pop
        //   870: aload #27
        //   872: invokevirtual toString : ()Ljava/lang/String;
        //   875: astore #28
        //   877: new java/lang/StringBuilder
        //   880: astore #27
        //   882: aload #27
        //   884: invokespecial <init> : ()V
        //   887: new java/util/Date
        //   890: astore #29
        //   892: aload #29
        //   894: aload #26
        //   896: invokevirtual longValue : ()J
        //   899: invokespecial <init> : (J)V
        //   902: aload #27
        //   904: ldc 'HH:mm:ss'
        //   906: aload #29
        //   908: invokestatic format : (Ljava/lang/CharSequence;Ljava/util/Date;)Ljava/lang/CharSequence;
        //   911: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   914: pop
        //   915: aload #27
        //   917: aload_3
        //   918: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   921: pop
        //   922: aload #27
        //   924: invokevirtual toString : ()Ljava/lang/String;
        //   927: astore #30
        //   929: aload #15
        //   931: aload #15
        //   933: ldc_w 'address'
        //   936: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
        //   941: invokeinterface getString : (I)Ljava/lang/String;
        //   946: aload_2
        //   947: aload_3
        //   948: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   951: astore #31
        //   953: aload #15
        //   955: aload #15
        //   957: ldc_w 'body'
        //   960: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
        //   965: invokeinterface getString : (I)Ljava/lang/String;
        //   970: invokevirtual toString : ()Ljava/lang/String;
        //   973: aload #7
        //   975: aload_2
        //   976: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   979: ldc_w '"'
        //   982: aload_2
        //   983: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   986: astore #32
        //   988: aload #15
        //   990: aload #15
        //   992: ldc_w 'type'
        //   995: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
        //   1000: invokeinterface getString : (I)Ljava/lang/String;
        //   1005: astore #33
        //   1007: aload #31
        //   1009: invokevirtual length : ()I
        //   1012: istore #10
        //   1014: iload #10
        //   1016: bipush #12
        //   1018: if_icmpge -> 1162
        //   1021: new java/lang/StringBuilder
        //   1024: astore #26
        //   1026: aload #26
        //   1028: invokespecial <init> : ()V
        //   1031: aload #26
        //   1033: ldc_w '+84'
        //   1036: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1039: pop
        //   1040: aload #26
        //   1042: aload #31
        //   1044: iconst_1
        //   1045: invokevirtual substring : (I)Ljava/lang/String;
        //   1048: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1051: pop
        //   1052: aload #26
        //   1054: invokevirtual toString : ()Ljava/lang/String;
        //   1057: astore #31
        //   1059: goto -> 1162
        //   1062: astore #8
        //   1064: aload_1
        //   1065: astore #9
        //   1067: aload #8
        //   1069: astore_1
        //   1070: aload #9
        //   1072: astore #8
        //   1074: goto -> 2743
        //   1077: astore #26
        //   1079: aload #19
        //   1081: astore #34
        //   1083: aload #5
        //   1085: astore #29
        //   1087: aload_1
        //   1088: astore #5
        //   1090: aload #13
        //   1092: astore #19
        //   1094: aload #14
        //   1096: astore #28
        //   1098: aload #20
        //   1100: astore #7
        //   1102: aload #22
        //   1104: astore #13
        //   1106: aload #8
        //   1108: astore_2
        //   1109: aload #11
        //   1111: astore #27
        //   1113: aload #23
        //   1115: astore #22
        //   1117: aload #9
        //   1119: astore #35
        //   1121: aload #18
        //   1123: astore #23
        //   1125: aload #34
        //   1127: astore_1
        //   1128: aload #26
        //   1130: astore #20
        //   1132: aload #21
        //   1134: astore #14
        //   1136: aload #29
        //   1138: astore #8
        //   1140: aload #7
        //   1142: astore #21
        //   1144: aload_2
        //   1145: astore #9
        //   1147: aload #19
        //   1149: astore #11
        //   1151: aload #35
        //   1153: astore #18
        //   1155: aload #27
        //   1157: astore #19
        //   1159: goto -> 2665
        //   1162: aload #12
        //   1164: aload #31
        //   1166: invokevirtual has : (Ljava/lang/String;)Z
        //   1169: ifeq -> 2140
        //   1172: aload #28
        //   1174: aload #4
        //   1176: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1179: istore #10
        //   1181: iload #10
        //   1183: iconst_m1
        //   1184: if_icmple -> 2140
        //   1187: aload #21
        //   1189: astore #35
        //   1191: aload #32
        //   1193: aload #35
        //   1195: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1198: iconst_m1
        //   1199: if_icmpne -> 2132
        //   1202: aload #12
        //   1204: aload #31
        //   1206: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   1209: astore #36
        //   1211: aload #14
        //   1213: astore #29
        //   1215: aload #36
        //   1217: aload #29
        //   1219: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1222: astore #28
        //   1224: aload #20
        //   1226: astore #34
        //   1228: aload #28
        //   1230: aload #34
        //   1232: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1235: istore #10
        //   1237: iload #10
        //   1239: iconst_m1
        //   1240: if_icmple -> 1249
        //   1243: iconst_1
        //   1244: istore #10
        //   1246: goto -> 1351
        //   1249: aload #36
        //   1251: aload #29
        //   1253: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1256: astore #26
        //   1258: aload #9
        //   1260: astore #28
        //   1262: aload #26
        //   1264: aload #28
        //   1266: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1269: istore #10
        //   1271: iload #10
        //   1273: iconst_m1
        //   1274: if_icmple -> 1307
        //   1277: aload #33
        //   1279: aload #28
        //   1281: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1284: istore #10
        //   1286: iload #10
        //   1288: iconst_m1
        //   1289: if_icmple -> 1307
        //   1292: iconst_1
        //   1293: istore #10
        //   1295: goto -> 1351
        //   1298: astore #20
        //   1300: aload #28
        //   1302: astore #26
        //   1304: goto -> 1416
        //   1307: aload #36
        //   1309: aload #29
        //   1311: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1314: aload #22
        //   1316: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1319: istore #10
        //   1321: iload #10
        //   1323: iconst_m1
        //   1324: if_icmple -> 1348
        //   1327: aload #33
        //   1329: aload #22
        //   1331: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1334: istore #10
        //   1336: iload #10
        //   1338: iconst_m1
        //   1339: if_icmple -> 1348
        //   1342: iconst_1
        //   1343: istore #10
        //   1345: goto -> 1351
        //   1348: iconst_0
        //   1349: istore #10
        //   1351: aload #9
        //   1353: astore #28
        //   1355: getstatic tamhoang/ldpro4/MainActivity.jSon_Setting : Lorg/json/JSONObject;
        //   1358: astore #26
        //   1360: aload #26
        //   1362: aload #18
        //   1364: invokevirtual getInt : (Ljava/lang/String;)I
        //   1367: istore #37
        //   1369: iload #37
        //   1371: iconst_1
        //   1372: if_icmpne -> 1481
        //   1375: aload #36
        //   1377: aload #32
        //   1379: invokevirtual has : (Ljava/lang/String;)Z
        //   1382: istore #6
        //   1384: iload #6
        //   1386: ifeq -> 1481
        //   1389: iconst_0
        //   1390: istore #10
        //   1392: goto -> 1481
        //   1395: astore #8
        //   1397: aload_1
        //   1398: astore #9
        //   1400: aload #8
        //   1402: astore_1
        //   1403: aload #9
        //   1405: astore #8
        //   1407: goto -> 2743
        //   1410: astore #20
        //   1412: aload #28
        //   1414: astore #26
        //   1416: aload #23
        //   1418: astore #27
        //   1420: aload #18
        //   1422: astore #23
        //   1424: aload #11
        //   1426: astore #7
        //   1428: aload #13
        //   1430: astore #11
        //   1432: aload_1
        //   1433: astore #18
        //   1435: aload #19
        //   1437: astore_1
        //   1438: aload #8
        //   1440: astore #9
        //   1442: aload #35
        //   1444: astore #14
        //   1446: aload #5
        //   1448: astore #8
        //   1450: aload #34
        //   1452: astore #21
        //   1454: aload #18
        //   1456: astore #5
        //   1458: aload #29
        //   1460: astore #28
        //   1462: aload #26
        //   1464: astore #18
        //   1466: aload #7
        //   1468: astore #19
        //   1470: aload #22
        //   1472: astore #13
        //   1474: aload #27
        //   1476: astore #22
        //   1478: goto -> 2665
        //   1481: iload #10
        //   1483: iconst_1
        //   1484: if_icmpne -> 1999
        //   1487: aload #13
        //   1489: astore #38
        //   1491: aload #36
        //   1493: aload #38
        //   1495: aload #36
        //   1497: aload #38
        //   1499: invokevirtual getInt : (Ljava/lang/String;)I
        //   1502: iconst_1
        //   1503: iadd
        //   1504: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1507: pop
        //   1508: aload #36
        //   1510: aload #32
        //   1512: aload #32
        //   1514: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1517: pop
        //   1518: aload_1
        //   1519: invokevirtual prepareForInsert : ()V
        //   1522: aload_1
        //   1523: astore #27
        //   1525: aload #22
        //   1527: astore #26
        //   1529: aload #18
        //   1531: astore #39
        //   1533: aload #27
        //   1535: aload #23
        //   1537: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1540: istore #10
        //   1542: aload #27
        //   1544: astore #26
        //   1546: aload #27
        //   1548: iload #10
        //   1550: aload #5
        //   1552: invokevirtual bind : (ILjava/lang/String;)V
        //   1555: aload #28
        //   1557: astore #9
        //   1559: aload #27
        //   1561: astore #26
        //   1563: aload #27
        //   1565: aload #27
        //   1567: aload #11
        //   1569: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1572: aload #30
        //   1574: invokevirtual bind : (ILjava/lang/String;)V
        //   1577: aload #27
        //   1579: astore #26
        //   1581: aload #27
        //   1583: aload #27
        //   1585: aload #29
        //   1587: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1590: aload #33
        //   1592: invokevirtual bind : (ILjava/lang/String;)V
        //   1595: aload #8
        //   1597: astore #28
        //   1599: aload #27
        //   1601: astore #26
        //   1603: aload #27
        //   1605: aload #28
        //   1607: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1610: istore #10
        //   1612: aload #27
        //   1614: astore #26
        //   1616: aload #27
        //   1618: iload #10
        //   1620: aload #36
        //   1622: aload #28
        //   1624: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1627: invokevirtual bind : (ILjava/lang/String;)V
        //   1630: aload #9
        //   1632: astore #28
        //   1634: aload #27
        //   1636: astore #26
        //   1638: aload #27
        //   1640: aload #27
        //   1642: aload #19
        //   1644: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1647: aload #31
        //   1649: invokevirtual bind : (ILjava/lang/String;)V
        //   1652: aload #9
        //   1654: astore #28
        //   1656: aload #27
        //   1658: astore #26
        //   1660: aload #27
        //   1662: aload #27
        //   1664: ldc_w 'use_app'
        //   1667: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1670: ldc_w 'sms'
        //   1673: invokevirtual bind : (ILjava/lang/String;)V
        //   1676: aload #9
        //   1678: astore #28
        //   1680: aload #27
        //   1682: astore #26
        //   1684: aload #27
        //   1686: aload #27
        //   1688: ldc_w 'so_tin_nhan'
        //   1691: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1694: aload #36
        //   1696: aload #38
        //   1698: invokevirtual getInt : (Ljava/lang/String;)I
        //   1701: invokevirtual bind : (II)V
        //   1704: aload #9
        //   1706: astore #28
        //   1708: aload #27
        //   1710: astore #26
        //   1712: aload #27
        //   1714: aload #27
        //   1716: ldc_w 'nd_goc'
        //   1719: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1722: aload #32
        //   1724: invokevirtual bind : (ILjava/lang/String;)V
        //   1727: aload #9
        //   1729: astore #28
        //   1731: aload #27
        //   1733: astore #26
        //   1735: aload #27
        //   1737: aload #27
        //   1739: ldc_w 'nd_sua'
        //   1742: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1745: aload #32
        //   1747: invokevirtual bind : (ILjava/lang/String;)V
        //   1750: aload #9
        //   1752: astore #28
        //   1754: aload #27
        //   1756: astore #26
        //   1758: aload #27
        //   1760: aload #27
        //   1762: ldc_w 'nd_phantich'
        //   1765: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1768: aload #32
        //   1770: invokevirtual bind : (ILjava/lang/String;)V
        //   1773: aload #9
        //   1775: astore #28
        //   1777: aload #27
        //   1779: astore #26
        //   1781: aload #27
        //   1783: aload #27
        //   1785: ldc_w 'phat_hien_loi'
        //   1788: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1791: ldc_w 'ko'
        //   1794: invokevirtual bind : (ILjava/lang/String;)V
        //   1797: aload #9
        //   1799: astore #28
        //   1801: aload #27
        //   1803: astore #26
        //   1805: aload #27
        //   1807: aload #27
        //   1809: ldc_w 'tinh_tien'
        //   1812: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1815: iconst_0
        //   1816: invokevirtual bind : (II)V
        //   1819: aload #9
        //   1821: astore #28
        //   1823: aload #27
        //   1825: astore #26
        //   1827: aload #27
        //   1829: aload #27
        //   1831: ldc_w 'ok_tn'
        //   1834: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1837: iconst_0
        //   1838: invokevirtual bind : (II)V
        //   1841: aload #9
        //   1843: astore #28
        //   1845: aload #27
        //   1847: astore #26
        //   1849: aload #27
        //   1851: aload #27
        //   1853: ldc_w 'del_sms'
        //   1856: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1859: iconst_0
        //   1860: invokevirtual bind : (II)V
        //   1863: aload #9
        //   1865: astore #28
        //   1867: aload #27
        //   1869: astore #26
        //   1871: aload #27
        //   1873: invokevirtual execute : ()J
        //   1876: pop2
        //   1877: aload #9
        //   1879: astore #28
        //   1881: aload #27
        //   1883: astore #26
        //   1885: aload #12
        //   1887: aload #31
        //   1889: aload #36
        //   1891: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1894: pop
        //   1895: goto -> 2140
        //   1898: astore #28
        //   1900: goto -> 1905
        //   1903: astore #28
        //   1905: aload #9
        //   1907: astore #26
        //   1909: goto -> 2499
        //   1912: astore #9
        //   1914: goto -> 1919
        //   1917: astore #9
        //   1919: aload #28
        //   1921: astore #26
        //   1923: aload #9
        //   1925: astore #28
        //   1927: goto -> 2499
        //   1930: astore_1
        //   1931: aload #27
        //   1933: astore #8
        //   1935: goto -> 2743
        //   1938: astore #20
        //   1940: aload #23
        //   1942: astore #22
        //   1944: aload #19
        //   1946: astore_1
        //   1947: aload #11
        //   1949: astore #19
        //   1951: aload #28
        //   1953: astore #18
        //   1955: aload #8
        //   1957: astore #9
        //   1959: aload #35
        //   1961: astore #14
        //   1963: aload #5
        //   1965: astore #8
        //   1967: aload #34
        //   1969: astore #21
        //   1971: aload #27
        //   1973: astore #5
        //   1975: aload #38
        //   1977: astore #11
        //   1979: aload #29
        //   1981: astore #28
        //   1983: aload #39
        //   1985: astore #23
        //   1987: aload #26
        //   1989: astore #13
        //   1991: goto -> 2665
        //   1994: astore #9
        //   1996: goto -> 2008
        //   1999: aload #28
        //   2001: astore #9
        //   2003: goto -> 2140
        //   2006: astore #9
        //   2008: aload #28
        //   2010: astore #26
        //   2012: goto -> 2025
        //   2015: astore #20
        //   2017: aload #9
        //   2019: astore #26
        //   2021: aload #20
        //   2023: astore #9
        //   2025: goto -> 2049
        //   2028: astore #9
        //   2030: aload_1
        //   2031: astore #8
        //   2033: aload #9
        //   2035: astore_1
        //   2036: goto -> 2743
        //   2039: astore #20
        //   2041: aload #9
        //   2043: astore #26
        //   2045: aload #20
        //   2047: astore #9
        //   2049: aload #23
        //   2051: astore #27
        //   2053: aload #18
        //   2055: astore #23
        //   2057: aload #11
        //   2059: astore #7
        //   2061: aload #13
        //   2063: astore #11
        //   2065: aload_1
        //   2066: astore #18
        //   2068: aload #8
        //   2070: astore #13
        //   2072: aload #5
        //   2074: astore #8
        //   2076: aload #19
        //   2078: astore_1
        //   2079: aload #9
        //   2081: astore #20
        //   2083: aload #35
        //   2085: astore #14
        //   2087: aload #34
        //   2089: astore #21
        //   2091: aload #13
        //   2093: astore #9
        //   2095: aload #18
        //   2097: astore #5
        //   2099: aload #29
        //   2101: astore #28
        //   2103: aload #26
        //   2105: astore #18
        //   2107: aload #7
        //   2109: astore #19
        //   2111: aload #22
        //   2113: astore #13
        //   2115: aload #27
        //   2117: astore #22
        //   2119: goto -> 2665
        //   2122: astore #28
        //   2124: goto -> 2170
        //   2127: astore #28
        //   2129: goto -> 2170
        //   2132: goto -> 2140
        //   2135: astore #28
        //   2137: goto -> 2170
        //   2140: aload_1
        //   2141: astore #27
        //   2143: aload #9
        //   2145: astore #28
        //   2147: aload #27
        //   2149: astore #26
        //   2151: aload #15
        //   2153: invokeinterface moveToNext : ()Z
        //   2158: pop
        //   2159: aload #27
        //   2161: astore_1
        //   2162: iinc #25, 1
        //   2165: goto -> 795
        //   2168: astore #28
        //   2170: goto -> 2180
        //   2173: astore #28
        //   2175: goto -> 2180
        //   2178: astore #28
        //   2180: aload #23
        //   2182: astore #26
        //   2184: aload #11
        //   2186: astore #23
        //   2188: aload #9
        //   2190: astore #27
        //   2192: aload #14
        //   2194: astore #29
        //   2196: aload #13
        //   2198: astore #11
        //   2200: aload_1
        //   2201: astore #13
        //   2203: aload #8
        //   2205: astore #9
        //   2207: aload #20
        //   2209: astore #35
        //   2211: aload #5
        //   2213: astore #8
        //   2215: aload #21
        //   2217: astore #14
        //   2219: aload #19
        //   2221: astore_1
        //   2222: aload #18
        //   2224: astore #34
        //   2226: goto -> 2240
        //   2229: astore #9
        //   2231: aload_1
        //   2232: astore #8
        //   2234: aload #9
        //   2236: astore_1
        //   2237: goto -> 2743
        //   2240: aload #28
        //   2242: astore #20
        //   2244: aload #35
        //   2246: astore #21
        //   2248: aload #13
        //   2250: astore #5
        //   2252: aload #29
        //   2254: astore #28
        //   2256: aload #27
        //   2258: astore #18
        //   2260: aload #23
        //   2262: astore #19
        //   2264: aload #34
        //   2266: astore #23
        //   2268: aload #22
        //   2270: astore #13
        //   2272: aload #26
        //   2274: astore #22
        //   2276: goto -> 2665
        //   2279: astore #9
        //   2281: aload_1
        //   2282: astore #8
        //   2284: aload #9
        //   2286: astore_1
        //   2287: goto -> 2743
        //   2290: astore #35
        //   2292: aload #20
        //   2294: astore #27
        //   2296: aload_1
        //   2297: astore #28
        //   2299: aload #19
        //   2301: astore_1
        //   2302: aload #8
        //   2304: astore #19
        //   2306: aload #5
        //   2308: astore #8
        //   2310: aload #23
        //   2312: astore #26
        //   2314: aload #14
        //   2316: astore #34
        //   2318: aload #18
        //   2320: astore #23
        //   2322: aload #11
        //   2324: astore #29
        //   2326: aload #9
        //   2328: astore #18
        //   2330: aload #35
        //   2332: astore #20
        //   2334: aload #21
        //   2336: astore #14
        //   2338: aload #27
        //   2340: astore #21
        //   2342: aload #19
        //   2344: astore #9
        //   2346: aload #28
        //   2348: astore #5
        //   2350: aload #13
        //   2352: astore #11
        //   2354: aload #34
        //   2356: astore #28
        //   2358: aload #29
        //   2360: astore #19
        //   2362: aload #22
        //   2364: astore #13
        //   2366: aload #26
        //   2368: astore #22
        //   2370: goto -> 2665
        //   2373: aload #22
        //   2375: astore #29
        //   2377: aload #13
        //   2379: astore_2
        //   2380: aload #20
        //   2382: astore_3
        //   2383: aload_1
        //   2384: astore #32
        //   2386: aload #8
        //   2388: astore #4
        //   2390: aload #5
        //   2392: astore #39
        //   2394: aload #23
        //   2396: astore #27
        //   2398: aload #21
        //   2400: astore #38
        //   2402: aload #14
        //   2404: astore #7
        //   2406: aload #18
        //   2408: astore #34
        //   2410: aload #19
        //   2412: astore #31
        //   2414: aload #11
        //   2416: astore #35
        //   2418: aload #9
        //   2420: astore #28
        //   2422: aload #32
        //   2424: astore #26
        //   2426: aload #24
        //   2428: invokevirtual setTransactionSuccessful : ()V
        //   2431: aload #24
        //   2433: invokevirtual endTransaction : ()V
        //   2436: aload #32
        //   2438: invokevirtual close : ()V
        //   2441: aload #31
        //   2443: astore_1
        //   2444: aload #38
        //   2446: astore #11
        //   2448: aload #39
        //   2450: astore #21
        //   2452: aload_3
        //   2453: astore #19
        //   2455: aload #4
        //   2457: astore #26
        //   2459: aload_2
        //   2460: astore #20
        //   2462: aload #7
        //   2464: astore #28
        //   2466: aload #9
        //   2468: astore #18
        //   2470: aload #35
        //   2472: astore #5
        //   2474: aload #34
        //   2476: astore #23
        //   2478: aload #29
        //   2480: astore #13
        //   2482: aload #27
        //   2484: astore #22
        //   2486: goto -> 2708
        //   2489: astore #9
        //   2491: aload #28
        //   2493: astore #26
        //   2495: aload #9
        //   2497: astore #28
        //   2499: aload #23
        //   2501: astore #27
        //   2503: aload #18
        //   2505: astore #23
        //   2507: aload #11
        //   2509: astore #29
        //   2511: aload #14
        //   2513: astore #18
        //   2515: aload #13
        //   2517: astore #11
        //   2519: aload_1
        //   2520: astore #13
        //   2522: aload #8
        //   2524: astore #9
        //   2526: aload #20
        //   2528: astore #34
        //   2530: aload #5
        //   2532: astore #8
        //   2534: aload #21
        //   2536: astore #14
        //   2538: aload #19
        //   2540: astore_1
        //   2541: aload #28
        //   2543: astore #20
        //   2545: aload #34
        //   2547: astore #21
        //   2549: aload #13
        //   2551: astore #5
        //   2553: aload #18
        //   2555: astore #28
        //   2557: aload #26
        //   2559: astore #18
        //   2561: aload #29
        //   2563: astore #19
        //   2565: aload #22
        //   2567: astore #13
        //   2569: aload #27
        //   2571: astore #22
        //   2573: goto -> 2665
        //   2576: astore #9
        //   2578: aload_1
        //   2579: astore #8
        //   2581: aload #9
        //   2583: astore_1
        //   2584: goto -> 2743
        //   2587: astore #26
        //   2589: ldc_w '1'
        //   2592: astore #13
        //   2594: ldc_w 'so_tn'
        //   2597: astore #27
        //   2599: ldc_w '3'
        //   2602: astore #21
        //   2604: aload_1
        //   2605: astore #9
        //   2607: aload #8
        //   2609: astore #20
        //   2611: aload #5
        //   2613: astore #8
        //   2615: ldc_w 'Ok Tin'
        //   2618: astore #14
        //   2620: ldc_w 'type_kh'
        //   2623: astore #28
        //   2625: aload #19
        //   2627: astore_1
        //   2628: ldc_w '2'
        //   2631: astore #5
        //   2633: aload #23
        //   2635: astore #22
        //   2637: aload #18
        //   2639: astore #23
        //   2641: aload #11
        //   2643: astore #19
        //   2645: aload #5
        //   2647: astore #18
        //   2649: aload #27
        //   2651: astore #11
        //   2653: aload #9
        //   2655: astore #5
        //   2657: aload #20
        //   2659: astore #9
        //   2661: aload #26
        //   2663: astore #20
        //   2665: aload #5
        //   2667: astore #26
        //   2669: aload #20
        //   2671: invokevirtual printStackTrace : ()V
        //   2674: aload #17
        //   2676: invokevirtual endTransaction : ()V
        //   2679: aload #5
        //   2681: invokevirtual close : ()V
        //   2684: aload #19
        //   2686: astore #5
        //   2688: aload #11
        //   2690: astore #20
        //   2692: aload #9
        //   2694: astore #26
        //   2696: aload #21
        //   2698: astore #19
        //   2700: aload #8
        //   2702: astore #21
        //   2704: aload #14
        //   2706: astore #11
        //   2708: aload #17
        //   2710: invokevirtual close : ()V
        //   2713: aload_1
        //   2714: astore #8
        //   2716: aload #21
        //   2718: astore #17
        //   2720: aload #20
        //   2722: astore #21
        //   2724: aload #18
        //   2726: astore_1
        //   2727: aload #5
        //   2729: astore #27
        //   2731: aload #13
        //   2733: astore #18
        //   2735: goto -> 2831
        //   2738: astore_1
        //   2739: aload #26
        //   2741: astore #8
        //   2743: aload #17
        //   2745: invokevirtual endTransaction : ()V
        //   2748: aload #8
        //   2750: invokevirtual close : ()V
        //   2753: aload #17
        //   2755: invokevirtual close : ()V
        //   2758: aload_1
        //   2759: athrow
        //   2760: ldc_w '1'
        //   2763: astore_1
        //   2764: ldc_w 'so_tn'
        //   2767: astore #21
        //   2769: ldc_w '3'
        //   2772: astore #13
        //   2774: aload #23
        //   2776: astore #22
        //   2778: ldc_w 'Ok Tin'
        //   2781: astore #20
        //   2783: ldc_w 'type_kh'
        //   2786: astore #28
        //   2788: aload #18
        //   2790: astore #23
        //   2792: aload #19
        //   2794: astore #9
        //   2796: ldc_w '2'
        //   2799: astore #19
        //   2801: aload_1
        //   2802: astore #18
        //   2804: aload #11
        //   2806: astore #27
        //   2808: aload #19
        //   2810: astore_1
        //   2811: aload #8
        //   2813: astore #26
        //   2815: aload #13
        //   2817: astore #19
        //   2819: aload #5
        //   2821: astore #17
        //   2823: aload #20
        //   2825: astore #11
        //   2827: aload #9
        //   2829: astore #8
        //   2831: new java/lang/StringBuilder
        //   2834: astore #9
        //   2836: aload #9
        //   2838: invokespecial <init> : ()V
        //   2841: aload #9
        //   2843: ldc_w 'Select * From Chat_database Where ngay_nhan = ''
        //   2846: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2849: pop
        //   2850: aload #9
        //   2852: aload #17
        //   2854: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2857: pop
        //   2858: aload #9
        //   2860: ldc_w '' And use_app <> 'sms''
        //   2863: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2866: pop
        //   2867: aload #9
        //   2869: invokevirtual toString : ()Ljava/lang/String;
        //   2872: astore #9
        //   2874: aload_0
        //   2875: getfield db : Ltamhoang/ldpro4/data/Database;
        //   2878: aload #9
        //   2880: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   2883: astore #9
        //   2885: aload_0
        //   2886: getfield db : Ltamhoang/ldpro4/data/Database;
        //   2889: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
        //   2892: astore #14
        //   2894: new android/database/DatabaseUtils$InsertHelper
        //   2897: astore #35
        //   2899: aload #35
        //   2901: aload #14
        //   2903: ldc_w 'tbl_tinnhanS'
        //   2906: invokespecial <init> : (Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)V
        //   2909: aload #9
        //   2911: astore #13
        //   2913: aload #14
        //   2915: astore #13
        //   2917: aload #15
        //   2919: astore #20
        //   2921: aload #9
        //   2923: astore #20
        //   2925: aload #14
        //   2927: astore #20
        //   2929: aload #15
        //   2931: astore #34
        //   2933: aload #14
        //   2935: invokevirtual beginTransaction : ()V
        //   2938: aload #22
        //   2940: astore #29
        //   2942: aload_1
        //   2943: astore #22
        //   2945: aload #15
        //   2947: astore #5
        //   2949: aload #14
        //   2951: astore_1
        //   2952: aload #19
        //   2954: astore #15
        //   2956: aload #11
        //   2958: astore #14
        //   2960: aload #8
        //   2962: astore #19
        //   2964: aload #9
        //   2966: astore #13
        //   2968: aload_1
        //   2969: astore #13
        //   2971: aload #5
        //   2973: astore #20
        //   2975: aload #9
        //   2977: astore #20
        //   2979: aload_1
        //   2980: astore #20
        //   2982: aload #5
        //   2984: astore #34
        //   2986: aload #9
        //   2988: invokeinterface moveToNext : ()Z
        //   2993: ifeq -> 3677
        //   2996: aload #9
        //   2998: astore #13
        //   3000: aload_1
        //   3001: astore #13
        //   3003: aload #5
        //   3005: astore #20
        //   3007: aload #9
        //   3009: astore #20
        //   3011: aload_1
        //   3012: astore #20
        //   3014: aload #5
        //   3016: astore #34
        //   3018: aload #9
        //   3020: iconst_1
        //   3021: invokeinterface getString : (I)Ljava/lang/String;
        //   3026: astore #11
        //   3028: aload #9
        //   3030: astore #13
        //   3032: aload_1
        //   3033: astore #13
        //   3035: aload #5
        //   3037: astore #20
        //   3039: aload #9
        //   3041: astore #20
        //   3043: aload_1
        //   3044: astore #20
        //   3046: aload #5
        //   3048: astore #34
        //   3050: aload #9
        //   3052: iconst_2
        //   3053: invokeinterface getString : (I)Ljava/lang/String;
        //   3058: astore #34
        //   3060: aload #9
        //   3062: iconst_4
        //   3063: invokeinterface getString : (I)Ljava/lang/String;
        //   3068: astore_2
        //   3069: aload_1
        //   3070: astore #8
        //   3072: aload #9
        //   3074: bipush #7
        //   3076: invokeinterface getString : (I)Ljava/lang/String;
        //   3081: astore #7
        //   3083: aload #19
        //   3085: astore #13
        //   3087: aload #9
        //   3089: iconst_3
        //   3090: invokeinterface getString : (I)Ljava/lang/String;
        //   3095: astore #4
        //   3097: aload #12
        //   3099: aload_2
        //   3100: invokevirtual has : (Ljava/lang/String;)Z
        //   3103: ifeq -> 3643
        //   3106: aload #11
        //   3108: aload #17
        //   3110: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3113: iconst_m1
        //   3114: if_icmple -> 3640
        //   3117: aload #7
        //   3119: aload #14
        //   3121: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3124: iconst_m1
        //   3125: if_icmpne -> 3640
        //   3128: aload #12
        //   3130: aload_2
        //   3131: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   3134: astore_3
        //   3135: aload_3
        //   3136: aload #28
        //   3138: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3141: aload #15
        //   3143: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3146: iconst_m1
        //   3147: if_icmple -> 3156
        //   3150: iconst_1
        //   3151: istore #10
        //   3153: goto -> 3259
        //   3156: aload_3
        //   3157: aload #28
        //   3159: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3162: aload #22
        //   3164: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3167: istore #10
        //   3169: iload #10
        //   3171: iconst_m1
        //   3172: if_icmple -> 3208
        //   3175: aload #4
        //   3177: aload #22
        //   3179: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3182: istore #10
        //   3184: iload #10
        //   3186: iconst_m1
        //   3187: if_icmple -> 3208
        //   3190: iconst_1
        //   3191: istore #10
        //   3193: goto -> 3259
        //   3196: astore_1
        //   3197: goto -> 3770
        //   3200: astore #9
        //   3202: aload #8
        //   3204: astore_1
        //   3205: goto -> 3723
        //   3208: aload_3
        //   3209: aload #28
        //   3211: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3214: astore #11
        //   3216: aload #18
        //   3218: astore #20
        //   3220: aload #11
        //   3222: aload #20
        //   3224: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3227: istore #10
        //   3229: iload #10
        //   3231: iconst_m1
        //   3232: if_icmple -> 3256
        //   3235: aload #8
        //   3237: astore #11
        //   3239: aload #4
        //   3241: aload #20
        //   3243: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3246: iconst_m1
        //   3247: if_icmple -> 3256
        //   3250: iconst_1
        //   3251: istore #10
        //   3253: goto -> 3259
        //   3256: iconst_0
        //   3257: istore #10
        //   3259: aload #8
        //   3261: astore #11
        //   3263: getstatic tamhoang/ldpro4/MainActivity.jSon_Setting : Lorg/json/JSONObject;
        //   3266: aload #23
        //   3268: invokevirtual getInt : (Ljava/lang/String;)I
        //   3271: istore #25
        //   3273: iload #25
        //   3275: iconst_1
        //   3276: if_icmpne -> 3298
        //   3279: aload #8
        //   3281: astore #11
        //   3283: aload_3
        //   3284: aload #7
        //   3286: invokevirtual has : (Ljava/lang/String;)Z
        //   3289: ifeq -> 3298
        //   3292: iconst_0
        //   3293: istore #10
        //   3295: goto -> 3298
        //   3298: iload #10
        //   3300: iconst_1
        //   3301: if_icmpne -> 3637
        //   3304: aload #8
        //   3306: astore #11
        //   3308: aload_3
        //   3309: aload #21
        //   3311: aload_3
        //   3312: aload #21
        //   3314: invokevirtual getInt : (Ljava/lang/String;)I
        //   3317: iconst_1
        //   3318: iadd
        //   3319: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   3322: pop
        //   3323: aload #8
        //   3325: astore #11
        //   3327: aload_3
        //   3328: aload #7
        //   3330: aload #7
        //   3332: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   3335: pop
        //   3336: aload #8
        //   3338: astore #11
        //   3340: aload #35
        //   3342: invokevirtual prepareForInsert : ()V
        //   3345: aload #8
        //   3347: astore #11
        //   3349: aload #35
        //   3351: aload #35
        //   3353: aload #29
        //   3355: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3358: aload #17
        //   3360: invokevirtual bind : (ILjava/lang/String;)V
        //   3363: aload #8
        //   3365: astore #11
        //   3367: aload #35
        //   3369: aload #35
        //   3371: aload #27
        //   3373: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3376: aload #34
        //   3378: invokevirtual bind : (ILjava/lang/String;)V
        //   3381: aload #8
        //   3383: astore #11
        //   3385: aload #35
        //   3387: aload #35
        //   3389: aload #28
        //   3391: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3394: aload #4
        //   3396: invokevirtual bind : (ILjava/lang/String;)V
        //   3399: aload #8
        //   3401: astore #11
        //   3403: aload #35
        //   3405: aload #35
        //   3407: aload #26
        //   3409: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3412: aload_3
        //   3413: aload #26
        //   3415: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3418: invokevirtual bind : (ILjava/lang/String;)V
        //   3421: aload #8
        //   3423: astore #11
        //   3425: aload #35
        //   3427: aload #35
        //   3429: aload #13
        //   3431: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3434: aload_2
        //   3435: invokevirtual bind : (ILjava/lang/String;)V
        //   3438: aload #8
        //   3440: astore #11
        //   3442: aload #35
        //   3444: aload #35
        //   3446: ldc_w 'use_app'
        //   3449: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3452: ldc_w 'sms'
        //   3455: invokevirtual bind : (ILjava/lang/String;)V
        //   3458: aload #8
        //   3460: astore #11
        //   3462: aload #35
        //   3464: aload #35
        //   3466: ldc_w 'so_tin_nhan'
        //   3469: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3472: aload_3
        //   3473: aload #21
        //   3475: invokevirtual getInt : (Ljava/lang/String;)I
        //   3478: invokevirtual bind : (II)V
        //   3481: aload #8
        //   3483: astore #11
        //   3485: aload #35
        //   3487: aload #35
        //   3489: ldc_w 'nd_goc'
        //   3492: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3495: aload #7
        //   3497: invokevirtual bind : (ILjava/lang/String;)V
        //   3500: aload #8
        //   3502: astore #11
        //   3504: aload #35
        //   3506: aload #35
        //   3508: ldc_w 'nd_sua'
        //   3511: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3514: aload #7
        //   3516: invokevirtual bind : (ILjava/lang/String;)V
        //   3519: aload #8
        //   3521: astore #11
        //   3523: aload #35
        //   3525: aload #35
        //   3527: ldc_w 'nd_phantich'
        //   3530: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3533: aload #7
        //   3535: invokevirtual bind : (ILjava/lang/String;)V
        //   3538: aload #8
        //   3540: astore #11
        //   3542: aload #35
        //   3544: aload #35
        //   3546: ldc_w 'phat_hien_loi'
        //   3549: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3552: ldc_w 'ko'
        //   3555: invokevirtual bind : (ILjava/lang/String;)V
        //   3558: aload #8
        //   3560: astore #11
        //   3562: aload #35
        //   3564: aload #35
        //   3566: ldc_w 'tinh_tien'
        //   3569: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3572: iconst_0
        //   3573: invokevirtual bind : (II)V
        //   3576: aload #8
        //   3578: astore #11
        //   3580: aload #35
        //   3582: aload #35
        //   3584: ldc_w 'ok_tn'
        //   3587: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3590: iconst_0
        //   3591: invokevirtual bind : (II)V
        //   3594: aload #8
        //   3596: astore #11
        //   3598: aload #35
        //   3600: aload #35
        //   3602: ldc_w 'del_sms'
        //   3605: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3608: iconst_0
        //   3609: invokevirtual bind : (II)V
        //   3612: aload #8
        //   3614: astore #11
        //   3616: aload #35
        //   3618: invokevirtual execute : ()J
        //   3621: pop2
        //   3622: aload #8
        //   3624: astore #11
        //   3626: aload #12
        //   3628: aload_2
        //   3629: aload_3
        //   3630: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   3633: pop
        //   3634: goto -> 3643
        //   3637: goto -> 3643
        //   3640: goto -> 3643
        //   3643: aload #8
        //   3645: astore_1
        //   3646: goto -> 2964
        //   3649: astore_1
        //   3650: goto -> 3770
        //   3653: astore #9
        //   3655: aload #8
        //   3657: astore_1
        //   3658: goto -> 3723
        //   3661: astore #9
        //   3663: aload_1
        //   3664: astore #8
        //   3666: aload #9
        //   3668: astore_1
        //   3669: goto -> 3770
        //   3672: astore #9
        //   3674: goto -> 3723
        //   3677: aload_1
        //   3678: astore #8
        //   3680: aload #8
        //   3682: astore #11
        //   3684: aload #8
        //   3686: invokevirtual setTransactionSuccessful : ()V
        //   3689: aload #8
        //   3691: invokevirtual endTransaction : ()V
        //   3694: aload #35
        //   3696: invokevirtual close : ()V
        //   3699: aload #8
        //   3701: astore_1
        //   3702: goto -> 3740
        //   3705: astore #9
        //   3707: goto -> 3723
        //   3710: astore_1
        //   3711: aload #13
        //   3713: astore #8
        //   3715: goto -> 3770
        //   3718: astore #9
        //   3720: aload #20
        //   3722: astore_1
        //   3723: aload_1
        //   3724: astore #11
        //   3726: aload #9
        //   3728: invokevirtual printStackTrace : ()V
        //   3731: aload_1
        //   3732: invokevirtual endTransaction : ()V
        //   3735: aload #35
        //   3737: invokevirtual close : ()V
        //   3740: aload_1
        //   3741: invokevirtual close : ()V
        //   3744: aload_0
        //   3745: invokevirtual xem_lv : ()V
        //   3748: aload_0
        //   3749: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3752: ldc_w 'txong tin nh
        //   3755: iconst_1
        //   3756: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3759: invokevirtual show : ()V
        //   3762: goto -> 3812
        //   3765: astore_1
        //   3766: aload #11
        //   3768: astore #8
        //   3770: aload #8
        //   3772: invokevirtual endTransaction : ()V
        //   3775: aload #35
        //   3777: invokevirtual close : ()V
        //   3780: aload #8
        //   3782: invokevirtual close : ()V
        //   3785: aload_1
        //   3786: athrow
        //   3787: astore_1
        //   3788: goto -> 3804
        //   3791: astore_1
        //   3792: goto -> 3812
        //   3795: astore_1
        //   3796: goto -> 3804
        //   3799: astore_1
        //   3800: goto -> 3812
        //   3803: astore_1
        //   3804: aload_1
        //   3805: invokevirtual printStackTrace : ()V
        //   3808: goto -> 3815
        //   3811: astore_1
        //   3812: goto -> 3815
        //   3815: return
        // Exception table:
        //   from	to	target	type
        //   45	111	114	org/json/JSONException
        //   134	232	3811	android/database/sqlite/SQLiteException
        //   134	232	3803	org/json/JSONException
        //   238	335	342	android/database/sqlite/SQLiteException
        //   238	335	338	org/json/JSONException
        //   346	554	3799	android/database/sqlite/SQLiteException
        //   346	554	3795	org/json/JSONException
        //   554	564	3799	android/database/sqlite/SQLiteException
        //   554	564	3795	org/json/JSONException
        //   564	572	3799	android/database/sqlite/SQLiteException
        //   564	572	3795	org/json/JSONException
        //   587	654	342	android/database/sqlite/SQLiteException
        //   587	654	338	org/json/JSONException
        //   657	711	3799	android/database/sqlite/SQLiteException
        //   657	711	3795	org/json/JSONException
        //   716	738	3799	android/database/sqlite/SQLiteException
        //   716	738	3795	org/json/JSONException
        //   783	788	2587	java/lang/Exception
        //   783	788	2576	finally
        //   802	834	2290	java/lang/Exception
        //   802	834	2279	finally
        //   834	839	2178	java/lang/Exception
        //   834	839	2229	finally
        //   839	892	2173	java/lang/Exception
        //   839	892	2229	finally
        //   892	1014	2168	java/lang/Exception
        //   892	1014	2229	finally
        //   1021	1059	1077	java/lang/Exception
        //   1021	1059	1062	finally
        //   1162	1181	2168	java/lang/Exception
        //   1162	1181	2229	finally
        //   1191	1211	2135	java/lang/Exception
        //   1191	1211	2229	finally
        //   1215	1224	2127	java/lang/Exception
        //   1215	1224	2229	finally
        //   1228	1237	2122	java/lang/Exception
        //   1228	1237	2229	finally
        //   1249	1258	2039	java/lang/Exception
        //   1249	1258	2028	finally
        //   1262	1271	2015	java/lang/Exception
        //   1262	1271	2028	finally
        //   1277	1286	1298	java/lang/Exception
        //   1277	1286	1395	finally
        //   1307	1321	2015	java/lang/Exception
        //   1307	1321	2028	finally
        //   1327	1336	1298	java/lang/Exception
        //   1327	1336	1395	finally
        //   1355	1360	2015	java/lang/Exception
        //   1355	1360	2028	finally
        //   1360	1369	2006	java/lang/Exception
        //   1360	1369	2028	finally
        //   1375	1384	1410	java/lang/Exception
        //   1375	1384	1395	finally
        //   1491	1522	1994	java/lang/Exception
        //   1491	1522	2028	finally
        //   1533	1542	1938	java/lang/Exception
        //   1533	1542	1930	finally
        //   1546	1555	1917	java/lang/Exception
        //   1546	1555	2738	finally
        //   1563	1577	1912	java/lang/Exception
        //   1563	1577	2738	finally
        //   1581	1595	1912	java/lang/Exception
        //   1581	1595	2738	finally
        //   1603	1612	1903	java/lang/Exception
        //   1603	1612	2738	finally
        //   1616	1630	1898	java/lang/Exception
        //   1616	1630	2738	finally
        //   1638	1652	2489	java/lang/Exception
        //   1638	1652	2738	finally
        //   1660	1676	2489	java/lang/Exception
        //   1660	1676	2738	finally
        //   1684	1704	2489	java/lang/Exception
        //   1684	1704	2738	finally
        //   1712	1727	2489	java/lang/Exception
        //   1712	1727	2738	finally
        //   1735	1750	2489	java/lang/Exception
        //   1735	1750	2738	finally
        //   1758	1773	2489	java/lang/Exception
        //   1758	1773	2738	finally
        //   1781	1797	2489	java/lang/Exception
        //   1781	1797	2738	finally
        //   1805	1819	2489	java/lang/Exception
        //   1805	1819	2738	finally
        //   1827	1841	2489	java/lang/Exception
        //   1827	1841	2738	finally
        //   1849	1863	2489	java/lang/Exception
        //   1849	1863	2738	finally
        //   1871	1877	2489	java/lang/Exception
        //   1871	1877	2738	finally
        //   1885	1895	2489	java/lang/Exception
        //   1885	1895	2738	finally
        //   2151	2159	2489	java/lang/Exception
        //   2151	2159	2738	finally
        //   2426	2431	2489	java/lang/Exception
        //   2426	2431	2738	finally
        //   2431	2441	3791	android/database/sqlite/SQLiteException
        //   2431	2441	3787	org/json/JSONException
        //   2669	2674	2738	finally
        //   2674	2684	3791	android/database/sqlite/SQLiteException
        //   2674	2684	3787	org/json/JSONException
        //   2708	2713	3791	android/database/sqlite/SQLiteException
        //   2708	2713	3787	org/json/JSONException
        //   2743	2758	3791	android/database/sqlite/SQLiteException
        //   2743	2758	3787	org/json/JSONException
        //   2758	2760	3791	android/database/sqlite/SQLiteException
        //   2758	2760	3787	org/json/JSONException
        //   2831	2909	3791	android/database/sqlite/SQLiteException
        //   2831	2909	3787	org/json/JSONException
        //   2933	2938	3718	java/lang/Exception
        //   2933	2938	3710	finally
        //   2986	2996	3718	java/lang/Exception
        //   2986	2996	3710	finally
        //   3018	3028	3718	java/lang/Exception
        //   3018	3028	3710	finally
        //   3050	3060	3718	java/lang/Exception
        //   3050	3060	3710	finally
        //   3060	3069	3672	java/lang/Exception
        //   3060	3069	3661	finally
        //   3072	3083	3653	java/lang/Exception
        //   3072	3083	3649	finally
        //   3087	3150	3653	java/lang/Exception
        //   3087	3150	3649	finally
        //   3156	3169	3653	java/lang/Exception
        //   3156	3169	3649	finally
        //   3175	3184	3200	java/lang/Exception
        //   3175	3184	3196	finally
        //   3208	3216	3653	java/lang/Exception
        //   3208	3216	3649	finally
        //   3220	3229	3653	java/lang/Exception
        //   3220	3229	3649	finally
        //   3239	3250	3705	java/lang/Exception
        //   3239	3250	3765	finally
        //   3263	3273	3705	java/lang/Exception
        //   3263	3273	3765	finally
        //   3283	3292	3705	java/lang/Exception
        //   3283	3292	3765	finally
        //   3308	3323	3705	java/lang/Exception
        //   3308	3323	3765	finally
        //   3327	3336	3705	java/lang/Exception
        //   3327	3336	3765	finally
        //   3340	3345	3705	java/lang/Exception
        //   3340	3345	3765	finally
        //   3349	3363	3705	java/lang/Exception
        //   3349	3363	3765	finally
        //   3367	3381	3705	java/lang/Exception
        //   3367	3381	3765	finally
        //   3385	3399	3705	java/lang/Exception
        //   3385	3399	3765	finally
        //   3403	3421	3705	java/lang/Exception
        //   3403	3421	3765	finally
        //   3425	3438	3705	java/lang/Exception
        //   3425	3438	3765	finally
        //   3442	3458	3705	java/lang/Exception
        //   3442	3458	3765	finally
        //   3462	3481	3705	java/lang/Exception
        //   3462	3481	3765	finally
        //   3485	3500	3705	java/lang/Exception
        //   3485	3500	3765	finally
        //   3504	3519	3705	java/lang/Exception
        //   3504	3519	3765	finally
        //   3523	3538	3705	java/lang/Exception
        //   3523	3538	3765	finally
        //   3542	3558	3705	java/lang/Exception
        //   3542	3558	3765	finally
        //   3562	3576	3705	java/lang/Exception
        //   3562	3576	3765	finally
        //   3580	3594	3705	java/lang/Exception
        //   3580	3594	3765	finally
        //   3598	3612	3705	java/lang/Exception
        //   3598	3612	3765	finally
        //   3616	3622	3705	java/lang/Exception
        //   3616	3622	3765	finally
        //   3626	3634	3705	java/lang/Exception
        //   3626	3634	3765	finally
        //   3684	3689	3705	java/lang/Exception
        //   3684	3689	3765	finally
        //   3689	3699	3791	android/database/sqlite/SQLiteException
        //   3689	3699	3787	org/json/JSONException
        //   3726	3731	3765	finally
        //   3731	3740	3791	android/database/sqlite/SQLiteException
        //   3731	3740	3787	org/json/JSONException
        //   3740	3744	3791	android/database/sqlite/SQLiteException
        //   3740	3744	3787	org/json/JSONException
        //   3744	3762	3791	android/database/sqlite/SQLiteException
        //   3744	3762	3787	org/json/JSONException
        //   3770	3785	3791	android/database/sqlite/SQLiteException
        //   3770	3785	3787	org/json/JSONException
        //   3785	3787	3791	android/database/sqlite/SQLiteException
        //   3785	3787	3787	org/json/JSONException
        }

public boolean onContextItemSelected(MenuItem paramMenuItem) {
        super.onContextItemSelected(paramMenuItem);
        if (paramMenuItem.getItemId() == 1) {
        if (this.lv_position >= 0) {
        Database database = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Delete FROM tbl_tinnhanS WHERE id = ");
        stringBuilder.append(this.mID.get(this.lv_position));
        database.QueryData(stringBuilder.toString());
        this.lv_position = -1;
        xem_lv();
        Toast.makeText((Context)getActivity(), "Xothc, 1).show();
        this.editTsuatin.setText("");
        }
        xem_lv();
        if (this.mND_DaSua.size() > 0) {
        this.lv_position = 0;
        if (((String)this.mPhatHienLoi.get(0)).indexOf("Khhi) > -1) {
        String str = ((String)this.mND_PhanTich.get(0)).replace("ldpro", "<font color='#FF0000'>");
        this.editTsuatin.setText((CharSequence)Html.fromHtml(str));
        int i = ((String)this.mND_PhanTich.get(0)).indexOf("ldpro");
        if (i > -1)
        try {
        this.editTsuatin.setSelection(i);
        } catch (Exception exception) {}
        i = this.mContact.indexOf(this.mTenKH.get(0));
        this.sp_TenKH.setSelection(i);
        this.error = false;
        } else {
        this.editTsuatin.setText(this.mND_DaSua.get(0));
        }
        } else {
        this.lv_position = -1;
        this.error = false;
        }
        }
        if (paramMenuItem.getItemId() == 2) {
        if (this.lv_position >= 0) {
        new MainActivity();
        String str = MainActivity.Get_date();
        Database database = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Delete FROM tbl_tinnhanS WHERE phat_hien_loi <> 'ok' And ngay_nhan = '");
        stringBuilder.append(str);
        stringBuilder.append("'");
        database.QueryData(stringBuilder.toString());
        this.lv_position = -1;
        xem_lv();
        Toast.makeText((Context)getActivity(), "Xothc, 1).show();
        this.editTsuatin.setText("");
        }
        xem_lv();
        }
        return true;
        }

public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo) {
        super.onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
        paramContextMenu.add(0, 1, 0, "Xtin n);
        }

public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.v = paramLayoutInflater.inflate(2131427435, paramViewGroup, false);
        this.db = new Database((Context)getActivity());
        this.btn_suatin = (Button)this.v.findViewById(2131230826);
        this.btn_LoadTin = (Button)this.v.findViewById(2131230821);
        this.editTsuatin = (EditText)this.v.findViewById(2131230916);
        this.btn_tai_All = (Button)this.v.findViewById(2131230828);
        this.sp_TenKH = (Spinner)this.v.findViewById(2131231265);
        this.radio_SuaTin = (RadioButton)this.v.findViewById(2131231179);
        this.radio_TaiTin = (RadioButton)this.v.findViewById(2131231170);
        this.lv_suatin = (ListView)this.v.findViewById(2131231100);
        Handler handler = new Handler();
        this.handler = handler;
        handler.postDelayed(this.runnable, 1000L);
        new MainActivity();
final String mDate = MainActivity.Get_date();
        this.btn_suatin.setOnClickListener(new View.OnClickListener() {
public void onClick(View param1View) {
        new MainActivity();
        Frag_Suatin.this.CurDate = MainActivity.Get_date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Frag_Suatin.this.Cur_date = simpleDateFormat.format(new Date());
        Frag_Suatin.this.handler = new Handler();
        Frag_Suatin.this.handler.postDelayed(Frag_Suatin.this.xulyTinnhan, 300L);
        }
        });
        this.lv_suatin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
        Frag_Suatin.this.lv_position = param1Int;
        String str = ((String)Frag_Suatin.this.mND_PhanTich.get(param1Int)).replace("ldpro", "<font color='#FF0000'>");
        Frag_Suatin.this.editTsuatin.setText((CharSequence)Html.fromHtml(str));
        int i = ((String)Frag_Suatin.this.mND_PhanTich.get(param1Int)).indexOf("ldpro");
        if (i > -1)
        try {
        Frag_Suatin.this.editTsuatin.setSelection(i);
        } catch (Exception exception) {}
        param1Int = Frag_Suatin.this.mContact.indexOf(Frag_Suatin.this.mTenKH.get(param1Int));
        Frag_Suatin.this.sp_TenKH.setSelection(param1Int);
        }
        });
        this.lv_suatin.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
public boolean onItemLongClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
        Frag_Suatin.this.lv_position = param1Int;
        return false;
        }
        });
        this.mContact.clear();
        this.mMobile.clear();
        this.mType_kh.clear();
        this.mApp.clear();
        try {
        Cursor cursor = this.db.GetData("Select * From tbl_kh_new Order by type_kh, ten_kh");
        while (cursor.moveToNext()) {
        this.mContact.add(cursor.getString(0));
        this.mMobile.add(cursor.getString(1));
        this.mApp.add(cursor.getString(2));
        this.mType_kh.add(Integer.valueOf(cursor.getInt(3)));
        }
        if (cursor != null)
        cursor.close();
        ArrayAdapter arrayAdapter = new ArrayAdapter();
        this((Context)getActivity(), 2131427455, this.mContact);
        this.sp_TenKH.setAdapter((SpinnerAdapter)arrayAdapter);
        if (this.mContact.size() > 0)
        this.sp_TenKH.setSelection(0);
        } catch (Exception exception) {
        Toast.makeText((Context)getActivity(), "copy dlibm, 1).show();
        }
        this.sp_TenKH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
        Frag_Suatin.this.spin_pointion = param1Int;
        }

public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        this.radio_SuaTin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
        Frag_Suatin.this.control_RadioButton();
        }
        });
        this.radio_TaiTin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
        Frag_Suatin.this.control_RadioButton();
        }
        });
        this.btn_LoadTin.setOnClickListener(new View.OnClickListener() {
public void onClick(View param1View) {
        if (Frag_Suatin.this.spin_pointion > -1 && Frag_Suatin.this.mContact.size() > 0) {
        if (((String)Frag_Suatin.this.mApp.get(Frag_Suatin.this.spin_pointion)).toString().indexOf("sms") > -1) {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_Suatin.this.getActivity());
        builder.setTitle("Tltin nhkhn);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param2DialogInterface, int param2Int) {
        try {
        Frag_Suatin.this.getFullSms(Frag_Suatin.this.mMobile.get(Frag_Suatin.this.spin_pointion));
        Database database = Frag_Suatin.this.db;
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Update chat_database set del_sms = 1 WHERE ten_kh = '");
        stringBuilder.append(Frag_Suatin.this.mContact.get(Frag_Suatin.this.spin_pointion));
        stringBuilder.append("' AND ngay_nhan = '");
        stringBuilder.append(mDate);
        stringBuilder.append("'");
        database.QueryData(stringBuilder.toString());
        } catch (ParseException parseException) {
        parseException.printStackTrace();
        }
        }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param2DialogInterface, int param2Int) {
        param2DialogInterface.cancel();
        }
        });
        builder.create().show();
        } else {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_Suatin.this.getActivity());
        builder.setTitle("Tltin nhkhn);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param2DialogInterface, int param2Int) {
        Frag_Suatin.this.getAllChat(((Integer)Frag_Suatin.this.mType_kh.get(Frag_Suatin.this.spin_pointion)).intValue());
        Database database = Frag_Suatin.this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Update chat_database set del_sms = 1 WHERE ten_kh = '");
        stringBuilder.append(Frag_Suatin.this.mContact.get(Frag_Suatin.this.spin_pointion));
        stringBuilder.append("' AND ngay_nhan = '");
        stringBuilder.append(mDate);
        stringBuilder.append("'");
        database.QueryData(stringBuilder.toString());
        }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param2DialogInterface, int param2Int) {
        param2DialogInterface.cancel();
        }
        });
        builder.create().show();
        }
        } else {
        Toast.makeText((Context)Frag_Suatin.this.getActivity(), "Chctkhh, 1).show();
        }
        }
        });
        this.btn_tai_All.setOnClickListener(new View.OnClickListener() {
public void onClick(View param1View) {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_Suatin.this.getActivity());
        builder.setTitle("Tltin nhctckh);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param2DialogInterface, int param2Int) {
        try {
        Frag_Suatin.this.getFullSms("Full");
        Database database = Frag_Suatin.this.db;
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Update chat_database set del_sms = 1 WHERE ngay_nhan = '");
        stringBuilder.append(mDate);
        stringBuilder.append("'");
        database.QueryData(stringBuilder.toString());
        } catch (ParseException parseException) {
        parseException.printStackTrace();
        }
        }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param2DialogInterface, int param2Int) {
        param2DialogInterface.cancel();
        }
        });
        builder.create().show();
        }
        });
        if (ContextCompat.checkSelfPermission(getContext(), "android.permission.READ_SMS") != 0)
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)getActivity(), "android.permission.READ_SMS")) {
        ActivityCompat.requestPermissions((Activity)getActivity(), new String[] { "android.permission.READ_SMS" }, 1);
        } else {
        ActivityCompat.requestPermissions((Activity)getActivity(), new String[] { "android.permission.READ_SMS" }, 1);
        }
        control_RadioButton();
        registerForContextMenu((View)this.lv_suatin);
        return this.v;
        }

public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacks(this.runnable);
        }

public void xem_lv() {
        new MainActivity();
        String str = MainActivity.Get_date();
        this.mID.clear();
        this.mNgay.clear();
        this.mSDT.clear();
        this.mTenKH.clear();
        this.mSoTinNhan.clear();
        this.mTinNhanGoc.clear();
        this.mND_DaSua.clear();
        this.mND_PhanTich.clear();
        this.mPhatHienLoi.clear();
        this.mTypeKH.clear();
        Database database = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from tbl_tinnhanS WHERE phat_hien_loi <> 'ok' AND ngay_nhan = '");
        stringBuilder.append(str);
        stringBuilder.append("'");
        Cursor cursor = database.GetData(stringBuilder.toString());
        if (cursor != null && cursor.getCount() > 0) {
        while (cursor.moveToNext()) {
        this.mID.add(Integer.valueOf(cursor.getInt(0)));
        this.mNgay.add(cursor.getString(1));
        this.mTenKH.add(cursor.getString(4));
        this.mSDT.add(cursor.getString(5));
        this.mSoTinNhan.add(Integer.valueOf(Integer.parseInt(cursor.getString(7))));
        this.mTinNhanGoc.add(cursor.getString(8));
        this.mND_DaSua.add(cursor.getString(9));
        this.mND_PhanTich.add(cursor.getString(10));
        this.mPhatHienLoi.add(cursor.getString(11));
        this.mTypeKH.add(Integer.valueOf(cursor.getInt(3)));
        }
        cursor.close();
        }
        if (getActivity() != null)
        this.lv_suatin.setAdapter((ListAdapter)new TNGAdapter((Context)getActivity(), 2131427437, this.mTinNhanGoc));
        }

class TNGAdapter extends ArrayAdapter {
    public TNGAdapter(Context param1Context, int param1Int, List<String> param1List) {
        super(param1Context, param1Int, param1List);
    }

    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
        ViewHolder viewHolder;
        if (param1View == null) {
            param1View = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427437, null);
            viewHolder = new ViewHolder();
            viewHolder.tview5 = (TextView)param1View.findViewById(2131231530);
            viewHolder.tview7 = (TextView)param1View.findViewById(2131231529);
            param1View.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)param1View.getTag();
        }
        viewHolder.tview5.setText(Frag_Suatin.this.mTinNhanGoc.get(param1Int));
        viewHolder.tview7.setText(Frag_Suatin.this.mPhatHienLoi.get(param1Int));
        return param1View;
    }

    class ViewHolder {
        TextView tview5;

        TextView tview7;
    }
}

class ViewHolder {
    TextView tview5;

    TextView tview7;
}
}
