package tamhoang.ldpro4.Activity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Congthuc.BaseToolBarActivity;
import tamhoang.ldpro4.Congthuc.Congthuc;
import tamhoang.ldpro4.data.Database;

public class Activity_AddKH2 extends BaseToolBarActivity {
    String[] baoloi_donvi;

    SeekBar bc_dly;

    SeekBar bc_khach;

    Button btn_exit;

    JSONObject caidat_tg;

    String[] chot_sodu;

    Database db;

    SeekBar de_dly;

    SeekBar de_khach;

    String[] dv_nhanXien;

    TextView edt_ten;

    String[] heso_de;

    JSONObject json;

    JSONObject json_KhongMax;

    String[] khach_de;

    LinearLayout li_nhanxien;

    SeekBar lo_dly;

    SeekBar lo_khach;

    String message;

    TextView pt_giu_bc_dly;

    TextView pt_giu_bc_khach;

    TextView pt_giu_de_dly;

    TextView pt_giu_de_khach;

    TextView pt_giu_lo_dly;

    TextView pt_giu_lo_khach;

    TextView pt_giu_xi_dly;

    TextView pt_giu_xi_khach;

    Spinner sp_Chot_sodu;

    Spinner sp_baoloidonvi;

    Spinner sp_hesode;

    Spinner sp_khachde;

    Spinner sp_nhanXien;

    Spinner sp_traloitn;

    String[] tl_tinnhan;

    TextView tv_KhongMax;

    TextView tv_Lo_xien;

    TextView tv_de_cang;

    SeekBar xi_dly;

    SeekBar xi_khach;

