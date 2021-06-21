package tamhoang.ldpro4;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TdApi;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Activity.Activity_ChuyenThang;
import tamhoang.ldpro4.Activity.Activity_GiuSo;
import tamhoang.ldpro4.Activity.Activity_thaythe;
import tamhoang.ldpro4.Fragment.Frag_CanChuyen;
import tamhoang.ldpro4.Fragment.Frag_Chat_Manager;
import tamhoang.ldpro4.Fragment.Frag_Database;
import tamhoang.ldpro4.Fragment.Frag_Home;
import tamhoang.ldpro4.Fragment.Frag_MoRP1;
import tamhoang.ldpro4.Fragment.Frag_No_new;
import tamhoang.ldpro4.Fragment.Frag_No_old;
import tamhoang.ldpro4.Fragment.Frag_SMS_Templates;
import tamhoang.ldpro4.Fragment.Frag_Setting1;
import tamhoang.ldpro4.Fragment.Frag_Setting3;
import tamhoang.ldpro4.Fragment.Livestream;
import tamhoang.ldpro4.Fragment.Tab_ChayTrang;
import tamhoang.ldpro4.Fragment.Tab_Tinnhan;
import tamhoang.ldpro4.Fragment.TructiepXoso;
import tamhoang.ldpro4.Telegram.TelegramClient;
import tamhoang.ldpro4.data.Contact;
import tamhoang.ldpro4.data.Database;

public class MainActivity extends AppCompatActivity implements TelegramClient.Callback {
    public static String Acc_manager;

    public static ArrayList<String> DSkhachhang;

    public static JSONObject Json_Chat_Telegram;

    public static JSONObject Json_Tinnhan;

    public static Context Main_activity;

    public static String MyToken;

    public static NotificationReader Notifi;

    static int TIME_REMOVE;

    public static final int TIPO_DIALOGO = 0;

    public static ArrayList<String> arr_TenKH;

    public static Client client;

    public static ArrayList<Contact> contactslist;

    public static Context context;

    public static ArrayList<HashMap<String, String>> formArray;

    public static ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();

    public static Handler handler;

    public static JSONObject jSon_Setting;

    public static JSONObject json_Tinnhan;

    public static List<Fragment> listFragments;

    public static JSONObject listKH;

    public static int mDay;

    public static int mMonth;

    public static int mYear;

    public static String myDate;

    private static DatePickerDialog.OnDateSetListener onDateSetListener;

    static Runnable runnable;

    public static boolean sms;

    TextView Text_Menu;

    TextView Text_date;

    ActionBarDrawerToggle actionBarDrawerToggle;

    Database db;

    DrawerLayout drawerLayout;

    RelativeLayout drawerPane;

    String insertData;

    List<NavItem> listNavItems;

    ListView lvNav;

    String my_id = "";

    String viewData;

    static {
        formArray = new ArrayList<HashMap<String, String>>();
        DSkhachhang = new ArrayList<String>();
        arr_TenKH = new ArrayList<String>();
        contactslist = new ArrayList<Contact>();
        Notifi = null;
        Json_Tinnhan = new JSONObject();
        sms = false;
        json_Tinnhan = new JSONObject();
        myDate = "";
        Acc_manager = "";
        TIME_REMOVE = 0;
        Json_Chat_Telegram = new JSONObject();
        MyToken = "";
        runnable = new Runnable() {
            public void run() {
                try {
                    Iterator<String> iterator = MainActivity.json_Tinnhan.keys();
                    while (iterator.hasNext()) {
                        String str = iterator.next();
                        JSONObject jSONObject = new JSONObject();
                        this(MainActivity.json_Tinnhan.getString(str));
                        jSONObject.put("Time", jSONObject.getInt("Time") + 1);
                        MainActivity.json_Tinnhan.put(str, jSONObject.toString());
                        if (jSONObject.getInt("Time") > 3 && jSONObject.length() > 1) {
                            Iterator<String> iterator1 = jSONObject.keys();
                            while (iterator1.hasNext()) {
                                String str1 = iterator1.next();
                                if (str1.indexOf("Time") == -1) {
                                    NotificationReader notificationReader = new NotificationReader();
                                    this();
                                    if (Build.VERSION.SDK_INT >= 20)
                                        notificationReader.NotificationWearReader(str, str1);
                                }
                            }
                            jSONObject = new JSONObject();
                            this();
                            jSONObject.put("Time", 0);
                            MainActivity.json_Tinnhan.put(str, jSONObject.toString());
                            continue;
                        }
                        if (jSONObject.getInt("Time") > 100) {
                            MainActivity.json_Tinnhan.remove(str);
                            break;
                        }
                    }
                } catch (JSONException jSONException) {
                    jSONException.printStackTrace();
                }
                if (MainActivity.json_Tinnhan.length() == 0) {
                    MainActivity.TIME_REMOVE++;
                } else {
                    MainActivity.TIME_REMOVE = 0;
                }
                if (MainActivity.TIME_REMOVE < 100) {
                    MainActivity.handler.postDelayed(this, 1000L);
                } else {
                    MainActivity.handler.removeCallbacks(MainActivity.runnable);
                    MainActivity.handler = null;
                }
            }
        };
    }

