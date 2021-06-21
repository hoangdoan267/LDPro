package tamhoang.ldpro4.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Activity.Activity_khach;
import tamhoang.ldpro4.Congthuc.Congthuc;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.NotificationReader;
import tamhoang.ldpro4.data.Database;

public class Frag_No_old extends Fragment {
    Button btn_nt;

    JSONObject caidat_tg;

    int currentIndex;

    Database db;

    Handler handler;

    boolean isSuccess = false;

    JSONObject json;

    List<JSONObject> jsonKhachHang;

    LinearLayout lnDea;

    LinearLayout lnDec;

    LinearLayout lnDed;

    LinearLayout lnDet;

    LinearLayout lnXn;

    LinearLayout ln_bca;

    LinearLayout ln_loa;

    LinearLayout ln_xi2;

    LinearLayout ln_xia2;

    ListView lv_no_tinnhan;

    LayoutInflater mInflate;

    private List<String> mSDT = new ArrayList<String>();

    private List<String> mTenKH = new ArrayList<String>();

    private List<String> mTypeKH = new ArrayList<String>();

    int position;

    private ProgressBar progressBar;

    private Runnable runnable = new Runnable() {
        public void run() {
            new MainActivity();
            if (MainActivity.sms == true) {
                Frag_No_old.this.lv_No2_KH();
                MainActivity.sms = false;
            }
            Frag_No_old.this.handler.postDelayed(this, 1000L);
        }
    };

    TextView tvAnBC1;

    TextView tvAnBCa;

    TextView tvAnDea;

    TextView tvAnDeb;

    TextView tvAnDec;

    TextView tvAnDed;

    TextView tvAnDet;

    TextView tvAnLo1;

    TextView tvAnLoa;

    TextView tvAnXi2;

    TextView tvAnXia2;

    TextView tvAnXn1;

    TextView tvDiemBc1;

    TextView tvDiemBca;

    TextView tvDiemDea;

    TextView tvDiemDeb;

    TextView tvDiemDec;

    TextView tvDiemDed;

    TextView tvDiemDet;

    TextView tvDiemLo1;

    TextView tvDiemLoa;

    TextView tvDiemXi2;

    TextView tvDiemXia2;

    TextView tvDiemXn1;

    TextView tvKQBc1;

    TextView tvKQBca;

    TextView tvKQDea;

    TextView tvKQDeb;

    TextView tvKQDec;

    TextView tvKQDed;

    TextView tvKQDet;

    TextView tvKQLo1;

    TextView tvKQLoa;

    TextView tvKQXi2;

    TextView tvKQXia2;

    TextView tvKQXn1;

    TextView tvTongTien1;

    View v;

    private void TinhlaitienKhachnay(String paramString1, String paramString2) {
        Database database = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Delete From tbl_soctS WHERE  ngay_nhan = '");
        stringBuilder.append(paramString1);
        stringBuilder.append("' AND ten_kh = '");
        stringBuilder.append(this.mTenKH.get(this.position));
        stringBuilder.append("'");
        database.QueryData(stringBuilder.toString());
        database = this.db;
        stringBuilder = new StringBuilder();
        stringBuilder.append("Select * FROM tbl_tinnhanS WHERE  ngay_nhan = '");
        stringBuilder.append(paramString1);
        stringBuilder.append("' AND phat_hien_loi = 'ok' AND ten_kh = '");
        stringBuilder.append(this.mTenKH.get(this.position));
        stringBuilder.append("'");
        Cursor cursor = database.GetData(stringBuilder.toString());
        while (cursor.moveToNext()) {
            paramString1 = cursor.getString(10).replaceAll("\\*", "");
            database = this.db;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Update tbl_tinnhanS set nd_phantich = '");
            stringBuilder.append(paramString1);
            stringBuilder.append("' WHERE id = ");
            stringBuilder.append(cursor.getInt(0));
            database.QueryData(stringBuilder.toString());
            this.db.NhapSoChiTiet(cursor.getInt(0));
        }
        Tinhtien();
        lv_No2_KH();
        if (!cursor.isClosed())
            cursor.close();
    }

    private void Tinhtien() {
        new MainActivity();
        String str = MainActivity.Get_date();
        Database database = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select * From Ketqua WHERE ngay = '");
        stringBuilder.append(str);
        stringBuilder.append("'");
        Cursor cursor = database.GetData(stringBuilder.toString());
        cursor.moveToFirst();
        byte b = 2;
        while (b < 29) {
            try {
                if (cursor.isNull(b) || !Congthuc.isNumeric(cursor.getString(b)))
                    break;
                b++;
            } catch (Exception exception) {
                // Byte code: goto -> 125
            }
        }
        if (b >= 29)
            this.db.Tinhtien((String)exception);
        if (cursor != null && !cursor.isClosed())
            cursor.close();
    }

