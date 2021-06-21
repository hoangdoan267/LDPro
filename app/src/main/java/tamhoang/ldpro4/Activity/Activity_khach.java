package tamhoang.ldpro4.Activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import tamhoang.ldpro4.Congthuc.BaseToolBarActivity;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.data.Database;

public class Activity_khach extends BaseToolBarActivity {
    String DangXuat;

    Database db;

    ListView lv_khach;

    private List<String> mDiem = new ArrayList<String>();

    private List<String> mDiemGiu = new ArrayList<String>();

    private List<Integer> mNhay = new ArrayList<Integer>();

    private List<String> mSo = new ArrayList<String>();

    private List<String> mThanhTien = new ArrayList<String>();

    String message;

    RadioButton radio_bc;

    RadioButton radio_de;

    RadioButton radio_lo;

    RadioButton radio_xi;

    TextView textView;

    protected int getLayoutId() {
        return 2131427367;
    }

    public void init() {
        this.radio_de = (RadioButton)findViewById(2131231172);
        this.radio_lo = (RadioButton)findViewById(2131231178);
        this.radio_xi = (RadioButton)findViewById(2131231180);
        this.radio_bc = (RadioButton)findViewById(2131231171);
        this.lv_khach = (ListView)findViewById(2131231095);
        this.textView = (TextView)findViewById(2131231307);
    }

