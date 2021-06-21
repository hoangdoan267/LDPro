package tamhoang.ldpro4.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Congthuc.Congthuc;
import tamhoang.ldpro4.Login;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.NotificationBindObject;
import tamhoang.ldpro4.data.Database;

public class Frag_Database extends Fragment {
    String[] ArrayGiai;

    String Imei = null;

    Button btnDelete;

    Button btn_tt;

    Database db;

    RadioGroup gr1;

    RadioGroup gr2;

    RadioButton ketquanet;

    WebView mWebView;

    RadioButton minhngoc;

    RadioButton nazzy;

    View v;

    RadioButton xosome;

    RadioButton xsme;

    RadioButton xsmn;

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

    public void DelAllSQL() {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)getActivity());
        builder.setTitle("Xohcsdli);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                        Frag_Database.this.db.QueryData("DROP TABLE if exists Chat_database");
                        Frag_Database.this.db.QueryData("DROP TABLE if exists tbl_tinnhanS");
                        Frag_Database.this.db.QueryData("DROP TABLE if exists tbl_soctS");
                        Frag_Database.this.db.Creat_TinNhanGoc();
                        Frag_Database.this.db.Creat_SoCT();
                        Frag_Database.this.db.Create_table_Chat();
                        Toast.makeText((Context)Frag_Database.this.getActivity(), "xo, 1).show();
                    }
                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                param1DialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    public void DelAllSQL_Congno() {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)getActivity());
        builder.setTitle("Xodlivgicn);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(5, -1);
                        String str = simpleDateFormat.format(new Date(calendar.getTimeInMillis()));
                        Cursor cursor = Frag_Database.this.db.GetData("Select tbl_soctS.ten_kh\n, SUM(tbl_soctS.ket_qua * (100-tbl_soctS.diem_khachgiu)/100)/1000  as NoCu,   \ntbl_soctS.so_dienthoai, tbl_kh_new.type_kh  \nFROM tbl_soctS INNER JOIN tbl_kh_new ON tbl_soctS.so_dienthoai = tbl_kh_new.sdt\nGROUP BY tbl_soctS.ten_kh ORDER BY tbl_soctS.type_kh DESC");
                        mTenKH.clear();
                        mSodt.clear();
                        mSoTien.clear();
                        while (cursor.moveToNext()) {
                            mTenKH.add(cursor.getString(0));
                            mSodt.add(cursor.getString(2));
                            List<String> list = mSoTien;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(cursor.getDouble(1) * 1000.0D);
                            stringBuilder.append("");
                            list.add(stringBuilder.toString());
                        }
                        Frag_Database.this.db.QueryData("DROP TABLE if exists Chat_database");
                        Frag_Database.this.db.QueryData("DROP TABLE if exists tbl_tinnhanS");
                        Frag_Database.this.db.QueryData("DROP TABLE if exists tbl_soctS");
                        Frag_Database.this.db.Creat_TinNhanGoc();
                        Frag_Database.this.db.Creat_SoCT();
                        Frag_Database.this.db.Create_table_Chat();
                        for (param1Int = 0; param1Int < mTenKH.size(); param1Int++) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Insert Into tbl_soctS (ngay_nhan, ten_kh, so_dienthoai, the_loai, ket_qua) Values ('");
                            stringBuilder.append(str);
                            stringBuilder.append("','");
                            stringBuilder.append(mTenKH.get(param1Int));
                            stringBuilder.append("','");
                            stringBuilder.append(mSodt.get(param1Int));
                            stringBuilder.append("', 'cn',");
                            stringBuilder.append(mSoTien.get(param1Int));
                            stringBuilder.append(")");
                            String str1 = stringBuilder.toString();
                            Frag_Database.this.db.QueryData(str1);
                        }
                        Toast.makeText((Context)Frag_Database.this.getActivity(), "xo, 1).show();
                    }
                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                param1DialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    public void DisplayKQnet() {
        this.mWebView.setVisibility(8);
        new MainActivity();
        String str = MainActivity.Get_ngay().replaceAll("/", "-");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://xoso.com.vn/xsmb-");
        stringBuilder.append(str);
        stringBuilder.append(".html");
        str = stringBuilder.toString();
        this.mWebView.loadUrl(str);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView param1WebView, String param1String) {
                Frag_Database.this.mWebView.setVisibility(0);
                Frag_Database frag_Database = Frag_Database.this;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("(function() { return ");
                stringBuilder.append("document.getElementsByClassName('table-result')[0].innerText;");
                stringBuilder.append("; })();");
                frag_Database.loadJavascript(stringBuilder.toString());
            }
        });
    }

    public void DisplayKQnetNew() {
        this.mWebView.setVisibility(8);
        new MainActivity();
        String str = MainActivity.Get_ngay().replaceAll("/", "-");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://ketqua.net/xo-so-mien-bac.php?ngay=");
        stringBuilder.append(str);
        str = stringBuilder.toString();
        this.mWebView.loadUrl(str);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView param1WebView, String param1String) {
                Frag_Database.this.mWebView.setVisibility(0);
                Frag_Database frag_Database = Frag_Database.this;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("(function() { return ");
                stringBuilder.append("document.getElementsByClassName('table table-condensed kqcenter kqvertimarginw table-kq-border table-kq-hover-div table-bordered kqbackground table-kq-bold-border tb-phoi-border watermark table-striped')[0].innerText;");
                stringBuilder.append("; })();");
                frag_Database.loadJavascript(stringBuilder.toString());
            }
        });
    }

    public void DisplayXSMNNew() {
        this.mWebView.setVisibility(8);
        new MainActivity();
        String str1 = MainActivity.Get_ngay().replaceAll("/", "-");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://xsmn.me/embedded/kq-mienbac?ngay_quay=");
        stringBuilder.append(str1);
        String str2 = stringBuilder.toString();
        this.mWebView.loadUrl(str2);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView param1WebView, String param1String) {
                Frag_Database.this.mWebView.setVisibility(0);
                Frag_Database frag_Database = Frag_Database.this;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("(function() { return ");
                stringBuilder.append("document.getElementsByClassName('extendable kqmb colgiai')[0].innerText;");
                stringBuilder.append("; })();");
                frag_Database.loadJavascript(stringBuilder.toString());
            }
        });
    }

    public void DisplayXSme() {
        this.mWebView.setVisibility(8);
        new MainActivity();
        String str = MainActivity.Get_ngay().replaceAll("/", "-");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://xosodaiphat.com/xsmb-");
        stringBuilder.append(str);
        stringBuilder.append(".html");
        str = stringBuilder.toString();
        this.mWebView.loadUrl(str);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView param1WebView, String param1String) {
                String str;
                Frag_Database.this.loadJavascript("document.getElementsByClassName('embeded-breadcrumb')[0].style.display = 'none';\ndocument.getElementsByClassName('tit-mien')[0].style.display = 'none';");
                Frag_Database.this.mWebView.setVisibility(0);
                if (Frag_Database.this.xosome.isChecked()) {
                    str = "document.getElementsByClassName('table table-bordered table-striped table-xsmb')[0].innerText;";
                } else {
                    str = "document.getElementsByClassName('table-result')[0].innerText;";
                }
                Frag_Database frag_Database = Frag_Database.this;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("(function() { return ");
                stringBuilder.append(str);
                stringBuilder.append("; })();");
                frag_Database.loadJavascript(stringBuilder.toString());
            }
        });
    }

    public void DisplayXSmeNew() {
        this.mWebView.setVisibility(8);
        new MainActivity();
        String str = MainActivity.Get_ngay().replaceAll("/", "-");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://xoso.me/embedded/kq-mienbac?ngay_quay=");
        stringBuilder.append(str);
        str = stringBuilder.toString();
        this.mWebView.loadUrl(str);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView param1WebView, String param1String) {
                Frag_Database.this.mWebView.setVisibility(0);
                Frag_Database frag_Database = Frag_Database.this;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("(function() { return ");
                stringBuilder.append("document.getElementsByClassName('kqmb extendable')[0].innerText;");
                stringBuilder.append("; })();");
                frag_Database.loadJavascript(stringBuilder.toString());
            }
        });
    }

    public void PhantichMinhngoc() {
        new MainActivity();
        String str = MainActivity.Get_date();
        boolean bool = true;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("InSert Into KETQUA VALUES(null,'");
            stringBuilder.append(str);
            stringBuilder.append("',");
            String str1 = stringBuilder.toString();
            byte b = 0;
            while (true) {
                int i = this.ArrayGiai.length;
                if (b < i) {
                    String[] arrayOfString = this.ArrayGiai[b].split(" ");
                    i = 0;
                    while (i < arrayOfString.length) {
                        String str2;
                        if (Congthuc.isNumeric(arrayOfString[i]) && arrayOfString[i].length() > 1) {
                            StringBuilder stringBuilder1 = new StringBuilder();
                            this();
                            stringBuilder1.append(str1);
                            stringBuilder1.append("'");
                            stringBuilder1.append(arrayOfString[i]);
                            stringBuilder1.append("',");
                            str2 = stringBuilder1.toString();
                        } else {
                            str2 = str1;
                            if (arrayOfString[i].length() < 1) {
                                bool = false;
                                str2 = str1;
                            }
                        }
                        i++;
                        str1 = str2;
                    }
                    b++;
                    continue;
                }
                if (bool == true) {
                    Database database = this.db;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    this();
                    stringBuilder1.append("Delete From ketqua WHERE ngay = '");
                    stringBuilder1.append(str);
                    stringBuilder1.append("'");
                    database.QueryData(stringBuilder1.toString());
                    stringBuilder1 = new StringBuilder();
                    this();
                    stringBuilder1.append(str1.substring(0, str1.length() - 1));
                    stringBuilder1.append(")");
                    str1 = stringBuilder1.toString();
                    this.db.QueryData(str1);
                    FragmentActivity fragmentActivity = getActivity();
                    stringBuilder1 = new StringBuilder();
                    this();
                    stringBuilder1.append("txong kqung");
                    stringBuilder1.append(MainActivity.Get_ngay());
                    Toast.makeText((Context)fragmentActivity, stringBuilder1.toString(), 1).show();
                } else {
                    Toast.makeText((Context)getActivity(), "Chckqu, 1).show();
                }
                return;
            }
        } catch (Exception exception) {}
    }

    public void PhantichXosome() {
        new MainActivity();
        String str = MainActivity.Get_date();
        boolean bool = true;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("InSert Into KETQUA VALUES(null,'");
            stringBuilder.append(str);
            stringBuilder.append("',");
            String str1 = stringBuilder.toString();
            byte b = 0;
            while (true) {
                int i = this.ArrayGiai.length;
                if (b < i) {
                    String str2;
                    if (Congthuc.isNumeric(this.ArrayGiai[b])) {
                        StringBuilder stringBuilder1 = new StringBuilder();
                        this();
                        stringBuilder1.append(str1);
                        stringBuilder1.append("'");
                        stringBuilder1.append(this.ArrayGiai[b]);
                        stringBuilder1.append("',");
                        str2 = stringBuilder1.toString();
                    } else {
                        str2 = str1;
                        if (this.ArrayGiai[b].length() < 2) {
                            bool = false;
                            str2 = str1;
                        }
                    }
                    b++;
                    str1 = str2;
                    continue;
                }
                if (bool == true) {
                    Database database = this.db;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    this();
                    stringBuilder2.append("Delete From ketqua WHERE ngay = '");
                    stringBuilder2.append(str);
                    stringBuilder2.append("'");
                    database.QueryData(stringBuilder2.toString());
                    StringBuilder stringBuilder1 = new StringBuilder();
                    this();
                    stringBuilder1.append(str1.substring(0, str1.length() - 1));
                    stringBuilder1.append(")");
                    str1 = stringBuilder1.toString();
                    this.db.QueryData(str1);
                    FragmentActivity fragmentActivity = getActivity();
                    stringBuilder1 = new StringBuilder();
                    this();
                    stringBuilder1.append("txong kqung");
                    stringBuilder1.append(MainActivity.Get_ngay());
                    Toast.makeText((Context)fragmentActivity, stringBuilder1.toString(), 1).show();
                } else {
                    Toast.makeText((Context)getActivity(), "Chckqu, 1).show();
                }
                return;
            }
        } catch (Exception exception) {}
    }

    public void PhantichXosomeNew() {
        // Byte code:
        //   0: new tamhoang/ldpro4/MainActivity
        //   3: dup
        //   4: invokespecial <init> : ()V
        //   7: pop
        //   8: invokestatic Get_date : ()Ljava/lang/String;
        //   11: astore_1
        //   12: ldc_w ''
        //   15: astore_2
        //   16: aload_2
        //   17: astore_3
        //   18: aload_2
        //   19: astore #4
        //   21: aload_0
        //   22: getfield ArrayGiai : [Ljava/lang/String;
        //   25: iconst_2
        //   26: aaload
        //   27: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   30: ifeq -> 4248
        //   33: aload_2
        //   34: astore_3
        //   35: aload_2
        //   36: astore #4
        //   38: new java/lang/StringBuilder
        //   41: astore #5
        //   43: aload_2
        //   44: astore_3
        //   45: aload_2
        //   46: astore #4
        //   48: aload #5
        //   50: invokespecial <init> : ()V
        //   53: aload_2
        //   54: astore_3
        //   55: aload_2
        //   56: astore #4
        //   58: aload #5
        //   60: ldc_w '''
        //   63: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   66: pop
        //   67: aload_2
        //   68: astore_3
        //   69: aload_2
        //   70: astore #4
        //   72: aload #5
        //   74: aload_0
        //   75: getfield ArrayGiai : [Ljava/lang/String;
        //   78: iconst_2
        //   79: aaload
        //   80: invokevirtual trim : ()Ljava/lang/String;
        //   83: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   86: pop
        //   87: aload_2
        //   88: astore_3
        //   89: aload_2
        //   90: astore #4
        //   92: aload #5
        //   94: ldc_w '','
        //   97: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   100: pop
        //   101: aload_2
        //   102: astore_3
        //   103: aload_2
        //   104: astore #4
        //   106: aload #5
        //   108: invokevirtual toString : ()Ljava/lang/String;
        //   111: astore_2
        //   112: aload_2
        //   113: astore_3
        //   114: aload_2
        //   115: astore #4
        //   117: aload_0
        //   118: getfield ArrayGiai : [Ljava/lang/String;
        //   121: iconst_4
        //   122: aaload
        //   123: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   126: ifeq -> 4073
        //   129: aload_2
        //   130: astore_3
        //   131: aload_2
        //   132: astore #4
        //   134: new java/lang/StringBuilder
        //   137: astore #5
        //   139: aload_2
        //   140: astore_3
        //   141: aload_2
        //   142: astore #4
        //   144: aload #5
        //   146: invokespecial <init> : ()V
        //   149: aload_2
        //   150: astore_3
        //   151: aload_2
        //   152: astore #4
        //   154: aload #5
        //   156: aload_2
        //   157: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   160: pop
        //   161: aload_2
        //   162: astore_3
        //   163: aload_2
        //   164: astore #4
        //   166: aload #5
        //   168: ldc_w '''
        //   171: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   174: pop
        //   175: aload_2
        //   176: astore_3
        //   177: aload_2
        //   178: astore #4
        //   180: aload #5
        //   182: aload_0
        //   183: getfield ArrayGiai : [Ljava/lang/String;
        //   186: iconst_4
        //   187: aaload
        //   188: invokevirtual trim : ()Ljava/lang/String;
        //   191: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   194: pop
        //   195: aload_2
        //   196: astore_3
        //   197: aload_2
        //   198: astore #4
        //   200: aload #5
        //   202: ldc_w '','
        //   205: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   208: pop
        //   209: aload_2
        //   210: astore_3
        //   211: aload_2
        //   212: astore #4
        //   214: aload #5
        //   216: invokevirtual toString : ()Ljava/lang/String;
        //   219: astore_2
        //   220: aload_2
        //   221: astore_3
        //   222: aload_2
        //   223: astore #4
        //   225: aload_0
        //   226: getfield ArrayGiai : [Ljava/lang/String;
        //   229: bipush #6
        //   231: aaload
        //   232: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   235: ifeq -> 3898
        //   238: aload_2
        //   239: astore_3
        //   240: aload_2
        //   241: astore #4
        //   243: new java/lang/StringBuilder
        //   246: astore #5
        //   248: aload_2
        //   249: astore_3
        //   250: aload_2
        //   251: astore #4
        //   253: aload #5
        //   255: invokespecial <init> : ()V
        //   258: aload_2
        //   259: astore_3
        //   260: aload_2
        //   261: astore #4
        //   263: aload #5
        //   265: aload_2
        //   266: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   269: pop
        //   270: aload_2
        //   271: astore_3
        //   272: aload_2
        //   273: astore #4
        //   275: aload #5
        //   277: ldc_w '''
        //   280: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   283: pop
        //   284: aload_2
        //   285: astore_3
        //   286: aload_2
        //   287: astore #4
        //   289: aload #5
        //   291: aload_0
        //   292: getfield ArrayGiai : [Ljava/lang/String;
        //   295: bipush #6
        //   297: aaload
        //   298: invokevirtual trim : ()Ljava/lang/String;
        //   301: iconst_0
        //   302: iconst_5
        //   303: invokevirtual substring : (II)Ljava/lang/String;
        //   306: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   309: pop
        //   310: aload_2
        //   311: astore_3
        //   312: aload_2
        //   313: astore #4
        //   315: aload #5
        //   317: ldc_w '','
        //   320: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   323: pop
        //   324: aload_2
        //   325: astore_3
        //   326: aload_2
        //   327: astore #4
        //   329: aload #5
        //   331: invokevirtual toString : ()Ljava/lang/String;
        //   334: astore_2
        //   335: aload_2
        //   336: astore_3
        //   337: aload_2
        //   338: astore #4
        //   340: new java/lang/StringBuilder
        //   343: astore #5
        //   345: aload_2
        //   346: astore_3
        //   347: aload_2
        //   348: astore #4
        //   350: aload #5
        //   352: invokespecial <init> : ()V
        //   355: aload_2
        //   356: astore_3
        //   357: aload_2
        //   358: astore #4
        //   360: aload #5
        //   362: aload_2
        //   363: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   366: pop
        //   367: aload_2
        //   368: astore_3
        //   369: aload_2
        //   370: astore #4
        //   372: aload #5
        //   374: ldc_w '''
        //   377: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   380: pop
        //   381: aload_2
        //   382: astore_3
        //   383: aload_2
        //   384: astore #4
        //   386: aload #5
        //   388: aload_0
        //   389: getfield ArrayGiai : [Ljava/lang/String;
        //   392: bipush #6
        //   394: aaload
        //   395: invokevirtual trim : ()Ljava/lang/String;
        //   398: iconst_5
        //   399: aload_0
        //   400: getfield ArrayGiai : [Ljava/lang/String;
        //   403: bipush #6
        //   405: aaload
        //   406: invokevirtual trim : ()Ljava/lang/String;
        //   409: invokevirtual length : ()I
        //   412: invokevirtual substring : (II)Ljava/lang/String;
        //   415: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   418: pop
        //   419: aload_2
        //   420: astore_3
        //   421: aload_2
        //   422: astore #4
        //   424: aload #5
        //   426: ldc_w '','
        //   429: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   432: pop
        //   433: aload_2
        //   434: astore_3
        //   435: aload_2
        //   436: astore #4
        //   438: aload #5
        //   440: invokevirtual toString : ()Ljava/lang/String;
        //   443: astore_2
        //   444: aload_2
        //   445: astore_3
        //   446: aload_2
        //   447: astore #4
        //   449: aload_0
        //   450: getfield ArrayGiai : [Ljava/lang/String;
        //   453: bipush #8
        //   455: aaload
        //   456: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   459: ifeq -> 3723
        //   462: aload_2
        //   463: astore_3
        //   464: aload_2
        //   465: astore #4
        //   467: new java/lang/StringBuilder
        //   470: astore #5
        //   472: aload_2
        //   473: astore_3
        //   474: aload_2
        //   475: astore #4
        //   477: aload #5
        //   479: invokespecial <init> : ()V
        //   482: aload_2
        //   483: astore_3
        //   484: aload_2
        //   485: astore #4
        //   487: aload #5
        //   489: aload_2
        //   490: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   493: pop
        //   494: aload_2
        //   495: astore_3
        //   496: aload_2
        //   497: astore #4
        //   499: aload #5
        //   501: ldc_w '''
        //   504: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   507: pop
        //   508: aload_2
        //   509: astore_3
        //   510: aload_2
        //   511: astore #4
        //   513: aload #5
        //   515: aload_0
        //   516: getfield ArrayGiai : [Ljava/lang/String;
        //   519: bipush #8
        //   521: aaload
        //   522: invokevirtual trim : ()Ljava/lang/String;
        //   525: iconst_0
        //   526: iconst_5
        //   527: invokevirtual substring : (II)Ljava/lang/String;
        //   530: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   533: pop
        //   534: aload_2
        //   535: astore_3
        //   536: aload_2
        //   537: astore #4
        //   539: aload #5
        //   541: ldc_w '','
        //   544: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   547: pop
        //   548: aload_2
        //   549: astore_3
        //   550: aload_2
        //   551: astore #4
        //   553: aload #5
        //   555: invokevirtual toString : ()Ljava/lang/String;
        //   558: astore_2
        //   559: aload_2
        //   560: astore_3
        //   561: aload_2
        //   562: astore #4
        //   564: new java/lang/StringBuilder
        //   567: astore #5
        //   569: aload_2
        //   570: astore_3
        //   571: aload_2
        //   572: astore #4
        //   574: aload #5
        //   576: invokespecial <init> : ()V
        //   579: aload_2
        //   580: astore_3
        //   581: aload_2
        //   582: astore #4
        //   584: aload #5
        //   586: aload_2
        //   587: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   590: pop
        //   591: aload_2
        //   592: astore_3
        //   593: aload_2
        //   594: astore #4
        //   596: aload #5
        //   598: ldc_w '''
        //   601: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   604: pop
        //   605: aload_2
        //   606: astore_3
        //   607: aload_2
        //   608: astore #4
        //   610: aload #5
        //   612: aload_0
        //   613: getfield ArrayGiai : [Ljava/lang/String;
        //   616: bipush #8
        //   618: aaload
        //   619: invokevirtual trim : ()Ljava/lang/String;
        //   622: iconst_5
        //   623: bipush #10
        //   625: invokevirtual substring : (II)Ljava/lang/String;
        //   628: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   631: pop
        //   632: aload_2
        //   633: astore_3
        //   634: aload_2
        //   635: astore #4
        //   637: aload #5
        //   639: ldc_w '','
        //   642: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   645: pop
        //   646: aload_2
        //   647: astore_3
        //   648: aload_2
        //   649: astore #4
        //   651: aload #5
        //   653: invokevirtual toString : ()Ljava/lang/String;
        //   656: astore_2
        //   657: aload_2
        //   658: astore_3
        //   659: aload_2
        //   660: astore #4
        //   662: new java/lang/StringBuilder
        //   665: astore #5
        //   667: aload_2
        //   668: astore_3
        //   669: aload_2
        //   670: astore #4
        //   672: aload #5
        //   674: invokespecial <init> : ()V
        //   677: aload_2
        //   678: astore_3
        //   679: aload_2
        //   680: astore #4
        //   682: aload #5
        //   684: aload_2
        //   685: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   688: pop
        //   689: aload_2
        //   690: astore_3
        //   691: aload_2
        //   692: astore #4
        //   694: aload #5
        //   696: ldc_w '''
        //   699: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   702: pop
        //   703: aload_2
        //   704: astore_3
        //   705: aload_2
        //   706: astore #4
        //   708: aload #5
        //   710: aload_0
        //   711: getfield ArrayGiai : [Ljava/lang/String;
        //   714: bipush #8
        //   716: aaload
        //   717: invokevirtual trim : ()Ljava/lang/String;
        //   720: bipush #10
        //   722: bipush #15
        //   724: invokevirtual substring : (II)Ljava/lang/String;
        //   727: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   730: pop
        //   731: aload_2
        //   732: astore_3
        //   733: aload_2
        //   734: astore #4
        //   736: aload #5
        //   738: ldc_w '','
        //   741: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   744: pop
        //   745: aload_2
        //   746: astore_3
        //   747: aload_2
        //   748: astore #4
        //   750: aload #5
        //   752: invokevirtual toString : ()Ljava/lang/String;
        //   755: astore_2
        //   756: aload_2
        //   757: astore_3
        //   758: aload_2
        //   759: astore #4
        //   761: new java/lang/StringBuilder
        //   764: astore #5
        //   766: aload_2
        //   767: astore_3
        //   768: aload_2
        //   769: astore #4
        //   771: aload #5
        //   773: invokespecial <init> : ()V
        //   776: aload_2
        //   777: astore_3
        //   778: aload_2
        //   779: astore #4
        //   781: aload #5
        //   783: aload_2
        //   784: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   787: pop
        //   788: aload_2
        //   789: astore_3
        //   790: aload_2
        //   791: astore #4
        //   793: aload #5
        //   795: ldc_w '''
        //   798: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   801: pop
        //   802: aload_2
        //   803: astore_3
        //   804: aload_2
        //   805: astore #4
        //   807: aload #5
        //   809: aload_0
        //   810: getfield ArrayGiai : [Ljava/lang/String;
        //   813: bipush #8
        //   815: aaload
        //   816: invokevirtual trim : ()Ljava/lang/String;
        //   819: bipush #15
        //   821: bipush #20
        //   823: invokevirtual substring : (II)Ljava/lang/String;
        //   826: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   829: pop
        //   830: aload_2
        //   831: astore_3
        //   832: aload_2
        //   833: astore #4
        //   835: aload #5
        //   837: ldc_w '','
        //   840: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   843: pop
        //   844: aload_2
        //   845: astore_3
        //   846: aload_2
        //   847: astore #4
        //   849: aload #5
        //   851: invokevirtual toString : ()Ljava/lang/String;
        //   854: astore_2
        //   855: aload_2
        //   856: astore_3
        //   857: aload_2
        //   858: astore #4
        //   860: new java/lang/StringBuilder
        //   863: astore #5
        //   865: aload_2
        //   866: astore_3
        //   867: aload_2
        //   868: astore #4
        //   870: aload #5
        //   872: invokespecial <init> : ()V
        //   875: aload_2
        //   876: astore_3
        //   877: aload_2
        //   878: astore #4
        //   880: aload #5
        //   882: aload_2
        //   883: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   886: pop
        //   887: aload_2
        //   888: astore_3
        //   889: aload_2
        //   890: astore #4
        //   892: aload #5
        //   894: ldc_w '''
        //   897: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   900: pop
        //   901: aload_2
        //   902: astore_3
        //   903: aload_2
        //   904: astore #4
        //   906: aload #5
        //   908: aload_0
        //   909: getfield ArrayGiai : [Ljava/lang/String;
        //   912: bipush #8
        //   914: aaload
        //   915: invokevirtual trim : ()Ljava/lang/String;
        //   918: bipush #20
        //   920: bipush #25
        //   922: invokevirtual substring : (II)Ljava/lang/String;
        //   925: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   928: pop
        //   929: aload_2
        //   930: astore_3
        //   931: aload_2
        //   932: astore #4
        //   934: aload #5
        //   936: ldc_w '','
        //   939: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   942: pop
        //   943: aload_2
        //   944: astore_3
        //   945: aload_2
        //   946: astore #4
        //   948: aload #5
        //   950: invokevirtual toString : ()Ljava/lang/String;
        //   953: astore_2
        //   954: aload_2
        //   955: astore_3
        //   956: aload_2
        //   957: astore #4
        //   959: new java/lang/StringBuilder
        //   962: astore #5
        //   964: aload_2
        //   965: astore_3
        //   966: aload_2
        //   967: astore #4
        //   969: aload #5
        //   971: invokespecial <init> : ()V
        //   974: aload_2
        //   975: astore_3
        //   976: aload_2
        //   977: astore #4
        //   979: aload #5
        //   981: aload_2
        //   982: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   985: pop
        //   986: aload_2
        //   987: astore_3
        //   988: aload_2
        //   989: astore #4
        //   991: aload #5
        //   993: ldc_w '''
        //   996: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   999: pop
        //   1000: aload_2
        //   1001: astore_3
        //   1002: aload_2
        //   1003: astore #4
        //   1005: aload #5
        //   1007: aload_0
        //   1008: getfield ArrayGiai : [Ljava/lang/String;
        //   1011: bipush #8
        //   1013: aaload
        //   1014: invokevirtual trim : ()Ljava/lang/String;
        //   1017: bipush #25
        //   1019: aload_0
        //   1020: getfield ArrayGiai : [Ljava/lang/String;
        //   1023: bipush #8
        //   1025: aaload
        //   1026: invokevirtual trim : ()Ljava/lang/String;
        //   1029: invokevirtual length : ()I
        //   1032: invokevirtual substring : (II)Ljava/lang/String;
        //   1035: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1038: pop
        //   1039: aload_2
        //   1040: astore_3
        //   1041: aload_2
        //   1042: astore #4
        //   1044: aload #5
        //   1046: ldc_w '','
        //   1049: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1052: pop
        //   1053: aload_2
        //   1054: astore_3
        //   1055: aload_2
        //   1056: astore #4
        //   1058: aload #5
        //   1060: invokevirtual toString : ()Ljava/lang/String;
        //   1063: astore_2
        //   1064: aload_2
        //   1065: astore_3
        //   1066: aload_2
        //   1067: astore #4
        //   1069: aload_0
        //   1070: getfield ArrayGiai : [Ljava/lang/String;
        //   1073: bipush #10
        //   1075: aaload
        //   1076: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   1079: ifeq -> 3547
        //   1082: aload_2
        //   1083: astore_3
        //   1084: aload_2
        //   1085: astore #4
        //   1087: new java/lang/StringBuilder
        //   1090: astore #5
        //   1092: aload_2
        //   1093: astore_3
        //   1094: aload_2
        //   1095: astore #4
        //   1097: aload #5
        //   1099: invokespecial <init> : ()V
        //   1102: aload_2
        //   1103: astore_3
        //   1104: aload_2
        //   1105: astore #4
        //   1107: aload #5
        //   1109: aload_2
        //   1110: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1113: pop
        //   1114: aload_2
        //   1115: astore_3
        //   1116: aload_2
        //   1117: astore #4
        //   1119: aload #5
        //   1121: ldc_w '''
        //   1124: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1127: pop
        //   1128: aload_2
        //   1129: astore_3
        //   1130: aload_2
        //   1131: astore #4
        //   1133: aload #5
        //   1135: aload_0
        //   1136: getfield ArrayGiai : [Ljava/lang/String;
        //   1139: bipush #10
        //   1141: aaload
        //   1142: invokevirtual trim : ()Ljava/lang/String;
        //   1145: iconst_0
        //   1146: iconst_4
        //   1147: invokevirtual substring : (II)Ljava/lang/String;
        //   1150: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1153: pop
        //   1154: aload_2
        //   1155: astore_3
        //   1156: aload_2
        //   1157: astore #4
        //   1159: aload #5
        //   1161: ldc_w '','
        //   1164: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1167: pop
        //   1168: aload_2
        //   1169: astore_3
        //   1170: aload_2
        //   1171: astore #4
        //   1173: aload #5
        //   1175: invokevirtual toString : ()Ljava/lang/String;
        //   1178: astore_2
        //   1179: aload_2
        //   1180: astore_3
        //   1181: aload_2
        //   1182: astore #4
        //   1184: new java/lang/StringBuilder
        //   1187: astore #5
        //   1189: aload_2
        //   1190: astore_3
        //   1191: aload_2
        //   1192: astore #4
        //   1194: aload #5
        //   1196: invokespecial <init> : ()V
        //   1199: aload_2
        //   1200: astore_3
        //   1201: aload_2
        //   1202: astore #4
        //   1204: aload #5
        //   1206: aload_2
        //   1207: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1210: pop
        //   1211: aload_2
        //   1212: astore_3
        //   1213: aload_2
        //   1214: astore #4
        //   1216: aload #5
        //   1218: ldc_w '''
        //   1221: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1224: pop
        //   1225: aload_2
        //   1226: astore_3
        //   1227: aload_2
        //   1228: astore #4
        //   1230: aload #5
        //   1232: aload_0
        //   1233: getfield ArrayGiai : [Ljava/lang/String;
        //   1236: bipush #10
        //   1238: aaload
        //   1239: invokevirtual trim : ()Ljava/lang/String;
        //   1242: iconst_4
        //   1243: bipush #8
        //   1245: invokevirtual substring : (II)Ljava/lang/String;
        //   1248: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1251: pop
        //   1252: aload_2
        //   1253: astore_3
        //   1254: aload_2
        //   1255: astore #4
        //   1257: aload #5
        //   1259: ldc_w '','
        //   1262: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1265: pop
        //   1266: aload_2
        //   1267: astore_3
        //   1268: aload_2
        //   1269: astore #4
        //   1271: aload #5
        //   1273: invokevirtual toString : ()Ljava/lang/String;
        //   1276: astore_2
        //   1277: aload_2
        //   1278: astore_3
        //   1279: aload_2
        //   1280: astore #4
        //   1282: new java/lang/StringBuilder
        //   1285: astore #5
        //   1287: aload_2
        //   1288: astore_3
        //   1289: aload_2
        //   1290: astore #4
        //   1292: aload #5
        //   1294: invokespecial <init> : ()V
        //   1297: aload_2
        //   1298: astore_3
        //   1299: aload_2
        //   1300: astore #4
        //   1302: aload #5
        //   1304: aload_2
        //   1305: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1308: pop
        //   1309: aload_2
        //   1310: astore_3
        //   1311: aload_2
        //   1312: astore #4
        //   1314: aload #5
        //   1316: ldc_w '''
        //   1319: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1322: pop
        //   1323: aload_2
        //   1324: astore_3
        //   1325: aload_2
        //   1326: astore #4
        //   1328: aload #5
        //   1330: aload_0
        //   1331: getfield ArrayGiai : [Ljava/lang/String;
        //   1334: bipush #10
        //   1336: aaload
        //   1337: invokevirtual trim : ()Ljava/lang/String;
        //   1340: bipush #8
        //   1342: bipush #12
        //   1344: invokevirtual substring : (II)Ljava/lang/String;
        //   1347: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1350: pop
        //   1351: aload_2
        //   1352: astore_3
        //   1353: aload_2
        //   1354: astore #4
        //   1356: aload #5
        //   1358: ldc_w '','
        //   1361: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1364: pop
        //   1365: aload_2
        //   1366: astore_3
        //   1367: aload_2
        //   1368: astore #4
        //   1370: aload #5
        //   1372: invokevirtual toString : ()Ljava/lang/String;
        //   1375: astore_2
        //   1376: aload_2
        //   1377: astore_3
        //   1378: aload_2
        //   1379: astore #4
        //   1381: new java/lang/StringBuilder
        //   1384: astore #5
        //   1386: aload_2
        //   1387: astore_3
        //   1388: aload_2
        //   1389: astore #4
        //   1391: aload #5
        //   1393: invokespecial <init> : ()V
        //   1396: aload_2
        //   1397: astore_3
        //   1398: aload_2
        //   1399: astore #4
        //   1401: aload #5
        //   1403: aload_2
        //   1404: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1407: pop
        //   1408: aload_2
        //   1409: astore_3
        //   1410: aload_2
        //   1411: astore #4
        //   1413: aload #5
        //   1415: ldc_w '''
        //   1418: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1421: pop
        //   1422: aload_2
        //   1423: astore_3
        //   1424: aload_2
        //   1425: astore #4
        //   1427: aload #5
        //   1429: aload_0
        //   1430: getfield ArrayGiai : [Ljava/lang/String;
        //   1433: bipush #10
        //   1435: aaload
        //   1436: invokevirtual trim : ()Ljava/lang/String;
        //   1439: bipush #12
        //   1441: aload_0
        //   1442: getfield ArrayGiai : [Ljava/lang/String;
        //   1445: bipush #10
        //   1447: aaload
        //   1448: invokevirtual trim : ()Ljava/lang/String;
        //   1451: invokevirtual length : ()I
        //   1454: invokevirtual substring : (II)Ljava/lang/String;
        //   1457: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1460: pop
        //   1461: aload_2
        //   1462: astore_3
        //   1463: aload_2
        //   1464: astore #4
        //   1466: aload #5
        //   1468: ldc_w '','
        //   1471: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1474: pop
        //   1475: aload_2
        //   1476: astore_3
        //   1477: aload_2
        //   1478: astore #4
        //   1480: aload #5
        //   1482: invokevirtual toString : ()Ljava/lang/String;
        //   1485: astore_2
        //   1486: aload_2
        //   1487: astore_3
        //   1488: aload_2
        //   1489: astore #4
        //   1491: aload_0
        //   1492: getfield ArrayGiai : [Ljava/lang/String;
        //   1495: bipush #12
        //   1497: aaload
        //   1498: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   1501: ifeq -> 3372
        //   1504: aload_2
        //   1505: astore_3
        //   1506: aload_2
        //   1507: astore #4
        //   1509: new java/lang/StringBuilder
        //   1512: astore #5
        //   1514: aload_2
        //   1515: astore_3
        //   1516: aload_2
        //   1517: astore #4
        //   1519: aload #5
        //   1521: invokespecial <init> : ()V
        //   1524: aload_2
        //   1525: astore_3
        //   1526: aload_2
        //   1527: astore #4
        //   1529: aload #5
        //   1531: aload_2
        //   1532: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1535: pop
        //   1536: aload_2
        //   1537: astore_3
        //   1538: aload_2
        //   1539: astore #4
        //   1541: aload #5
        //   1543: ldc_w '''
        //   1546: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1549: pop
        //   1550: aload_2
        //   1551: astore_3
        //   1552: aload_2
        //   1553: astore #4
        //   1555: aload #5
        //   1557: aload_0
        //   1558: getfield ArrayGiai : [Ljava/lang/String;
        //   1561: bipush #12
        //   1563: aaload
        //   1564: invokevirtual trim : ()Ljava/lang/String;
        //   1567: iconst_0
        //   1568: iconst_4
        //   1569: invokevirtual substring : (II)Ljava/lang/String;
        //   1572: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1575: pop
        //   1576: aload_2
        //   1577: astore_3
        //   1578: aload_2
        //   1579: astore #4
        //   1581: aload #5
        //   1583: ldc_w '','
        //   1586: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1589: pop
        //   1590: aload_2
        //   1591: astore_3
        //   1592: aload_2
        //   1593: astore #4
        //   1595: aload #5
        //   1597: invokevirtual toString : ()Ljava/lang/String;
        //   1600: astore_2
        //   1601: aload_2
        //   1602: astore_3
        //   1603: aload_2
        //   1604: astore #4
        //   1606: new java/lang/StringBuilder
        //   1609: astore #5
        //   1611: aload_2
        //   1612: astore_3
        //   1613: aload_2
        //   1614: astore #4
        //   1616: aload #5
        //   1618: invokespecial <init> : ()V
        //   1621: aload_2
        //   1622: astore_3
        //   1623: aload_2
        //   1624: astore #4
        //   1626: aload #5
        //   1628: aload_2
        //   1629: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1632: pop
        //   1633: aload_2
        //   1634: astore_3
        //   1635: aload_2
        //   1636: astore #4
        //   1638: aload #5
        //   1640: ldc_w '''
        //   1643: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1646: pop
        //   1647: aload_2
        //   1648: astore_3
        //   1649: aload_2
        //   1650: astore #4
        //   1652: aload #5
        //   1654: aload_0
        //   1655: getfield ArrayGiai : [Ljava/lang/String;
        //   1658: bipush #12
        //   1660: aaload
        //   1661: invokevirtual trim : ()Ljava/lang/String;
        //   1664: iconst_4
        //   1665: bipush #8
        //   1667: invokevirtual substring : (II)Ljava/lang/String;
        //   1670: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1673: pop
        //   1674: aload_2
        //   1675: astore_3
        //   1676: aload_2
        //   1677: astore #4
        //   1679: aload #5
        //   1681: ldc_w '','
        //   1684: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1687: pop
        //   1688: aload_2
        //   1689: astore_3
        //   1690: aload_2
        //   1691: astore #4
        //   1693: aload #5
        //   1695: invokevirtual toString : ()Ljava/lang/String;
        //   1698: astore_2
        //   1699: aload_2
        //   1700: astore_3
        //   1701: aload_2
        //   1702: astore #4
        //   1704: new java/lang/StringBuilder
        //   1707: astore #5
        //   1709: aload_2
        //   1710: astore_3
        //   1711: aload_2
        //   1712: astore #4
        //   1714: aload #5
        //   1716: invokespecial <init> : ()V
        //   1719: aload_2
        //   1720: astore_3
        //   1721: aload_2
        //   1722: astore #4
        //   1724: aload #5
        //   1726: aload_2
        //   1727: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1730: pop
        //   1731: aload_2
        //   1732: astore_3
        //   1733: aload_2
        //   1734: astore #4
        //   1736: aload #5
        //   1738: ldc_w '''
        //   1741: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1744: pop
        //   1745: aload_2
        //   1746: astore_3
        //   1747: aload_2
        //   1748: astore #4
        //   1750: aload #5
        //   1752: aload_0
        //   1753: getfield ArrayGiai : [Ljava/lang/String;
        //   1756: bipush #12
        //   1758: aaload
        //   1759: invokevirtual trim : ()Ljava/lang/String;
        //   1762: bipush #8
        //   1764: bipush #12
        //   1766: invokevirtual substring : (II)Ljava/lang/String;
        //   1769: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1772: pop
        //   1773: aload_2
        //   1774: astore_3
        //   1775: aload_2
        //   1776: astore #4
        //   1778: aload #5
        //   1780: ldc_w '','
        //   1783: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1786: pop
        //   1787: aload_2
        //   1788: astore_3
        //   1789: aload_2
        //   1790: astore #4
        //   1792: aload #5
        //   1794: invokevirtual toString : ()Ljava/lang/String;
        //   1797: astore_2
        //   1798: aload_2
        //   1799: astore_3
        //   1800: aload_2
        //   1801: astore #4
        //   1803: new java/lang/StringBuilder
        //   1806: astore #5
        //   1808: aload_2
        //   1809: astore_3
        //   1810: aload_2
        //   1811: astore #4
        //   1813: aload #5
        //   1815: invokespecial <init> : ()V
        //   1818: aload_2
        //   1819: astore_3
        //   1820: aload_2
        //   1821: astore #4
        //   1823: aload #5
        //   1825: aload_2
        //   1826: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1829: pop
        //   1830: aload_2
        //   1831: astore_3
        //   1832: aload_2
        //   1833: astore #4
        //   1835: aload #5
        //   1837: ldc_w '''
        //   1840: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1843: pop
        //   1844: aload_2
        //   1845: astore_3
        //   1846: aload_2
        //   1847: astore #4
        //   1849: aload #5
        //   1851: aload_0
        //   1852: getfield ArrayGiai : [Ljava/lang/String;
        //   1855: bipush #12
        //   1857: aaload
        //   1858: invokevirtual trim : ()Ljava/lang/String;
        //   1861: bipush #12
        //   1863: bipush #16
        //   1865: invokevirtual substring : (II)Ljava/lang/String;
        //   1868: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1871: pop
        //   1872: aload_2
        //   1873: astore_3
        //   1874: aload_2
        //   1875: astore #4
        //   1877: aload #5
        //   1879: ldc_w '','
        //   1882: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1885: pop
        //   1886: aload_2
        //   1887: astore_3
        //   1888: aload_2
        //   1889: astore #4
        //   1891: aload #5
        //   1893: invokevirtual toString : ()Ljava/lang/String;
        //   1896: astore_2
        //   1897: aload_2
        //   1898: astore_3
        //   1899: aload_2
        //   1900: astore #4
        //   1902: new java/lang/StringBuilder
        //   1905: astore #5
        //   1907: aload_2
        //   1908: astore_3
        //   1909: aload_2
        //   1910: astore #4
        //   1912: aload #5
        //   1914: invokespecial <init> : ()V
        //   1917: aload_2
        //   1918: astore_3
        //   1919: aload_2
        //   1920: astore #4
        //   1922: aload #5
        //   1924: aload_2
        //   1925: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1928: pop
        //   1929: aload_2
        //   1930: astore_3
        //   1931: aload_2
        //   1932: astore #4
        //   1934: aload #5
        //   1936: ldc_w '''
        //   1939: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1942: pop
        //   1943: aload_2
        //   1944: astore_3
        //   1945: aload_2
        //   1946: astore #4
        //   1948: aload #5
        //   1950: aload_0
        //   1951: getfield ArrayGiai : [Ljava/lang/String;
        //   1954: bipush #12
        //   1956: aaload
        //   1957: invokevirtual trim : ()Ljava/lang/String;
        //   1960: bipush #16
        //   1962: bipush #20
        //   1964: invokevirtual substring : (II)Ljava/lang/String;
        //   1967: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1970: pop
        //   1971: aload_2
        //   1972: astore_3
        //   1973: aload_2
        //   1974: astore #4
        //   1976: aload #5
        //   1978: ldc_w '','
        //   1981: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1984: pop
        //   1985: aload_2
        //   1986: astore_3
        //   1987: aload_2
        //   1988: astore #4
        //   1990: aload #5
        //   1992: invokevirtual toString : ()Ljava/lang/String;
        //   1995: astore_2
        //   1996: aload_2
        //   1997: astore_3
        //   1998: aload_2
        //   1999: astore #4
        //   2001: new java/lang/StringBuilder
        //   2004: astore #5
        //   2006: aload_2
        //   2007: astore_3
        //   2008: aload_2
        //   2009: astore #4
        //   2011: aload #5
        //   2013: invokespecial <init> : ()V
        //   2016: aload_2
        //   2017: astore_3
        //   2018: aload_2
        //   2019: astore #4
        //   2021: aload #5
        //   2023: aload_2
        //   2024: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2027: pop
        //   2028: aload_2
        //   2029: astore_3
        //   2030: aload_2
        //   2031: astore #4
        //   2033: aload #5
        //   2035: ldc_w '''
        //   2038: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2041: pop
        //   2042: aload_2
        //   2043: astore_3
        //   2044: aload_2
        //   2045: astore #4
        //   2047: aload #5
        //   2049: aload_0
        //   2050: getfield ArrayGiai : [Ljava/lang/String;
        //   2053: bipush #12
        //   2055: aaload
        //   2056: invokevirtual trim : ()Ljava/lang/String;
        //   2059: bipush #20
        //   2061: aload_0
        //   2062: getfield ArrayGiai : [Ljava/lang/String;
        //   2065: bipush #12
        //   2067: aaload
        //   2068: invokevirtual trim : ()Ljava/lang/String;
        //   2071: invokevirtual length : ()I
        //   2074: invokevirtual substring : (II)Ljava/lang/String;
        //   2077: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2080: pop
        //   2081: aload_2
        //   2082: astore_3
        //   2083: aload_2
        //   2084: astore #4
        //   2086: aload #5
        //   2088: ldc_w '','
        //   2091: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2094: pop
        //   2095: aload_2
        //   2096: astore_3
        //   2097: aload_2
        //   2098: astore #4
        //   2100: aload #5
        //   2102: invokevirtual toString : ()Ljava/lang/String;
        //   2105: astore_2
        //   2106: aload_2
        //   2107: astore_3
        //   2108: aload_2
        //   2109: astore #4
        //   2111: aload_0
        //   2112: getfield ArrayGiai : [Ljava/lang/String;
        //   2115: bipush #14
        //   2117: aaload
        //   2118: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   2121: ifeq -> 3199
        //   2124: aload_2
        //   2125: astore_3
        //   2126: aload_2
        //   2127: astore #4
        //   2129: new java/lang/StringBuilder
        //   2132: astore #5
        //   2134: aload_2
        //   2135: astore_3
        //   2136: aload_2
        //   2137: astore #4
        //   2139: aload #5
        //   2141: invokespecial <init> : ()V
        //   2144: aload_2
        //   2145: astore_3
        //   2146: aload_2
        //   2147: astore #4
        //   2149: aload #5
        //   2151: aload_2
        //   2152: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2155: pop
        //   2156: aload_2
        //   2157: astore_3
        //   2158: aload_2
        //   2159: astore #4
        //   2161: aload #5
        //   2163: ldc_w '''
        //   2166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2169: pop
        //   2170: aload_2
        //   2171: astore_3
        //   2172: aload_2
        //   2173: astore #4
        //   2175: aload #5
        //   2177: aload_0
        //   2178: getfield ArrayGiai : [Ljava/lang/String;
        //   2181: bipush #14
        //   2183: aaload
        //   2184: invokevirtual trim : ()Ljava/lang/String;
        //   2187: iconst_0
        //   2188: iconst_3
        //   2189: invokevirtual substring : (II)Ljava/lang/String;
        //   2192: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2195: pop
        //   2196: aload_2
        //   2197: astore_3
        //   2198: aload_2
        //   2199: astore #4
        //   2201: aload #5
        //   2203: ldc_w '','
        //   2206: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2209: pop
        //   2210: aload_2
        //   2211: astore_3
        //   2212: aload_2
        //   2213: astore #4
        //   2215: aload #5
        //   2217: invokevirtual toString : ()Ljava/lang/String;
        //   2220: astore_2
        //   2221: aload_2
        //   2222: astore_3
        //   2223: aload_2
        //   2224: astore #4
        //   2226: new java/lang/StringBuilder
        //   2229: astore #5
        //   2231: aload_2
        //   2232: astore_3
        //   2233: aload_2
        //   2234: astore #4
        //   2236: aload #5
        //   2238: invokespecial <init> : ()V
        //   2241: aload_2
        //   2242: astore_3
        //   2243: aload_2
        //   2244: astore #4
        //   2246: aload #5
        //   2248: aload_2
        //   2249: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2252: pop
        //   2253: aload_2
        //   2254: astore_3
        //   2255: aload_2
        //   2256: astore #4
        //   2258: aload #5
        //   2260: ldc_w '''
        //   2263: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2266: pop
        //   2267: aload_2
        //   2268: astore_3
        //   2269: aload_2
        //   2270: astore #4
        //   2272: aload #5
        //   2274: aload_0
        //   2275: getfield ArrayGiai : [Ljava/lang/String;
        //   2278: bipush #14
        //   2280: aaload
        //   2281: invokevirtual trim : ()Ljava/lang/String;
        //   2284: iconst_3
        //   2285: bipush #6
        //   2287: invokevirtual substring : (II)Ljava/lang/String;
        //   2290: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2293: pop
        //   2294: aload_2
        //   2295: astore_3
        //   2296: aload_2
        //   2297: astore #4
        //   2299: aload #5
        //   2301: ldc_w '','
        //   2304: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2307: pop
        //   2308: aload_2
        //   2309: astore_3
        //   2310: aload_2
        //   2311: astore #4
        //   2313: aload #5
        //   2315: invokevirtual toString : ()Ljava/lang/String;
        //   2318: astore_2
        //   2319: aload_2
        //   2320: astore_3
        //   2321: aload_2
        //   2322: astore #4
        //   2324: new java/lang/StringBuilder
        //   2327: astore #5
        //   2329: aload_2
        //   2330: astore_3
        //   2331: aload_2
        //   2332: astore #4
        //   2334: aload #5
        //   2336: invokespecial <init> : ()V
        //   2339: aload_2
        //   2340: astore_3
        //   2341: aload_2
        //   2342: astore #4
        //   2344: aload #5
        //   2346: aload_2
        //   2347: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2350: pop
        //   2351: aload_2
        //   2352: astore_3
        //   2353: aload_2
        //   2354: astore #4
        //   2356: aload #5
        //   2358: ldc_w '''
        //   2361: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2364: pop
        //   2365: aload_2
        //   2366: astore_3
        //   2367: aload_2
        //   2368: astore #4
        //   2370: aload #5
        //   2372: aload_0
        //   2373: getfield ArrayGiai : [Ljava/lang/String;
        //   2376: bipush #14
        //   2378: aaload
        //   2379: invokevirtual trim : ()Ljava/lang/String;
        //   2382: bipush #6
        //   2384: aload_0
        //   2385: getfield ArrayGiai : [Ljava/lang/String;
        //   2388: bipush #14
        //   2390: aaload
        //   2391: invokevirtual trim : ()Ljava/lang/String;
        //   2394: invokevirtual length : ()I
        //   2397: invokevirtual substring : (II)Ljava/lang/String;
        //   2400: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2403: pop
        //   2404: aload_2
        //   2405: astore_3
        //   2406: aload_2
        //   2407: astore #4
        //   2409: aload #5
        //   2411: ldc_w '','
        //   2414: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2417: pop
        //   2418: aload_2
        //   2419: astore_3
        //   2420: aload_2
        //   2421: astore #4
        //   2423: aload #5
        //   2425: invokevirtual toString : ()Ljava/lang/String;
        //   2428: astore_2
        //   2429: aload_2
        //   2430: astore_3
        //   2431: aload_2
        //   2432: astore #4
        //   2434: aload_0
        //   2435: getfield ArrayGiai : [Ljava/lang/String;
        //   2438: bipush #16
        //   2440: aaload
        //   2441: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   2444: ifeq -> 3026
        //   2447: aload_2
        //   2448: astore_3
        //   2449: aload_2
        //   2450: astore #4
        //   2452: new java/lang/StringBuilder
        //   2455: astore #5
        //   2457: aload_2
        //   2458: astore_3
        //   2459: aload_2
        //   2460: astore #4
        //   2462: aload #5
        //   2464: invokespecial <init> : ()V
        //   2467: aload_2
        //   2468: astore_3
        //   2469: aload_2
        //   2470: astore #4
        //   2472: aload #5
        //   2474: aload_2
        //   2475: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2478: pop
        //   2479: aload_2
        //   2480: astore_3
        //   2481: aload_2
        //   2482: astore #4
        //   2484: aload #5
        //   2486: ldc_w '''
        //   2489: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2492: pop
        //   2493: aload_2
        //   2494: astore_3
        //   2495: aload_2
        //   2496: astore #4
        //   2498: aload #5
        //   2500: aload_0
        //   2501: getfield ArrayGiai : [Ljava/lang/String;
        //   2504: bipush #16
        //   2506: aaload
        //   2507: invokevirtual trim : ()Ljava/lang/String;
        //   2510: iconst_0
        //   2511: iconst_2
        //   2512: invokevirtual substring : (II)Ljava/lang/String;
        //   2515: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2518: pop
        //   2519: aload_2
        //   2520: astore_3
        //   2521: aload_2
        //   2522: astore #4
        //   2524: aload #5
        //   2526: ldc_w '','
        //   2529: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2532: pop
        //   2533: aload_2
        //   2534: astore_3
        //   2535: aload_2
        //   2536: astore #4
        //   2538: aload #5
        //   2540: invokevirtual toString : ()Ljava/lang/String;
        //   2543: astore_2
        //   2544: aload_2
        //   2545: astore_3
        //   2546: aload_2
        //   2547: astore #4
        //   2549: new java/lang/StringBuilder
        //   2552: astore #5
        //   2554: aload_2
        //   2555: astore_3
        //   2556: aload_2
        //   2557: astore #4
        //   2559: aload #5
        //   2561: invokespecial <init> : ()V
        //   2564: aload_2
        //   2565: astore_3
        //   2566: aload_2
        //   2567: astore #4
        //   2569: aload #5
        //   2571: aload_2
        //   2572: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2575: pop
        //   2576: aload_2
        //   2577: astore_3
        //   2578: aload_2
        //   2579: astore #4
        //   2581: aload #5
        //   2583: ldc_w '''
        //   2586: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2589: pop
        //   2590: aload_2
        //   2591: astore_3
        //   2592: aload_2
        //   2593: astore #4
        //   2595: aload #5
        //   2597: aload_0
        //   2598: getfield ArrayGiai : [Ljava/lang/String;
        //   2601: bipush #16
        //   2603: aaload
        //   2604: invokevirtual trim : ()Ljava/lang/String;
        //   2607: iconst_2
        //   2608: iconst_4
        //   2609: invokevirtual substring : (II)Ljava/lang/String;
        //   2612: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2615: pop
        //   2616: aload_2
        //   2617: astore_3
        //   2618: aload_2
        //   2619: astore #4
        //   2621: aload #5
        //   2623: ldc_w '','
        //   2626: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2629: pop
        //   2630: aload_2
        //   2631: astore_3
        //   2632: aload_2
        //   2633: astore #4
        //   2635: aload #5
        //   2637: invokevirtual toString : ()Ljava/lang/String;
        //   2640: astore_2
        //   2641: aload_2
        //   2642: astore_3
        //   2643: aload_2
        //   2644: astore #4
        //   2646: new java/lang/StringBuilder
        //   2649: astore #5
        //   2651: aload_2
        //   2652: astore_3
        //   2653: aload_2
        //   2654: astore #4
        //   2656: aload #5
        //   2658: invokespecial <init> : ()V
        //   2661: aload_2
        //   2662: astore_3
        //   2663: aload_2
        //   2664: astore #4
        //   2666: aload #5
        //   2668: aload_2
        //   2669: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2672: pop
        //   2673: aload_2
        //   2674: astore_3
        //   2675: aload_2
        //   2676: astore #4
        //   2678: aload #5
        //   2680: ldc_w '''
        //   2683: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2686: pop
        //   2687: aload_2
        //   2688: astore_3
        //   2689: aload_2
        //   2690: astore #4
        //   2692: aload #5
        //   2694: aload_0
        //   2695: getfield ArrayGiai : [Ljava/lang/String;
        //   2698: bipush #16
        //   2700: aaload
        //   2701: invokevirtual trim : ()Ljava/lang/String;
        //   2704: iconst_4
        //   2705: bipush #6
        //   2707: invokevirtual substring : (II)Ljava/lang/String;
        //   2710: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2713: pop
        //   2714: aload_2
        //   2715: astore_3
        //   2716: aload_2
        //   2717: astore #4
        //   2719: aload #5
        //   2721: ldc_w '','
        //   2724: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2727: pop
        //   2728: aload_2
        //   2729: astore_3
        //   2730: aload_2
        //   2731: astore #4
        //   2733: aload #5
        //   2735: invokevirtual toString : ()Ljava/lang/String;
        //   2738: astore_2
        //   2739: aload_2
        //   2740: astore_3
        //   2741: aload_2
        //   2742: astore #4
        //   2744: new java/lang/StringBuilder
        //   2747: astore #5
        //   2749: aload_2
        //   2750: astore_3
        //   2751: aload_2
        //   2752: astore #4
        //   2754: aload #5
        //   2756: invokespecial <init> : ()V
        //   2759: aload_2
        //   2760: astore_3
        //   2761: aload_2
        //   2762: astore #4
        //   2764: aload #5
        //   2766: aload_2
        //   2767: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2770: pop
        //   2771: aload_2
        //   2772: astore_3
        //   2773: aload_2
        //   2774: astore #4
        //   2776: aload #5
        //   2778: ldc_w '''
        //   2781: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2784: pop
        //   2785: aload_2
        //   2786: astore_3
        //   2787: aload_2
        //   2788: astore #4
        //   2790: aload #5
        //   2792: aload_0
        //   2793: getfield ArrayGiai : [Ljava/lang/String;
        //   2796: bipush #16
        //   2798: aaload
        //   2799: invokevirtual trim : ()Ljava/lang/String;
        //   2802: bipush #6
        //   2804: aload_0
        //   2805: getfield ArrayGiai : [Ljava/lang/String;
        //   2808: bipush #16
        //   2810: aaload
        //   2811: invokevirtual trim : ()Ljava/lang/String;
        //   2814: invokevirtual length : ()I
        //   2817: invokevirtual substring : (II)Ljava/lang/String;
        //   2820: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2823: pop
        //   2824: aload_2
        //   2825: astore_3
        //   2826: aload_2
        //   2827: astore #4
        //   2829: aload #5
        //   2831: ldc_w '')'
        //   2834: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2837: pop
        //   2838: aload_2
        //   2839: astore_3
        //   2840: aload_2
        //   2841: astore #4
        //   2843: aload #5
        //   2845: invokevirtual toString : ()Ljava/lang/String;
        //   2848: astore_2
        //   2849: aload_2
        //   2850: invokevirtual length : ()I
        //   2853: sipush #185
        //   2856: if_icmple -> 3009
        //   2859: aload_0
        //   2860: getfield db : Ltamhoang/ldpro4/data/Database;
        //   2863: astore #4
        //   2865: new java/lang/StringBuilder
        //   2868: dup
        //   2869: invokespecial <init> : ()V
        //   2872: astore_3
        //   2873: aload_3
        //   2874: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   2877: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2880: pop
        //   2881: aload_3
        //   2882: aload_1
        //   2883: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2886: pop
        //   2887: aload_3
        //   2888: ldc_w '''
        //   2891: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2894: pop
        //   2895: aload #4
        //   2897: aload_3
        //   2898: invokevirtual toString : ()Ljava/lang/String;
        //   2901: invokevirtual QueryData : (Ljava/lang/String;)V
        //   2904: new java/lang/StringBuilder
        //   2907: dup
        //   2908: invokespecial <init> : ()V
        //   2911: astore #4
        //   2913: aload #4
        //   2915: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   2918: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2921: pop
        //   2922: aload #4
        //   2924: aload_1
        //   2925: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2928: pop
        //   2929: aload #4
        //   2931: ldc_w '','
        //   2934: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2937: pop
        //   2938: aload #4
        //   2940: aload_2
        //   2941: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2944: pop
        //   2945: aload #4
        //   2947: invokevirtual toString : ()Ljava/lang/String;
        //   2950: astore #4
        //   2952: aload_0
        //   2953: getfield db : Ltamhoang/ldpro4/data/Database;
        //   2956: aload #4
        //   2958: invokevirtual QueryData : (Ljava/lang/String;)V
        //   2961: aload_0
        //   2962: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   2965: astore_3
        //   2966: new java/lang/StringBuilder
        //   2969: dup
        //   2970: invokespecial <init> : ()V
        //   2973: astore #4
        //   2975: aload #4
        //   2977: ldc_w 'txong kqung'
        //   2980: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2983: pop
        //   2984: aload #4
        //   2986: invokestatic Get_ngay : ()Ljava/lang/String;
        //   2989: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2992: pop
        //   2993: aload_3
        //   2994: aload #4
        //   2996: invokevirtual toString : ()Ljava/lang/String;
        //   2999: iconst_1
        //   3000: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3003: invokevirtual show : ()V
        //   3006: goto -> 4574
        //   3009: aload_0
        //   3010: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3013: ldc_w 'Khckquphh
        //   3016: iconst_1
        //   3017: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3020: invokevirtual show : ()V
        //   3023: goto -> 4574
        //   3026: aload_2
        //   3027: invokevirtual length : ()I
        //   3030: sipush #185
        //   3033: if_icmple -> 3184
        //   3036: aload_0
        //   3037: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3040: astore #4
        //   3042: new java/lang/StringBuilder
        //   3045: dup
        //   3046: invokespecial <init> : ()V
        //   3049: astore_3
        //   3050: aload_3
        //   3051: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   3054: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3057: pop
        //   3058: aload_3
        //   3059: aload_1
        //   3060: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3063: pop
        //   3064: aload_3
        //   3065: ldc_w '''
        //   3068: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3071: pop
        //   3072: aload #4
        //   3074: aload_3
        //   3075: invokevirtual toString : ()Ljava/lang/String;
        //   3078: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3081: new java/lang/StringBuilder
        //   3084: dup
        //   3085: invokespecial <init> : ()V
        //   3088: astore #4
        //   3090: aload #4
        //   3092: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   3095: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3098: pop
        //   3099: aload #4
        //   3101: aload_1
        //   3102: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3105: pop
        //   3106: aload #4
        //   3108: ldc_w '','
        //   3111: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3114: pop
        //   3115: aload #4
        //   3117: aload_2
        //   3118: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3121: pop
        //   3122: aload #4
        //   3124: invokevirtual toString : ()Ljava/lang/String;
        //   3127: astore #4
        //   3129: aload_0
        //   3130: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3133: aload #4
        //   3135: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3138: aload_0
        //   3139: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3142: astore #4
        //   3144: new java/lang/StringBuilder
        //   3147: dup
        //   3148: invokespecial <init> : ()V
        //   3151: astore_3
        //   3152: aload_3
        //   3153: ldc_w 'txong kqung'
        //   3156: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3159: pop
        //   3160: aload_3
        //   3161: invokestatic Get_ngay : ()Ljava/lang/String;
        //   3164: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3167: pop
        //   3168: aload #4
        //   3170: aload_3
        //   3171: invokevirtual toString : ()Ljava/lang/String;
        //   3174: iconst_1
        //   3175: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3178: invokevirtual show : ()V
        //   3181: goto -> 3198
        //   3184: aload_0
        //   3185: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3188: ldc_w 'Khckquphh
        //   3191: iconst_1
        //   3192: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3195: invokevirtual show : ()V
        //   3198: return
        //   3199: aload_2
        //   3200: invokevirtual length : ()I
        //   3203: sipush #185
        //   3206: if_icmple -> 3357
        //   3209: aload_0
        //   3210: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3213: astore #4
        //   3215: new java/lang/StringBuilder
        //   3218: dup
        //   3219: invokespecial <init> : ()V
        //   3222: astore_3
        //   3223: aload_3
        //   3224: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   3227: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3230: pop
        //   3231: aload_3
        //   3232: aload_1
        //   3233: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3236: pop
        //   3237: aload_3
        //   3238: ldc_w '''
        //   3241: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3244: pop
        //   3245: aload #4
        //   3247: aload_3
        //   3248: invokevirtual toString : ()Ljava/lang/String;
        //   3251: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3254: new java/lang/StringBuilder
        //   3257: dup
        //   3258: invokespecial <init> : ()V
        //   3261: astore #4
        //   3263: aload #4
        //   3265: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   3268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3271: pop
        //   3272: aload #4
        //   3274: aload_1
        //   3275: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3278: pop
        //   3279: aload #4
        //   3281: ldc_w '','
        //   3284: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3287: pop
        //   3288: aload #4
        //   3290: aload_2
        //   3291: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3294: pop
        //   3295: aload #4
        //   3297: invokevirtual toString : ()Ljava/lang/String;
        //   3300: astore #4
        //   3302: aload_0
        //   3303: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3306: aload #4
        //   3308: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3311: aload_0
        //   3312: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3315: astore #4
        //   3317: new java/lang/StringBuilder
        //   3320: dup
        //   3321: invokespecial <init> : ()V
        //   3324: astore_3
        //   3325: aload_3
        //   3326: ldc_w 'txong kqung'
        //   3329: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3332: pop
        //   3333: aload_3
        //   3334: invokestatic Get_ngay : ()Ljava/lang/String;
        //   3337: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3340: pop
        //   3341: aload #4
        //   3343: aload_3
        //   3344: invokevirtual toString : ()Ljava/lang/String;
        //   3347: iconst_1
        //   3348: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3351: invokevirtual show : ()V
        //   3354: goto -> 3371
        //   3357: aload_0
        //   3358: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3361: ldc_w 'Khckquphh
        //   3364: iconst_1
        //   3365: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3368: invokevirtual show : ()V
        //   3371: return
        //   3372: aload_2
        //   3373: invokevirtual length : ()I
        //   3376: sipush #185
        //   3379: if_icmple -> 3532
        //   3382: aload_0
        //   3383: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3386: astore #4
        //   3388: new java/lang/StringBuilder
        //   3391: dup
        //   3392: invokespecial <init> : ()V
        //   3395: astore_3
        //   3396: aload_3
        //   3397: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   3400: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3403: pop
        //   3404: aload_3
        //   3405: aload_1
        //   3406: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3409: pop
        //   3410: aload_3
        //   3411: ldc_w '''
        //   3414: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3417: pop
        //   3418: aload #4
        //   3420: aload_3
        //   3421: invokevirtual toString : ()Ljava/lang/String;
        //   3424: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3427: new java/lang/StringBuilder
        //   3430: dup
        //   3431: invokespecial <init> : ()V
        //   3434: astore #4
        //   3436: aload #4
        //   3438: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   3441: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3444: pop
        //   3445: aload #4
        //   3447: aload_1
        //   3448: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3451: pop
        //   3452: aload #4
        //   3454: ldc_w '','
        //   3457: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3460: pop
        //   3461: aload #4
        //   3463: aload_2
        //   3464: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3467: pop
        //   3468: aload #4
        //   3470: invokevirtual toString : ()Ljava/lang/String;
        //   3473: astore #4
        //   3475: aload_0
        //   3476: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3479: aload #4
        //   3481: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3484: aload_0
        //   3485: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3488: astore_3
        //   3489: new java/lang/StringBuilder
        //   3492: dup
        //   3493: invokespecial <init> : ()V
        //   3496: astore #4
        //   3498: aload #4
        //   3500: ldc_w 'txong kqung'
        //   3503: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3506: pop
        //   3507: aload #4
        //   3509: invokestatic Get_ngay : ()Ljava/lang/String;
        //   3512: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3515: pop
        //   3516: aload_3
        //   3517: aload #4
        //   3519: invokevirtual toString : ()Ljava/lang/String;
        //   3522: iconst_1
        //   3523: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3526: invokevirtual show : ()V
        //   3529: goto -> 3546
        //   3532: aload_0
        //   3533: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3536: ldc_w 'Khckquphh
        //   3539: iconst_1
        //   3540: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3543: invokevirtual show : ()V
        //   3546: return
        //   3547: aload_2
        //   3548: invokevirtual length : ()I
        //   3551: sipush #185
        //   3554: if_icmple -> 3708
        //   3557: aload_0
        //   3558: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3561: astore_3
        //   3562: new java/lang/StringBuilder
        //   3565: dup
        //   3566: invokespecial <init> : ()V
        //   3569: astore #4
        //   3571: aload #4
        //   3573: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   3576: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3579: pop
        //   3580: aload #4
        //   3582: aload_1
        //   3583: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3586: pop
        //   3587: aload #4
        //   3589: ldc_w '''
        //   3592: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3595: pop
        //   3596: aload_3
        //   3597: aload #4
        //   3599: invokevirtual toString : ()Ljava/lang/String;
        //   3602: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3605: new java/lang/StringBuilder
        //   3608: dup
        //   3609: invokespecial <init> : ()V
        //   3612: astore #4
        //   3614: aload #4
        //   3616: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   3619: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3622: pop
        //   3623: aload #4
        //   3625: aload_1
        //   3626: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3629: pop
        //   3630: aload #4
        //   3632: ldc_w '','
        //   3635: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3638: pop
        //   3639: aload #4
        //   3641: aload_2
        //   3642: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3645: pop
        //   3646: aload #4
        //   3648: invokevirtual toString : ()Ljava/lang/String;
        //   3651: astore #4
        //   3653: aload_0
        //   3654: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3657: aload #4
        //   3659: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3662: aload_0
        //   3663: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3666: astore #4
        //   3668: new java/lang/StringBuilder
        //   3671: dup
        //   3672: invokespecial <init> : ()V
        //   3675: astore_3
        //   3676: aload_3
        //   3677: ldc_w 'txong kqung'
        //   3680: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3683: pop
        //   3684: aload_3
        //   3685: invokestatic Get_ngay : ()Ljava/lang/String;
        //   3688: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3691: pop
        //   3692: aload #4
        //   3694: aload_3
        //   3695: invokevirtual toString : ()Ljava/lang/String;
        //   3698: iconst_1
        //   3699: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3702: invokevirtual show : ()V
        //   3705: goto -> 3722
        //   3708: aload_0
        //   3709: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3712: ldc_w 'Khckquphh
        //   3715: iconst_1
        //   3716: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3719: invokevirtual show : ()V
        //   3722: return
        //   3723: aload_2
        //   3724: invokevirtual length : ()I
        //   3727: sipush #185
        //   3730: if_icmple -> 3883
        //   3733: aload_0
        //   3734: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3737: astore #4
        //   3739: new java/lang/StringBuilder
        //   3742: dup
        //   3743: invokespecial <init> : ()V
        //   3746: astore_3
        //   3747: aload_3
        //   3748: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   3751: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3754: pop
        //   3755: aload_3
        //   3756: aload_1
        //   3757: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3760: pop
        //   3761: aload_3
        //   3762: ldc_w '''
        //   3765: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3768: pop
        //   3769: aload #4
        //   3771: aload_3
        //   3772: invokevirtual toString : ()Ljava/lang/String;
        //   3775: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3778: new java/lang/StringBuilder
        //   3781: dup
        //   3782: invokespecial <init> : ()V
        //   3785: astore #4
        //   3787: aload #4
        //   3789: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   3792: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3795: pop
        //   3796: aload #4
        //   3798: aload_1
        //   3799: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3802: pop
        //   3803: aload #4
        //   3805: ldc_w '','
        //   3808: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3811: pop
        //   3812: aload #4
        //   3814: aload_2
        //   3815: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3818: pop
        //   3819: aload #4
        //   3821: invokevirtual toString : ()Ljava/lang/String;
        //   3824: astore #4
        //   3826: aload_0
        //   3827: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3830: aload #4
        //   3832: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3835: aload_0
        //   3836: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3839: astore_3
        //   3840: new java/lang/StringBuilder
        //   3843: dup
        //   3844: invokespecial <init> : ()V
        //   3847: astore #4
        //   3849: aload #4
        //   3851: ldc_w 'txong kqung'
        //   3854: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3857: pop
        //   3858: aload #4
        //   3860: invokestatic Get_ngay : ()Ljava/lang/String;
        //   3863: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3866: pop
        //   3867: aload_3
        //   3868: aload #4
        //   3870: invokevirtual toString : ()Ljava/lang/String;
        //   3873: iconst_1
        //   3874: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3877: invokevirtual show : ()V
        //   3880: goto -> 3897
        //   3883: aload_0
        //   3884: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3887: ldc_w 'Khckquphh
        //   3890: iconst_1
        //   3891: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3894: invokevirtual show : ()V
        //   3897: return
        //   3898: aload_2
        //   3899: invokevirtual length : ()I
        //   3902: sipush #185
        //   3905: if_icmple -> 4058
        //   3908: aload_0
        //   3909: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3912: astore #4
        //   3914: new java/lang/StringBuilder
        //   3917: dup
        //   3918: invokespecial <init> : ()V
        //   3921: astore_3
        //   3922: aload_3
        //   3923: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   3926: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3929: pop
        //   3930: aload_3
        //   3931: aload_1
        //   3932: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3935: pop
        //   3936: aload_3
        //   3937: ldc_w '''
        //   3940: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3943: pop
        //   3944: aload #4
        //   3946: aload_3
        //   3947: invokevirtual toString : ()Ljava/lang/String;
        //   3950: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3953: new java/lang/StringBuilder
        //   3956: dup
        //   3957: invokespecial <init> : ()V
        //   3960: astore #4
        //   3962: aload #4
        //   3964: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   3967: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3970: pop
        //   3971: aload #4
        //   3973: aload_1
        //   3974: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3977: pop
        //   3978: aload #4
        //   3980: ldc_w '','
        //   3983: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3986: pop
        //   3987: aload #4
        //   3989: aload_2
        //   3990: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3993: pop
        //   3994: aload #4
        //   3996: invokevirtual toString : ()Ljava/lang/String;
        //   3999: astore #4
        //   4001: aload_0
        //   4002: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4005: aload #4
        //   4007: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4010: aload_0
        //   4011: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4014: astore_3
        //   4015: new java/lang/StringBuilder
        //   4018: dup
        //   4019: invokespecial <init> : ()V
        //   4022: astore #4
        //   4024: aload #4
        //   4026: ldc_w 'txong kqung'
        //   4029: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4032: pop
        //   4033: aload #4
        //   4035: invokestatic Get_ngay : ()Ljava/lang/String;
        //   4038: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4041: pop
        //   4042: aload_3
        //   4043: aload #4
        //   4045: invokevirtual toString : ()Ljava/lang/String;
        //   4048: iconst_1
        //   4049: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4052: invokevirtual show : ()V
        //   4055: goto -> 4072
        //   4058: aload_0
        //   4059: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4062: ldc_w 'Khckquphh
        //   4065: iconst_1
        //   4066: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4069: invokevirtual show : ()V
        //   4072: return
        //   4073: aload_2
        //   4074: invokevirtual length : ()I
        //   4077: sipush #185
        //   4080: if_icmple -> 4233
        //   4083: aload_0
        //   4084: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4087: astore #4
        //   4089: new java/lang/StringBuilder
        //   4092: dup
        //   4093: invokespecial <init> : ()V
        //   4096: astore_3
        //   4097: aload_3
        //   4098: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   4101: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4104: pop
        //   4105: aload_3
        //   4106: aload_1
        //   4107: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4110: pop
        //   4111: aload_3
        //   4112: ldc_w '''
        //   4115: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4118: pop
        //   4119: aload #4
        //   4121: aload_3
        //   4122: invokevirtual toString : ()Ljava/lang/String;
        //   4125: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4128: new java/lang/StringBuilder
        //   4131: dup
        //   4132: invokespecial <init> : ()V
        //   4135: astore #4
        //   4137: aload #4
        //   4139: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   4142: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4145: pop
        //   4146: aload #4
        //   4148: aload_1
        //   4149: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4152: pop
        //   4153: aload #4
        //   4155: ldc_w '','
        //   4158: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4161: pop
        //   4162: aload #4
        //   4164: aload_2
        //   4165: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4168: pop
        //   4169: aload #4
        //   4171: invokevirtual toString : ()Ljava/lang/String;
        //   4174: astore #4
        //   4176: aload_0
        //   4177: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4180: aload #4
        //   4182: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4185: aload_0
        //   4186: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4189: astore_3
        //   4190: new java/lang/StringBuilder
        //   4193: dup
        //   4194: invokespecial <init> : ()V
        //   4197: astore #4
        //   4199: aload #4
        //   4201: ldc_w 'txong kqung'
        //   4204: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4207: pop
        //   4208: aload #4
        //   4210: invokestatic Get_ngay : ()Ljava/lang/String;
        //   4213: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4216: pop
        //   4217: aload_3
        //   4218: aload #4
        //   4220: invokevirtual toString : ()Ljava/lang/String;
        //   4223: iconst_1
        //   4224: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4227: invokevirtual show : ()V
        //   4230: goto -> 4247
        //   4233: aload_0
        //   4234: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4237: ldc_w 'Khckquphh
        //   4240: iconst_1
        //   4241: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4244: invokevirtual show : ()V
        //   4247: return
        //   4248: ldc_w ''
        //   4251: invokevirtual length : ()I
        //   4254: sipush #185
        //   4257: if_icmple -> 4413
        //   4260: aload_0
        //   4261: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4264: astore_3
        //   4265: new java/lang/StringBuilder
        //   4268: dup
        //   4269: invokespecial <init> : ()V
        //   4272: astore #4
        //   4274: aload #4
        //   4276: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   4279: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4282: pop
        //   4283: aload #4
        //   4285: aload_1
        //   4286: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4289: pop
        //   4290: aload #4
        //   4292: ldc_w '''
        //   4295: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4298: pop
        //   4299: aload_3
        //   4300: aload #4
        //   4302: invokevirtual toString : ()Ljava/lang/String;
        //   4305: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4308: new java/lang/StringBuilder
        //   4311: dup
        //   4312: invokespecial <init> : ()V
        //   4315: astore #4
        //   4317: aload #4
        //   4319: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   4322: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4325: pop
        //   4326: aload #4
        //   4328: aload_1
        //   4329: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4332: pop
        //   4333: aload #4
        //   4335: ldc_w '','
        //   4338: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4341: pop
        //   4342: aload #4
        //   4344: ldc_w ''
        //   4347: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4350: pop
        //   4351: aload #4
        //   4353: invokevirtual toString : ()Ljava/lang/String;
        //   4356: astore #4
        //   4358: aload_0
        //   4359: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4362: aload #4
        //   4364: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4367: aload_0
        //   4368: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4371: astore #4
        //   4373: new java/lang/StringBuilder
        //   4376: dup
        //   4377: invokespecial <init> : ()V
        //   4380: astore_3
        //   4381: aload_3
        //   4382: ldc_w 'txong kqung'
        //   4385: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4388: pop
        //   4389: aload_3
        //   4390: invokestatic Get_ngay : ()Ljava/lang/String;
        //   4393: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4396: pop
        //   4397: aload #4
        //   4399: aload_3
        //   4400: invokevirtual toString : ()Ljava/lang/String;
        //   4403: iconst_1
        //   4404: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4407: invokevirtual show : ()V
        //   4410: goto -> 4427
        //   4413: aload_0
        //   4414: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4417: ldc_w 'Khckquphh
        //   4420: iconst_1
        //   4421: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4424: invokevirtual show : ()V
        //   4427: return
        //   4428: astore #4
        //   4430: goto -> 4575
        //   4433: astore_3
        //   4434: aload #4
        //   4436: astore_3
        //   4437: aload_0
        //   4438: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4441: ldc_w 'Khckquphh
        //   4444: iconst_1
        //   4445: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4448: invokevirtual show : ()V
        //   4451: aload #4
        //   4453: invokevirtual length : ()I
        //   4456: sipush #185
        //   4459: if_icmple -> 3009
        //   4462: aload_0
        //   4463: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4466: astore_3
        //   4467: new java/lang/StringBuilder
        //   4470: dup
        //   4471: invokespecial <init> : ()V
        //   4474: astore_2
        //   4475: aload_2
        //   4476: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   4479: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4482: pop
        //   4483: aload_2
        //   4484: aload_1
        //   4485: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4488: pop
        //   4489: aload_2
        //   4490: ldc_w '''
        //   4493: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4496: pop
        //   4497: aload_3
        //   4498: aload_2
        //   4499: invokevirtual toString : ()Ljava/lang/String;
        //   4502: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4505: new java/lang/StringBuilder
        //   4508: dup
        //   4509: invokespecial <init> : ()V
        //   4512: astore_3
        //   4513: aload_3
        //   4514: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   4517: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4520: pop
        //   4521: aload_3
        //   4522: aload_1
        //   4523: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4526: pop
        //   4527: aload_3
        //   4528: ldc_w '','
        //   4531: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4534: pop
        //   4535: aload_3
        //   4536: aload #4
        //   4538: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4541: pop
        //   4542: aload_3
        //   4543: invokevirtual toString : ()Ljava/lang/String;
        //   4546: astore #4
        //   4548: aload_0
        //   4549: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4552: aload #4
        //   4554: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4557: aload_0
        //   4558: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4561: astore_3
        //   4562: new java/lang/StringBuilder
        //   4565: dup
        //   4566: invokespecial <init> : ()V
        //   4569: astore #4
        //   4571: goto -> 2975
        //   4574: return
        //   4575: aload_3
        //   4576: invokevirtual length : ()I
        //   4579: sipush #185
        //   4582: if_icmple -> 4726
        //   4585: aload_0
        //   4586: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4589: astore_2
        //   4590: new java/lang/StringBuilder
        //   4593: dup
        //   4594: invokespecial <init> : ()V
        //   4597: astore #5
        //   4599: aload #5
        //   4601: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   4604: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4607: pop
        //   4608: aload #5
        //   4610: aload_1
        //   4611: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4614: pop
        //   4615: aload #5
        //   4617: ldc_w '''
        //   4620: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4623: pop
        //   4624: aload_2
        //   4625: aload #5
        //   4627: invokevirtual toString : ()Ljava/lang/String;
        //   4630: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4633: new java/lang/StringBuilder
        //   4636: dup
        //   4637: invokespecial <init> : ()V
        //   4640: astore_2
        //   4641: aload_2
        //   4642: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   4645: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4648: pop
        //   4649: aload_2
        //   4650: aload_1
        //   4651: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4654: pop
        //   4655: aload_2
        //   4656: ldc_w '','
        //   4659: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4662: pop
        //   4663: aload_2
        //   4664: aload_3
        //   4665: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4668: pop
        //   4669: aload_2
        //   4670: invokevirtual toString : ()Ljava/lang/String;
        //   4673: astore_3
        //   4674: aload_0
        //   4675: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4678: aload_3
        //   4679: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4682: aload_0
        //   4683: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4686: astore_3
        //   4687: new java/lang/StringBuilder
        //   4690: dup
        //   4691: invokespecial <init> : ()V
        //   4694: astore_2
        //   4695: aload_2
        //   4696: ldc_w 'txong kqung'
        //   4699: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4702: pop
        //   4703: aload_2
        //   4704: invokestatic Get_ngay : ()Ljava/lang/String;
        //   4707: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4710: pop
        //   4711: aload_3
        //   4712: aload_2
        //   4713: invokevirtual toString : ()Ljava/lang/String;
        //   4716: iconst_1
        //   4717: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4720: invokevirtual show : ()V
        //   4723: goto -> 4740
        //   4726: aload_0
        //   4727: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4730: ldc_w 'Khckquphh
        //   4733: iconst_1
        //   4734: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4737: invokevirtual show : ()V
        //   4740: goto -> 4746
        //   4743: aload #4
        //   4745: athrow
        //   4746: goto -> 4743
        // Exception table:
        //   from	to	target	type
        //   21	33	4433	java/lang/Exception
        //   21	33	4428	finally
        //   38	43	4433	java/lang/Exception
        //   38	43	4428	finally
        //   48	53	4433	java/lang/Exception
        //   48	53	4428	finally
        //   58	67	4433	java/lang/Exception
        //   58	67	4428	finally
        //   72	87	4433	java/lang/Exception
        //   72	87	4428	finally
        //   92	101	4433	java/lang/Exception
        //   92	101	4428	finally
        //   106	112	4433	java/lang/Exception
        //   106	112	4428	finally
        //   117	129	4433	java/lang/Exception
        //   117	129	4428	finally
        //   134	139	4433	java/lang/Exception
        //   134	139	4428	finally
        //   144	149	4433	java/lang/Exception
        //   144	149	4428	finally
        //   154	161	4433	java/lang/Exception
        //   154	161	4428	finally
        //   166	175	4433	java/lang/Exception
        //   166	175	4428	finally
        //   180	195	4433	java/lang/Exception
        //   180	195	4428	finally
        //   200	209	4433	java/lang/Exception
        //   200	209	4428	finally
        //   214	220	4433	java/lang/Exception
        //   214	220	4428	finally
        //   225	238	4433	java/lang/Exception
        //   225	238	4428	finally
        //   243	248	4433	java/lang/Exception
        //   243	248	4428	finally
        //   253	258	4433	java/lang/Exception
        //   253	258	4428	finally
        //   263	270	4433	java/lang/Exception
        //   263	270	4428	finally
        //   275	284	4433	java/lang/Exception
        //   275	284	4428	finally
        //   289	310	4433	java/lang/Exception
        //   289	310	4428	finally
        //   315	324	4433	java/lang/Exception
        //   315	324	4428	finally
        //   329	335	4433	java/lang/Exception
        //   329	335	4428	finally
        //   340	345	4433	java/lang/Exception
        //   340	345	4428	finally
        //   350	355	4433	java/lang/Exception
        //   350	355	4428	finally
        //   360	367	4433	java/lang/Exception
        //   360	367	4428	finally
        //   372	381	4433	java/lang/Exception
        //   372	381	4428	finally
        //   386	419	4433	java/lang/Exception
        //   386	419	4428	finally
        //   424	433	4433	java/lang/Exception
        //   424	433	4428	finally
        //   438	444	4433	java/lang/Exception
        //   438	444	4428	finally
        //   449	462	4433	java/lang/Exception
        //   449	462	4428	finally
        //   467	472	4433	java/lang/Exception
        //   467	472	4428	finally
        //   477	482	4433	java/lang/Exception
        //   477	482	4428	finally
        //   487	494	4433	java/lang/Exception
        //   487	494	4428	finally
        //   499	508	4433	java/lang/Exception
        //   499	508	4428	finally
        //   513	534	4433	java/lang/Exception
        //   513	534	4428	finally
        //   539	548	4433	java/lang/Exception
        //   539	548	4428	finally
        //   553	559	4433	java/lang/Exception
        //   553	559	4428	finally
        //   564	569	4433	java/lang/Exception
        //   564	569	4428	finally
        //   574	579	4433	java/lang/Exception
        //   574	579	4428	finally
        //   584	591	4433	java/lang/Exception
        //   584	591	4428	finally
        //   596	605	4433	java/lang/Exception
        //   596	605	4428	finally
        //   610	632	4433	java/lang/Exception
        //   610	632	4428	finally
        //   637	646	4433	java/lang/Exception
        //   637	646	4428	finally
        //   651	657	4433	java/lang/Exception
        //   651	657	4428	finally
        //   662	667	4433	java/lang/Exception
        //   662	667	4428	finally
        //   672	677	4433	java/lang/Exception
        //   672	677	4428	finally
        //   682	689	4433	java/lang/Exception
        //   682	689	4428	finally
        //   694	703	4433	java/lang/Exception
        //   694	703	4428	finally
        //   708	731	4433	java/lang/Exception
        //   708	731	4428	finally
        //   736	745	4433	java/lang/Exception
        //   736	745	4428	finally
        //   750	756	4433	java/lang/Exception
        //   750	756	4428	finally
        //   761	766	4433	java/lang/Exception
        //   761	766	4428	finally
        //   771	776	4433	java/lang/Exception
        //   771	776	4428	finally
        //   781	788	4433	java/lang/Exception
        //   781	788	4428	finally
        //   793	802	4433	java/lang/Exception
        //   793	802	4428	finally
        //   807	830	4433	java/lang/Exception
        //   807	830	4428	finally
        //   835	844	4433	java/lang/Exception
        //   835	844	4428	finally
        //   849	855	4433	java/lang/Exception
        //   849	855	4428	finally
        //   860	865	4433	java/lang/Exception
        //   860	865	4428	finally
        //   870	875	4433	java/lang/Exception
        //   870	875	4428	finally
        //   880	887	4433	java/lang/Exception
        //   880	887	4428	finally
        //   892	901	4433	java/lang/Exception
        //   892	901	4428	finally
        //   906	929	4433	java/lang/Exception
        //   906	929	4428	finally
        //   934	943	4433	java/lang/Exception
        //   934	943	4428	finally
        //   948	954	4433	java/lang/Exception
        //   948	954	4428	finally
        //   959	964	4433	java/lang/Exception
        //   959	964	4428	finally
        //   969	974	4433	java/lang/Exception
        //   969	974	4428	finally
        //   979	986	4433	java/lang/Exception
        //   979	986	4428	finally
        //   991	1000	4433	java/lang/Exception
        //   991	1000	4428	finally
        //   1005	1039	4433	java/lang/Exception
        //   1005	1039	4428	finally
        //   1044	1053	4433	java/lang/Exception
        //   1044	1053	4428	finally
        //   1058	1064	4433	java/lang/Exception
        //   1058	1064	4428	finally
        //   1069	1082	4433	java/lang/Exception
        //   1069	1082	4428	finally
        //   1087	1092	4433	java/lang/Exception
        //   1087	1092	4428	finally
        //   1097	1102	4433	java/lang/Exception
        //   1097	1102	4428	finally
        //   1107	1114	4433	java/lang/Exception
        //   1107	1114	4428	finally
        //   1119	1128	4433	java/lang/Exception
        //   1119	1128	4428	finally
        //   1133	1154	4433	java/lang/Exception
        //   1133	1154	4428	finally
        //   1159	1168	4433	java/lang/Exception
        //   1159	1168	4428	finally
        //   1173	1179	4433	java/lang/Exception
        //   1173	1179	4428	finally
        //   1184	1189	4433	java/lang/Exception
        //   1184	1189	4428	finally
        //   1194	1199	4433	java/lang/Exception
        //   1194	1199	4428	finally
        //   1204	1211	4433	java/lang/Exception
        //   1204	1211	4428	finally
        //   1216	1225	4433	java/lang/Exception
        //   1216	1225	4428	finally
        //   1230	1252	4433	java/lang/Exception
        //   1230	1252	4428	finally
        //   1257	1266	4433	java/lang/Exception
        //   1257	1266	4428	finally
        //   1271	1277	4433	java/lang/Exception
        //   1271	1277	4428	finally
        //   1282	1287	4433	java/lang/Exception
        //   1282	1287	4428	finally
        //   1292	1297	4433	java/lang/Exception
        //   1292	1297	4428	finally
        //   1302	1309	4433	java/lang/Exception
        //   1302	1309	4428	finally
        //   1314	1323	4433	java/lang/Exception
        //   1314	1323	4428	finally
        //   1328	1351	4433	java/lang/Exception
        //   1328	1351	4428	finally
        //   1356	1365	4433	java/lang/Exception
        //   1356	1365	4428	finally
        //   1370	1376	4433	java/lang/Exception
        //   1370	1376	4428	finally
        //   1381	1386	4433	java/lang/Exception
        //   1381	1386	4428	finally
        //   1391	1396	4433	java/lang/Exception
        //   1391	1396	4428	finally
        //   1401	1408	4433	java/lang/Exception
        //   1401	1408	4428	finally
        //   1413	1422	4433	java/lang/Exception
        //   1413	1422	4428	finally
        //   1427	1461	4433	java/lang/Exception
        //   1427	1461	4428	finally
        //   1466	1475	4433	java/lang/Exception
        //   1466	1475	4428	finally
        //   1480	1486	4433	java/lang/Exception
        //   1480	1486	4428	finally
        //   1491	1504	4433	java/lang/Exception
        //   1491	1504	4428	finally
        //   1509	1514	4433	java/lang/Exception
        //   1509	1514	4428	finally
        //   1519	1524	4433	java/lang/Exception
        //   1519	1524	4428	finally
        //   1529	1536	4433	java/lang/Exception
        //   1529	1536	4428	finally
        //   1541	1550	4433	java/lang/Exception
        //   1541	1550	4428	finally
        //   1555	1576	4433	java/lang/Exception
        //   1555	1576	4428	finally
        //   1581	1590	4433	java/lang/Exception
        //   1581	1590	4428	finally
        //   1595	1601	4433	java/lang/Exception
        //   1595	1601	4428	finally
        //   1606	1611	4433	java/lang/Exception
        //   1606	1611	4428	finally
        //   1616	1621	4433	java/lang/Exception
        //   1616	1621	4428	finally
        //   1626	1633	4433	java/lang/Exception
        //   1626	1633	4428	finally
        //   1638	1647	4433	java/lang/Exception
        //   1638	1647	4428	finally
        //   1652	1674	4433	java/lang/Exception
        //   1652	1674	4428	finally
        //   1679	1688	4433	java/lang/Exception
        //   1679	1688	4428	finally
        //   1693	1699	4433	java/lang/Exception
        //   1693	1699	4428	finally
        //   1704	1709	4433	java/lang/Exception
        //   1704	1709	4428	finally
        //   1714	1719	4433	java/lang/Exception
        //   1714	1719	4428	finally
        //   1724	1731	4433	java/lang/Exception
        //   1724	1731	4428	finally
        //   1736	1745	4433	java/lang/Exception
        //   1736	1745	4428	finally
        //   1750	1773	4433	java/lang/Exception
        //   1750	1773	4428	finally
        //   1778	1787	4433	java/lang/Exception
        //   1778	1787	4428	finally
        //   1792	1798	4433	java/lang/Exception
        //   1792	1798	4428	finally
        //   1803	1808	4433	java/lang/Exception
        //   1803	1808	4428	finally
        //   1813	1818	4433	java/lang/Exception
        //   1813	1818	4428	finally
        //   1823	1830	4433	java/lang/Exception
        //   1823	1830	4428	finally
        //   1835	1844	4433	java/lang/Exception
        //   1835	1844	4428	finally
        //   1849	1872	4433	java/lang/Exception
        //   1849	1872	4428	finally
        //   1877	1886	4433	java/lang/Exception
        //   1877	1886	4428	finally
        //   1891	1897	4433	java/lang/Exception
        //   1891	1897	4428	finally
        //   1902	1907	4433	java/lang/Exception
        //   1902	1907	4428	finally
        //   1912	1917	4433	java/lang/Exception
        //   1912	1917	4428	finally
        //   1922	1929	4433	java/lang/Exception
        //   1922	1929	4428	finally
        //   1934	1943	4433	java/lang/Exception
        //   1934	1943	4428	finally
        //   1948	1971	4433	java/lang/Exception
        //   1948	1971	4428	finally
        //   1976	1985	4433	java/lang/Exception
        //   1976	1985	4428	finally
        //   1990	1996	4433	java/lang/Exception
        //   1990	1996	4428	finally
        //   2001	2006	4433	java/lang/Exception
        //   2001	2006	4428	finally
        //   2011	2016	4433	java/lang/Exception
        //   2011	2016	4428	finally
        //   2021	2028	4433	java/lang/Exception
        //   2021	2028	4428	finally
        //   2033	2042	4433	java/lang/Exception
        //   2033	2042	4428	finally
        //   2047	2081	4433	java/lang/Exception
        //   2047	2081	4428	finally
        //   2086	2095	4433	java/lang/Exception
        //   2086	2095	4428	finally
        //   2100	2106	4433	java/lang/Exception
        //   2100	2106	4428	finally
        //   2111	2124	4433	java/lang/Exception
        //   2111	2124	4428	finally
        //   2129	2134	4433	java/lang/Exception
        //   2129	2134	4428	finally
        //   2139	2144	4433	java/lang/Exception
        //   2139	2144	4428	finally
        //   2149	2156	4433	java/lang/Exception
        //   2149	2156	4428	finally
        //   2161	2170	4433	java/lang/Exception
        //   2161	2170	4428	finally
        //   2175	2196	4433	java/lang/Exception
        //   2175	2196	4428	finally
        //   2201	2210	4433	java/lang/Exception
        //   2201	2210	4428	finally
        //   2215	2221	4433	java/lang/Exception
        //   2215	2221	4428	finally
        //   2226	2231	4433	java/lang/Exception
        //   2226	2231	4428	finally
        //   2236	2241	4433	java/lang/Exception
        //   2236	2241	4428	finally
        //   2246	2253	4433	java/lang/Exception
        //   2246	2253	4428	finally
        //   2258	2267	4433	java/lang/Exception
        //   2258	2267	4428	finally
        //   2272	2294	4433	java/lang/Exception
        //   2272	2294	4428	finally
        //   2299	2308	4433	java/lang/Exception
        //   2299	2308	4428	finally
        //   2313	2319	4433	java/lang/Exception
        //   2313	2319	4428	finally
        //   2324	2329	4433	java/lang/Exception
        //   2324	2329	4428	finally
        //   2334	2339	4433	java/lang/Exception
        //   2334	2339	4428	finally
        //   2344	2351	4433	java/lang/Exception
        //   2344	2351	4428	finally
        //   2356	2365	4433	java/lang/Exception
        //   2356	2365	4428	finally
        //   2370	2404	4433	java/lang/Exception
        //   2370	2404	4428	finally
        //   2409	2418	4433	java/lang/Exception
        //   2409	2418	4428	finally
        //   2423	2429	4433	java/lang/Exception
        //   2423	2429	4428	finally
        //   2434	2447	4433	java/lang/Exception
        //   2434	2447	4428	finally
        //   2452	2457	4433	java/lang/Exception
        //   2452	2457	4428	finally
        //   2462	2467	4433	java/lang/Exception
        //   2462	2467	4428	finally
        //   2472	2479	4433	java/lang/Exception
        //   2472	2479	4428	finally
        //   2484	2493	4433	java/lang/Exception
        //   2484	2493	4428	finally
        //   2498	2519	4433	java/lang/Exception
        //   2498	2519	4428	finally
        //   2524	2533	4433	java/lang/Exception
        //   2524	2533	4428	finally
        //   2538	2544	4433	java/lang/Exception
        //   2538	2544	4428	finally
        //   2549	2554	4433	java/lang/Exception
        //   2549	2554	4428	finally
        //   2559	2564	4433	java/lang/Exception
        //   2559	2564	4428	finally
        //   2569	2576	4433	java/lang/Exception
        //   2569	2576	4428	finally
        //   2581	2590	4433	java/lang/Exception
        //   2581	2590	4428	finally
        //   2595	2616	4433	java/lang/Exception
        //   2595	2616	4428	finally
        //   2621	2630	4433	java/lang/Exception
        //   2621	2630	4428	finally
        //   2635	2641	4433	java/lang/Exception
        //   2635	2641	4428	finally
        //   2646	2651	4433	java/lang/Exception
        //   2646	2651	4428	finally
        //   2656	2661	4433	java/lang/Exception
        //   2656	2661	4428	finally
        //   2666	2673	4433	java/lang/Exception
        //   2666	2673	4428	finally
        //   2678	2687	4433	java/lang/Exception
        //   2678	2687	4428	finally
        //   2692	2714	4433	java/lang/Exception
        //   2692	2714	4428	finally
        //   2719	2728	4433	java/lang/Exception
        //   2719	2728	4428	finally
        //   2733	2739	4433	java/lang/Exception
        //   2733	2739	4428	finally
        //   2744	2749	4433	java/lang/Exception
        //   2744	2749	4428	finally
        //   2754	2759	4433	java/lang/Exception
        //   2754	2759	4428	finally
        //   2764	2771	4433	java/lang/Exception
        //   2764	2771	4428	finally
        //   2776	2785	4433	java/lang/Exception
        //   2776	2785	4428	finally
        //   2790	2824	4433	java/lang/Exception
        //   2790	2824	4428	finally
        //   2829	2838	4433	java/lang/Exception
        //   2829	2838	4428	finally
        //   2843	2849	4433	java/lang/Exception
        //   2843	2849	4428	finally
        //   4437	4451	4428	finally
    }

    public void PhantichXosomeNewNew() {
        // Byte code:
        //   0: new tamhoang/ldpro4/MainActivity
        //   3: dup
        //   4: invokespecial <init> : ()V
        //   7: pop
        //   8: invokestatic Get_date : ()Ljava/lang/String;
        //   11: astore_1
        //   12: ldc_w ''
        //   15: astore_2
        //   16: aload_2
        //   17: astore_3
        //   18: aload_2
        //   19: astore #4
        //   21: aload_0
        //   22: getfield ArrayGiai : [Ljava/lang/String;
        //   25: iconst_3
        //   26: aaload
        //   27: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   30: ifeq -> 4255
        //   33: aload_2
        //   34: astore_3
        //   35: aload_2
        //   36: astore #4
        //   38: new java/lang/StringBuilder
        //   41: astore #5
        //   43: aload_2
        //   44: astore_3
        //   45: aload_2
        //   46: astore #4
        //   48: aload #5
        //   50: invokespecial <init> : ()V
        //   53: aload_2
        //   54: astore_3
        //   55: aload_2
        //   56: astore #4
        //   58: aload #5
        //   60: ldc_w '''
        //   63: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   66: pop
        //   67: aload_2
        //   68: astore_3
        //   69: aload_2
        //   70: astore #4
        //   72: aload #5
        //   74: aload_0
        //   75: getfield ArrayGiai : [Ljava/lang/String;
        //   78: iconst_3
        //   79: aaload
        //   80: invokevirtual trim : ()Ljava/lang/String;
        //   83: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   86: pop
        //   87: aload_2
        //   88: astore_3
        //   89: aload_2
        //   90: astore #4
        //   92: aload #5
        //   94: ldc_w '','
        //   97: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   100: pop
        //   101: aload_2
        //   102: astore_3
        //   103: aload_2
        //   104: astore #4
        //   106: aload #5
        //   108: invokevirtual toString : ()Ljava/lang/String;
        //   111: astore_2
        //   112: aload_2
        //   113: astore_3
        //   114: aload_2
        //   115: astore #4
        //   117: aload_0
        //   118: getfield ArrayGiai : [Ljava/lang/String;
        //   121: iconst_5
        //   122: aaload
        //   123: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   126: ifeq -> 4082
        //   129: aload_2
        //   130: astore_3
        //   131: aload_2
        //   132: astore #4
        //   134: new java/lang/StringBuilder
        //   137: astore #5
        //   139: aload_2
        //   140: astore_3
        //   141: aload_2
        //   142: astore #4
        //   144: aload #5
        //   146: invokespecial <init> : ()V
        //   149: aload_2
        //   150: astore_3
        //   151: aload_2
        //   152: astore #4
        //   154: aload #5
        //   156: aload_2
        //   157: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   160: pop
        //   161: aload_2
        //   162: astore_3
        //   163: aload_2
        //   164: astore #4
        //   166: aload #5
        //   168: ldc_w '''
        //   171: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   174: pop
        //   175: aload_2
        //   176: astore_3
        //   177: aload_2
        //   178: astore #4
        //   180: aload #5
        //   182: aload_0
        //   183: getfield ArrayGiai : [Ljava/lang/String;
        //   186: iconst_5
        //   187: aaload
        //   188: invokevirtual trim : ()Ljava/lang/String;
        //   191: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   194: pop
        //   195: aload_2
        //   196: astore_3
        //   197: aload_2
        //   198: astore #4
        //   200: aload #5
        //   202: ldc_w '','
        //   205: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   208: pop
        //   209: aload_2
        //   210: astore_3
        //   211: aload_2
        //   212: astore #4
        //   214: aload #5
        //   216: invokevirtual toString : ()Ljava/lang/String;
        //   219: astore_2
        //   220: aload_2
        //   221: astore_3
        //   222: aload_2
        //   223: astore #4
        //   225: aload_0
        //   226: getfield ArrayGiai : [Ljava/lang/String;
        //   229: bipush #7
        //   231: aaload
        //   232: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   235: ifeq -> 3909
        //   238: aload_2
        //   239: astore_3
        //   240: aload_2
        //   241: astore #4
        //   243: new java/lang/StringBuilder
        //   246: astore #5
        //   248: aload_2
        //   249: astore_3
        //   250: aload_2
        //   251: astore #4
        //   253: aload #5
        //   255: invokespecial <init> : ()V
        //   258: aload_2
        //   259: astore_3
        //   260: aload_2
        //   261: astore #4
        //   263: aload #5
        //   265: aload_2
        //   266: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   269: pop
        //   270: aload_2
        //   271: astore_3
        //   272: aload_2
        //   273: astore #4
        //   275: aload #5
        //   277: ldc_w '''
        //   280: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   283: pop
        //   284: aload_2
        //   285: astore_3
        //   286: aload_2
        //   287: astore #4
        //   289: aload #5
        //   291: aload_0
        //   292: getfield ArrayGiai : [Ljava/lang/String;
        //   295: bipush #7
        //   297: aaload
        //   298: invokevirtual trim : ()Ljava/lang/String;
        //   301: iconst_0
        //   302: iconst_5
        //   303: invokevirtual substring : (II)Ljava/lang/String;
        //   306: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   309: pop
        //   310: aload_2
        //   311: astore_3
        //   312: aload_2
        //   313: astore #4
        //   315: aload #5
        //   317: ldc_w '','
        //   320: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   323: pop
        //   324: aload_2
        //   325: astore_3
        //   326: aload_2
        //   327: astore #4
        //   329: aload #5
        //   331: invokevirtual toString : ()Ljava/lang/String;
        //   334: astore_2
        //   335: aload_2
        //   336: astore_3
        //   337: aload_2
        //   338: astore #4
        //   340: new java/lang/StringBuilder
        //   343: astore #5
        //   345: aload_2
        //   346: astore_3
        //   347: aload_2
        //   348: astore #4
        //   350: aload #5
        //   352: invokespecial <init> : ()V
        //   355: aload_2
        //   356: astore_3
        //   357: aload_2
        //   358: astore #4
        //   360: aload #5
        //   362: aload_2
        //   363: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   366: pop
        //   367: aload_2
        //   368: astore_3
        //   369: aload_2
        //   370: astore #4
        //   372: aload #5
        //   374: ldc_w '''
        //   377: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   380: pop
        //   381: aload_2
        //   382: astore_3
        //   383: aload_2
        //   384: astore #4
        //   386: aload #5
        //   388: aload_0
        //   389: getfield ArrayGiai : [Ljava/lang/String;
        //   392: bipush #7
        //   394: aaload
        //   395: invokevirtual trim : ()Ljava/lang/String;
        //   398: iconst_5
        //   399: aload_0
        //   400: getfield ArrayGiai : [Ljava/lang/String;
        //   403: bipush #7
        //   405: aaload
        //   406: invokevirtual trim : ()Ljava/lang/String;
        //   409: invokevirtual length : ()I
        //   412: invokevirtual substring : (II)Ljava/lang/String;
        //   415: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   418: pop
        //   419: aload_2
        //   420: astore_3
        //   421: aload_2
        //   422: astore #4
        //   424: aload #5
        //   426: ldc_w '','
        //   429: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   432: pop
        //   433: aload_2
        //   434: astore_3
        //   435: aload_2
        //   436: astore #4
        //   438: aload #5
        //   440: invokevirtual toString : ()Ljava/lang/String;
        //   443: astore_2
        //   444: aload_2
        //   445: astore_3
        //   446: aload_2
        //   447: astore #4
        //   449: aload_0
        //   450: getfield ArrayGiai : [Ljava/lang/String;
        //   453: bipush #9
        //   455: aaload
        //   456: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   459: ifeq -> 3731
        //   462: aload_2
        //   463: astore_3
        //   464: aload_2
        //   465: astore #4
        //   467: new java/lang/StringBuilder
        //   470: astore #5
        //   472: aload_2
        //   473: astore_3
        //   474: aload_2
        //   475: astore #4
        //   477: aload #5
        //   479: invokespecial <init> : ()V
        //   482: aload_2
        //   483: astore_3
        //   484: aload_2
        //   485: astore #4
        //   487: aload #5
        //   489: aload_2
        //   490: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   493: pop
        //   494: aload_2
        //   495: astore_3
        //   496: aload_2
        //   497: astore #4
        //   499: aload #5
        //   501: ldc_w '''
        //   504: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   507: pop
        //   508: aload_2
        //   509: astore_3
        //   510: aload_2
        //   511: astore #4
        //   513: aload #5
        //   515: aload_0
        //   516: getfield ArrayGiai : [Ljava/lang/String;
        //   519: bipush #9
        //   521: aaload
        //   522: invokevirtual trim : ()Ljava/lang/String;
        //   525: iconst_0
        //   526: iconst_5
        //   527: invokevirtual substring : (II)Ljava/lang/String;
        //   530: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   533: pop
        //   534: aload_2
        //   535: astore_3
        //   536: aload_2
        //   537: astore #4
        //   539: aload #5
        //   541: ldc_w '','
        //   544: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   547: pop
        //   548: aload_2
        //   549: astore_3
        //   550: aload_2
        //   551: astore #4
        //   553: aload #5
        //   555: invokevirtual toString : ()Ljava/lang/String;
        //   558: astore_2
        //   559: aload_2
        //   560: astore_3
        //   561: aload_2
        //   562: astore #4
        //   564: new java/lang/StringBuilder
        //   567: astore #5
        //   569: aload_2
        //   570: astore_3
        //   571: aload_2
        //   572: astore #4
        //   574: aload #5
        //   576: invokespecial <init> : ()V
        //   579: aload_2
        //   580: astore_3
        //   581: aload_2
        //   582: astore #4
        //   584: aload #5
        //   586: aload_2
        //   587: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   590: pop
        //   591: aload_2
        //   592: astore_3
        //   593: aload_2
        //   594: astore #4
        //   596: aload #5
        //   598: ldc_w '''
        //   601: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   604: pop
        //   605: aload_2
        //   606: astore_3
        //   607: aload_2
        //   608: astore #4
        //   610: aload #5
        //   612: aload_0
        //   613: getfield ArrayGiai : [Ljava/lang/String;
        //   616: bipush #9
        //   618: aaload
        //   619: invokevirtual trim : ()Ljava/lang/String;
        //   622: iconst_5
        //   623: bipush #10
        //   625: invokevirtual substring : (II)Ljava/lang/String;
        //   628: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   631: pop
        //   632: aload_2
        //   633: astore_3
        //   634: aload_2
        //   635: astore #4
        //   637: aload #5
        //   639: ldc_w '','
        //   642: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   645: pop
        //   646: aload_2
        //   647: astore_3
        //   648: aload_2
        //   649: astore #4
        //   651: aload #5
        //   653: invokevirtual toString : ()Ljava/lang/String;
        //   656: astore_2
        //   657: aload_2
        //   658: astore_3
        //   659: aload_2
        //   660: astore #4
        //   662: new java/lang/StringBuilder
        //   665: astore #5
        //   667: aload_2
        //   668: astore_3
        //   669: aload_2
        //   670: astore #4
        //   672: aload #5
        //   674: invokespecial <init> : ()V
        //   677: aload_2
        //   678: astore_3
        //   679: aload_2
        //   680: astore #4
        //   682: aload #5
        //   684: aload_2
        //   685: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   688: pop
        //   689: aload_2
        //   690: astore_3
        //   691: aload_2
        //   692: astore #4
        //   694: aload #5
        //   696: ldc_w '''
        //   699: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   702: pop
        //   703: aload_2
        //   704: astore_3
        //   705: aload_2
        //   706: astore #4
        //   708: aload #5
        //   710: aload_0
        //   711: getfield ArrayGiai : [Ljava/lang/String;
        //   714: bipush #9
        //   716: aaload
        //   717: invokevirtual trim : ()Ljava/lang/String;
        //   720: bipush #10
        //   722: bipush #15
        //   724: invokevirtual substring : (II)Ljava/lang/String;
        //   727: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   730: pop
        //   731: aload_2
        //   732: astore_3
        //   733: aload_2
        //   734: astore #4
        //   736: aload #5
        //   738: ldc_w '','
        //   741: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   744: pop
        //   745: aload_2
        //   746: astore_3
        //   747: aload_2
        //   748: astore #4
        //   750: aload #5
        //   752: invokevirtual toString : ()Ljava/lang/String;
        //   755: astore_2
        //   756: aload_2
        //   757: astore_3
        //   758: aload_2
        //   759: astore #4
        //   761: new java/lang/StringBuilder
        //   764: astore #5
        //   766: aload_2
        //   767: astore_3
        //   768: aload_2
        //   769: astore #4
        //   771: aload #5
        //   773: invokespecial <init> : ()V
        //   776: aload_2
        //   777: astore_3
        //   778: aload_2
        //   779: astore #4
        //   781: aload #5
        //   783: aload_2
        //   784: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   787: pop
        //   788: aload_2
        //   789: astore_3
        //   790: aload_2
        //   791: astore #4
        //   793: aload #5
        //   795: ldc_w '''
        //   798: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   801: pop
        //   802: aload_2
        //   803: astore_3
        //   804: aload_2
        //   805: astore #4
        //   807: aload #5
        //   809: aload_0
        //   810: getfield ArrayGiai : [Ljava/lang/String;
        //   813: bipush #9
        //   815: aaload
        //   816: invokevirtual trim : ()Ljava/lang/String;
        //   819: bipush #15
        //   821: bipush #20
        //   823: invokevirtual substring : (II)Ljava/lang/String;
        //   826: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   829: pop
        //   830: aload_2
        //   831: astore_3
        //   832: aload_2
        //   833: astore #4
        //   835: aload #5
        //   837: ldc_w '','
        //   840: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   843: pop
        //   844: aload_2
        //   845: astore_3
        //   846: aload_2
        //   847: astore #4
        //   849: aload #5
        //   851: invokevirtual toString : ()Ljava/lang/String;
        //   854: astore_2
        //   855: aload_2
        //   856: astore_3
        //   857: aload_2
        //   858: astore #4
        //   860: new java/lang/StringBuilder
        //   863: astore #5
        //   865: aload_2
        //   866: astore_3
        //   867: aload_2
        //   868: astore #4
        //   870: aload #5
        //   872: invokespecial <init> : ()V
        //   875: aload_2
        //   876: astore_3
        //   877: aload_2
        //   878: astore #4
        //   880: aload #5
        //   882: aload_2
        //   883: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   886: pop
        //   887: aload_2
        //   888: astore_3
        //   889: aload_2
        //   890: astore #4
        //   892: aload #5
        //   894: ldc_w '''
        //   897: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   900: pop
        //   901: aload_2
        //   902: astore_3
        //   903: aload_2
        //   904: astore #4
        //   906: aload #5
        //   908: aload_0
        //   909: getfield ArrayGiai : [Ljava/lang/String;
        //   912: bipush #9
        //   914: aaload
        //   915: invokevirtual trim : ()Ljava/lang/String;
        //   918: bipush #20
        //   920: bipush #25
        //   922: invokevirtual substring : (II)Ljava/lang/String;
        //   925: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   928: pop
        //   929: aload_2
        //   930: astore_3
        //   931: aload_2
        //   932: astore #4
        //   934: aload #5
        //   936: ldc_w '','
        //   939: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   942: pop
        //   943: aload_2
        //   944: astore_3
        //   945: aload_2
        //   946: astore #4
        //   948: aload #5
        //   950: invokevirtual toString : ()Ljava/lang/String;
        //   953: astore_2
        //   954: aload_2
        //   955: astore_3
        //   956: aload_2
        //   957: astore #4
        //   959: new java/lang/StringBuilder
        //   962: astore #5
        //   964: aload_2
        //   965: astore_3
        //   966: aload_2
        //   967: astore #4
        //   969: aload #5
        //   971: invokespecial <init> : ()V
        //   974: aload_2
        //   975: astore_3
        //   976: aload_2
        //   977: astore #4
        //   979: aload #5
        //   981: aload_2
        //   982: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   985: pop
        //   986: aload_2
        //   987: astore_3
        //   988: aload_2
        //   989: astore #4
        //   991: aload #5
        //   993: ldc_w '''
        //   996: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   999: pop
        //   1000: aload_2
        //   1001: astore_3
        //   1002: aload_2
        //   1003: astore #4
        //   1005: aload #5
        //   1007: aload_0
        //   1008: getfield ArrayGiai : [Ljava/lang/String;
        //   1011: bipush #9
        //   1013: aaload
        //   1014: invokevirtual trim : ()Ljava/lang/String;
        //   1017: bipush #25
        //   1019: aload_0
        //   1020: getfield ArrayGiai : [Ljava/lang/String;
        //   1023: bipush #9
        //   1025: aaload
        //   1026: invokevirtual trim : ()Ljava/lang/String;
        //   1029: invokevirtual length : ()I
        //   1032: invokevirtual substring : (II)Ljava/lang/String;
        //   1035: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1038: pop
        //   1039: aload_2
        //   1040: astore_3
        //   1041: aload_2
        //   1042: astore #4
        //   1044: aload #5
        //   1046: ldc_w '','
        //   1049: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1052: pop
        //   1053: aload_2
        //   1054: astore_3
        //   1055: aload_2
        //   1056: astore #4
        //   1058: aload #5
        //   1060: invokevirtual toString : ()Ljava/lang/String;
        //   1063: astore_2
        //   1064: aload_2
        //   1065: astore_3
        //   1066: aload_2
        //   1067: astore #4
        //   1069: aload_0
        //   1070: getfield ArrayGiai : [Ljava/lang/String;
        //   1073: bipush #11
        //   1075: aaload
        //   1076: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   1079: ifeq -> 3558
        //   1082: aload_2
        //   1083: astore_3
        //   1084: aload_2
        //   1085: astore #4
        //   1087: new java/lang/StringBuilder
        //   1090: astore #5
        //   1092: aload_2
        //   1093: astore_3
        //   1094: aload_2
        //   1095: astore #4
        //   1097: aload #5
        //   1099: invokespecial <init> : ()V
        //   1102: aload_2
        //   1103: astore_3
        //   1104: aload_2
        //   1105: astore #4
        //   1107: aload #5
        //   1109: aload_2
        //   1110: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1113: pop
        //   1114: aload_2
        //   1115: astore_3
        //   1116: aload_2
        //   1117: astore #4
        //   1119: aload #5
        //   1121: ldc_w '''
        //   1124: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1127: pop
        //   1128: aload_2
        //   1129: astore_3
        //   1130: aload_2
        //   1131: astore #4
        //   1133: aload #5
        //   1135: aload_0
        //   1136: getfield ArrayGiai : [Ljava/lang/String;
        //   1139: bipush #11
        //   1141: aaload
        //   1142: invokevirtual trim : ()Ljava/lang/String;
        //   1145: iconst_0
        //   1146: iconst_4
        //   1147: invokevirtual substring : (II)Ljava/lang/String;
        //   1150: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1153: pop
        //   1154: aload_2
        //   1155: astore_3
        //   1156: aload_2
        //   1157: astore #4
        //   1159: aload #5
        //   1161: ldc_w '','
        //   1164: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1167: pop
        //   1168: aload_2
        //   1169: astore_3
        //   1170: aload_2
        //   1171: astore #4
        //   1173: aload #5
        //   1175: invokevirtual toString : ()Ljava/lang/String;
        //   1178: astore_2
        //   1179: aload_2
        //   1180: astore_3
        //   1181: aload_2
        //   1182: astore #4
        //   1184: new java/lang/StringBuilder
        //   1187: astore #5
        //   1189: aload_2
        //   1190: astore_3
        //   1191: aload_2
        //   1192: astore #4
        //   1194: aload #5
        //   1196: invokespecial <init> : ()V
        //   1199: aload_2
        //   1200: astore_3
        //   1201: aload_2
        //   1202: astore #4
        //   1204: aload #5
        //   1206: aload_2
        //   1207: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1210: pop
        //   1211: aload_2
        //   1212: astore_3
        //   1213: aload_2
        //   1214: astore #4
        //   1216: aload #5
        //   1218: ldc_w '''
        //   1221: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1224: pop
        //   1225: aload_2
        //   1226: astore_3
        //   1227: aload_2
        //   1228: astore #4
        //   1230: aload #5
        //   1232: aload_0
        //   1233: getfield ArrayGiai : [Ljava/lang/String;
        //   1236: bipush #11
        //   1238: aaload
        //   1239: invokevirtual trim : ()Ljava/lang/String;
        //   1242: iconst_4
        //   1243: bipush #8
        //   1245: invokevirtual substring : (II)Ljava/lang/String;
        //   1248: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1251: pop
        //   1252: aload_2
        //   1253: astore_3
        //   1254: aload_2
        //   1255: astore #4
        //   1257: aload #5
        //   1259: ldc_w '','
        //   1262: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1265: pop
        //   1266: aload_2
        //   1267: astore_3
        //   1268: aload_2
        //   1269: astore #4
        //   1271: aload #5
        //   1273: invokevirtual toString : ()Ljava/lang/String;
        //   1276: astore_2
        //   1277: aload_2
        //   1278: astore_3
        //   1279: aload_2
        //   1280: astore #4
        //   1282: new java/lang/StringBuilder
        //   1285: astore #5
        //   1287: aload_2
        //   1288: astore_3
        //   1289: aload_2
        //   1290: astore #4
        //   1292: aload #5
        //   1294: invokespecial <init> : ()V
        //   1297: aload_2
        //   1298: astore_3
        //   1299: aload_2
        //   1300: astore #4
        //   1302: aload #5
        //   1304: aload_2
        //   1305: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1308: pop
        //   1309: aload_2
        //   1310: astore_3
        //   1311: aload_2
        //   1312: astore #4
        //   1314: aload #5
        //   1316: ldc_w '''
        //   1319: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1322: pop
        //   1323: aload_2
        //   1324: astore_3
        //   1325: aload_2
        //   1326: astore #4
        //   1328: aload #5
        //   1330: aload_0
        //   1331: getfield ArrayGiai : [Ljava/lang/String;
        //   1334: bipush #11
        //   1336: aaload
        //   1337: invokevirtual trim : ()Ljava/lang/String;
        //   1340: bipush #8
        //   1342: bipush #12
        //   1344: invokevirtual substring : (II)Ljava/lang/String;
        //   1347: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1350: pop
        //   1351: aload_2
        //   1352: astore_3
        //   1353: aload_2
        //   1354: astore #4
        //   1356: aload #5
        //   1358: ldc_w '','
        //   1361: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1364: pop
        //   1365: aload_2
        //   1366: astore_3
        //   1367: aload_2
        //   1368: astore #4
        //   1370: aload #5
        //   1372: invokevirtual toString : ()Ljava/lang/String;
        //   1375: astore_2
        //   1376: aload_2
        //   1377: astore_3
        //   1378: aload_2
        //   1379: astore #4
        //   1381: new java/lang/StringBuilder
        //   1384: astore #5
        //   1386: aload_2
        //   1387: astore_3
        //   1388: aload_2
        //   1389: astore #4
        //   1391: aload #5
        //   1393: invokespecial <init> : ()V
        //   1396: aload_2
        //   1397: astore_3
        //   1398: aload_2
        //   1399: astore #4
        //   1401: aload #5
        //   1403: aload_2
        //   1404: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1407: pop
        //   1408: aload_2
        //   1409: astore_3
        //   1410: aload_2
        //   1411: astore #4
        //   1413: aload #5
        //   1415: ldc_w '''
        //   1418: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1421: pop
        //   1422: aload_2
        //   1423: astore_3
        //   1424: aload_2
        //   1425: astore #4
        //   1427: aload #5
        //   1429: aload_0
        //   1430: getfield ArrayGiai : [Ljava/lang/String;
        //   1433: bipush #11
        //   1435: aaload
        //   1436: invokevirtual trim : ()Ljava/lang/String;
        //   1439: bipush #12
        //   1441: aload_0
        //   1442: getfield ArrayGiai : [Ljava/lang/String;
        //   1445: bipush #11
        //   1447: aaload
        //   1448: invokevirtual trim : ()Ljava/lang/String;
        //   1451: invokevirtual length : ()I
        //   1454: invokevirtual substring : (II)Ljava/lang/String;
        //   1457: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1460: pop
        //   1461: aload_2
        //   1462: astore_3
        //   1463: aload_2
        //   1464: astore #4
        //   1466: aload #5
        //   1468: ldc_w '','
        //   1471: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1474: pop
        //   1475: aload_2
        //   1476: astore_3
        //   1477: aload_2
        //   1478: astore #4
        //   1480: aload #5
        //   1482: invokevirtual toString : ()Ljava/lang/String;
        //   1485: astore_2
        //   1486: aload_2
        //   1487: astore_3
        //   1488: aload_2
        //   1489: astore #4
        //   1491: aload_0
        //   1492: getfield ArrayGiai : [Ljava/lang/String;
        //   1495: bipush #13
        //   1497: aaload
        //   1498: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   1501: ifeq -> 3380
        //   1504: aload_2
        //   1505: astore_3
        //   1506: aload_2
        //   1507: astore #4
        //   1509: new java/lang/StringBuilder
        //   1512: astore #5
        //   1514: aload_2
        //   1515: astore_3
        //   1516: aload_2
        //   1517: astore #4
        //   1519: aload #5
        //   1521: invokespecial <init> : ()V
        //   1524: aload_2
        //   1525: astore_3
        //   1526: aload_2
        //   1527: astore #4
        //   1529: aload #5
        //   1531: aload_2
        //   1532: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1535: pop
        //   1536: aload_2
        //   1537: astore_3
        //   1538: aload_2
        //   1539: astore #4
        //   1541: aload #5
        //   1543: ldc_w '''
        //   1546: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1549: pop
        //   1550: aload_2
        //   1551: astore_3
        //   1552: aload_2
        //   1553: astore #4
        //   1555: aload #5
        //   1557: aload_0
        //   1558: getfield ArrayGiai : [Ljava/lang/String;
        //   1561: bipush #13
        //   1563: aaload
        //   1564: invokevirtual trim : ()Ljava/lang/String;
        //   1567: iconst_0
        //   1568: iconst_4
        //   1569: invokevirtual substring : (II)Ljava/lang/String;
        //   1572: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1575: pop
        //   1576: aload_2
        //   1577: astore_3
        //   1578: aload_2
        //   1579: astore #4
        //   1581: aload #5
        //   1583: ldc_w '','
        //   1586: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1589: pop
        //   1590: aload_2
        //   1591: astore_3
        //   1592: aload_2
        //   1593: astore #4
        //   1595: aload #5
        //   1597: invokevirtual toString : ()Ljava/lang/String;
        //   1600: astore_2
        //   1601: aload_2
        //   1602: astore_3
        //   1603: aload_2
        //   1604: astore #4
        //   1606: new java/lang/StringBuilder
        //   1609: astore #5
        //   1611: aload_2
        //   1612: astore_3
        //   1613: aload_2
        //   1614: astore #4
        //   1616: aload #5
        //   1618: invokespecial <init> : ()V
        //   1621: aload_2
        //   1622: astore_3
        //   1623: aload_2
        //   1624: astore #4
        //   1626: aload #5
        //   1628: aload_2
        //   1629: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1632: pop
        //   1633: aload_2
        //   1634: astore_3
        //   1635: aload_2
        //   1636: astore #4
        //   1638: aload #5
        //   1640: ldc_w '''
        //   1643: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1646: pop
        //   1647: aload_2
        //   1648: astore_3
        //   1649: aload_2
        //   1650: astore #4
        //   1652: aload #5
        //   1654: aload_0
        //   1655: getfield ArrayGiai : [Ljava/lang/String;
        //   1658: bipush #13
        //   1660: aaload
        //   1661: invokevirtual trim : ()Ljava/lang/String;
        //   1664: iconst_4
        //   1665: bipush #8
        //   1667: invokevirtual substring : (II)Ljava/lang/String;
        //   1670: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1673: pop
        //   1674: aload_2
        //   1675: astore_3
        //   1676: aload_2
        //   1677: astore #4
        //   1679: aload #5
        //   1681: ldc_w '','
        //   1684: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1687: pop
        //   1688: aload_2
        //   1689: astore_3
        //   1690: aload_2
        //   1691: astore #4
        //   1693: aload #5
        //   1695: invokevirtual toString : ()Ljava/lang/String;
        //   1698: astore_2
        //   1699: aload_2
        //   1700: astore_3
        //   1701: aload_2
        //   1702: astore #4
        //   1704: new java/lang/StringBuilder
        //   1707: astore #5
        //   1709: aload_2
        //   1710: astore_3
        //   1711: aload_2
        //   1712: astore #4
        //   1714: aload #5
        //   1716: invokespecial <init> : ()V
        //   1719: aload_2
        //   1720: astore_3
        //   1721: aload_2
        //   1722: astore #4
        //   1724: aload #5
        //   1726: aload_2
        //   1727: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1730: pop
        //   1731: aload_2
        //   1732: astore_3
        //   1733: aload_2
        //   1734: astore #4
        //   1736: aload #5
        //   1738: ldc_w '''
        //   1741: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1744: pop
        //   1745: aload_2
        //   1746: astore_3
        //   1747: aload_2
        //   1748: astore #4
        //   1750: aload #5
        //   1752: aload_0
        //   1753: getfield ArrayGiai : [Ljava/lang/String;
        //   1756: bipush #13
        //   1758: aaload
        //   1759: invokevirtual trim : ()Ljava/lang/String;
        //   1762: bipush #8
        //   1764: bipush #12
        //   1766: invokevirtual substring : (II)Ljava/lang/String;
        //   1769: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1772: pop
        //   1773: aload_2
        //   1774: astore_3
        //   1775: aload_2
        //   1776: astore #4
        //   1778: aload #5
        //   1780: ldc_w '','
        //   1783: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1786: pop
        //   1787: aload_2
        //   1788: astore_3
        //   1789: aload_2
        //   1790: astore #4
        //   1792: aload #5
        //   1794: invokevirtual toString : ()Ljava/lang/String;
        //   1797: astore_2
        //   1798: aload_2
        //   1799: astore_3
        //   1800: aload_2
        //   1801: astore #4
        //   1803: new java/lang/StringBuilder
        //   1806: astore #5
        //   1808: aload_2
        //   1809: astore_3
        //   1810: aload_2
        //   1811: astore #4
        //   1813: aload #5
        //   1815: invokespecial <init> : ()V
        //   1818: aload_2
        //   1819: astore_3
        //   1820: aload_2
        //   1821: astore #4
        //   1823: aload #5
        //   1825: aload_2
        //   1826: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1829: pop
        //   1830: aload_2
        //   1831: astore_3
        //   1832: aload_2
        //   1833: astore #4
        //   1835: aload #5
        //   1837: ldc_w '''
        //   1840: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1843: pop
        //   1844: aload_2
        //   1845: astore_3
        //   1846: aload_2
        //   1847: astore #4
        //   1849: aload #5
        //   1851: aload_0
        //   1852: getfield ArrayGiai : [Ljava/lang/String;
        //   1855: bipush #13
        //   1857: aaload
        //   1858: invokevirtual trim : ()Ljava/lang/String;
        //   1861: bipush #12
        //   1863: bipush #16
        //   1865: invokevirtual substring : (II)Ljava/lang/String;
        //   1868: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1871: pop
        //   1872: aload_2
        //   1873: astore_3
        //   1874: aload_2
        //   1875: astore #4
        //   1877: aload #5
        //   1879: ldc_w '','
        //   1882: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1885: pop
        //   1886: aload_2
        //   1887: astore_3
        //   1888: aload_2
        //   1889: astore #4
        //   1891: aload #5
        //   1893: invokevirtual toString : ()Ljava/lang/String;
        //   1896: astore_2
        //   1897: aload_2
        //   1898: astore_3
        //   1899: aload_2
        //   1900: astore #4
        //   1902: new java/lang/StringBuilder
        //   1905: astore #5
        //   1907: aload_2
        //   1908: astore_3
        //   1909: aload_2
        //   1910: astore #4
        //   1912: aload #5
        //   1914: invokespecial <init> : ()V
        //   1917: aload_2
        //   1918: astore_3
        //   1919: aload_2
        //   1920: astore #4
        //   1922: aload #5
        //   1924: aload_2
        //   1925: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1928: pop
        //   1929: aload_2
        //   1930: astore_3
        //   1931: aload_2
        //   1932: astore #4
        //   1934: aload #5
        //   1936: ldc_w '''
        //   1939: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1942: pop
        //   1943: aload_2
        //   1944: astore_3
        //   1945: aload_2
        //   1946: astore #4
        //   1948: aload #5
        //   1950: aload_0
        //   1951: getfield ArrayGiai : [Ljava/lang/String;
        //   1954: bipush #13
        //   1956: aaload
        //   1957: invokevirtual trim : ()Ljava/lang/String;
        //   1960: bipush #16
        //   1962: bipush #20
        //   1964: invokevirtual substring : (II)Ljava/lang/String;
        //   1967: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1970: pop
        //   1971: aload_2
        //   1972: astore_3
        //   1973: aload_2
        //   1974: astore #4
        //   1976: aload #5
        //   1978: ldc_w '','
        //   1981: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1984: pop
        //   1985: aload_2
        //   1986: astore_3
        //   1987: aload_2
        //   1988: astore #4
        //   1990: aload #5
        //   1992: invokevirtual toString : ()Ljava/lang/String;
        //   1995: astore_2
        //   1996: aload_2
        //   1997: astore_3
        //   1998: aload_2
        //   1999: astore #4
        //   2001: new java/lang/StringBuilder
        //   2004: astore #5
        //   2006: aload_2
        //   2007: astore_3
        //   2008: aload_2
        //   2009: astore #4
        //   2011: aload #5
        //   2013: invokespecial <init> : ()V
        //   2016: aload_2
        //   2017: astore_3
        //   2018: aload_2
        //   2019: astore #4
        //   2021: aload #5
        //   2023: aload_2
        //   2024: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2027: pop
        //   2028: aload_2
        //   2029: astore_3
        //   2030: aload_2
        //   2031: astore #4
        //   2033: aload #5
        //   2035: ldc_w '''
        //   2038: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2041: pop
        //   2042: aload_2
        //   2043: astore_3
        //   2044: aload_2
        //   2045: astore #4
        //   2047: aload #5
        //   2049: aload_0
        //   2050: getfield ArrayGiai : [Ljava/lang/String;
        //   2053: bipush #13
        //   2055: aaload
        //   2056: invokevirtual trim : ()Ljava/lang/String;
        //   2059: bipush #20
        //   2061: aload_0
        //   2062: getfield ArrayGiai : [Ljava/lang/String;
        //   2065: bipush #13
        //   2067: aaload
        //   2068: invokevirtual trim : ()Ljava/lang/String;
        //   2071: invokevirtual length : ()I
        //   2074: invokevirtual substring : (II)Ljava/lang/String;
        //   2077: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2080: pop
        //   2081: aload_2
        //   2082: astore_3
        //   2083: aload_2
        //   2084: astore #4
        //   2086: aload #5
        //   2088: ldc_w '','
        //   2091: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2094: pop
        //   2095: aload_2
        //   2096: astore_3
        //   2097: aload_2
        //   2098: astore #4
        //   2100: aload #5
        //   2102: invokevirtual toString : ()Ljava/lang/String;
        //   2105: astore_2
        //   2106: aload_2
        //   2107: astore_3
        //   2108: aload_2
        //   2109: astore #4
        //   2111: aload_0
        //   2112: getfield ArrayGiai : [Ljava/lang/String;
        //   2115: bipush #15
        //   2117: aaload
        //   2118: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   2121: ifeq -> 3205
        //   2124: aload_2
        //   2125: astore_3
        //   2126: aload_2
        //   2127: astore #4
        //   2129: new java/lang/StringBuilder
        //   2132: astore #5
        //   2134: aload_2
        //   2135: astore_3
        //   2136: aload_2
        //   2137: astore #4
        //   2139: aload #5
        //   2141: invokespecial <init> : ()V
        //   2144: aload_2
        //   2145: astore_3
        //   2146: aload_2
        //   2147: astore #4
        //   2149: aload #5
        //   2151: aload_2
        //   2152: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2155: pop
        //   2156: aload_2
        //   2157: astore_3
        //   2158: aload_2
        //   2159: astore #4
        //   2161: aload #5
        //   2163: ldc_w '''
        //   2166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2169: pop
        //   2170: aload_2
        //   2171: astore_3
        //   2172: aload_2
        //   2173: astore #4
        //   2175: aload #5
        //   2177: aload_0
        //   2178: getfield ArrayGiai : [Ljava/lang/String;
        //   2181: bipush #15
        //   2183: aaload
        //   2184: invokevirtual trim : ()Ljava/lang/String;
        //   2187: iconst_0
        //   2188: iconst_3
        //   2189: invokevirtual substring : (II)Ljava/lang/String;
        //   2192: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2195: pop
        //   2196: aload_2
        //   2197: astore_3
        //   2198: aload_2
        //   2199: astore #4
        //   2201: aload #5
        //   2203: ldc_w '','
        //   2206: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2209: pop
        //   2210: aload_2
        //   2211: astore_3
        //   2212: aload_2
        //   2213: astore #4
        //   2215: aload #5
        //   2217: invokevirtual toString : ()Ljava/lang/String;
        //   2220: astore_2
        //   2221: aload_2
        //   2222: astore_3
        //   2223: aload_2
        //   2224: astore #4
        //   2226: new java/lang/StringBuilder
        //   2229: astore #5
        //   2231: aload_2
        //   2232: astore_3
        //   2233: aload_2
        //   2234: astore #4
        //   2236: aload #5
        //   2238: invokespecial <init> : ()V
        //   2241: aload_2
        //   2242: astore_3
        //   2243: aload_2
        //   2244: astore #4
        //   2246: aload #5
        //   2248: aload_2
        //   2249: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2252: pop
        //   2253: aload_2
        //   2254: astore_3
        //   2255: aload_2
        //   2256: astore #4
        //   2258: aload #5
        //   2260: ldc_w '''
        //   2263: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2266: pop
        //   2267: aload_2
        //   2268: astore_3
        //   2269: aload_2
        //   2270: astore #4
        //   2272: aload #5
        //   2274: aload_0
        //   2275: getfield ArrayGiai : [Ljava/lang/String;
        //   2278: bipush #15
        //   2280: aaload
        //   2281: invokevirtual trim : ()Ljava/lang/String;
        //   2284: iconst_3
        //   2285: bipush #6
        //   2287: invokevirtual substring : (II)Ljava/lang/String;
        //   2290: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2293: pop
        //   2294: aload_2
        //   2295: astore_3
        //   2296: aload_2
        //   2297: astore #4
        //   2299: aload #5
        //   2301: ldc_w '','
        //   2304: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2307: pop
        //   2308: aload_2
        //   2309: astore_3
        //   2310: aload_2
        //   2311: astore #4
        //   2313: aload #5
        //   2315: invokevirtual toString : ()Ljava/lang/String;
        //   2318: astore_2
        //   2319: aload_2
        //   2320: astore_3
        //   2321: aload_2
        //   2322: astore #4
        //   2324: new java/lang/StringBuilder
        //   2327: astore #5
        //   2329: aload_2
        //   2330: astore_3
        //   2331: aload_2
        //   2332: astore #4
        //   2334: aload #5
        //   2336: invokespecial <init> : ()V
        //   2339: aload_2
        //   2340: astore_3
        //   2341: aload_2
        //   2342: astore #4
        //   2344: aload #5
        //   2346: aload_2
        //   2347: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2350: pop
        //   2351: aload_2
        //   2352: astore_3
        //   2353: aload_2
        //   2354: astore #4
        //   2356: aload #5
        //   2358: ldc_w '''
        //   2361: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2364: pop
        //   2365: aload_2
        //   2366: astore_3
        //   2367: aload_2
        //   2368: astore #4
        //   2370: aload #5
        //   2372: aload_0
        //   2373: getfield ArrayGiai : [Ljava/lang/String;
        //   2376: bipush #15
        //   2378: aaload
        //   2379: invokevirtual trim : ()Ljava/lang/String;
        //   2382: bipush #6
        //   2384: aload_0
        //   2385: getfield ArrayGiai : [Ljava/lang/String;
        //   2388: bipush #15
        //   2390: aaload
        //   2391: invokevirtual trim : ()Ljava/lang/String;
        //   2394: invokevirtual length : ()I
        //   2397: invokevirtual substring : (II)Ljava/lang/String;
        //   2400: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2403: pop
        //   2404: aload_2
        //   2405: astore_3
        //   2406: aload_2
        //   2407: astore #4
        //   2409: aload #5
        //   2411: ldc_w '','
        //   2414: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2417: pop
        //   2418: aload_2
        //   2419: astore_3
        //   2420: aload_2
        //   2421: astore #4
        //   2423: aload #5
        //   2425: invokevirtual toString : ()Ljava/lang/String;
        //   2428: astore_2
        //   2429: aload_2
        //   2430: astore_3
        //   2431: aload_2
        //   2432: astore #4
        //   2434: aload_0
        //   2435: getfield ArrayGiai : [Ljava/lang/String;
        //   2438: bipush #17
        //   2440: aaload
        //   2441: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   2444: ifeq -> 3027
        //   2447: aload_2
        //   2448: astore_3
        //   2449: aload_2
        //   2450: astore #4
        //   2452: new java/lang/StringBuilder
        //   2455: astore #5
        //   2457: aload_2
        //   2458: astore_3
        //   2459: aload_2
        //   2460: astore #4
        //   2462: aload #5
        //   2464: invokespecial <init> : ()V
        //   2467: aload_2
        //   2468: astore_3
        //   2469: aload_2
        //   2470: astore #4
        //   2472: aload #5
        //   2474: aload_2
        //   2475: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2478: pop
        //   2479: aload_2
        //   2480: astore_3
        //   2481: aload_2
        //   2482: astore #4
        //   2484: aload #5
        //   2486: ldc_w '''
        //   2489: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2492: pop
        //   2493: aload_2
        //   2494: astore_3
        //   2495: aload_2
        //   2496: astore #4
        //   2498: aload #5
        //   2500: aload_0
        //   2501: getfield ArrayGiai : [Ljava/lang/String;
        //   2504: bipush #17
        //   2506: aaload
        //   2507: invokevirtual trim : ()Ljava/lang/String;
        //   2510: iconst_0
        //   2511: iconst_2
        //   2512: invokevirtual substring : (II)Ljava/lang/String;
        //   2515: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2518: pop
        //   2519: aload_2
        //   2520: astore_3
        //   2521: aload_2
        //   2522: astore #4
        //   2524: aload #5
        //   2526: ldc_w '','
        //   2529: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2532: pop
        //   2533: aload_2
        //   2534: astore_3
        //   2535: aload_2
        //   2536: astore #4
        //   2538: aload #5
        //   2540: invokevirtual toString : ()Ljava/lang/String;
        //   2543: astore_2
        //   2544: aload_2
        //   2545: astore_3
        //   2546: aload_2
        //   2547: astore #4
        //   2549: new java/lang/StringBuilder
        //   2552: astore #5
        //   2554: aload_2
        //   2555: astore_3
        //   2556: aload_2
        //   2557: astore #4
        //   2559: aload #5
        //   2561: invokespecial <init> : ()V
        //   2564: aload_2
        //   2565: astore_3
        //   2566: aload_2
        //   2567: astore #4
        //   2569: aload #5
        //   2571: aload_2
        //   2572: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2575: pop
        //   2576: aload_2
        //   2577: astore_3
        //   2578: aload_2
        //   2579: astore #4
        //   2581: aload #5
        //   2583: ldc_w '''
        //   2586: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2589: pop
        //   2590: aload_2
        //   2591: astore_3
        //   2592: aload_2
        //   2593: astore #4
        //   2595: aload #5
        //   2597: aload_0
        //   2598: getfield ArrayGiai : [Ljava/lang/String;
        //   2601: bipush #17
        //   2603: aaload
        //   2604: invokevirtual trim : ()Ljava/lang/String;
        //   2607: iconst_2
        //   2608: iconst_4
        //   2609: invokevirtual substring : (II)Ljava/lang/String;
        //   2612: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2615: pop
        //   2616: aload_2
        //   2617: astore_3
        //   2618: aload_2
        //   2619: astore #4
        //   2621: aload #5
        //   2623: ldc_w '','
        //   2626: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2629: pop
        //   2630: aload_2
        //   2631: astore_3
        //   2632: aload_2
        //   2633: astore #4
        //   2635: aload #5
        //   2637: invokevirtual toString : ()Ljava/lang/String;
        //   2640: astore_2
        //   2641: aload_2
        //   2642: astore_3
        //   2643: aload_2
        //   2644: astore #4
        //   2646: new java/lang/StringBuilder
        //   2649: astore #5
        //   2651: aload_2
        //   2652: astore_3
        //   2653: aload_2
        //   2654: astore #4
        //   2656: aload #5
        //   2658: invokespecial <init> : ()V
        //   2661: aload_2
        //   2662: astore_3
        //   2663: aload_2
        //   2664: astore #4
        //   2666: aload #5
        //   2668: aload_2
        //   2669: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2672: pop
        //   2673: aload_2
        //   2674: astore_3
        //   2675: aload_2
        //   2676: astore #4
        //   2678: aload #5
        //   2680: ldc_w '''
        //   2683: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2686: pop
        //   2687: aload_2
        //   2688: astore_3
        //   2689: aload_2
        //   2690: astore #4
        //   2692: aload #5
        //   2694: aload_0
        //   2695: getfield ArrayGiai : [Ljava/lang/String;
        //   2698: bipush #17
        //   2700: aaload
        //   2701: invokevirtual trim : ()Ljava/lang/String;
        //   2704: iconst_4
        //   2705: bipush #6
        //   2707: invokevirtual substring : (II)Ljava/lang/String;
        //   2710: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2713: pop
        //   2714: aload_2
        //   2715: astore_3
        //   2716: aload_2
        //   2717: astore #4
        //   2719: aload #5
        //   2721: ldc_w '','
        //   2724: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2727: pop
        //   2728: aload_2
        //   2729: astore_3
        //   2730: aload_2
        //   2731: astore #4
        //   2733: aload #5
        //   2735: invokevirtual toString : ()Ljava/lang/String;
        //   2738: astore_2
        //   2739: aload_2
        //   2740: astore_3
        //   2741: aload_2
        //   2742: astore #4
        //   2744: new java/lang/StringBuilder
        //   2747: astore #5
        //   2749: aload_2
        //   2750: astore_3
        //   2751: aload_2
        //   2752: astore #4
        //   2754: aload #5
        //   2756: invokespecial <init> : ()V
        //   2759: aload_2
        //   2760: astore_3
        //   2761: aload_2
        //   2762: astore #4
        //   2764: aload #5
        //   2766: aload_2
        //   2767: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2770: pop
        //   2771: aload_2
        //   2772: astore_3
        //   2773: aload_2
        //   2774: astore #4
        //   2776: aload #5
        //   2778: ldc_w '''
        //   2781: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2784: pop
        //   2785: aload_2
        //   2786: astore_3
        //   2787: aload_2
        //   2788: astore #4
        //   2790: aload #5
        //   2792: aload_0
        //   2793: getfield ArrayGiai : [Ljava/lang/String;
        //   2796: bipush #17
        //   2798: aaload
        //   2799: invokevirtual trim : ()Ljava/lang/String;
        //   2802: bipush #6
        //   2804: aload_0
        //   2805: getfield ArrayGiai : [Ljava/lang/String;
        //   2808: bipush #17
        //   2810: aaload
        //   2811: invokevirtual trim : ()Ljava/lang/String;
        //   2814: invokevirtual length : ()I
        //   2817: invokevirtual substring : (II)Ljava/lang/String;
        //   2820: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2823: pop
        //   2824: aload_2
        //   2825: astore_3
        //   2826: aload_2
        //   2827: astore #4
        //   2829: aload #5
        //   2831: ldc_w '')'
        //   2834: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2837: pop
        //   2838: aload_2
        //   2839: astore_3
        //   2840: aload_2
        //   2841: astore #4
        //   2843: aload #5
        //   2845: invokevirtual toString : ()Ljava/lang/String;
        //   2848: astore_2
        //   2849: aload_2
        //   2850: invokevirtual length : ()I
        //   2853: sipush #185
        //   2856: if_icmple -> 3010
        //   2859: aload_0
        //   2860: getfield db : Ltamhoang/ldpro4/data/Database;
        //   2863: astore_3
        //   2864: new java/lang/StringBuilder
        //   2867: dup
        //   2868: invokespecial <init> : ()V
        //   2871: astore #4
        //   2873: aload #4
        //   2875: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   2878: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2881: pop
        //   2882: aload #4
        //   2884: aload_1
        //   2885: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2888: pop
        //   2889: aload #4
        //   2891: ldc_w '''
        //   2894: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2897: pop
        //   2898: aload_3
        //   2899: aload #4
        //   2901: invokevirtual toString : ()Ljava/lang/String;
        //   2904: invokevirtual QueryData : (Ljava/lang/String;)V
        //   2907: new java/lang/StringBuilder
        //   2910: dup
        //   2911: invokespecial <init> : ()V
        //   2914: astore #4
        //   2916: aload #4
        //   2918: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   2921: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2924: pop
        //   2925: aload #4
        //   2927: aload_1
        //   2928: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2931: pop
        //   2932: aload #4
        //   2934: ldc_w '','
        //   2937: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2940: pop
        //   2941: aload #4
        //   2943: aload_2
        //   2944: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2947: pop
        //   2948: aload #4
        //   2950: invokevirtual toString : ()Ljava/lang/String;
        //   2953: astore #4
        //   2955: aload_0
        //   2956: getfield db : Ltamhoang/ldpro4/data/Database;
        //   2959: aload #4
        //   2961: invokevirtual QueryData : (Ljava/lang/String;)V
        //   2964: aload_0
        //   2965: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   2968: astore #4
        //   2970: new java/lang/StringBuilder
        //   2973: dup
        //   2974: invokespecial <init> : ()V
        //   2977: astore_3
        //   2978: aload_3
        //   2979: ldc_w 'txong kqung'
        //   2982: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2985: pop
        //   2986: aload_3
        //   2987: invokestatic Get_ngay : ()Ljava/lang/String;
        //   2990: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2993: pop
        //   2994: aload #4
        //   2996: aload_3
        //   2997: invokevirtual toString : ()Ljava/lang/String;
        //   3000: iconst_1
        //   3001: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3004: invokevirtual show : ()V
        //   3007: goto -> 4578
        //   3010: aload_0
        //   3011: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3014: ldc_w 'Khckquphh
        //   3017: iconst_1
        //   3018: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3021: invokevirtual show : ()V
        //   3024: goto -> 4578
        //   3027: aload_2
        //   3028: invokevirtual length : ()I
        //   3031: sipush #185
        //   3034: if_icmple -> 3190
        //   3037: aload_0
        //   3038: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3041: astore_3
        //   3042: new java/lang/StringBuilder
        //   3045: dup
        //   3046: invokespecial <init> : ()V
        //   3049: astore #4
        //   3051: aload #4
        //   3053: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   3056: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3059: pop
        //   3060: aload #4
        //   3062: aload_1
        //   3063: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3066: pop
        //   3067: aload #4
        //   3069: ldc_w '''
        //   3072: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3075: pop
        //   3076: aload_3
        //   3077: aload #4
        //   3079: invokevirtual toString : ()Ljava/lang/String;
        //   3082: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3085: new java/lang/StringBuilder
        //   3088: dup
        //   3089: invokespecial <init> : ()V
        //   3092: astore #4
        //   3094: aload #4
        //   3096: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   3099: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3102: pop
        //   3103: aload #4
        //   3105: aload_1
        //   3106: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3109: pop
        //   3110: aload #4
        //   3112: ldc_w '','
        //   3115: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3118: pop
        //   3119: aload #4
        //   3121: aload_2
        //   3122: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3125: pop
        //   3126: aload #4
        //   3128: invokevirtual toString : ()Ljava/lang/String;
        //   3131: astore #4
        //   3133: aload_0
        //   3134: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3137: aload #4
        //   3139: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3142: aload_0
        //   3143: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3146: astore_3
        //   3147: new java/lang/StringBuilder
        //   3150: dup
        //   3151: invokespecial <init> : ()V
        //   3154: astore #4
        //   3156: aload #4
        //   3158: ldc_w 'txong kqung'
        //   3161: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3164: pop
        //   3165: aload #4
        //   3167: invokestatic Get_ngay : ()Ljava/lang/String;
        //   3170: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3173: pop
        //   3174: aload_3
        //   3175: aload #4
        //   3177: invokevirtual toString : ()Ljava/lang/String;
        //   3180: iconst_1
        //   3181: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3184: invokevirtual show : ()V
        //   3187: goto -> 3204
        //   3190: aload_0
        //   3191: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3194: ldc_w 'Khckquphh
        //   3197: iconst_1
        //   3198: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3201: invokevirtual show : ()V
        //   3204: return
        //   3205: aload_2
        //   3206: invokevirtual length : ()I
        //   3209: sipush #185
        //   3212: if_icmple -> 3365
        //   3215: aload_0
        //   3216: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3219: astore #4
        //   3221: new java/lang/StringBuilder
        //   3224: dup
        //   3225: invokespecial <init> : ()V
        //   3228: astore_3
        //   3229: aload_3
        //   3230: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   3233: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3236: pop
        //   3237: aload_3
        //   3238: aload_1
        //   3239: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3242: pop
        //   3243: aload_3
        //   3244: ldc_w '''
        //   3247: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3250: pop
        //   3251: aload #4
        //   3253: aload_3
        //   3254: invokevirtual toString : ()Ljava/lang/String;
        //   3257: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3260: new java/lang/StringBuilder
        //   3263: dup
        //   3264: invokespecial <init> : ()V
        //   3267: astore #4
        //   3269: aload #4
        //   3271: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   3274: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3277: pop
        //   3278: aload #4
        //   3280: aload_1
        //   3281: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3284: pop
        //   3285: aload #4
        //   3287: ldc_w '','
        //   3290: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3293: pop
        //   3294: aload #4
        //   3296: aload_2
        //   3297: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3300: pop
        //   3301: aload #4
        //   3303: invokevirtual toString : ()Ljava/lang/String;
        //   3306: astore #4
        //   3308: aload_0
        //   3309: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3312: aload #4
        //   3314: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3317: aload_0
        //   3318: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3321: astore_3
        //   3322: new java/lang/StringBuilder
        //   3325: dup
        //   3326: invokespecial <init> : ()V
        //   3329: astore #4
        //   3331: aload #4
        //   3333: ldc_w 'txong kqung'
        //   3336: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3339: pop
        //   3340: aload #4
        //   3342: invokestatic Get_ngay : ()Ljava/lang/String;
        //   3345: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3348: pop
        //   3349: aload_3
        //   3350: aload #4
        //   3352: invokevirtual toString : ()Ljava/lang/String;
        //   3355: iconst_1
        //   3356: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3359: invokevirtual show : ()V
        //   3362: goto -> 3379
        //   3365: aload_0
        //   3366: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3369: ldc_w 'Khckquphh
        //   3372: iconst_1
        //   3373: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3376: invokevirtual show : ()V
        //   3379: return
        //   3380: aload_2
        //   3381: invokevirtual length : ()I
        //   3384: sipush #185
        //   3387: if_icmple -> 3543
        //   3390: aload_0
        //   3391: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3394: astore_3
        //   3395: new java/lang/StringBuilder
        //   3398: dup
        //   3399: invokespecial <init> : ()V
        //   3402: astore #4
        //   3404: aload #4
        //   3406: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   3409: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3412: pop
        //   3413: aload #4
        //   3415: aload_1
        //   3416: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3419: pop
        //   3420: aload #4
        //   3422: ldc_w '''
        //   3425: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3428: pop
        //   3429: aload_3
        //   3430: aload #4
        //   3432: invokevirtual toString : ()Ljava/lang/String;
        //   3435: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3438: new java/lang/StringBuilder
        //   3441: dup
        //   3442: invokespecial <init> : ()V
        //   3445: astore #4
        //   3447: aload #4
        //   3449: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   3452: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3455: pop
        //   3456: aload #4
        //   3458: aload_1
        //   3459: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3462: pop
        //   3463: aload #4
        //   3465: ldc_w '','
        //   3468: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3471: pop
        //   3472: aload #4
        //   3474: aload_2
        //   3475: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3478: pop
        //   3479: aload #4
        //   3481: invokevirtual toString : ()Ljava/lang/String;
        //   3484: astore #4
        //   3486: aload_0
        //   3487: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3490: aload #4
        //   3492: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3495: aload_0
        //   3496: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3499: astore_3
        //   3500: new java/lang/StringBuilder
        //   3503: dup
        //   3504: invokespecial <init> : ()V
        //   3507: astore #4
        //   3509: aload #4
        //   3511: ldc_w 'txong kqung'
        //   3514: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3517: pop
        //   3518: aload #4
        //   3520: invokestatic Get_ngay : ()Ljava/lang/String;
        //   3523: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3526: pop
        //   3527: aload_3
        //   3528: aload #4
        //   3530: invokevirtual toString : ()Ljava/lang/String;
        //   3533: iconst_1
        //   3534: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3537: invokevirtual show : ()V
        //   3540: goto -> 3557
        //   3543: aload_0
        //   3544: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3547: ldc_w 'Khckquphh
        //   3550: iconst_1
        //   3551: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3554: invokevirtual show : ()V
        //   3557: return
        //   3558: aload_2
        //   3559: invokevirtual length : ()I
        //   3562: sipush #185
        //   3565: if_icmple -> 3716
        //   3568: aload_0
        //   3569: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3572: astore #4
        //   3574: new java/lang/StringBuilder
        //   3577: dup
        //   3578: invokespecial <init> : ()V
        //   3581: astore_3
        //   3582: aload_3
        //   3583: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   3586: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3589: pop
        //   3590: aload_3
        //   3591: aload_1
        //   3592: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3595: pop
        //   3596: aload_3
        //   3597: ldc_w '''
        //   3600: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3603: pop
        //   3604: aload #4
        //   3606: aload_3
        //   3607: invokevirtual toString : ()Ljava/lang/String;
        //   3610: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3613: new java/lang/StringBuilder
        //   3616: dup
        //   3617: invokespecial <init> : ()V
        //   3620: astore #4
        //   3622: aload #4
        //   3624: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   3627: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3630: pop
        //   3631: aload #4
        //   3633: aload_1
        //   3634: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3637: pop
        //   3638: aload #4
        //   3640: ldc_w '','
        //   3643: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3646: pop
        //   3647: aload #4
        //   3649: aload_2
        //   3650: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3653: pop
        //   3654: aload #4
        //   3656: invokevirtual toString : ()Ljava/lang/String;
        //   3659: astore #4
        //   3661: aload_0
        //   3662: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3665: aload #4
        //   3667: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3670: aload_0
        //   3671: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3674: astore #4
        //   3676: new java/lang/StringBuilder
        //   3679: dup
        //   3680: invokespecial <init> : ()V
        //   3683: astore_3
        //   3684: aload_3
        //   3685: ldc_w 'txong kqung'
        //   3688: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3691: pop
        //   3692: aload_3
        //   3693: invokestatic Get_ngay : ()Ljava/lang/String;
        //   3696: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3699: pop
        //   3700: aload #4
        //   3702: aload_3
        //   3703: invokevirtual toString : ()Ljava/lang/String;
        //   3706: iconst_1
        //   3707: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3710: invokevirtual show : ()V
        //   3713: goto -> 3730
        //   3716: aload_0
        //   3717: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3720: ldc_w 'Khckquphh
        //   3723: iconst_1
        //   3724: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3727: invokevirtual show : ()V
        //   3730: return
        //   3731: aload_2
        //   3732: invokevirtual length : ()I
        //   3735: sipush #185
        //   3738: if_icmple -> 3894
        //   3741: aload_0
        //   3742: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3745: astore_3
        //   3746: new java/lang/StringBuilder
        //   3749: dup
        //   3750: invokespecial <init> : ()V
        //   3753: astore #4
        //   3755: aload #4
        //   3757: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   3760: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3763: pop
        //   3764: aload #4
        //   3766: aload_1
        //   3767: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3770: pop
        //   3771: aload #4
        //   3773: ldc_w '''
        //   3776: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3779: pop
        //   3780: aload_3
        //   3781: aload #4
        //   3783: invokevirtual toString : ()Ljava/lang/String;
        //   3786: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3789: new java/lang/StringBuilder
        //   3792: dup
        //   3793: invokespecial <init> : ()V
        //   3796: astore #4
        //   3798: aload #4
        //   3800: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   3803: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3806: pop
        //   3807: aload #4
        //   3809: aload_1
        //   3810: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3813: pop
        //   3814: aload #4
        //   3816: ldc_w '','
        //   3819: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3822: pop
        //   3823: aload #4
        //   3825: aload_2
        //   3826: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3829: pop
        //   3830: aload #4
        //   3832: invokevirtual toString : ()Ljava/lang/String;
        //   3835: astore #4
        //   3837: aload_0
        //   3838: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3841: aload #4
        //   3843: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3846: aload_0
        //   3847: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3850: astore_3
        //   3851: new java/lang/StringBuilder
        //   3854: dup
        //   3855: invokespecial <init> : ()V
        //   3858: astore #4
        //   3860: aload #4
        //   3862: ldc_w 'txong kqung'
        //   3865: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3868: pop
        //   3869: aload #4
        //   3871: invokestatic Get_ngay : ()Ljava/lang/String;
        //   3874: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3877: pop
        //   3878: aload_3
        //   3879: aload #4
        //   3881: invokevirtual toString : ()Ljava/lang/String;
        //   3884: iconst_1
        //   3885: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3888: invokevirtual show : ()V
        //   3891: goto -> 3908
        //   3894: aload_0
        //   3895: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   3898: ldc_w 'Khckquphh
        //   3901: iconst_1
        //   3902: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   3905: invokevirtual show : ()V
        //   3908: return
        //   3909: aload_2
        //   3910: invokevirtual length : ()I
        //   3913: sipush #185
        //   3916: if_icmple -> 4067
        //   3919: aload_0
        //   3920: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3923: astore #4
        //   3925: new java/lang/StringBuilder
        //   3928: dup
        //   3929: invokespecial <init> : ()V
        //   3932: astore_3
        //   3933: aload_3
        //   3934: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   3937: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3940: pop
        //   3941: aload_3
        //   3942: aload_1
        //   3943: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3946: pop
        //   3947: aload_3
        //   3948: ldc_w '''
        //   3951: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3954: pop
        //   3955: aload #4
        //   3957: aload_3
        //   3958: invokevirtual toString : ()Ljava/lang/String;
        //   3961: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3964: new java/lang/StringBuilder
        //   3967: dup
        //   3968: invokespecial <init> : ()V
        //   3971: astore #4
        //   3973: aload #4
        //   3975: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   3978: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3981: pop
        //   3982: aload #4
        //   3984: aload_1
        //   3985: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3988: pop
        //   3989: aload #4
        //   3991: ldc_w '','
        //   3994: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3997: pop
        //   3998: aload #4
        //   4000: aload_2
        //   4001: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4004: pop
        //   4005: aload #4
        //   4007: invokevirtual toString : ()Ljava/lang/String;
        //   4010: astore #4
        //   4012: aload_0
        //   4013: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4016: aload #4
        //   4018: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4021: aload_0
        //   4022: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4025: astore #4
        //   4027: new java/lang/StringBuilder
        //   4030: dup
        //   4031: invokespecial <init> : ()V
        //   4034: astore_3
        //   4035: aload_3
        //   4036: ldc_w 'txong kqung'
        //   4039: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4042: pop
        //   4043: aload_3
        //   4044: invokestatic Get_ngay : ()Ljava/lang/String;
        //   4047: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4050: pop
        //   4051: aload #4
        //   4053: aload_3
        //   4054: invokevirtual toString : ()Ljava/lang/String;
        //   4057: iconst_1
        //   4058: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4061: invokevirtual show : ()V
        //   4064: goto -> 4081
        //   4067: aload_0
        //   4068: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4071: ldc_w 'Khckquphh
        //   4074: iconst_1
        //   4075: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4078: invokevirtual show : ()V
        //   4081: return
        //   4082: aload_2
        //   4083: invokevirtual length : ()I
        //   4086: sipush #185
        //   4089: if_icmple -> 4240
        //   4092: aload_0
        //   4093: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4096: astore #4
        //   4098: new java/lang/StringBuilder
        //   4101: dup
        //   4102: invokespecial <init> : ()V
        //   4105: astore_3
        //   4106: aload_3
        //   4107: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   4110: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4113: pop
        //   4114: aload_3
        //   4115: aload_1
        //   4116: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4119: pop
        //   4120: aload_3
        //   4121: ldc_w '''
        //   4124: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4127: pop
        //   4128: aload #4
        //   4130: aload_3
        //   4131: invokevirtual toString : ()Ljava/lang/String;
        //   4134: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4137: new java/lang/StringBuilder
        //   4140: dup
        //   4141: invokespecial <init> : ()V
        //   4144: astore #4
        //   4146: aload #4
        //   4148: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   4151: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4154: pop
        //   4155: aload #4
        //   4157: aload_1
        //   4158: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4161: pop
        //   4162: aload #4
        //   4164: ldc_w '','
        //   4167: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4170: pop
        //   4171: aload #4
        //   4173: aload_2
        //   4174: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4177: pop
        //   4178: aload #4
        //   4180: invokevirtual toString : ()Ljava/lang/String;
        //   4183: astore #4
        //   4185: aload_0
        //   4186: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4189: aload #4
        //   4191: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4194: aload_0
        //   4195: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4198: astore #4
        //   4200: new java/lang/StringBuilder
        //   4203: dup
        //   4204: invokespecial <init> : ()V
        //   4207: astore_3
        //   4208: aload_3
        //   4209: ldc_w 'txong kqung'
        //   4212: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4215: pop
        //   4216: aload_3
        //   4217: invokestatic Get_ngay : ()Ljava/lang/String;
        //   4220: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4223: pop
        //   4224: aload #4
        //   4226: aload_3
        //   4227: invokevirtual toString : ()Ljava/lang/String;
        //   4230: iconst_1
        //   4231: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4234: invokevirtual show : ()V
        //   4237: goto -> 4254
        //   4240: aload_0
        //   4241: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4244: ldc_w 'Khckquphh
        //   4247: iconst_1
        //   4248: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4251: invokevirtual show : ()V
        //   4254: return
        //   4255: ldc_w ''
        //   4258: invokevirtual length : ()I
        //   4261: sipush #185
        //   4264: if_icmple -> 4417
        //   4267: aload_0
        //   4268: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4271: astore #4
        //   4273: new java/lang/StringBuilder
        //   4276: dup
        //   4277: invokespecial <init> : ()V
        //   4280: astore_3
        //   4281: aload_3
        //   4282: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   4285: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4288: pop
        //   4289: aload_3
        //   4290: aload_1
        //   4291: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4294: pop
        //   4295: aload_3
        //   4296: ldc_w '''
        //   4299: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4302: pop
        //   4303: aload #4
        //   4305: aload_3
        //   4306: invokevirtual toString : ()Ljava/lang/String;
        //   4309: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4312: new java/lang/StringBuilder
        //   4315: dup
        //   4316: invokespecial <init> : ()V
        //   4319: astore #4
        //   4321: aload #4
        //   4323: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   4326: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4329: pop
        //   4330: aload #4
        //   4332: aload_1
        //   4333: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4336: pop
        //   4337: aload #4
        //   4339: ldc_w '','
        //   4342: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4345: pop
        //   4346: aload #4
        //   4348: ldc_w ''
        //   4351: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4354: pop
        //   4355: aload #4
        //   4357: invokevirtual toString : ()Ljava/lang/String;
        //   4360: astore #4
        //   4362: aload_0
        //   4363: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4366: aload #4
        //   4368: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4371: aload_0
        //   4372: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4375: astore #4
        //   4377: new java/lang/StringBuilder
        //   4380: dup
        //   4381: invokespecial <init> : ()V
        //   4384: astore_3
        //   4385: aload_3
        //   4386: ldc_w 'txong kqung'
        //   4389: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4392: pop
        //   4393: aload_3
        //   4394: invokestatic Get_ngay : ()Ljava/lang/String;
        //   4397: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4400: pop
        //   4401: aload #4
        //   4403: aload_3
        //   4404: invokevirtual toString : ()Ljava/lang/String;
        //   4407: iconst_1
        //   4408: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4411: invokevirtual show : ()V
        //   4414: goto -> 4431
        //   4417: aload_0
        //   4418: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4421: ldc_w 'Khckquphh
        //   4424: iconst_1
        //   4425: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4428: invokevirtual show : ()V
        //   4431: return
        //   4432: astore #4
        //   4434: goto -> 4579
        //   4437: astore_3
        //   4438: aload #4
        //   4440: astore_3
        //   4441: aload_0
        //   4442: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4445: ldc_w 'Khckquphh
        //   4448: iconst_1
        //   4449: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4452: invokevirtual show : ()V
        //   4455: aload #4
        //   4457: invokevirtual length : ()I
        //   4460: sipush #185
        //   4463: if_icmple -> 3010
        //   4466: aload_0
        //   4467: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4470: astore_2
        //   4471: new java/lang/StringBuilder
        //   4474: dup
        //   4475: invokespecial <init> : ()V
        //   4478: astore_3
        //   4479: aload_3
        //   4480: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   4483: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4486: pop
        //   4487: aload_3
        //   4488: aload_1
        //   4489: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4492: pop
        //   4493: aload_3
        //   4494: ldc_w '''
        //   4497: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4500: pop
        //   4501: aload_2
        //   4502: aload_3
        //   4503: invokevirtual toString : ()Ljava/lang/String;
        //   4506: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4509: new java/lang/StringBuilder
        //   4512: dup
        //   4513: invokespecial <init> : ()V
        //   4516: astore_3
        //   4517: aload_3
        //   4518: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   4521: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4524: pop
        //   4525: aload_3
        //   4526: aload_1
        //   4527: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4530: pop
        //   4531: aload_3
        //   4532: ldc_w '','
        //   4535: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4538: pop
        //   4539: aload_3
        //   4540: aload #4
        //   4542: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4545: pop
        //   4546: aload_3
        //   4547: invokevirtual toString : ()Ljava/lang/String;
        //   4550: astore #4
        //   4552: aload_0
        //   4553: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4556: aload #4
        //   4558: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4561: aload_0
        //   4562: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4565: astore #4
        //   4567: new java/lang/StringBuilder
        //   4570: dup
        //   4571: invokespecial <init> : ()V
        //   4574: astore_3
        //   4575: goto -> 2978
        //   4578: return
        //   4579: aload_3
        //   4580: invokevirtual length : ()I
        //   4583: sipush #185
        //   4586: if_icmple -> 4727
        //   4589: aload_0
        //   4590: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4593: astore #5
        //   4595: new java/lang/StringBuilder
        //   4598: dup
        //   4599: invokespecial <init> : ()V
        //   4602: astore_2
        //   4603: aload_2
        //   4604: ldc_w 'Delete From ketqua WHERE ngay = ''
        //   4607: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4610: pop
        //   4611: aload_2
        //   4612: aload_1
        //   4613: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4616: pop
        //   4617: aload_2
        //   4618: ldc_w '''
        //   4621: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4624: pop
        //   4625: aload #5
        //   4627: aload_2
        //   4628: invokevirtual toString : ()Ljava/lang/String;
        //   4631: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4634: new java/lang/StringBuilder
        //   4637: dup
        //   4638: invokespecial <init> : ()V
        //   4641: astore_2
        //   4642: aload_2
        //   4643: ldc_w 'InSert Into KETQUA VALUES(null,''
        //   4646: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4649: pop
        //   4650: aload_2
        //   4651: aload_1
        //   4652: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4655: pop
        //   4656: aload_2
        //   4657: ldc_w '','
        //   4660: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4663: pop
        //   4664: aload_2
        //   4665: aload_3
        //   4666: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4669: pop
        //   4670: aload_2
        //   4671: invokevirtual toString : ()Ljava/lang/String;
        //   4674: astore_3
        //   4675: aload_0
        //   4676: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4679: aload_3
        //   4680: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4683: aload_0
        //   4684: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4687: astore_2
        //   4688: new java/lang/StringBuilder
        //   4691: dup
        //   4692: invokespecial <init> : ()V
        //   4695: astore_3
        //   4696: aload_3
        //   4697: ldc_w 'txong kqung'
        //   4700: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4703: pop
        //   4704: aload_3
        //   4705: invokestatic Get_ngay : ()Ljava/lang/String;
        //   4708: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4711: pop
        //   4712: aload_2
        //   4713: aload_3
        //   4714: invokevirtual toString : ()Ljava/lang/String;
        //   4717: iconst_1
        //   4718: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4721: invokevirtual show : ()V
        //   4724: goto -> 4741
        //   4727: aload_0
        //   4728: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   4731: ldc_w 'Khckquphh
        //   4734: iconst_1
        //   4735: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   4738: invokevirtual show : ()V
        //   4741: goto -> 4747
        //   4744: aload #4
        //   4746: athrow
        //   4747: goto -> 4744
        // Exception table:
        //   from	to	target	type
        //   21	33	4437	java/lang/Exception
        //   21	33	4432	finally
        //   38	43	4437	java/lang/Exception
        //   38	43	4432	finally
        //   48	53	4437	java/lang/Exception
        //   48	53	4432	finally
        //   58	67	4437	java/lang/Exception
        //   58	67	4432	finally
        //   72	87	4437	java/lang/Exception
        //   72	87	4432	finally
        //   92	101	4437	java/lang/Exception
        //   92	101	4432	finally
        //   106	112	4437	java/lang/Exception
        //   106	112	4432	finally
        //   117	129	4437	java/lang/Exception
        //   117	129	4432	finally
        //   134	139	4437	java/lang/Exception
        //   134	139	4432	finally
        //   144	149	4437	java/lang/Exception
        //   144	149	4432	finally
        //   154	161	4437	java/lang/Exception
        //   154	161	4432	finally
        //   166	175	4437	java/lang/Exception
        //   166	175	4432	finally
        //   180	195	4437	java/lang/Exception
        //   180	195	4432	finally
        //   200	209	4437	java/lang/Exception
        //   200	209	4432	finally
        //   214	220	4437	java/lang/Exception
        //   214	220	4432	finally
        //   225	238	4437	java/lang/Exception
        //   225	238	4432	finally
        //   243	248	4437	java/lang/Exception
        //   243	248	4432	finally
        //   253	258	4437	java/lang/Exception
        //   253	258	4432	finally
        //   263	270	4437	java/lang/Exception
        //   263	270	4432	finally
        //   275	284	4437	java/lang/Exception
        //   275	284	4432	finally
        //   289	310	4437	java/lang/Exception
        //   289	310	4432	finally
        //   315	324	4437	java/lang/Exception
        //   315	324	4432	finally
        //   329	335	4437	java/lang/Exception
        //   329	335	4432	finally
        //   340	345	4437	java/lang/Exception
        //   340	345	4432	finally
        //   350	355	4437	java/lang/Exception
        //   350	355	4432	finally
        //   360	367	4437	java/lang/Exception
        //   360	367	4432	finally
        //   372	381	4437	java/lang/Exception
        //   372	381	4432	finally
        //   386	419	4437	java/lang/Exception
        //   386	419	4432	finally
        //   424	433	4437	java/lang/Exception
        //   424	433	4432	finally
        //   438	444	4437	java/lang/Exception
        //   438	444	4432	finally
        //   449	462	4437	java/lang/Exception
        //   449	462	4432	finally
        //   467	472	4437	java/lang/Exception
        //   467	472	4432	finally
        //   477	482	4437	java/lang/Exception
        //   477	482	4432	finally
        //   487	494	4437	java/lang/Exception
        //   487	494	4432	finally
        //   499	508	4437	java/lang/Exception
        //   499	508	4432	finally
        //   513	534	4437	java/lang/Exception
        //   513	534	4432	finally
        //   539	548	4437	java/lang/Exception
        //   539	548	4432	finally
        //   553	559	4437	java/lang/Exception
        //   553	559	4432	finally
        //   564	569	4437	java/lang/Exception
        //   564	569	4432	finally
        //   574	579	4437	java/lang/Exception
        //   574	579	4432	finally
        //   584	591	4437	java/lang/Exception
        //   584	591	4432	finally
        //   596	605	4437	java/lang/Exception
        //   596	605	4432	finally
        //   610	632	4437	java/lang/Exception
        //   610	632	4432	finally
        //   637	646	4437	java/lang/Exception
        //   637	646	4432	finally
        //   651	657	4437	java/lang/Exception
        //   651	657	4432	finally
        //   662	667	4437	java/lang/Exception
        //   662	667	4432	finally
        //   672	677	4437	java/lang/Exception
        //   672	677	4432	finally
        //   682	689	4437	java/lang/Exception
        //   682	689	4432	finally
        //   694	703	4437	java/lang/Exception
        //   694	703	4432	finally
        //   708	731	4437	java/lang/Exception
        //   708	731	4432	finally
        //   736	745	4437	java/lang/Exception
        //   736	745	4432	finally
        //   750	756	4437	java/lang/Exception
        //   750	756	4432	finally
        //   761	766	4437	java/lang/Exception
        //   761	766	4432	finally
        //   771	776	4437	java/lang/Exception
        //   771	776	4432	finally
        //   781	788	4437	java/lang/Exception
        //   781	788	4432	finally
        //   793	802	4437	java/lang/Exception
        //   793	802	4432	finally
        //   807	830	4437	java/lang/Exception
        //   807	830	4432	finally
        //   835	844	4437	java/lang/Exception
        //   835	844	4432	finally
        //   849	855	4437	java/lang/Exception
        //   849	855	4432	finally
        //   860	865	4437	java/lang/Exception
        //   860	865	4432	finally
        //   870	875	4437	java/lang/Exception
        //   870	875	4432	finally
        //   880	887	4437	java/lang/Exception
        //   880	887	4432	finally
        //   892	901	4437	java/lang/Exception
        //   892	901	4432	finally
        //   906	929	4437	java/lang/Exception
        //   906	929	4432	finally
        //   934	943	4437	java/lang/Exception
        //   934	943	4432	finally
        //   948	954	4437	java/lang/Exception
        //   948	954	4432	finally
        //   959	964	4437	java/lang/Exception
        //   959	964	4432	finally
        //   969	974	4437	java/lang/Exception
        //   969	974	4432	finally
        //   979	986	4437	java/lang/Exception
        //   979	986	4432	finally
        //   991	1000	4437	java/lang/Exception
        //   991	1000	4432	finally
        //   1005	1039	4437	java/lang/Exception
        //   1005	1039	4432	finally
        //   1044	1053	4437	java/lang/Exception
        //   1044	1053	4432	finally
        //   1058	1064	4437	java/lang/Exception
        //   1058	1064	4432	finally
        //   1069	1082	4437	java/lang/Exception
        //   1069	1082	4432	finally
        //   1087	1092	4437	java/lang/Exception
        //   1087	1092	4432	finally
        //   1097	1102	4437	java/lang/Exception
        //   1097	1102	4432	finally
        //   1107	1114	4437	java/lang/Exception
        //   1107	1114	4432	finally
        //   1119	1128	4437	java/lang/Exception
        //   1119	1128	4432	finally
        //   1133	1154	4437	java/lang/Exception
        //   1133	1154	4432	finally
        //   1159	1168	4437	java/lang/Exception
        //   1159	1168	4432	finally
        //   1173	1179	4437	java/lang/Exception
        //   1173	1179	4432	finally
        //   1184	1189	4437	java/lang/Exception
        //   1184	1189	4432	finally
        //   1194	1199	4437	java/lang/Exception
        //   1194	1199	4432	finally
        //   1204	1211	4437	java/lang/Exception
        //   1204	1211	4432	finally
        //   1216	1225	4437	java/lang/Exception
        //   1216	1225	4432	finally
        //   1230	1252	4437	java/lang/Exception
        //   1230	1252	4432	finally
        //   1257	1266	4437	java/lang/Exception
        //   1257	1266	4432	finally
        //   1271	1277	4437	java/lang/Exception
        //   1271	1277	4432	finally
        //   1282	1287	4437	java/lang/Exception
        //   1282	1287	4432	finally
        //   1292	1297	4437	java/lang/Exception
        //   1292	1297	4432	finally
        //   1302	1309	4437	java/lang/Exception
        //   1302	1309	4432	finally
        //   1314	1323	4437	java/lang/Exception
        //   1314	1323	4432	finally
        //   1328	1351	4437	java/lang/Exception
        //   1328	1351	4432	finally
        //   1356	1365	4437	java/lang/Exception
        //   1356	1365	4432	finally
        //   1370	1376	4437	java/lang/Exception
        //   1370	1376	4432	finally
        //   1381	1386	4437	java/lang/Exception
        //   1381	1386	4432	finally
        //   1391	1396	4437	java/lang/Exception
        //   1391	1396	4432	finally
        //   1401	1408	4437	java/lang/Exception
        //   1401	1408	4432	finally
        //   1413	1422	4437	java/lang/Exception
        //   1413	1422	4432	finally
        //   1427	1461	4437	java/lang/Exception
        //   1427	1461	4432	finally
        //   1466	1475	4437	java/lang/Exception
        //   1466	1475	4432	finally
        //   1480	1486	4437	java/lang/Exception
        //   1480	1486	4432	finally
        //   1491	1504	4437	java/lang/Exception
        //   1491	1504	4432	finally
        //   1509	1514	4437	java/lang/Exception
        //   1509	1514	4432	finally
        //   1519	1524	4437	java/lang/Exception
        //   1519	1524	4432	finally
        //   1529	1536	4437	java/lang/Exception
        //   1529	1536	4432	finally
        //   1541	1550	4437	java/lang/Exception
        //   1541	1550	4432	finally
        //   1555	1576	4437	java/lang/Exception
        //   1555	1576	4432	finally
        //   1581	1590	4437	java/lang/Exception
        //   1581	1590	4432	finally
        //   1595	1601	4437	java/lang/Exception
        //   1595	1601	4432	finally
        //   1606	1611	4437	java/lang/Exception
        //   1606	1611	4432	finally
        //   1616	1621	4437	java/lang/Exception
        //   1616	1621	4432	finally
        //   1626	1633	4437	java/lang/Exception
        //   1626	1633	4432	finally
        //   1638	1647	4437	java/lang/Exception
        //   1638	1647	4432	finally
        //   1652	1674	4437	java/lang/Exception
        //   1652	1674	4432	finally
        //   1679	1688	4437	java/lang/Exception
        //   1679	1688	4432	finally
        //   1693	1699	4437	java/lang/Exception
        //   1693	1699	4432	finally
        //   1704	1709	4437	java/lang/Exception
        //   1704	1709	4432	finally
        //   1714	1719	4437	java/lang/Exception
        //   1714	1719	4432	finally
        //   1724	1731	4437	java/lang/Exception
        //   1724	1731	4432	finally
        //   1736	1745	4437	java/lang/Exception
        //   1736	1745	4432	finally
        //   1750	1773	4437	java/lang/Exception
        //   1750	1773	4432	finally
        //   1778	1787	4437	java/lang/Exception
        //   1778	1787	4432	finally
        //   1792	1798	4437	java/lang/Exception
        //   1792	1798	4432	finally
        //   1803	1808	4437	java/lang/Exception
        //   1803	1808	4432	finally
        //   1813	1818	4437	java/lang/Exception
        //   1813	1818	4432	finally
        //   1823	1830	4437	java/lang/Exception
        //   1823	1830	4432	finally
        //   1835	1844	4437	java/lang/Exception
        //   1835	1844	4432	finally
        //   1849	1872	4437	java/lang/Exception
        //   1849	1872	4432	finally
        //   1877	1886	4437	java/lang/Exception
        //   1877	1886	4432	finally
        //   1891	1897	4437	java/lang/Exception
        //   1891	1897	4432	finally
        //   1902	1907	4437	java/lang/Exception
        //   1902	1907	4432	finally
        //   1912	1917	4437	java/lang/Exception
        //   1912	1917	4432	finally
        //   1922	1929	4437	java/lang/Exception
        //   1922	1929	4432	finally
        //   1934	1943	4437	java/lang/Exception
        //   1934	1943	4432	finally
        //   1948	1971	4437	java/lang/Exception
        //   1948	1971	4432	finally
        //   1976	1985	4437	java/lang/Exception
        //   1976	1985	4432	finally
        //   1990	1996	4437	java/lang/Exception
        //   1990	1996	4432	finally
        //   2001	2006	4437	java/lang/Exception
        //   2001	2006	4432	finally
        //   2011	2016	4437	java/lang/Exception
        //   2011	2016	4432	finally
        //   2021	2028	4437	java/lang/Exception
        //   2021	2028	4432	finally
        //   2033	2042	4437	java/lang/Exception
        //   2033	2042	4432	finally
        //   2047	2081	4437	java/lang/Exception
        //   2047	2081	4432	finally
        //   2086	2095	4437	java/lang/Exception
        //   2086	2095	4432	finally
        //   2100	2106	4437	java/lang/Exception
        //   2100	2106	4432	finally
        //   2111	2124	4437	java/lang/Exception
        //   2111	2124	4432	finally
        //   2129	2134	4437	java/lang/Exception
        //   2129	2134	4432	finally
        //   2139	2144	4437	java/lang/Exception
        //   2139	2144	4432	finally
        //   2149	2156	4437	java/lang/Exception
        //   2149	2156	4432	finally
        //   2161	2170	4437	java/lang/Exception
        //   2161	2170	4432	finally
        //   2175	2196	4437	java/lang/Exception
        //   2175	2196	4432	finally
        //   2201	2210	4437	java/lang/Exception
        //   2201	2210	4432	finally
        //   2215	2221	4437	java/lang/Exception
        //   2215	2221	4432	finally
        //   2226	2231	4437	java/lang/Exception
        //   2226	2231	4432	finally
        //   2236	2241	4437	java/lang/Exception
        //   2236	2241	4432	finally
        //   2246	2253	4437	java/lang/Exception
        //   2246	2253	4432	finally
        //   2258	2267	4437	java/lang/Exception
        //   2258	2267	4432	finally
        //   2272	2294	4437	java/lang/Exception
        //   2272	2294	4432	finally
        //   2299	2308	4437	java/lang/Exception
        //   2299	2308	4432	finally
        //   2313	2319	4437	java/lang/Exception
        //   2313	2319	4432	finally
        //   2324	2329	4437	java/lang/Exception
        //   2324	2329	4432	finally
        //   2334	2339	4437	java/lang/Exception
        //   2334	2339	4432	finally
        //   2344	2351	4437	java/lang/Exception
        //   2344	2351	4432	finally
        //   2356	2365	4437	java/lang/Exception
        //   2356	2365	4432	finally
        //   2370	2404	4437	java/lang/Exception
        //   2370	2404	4432	finally
        //   2409	2418	4437	java/lang/Exception
        //   2409	2418	4432	finally
        //   2423	2429	4437	java/lang/Exception
        //   2423	2429	4432	finally
        //   2434	2447	4437	java/lang/Exception
        //   2434	2447	4432	finally
        //   2452	2457	4437	java/lang/Exception
        //   2452	2457	4432	finally
        //   2462	2467	4437	java/lang/Exception
        //   2462	2467	4432	finally
        //   2472	2479	4437	java/lang/Exception
        //   2472	2479	4432	finally
        //   2484	2493	4437	java/lang/Exception
        //   2484	2493	4432	finally
        //   2498	2519	4437	java/lang/Exception
        //   2498	2519	4432	finally
        //   2524	2533	4437	java/lang/Exception
        //   2524	2533	4432	finally
        //   2538	2544	4437	java/lang/Exception
        //   2538	2544	4432	finally
        //   2549	2554	4437	java/lang/Exception
        //   2549	2554	4432	finally
        //   2559	2564	4437	java/lang/Exception
        //   2559	2564	4432	finally
        //   2569	2576	4437	java/lang/Exception
        //   2569	2576	4432	finally
        //   2581	2590	4437	java/lang/Exception
        //   2581	2590	4432	finally
        //   2595	2616	4437	java/lang/Exception
        //   2595	2616	4432	finally
        //   2621	2630	4437	java/lang/Exception
        //   2621	2630	4432	finally
        //   2635	2641	4437	java/lang/Exception
        //   2635	2641	4432	finally
        //   2646	2651	4437	java/lang/Exception
        //   2646	2651	4432	finally
        //   2656	2661	4437	java/lang/Exception
        //   2656	2661	4432	finally
        //   2666	2673	4437	java/lang/Exception
        //   2666	2673	4432	finally
        //   2678	2687	4437	java/lang/Exception
        //   2678	2687	4432	finally
        //   2692	2714	4437	java/lang/Exception
        //   2692	2714	4432	finally
        //   2719	2728	4437	java/lang/Exception
        //   2719	2728	4432	finally
        //   2733	2739	4437	java/lang/Exception
        //   2733	2739	4432	finally
        //   2744	2749	4437	java/lang/Exception
        //   2744	2749	4432	finally
        //   2754	2759	4437	java/lang/Exception
        //   2754	2759	4432	finally
        //   2764	2771	4437	java/lang/Exception
        //   2764	2771	4432	finally
        //   2776	2785	4437	java/lang/Exception
        //   2776	2785	4432	finally
        //   2790	2824	4437	java/lang/Exception
        //   2790	2824	4432	finally
        //   2829	2838	4437	java/lang/Exception
        //   2829	2838	4432	finally
        //   2843	2849	4437	java/lang/Exception
        //   2843	2849	4432	finally
        //   4441	4455	4432	finally
    }

    public void check() {
        try {
            StringRequest stringRequest = new StringRequest() {
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
                    hashMap.put("imei", Frag_Database.this.Imei);
                    hashMap.put("serial", Login.serial);
                    return (Map)hashMap;
                }
            };
            Response.Listener<String> listener = new Response.Listener<String>() {
                public void onResponse(String param1String) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        this(param1String);
                        MainActivity.listKH = jSONObject.getJSONArray("listKHs").getJSONObject(0);
                        param1String = MainActivity.listKH.getString("date").replaceAll("-", "");
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(param1String.substring(6));
                        stringBuilder.append("/");
                        stringBuilder.append(param1String.substring(4, 6));
                        stringBuilder.append("/");
                        stringBuilder.append(param1String.substring(0, 4));
                        MainActivity.myDate = stringBuilder.toString();
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                }
            };
            super(this);
            Response.ErrorListener errorListener = new Response.ErrorListener() {
                public void onErrorResponse(VolleyError param1VolleyError) {}
            };
            super(this);
            super(this, 1, "http://ldpro.biz/json_date1.php", listener, errorListener);
            Volley.newRequestQueue((Context)getActivity()).add((Request)stringRequest);
        } catch (Exception exception) {
            Toast.makeText((Context)getActivity(), "Kitra knm, 1).show();
        }
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
                            if (str != null) {
                                str = str.trim().replaceAll("\t", "!").replaceAll("\n", "!").replaceAll("!!", "!").replaceAll("!!", "!").replaceAll("!!", "!").replaceAll("!!", "!");
                                Frag_Database.this.ArrayGiai = str.split("!");
                                if (Frag_Database.this.ArrayGiai.length > 0) {
                                    if (Frag_Database.this.ArrayGiai.length > 16)
                                        if (Frag_Database.this.xosome.isChecked()) {
                                            Frag_Database.this.PhantichXosome();
                                        } else if (Frag_Database.this.xsme.isChecked()) {
                                            Frag_Database.this.PhantichXosomeNew();
                                        } else if (Frag_Database.this.xsmn.isChecked()) {
                                            Frag_Database.this.PhantichXosomeNewNew();
                                        } else {
                                            Frag_Database.this.PhantichMinhngoc();
                                        }
                                } else {
                                    Toast.makeText((Context)Frag_Database.this.getActivity(), "Kitra lknInternet!", 1).show();
                                }
                            }
                        }
                        try {
                            jsonReader.close();
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
        View view = paramLayoutInflater.inflate(2131427405, paramViewGroup, false);
        this.db = new Database((Context)getActivity());
        this.btn_tt = (Button)view.findViewById(2131230833);
        this.btnDelete = (Button)view.findViewById(2131230801);
        this.xosome = (RadioButton)view.findViewById(2131231579);
        this.minhngoc = (RadioButton)view.findViewById(2131231109);
        this.nazzy = (RadioButton)view.findViewById(2131231119);
        this.ketquanet = (RadioButton)view.findViewById(2131231010);
        this.xsme = (RadioButton)view.findViewById(2131231580);
        this.xsmn = (RadioButton)view.findViewById(2131231581);
        this.gr1 = (RadioGroup)view.findViewById(2131230986);
        this.gr2 = (RadioGroup)view.findViewById(2131230987);
        this.nazzy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                Frag_Database.this.gr1.clearCheck();
                if (Frag_Database.this.nazzy.isChecked())
                    try {
                        String str3 = MainActivity.Get_ngay();
                        String str1 = MainActivity.Get_date();
                        StringBuilder stringBuilder2 = new StringBuilder();
                        this();
                        stringBuilder2.append(str3.substring(3, 5));
                        stringBuilder2.append("/");
                        stringBuilder2.append(str3.substring(0, 2));
                        stringBuilder2.append("/");
                        stringBuilder2.append(str3.substring(6));
                        String str4 = stringBuilder2.toString();
                        StringBuilder stringBuilder1 = new StringBuilder();
                        this();
                        stringBuilder1.append("http://thongke.nazzy.vn/handler/thongke.ashx?t=kqxsmb&date=");
                        stringBuilder1.append(str4);
                        String str2 = stringBuilder1.toString();
                        StringRequest stringRequest = new StringRequest() {
                            protected Map<String, String> getParams() throws AuthFailureError {
                                return new HashMap<String, String>();
                            }
                        };
                        Response.Listener<String> listener = new Response.Listener<String>() {
                            public void onResponse(String param2String) {
                                // Byte code:
                                //   0: ldc ''
                                //   2: astore_2
                                //   3: aload_2
                                //   4: astore_3
                                //   5: aload_2
                                //   6: astore #4
                                //   8: new org/json/JSONObject
                                //   11: astore #5
                                //   13: aload_2
                                //   14: astore_3
                                //   15: aload_2
                                //   16: astore #4
                                //   18: aload #5
                                //   20: aload_1
                                //   21: invokespecial <init> : (Ljava/lang/String;)V
                                //   24: aload_2
                                //   25: astore_1
                                //   26: aload_2
                                //   27: astore_3
                                //   28: aload_2
                                //   29: astore #4
                                //   31: aload #5
                                //   33: ldc 'Ngay'
                                //   35: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   38: invokestatic Get_ngay : ()Ljava/lang/String;
                                //   41: invokevirtual contains : (Ljava/lang/CharSequence;)Z
                                //   44: ifeq -> 2283
                                //   47: aload_2
                                //   48: astore_3
                                //   49: aload_2
                                //   50: astore #4
                                //   52: aload_0
                                //   53: getfield this$1 : Ltamhoang/ldpro4/Fragment/Frag_Database$1;
                                //   56: getfield this$0 : Ltamhoang/ldpro4/Fragment/Frag_Database;
                                //   59: getfield db : Ltamhoang/ldpro4/data/Database;
                                //   62: astore #6
                                //   64: aload_2
                                //   65: astore_3
                                //   66: aload_2
                                //   67: astore #4
                                //   69: new java/lang/StringBuilder
                                //   72: astore_1
                                //   73: aload_2
                                //   74: astore_3
                                //   75: aload_2
                                //   76: astore #4
                                //   78: aload_1
                                //   79: invokespecial <init> : ()V
                                //   82: aload_2
                                //   83: astore_3
                                //   84: aload_2
                                //   85: astore #4
                                //   87: aload_1
                                //   88: ldc 'Delete From ketqua WHERE ngay = ''
                                //   90: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   93: pop
                                //   94: aload_2
                                //   95: astore_3
                                //   96: aload_2
                                //   97: astore #4
                                //   99: aload_1
                                //   100: aload_0
                                //   101: getfield val$str_date : Ljava/lang/String;
                                //   104: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   107: pop
                                //   108: aload_2
                                //   109: astore_3
                                //   110: aload_2
                                //   111: astore #4
                                //   113: aload_1
                                //   114: ldc '''
                                //   116: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   119: pop
                                //   120: aload_2
                                //   121: astore_3
                                //   122: aload_2
                                //   123: astore #4
                                //   125: aload #6
                                //   127: aload_1
                                //   128: invokevirtual toString : ()Ljava/lang/String;
                                //   131: invokevirtual QueryData : (Ljava/lang/String;)V
                                //   134: aload_2
                                //   135: astore_3
                                //   136: aload_2
                                //   137: astore #4
                                //   139: new java/lang/StringBuilder
                                //   142: astore_1
                                //   143: aload_2
                                //   144: astore_3
                                //   145: aload_2
                                //   146: astore #4
                                //   148: aload_1
                                //   149: invokespecial <init> : ()V
                                //   152: aload_2
                                //   153: astore_3
                                //   154: aload_2
                                //   155: astore #4
                                //   157: aload_1
                                //   158: ldc '''
                                //   160: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   163: pop
                                //   164: aload_2
                                //   165: astore_3
                                //   166: aload_2
                                //   167: astore #4
                                //   169: aload_1
                                //   170: aload #5
                                //   172: ldc 'GDB'
                                //   174: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   177: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   180: pop
                                //   181: aload_2
                                //   182: astore_3
                                //   183: aload_2
                                //   184: astore #4
                                //   186: aload_1
                                //   187: ldc '','
                                //   189: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   192: pop
                                //   193: aload_2
                                //   194: astore_3
                                //   195: aload_2
                                //   196: astore #4
                                //   198: aload_1
                                //   199: invokevirtual toString : ()Ljava/lang/String;
                                //   202: astore_1
                                //   203: aload_1
                                //   204: astore_3
                                //   205: aload_1
                                //   206: astore #4
                                //   208: new java/lang/StringBuilder
                                //   211: astore_2
                                //   212: aload_1
                                //   213: astore_3
                                //   214: aload_1
                                //   215: astore #4
                                //   217: aload_2
                                //   218: invokespecial <init> : ()V
                                //   221: aload_1
                                //   222: astore_3
                                //   223: aload_1
                                //   224: astore #4
                                //   226: aload_2
                                //   227: aload_1
                                //   228: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   231: pop
                                //   232: aload_1
                                //   233: astore_3
                                //   234: aload_1
                                //   235: astore #4
                                //   237: aload_2
                                //   238: ldc '''
                                //   240: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   243: pop
                                //   244: aload_1
                                //   245: astore_3
                                //   246: aload_1
                                //   247: astore #4
                                //   249: aload_2
                                //   250: aload #5
                                //   252: ldc 'G1'
                                //   254: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   257: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   260: pop
                                //   261: aload_1
                                //   262: astore_3
                                //   263: aload_1
                                //   264: astore #4
                                //   266: aload_2
                                //   267: ldc '','
                                //   269: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   272: pop
                                //   273: aload_1
                                //   274: astore_3
                                //   275: aload_1
                                //   276: astore #4
                                //   278: aload_2
                                //   279: invokevirtual toString : ()Ljava/lang/String;
                                //   282: astore_1
                                //   283: aload_1
                                //   284: astore_3
                                //   285: aload_1
                                //   286: astore #4
                                //   288: new java/lang/StringBuilder
                                //   291: astore_2
                                //   292: aload_1
                                //   293: astore_3
                                //   294: aload_1
                                //   295: astore #4
                                //   297: aload_2
                                //   298: invokespecial <init> : ()V
                                //   301: aload_1
                                //   302: astore_3
                                //   303: aload_1
                                //   304: astore #4
                                //   306: aload_2
                                //   307: aload_1
                                //   308: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   311: pop
                                //   312: aload_1
                                //   313: astore_3
                                //   314: aload_1
                                //   315: astore #4
                                //   317: aload_2
                                //   318: ldc '''
                                //   320: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   323: pop
                                //   324: aload_1
                                //   325: astore_3
                                //   326: aload_1
                                //   327: astore #4
                                //   329: aload_2
                                //   330: aload #5
                                //   332: ldc 'G21'
                                //   334: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   337: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   340: pop
                                //   341: aload_1
                                //   342: astore_3
                                //   343: aload_1
                                //   344: astore #4
                                //   346: aload_2
                                //   347: ldc '','
                                //   349: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   352: pop
                                //   353: aload_1
                                //   354: astore_3
                                //   355: aload_1
                                //   356: astore #4
                                //   358: aload_2
                                //   359: invokevirtual toString : ()Ljava/lang/String;
                                //   362: astore_1
                                //   363: aload_1
                                //   364: astore_3
                                //   365: aload_1
                                //   366: astore #4
                                //   368: new java/lang/StringBuilder
                                //   371: astore_2
                                //   372: aload_1
                                //   373: astore_3
                                //   374: aload_1
                                //   375: astore #4
                                //   377: aload_2
                                //   378: invokespecial <init> : ()V
                                //   381: aload_1
                                //   382: astore_3
                                //   383: aload_1
                                //   384: astore #4
                                //   386: aload_2
                                //   387: aload_1
                                //   388: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   391: pop
                                //   392: aload_1
                                //   393: astore_3
                                //   394: aload_1
                                //   395: astore #4
                                //   397: aload_2
                                //   398: ldc '''
                                //   400: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   403: pop
                                //   404: aload_1
                                //   405: astore_3
                                //   406: aload_1
                                //   407: astore #4
                                //   409: aload_2
                                //   410: aload #5
                                //   412: ldc 'G22'
                                //   414: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   417: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   420: pop
                                //   421: aload_1
                                //   422: astore_3
                                //   423: aload_1
                                //   424: astore #4
                                //   426: aload_2
                                //   427: ldc '','
                                //   429: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   432: pop
                                //   433: aload_1
                                //   434: astore_3
                                //   435: aload_1
                                //   436: astore #4
                                //   438: aload_2
                                //   439: invokevirtual toString : ()Ljava/lang/String;
                                //   442: astore_1
                                //   443: aload_1
                                //   444: astore_3
                                //   445: aload_1
                                //   446: astore #4
                                //   448: new java/lang/StringBuilder
                                //   451: astore_2
                                //   452: aload_1
                                //   453: astore_3
                                //   454: aload_1
                                //   455: astore #4
                                //   457: aload_2
                                //   458: invokespecial <init> : ()V
                                //   461: aload_1
                                //   462: astore_3
                                //   463: aload_1
                                //   464: astore #4
                                //   466: aload_2
                                //   467: aload_1
                                //   468: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   471: pop
                                //   472: aload_1
                                //   473: astore_3
                                //   474: aload_1
                                //   475: astore #4
                                //   477: aload_2
                                //   478: ldc '''
                                //   480: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   483: pop
                                //   484: aload_1
                                //   485: astore_3
                                //   486: aload_1
                                //   487: astore #4
                                //   489: aload_2
                                //   490: aload #5
                                //   492: ldc 'G31'
                                //   494: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   497: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   500: pop
                                //   501: aload_1
                                //   502: astore_3
                                //   503: aload_1
                                //   504: astore #4
                                //   506: aload_2
                                //   507: ldc '','
                                //   509: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   512: pop
                                //   513: aload_1
                                //   514: astore_3
                                //   515: aload_1
                                //   516: astore #4
                                //   518: aload_2
                                //   519: invokevirtual toString : ()Ljava/lang/String;
                                //   522: astore_1
                                //   523: aload_1
                                //   524: astore_3
                                //   525: aload_1
                                //   526: astore #4
                                //   528: new java/lang/StringBuilder
                                //   531: astore_2
                                //   532: aload_1
                                //   533: astore_3
                                //   534: aload_1
                                //   535: astore #4
                                //   537: aload_2
                                //   538: invokespecial <init> : ()V
                                //   541: aload_1
                                //   542: astore_3
                                //   543: aload_1
                                //   544: astore #4
                                //   546: aload_2
                                //   547: aload_1
                                //   548: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   551: pop
                                //   552: aload_1
                                //   553: astore_3
                                //   554: aload_1
                                //   555: astore #4
                                //   557: aload_2
                                //   558: ldc '''
                                //   560: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   563: pop
                                //   564: aload_1
                                //   565: astore_3
                                //   566: aload_1
                                //   567: astore #4
                                //   569: aload_2
                                //   570: aload #5
                                //   572: ldc 'G32'
                                //   574: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   577: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   580: pop
                                //   581: aload_1
                                //   582: astore_3
                                //   583: aload_1
                                //   584: astore #4
                                //   586: aload_2
                                //   587: ldc '','
                                //   589: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   592: pop
                                //   593: aload_1
                                //   594: astore_3
                                //   595: aload_1
                                //   596: astore #4
                                //   598: aload_2
                                //   599: invokevirtual toString : ()Ljava/lang/String;
                                //   602: astore_1
                                //   603: aload_1
                                //   604: astore_3
                                //   605: aload_1
                                //   606: astore #4
                                //   608: new java/lang/StringBuilder
                                //   611: astore_2
                                //   612: aload_1
                                //   613: astore_3
                                //   614: aload_1
                                //   615: astore #4
                                //   617: aload_2
                                //   618: invokespecial <init> : ()V
                                //   621: aload_1
                                //   622: astore_3
                                //   623: aload_1
                                //   624: astore #4
                                //   626: aload_2
                                //   627: aload_1
                                //   628: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   631: pop
                                //   632: aload_1
                                //   633: astore_3
                                //   634: aload_1
                                //   635: astore #4
                                //   637: aload_2
                                //   638: ldc '''
                                //   640: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   643: pop
                                //   644: aload_1
                                //   645: astore_3
                                //   646: aload_1
                                //   647: astore #4
                                //   649: aload_2
                                //   650: aload #5
                                //   652: ldc 'G33'
                                //   654: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   657: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   660: pop
                                //   661: aload_1
                                //   662: astore_3
                                //   663: aload_1
                                //   664: astore #4
                                //   666: aload_2
                                //   667: ldc '','
                                //   669: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   672: pop
                                //   673: aload_1
                                //   674: astore_3
                                //   675: aload_1
                                //   676: astore #4
                                //   678: aload_2
                                //   679: invokevirtual toString : ()Ljava/lang/String;
                                //   682: astore_1
                                //   683: aload_1
                                //   684: astore_3
                                //   685: aload_1
                                //   686: astore #4
                                //   688: new java/lang/StringBuilder
                                //   691: astore_2
                                //   692: aload_1
                                //   693: astore_3
                                //   694: aload_1
                                //   695: astore #4
                                //   697: aload_2
                                //   698: invokespecial <init> : ()V
                                //   701: aload_1
                                //   702: astore_3
                                //   703: aload_1
                                //   704: astore #4
                                //   706: aload_2
                                //   707: aload_1
                                //   708: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   711: pop
                                //   712: aload_1
                                //   713: astore_3
                                //   714: aload_1
                                //   715: astore #4
                                //   717: aload_2
                                //   718: ldc '''
                                //   720: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   723: pop
                                //   724: aload_1
                                //   725: astore_3
                                //   726: aload_1
                                //   727: astore #4
                                //   729: aload_2
                                //   730: aload #5
                                //   732: ldc 'G34'
                                //   734: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   737: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   740: pop
                                //   741: aload_1
                                //   742: astore_3
                                //   743: aload_1
                                //   744: astore #4
                                //   746: aload_2
                                //   747: ldc '','
                                //   749: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   752: pop
                                //   753: aload_1
                                //   754: astore_3
                                //   755: aload_1
                                //   756: astore #4
                                //   758: aload_2
                                //   759: invokevirtual toString : ()Ljava/lang/String;
                                //   762: astore_1
                                //   763: aload_1
                                //   764: astore_3
                                //   765: aload_1
                                //   766: astore #4
                                //   768: new java/lang/StringBuilder
                                //   771: astore_2
                                //   772: aload_1
                                //   773: astore_3
                                //   774: aload_1
                                //   775: astore #4
                                //   777: aload_2
                                //   778: invokespecial <init> : ()V
                                //   781: aload_1
                                //   782: astore_3
                                //   783: aload_1
                                //   784: astore #4
                                //   786: aload_2
                                //   787: aload_1
                                //   788: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   791: pop
                                //   792: aload_1
                                //   793: astore_3
                                //   794: aload_1
                                //   795: astore #4
                                //   797: aload_2
                                //   798: ldc '''
                                //   800: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   803: pop
                                //   804: aload_1
                                //   805: astore_3
                                //   806: aload_1
                                //   807: astore #4
                                //   809: aload_2
                                //   810: aload #5
                                //   812: ldc 'G35'
                                //   814: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   817: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   820: pop
                                //   821: aload_1
                                //   822: astore_3
                                //   823: aload_1
                                //   824: astore #4
                                //   826: aload_2
                                //   827: ldc '','
                                //   829: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   832: pop
                                //   833: aload_1
                                //   834: astore_3
                                //   835: aload_1
                                //   836: astore #4
                                //   838: aload_2
                                //   839: invokevirtual toString : ()Ljava/lang/String;
                                //   842: astore_1
                                //   843: aload_1
                                //   844: astore_3
                                //   845: aload_1
                                //   846: astore #4
                                //   848: new java/lang/StringBuilder
                                //   851: astore_2
                                //   852: aload_1
                                //   853: astore_3
                                //   854: aload_1
                                //   855: astore #4
                                //   857: aload_2
                                //   858: invokespecial <init> : ()V
                                //   861: aload_1
                                //   862: astore_3
                                //   863: aload_1
                                //   864: astore #4
                                //   866: aload_2
                                //   867: aload_1
                                //   868: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   871: pop
                                //   872: aload_1
                                //   873: astore_3
                                //   874: aload_1
                                //   875: astore #4
                                //   877: aload_2
                                //   878: ldc '''
                                //   880: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   883: pop
                                //   884: aload_1
                                //   885: astore_3
                                //   886: aload_1
                                //   887: astore #4
                                //   889: aload_2
                                //   890: aload #5
                                //   892: ldc 'G36'
                                //   894: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   897: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   900: pop
                                //   901: aload_1
                                //   902: astore_3
                                //   903: aload_1
                                //   904: astore #4
                                //   906: aload_2
                                //   907: ldc '','
                                //   909: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   912: pop
                                //   913: aload_1
                                //   914: astore_3
                                //   915: aload_1
                                //   916: astore #4
                                //   918: aload_2
                                //   919: invokevirtual toString : ()Ljava/lang/String;
                                //   922: astore_1
                                //   923: aload_1
                                //   924: astore_3
                                //   925: aload_1
                                //   926: astore #4
                                //   928: new java/lang/StringBuilder
                                //   931: astore_2
                                //   932: aload_1
                                //   933: astore_3
                                //   934: aload_1
                                //   935: astore #4
                                //   937: aload_2
                                //   938: invokespecial <init> : ()V
                                //   941: aload_1
                                //   942: astore_3
                                //   943: aload_1
                                //   944: astore #4
                                //   946: aload_2
                                //   947: aload_1
                                //   948: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   951: pop
                                //   952: aload_1
                                //   953: astore_3
                                //   954: aload_1
                                //   955: astore #4
                                //   957: aload_2
                                //   958: ldc '''
                                //   960: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   963: pop
                                //   964: aload_1
                                //   965: astore_3
                                //   966: aload_1
                                //   967: astore #4
                                //   969: aload_2
                                //   970: aload #5
                                //   972: ldc 'G41'
                                //   974: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   977: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   980: pop
                                //   981: aload_1
                                //   982: astore_3
                                //   983: aload_1
                                //   984: astore #4
                                //   986: aload_2
                                //   987: ldc '','
                                //   989: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   992: pop
                                //   993: aload_1
                                //   994: astore_3
                                //   995: aload_1
                                //   996: astore #4
                                //   998: aload_2
                                //   999: invokevirtual toString : ()Ljava/lang/String;
                                //   1002: astore_1
                                //   1003: aload_1
                                //   1004: astore_3
                                //   1005: aload_1
                                //   1006: astore #4
                                //   1008: new java/lang/StringBuilder
                                //   1011: astore_2
                                //   1012: aload_1
                                //   1013: astore_3
                                //   1014: aload_1
                                //   1015: astore #4
                                //   1017: aload_2
                                //   1018: invokespecial <init> : ()V
                                //   1021: aload_1
                                //   1022: astore_3
                                //   1023: aload_1
                                //   1024: astore #4
                                //   1026: aload_2
                                //   1027: aload_1
                                //   1028: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1031: pop
                                //   1032: aload_1
                                //   1033: astore_3
                                //   1034: aload_1
                                //   1035: astore #4
                                //   1037: aload_2
                                //   1038: ldc '''
                                //   1040: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1043: pop
                                //   1044: aload_1
                                //   1045: astore_3
                                //   1046: aload_1
                                //   1047: astore #4
                                //   1049: aload_2
                                //   1050: aload #5
                                //   1052: ldc 'G42'
                                //   1054: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   1057: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1060: pop
                                //   1061: aload_1
                                //   1062: astore_3
                                //   1063: aload_1
                                //   1064: astore #4
                                //   1066: aload_2
                                //   1067: ldc '','
                                //   1069: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1072: pop
                                //   1073: aload_1
                                //   1074: astore_3
                                //   1075: aload_1
                                //   1076: astore #4
                                //   1078: aload_2
                                //   1079: invokevirtual toString : ()Ljava/lang/String;
                                //   1082: astore_1
                                //   1083: aload_1
                                //   1084: astore_3
                                //   1085: aload_1
                                //   1086: astore #4
                                //   1088: new java/lang/StringBuilder
                                //   1091: astore_2
                                //   1092: aload_1
                                //   1093: astore_3
                                //   1094: aload_1
                                //   1095: astore #4
                                //   1097: aload_2
                                //   1098: invokespecial <init> : ()V
                                //   1101: aload_1
                                //   1102: astore_3
                                //   1103: aload_1
                                //   1104: astore #4
                                //   1106: aload_2
                                //   1107: aload_1
                                //   1108: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1111: pop
                                //   1112: aload_1
                                //   1113: astore_3
                                //   1114: aload_1
                                //   1115: astore #4
                                //   1117: aload_2
                                //   1118: ldc '''
                                //   1120: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1123: pop
                                //   1124: aload_1
                                //   1125: astore_3
                                //   1126: aload_1
                                //   1127: astore #4
                                //   1129: aload_2
                                //   1130: aload #5
                                //   1132: ldc 'G43'
                                //   1134: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   1137: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1140: pop
                                //   1141: aload_1
                                //   1142: astore_3
                                //   1143: aload_1
                                //   1144: astore #4
                                //   1146: aload_2
                                //   1147: ldc '','
                                //   1149: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1152: pop
                                //   1153: aload_1
                                //   1154: astore_3
                                //   1155: aload_1
                                //   1156: astore #4
                                //   1158: aload_2
                                //   1159: invokevirtual toString : ()Ljava/lang/String;
                                //   1162: astore_1
                                //   1163: aload_1
                                //   1164: astore_3
                                //   1165: aload_1
                                //   1166: astore #4
                                //   1168: new java/lang/StringBuilder
                                //   1171: astore_2
                                //   1172: aload_1
                                //   1173: astore_3
                                //   1174: aload_1
                                //   1175: astore #4
                                //   1177: aload_2
                                //   1178: invokespecial <init> : ()V
                                //   1181: aload_1
                                //   1182: astore_3
                                //   1183: aload_1
                                //   1184: astore #4
                                //   1186: aload_2
                                //   1187: aload_1
                                //   1188: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1191: pop
                                //   1192: aload_1
                                //   1193: astore_3
                                //   1194: aload_1
                                //   1195: astore #4
                                //   1197: aload_2
                                //   1198: ldc '''
                                //   1200: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1203: pop
                                //   1204: aload_1
                                //   1205: astore_3
                                //   1206: aload_1
                                //   1207: astore #4
                                //   1209: aload_2
                                //   1210: aload #5
                                //   1212: ldc 'G44'
                                //   1214: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   1217: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1220: pop
                                //   1221: aload_1
                                //   1222: astore_3
                                //   1223: aload_1
                                //   1224: astore #4
                                //   1226: aload_2
                                //   1227: ldc '','
                                //   1229: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1232: pop
                                //   1233: aload_1
                                //   1234: astore_3
                                //   1235: aload_1
                                //   1236: astore #4
                                //   1238: aload_2
                                //   1239: invokevirtual toString : ()Ljava/lang/String;
                                //   1242: astore_1
                                //   1243: aload_1
                                //   1244: astore_3
                                //   1245: aload_1
                                //   1246: astore #4
                                //   1248: new java/lang/StringBuilder
                                //   1251: astore_2
                                //   1252: aload_1
                                //   1253: astore_3
                                //   1254: aload_1
                                //   1255: astore #4
                                //   1257: aload_2
                                //   1258: invokespecial <init> : ()V
                                //   1261: aload_1
                                //   1262: astore_3
                                //   1263: aload_1
                                //   1264: astore #4
                                //   1266: aload_2
                                //   1267: aload_1
                                //   1268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1271: pop
                                //   1272: aload_1
                                //   1273: astore_3
                                //   1274: aload_1
                                //   1275: astore #4
                                //   1277: aload_2
                                //   1278: ldc '''
                                //   1280: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1283: pop
                                //   1284: aload_1
                                //   1285: astore_3
                                //   1286: aload_1
                                //   1287: astore #4
                                //   1289: aload_2
                                //   1290: aload #5
                                //   1292: ldc 'G51'
                                //   1294: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   1297: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1300: pop
                                //   1301: aload_1
                                //   1302: astore_3
                                //   1303: aload_1
                                //   1304: astore #4
                                //   1306: aload_2
                                //   1307: ldc '','
                                //   1309: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1312: pop
                                //   1313: aload_1
                                //   1314: astore_3
                                //   1315: aload_1
                                //   1316: astore #4
                                //   1318: aload_2
                                //   1319: invokevirtual toString : ()Ljava/lang/String;
                                //   1322: astore_1
                                //   1323: aload_1
                                //   1324: astore_3
                                //   1325: aload_1
                                //   1326: astore #4
                                //   1328: new java/lang/StringBuilder
                                //   1331: astore_2
                                //   1332: aload_1
                                //   1333: astore_3
                                //   1334: aload_1
                                //   1335: astore #4
                                //   1337: aload_2
                                //   1338: invokespecial <init> : ()V
                                //   1341: aload_1
                                //   1342: astore_3
                                //   1343: aload_1
                                //   1344: astore #4
                                //   1346: aload_2
                                //   1347: aload_1
                                //   1348: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1351: pop
                                //   1352: aload_1
                                //   1353: astore_3
                                //   1354: aload_1
                                //   1355: astore #4
                                //   1357: aload_2
                                //   1358: ldc '''
                                //   1360: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1363: pop
                                //   1364: aload_1
                                //   1365: astore_3
                                //   1366: aload_1
                                //   1367: astore #4
                                //   1369: aload_2
                                //   1370: aload #5
                                //   1372: ldc 'G52'
                                //   1374: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   1377: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1380: pop
                                //   1381: aload_1
                                //   1382: astore_3
                                //   1383: aload_1
                                //   1384: astore #4
                                //   1386: aload_2
                                //   1387: ldc '','
                                //   1389: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1392: pop
                                //   1393: aload_1
                                //   1394: astore_3
                                //   1395: aload_1
                                //   1396: astore #4
                                //   1398: aload_2
                                //   1399: invokevirtual toString : ()Ljava/lang/String;
                                //   1402: astore_1
                                //   1403: aload_1
                                //   1404: astore_3
                                //   1405: aload_1
                                //   1406: astore #4
                                //   1408: new java/lang/StringBuilder
                                //   1411: astore_2
                                //   1412: aload_1
                                //   1413: astore_3
                                //   1414: aload_1
                                //   1415: astore #4
                                //   1417: aload_2
                                //   1418: invokespecial <init> : ()V
                                //   1421: aload_1
                                //   1422: astore_3
                                //   1423: aload_1
                                //   1424: astore #4
                                //   1426: aload_2
                                //   1427: aload_1
                                //   1428: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1431: pop
                                //   1432: aload_1
                                //   1433: astore_3
                                //   1434: aload_1
                                //   1435: astore #4
                                //   1437: aload_2
                                //   1438: ldc '''
                                //   1440: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1443: pop
                                //   1444: aload_1
                                //   1445: astore_3
                                //   1446: aload_1
                                //   1447: astore #4
                                //   1449: aload_2
                                //   1450: aload #5
                                //   1452: ldc 'G53'
                                //   1454: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   1457: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1460: pop
                                //   1461: aload_1
                                //   1462: astore_3
                                //   1463: aload_1
                                //   1464: astore #4
                                //   1466: aload_2
                                //   1467: ldc '','
                                //   1469: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1472: pop
                                //   1473: aload_1
                                //   1474: astore_3
                                //   1475: aload_1
                                //   1476: astore #4
                                //   1478: aload_2
                                //   1479: invokevirtual toString : ()Ljava/lang/String;
                                //   1482: astore_1
                                //   1483: aload_1
                                //   1484: astore_3
                                //   1485: aload_1
                                //   1486: astore #4
                                //   1488: new java/lang/StringBuilder
                                //   1491: astore_2
                                //   1492: aload_1
                                //   1493: astore_3
                                //   1494: aload_1
                                //   1495: astore #4
                                //   1497: aload_2
                                //   1498: invokespecial <init> : ()V
                                //   1501: aload_1
                                //   1502: astore_3
                                //   1503: aload_1
                                //   1504: astore #4
                                //   1506: aload_2
                                //   1507: aload_1
                                //   1508: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1511: pop
                                //   1512: aload_1
                                //   1513: astore_3
                                //   1514: aload_1
                                //   1515: astore #4
                                //   1517: aload_2
                                //   1518: ldc '''
                                //   1520: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1523: pop
                                //   1524: aload_1
                                //   1525: astore_3
                                //   1526: aload_1
                                //   1527: astore #4
                                //   1529: aload_2
                                //   1530: aload #5
                                //   1532: ldc 'G54'
                                //   1534: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   1537: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1540: pop
                                //   1541: aload_1
                                //   1542: astore_3
                                //   1543: aload_1
                                //   1544: astore #4
                                //   1546: aload_2
                                //   1547: ldc '','
                                //   1549: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1552: pop
                                //   1553: aload_1
                                //   1554: astore_3
                                //   1555: aload_1
                                //   1556: astore #4
                                //   1558: aload_2
                                //   1559: invokevirtual toString : ()Ljava/lang/String;
                                //   1562: astore_1
                                //   1563: aload_1
                                //   1564: astore_3
                                //   1565: aload_1
                                //   1566: astore #4
                                //   1568: new java/lang/StringBuilder
                                //   1571: astore_2
                                //   1572: aload_1
                                //   1573: astore_3
                                //   1574: aload_1
                                //   1575: astore #4
                                //   1577: aload_2
                                //   1578: invokespecial <init> : ()V
                                //   1581: aload_1
                                //   1582: astore_3
                                //   1583: aload_1
                                //   1584: astore #4
                                //   1586: aload_2
                                //   1587: aload_1
                                //   1588: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1591: pop
                                //   1592: aload_1
                                //   1593: astore_3
                                //   1594: aload_1
                                //   1595: astore #4
                                //   1597: aload_2
                                //   1598: ldc '''
                                //   1600: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1603: pop
                                //   1604: aload_1
                                //   1605: astore_3
                                //   1606: aload_1
                                //   1607: astore #4
                                //   1609: aload_2
                                //   1610: aload #5
                                //   1612: ldc 'G55'
                                //   1614: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   1617: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1620: pop
                                //   1621: aload_1
                                //   1622: astore_3
                                //   1623: aload_1
                                //   1624: astore #4
                                //   1626: aload_2
                                //   1627: ldc '','
                                //   1629: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1632: pop
                                //   1633: aload_1
                                //   1634: astore_3
                                //   1635: aload_1
                                //   1636: astore #4
                                //   1638: aload_2
                                //   1639: invokevirtual toString : ()Ljava/lang/String;
                                //   1642: astore_1
                                //   1643: aload_1
                                //   1644: astore_3
                                //   1645: aload_1
                                //   1646: astore #4
                                //   1648: new java/lang/StringBuilder
                                //   1651: astore_2
                                //   1652: aload_1
                                //   1653: astore_3
                                //   1654: aload_1
                                //   1655: astore #4
                                //   1657: aload_2
                                //   1658: invokespecial <init> : ()V
                                //   1661: aload_1
                                //   1662: astore_3
                                //   1663: aload_1
                                //   1664: astore #4
                                //   1666: aload_2
                                //   1667: aload_1
                                //   1668: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1671: pop
                                //   1672: aload_1
                                //   1673: astore_3
                                //   1674: aload_1
                                //   1675: astore #4
                                //   1677: aload_2
                                //   1678: ldc '''
                                //   1680: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1683: pop
                                //   1684: aload_1
                                //   1685: astore_3
                                //   1686: aload_1
                                //   1687: astore #4
                                //   1689: aload_2
                                //   1690: aload #5
                                //   1692: ldc 'G56'
                                //   1694: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   1697: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1700: pop
                                //   1701: aload_1
                                //   1702: astore_3
                                //   1703: aload_1
                                //   1704: astore #4
                                //   1706: aload_2
                                //   1707: ldc '','
                                //   1709: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1712: pop
                                //   1713: aload_1
                                //   1714: astore_3
                                //   1715: aload_1
                                //   1716: astore #4
                                //   1718: aload_2
                                //   1719: invokevirtual toString : ()Ljava/lang/String;
                                //   1722: astore_1
                                //   1723: aload_1
                                //   1724: astore_3
                                //   1725: aload_1
                                //   1726: astore #4
                                //   1728: new java/lang/StringBuilder
                                //   1731: astore_2
                                //   1732: aload_1
                                //   1733: astore_3
                                //   1734: aload_1
                                //   1735: astore #4
                                //   1737: aload_2
                                //   1738: invokespecial <init> : ()V
                                //   1741: aload_1
                                //   1742: astore_3
                                //   1743: aload_1
                                //   1744: astore #4
                                //   1746: aload_2
                                //   1747: aload_1
                                //   1748: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1751: pop
                                //   1752: aload_1
                                //   1753: astore_3
                                //   1754: aload_1
                                //   1755: astore #4
                                //   1757: aload_2
                                //   1758: ldc '''
                                //   1760: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1763: pop
                                //   1764: aload_1
                                //   1765: astore_3
                                //   1766: aload_1
                                //   1767: astore #4
                                //   1769: aload_2
                                //   1770: aload #5
                                //   1772: ldc 'G61'
                                //   1774: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   1777: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1780: pop
                                //   1781: aload_1
                                //   1782: astore_3
                                //   1783: aload_1
                                //   1784: astore #4
                                //   1786: aload_2
                                //   1787: ldc '','
                                //   1789: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1792: pop
                                //   1793: aload_1
                                //   1794: astore_3
                                //   1795: aload_1
                                //   1796: astore #4
                                //   1798: aload_2
                                //   1799: invokevirtual toString : ()Ljava/lang/String;
                                //   1802: astore_1
                                //   1803: aload_1
                                //   1804: astore_3
                                //   1805: aload_1
                                //   1806: astore #4
                                //   1808: new java/lang/StringBuilder
                                //   1811: astore_2
                                //   1812: aload_1
                                //   1813: astore_3
                                //   1814: aload_1
                                //   1815: astore #4
                                //   1817: aload_2
                                //   1818: invokespecial <init> : ()V
                                //   1821: aload_1
                                //   1822: astore_3
                                //   1823: aload_1
                                //   1824: astore #4
                                //   1826: aload_2
                                //   1827: aload_1
                                //   1828: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1831: pop
                                //   1832: aload_1
                                //   1833: astore_3
                                //   1834: aload_1
                                //   1835: astore #4
                                //   1837: aload_2
                                //   1838: ldc '''
                                //   1840: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1843: pop
                                //   1844: aload_1
                                //   1845: astore_3
                                //   1846: aload_1
                                //   1847: astore #4
                                //   1849: aload_2
                                //   1850: aload #5
                                //   1852: ldc 'G62'
                                //   1854: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   1857: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1860: pop
                                //   1861: aload_1
                                //   1862: astore_3
                                //   1863: aload_1
                                //   1864: astore #4
                                //   1866: aload_2
                                //   1867: ldc '','
                                //   1869: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1872: pop
                                //   1873: aload_1
                                //   1874: astore_3
                                //   1875: aload_1
                                //   1876: astore #4
                                //   1878: aload_2
                                //   1879: invokevirtual toString : ()Ljava/lang/String;
                                //   1882: astore_1
                                //   1883: aload_1
                                //   1884: astore_3
                                //   1885: aload_1
                                //   1886: astore #4
                                //   1888: new java/lang/StringBuilder
                                //   1891: astore_2
                                //   1892: aload_1
                                //   1893: astore_3
                                //   1894: aload_1
                                //   1895: astore #4
                                //   1897: aload_2
                                //   1898: invokespecial <init> : ()V
                                //   1901: aload_1
                                //   1902: astore_3
                                //   1903: aload_1
                                //   1904: astore #4
                                //   1906: aload_2
                                //   1907: aload_1
                                //   1908: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1911: pop
                                //   1912: aload_1
                                //   1913: astore_3
                                //   1914: aload_1
                                //   1915: astore #4
                                //   1917: aload_2
                                //   1918: ldc '''
                                //   1920: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1923: pop
                                //   1924: aload_1
                                //   1925: astore_3
                                //   1926: aload_1
                                //   1927: astore #4
                                //   1929: aload_2
                                //   1930: aload #5
                                //   1932: ldc 'G63'
                                //   1934: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   1937: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1940: pop
                                //   1941: aload_1
                                //   1942: astore_3
                                //   1943: aload_1
                                //   1944: astore #4
                                //   1946: aload_2
                                //   1947: ldc '','
                                //   1949: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1952: pop
                                //   1953: aload_1
                                //   1954: astore_3
                                //   1955: aload_1
                                //   1956: astore #4
                                //   1958: aload_2
                                //   1959: invokevirtual toString : ()Ljava/lang/String;
                                //   1962: astore_1
                                //   1963: aload_1
                                //   1964: astore_3
                                //   1965: aload_1
                                //   1966: astore #4
                                //   1968: new java/lang/StringBuilder
                                //   1971: astore_2
                                //   1972: aload_1
                                //   1973: astore_3
                                //   1974: aload_1
                                //   1975: astore #4
                                //   1977: aload_2
                                //   1978: invokespecial <init> : ()V
                                //   1981: aload_1
                                //   1982: astore_3
                                //   1983: aload_1
                                //   1984: astore #4
                                //   1986: aload_2
                                //   1987: aload_1
                                //   1988: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   1991: pop
                                //   1992: aload_1
                                //   1993: astore_3
                                //   1994: aload_1
                                //   1995: astore #4
                                //   1997: aload_2
                                //   1998: ldc '''
                                //   2000: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2003: pop
                                //   2004: aload_1
                                //   2005: astore_3
                                //   2006: aload_1
                                //   2007: astore #4
                                //   2009: aload_2
                                //   2010: aload #5
                                //   2012: ldc 'G71'
                                //   2014: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   2017: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2020: pop
                                //   2021: aload_1
                                //   2022: astore_3
                                //   2023: aload_1
                                //   2024: astore #4
                                //   2026: aload_2
                                //   2027: ldc '','
                                //   2029: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2032: pop
                                //   2033: aload_1
                                //   2034: astore_3
                                //   2035: aload_1
                                //   2036: astore #4
                                //   2038: aload_2
                                //   2039: invokevirtual toString : ()Ljava/lang/String;
                                //   2042: astore_1
                                //   2043: aload_1
                                //   2044: astore_3
                                //   2045: aload_1
                                //   2046: astore #4
                                //   2048: new java/lang/StringBuilder
                                //   2051: astore_2
                                //   2052: aload_1
                                //   2053: astore_3
                                //   2054: aload_1
                                //   2055: astore #4
                                //   2057: aload_2
                                //   2058: invokespecial <init> : ()V
                                //   2061: aload_1
                                //   2062: astore_3
                                //   2063: aload_1
                                //   2064: astore #4
                                //   2066: aload_2
                                //   2067: aload_1
                                //   2068: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2071: pop
                                //   2072: aload_1
                                //   2073: astore_3
                                //   2074: aload_1
                                //   2075: astore #4
                                //   2077: aload_2
                                //   2078: ldc '''
                                //   2080: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2083: pop
                                //   2084: aload_1
                                //   2085: astore_3
                                //   2086: aload_1
                                //   2087: astore #4
                                //   2089: aload_2
                                //   2090: aload #5
                                //   2092: ldc 'G72'
                                //   2094: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   2097: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2100: pop
                                //   2101: aload_1
                                //   2102: astore_3
                                //   2103: aload_1
                                //   2104: astore #4
                                //   2106: aload_2
                                //   2107: ldc '','
                                //   2109: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2112: pop
                                //   2113: aload_1
                                //   2114: astore_3
                                //   2115: aload_1
                                //   2116: astore #4
                                //   2118: aload_2
                                //   2119: invokevirtual toString : ()Ljava/lang/String;
                                //   2122: astore_1
                                //   2123: aload_1
                                //   2124: astore_3
                                //   2125: aload_1
                                //   2126: astore #4
                                //   2128: new java/lang/StringBuilder
                                //   2131: astore_2
                                //   2132: aload_1
                                //   2133: astore_3
                                //   2134: aload_1
                                //   2135: astore #4
                                //   2137: aload_2
                                //   2138: invokespecial <init> : ()V
                                //   2141: aload_1
                                //   2142: astore_3
                                //   2143: aload_1
                                //   2144: astore #4
                                //   2146: aload_2
                                //   2147: aload_1
                                //   2148: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2151: pop
                                //   2152: aload_1
                                //   2153: astore_3
                                //   2154: aload_1
                                //   2155: astore #4
                                //   2157: aload_2
                                //   2158: ldc '''
                                //   2160: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2163: pop
                                //   2164: aload_1
                                //   2165: astore_3
                                //   2166: aload_1
                                //   2167: astore #4
                                //   2169: aload_2
                                //   2170: aload #5
                                //   2172: ldc 'G73'
                                //   2174: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   2177: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2180: pop
                                //   2181: aload_1
                                //   2182: astore_3
                                //   2183: aload_1
                                //   2184: astore #4
                                //   2186: aload_2
                                //   2187: ldc '','
                                //   2189: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2192: pop
                                //   2193: aload_1
                                //   2194: astore_3
                                //   2195: aload_1
                                //   2196: astore #4
                                //   2198: aload_2
                                //   2199: invokevirtual toString : ()Ljava/lang/String;
                                //   2202: astore_1
                                //   2203: aload_1
                                //   2204: astore_3
                                //   2205: aload_1
                                //   2206: astore #4
                                //   2208: new java/lang/StringBuilder
                                //   2211: astore_2
                                //   2212: aload_1
                                //   2213: astore_3
                                //   2214: aload_1
                                //   2215: astore #4
                                //   2217: aload_2
                                //   2218: invokespecial <init> : ()V
                                //   2221: aload_1
                                //   2222: astore_3
                                //   2223: aload_1
                                //   2224: astore #4
                                //   2226: aload_2
                                //   2227: aload_1
                                //   2228: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2231: pop
                                //   2232: aload_1
                                //   2233: astore_3
                                //   2234: aload_1
                                //   2235: astore #4
                                //   2237: aload_2
                                //   2238: ldc '''
                                //   2240: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2243: pop
                                //   2244: aload_1
                                //   2245: astore_3
                                //   2246: aload_1
                                //   2247: astore #4
                                //   2249: aload_2
                                //   2250: aload #5
                                //   2252: ldc 'G74'
                                //   2254: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
                                //   2257: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2260: pop
                                //   2261: aload_1
                                //   2262: astore_3
                                //   2263: aload_1
                                //   2264: astore #4
                                //   2266: aload_2
                                //   2267: ldc '')'
                                //   2269: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2272: pop
                                //   2273: aload_1
                                //   2274: astore_3
                                //   2275: aload_1
                                //   2276: astore #4
                                //   2278: aload_2
                                //   2279: invokevirtual toString : ()Ljava/lang/String;
                                //   2282: astore_1
                                //   2283: aload_1
                                //   2284: invokevirtual length : ()I
                                //   2287: ifle -> 2395
                                //   2290: new java/lang/StringBuilder
                                //   2293: dup
                                //   2294: invokespecial <init> : ()V
                                //   2297: astore_3
                                //   2298: aload_3
                                //   2299: ldc 'InSert Into KETQUA VALUES(null,''
                                //   2301: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2304: pop
                                //   2305: aload_3
                                //   2306: aload_0
                                //   2307: getfield val$str_date : Ljava/lang/String;
                                //   2310: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2313: pop
                                //   2314: aload_3
                                //   2315: ldc '','
                                //   2317: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2320: pop
                                //   2321: aload_3
                                //   2322: aload_1
                                //   2323: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2326: pop
                                //   2327: aload_3
                                //   2328: invokevirtual toString : ()Ljava/lang/String;
                                //   2331: astore_1
                                //   2332: aload_0
                                //   2333: getfield this$1 : Ltamhoang/ldpro4/Fragment/Frag_Database$1;
                                //   2336: getfield this$0 : Ltamhoang/ldpro4/Fragment/Frag_Database;
                                //   2339: getfield db : Ltamhoang/ldpro4/data/Database;
                                //   2342: aload_1
                                //   2343: invokevirtual QueryData : (Ljava/lang/String;)V
                                //   2346: aload_0
                                //   2347: getfield this$1 : Ltamhoang/ldpro4/Fragment/Frag_Database$1;
                                //   2350: getfield this$0 : Ltamhoang/ldpro4/Fragment/Frag_Database;
                                //   2353: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
                                //   2356: astore_1
                                //   2357: new java/lang/StringBuilder
                                //   2360: dup
                                //   2361: invokespecial <init> : ()V
                                //   2364: astore_3
                                //   2365: aload_3
                                //   2366: ldc 'txong kqung'
                                //   2368: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2371: pop
                                //   2372: aload_3
                                //   2373: invokestatic Get_ngay : ()Ljava/lang/String;
                                //   2376: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2379: pop
                                //   2380: aload_1
                                //   2381: aload_3
                                //   2382: invokevirtual toString : ()Ljava/lang/String;
                                //   2385: iconst_1
                                //   2386: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
                                //   2389: invokevirtual show : ()V
                                //   2392: goto -> 2516
                                //   2395: aload_0
                                //   2396: getfield this$1 : Ltamhoang/ldpro4/Fragment/Frag_Database$1;
                                //   2399: getfield this$0 : Ltamhoang/ldpro4/Fragment/Frag_Database;
                                //   2402: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
                                //   2405: ldc 'Khckquphh
                                //   2407: iconst_1
                                //   2408: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
                                //   2411: invokevirtual show : ()V
                                //   2414: goto -> 2516
                                //   2417: astore_1
                                //   2418: goto -> 2517
                                //   2421: astore_1
                                //   2422: aload #4
                                //   2424: astore_3
                                //   2425: aload_1
                                //   2426: invokevirtual printStackTrace : ()V
                                //   2429: aload #4
                                //   2431: invokevirtual length : ()I
                                //   2434: ifle -> 2395
                                //   2437: new java/lang/StringBuilder
                                //   2440: dup
                                //   2441: invokespecial <init> : ()V
                                //   2444: astore_1
                                //   2445: aload_1
                                //   2446: ldc 'InSert Into KETQUA VALUES(null,''
                                //   2448: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2451: pop
                                //   2452: aload_1
                                //   2453: aload_0
                                //   2454: getfield val$str_date : Ljava/lang/String;
                                //   2457: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2460: pop
                                //   2461: aload_1
                                //   2462: ldc '','
                                //   2464: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2467: pop
                                //   2468: aload_1
                                //   2469: aload #4
                                //   2471: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2474: pop
                                //   2475: aload_1
                                //   2476: invokevirtual toString : ()Ljava/lang/String;
                                //   2479: astore_1
                                //   2480: aload_0
                                //   2481: getfield this$1 : Ltamhoang/ldpro4/Fragment/Frag_Database$1;
                                //   2484: getfield this$0 : Ltamhoang/ldpro4/Fragment/Frag_Database;
                                //   2487: getfield db : Ltamhoang/ldpro4/data/Database;
                                //   2490: aload_1
                                //   2491: invokevirtual QueryData : (Ljava/lang/String;)V
                                //   2494: aload_0
                                //   2495: getfield this$1 : Ltamhoang/ldpro4/Fragment/Frag_Database$1;
                                //   2498: getfield this$0 : Ltamhoang/ldpro4/Fragment/Frag_Database;
                                //   2501: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
                                //   2504: astore_1
                                //   2505: new java/lang/StringBuilder
                                //   2508: dup
                                //   2509: invokespecial <init> : ()V
                                //   2512: astore_3
                                //   2513: goto -> 2365
                                //   2516: return
                                //   2517: aload_3
                                //   2518: invokevirtual length : ()I
                                //   2521: ifle -> 2637
                                //   2524: new java/lang/StringBuilder
                                //   2527: dup
                                //   2528: invokespecial <init> : ()V
                                //   2531: astore #4
                                //   2533: aload #4
                                //   2535: ldc 'InSert Into KETQUA VALUES(null,''
                                //   2537: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2540: pop
                                //   2541: aload #4
                                //   2543: aload_0
                                //   2544: getfield val$str_date : Ljava/lang/String;
                                //   2547: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2550: pop
                                //   2551: aload #4
                                //   2553: ldc '','
                                //   2555: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2558: pop
                                //   2559: aload #4
                                //   2561: aload_3
                                //   2562: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2565: pop
                                //   2566: aload #4
                                //   2568: invokevirtual toString : ()Ljava/lang/String;
                                //   2571: astore_3
                                //   2572: aload_0
                                //   2573: getfield this$1 : Ltamhoang/ldpro4/Fragment/Frag_Database$1;
                                //   2576: getfield this$0 : Ltamhoang/ldpro4/Fragment/Frag_Database;
                                //   2579: getfield db : Ltamhoang/ldpro4/data/Database;
                                //   2582: aload_3
                                //   2583: invokevirtual QueryData : (Ljava/lang/String;)V
                                //   2586: aload_0
                                //   2587: getfield this$1 : Ltamhoang/ldpro4/Fragment/Frag_Database$1;
                                //   2590: getfield this$0 : Ltamhoang/ldpro4/Fragment/Frag_Database;
                                //   2593: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
                                //   2596: astore #4
                                //   2598: new java/lang/StringBuilder
                                //   2601: dup
                                //   2602: invokespecial <init> : ()V
                                //   2605: astore_3
                                //   2606: aload_3
                                //   2607: ldc 'txong kqung'
                                //   2609: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2612: pop
                                //   2613: aload_3
                                //   2614: invokestatic Get_ngay : ()Ljava/lang/String;
                                //   2617: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                                //   2620: pop
                                //   2621: aload #4
                                //   2623: aload_3
                                //   2624: invokevirtual toString : ()Ljava/lang/String;
                                //   2627: iconst_1
                                //   2628: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
                                //   2631: invokevirtual show : ()V
                                //   2634: goto -> 2656
                                //   2637: aload_0
                                //   2638: getfield this$1 : Ltamhoang/ldpro4/Fragment/Frag_Database$1;
                                //   2641: getfield this$0 : Ltamhoang/ldpro4/Fragment/Frag_Database;
                                //   2644: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
                                //   2647: ldc 'Khckquphh
                                //   2649: iconst_1
                                //   2650: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
                                //   2653: invokevirtual show : ()V
                                //   2656: goto -> 2661
                                //   2659: aload_1
                                //   2660: athrow
                                //   2661: goto -> 2659
                                // Exception table:
                                //   from	to	target	type
                                //   8	13	2421	org/json/JSONException
                                //   8	13	2417	finally
                                //   18	24	2421	org/json/JSONException
                                //   18	24	2417	finally
                                //   31	47	2421	org/json/JSONException
                                //   31	47	2417	finally
                                //   52	64	2421	org/json/JSONException
                                //   52	64	2417	finally
                                //   69	73	2421	org/json/JSONException
                                //   69	73	2417	finally
                                //   78	82	2421	org/json/JSONException
                                //   78	82	2417	finally
                                //   87	94	2421	org/json/JSONException
                                //   87	94	2417	finally
                                //   99	108	2421	org/json/JSONException
                                //   99	108	2417	finally
                                //   113	120	2421	org/json/JSONException
                                //   113	120	2417	finally
                                //   125	134	2421	org/json/JSONException
                                //   125	134	2417	finally
                                //   139	143	2421	org/json/JSONException
                                //   139	143	2417	finally
                                //   148	152	2421	org/json/JSONException
                                //   148	152	2417	finally
                                //   157	164	2421	org/json/JSONException
                                //   157	164	2417	finally
                                //   169	181	2421	org/json/JSONException
                                //   169	181	2417	finally
                                //   186	193	2421	org/json/JSONException
                                //   186	193	2417	finally
                                //   198	203	2421	org/json/JSONException
                                //   198	203	2417	finally
                                //   208	212	2421	org/json/JSONException
                                //   208	212	2417	finally
                                //   217	221	2421	org/json/JSONException
                                //   217	221	2417	finally
                                //   226	232	2421	org/json/JSONException
                                //   226	232	2417	finally
                                //   237	244	2421	org/json/JSONException
                                //   237	244	2417	finally
                                //   249	261	2421	org/json/JSONException
                                //   249	261	2417	finally
                                //   266	273	2421	org/json/JSONException
                                //   266	273	2417	finally
                                //   278	283	2421	org/json/JSONException
                                //   278	283	2417	finally
                                //   288	292	2421	org/json/JSONException
                                //   288	292	2417	finally
                                //   297	301	2421	org/json/JSONException
                                //   297	301	2417	finally
                                //   306	312	2421	org/json/JSONException
                                //   306	312	2417	finally
                                //   317	324	2421	org/json/JSONException
                                //   317	324	2417	finally
                                //   329	341	2421	org/json/JSONException
                                //   329	341	2417	finally
                                //   346	353	2421	org/json/JSONException
                                //   346	353	2417	finally
                                //   358	363	2421	org/json/JSONException
                                //   358	363	2417	finally
                                //   368	372	2421	org/json/JSONException
                                //   368	372	2417	finally
                                //   377	381	2421	org/json/JSONException
                                //   377	381	2417	finally
                                //   386	392	2421	org/json/JSONException
                                //   386	392	2417	finally
                                //   397	404	2421	org/json/JSONException
                                //   397	404	2417	finally
                                //   409	421	2421	org/json/JSONException
                                //   409	421	2417	finally
                                //   426	433	2421	org/json/JSONException
                                //   426	433	2417	finally
                                //   438	443	2421	org/json/JSONException
                                //   438	443	2417	finally
                                //   448	452	2421	org/json/JSONException
                                //   448	452	2417	finally
                                //   457	461	2421	org/json/JSONException
                                //   457	461	2417	finally
                                //   466	472	2421	org/json/JSONException
                                //   466	472	2417	finally
                                //   477	484	2421	org/json/JSONException
                                //   477	484	2417	finally
                                //   489	501	2421	org/json/JSONException
                                //   489	501	2417	finally
                                //   506	513	2421	org/json/JSONException
                                //   506	513	2417	finally
                                //   518	523	2421	org/json/JSONException
                                //   518	523	2417	finally
                                //   528	532	2421	org/json/JSONException
                                //   528	532	2417	finally
                                //   537	541	2421	org/json/JSONException
                                //   537	541	2417	finally
                                //   546	552	2421	org/json/JSONException
                                //   546	552	2417	finally
                                //   557	564	2421	org/json/JSONException
                                //   557	564	2417	finally
                                //   569	581	2421	org/json/JSONException
                                //   569	581	2417	finally
                                //   586	593	2421	org/json/JSONException
                                //   586	593	2417	finally
                                //   598	603	2421	org/json/JSONException
                                //   598	603	2417	finally
                                //   608	612	2421	org/json/JSONException
                                //   608	612	2417	finally
                                //   617	621	2421	org/json/JSONException
                                //   617	621	2417	finally
                                //   626	632	2421	org/json/JSONException
                                //   626	632	2417	finally
                                //   637	644	2421	org/json/JSONException
                                //   637	644	2417	finally
                                //   649	661	2421	org/json/JSONException
                                //   649	661	2417	finally
                                //   666	673	2421	org/json/JSONException
                                //   666	673	2417	finally
                                //   678	683	2421	org/json/JSONException
                                //   678	683	2417	finally
                                //   688	692	2421	org/json/JSONException
                                //   688	692	2417	finally
                                //   697	701	2421	org/json/JSONException
                                //   697	701	2417	finally
                                //   706	712	2421	org/json/JSONException
                                //   706	712	2417	finally
                                //   717	724	2421	org/json/JSONException
                                //   717	724	2417	finally
                                //   729	741	2421	org/json/JSONException
                                //   729	741	2417	finally
                                //   746	753	2421	org/json/JSONException
                                //   746	753	2417	finally
                                //   758	763	2421	org/json/JSONException
                                //   758	763	2417	finally
                                //   768	772	2421	org/json/JSONException
                                //   768	772	2417	finally
                                //   777	781	2421	org/json/JSONException
                                //   777	781	2417	finally
                                //   786	792	2421	org/json/JSONException
                                //   786	792	2417	finally
                                //   797	804	2421	org/json/JSONException
                                //   797	804	2417	finally
                                //   809	821	2421	org/json/JSONException
                                //   809	821	2417	finally
                                //   826	833	2421	org/json/JSONException
                                //   826	833	2417	finally
                                //   838	843	2421	org/json/JSONException
                                //   838	843	2417	finally
                                //   848	852	2421	org/json/JSONException
                                //   848	852	2417	finally
                                //   857	861	2421	org/json/JSONException
                                //   857	861	2417	finally
                                //   866	872	2421	org/json/JSONException
                                //   866	872	2417	finally
                                //   877	884	2421	org/json/JSONException
                                //   877	884	2417	finally
                                //   889	901	2421	org/json/JSONException
                                //   889	901	2417	finally
                                //   906	913	2421	org/json/JSONException
                                //   906	913	2417	finally
                                //   918	923	2421	org/json/JSONException
                                //   918	923	2417	finally
                                //   928	932	2421	org/json/JSONException
                                //   928	932	2417	finally
                                //   937	941	2421	org/json/JSONException
                                //   937	941	2417	finally
                                //   946	952	2421	org/json/JSONException
                                //   946	952	2417	finally
                                //   957	964	2421	org/json/JSONException
                                //   957	964	2417	finally
                                //   969	981	2421	org/json/JSONException
                                //   969	981	2417	finally
                                //   986	993	2421	org/json/JSONException
                                //   986	993	2417	finally
                                //   998	1003	2421	org/json/JSONException
                                //   998	1003	2417	finally
                                //   1008	1012	2421	org/json/JSONException
                                //   1008	1012	2417	finally
                                //   1017	1021	2421	org/json/JSONException
                                //   1017	1021	2417	finally
                                //   1026	1032	2421	org/json/JSONException
                                //   1026	1032	2417	finally
                                //   1037	1044	2421	org/json/JSONException
                                //   1037	1044	2417	finally
                                //   1049	1061	2421	org/json/JSONException
                                //   1049	1061	2417	finally
                                //   1066	1073	2421	org/json/JSONException
                                //   1066	1073	2417	finally
                                //   1078	1083	2421	org/json/JSONException
                                //   1078	1083	2417	finally
                                //   1088	1092	2421	org/json/JSONException
                                //   1088	1092	2417	finally
                                //   1097	1101	2421	org/json/JSONException
                                //   1097	1101	2417	finally
                                //   1106	1112	2421	org/json/JSONException
                                //   1106	1112	2417	finally
                                //   1117	1124	2421	org/json/JSONException
                                //   1117	1124	2417	finally
                                //   1129	1141	2421	org/json/JSONException
                                //   1129	1141	2417	finally
                                //   1146	1153	2421	org/json/JSONException
                                //   1146	1153	2417	finally
                                //   1158	1163	2421	org/json/JSONException
                                //   1158	1163	2417	finally
                                //   1168	1172	2421	org/json/JSONException
                                //   1168	1172	2417	finally
                                //   1177	1181	2421	org/json/JSONException
                                //   1177	1181	2417	finally
                                //   1186	1192	2421	org/json/JSONException
                                //   1186	1192	2417	finally
                                //   1197	1204	2421	org/json/JSONException
                                //   1197	1204	2417	finally
                                //   1209	1221	2421	org/json/JSONException
                                //   1209	1221	2417	finally
                                //   1226	1233	2421	org/json/JSONException
                                //   1226	1233	2417	finally
                                //   1238	1243	2421	org/json/JSONException
                                //   1238	1243	2417	finally
                                //   1248	1252	2421	org/json/JSONException
                                //   1248	1252	2417	finally
                                //   1257	1261	2421	org/json/JSONException
                                //   1257	1261	2417	finally
                                //   1266	1272	2421	org/json/JSONException
                                //   1266	1272	2417	finally
                                //   1277	1284	2421	org/json/JSONException
                                //   1277	1284	2417	finally
                                //   1289	1301	2421	org/json/JSONException
                                //   1289	1301	2417	finally
                                //   1306	1313	2421	org/json/JSONException
                                //   1306	1313	2417	finally
                                //   1318	1323	2421	org/json/JSONException
                                //   1318	1323	2417	finally
                                //   1328	1332	2421	org/json/JSONException
                                //   1328	1332	2417	finally
                                //   1337	1341	2421	org/json/JSONException
                                //   1337	1341	2417	finally
                                //   1346	1352	2421	org/json/JSONException
                                //   1346	1352	2417	finally
                                //   1357	1364	2421	org/json/JSONException
                                //   1357	1364	2417	finally
                                //   1369	1381	2421	org/json/JSONException
                                //   1369	1381	2417	finally
                                //   1386	1393	2421	org/json/JSONException
                                //   1386	1393	2417	finally
                                //   1398	1403	2421	org/json/JSONException
                                //   1398	1403	2417	finally
                                //   1408	1412	2421	org/json/JSONException
                                //   1408	1412	2417	finally
                                //   1417	1421	2421	org/json/JSONException
                                //   1417	1421	2417	finally
                                //   1426	1432	2421	org/json/JSONException
                                //   1426	1432	2417	finally
                                //   1437	1444	2421	org/json/JSONException
                                //   1437	1444	2417	finally
                                //   1449	1461	2421	org/json/JSONException
                                //   1449	1461	2417	finally
                                //   1466	1473	2421	org/json/JSONException
                                //   1466	1473	2417	finally
                                //   1478	1483	2421	org/json/JSONException
                                //   1478	1483	2417	finally
                                //   1488	1492	2421	org/json/JSONException
                                //   1488	1492	2417	finally
                                //   1497	1501	2421	org/json/JSONException
                                //   1497	1501	2417	finally
                                //   1506	1512	2421	org/json/JSONException
                                //   1506	1512	2417	finally
                                //   1517	1524	2421	org/json/JSONException
                                //   1517	1524	2417	finally
                                //   1529	1541	2421	org/json/JSONException
                                //   1529	1541	2417	finally
                                //   1546	1553	2421	org/json/JSONException
                                //   1546	1553	2417	finally
                                //   1558	1563	2421	org/json/JSONException
                                //   1558	1563	2417	finally
                                //   1568	1572	2421	org/json/JSONException
                                //   1568	1572	2417	finally
                                //   1577	1581	2421	org/json/JSONException
                                //   1577	1581	2417	finally
                                //   1586	1592	2421	org/json/JSONException
                                //   1586	1592	2417	finally
                                //   1597	1604	2421	org/json/JSONException
                                //   1597	1604	2417	finally
                                //   1609	1621	2421	org/json/JSONException
                                //   1609	1621	2417	finally
                                //   1626	1633	2421	org/json/JSONException
                                //   1626	1633	2417	finally
                                //   1638	1643	2421	org/json/JSONException
                                //   1638	1643	2417	finally
                                //   1648	1652	2421	org/json/JSONException
                                //   1648	1652	2417	finally
                                //   1657	1661	2421	org/json/JSONException
                                //   1657	1661	2417	finally
                                //   1666	1672	2421	org/json/JSONException
                                //   1666	1672	2417	finally
                                //   1677	1684	2421	org/json/JSONException
                                //   1677	1684	2417	finally
                                //   1689	1701	2421	org/json/JSONException
                                //   1689	1701	2417	finally
                                //   1706	1713	2421	org/json/JSONException
                                //   1706	1713	2417	finally
                                //   1718	1723	2421	org/json/JSONException
                                //   1718	1723	2417	finally
                                //   1728	1732	2421	org/json/JSONException
                                //   1728	1732	2417	finally
                                //   1737	1741	2421	org/json/JSONException
                                //   1737	1741	2417	finally
                                //   1746	1752	2421	org/json/JSONException
                                //   1746	1752	2417	finally
                                //   1757	1764	2421	org/json/JSONException
                                //   1757	1764	2417	finally
                                //   1769	1781	2421	org/json/JSONException
                                //   1769	1781	2417	finally
                                //   1786	1793	2421	org/json/JSONException
                                //   1786	1793	2417	finally
                                //   1798	1803	2421	org/json/JSONException
                                //   1798	1803	2417	finally
                                //   1808	1812	2421	org/json/JSONException
                                //   1808	1812	2417	finally
                                //   1817	1821	2421	org/json/JSONException
                                //   1817	1821	2417	finally
                                //   1826	1832	2421	org/json/JSONException
                                //   1826	1832	2417	finally
                                //   1837	1844	2421	org/json/JSONException
                                //   1837	1844	2417	finally
                                //   1849	1861	2421	org/json/JSONException
                                //   1849	1861	2417	finally
                                //   1866	1873	2421	org/json/JSONException
                                //   1866	1873	2417	finally
                                //   1878	1883	2421	org/json/JSONException
                                //   1878	1883	2417	finally
                                //   1888	1892	2421	org/json/JSONException
                                //   1888	1892	2417	finally
                                //   1897	1901	2421	org/json/JSONException
                                //   1897	1901	2417	finally
                                //   1906	1912	2421	org/json/JSONException
                                //   1906	1912	2417	finally
                                //   1917	1924	2421	org/json/JSONException
                                //   1917	1924	2417	finally
                                //   1929	1941	2421	org/json/JSONException
                                //   1929	1941	2417	finally
                                //   1946	1953	2421	org/json/JSONException
                                //   1946	1953	2417	finally
                                //   1958	1963	2421	org/json/JSONException
                                //   1958	1963	2417	finally
                                //   1968	1972	2421	org/json/JSONException
                                //   1968	1972	2417	finally
                                //   1977	1981	2421	org/json/JSONException
                                //   1977	1981	2417	finally
                                //   1986	1992	2421	org/json/JSONException
                                //   1986	1992	2417	finally
                                //   1997	2004	2421	org/json/JSONException
                                //   1997	2004	2417	finally
                                //   2009	2021	2421	org/json/JSONException
                                //   2009	2021	2417	finally
                                //   2026	2033	2421	org/json/JSONException
                                //   2026	2033	2417	finally
                                //   2038	2043	2421	org/json/JSONException
                                //   2038	2043	2417	finally
                                //   2048	2052	2421	org/json/JSONException
                                //   2048	2052	2417	finally
                                //   2057	2061	2421	org/json/JSONException
                                //   2057	2061	2417	finally
                                //   2066	2072	2421	org/json/JSONException
                                //   2066	2072	2417	finally
                                //   2077	2084	2421	org/json/JSONException
                                //   2077	2084	2417	finally
                                //   2089	2101	2421	org/json/JSONException
                                //   2089	2101	2417	finally
                                //   2106	2113	2421	org/json/JSONException
                                //   2106	2113	2417	finally
                                //   2118	2123	2421	org/json/JSONException
                                //   2118	2123	2417	finally
                                //   2128	2132	2421	org/json/JSONException
                                //   2128	2132	2417	finally
                                //   2137	2141	2421	org/json/JSONException
                                //   2137	2141	2417	finally
                                //   2146	2152	2421	org/json/JSONException
                                //   2146	2152	2417	finally
                                //   2157	2164	2421	org/json/JSONException
                                //   2157	2164	2417	finally
                                //   2169	2181	2421	org/json/JSONException
                                //   2169	2181	2417	finally
                                //   2186	2193	2421	org/json/JSONException
                                //   2186	2193	2417	finally
                                //   2198	2203	2421	org/json/JSONException
                                //   2198	2203	2417	finally
                                //   2208	2212	2421	org/json/JSONException
                                //   2208	2212	2417	finally
                                //   2217	2221	2421	org/json/JSONException
                                //   2217	2221	2417	finally
                                //   2226	2232	2421	org/json/JSONException
                                //   2226	2232	2417	finally
                                //   2237	2244	2421	org/json/JSONException
                                //   2237	2244	2417	finally
                                //   2249	2261	2421	org/json/JSONException
                                //   2249	2261	2417	finally
                                //   2266	2273	2421	org/json/JSONException
                                //   2266	2273	2417	finally
                                //   2278	2283	2421	org/json/JSONException
                                //   2278	2283	2417	finally
                                //   2425	2429	2417	finally
                            }
                        };
                        super(this, str1);
                        Response.ErrorListener errorListener = new Response.ErrorListener() {
                            public void onErrorResponse(VolleyError param2VolleyError) {}
                        };
                        super(this);
                        super(this, 1, str2, listener, errorListener);
                        Volley.newRequestQueue((Context)Frag_Database.this.getActivity()).add((Request)stringRequest);
                    } catch (Exception exception) {
                        Toast.makeText((Context)Frag_Database.this.getActivity(), "Kitra knm, 1).show();
                    }
            }
        });
        this.mWebView = (WebView)view.findViewById(2131230979);
        this.Imei = ((TelephonyManager)getActivity().getSystemService("phone")).getDeviceId();
        if (isNetworkConnected() && this.Imei != null)
            check();
        this.btn_tt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                // Byte code:
                //   0: new tamhoang/ldpro4/MainActivity
                //   3: dup
                //   4: invokespecial <init> : ()V
                //   7: pop
                //   8: invokestatic Get_date : ()Ljava/lang/String;
                //   11: astore_2
                //   12: invokestatic Get_ngay : ()Ljava/lang/String;
                //   15: astore_1
                //   16: aload_0
                //   17: getfield this$0 : Ltamhoang/ldpro4/Fragment/Frag_Database;
                //   20: getfield db : Ltamhoang/ldpro4/data/Database;
                //   23: astore_3
                //   24: new java/lang/StringBuilder
                //   27: dup
                //   28: invokespecial <init> : ()V
                //   31: astore #4
                //   33: aload #4
                //   35: ldc 'Select * From Ketqua WHERE ngay = ''
                //   37: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   40: pop
                //   41: aload #4
                //   43: aload_2
                //   44: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   47: pop
                //   48: aload #4
                //   50: ldc '''
                //   52: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   55: pop
                //   56: aload_3
                //   57: aload #4
                //   59: invokevirtual toString : ()Ljava/lang/String;
                //   62: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
                //   65: astore #4
                //   67: aload #4
                //   69: invokeinterface moveToFirst : ()Z
                //   74: pop
                //   75: iconst_2
                //   76: istore #5
                //   78: iload #5
                //   80: bipush #29
                //   82: if_icmpge -> 125
                //   85: aload #4
                //   87: iload #5
                //   89: invokeinterface isNull : (I)Z
                //   94: ifne -> 125
                //   97: aload #4
                //   99: iload #5
                //   101: invokeinterface getString : (I)Ljava/lang/String;
                //   106: invokestatic isNumeric : (Ljava/lang/String;)Z
                //   109: ifne -> 115
                //   112: goto -> 125
                //   115: iinc #5, 1
                //   118: goto -> 78
                //   121: astore_2
                //   122: goto -> 238
                //   125: iload #5
                //   127: bipush #29
                //   129: if_icmplt -> 187
                //   132: aload_0
                //   133: getfield this$0 : Ltamhoang/ldpro4/Fragment/Frag_Database;
                //   136: getfield db : Ltamhoang/ldpro4/data/Database;
                //   139: aload_2
                //   140: invokevirtual Tinhtien : (Ljava/lang/String;)V
                //   143: aload_0
                //   144: getfield this$0 : Ltamhoang/ldpro4/Fragment/Frag_Database;
                //   147: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
                //   150: astore_3
                //   151: new java/lang/StringBuilder
                //   154: astore_2
                //   155: aload_2
                //   156: invokespecial <init> : ()V
                //   159: aload_2
                //   160: ldc 'ttixong ng'
                //   162: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   165: pop
                //   166: aload_2
                //   167: aload_1
                //   168: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   171: pop
                //   172: aload_3
                //   173: aload_2
                //   174: invokevirtual toString : ()Ljava/lang/String;
                //   177: iconst_1
                //   178: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
                //   181: invokevirtual show : ()V
                //   184: goto -> 235
                //   187: aload_0
                //   188: getfield this$0 : Ltamhoang/ldpro4/Fragment/Frag_Database;
                //   191: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
                //   194: astore_2
                //   195: new java/lang/StringBuilder
                //   198: astore_3
                //   199: aload_3
                //   200: invokespecial <init> : ()V
                //   203: aload_3
                //   204: ldc 'Chckqung'
                //   206: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   209: pop
                //   210: aload_3
                //   211: aload_1
                //   212: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   215: pop
                //   216: aload_3
                //   217: ldc ' hcnhthc
                //   219: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   222: pop
                //   223: aload_2
                //   224: aload_3
                //   225: invokevirtual toString : ()Ljava/lang/String;
                //   228: iconst_1
                //   229: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
                //   232: invokevirtual show : ()V
                //   235: goto -> 279
                //   238: aload_0
                //   239: getfield this$0 : Ltamhoang/ldpro4/Fragment/Frag_Database;
                //   242: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
                //   245: astore_2
                //   246: new java/lang/StringBuilder
                //   249: dup
                //   250: invokespecial <init> : ()V
                //   253: astore_3
                //   254: aload_3
                //   255: ldc 'Chckqung'
                //   257: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   260: pop
                //   261: aload_3
                //   262: aload_1
                //   263: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   266: pop
                //   267: aload_2
                //   268: aload_3
                //   269: invokevirtual toString : ()Ljava/lang/String;
                //   272: iconst_1
                //   273: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
                //   276: invokevirtual show : ()V
                //   279: aload #4
                //   281: ifnull -> 301
                //   284: aload #4
                //   286: invokeinterface isClosed : ()Z
                //   291: ifne -> 301
                //   294: aload #4
                //   296: invokeinterface close : ()V
                //   301: return
                // Exception table:
                //   from	to	target	type
                //   85	112	121	java/lang/Exception
                //   132	184	121	java/lang/Exception
                //   187	235	121	java/lang/Exception
            }
        });
        this.btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                new MainActivity();
                final String mDate = MainActivity.Get_date();
                String[] arrayOfString = new String[3];
                arrayOfString[0] = "Xvllcn;
                arrayOfString[1] = "Xhcsdli;
                arrayOfString[2] = "Xhdlihnay";
                PopupMenu popupMenu = new PopupMenu((Context)Frag_Database.this.getActivity(), param1View);
                for (byte b = 0; b < arrayOfString.length; b++)
                    popupMenu.getMenu().add(1, b, b, arrayOfString[b]);
                new AlertDialog.Builder((Context)Frag_Database.this.getActivity());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem param2MenuItem) {
                        int i = param2MenuItem.getOrder();
                        if (i != 0) {
                            if (i != 1) {
                                if (i == 2) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_Database.this.getActivity());
                                    builder.setTitle("Xohdlihnay?");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface param3DialogInterface, int param3Int) {
                                            StringBuilder stringBuilder3 = new StringBuilder();
                                            stringBuilder3.append("DELETE FROM tbl_soctS WHERE ngay_nhan = '");
                                            stringBuilder3.append(mDate);
                                            stringBuilder3.append("'");
                                            String str2 = stringBuilder3.toString();
                                            Frag_Database.this.db.QueryData(str2);
                                            StringBuilder stringBuilder2 = new StringBuilder();
                                            stringBuilder2.append("DELETE FROM tbl_tinnhanS WHERE ngay_nhan = '");
                                            stringBuilder2.append(mDate);
                                            stringBuilder2.append("'");
                                            String str1 = stringBuilder2.toString();
                                            Frag_Database.this.db.QueryData(str1);
                                            Database database = Frag_Database.this.db;
                                            StringBuilder stringBuilder1 = new StringBuilder();
                                            stringBuilder1.append("DELETE FROM Chat_database WHERE ngay_nhan = '");
                                            stringBuilder1.append(mDate);
                                            stringBuilder1.append("'");
                                            database.QueryData(stringBuilder1.toString());
                                            Toast.makeText((Context)Frag_Database.this.getActivity(), "xo, 1).show();
                                        }
                                    });
                                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface param3DialogInterface, int param3Int) {
                                            param3DialogInterface.cancel();
                                        }
                                    });
                                    builder.create().show();
                                }
                            } else {
                                Frag_Database.this.DelAllSQL();
                            }
                        } else {
                            Frag_Database.this.DelAllSQL_Congno();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        this.minhngoc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                Frag_Database.this.gr1.clearCheck();
                if (Frag_Database.this.minhngoc.isChecked())
                    Frag_Database.this.DisplayKQnet();
            }
        });
        this.ketquanet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                Frag_Database.this.gr2.clearCheck();
                if (Frag_Database.this.ketquanet.isChecked())
                    Frag_Database.this.DisplayKQnetNew();
            }
        });
        this.xosome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                Frag_Database.this.gr1.clearCheck();
                if (Frag_Database.this.xosome.isChecked())
                    Frag_Database.this.DisplayXSme();
            }
        });
        this.xsme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                Frag_Database.this.gr2.clearCheck();
                if (Frag_Database.this.xsme.isChecked())
                    Frag_Database.this.DisplayXSmeNew();
            }
        });
        this.xsmn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                Frag_Database.this.gr2.clearCheck();
                if (Frag_Database.this.xsmn.isChecked())
                    Frag_Database.this.DisplayXSMNNew();
            }
        });
        this.mWebView.addJavascriptInterface(new NotificationBindObject(getActivity().getApplicationContext()), "NotificationBind");
        setUpWebViewDefaults(this.mWebView);
        if (paramBundle != null)
            this.mWebView.restoreState(paramBundle);
        DisplayXSmeNew();
        return view;
    }

    public void onStop() {
        super.onStop();
        this.mWebView.clearCache(true);
    }
}
