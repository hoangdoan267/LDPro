package tamhoang.ldpro4.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Congthuc.Congthuc;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.data.Database;

public class Frag_Chaytrang extends Fragment {
    static long Curent_date_time = 0L;

    String DangXuat = null;

    int Dem = 0;

    String Dieukien = "(the_loai = 'deb' or the_loai = 'det')";

    int GameType = 0;

    public List<Integer> HuyCuoc = new ArrayList<Integer>();

    boolean LoLive = false;

    int MaxChay;

    public List<String> NoiDung = new ArrayList<String>();

    int Price = 0;

    int PriceLive = 0;

    public List<String> SoTin = new ArrayList<String>();

    public List<Integer> TheLoai = new ArrayList<Integer>();

    public List<String> ThoiGian = new ArrayList<String>();

    public List<String> TienCuoc = new ArrayList<String>();

    String ToDay = "";

    Button btn_MaXuat;

    Button btn_Xuatso;

    Database db;

    String donvi = "n ";

    EditText edt_tien;

    Handler handler;

    JSONArray jsonArray = new JSONArray();

    JSONObject jsonChayTrang = new JSONObject();

    JSONObject jsonGia = new JSONObject();

    JSONObject jsonTienxien = new JSONObject();

    String lay_xien = " length(so_chon) = 5 ";

    LinearLayout li_loaide;

    LinearLayout li_loaixi;

    ListView lview;

    public List<String> mGia = new ArrayList<String>();

    public List<String> mMax = new ArrayList<String>();

    public List<Integer> mNhay = new ArrayList<Integer>();

    public List<String> mSo = new ArrayList<String>();

    public List<String> mTienNhan = new ArrayList<String>();

    public List<String> mTienOm = new ArrayList<String>();

    public List<String> mTienTon = new ArrayList<String>();

    public List<String> mTienchuyen = new ArrayList<String>();

    public List<String> mpassword = new ArrayList<String>();

    public List<String> mwebsite = new ArrayList<String>();

    double myBalance = 0.0D;

    String myMax = "";

    RadioButton radio_de;

    RadioButton radio_dea;

    RadioButton radio_deb;

    RadioButton radio_dec;

    RadioButton radio_ded;

    RadioButton radio_lo;

    RadioButton radio_xi;

    RadioButton radio_xi2;

    RadioButton radio_xi3;

    RadioButton radio_xi4;