    public void lv_Khach() {
        new MainActivity();
        String str = MainActivity.Get_date();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select so_chon, sum(diem_quydoi) as diem, sum(diem_khachgiu*diem_quydoi)/100 as diemgiu, sum((100-diem_khachgiu)*diem_quydoi/100) as diemton, so_nhay From tbl_soctS where ngay_nhan = '");
        stringBuilder.append(str);
        stringBuilder.append("' AND ten_kh = '");
        stringBuilder.append(this.message);
        stringBuilder.append("' AND ");
        stringBuilder.append(this.DangXuat);
        stringBuilder.append(" GRoup by so_chon order by diem DESC;");
        str = stringBuilder.toString();
        Cursor cursor = this.db.GetData(str);
        this.mSo.clear();
        this.mDiem.clear();
        this.mDiemGiu.clear();
        this.mThanhTien.clear();
        this.mNhay.clear();
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        while (cursor.moveToNext()) {
            this.mSo.add(cursor.getString(0));
            this.mDiem.add(decimalFormat.format(cursor.getDouble(1)));
            this.mDiemGiu.add(decimalFormat.format(cursor.getDouble(2)));
            this.mThanhTien.add(decimalFormat.format(cursor.getDouble(3)));
            this.mNhay.add(Integer.valueOf(cursor.getInt(4)));
        }
        this.lv_khach.setAdapter((ListAdapter)new Khach_Adapter((Context)this, 2131427368, this.mSo));
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131427367);
        this.db = new Database((Context)this);
        init();
        this.message = getIntent().getStringExtra("tenKH");
        TextView textView = (TextView)findViewById(2131231307);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Khh");
        stringBuilder.append(this.message);
        textView.setText(stringBuilder.toString());
        this.radio_de.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Activity_khach.this.radio_de.isChecked()) {
                    Activity_khach.this.DangXuat = "(the_loai = 'deb' or the_loai = 'det')";
                    Activity_khach.this.lv_Khach();
                }
            }
        });
        this.radio_lo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Activity_khach.this.radio_lo.isChecked()) {
                    Activity_khach.this.DangXuat = "the_loai = 'lo'";
                    Activity_khach.this.lv_Khach();
                }
            }
        });
        this.radio_xi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Activity_khach.this.radio_xi.isChecked()) {
                    Activity_khach.this.DangXuat = "the_loai = 'xi'";
                    Activity_khach.this.lv_Khach();
                }
            }
        });
        this.radio_bc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Activity_khach.this.radio_bc.isChecked()) {
                    Activity_khach.this.DangXuat = "the_loai = 'bc'";
                    Activity_khach.this.lv_Khach();
                }
            }
        });
        this.radio_de.setChecked(true);
    }

    class Khach_Adapter extends ArrayAdapter {
        private ViewHolder holder;

        private LayoutInflater mInflater;

        public Khach_Adapter(Context param1Context, int param1Int, List<String> param1List) {
            super(param1Context, param1Int, param1List);
            this.mInflater = LayoutInflater.from(param1Context);
        }

        public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
            if (param1View == null) {
                param1View = this.mInflater.inflate(2131427368, null);
                ViewHolder viewHolder = new ViewHolder();
                this.holder = viewHolder;
                ViewHolder.access$002(viewHolder, (TextView)param1View.findViewById(2131231286));
                ViewHolder.access$102(this.holder, (TextView)param1View.findViewById(2131230742));
                ViewHolder.access$202(this.holder, (TextView)param1View.findViewById(2131231439));
                ViewHolder.access$302(this.holder, (TextView)param1View.findViewById(2131231435));
                ViewHolder.access$402(this.holder, (TextView)param1View.findViewById(2131231534));
                param1View.setTag(this.holder);
            } else {
                this.holder = (ViewHolder)param1View.getTag();
            }
            if (((Integer)Activity_khach.this.mNhay.get(param1Int)).intValue() > 0) {
                this.holder.sochon.setTextColor(-65536);
                this.holder.tv_diemnhan.setTextColor(-65536);
                this.holder.tv_diemgiu.setTextColor(-65536);
                this.holder.tv_thanhtien.setTextColor(-65536);
                if (((Integer)Activity_khach.this.mNhay.get(param1Int)).intValue() == 1) {
                    TextView textView1 = this.holder.sochon;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Activity_khach.this.mSo.get(param1Int));
                    stringBuilder1.append("*");
                    textView1.setText(stringBuilder1.toString());
                } else if (((Integer)Activity_khach.this.mNhay.get(param1Int)).intValue() == 2) {
                    TextView textView1 = this.holder.sochon;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Activity_khach.this.mSo.get(param1Int));
                    stringBuilder1.append("**");
                    textView1.setText(stringBuilder1.toString());
                } else if (((Integer)Activity_khach.this.mNhay.get(param1Int)).intValue() == 3) {
                    TextView textView1 = this.holder.sochon;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Activity_khach.this.mSo.get(param1Int));
                    stringBuilder1.append("***");
                    textView1.setText(stringBuilder1.toString());
                } else if (((Integer)Activity_khach.this.mNhay.get(param1Int)).intValue() == 4) {
                    TextView textView1 = this.holder.sochon;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Activity_khach.this.mSo.get(param1Int));
                    stringBuilder1.append("****");
                    textView1.setText(stringBuilder1.toString());
                }
                TextView textView = this.holder.stt;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(param1Int + 1);
                stringBuilder.append("");
                textView.setText(stringBuilder.toString());
                this.holder.tv_diemnhan.setText(Activity_khach.this.mDiem.get(param1Int));
                this.holder.tv_diemgiu.setText(Activity_khach.this.mDiemGiu.get(param1Int));
                this.holder.tv_thanhtien.setText(Activity_khach.this.mThanhTien.get(param1Int));
            } else {
                this.holder.sochon.setTextColor(-16777216);
                this.holder.tv_diemnhan.setTextColor(-16777216);
                this.holder.tv_diemgiu.setTextColor(-16777216);
                this.holder.tv_thanhtien.setTextColor(-16777216);
                TextView textView = this.holder.stt;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(param1Int + 1);
                stringBuilder.append("");
                textView.setText(stringBuilder.toString());
                this.holder.sochon.setText(Activity_khach.this.mSo.get(param1Int));
                this.holder.tv_diemnhan.setText(Activity_khach.this.mDiem.get(param1Int));
                this.holder.tv_diemgiu.setText(Activity_khach.this.mDiemGiu.get(param1Int));
                this.holder.tv_thanhtien.setText(Activity_khach.this.mThanhTien.get(param1Int));
            }
            return param1View;
        }

        public class ViewHolder {
            private TextView sochon;

            private TextView stt;

            private TextView tv_diemgiu;

            private TextView tv_diemnhan;

            private TextView tv_thanhtien;
        }
    }

    public class ViewHolder {
        private TextView sochon;

        private TextView stt;

        private TextView tv_diemgiu;

        private TextView tv_diemnhan;

        private TextView tv_thanhtien;
    }
}
