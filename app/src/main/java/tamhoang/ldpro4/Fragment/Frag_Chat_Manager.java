package tamhoang.ldpro4.Fragment;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Activity.Activity_AddKH;
import tamhoang.ldpro4.Activity.Chatbox;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.NotificationReader;
import tamhoang.ldpro4.data.Database;

public class Frag_Chat_Manager extends Fragment {
    boolean Running = true;

    Button btn_Thongbao;

    Button btn_login;

    Database db;

    Handler handler;

    ListView listviewKH;

    private List<String> mApp = new ArrayList<String>();

    private List<String> mNoiDung = new ArrayList<String>();

    private List<String> mSDT = new ArrayList<String>();

    private List<String> mTenKH = new ArrayList<String>();

    private Runnable runnable = new Runnable() {
        public void run() {
            if (MainActivity.sms == true) {
                Frag_Chat_Manager.this.XemListview();
                MainActivity.sms = false;
            }
            Frag_Chat_Manager.this.handler.postDelayed(this, 1000L);
        }
    };

    public View v;

    private void XemListview() {
        this.mTenKH.clear();
        this.mNoiDung.clear();
        this.mApp.clear();
        this.mSDT.clear();
        new MainActivity();
        String str = MainActivity.Get_date();
        JSONObject jSONObject = new JSONObject();
        Database database = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM Chat_database WHERE ngay_nhan = '");
        stringBuilder.append(str);
        stringBuilder.append("' ORDER BY Gio_nhan DESC, ID DESC");
        Cursor cursor = database.GetData(stringBuilder.toString());
        if (cursor != null) {
            while (cursor.moveToNext()) {
                if ((MainActivity.arr_TenKH.indexOf(cursor.getString(4)) > -1 || cursor.getString(6).indexOf("sms") > -1 || cursor.getString(6).indexOf("TL") > -1) && !jSONObject.has(cursor.getString(4)))
                    try {
                        jSONObject.put(cursor.getString(4), "OK");
                        this.mTenKH.add(cursor.getString(4));
                        this.mSDT.add(cursor.getString(5));
                        this.mApp.add(cursor.getString(6));
                        this.mNoiDung.add(cursor.getString(7));
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
            }
            cursor.close();
        }
        for (String str1 : MainActivity.arr_TenKH) {
            if (this.mTenKH.indexOf(str1) == -1) {
                this.mTenKH.add(str1);
                this.mNoiDung.add("Hnay chctin nh);
                if (str1.indexOf("ZL") > -1) {
                    this.mApp.add("ZL");
                    continue;
                }
                if (str1.indexOf("VB") > -1) {
                    this.mApp.add("VB");
                    continue;
                }
                if (str1.indexOf("WA") > -1)
                    this.mApp.add("WA");
            }
        }
        if (getActivity() != null)
            this.listviewKH.setAdapter((ListAdapter)new Chat_Main((Context)getActivity(), 2131427397, this.mTenKH));
    }

    private void getSMS() {
        // Byte code:
        //   0: aload_0
        //   1: astore_1
        //   2: ldc '''
        //   4: astore_2
        //   5: ldc ''
        //   7: astore_3
        //   8: ldc ' '
        //   10: astore #4
        //   12: new tamhoang/ldpro4/MainActivity
        //   15: dup
        //   16: invokespecial <init> : ()V
        //   19: astore #5
        //   21: invokestatic Get_date : ()Ljava/lang/String;
        //   24: astore #6
        //   26: aload_0
        //   27: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   30: ldc 'android.permission.READ_SMS'
        //   32: invokestatic checkSelfPermission : (Landroid/content/Context;Ljava/lang/String;)I
        //   35: ifne -> 1592
        //   38: aload #5
        //   40: astore #7
        //   42: aload #5
        //   44: astore #7
        //   46: aload #5
        //   48: astore #7
        //   50: new java/text/SimpleDateFormat
        //   53: astore #8
        //   55: aload #5
        //   57: astore #7
        //   59: aload #5
        //   61: astore #7
        //   63: aload #5
        //   65: astore #7
        //   67: aload #8
        //   69: ldc 'yyyy-MM-dd'T'HH:mm:ss'
        //   71: invokespecial <init> : (Ljava/lang/String;)V
        //   74: aload #5
        //   76: astore #7
        //   78: aload #5
        //   80: astore #7
        //   82: aload #5
        //   84: astore #7
        //   86: new java/lang/StringBuilder
        //   89: astore #9
        //   91: aload #5
        //   93: astore #7
        //   95: aload #5
        //   97: astore #7
        //   99: aload #5
        //   101: astore #7
        //   103: aload #9
        //   105: invokespecial <init> : ()V
        //   108: aload #5
        //   110: astore #7
        //   112: aload #5
        //   114: astore #7
        //   116: aload #5
        //   118: astore #7
        //   120: aload #9
        //   122: aload #6
        //   124: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   127: pop
        //   128: aload #5
        //   130: astore #7
        //   132: aload #5
        //   134: astore #7
        //   136: aload #5
        //   138: astore #7
        //   140: aload #9
        //   142: ldc 'T00:00:00'
        //   144: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   147: pop
        //   148: aload #5
        //   150: astore #7
        //   152: aload #5
        //   154: astore #7
        //   156: aload #5
        //   158: astore #7
        //   160: aload #8
        //   162: aload #9
        //   164: invokevirtual toString : ()Ljava/lang/String;
        //   167: invokevirtual parse : (Ljava/lang/String;)Ljava/util/Date;
        //   170: astore #9
        //   172: aload #5
        //   174: astore #7
        //   176: aload #5
        //   178: astore #7
        //   180: aload #5
        //   182: astore #7
        //   184: new java/lang/StringBuilder
        //   187: astore #8
        //   189: aload #5
        //   191: astore #7
        //   193: aload #5
        //   195: astore #7
        //   197: aload #5
        //   199: astore #7
        //   201: aload #8
        //   203: invokespecial <init> : ()V
        //   206: aload #5
        //   208: astore #7
        //   210: aload #5
        //   212: astore #7
        //   214: aload #5
        //   216: astore #7
        //   218: aload #8
        //   220: ldc 'date>='
        //   222: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   225: pop
        //   226: aload #5
        //   228: astore #7
        //   230: aload #5
        //   232: astore #7
        //   234: aload #5
        //   236: astore #7
        //   238: aload #8
        //   240: aload #9
        //   242: invokevirtual getTime : ()J
        //   245: invokevirtual append : (J)Ljava/lang/StringBuilder;
        //   248: pop
        //   249: aload #5
        //   251: astore #7
        //   253: aload #5
        //   255: astore #7
        //   257: aload #5
        //   259: astore #7
        //   261: aload #8
        //   263: invokevirtual toString : ()Ljava/lang/String;
        //   266: astore #10
        //   268: aload #5
        //   270: astore #7
        //   272: aload #5
        //   274: astore #7
        //   276: aload #5
        //   278: astore #7
        //   280: new java/lang/StringBuilder
        //   283: astore #8
        //   285: aload #5
        //   287: astore #7
        //   289: aload #5
        //   291: astore #7
        //   293: aload #5
        //   295: astore #7
        //   297: aload #8
        //   299: invokespecial <init> : ()V
        //   302: aload #5
        //   304: astore #7
        //   306: aload #5
        //   308: astore #7
        //   310: aload #5
        //   312: astore #7
        //   314: aload #8
        //   316: ldc 'DELETE FROM Chat_database WHERE ngay_nhan = ''
        //   318: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   321: pop
        //   322: aload #5
        //   324: astore #7
        //   326: aload #5
        //   328: astore #7
        //   330: aload #5
        //   332: astore #7
        //   334: aload #8
        //   336: aload #6
        //   338: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   341: pop
        //   342: aload #5
        //   344: astore #7
        //   346: aload #5
        //   348: astore #7
        //   350: aload #5
        //   352: astore #7
        //   354: aload #8
        //   356: ldc '' AND use_app = 'sms''
        //   358: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   361: pop
        //   362: aload #5
        //   364: astore #7
        //   366: aload #5
        //   368: astore #7
        //   370: aload #5
        //   372: astore #7
        //   374: aload #8
        //   376: invokevirtual toString : ()Ljava/lang/String;
        //   379: astore #8
        //   381: aload #5
        //   383: astore #7
        //   385: aload #5
        //   387: astore #7
        //   389: aload #5
        //   391: astore #7
        //   393: aload_1
        //   394: getfield db : Ltamhoang/ldpro4/data/Database;
        //   397: aload #8
        //   399: invokevirtual QueryData : (Ljava/lang/String;)V
        //   402: aload #5
        //   404: astore #7
        //   406: aload #5
        //   408: astore #7
        //   410: aload #5
        //   412: astore #7
        //   414: aload_1
        //   415: getfield db : Ltamhoang/ldpro4/data/Database;
        //   418: ldc_w 'Select * From tbl_kh_new'
        //   421: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   424: astore #11
        //   426: aload #5
        //   428: astore #7
        //   430: aload #5
        //   432: astore #7
        //   434: aload #5
        //   436: astore #7
        //   438: new org/json/JSONObject
        //   441: astore #12
        //   443: aload #5
        //   445: astore #7
        //   447: aload #5
        //   449: astore #7
        //   451: aload #5
        //   453: astore #7
        //   455: aload #12
        //   457: invokespecial <init> : ()V
        //   460: aload #5
        //   462: astore #7
        //   464: aload #5
        //   466: astore #7
        //   468: aload #5
        //   470: astore #7
        //   472: aload #11
        //   474: invokeinterface moveToNext : ()Z
        //   479: istore #13
        //   481: iload #13
        //   483: ifeq -> 576
        //   486: new org/json/JSONObject
        //   489: astore #7
        //   491: aload #7
        //   493: invokespecial <init> : ()V
        //   496: aload #7
        //   498: ldc_w 'type_kh'
        //   501: aload #11
        //   503: iconst_3
        //   504: invokeinterface getString : (I)Ljava/lang/String;
        //   509: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   512: pop
        //   513: aload #7
        //   515: ldc_w 'ten_kh'
        //   518: aload #11
        //   520: iconst_0
        //   521: invokeinterface getString : (I)Ljava/lang/String;
        //   526: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   529: pop
        //   530: aload #7
        //   532: ldc_w 'sdt'
        //   535: aload #11
        //   537: iconst_1
        //   538: invokeinterface getString : (I)Ljava/lang/String;
        //   543: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   546: pop
        //   547: aload #7
        //   549: ldc_w 'so_tn'
        //   552: iconst_0
        //   553: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   556: pop
        //   557: aload #12
        //   559: aload #11
        //   561: iconst_1
        //   562: invokeinterface getString : (I)Ljava/lang/String;
        //   567: aload #7
        //   569: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   572: pop
        //   573: goto -> 460
        //   576: aload #11
        //   578: invokeinterface close : ()V
        //   583: ldc_w 'content://sms'
        //   586: invokestatic parse : (Ljava/lang/String;)Landroid/net/Uri;
        //   589: astore #14
        //   591: ldc_w 'ten_kh'
        //   594: astore #8
        //   596: aload_0
        //   597: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   600: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
        //   603: astore #15
        //   605: ldc_w 'type_kh'
        //   608: astore #16
        //   610: aload #15
        //   612: aload #14
        //   614: aconst_null
        //   615: aload #10
        //   617: aconst_null
        //   618: ldc_w 'date ASC'
        //   621: invokevirtual query : (Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   624: astore #17
        //   626: aload_0
        //   627: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   630: aload #17
        //   632: invokevirtual startManagingCursor : (Landroid/database/Cursor;)V
        //   635: aload #17
        //   637: invokeinterface getCount : ()I
        //   642: istore #18
        //   644: aload #17
        //   646: invokeinterface moveToFirst : ()Z
        //   651: ifeq -> 1544
        //   654: aload_1
        //   655: getfield db : Ltamhoang/ldpro4/data/Database;
        //   658: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
        //   661: astore #7
        //   663: new android/database/DatabaseUtils$InsertHelper
        //   666: astore #5
        //   668: aload #5
        //   670: aload #7
        //   672: ldc_w 'Chat_database'
        //   675: invokespecial <init> : (Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)V
        //   678: aload #7
        //   680: invokevirtual beginTransaction : ()V
        //   683: iconst_0
        //   684: istore #19
        //   686: aload #8
        //   688: astore_1
        //   689: iload #19
        //   691: iload #18
        //   693: if_icmpge -> 1435
        //   696: aload #17
        //   698: aload #17
        //   700: ldc_w 'date'
        //   703: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
        //   708: invokeinterface getLong : (I)J
        //   713: lstore #20
        //   715: new java/lang/StringBuilder
        //   718: astore #22
        //   720: aload #22
        //   722: invokespecial <init> : ()V
        //   725: new java/util/Date
        //   728: astore #9
        //   730: aload #7
        //   732: astore #8
        //   734: aload #9
        //   736: lload #20
        //   738: invokestatic valueOf : (J)Ljava/lang/Long;
        //   741: invokevirtual longValue : ()J
        //   744: invokespecial <init> : (J)V
        //   747: aload #22
        //   749: ldc_w 'HH:mm:ss'
        //   752: aload #9
        //   754: invokestatic format : (Ljava/lang/CharSequence;Ljava/util/Date;)Ljava/lang/CharSequence;
        //   757: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   760: pop
        //   761: aload #22
        //   763: aload_3
        //   764: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   767: pop
        //   768: aload #22
        //   770: invokevirtual toString : ()Ljava/lang/String;
        //   773: astore #23
        //   775: aload #17
        //   777: aload #17
        //   779: ldc_w 'address'
        //   782: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
        //   787: invokeinterface getString : (I)Ljava/lang/String;
        //   792: aload #4
        //   794: aload_3
        //   795: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   798: astore #24
        //   800: aload #17
        //   802: aload #17
        //   804: ldc_w 'body'
        //   807: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
        //   812: invokeinterface getString : (I)Ljava/lang/String;
        //   817: invokevirtual toString : ()Ljava/lang/String;
        //   820: aload_2
        //   821: aload #4
        //   823: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   826: ldc_w '"'
        //   829: aload #4
        //   831: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   834: astore #25
        //   836: aload #17
        //   838: aload #17
        //   840: ldc_w 'type'
        //   843: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
        //   848: invokeinterface getString : (I)Ljava/lang/String;
        //   853: astore #26
        //   855: aload #24
        //   857: ldc_w '0'
        //   860: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   863: istore #13
        //   865: iload #13
        //   867: ifeq -> 933
        //   870: new java/lang/StringBuilder
        //   873: astore #9
        //   875: aload #9
        //   877: invokespecial <init> : ()V
        //   880: aload #9
        //   882: ldc_w '+84'
        //   885: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   888: pop
        //   889: aload #9
        //   891: aload #24
        //   893: iconst_1
        //   894: invokevirtual substring : (I)Ljava/lang/String;
        //   897: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   900: pop
        //   901: aload #9
        //   903: invokevirtual toString : ()Ljava/lang/String;
        //   906: astore #24
        //   908: goto -> 933
        //   911: astore_1
        //   912: aload #5
        //   914: astore #7
        //   916: aload_1
        //   917: astore #5
        //   919: aload #8
        //   921: astore_1
        //   922: goto -> 1528
        //   925: astore_1
        //   926: aload #8
        //   928: astore #7
        //   930: goto -> 1489
        //   933: aload_0
        //   934: getfield db : Ltamhoang/ldpro4/data/Database;
        //   937: astore #22
        //   939: new java/lang/StringBuilder
        //   942: astore #9
        //   944: aload #9
        //   946: invokespecial <init> : ()V
        //   949: aload #9
        //   951: ldc_w 'Update tbl_tinnhanS set gio_nhan =''
        //   954: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   957: pop
        //   958: aload #9
        //   960: aload #23
        //   962: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   965: pop
        //   966: aload #9
        //   968: ldc_w '' WHERE nd_goc = ''
        //   971: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   974: pop
        //   975: aload #9
        //   977: aload #25
        //   979: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   982: pop
        //   983: aload #9
        //   985: ldc_w '' AND so_dienthoai = ''
        //   988: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   991: pop
        //   992: aload #9
        //   994: aload #24
        //   996: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   999: pop
        //   1000: aload #9
        //   1002: ldc_w '' AND ngay_nhan = ''
        //   1005: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1008: pop
        //   1009: aload #9
        //   1011: aload #6
        //   1013: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1016: pop
        //   1017: aload #9
        //   1019: aload_2
        //   1020: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1023: pop
        //   1024: aload #22
        //   1026: aload #9
        //   1028: invokevirtual toString : ()Ljava/lang/String;
        //   1031: invokevirtual QueryData : (Ljava/lang/String;)V
        //   1034: aload #12
        //   1036: aload #24
        //   1038: invokevirtual has : (Ljava/lang/String;)Z
        //   1041: ifeq -> 1315
        //   1044: aload #12
        //   1046: aload #24
        //   1048: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   1051: astore #27
        //   1053: aload #27
        //   1055: ldc_w 'so_tn'
        //   1058: aload #27
        //   1060: ldc_w 'so_tn'
        //   1063: invokevirtual getInt : (Ljava/lang/String;)I
        //   1066: iconst_1
        //   1067: iadd
        //   1068: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1071: pop
        //   1072: aload #27
        //   1074: aload #25
        //   1076: aload #25
        //   1078: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1081: pop
        //   1082: aload #5
        //   1084: invokevirtual prepareForInsert : ()V
        //   1087: aload #5
        //   1089: astore #28
        //   1091: aload #28
        //   1093: astore #9
        //   1095: aload #8
        //   1097: astore #22
        //   1099: aload #28
        //   1101: aload #28
        //   1103: ldc_w 'ngay_nhan'
        //   1106: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1109: aload #6
        //   1111: invokevirtual bind : (ILjava/lang/String;)V
        //   1114: aload #28
        //   1116: astore #9
        //   1118: aload #8
        //   1120: astore #22
        //   1122: aload #28
        //   1124: aload #28
        //   1126: ldc_w 'gio_nhan'
        //   1129: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1132: aload #23
        //   1134: invokevirtual bind : (ILjava/lang/String;)V
        //   1137: aload #28
        //   1139: astore #9
        //   1141: aload #8
        //   1143: astore #22
        //   1145: aload #28
        //   1147: aload #28
        //   1149: aload #16
        //   1151: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1154: aload #26
        //   1156: invokevirtual bind : (ILjava/lang/String;)V
        //   1159: aload_1
        //   1160: astore #23
        //   1162: aload #28
        //   1164: astore #9
        //   1166: aload #8
        //   1168: astore #22
        //   1170: aload #28
        //   1172: aload #28
        //   1174: aload #23
        //   1176: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1179: aload #27
        //   1181: aload #23
        //   1183: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1186: invokevirtual bind : (ILjava/lang/String;)V
        //   1189: aload #28
        //   1191: astore #9
        //   1193: aload #8
        //   1195: astore #22
        //   1197: aload #28
        //   1199: aload #28
        //   1201: ldc_w 'so_dienthoai'
        //   1204: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1207: aload #24
        //   1209: invokevirtual bind : (ILjava/lang/String;)V
        //   1212: aload #28
        //   1214: astore #9
        //   1216: aload #8
        //   1218: astore #22
        //   1220: aload #28
        //   1222: aload #28
        //   1224: ldc_w 'use_app'
        //   1227: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1230: ldc 'sms'
        //   1232: invokevirtual bind : (ILjava/lang/String;)V
        //   1235: aload #28
        //   1237: astore #9
        //   1239: aload #8
        //   1241: astore #22
        //   1243: aload #28
        //   1245: aload #28
        //   1247: ldc_w 'nd_goc'
        //   1250: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1253: aload #25
        //   1255: invokevirtual bind : (ILjava/lang/String;)V
        //   1258: aload #28
        //   1260: astore #9
        //   1262: aload #8
        //   1264: astore #22
        //   1266: aload #28
        //   1268: aload #28
        //   1270: ldc_w 'del_sms'
        //   1273: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   1276: iconst_1
        //   1277: invokevirtual bind : (II)V
        //   1280: aload #28
        //   1282: astore #9
        //   1284: aload #8
        //   1286: astore #22
        //   1288: aload #28
        //   1290: invokevirtual execute : ()J
        //   1293: pop2
        //   1294: aload #28
        //   1296: astore #9
        //   1298: aload #8
        //   1300: astore #22
        //   1302: aload #12
        //   1304: aload #24
        //   1306: aload #27
        //   1308: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1311: pop
        //   1312: goto -> 1315
        //   1315: aload #5
        //   1317: astore #24
        //   1319: aload #24
        //   1321: astore #9
        //   1323: aload #8
        //   1325: astore #22
        //   1327: aload #17
        //   1329: invokeinterface moveToNext : ()Z
        //   1334: pop
        //   1335: iinc #19, 1
        //   1338: aload #24
        //   1340: astore #5
        //   1342: aload #8
        //   1344: astore #7
        //   1346: goto -> 689
        //   1349: astore #7
        //   1351: aload #5
        //   1353: astore_1
        //   1354: aload #7
        //   1356: astore #5
        //   1358: aload_1
        //   1359: astore #7
        //   1361: aload #8
        //   1363: astore_1
        //   1364: goto -> 1528
        //   1367: astore_1
        //   1368: aload #8
        //   1370: astore #7
        //   1372: goto -> 1489
        //   1375: astore #8
        //   1377: aload #7
        //   1379: astore_1
        //   1380: aload #5
        //   1382: astore #7
        //   1384: aload #8
        //   1386: astore #5
        //   1388: goto -> 1528
        //   1391: astore_1
        //   1392: goto -> 1489
        //   1395: astore #8
        //   1397: aload #7
        //   1399: astore_1
        //   1400: aload #5
        //   1402: astore #7
        //   1404: aload #8
        //   1406: astore #5
        //   1408: goto -> 1528
        //   1411: astore_1
        //   1412: goto -> 1489
        //   1415: astore #8
        //   1417: aload #7
        //   1419: astore_1
        //   1420: aload #5
        //   1422: astore #7
        //   1424: aload #8
        //   1426: astore #5
        //   1428: goto -> 1528
        //   1431: astore_1
        //   1432: goto -> 1489
        //   1435: aload #7
        //   1437: astore_1
        //   1438: aload #5
        //   1440: astore #8
        //   1442: aload #8
        //   1444: astore #9
        //   1446: aload_1
        //   1447: astore #22
        //   1449: aload_1
        //   1450: invokevirtual setTransactionSuccessful : ()V
        //   1453: aload_1
        //   1454: invokevirtual endTransaction : ()V
        //   1457: aload #8
        //   1459: invokevirtual close : ()V
        //   1462: aload_1
        //   1463: astore #7
        //   1465: goto -> 1511
        //   1468: astore_1
        //   1469: goto -> 1489
        //   1472: astore #8
        //   1474: aload #7
        //   1476: astore_1
        //   1477: aload #5
        //   1479: astore #7
        //   1481: aload #8
        //   1483: astore #5
        //   1485: goto -> 1528
        //   1488: astore_1
        //   1489: aload #5
        //   1491: astore #9
        //   1493: aload #7
        //   1495: astore #22
        //   1497: aload_1
        //   1498: invokevirtual printStackTrace : ()V
        //   1501: aload #7
        //   1503: invokevirtual endTransaction : ()V
        //   1506: aload #5
        //   1508: invokevirtual close : ()V
        //   1511: aload #7
        //   1513: invokevirtual close : ()V
        //   1516: goto -> 1589
        //   1519: astore #5
        //   1521: aload #22
        //   1523: astore_1
        //   1524: aload #9
        //   1526: astore #7
        //   1528: aload_1
        //   1529: invokevirtual endTransaction : ()V
        //   1532: aload #7
        //   1534: invokevirtual close : ()V
        //   1537: aload_1
        //   1538: invokevirtual close : ()V
        //   1541: aload #5
        //   1543: athrow
        //   1544: goto -> 1589
        //   1547: astore #5
        //   1549: goto -> 1564
        //   1552: astore #5
        //   1554: goto -> 1574
        //   1557: astore #5
        //   1559: goto -> 1584
        //   1562: astore #5
        //   1564: aload #5
        //   1566: invokevirtual printStackTrace : ()V
        //   1569: goto -> 1592
        //   1572: astore #5
        //   1574: aload #5
        //   1576: invokevirtual printStackTrace : ()V
        //   1579: goto -> 1589
        //   1582: astore #5
        //   1584: aload #5
        //   1586: invokevirtual printStackTrace : ()V
        //   1589: goto -> 1592
        //   1592: return
        // Exception table:
        //   from	to	target	type
        //   50	55	1582	android/database/sqlite/SQLiteException
        //   50	55	1572	org/json/JSONException
        //   50	55	1562	java/text/ParseException
        //   67	74	1582	android/database/sqlite/SQLiteException
        //   67	74	1572	org/json/JSONException
        //   67	74	1562	java/text/ParseException
        //   86	91	1582	android/database/sqlite/SQLiteException
        //   86	91	1572	org/json/JSONException
        //   86	91	1562	java/text/ParseException
        //   103	108	1582	android/database/sqlite/SQLiteException
        //   103	108	1572	org/json/JSONException
        //   103	108	1562	java/text/ParseException
        //   120	128	1582	android/database/sqlite/SQLiteException
        //   120	128	1572	org/json/JSONException
        //   120	128	1562	java/text/ParseException
        //   140	148	1582	android/database/sqlite/SQLiteException
        //   140	148	1572	org/json/JSONException
        //   140	148	1562	java/text/ParseException
        //   160	172	1582	android/database/sqlite/SQLiteException
        //   160	172	1572	org/json/JSONException
        //   160	172	1562	java/text/ParseException
        //   184	189	1582	android/database/sqlite/SQLiteException
        //   184	189	1572	org/json/JSONException
        //   184	189	1562	java/text/ParseException
        //   201	206	1582	android/database/sqlite/SQLiteException
        //   201	206	1572	org/json/JSONException
        //   201	206	1562	java/text/ParseException
        //   218	226	1582	android/database/sqlite/SQLiteException
        //   218	226	1572	org/json/JSONException
        //   218	226	1562	java/text/ParseException
        //   238	249	1582	android/database/sqlite/SQLiteException
        //   238	249	1572	org/json/JSONException
        //   238	249	1562	java/text/ParseException
        //   261	268	1582	android/database/sqlite/SQLiteException
        //   261	268	1572	org/json/JSONException
        //   261	268	1562	java/text/ParseException
        //   280	285	1582	android/database/sqlite/SQLiteException
        //   280	285	1572	org/json/JSONException
        //   280	285	1562	java/text/ParseException
        //   297	302	1582	android/database/sqlite/SQLiteException
        //   297	302	1572	org/json/JSONException
        //   297	302	1562	java/text/ParseException
        //   314	322	1582	android/database/sqlite/SQLiteException
        //   314	322	1572	org/json/JSONException
        //   314	322	1562	java/text/ParseException
        //   334	342	1582	android/database/sqlite/SQLiteException
        //   334	342	1572	org/json/JSONException
        //   334	342	1562	java/text/ParseException
        //   354	362	1582	android/database/sqlite/SQLiteException
        //   354	362	1572	org/json/JSONException
        //   354	362	1562	java/text/ParseException
        //   374	381	1582	android/database/sqlite/SQLiteException
        //   374	381	1572	org/json/JSONException
        //   374	381	1562	java/text/ParseException
        //   393	402	1582	android/database/sqlite/SQLiteException
        //   393	402	1572	org/json/JSONException
        //   393	402	1562	java/text/ParseException
        //   414	426	1582	android/database/sqlite/SQLiteException
        //   414	426	1572	org/json/JSONException
        //   414	426	1562	java/text/ParseException
        //   438	443	1582	android/database/sqlite/SQLiteException
        //   438	443	1572	org/json/JSONException
        //   438	443	1562	java/text/ParseException
        //   455	460	1582	android/database/sqlite/SQLiteException
        //   455	460	1572	org/json/JSONException
        //   455	460	1562	java/text/ParseException
        //   472	481	1582	android/database/sqlite/SQLiteException
        //   472	481	1572	org/json/JSONException
        //   472	481	1562	java/text/ParseException
        //   486	573	1557	android/database/sqlite/SQLiteException
        //   486	573	1552	org/json/JSONException
        //   486	573	1547	java/text/ParseException
        //   576	591	1557	android/database/sqlite/SQLiteException
        //   576	591	1552	org/json/JSONException
        //   576	591	1547	java/text/ParseException
        //   596	605	1557	android/database/sqlite/SQLiteException
        //   596	605	1552	org/json/JSONException
        //   596	605	1547	java/text/ParseException
        //   610	678	1557	android/database/sqlite/SQLiteException
        //   610	678	1552	org/json/JSONException
        //   610	678	1547	java/text/ParseException
        //   678	683	1488	java/lang/Exception
        //   678	683	1472	finally
        //   696	715	1431	java/lang/Exception
        //   696	715	1415	finally
        //   715	725	1411	java/lang/Exception
        //   715	725	1395	finally
        //   725	730	1391	java/lang/Exception
        //   725	730	1375	finally
        //   734	865	1367	java/lang/Exception
        //   734	865	1349	finally
        //   870	908	925	java/lang/Exception
        //   870	908	911	finally
        //   933	1087	1367	java/lang/Exception
        //   933	1087	1349	finally
        //   1099	1114	1468	java/lang/Exception
        //   1099	1114	1519	finally
        //   1122	1137	1468	java/lang/Exception
        //   1122	1137	1519	finally
        //   1145	1159	1468	java/lang/Exception
        //   1145	1159	1519	finally
        //   1170	1189	1468	java/lang/Exception
        //   1170	1189	1519	finally
        //   1197	1212	1468	java/lang/Exception
        //   1197	1212	1519	finally
        //   1220	1235	1468	java/lang/Exception
        //   1220	1235	1519	finally
        //   1243	1258	1468	java/lang/Exception
        //   1243	1258	1519	finally
        //   1266	1280	1468	java/lang/Exception
        //   1266	1280	1519	finally
        //   1288	1294	1468	java/lang/Exception
        //   1288	1294	1519	finally
        //   1302	1312	1468	java/lang/Exception
        //   1302	1312	1519	finally
        //   1327	1335	1468	java/lang/Exception
        //   1327	1335	1519	finally
        //   1449	1453	1468	java/lang/Exception
        //   1449	1453	1519	finally
        //   1453	1462	1557	android/database/sqlite/SQLiteException
        //   1453	1462	1552	org/json/JSONException
        //   1453	1462	1547	java/text/ParseException
        //   1497	1501	1519	finally
        //   1501	1511	1557	android/database/sqlite/SQLiteException
        //   1501	1511	1552	org/json/JSONException
        //   1501	1511	1547	java/text/ParseException
        //   1511	1516	1557	android/database/sqlite/SQLiteException
        //   1511	1516	1552	org/json/JSONException
        //   1511	1516	1547	java/text/ParseException
        //   1528	1541	1557	android/database/sqlite/SQLiteException
        //   1528	1541	1552	org/json/JSONException
        //   1528	1541	1547	java/text/ParseException
        //   1541	1544	1557	android/database/sqlite/SQLiteException
        //   1541	1544	1552	org/json/JSONException
        //   1541	1544	1547	java/text/ParseException
    }

    private void notificationPermission() {
        boolean bool;
        ComponentName componentName = new ComponentName((Context)getActivity(), NotificationReader.class);
        String str = Settings.Secure.getString(getActivity().getContentResolver(), "enabled_notification_listeners");
        if (str == null || !str.contains(componentName.flattenToString())) {
            bool = false;
        } else {
            bool = true;
        }
        if (!bool)
            showAlertBox("Truy cthb, "Hcho phphmtruy cthbcthokhochnnhtin.").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            if (Build.VERSION.SDK_INT >= 22) {
                Frag_Chat_Manager.this.getActivity().startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            } else {
                Frag_Chat_Manager.this.getActivity().startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            }
        }
    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            param1DialogInterface.dismiss();
        }
    }).show().setCanceledOnTouchOutside(false);
}

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.v = paramLayoutInflater.inflate(2131427396, paramViewGroup, false);
        this.db = new Database((Context)getActivity());
        this.btn_Thongbao = (Button)this.v.findViewById(2131230808);
        this.btn_login = (Button)this.v.findViewById(2131230822);
        this.listviewKH = (ListView)this.v.findViewById(2131231049);
        this.btn_Thongbao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (Build.VERSION.SDK_INT >= 22) {
                    Frag_Chat_Manager.this.startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
                } else {
                    Frag_Chat_Manager.this.startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
                }
            }
        });
        this.listviewKH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Intent intent = new Intent((Context)Frag_Chat_Manager.this.getActivity(), Chatbox.class);
                intent.putExtra("tenKH", Frag_Chat_Manager.this.mTenKH.get(param1Int));
                intent.putExtra("so_dienthoai", Frag_Chat_Manager.this.mSDT.get(param1Int));
                intent.putExtra("app", Frag_Chat_Manager.this.mApp.get(param1Int));
                Frag_Chat_Manager.this.startActivity(intent);
            }
        });
        notificationPermission();
        Handler handler = new Handler();
        this.handler = handler;
        handler.postDelayed(this.runnable, 1000L);
        return this.v;
    }

    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacks(this.runnable);
    }

    public void onResume() {
        getSMS();
        XemListview();
        super.onResume();
    }

    public AlertDialog.Builder showAlertBox(String paramString1, String paramString2) {
        return (new AlertDialog.Builder((Context)getActivity())).setTitle(paramString1).setMessage(paramString2);
    }