    public void Dialog(String paramString) {
        Dialog dialog = new Dialog((Context)getActivity());
        dialog.setContentView(2131427415);
        new MainActivity();
        String str = MainActivity.Get_date();
        ClipboardManager clipboardManager = (ClipboardManager)getActivity().getSystemService("clipboard");
        Database database = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select * From tbl_kh_new WHERE ten_kh ='");
        stringBuilder.append(paramString);
        stringBuilder.append("'");
        Cursor cursor = database.GetData(stringBuilder.toString());
        cursor.moveToFirst();
        Button button1 = (Button)dialog.findViewById(2131230722);
        Button button2 = (Button)dialog.findViewById(2131231319);
        Button button3 = (Button)dialog.findViewById(2131231121);
        Button button4 = (Button)dialog.findViewById(2131230858);
        Button button5 = (Button)dialog.findViewById(2131230859);
        Button button6 = (Button)dialog.findViewById(2131231578);
        Switch switch_ = (Switch)dialog.findViewById(2131231291);
        LinearLayout linearLayout = (LinearLayout)dialog.findViewById(2131231053);
        SeekBar seekBar2 = (SeekBar)dialog.findViewById(2131231217);
        SeekBar seekBar3 = (SeekBar)dialog.findViewById(2131231219);
        SeekBar seekBar4 = (SeekBar)dialog.findViewById(2131231221);
        SeekBar seekBar5 = (SeekBar)dialog.findViewById(2131231215);
        SeekBar seekBar6 = (SeekBar)dialog.findViewById(2131231216);
        SeekBar seekBar7 = (SeekBar)dialog.findViewById(2131231218);
        SeekBar seekBar8 = (SeekBar)dialog.findViewById(2131231220);
        SeekBar seekBar1 = (SeekBar)dialog.findViewById(2131231214);
        TextView textView1 = (TextView)dialog.findViewById(2131231149);
        TextView textView2 = (TextView)dialog.findViewById(2131231151);
        TextView textView3 = (TextView)dialog.findViewById(2131231153);
        TextView textView4 = (TextView)dialog.findViewById(2131231147);
        TextView textView5 = (TextView)dialog.findViewById(2131231148);
        TextView textView6 = (TextView)dialog.findViewById(2131231150);
        TextView textView7 = (TextView)dialog.findViewById(2131231152);
        TextView textView8 = (TextView)dialog.findViewById(2131231146);
        try {
            JSONObject jSONObject = new JSONObject();
            SeekBar seekBar10 = seekBar1;
            SeekBar seekBar9 = seekBar8;
            try {
                this(cursor.getString(5));
                this.json = jSONObject;
                this.caidat_tg = jSONObject.getJSONObject("caidat_tg");
                StringBuilder stringBuilder1 = new StringBuilder();
                this();
                stringBuilder1.append(this.caidat_tg.getInt("dlgiu_de"));
                stringBuilder1.append("%");
                textView5.setText(stringBuilder1.toString());
                stringBuilder1 = new StringBuilder();
                this();
                stringBuilder1.append(this.caidat_tg.getInt("dlgiu_lo"));
                stringBuilder1.append("%");
                textView6.setText(stringBuilder1.toString());
                stringBuilder1 = new StringBuilder();
                this();
                stringBuilder1.append(this.caidat_tg.getInt("dlgiu_xi"));
                stringBuilder1.append("%");
                textView7.setText(stringBuilder1.toString());
                stringBuilder1 = new StringBuilder();
                this();
                stringBuilder1.append(this.caidat_tg.getInt("dlgiu_bc"));
                stringBuilder1.append("%");
                textView8.setText(stringBuilder1.toString());
                seekBar6.setProgress(this.caidat_tg.getInt("dlgiu_de") / 5);
                seekBar7.setProgress(this.caidat_tg.getInt("dlgiu_lo") / 5);
                int i = this.caidat_tg.getInt("dlgiu_xi") / 5;
                try {
                    seekBar9.setProgress(i);
                    i = this.caidat_tg.getInt("dlgiu_bc") / 5;
                    try {
                        seekBar10.setProgress(i);
                        StringBuilder stringBuilder2 = new StringBuilder();
                        this();
                        stringBuilder2.append(this.caidat_tg.getInt("khgiu_de"));
                        stringBuilder2.append("%");
                        String str1 = stringBuilder2.toString();
                        try {
                            textView1.setText(str1);
                            StringBuilder stringBuilder3 = new StringBuilder();
                            this();
                            stringBuilder3.append(this.caidat_tg.getInt("khgiu_lo"));
                            stringBuilder3.append("%");
                            String str2 = stringBuilder3.toString();
                            try {
                                textView2.setText(str2);
                                StringBuilder stringBuilder4 = new StringBuilder();
                                this();
                                try {
                                    JSONObject jSONObject1 = this.caidat_tg;
                                    try {
                                        stringBuilder4.append(jSONObject1.getInt("khgiu_xi"));
                                        stringBuilder4.append("%");
                                        String str3 = stringBuilder4.toString();
                                        try {
                                            textView3.setText(str3);
                                            StringBuilder stringBuilder5 = new StringBuilder();
                                            this();
                                            try {
                                                JSONObject jSONObject2 = this.caidat_tg;
                                                try {
                                                    stringBuilder5.append(jSONObject2.getInt("khgiu_bc"));
                                                    stringBuilder5.append("%");
                                                    String str4 = stringBuilder5.toString();
                                                    try {
                                                        textView4.setText(str4);
                                                        i = this.caidat_tg.getInt("khgiu_de") / 5;
                                                        try {
                                                            seekBar2.setProgress(i);
                                                            i = this.caidat_tg.getInt("khgiu_lo") / 5;
                                                            try {
                                                                seekBar3.setProgress(i);
                                                                i = this.caidat_tg.getInt("khgiu_xi") / 5;
                                                                try {
                                                                    seekBar4.setProgress(i);
                                                                    i = this.caidat_tg.getInt("khgiu_bc") / 5;
                                                                    try {
                                                                        seekBar5.setProgress(i);
                                                                        cursor.close();
                                                                    } catch (JSONException jSONException) {
                                                                        jSONException.printStackTrace();
                                                                    } catch (Exception null) {}
                                                                } catch (JSONException jSONException) {

                                                                } catch (Exception null) {}
                                                            } catch (JSONException jSONException) {

                                                            } catch (Exception null) {}
                                                        } catch (JSONException jSONException) {

                                                        } catch (Exception null) {}
                                                    } catch (JSONException jSONException) {

                                                    } catch (Exception null) {}
                                                } catch (JSONException jSONException) {

                                                } catch (Exception null) {}
                                            } catch (JSONException jSONException) {

                                            } catch (Exception null) {}
                                        } catch (JSONException jSONException) {

                                        } catch (Exception null) {}
                                    } catch (JSONException jSONException) {

                                    } catch (Exception null) {}
                                } catch (JSONException jSONException) {

                                } catch (Exception null) {}
                            } catch (JSONException jSONException) {

                            } catch (Exception null) {}
                        } catch (JSONException jSONException) {

                        } catch (Exception null) {}
                    } catch (JSONException jSONException) {

                    } catch (Exception null) {}
                } catch (JSONException jSONException) {

                } catch (Exception null) {}
            } catch (JSONException jSONException) {

            } catch (Exception null) {}
        } catch (JSONException jSONException) {

        } catch (Exception exception) {}
        exception.printStackTrace();
    }

