package tamhoang.ldpro4.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import tamhoang.ldpro4.Activity.Activity_Congno;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.data.Database;

public class Frag_MoRP1 extends Fragment {
    TextView TienNoCu;

    TextView TienPhatSinh;

    TextView TienSoCuoi;

    Database db;

    DecimalFormat decimalFormat = new DecimalFormat(this.pattern);

    Handler handler;

    ListView lv_Morp;

    public List<String> mKhachHang = new ArrayList<String>();

    public List<String> mNocu = new ArrayList<String>();

    public List<String> mPhatSinh = new ArrayList<String>();

    int mPoistion = 0;

    public List<String> mSdt = new ArrayList<String>();

    public List<String> mSoCuoi = new ArrayList<String>();

    public List<String> mtype = new ArrayList<String>();

    String ngayChon;

    String pattern = "###,###";

    private Runnable runnable = new Runnable() {
        public void run() {
            new MainActivity();
            if (MainActivity.sms == true) {
                Frag_MoRP1.this.money_lv();
                MainActivity.sms = false;
            }
            Frag_MoRP1.this.handler.postDelayed(this, 1000L);
        }
    };

    View v;

    private void itemClick(View paramView) {
        String[] arrayOfString = new String[2];
        arrayOfString[0] = "Xem phsinh chi ti;
        arrayOfString[1] = "Xkhn;
        PopupMenu popupMenu = new PopupMenu((Context)getActivity(), paramView);
        for (byte b = 0; b < arrayOfString.length; b++)
            popupMenu.getMenu().add(1, b, b, arrayOfString[b]);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem param1MenuItem) {
                int i = param1MenuItem.getOrder();
                if (i != 0) {
                    if (i == 1) {
                        AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_MoRP1.this.getActivity());
                        builder.setTitle("Xhslikhn);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                                        Database database = Frag_MoRP1.this.db;
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("Delete FROM tbl_tinnhanS WHERE ten_kh = '");
                                        stringBuilder.append(Frag_MoRP1.this.mKhachHang.get(Frag_MoRP1.this.mPoistion));
                                        stringBuilder.append("'");
                                        database.QueryData(stringBuilder.toString());
                                        database = Frag_MoRP1.this.db;
                                        stringBuilder = new StringBuilder();
                                        stringBuilder.append("Delete FROM tbl_soctS WHERE ten_kh = '");
                                        stringBuilder.append(Frag_MoRP1.this.mKhachHang.get(Frag_MoRP1.this.mPoistion));
                                        stringBuilder.append("'");
                                        database.QueryData(stringBuilder.toString());
                                        Frag_MoRP1.this.money_lv();
                                        Toast.makeText((Context)Frag_MoRP1.this.getActivity(), "Xothc, 1).show();
                                    }
                                });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                                param2DialogInterface.cancel();
                            }
                        });
                        builder.create().show();
                    }
                } else {
                    Intent intent = new Intent((Context)Frag_MoRP1.this.getActivity(), Activity_Congno.class);
                    intent.putExtra("tenKH", Frag_MoRP1.this.mKhachHang.get(Frag_MoRP1.this.mPoistion));
                    Frag_MoRP1.this.startActivity(intent);
                }
                return false;
            }
        });
    }

    public void money_lv() {
        double d1 = 0.0D;
        double d2 = 0.0D;
        double d3 = 0.0D;
        this.mKhachHang.clear();
        this.mSdt.clear();
        this.mNocu.clear();
        this.mPhatSinh.clear();
        this.mSoCuoi.clear();
        this.mtype.clear();
        MainActivity mainActivity = new MainActivity();
        String str1 = MainActivity.Get_date();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select tbl_soctS.ten_kh\n, SUM((tbl_soctS.ngay_nhan < '");
        stringBuilder.append(str1);
        stringBuilder.append("') * tbl_soctS.ket_qua * (100-tbl_soctS.diem_khachgiu)/100)/1000  as NoCu   \n, SUM((tbl_soctS.ngay_nhan = '");
        stringBuilder.append(str1);
        stringBuilder.append("') * tbl_soctS.ket_qua * (100-tbl_soctS.diem_khachgiu)/100)/1000  as PhatSinh   \n, SUM((tbl_soctS.ngay_nhan <= '");
        stringBuilder.append(str1);
        stringBuilder.append("')*tbl_soctS.ket_qua*(100-tbl_soctS.diem_khachgiu)/100)/1000 as SoCuoi, tbl_soctS.so_dienthoai, tbl_kh_new.type_kh  \nFROM tbl_soctS INNER JOIN tbl_kh_new ON tbl_soctS.so_dienthoai = tbl_kh_new.sdt\nGROUP BY tbl_soctS.ten_kh ORDER BY tbl_soctS.type_kh DESC");
        String str2 = stringBuilder.toString();
        Cursor cursor = this.db.GetData(str2);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                this.mKhachHang.add(cursor.getString(0));
                this.mSdt.add(cursor.getString(4));
                this.mNocu.add(this.decimalFormat.format(cursor.getDouble(1)));
                this.mPhatSinh.add(this.decimalFormat.format(cursor.getDouble(2)));
                this.mSoCuoi.add(this.decimalFormat.format(cursor.getDouble(3)));
                this.mtype.add(cursor.getString(5));
                d1 += cursor.getDouble(1);
                d2 += cursor.getDouble(2);
                d3 += cursor.getDouble(3);
            }
            this.TienNoCu.setText(this.decimalFormat.format(-d1));
            this.TienPhatSinh.setText(this.decimalFormat.format(-d2));
            this.TienSoCuoi.setText(this.decimalFormat.format(-d3));
        }
        if (getActivity() != null)
            this.lv_Morp.setAdapter((ListAdapter)new MoneyReport((Context)getActivity(), 2131427412, this.mKhachHang));
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.v = paramLayoutInflater.inflate(2131427409, paramViewGroup, false);
        this.db = new Database((Context)getActivity());
        this.lv_Morp = (ListView)this.v.findViewById(2131231097);
        this.TienNoCu = (TextView)this.v.findViewById(2131230739);
        this.TienPhatSinh = (TextView)this.v.findViewById(2131230740);
        this.TienSoCuoi = (TextView)this.v.findViewById(2131230741);
        money_lv();
        new MainActivity();
        this.ngayChon = MainActivity.Get_date();
        this.lv_Morp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_MoRP1.this.mPoistion = param1Int;
                return false;
            }
        });
        this.lv_Morp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_MoRP1.this.mPoistion = param1Int;
                Frag_MoRP1.this.itemClick(param1View);
            }
        });
        Handler handler = new Handler();
        this.handler = handler;
        handler.postDelayed(this.runnable, 1000L);
        return this.v;
    }

    public void onDestroy() {
        try {
            this.mKhachHang.clear();
            this.mSdt.clear();
            this.mNocu.clear();
            this.mPhatSinh.clear();
            this.mSoCuoi.clear();
            this.mtype.clear();
        } catch (Exception exception) {}
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        money_lv();
    }

    public void onStop() {
        super.onStop();
        this.handler.removeCallbacks(this.runnable);
    }

    class MoneyReport extends ArrayAdapter {
        public MoneyReport(Context param1Context, int param1Int, List<String> param1List) {
            super(param1Context, param1Int, param1List);
        }

        public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
            View view = ((Activity)getContext()).getLayoutInflater().inflate(2131427412, null);
            TextView textView2 = (TextView)view.findViewById(2131231375);
            textView2.setText(Frag_MoRP1.this.mKhachHang.get(param1Int));
            TextView textView3 = (TextView)view.findViewById(2131231523);
            textView3.setText(Frag_MoRP1.this.mNocu.get(param1Int));
            TextView textView4 = (TextView)view.findViewById(2131231525);
            textView4.setText(Frag_MoRP1.this.mPhatSinh.get(param1Int));
            TextView textView1 = (TextView)view.findViewById(2131231540);
            textView1.setText(Frag_MoRP1.this.mSoCuoi.get(param1Int));
            if (((String)Frag_MoRP1.this.mtype.get(param1Int)).indexOf("1") == -1) {
                textView2.setTextColor(-16776961);
                textView3.setTextColor(-16776961);
                textView4.setTextColor(-16776961);
                textView1.setTextColor(-16776961);
            }
            return view;
        }
    }
}
