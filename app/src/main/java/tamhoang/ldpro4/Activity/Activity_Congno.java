package tamhoang.ldpro4.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import tamhoang.ldpro4.Congthuc.BaseToolBarActivity;
import tamhoang.ldpro4.Congthuc.Congthuc;
import tamhoang.ldpro4.data.Database;

public class Activity_Congno extends BaseToolBarActivity {
    Button btn_congno;

    Database db;

    ListView lv_congno;

    public List<String> mKetQua = new ArrayList<String>();

    public List<String> mLuy_ke = new ArrayList<String>();

    public List<String> mNgay = new ArrayList<String>();

    public List<String> mNgayNhan = new ArrayList<String>();

    public List<String> mSdt = new ArrayList<String>();

    public List<String> mThanhToan = new ArrayList<String>();

    String message;

    TextView tv_tenKH;

    public void Congno_report_listview() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        this.mNgayNhan.clear();
        this.mSdt.clear();
        this.mNgay.clear();
        this.mKetQua.clear();
        this.mThanhToan.clear();
        this.mLuy_ke.clear();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select ngay_nhan, so_dienthoai,strftime('%d/%m/%Y',ngay_nhan) as Ngay\n, sum((the_loai <> 'tt') *ket_qua*(100 - diem_khachgiu)/100)/1000 as KQ \n, sum((the_loai = 'tt') *ket_qua)/1000 as TT \n, (Select sum(ket_qua*(100 - diem_khachgiu)/100) FROM tbl_soctS t2 \nWHERE tbl_soctS.ngay_nhan >= t2.ngay_nhan And tbl_soctS.ten_kh = t2.ten_kh)/1000 AS luy_ke \nFROM tbl_soctS \nWHERE ten_kh = '");
        stringBuilder.append(this.message);
        stringBuilder.append("' \nGROUP BY ngay_nhan ORDER BY ngay_nhan");
        String str = stringBuilder.toString();
        Cursor cursor = this.db.GetData(str);
        while (cursor.moveToNext()) {
            this.mNgayNhan.add(cursor.getString(0));
            this.mSdt.add(cursor.getString(1));
            this.mNgay.add(cursor.getString(2));
            this.mKetQua.add(decimalFormat.format(cursor.getDouble(3)));
            this.mThanhToan.add(decimalFormat.format(cursor.getDouble(4)));
            this.mLuy_ke.add(decimalFormat.format(cursor.getDouble(5)));
        }
        this.lv_congno.setAdapter((ListAdapter)new Congno_Adapter((Context)this, 2131427364, this.mNgay));
    }

    protected int getLayoutId() {
        return 2131427363;
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131427363);
        this.db = new Database((Context)this);
        this.lv_congno = (ListView)findViewById(2131231094);
        this.tv_tenKH = (TextView)findViewById(2131231532);
        this.btn_congno = (Button)findViewById(2131230817);
        this.message = getIntent().getStringExtra("tenKH");
        TextView textView = this.tv_tenKH;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Khh");
        stringBuilder.append(this.message);
        textView.setText(stringBuilder.toString());
        this.btn_congno.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (!Activity_Congno.this.isFinishing())
                    Activity_Congno.this.showDialog1(1);
            }
        });
        this.lv_congno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                if (!Activity_Congno.this.isFinishing())
                    Activity_Congno.this.showDialog2(param1Int);
            }
        });
        Congno_report_listview();
    }

    public void showDialog1(int paramInt) {
        final Dialog dialog = new Dialog((Context)this);
        dialog.setContentView(2131427410);
        dialog.getWindow().setLayout(-1, -2);
        final EditText edt_thanhtoan = (EditText)dialog.findViewById(2131230961);
        Button button = (Button)dialog.findViewById(2131230815);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        Database database = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select sum(ket_qua)/1000 From tbl_soctS WHere ten_kh = '");
        stringBuilder.append(this.message);
        stringBuilder.append("' AND the_loai = 'cn'");
        Cursor cursor = database.GetData(stringBuilder.toString());
        cursor.moveToFirst();
        editText.setText(decimalFormat.format(cursor.getDouble(0)));
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        editText.addTextChangedListener(new TextWatcher() {
            int len = 0;

            String str;

            public void afterTextChanged(Editable param1Editable) {}

            public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
                this.len = edt_thanhtoan.getText().toString().length();
            }

            public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
                param1CharSequence = edt_thanhtoan.getText().toString();
                this.str = (String)param1CharSequence;
                if (param1CharSequence.length() == 0) {
                    edt_thanhtoan.setText("0");
                } else if (this.len != this.str.length() && this.len > 2) {
                    try {
                        DecimalFormat decimalFormat = new DecimalFormat();
                        this("###,###");
                        param1CharSequence = this.str.replaceAll("[$,.]", "");
                        this.str = (String)param1CharSequence;
                        param1CharSequence = decimalFormat.format(Double.parseDouble((String)param1CharSequence));
                        this.str = (String)param1CharSequence;
                        edt_thanhtoan.setText(param1CharSequence);
                        edt_thanhtoan.setSelection(this.str.length());
                    } catch (Exception exception) {}
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (Congthuc.isNumeric(edt_thanhtoan.getText().toString().replaceAll("\\.", "").replace("-", ""))) {
                    Database database = Activity_Congno.this.db;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Select count(id) From tbl_soctS WHere ten_kh = '");
                    stringBuilder.append(Activity_Congno.this.message);
                    stringBuilder.append("' AND the_loai = 'cn'");
                    Cursor cursor = database.GetData(stringBuilder.toString());
                    cursor.moveToFirst();
                    if (cursor.getInt(0) == 0) {
                        Database database1 = Activity_Congno.this.db;
                        StringBuilder stringBuilder1 = new StringBuilder();
                        stringBuilder1.append("Select min(ngay_nhan), so_dienthoai From tbl_soctS Where ten_kh = '");
                        stringBuilder1.append(Activity_Congno.this.message);
                        stringBuilder1.append("'");
                        Cursor cursor1 = database1.GetData(stringBuilder1.toString());
                        cursor1.moveToFirst();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar calendar = Calendar.getInstance();
                        try {
                            calendar.setTime(simpleDateFormat.parse(cursor1.getString(0)));
                        } catch (Exception exception) {}
                        calendar.add(5, -1);
                        String str = simpleDateFormat.format(new Date(calendar.getTimeInMillis()));
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Insert Into tbl_soctS (ngay_nhan, ten_kh, so_dienthoai, the_loai, ket_qua, diem_quydoi) Values ('");
                        stringBuilder2.append(str);
                        stringBuilder2.append("','");
                        stringBuilder2.append(Activity_Congno.this.message);
                        stringBuilder2.append("','");
                        stringBuilder2.append(cursor1.getString(1));
                        stringBuilder2.append("', 'cn',");
                        stringBuilder2.append(edt_thanhtoan.getText().toString().replaceAll("\\.", ""));
                        stringBuilder2.append("000,1)");
                        str = stringBuilder2.toString();
                        Activity_Congno.this.db.QueryData(str);
                        Activity_Congno.this.Congno_report_listview();
                        if (cursor1 != null && !cursor1.isClosed())
                            cursor1.close();
                    } else {
                        StringBuilder stringBuilder1 = new StringBuilder();
                        stringBuilder1.append("Update tbl_soctS set ket_qua = ");
                        stringBuilder1.append(edt_thanhtoan.getText().toString().replaceAll("\\.", ""));
                        stringBuilder1.append("000 WHere ten_kh = '");
                        stringBuilder1.append(Activity_Congno.this.message);
                        stringBuilder1.append("' AND the_loai = 'cn'");
                        String str = stringBuilder1.toString();
                        Activity_Congno.this.db.QueryData(str);
                        Activity_Congno.this.Congno_report_listview();
                    }
                    dialog.cancel();
                }
            }
        });
        dialog.setCancelable(true);
        dialog.show();
    }

    public void showDialog2(final int poin) {
        final Dialog dialog = new Dialog((Context)this);
        dialog.setContentView(2131427411);
        dialog.getWindow().setLayout(-1, -2);
        TextView textView = (TextView)dialog.findViewById(2131231474);
        final EditText edt_thanhtoan = (EditText)dialog.findViewById(2131230961);
        Button button = (Button)dialog.findViewById(2131230815);
        textView.setText(this.mNgay.get(poin));
        editText.addTextChangedListener(new TextWatcher() {
            int len = 0;

            String str;

            public void afterTextChanged(Editable param1Editable) {}

            public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
                this.len = edt_thanhtoan.getText().toString().length();
            }

            public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
                param1CharSequence = edt_thanhtoan.getText().toString();
                this.str = (String)param1CharSequence;
                if (param1CharSequence.length() == 0) {
                    edt_thanhtoan.setText("0");
                } else if (this.len != this.str.length() && this.len > 2) {
                    try {
                        DecimalFormat decimalFormat = new DecimalFormat();
                        this("###,###");
                        String str2 = this.str.replaceAll("[$,.]", "");
                        this.str = str2;
                        String str1 = decimalFormat.format(Double.parseDouble(str2));
                        this.str = str1;
                        edt_thanhtoan.setText(str1);
                        edt_thanhtoan.setSelection(this.str.length());
                    } catch (Exception exception) {}
                }
            }
        });
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        Database database = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select sum(ket_qua)/1000 From tbl_soctS WHere ten_kh = '");
        stringBuilder.append(this.message);
        stringBuilder.append("' AND the_loai = 'tt' And ngay_nhan = '");
        stringBuilder.append(this.mNgayNhan.get(poin));
        stringBuilder.append("'");
        Cursor cursor = database.GetData(stringBuilder.toString());
        cursor.moveToFirst();
        editText.setText(decimalFormat.format(cursor.getDouble(0)));
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (Congthuc.isNumeric(edt_thanhtoan.getText().toString().replaceAll("\\.", "").replace("-", ""))) {
                    Database database = Activity_Congno.this.db;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Select count(id) From tbl_soctS WHere ten_kh = '");
                    stringBuilder.append(Activity_Congno.this.message);
                    stringBuilder.append("' AND the_loai = 'tt' AND ngay_nhan = '");
                    stringBuilder.append(Activity_Congno.this.mNgayNhan.get(poin));
                    stringBuilder.append("'");
                    Cursor cursor = database.GetData(stringBuilder.toString());
                    cursor.moveToFirst();
                    if (cursor.getInt(0) == 0) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Insert Into tbl_soctS (ngay_nhan, ten_kh, so_dienthoai, the_loai, ket_qua, diem_quydoi) Values ('");
                        stringBuilder.append(Activity_Congno.this.mNgayNhan.get(poin));
                        stringBuilder.append("','");
                        stringBuilder.append(Activity_Congno.this.message);
                        stringBuilder.append("','");
                        stringBuilder.append(Activity_Congno.this.mSdt.get(poin));
                        stringBuilder.append("', 'tt',");
                        stringBuilder.append(edt_thanhtoan.getText().toString().replaceAll("\\.", ""));
                        stringBuilder.append("000,1)");
                        String str = stringBuilder.toString();
                        Activity_Congno.this.db.QueryData(str);
                        Activity_Congno.this.Congno_report_listview();
                    } else {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Update tbl_soctS set ket_qua = ");
                        stringBuilder.append(edt_thanhtoan.getText().toString().replaceAll("\\.", ""));
                        stringBuilder.append("000 WHere ten_kh = '");
                        stringBuilder.append(Activity_Congno.this.message);
                        stringBuilder.append("' AND the_loai = 'tt' AND ngay_nhan = '");
                        stringBuilder.append(Activity_Congno.this.mNgayNhan.get(poin));
                        stringBuilder.append("'");
                        String str = stringBuilder.toString();
                        Activity_Congno.this.db.QueryData(str);
                        Activity_Congno.this.Congno_report_listview();
                    }
                    if (cursor != null && !cursor.isClosed())
                        cursor.close();
                    dialog.cancel();
                }
            }
        });
        dialog.setCancelable(true);
        dialog.show();
    }

    class Congno_Adapter extends ArrayAdapter {
        public Congno_Adapter(Context param1Context, int param1Int, List<String> param1List) {
            super(param1Context, param1Int, param1List);
        }

        public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
            param1View = ((Activity)getContext()).getLayoutInflater().inflate(2131427364, null);
            ((TextView)param1View.findViewById(2131231473)).setText(Activity_Congno.this.mNgay.get(param1Int));
            ((TextView)param1View.findViewById(2131231525)).setText(Activity_Congno.this.mKetQua.get(param1Int));
            ((TextView)param1View.findViewById(2131231535)).setText(Activity_Congno.this.mThanhToan.get(param1Int));
            ((TextView)param1View.findViewById(2131231468)).setText(Activity_Congno.this.mLuy_ke.get(param1Int));
            return param1View;
        }
    }
}
