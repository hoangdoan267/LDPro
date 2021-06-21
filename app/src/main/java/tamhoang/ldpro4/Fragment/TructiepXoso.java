package tamhoang.ldpro4.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Congthuc.Congthuc;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.NotificationBindObject;
import tamhoang.ldpro4.data.Database;

public class TructiepXoso extends Fragment {
    public static final String EXTRA_FROM_NOTIFICATION = "EXTRA_FROM_NOTIFICATION";

    String DangXuat = "lo";

    int So_giai = 0;

    Switch Switch1;

    Database db;

    Handler handler;

    List<JSONObject> jsonValues;

    ArrayList<String> listSo = new ArrayList<String>();

    ListView listView;

    String mDate = "";

    WebView mWebView;

    RadioButton rdb_XemLo;

    RadioButton rdb_XemXien;

    private Runnable runnable = new Runnable() {
        public void run() {
            if (TructiepXoso.this.listSo.size() > 26) {
                TructiepXoso.this.handler.removeCallbacks(TructiepXoso.this.runnable);
            } else {
                TructiepXoso tructiepXoso = TructiepXoso.this;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("(function() { return ");
                stringBuilder.append("document.getElementsByClassName('firstlast-mb fl')[0].innerText;");
                stringBuilder.append("; })();");
                tructiepXoso.loadJavascript(stringBuilder.toString());
                TructiepXoso.this.handler.postDelayed(this, 2000L);
            }
        }
    };

    View v;