class Chat_Main extends ArrayAdapter {
    public Chat_Main(Context param1Context, int param1Int, List<String> param1List) {
        super(param1Context, param1Int, param1List);
    }

    public View getView(final int position, View param1View, ViewGroup param1ViewGroup) {
        ViewHolder viewHolder;
        param1View = null;
        if (!false) {
            param1View = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427397, null);
            viewHolder = new ViewHolder();
            viewHolder.add_contacts = (ImageButton)param1View.findViewById(2131230766);
            viewHolder.tv_delete = (TextView)param1View.findViewById(2131231419);
            viewHolder.imageView = (ImageView)param1View.findViewById(2131231003);
            viewHolder.TenKH = (TextView)param1View.findViewById(2131231375);
            viewHolder.ndChat = (TextView)param1View.findViewById(2131231381);
        } else {
            throw new NullPointerException();
        }
        if (((String)Frag_Chat_Manager.this.mApp.get(position)).indexOf("WA") > -1) {
            viewHolder.imageView.setBackgroundResource(2131165350);
        } else if (((String)Frag_Chat_Manager.this.mApp.get(position)).indexOf("VI") > -1) {
            viewHolder.imageView.setBackgroundResource(2131165349);
        } else if (((String)Frag_Chat_Manager.this.mApp.get(position)).indexOf("ZL") > -1) {
            viewHolder.imageView.setBackgroundResource(2131165352);
        } else if (((String)Frag_Chat_Manager.this.mApp.get(position)).indexOf("TL") > -1) {
            viewHolder.imageView.setBackgroundResource(2131165346);
            viewHolder.tv_delete.setVisibility(8);
        } else if (((String)Frag_Chat_Manager.this.mApp.get(position)).indexOf("sms") > -1) {
            viewHolder.imageView.setBackgroundResource(2131165343);
            viewHolder.add_contacts.setVisibility(8);
            viewHolder.tv_delete.setVisibility(8);
        }
        viewHolder.add_contacts.setFocusable(false);
        viewHolder.add_contacts.setFocusableInTouchMode(false);
        viewHolder.add_contacts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param2View) {
                Intent intent = new Intent((Context)Frag_Chat_Manager.this.getActivity(), Activity_AddKH.class);
                intent.putExtra("tenKH", Frag_Chat_Manager.this.mTenKH.get(position));
                intent.putExtra("so_dienthoai", Frag_Chat_Manager.this.mSDT.get(position));
                intent.putExtra("use_app", Frag_Chat_Manager.this.mApp.get(position));
                Frag_Chat_Manager.this.startActivity(intent);
            }
        });
        if (MainActivity.DSkhachhang.indexOf(Frag_Chat_Manager.this.mTenKH.get(position)) > -1)
            viewHolder.add_contacts.setVisibility(8);
        viewHolder.tv_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param2View) {
                AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_Chat_Manager.this.getActivity());
                builder.setTitle("XoKh);
                        builder.setMessage("Sxhdlichat tkhnkhthkhphvkhthtltin nh);
                                builder.setNegativeButton("C, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface param3DialogInterface, int param3Int) {
                    param3Int = MainActivity.arr_TenKH.indexOf(Frag_Chat_Manager.this.mTenKH.get(position));
                    MainActivity.arr_TenKH.remove(param3Int);
                    MainActivity.contactslist.remove(param3Int);
                    Frag_Chat_Manager.this.XemListview();
                    param3DialogInterface.dismiss();
                    Toast.makeText((Context)Frag_Chat_Manager.this.getActivity(), "x, 1).show();
                }
            });
              builder.setPositiveButton("Kh, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param3DialogInterface, int param3Int) {
                param3DialogInterface.dismiss();
            }
        });
        builder.show();
    }
});
        viewHolder.TenKH.setText(Frag_Chat_Manager.this.mTenKH.get(position));
        viewHolder.ndChat.setText(Frag_Chat_Manager.this.mNoiDung.get(position));
        return param1View;
        }