    private Runnable runnable = new Runnable() {
        public void run() {
            int i = 1;
            int j = 1;
            if (Frag_Chaytrang.Curent_date_time > 0L) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                Date date1 = Frag_Chaytrang.parseDate("01:00");
                Date date2 = Frag_Chaytrang.parseDate("18:14");
                Date date3 = Frag_Chaytrang.parseDate("18:28");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(Frag_Chaytrang.Curent_date_time * 1000L);
                int k = calendar.get(11);
                i = calendar.get(12);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(k);
                stringBuilder.append(":");
                stringBuilder.append(i);
                Date date4 = Frag_Chaytrang.parseDate(stringBuilder.toString());
                if (date4.after(date2) && date4.before(date3) && !Frag_Chaytrang.this.LoLive) {
                    Frag_Chaytrang.this.radio_xi.setEnabled(false);
                    Frag_Chaytrang.this.radio_lo.setText("LLive");
                    Frag_Chaytrang.this.LoLive = true;
                } else if (date4.after(date3)) {
                    Frag_Chaytrang.this.handler.removeCallbacks(Frag_Chaytrang.this.runnable);
                    j = 0;
                    Frag_Chaytrang.this.btn_Xuatso.setEnabled(false);
                    Frag_Chaytrang.this.btn_Xuatso.setText("Hgi);
                            Frag_Chaytrang.this.btn_Xuatso.setTextColor(-7829368);
                } else if (date4.before(date1)) {
                    Frag_Chaytrang.this.btn_Xuatso.setEnabled(false);
                    Frag_Chaytrang.this.btn_Xuatso.setText("Chm);
                            Frag_Chaytrang.this.btn_Xuatso.setTextColor(-7829368);
                    j = 0;
                }
                if (Frag_Chaytrang.this.LoLive == true && Frag_Chaytrang.this.radio_lo.isChecked()) {
                    Frag_Chaytrang frag_Chaytrang = Frag_Chaytrang.this;
                    frag_Chaytrang.Dem++;
                    if (Frag_Chaytrang.this.Dem >= 4) {
                        Frag_Chaytrang.this.Dem = 0;
                        Frag_Chaytrang.this.Laygia();
                    }
                }
                if (j) {
                    Button button = Frag_Chaytrang.this.btn_Xuatso;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append("Chtrang (");
                    stringBuilder1.append(simpleDateFormat.format(calendar.getTime()));
                    stringBuilder1.append(")");
                    button.setText(stringBuilder1.toString());
                }
            } else {
                Frag_Chaytrang.Curent_date_time = (new Timestamp(System.currentTimeMillis())).getTime() / 1000L;
                j = i;
            }
            if (j != 0) {
                Frag_Chaytrang.Curent_date_time++;
                Frag_Chaytrang.this.handler.postDelayed(this, 1000L);
            }
        }
    };

    int spin_pointion = -1;

    Spinner spr_trang;

    String the_loai = "deb";

    View v;

    String xuatDan = "De:";

    private String CreateJson() {
        // Byte code:
        //   0: ldc_w ','
        //   3: astore_1
        //   4: new org/json/JSONObject
        //   7: dup
        //   8: invokespecial <init> : ()V
        //   11: astore_2
        //   12: invokestatic getInstance : ()Ljava/util/Calendar;
        //   15: astore_3
        //   16: aload_3
        //   17: new java/util/Date
        //   20: dup
        //   21: invokespecial <init> : ()V
        //   24: invokevirtual setTime : (Ljava/util/Date;)V
        //   27: new java/text/SimpleDateFormat
        //   30: dup
        //   31: ldc_w 'yyyy-MM-dd'
        //   34: invokespecial <init> : (Ljava/lang/String;)V
        //   37: astore #4
        //   39: aload #4
        //   41: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   44: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   47: aload #4
        //   49: aload_3
        //   50: invokevirtual getTime : ()Ljava/util/Date;
        //   53: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
        //   56: astore #5
        //   58: aload_0
        //   59: getfield db : Ltamhoang/ldpro4/data/Database;
        //   62: astore #6
        //   64: new java/lang/StringBuilder
        //   67: dup
        //   68: invokespecial <init> : ()V
        //   71: astore #7
        //   73: aload #7
        //   75: ldc_w 'Select max(so_tin_nhan) from tbl_tinnhans where ngay_nhan = ''
        //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   81: pop
        //   82: aload #7
        //   84: aload #5
        //   86: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   89: pop
        //   90: aload #7
        //   92: ldc_w '' And ten_kh = ''
        //   95: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   98: pop
        //   99: aload #7
        //   101: aload_0
        //   102: getfield mwebsite : Ljava/util/List;
        //   105: aload_0
        //   106: getfield spin_pointion : I
        //   109: invokeinterface get : (I)Ljava/lang/Object;
        //   114: checkcast java/lang/String
        //   117: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   120: pop
        //   121: aload #7
        //   123: ldc_w '''
        //   126: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   129: pop
        //   130: aload #6
        //   132: aload #7
        //   134: invokevirtual toString : ()Ljava/lang/String;
        //   137: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   140: astore #7
        //   142: aload #7
        //   144: invokeinterface moveToFirst : ()Z
        //   149: pop
        //   150: aload #7
        //   152: iconst_0
        //   153: invokeinterface getInt : (I)I
        //   158: istore #8
        //   160: new org/json/JSONObject
        //   163: astore #6
        //   165: aload #6
        //   167: invokespecial <init> : ()V
        //   170: aload #6
        //   172: astore_2
        //   173: aload_2
        //   174: astore #6
        //   176: aload_3
        //   177: astore #9
        //   179: aload #4
        //   181: astore #9
        //   183: aload #5
        //   185: astore #9
        //   187: aload #7
        //   189: astore #9
        //   191: aload_2
        //   192: ldc_w 'Term'
        //   195: aload #5
        //   197: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   200: pop
        //   201: aload_2
        //   202: astore #6
        //   204: aload_3
        //   205: astore #9
        //   207: aload #4
        //   209: astore #9
        //   211: aload #5
        //   213: astore #9
        //   215: aload #7
        //   217: astore #9
        //   219: aload_2
        //   220: ldc_w 'IgnorePrice'
        //   223: iconst_1
        //   224: invokevirtual put : (Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   227: pop
        //   228: aload_2
        //   229: astore #6
        //   231: aload_3
        //   232: astore #9
        //   234: aload #4
        //   236: astore #9
        //   238: aload #5
        //   240: astore #9
        //   242: aload #7
        //   244: astore #9
        //   246: new org/json/JSONArray
        //   249: astore #10
        //   251: aload_2
        //   252: astore #6
        //   254: aload_3
        //   255: astore #9
        //   257: aload #4
        //   259: astore #9
        //   261: aload #5
        //   263: astore #9
        //   265: aload #7
        //   267: astore #9
        //   269: aload #10
        //   271: invokespecial <init> : ()V
        //   274: aload_2
        //   275: astore #6
        //   277: aload_3
        //   278: astore #9
        //   280: aload #4
        //   282: astore #9
        //   284: aload #5
        //   286: astore #9
        //   288: aload #7
        //   290: astore #9
        //   292: new org/json/JSONObject
        //   295: astore #11
        //   297: aload_2
        //   298: astore #6
        //   300: aload_3
        //   301: astore #9
        //   303: aload #4
        //   305: astore #9
        //   307: aload #5
        //   309: astore #9
        //   311: aload #7
        //   313: astore #9
        //   315: aload #11
        //   317: invokespecial <init> : ()V
        //   320: aload_2
        //   321: astore #6
        //   323: aload_3
        //   324: astore #9
        //   326: aload #4
        //   328: astore #9
        //   330: aload #5
        //   332: astore #9
        //   334: aload #7
        //   336: astore #9
        //   338: aload #11
        //   340: ldc_w 'GameType'
        //   343: iconst_0
        //   344: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   347: pop
        //   348: aload_2
        //   349: astore #6
        //   351: aload_3
        //   352: astore #9
        //   354: aload #4
        //   356: astore #9
        //   358: aload #5
        //   360: astore #9
        //   362: aload #7
        //   364: astore #9
        //   366: aload #11
        //   368: ldc_w 'BetType'
        //   371: aload_0
        //   372: getfield GameType : I
        //   375: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   378: pop
        //   379: aload_2
        //   380: astore #6
        //   382: aload_3
        //   383: astore #9
        //   385: aload #4
        //   387: astore #9
        //   389: aload #5
        //   391: astore #9
        //   393: aload #7
        //   395: astore #9
        //   397: new org/json/JSONArray
        //   400: astore #12
        //   402: aload_2
        //   403: astore #6
        //   405: aload_3
        //   406: astore #9
        //   408: aload #4
        //   410: astore #9
        //   412: aload #5
        //   414: astore #9
        //   416: aload #7
        //   418: astore #9
        //   420: aload #12
        //   422: invokespecial <init> : ()V
        //   425: aload_2
        //   426: astore #6
        //   428: aload_3
        //   429: astore #9
        //   431: aload #4
        //   433: astore #9
        //   435: aload #5
        //   437: astore #9
        //   439: aload #7
        //   441: astore #9
        //   443: new org/json/JSONObject
        //   446: invokespecial <init> : ()V
        //   449: aload_2
        //   450: astore #6
        //   452: aload_3
        //   453: astore #9
        //   455: aload #4
        //   457: astore #9
        //   459: aload #5
        //   461: astore #9
        //   463: aload #7
        //   465: astore #9
        //   467: new org/json/JSONArray
        //   470: invokespecial <init> : ()V
        //   473: aload_2
        //   474: astore #6
        //   476: aload_3
        //   477: astore #9
        //   479: aload #4
        //   481: astore #9
        //   483: aload #5
        //   485: astore #9
        //   487: aload #7
        //   489: astore #9
        //   491: aload_0
        //   492: getfield GameType : I
        //   495: istore #13
        //   497: iload #13
        //   499: ifeq -> 541
        //   502: aload_0
        //   503: getfield GameType : I
        //   506: bipush #21
        //   508: if_icmpeq -> 541
        //   511: aload_0
        //   512: getfield GameType : I
        //   515: bipush #22
        //   517: if_icmpeq -> 541
        //   520: aload_0
        //   521: getfield GameType : I
        //   524: istore #13
        //   526: iload #13
        //   528: bipush #23
        //   530: if_icmpne -> 577
        //   533: goto -> 541
        //   536: astore #5
        //   538: goto -> 1628
        //   541: aload_2
        //   542: astore #6
        //   544: aload_3
        //   545: astore #9
        //   547: aload #4
        //   549: astore #9
        //   551: aload #5
        //   553: astore #9
        //   555: aload #7
        //   557: astore #9
        //   559: aload_0
        //   560: getfield Price : I
        //   563: ldc_w 80000
        //   566: if_icmpge -> 577
        //   569: ldc_w 70000
        //   572: istore #13
        //   574: goto -> 795
        //   577: aload_2
        //   578: astore #6
        //   580: aload_3
        //   581: astore #9
        //   583: aload #4
        //   585: astore #9
        //   587: aload #5
        //   589: astore #9
        //   591: aload #7
        //   593: astore #9
        //   595: aload_0
        //   596: getfield GameType : I
        //   599: istore #13
        //   601: iload #13
        //   603: ifeq -> 637
        //   606: aload_0
        //   607: getfield GameType : I
        //   610: bipush #21
        //   612: if_icmpeq -> 637
        //   615: aload_0
        //   616: getfield GameType : I
        //   619: bipush #22
        //   621: if_icmpeq -> 637
        //   624: aload_0
        //   625: getfield GameType : I
        //   628: istore #13
        //   630: iload #13
        //   632: bipush #23
        //   634: if_icmpne -> 673
        //   637: aload_2
        //   638: astore #6
        //   640: aload_3
        //   641: astore #9
        //   643: aload #4
        //   645: astore #9
        //   647: aload #5
        //   649: astore #9
        //   651: aload #7
        //   653: astore #9
        //   655: aload_0
        //   656: getfield Price : I
        //   659: ldc_w 80000
        //   662: if_icmple -> 673
        //   665: ldc_w 80000
        //   668: istore #13
        //   670: goto -> 795
        //   673: aload_2
        //   674: astore #6
        //   676: aload_3
        //   677: astore #9
        //   679: aload #4
        //   681: astore #9
        //   683: aload #5
        //   685: astore #9
        //   687: aload #7
        //   689: astore #9
        //   691: aload_0
        //   692: getfield GameType : I
        //   695: istore #13
        //   697: iload #13
        //   699: iconst_1
        //   700: if_icmpeq -> 790
        //   703: aload_0
        //   704: getfield GameType : I
        //   707: bipush #20
        //   709: if_icmpne -> 715
        //   712: goto -> 790
        //   715: aload_0
        //   716: getfield GameType : I
        //   719: iconst_2
        //   720: if_icmpne -> 731
        //   723: sipush #10000
        //   726: istore #13
        //   728: goto -> 795
        //   731: aload_0
        //   732: getfield GameType : I
        //   735: iconst_3
        //   736: if_icmpne -> 747
        //   739: ldc_w 40000
        //   742: istore #13
        //   744: goto -> 795
        //   747: aload_0
        //   748: getfield GameType : I
        //   751: iconst_4
        //   752: if_icmpne -> 763
        //   755: ldc_w 100000
        //   758: istore #13
        //   760: goto -> 795
        //   763: aload_0
        //   764: getfield GameType : I
        //   767: istore #13
        //   769: iload #13
        //   771: bipush #6
        //   773: if_icmpne -> 784
        //   776: ldc_w 80000
        //   779: istore #13
        //   781: goto -> 795
        //   784: iconst_0
        //   785: istore #13
        //   787: goto -> 795
        //   790: ldc_w 80000
        //   793: istore #13
        //   795: aload_2
        //   796: astore #6
        //   798: aload_3
        //   799: astore #9
        //   801: aload #4
        //   803: astore #9
        //   805: aload #5
        //   807: astore #9
        //   809: aload #7
        //   811: astore #9
        //   813: aload_0
        //   814: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   817: invokevirtual keys : ()Ljava/util/Iterator;
        //   820: astore #14
        //   822: aload_2
        //   823: astore #6
        //   825: aload_3
        //   826: astore #9
        //   828: aload #4
        //   830: astore #9
        //   832: aload #5
        //   834: astore #9
        //   836: aload #7
        //   838: astore #9
        //   840: aload #14
        //   842: invokeinterface hasNext : ()Z
        //   847: istore #15
        //   849: iload #15
        //   851: ifeq -> 1576
        //   854: aload #14
        //   856: invokeinterface next : ()Ljava/lang/Object;
        //   861: checkcast java/lang/String
        //   864: astore #16
        //   866: aload_0
        //   867: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   870: astore #6
        //   872: aload #6
        //   874: aload #16
        //   876: invokevirtual getDouble : (Ljava/lang/String;)D
        //   879: dconst_0
        //   880: dcmpl
        //   881: ifle -> 1547
        //   884: new org/json/JSONObject
        //   887: astore #17
        //   889: aload #17
        //   891: invokespecial <init> : ()V
        //   894: new org/json/JSONArray
        //   897: astore #18
        //   899: aload #18
        //   901: invokespecial <init> : ()V
        //   904: aload #16
        //   906: aload_1
        //   907: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   910: astore #9
        //   912: aload #9
        //   914: arraylength
        //   915: istore #19
        //   917: ldc ''
        //   919: astore #6
        //   921: iconst_0
        //   922: istore #20
        //   924: iload #20
        //   926: iload #19
        //   928: if_icmpge -> 1008
        //   931: aload #9
        //   933: iload #20
        //   935: aaload
        //   936: astore #21
        //   938: aload #21
        //   940: invokevirtual length : ()I
        //   943: ifle -> 997
        //   946: aload #18
        //   948: aload #21
        //   950: invokevirtual put : (Ljava/lang/Object;)Lorg/json/JSONArray;
        //   953: pop
        //   954: new java/lang/StringBuilder
        //   957: astore #22
        //   959: aload #22
        //   961: invokespecial <init> : ()V
        //   964: aload #22
        //   966: aload #6
        //   968: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   971: pop
        //   972: aload #22
        //   974: aload #21
        //   976: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   979: pop
        //   980: aload #22
        //   982: aload_1
        //   983: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   986: pop
        //   987: aload #22
        //   989: invokevirtual toString : ()Ljava/lang/String;
        //   992: astore #6
        //   994: goto -> 997
        //   997: iinc #20, 1
        //   1000: goto -> 924
        //   1003: astore #5
        //   1005: goto -> 1557
        //   1008: aload #6
        //   1010: aload_1
        //   1011: invokevirtual endsWith : (Ljava/lang/String;)Z
        //   1014: istore #15
        //   1016: iload #15
        //   1018: ifeq -> 1039
        //   1021: aload #6
        //   1023: iconst_0
        //   1024: aload #6
        //   1026: invokevirtual length : ()I
        //   1029: iconst_1
        //   1030: isub
        //   1031: invokevirtual substring : (II)Ljava/lang/String;
        //   1034: astore #6
        //   1036: goto -> 1039
        //   1039: new org/json/JSONObject
        //   1042: astore #22
        //   1044: aload #22
        //   1046: invokespecial <init> : ()V
        //   1049: aload #22
        //   1051: ldc_w 'ngay_nhan'
        //   1054: aload #5
        //   1056: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1059: pop
        //   1060: aload #22
        //   1062: ldc_w 'type_kh'
        //   1065: iconst_2
        //   1066: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1069: pop
        //   1070: aload #22
        //   1072: ldc_w 'ten_kh'
        //   1075: aload_0
        //   1076: getfield mwebsite : Ljava/util/List;
        //   1079: aload_0
        //   1080: getfield spin_pointion : I
        //   1083: invokeinterface get : (I)Ljava/lang/Object;
        //   1088: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1091: pop
        //   1092: aload #22
        //   1094: ldc_w 'so_dienthoai'
        //   1097: aload_0
        //   1098: getfield mwebsite : Ljava/util/List;
        //   1101: aload_0
        //   1102: getfield spin_pointion : I
        //   1105: invokeinterface get : (I)Ljava/lang/Object;
        //   1110: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1113: pop
        //   1114: aload #22
        //   1116: ldc_w 'so_tin_nhan'
        //   1119: iload #8
        //   1121: iconst_1
        //   1122: iadd
        //   1123: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1126: pop
        //   1127: aload_0
        //   1128: getfield the_loai : Ljava/lang/String;
        //   1131: ldc_w 'xi'
        //   1134: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1137: iconst_m1
        //   1138: if_icmple -> 1149
        //   1141: ldc_w 'xi'
        //   1144: astore #9
        //   1146: goto -> 1155
        //   1149: aload_0
        //   1150: getfield the_loai : Ljava/lang/String;
        //   1153: astore #9
        //   1155: aload #22
        //   1157: ldc_w 'the_loai'
        //   1160: aload #9
        //   1162: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1165: pop
        //   1166: aload #22
        //   1168: ldc_w 'so_chon'
        //   1171: aload #6
        //   1173: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1176: pop
        //   1177: aload_0
        //   1178: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   1181: astore #9
        //   1183: aload #22
        //   1185: ldc_w 'diem'
        //   1188: aload #9
        //   1190: aload #16
        //   1192: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1195: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   1198: pop
        //   1199: aload #22
        //   1201: ldc_w 'diem_quydoi'
        //   1204: aload_0
        //   1205: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   1208: aload #16
        //   1210: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1213: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   1216: pop
        //   1217: aload #22
        //   1219: ldc_w 'diem_khachgiu'
        //   1222: iconst_0
        //   1223: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1226: pop
        //   1227: aload #22
        //   1229: ldc_w 'diem_dly_giu'
        //   1232: iconst_0
        //   1233: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1236: pop
        //   1237: aload #22
        //   1239: ldc_w 'diem_ton'
        //   1242: aload_0
        //   1243: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   1246: aload #16
        //   1248: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1251: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   1254: pop
        //   1255: aload_0
        //   1256: getfield radio_xi : Landroid/widget/RadioButton;
        //   1259: invokevirtual isChecked : ()Z
        //   1262: istore #15
        //   1264: iload #15
        //   1266: ifne -> 1320
        //   1269: aload_0
        //   1270: getfield jsonGia : Lorg/json/JSONObject;
        //   1273: aload #16
        //   1275: invokevirtual has : (Ljava/lang/String;)Z
        //   1278: ifeq -> 1300
        //   1281: aload_0
        //   1282: getfield Price : I
        //   1285: aload_0
        //   1286: getfield jsonGia : Lorg/json/JSONObject;
        //   1289: aload #16
        //   1291: invokevirtual getInt : (Ljava/lang/String;)I
        //   1294: iadd
        //   1295: istore #19
        //   1297: goto -> 1306
        //   1300: aload_0
        //   1301: getfield Price : I
        //   1304: istore #19
        //   1306: aload #22
        //   1308: ldc_w 'gia'
        //   1311: iload #19
        //   1313: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1316: pop
        //   1317: goto -> 1366
        //   1320: aload_0
        //   1321: getfield jsonTienxien : Lorg/json/JSONObject;
        //   1324: aload #6
        //   1326: invokevirtual has : (Ljava/lang/String;)Z
        //   1329: ifeq -> 1353
        //   1332: aload #22
        //   1334: ldc_w 'gia'
        //   1337: aload_0
        //   1338: getfield jsonTienxien : Lorg/json/JSONObject;
        //   1341: aload #6
        //   1343: invokevirtual getInt : (Ljava/lang/String;)I
        //   1346: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1349: pop
        //   1350: goto -> 1366
        //   1353: aload #22
        //   1355: ldc_w 'gia'
        //   1358: aload_0
        //   1359: getfield Price : I
        //   1362: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1365: pop
        //   1366: aload #22
        //   1368: ldc_w 'lan_an'
        //   1371: iload #13
        //   1373: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1376: pop
        //   1377: aload #22
        //   1379: ldc_w 'so_nhay'
        //   1382: iconst_0
        //   1383: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1386: pop
        //   1387: aload_0
        //   1388: getfield jsonGia : Lorg/json/JSONObject;
        //   1391: aload #16
        //   1393: invokevirtual has : (Ljava/lang/String;)Z
        //   1396: ifeq -> 1418
        //   1399: aload_0
        //   1400: getfield Price : I
        //   1403: aload_0
        //   1404: getfield jsonGia : Lorg/json/JSONObject;
        //   1407: aload #16
        //   1409: invokevirtual getInt : (Ljava/lang/String;)I
        //   1412: iadd
        //   1413: istore #19
        //   1415: goto -> 1424
        //   1418: aload_0
        //   1419: getfield Price : I
        //   1422: istore #19
        //   1424: iload #19
        //   1426: i2d
        //   1427: dstore #23
        //   1429: aload_0
        //   1430: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   1433: aload #16
        //   1435: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1438: dstore #25
        //   1440: dload #23
        //   1442: invokestatic isNaN : (D)Z
        //   1445: pop
        //   1446: aload #22
        //   1448: ldc_w 'tong_tien'
        //   1451: dload #23
        //   1453: dload #25
        //   1455: dmul
        //   1456: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   1459: pop
        //   1460: aload #22
        //   1462: ldc_w 'ket_qua'
        //   1465: iconst_0
        //   1466: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1469: pop
        //   1470: aload_0
        //   1471: getfield jsonArray : Lorg/json/JSONArray;
        //   1474: aload #22
        //   1476: invokevirtual put : (Ljava/lang/Object;)Lorg/json/JSONArray;
        //   1479: pop
        //   1480: aload #17
        //   1482: ldc_w 'Numbers'
        //   1485: aload #18
        //   1487: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1490: pop
        //   1491: aload #17
        //   1493: ldc_w 'Point'
        //   1496: aload_0
        //   1497: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   1500: aload #16
        //   1502: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1505: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   1508: pop
        //   1509: aload #17
        //   1511: ldc_w 'Price'
        //   1514: sipush #705
        //   1517: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1520: pop
        //   1521: aload #12
        //   1523: aload #17
        //   1525: invokevirtual put : (Ljava/lang/Object;)Lorg/json/JSONArray;
        //   1528: pop
        //   1529: goto -> 1547
        //   1532: astore #5
        //   1534: goto -> 1557
        //   1537: astore #5
        //   1539: goto -> 1557
        //   1542: astore #5
        //   1544: goto -> 1557
        //   1547: goto -> 822
        //   1550: astore #5
        //   1552: goto -> 1557
        //   1555: astore #5
        //   1557: aload #5
        //   1559: invokevirtual printStackTrace : ()V
        //   1562: ldc_w 'Kitra lsli
        //   1565: areturn
        //   1566: astore #5
        //   1568: goto -> 1628
        //   1571: astore #5
        //   1573: goto -> 1628
        //   1576: aload #11
        //   1578: ldc_w 'Items'
        //   1581: aload #12
        //   1583: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1586: pop
        //   1587: aload #10
        //   1589: aload #11
        //   1591: invokevirtual put : (Ljava/lang/Object;)Lorg/json/JSONArray;
        //   1594: pop
        //   1595: aload_2
        //   1596: ldc_w 'Tickets'
        //   1599: aload #10
        //   1601: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1604: pop
        //   1605: goto -> 1633
        //   1608: astore #5
        //   1610: goto -> 1628
        //   1613: astore #5
        //   1615: goto -> 1628
        //   1618: astore #5
        //   1620: aload #6
        //   1622: astore_2
        //   1623: goto -> 1628
        //   1626: astore #5
        //   1628: aload #5
        //   1630: invokevirtual printStackTrace : ()V
        //   1633: aload_2
        //   1634: invokevirtual toString : ()Ljava/lang/String;
        //   1637: areturn
        // Exception table:
        //   from	to	target	type
        //   160	170	1626	org/json/JSONException
        //   191	201	1618	org/json/JSONException
        //   219	228	1618	org/json/JSONException
        //   246	251	1618	org/json/JSONException
        //   269	274	1618	org/json/JSONException
        //   292	297	1618	org/json/JSONException
        //   315	320	1618	org/json/JSONException
        //   338	348	1618	org/json/JSONException
        //   366	379	1618	org/json/JSONException
        //   397	402	1618	org/json/JSONException
        //   420	425	1618	org/json/JSONException
        //   443	449	1618	org/json/JSONException
        //   467	473	1618	org/json/JSONException
        //   491	497	1618	org/json/JSONException
        //   502	526	536	org/json/JSONException
        //   559	569	1618	org/json/JSONException
        //   595	601	1618	org/json/JSONException
        //   606	630	536	org/json/JSONException
        //   655	665	1618	org/json/JSONException
        //   691	697	1618	org/json/JSONException
        //   703	712	536	org/json/JSONException
        //   715	723	536	org/json/JSONException
        //   731	739	536	org/json/JSONException
        //   747	755	536	org/json/JSONException
        //   763	769	536	org/json/JSONException
        //   813	822	1618	org/json/JSONException
        //   840	849	1618	org/json/JSONException
        //   854	866	1571	org/json/JSONException
        //   866	872	1555	org/json/JSONException
        //   872	912	1550	org/json/JSONException
        //   912	917	1542	org/json/JSONException
        //   938	994	1003	org/json/JSONException
        //   1008	1016	1537	org/json/JSONException
        //   1021	1036	1003	org/json/JSONException
        //   1039	1141	1537	org/json/JSONException
        //   1149	1155	1537	org/json/JSONException
        //   1155	1183	1537	org/json/JSONException
        //   1183	1264	1532	org/json/JSONException
        //   1269	1297	1532	org/json/JSONException
        //   1300	1306	1532	org/json/JSONException
        //   1306	1317	1532	org/json/JSONException
        //   1320	1350	1532	org/json/JSONException
        //   1353	1366	1532	org/json/JSONException
        //   1366	1415	1532	org/json/JSONException
        //   1418	1424	1532	org/json/JSONException
        //   1429	1440	1532	org/json/JSONException
        //   1446	1529	1532	org/json/JSONException
        //   1557	1562	1566	org/json/JSONException
        //   1576	1595	1613	org/json/JSONException
        //   1595	1605	1608	org/json/JSONException
    }

    private String Laygia() {
        this.jsonGia = new JSONObject();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        String str = simpleDateFormat.format(calendar.getTime());
        String[] arrayOfString = new String[1];
        arrayOfString[0] = "";
        OkHttpClient okHttpClient = new OkHttpClient();
        if (MainActivity.MyToken.length() > 0 && Build.VERSION.SDK_INT >= 24)
            CompletableFuture.runAsync(new _$$Lambda$Frag_Chaytrang$hzE2FSVfsxb0QfhTAHA5cgHOUQM(this, str, okHttpClient, arrayOfString));
        return arrayOfString[0];
    }

    private void init() {
        this.radio_de = (RadioButton)this.v.findViewById(2131231172);
        this.radio_lo = (RadioButton)this.v.findViewById(2131231178);
        this.radio_xi = (RadioButton)this.v.findViewById(2131231180);
        this.radio_dea = (RadioButton)this.v.findViewById(2131231173);
        this.radio_deb = (RadioButton)this.v.findViewById(2131231174);
        this.radio_dec = (RadioButton)this.v.findViewById(2131231175);
        this.radio_ded = (RadioButton)this.v.findViewById(2131231176);
        this.radio_xi2 = (RadioButton)this.v.findViewById(2131231181);
        this.radio_xi3 = (RadioButton)this.v.findViewById(2131231182);
        this.radio_xi4 = (RadioButton)this.v.findViewById(2131231183);
        this.spr_trang = (Spinner)this.v.findViewById(2131231266);
        this.btn_Xuatso = (Button)this.v.findViewById(2131230813);
        this.lview = (ListView)this.v.findViewById(2131231103);
        this.li_loaide = (LinearLayout)this.v.findViewById(2131231027);
        this.li_loaixi = (LinearLayout)this.v.findViewById(2131231028);
        this.spr_trang = (Spinner)this.v.findViewById(2131231266);
        this.btn_MaXuat = (Button)this.v.findViewById(2131230806);
        this.edt_tien = (EditText)this.v.findViewById(2131230962);
    }

    private void login(String paramString1, String paramString2) {
        OkHttpClient okHttpClient = new OkHttpClient();
        JSONObject jSONObject = new JSONObject();
        AtomicReference<String> atomicReference = new AtomicReference<String>("");
        if (Build.VERSION.SDK_INT >= 24)
            CompletableFuture.runAsync(new _$$Lambda$Frag_Chaytrang$l_QEsOD1ZvBMJIMuUzyYsFMIunc(this, jSONObject, paramString1, paramString2, atomicReference, okHttpClient));
    }

    private static Date parseDate(String paramString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.US);
        try {
            return simpleDateFormat.parse(paramString);
        } catch (ParseException parseException) {
            return new Date(0L);
        }
    }

