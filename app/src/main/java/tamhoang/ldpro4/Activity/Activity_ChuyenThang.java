package tamhoang.ldpro4.Activity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import tamhoang.ldpro4.Congthuc.BaseToolBarActivity;
import tamhoang.ldpro4.data.Database;

public class Activity_ChuyenThang extends BaseToolBarActivity {
    Button add_chuyen;

    Database db;

    ListView lv_chuyenthang;

    public List<String> nameChu = new ArrayList<String>();

    public List<String> nameKhach = new ArrayList<String>();

    RadioButton rad_chuyenthang;

    RadioButton rad_sauxuly;

    public List<String> sdtChu = new ArrayList<String>();

    public List<String> sdtKhach = new ArrayList<String>();

    public List<String> sdt_Chu = new ArrayList<String>();

    public List<String> sdt_KH = new ArrayList<String>();

    int sp_Chu;

    int sp_KH;

    Spinner spin_Chu;

    Spinner spin_KH;

    public List<String> ten_Chu = new ArrayList<String>();

    public List<String> ten_KH = new ArrayList<String>();

    protected int getLayoutId() {
        return 2131427361;
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131427361);
        this.db = new Database((Context)this);
        this.add_chuyen = (Button)findViewById(2131230765);
        this.spin_KH = (Spinner)findViewById(2131231262);
        this.spin_Chu = (Spinner)findViewById(2131231261);
        this.lv_chuyenthang = (ListView)findViewById(2131231089);
        this.nameKhach.clear();
        this.sdtKhach.clear();
        Cursor cursor3 = this.db.GetData("Select * From tbl_kh_new WHERE type_kh = 1 ORDER by ten_kh");
        if (cursor3 != null && cursor3.getCount() > 0) {
            while (cursor3.moveToNext()) {
                this.nameKhach.add(cursor3.getString(0));
                this.sdtKhach.add(cursor3.getString(1));
            }
            cursor3.close();
        }
        ArrayAdapter arrayAdapter2 = new ArrayAdapter((Context)this, 2131427455, this.nameKhach);
        this.spin_KH.setAdapter((SpinnerAdapter)arrayAdapter2);
        this.nameChu.clear();
        this.sdtChu.clear();
        Cursor cursor2 = this.db.GetData("Select * From tbl_kh_new WHERE type_kh <> 1 ORDER by ten_kh");
        if (cursor2 != null && cursor2.getCount() > 0) {
            while (cursor2.moveToNext()) {
                this.nameChu.add(cursor2.getString(0));
                this.sdtChu.add(cursor2.getString(1));
            }
            cursor2.close();
        }
        ArrayAdapter arrayAdapter1 = new ArrayAdapter((Context)this, 2131427455, this.nameChu);
        this.spin_Chu.setAdapter((SpinnerAdapter)arrayAdapter1);
        this.spin_KH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Activity_ChuyenThang.this.sp_KH = param1Int;
                Database database = Activity_ChuyenThang.this.db;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Select * from tbl_chuyenthang where sdt_nhan = '");
                stringBuilder.append(Activity_ChuyenThang.this.sdtKhach.get(Activity_ChuyenThang.this.sp_KH));
                stringBuilder.append("'");
                Cursor cursor = database.GetData(stringBuilder.toString());
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    param1Int = Activity_ChuyenThang.this.sdtChu.indexOf(cursor.getString(4));
                    Activity_ChuyenThang.this.spin_Chu.setSelection(param1Int);
                    cursor.close();
                }
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        this.spin_Chu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Activity_ChuyenThang.this.sp_Chu = param1Int;
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        this.add_chuyen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                try {
                    Database database = Activity_ChuyenThang.this.db;
                    StringBuilder stringBuilder = new StringBuilder();
                    this();
                    stringBuilder.append("Select * From tbl_chuyenthang WHERE kh_nhan = '");
                    stringBuilder.append(Activity_ChuyenThang.this.nameKhach.get(Activity_ChuyenThang.this.sp_KH));
                    stringBuilder.append("'");
                    Cursor cursor = database.GetData(stringBuilder.toString());
                    cursor.moveToFirst();
                    if (cursor.getCount() == 0 && Activity_ChuyenThang.this.sp_Chu > -1) {
                        StringBuilder stringBuilder1 = new StringBuilder();
                        this();
                        stringBuilder1.append("Insert into tbl_chuyenthang Values (null, '");
                        stringBuilder1.append(Activity_ChuyenThang.this.nameKhach.get(Activity_ChuyenThang.this.sp_KH));
                        stringBuilder1.append("', '");
                        stringBuilder1.append(Activity_ChuyenThang.this.sdtKhach.get(Activity_ChuyenThang.this.sp_KH));
                        stringBuilder1.append("', '");
                        stringBuilder1.append(Activity_ChuyenThang.this.nameChu.get(Activity_ChuyenThang.this.sp_Chu));
                        stringBuilder1.append("', '");
                        stringBuilder1.append(Activity_ChuyenThang.this.sdtChu.get(Activity_ChuyenThang.this.sp_Chu));
                        stringBuilder1.append("')");
                        String str = stringBuilder1.toString();
                        Activity_ChuyenThang.this.db.QueryData(str);
                        Toast.makeText((Context)Activity_ChuyenThang.this, "th, 1).show();
                    } else {
                        StringBuilder stringBuilder1 = new StringBuilder();
                        this();
                        stringBuilder1.append("UPDATE tbl_chuyenthang set kh_chuyen = '");
                        stringBuilder1.append(Activity_ChuyenThang.this.nameChu.get(Activity_ChuyenThang.this.sp_Chu));
                        stringBuilder1.append("', sdt_chuyen = '");
                        stringBuilder1.append(Activity_ChuyenThang.this.sdtChu.get(Activity_ChuyenThang.this.sp_Chu));
                        stringBuilder1.append("' Where sdt_nhan = '");
                        stringBuilder1.append(Activity_ChuyenThang.this.sdtKhach.get(Activity_ChuyenThang.this.sp_KH));
                        stringBuilder1.append("'");
                        String str = stringBuilder1.toString();
                        Activity_ChuyenThang.this.db.QueryData(str);
                        Toast.makeText((Context)Activity_ChuyenThang.this, "s, 1).show();
                    }
                    cursor.close();
                } catch (Exception exception) {
                    Toast.makeText((Context)Activity_ChuyenThang.this, "Thl, 1).show();
                }
                Activity_ChuyenThang.this.xem_lv();
            }
        });
        this.lv_chuyenthang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                int i = Activity_ChuyenThang.this.nameKhach.indexOf(Activity_ChuyenThang.this.ten_KH.get(param1Int));
                param1Int = Activity_ChuyenThang.this.nameChu.indexOf(Activity_ChuyenThang.this.ten_Chu.get(param1Int));
                Activity_ChuyenThang.this.spin_KH.setSelection(i);
                Activity_ChuyenThang.this.spin_Chu.setSelection(param1Int);
            }
        });
        this.rad_chuyenthang = (RadioButton)findViewById(2131231156);
        this.rad_sauxuly = (RadioButton)findViewById(2131231157);
        this.rad_chuyenthang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Activity_ChuyenThang.this.rad_chuyenthang.isChecked())
                    Activity_ChuyenThang.this.db.QueryData("UPDATE So_Om set Om_Xi3 = 0 WHERE ID = 13");
            }
        });
        this.rad_sauxuly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Activity_ChuyenThang.this.rad_sauxuly.isChecked())
                    Activity_ChuyenThang.this.db.QueryData("UPDATE So_Om set Om_Xi3 = 1 WHERE ID = 13");
            }
        });
        Cursor cursor1 = this.db.GetData("Select Om_Xi3 From so_om WHERE id = 13");
        if (cursor1 != null && cursor1.moveToFirst()) {
            if (cursor1.getInt(0) == 0) {
                this.rad_chuyenthang.setChecked(true);
                this.rad_sauxuly.setChecked(false);
            } else {
                this.rad_sauxuly.setChecked(true);
                this.rad_chuyenthang.setChecked(false);
            }
            if (cursor1 != null && !cursor1.isClosed())
                cursor1.close();
        }
        xem_lv();
    }

    public void xem_lv() {
        this.ten_KH.clear();
        this.sdt_KH.clear();
        this.ten_Chu.clear();
        this.sdt_Chu.clear();
        Cursor cursor = this.db.GetData("Select * From tbl_chuyenthang");
        while (cursor.moveToNext()) {
            this.ten_KH.add(cursor.getString(1));
            this.sdt_KH.add(cursor.getString(2));
            this.ten_Chu.add(cursor.getString(3));
            this.sdt_Chu.add(cursor.getString(4));
        }
        cursor.close();
        this.lv_chuyenthang.setAdapter((ListAdapter)new CTAdapter((Context)this, 2131427362, this.ten_KH));
    }

    class CTAdapter extends ArrayAdapter {
        public CTAdapter(Context param1Context, int param1Int, List<String> param1List) {
            super(param1Context, param1Int, param1List);
        }

        public View getView(final int position, View param1View, ViewGroup param1ViewGroup) {
            param1View = ((Activity)getContext()).getLayoutInflater().inflate(2131427362, null);
            TextView textView = (TextView)param1View.findViewById(2131231528);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(position + 1);
            stringBuilder.append("");
            textView.setText(stringBuilder.toString());
            ((TextView)param1View.findViewById(2131231467)).setText(Activity_ChuyenThang.this.ten_KH.get(position));
            ((TextView)param1View.findViewById(2131231415)).setText(Activity_ChuyenThang.this.ten_Chu.get(position));
            ((TextView)param1View.findViewById(2131231419)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View param2View) {
                    Database database = Activity_ChuyenThang.this.db;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Delete FROM tbl_chuyenthang WHERE sdt_nhan = '");
                    stringBuilder.append(Activity_ChuyenThang.this.sdt_KH.get(position));
                    stringBuilder.append("'");
                    database.QueryData(stringBuilder.toString());
                    Activity_ChuyenThang.this.xem_lv();
                }
            });
            return param1View;
        }
    }

    class null implements View.OnClickListener {
        public void onClick(View param1View) {
            Database database = Activity_ChuyenThang.this.db;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Delete FROM tbl_chuyenthang WHERE sdt_nhan = '");
            stringBuilder.append(Activity_ChuyenThang.this.sdt_KH.get(position));
            stringBuilder.append("'");
            database.QueryData(stringBuilder.toString());
            Activity_ChuyenThang.this.xem_lv();
        }
    }
}