    public void istart() {
        this.lnDea = (LinearLayout)this.v.findViewById(2131231061);
        this.lnDet = (LinearLayout)this.v.findViewById(2131231064);
        this.lnDec = (LinearLayout)this.v.findViewById(2131231062);
        this.lnDed = (LinearLayout)this.v.findViewById(2131231063);
        this.lnXn = (LinearLayout)this.v.findViewById(2131231058);
        this.ln_xi2 = (LinearLayout)this.v.findViewById(2131231070);
        this.ln_xia2 = (LinearLayout)this.v.findViewById(2131231071);
        this.ln_loa = (LinearLayout)this.v.findViewById(2131231065);
        this.ln_bca = (LinearLayout)this.v.findViewById(2131231060);
        this.tvDiemDea = (TextView)this.v.findViewById(2131231430);
        this.tvDiemDeb = (TextView)this.v.findViewById(2131231431);
        this.tvDiemDet = (TextView)this.v.findViewById(2131231434);
        this.tvDiemDec = (TextView)this.v.findViewById(2131231432);
        this.tvDiemDed = (TextView)this.v.findViewById(2131231433);
        this.tvDiemLo1 = (TextView)this.v.findViewById(2131231437);
        this.tvDiemLoa = (TextView)this.v.findViewById(2131231438);
        this.tvDiemXi2 = (TextView)this.v.findViewById(2131231443);
        this.tvDiemXia2 = (TextView)this.v.findViewById(2131231444);
        this.tvDiemXn1 = (TextView)this.v.findViewById(2131231446);
        this.tvDiemBc1 = (TextView)this.v.findViewById(2131231422);
        this.tvDiemBca = (TextView)this.v.findViewById(2131231423);
        this.tvAnDea = (TextView)this.v.findViewById(2131231345);
        this.tvAnDeb = (TextView)this.v.findViewById(2131231346);
        this.tvAnDet = (TextView)this.v.findViewById(2131231349);
        this.tvAnDec = (TextView)this.v.findViewById(2131231347);
        this.tvAnDed = (TextView)this.v.findViewById(2131231348);
        this.tvAnLo1 = (TextView)this.v.findViewById(2131231351);
        this.tvAnLoa = (TextView)this.v.findViewById(2131231352);
        this.tvAnXi2 = (TextView)this.v.findViewById(2131231354);
        this.tvAnXia2 = (TextView)this.v.findViewById(2131231355);
        this.tvAnXn1 = (TextView)this.v.findViewById(2131231357);
        this.tvAnBC1 = (TextView)this.v.findViewById(2131231338);
        this.tvAnBCa = (TextView)this.v.findViewById(2131231339);
        this.tvKQDea = (TextView)this.v.findViewById(2131231362);
        this.tvKQDeb = (TextView)this.v.findViewById(2131231363);
        this.tvKQDet = (TextView)this.v.findViewById(2131231366);
        this.tvKQDec = (TextView)this.v.findViewById(2131231364);
        this.tvKQDed = (TextView)this.v.findViewById(2131231365);
        this.tvKQLo1 = (TextView)this.v.findViewById(2131231368);
        this.tvKQLoa = (TextView)this.v.findViewById(2131231369);
        this.tvKQXi2 = (TextView)this.v.findViewById(2131231371);
        this.tvKQXia2 = (TextView)this.v.findViewById(2131231372);
        this.tvKQXn1 = (TextView)this.v.findViewById(2131231374);
        this.tvKQBc1 = (TextView)this.v.findViewById(2131231360);
        this.tvKQBca = (TextView)this.v.findViewById(2131231361);
        this.tvTongTien1 = (TextView)this.v.findViewById(2131231392);
    }