    public void UPdate() {
        try {
            String str3;
            if (this.json_KhongMax.getString("danDe").length() == 0) {
                StringBuilder stringBuilder = new StringBuilder();
                this();
                stringBuilder.append("");
                stringBuilder.append("  Khkh);
                        str3 = stringBuilder.toString();
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                this();
                stringBuilder.append("");
                stringBuilder.append("  ");
                stringBuilder.append(this.json_KhongMax.getString("danDe"));
                str3 = stringBuilder.toString();
            }
            if (this.json_KhongMax.getString("danLo").length() == 0) {
                StringBuilder stringBuilder = new StringBuilder();
                this();
                stringBuilder.append(str3);
                stringBuilder.append("\n  LKhkh);
                        str3 = stringBuilder.toString();
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                this();
                stringBuilder.append(str3);
                stringBuilder.append("\n  L");
                stringBuilder.append(this.json_KhongMax.getString("danLo"));
                str3 = stringBuilder.toString();
            }
            StringBuilder stringBuilder4 = new StringBuilder();
            this();
            stringBuilder4.append(str3);
            stringBuilder4.append("\n  Xi2: ");
            stringBuilder4.append(this.json_KhongMax.getString("xien2"));
            String str5 = stringBuilder4.toString();
            StringBuilder stringBuilder2 = new StringBuilder();
            this();
            stringBuilder2.append(str5);
            stringBuilder2.append("\n  Xi3: ");
            stringBuilder2.append(this.json_KhongMax.getString("xien3"));
            String str2 = stringBuilder2.toString();
            StringBuilder stringBuilder3 = new StringBuilder();
            this();
            stringBuilder3.append(str2);
            stringBuilder3.append("\n  Xi4: ");
            stringBuilder3.append(this.json_KhongMax.getString("xien4"));
            String str4 = stringBuilder3.toString();
            StringBuilder stringBuilder1 = new StringBuilder();
            this();
            stringBuilder1.append(str4);
            stringBuilder1.append("\n  C");
            stringBuilder1.append(this.json_KhongMax.getString("cang"));
            String str1 = stringBuilder1.toString();
            this.tv_KhongMax.setText(str1);
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
    }

    protected int getLayoutId() {
        return 2131427358;
    }

    public void init() {
        this.edt_ten = (TextView)findViewById(2131230960);
        this.btn_exit = (Button)findViewById(2131230820);
        this.pt_giu_de_khach = (TextView)findViewById(2131231149);
        this.pt_giu_lo_khach = (TextView)findViewById(2131231151);
        this.pt_giu_xi_khach = (TextView)findViewById(2131231153);
        this.pt_giu_bc_khach = (TextView)findViewById(2131231147);
        this.pt_giu_de_dly = (TextView)findViewById(2131231148);
        this.pt_giu_lo_dly = (TextView)findViewById(2131231150);
        this.pt_giu_xi_dly = (TextView)findViewById(2131231152);
        this.pt_giu_bc_dly = (TextView)findViewById(2131231146);
        this.de_khach = (SeekBar)findViewById(2131231217);
        this.lo_khach = (SeekBar)findViewById(2131231219);
        this.xi_khach = (SeekBar)findViewById(2131231221);
        this.bc_khach = (SeekBar)findViewById(2131231215);
        this.de_dly = (SeekBar)findViewById(2131231216);
        this.lo_dly = (SeekBar)findViewById(2131231218);
        this.xi_dly = (SeekBar)findViewById(2131231220);
        this.bc_dly = (SeekBar)findViewById(2131231214);
        this.sp_traloitn = (Spinner)findViewById(2131231257);
        this.sp_nhanXien = (Spinner)findViewById(2131231255);
        this.sp_Chot_sodu = (Spinner)findViewById(2131231241);
        this.sp_hesode = (Spinner)findViewById(2131231249);
        this.sp_baoloidonvi = (Spinner)findViewById(2131231244);
        this.sp_khachde = (Spinner)findViewById(2131231251);
        this.li_nhanxien = (LinearLayout)findViewById(2131231066);
        this.tv_Lo_xien = (TextView)findViewById(2131231377);
        this.tv_de_cang = (TextView)findViewById(2131231418);
        this.tv_KhongMax = (TextView)findViewById(2131231376);
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131427358);
        this.db = new Database((Context)this);
        init();
        String str = getIntent().getStringExtra("tenKH");
        this.message = str;
        if (str.length() > 0) {
            this.edt_ten.setText(this.message);
            this.tl_tinnhan = new String[] { "1. Ok tin vnd pht, "2. Chok tin", "3. Khtrl, "4. Ok tin nguym, "5. Chok tin (ngay khi nh, "6. OK nguym(ngay khi nh};
                    ArrayAdapter arrayAdapter = new ArrayAdapter((Context)this, 2131427455, (Object[])this.tl_tinnhan);
            this.sp_traloitn.setAdapter((SpinnerAdapter)arrayAdapter);
            this.dv_nhanXien = new String[] { "1. Ginguygi, "2. Nh10 khi l, "3. Nh10 tcxi};
                    arrayAdapter = new ArrayAdapter((Context)this, 2131427455, (Object[])this.dv_nhanXien);
            this.sp_nhanXien.setAdapter((SpinnerAdapter)arrayAdapter);
            this.chot_sodu = new String[] { "1. Chtitrong ng, "2. Chccn};
            arrayAdapter = new ArrayAdapter((Context)this, 2131427455, (Object[])this.chot_sodu);
            this.sp_Chot_sodu.setAdapter((SpinnerAdapter)arrayAdapter);
            this.baoloi_donvi = new String[] { "1. Ko blsai v, "2. Blkhi sai v};
            arrayAdapter = new ArrayAdapter((Context)this, 2131427455, (Object[])this.baoloi_donvi);
            this.sp_baoloidonvi.setAdapter((SpinnerAdapter)arrayAdapter);
            this.khach_de = new String[] { "1. Th(de = deb, de8 = det)", "2. 8 (de = det)" };
            arrayAdapter = new ArrayAdapter((Context)this, 2131427455, (Object[])this.khach_de);
            this.sp_khachde.setAdapter((SpinnerAdapter)arrayAdapter);
            this.heso_de = new String[] { "1. Ginguy(HS=1)", "2. 8->7 (HS=1,143)", "3. 7->8 (HS=0,875)" };
            arrayAdapter = new ArrayAdapter((Context)this, 2131427455, (Object[])this.heso_de);
            this.sp_hesode.setAdapter((SpinnerAdapter)arrayAdapter);
            Database database = this.db;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Select * From tbl_kh_new WHERE ten_kh ='");
            stringBuilder.append(this.message);
            stringBuilder.append("'");
            Cursor cursor = database.GetData(stringBuilder.toString());
            cursor.moveToFirst();
            try {
                JSONObject jSONObject2 = new JSONObject();
                this(cursor.getString(5));
                this.json = jSONObject2;
                jSONObject2 = jSONObject2.getJSONObject("caidat_tg");
                this.caidat_tg = jSONObject2;
                this.sp_traloitn.setSelection(jSONObject2.getInt("ok_tin"));
                this.sp_nhanXien.setSelection(this.caidat_tg.getInt("xien_nhan"));
                this.sp_Chot_sodu.setSelection(this.caidat_tg.getInt("chot_sodu"));
                this.tv_Lo_xien.setText(this.caidat_tg.getString("tg_loxien"));
                this.tv_de_cang.setText(this.caidat_tg.getString("tg_debc"));
                this.sp_hesode.setSelection(this.caidat_tg.getInt("heso_de"));
                try {
                    this.sp_khachde.setSelection(this.caidat_tg.getInt("khach_de"));
                } catch (JSONException jSONException) {
                    this.caidat_tg.put("khach_de", 0);
                }
                this.sp_baoloidonvi.setSelection(this.caidat_tg.getInt("loi_donvi"));
                TextView textView5 = this.pt_giu_de_dly;
                StringBuilder stringBuilder3 = new StringBuilder();
                this();
                stringBuilder3.append(this.caidat_tg.getInt("dlgiu_de"));
                stringBuilder3.append("%");
                textView5.setText(stringBuilder3.toString());
                TextView textView2 = this.pt_giu_lo_dly;
                StringBuilder stringBuilder6 = new StringBuilder();
                this();
                stringBuilder6.append(this.caidat_tg.getInt("dlgiu_lo"));
                stringBuilder6.append("%");
                textView2.setText(stringBuilder6.toString());
                textView2 = this.pt_giu_xi_dly;
                stringBuilder6 = new StringBuilder();
                this();
                stringBuilder6.append(this.caidat_tg.getInt("dlgiu_xi"));
                stringBuilder6.append("%");
                textView2.setText(stringBuilder6.toString());
                textView2 = this.pt_giu_bc_dly;
                stringBuilder6 = new StringBuilder();
                this();
                stringBuilder6.append(this.caidat_tg.getInt("dlgiu_bc"));
                stringBuilder6.append("%");
                textView2.setText(stringBuilder6.toString());
                this.de_dly.setProgress(this.caidat_tg.getInt("dlgiu_de") / 5);
                this.lo_dly.setProgress(this.caidat_tg.getInt("dlgiu_lo") / 5);
                this.xi_dly.setProgress(this.caidat_tg.getInt("dlgiu_xi") / 5);
                this.bc_dly.setProgress(this.caidat_tg.getInt("dlgiu_bc") / 5);
                TextView textView4 = this.pt_giu_de_khach;
                StringBuilder stringBuilder2 = new StringBuilder();
                this();
                stringBuilder2.append(this.caidat_tg.getInt("khgiu_de"));
                stringBuilder2.append("%");
                textView4.setText(stringBuilder2.toString());
                textView4 = this.pt_giu_lo_khach;
                stringBuilder2 = new StringBuilder();
                this();
                stringBuilder2.append(this.caidat_tg.getInt("khgiu_lo"));
                stringBuilder2.append("%");
                textView4.setText(stringBuilder2.toString());
                TextView textView1 = this.pt_giu_xi_khach;
                StringBuilder stringBuilder5 = new StringBuilder();
                this();
                stringBuilder5.append(this.caidat_tg.getInt("khgiu_xi"));
                stringBuilder5.append("%");
                textView1.setText(stringBuilder5.toString());
                TextView textView3 = this.pt_giu_bc_khach;
                StringBuilder stringBuilder1 = new StringBuilder();
                this();
                stringBuilder1.append(this.caidat_tg.getInt("khgiu_bc"));
                stringBuilder1.append("%");
                textView3.setText(stringBuilder1.toString());
                this.de_khach.setProgress(this.caidat_tg.getInt("khgiu_de") / 5);
                this.lo_khach.setProgress(this.caidat_tg.getInt("khgiu_lo") / 5);
                this.xi_khach.setProgress(this.caidat_tg.getInt("khgiu_xi") / 5);
                this.bc_khach.setProgress(this.caidat_tg.getInt("khgiu_bc") / 5);
                JSONObject jSONObject1 = new JSONObject();
                this(cursor.getString(6));
                this.json_KhongMax = jSONObject1;
                if (jSONObject1.getString("danDe").length() == 0) {
                    StringBuilder stringBuilder7 = new StringBuilder();
                    this();
                    stringBuilder7.append("");
                    stringBuilder7.append("  Khkh);
                            str1 = stringBuilder7.toString();
                } else {
                    StringBuilder stringBuilder7 = new StringBuilder();
                    this();
                    stringBuilder7.append("");
                    stringBuilder7.append("  ");
                    stringBuilder7.append(this.json_KhongMax.getString("danDe"));
                    str1 = stringBuilder7.toString();
                }
                if (this.json_KhongMax.getString("danLo").length() == 0) {
                    StringBuilder stringBuilder7 = new StringBuilder();
                    this();
                    stringBuilder7.append(str1);
                    stringBuilder7.append("\n  LKhkh);
                            str1 = stringBuilder7.toString();
                } else {
                    StringBuilder stringBuilder7 = new StringBuilder();
                    this();
                    stringBuilder7.append(str1);
                    stringBuilder7.append("\n  L");
                    stringBuilder7.append(this.json_KhongMax.getString("danLo"));
                    str1 = stringBuilder7.toString();
                }
                StringBuilder stringBuilder4 = new StringBuilder();
                this();
                stringBuilder4.append(str1);
                stringBuilder4.append("\n  Xi2: ");
                stringBuilder4.append(this.json_KhongMax.getString("xien2"));
                String str1 = stringBuilder4.toString();
                stringBuilder4 = new StringBuilder();
                this();
                stringBuilder4.append(str1);
                stringBuilder4.append("\n  Xi3: ");
                stringBuilder4.append(this.json_KhongMax.getString("xien3"));
                str1 = stringBuilder4.toString();
                stringBuilder4 = new StringBuilder();
                this();
                stringBuilder4.append(str1);
                stringBuilder4.append("\n  Xi4: ");
                stringBuilder4.append(this.json_KhongMax.getString("xien4"));
                str1 = stringBuilder4.toString();
                stringBuilder4 = new StringBuilder();
                this();
                stringBuilder4.append(str1);
                stringBuilder4.append("\n  C");
                stringBuilder4.append(this.json_KhongMax.getString("cang"));
                str1 = stringBuilder4.toString();
                this.tv_KhongMax.setText(str1);
                if (cursor != null && !cursor.isClosed())
                    cursor.close();
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
    }
            this.sp_traloitn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                    try {
                        Activity_AddKH2.this.caidat_tg.put("ok_tin", param1Int);
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                }

                public void onNothingSelected(AdapterView<?> param1AdapterView) {}
            });
            this.sp_nhanXien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                    try {
                        Activity_AddKH2.this.caidat_tg.put("xien_nhan", param1Int);
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                }

                public void onNothingSelected(AdapterView<?> param1AdapterView) {}
            });
            this.sp_Chot_sodu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                    try {
                        Activity_AddKH2.this.caidat_tg.put("chot_sodu", param1Int);
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                }

                public void onNothingSelected(AdapterView<?> param1AdapterView) {}
            });
            this.sp_khachde.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                    try {
                        Activity_AddKH2.this.caidat_tg.put("khach_de", param1Int);
                    } catch (JSONException jSONException) {}
                }

                public void onNothingSelected(AdapterView<?> param1AdapterView) {}
            });
            this.sp_hesode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                    try {
                        Activity_AddKH2.this.caidat_tg.put("heso_de", param1Int);
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                }