    public static String Get_date() {
        String str;
        if (mDay < 10 && mMonth + 1 < 10) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mYear);
            stringBuilder.append("-0");
            stringBuilder.append(mMonth + 1);
            stringBuilder.append("-0");
            stringBuilder.append(mDay);
            str = stringBuilder.toString();
        } else if (mDay < 10) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mYear);
            stringBuilder.append("-");
            stringBuilder.append(mMonth + 1);
            stringBuilder.append("-0");
            stringBuilder.append(mDay);
            str = stringBuilder.toString();
        } else if (mMonth + 1 < 10) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mYear);
            stringBuilder.append("-0");
            stringBuilder.append(mMonth + 1);
            stringBuilder.append("-");
            stringBuilder.append(mDay);
            str = stringBuilder.toString();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mYear);
            stringBuilder.append("-");
            stringBuilder.append(mMonth + 1);
            stringBuilder.append("-");
            stringBuilder.append(mDay);
            str = stringBuilder.toString();
        }
        return str;
    }

    public static String Get_ngay() {
        String str;
        if (mDay < 10 && mMonth + 1 < 10) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("0");
            stringBuilder.append(mDay);
            stringBuilder.append("/0");
            stringBuilder.append(mMonth + 1);
            stringBuilder.append("/");
            stringBuilder.append(mYear);
            str = stringBuilder.toString();
        } else if (mDay < 10) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("0");
            stringBuilder.append(mDay);
            stringBuilder.append("/");
            stringBuilder.append(mMonth + 1);
            stringBuilder.append("/");
            stringBuilder.append(mYear);
            str = stringBuilder.toString();
        } else if (mMonth + 1 < 10) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mDay);
            stringBuilder.append("/0");
            stringBuilder.append(mMonth + 1);
            stringBuilder.append("/");
            stringBuilder.append(mYear);
            str = stringBuilder.toString();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mDay);
            stringBuilder.append("/");
            stringBuilder.append(mMonth + 1);
            stringBuilder.append("/");
            stringBuilder.append(mYear);
            str = stringBuilder.toString();
        }
        return str;
    }

    public static String Get_ngay_new() {
        String str;
        if (mDay < 10 && mMonth + 1 < 10) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("0");
            stringBuilder.append(mDay);
            stringBuilder.append("/0");
            stringBuilder.append(mMonth + 1);
            stringBuilder.append("/");
            stringBuilder.append(mYear);
            str = stringBuilder.toString();
        } else if (mDay < 10) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("0");
            stringBuilder.append(mDay);
            stringBuilder.append("/");
            stringBuilder.append(mMonth + 1);
            stringBuilder.append("/");
            stringBuilder.append(mYear);
            str = stringBuilder.toString();
        } else if (mMonth + 1 < 10) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mDay);
            stringBuilder.append("/0");
            stringBuilder.append(mMonth + 1);
            stringBuilder.append("/");
            stringBuilder.append(mYear);
            str = stringBuilder.toString();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mDay);
            stringBuilder.append("/");
            stringBuilder.append(mMonth + 1);
            stringBuilder.append("/");
            stringBuilder.append(mYear);
            str = stringBuilder.toString();
        }
        return str;
    }

    private void Xulytin(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt) {
        // Byte code:
        //   0: getstatic tamhoang/ldpro4/MainActivity.DSkhachhang : Ljava/util/ArrayList;
        //   3: aload_1
        //   4: invokevirtual indexOf : (Ljava/lang/Object;)I
        //   7: iconst_m1
        //   8: if_icmple -> 38
        //   11: aload_2
        //   12: ldc 'Ok'
        //   14: invokevirtual indexOf : (Ljava/lang/String;)I
        //   17: ifeq -> 38
        //   20: aload_2
        //   21: ldc 'B
        //   23: invokevirtual indexOf : (Ljava/lang/String;)I
        //   26: ifeq -> 38
        //   29: aload_2
        //   30: ldc 'Thi
        //   32: invokevirtual indexOf : (Ljava/lang/String;)I
        //   35: ifne -> 48
        //   38: aload_2
        //   39: ldc 'Tra lai'
        //   41: invokevirtual indexOf : (Ljava/lang/String;)I
        //   44: iconst_m1
        //   45: if_icmple -> 1366
        //   48: iconst_1
        //   49: putstatic tamhoang/ldpro4/MainActivity.sms : Z
        //   52: aconst_null
        //   53: astore #6
        //   55: aconst_null
        //   56: astore #7
        //   58: new java/lang/StringBuilder
        //   61: dup
        //   62: invokespecial <init> : ()V
        //   65: astore #8
        //   67: aload #8
        //   69: ldc 'Select * FROM tbl_kh_new WHERE sdt =''
        //   71: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   74: pop
        //   75: aload #8
        //   77: aload_1
        //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   81: pop
        //   82: aload #8
        //   84: ldc '''
        //   86: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   89: pop
        //   90: aload #8
        //   92: invokevirtual toString : ()Ljava/lang/String;
        //   95: astore #8
        //   97: aload_0
        //   98: getfield db : Ltamhoang/ldpro4/data/Database;
        //   101: aload #8
        //   103: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   106: astore #9
        //   108: aload #9
        //   110: invokeinterface moveToFirst : ()Z
        //   115: pop
        //   116: aload #6
        //   118: astore #8
        //   120: new org/json/JSONObject
        //   123: astore #10
        //   125: aload #6
        //   127: astore #8
        //   129: aload #10
        //   131: aload #9
        //   133: iconst_5
        //   134: invokeinterface getString : (I)Ljava/lang/String;
        //   139: invokespecial <init> : (Ljava/lang/String;)V
        //   142: aload #10
        //   144: astore #6
        //   146: aload #6
        //   148: astore #8
        //   150: aload #6
        //   152: ldc 'caidat_tg'
        //   154: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   157: astore #10
        //   159: aload #10
        //   161: astore #8
        //   163: aload #6
        //   165: astore #7
        //   167: aload #8
        //   169: astore #6
        //   171: goto -> 189
        //   174: astore #6
        //   176: aload #6
        //   178: invokevirtual printStackTrace : ()V
        //   181: aload #7
        //   183: astore #6
        //   185: aload #8
        //   187: astore #7
        //   189: aload #6
        //   191: ldc 'tg_debc'
        //   193: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   196: invokestatic CheckTime : (Ljava/lang/String;)Z
        //   199: istore #11
        //   201: iload #11
        //   203: ifne -> 1027
        //   206: new java/lang/StringBuilder
        //   209: astore #8
        //   211: aload #8
        //   213: invokespecial <init> : ()V
        //   216: aload #8
        //   218: ldc 'Select max(so_tin_nhan) from tbl_tinnhanS WHERE ngay_nhan = ''
        //   220: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   223: pop
        //   224: aload #8
        //   226: aload_3
        //   227: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   230: pop
        //   231: aload #8
        //   233: ldc '' AND so_dienthoai = ''
        //   235: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   238: pop
        //   239: aload #8
        //   241: aload_1
        //   242: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   245: pop
        //   246: aload #8
        //   248: ldc '' AND type_kh = 1'
        //   250: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   253: pop
        //   254: aload #8
        //   256: invokevirtual toString : ()Ljava/lang/String;
        //   259: astore #8
        //   261: aload_0
        //   262: getfield db : Ltamhoang/ldpro4/data/Database;
        //   265: aload #8
        //   267: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   270: astore #8
        //   272: aload #8
        //   274: invokeinterface moveToFirst : ()Z
        //   279: pop
        //   280: aload #9
        //   282: iconst_0
        //   283: invokeinterface getString : (I)Ljava/lang/String;
        //   288: astore #6
        //   290: aload #8
        //   292: iconst_0
        //   293: invokeinterface getInt : (I)I
        //   298: iconst_1
        //   299: iadd
        //   300: istore #12
        //   302: aload_2
        //   303: ldc 'Tra lai'
        //   305: invokevirtual indexOf : (Ljava/lang/String;)I
        //   308: istore #13
        //   310: iload #13
        //   312: iconst_m1
        //   313: if_icmpne -> 484
        //   316: new java/lang/StringBuilder
        //   319: astore #7
        //   321: aload #7
        //   323: invokespecial <init> : ()V
        //   326: aload #7
        //   328: ldc_w 'Insert Into tbl_tinnhanS values (null, ''
        //   331: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   334: pop
        //   335: aload #7
        //   337: aload_3
        //   338: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   341: pop
        //   342: aload #7
        //   344: ldc_w '', ''
        //   347: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   350: pop
        //   351: aload #7
        //   353: aload #4
        //   355: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   358: pop
        //   359: aload #7
        //   361: ldc_w '','
        //   364: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   367: pop
        //   368: aload #7
        //   370: iload #5
        //   372: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   375: pop
        //   376: aload #7
        //   378: ldc_w ', ''
        //   381: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   384: pop
        //   385: aload #7
        //   387: aload #6
        //   389: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   392: pop
        //   393: aload #7
        //   395: ldc_w '', ''
        //   398: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   401: pop
        //   402: aload #7
        //   404: aload #9
        //   406: iconst_1
        //   407: invokeinterface getString : (I)Ljava/lang/String;
        //   412: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   415: pop
        //   416: aload #7
        //   418: ldc_w '','TL', '
        //   421: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   424: pop
        //   425: aload #7
        //   427: iload #12
        //   429: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   432: pop
        //   433: aload #7
        //   435: ldc_w ', ''
        //   438: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   441: pop
        //   442: aload #7
        //   444: aload_2
        //   445: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   448: pop
        //   449: aload #7
        //   451: ldc_w '',null,''
        //   454: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   457: pop
        //   458: aload #7
        //   460: aload_2
        //   461: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   464: pop
        //   465: aload #7
        //   467: ldc_w '', 'ko',0,1,1, null)'
        //   470: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   473: pop
        //   474: aload #7
        //   476: invokevirtual toString : ()Ljava/lang/String;
        //   479: astore #4
        //   481: goto -> 649
        //   484: new java/lang/StringBuilder
        //   487: astore #7
        //   489: aload #7
        //   491: invokespecial <init> : ()V
        //   494: aload #7
        //   496: ldc_w 'Insert Into tbl_tinnhanS values (null, ''
        //   499: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   502: pop
        //   503: aload #7
        //   505: aload_3
        //   506: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   509: pop
        //   510: aload #7
        //   512: ldc_w '', ''
        //   515: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   518: pop
        //   519: aload #7
        //   521: aload #4
        //   523: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   526: pop
        //   527: aload #7
        //   529: ldc_w '','
        //   532: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   535: pop
        //   536: aload #7
        //   538: iload #5
        //   540: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   543: pop
        //   544: aload #7
        //   546: ldc_w ', ''
        //   549: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   552: pop
        //   553: aload #7
        //   555: aload #6
        //   557: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   560: pop
        //   561: aload #7
        //   563: ldc_w '', ''
        //   566: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   569: pop
        //   570: aload #7
        //   572: aload #9
        //   574: iconst_1
        //   575: invokeinterface getString : (I)Ljava/lang/String;
        //   580: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   583: pop
        //   584: aload #7
        //   586: ldc_w '','TL', '
        //   589: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   592: pop
        //   593: aload #7
        //   595: iload #12
        //   597: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   600: pop
        //   601: aload #7
        //   603: ldc_w ', ''
        //   606: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   609: pop
        //   610: aload #7
        //   612: aload_2
        //   613: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   616: pop
        //   617: aload #7
        //   619: ldc_w '',null,''
        //   622: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   625: pop
        //   626: aload #7
        //   628: aload_2
        //   629: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   632: pop
        //   633: aload #7
        //   635: ldc_w '', 'ko',0,0,0, null)'
        //   638: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   641: pop
        //   642: aload #7
        //   644: invokevirtual toString : ()Ljava/lang/String;
        //   647: astore #4
        //   649: aload_0
        //   650: getfield db : Ltamhoang/ldpro4/data/Database;
        //   653: aload #4
        //   655: invokevirtual QueryData : (Ljava/lang/String;)V
        //   658: getstatic tamhoang/ldpro4/MainActivity.myDate : Ljava/lang/String;
        //   661: invokestatic CheckDate : (Ljava/lang/String;)Z
        //   664: ifeq -> 979
        //   667: aload_0
        //   668: getfield db : Ltamhoang/ldpro4/data/Database;
        //   671: astore #4
        //   673: new java/lang/StringBuilder
        //   676: astore #6
        //   678: aload #6
        //   680: invokespecial <init> : ()V
        //   683: aload #6
        //   685: ldc_w 'Select * from tbl_tinnhanS WHERE ngay_nhan = ''
        //   688: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   691: pop
        //   692: aload #6
        //   694: aload_3
        //   695: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   698: pop
        //   699: aload #6
        //   701: ldc '' AND so_dienthoai = ''
        //   703: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   706: pop
        //   707: aload #6
        //   709: aload_1
        //   710: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   713: pop
        //   714: aload #6
        //   716: ldc_w '' AND so_tin_nhan = '
        //   719: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   722: pop
        //   723: aload #6
        //   725: iload #12
        //   727: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   730: pop
        //   731: aload #6
        //   733: ldc_w ' AND type_kh = '
        //   736: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   739: pop
        //   740: aload #6
        //   742: iload #5
        //   744: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   747: pop
        //   748: aload #4
        //   750: aload #6
        //   752: invokevirtual toString : ()Ljava/lang/String;
        //   755: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   758: astore #4
        //   760: aload #4
        //   762: invokeinterface moveToFirst : ()Z
        //   767: pop
        //   768: aload_0
        //   769: getfield db : Ltamhoang/ldpro4/data/Database;
        //   772: aload #4
        //   774: iconst_0
        //   775: invokeinterface getInt : (I)I
        //   780: iconst_1
        //   781: invokevirtual Update_TinNhanGoc : (II)V
        //   784: goto -> 929
        //   787: astore #6
        //   789: aload_0
        //   790: getfield db : Ltamhoang/ldpro4/data/Database;
        //   793: astore #7
        //   795: new java/lang/StringBuilder
        //   798: astore #6
        //   800: aload #6
        //   802: invokespecial <init> : ()V
        //   805: aload #6
        //   807: ldc_w 'Update tbl_tinnhanS set phat_hien_loi = 'ko' WHERE id = '
        //   810: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   813: pop
        //   814: aload #6
        //   816: aload #4
        //   818: iconst_0
        //   819: invokeinterface getInt : (I)I
        //   824: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   827: pop
        //   828: aload #7
        //   830: aload #6
        //   832: invokevirtual toString : ()Ljava/lang/String;
        //   835: invokevirtual QueryData : (Ljava/lang/String;)V
        //   838: aload_0
        //   839: getfield db : Ltamhoang/ldpro4/data/Database;
        //   842: astore #7
        //   844: new java/lang/StringBuilder
        //   847: astore #6
        //   849: aload #6
        //   851: invokespecial <init> : ()V
        //   854: aload #6
        //   856: ldc_w 'Delete From tbl_soctS WHERE ngay_nhan = ''
        //   859: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   862: pop
        //   863: aload #6
        //   865: aload_3
        //   866: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   869: pop
        //   870: aload #6
        //   872: ldc '' AND so_dienthoai = ''
        //   874: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   877: pop
        //   878: aload #6
        //   880: aload_1
        //   881: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   884: pop
        //   885: aload #6
        //   887: ldc_w '' AND so_tin_nhan = '
        //   890: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   893: pop
        //   894: aload #6
        //   896: iload #12
        //   898: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   901: pop
        //   902: aload #6
        //   904: ldc_w ' AND type_kh ='
        //   907: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   910: pop
        //   911: aload #6
        //   913: iload #5
        //   915: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   918: pop
        //   919: aload #7
        //   921: aload #6
        //   923: invokevirtual toString : ()Ljava/lang/String;
        //   926: invokevirtual QueryData : (Ljava/lang/String;)V
        //   929: ldc_w '18:30'
        //   932: invokestatic CheckTime : (Ljava/lang/String;)Z
        //   935: ifne -> 969
        //   938: aload_2
        //   939: ldc 'Tra lai'
        //   941: invokevirtual indexOf : (Ljava/lang/String;)I
        //   944: iconst_m1
        //   945: if_icmpne -> 969
        //   948: iload #5
        //   950: iconst_1
        //   951: if_icmpne -> 969
        //   954: aload_0
        //   955: getfield db : Ltamhoang/ldpro4/data/Database;
        //   958: aload #4
        //   960: iconst_0
        //   961: invokeinterface getInt : (I)I
        //   966: invokevirtual Gui_Tin_Nhan : (I)V
        //   969: aload #4
        //   971: invokeinterface close : ()V
        //   976: goto -> 979
        //   979: aload #8
        //   981: ifnull -> 1008
        //   984: aload #8
        //   986: invokeinterface isClosed : ()Z
        //   991: ifne -> 1008
        //   994: aload #8
        //   996: invokeinterface close : ()V
        //   1001: goto -> 1008
        //   1004: astore_1
        //   1005: goto -> 1024
        //   1008: goto -> 1336
        //   1011: astore_1
        //   1012: goto -> 1340
        //   1015: astore_1
        //   1016: goto -> 1024
        //   1019: astore_1
        //   1020: goto -> 1340
        //   1023: astore_1
        //   1024: goto -> 1336
        //   1027: new java/lang/StringBuilder
        //   1030: astore #8
        //   1032: aload #8
        //   1034: invokespecial <init> : ()V
        //   1037: aload #8
        //   1039: ldc 'Select max(so_tin_nhan) from tbl_tinnhanS WHERE ngay_nhan = ''
        //   1041: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1044: pop
        //   1045: aload #8
        //   1047: aload_3
        //   1048: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1051: pop
        //   1052: aload #8
        //   1054: ldc '' AND so_dienthoai = ''
        //   1056: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1059: pop
        //   1060: aload #8
        //   1062: aload_1
        //   1063: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1066: pop
        //   1067: aload #8
        //   1069: ldc '' AND type_kh = 1'
        //   1071: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1074: pop
        //   1075: aload #8
        //   1077: invokevirtual toString : ()Ljava/lang/String;
        //   1080: astore_1
        //   1081: aload_0
        //   1082: getfield db : Ltamhoang/ldpro4/data/Database;
        //   1085: aload_1
        //   1086: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   1089: astore_1
        //   1090: aload_1
        //   1091: invokeinterface moveToFirst : ()Z
        //   1096: pop
        //   1097: aload #9
        //   1099: iconst_0
        //   1100: invokeinterface getString : (I)Ljava/lang/String;
        //   1105: astore #8
        //   1107: aload_1
        //   1108: iconst_0
        //   1109: invokeinterface getInt : (I)I
        //   1114: istore #5
        //   1116: new java/lang/StringBuilder
        //   1119: astore #6
        //   1121: aload #6
        //   1123: invokespecial <init> : ()V
        //   1126: aload #6
        //   1128: ldc_w 'Insert Into tbl_tinnhanS values (null, ''
        //   1131: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1134: pop
        //   1135: aload #6
        //   1137: aload_3
        //   1138: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1141: pop
        //   1142: aload #6
        //   1144: ldc_w '', ''
        //   1147: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1150: pop
        //   1151: aload #6
        //   1153: aload #4
        //   1155: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1158: pop
        //   1159: aload #6
        //   1161: ldc_w '',1, ''
        //   1164: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1167: pop
        //   1168: aload #6
        //   1170: aload #8
        //   1172: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1175: pop
        //   1176: aload #6
        //   1178: ldc_w '', ''
        //   1181: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1184: pop
        //   1185: aload #6
        //   1187: aload #9
        //   1189: iconst_1
        //   1190: invokeinterface getString : (I)Ljava/lang/String;
        //   1195: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1198: pop
        //   1199: aload #6
        //   1201: ldc_w '','TL', '
        //   1204: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1207: pop
        //   1208: aload #6
        //   1210: iload #5
        //   1212: iconst_1
        //   1213: iadd
        //   1214: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1217: pop
        //   1218: aload #6
        //   1220: ldc_w ', ''
        //   1223: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1226: pop
        //   1227: aload #6
        //   1229: aload_2
        //   1230: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1233: pop
        //   1234: aload #6
        //   1236: ldc_w '',null,''
        //   1239: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1242: pop
        //   1243: aload #6
        //   1245: aload_2
        //   1246: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1249: pop
        //   1250: aload #6
        //   1252: ldc_w '', 'Hginhsnull)'
        //   1255: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1258: pop
        //   1259: aload #6
        //   1261: invokevirtual toString : ()Ljava/lang/String;
        //   1264: astore_2
        //   1265: aload_0
        //   1266: getfield db : Ltamhoang/ldpro4/data/Database;
        //   1269: aload_2
        //   1270: invokevirtual QueryData : (Ljava/lang/String;)V
        //   1273: aload_1
        //   1274: ifnull -> 1292
        //   1277: aload_1
        //   1278: invokeinterface isClosed : ()Z
        //   1283: ifne -> 1292
        //   1286: aload_1
        //   1287: invokeinterface close : ()V
        //   1292: ldc_w '18:30'
        //   1295: invokestatic CheckTime : (Ljava/lang/String;)Z
        //   1298: ifne -> 1328
        //   1301: getstatic tamhoang/ldpro4/MainActivity.jSon_Setting : Lorg/json/JSONObject;
        //   1304: ldc_w 'tin_qua_gio'
        //   1307: invokevirtual getInt : (Ljava/lang/String;)I
        //   1310: iconst_1
        //   1311: if_icmpne -> 1328
        //   1314: aload #9
        //   1316: iconst_1
        //   1317: invokeinterface getLong : (I)J
        //   1322: ldc_w 'Hginh
        //   1325: invokestatic sendMessage : (JLjava/lang/String;)V
        //   1328: goto -> 1336
        //   1331: astore_1
        //   1332: goto -> 1340
        //   1335: astore_1
        //   1336: goto -> 1344
        //   1339: astore_1
        //   1340: aload_1
        //   1341: invokevirtual printStackTrace : ()V
        //   1344: aload #9
        //   1346: ifnull -> 1366
        //   1349: aload #9
        //   1351: invokeinterface isClosed : ()Z
        //   1356: ifne -> 1366
        //   1359: aload #9
        //   1361: invokeinterface close : ()V
        //   1366: return
        // Exception table:
        //   from	to	target	type
        //   120	125	174	org/json/JSONException
        //   129	142	174	org/json/JSONException
        //   150	159	174	org/json/JSONException
        //   189	201	1339	org/json/JSONException
        //   206	310	1023	android/database/SQLException
        //   206	310	1019	org/json/JSONException
        //   316	481	1023	android/database/SQLException
        //   316	481	1331	org/json/JSONException
        //   484	649	1015	android/database/SQLException
        //   484	649	1011	org/json/JSONException
        //   649	707	1015	android/database/SQLException
        //   649	707	1011	org/json/JSONException
        //   707	768	1004	android/database/SQLException
        //   707	768	1331	org/json/JSONException
        //   768	784	787	java/lang/Exception
        //   768	784	1004	android/database/SQLException
        //   768	784	1331	org/json/JSONException
        //   789	929	1004	android/database/SQLException
        //   789	929	1331	org/json/JSONException
        //   929	948	1004	android/database/SQLException
        //   929	948	1331	org/json/JSONException
        //   954	969	1004	android/database/SQLException
        //   954	969	1331	org/json/JSONException
        //   969	976	1004	android/database/SQLException
        //   969	976	1331	org/json/JSONException
        //   984	1001	1004	android/database/SQLException
        //   984	1001	1331	org/json/JSONException
        //   1027	1273	1335	android/database/SQLException
        //   1027	1273	1331	org/json/JSONException
        //   1277	1292	1335	android/database/SQLException
        //   1277	1292	1331	org/json/JSONException
        //   1292	1328	1335	android/database/SQLException
        //   1292	1328	1331	org/json/JSONException
    }

    public static void deleteCache(Context paramContext) {
        try {
            deleteDir(paramContext.getCacheDir());
        } catch (Exception exception) {}
    }

    public static boolean deleteDir(File paramFile) {
        if (paramFile != null && paramFile.isDirectory()) {
            String[] arrayOfString = paramFile.list();
            for (byte b = 0; b < arrayOfString.length; b++) {
                if (!deleteDir(new File(paramFile, arrayOfString[b])))
                    return false;
            }
            return paramFile.delete();
        }
        return (paramFile != null && paramFile.isFile()) ? paramFile.delete() : false;
    }

    private void notificationPermission() {
        boolean bool;
        ComponentName componentName = new ComponentName((Context)this, NotificationReader.class);
        String str = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        if (str == null || !str.contains(componentName.flattenToString())) {
            bool = false;
        } else {
            bool = true;
        }
        if (!bool)
            showAlertBox("Truy cthb, "Hcho phphmtruy cthbcthokhochnnhtin.").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            if (Build.VERSION.SDK_INT >= 22) {
                MainActivity.this.startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            } else {
                MainActivity.this.startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            }
        }
    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            param1DialogInterface.dismiss();
        }
    }).show().setCanceledOnTouchOutside(false);
}

    private void onAuthStateUpdated(TdApi.AuthorizationState paramAuthorizationState) {
        int i = paramAuthorizationState.getConstructor();
        if (i != 52643073) {
            if (i != 612103496) {
                if (i == 904720988) {
                    TdApi.TdlibParameters tdlibParameters = new TdApi.TdlibParameters();
                    tdlibParameters.apiId = 1855995;
                    tdlibParameters.apiHash = "a4a4dcc61215e41de68609fabb28bcb8";
                    tdlibParameters.useMessageDatabase = true;
                    tdlibParameters.useSecretChats = true;
                    tdlibParameters.systemLanguageCode = "en";
                    tdlibParameters.databaseDirectory = getApplicationContext().getFilesDir().getAbsolutePath();
                    tdlibParameters.deviceModel = "Moto";
                    tdlibParameters.systemVersion = "7.0";
                    tdlibParameters.applicationVersion = "0.1";
                    tdlibParameters.enableStorageOptimizer = true;
                    client.send((TdApi.Function)new TdApi.SetTdlibParameters(tdlibParameters), (Client.ResultHandler)this);
                }
            } else {
                client.send((TdApi.Function)new TdApi.CheckDatabaseEncryptionKey(), (Client.ResultHandler)this);
            }
        } else {
            (new Handler(Looper.getMainLooper())).post(new Runnable() {
                public void run() {
                    MainActivity.this.showDialog2();
                }
            });
        }
    }

    public static void sendMessage(long paramLong, String paramString) {
        TdApi.InlineKeyboardButton[] arrayOfInlineKeyboardButton = new TdApi.InlineKeyboardButton[3];
        arrayOfInlineKeyboardButton[0] = new TdApi.InlineKeyboardButton("https://telegram.org?1", (TdApi.InlineKeyboardButtonType)new TdApi.InlineKeyboardButtonTypeUrl());
        arrayOfInlineKeyboardButton[1] = new TdApi.InlineKeyboardButton("https://telegram.org?2", (TdApi.InlineKeyboardButtonType)new TdApi.InlineKeyboardButtonTypeUrl());
        arrayOfInlineKeyboardButton[2] = new TdApi.InlineKeyboardButton("https://telegram.org?3", (TdApi.InlineKeyboardButtonType)new TdApi.InlineKeyboardButtonTypeUrl());
        TdApi.ReplyMarkupInlineKeyboard replyMarkupInlineKeyboard = new TdApi.ReplyMarkupInlineKeyboard(new TdApi.InlineKeyboardButton[][] { arrayOfInlineKeyboardButton, arrayOfInlineKeyboardButton, arrayOfInlineKeyboardButton });
        TdApi.InputMessageText inputMessageText = new TdApi.InputMessageText(new TdApi.FormattedText(paramString, null), false, true);
        client.send((TdApi.Function)new TdApi.SendMessage(paramLong, 0L, null, (TdApi.ReplyMarkup)replyMarkupInlineKeyboard, (TdApi.InputMessageContent)inputMessageText), null);
    }

    public static void setListFragment(int paramInt) {
        listFragments.remove(4);
        if (paramInt == 1) {
            listFragments.add(4, new Frag_No_new());
        } else {
            listFragments.add(4, new Frag_No_old());
        }
    }

    public String Get_link() {
        return "http://ldpro.biz/";
    }

    public void LayDulieuJson1() {
        try {
            JSONObject jSONObject = new JSONObject();
            this(loadJSONFromAsset("kytuthaythe.json"));
            JSONArray jSONArray = jSONObject.getJSONArray("formules");
            int i = 0;
            byte b = 0;
            while (b < jSONArray.length()) {
                JSONObject jSONObject1 = jSONArray.getJSONObject(b);
                String str = jSONObject1.optString("type", "");
                int j = i;
                if (!str.isEmpty()) {
                    JSONArray jSONArray1 = jSONObject1.getJSONArray("datas");
                    int k = i + jSONArray1.length();
                    i = 0;
                    while (true) {
                        j = k;
                        if (i < jSONArray1.length()) {
                            String str1 = jSONArray1.getString(i);
                            HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
                            this();
                            hashMap.put("type", str);
                            hashMap.put("datas", str1);
                            formList.add(hashMap);
                            i++;
                            continue;
                        }
                        break;
                    }
                }
                b++;
                i = j;
            }
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
    }

    public void LayDulieuJson2() {
        if (formArray.size() == 0)
            try {
                JSONObject jSONObject = new JSONObject();
                this(loadJSONFromAsset("thaythe.json"));
                JSONArray jSONArray = jSONObject.getJSONArray("listKHs");
                for (byte b = 0; b < jSONArray.length(); b++) {
                    JSONObject jSONObject1 = jSONArray.getJSONObject(b);
                    String str1 = jSONObject1.optString("str", "");
                    String str2 = jSONObject1.optString("repl_str", "");
                    if (!str1.isEmpty()) {
                        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
                        this();
                        hashMap.put("str", str1);
                        hashMap.put("repl_str", str2);
                        formArray.add(hashMap);
                    }
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
    }

    public void Suagia() {
        Cursor cursor3;
        Cursor cursor1 = null;
        Cursor cursor2 = null;
        try {
            Cursor cursor = this.db.GetData("Select * From tbl_kh_new");
            cursor2 = cursor;
            cursor1 = cursor;
            cursor3 = cursor;
            if (cursor.getCount() > 0) {
                cursor2 = cursor;
                cursor1 = cursor;
                cursor3 = cursor;
                if (cursor.moveToFirst()) {
                    cursor2 = cursor;
                    cursor1 = cursor;
                    JSONObject jSONObject1 = new JSONObject();
                    cursor2 = cursor;
                    cursor1 = cursor;
                    this(cursor.getString(5));
                    cursor2 = cursor;
                    cursor1 = cursor;
                    JSONObject jSONObject2 = new JSONObject();
                    cursor2 = cursor;
                    cursor1 = cursor;
                    this(jSONObject1.getString("caidat_gia"));
                    cursor2 = cursor;
                    cursor1 = cursor;
                    Cursor cursor4 = cursor;
                    if (jSONObject2.getDouble("dea") > 10.0D) {
                        cursor2 = cursor;
                        cursor1 = cursor;
                        cursor4 = this.db.GetData("Select * From tbl_kh_new");
                        while (true) {
                            cursor2 = cursor;
                            cursor1 = cursor;
                            if (cursor4.moveToNext()) {
                                StringBuilder stringBuilder;
                                Database database;
                                cursor2 = cursor;
                                cursor1 = cursor;
                                jSONObject2 = new JSONObject();
                                cursor2 = cursor;
                                cursor1 = cursor;
                                this(cursor4.getString(5));
                                cursor2 = cursor;
                                cursor1 = cursor;
                                JSONObject jSONObject = new JSONObject();
                                cursor2 = cursor;
                                cursor1 = cursor;
                                this(jSONObject2.getString("caidat_gia"));
                                cursor2 = cursor;
                                cursor1 = cursor;
                                Iterator<String> iterator = jSONObject.keys();
                                while (true) {
                                    cursor2 = cursor;
                                    cursor1 = cursor;
                                    if (iterator.hasNext()) {
                                        cursor2 = cursor;
                                        cursor1 = cursor;
                                        String str = iterator.next();
                                        cursor2 = cursor;
                                        cursor1 = cursor;
                                        if (jSONObject.getDouble(str) > 100.0D) {
                                            cursor2 = cursor;
                                            cursor1 = cursor;
                                            jSONObject.put(str, jSONObject.getDouble(str) / 1000.0D);
                                        }
                                        continue;
                                    }
                                    cursor2 = cursor;
                                    cursor1 = cursor;
                                    jSONObject2.put("caidat_gia", jSONObject);
                                    cursor2 = cursor;
                                    cursor1 = cursor;
                                    database = this.db;
                                    cursor2 = cursor;
                                    cursor1 = cursor;
                                    stringBuilder = new StringBuilder();
                                    cursor2 = cursor;
                                    cursor1 = cursor;
                                    this();
                                    cursor2 = cursor;
                                    cursor1 = cursor;
                                    stringBuilder.append("update tbl_kh_new set tbl_mb = '");
                                    cursor2 = cursor;
                                    cursor1 = cursor;
                                    stringBuilder.append(jSONObject2.toString());
                                    cursor2 = cursor;
                                    cursor1 = cursor;
                                    stringBuilder.append("' WHERE ten_kh = '");
                                    cursor2 = cursor;
                                    cursor1 = cursor;
                                    stringBuilder.append(cursor4.getString(0));
                                    cursor2 = cursor;
                                    cursor1 = cursor;
                                    stringBuilder.append("'");
                                    cursor2 = cursor;
                                    cursor1 = cursor;
                                    break;
                                }
                                database.QueryData(stringBuilder.toString());
                                continue;
                            }
                            cursor2 = cursor;
                            cursor1 = cursor;
                            cursor4.close();
                            cursor4 = cursor;
                            break;
                        }
                    }
                }
            }
        } catch (JSONException jSONException) {
            cursor2 = cursor1;
            jSONException.printStackTrace();
            cursor3 = cursor1;
        } finally {}
        cursor3.close();
    }

    public String loadJSONFromAsset(String paramString) {
        try {
            InputStream inputStream = getAssets().open(paramString);
            byte[] arrayOfByte = new byte[inputStream.available()];
            inputStream.read(arrayOfByte);
            inputStream.close();
            return new String(arrayOfByte, "UTF-8");
        } catch (IOException iOException) {
            iOException.printStackTrace();
            return null;
        }
    }

    public void onBackPressed() {
        (new AlertDialog.Builder((Context)this)).setMessage("Bcmuthokh).setCancelable(true).setPositiveButton("Tho, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                intent.setFlags(67108864);
                MainActivity.this.startActivity(intent);
            }
        }).setNegativeButton("Kh, null).show();
    }

    public void onClick(View paramView) {
        showDialog(0);
    }

    protected void onCreate(Bundle paramBundle) {
        Integer integer;
        super.onCreate(paramBundle);
        setContentView(2131427372);
        this.db = new Database((Context)this);
        Main_activity = (Context)this;
        Suagia();
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(Get_link());
        stringBuilder1.append("json_data.php");
        this.viewData = stringBuilder1.toString();
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(Get_link());
        stringBuilder1.append("json_insert.php");
        this.insertData = stringBuilder1.toString();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        LayDulieuJson1();
        LayDulieuJson2();
        this.db.LayDanhsachKH();
        Cursor cursor = this.db.GetData("Select * From tbl_Setting WHERE ID = 1");
        if (cursor != null && cursor.moveToFirst()) {
            try {
                JSONObject jSONObject = new JSONObject();
                this(cursor.getString(1));
                jSon_Setting = jSONObject;
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
            cursor.close();
        }
        View view = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2131427378, null);
        actionBar.setCustomView(view);
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(1);
        mMonth = calendar.get(2);
        mDay = calendar.get(5);
        this.Text_date = (TextView)view.findViewById(2131231115);
        this.Text_Menu = (TextView)view.findViewById(2131231116);
        TextView textView = this.Text_date;
        StringBuilder stringBuilder2 = new StringBuilder();
        int i = mDay;
        if (i < 10) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("0");
            stringBuilder.append(mDay);
            String str = stringBuilder.toString();
        } else {
            integer = Integer.valueOf(i);
        }
        stringBuilder2.append(integer);
        stringBuilder2.append("-");
        i = mMonth;
        if (i + 1 < 10) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("0");
            stringBuilder.append(mMonth + 1);
            String str = stringBuilder.toString();
        } else {
            integer = Integer.valueOf(i + 1);
        }
        stringBuilder2.append(integer);
        stringBuilder2.append("-");
        stringBuilder2.append(mYear);
        textView.setText(stringBuilder2.toString());
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker param1DatePicker, int param1Int1, int param1Int2, int param1Int3) {
                Integer integer;
                MainActivity.mYear = param1Int1;
                MainActivity.mMonth = param1Int2;
                MainActivity.mDay = param1Int3;
                MainActivity.sms = true;
                TextView textView = MainActivity.this.Text_date;
                StringBuilder stringBuilder = new StringBuilder();
                if (MainActivity.mDay < 10) {
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append("0");
                    stringBuilder1.append(MainActivity.mDay);
                    String str = stringBuilder1.toString();
                } else {
                    integer = Integer.valueOf(MainActivity.mDay);
                }
                stringBuilder.append(integer);
                stringBuilder.append("-");
                if (MainActivity.mMonth + 1 < 10) {
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append("0");
                    stringBuilder1.append(MainActivity.mMonth + 1);
                    String str = stringBuilder1.toString();
                } else {
                    integer = Integer.valueOf(MainActivity.mMonth + 1);
                }
                stringBuilder.append(integer);
                stringBuilder.append("-");
                stringBuilder.append(MainActivity.mYear);
                textView.setText(stringBuilder.toString());
            }
        };
        actionBar.setBackgroundDrawable((Drawable)new ColorDrawable(Color.parseColor("#ff37474f")));
        this.drawerLayout = (DrawerLayout)findViewById(2131230907);
        this.drawerPane = (RelativeLayout)findViewById(2131230908);
        this.lvNav = (ListView)findViewById(2131231117);
        ArrayList<NavItem> arrayList1 = new ArrayList();
        this.listNavItems = arrayList1;
        arrayList1.add(new NavItem("Trang ch, "Imei, hsd, 2131165302));
        this.listNavItems.add(new NavItem("Stin nh, "Sltin nh, 2131165297));
        this.listNavItems.add(new NavItem("Qultin nh, "SMS, Zalo, Viber, WhatsApp", 2131165283));
        this.listNavItems.add(new NavItem("Chuyss, "Chuysvgis, 2131165334));
        this.listNavItems.add(new NavItem("Bcththua", "Bckqutkh, 2131165312));
        this.listNavItems.add(new NavItem("Chtrang", "Vtrang One789", 2131165311));
        this.listNavItems.add(new NavItem("Cb, "Cbtrti, 2131165315));
        this.listNavItems.add(new NavItem("Xstrti, "Quay vttitrti, 2131165314));
        this.listNavItems.add(new NavItem("Qulcn, "Cnto, 2131165317));
        this.listNavItems.add(new NavItem("Danh skhh, "Thtin khh, 2131165286));
        this.listNavItems.add(new NavItem("C, "Ccho d, 2131165341));
        this.listNavItems.add(new NavItem("Ctin nhm, "Ccphchu, 2131165301));
        this.listNavItems.add(new NavItem("Csdli, "CnhKQ/Tti, 2131165288));
        NavListAdapter navListAdapter = new NavListAdapter(getApplicationContext(), 2131427440, this.listNavItems);
        this.lvNav.setAdapter((ListAdapter)navListAdapter);
        ArrayList<Fragment> arrayList = new ArrayList();
        listFragments = arrayList;
        arrayList.add(new Frag_Home());
        listFragments.add(new Tab_Tinnhan());
        listFragments.add(new Frag_Chat_Manager());
        listFragments.add(new Frag_CanChuyen());
        try {
            if (jSon_Setting.getInt("kieu_bao_cao") == 1) {
                List<Fragment> list = listFragments;
                Frag_No_new frag_No_new = new Frag_No_new();
                this();
                list.add(frag_No_new);
            } else {
                List<Fragment> list = listFragments;
                Frag_No_old frag_No_old = new Frag_No_old();
                this();
                list.add(frag_No_old);
            }
        } catch (SQLException sQLException) {
            listFragments.add(new Frag_No_new());
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
            listFragments.add(new Frag_No_new());
        }
        listFragments.add(new Tab_ChayTrang());
        listFragments.add(new Livestream());
        listFragments.add(new TructiepXoso());
        listFragments.add(new Frag_MoRP1());
        listFragments.add(new Frag_Setting1());
        listFragments.add(new Frag_Setting3());
        listFragments.add(new Frag_SMS_Templates());
        listFragments.add(new Frag_Database());
        getSupportFragmentManager().beginTransaction().replace(2131231104, listFragments.get(0)).commit();
        setTitle(((NavItem)this.listNavItems.get(0)).getTitle());
        this.lvNav.setItemChecked(0, true);
        this.drawerLayout.closeDrawer((View)this.drawerPane);
        this.lvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                MainActivity.this.getSupportFragmentManager().beginTransaction().replace(2131231104, MainActivity.listFragments.get(param1Int)).commit();
                MainActivity mainActivity = MainActivity.this;
                mainActivity.setTitle(((NavItem)mainActivity.listNavItems.get(param1Int)).getTitle());
                MainActivity.this.lvNav.setItemChecked(param1Int, true);
                MainActivity.this.drawerLayout.closeDrawer((View)MainActivity.this.drawerPane);
            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle((Activity)this, this.drawerLayout, 2131623988, 2131623987) {
            public void onDrawerClosed(View param1View) {
                super.onDrawerClosed(param1View);
            }

            public void onDrawerOpened(View param1View) {
                super.onDrawerOpened(param1View);
                InputMethodManager inputMethodManager = (InputMethodManager)MainActivity.this.getSystemService("input_method");
                View view = MainActivity.this.getCurrentFocus();
                param1View = view;
                if (view == null)
                    param1View = new View((Context)MainActivity.this);
                inputMethodManager.hideSoftInputFromWindow(param1View.getWindowToken(), 0);
            }

            public void onDrawerSlide(View param1View, float param1Float) {
                super.onDrawerSlide(param1View, param1Float);
                InputMethodManager inputMethodManager = (InputMethodManager)MainActivity.this.getSystemService("input_method");
                View view = MainActivity.this.getCurrentFocus();
                param1View = view;
                if (view == null)
                    param1View = new View((Context)MainActivity.this);
                inputMethodManager.hideSoftInputFromWindow(param1View.getWindowToken(), 0);
            }
        };
        this.actionBarDrawerToggle = actionBarDrawerToggle;
        this.drawerLayout.setDrawerListener((DrawerLayout.DrawerListener)actionBarDrawerToggle);
        this.actionBarDrawerToggle.syncState();
        notificationPermission();
        ((NotificationManager)getSystemService("notification")).cancel(1);
        startService(new Intent((Context)this, ZBroadcast.class));
        Client client = TelegramClient.getClient(this);
        client = client;
        client.send((TdApi.Function)new TdApi.GetMe(), (Client.ResultHandler)this);
    }

    protected Dialog onCreateDialog(int paramInt) {
        return (Dialog)((paramInt != 0) ? null : new DatePickerDialog((Context)this, onDateSetListener, mYear, mMonth, mDay));
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(2131492864, paramMenu);
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
        deleteCache(getApplicationContext());
    }

    public void onMenu(View paramView) {
        String[] arrayOfString;
        if (this.my_id != "") {
            arrayOfString = new String[] { "Tcnh, "Nhdgis, "Cchuyth, "Logout Telegram" };
            } else {
                arrayOfString = new String[] { "Tcnh, "Nhdgis, "Cchuyth, "Login Telegram" };
                }
                PopupMenu popupMenu = new PopupMenu((Context)this, paramView);
                for (byte b = 0; b < arrayOfString.length; b++)
                    popupMenu.getMenu().add(1, b, b, arrayOfString[b]);
                new AlertDialog.Builder((Context)this);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem param1MenuItem) {
                        int i = param1MenuItem.getOrder();
                        if (i != 0) {
                            if (i != 1) {
                                if (i != 2) {
                                    if (i == 3)
                                        if (MainActivity.this.my_id != "") {
                                            AlertDialog.Builder builder = new AlertDialog.Builder((Context)MainActivity.this);
                                            builder.setTitle("ThoTelegram?");
                                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                                                    MainActivity.this.db.QueryData("Update So_om set  Sphu1 ='' where ID = 1");
                                                    MainActivity.client.send((TdApi.Function)new TdApi.LogOut(), (Client.ResultHandler)MainActivity.this, null);
                                                    MainActivity.this.my_id = "";
                                                    Toast.makeText((Context)MainActivity.this, "thoTelegram", 0).show();
                                                }
                                            });
                                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                                                    param2DialogInterface.cancel();
                                                }
                                            });
                                            builder.create().show();
                                        } else {
                                            MainActivity.this.showDialog1();
                                        }
                                } else {
                                    Intent intent = new Intent((Context)MainActivity.this, Activity_ChuyenThang.class);
                                    MainActivity.this.startActivity(intent);
                                }
                            } else {
                                Intent intent = new Intent((Context)MainActivity.this, Activity_GiuSo.class);
                                MainActivity.this.startActivity(intent);
                            }
                        } else {
                            Intent intent = new Intent((Context)MainActivity.this, Activity_thaythe.class);
                            MainActivity.this.startActivity(intent);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }

            public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
                return this.actionBarDrawerToggle.onOptionsItemSelected(paramMenuItem) ? true : super.onOptionsItemSelected(paramMenuItem);
            }

            protected void onPostCreate(Bundle paramBundle) {
                super.onPostCreate(paramBundle);
                this.actionBarDrawerToggle.syncState();
            }

            public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint) {
                if (paramInt != 1) {
                    if (paramInt != 2)
                        return;
                } else if (paramArrayOfint.length > 0 && paramArrayOfint[0] == 0) {
                    if (ContextCompat.checkSelfPermission((Context)this, "android.permission.READ_CONTACTS") != 0 && !ActivityCompat.shouldShowRequestPermissionRationale((Activity)this, "android.permission.READ_CONTACTS"))
                        ActivityCompat.requestPermissions((Activity)this, new String[] { "android.permission.READ_CONTACTS" }, 2);
                } else {
                    Toast.makeText(getApplicationContext(), "Can't access messages.", 1).show();
                    return;
                }
                if (paramArrayOfint.length <= 0 || paramArrayOfint[0] != 0) {
                    Toast.makeText(getApplicationContext(), "Can't access messages.", 1).show();
                    return;
                }
            }

            public void onResult(TdApi.Object paramObject) {
                TdApi.UpdateNewChat updateNewChat;
                TdApi.UpdateUser updateUser;
                TdApi.UpdateOption updateOption;
                Database database;
                TdApi.User user;
                byte b;
                switch (paramObject.getConstructor()) {
                    default:
                        return;
                    case 2075757773:
                        updateNewChat = (TdApi.UpdateNewChat)paramObject;
                        try {
                            JSONObject jSONObject = Json_Chat_Telegram;
                            StringBuilder stringBuilder = new StringBuilder();
                            this();
                            stringBuilder.append(updateNewChat.chat.id);
                            stringBuilder.append("");
                            if (!jSONObject.has(stringBuilder.toString())) {
                                jSONObject = new JSONObject();
                                this();
                                String str = updateNewChat.chat.type.toString();
                                jSONObject.put("type", str.substring(0, str.indexOf("{")).trim());
                                jSONObject.put("basicGroupId", updateNewChat.chat.id);
                                StringBuilder stringBuilder3 = new StringBuilder();
                                this();
                                stringBuilder3.append("TL - ");
                                stringBuilder3.append(updateNewChat.chat.title);
                                jSONObject.put("title", stringBuilder3.toString());
                                JSONObject jSONObject1 = Json_Chat_Telegram;
                                stringBuilder3 = new StringBuilder();
                                this();
                                stringBuilder3.append(updateNewChat.chat.id);
                                stringBuilder3.append("");
                                jSONObject1.put(stringBuilder3.toString(), jSONObject);
                            }
                        } catch (JSONException jSONException2) {
                            jSONException2.printStackTrace();
                        }
                    case 1622347490:
                        onAuthStateUpdated(((TdApi.UpdateAuthorizationState)jSONException2).authorizationState);
                    case 1469292078:
                        if (((TdApi.UpdateConnectionState)jSONException2).state.getConstructor() == 48608492)
                            Log.d("AuthActivity", "onResult: ConnectionStateReady");
                    case 1183394041:
                        updateUser = (TdApi.UpdateUser)jSONException2;
                        try {
                            JSONObject jSONObject = Json_Chat_Telegram;
                            StringBuilder stringBuilder = new StringBuilder();
                            this();
                            stringBuilder.append(updateUser.user.id);
                            stringBuilder.append("");
                            if (!jSONObject.has(stringBuilder.toString())) {
                                jSONObject = new JSONObject();
                                this();
                                String str = updateUser.user.type.toString();
                                jSONObject.put("type", str.substring(0, str.indexOf("{")).trim());
                                jSONObject.put("basicGroupId", updateUser.user.id);
                                StringBuilder stringBuilder3 = new StringBuilder();
                                this();
                                stringBuilder3.append("TL - ");
                                stringBuilder3.append(updateUser.user.firstName);
                                stringBuilder3.append(" ");
                                stringBuilder3.append(updateUser.user.lastName);
                                jSONObject.put("title", stringBuilder3.toString());
                                JSONObject jSONObject1 = Json_Chat_Telegram;
                                stringBuilder3 = new StringBuilder();
                                this();
                                stringBuilder3.append(updateUser.user.id);
                                stringBuilder3.append("");
                                jSONObject1.put(stringBuilder3.toString(), jSONObject);
                            }
                        } catch (JSONException jSONException1) {
                            jSONException1.printStackTrace();
                        }
                    case 900822020:
                        updateOption = (TdApi.UpdateOption)jSONException1;
                        if (updateOption.name.indexOf("my_id") > -1) {
                            String str = updateOption.value.toString();
                            this.my_id = str;
                            str = str.substring(str.indexOf("=") + 1);
                            this.my_id = str;
                            this.my_id = str.substring(0, str.indexOf("\n")).trim();
                            database = this.db;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Update So_Om set Sphu1 = '");
                            stringBuilder.append(this.my_id);
                            stringBuilder.append("' WHERE ID = 1");
                            database.QueryData(stringBuilder.toString());
                        }
                    case -824771497:
                        user = (TdApi.User)database;
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(user.id);
                        stringBuilder2.append("");
                        this.my_id = stringBuilder2.toString();
                        break;
                    case -563105266:
                        break;
                }
                if (this.my_id == "") {
                    Cursor cursor = this.db.GetData("Select Sphu1 from so_om where ID = 1");
                    cursor.moveToFirst();
                    this.my_id = cursor.getString(0);
                    cursor.close();
                }
                TdApi.UpdateNewMessage updateNewMessage = (TdApi.UpdateNewMessage)database;
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append(updateNewMessage.message.senderUserId);
                stringBuilder1.append("");
                String str1 = stringBuilder1.toString();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(updateNewMessage.message.chatId);
                stringBuilder2.append("");
                String str2 = stringBuilder2.toString();
                String str3 = ((TdApi.MessageText)updateNewMessage.message.content).text.text.replace("'", "");
                if (updateNewMessage.message.isChannelPost || updateNewMessage.message.chatId == 777000L || updateNewMessage.message.chatId == 93372553L) {
                    b = 0;
                } else {
                    b = 1;
                }
                if (b == 1) {
                    String str4;
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss");
                    simpleDateFormat2.setTimeZone(TimeZone.getDefault());
                    simpleDateFormat1.setTimeZone(TimeZone.getDefault());
                    String str7 = simpleDateFormat2.format(calendar.getTime());
                    String str6 = simpleDateFormat1.format(calendar.getTime());
                    try {
                        str4 = Json_Chat_Telegram.getJSONObject(str2).getString("title");
                    } catch (JSONException jSONException) {
                        Database database2 = this.db;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Select * From tbl_kh_new Where sdt = '");
                        stringBuilder.append(str2);
                        stringBuilder.append("'");
                        Cursor cursor1 = database2.GetData(stringBuilder.toString());
                        if (cursor1.getCount() > 0) {
                            cursor1.moveToFirst();
                            str4 = cursor1.getString(0);
                            cursor1.close();
                        } else {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("TL - ");
                            stringBuilder.append(str2);
                            str4 = stringBuilder.toString();
                        }
                    }
                    if (str2.indexOf(this.my_id) > -1 || str1.indexOf(this.my_id) > -1) {
                        b = 2;
                    } else {
                        b = 1;
                    }
                    StringBuilder stringBuilder4 = new StringBuilder();
                    stringBuilder4.append("Insert into Chat_database Values( null,'");
                    stringBuilder4.append(str7);
                    stringBuilder4.append("', '");
                    stringBuilder4.append(str6);
                    stringBuilder4.append("', ");
                    stringBuilder4.append(b);
                    stringBuilder4.append(", '");
                    stringBuilder4.append(str4);
                    stringBuilder4.append("','");
                    stringBuilder4.append(str2);
                    stringBuilder4.append("', 'TL','");
                    stringBuilder4.append(str3);
                    stringBuilder4.append("',1)");
                    String str5 = stringBuilder4.toString();
                    this.db.QueryData(str5);
                    sms = true;
                    Database database1 = this.db;
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("Select * From tbl_tinnhanS WHERE ngay_nhan = '");
                    stringBuilder3.append(str7);
                    stringBuilder3.append("' And Ten_kh = '");
                    stringBuilder3.append(str4);
                    stringBuilder3.append("' AND nd_goc = '");
                    stringBuilder3.append(str3);
                    stringBuilder3.append("'");
                    Cursor cursor = database1.GetData(stringBuilder3.toString());
                    if (cursor.getCount() == 0) {
                        Database database2 = this.db;
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("Select * From tbl_kh_new Where sdt = '");
                        stringBuilder3.append(str2);
                        stringBuilder3.append("'");
                        Cursor cursor1 = database2.GetData(stringBuilder3.toString());
                        if (cursor1.getCount() > 0 && str3.length() > 5) {
                            cursor1.moveToFirst();
                            if (cursor1.getInt(3) == 1 && b == 1)
                                Xulytin(str2, str3, str7, str6, b);
                            Cursor cursor2 = cursor1;
                            byte b1 = b;
                            if (cursor2.getInt(3) == 2 && b1 == 1 && str3.indexOf("Tra lai") == 0)
                                Xulytin(str2, str3, str7, str6, b1);
                            if (cursor2.getInt(3) == 3 && b == 1)
                                Xulytin(str2, str3, str7, str6, b);
                        }
                        cursor1.close();
                    }
                    cursor.close();
                }
            }

            public AlertDialog.Builder showAlertBox(String paramString1, String paramString2) {
                return (new AlertDialog.Builder((Context)this)).setTitle(paramString1).setMessage(paramString2);
            }

            public void showDialog1() {
                final Dialog dialog = new Dialog((Context)this);
                dialog.setContentView(2131427373);
                dialog.getWindow().setLayout(-1, -2);
                final EditText authPhone = (EditText)dialog.findViewById(2131230777);
                Button button = (Button)dialog.findViewById(2131231088);
                TextView textView = (TextView)dialog.findViewById(2131230776);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View param1View) {
                        String str = authPhone.getText().toString();
                        if (str.length() == 10) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("+84");
                            stringBuilder.append(str.substring(1));
                            String str1 = stringBuilder.toString();
                            MainActivity.client.send((TdApi.Function)new TdApi.SetAuthenticationPhoneNumber(str1, null), (Client.ResultHandler)MainActivity.this);
                            dialog.dismiss();
                        } else {
                            Toast.makeText((Context)MainActivity.this, "Hnh10 scstho, 1).show();
                        }
                    }
                });
                dialog.setCancelable(true);
                dialog.show();
            }

            public void showDialog2() {
                final Dialog dialog = new Dialog((Context)this);
                dialog.setContentView(2131427359);
                dialog.getWindow().setLayout(-1, -2);
                final EditText authPhone = (EditText)dialog.findViewById(2131230775);
                ((Button)dialog.findViewById(2131230843)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View param1View) {
                        String str = authPhone.getText().toString();
                        if (str.length() == 5) {
                            TdApi.CheckAuthenticationCode checkAuthenticationCode = new TdApi.CheckAuthenticationCode(str);
                            MainActivity.client.send((TdApi.Function)checkAuthenticationCode, (Client.ResultHandler)MainActivity.this);
                            dialog.dismiss();
                        } else {
                            Toast.makeText((Context)MainActivity.this, "Hnh5 sgvTelegram!", 1).show();
                        }
                    }
                });
                dialog.setCancelable(true);
                dialog.show();
            }
        }