    private boolean isNetworkConnected() {
        boolean bool;
        NetworkInfo networkInfo = ((ConnectivityManager)getActivity().getSystemService("connectivity")).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            bool = true;
        } else {
            bool = false;
        }
        return bool;
    }

    private void setUpWebViewDefaults(WebView paramWebView) {
        WebSettings webSettings = paramWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        if (Build.VERSION.SDK_INT > 11)
            webSettings.setDisplayZoomControls(false);
        if (Build.VERSION.SDK_INT >= 19)
            WebView.setWebContentsDebuggingEnabled(true);
    }

    public void TinhTienTuDong(ArrayList<String> paramArrayList) {
        Database database3 = this.db;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Update tbl_soctS Set so_nhay = 0, ket_qua = 0 WHERE ngay_nhan = '");
        stringBuilder2.append(this.mDate);
        stringBuilder2.append("' AND the_loai <> 'tt' AND the_loai <> 'cn'");
        database3.QueryData(stringBuilder2.toString());
        String str = "";
        byte b;
        for (b = 0; b < paramArrayList.size(); b++) {
            Database database = this.db;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Update tbl_soctS Set so_nhay = so_nhay + 1 Where the_loai = 'lo' and ngay_nhan = '");
            stringBuilder2.append(this.mDate);
            stringBuilder2.append("' And so_chon ='");
            stringBuilder2.append(paramArrayList.get(b));
            stringBuilder2.append("'");
            database.QueryData(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(paramArrayList.get(b));
            stringBuilder2.append(",");
            str = stringBuilder2.toString();
        }
        Database database1 = this.db;
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Select * From tbl_soctS Where ngay_nhan = '");
        stringBuilder2.append(this.mDate);
        stringBuilder2.append("' AND the_loai = 'xi'");
        Cursor cursor = database1.GetData(stringBuilder2.toString());
        while (cursor.moveToNext()) {
            boolean bool2;
            String[] arrayOfString = cursor.getString(7).split(",");
            boolean bool1 = true;
            b = 0;
            while (true) {
                bool2 = bool1;
                if (b < arrayOfString.length) {
                    if (str.indexOf(arrayOfString[b]) == -1) {
                        bool2 = false;
                        break;
                    }
                    b++;
                    continue;
                }
                break;
            }
            if (bool2 == true) {
                Database database = this.db;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Update tbl_soctS Set so_nhay = 1 WHERE ID = ");
                stringBuilder.append(cursor.getString(0));
                database.QueryData(stringBuilder.toString());
            }
        }
        Database database2 = this.db;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Update tbl_soctS set ket_qua = diem * lan_an * so_nhay - tong_tien WHERE ngay_nhan = '");
        stringBuilder1.append(this.mDate);
        stringBuilder1.append("' AND type_kh = 1 AND the_loai <> 'tt' AND the_loai <> 'cn'");
        database2.QueryData(stringBuilder1.toString());
        database2 = this.db;
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Update tbl_soctS set ket_qua = -diem * lan_an * so_nhay + tong_tien WHERE ngay_nhan = '");
        stringBuilder1.append(this.mDate);
        stringBuilder1.append("' AND type_kh = 2 AND the_loai <> 'tt' AND the_loai <> 'cn'");
        database2.QueryData(stringBuilder1.toString());
    }

    public void Xem_lv() {
        String str;
        this.jsonValues = new ArrayList<JSONObject>();
        MainActivity mainActivity = new MainActivity();
        this.mDate = MainActivity.Get_date();
        if (this.DangXuat == "lo") {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Select tbl_soctS.So_chon\n, Sum((tbl_soctS.type_kh = 1) * (100-tbl_soctS.diem_khachgiu)*diem_quydoi/100) as diem\n, 0, Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) as chuyen\n, Sum((tbl_soctS.type_kh =1) * (100-tbl_soctS.diem_khachgiu-tbl_soctS.diem_dly_giu)*diem_quydoi/100) - Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) as ton\n, so_nhay  From so_om Left Join tbl_soctS On tbl_soctS.so_chon = so_om.So \n Where tbl_soctS.ngay_nhan='");
            stringBuilder.append(this.mDate);
            stringBuilder.append("' AND tbl_soctS.the_loai='lo' \n GROUP by so_om.So Order by ton DESC, diem DESC");
            str = stringBuilder.toString();
        } else {
            this.db.GetData("Select * From So_om WHERE ID = 1").moveToFirst();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT so_chon, sum((type_kh =1)*(100-diem_khachgiu)*diem)/100 AS diem, 0, SUm((type_kh =2)*diem) as chuyen , SUm((type_kh =1)*(100-diem_khachgiu-diem_dly_giu)*diem/100)-SUm((type_kh =2)*diem) AS ton, so_nhay   From tbl_soctS Where ngay_nhan='");
            stringBuilder.append(this.mDate);
            stringBuilder.append("' AND the_loai='xi' GROUP by so_chon Order by ton DESC, diem DESC");
            str = stringBuilder.toString();
        }
        Cursor cursor = this.db.GetData(str);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        if (cursor != null) {
            try {
                label98: while (true) {
                    if (cursor.moveToNext()) {
                        MainActivity mainActivity1;
                        Comparator<JSONObject> comparator2;
                        JSONObject jSONObject = new JSONObject();
                        this();
                        String str1 = this.DangXuat;
                        if (str1 == "lo")
                            try {
                                jSONObject.put("so_chon", cursor.getString(0));
                                jSONObject.put("xep_diem", cursor.getInt(5));
                                str1 = str;
                                mainActivity1 = mainActivity;
                                String str2 = str1;
                                continue;
                            } catch (JSONException null) {
                            // Byte code: goto -> 904
                        }
                        if (this.listSo.size() > 0) {
                            byte b1 = 0;
                            String[] arrayOfString = cursor.getString(0).split(",");
                            str1 = "";
                            byte b2 = 0;
                            try {
                                while (true) {
                                    Comparator<JSONObject> comparator;
                                    List<JSONObject> list2;
                                    if (b2 < arrayOfString.length) {
                                        ArrayList<String> arrayList = this.listSo;
                                        try {
                                            if (arrayList.indexOf(arrayOfString[b2]) > -1) {
                                                StringBuilder stringBuilder = new StringBuilder();
                                                this();
                                                stringBuilder.append(str1);
                                                stringBuilder.append("<font color='#FF0000'>");
                                                stringBuilder.append(arrayOfString[b2]);
                                                stringBuilder.append("</font>,");
                                                str1 = stringBuilder.toString();
                                                b1++;
                                            } else {
                                                StringBuilder stringBuilder = new StringBuilder();
                                                this();
                                                stringBuilder.append(str1);
                                                stringBuilder.append(arrayOfString[b2]);
                                                stringBuilder.append(",");
                                                str1 = stringBuilder.toString();
                                            }
                                            b2++;
                                        } catch (JSONException jSONException3) {
                                            // Byte code: goto -> 904
                                        }
                                        continue;
                                    }
                                    JSONException jSONException5 = jSONException3;
                                    if (b1 == 4) {
                                        jSONObject.put("xep_diem", 1000);
                                    } else if (b1 == 3 && arrayOfString.length == 3) {
                                        jSONObject.put("xep_diem", 900);
                                    } else if (b1 == 2 && arrayOfString.length == 2) {
                                        jSONObject.put("xep_diem", 800);
                                    } else if (b1 == 3 && arrayOfString.length == 4) {
                                        jSONObject.put("xep_diem", 100);
                                    } else if (b1 == 2 && arrayOfString.length == 4) {
                                        jSONObject.put("xep_diem", 70);
                                    } else if (b1 == 1 && arrayOfString.length == 4) {
                                        jSONObject.put("xep_diem", 50);
                                    } else if (b1 == 2 && arrayOfString.length == 3) {
                                        jSONObject.put("xep_diem", 90);
                                    } else if (b1 == 1 && arrayOfString.length == 3) {
                                        jSONObject.put("xep_diem", 60);
                                    } else if (b1 == 1 && arrayOfString.length == 2) {
                                        jSONObject.put("xep_diem", 80);
                                    } else {
                                        jSONObject.put("xep_diem", 0);
                                    }
                                    jSONObject.put("so_chon", str1);
                                    MainActivity mainActivity2 = mainActivity1;
                                    JSONException jSONException4 = jSONException5;
                                    jSONObject.put("tien_nhan", decimalFormat.format(cursor.getInt(1)));
                                    jSONObject.put("tien_om", decimalFormat.format(cursor.getInt(2)));
                                    jSONObject.put("tien_chuyen", decimalFormat.format(cursor.getInt(3)));
                                    jSONObject.put("tien_ton", decimalFormat.format(cursor.getInt(4)));
                                    jSONObject.put("so_nhay", cursor.getInt(5));
                                    if (this.DangXuat == "lo") {
                                        this.jsonValues.add(jSONObject);
                                    } else if (this.DangXuat == "xi" && cursor.getInt(4) > 0) {
                                        this.jsonValues.add(jSONObject);
                                    } else {
                                        list2 = this.jsonValues;
                                        comparator = new Comparator<JSONObject>() {
                                            public int compare(JSONObject param1JSONObject1, JSONObject param1JSONObject2) {
                                                Integer integer1;
                                                Integer integer2;
                                                Integer integer3 = Integer.valueOf(0);
                                                Integer integer4 = Integer.valueOf(0);
                                                try {
                                                    integer1 = Integer.valueOf(param1JSONObject1.getInt("xep_diem"));
                                                    integer3 = integer1;
                                                    int i = param1JSONObject2.getInt("xep_diem");
                                                    integer2 = Integer.valueOf(i);
                                                } catch (JSONException jSONException) {
                                                    integer1 = integer3;
                                                    integer2 = integer4;
                                                }
                                                return integer2.compareTo(integer1);
                                            }
                                        };
                                        super(this);
                                        Collections.sort(list2, comparator);
                                        break;
                                    }
                                    List<JSONObject> list3 = list2;
                                    comparator2 = comparator;
                                    List<JSONObject> list1 = list3;
                                    continue label98;
                                }
                            } catch (JSONException jSONException1) {
                                // Byte code: goto -> 904
                            }
                            break;
                        }
                        Comparator<JSONObject> comparator3 = comparator2;
                        jSONObject.put("so_chon", cursor.getString(0));
                        jSONObject.put("xep_diem", 0);
                        JSONException jSONException2 = jSONException1;
                        Comparator<JSONObject> comparator1 = comparator3;
                        continue;
                    }
                    continue;
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        if (getActivity() != null)
            this.listView.setAdapter((ListAdapter)new So_OmAdapter((Context)getActivity(), 2131427395, this.jsonValues));
    }

    public void init() {
        this.Switch1 = (Switch)this.v.findViewById(2131230738);
        this.rdb_XemLo = (RadioButton)this.v.findViewById(2131231186);
        this.rdb_XemXien = (RadioButton)this.v.findViewById(2131231187);
        this.listView = (ListView)this.v.findViewById(2131230727);
        this.mWebView = (WebView)this.v.findViewById(2131230979);
    }

    public void loadJavascript(String paramString) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mWebView.evaluateJavascript(paramString, new ValueCallback<String>() {
                public void onReceiveValue(String param1String) {
                    JsonReader jsonReader = new JsonReader(new StringReader(param1String));
                    jsonReader.setLenient(true);
                    try {
                        if (jsonReader.peek() != JsonToken.NULL && jsonReader.peek() == JsonToken.STRING) {
                            String str = jsonReader.nextString();
                            if (str != null && str.indexOf("\n") > -1) {
                                String[] arrayOfString = str.substring(str.indexOf("0")).split("\n");
                                byte b = 0;
                                while (true) {
                                    int i = arrayOfString.length;
                                    if (b < i) {
                                        if (arrayOfString[b].length() > 2) {
                                            arrayOfString[b] = arrayOfString[b].substring(2);
                                        } else {
                                            arrayOfString[b] = "";
                                        }
                                        b++;
                                        continue;
                                    }
                                    if (arrayOfString.length == 10) {
                                        TructiepXoso tructiepXoso = TructiepXoso.this;
                                        ArrayList<String> arrayList = new ArrayList();
                                        this();
                                        tructiepXoso.listSo = arrayList;
                                        for (b = 0; b < arrayOfString.length; b++) {
                                            String[] arrayOfString1 = arrayOfString[b].replaceAll(" ", "").split(",");
                                            for (i = 0; i < arrayOfString1.length; i++) {
                                                if (arrayOfString1[i].length() == 1) {
                                                    ArrayList<String> arrayList1 = TructiepXoso.this.listSo;
                                                    StringBuilder stringBuilder = new StringBuilder();
                                                    this();
                                                    stringBuilder.append(b);
                                                    stringBuilder.append(arrayOfString1[i]);
                                                    arrayList1.add(stringBuilder.toString());
                                                } else if (arrayOfString1[i].length() == 2) {
                                                    arrayList = TructiepXoso.this.listSo;
                                                    StringBuilder stringBuilder = new StringBuilder();
                                                    this();
                                                    stringBuilder.append(b);
                                                    stringBuilder.append(arrayOfString1[i].substring(1));
                                                    arrayList.add(stringBuilder.toString());
                                                }
                                            }
                                        }
                                        if (TructiepXoso.this.listSo.size() != TructiepXoso.this.So_giai) {
                                            TructiepXoso.this.TinhTienTuDong(TructiepXoso.this.listSo);
                                            TructiepXoso.this.Xem_lv();
                                            TructiepXoso.this.So_giai = TructiepXoso.this.listSo.size();
                                        }
                                        break;
                                    }
                                    Toast.makeText((Context)TructiepXoso.this.getActivity(), "Trang xoso.me bl, 1).show();
                                            TructiepXoso.this.handler.removeCallbacks(TructiepXoso.this.runnable);
                                    try {
                                        jsonReader.close();
                                    } catch (IOException null) {}
                                }
                            }
                        }
                        try {
                            iOException.close();
                        } catch (IOException iOException) {}
                    } catch (IOException iOException1) {
                        Log.e("TAG", "MainActivity: IOException", iOException1);
                        iOException.close();
                    } finally {
                        Exception exception;
                    }
                }
            });
        } else {
            WebView webView = this.mWebView;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("javascript:");
            stringBuilder.append(paramString);
            webView.loadUrl(stringBuilder.toString());
        }
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        onCreate(paramBundle);
        this.v = paramLayoutInflater.inflate(2131427457, paramViewGroup, false);
        this.db = new Database((Context)getActivity());
        init();
        Calendar.getInstance().setTime(new Date());
        (new SimpleDateFormat("yyyy-MM-dd")).setTimeZone(TimeZone.getDefault());
        this.Switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (TructiepXoso.this.Switch1.isChecked()) {
                    TructiepXoso.this.mWebView.setVisibility(0);
                } else {
                    TructiepXoso.this.mWebView.setVisibility(8);
                }
            }
        });
        this.rdb_XemLo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (TructiepXoso.this.rdb_XemLo.isChecked()) {
                    TructiepXoso.this.DangXuat = "lo";
                    TructiepXoso.this.Xem_lv();
                }
            }
        });
        this.rdb_XemXien.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (TructiepXoso.this.rdb_XemXien.isChecked()) {
                    TructiepXoso.this.DangXuat = "xi";
                    TructiepXoso.this.Xem_lv();
                }
            }
        });
        this.handler = new Handler();
        if (Congthuc.CheckTime("18:14") && !Congthuc.CheckTime("24:30")) {
            this.Switch1.setText("bKqu);
            this.handler.postDelayed(this.runnable, 3000L);
            this.mWebView.setVisibility(8);
        } else {
            this.mWebView.setVisibility(8);
        }
        this.mWebView.addJavascriptInterface(new NotificationBindObject(getActivity().getApplicationContext()), "NotificationBind");
        setUpWebViewDefaults(this.mWebView);
        if (paramBundle != null)
            this.mWebView.restoreState(paramBundle);
        if (this.mWebView.getUrl() == null)
            this.mWebView.loadUrl("https://xoso.me/embedded/kq-mienbac");
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView param1WebView, String param1String) {
                TructiepXoso.this.loadJavascript("document.getElementsByClassName('embeded-breadcrumb')[0].style.display = 'none';\ndocument.getElementsByClassName('tit-mien')[0].style.display = 'none';");
                TructiepXoso.this.mWebView.setVisibility(0);
                TructiepXoso.this.Switch1.setEnabled(true);
            }
        });
        this.mWebView.setEnabled(false);
        Xem_lv();
        return this.v;
    }

    public void onResume() {
        super.onResume();
        if (Congthuc.CheckTime("18:14") && !Congthuc.CheckTime("18:30") && isNetworkConnected()) {
            this.Switch1.setText("bKqu);
            this.handler.postDelayed(this.runnable, 3000L);
        }
    }

    public void onStop() {
        super.onStop();
        this.mWebView.clearCache(true);
        this.handler.removeCallbacks(this.runnable);
    }

    class So_OmAdapter extends ArrayAdapter {
        public So_OmAdapter(Context param1Context, int param1Int, List<JSONObject> param1List) {
            super(param1Context, param1Int, param1List);
        }

        public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
            ViewHolder viewHolder;
            if (param1View == null) {
                param1View = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427395, null);
                viewHolder = new ViewHolder();
                viewHolder.tview5 = (TextView)param1View.findViewById(2131230742);
                viewHolder.tview7 = (TextView)param1View.findViewById(2131231439);
                viewHolder.tview8 = (TextView)param1View.findViewById(2131231440);
                viewHolder.tview1 = (TextView)param1View.findViewById(2131231424);
                viewHolder.tview4 = (TextView)param1View.findViewById(2131231441);
                viewHolder.tview2 = (TextView)param1View.findViewById(2131231286);
                param1View.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder)param1View.getTag();
            }
            JSONObject jSONObject = TructiepXoso.this.jsonValues.get(param1Int);
            try {
                StringBuilder stringBuilder;
                TextView textView;
                int i = jSONObject.getInt("so_nhay");
                if (i > 0) {
                    viewHolder.tview5.setTextColor(-65536);
                    viewHolder.tview7.setTextColor(-65536);
                    viewHolder.tview8.setTextColor(-65536);
                    viewHolder.tview1.setTextColor(-65536);
                    viewHolder.tview4.setTextColor(-65536);
                    if (jSONObject.getInt("so_nhay") == 1) {
                        TextView textView1 = viewHolder.tview5;
                        StringBuilder stringBuilder1 = new StringBuilder();
                        this();
                        stringBuilder1.append(Html.fromHtml(jSONObject.getString("so_chon")));
                        stringBuilder1.append("*");
                        textView1.setText(stringBuilder1.toString());
                    } else if (jSONObject.getInt("so_nhay") == 2) {
                        TextView textView1 = viewHolder.tview5;
                        StringBuilder stringBuilder1 = new StringBuilder();
                        this();
                        stringBuilder1.append(Html.fromHtml(jSONObject.getString("so_chon")));
                        stringBuilder1.append("**");
                        textView1.setText(stringBuilder1.toString());
                    } else if (jSONObject.getInt("so_nhay") == 3) {
                        TextView textView1 = viewHolder.tview5;
                        StringBuilder stringBuilder1 = new StringBuilder();
                        this();
                        stringBuilder1.append(Html.fromHtml(jSONObject.getString("so_chon")));
                        stringBuilder1.append("***");
                        textView1.setText(stringBuilder1.toString());
                    } else if (jSONObject.getInt("so_nhay") == 4) {
                        TextView textView1 = viewHolder.tview5;
                        StringBuilder stringBuilder1 = new StringBuilder();
                        this();
                        stringBuilder1.append(Html.fromHtml(jSONObject.getString("so_chon")));
                        stringBuilder1.append("****");
                        textView1.setText(stringBuilder1.toString());
                    } else if (jSONObject.getInt("so_nhay") == 5) {
                        TextView textView1 = viewHolder.tview5;
                        StringBuilder stringBuilder1 = new StringBuilder();
                        this();
                        stringBuilder1.append(Html.fromHtml(jSONObject.getString("so_chon")));
                        stringBuilder1.append("*****");
                        textView1.setText(stringBuilder1.toString());
                    } else if (jSONObject.getInt("so_nhay") == 6) {
                        TextView textView1 = viewHolder.tview5;
                        StringBuilder stringBuilder1 = new StringBuilder();
                        this();
                        stringBuilder1.append(Html.fromHtml(jSONObject.getString("so_chon")));
                        stringBuilder1.append("******");
                        textView1.setText(stringBuilder1.toString());
                    }
                    viewHolder.tview7.setText(jSONObject.getString("tien_nhan"));
                    viewHolder.tview8.setText(jSONObject.getString("tien_om"));
                    viewHolder.tview1.setText(jSONObject.getString("tien_chuyen"));
                    viewHolder.tview4.setText(jSONObject.getString("tien_ton"));
                    textView = viewHolder.tview2;
                    stringBuilder = new StringBuilder();
                    this();
                    stringBuilder.append(param1Int + 1);
                    stringBuilder.append("");
                    textView.setText(stringBuilder.toString());
                } else {
                    ((ViewHolder)stringBuilder).tview5.setTextColor(-16777216);
                    ((ViewHolder)stringBuilder).tview7.setTextColor(-16777216);
                    ((ViewHolder)stringBuilder).tview8.setTextColor(-16777216);
                    ((ViewHolder)stringBuilder).tview1.setTextColor(-16777216);
                    ((ViewHolder)stringBuilder).tview4.setTextColor(-16777216);
                    ((ViewHolder)stringBuilder).tview5.setText((CharSequence)Html.fromHtml(textView.getString("so_chon")));
                    ((ViewHolder)stringBuilder).tview7.setText(textView.getString("tien_nhan"));
                    ((ViewHolder)stringBuilder).tview8.setText(textView.getString("tien_om"));
                    ((ViewHolder)stringBuilder).tview1.setText(textView.getString("tien_chuyen"));
                    ((ViewHolder)stringBuilder).tview4.setText(textView.getString("tien_ton"));
                    TextView textView1 = ((ViewHolder)stringBuilder).tview2;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    this();
                    stringBuilder1.append(param1Int + 1);
                    stringBuilder1.append("");
                    textView1.setText(stringBuilder1.toString());
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
            return param1View;
        }

        class ViewHolder {
            TextView tview1;

            TextView tview2;

            TextView tview4;

            TextView tview5;

            TextView tview7;

            TextView tview8;
        }
    }

    class ViewHolder {
        TextView tview1;

        TextView tview2;

        TextView tview4;

        TextView tview5;

        TextView tview7;

        TextView tview8;
    }
}