                public void onNothingSelected(AdapterView<?> param1AdapterView) {}
            });
            this.sp_baoloidonvi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                    try {
                        Activity_AddKH2.this.caidat_tg.put("loi_donvi", param1Int);
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                }

                public void onNothingSelected(AdapterView<?> param1AdapterView) {}
            });
            this.btn_exit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param1View) {
                    try {
                        Activity_AddKH2.this.json.put("caidat_tg", Activity_AddKH2.this.caidat_tg);
                        Database database = Activity_AddKH2.this.db;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append("update tbl_kh_new set tbl_MB = '");
                        stringBuilder.append(Activity_AddKH2.this.json.toString());
                        stringBuilder.append("', tbl_XS = '");
                        stringBuilder.append(Activity_AddKH2.this.json_KhongMax.toString());
                        stringBuilder.append("' WHERE ten_kh = '");
                        stringBuilder.append(Activity_AddKH2.this.message);
                        stringBuilder.append("'");
                        database.QueryData(stringBuilder.toString());
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                    Activity_AddKH2.this.finish();
                }
            });
            this.de_khach.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int max;

                public void onProgressChanged(SeekBar param1SeekBar, int param1Int, boolean param1Boolean) {
                    TextView textView = Activity_AddKH2.this.pt_giu_de_khach;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(param1Int * 5);
                    stringBuilder.append("%");
                    textView.setText(stringBuilder.toString());
                    this.max = param1Int * 5;
                }

                public void onStartTrackingTouch(SeekBar param1SeekBar) {}

                public void onStopTrackingTouch(SeekBar param1SeekBar) {
                    try {
                        Activity_AddKH2.this.caidat_tg.put("khgiu_de", this.max);
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                    Activity_AddKH2 activity_AddKH2 = Activity_AddKH2.this;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Gicho kh");
                    stringBuilder.append(this.max);
                    stringBuilder.append("%");
                    Toast.makeText((Context)activity_AddKH2, stringBuilder.toString(), 1).show();
                }
            });
            this.lo_khach.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int max;

                public void onProgressChanged(SeekBar param1SeekBar, int param1Int, boolean param1Boolean) {
                    TextView textView = Activity_AddKH2.this.pt_giu_lo_khach;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(param1Int * 5);
                    stringBuilder.append("%");
                    textView.setText(stringBuilder.toString());
                    this.max = param1Int * 5;
                }

                public void onStartTrackingTouch(SeekBar param1SeekBar) {}

                public void onStopTrackingTouch(SeekBar param1SeekBar) {
                    try {
                        Activity_AddKH2.this.caidat_tg.put("khgiu_lo", this.max);
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                    Activity_AddKH2 activity_AddKH2 = Activity_AddKH2.this;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Gicho kh");
                    stringBuilder.append(this.max);
                    stringBuilder.append("%");
                    Toast.makeText((Context)activity_AddKH2, stringBuilder.toString(), 1).show();
                }
            });
            this.xi_khach.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int max;

                public void onProgressChanged(SeekBar param1SeekBar, int param1Int, boolean param1Boolean) {
                    TextView textView = Activity_AddKH2.this.pt_giu_xi_khach;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(param1Int * 5);
                    stringBuilder.append("%");
                    textView.setText(stringBuilder.toString());
                    this.max = param1Int * 5;
                }

                public void onStartTrackingTouch(SeekBar param1SeekBar) {}

                public void onStopTrackingTouch(SeekBar param1SeekBar) {
                    try {
                        Activity_AddKH2.this.caidat_tg.put("khgiu_xi", this.max);
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                    Activity_AddKH2 activity_AddKH2 = Activity_AddKH2.this;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Gicho kh");
                    stringBuilder.append(this.max);
                    stringBuilder.append("%");
                    Toast.makeText((Context)activity_AddKH2, stringBuilder.toString(), 1).show();
                }
            });
            this.bc_khach.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int max;

                public void onProgressChanged(SeekBar param1SeekBar, int param1Int, boolean param1Boolean) {
                    TextView textView = Activity_AddKH2.this.pt_giu_bc_khach;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(param1Int * 5);
                    stringBuilder.append("%");
                    textView.setText(stringBuilder.toString());
                    this.max = param1Int * 5;
                }

                public void onStartTrackingTouch(SeekBar param1SeekBar) {}

                public void onStopTrackingTouch(SeekBar param1SeekBar) {
                    try {
                        Activity_AddKH2.this.caidat_tg.put("khgiu_bc", this.max);
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                    Activity_AddKH2 activity_AddKH2 = Activity_AddKH2.this;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Gicho kh");
                    stringBuilder.append(this.max);
                    stringBuilder.append("%");
                    Toast.makeText((Context)activity_AddKH2, stringBuilder.toString(), 1).show();
                }
            });
            this.de_dly.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int max;

                public void onProgressChanged(SeekBar param1SeekBar, int param1Int, boolean param1Boolean) {
                    TextView textView = Activity_AddKH2.this.pt_giu_de_dly;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(param1Int * 5);
                    stringBuilder.append("%");
                    textView.setText(stringBuilder.toString());
                    this.max = param1Int * 5;
                }

                public void onStartTrackingTouch(SeekBar param1SeekBar) {}

                public void onStopTrackingTouch(SeekBar param1SeekBar) {
                    try {
                        Activity_AddKH2.this.caidat_tg.put("dlgiu_de", this.max);
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                    Activity_AddKH2 activity_AddKH2 = Activity_AddKH2.this;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Mgi");
                    stringBuilder.append(this.max);
                    stringBuilder.append("%");
                    Toast.makeText((Context)activity_AddKH2, stringBuilder.toString(), 1).show();
                }
            });
            this.lo_dly.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int max;

                public void onProgressChanged(SeekBar param1SeekBar, int param1Int, boolean param1Boolean) {
                    TextView textView = Activity_AddKH2.this.pt_giu_lo_dly;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(param1Int * 5);
                    stringBuilder.append("%");
                    textView.setText(stringBuilder.toString());
                    this.max = param1Int * 5;
                }

                public void onStartTrackingTouch(SeekBar param1SeekBar) {}

                public void onStopTrackingTouch(SeekBar param1SeekBar) {
                    try {
                        Activity_AddKH2.this.caidat_tg.put("dlgiu_lo", this.max);
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                    Activity_AddKH2 activity_AddKH2 = Activity_AddKH2.this;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Mgi");
                    stringBuilder.append(this.max);
                    stringBuilder.append("%");
                    Toast.makeText((Context)activity_AddKH2, stringBuilder.toString(), 1).show();
                }
            });
            this.xi_dly.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int max;

                public void onProgressChanged(SeekBar param1SeekBar, int param1Int, boolean param1Boolean) {
                    TextView textView = Activity_AddKH2.this.pt_giu_xi_dly;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(param1Int * 5);
                    stringBuilder.append("%");
                    textView.setText(stringBuilder.toString());
                    this.max = param1Int * 5;
                }

                public void onStartTrackingTouch(SeekBar param1SeekBar) {}

                public void onStopTrackingTouch(SeekBar param1SeekBar) {
                    try {
                        Activity_AddKH2.this.caidat_tg.put("dlgiu_xi", this.max);
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                    Activity_AddKH2 activity_AddKH2 = Activity_AddKH2.this;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Mgi");
                    stringBuilder.append(this.max);
                    stringBuilder.append("%");
                    Toast.makeText((Context)activity_AddKH2, stringBuilder.toString(), 1).show();
                }
            });
            this.bc_dly.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int max;

                public void onProgressChanged(SeekBar param1SeekBar, int param1Int, boolean param1Boolean) {
                    TextView textView = Activity_AddKH2.this.pt_giu_bc_dly;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(param1Int * 5);
                    stringBuilder.append("%");
                    textView.setText(stringBuilder.toString());
                    this.max = param1Int * 5;
                }

                public void onStartTrackingTouch(SeekBar param1SeekBar) {}

                public void onStopTrackingTouch(SeekBar param1SeekBar) {
                    try {
                        Activity_AddKH2.this.caidat_tg.put("dlgiu_bc", this.max);
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                    Activity_AddKH2 activity_AddKH2 = Activity_AddKH2.this;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Mgi");
                    stringBuilder.append(this.max);
                    stringBuilder.append("%");
                    Toast.makeText((Context)activity_AddKH2, stringBuilder.toString(), 1).show();
                }
            });
            this.tv_Lo_xien.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param1View) {
                    Calendar calendar = Calendar.getInstance();
                    int i = calendar.get(11);
                    int j = calendar.get(12);
                    TimePickerDialog timePickerDialog = new TimePickerDialog((Context)Activity_AddKH2.this, new TimePickerDialog.OnTimeSetListener() {
                        public void onTimeSet(TimePicker param2TimePicker, int param2Int1, int param2Int2) {
                            if (param2Int2 < 10) {
                                TextView textView = Activity_AddKH2.this.tv_Lo_xien;
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append(param2Int1);
                                stringBuilder2.append(":0");
                                stringBuilder2.append(param2Int2);
                                textView.setText(stringBuilder2.toString());
                                Activity_AddKH2 activity_AddKH2 = Activity_AddKH2.this;
                                StringBuilder stringBuilder1 = new StringBuilder();
                                stringBuilder1.append("khnhlxisau: ");
                                stringBuilder1.append(param2Int1);
                                stringBuilder1.append(":0");
                                stringBuilder1.append(param2Int2);
                                Toast.makeText((Context)activity_AddKH2, stringBuilder1.toString(), 1).show();
                            } else {
                                TextView textView = Activity_AddKH2.this.tv_Lo_xien;
                                StringBuilder stringBuilder1 = new StringBuilder();
                                stringBuilder1.append(param2Int1);
                                stringBuilder1.append(":");
                                stringBuilder1.append(param2Int2);
                                textView.setText(stringBuilder1.toString());
                                Activity_AddKH2 activity_AddKH2 = Activity_AddKH2.this;
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("khnhlxisau: ");
                                stringBuilder2.append(param2Int1);
                                stringBuilder2.append(":");
                                stringBuilder2.append(param2Int2);
                                Toast.makeText((Context)activity_AddKH2, stringBuilder2.toString(), 1).show();
                            }
                            try {
                                Activity_AddKH2.this.caidat_tg.put("tg_loxien", Activity_AddKH2.this.tv_Lo_xien.getText().toString());
                            } catch (JSONException jSONException) {
                                jSONException.printStackTrace();
                            }
                        }
                    }i, j, true);
                    timePickerDialog.setTitle("Select Time");
                    timePickerDialog.show();
                }
            });
            this.tv_de_cang.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param1View) {
                    Calendar calendar = Calendar.getInstance();
                    int i = calendar.get(11);
                    int j = calendar.get(12);
                    TimePickerDialog timePickerDialog = new TimePickerDialog((Context)Activity_AddKH2.this, new TimePickerDialog.OnTimeSetListener() {
                        public void onTimeSet(TimePicker param2TimePicker, int param2Int1, int param2Int2) {
                            if (param2Int2 < 10) {
                                TextView textView = Activity_AddKH2.this.tv_de_cang;
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append(param2Int1);
                                stringBuilder2.append(":0");
                                stringBuilder2.append(param2Int2);
                                textView.setText(stringBuilder2.toString());
                                Activity_AddKH2 activity_AddKH2 = Activity_AddKH2.this;
                                StringBuilder stringBuilder1 = new StringBuilder();
                                stringBuilder1.append("khnhsau: ");
                                stringBuilder1.append(param2Int1);
                                stringBuilder1.append(":0");
                                stringBuilder1.append(param2Int2);
                                Toast.makeText((Context)activity_AddKH2, stringBuilder1.toString(), 1).show();
                            } else {
                                TextView textView = Activity_AddKH2.this.tv_de_cang;
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append(param2Int1);
                                stringBuilder2.append(":");
                                stringBuilder2.append(param2Int2);
                                textView.setText(stringBuilder2.toString());
                                Activity_AddKH2 activity_AddKH2 = Activity_AddKH2.this;
                                StringBuilder stringBuilder1 = new StringBuilder();
                                stringBuilder1.append("khnhsau: ");
                                stringBuilder1.append(param2Int1);
                                stringBuilder1.append(":");
                                stringBuilder1.append(param2Int2);
                                Toast.makeText((Context)activity_AddKH2, stringBuilder1.toString(), 1).show();
                            }
                            try {
                                Activity_AddKH2.this.caidat_tg.put("tg_debc", Activity_AddKH2.this.tv_de_cang.getText().toString());
                            } catch (JSONException jSONException) {
                                jSONException.printStackTrace();
                            }
                        }
                    }i, j, true);
                    timePickerDialog.setTitle("Select Time");
                    timePickerDialog.show();
                }
            });
            this.tv_KhongMax.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param1View) {
                    Activity_AddKH2.this.showDialog2();
                }
            });
  }

            public void showDialog2() {
                Dialog dialog = new Dialog((Context)this);
                dialog.setContentView(2131427407);
                dialog.getWindow().setLayout(-1, -2);
                Button button1 = (Button)dialog.findViewById(2131230803);
                Button button2 = (Button)dialog.findViewById(2131230810);
                Button button3 = (Button)dialog.findViewById(2131230804);
                Button button4 = (Button)dialog.findViewById(2131230811);
                Button button5 = (Button)dialog.findViewById(2131230805);
                Button button6 = (Button)dialog.findViewById(2131230812);
                EditText editText1 = (EditText)dialog.findViewById(2131230929);
                EditText editText2 = (EditText)dialog.findViewById(2131230930);
                EditText editText3 = (EditText)dialog.findViewById(2131230982);
                EditText editText4 = (EditText)dialog.findViewById(2131230983);
                EditText editText5 = (EditText)dialog.findViewById(2131230984);
                EditText editText6 = (EditText)dialog.findViewById(2131230981);
                try {
                    JSONObject jSONObject = this.json_KhongMax;
                    try {
                        editText1.setText(jSONObject.getString("danDe"));
                        editText2.setText(this.json_KhongMax.getString("danLo"));
                        editText3.setText(this.json_KhongMax.getString("xien2"));
                        editText4.setText(this.json_KhongMax.getString("xien3"));
                        editText5.setText(this.json_KhongMax.getString("xien4"));
                        editText6.setText(this.json_KhongMax.getString("cang"));
                    } catch (JSONException null) {}
                } catch (JSONException jSONException) {}
                jSONException.printStackTrace();
            }
        }