    public void Dialog() {
        final Dialog dialog = new Dialog((Context)getActivity());
        dialog.setContentView(2131427401);
        dialog.getWindow().setLayout(-1, -2);
        final EditText edt_XuatDan = (EditText)dialog.findViewById(2131230931);
        TextView textView1 = (TextView)dialog.findViewById(2131231299);
        TextView textView2 = (TextView)dialog.findViewById(2131230724);
        TextView textView3 = (TextView)dialog.findViewById(2131230721);
        final TextView edt_XuatErr = (TextView)dialog.findViewById(2131230932);
        textView4.setVisibility(8);
        final Button btn_chuyendi = (Button)dialog.findViewById(2131230816);
        OkHttpClient okHttpClient = new OkHttpClient();
        if (MainActivity.MyToken.length() > 0 && Build.VERSION.SDK_INT >= 24)
            CompletableFuture.runAsync(new _$$Lambda$Frag_Chaytrang$DGn7QqO0Xk9idVJ8MyHUxv0XV3o(this, okHttpClient, textView1, textView2, textView3));
        editText.setText("");
        editText.setText(this.xuatDan.replaceAll(",x", "x"));
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                String str;
                AtomicReference atomicReference;
                btn_chuyendi.setEnabled(false);
                SQLiteDatabase sQLiteDatabase = Frag_Chaytrang.this.db.getWritableDatabase();
                DatabaseUtils.InsertHelper insertHelper = new DatabaseUtils.InsertHelper(sQLiteDatabase, "tbl_soctS");
                param1View = null;
                try {
                    String str1 = Frag_Chaytrang.this.KiemTraTruocKhiChayTrang(edt_XuatDan.getText().toString().replaceAll("'", " ").trim());
                    str = str1;
                } catch (JSONException jSONException) {
                    jSONException.printStackTrace();
                } catch (Exception exception) {}
                if (exception == "")
                    str = Frag_Chaytrang.this.Laygia();
                if (str == "") {
                    Frag_Chaytrang frag_Chaytrang = Frag_Chaytrang.this;
                    JSONArray jSONArray = new JSONArray();
                    this();
                    frag_Chaytrang.jsonArray = jSONArray;
                    String str1 = Frag_Chaytrang.this.CreateJson();
                    OkHttpClient okHttpClient = new OkHttpClient();
                    this();
                    atomicReference = new AtomicReference();
                    this((V)"");
                    if (Build.VERSION.SDK_INT >= 24) {
                        EditText editText = edt_XuatDan;
                        Dialog dialog = dialog;
                        TextView textView = edt_XuatErr;
                        Button button = btn_chuyendi;
                        _$$Lambda$Frag_Chaytrang$18$Aa1GizXhuIa4XrlZ0t5mv9Cb18Q _$$Lambda$Frag_Chaytrang$18$Aa1GizXhuIa4XrlZ0t5mv9Cb18Q = new _$$Lambda$Frag_Chaytrang$18$Aa1GizXhuIa4XrlZ0t5mv9Cb18Q();
                        this(this, atomicReference, okHttpClient, str1, sQLiteDatabase, insertHelper, editText, dialog, textView, button);
                        CompletableFuture.runAsync(_$$Lambda$Frag_Chaytrang$18$Aa1GizXhuIa4XrlZ0t5mv9Cb18Q);
                    }
                } else {
                    edt_XuatErr.setText((CharSequence)atomicReference);
                    edt_XuatErr.setVisibility(0);
                    btn_chuyendi.setEnabled(true);
                }
            }
        });
        dialog.setCancelable(true);
        dialog.setTitle("Xem d);
                dialog.show();
    }

    public void Dialog2() {
        Dialog dialog = new Dialog((Context)getActivity());
        dialog.setContentView(2131427402);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        dialog.getWindow().setLayout(-1, -2);
        ListView listView = (ListView)dialog.findViewById(2131231093);
        this.SoTin.clear();
        this.TheLoai.clear();
        this.NoiDung.clear();
        this.ThoiGian.clear();
        this.TienCuoc.clear();
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            this();
            if (Build.VERSION.SDK_INT >= 24) {
                _$$Lambda$Frag_Chaytrang$XywKntZqBQRIghYKYLeRhEnIabQ _$$Lambda$Frag_Chaytrang$XywKntZqBQRIghYKYLeRhEnIabQ = new _$$Lambda$Frag_Chaytrang$XywKntZqBQRIghYKYLeRhEnIabQ();
                this(this, okHttpClient, decimalFormat, listView);
                CompletableFuture.runAsync(_$$Lambda$Frag_Chaytrang$XywKntZqBQRIghYKYLeRhEnIabQ);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        dialog.getWindow().setLayout(-1, -2);
        dialog.setCancelable(true);
        dialog.setTitle("Xem d);
                dialog.show();
    }

    public String KiemTraTruocKhiChayTrang(String paramString) throws JSONException {
        // Byte code:
        //   0: aload_0
        //   1: astore_2
        //   2: aload_2
        //   3: new org/json/JSONObject
        //   6: dup
        //   7: invokespecial <init> : ()V
        //   10: putfield jsonChayTrang : Lorg/json/JSONObject;
        //   13: new org/json/JSONObject
        //   16: dup
        //   17: invokespecial <init> : ()V
        //   20: astore_3
        //   21: new tamhoang/ldpro4/MainActivity
        //   24: dup
        //   25: invokespecial <init> : ()V
        //   28: pop
        //   29: invokestatic Get_date : ()Ljava/lang/String;
        //   32: astore #4
        //   34: aload_2
        //   35: getfield the_loai : Ljava/lang/String;
        //   38: ldc 'deb'
        //   40: invokevirtual indexOf : (Ljava/lang/String;)I
        //   43: iconst_m1
        //   44: if_icmple -> 123
        //   47: new java/lang/StringBuilder
        //   50: dup
        //   51: invokespecial <init> : ()V
        //   54: astore #5
        //   56: aload #5
        //   58: ldc_w 'Select so_chon, Sum(diem) FROM tbl_soctS Where ten_kh = ''
        //   61: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   64: pop
        //   65: aload #5
        //   67: aload_2
        //   68: getfield mwebsite : Ljava/util/List;
        //   71: aload_2
        //   72: getfield spin_pointion : I
        //   75: invokeinterface get : (I)Ljava/lang/Object;
        //   80: checkcast java/lang/String
        //   83: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   86: pop
        //   87: aload #5
        //   89: ldc_w '' AND type_kh = 2 AND (the_loai = 'deb' or the_loai = 'det') AND ngay_nhan = ''
        //   92: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   95: pop
        //   96: aload #5
        //   98: aload #4
        //   100: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   103: pop
        //   104: aload #5
        //   106: ldc_w '' Group by so_chon'
        //   109: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   112: pop
        //   113: aload #5
        //   115: invokevirtual toString : ()Ljava/lang/String;
        //   118: astore #6
        //   120: goto -> 485
        //   123: aload_2
        //   124: getfield the_loai : Ljava/lang/String;
        //   127: ldc_w 'xi2'
        //   130: invokevirtual indexOf : (Ljava/lang/String;)I
        //   133: iconst_m1
        //   134: if_icmple -> 213
        //   137: new java/lang/StringBuilder
        //   140: dup
        //   141: invokespecial <init> : ()V
        //   144: astore #5
        //   146: aload #5
        //   148: ldc_w 'Select so_chon, Sum(diem) FROM tbl_soctS Where ten_kh = ''
        //   151: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   154: pop
        //   155: aload #5
        //   157: aload_2
        //   158: getfield mwebsite : Ljava/util/List;
        //   161: aload_2
        //   162: getfield spin_pointion : I
        //   165: invokeinterface get : (I)Ljava/lang/Object;
        //   170: checkcast java/lang/String
        //   173: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   176: pop
        //   177: aload #5
        //   179: ldc_w '' AND type_kh = 2 AND the_loai = 'xi' AND length(so_chon) = 5  AND ngay_nhan = ''
        //   182: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   185: pop
        //   186: aload #5
        //   188: aload #4
        //   190: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   193: pop
        //   194: aload #5
        //   196: ldc_w '' Group by so_chon'
        //   199: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   202: pop
        //   203: aload #5
        //   205: invokevirtual toString : ()Ljava/lang/String;
        //   208: astore #6
        //   210: goto -> 485
        //   213: aload_2
        //   214: getfield the_loai : Ljava/lang/String;
        //   217: ldc_w 'xi3'
        //   220: invokevirtual indexOf : (Ljava/lang/String;)I
        //   223: iconst_m1
        //   224: if_icmple -> 303
        //   227: new java/lang/StringBuilder
        //   230: dup
        //   231: invokespecial <init> : ()V
        //   234: astore #5
        //   236: aload #5
        //   238: ldc_w 'Select so_chon, Sum(diem) FROM tbl_soctS Where ten_kh = ''
        //   241: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   244: pop
        //   245: aload #5
        //   247: aload_2
        //   248: getfield mwebsite : Ljava/util/List;
        //   251: aload_2
        //   252: getfield spin_pointion : I
        //   255: invokeinterface get : (I)Ljava/lang/Object;
        //   260: checkcast java/lang/String
        //   263: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   266: pop
        //   267: aload #5
        //   269: ldc_w '' AND type_kh = 2 AND the_loai = 'xi' AND length(so_chon) = 8  AND ngay_nhan = ''
        //   272: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   275: pop
        //   276: aload #5
        //   278: aload #4
        //   280: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   283: pop
        //   284: aload #5
        //   286: ldc_w '' Group by so_chon'
        //   289: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   292: pop
        //   293: aload #5
        //   295: invokevirtual toString : ()Ljava/lang/String;
        //   298: astore #6
        //   300: goto -> 485
        //   303: aload_2
        //   304: getfield the_loai : Ljava/lang/String;
        //   307: ldc_w 'xi4'
        //   310: invokevirtual indexOf : (Ljava/lang/String;)I
        //   313: iconst_m1
        //   314: if_icmple -> 393
        //   317: new java/lang/StringBuilder
        //   320: dup
        //   321: invokespecial <init> : ()V
        //   324: astore #5
        //   326: aload #5
        //   328: ldc_w 'Select so_chon, Sum(diem) FROM tbl_soctS Where ten_kh = ''
        //   331: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   334: pop
        //   335: aload #5
        //   337: aload_2
        //   338: getfield mwebsite : Ljava/util/List;
        //   341: aload_2
        //   342: getfield spin_pointion : I
        //   345: invokeinterface get : (I)Ljava/lang/Object;
        //   350: checkcast java/lang/String
        //   353: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   356: pop
        //   357: aload #5
        //   359: ldc_w '' AND type_kh = 2 AND the_loai = 'xi' AND length(so_chon) = 11  AND ngay_nhan = ''
        //   362: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   365: pop
        //   366: aload #5
        //   368: aload #4
        //   370: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   373: pop
        //   374: aload #5
        //   376: ldc_w '' Group by so_chon'
        //   379: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   382: pop
        //   383: aload #5
        //   385: invokevirtual toString : ()Ljava/lang/String;
        //   388: astore #6
        //   390: goto -> 485
        //   393: new java/lang/StringBuilder
        //   396: dup
        //   397: invokespecial <init> : ()V
        //   400: astore #5
        //   402: aload #5
        //   404: ldc_w 'Select so_chon, Sum(diem) FROM tbl_soctS Where ten_kh = ''
        //   407: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   410: pop
        //   411: aload #5
        //   413: aload_2
        //   414: getfield mwebsite : Ljava/util/List;
        //   417: aload_2
        //   418: getfield spin_pointion : I
        //   421: invokeinterface get : (I)Ljava/lang/Object;
        //   426: checkcast java/lang/String
        //   429: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   432: pop
        //   433: aload #5
        //   435: ldc_w '' AND type_kh = 2 AND the_loai = ''
        //   438: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   441: pop
        //   442: aload #5
        //   444: aload_2
        //   445: getfield the_loai : Ljava/lang/String;
        //   448: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   451: pop
        //   452: aload #5
        //   454: ldc_w '' AND ngay_nhan = ''
        //   457: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   460: pop
        //   461: aload #5
        //   463: aload #4
        //   465: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   468: pop
        //   469: aload #5
        //   471: ldc_w '' Group by so_chon'
        //   474: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   477: pop
        //   478: aload #5
        //   480: invokevirtual toString : ()Ljava/lang/String;
        //   483: astore #6
        //   485: aload_2
        //   486: getfield db : Ltamhoang/ldpro4/data/Database;
        //   489: aload #6
        //   491: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   494: astore #5
        //   496: aload #5
        //   498: invokeinterface moveToNext : ()Z
        //   503: istore #7
        //   505: iload #7
        //   507: ifeq -> 542
        //   510: aload_3
        //   511: aload #5
        //   513: iconst_0
        //   514: invokeinterface getString : (I)Ljava/lang/String;
        //   519: aload #5
        //   521: iconst_1
        //   522: invokeinterface getInt : (I)I
        //   527: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   530: pop
        //   531: goto -> 496
        //   534: astore_1
        //   535: goto -> 1877
        //   538: astore_1
        //   539: goto -> 1834
        //   542: aload_2
        //   543: getfield the_loai : Ljava/lang/String;
        //   546: ldc_w 'xi'
        //   549: invokevirtual contains : (Ljava/lang/CharSequence;)Z
        //   552: istore #7
        //   554: ldc_w 'k'
        //   557: astore #8
        //   559: ldc_w 'd'
        //   562: astore #9
        //   564: ldc_w ','
        //   567: astore_2
        //   568: iconst_0
        //   569: istore #10
        //   571: iload #7
        //   573: ifne -> 1128
        //   576: aload_1
        //   577: aload_1
        //   578: ldc_w ':'
        //   581: invokevirtual indexOf : (Ljava/lang/String;)I
        //   584: invokevirtual substring : (I)Ljava/lang/String;
        //   587: ldc_w ' '
        //   590: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   593: astore #11
        //   595: iconst_0
        //   596: istore #12
        //   598: aload #5
        //   600: astore_1
        //   601: aload_3
        //   602: astore #5
        //   604: aload #11
        //   606: astore_3
        //   607: iload #12
        //   609: aload_3
        //   610: arraylength
        //   611: if_icmpge -> 1082
        //   614: aload_3
        //   615: iload #12
        //   617: aaload
        //   618: astore #11
        //   620: aload #11
        //   622: aload_3
        //   623: iload #12
        //   625: aaload
        //   626: ldc_w ':'
        //   629: invokevirtual indexOf : (Ljava/lang/String;)I
        //   632: iconst_1
        //   633: iadd
        //   634: invokevirtual substring : (I)Ljava/lang/String;
        //   637: astore #11
        //   639: aload #11
        //   641: ldc_w 'x'
        //   644: invokevirtual indexOf : (Ljava/lang/String;)I
        //   647: istore #13
        //   649: aload #11
        //   651: iconst_0
        //   652: iload #13
        //   654: invokevirtual substring : (II)Ljava/lang/String;
        //   657: astore #11
        //   659: aload_3
        //   660: iload #12
        //   662: aaload
        //   663: aload_3
        //   664: iload #12
        //   666: aaload
        //   667: ldc_w 'x'
        //   670: invokevirtual indexOf : (Ljava/lang/String;)I
        //   673: invokevirtual substring : (I)Ljava/lang/String;
        //   676: ldc_w 'x'
        //   679: ldc ''
        //   681: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   684: ldc_w 'n'
        //   687: ldc ''
        //   689: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   692: aload #9
        //   694: ldc ''
        //   696: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   699: aload #8
        //   701: ldc ''
        //   703: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   706: astore #14
        //   708: aload #11
        //   710: aload_2
        //   711: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   714: astore #11
        //   716: aload #11
        //   718: arraylength
        //   719: istore #13
        //   721: iconst_0
        //   722: istore #15
        //   724: aload_0
        //   725: astore #16
        //   727: iload #15
        //   729: iload #13
        //   731: if_icmpge -> 1016
        //   734: aload #11
        //   736: iload #15
        //   738: aaload
        //   739: astore #17
        //   741: aload #16
        //   743: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   746: aload #17
        //   748: invokevirtual has : (Ljava/lang/String;)Z
        //   751: istore #7
        //   753: iload #7
        //   755: ifeq -> 828
        //   758: aload #16
        //   760: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   763: astore #18
        //   765: aload #18
        //   767: aload #17
        //   769: aload #16
        //   771: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   774: aload #17
        //   776: invokevirtual getInt : (Ljava/lang/String;)I
        //   779: aload #14
        //   781: invokestatic parseInt : (Ljava/lang/String;)I
        //   784: iadd
        //   785: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   788: pop
        //   789: goto -> 841
        //   792: aload_1
        //   793: astore #5
        //   795: astore_1
        //   796: goto -> 1877
        //   799: aload_1
        //   800: astore #5
        //   802: astore_1
        //   803: goto -> 1834
        //   806: astore #6
        //   808: aload_1
        //   809: astore #5
        //   811: aload #6
        //   813: astore_1
        //   814: goto -> 1877
        //   817: astore #6
        //   819: aload_1
        //   820: astore #5
        //   822: aload #6
        //   824: astore_1
        //   825: goto -> 1834
        //   828: aload #16
        //   830: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   833: aload #17
        //   835: aload #14
        //   837: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   840: pop
        //   841: aload #16
        //   843: getfield jsonGia : Lorg/json/JSONObject;
        //   846: aload #17
        //   848: invokevirtual has : (Ljava/lang/String;)Z
        //   851: istore #7
        //   853: iload #7
        //   855: ifeq -> 923
        //   858: iload #10
        //   860: i2d
        //   861: dstore #19
        //   863: aload #14
        //   865: invokestatic parseDouble : (Ljava/lang/String;)D
        //   868: dstore #21
        //   870: aload #16
        //   872: getfield jsonGia : Lorg/json/JSONObject;
        //   875: aload #17
        //   877: invokevirtual getInt : (Ljava/lang/String;)I
        //   880: istore #23
        //   882: aload #16
        //   884: getfield Price : I
        //   887: istore #10
        //   889: iload #23
        //   891: iload #10
        //   893: iadd
        //   894: i2d
        //   895: dstore #24
        //   897: dload #24
        //   899: invokestatic isNaN : (D)Z
        //   902: pop
        //   903: dload #19
        //   905: invokestatic isNaN : (D)Z
        //   908: pop
        //   909: dload #19
        //   911: dload #21
        //   913: dload #24
        //   915: dmul
        //   916: dadd
        //   917: d2i
        //   918: istore #10
        //   920: goto -> 970
        //   923: iload #10
        //   925: i2d
        //   926: dstore #19
        //   928: aload #14
        //   930: invokestatic parseDouble : (Ljava/lang/String;)D
        //   933: dstore #21
        //   935: aload #16
        //   937: getfield Price : I
        //   940: istore #10
        //   942: iload #10
        //   944: i2d
        //   945: dstore #24
        //   947: dload #24
        //   949: invokestatic isNaN : (D)Z
        //   952: pop
        //   953: dload #19
        //   955: invokestatic isNaN : (D)Z
        //   958: pop
        //   959: dload #19
        //   961: dload #21
        //   963: dload #24
        //   965: dmul
        //   966: dadd
        //   967: d2i
        //   968: istore #10
        //   970: iinc #15, 1
        //   973: goto -> 724
        //   976: aload_1
        //   977: astore #5
        //   979: astore_1
        //   980: goto -> 1877
        //   983: aload_1
        //   984: astore #5
        //   986: astore_1
        //   987: goto -> 1834
        //   990: astore #5
        //   992: aload_1
        //   993: astore #6
        //   995: aload #5
        //   997: astore_1
        //   998: aload #6
        //   1000: astore #5
        //   1002: goto -> 1877
        //   1005: astore #6
        //   1007: aload_1
        //   1008: astore #5
        //   1010: aload #6
        //   1012: astore_1
        //   1013: goto -> 1834
        //   1016: iinc #12, 1
        //   1019: goto -> 607
        //   1022: astore #5
        //   1024: aload_1
        //   1025: astore #6
        //   1027: aload #5
        //   1029: astore_1
        //   1030: aload #6
        //   1032: astore #5
        //   1034: goto -> 1877
        //   1037: astore #5
        //   1039: aload_1
        //   1040: astore #6
        //   1042: aload #5
        //   1044: astore_1
        //   1045: aload #6
        //   1047: astore #5
        //   1049: goto -> 1834
        //   1052: astore #5
        //   1054: aload_1
        //   1055: astore #6
        //   1057: aload #5
        //   1059: astore_1
        //   1060: aload #6
        //   1062: astore #5
        //   1064: goto -> 1877
        //   1067: astore #5
        //   1069: aload_1
        //   1070: astore #6
        //   1072: aload #5
        //   1074: astore_1
        //   1075: aload #6
        //   1077: astore #5
        //   1079: goto -> 1834
        //   1082: aload_1
        //   1083: astore #6
        //   1085: aload #5
        //   1087: astore_1
        //   1088: aload_2
        //   1089: astore #5
        //   1091: goto -> 1453
        //   1094: astore #6
        //   1096: aload_1
        //   1097: astore #5
        //   1099: aload #6
        //   1101: astore_1
        //   1102: goto -> 1877
        //   1105: astore #5
        //   1107: aload_1
        //   1108: astore #6
        //   1110: aload #5
        //   1112: astore_1
        //   1113: aload #6
        //   1115: astore #5
        //   1117: goto -> 1834
        //   1120: astore_1
        //   1121: goto -> 1877
        //   1124: astore_1
        //   1125: goto -> 1834
        //   1128: ldc_w 'k'
        //   1131: astore #8
        //   1133: ldc_w 'd'
        //   1136: astore #6
        //   1138: ldc_w ','
        //   1141: astore #4
        //   1143: aload_1
        //   1144: aload_1
        //   1145: ldc_w ':'
        //   1148: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1151: invokevirtual substring : (I)Ljava/lang/String;
        //   1154: ldc_w ' '
        //   1157: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   1160: astore_2
        //   1161: iconst_0
        //   1162: istore #12
        //   1164: aload #8
        //   1166: astore_1
        //   1167: aload_2
        //   1168: arraylength
        //   1169: istore #13
        //   1171: iload #12
        //   1173: iload #13
        //   1175: if_icmpge -> 1443
        //   1178: aload_2
        //   1179: iload #12
        //   1181: aaload
        //   1182: aload_2
        //   1183: iload #12
        //   1185: aaload
        //   1186: ldc_w ':'
        //   1189: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1192: iconst_1
        //   1193: iadd
        //   1194: invokevirtual substring : (I)Ljava/lang/String;
        //   1197: astore #8
        //   1199: aload #8
        //   1201: iconst_0
        //   1202: aload #8
        //   1204: ldc_w 'x'
        //   1207: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1210: invokevirtual substring : (II)Ljava/lang/String;
        //   1213: astore #9
        //   1215: aload_2
        //   1216: iload #12
        //   1218: aaload
        //   1219: aload_2
        //   1220: iload #12
        //   1222: aaload
        //   1223: ldc_w 'x'
        //   1226: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1229: invokevirtual substring : (I)Ljava/lang/String;
        //   1232: ldc_w 'x'
        //   1235: ldc ''
        //   1237: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1240: ldc_w 'n'
        //   1243: ldc ''
        //   1245: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1248: aload #6
        //   1250: ldc ''
        //   1252: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1255: aload_1
        //   1256: ldc ''
        //   1258: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1261: astore #8
        //   1263: aload_0
        //   1264: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   1267: aload #9
        //   1269: invokevirtual has : (Ljava/lang/String;)Z
        //   1272: istore #7
        //   1274: iload #7
        //   1276: ifeq -> 1315
        //   1279: aload_0
        //   1280: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   1283: aload #9
        //   1285: aload_0
        //   1286: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   1289: aload #9
        //   1291: invokevirtual getInt : (Ljava/lang/String;)I
        //   1294: aload #8
        //   1296: invokestatic parseInt : (Ljava/lang/String;)I
        //   1299: iadd
        //   1300: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1303: pop
        //   1304: goto -> 1327
        //   1307: astore_1
        //   1308: goto -> 1877
        //   1311: astore_1
        //   1312: goto -> 1834
        //   1315: aload_0
        //   1316: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   1319: aload #9
        //   1321: aload #8
        //   1323: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1326: pop
        //   1327: iload #10
        //   1329: i2d
        //   1330: dstore #19
        //   1332: aload #8
        //   1334: invokestatic parseDouble : (Ljava/lang/String;)D
        //   1337: dstore #21
        //   1339: aload_0
        //   1340: getfield jsonTienxien : Lorg/json/JSONObject;
        //   1343: aload #9
        //   1345: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1348: dstore #24
        //   1350: dload #19
        //   1352: invokestatic isNaN : (D)Z
        //   1355: pop
        //   1356: dload #19
        //   1358: dload #21
        //   1360: dload #24
        //   1362: dmul
        //   1363: dadd
        //   1364: d2i
        //   1365: istore #10
        //   1367: goto -> 1421
        //   1370: astore #9
        //   1372: iload #10
        //   1374: i2d
        //   1375: dstore #19
        //   1377: aload #8
        //   1379: invokestatic parseDouble : (Ljava/lang/String;)D
        //   1382: dstore #21
        //   1384: aload_0
        //   1385: getfield Price : I
        //   1388: istore #10
        //   1390: iload #10
        //   1392: bipush #20
        //   1394: iadd
        //   1395: i2d
        //   1396: dstore #24
        //   1398: dload #24
        //   1400: invokestatic isNaN : (D)Z
        //   1403: pop
        //   1404: dload #19
        //   1406: invokestatic isNaN : (D)Z
        //   1409: pop
        //   1410: dload #19
        //   1412: dload #21
        //   1414: dload #24
        //   1416: dmul
        //   1417: dadd
        //   1418: d2i
        //   1419: istore #10
        //   1421: iinc #12, 1
        //   1424: goto -> 1167
        //   1427: astore_1
        //   1428: goto -> 1436
        //   1431: astore_1
        //   1432: goto -> 1440
        //   1435: astore_1
        //   1436: goto -> 1877
        //   1439: astore_1
        //   1440: goto -> 1834
        //   1443: aload #5
        //   1445: astore #6
        //   1447: aload #4
        //   1449: astore #5
        //   1451: aload_3
        //   1452: astore_1
        //   1453: aload_0
        //   1454: astore #4
        //   1456: aload #6
        //   1458: invokeinterface isClosed : ()Z
        //   1463: ifne -> 1473
        //   1466: aload #6
        //   1468: invokeinterface close : ()V
        //   1473: iload #10
        //   1475: i2d
        //   1476: aload #4
        //   1478: getfield myBalance : D
        //   1481: dcmpl
        //   1482: ifle -> 1489
        //   1485: ldc_w 'Vqusticl
        //   1488: areturn
        //   1489: aload #4
        //   1491: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   1494: invokevirtual keys : ()Ljava/util/Iterator;
        //   1497: astore_3
        //   1498: ldc ''
        //   1500: astore #8
        //   1502: aload #5
        //   1504: astore #6
        //   1506: aload_1
        //   1507: astore_2
        //   1508: aload #8
        //   1510: astore_1
        //   1511: aload_3
        //   1512: invokeinterface hasNext : ()Z
        //   1517: ifeq -> 1763
        //   1520: aload_3
        //   1521: invokeinterface next : ()Ljava/lang/Object;
        //   1526: checkcast java/lang/String
        //   1529: astore #8
        //   1531: aload #4
        //   1533: getfield myMax : Ljava/lang/String;
        //   1536: ldc_w '.'
        //   1539: invokevirtual contains : (Ljava/lang/CharSequence;)Z
        //   1542: istore #7
        //   1544: iload #7
        //   1546: ifeq -> 1574
        //   1549: aload #4
        //   1551: getfield myMax : Ljava/lang/String;
        //   1554: ldc_w '\.'
        //   1557: ldc ''
        //   1559: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1562: invokestatic parseInt : (Ljava/lang/String;)I
        //   1565: istore #12
        //   1567: goto -> 1591
        //   1570: astore_1
        //   1571: goto -> 1755
        //   1574: aload #4
        //   1576: getfield myMax : Ljava/lang/String;
        //   1579: aload #6
        //   1581: ldc ''
        //   1583: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1586: invokestatic parseInt : (Ljava/lang/String;)I
        //   1589: istore #12
        //   1591: aload_2
        //   1592: aload #8
        //   1594: invokevirtual has : (Ljava/lang/String;)Z
        //   1597: ifeq -> 1679
        //   1600: aload #4
        //   1602: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   1605: aload #8
        //   1607: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1610: dstore #24
        //   1612: aload_2
        //   1613: aload #8
        //   1615: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1618: dstore #21
        //   1620: aload_1
        //   1621: astore #5
        //   1623: dload #24
        //   1625: dload #21
        //   1627: dadd
        //   1628: iload #12
        //   1630: i2d
        //   1631: dcmpl
        //   1632: ifle -> 1676
        //   1635: new java/lang/StringBuilder
        //   1638: astore #5
        //   1640: aload #5
        //   1642: invokespecial <init> : ()V
        //   1645: aload #5
        //   1647: aload_1
        //   1648: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1651: pop
        //   1652: aload #5
        //   1654: aload #8
        //   1656: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1659: pop
        //   1660: aload #5
        //   1662: ldc_w ' '
        //   1665: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1668: pop
        //   1669: aload #5
        //   1671: invokevirtual toString : ()Ljava/lang/String;
        //   1674: astore #5
        //   1676: goto -> 1740
        //   1679: aload_1
        //   1680: astore #5
        //   1682: aload #4
        //   1684: getfield jsonChayTrang : Lorg/json/JSONObject;
        //   1687: aload #8
        //   1689: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1692: iload #12
        //   1694: i2d
        //   1695: dcmpl
        //   1696: ifle -> 1740
        //   1699: new java/lang/StringBuilder
        //   1702: astore #5
        //   1704: aload #5
        //   1706: invokespecial <init> : ()V
        //   1709: aload #5
        //   1711: aload_1
        //   1712: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1715: pop
        //   1716: aload #5
        //   1718: aload #8
        //   1720: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1723: pop
        //   1724: aload #5
        //   1726: ldc_w ' '
        //   1729: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1732: pop
        //   1733: aload #5
        //   1735: invokevirtual toString : ()Ljava/lang/String;
        //   1738: astore #5
        //   1740: aload #5
        //   1742: astore_1
        //   1743: goto -> 1511
        //   1746: astore_1
        //   1747: goto -> 1755
        //   1750: astore_1
        //   1751: goto -> 1755
        //   1754: astore_1
        //   1755: aload_1
        //   1756: invokevirtual printStackTrace : ()V
        //   1759: ldc_w 'Kitra lsli
        //   1762: areturn
        //   1763: aload_1
        //   1764: invokevirtual length : ()I
        //   1767: ifle -> 1810
        //   1770: new java/lang/StringBuilder
        //   1773: dup
        //   1774: invokespecial <init> : ()V
        //   1777: astore #5
        //   1779: aload #5
        //   1781: ldc_w 'Cc'
        //   1784: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1787: pop
        //   1788: aload #5
        //   1790: aload_1
        //   1791: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1794: pop
        //   1795: aload #5
        //   1797: ldc_w ' vqumax ctrang'
        //   1800: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1803: pop
        //   1804: aload #5
        //   1806: invokevirtual toString : ()Ljava/lang/String;
        //   1809: areturn
        //   1810: ldc ''
        //   1812: areturn
        //   1813: astore_1
        //   1814: goto -> 1877
        //   1817: astore_1
        //   1818: goto -> 1834
        //   1821: astore_1
        //   1822: goto -> 1877
        //   1825: astore_1
        //   1826: goto -> 1834
        //   1829: astore_1
        //   1830: goto -> 1877
        //   1833: astore_1
        //   1834: aload_1
        //   1835: invokevirtual printStackTrace : ()V
        //   1838: aload #5
        //   1840: invokeinterface isClosed : ()Z
        //   1845: ifne -> 1855
        //   1848: aload #5
        //   1850: invokeinterface close : ()V
        //   1855: aload #5
        //   1857: invokeinterface isClosed : ()Z
        //   1862: ifne -> 1872
        //   1865: aload #5
        //   1867: invokeinterface close : ()V
        //   1872: ldc_w 'Khphtndung!'
        //   1875: areturn
        //   1876: astore_1
        //   1877: aload #5
        //   1879: invokeinterface isClosed : ()Z
        //   1884: ifne -> 1894
        //   1887: aload #5
        //   1889: invokeinterface close : ()V
        //   1894: goto -> 1899
        //   1897: aload_1
        //   1898: athrow
        //   1899: goto -> 1897
        // Exception table:
        //   from	to	target	type
        //   496	505	1833	org/json/JSONException
        //   496	505	1829	finally
        //   510	531	538	org/json/JSONException
        //   510	531	534	finally
        //   542	554	1833	org/json/JSONException
        //   542	554	1829	finally
        //   576	595	1124	org/json/JSONException
        //   576	595	1120	finally
        //   607	614	1105	org/json/JSONException
        //   607	614	1094	finally
        //   620	649	1067	org/json/JSONException
        //   620	649	1052	finally
        //   649	721	1037	org/json/JSONException
        //   649	721	1022	finally
        //   741	753	1005	org/json/JSONException
        //   741	753	990	finally
        //   758	765	817	org/json/JSONException
        //   758	765	806	finally
        //   765	789	799	org/json/JSONException
        //   765	789	792	finally
        //   828	841	983	org/json/JSONException
        //   828	841	976	finally
        //   841	853	983	org/json/JSONException
        //   841	853	976	finally
        //   863	889	799	org/json/JSONException
        //   863	889	792	finally
        //   928	942	983	org/json/JSONException
        //   928	942	976	finally
        //   1143	1161	1825	org/json/JSONException
        //   1143	1161	1821	finally
        //   1167	1171	1817	org/json/JSONException
        //   1167	1171	1813	finally
        //   1178	1263	1439	org/json/JSONException
        //   1178	1263	1435	finally
        //   1263	1274	1431	org/json/JSONException
        //   1263	1274	1427	finally
        //   1279	1304	1311	org/json/JSONException
        //   1279	1304	1307	finally
        //   1315	1327	1431	org/json/JSONException
        //   1315	1327	1427	finally
        //   1332	1350	1370	java/lang/Exception
        //   1332	1350	1311	org/json/JSONException
        //   1332	1350	1307	finally
        //   1377	1390	1431	org/json/JSONException
        //   1377	1390	1427	finally
        //   1531	1544	1754	org/json/JSONException
        //   1549	1567	1570	org/json/JSONException
        //   1574	1591	1754	org/json/JSONException
        //   1591	1620	1750	org/json/JSONException
        //   1635	1676	1746	org/json/JSONException
        //   1682	1740	1746	org/json/JSONException
        //   1834	1855	1876	finally
    }

    public String TaoTinDe() {
        String str = "Se_chuyen";
        byte b = 0;
        JSONObject jSONObject = new JSONObject();
        ArrayList<JSONObject> arrayList = new ArrayList();
        try {
            i = Integer.parseInt(this.myMax.replace(".", ""));
        } catch (Exception exception) {
            i = Integer.parseInt(this.myMax.replace(",", ""));
        }
        if (this.edt_tien.getText().toString().length() != 0) {
            if (Integer.parseInt(this.edt_tien.getText().toString()) > i)
                return "Stivqumax ";
            i = Integer.parseInt(this.edt_tien.getText().toString());
        }
        int j = 0;
        try {
            Object object;
            while (j < this.mSo.size()) {
                int k;
                String str1 = this.mSo.get(j);
                if (object >= 50)
                    break;
                if (this.jsonGia.has(str1)) {
                    if (this.jsonGia.getInt(str1) + this.Price > this.MaxChay) {
                        Object object2 = object;
                        continue;
                    }
                } else if (this.Price > this.MaxChay) {
                    Object object2 = object;
                    continue;
                }
                int m = Integer.parseInt(((String)this.mTienTon.get(j)).replace(".", ""));
                JSONObject jSONObject1 = new JSONObject();
                this();
                jSONObject1.put("So_chon", str1);
                boolean bool = jSONObject.has(str1);
                if (bool) {
                    k = jSONObject.getJSONObject(str1).getInt("Da_chuyen") + m;
                } else {
                    k = 0;
                }
                jSONObject1.put("Da_chuyen", k);
                if (jSONObject1.getInt("Da_chuyen") + m <= i) {
                    k = m;
                } else {
                    k = i - jSONObject1.getInt("Da_chuyen");
                }
                jSONObject1.put("Se_chuyen", k);
                Object object1 = object;
                if (jSONObject1.getInt("Se_chuyen") > 0) {
                    arrayList.add(jSONObject1);
                    int n = object + 1;
                }
                continue;
                j++;
                object = SYNTHETIC_LOCAL_VARIABLE_8;
            }
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        Collections.sort(arrayList, new Comparator<JSONObject>() {
            public int compare(JSONObject param1JSONObject1, JSONObject param1JSONObject2) {
                Integer integer1;
                Integer integer2;
                Integer integer3 = Integer.valueOf(0);
                Integer integer4 = Integer.valueOf(0);
                try {
                    integer1 = Integer.valueOf(param1JSONObject1.getInt("Se_chuyen"));
                    integer3 = integer1;
                    int i = param1JSONObject2.getInt("Se_chuyen");
                    integer2 = Integer.valueOf(i);
                } catch (JSONException jSONException) {
                    integer1 = integer3;
                    integer2 = integer4;
                }
                return integer2.compareTo(integer1);
            }
        });
        j = 0;
        b = 0;
        int i = 0;
        try {
            while (true) {
                int k = arrayList.size();
                if (b < k) {
                    jSONObject = arrayList.get(b);
                    k = jSONObject.getInt(str);
                    if (k > 0) {
                        if (j > k) {
                            StringBuilder stringBuilder = new StringBuilder();
                            this();
                            stringBuilder.append(this.xuatDan);
                            stringBuilder.append("x");
                            stringBuilder.append(j);
                            stringBuilder.append(this.donvi);
                            this.xuatDan = stringBuilder.toString();
                            stringBuilder = new StringBuilder();
                            this();
                            stringBuilder.append(this.xuatDan);
                            stringBuilder.append(jSONObject.getString("So_chon"));
                            stringBuilder.append(",");
                            this.xuatDan = stringBuilder.toString();
                            i = 0;
                        } else {
                            StringBuilder stringBuilder = new StringBuilder();
                            this();
                            stringBuilder.append(this.xuatDan);
                            stringBuilder.append(jSONObject.getString("So_chon"));
                            stringBuilder.append(",");
                            this.xuatDan = stringBuilder.toString();
                        }
                        j = k;
                        i++;
                    }
                    b++;
                    continue;
                }
                if (this.xuatDan.length() > 4 && i > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    this();
                    stringBuilder.append(this.xuatDan);
                    stringBuilder.append("x");
                    stringBuilder.append(j);
                    stringBuilder.append(this.donvi);
                    this.xuatDan = stringBuilder.toString();
                }
                return (i > 0) ? this.xuatDan : "";
            }
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        return (i > 0) ? this.xuatDan : "";
    }

    public String TaoTinXi() {
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        ArrayList<JSONObject> arrayList = new ArrayList();
        int j = Integer.parseInt(this.myMax.replace(".", ""));
        int k = j;
        try {
            if (this.edt_tien.getText().toString().trim().length() > 0) {
                k = j;
                if (Congthuc.isNumeric(this.edt_tien.getText().toString().trim())) {
                    if (Integer.parseInt(this.edt_tien.getText().toString()) > j)
                        return "Stivqumax ";
                    k = Integer.parseInt(this.edt_tien.getText().toString());
                }
            }
            j = 0;
            while (true) {
                int m = this.mSo.size();
                if (j >= m || i >= 50)
                    break;
                String str = this.mSo.get(j);
                int n = Integer.parseInt(((String)this.mTienTon.get(j)).replace(".", ""));
                m = i;
                if (n > 0) {
                    m = i;
                    if (Integer.parseInt((String)this.mGia.get(j)) <= this.MaxChay) {
                        JSONObject jSONObject1 = new JSONObject();
                        this();
                        jSONObject1.put("So_chon", str);
                        boolean bool = jSONObject.has(str);
                        if (bool) {
                            m = jSONObject.getJSONObject(str).getInt("Da_chuyen") + n;
                        } else {
                            m = 0;
                        }
                        jSONObject1.put("Da_chuyen", m);
                        if (jSONObject1.getInt("Da_chuyen") + n <= k) {
                            m = n;
                        } else {
                            m = k - jSONObject1.getInt("Da_chuyen");
                        }
                        jSONObject1.put("Se_chuyen", m);
                        m = i;
                        if (jSONObject1.getInt("Se_chuyen") > 0) {
                            arrayList.add(jSONObject1);
                            m = i + 1;
                        }
                    }
                }
                j++;
                i = m;
            }
            Comparator<JSONObject> comparator = new Comparator<JSONObject>() {
                public int compare(JSONObject param1JSONObject1, JSONObject param1JSONObject2) {
                    Integer integer1;
                    Integer integer2;
                    Integer integer3 = Integer.valueOf(0);
                    Integer integer4 = Integer.valueOf(0);
                    try {
                        integer1 = Integer.valueOf(param1JSONObject1.getInt("Se_chuyen"));
                        integer3 = integer1;
                        int i = param1JSONObject2.getInt("Se_chuyen");
                        integer2 = Integer.valueOf(i);
                    } catch (JSONException jSONException) {
                        integer1 = integer3;
                        integer2 = integer4;
                    }
                    return integer2.compareTo(integer1);
                }
            };
            super(this);
            Collections.sort(arrayList, comparator);
            for (k = 0; k < arrayList.size(); k++) {
                StringBuilder stringBuilder = new StringBuilder();
                this();
                stringBuilder.append(this.xuatDan);
                stringBuilder.append(((JSONObject)arrayList.get(k)).getString("So_chon"));
                stringBuilder.append("x");
                stringBuilder.append(((JSONObject)arrayList.get(k)).getString("Se_chuyen"));
                stringBuilder.append(this.donvi);
                this.xuatDan = stringBuilder.toString();
            }
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        } catch (Exception exception) {}
        return (this.xuatDan.length() > 5) ? this.xuatDan : "";
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.v = paramLayoutInflater.inflate(2131427398, paramViewGroup, false);
        init();
        this.db = new Database((Context)getActivity());
        new MainActivity();
        this.ToDay = MainActivity.Get_date();
        this.radio_de.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_Chaytrang.this.radio_de.isChecked()) {
                    Frag_Chaytrang.this.li_loaide.setVisibility(0);
                    try {
                        Database database = Frag_Chaytrang.this.db;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append("Select sum((the_loai = 'dea')* diem) as de_a\n,sum((the_loai = 'deb')* diem) as de_b\n,sum((the_loai = 'det')* diem) as de_t\n,sum((the_loai = 'dec')* diem) as de_c\n,sum((the_loai = 'ded')* diem) as de_d\nFrom tbl_soctS \nWhere ngay_nhan = '");
                        stringBuilder.append(Frag_Chaytrang.this.ToDay);
                        stringBuilder.append("'");
                        Cursor cursor = database.GetData(stringBuilder.toString());
                        if (cursor.moveToFirst() && cursor != null) {
                            int[] arrayOfInt = new int[5];
                            if (cursor.getDouble(0) > 0.0D) {
                                arrayOfInt[0] = 1;
                                Frag_Chaytrang.this.radio_dea.setEnabled(true);
                            } else {
                                arrayOfInt[0] = 0;
                                Frag_Chaytrang.this.radio_dea.setEnabled(false);
                            }
                            if (cursor.getDouble(1) > 0.0D) {
                                arrayOfInt[1] = 1;
                                Frag_Chaytrang.this.radio_deb.setEnabled(true);
                            } else {
                                arrayOfInt[1] = 0;
                                Frag_Chaytrang.this.radio_deb.setEnabled(false);
                            }
                            if (cursor.getDouble(2) > 0.0D) {
                                arrayOfInt[2] = 1;
                            } else {
                                arrayOfInt[2] = 0;
                            }
                            if (cursor.getDouble(3) > 0.0D) {
                                arrayOfInt[3] = 1;
                                Frag_Chaytrang.this.radio_dec.setEnabled(true);
                            } else {
                                arrayOfInt[3] = 0;
                                Frag_Chaytrang.this.radio_dec.setEnabled(false);
                            }
                            if (cursor.getDouble(4) > 0.0D) {
                                arrayOfInt[4] = 1;
                                Frag_Chaytrang.this.radio_ded.setEnabled(true);
                            } else {
                                arrayOfInt[4] = 0;
                                Frag_Chaytrang.this.radio_ded.setEnabled(false);
                            }
                            if (arrayOfInt[0] == 0 && (arrayOfInt[1] == 1 || arrayOfInt[2] == 1) && arrayOfInt[3] == 0 && arrayOfInt[4] == 0) {
                                Frag_Chaytrang.this.DangXuat = "(the_loai = 'deb' or the_loai = 'det')";
                                Frag_Chaytrang.this.li_loaixi.setVisibility(8);
                                Frag_Chaytrang.this.li_loaide.setVisibility(8);
                                Frag_Chaytrang.this.radio_deb.setChecked(true);
                                Frag_Chaytrang.this.xem_RecycView();
                            } else if (arrayOfInt[0] == 0 && arrayOfInt[1] == 0 && arrayOfInt[2] == 0 && arrayOfInt[3] == 0 && arrayOfInt[4] == 0) {
                                Frag_Chaytrang.this.DangXuat = "(the_loai = 'deb' or the_loai = 'det')";
                                Frag_Chaytrang.this.li_loaixi.setVisibility(8);
                                Frag_Chaytrang.this.li_loaide.setVisibility(8);
                                Frag_Chaytrang.this.radio_deb.setChecked(true);
                                Frag_Chaytrang.this.xem_RecycView();
                            } else {
                                Frag_Chaytrang.this.DangXuat = "(the_loai = 'deb' or the_loai = 'det')";
                                Frag_Chaytrang.this.li_loaixi.setVisibility(8);
                                Frag_Chaytrang.this.li_loaide.setVisibility(0);
                                Frag_Chaytrang.this.radio_deb.setChecked(true);
                                Frag_Chaytrang.this.xem_RecycView();
                            }
                            if (!cursor.isClosed() && cursor != null && !cursor.isClosed())
                                cursor.close();
                            Frag_Chaytrang.this.GameType = 0;
                            if (MainActivity.MyToken.length() > 0)
                                Frag_Chaytrang.this.Laygia();
                        } else {
                            Frag_Chaytrang.this.DangXuat = "(the_loai = 'deb' or the_loai = 'det')";
                            Frag_Chaytrang.this.GameType = 0;
                            if (MainActivity.MyToken.length() > 0)
                                Frag_Chaytrang.this.Laygia();
                        }
                    } catch (SQLException sQLException) {
                        Frag_Chaytrang.this.DangXuat = "(the_loai = 'deb' or the_loai = 'det')";
                        Frag_Chaytrang.this.GameType = 0;
                        if (MainActivity.MyToken.length() > 0)
                            Frag_Chaytrang.this.Laygia();
                    }
                }
            }
        });
        this.radio_dea.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_Chaytrang.this.radio_dea.isChecked()) {
                    Frag_Chaytrang.this.DangXuat = "the_loai = 'dea'";
                    Frag_Chaytrang.this.li_loaixi.setVisibility(8);
                    Frag_Chaytrang.this.GameType = 21;
                    Frag_Chaytrang.this.Laygia();
                }
            }
        });
        try {
            this.mwebsite.clear();
            this.mpassword.clear();
            Cursor cursor = this.db.GetData("Select * From tbl_chaytrang_acc");
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    this.mwebsite.add(cursor.getString(0));
                    this.mpassword.add(cursor.getString(1));
                }
                if (cursor != null)
                    cursor.close();
                ArrayAdapter arrayAdapter = new ArrayAdapter();
                this((Context)getActivity(), 2131427455, this.mwebsite);
                this.spr_trang.setAdapter((SpinnerAdapter)arrayAdapter);
                if (this.mwebsite.size() > 0) {
                    this.spr_trang.setSelection(0);
                    this.spin_pointion = 0;
                }
            }
            cursor.close();
        } catch (Exception exception) {
            Toast.makeText((Context)getActivity(), "copy dlibm, 1).show();
        }
        this.radio_deb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_Chaytrang.this.radio_deb.isChecked()) {
                    Frag_Chaytrang.this.DangXuat = "(the_loai = 'deb' or the_loai = 'det')";
                    Frag_Chaytrang.this.li_loaixi.setVisibility(8);
                    Frag_Chaytrang.this.GameType = 0;
                    if (MainActivity.MyToken.length() > 0)
                        Frag_Chaytrang.this.Laygia();
                }
            }
        });
        this.radio_dec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_Chaytrang.this.radio_dec.isChecked()) {
                    Frag_Chaytrang.this.DangXuat = "the_loai = 'dec'";
                    Frag_Chaytrang.this.li_loaixi.setVisibility(8);
                    Frag_Chaytrang.this.GameType = 23;
                    if (MainActivity.MyToken.length() > 0)
                        Frag_Chaytrang.this.Laygia();
                }
            }
        });
        this.radio_ded.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_Chaytrang.this.radio_ded.isChecked()) {
                    Frag_Chaytrang.this.DangXuat = "the_loai = 'ded'";
                    Frag_Chaytrang.this.li_loaixi.setVisibility(8);
                    Frag_Chaytrang.this.GameType = 22;
                    if (MainActivity.MyToken.length() > 0)
                        Frag_Chaytrang.this.Laygia();
                }
            }
        });
        this.radio_lo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_Chaytrang.this.radio_lo.isChecked()) {
                    Frag_Chaytrang.this.DangXuat = "the_loai = 'lo'";
                    Frag_Chaytrang.this.li_loaixi.setVisibility(8);
                    Frag_Chaytrang.this.li_loaide.setVisibility(8);
                    if (!Frag_Chaytrang.this.LoLive) {
                        Frag_Chaytrang.this.GameType = 1;
                    } else {
                        Frag_Chaytrang.this.GameType = 20;
                    }
                    if (MainActivity.MyToken.length() > 0)
                        Frag_Chaytrang.this.Laygia();
                }
            }
        });
        this.radio_xi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_Chaytrang.this.radio_xi.isChecked()) {
                    Frag_Chaytrang.this.DangXuat = "the_loai = 'xi'";
                    Frag_Chaytrang.this.li_loaixi.setVisibility(0);
                    Frag_Chaytrang.this.li_loaide.setVisibility(8);
                    Frag_Chaytrang.this.radio_xi2.setChecked(true);
                    Frag_Chaytrang.this.GameType = 2;
                    if (MainActivity.MyToken.length() > 0)
                        Frag_Chaytrang.this.Laygia();
                }
            }
        });
        this.radio_xi2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_Chaytrang.this.radio_xi2.isChecked()) {
                    Frag_Chaytrang.this.DangXuat = "the_loai = 'xi'";
                    Frag_Chaytrang.this.li_loaixi.setVisibility(0);
                    Frag_Chaytrang.this.li_loaide.setVisibility(8);
                    Frag_Chaytrang.this.lay_xien = " length(so_chon) = 5 ";
                    Frag_Chaytrang.this.GameType = 2;
                    if (MainActivity.MyToken.length() > 0)
                        Frag_Chaytrang.this.Laygia();
                }
            }
        });
        this.radio_xi3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_Chaytrang.this.radio_xi3.isChecked()) {
                    Frag_Chaytrang.this.DangXuat = "the_loai = 'xi'";
                    Frag_Chaytrang.this.li_loaixi.setVisibility(0);
                    Frag_Chaytrang.this.li_loaide.setVisibility(8);
                    Frag_Chaytrang.this.lay_xien = " length(so_chon) = 8 ";
                    Frag_Chaytrang.this.GameType = 3;
                    if (MainActivity.MyToken.length() > 0)
                        Frag_Chaytrang.this.Laygia();
                }
            }
        });
        this.radio_xi4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_Chaytrang.this.radio_xi4.isChecked()) {
                    Frag_Chaytrang.this.DangXuat = "the_loai = 'xi'";
                    Frag_Chaytrang.this.li_loaixi.setVisibility(0);
                    Frag_Chaytrang.this.li_loaide.setVisibility(8);
                    Frag_Chaytrang.this.lay_xien = " length(so_chon) = 11 ";
                    Frag_Chaytrang.this.GameType = 4;
                    if (MainActivity.MyToken.length() > 0)
                        Frag_Chaytrang.this.Laygia();
                }
            }
        });
        this.spr_trang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_Chaytrang.this.spin_pointion = param1Int;
                Frag_Chaytrang frag_Chaytrang = Frag_Chaytrang.this;
                frag_Chaytrang.login(frag_Chaytrang.mwebsite.get(param1Int), Frag_Chaytrang.this.mpassword.get(param1Int));
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        if (this.mwebsite.size() > 0)
            login(this.mwebsite.get(this.spin_pointion), this.mpassword.get(this.spin_pointion));
        this.btn_Xuatso.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (Frag_Chaytrang.this.spin_pointion == -1)
                    Toast.makeText((Context)Frag_Chaytrang.this.getActivity(), "Khctrang xu, 0).show();
                if (MainActivity.MyToken.length() > 0) {
                    int i = Frag_Chaytrang.this.GameType;
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i != 3) {
                                    if (i != 4) {
                                        switch (i) {
                                            case 23:
                                                Frag_Chaytrang.this.the_loai = "dec";
                                                Frag_Chaytrang.this.xuatDan = "De dau giai 1:";
                                                Frag_Chaytrang.this.donvi = "n ";
                                                Frag_Chaytrang.this.Dieukien = "the_loai = 'dec'";
                                                Frag_Chaytrang.this.TaoTinDe();
                                                break;
                                            case 22:
                                                Frag_Chaytrang.this.the_loai = "ded";
                                                Frag_Chaytrang.this.xuatDan = "De giai 1:";
                                                Frag_Chaytrang.this.donvi = "n ";
                                                Frag_Chaytrang.this.Dieukien = "the_loai = 'ded'";
                                                Frag_Chaytrang.this.TaoTinDe();
                                                break;
                                            case 21:
                                                Frag_Chaytrang.this.the_loai = "dea";
                                                Frag_Chaytrang.this.xuatDan = "De dau:";
                                                Frag_Chaytrang.this.donvi = "n ";
                                                Frag_Chaytrang.this.Dieukien = "the_loai = 'dea'";
                                                Frag_Chaytrang.this.TaoTinDe();
                                                break;
                                            case 20:
                                                Frag_Chaytrang.this.the_loai = "lo";
                                                Frag_Chaytrang.this.xuatDan = "Lo:";
                                                Frag_Chaytrang.this.donvi = "d ";
                                                Frag_Chaytrang.this.Dieukien = "the_loai = 'lo'";
                                                Frag_Chaytrang.this.TaoTinDe();
                                                break;
                                        }
                                    } else {
                                        Frag_Chaytrang.this.the_loai = "xi4";
                                        Frag_Chaytrang.this.xuatDan = "Xi:";
                                        Frag_Chaytrang.this.donvi = "n ";
                                        Frag_Chaytrang.this.Dieukien = "the_loai = 'xi' AND length(so_chon) = 11";
                                        Frag_Chaytrang.this.TaoTinXi();
                                    }
                                } else {
                                    Frag_Chaytrang.this.the_loai = "xi3";
                                    Frag_Chaytrang.this.xuatDan = "Xi:";
                                    Frag_Chaytrang.this.donvi = "n ";
                                    Frag_Chaytrang.this.Dieukien = "the_loai = 'xi' AND length(so_chon) = 8";
                                    Frag_Chaytrang.this.TaoTinXi();
                                }
                            } else {
                                Frag_Chaytrang.this.the_loai = "xi2";
                                Frag_Chaytrang.this.xuatDan = "Xi:";
                                Frag_Chaytrang.this.donvi = "n ";
                                Frag_Chaytrang.this.Dieukien = "the_loai = 'xi' AND length(so_chon) = 5";
                                Frag_Chaytrang.this.TaoTinXi();
                            }
                        } else {

                        }
                    } else {
                        Frag_Chaytrang.this.the_loai = "deb";
                        Frag_Chaytrang.this.xuatDan = "De:";
                        Frag_Chaytrang.this.donvi = "n ";
                        Frag_Chaytrang.this.Dieukien = "(the_loai = 'deb' or the_loai = 'det')";
                        Frag_Chaytrang.this.TaoTinDe();
                    }
                    Frag_Chaytrang.this.Dialog();
                }
            }
        });
        this.btn_MaXuat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                Frag_Chaytrang.this.Dialog2();
            }
        });
        Handler handler = new Handler();
        this.handler = handler;
        handler.postDelayed(this.runnable, 1000L);
        xemlv();
        return this.v;
    }

    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacks(this.runnable);
    }

    public void xem_RecycView() {
        String str2;
        StringBuilder stringBuilder2;
        new MainActivity();
        String str1 = MainActivity.Get_date();
        StringBuilder stringBuilder1 = null;
        this.jsonTienxien = new JSONObject();
        this.mSo.clear();
        this.mTienNhan.clear();
        this.mTienOm.clear();
        this.mTienchuyen.clear();
        this.mTienTon.clear();
        this.mMax.clear();
        this.mGia.clear();
        this.mNhay.clear();
        String str3 = this.DangXuat;
        if (str3 == "(the_loai = 'deb' or the_loai = 'det')") {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Select tbl_soctS.So_chon\n, Sum((tbl_soctS.type_kh = 1) * (100-tbl_soctS.diem_khachgiu)*diem_quydoi/100) as diem\n, so_om.Om_deB + sum(tbl_soctS.diem_dly_giu*tbl_soctS.diem_quydoi/100) as So_Om\n, Sum((tbl_soctS.type_kh =2)");
            if (this.spin_pointion > -1) {
                stringBuilder1 = new StringBuilder();
                stringBuilder1.append("*(tbl_soctS.ten_kh='");
                stringBuilder1.append(this.mwebsite.get(this.spin_pointion));
                stringBuilder1.append("')");
                str2 = stringBuilder1.toString();
            } else {
                str2 = "";
            }
            stringBuilder2.append(str2);
            stringBuilder2.append("* tbl_soctS.diem_quydoi) as chuyen\n, Sum((tbl_soctS.type_kh =1) * (100-tbl_soctS.diem_khachgiu-tbl_soctS.diem_dly_giu)*diem_quydoi/100) - Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) - so_om.Om_deB as ton\n, so_nhay  From so_om Left Join tbl_soctS On tbl_soctS.so_chon = so_om.So\n Where tbl_soctS.ngay_nhan='");
            stringBuilder2.append(str1);
            stringBuilder2.append("' AND (tbl_soctS.the_loai='deb' OR tbl_soctS.the_loai='det') GROUP by so_om.So Order by ton DESC");
            str2 = stringBuilder2.toString();
        } else if (stringBuilder2 == "the_loai = 'lo'") {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Select tbl_soctS.So_chon\n, Sum((tbl_soctS.type_kh = 1) * (100-tbl_soctS.diem_khachgiu)*diem_quydoi/100) as diem\n, so_om.Om_Lo + sum(tbl_soctS.diem_dly_giu*tbl_soctS.diem_quydoi/100) as So_Om\n, Sum((tbl_soctS.type_kh =2)");
            if (this.spin_pointion > -1) {
                stringBuilder1 = new StringBuilder();
                stringBuilder1.append("*(tbl_soctS.ten_kh='");
                stringBuilder1.append(this.mwebsite.get(this.spin_pointion));
                stringBuilder1.append("')");
                str2 = stringBuilder1.toString();
            } else {
                str2 = "";
            }
            stringBuilder2.append(str2);
            stringBuilder2.append(" * tbl_soctS.diem_quydoi) as chuyen\n, Sum((tbl_soctS.type_kh =1) * (100-tbl_soctS.diem_khachgiu-tbl_soctS.diem_dly_giu)*diem_quydoi/100) - Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) - so_om.Om_Lo as ton\n, so_nhay  From so_om Left Join tbl_soctS On tbl_soctS.so_chon = so_om.So \n Where tbl_soctS.ngay_nhan='");
            stringBuilder2.append(str1);
            stringBuilder2.append("' AND tbl_soctS.the_loai='lo' \n GROUP by so_om.So Order by ton DESC");
            str2 = stringBuilder2.toString();
        } else if (stringBuilder2 == "the_loai = 'dea'") {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Select tbl_soctS.So_chon\n, Sum((tbl_soctS.type_kh = 1) * (100-tbl_soctS.diem_khachgiu)*diem_quydoi/100) as diem\n, so_om.Om_DeA + sum(tbl_soctS.diem_dly_giu*tbl_soctS.diem_quydoi/100) as So_Om\n, Sum((tbl_soctS.type_kh =2)");
            if (this.spin_pointion > -1) {
                stringBuilder1 = new StringBuilder();
                stringBuilder1.append("*(tbl_soctS.ten_kh='");
                stringBuilder1.append(this.mwebsite.get(this.spin_pointion));
                stringBuilder1.append("')");
                str2 = stringBuilder1.toString();
            } else {
                str2 = "";
            }
            stringBuilder2.append(str2);
            stringBuilder2.append(" * tbl_soctS.diem_quydoi) as chuyen\n, Sum((tbl_soctS.type_kh =1) * (100-tbl_soctS.diem_khachgiu-tbl_soctS.diem_dly_giu)*diem_quydoi/100) - Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) - so_om.Om_DeA as ton\n, so_nhay  From so_om Left Join tbl_soctS On tbl_soctS.so_chon = so_om.So \n Where tbl_soctS.ngay_nhan='");
            stringBuilder2.append(str1);
            stringBuilder2.append("' AND tbl_soctS.the_loai='dea' GROUP by so_chon Order by ton DESC");
            str2 = stringBuilder2.toString();
        } else if (stringBuilder2 == "the_loai = 'dec'") {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Select tbl_soctS.So_chon\n, Sum((tbl_soctS.type_kh = 1) * (100-tbl_soctS.diem_khachgiu)*diem_quydoi/100) as diem\n, so_om.Om_DeC + sum(tbl_soctS.diem_dly_giu*tbl_soctS.diem_quydoi/100) as So_Om\n, Sum((tbl_soctS.type_kh =2)");
            if (this.spin_pointion > -1) {
                stringBuilder1 = new StringBuilder();
                stringBuilder1.append("*(tbl_soctS.ten_kh='");
                stringBuilder1.append(this.mwebsite.get(this.spin_pointion));
                stringBuilder1.append("')");
                str2 = stringBuilder1.toString();
            } else {
                str2 = "";
            }
            stringBuilder2.append(str2);
            stringBuilder2.append(" * tbl_soctS.diem_quydoi) as chuyen\n, Sum((tbl_soctS.type_kh =1) * (100-tbl_soctS.diem_khachgiu-tbl_soctS.diem_dly_giu)*diem_quydoi/100) - Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) - so_om.Om_DeC as ton\n, so_nhay  From so_om Left Join tbl_soctS On tbl_soctS.so_chon = so_om.So \n Where tbl_soctS.ngay_nhan='");
            stringBuilder2.append(str1);
            stringBuilder2.append("' AND tbl_soctS.the_loai='dec' GROUP by so_chon Order by ton DESC");
            str2 = stringBuilder2.toString();
        } else if (stringBuilder2 == "the_loai = 'ded'") {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Select tbl_soctS.So_chon\n, Sum((tbl_soctS.type_kh = 1) * (100-tbl_soctS.diem_khachgiu)*diem_quydoi/100) as diem\n, so_om.Om_DeD + sum(tbl_soctS.diem_dly_giu*tbl_soctS.diem_quydoi/100) as So_Om\n, Sum((tbl_soctS.type_kh =2)");
            if (this.spin_pointion > -1) {
                stringBuilder1 = new StringBuilder();
                stringBuilder1.append("*(tbl_soctS.ten_kh='");
                stringBuilder1.append(this.mwebsite.get(this.spin_pointion));
                stringBuilder1.append("')");
                str2 = stringBuilder1.toString();
            } else {
                str2 = "";
            }
            stringBuilder2.append(str2);
            stringBuilder2.append(" * tbl_soctS.diem_quydoi) as chuyen\n, Sum((tbl_soctS.type_kh =1) * (100-tbl_soctS.diem_khachgiu-tbl_soctS.diem_dly_giu)*diem_quydoi/100) - Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) - so_om.Om_DeD as ton\n, so_nhay  From so_om Left Join tbl_soctS On tbl_soctS.so_chon = so_om.So \n Where tbl_soctS.ngay_nhan='");
            stringBuilder2.append(str1);
            stringBuilder2.append("' AND tbl_soctS.the_loai='ded' GROUP by so_chon Order by ton DESC");
            str2 = stringBuilder2.toString();
        } else if (stringBuilder2 == "the_loai = 'xi'") {
            Cursor cursor1 = this.db.GetData("Select * From So_om WHERE ID = 1");
            cursor1.moveToFirst();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT so_chon, sum((type_kh =1)*(100-diem_khachgiu)*diem_quydoi)/100 AS diem, ((length(so_chon) = 5) * ");
            stringBuilder.append(cursor1.getString(7));
            stringBuilder.append(" +(length(so_chon) = 8) * ");
            stringBuilder.append(cursor1.getString(8));
            stringBuilder.append(" +(length(so_chon) = 11) * ");
            stringBuilder.append(cursor1.getString(9));
            stringBuilder.append(" + sum(diem_dly_giu*diem_quydoi/100)) AS Om, SUm((type_kh =2)");
            if (this.spin_pointion > -1) {
                stringBuilder1 = new StringBuilder();
                stringBuilder1.append("*(tbl_soctS.ten_kh='");
                stringBuilder1.append(this.mwebsite.get(this.spin_pointion));
                stringBuilder1.append("')");
                str2 = stringBuilder1.toString();
            } else {
                str2 = "";
            }
            stringBuilder.append(str2);
            stringBuilder.append(" *diem) as chuyen , SUm((type_kh =1)*(100-diem_khachgiu-diem_dly_giu)*diem_quydoi/100)-SUm((type_kh =2)*diem) -  ((length(so_chon) = 5) * ");
            stringBuilder.append(cursor1.getString(7));
            stringBuilder.append(" +(length(so_chon) = 8) * ");
            stringBuilder.append(cursor1.getString(8));
            stringBuilder.append(" +(length(so_chon) = 11) * ");
            stringBuilder.append(cursor1.getString(9));
            stringBuilder.append(") AS ton, so_nhay   From tbl_soctS Where ngay_nhan='");
            stringBuilder.append(str1);
            stringBuilder.append("' AND the_loai='xi' AND");
            stringBuilder.append(this.lay_xien);
            stringBuilder.append("  GROUP by so_chon Order by ton DESC, diem DESC");
            str1 = stringBuilder.toString();
            str2 = str1;
            if (cursor1 != null) {
                str2 = str1;
                if (!cursor1.isClosed()) {
                    cursor1.close();
                    str2 = str1;
                }
            }
        }
        Cursor cursor = this.db.GetData(str2);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        if (this.spin_pointion > -1) {
            Database database = this.db;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select * from tbl_chaytrang_acc Where Username = '");
            stringBuilder.append(this.mwebsite.get(this.spin_pointion));
            stringBuilder.append("'");
            Cursor cursor1 = database.GetData(stringBuilder.toString());
            cursor1.moveToFirst();
            try {
                JSONObject jSONObject = new JSONObject();
                this(cursor1.getString(2));
                int i = this.GameType;
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    switch (i) {
                                        case 23:
                                            this.myMax = decimalFormat.format(jSONObject.getInt("max_dec"));
                                            this.MaxChay = jSONObject.getInt("gia_dec");
                                            break;
                                        case 22:
                                            this.myMax = decimalFormat.format(jSONObject.getInt("max_ded"));
                                            this.MaxChay = jSONObject.getInt("gia_ded");
                                            break;
                                        case 21:
                                            this.myMax = decimalFormat.format(jSONObject.getInt("max_dea"));
                                            this.MaxChay = jSONObject.getInt("gia_dea");
                                            break;
                                        case 20:
                                            this.myMax = decimalFormat.format(jSONObject.getInt("max_lo"));
                                            this.MaxChay = jSONObject.getInt("gia_lo");
                                            break;
                                    }
                                } else {
                                    this.myMax = decimalFormat.format(jSONObject.getInt("max_xi4"));
                                    if (jSONObject.has("gia_xi4")) {
                                        i = jSONObject.getInt("gia_xi4");
                                    } else {
                                        i = 450;
                                    }
                                    this.MaxChay = i;
                                }
                            } else {
                                this.myMax = decimalFormat.format(jSONObject.getInt("max_xi3"));
                                if (jSONObject.has("gia_xi3")) {
                                    i = jSONObject.getInt("gia_xi3");
                                } else {
                                    i = 520;
                                }
                                this.MaxChay = i;
                            }
                        } else {
                            this.myMax = decimalFormat.format(jSONObject.getInt("max_xi2"));
                            if (jSONObject.has("gia_xi2")) {
                                i = jSONObject.getInt("gia_xi2");
                            } else {
                                i = 560;
                            }
                            this.MaxChay = i;
                        }
                    } else {

                    }
                } else {
                    this.myMax = decimalFormat.format(jSONObject.getInt("max_deb"));
                    this.MaxChay = jSONObject.getInt("gia_deb");
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            } finally {}
            cursor1.close();
        } else {
            this.myMax = "0";
        }
        if (cursor != null) {
            if (this.radio_xi.isChecked()) {
                try {
                    while (cursor.moveToNext()) {
                        this.mSo.add(cursor.getString(0));
                        this.mTienNhan.add(decimalFormat.format(cursor.getInt(1)));
                        this.mTienOm.add(decimalFormat.format(cursor.getInt(2)));
                        this.mTienchuyen.add(decimalFormat.format(cursor.getInt(3)));
                        this.mTienTon.add(decimalFormat.format(cursor.getInt(4)));
                        this.mNhay.add(Integer.valueOf(cursor.getInt(5)));
                        this.mMax.add(this.myMax);
                        boolean bool = this.radio_xi2.isChecked();
                        if (bool) {
                            int i;
                            int j;
                            String[] arrayOfString = cursor.getString(0).split(",");
                            if (arrayOfString.length < 2)
                                continue;
                            if (!this.jsonGia.has(arrayOfString[0])) {
                                i = this.Price;
                            } else {
                                i = this.Price + this.jsonGia.getInt(arrayOfString[0]);
                            }
                            if (!this.jsonGia.has(arrayOfString[1])) {
                                j = this.Price;
                            } else {
                                j = this.Price + this.jsonGia.getInt(arrayOfString[1]);
                            }
                            List<String> list = this.mGia;
                            StringBuilder stringBuilder = new StringBuilder();
                            this();
                            stringBuilder.append("");
                            stringBuilder.append((i + j) / 2);
                            list.add(stringBuilder.toString());
                            this.jsonTienxien.put(cursor.getString(0), (i + j) / 2);
                        }
                        if (this.radio_xi3.isChecked()) {
                            int i;
                            int j;
                            int k;
                            String[] arrayOfString = cursor.getString(0).split(",");
                            if (arrayOfString.length < 3)
                                continue;
                            if (!this.jsonGia.has(arrayOfString[0])) {
                                i = this.Price;
                            } else {
                                i = this.Price + this.jsonGia.getInt(arrayOfString[0]);
                            }
                            if (!this.jsonGia.has(arrayOfString[1])) {
                                j = this.Price;
                            } else {
                                j = this.Price + this.jsonGia.getInt(arrayOfString[1]);
                            }
                            if (!this.jsonGia.has(arrayOfString[2])) {
                                k = this.Price;
                            } else {
                                k = this.Price + this.jsonGia.getInt(arrayOfString[2]);
                            }
                            List<String> list = this.mGia;
                            StringBuilder stringBuilder = new StringBuilder();
                            this();
                            stringBuilder.append("");
                            stringBuilder.append((i + j + k) / 3);
                            list.add(stringBuilder.toString());
                            this.jsonTienxien.put(cursor.getString(0), (i + j + k) / 3);
                        }
                        if (this.radio_xi4.isChecked()) {
                            int i;
                            int j;
                            int k;
                            int m;
                            String[] arrayOfString = cursor.getString(0).split(",");
                            if (arrayOfString.length < 4)
                                continue;
                            if (!this.jsonGia.has(arrayOfString[0])) {
                                i = this.Price;
                            } else {
                                i = this.Price + this.jsonGia.getInt(arrayOfString[0]);
                            }
                            if (!this.jsonGia.has(arrayOfString[1])) {
                                j = this.Price;
                            } else {
                                j = this.Price + this.jsonGia.getInt(arrayOfString[1]);
                            }
                            if (!this.jsonGia.has(arrayOfString[2])) {
                                k = this.Price;
                            } else {
                                k = this.Price + this.jsonGia.getInt(arrayOfString[2]);
                            }
                            if (!this.jsonGia.has(arrayOfString[3])) {
                                m = this.Price;
                            } else {
                                m = this.Price + this.jsonGia.getInt(arrayOfString[3]);
                            }
                            List<String> list = this.mGia;
                            StringBuilder stringBuilder = new StringBuilder();
                            this();
                            stringBuilder.append("");
                            stringBuilder.append((i + j + k + m) / 4);
                            list.add(stringBuilder.toString());
                            this.jsonTienxien.put(cursor.getString(0), (i + j + k + m) / 4);
                        }
                    }
                } catch (JSONException jSONException) {
                    jSONException.printStackTrace();
                }
            } else {
                while (cursor.moveToNext()) {
                    this.mSo.add(cursor.getString(0));
                    this.mTienNhan.add(jSONException.format(cursor.getInt(1)));
                    this.mTienOm.add(jSONException.format(cursor.getInt(2)));
                    this.mTienchuyen.add(jSONException.format(cursor.getInt(3)));
                    this.mTienTon.add(jSONException.format(cursor.getInt(4)));
                    this.mNhay.add(Integer.valueOf(cursor.getInt(5)));
                    if (this.jsonGia.has(cursor.getString(0))) {
                        try {
                            List<String> list = this.mGia;
                            StringBuilder stringBuilder = new StringBuilder();
                            this();
                            stringBuilder.append("");
                            stringBuilder.append(this.Price + this.jsonGia.getInt(cursor.getString(0)));
                            list.add(stringBuilder.toString());
                        } catch (JSONException jSONException1) {
                            jSONException1.printStackTrace();
                        }
                    } else {
                        List<String> list = this.mGia;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(this.Price);
                        stringBuilder.append("");
                        list.add(stringBuilder.toString());
                    }
                    this.mMax.add(this.myMax);
                }
            }
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        if (getActivity() != null)
            this.lview.setAdapter((ListAdapter)new So_OmAdapter((Context)getActivity(), 2131427395, this.mSo));
    }

    public void xemlv() {
        if (this.DangXuat != null) {
            xem_RecycView();
        } else {
            this.radio_de.setChecked(true);
        }
    }

    class Ma_da_chay extends ArrayAdapter {
        public Ma_da_chay(Context param1Context, int param1Int, List<String> param1List) {
            super(param1Context, param1Int, param1List);
        }

        public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
            ViewHolder viewHolder;
            param1View = null;
            if (!false) {
                param1View = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427403, null);
                viewHolder = new ViewHolder();
                viewHolder.tv_SoTin = (TextView)param1View.findViewById(2131231383);
                viewHolder.tv_NoiDung = (TextView)param1View.findViewById(2131231381);
                viewHolder.tv_ThoiGian = (TextView)param1View.findViewById(2131231387);
                viewHolder.tv_TienCuoc = (TextView)param1View.findViewById(2131231388);
                viewHolder.tv_HuyCuoc = (TextView)param1View.findViewById(2131231359);
            } else {
                throw new NullPointerException();
            }
            viewHolder.tv_HuyCuoc.setFocusable(false);
            viewHolder.tv_HuyCuoc.setFocusableInTouchMode(false);
            viewHolder.tv_HuyCuoc.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param2View) {}
            });
            if (((Integer)Frag_Chaytrang.this.TheLoai.get(param1Int)).intValue() == 0) {
                TextView textView1 = viewHolder.tv_NoiDung;
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("");
                stringBuilder1.append(Frag_Chaytrang.this.NoiDung.get(param1Int));
                textView1.setText(stringBuilder1.toString());
            } else if (((Integer)Frag_Chaytrang.this.TheLoai.get(param1Int)).intValue() == 1) {
                TextView textView1 = viewHolder.tv_NoiDung;
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("L");
                stringBuilder1.append(Frag_Chaytrang.this.NoiDung.get(param1Int));
                textView1.setText(stringBuilder1.toString());
            } else if (((Integer)Frag_Chaytrang.this.TheLoai.get(param1Int)).intValue() == 2) {
                TextView textView1 = viewHolder.tv_NoiDung;
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("Xi2: ");
                stringBuilder1.append(Frag_Chaytrang.this.NoiDung.get(param1Int));
                textView1.setText(stringBuilder1.toString());
            } else if (((Integer)Frag_Chaytrang.this.TheLoai.get(param1Int)).intValue() == 3) {
                TextView textView1 = viewHolder.tv_NoiDung;
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("Xi3: ");
                stringBuilder1.append(Frag_Chaytrang.this.NoiDung.get(param1Int));
                textView1.setText(stringBuilder1.toString());
            } else if (((Integer)Frag_Chaytrang.this.TheLoai.get(param1Int)).intValue() == 4) {
                TextView textView1 = viewHolder.tv_NoiDung;
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("Xi4: ");
                stringBuilder1.append(Frag_Chaytrang.this.NoiDung.get(param1Int));
                textView1.setText(stringBuilder1.toString());
            } else if (((Integer)Frag_Chaytrang.this.TheLoai.get(param1Int)).intValue() == 20) {
                TextView textView1 = viewHolder.tv_NoiDung;
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("LLive: ");
                stringBuilder1.append(Frag_Chaytrang.this.NoiDung.get(param1Int));
                textView1.setText(stringBuilder1.toString());
            } else if (((Integer)Frag_Chaytrang.this.TheLoai.get(param1Int)).intValue() == 21) {
                TextView textView1 = viewHolder.tv_NoiDung;
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("");
                stringBuilder1.append(Frag_Chaytrang.this.NoiDung.get(param1Int));
                textView1.setText(stringBuilder1.toString());
            } else if (((Integer)Frag_Chaytrang.this.TheLoai.get(param1Int)).intValue() == 22) {
                TextView textView1 = viewHolder.tv_NoiDung;
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("Ginh");
                stringBuilder1.append(Frag_Chaytrang.this.NoiDung.get(param1Int));
                textView1.setText(stringBuilder1.toString());
            } else if (((Integer)Frag_Chaytrang.this.TheLoai.get(param1Int)).intValue() == 23) {
                TextView textView1 = viewHolder.tv_NoiDung;
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("ginh");
                stringBuilder1.append(Frag_Chaytrang.this.NoiDung.get(param1Int));
                textView1.setText(stringBuilder1.toString());
            }
            TextView textView = viewHolder.tv_ThoiGian;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Time: ");
            stringBuilder.append(Frag_Chaytrang.this.ThoiGian.get(param1Int));
            textView.setText(stringBuilder.toString());
            textView = viewHolder.tv_TienCuoc;
            stringBuilder = new StringBuilder();
            stringBuilder.append("T");
            stringBuilder.append(Frag_Chaytrang.this.TienCuoc.get(param1Int));
            textView.setText(stringBuilder.toString());
            viewHolder.tv_SoTin.setText(Frag_Chaytrang.this.SoTin.get(param1Int));
            if (((Integer)Frag_Chaytrang.this.HuyCuoc.get(param1Int)).intValue() == 0) {
                viewHolder.tv_HuyCuoc.setTextColor(-7829368);
                viewHolder.tv_HuyCuoc.setEnabled(false);
                viewHolder.tv_HuyCuoc.setText("hu);
            } else {
                viewHolder.tv_HuyCuoc.setVisibility(8);
            }
            return param1View;
        }

        class ViewHolder {
            TextView tv_HuyCuoc;

            TextView tv_NoiDung;

            TextView tv_SoTin;

            TextView tv_ThoiGian;

            TextView tv_TienCuoc;
        }
    }

    class null implements View.OnClickListener {
        public void onClick(View param1View) {}
    }

    class ViewHolder {
        TextView tv_HuyCuoc;

        TextView tv_NoiDung;

        TextView tv_SoTin;

        TextView tv_ThoiGian;

        TextView tv_TienCuoc;
    }

    class So_OmAdapter extends ArrayAdapter {
        public So_OmAdapter(Context param1Context, int param1Int, List<String> param1List) {
            super(param1Context, param1Int, param1List);
        }

        public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
            ViewHolder viewHolder1;
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService("layout_inflater");
            ViewHolder viewHolder2 = new ViewHolder();
            if (param1View == null) {
                View view2 = layoutInflater.inflate(2131427395, null);
                viewHolder2.tview2 = (TextView)view2.findViewById(2131231286);
                viewHolder2.tview5 = (TextView)view2.findViewById(2131230742);
                viewHolder2.tview7 = (TextView)view2.findViewById(2131231439);
                viewHolder2.tview8 = (TextView)view2.findViewById(2131231440);
                viewHolder2.tview1 = (TextView)view2.findViewById(2131231424);
                viewHolder2.tview4 = (TextView)view2.findViewById(2131231441);
                view2.setTag(viewHolder2);
                viewHolder1 = viewHolder2;
                View view1 = view2;
            } else {
                ViewHolder viewHolder = (ViewHolder)viewHolder1.getTag();
                viewHolder2 = viewHolder1;
                viewHolder1 = viewHolder;
            }
            if (((Integer)Frag_Chaytrang.this.mNhay.get(param1Int)).intValue() > 0) {
                viewHolder1.tview5.setTextColor(-65536);
                viewHolder1.tview7.setTextColor(-65536);
                viewHolder1.tview8.setTextColor(-65536);
                viewHolder1.tview1.setTextColor(-65536);
                viewHolder1.tview4.setTextColor(-65536);
                if (((Integer)Frag_Chaytrang.this.mNhay.get(param1Int)).intValue() == 1) {
                    TextView textView1 = viewHolder1.tview5;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Frag_Chaytrang.this.mSo.get(param1Int));
                    stringBuilder1.append("*");
                    textView1.setText(stringBuilder1.toString());
                } else if (((Integer)Frag_Chaytrang.this.mNhay.get(param1Int)).intValue() == 2) {
                    TextView textView1 = viewHolder1.tview5;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Frag_Chaytrang.this.mSo.get(param1Int));
                    stringBuilder1.append("**");
                    textView1.setText(stringBuilder1.toString());
                } else if (((Integer)Frag_Chaytrang.this.mNhay.get(param1Int)).intValue() == 3) {
                    TextView textView1 = viewHolder1.tview5;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Frag_Chaytrang.this.mSo.get(param1Int));
                    stringBuilder1.append("***");
                    textView1.setText(stringBuilder1.toString());
                } else if (((Integer)Frag_Chaytrang.this.mNhay.get(param1Int)).intValue() == 4) {
                    TextView textView1 = viewHolder1.tview5;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Frag_Chaytrang.this.mSo.get(param1Int));
                    stringBuilder1.append("****");
                    textView1.setText(stringBuilder1.toString());
                } else if (((Integer)Frag_Chaytrang.this.mNhay.get(param1Int)).intValue() == 5) {
                    TextView textView1 = viewHolder1.tview5;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Frag_Chaytrang.this.mSo.get(param1Int));
                    stringBuilder1.append("*****");
                    textView1.setText(stringBuilder1.toString());
                } else if (((Integer)Frag_Chaytrang.this.mNhay.get(param1Int)).intValue() == 6) {
                    TextView textView1 = viewHolder1.tview5;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Frag_Chaytrang.this.mSo.get(param1Int));
                    stringBuilder1.append("******");
                    textView1.setText(stringBuilder1.toString());
                }
                TextView textView = viewHolder1.tview2;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(param1Int + 1);
                stringBuilder.append("");
                textView.setText(stringBuilder.toString());
                viewHolder1.tview7.setText(Frag_Chaytrang.this.mTienTon.get(param1Int));
                viewHolder1.tview8.setText("0");
                viewHolder1.tview1.setText(Frag_Chaytrang.this.mMax.get(param1Int));
                viewHolder1.tview4.setText(Frag_Chaytrang.this.mGia.get(param1Int));
            } else {
                viewHolder1.tview5.setTextColor(-16777216);
                viewHolder1.tview7.setTextColor(-16777216);
                viewHolder1.tview8.setTextColor(-16777216);
                viewHolder1.tview1.setTextColor(-16777216);
                viewHolder1.tview4.setTextColor(-16777216);
                TextView textView = viewHolder1.tview2;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(param1Int + 1);
                stringBuilder.append("");
                textView.setText(stringBuilder.toString());
                viewHolder1.tview5.setText(Frag_Chaytrang.this.mSo.get(param1Int));
                viewHolder1.tview7.setText(Frag_Chaytrang.this.mTienTon.get(param1Int));
                viewHolder1.tview8.setText(Frag_Chaytrang.this.mTienchuyen.get(param1Int));
                viewHolder1.tview1.setText(Frag_Chaytrang.this.mMax.get(param1Int));
                if (Frag_Chaytrang.this.mGia.size() > 0) {
                    viewHolder1.tview4.setText(Frag_Chaytrang.this.mGia.get(param1Int));
                    if (Integer.parseInt((String)Frag_Chaytrang.this.mGia.get(param1Int)) > Frag_Chaytrang.this.Price)
                        viewHolder1.tview4.setTextColor(-65536);
                }
            }
            return (View)viewHolder2;
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