class ViewHolder {
    TextView TenKH;

    ImageButton add_contacts;

    ImageView imageView;

    TextView ndChat;

    TextView tv_delete;
}
  }

class null implements View.OnClickListener {
public void onClick(View param1View) {
        Intent intent = new Intent((Context)Frag_Chat_Manager.this.getActivity(), Activity_AddKH.class);
        intent.putExtra("tenKH", Frag_Chat_Manager.this.mTenKH.get(position));
        intent.putExtra("so_dienthoai", Frag_Chat_Manager.this.mSDT.get(position));
        intent.putExtra("use_app", Frag_Chat_Manager.this.mApp.get(position));
        Frag_Chat_Manager.this.startActivity(intent);
        }
        }

class null implements View.OnClickListener {
public void onClick(View param1View) {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_Chat_Manager.this.getActivity());
        builder.setTitle("XoKh);
        builder.setMessage("Sxhdlichat tkhnkhthkhphvkhthtltin nh);
        builder.setNegativeButton("C, new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param3DialogInterface, int param3Int) {
        param3Int = MainActivity.arr_TenKH.indexOf(Frag_Chat_Manager.this.mTenKH.get(position));
        MainActivity.arr_TenKH.remove(param3Int);
        MainActivity.contactslist.remove(param3Int);
        Frag_Chat_Manager.this.XemListview();
        param3DialogInterface.dismiss();
        Toast.makeText((Context)Frag_Chat_Manager.this.getActivity(), "x, 1).show();
        }
        });
        builder.setPositiveButton("Kh, new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param3DialogInterface, int param3Int) {
        param3DialogInterface.dismiss();
        }
        });
        builder.show();
        }
        }

class null implements DialogInterface.OnClickListener {
public void onClick(DialogInterface param1DialogInterface, int param1Int) {
        param1Int = MainActivity.arr_TenKH.indexOf(Frag_Chat_Manager.this.mTenKH.get(position));
        MainActivity.arr_TenKH.remove(param1Int);
        MainActivity.contactslist.remove(param1Int);
        Frag_Chat_Manager.this.XemListview();
        param1DialogInterface.dismiss();
        Toast.makeText((Context)Frag_Chat_Manager.this.getActivity(), "x, 1).show();
        }
        }

class null implements DialogInterface.OnClickListener {
public void onClick(DialogInterface param1DialogInterface, int param1Int) {
        param1DialogInterface.dismiss();
        }
        }

class ViewHolder {
    TextView TenKH;

    ImageButton add_contacts;

    ImageView imageView;

    TextView ndChat;

    TextView tv_delete;
}
}
