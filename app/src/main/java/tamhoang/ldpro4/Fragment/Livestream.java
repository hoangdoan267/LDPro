package tamhoang.ldpro4.Fragment;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.data.Database;

public class Livestream extends Fragment {
    MainActivity activity;

    Database db;

    Handler handler;

    LinearLayout ln1;

    ListView lvLivestrem;

    String mDate;

    public List<Integer> mDemso = new ArrayList<Integer>();

    public List<String> mDiem = new ArrayList<String>();

    public List<Double> mThangthua = new ArrayList<Double>();

    public List<Double> mTongTien = new ArrayList<Double>();

    RadioButton radio_dea;

    RadioButton radio_deb;

    RadioButton radio_dec;

    RadioButton radio_ded;

    private Runnable runnable = new Runnable() {
        public void run() {
            new MainActivity();
            if (MainActivity.sms == true) {
                Livestream.this.xem_lv();
                MainActivity.sms = false;
            }
            Livestream.this.handler.postDelayed(this, 1000L);
        }
    };

    int so;

    Switch switch1;

    String th_loai = "the_loai = 'deb'";

    TextView tvChuy;

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        onCreate(paramBundle);
        View view = paramLayoutInflater.inflate(2131427369, paramViewGroup, false);
        this.db = new Database((Context)getActivity());
        this.lvLivestrem = (ListView)view.findViewById(2131231096);
        this.tvChuy = (TextView)view.findViewById(2131231416);
        this.switch1 = (Switch)view.findViewById(2131231291);
        this.ln1 = (LinearLayout)view.findViewById(2131231052);
        this.radio_dea = (RadioButton)view.findViewById(2131231164);
        this.radio_deb = (RadioButton)view.findViewById(2131231165);
        this.radio_dec = (RadioButton)view.findViewById(2131231166);
        this.radio_ded = (RadioButton)view.findViewById(2131231167);
        this.activity = new MainActivity();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        this.mDate = simpleDateFormat.format(calendar.getTime());
        this.lvLivestrem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {}
        });
        this.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Livestream.this.switch1.isChecked()) {
                    Livestream.this.ln1.setVisibility(0);
                } else {
                    Livestream.this.ln1.setVisibility(8);
                }
            }
        });
        Handler handler = new Handler();
        this.handler = handler;
        handler.postDelayed(this.runnable, 1000L);
        this.radio_dea.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Livestream.this.radio_dea.isChecked()) {
                    Livestream.this.th_loai = "the_loai = 'dea'";
                    Livestream.this.xem_lv();
                }
            }
        });
        this.radio_deb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Livestream.this.radio_deb.isChecked()) {
                    Livestream.this.th_loai = "the_loai = 'deb'";
                    Livestream.this.xem_lv();
                }
            }
        });
        this.radio_dec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Livestream.this.radio_dec.isChecked()) {
                    Livestream.this.th_loai = "the_loai = 'dec'";
                    Livestream.this.xem_lv();
                }
            }
        });
        this.radio_ded.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Livestream.this.radio_ded.isChecked()) {
                    Livestream.this.th_loai = "the_loai = 'ded'";
                    Livestream.this.xem_lv();
                }
            }
        });
        xem_lv();
        return view;
    }

    public void onDestroy() {
        this.mDiem.clear();
        this.mDemso.clear();
        this.mTongTien.clear();
        this.mThangthua.clear();
        this.lvLivestrem.setAdapter(null);
        super.onDestroy();
    }

    public void onStop() {
        super.onStop();
        this.handler.removeCallbacks(this.runnable);
    }

    public void xem_lv() {
        String str1 = "###,###";
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        MainActivity mainActivity = new MainActivity();
        String str2 = MainActivity.Get_date();
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("SELECT count(mycount) AS dem FROM (SELECT sum((type_kh = 1) *diem_ton) - sum((type_kh = 2) * diem_ton) AS mycount FROM tbl_soctS WHERE ");
        stringBuilder1.append(this.th_loai);
        stringBuilder1.append(" AND ngay_nhan = '");
        stringBuilder1.append(str2);
        stringBuilder1.append("' GROUP BY so_chon ) a");
        String str3 = stringBuilder1.toString();
        Cursor cursor2 = this.db.GetData(str3);
        cursor2.moveToFirst();
        int i = cursor2.getInt(0);
        this.so = i;
        if (i > 0) {
            TextView textView = this.tvChuy;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("C");
            stringBuilder.append(100 - cursor2.getInt(0));
            stringBuilder.append(" s0 );
                    textView.setText(stringBuilder.toString());
        } else {
            this.tvChuy.setText("Chcdlinghnay.");
        }
        this.mDiem.clear();
        this.mDemso.clear();
        this.mTongTien.clear();
        this.mThangthua.clear();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("SELECT moctien, count(moctien) AS dem\nFROM (Select (Sum((tbl_soctS.type_kh =1) * tbl_soctS.diem_ton) - Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_ton) - so_om.Om_DeB ) as moctien\nFrom so_om Left Join tbl_soctS On tbl_soctS.so_chon = so_om.So \nWhere tbl_soctS.ngay_nhan='");
        stringBuilder2.append(str2);
        stringBuilder2.append("' AND (tbl_soctS.");
        stringBuilder2.append(this.th_loai);
        stringBuilder2.append(" OR tbl_soctS.the_loai='det') \nGROUP by so_om.So order by moctien DESC) as a \nGROUP BY moctien ORDER BY moctien DESC");
        str2 = stringBuilder2.toString();
        Cursor cursor1 = this.db.GetData(str2);
        while (cursor1.moveToNext()) {
            this.mDiem.add(decimalFormat.format(cursor1.getDouble(0)));
            this.mDemso.add(Integer.valueOf(cursor1.getInt(1)));
        }
        if (this.mDiem.size() > 0)
            for (i = 0; i < this.mDiem.size(); i++) {
                int j = Integer.parseInt(((String)this.mDiem.get(i)).replaceAll("\\.", ""));
                double d1 = (j * 100);
                if (i < this.mDiem.size())
                    for (int k = i + 1; k < this.mDiem.size(); k++) {
                        double d = ((j - Integer.parseInt(((String)this.mDiem.get(k)).replaceAll("\\.", ""))) * ((Integer)this.mDemso.get(k)).intValue());
                        Double.isNaN(d);
                        d1 -= d;
                    }
                List<Double> list = this.mTongTien;
                double d2 = ((100 - this.so) * j);
                Double.isNaN(d2);
                list.add(Double.valueOf((d1 - d2) * 715.0D / 1000.0D));
                list = this.mThangthua;
                d2 = ((100 - this.so) * j);
                Double.isNaN(d2);
                d1 = (d1 - d2) * 715.0D / 1000.0D;
                d2 = (j * 70);
                Double.isNaN(d2);
                list.add(Double.valueOf(d1 - d2));
            }
        if (getActivity() != null)
            this.lvLivestrem.setAdapter((ListAdapter)new TNGAdapter((Context)getActivity(), 2131427370, this.mDiem));
        if (cursor1 != null)
            cursor1.close();
        if (cursor2 != null)
            cursor2.close();
    }

    class TNGAdapter extends ArrayAdapter {
        DecimalFormat decimalFormat = new DecimalFormat(this.pattern);

        String pattern = "###,###";

        public TNGAdapter(Context param1Context, int param1Int, List<String> param1List) {
            super(param1Context, param1Int, param1List);
        }

        public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
            param1View = ((Activity)getContext()).getLayoutInflater().inflate(2131427370, null);
            ((TextView)param1View.findViewById(2131231420)).setText(Livestream.this.mDiem.get(param1Int));
            TextView textView = (TextView)param1View.findViewById(2131231526);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Livestream.this.mDemso.get(param1Int));
            stringBuilder.append("");
            textView.setText(stringBuilder.toString());
            ((TextView)param1View.findViewById(2131231539)).setText(this.decimalFormat.format(Livestream.this.mTongTien.get(param1Int)));
            ((TextView)param1View.findViewById(2131231385)).setText(this.decimalFormat.format(Livestream.this.mThangthua.get(param1Int)));
            return param1View;
        }
    }
}