    public void lv_No2_KH() {
        String str1 = "xi";
        String str2 = "xn";
        String str3 = "loa";
        String str4 = "lo";
        String str5 = "ded";
        new MainActivity();
        String str6 = MainActivity.Get_date();
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select the_loai\n, sum((type_kh = 1)*(100-diem_khachgiu)*diem/100) as mDiem\n, CASE WHEN the_loai = 'xi' OR the_loai = 'xia' \n THEN sum((type_kh = 1)*(100-diem_khachgiu)*diem/100*so_nhay*lan_an/1000) \n ELSE sum((type_kh = 1)*(100-diem_khachgiu)*diem/100*so_nhay)  END nAn\n, sum((type_kh = 1)*ket_qua*(100-diem_khachgiu)/100) as mKetqua\n, sum((type_kh = 2)*(100-diem_khachgiu)*diem/100) as mDiem\n, CASE WHEN the_loai = 'xi' OR the_loai = 'xia' \n THEN sum((type_kh = 2)*(100-diem_khachgiu)*diem/100*so_nhay*lan_an/1000) \n ELSE sum((type_kh = 2)*(100-diem_khachgiu)*diem/100*so_nhay)  END nAn \n, sum((type_kh = 2)*ket_qua*(100-diem_khachgiu)/100) as mKetqua\n  From tbl_soctS Where ngay_nhan = '");
        stringBuilder.append(str6);
        stringBuilder.append("'\n   AND the_loai <> 'tt' GROUP by the_loai");
        str6 = stringBuilder.toString();
        Cursor cursor = this.db.GetData(str6);
        String str7 = str6;
        str7 = str6;
        try {
            JSONObject jSONObject = new JSONObject();
            str7 = str6;
            str7 = str6;
            this();
            if (cursor != null) {
                double d = 0.0D;
                while (true) {
                    str7 = str6;
                    str7 = str6;
                    boolean bool = cursor.moveToNext();
                    if (bool)
                        try {
                            JSONObject jSONObject1 = new JSONObject();
                            this();
                            jSONObject1.put("DiemNhan", decimalFormat.format(cursor.getDouble(1) - cursor.getDouble(4)));
                            jSONObject1.put("AnNhan", decimalFormat.format(cursor.getDouble(2) - cursor.getDouble(5)));
                            jSONObject1.put("KQNhan", decimalFormat.format(-(cursor.getDouble(3) + cursor.getDouble(6))));
                            d = d - cursor.getDouble(3) - cursor.getDouble(6);
                            jSONObject.put(cursor.getString(0), jSONObject1.toString());
                            continue;
                        } catch (SQLException sQLException) {
                            break;
                        } catch (JSONException jSONException) {
                            // Byte code: goto -> 1304
                        }
                    if (jSONObject.has("dea")) {
                        this.lnDea.setVisibility(0);
                        JSONObject jSONObject1 = new JSONObject();
                        this(jSONObject.getString("dea"));
                        this.tvDiemDea.setText(jSONObject1.getString("DiemNhan"));
                        this.tvAnDea.setText(jSONObject1.getString("AnNhan"));
                        this.tvKQDea.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject.has("deb")) {
                        JSONObject jSONObject1 = new JSONObject();
                        this(jSONObject.getString("deb"));
                        this.tvDiemDeb.setText(jSONObject1.getString("DiemNhan"));
                        this.tvAnDeb.setText(jSONObject1.getString("AnNhan"));
                        this.tvKQDeb.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject.has("det")) {
                        this.lnDet.setVisibility(0);
                        JSONObject jSONObject1 = new JSONObject();
                        this(jSONObject.getString("det"));
                        this.tvDiemDet.setText(jSONObject1.getString("DiemNhan"));
                        this.tvAnDet.setText(jSONObject1.getString("AnNhan"));
                        this.tvKQDet.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject.has("dec")) {
                        this.lnDec.setVisibility(0);
                        JSONObject jSONObject1 = new JSONObject();
                        this(jSONObject.getString("dec"));
                        this.tvDiemDec.setText(jSONObject1.getString("DiemNhan"));
                        this.tvAnDec.setText(jSONObject1.getString("AnNhan"));
                        this.tvKQDec.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject.has(str5)) {
                        this.lnDed.setVisibility(0);
                        JSONObject jSONObject1 = new JSONObject();
                        this(jSONObject.getString(str5));
                        this.tvDiemDed.setText(jSONObject1.getString("DiemNhan"));
                        this.tvAnDed.setText(jSONObject1.getString("AnNhan"));
                        this.tvKQDed.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject.has(str4)) {
                        JSONObject jSONObject1 = new JSONObject();
                        this(jSONObject.getString(str4));
                        this.tvDiemLo1.setText(jSONObject1.getString("DiemNhan"));
                        this.tvAnLo1.setText(jSONObject1.getString("AnNhan"));
                        this.tvKQLo1.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject.has(str3)) {
                        this.ln_loa.setVisibility(0);
                        JSONObject jSONObject1 = new JSONObject();
                        this(jSONObject.getString(str3));
                        this.tvDiemLoa.setText(jSONObject1.getString("DiemNhan"));
                        this.tvAnLoa.setText(jSONObject1.getString("AnNhan"));
                        this.tvKQLoa.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject.has(str2)) {
                        this.lnXn.setVisibility(0);
                        JSONObject jSONObject1 = new JSONObject();
                        this(jSONObject.getString(str2));
                        this.tvDiemXn1.setText(jSONObject1.getString("DiemNhan"));
                        this.tvAnXn1.setText(jSONObject1.getString("AnNhan"));
                        this.tvKQXn1.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject.has(str1)) {
                        this.ln_xi2.setVisibility(0);
                        JSONObject jSONObject1 = new JSONObject();
                        this(jSONObject.getString(str1));
                        this.tvDiemXi2.setText(jSONObject1.getString("DiemNhan"));
                        this.tvAnXi2.setText(jSONObject1.getString("AnNhan"));
                        this.tvKQXi2.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject.has("xia")) {
                        this.ln_xia2.setVisibility(0);
                        JSONObject jSONObject1 = new JSONObject();
                        this(jSONObject.getString("xia"));
                        this.tvDiemXia2.setText(jSONObject1.getString("DiemNhan"));
                        this.tvAnXia2.setText(jSONObject1.getString("AnNhan"));
                        this.tvKQXia2.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject.has("bc")) {
                        JSONObject jSONObject1 = new JSONObject();
                        this(jSONObject.getString("bc"));
                        this.tvDiemBc1.setText(jSONObject1.getString("DiemNhan"));
                        this.tvAnBC1.setText(jSONObject1.getString("AnNhan"));
                        this.tvKQBc1.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject.has("bca")) {
                        this.ln_bca.setVisibility(0);
                        JSONObject jSONObject1 = new JSONObject();
                        this(jSONObject.getString("bca"));
                        this.tvDiemBca.setText(jSONObject1.getString("DiemNhan"));
                        this.tvAnBCa.setText(jSONObject1.getString("AnNhan"));
                        this.tvKQBca.setText(jSONObject1.getString("KQNhan"));
                    }
                    this.tvTongTien1.setText(decimalFormat.format(d));
                    if (cursor != null && !cursor.isClosed())
                        cursor.close();
                    break;
                }
            }
        } catch (SQLException sQLException) {

        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        xemListview();
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.v = paramLayoutInflater.inflate(2131427421, paramViewGroup, false);
        this.db = new Database((Context)getActivity());
        this.mInflate = paramLayoutInflater;
        istart();
        this.progressBar = (ProgressBar)this.v.findViewById(2131231143);
        this.lv_no_tinnhan = (ListView)this.v.findViewById(2131231122);
        this.btn_nt = (Button)this.v.findViewById(2131230824);
        this.lv_no_tinnhan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_No_old.this.position = param1Int;
                Frag_No_old frag_No_old = Frag_No_old.this;
                frag_No_old.Dialog(frag_No_old.mTenKH.get(param1Int));
                return false;
            }
        });
        if (!Congthuc.CheckTime("18:30")) {
            Handler handler = new Handler();
            this.handler = handler;
            handler.postDelayed(this.runnable, 1000L);
        }
        lv_No2_KH();
        this.btn_nt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (Frag_No_old.this.jsonKhachHang.size() < 1)
                    return;
                AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_No_old.this.getActivity());
                builder.setMessage("Bcmunhtin chtitckh).setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                    param2DialogInterface.cancel();
                    Frag_No_old.this.progressBar.setVisibility(0);
                    Frag_No_old.this.getActivity().getWindow().setFlags(16, 16);
                    for (param2Int = 0; param2Int < Frag_No_old.this.jsonKhachHang.size(); param2Int++) {
                        Frag_No_old.this.currentIndex = param2Int;
                        Database database = Frag_No_old.this.db;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Select * From tbl_kh_new Where ten_kh = '");
                        stringBuilder.append(Frag_No_old.this.mTenKH.get(Frag_No_old.this.currentIndex));
                        stringBuilder.append("'");
                        Cursor cursor = database.GetData(stringBuilder.toString());
                        if (cursor != null && cursor.getCount() > 0) {
                            cursor.moveToFirst();
                            if (cursor.getString(2).indexOf("sms") > -1) {
                                try {
                                    if (MainActivity.jSon_Setting.getInt("tachxien_tinchot") == 0) {
                                        Frag_No_old.this.db.SendSMS(Frag_No_old.this.mSDT.get(Frag_No_old.this.currentIndex), Frag_No_old.this.db.Tin_Chottien(Frag_No_old.this.mTenKH.get(Frag_No_old.this.currentIndex)));
                                    } else {
                                        Frag_No_old.this.db.SendSMS(Frag_No_old.this.mSDT.get(Frag_No_old.this.currentIndex), Frag_No_old.this.db.Tin_Chottien_xien(Frag_No_old.this.mTenKH.get(Frag_No_old.this.currentIndex)));
                                    }
                                    Frag_No_old.this.isSuccess = true;
                                } catch (JSONException jSONException) {
                                    Frag_No_old.this.isSuccess = false;
                                    jSONException.printStackTrace();
                                }
                            } else if (jSONException.getString(2).indexOf("TL") > -1) {
                                try {
                                    if (MainActivity.jSon_Setting.getInt("tachxien_tinchot") == 0) {
                                        MainActivity.sendMessage(Long.parseLong(Frag_No_old.this.mSDT.get(Frag_No_old.this.currentIndex)), Frag_No_old.this.db.Tin_Chottien(Frag_No_old.this.mTenKH.get(Frag_No_old.this.currentIndex)));
                                    } else {
                                        MainActivity.sendMessage(Long.parseLong(Frag_No_old.this.mSDT.get(Frag_No_old.this.currentIndex)), Frag_No_old.this.db.Tin_Chottien_xien(Frag_No_old.this.mTenKH.get(Frag_No_old.this.currentIndex)));
                                    }
                                    Frag_No_old.this.isSuccess = true;
                                    try {
                                        Thread.sleep(1000L);
                                    } catch (InterruptedException interruptedException) {
                                        interruptedException.printStackTrace();
                                    }
                                } catch (JSONException jSONException1) {
                                    Frag_No_old.this.isSuccess = false;
                                    jSONException1.printStackTrace();
                                }
                            } else if (MainActivity.arr_TenKH.indexOf(jSONException1.getString(1)) > -1) {
                                NotificationReader notificationReader = new NotificationReader();
                                try {
                                    if (MainActivity.jSon_Setting.getInt("tachxien_tinchot") == 0) {
                                        notificationReader.NotificationWearReader(jSONException1.getString(1), Frag_No_old.this.db.Tin_Chottien(Frag_No_old.this.mTenKH.get(Frag_No_old.this.currentIndex)));
                                    } else {
                                        notificationReader.NotificationWearReader(jSONException1.getString(1), Frag_No_old.this.db.Tin_Chottien_xien(Frag_No_old.this.mTenKH.get(Frag_No_old.this.currentIndex)));
                                    }
                                } catch (JSONException jSONException2) {
                                    Frag_No_old.this.isSuccess = false;
                                    jSONException2.printStackTrace();
                                }
                                Frag_No_old.this.isSuccess = true;
                            } else {
                                Frag_No_old.this.isSuccess = false;
                                Toast.makeText((Context)Frag_No_old.this.getActivity(), "Khcngntrong Chatbox", 1).show();
                            }
                        }
                    }
                    (new Handler(Looper.getMainLooper())).postDelayed(new Runnable() {
                        public void run() {
                            if (Frag_No_old.this.isSuccess)
                                Toast.makeText((Context)Frag_No_old.this.getActivity(), "nhchti, 1).show();
                                        Frag_No_old.this.progressBar.setVisibility(8);
                            Frag_No_old.this.getActivity().getWindow().clearFlags(16);
                        }
                    }2000L);
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                    param2DialogInterface.cancel();
                }
            });
            builder.create().show();
        }
    });
    return this.v;
}

    public void onDestroy() {
        super.onDestroy();
        try {
            this.handler.removeCallbacks(this.runnable);
        } catch (Exception exception) {}
    }

    public void xemListview() {
        new MainActivity();
        String str = MainActivity.Get_date();
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        this.jsonKhachHang = new ArrayList<JSONObject>();
        this.mTenKH.clear();
        this.mSDT.clear();
        this.mTypeKH.clear();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select ten_kh, so_dienthoai, type_kh, the_loai\n, sum((100-diem_khachgiu)*diem/100) as mDiem\n, CASE WHEN the_loai = 'xi' OR the_loai = 'xia' \n THEN sum((100-diem_khachgiu)*diem/100*so_nhay*lan_an/1000) \n ELSE sum((100-diem_khachgiu)*diem/100*so_nhay)  END nAn\n, sum(ket_qua*(100-diem_khachgiu)/100) as mKetqua\n  From tbl_soctS Where ngay_nhan = '");
        stringBuilder.append(str);
        stringBuilder.append("'\n  AND the_loai <> 'tt' GROUP by type_kh, ten_kh, the_loai ORDER by type_kh DESC, ten_kh");
        str = stringBuilder.toString();
        Cursor cursor = this.db.GetData(str);
        try {
            JSONObject jSONObject = new JSONObject();
            this();
            double d = 0.0D;
            if (cursor != null) {
                String str1 = "";
                while (true) {
                    boolean bool = cursor.moveToNext();
                    if (bool) {
                        JSONObject jSONObject1;
                        double d1;
                        if (str1.length() == 0) {
                            this.mTenKH.add(cursor.getString(0));
                            this.mSDT.add(cursor.getString(1));
                            this.mTypeKH.add(cursor.getString(2));
                            str = cursor.getString(0);
                            jSONObject1 = jSONObject;
                            d1 = d;
                        } else {
                            jSONObject1 = jSONObject;
                            d1 = d;
                            str = str1;
                            if (str1.indexOf(cursor.getString(0)) != 0) {
                                jSONObject.put("Tien_Nhan", decimalFormat.format(d));
                                this.jsonKhachHang.add(jSONObject);
                                d1 = 0.0D;
                                this.mTenKH.add(cursor.getString(0));
                                this.mSDT.add(cursor.getString(1));
                                this.mTypeKH.add(cursor.getString(2));
                                str = cursor.getString(0);
                                jSONObject1 = new JSONObject();
                            }
                        }
                        JSONObject jSONObject2 = new JSONObject();
                        this();
                        jSONObject2.put("DiemNhan", decimalFormat.format(cursor.getDouble(4)));
                        jSONObject2.put("AnNhan", decimalFormat.format(cursor.getDouble(5)));
                        jSONObject2.put("KQNhan", decimalFormat.format(cursor.getDouble(6)));
                        d = d1 + cursor.getDouble(6);
                        jSONObject1.put(cursor.getString(3), jSONObject2.toString());
                        jSONObject = jSONObject1;
                        String str2 = str;
                        continue;
                    }
                    jSONObject.put("Tien_Nhan", decimalFormat.format(d));
                    if (cursor.getCount() > 0)
                        this.jsonKhachHang.add(jSONObject);
                    if (cursor != null && !cursor.isClosed())
                        cursor.close();
                    break;
                }
            }
        } catch (SQLException sQLException) {

        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        if (getActivity() != null)
            this.lv_no_tinnhan.setAdapter((ListAdapter)new NoRP_TN_Adapter((Context)getActivity(), 2131427422, this.jsonKhachHang));
    }

class NoRP_TN_Adapter extends ArrayAdapter {
    public NoRP_TN_Adapter(Context param1Context, int param1Int, List<JSONObject> param1List) {
        super(param1Context, param1Int, param1List);
    }

    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
        param1View = ((Activity)getContext()).getLayoutInflater().inflate(2131427422, null);
        TextView textView = (TextView)param1View.findViewById(2131231375);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Frag_No_old.this.mTenKH.get(param1Int));
        stringBuilder.append("");
        textView.setText(stringBuilder.toString());
        if (((String)Frag_No_old.this.mTypeKH.get(param1Int)).indexOf("2") > -1)
            textView.setTextColor(-16776961);
        JSONObject jSONObject = Frag_No_old.this.jsonKhachHang.get(param1Int);
        try {
            boolean bool = jSONObject.has("dea");
            if (bool)
                try {
                    ((LinearLayout)param1View.findViewById(2131231061)).setVisibility(0);
                    JSONObject jSONObject1 = new JSONObject();
                    this(jSONObject.getString("dea"));
                    ((TextView)param1View.findViewById(2131231430)).setText(jSONObject1.getString("DiemNhan"));
                    ((TextView)param1View.findViewById(2131231345)).setText(jSONObject1.getString("AnNhan"));
                    ((TextView)param1View.findViewById(2131231362)).setText(jSONObject1.getString("KQNhan"));
                } catch (JSONException jSONException) {}
            if (jSONException.has("deb")) {
                JSONObject jSONObject1 = new JSONObject();
                this(jSONException.getString("deb"));
                ((TextView)param1View.findViewById(2131231431)).setText(jSONObject1.getString("DiemNhan"));
                ((TextView)param1View.findViewById(2131231346)).setText(jSONObject1.getString("AnNhan"));
                ((TextView)param1View.findViewById(2131231363)).setText(jSONObject1.getString("KQNhan"));
            }
            if (jSONException.has("det")) {
                ((LinearLayout)param1View.findViewById(2131231064)).setVisibility(0);
                JSONObject jSONObject1 = new JSONObject();
                this(jSONException.getString("det"));
                ((TextView)param1View.findViewById(2131231434)).setText(jSONObject1.getString("DiemNhan"));
                ((TextView)param1View.findViewById(2131231349)).setText(jSONObject1.getString("AnNhan"));
                ((TextView)param1View.findViewById(2131231366)).setText(jSONObject1.getString("KQNhan"));
            }
            if (jSONException.has("dec")) {
                ((LinearLayout)param1View.findViewById(2131231062)).setVisibility(0);
                JSONObject jSONObject1 = new JSONObject();
                this(jSONException.getString("dec"));
                ((TextView)param1View.findViewById(2131231432)).setText(jSONObject1.getString("DiemNhan"));
                ((TextView)param1View.findViewById(2131231347)).setText(jSONObject1.getString("AnNhan"));
                ((TextView)param1View.findViewById(2131231364)).setText(jSONObject1.getString("KQNhan"));
            }
            if (jSONException.has("ded")) {
                ((LinearLayout)param1View.findViewById(2131231063)).setVisibility(0);
                JSONObject jSONObject1 = new JSONObject();
                this(jSONException.getString("ded"));
                ((TextView)param1View.findViewById(2131231433)).setText(jSONObject1.getString("DiemNhan"));
                ((TextView)param1View.findViewById(2131231348)).setText(jSONObject1.getString("AnNhan"));
                ((TextView)param1View.findViewById(2131231365)).setText(jSONObject1.getString("KQNhan"));
            }
            if (jSONException.has("lo")) {
                JSONObject jSONObject1 = new JSONObject();
                this(jSONException.getString("lo"));
                ((TextView)param1View.findViewById(2131231437)).setText(jSONObject1.getString("DiemNhan"));
                ((TextView)param1View.findViewById(2131231351)).setText(jSONObject1.getString("AnNhan"));
                ((TextView)param1View.findViewById(2131231368)).setText(jSONObject1.getString("KQNhan"));
            }
            if (jSONException.has("loa")) {
                ((LinearLayout)param1View.findViewById(2131231065)).setVisibility(0);
                JSONObject jSONObject1 = new JSONObject();
                this(jSONException.getString("loa"));
                ((TextView)param1View.findViewById(2131231438)).setText(jSONObject1.getString("DiemNhan"));
                ((TextView)param1View.findViewById(2131231352)).setText(jSONObject1.getString("AnNhan"));
                ((TextView)param1View.findViewById(2131231369)).setText(jSONObject1.getString("KQNhan"));
            }
            if (jSONException.has("xi")) {
                ((LinearLayout)param1View.findViewById(2131231070)).setVisibility(0);
                JSONObject jSONObject1 = new JSONObject();
                this(jSONException.getString("xi"));
                ((TextView)param1View.findViewById(2131231443)).setText(jSONObject1.getString("DiemNhan"));
                ((TextView)param1View.findViewById(2131231354)).setText(jSONObject1.getString("AnNhan"));
                ((TextView)param1View.findViewById(2131231371)).setText(jSONObject1.getString("KQNhan"));
            }
            if (jSONException.has("xia")) {
                ((LinearLayout)param1View.findViewById(2131231071)).setVisibility(0);
                JSONObject jSONObject1 = new JSONObject();
                this(jSONException.getString("xia"));
                ((TextView)param1View.findViewById(2131231444)).setText(jSONObject1.getString("DiemNhan"));
                ((TextView)param1View.findViewById(2131231355)).setText(jSONObject1.getString("AnNhan"));
                ((TextView)param1View.findViewById(2131231372)).setText(jSONObject1.getString("KQNhan"));
            }
            if (jSONException.has("xn")) {
                ((LinearLayout)param1View.findViewById(2131231058)).setVisibility(0);
                JSONObject jSONObject1 = new JSONObject();
                this(jSONException.getString("xn"));
                ((TextView)param1View.findViewById(2131231446)).setText(jSONObject1.getString("DiemNhan"));
                ((TextView)param1View.findViewById(2131231357)).setText(jSONObject1.getString("AnNhan"));
                ((TextView)param1View.findViewById(2131231374)).setText(jSONObject1.getString("KQNhan"));
            }
            if (jSONException.has("bc")) {
                JSONObject jSONObject1 = new JSONObject();
                this(jSONException.getString("bc"));
                ((TextView)param1View.findViewById(2131231422)).setText(jSONObject1.getString("DiemNhan"));
                ((TextView)param1View.findViewById(2131231338)).setText(jSONObject1.getString("AnNhan"));
                ((TextView)param1View.findViewById(2131231360)).setText(jSONObject1.getString("KQNhan"));
            }
            if (jSONException.has("bca")) {
                ((LinearLayout)param1View.findViewById(2131231060)).setVisibility(0);
                JSONObject jSONObject1 = new JSONObject();
                this(jSONException.getString("bca"));
                ((TextView)param1View.findViewById(2131231423)).setText(jSONObject1.getString("DiemNhan"));
                ((TextView)param1View.findViewById(2131231339)).setText(jSONObject1.getString("AnNhan"));
                ((TextView)param1View.findViewById(2131231361)).setText(jSONObject1.getString("KQNhan"));
            }
            ((TextView)param1View.findViewById(2131231392)).setText(jSONException.getString("Tien_Nhan"));
        } catch (JSONException jSONException) {}
        return param1View;
    }
}
}
