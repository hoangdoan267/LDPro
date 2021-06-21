package tamhoang.ldpro4;

import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import java.util.ArrayList;
import org.json.JSONObject;
import tamhoang.ldpro4.data.Contact;
import tamhoang.ldpro4.data.Database;

public class NotificationReader extends NotificationListenerService {
    public static final String VIBER = "com.viber.voip";

    public static final String WHATSAPP = "com.whatsapp";

    public static final String ZALO = "com.zing.zalo";

    static boolean replied;

    String ID = "";

    String Ten_KH;

    private ArrayList<NotificationCompat.Action> actions;

    String body = "";

    JSONObject caidat_tg;

    Context context;

    Database db;

    JSONObject json;

    String mWhat = "";

    int soTN;

    public void NotificationWearReader(String paramString1, String paramString2) {
        int i = MainActivity.arr_TenKH.indexOf(paramString1);
        if (i > -1)
            try {
                RemoteInput[] arrayOfRemoteInput = new RemoteInput[1];
                Intent intent = new Intent();
                this();
                Bundle bundle = ((Contact)MainActivity.contactslist.get(i)).remoteExtras;
                arrayOfRemoteInput[0] = ((Contact)MainActivity.contactslist.get(i)).remoteInput;
                if (Build.VERSION.SDK_INT >= 20) {
                    bundle.putCharSequence(arrayOfRemoteInput[0].getResultKey(), paramString2);
                    RemoteInput.addResultsToIntent(arrayOfRemoteInput, intent, bundle);
                }
                ((Contact)MainActivity.contactslist.get(i)).pendingIntent.send((Context)MainActivity.Notifi, 0, intent);
                if (MainActivity.Json_Tinnhan.has(paramString1))
                    MainActivity.Json_Tinnhan.remove(paramString1);
            } catch (android.app.PendingIntent.CanceledException canceledException) {
                canceledException.printStackTrace();
            } catch (Exception exception) {
                exception.getMessage();
            }
    }

    public void onCreate() {
        super.onCreate();
        this.actions = new ArrayList<NotificationCompat.Action>();
        this.db = new Database(getBaseContext());
    }

    public void onNotificationPosted(StatusBarNotification paramStatusBarNotification) {
        // Byte code:
        //   0: aload_1
        //   1: invokevirtual getPackageName : ()Ljava/lang/String;
        //   4: ldc 'com.zing.zalo'
        //   6: invokevirtual equals : (Ljava/lang/Object;)Z
        //   9: ifne -> 36
        //   12: aload_1
        //   13: invokevirtual getPackageName : ()Ljava/lang/String;
        //   16: ldc 'com.whatsapp'
        //   18: invokevirtual equals : (Ljava/lang/Object;)Z
        //   21: ifne -> 36
        //   24: aload_1
        //   25: invokevirtual getPackageName : ()Ljava/lang/String;
        //   28: ldc 'com.viber.voip'
        //   30: invokevirtual equals : (Ljava/lang/Object;)Z
        //   33: ifeq -> 4399
        //   36: aload_0
        //   37: getfield context : Landroid/content/Context;
        //   40: ifnonnull -> 48
        //   43: aload_0
        //   44: aload_0
        //   45: putfield context : Landroid/content/Context;
        //   48: invokestatic getInstance : ()Ljava/util/Calendar;
        //   51: astore_2
        //   52: aload_2
        //   53: new java/util/Date
        //   56: dup
        //   57: invokespecial <init> : ()V
        //   60: invokevirtual setTime : (Ljava/util/Date;)V
        //   63: new java/text/SimpleDateFormat
        //   66: dup
        //   67: ldc 'yyyy-MM-dd'
        //   69: invokespecial <init> : (Ljava/lang/String;)V
        //   72: astore_3
        //   73: new java/text/SimpleDateFormat
        //   76: dup
        //   77: ldc 'HH:mm:ss'
        //   79: invokespecial <init> : (Ljava/lang/String;)V
        //   82: astore #4
        //   84: aload_3
        //   85: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   88: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   91: aload #4
        //   93: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   96: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   99: aload_3
        //   100: aload_2
        //   101: invokevirtual getTime : ()Ljava/util/Date;
        //   104: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
        //   107: astore #5
        //   109: aload #4
        //   111: aload_2
        //   112: invokevirtual getTime : ()Ljava/util/Date;
        //   115: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
        //   118: astore #6
        //   120: aload_0
        //   121: ldc ''
        //   123: putfield ID : Ljava/lang/String;
        //   126: aload_1
        //   127: invokevirtual getNotification : ()Landroid/app/Notification;
        //   130: getfield extras : Landroid/os/Bundle;
        //   133: astore #7
        //   135: aload #7
        //   137: ldc 'android.text'
        //   139: invokevirtual getCharSequence : (Ljava/lang/String;)Ljava/lang/CharSequence;
        //   142: invokeinterface toString : ()Ljava/lang/String;
        //   147: astore #8
        //   149: aload_1
        //   150: invokevirtual getNotification : ()Landroid/app/Notification;
        //   153: astore #9
        //   155: aload #7
        //   157: ldc 'android.title'
        //   159: invokevirtual getCharSequence : (Ljava/lang/String;)Ljava/lang/CharSequence;
        //   162: invokeinterface toString : ()Ljava/lang/String;
        //   167: astore #10
        //   169: aload_1
        //   170: invokevirtual getPackageName : ()Ljava/lang/String;
        //   173: ldc 'com.zing.zalo'
        //   175: invokevirtual equals : (Ljava/lang/Object;)Z
        //   178: istore #11
        //   180: iload #11
        //   182: ifeq -> 840
        //   185: aload #8
        //   187: ldc 'tin nhch
        //   189: invokevirtual indexOf : (Ljava/lang/String;)I
        //   192: iconst_m1
        //   193: if_icmpne -> 833
        //   196: aload #8
        //   198: ldc 'gttin cho b
        //   200: invokevirtual indexOf : (Ljava/lang/String;)I
        //   203: iconst_m1
        //   204: if_icmpne -> 830
        //   207: aload #8
        //   209: ldc 'cxvtin nhbg
        //   211: invokevirtual indexOf : (Ljava/lang/String;)I
        //   214: iconst_m1
        //   215: if_icmpne -> 827
        //   218: aload #8
        //   220: ldc 'hcho b
        //   222: invokevirtual indexOf : (Ljava/lang/String;)I
        //   225: iconst_m1
        //   226: if_icmpne -> 824
        //   229: aload #8
        //   231: ldc 'thvicnh
        //   233: invokevirtual indexOf : (Ljava/lang/String;)I
        //   236: iconst_m1
        //   237: if_icmpne -> 821
        //   240: aload #8
        //   242: ldc 'thvnh
        //   244: invokevirtual indexOf : (Ljava/lang/String;)I
        //   247: iconst_m1
        //   248: if_icmpne -> 818
        //   251: aload #8
        //   253: ldc 'gcho b
        //   255: invokevirtual indexOf : (Ljava/lang/String;)I
        //   258: iconst_m1
        //   259: if_icmpne -> 815
        //   262: aload #8
        //   264: ldc 'cutrchuy
        //   266: invokevirtual indexOf : (Ljava/lang/String;)I
        //   269: iconst_m1
        //   270: if_icmpne -> 812
        //   273: aload #7
        //   275: ldc 'android.title'
        //   277: invokevirtual getCharSequence : (Ljava/lang/String;)Ljava/lang/CharSequence;
        //   280: invokeinterface toString : ()Ljava/lang/String;
        //   285: astore_2
        //   286: aload_0
        //   287: aload_2
        //   288: putfield ID : Ljava/lang/String;
        //   291: aload_2
        //   292: ldc ' ('
        //   294: invokevirtual indexOf : (Ljava/lang/String;)I
        //   297: iconst_m1
        //   298: if_icmple -> 334
        //   301: aload_0
        //   302: getfield ID : Ljava/lang/String;
        //   305: astore_2
        //   306: aload_0
        //   307: getfield ID : Ljava/lang/String;
        //   310: astore #4
        //   312: aload_0
        //   313: aload_2
        //   314: iconst_0
        //   315: aload #4
        //   317: ldc '('
        //   319: invokevirtual indexOf : (Ljava/lang/String;)I
        //   322: invokevirtual substring : (II)Ljava/lang/String;
        //   325: invokevirtual trim : ()Ljava/lang/String;
        //   328: putfield ID : Ljava/lang/String;
        //   331: goto -> 334
        //   334: aload #8
        //   336: astore_2
        //   337: aload #10
        //   339: ldc_w 'Nh
        //   342: invokevirtual indexOf : (Ljava/lang/String;)I
        //   345: ifne -> 393
        //   348: aload_0
        //   349: aload_0
        //   350: getfield ID : Ljava/lang/String;
        //   353: aload_0
        //   354: getfield ID : Ljava/lang/String;
        //   357: ldc_w ':'
        //   360: invokevirtual indexOf : (Ljava/lang/String;)I
        //   363: iconst_2
        //   364: iadd
        //   365: invokevirtual substring : (I)Ljava/lang/String;
        //   368: invokevirtual trim : ()Ljava/lang/String;
        //   371: putfield ID : Ljava/lang/String;
        //   374: aload #8
        //   376: aload #8
        //   378: ldc_w ':'
        //   381: invokevirtual indexOf : (Ljava/lang/String;)I
        //   384: iconst_1
        //   385: iadd
        //   386: invokevirtual substring : (I)Ljava/lang/String;
        //   389: invokevirtual trim : ()Ljava/lang/String;
        //   392: astore_2
        //   393: aload_2
        //   394: ldc_w '''
        //   397: ldc ''
        //   399: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   402: astore #8
        //   404: ldc_w 'ZL'
        //   407: astore_2
        //   408: aload_2
        //   409: astore #4
        //   411: getstatic tamhoang/ldpro4/MainActivity.Json_Tinnhan : Lorg/json/JSONObject;
        //   414: astore_3
        //   415: aload_2
        //   416: astore #4
        //   418: new java/lang/StringBuilder
        //   421: astore #12
        //   423: aload_2
        //   424: astore #4
        //   426: aload #12
        //   428: invokespecial <init> : ()V
        //   431: aload_2
        //   432: astore #4
        //   434: aload #12
        //   436: ldc_w 'ZL'
        //   439: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   442: pop
        //   443: aload_2
        //   444: astore #4
        //   446: aload #12
        //   448: ldc_w ' - '
        //   451: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   454: pop
        //   455: aload_2
        //   456: astore #4
        //   458: aload #12
        //   460: aload_0
        //   461: getfield ID : Ljava/lang/String;
        //   464: invokevirtual trim : ()Ljava/lang/String;
        //   467: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   470: pop
        //   471: aload_2
        //   472: astore #4
        //   474: aload_3
        //   475: aload #12
        //   477: invokevirtual toString : ()Ljava/lang/String;
        //   480: invokevirtual has : (Ljava/lang/String;)Z
        //   483: ifeq -> 695
        //   486: aload_2
        //   487: astore #4
        //   489: new org/json/JSONObject
        //   492: astore_3
        //   493: aload_2
        //   494: astore #4
        //   496: getstatic tamhoang/ldpro4/MainActivity.Json_Tinnhan : Lorg/json/JSONObject;
        //   499: astore #13
        //   501: aload_2
        //   502: astore #4
        //   504: new java/lang/StringBuilder
        //   507: astore #12
        //   509: aload_2
        //   510: astore #4
        //   512: aload #12
        //   514: invokespecial <init> : ()V
        //   517: aload_2
        //   518: astore #4
        //   520: aload #12
        //   522: ldc_w 'ZL'
        //   525: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   528: pop
        //   529: aload_2
        //   530: astore #4
        //   532: aload #12
        //   534: ldc_w ' - '
        //   537: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   540: pop
        //   541: aload_2
        //   542: astore #4
        //   544: aload #12
        //   546: aload_0
        //   547: getfield ID : Ljava/lang/String;
        //   550: invokevirtual trim : ()Ljava/lang/String;
        //   553: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   556: pop
        //   557: aload_2
        //   558: astore #4
        //   560: aload_3
        //   561: aload #13
        //   563: aload #12
        //   565: invokevirtual toString : ()Ljava/lang/String;
        //   568: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   571: invokespecial <init> : (Ljava/lang/String;)V
        //   574: aload_2
        //   575: astore #4
        //   577: aload_3
        //   578: aload #8
        //   580: invokevirtual trim : ()Ljava/lang/String;
        //   583: invokevirtual has : (Ljava/lang/String;)Z
        //   586: ifeq -> 594
        //   589: aconst_null
        //   590: astore_2
        //   591: goto -> 692
        //   594: aload_2
        //   595: astore #4
        //   597: aload_3
        //   598: aload #8
        //   600: invokevirtual trim : ()Ljava/lang/String;
        //   603: ldc_w 'OK'
        //   606: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   609: pop
        //   610: aload_2
        //   611: astore #4
        //   613: getstatic tamhoang/ldpro4/MainActivity.Json_Tinnhan : Lorg/json/JSONObject;
        //   616: astore #13
        //   618: aload_2
        //   619: astore #4
        //   621: new java/lang/StringBuilder
        //   624: astore #12
        //   626: aload_2
        //   627: astore #4
        //   629: aload #12
        //   631: invokespecial <init> : ()V
        //   634: aload_2
        //   635: astore #4
        //   637: aload #12
        //   639: ldc_w 'ZL'
        //   642: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   645: pop
        //   646: aload_2
        //   647: astore #4
        //   649: aload #12
        //   651: ldc_w ' - '
        //   654: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   657: pop
        //   658: aload_2
        //   659: astore #4
        //   661: aload #12
        //   663: aload_0
        //   664: getfield ID : Ljava/lang/String;
        //   667: invokevirtual trim : ()Ljava/lang/String;
        //   670: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   673: pop
        //   674: aload_2
        //   675: astore #4
        //   677: aload #13
        //   679: aload #12
        //   681: invokevirtual toString : ()Ljava/lang/String;
        //   684: aload_3
        //   685: invokevirtual toString : ()Ljava/lang/String;
        //   688: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   691: pop
        //   692: goto -> 842
        //   695: aload_2
        //   696: astore #4
        //   698: new org/json/JSONObject
        //   701: astore #13
        //   703: aload_2
        //   704: astore #4
        //   706: aload #13
        //   708: invokespecial <init> : ()V
        //   711: aload_2
        //   712: astore #4
        //   714: aload #13
        //   716: aload #8
        //   718: invokevirtual trim : ()Ljava/lang/String;
        //   721: ldc_w 'OK'
        //   724: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   727: pop
        //   728: aload_2
        //   729: astore #4
        //   731: getstatic tamhoang/ldpro4/MainActivity.Json_Tinnhan : Lorg/json/JSONObject;
        //   734: astore #12
        //   736: aload_2
        //   737: astore #4
        //   739: new java/lang/StringBuilder
        //   742: astore_3
        //   743: aload_2
        //   744: astore #4
        //   746: aload_3
        //   747: invokespecial <init> : ()V
        //   750: aload_2
        //   751: astore #4
        //   753: aload_3
        //   754: ldc_w 'ZL'
        //   757: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   760: pop
        //   761: aload_2
        //   762: astore #4
        //   764: aload_3
        //   765: ldc_w ' - '
        //   768: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   771: pop
        //   772: aload_2
        //   773: astore #4
        //   775: aload_3
        //   776: aload_0
        //   777: getfield ID : Ljava/lang/String;
        //   780: invokevirtual trim : ()Ljava/lang/String;
        //   783: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   786: pop
        //   787: aload_2
        //   788: astore #4
        //   790: aload #12
        //   792: aload_3
        //   793: invokevirtual toString : ()Ljava/lang/String;
        //   796: aload #13
        //   798: invokevirtual toString : ()Ljava/lang/String;
        //   801: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   804: pop
        //   805: goto -> 842
        //   808: astore_1
        //   809: goto -> 4399
        //   812: goto -> 840
        //   815: goto -> 840
        //   818: goto -> 840
        //   821: goto -> 840
        //   824: goto -> 840
        //   827: goto -> 840
        //   830: goto -> 840
        //   833: goto -> 840
        //   836: astore_1
        //   837: goto -> 4399
        //   840: aconst_null
        //   841: astore_2
        //   842: aload_1
        //   843: invokevirtual getPackageName : ()Ljava/lang/String;
        //   846: ldc 'com.viber.voip'
        //   848: invokevirtual equals : (Ljava/lang/Object;)Z
        //   851: istore #11
        //   853: iload #11
        //   855: ifeq -> 1412
        //   858: aload_2
        //   859: astore #4
        //   861: aload #8
        //   863: ldc_w 'Bcctin nh
        //   866: invokevirtual indexOf : (Ljava/lang/String;)I
        //   869: iconst_m1
        //   870: if_icmpne -> 1412
        //   873: aload_2
        //   874: astore #4
        //   876: aload #8
        //   878: ldc_w 'thbv
        //   881: invokevirtual indexOf : (Ljava/lang/String;)I
        //   884: iconst_m1
        //   885: if_icmpne -> 1412
        //   888: aload_2
        //   889: astore #4
        //   891: aload #8
        //   893: ldc_w 'ug
        //   896: invokevirtual indexOf : (Ljava/lang/String;)I
        //   899: iconst_m1
        //   900: if_icmpne -> 1412
        //   903: aload_2
        //   904: astore #4
        //   906: aload #8
        //   908: ldc_w 'tin nhch
        //   911: invokevirtual indexOf : (Ljava/lang/String;)I
        //   914: iconst_m1
        //   915: if_icmpne -> 1412
        //   918: aload_2
        //   919: astore #4
        //   921: aload #7
        //   923: ldc 'android.title'
        //   925: invokevirtual getCharSequence : (Ljava/lang/String;)Ljava/lang/CharSequence;
        //   928: invokeinterface toString : ()Ljava/lang/String;
        //   933: astore_3
        //   934: aload_2
        //   935: astore #4
        //   937: aload_0
        //   938: aload_3
        //   939: putfield ID : Ljava/lang/String;
        //   942: aload_2
        //   943: astore #4
        //   945: aload_3
        //   946: ldc_w 'trong'
        //   949: invokevirtual indexOf : (Ljava/lang/String;)I
        //   952: iconst_m1
        //   953: if_icmple -> 983
        //   956: aload_2
        //   957: astore #4
        //   959: aload_0
        //   960: aload_0
        //   961: getfield ID : Ljava/lang/String;
        //   964: aload_0
        //   965: getfield ID : Ljava/lang/String;
        //   968: ldc_w 'trong'
        //   971: invokevirtual indexOf : (Ljava/lang/String;)I
        //   974: bipush #6
        //   976: iadd
        //   977: invokevirtual substring : (I)Ljava/lang/String;
        //   980: putfield ID : Ljava/lang/String;
        //   983: aload_2
        //   984: astore #4
        //   986: aload_0
        //   987: getfield ID : Ljava/lang/String;
        //   990: ldc_w 'tin nhch
        //   993: invokevirtual indexOf : (Ljava/lang/String;)I
        //   996: iconst_m1
        //   997: if_icmpne -> 1412
        //   1000: ldc_w 'VB'
        //   1003: astore #4
        //   1005: aload #4
        //   1007: astore_3
        //   1008: getstatic tamhoang/ldpro4/MainActivity.Json_Tinnhan : Lorg/json/JSONObject;
        //   1011: astore_2
        //   1012: aload #4
        //   1014: astore_3
        //   1015: new java/lang/StringBuilder
        //   1018: astore #12
        //   1020: aload #4
        //   1022: astore_3
        //   1023: aload #12
        //   1025: invokespecial <init> : ()V
        //   1028: aload #4
        //   1030: astore_3
        //   1031: aload #12
        //   1033: ldc_w 'VB'
        //   1036: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1039: pop
        //   1040: aload #4
        //   1042: astore_3
        //   1043: aload #12
        //   1045: ldc_w ' - '
        //   1048: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1051: pop
        //   1052: aload #4
        //   1054: astore_3
        //   1055: aload #12
        //   1057: aload_0
        //   1058: getfield ID : Ljava/lang/String;
        //   1061: invokevirtual trim : ()Ljava/lang/String;
        //   1064: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1067: pop
        //   1068: aload #4
        //   1070: astore_3
        //   1071: aload_2
        //   1072: aload #12
        //   1074: invokevirtual toString : ()Ljava/lang/String;
        //   1077: invokevirtual has : (Ljava/lang/String;)Z
        //   1080: ifeq -> 1293
        //   1083: aload #4
        //   1085: astore_3
        //   1086: new org/json/JSONObject
        //   1089: astore_2
        //   1090: aload #4
        //   1092: astore_3
        //   1093: getstatic tamhoang/ldpro4/MainActivity.Json_Tinnhan : Lorg/json/JSONObject;
        //   1096: astore #12
        //   1098: aload #4
        //   1100: astore_3
        //   1101: new java/lang/StringBuilder
        //   1104: astore #13
        //   1106: aload #4
        //   1108: astore_3
        //   1109: aload #13
        //   1111: invokespecial <init> : ()V
        //   1114: aload #4
        //   1116: astore_3
        //   1117: aload #13
        //   1119: ldc_w 'VB'
        //   1122: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1125: pop
        //   1126: aload #4
        //   1128: astore_3
        //   1129: aload #13
        //   1131: ldc_w ' - '
        //   1134: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1137: pop
        //   1138: aload #4
        //   1140: astore_3
        //   1141: aload #13
        //   1143: aload_0
        //   1144: getfield ID : Ljava/lang/String;
        //   1147: invokevirtual trim : ()Ljava/lang/String;
        //   1150: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1153: pop
        //   1154: aload #4
        //   1156: astore_3
        //   1157: aload_2
        //   1158: aload #12
        //   1160: aload #13
        //   1162: invokevirtual toString : ()Ljava/lang/String;
        //   1165: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1168: invokespecial <init> : (Ljava/lang/String;)V
        //   1171: aload #4
        //   1173: astore_3
        //   1174: aload_2
        //   1175: aload #8
        //   1177: invokevirtual trim : ()Ljava/lang/String;
        //   1180: invokevirtual has : (Ljava/lang/String;)Z
        //   1183: ifeq -> 1192
        //   1186: aconst_null
        //   1187: astore #4
        //   1189: goto -> 1290
        //   1192: aload #4
        //   1194: astore_3
        //   1195: aload_2
        //   1196: aload #8
        //   1198: invokevirtual trim : ()Ljava/lang/String;
        //   1201: ldc_w 'OK'
        //   1204: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1207: pop
        //   1208: aload #4
        //   1210: astore_3
        //   1211: getstatic tamhoang/ldpro4/MainActivity.Json_Tinnhan : Lorg/json/JSONObject;
        //   1214: astore #13
        //   1216: aload #4
        //   1218: astore_3
        //   1219: new java/lang/StringBuilder
        //   1222: astore #12
        //   1224: aload #4
        //   1226: astore_3
        //   1227: aload #12
        //   1229: invokespecial <init> : ()V
        //   1232: aload #4
        //   1234: astore_3
        //   1235: aload #12
        //   1237: ldc_w 'VB'
        //   1240: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1243: pop
        //   1244: aload #4
        //   1246: astore_3
        //   1247: aload #12
        //   1249: ldc_w ' - '
        //   1252: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1255: pop
        //   1256: aload #4
        //   1258: astore_3
        //   1259: aload #12
        //   1261: aload_0
        //   1262: getfield ID : Ljava/lang/String;
        //   1265: invokevirtual trim : ()Ljava/lang/String;
        //   1268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1271: pop
        //   1272: aload #4
        //   1274: astore_3
        //   1275: aload #13
        //   1277: aload #12
        //   1279: invokevirtual toString : ()Ljava/lang/String;
        //   1282: aload_2
        //   1283: invokevirtual toString : ()Ljava/lang/String;
        //   1286: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1289: pop
        //   1290: goto -> 1415
        //   1293: aload #4
        //   1295: astore_3
        //   1296: new org/json/JSONObject
        //   1299: astore_2
        //   1300: aload #4
        //   1302: astore_3
        //   1303: aload_2
        //   1304: invokespecial <init> : ()V
        //   1307: aload #4
        //   1309: astore_3
        //   1310: aload_2
        //   1311: aload #8
        //   1313: invokevirtual trim : ()Ljava/lang/String;
        //   1316: ldc_w 'OK'
        //   1319: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1322: pop
        //   1323: aload #4
        //   1325: astore_3
        //   1326: getstatic tamhoang/ldpro4/MainActivity.Json_Tinnhan : Lorg/json/JSONObject;
        //   1329: astore #13
        //   1331: aload #4
        //   1333: astore_3
        //   1334: new java/lang/StringBuilder
        //   1337: astore #12
        //   1339: aload #4
        //   1341: astore_3
        //   1342: aload #12
        //   1344: invokespecial <init> : ()V
        //   1347: aload #4
        //   1349: astore_3
        //   1350: aload #12
        //   1352: ldc_w 'VB'
        //   1355: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1358: pop
        //   1359: aload #4
        //   1361: astore_3
        //   1362: aload #12
        //   1364: ldc_w ' - '
        //   1367: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1370: pop
        //   1371: aload #4
        //   1373: astore_3
        //   1374: aload #12
        //   1376: aload_0
        //   1377: getfield ID : Ljava/lang/String;
        //   1380: invokevirtual trim : ()Ljava/lang/String;
        //   1383: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1386: pop
        //   1387: aload #4
        //   1389: astore_3
        //   1390: aload #13
        //   1392: aload #12
        //   1394: invokevirtual toString : ()Ljava/lang/String;
        //   1397: aload_2
        //   1398: invokevirtual toString : ()Ljava/lang/String;
        //   1401: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1404: pop
        //   1405: goto -> 1415
        //   1408: astore_1
        //   1409: goto -> 4399
        //   1412: aload_2
        //   1413: astore #4
        //   1415: aload #4
        //   1417: astore_3
        //   1418: aload_1
        //   1419: invokevirtual getPackageName : ()Ljava/lang/String;
        //   1422: ldc 'com.whatsapp'
        //   1424: invokevirtual equals : (Ljava/lang/Object;)Z
        //   1427: istore #11
        //   1429: aload #4
        //   1431: astore_2
        //   1432: iload #11
        //   1434: ifeq -> 2269
        //   1437: aload #4
        //   1439: astore_3
        //   1440: aload #4
        //   1442: astore_2
        //   1443: aload #8
        //   1445: ldc_w 'tin nh
        //   1448: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1451: iconst_m1
        //   1452: if_icmpne -> 2269
        //   1455: aload #4
        //   1457: astore_3
        //   1458: aload #7
        //   1460: ldc 'android.title'
        //   1462: invokevirtual getCharSequence : (Ljava/lang/String;)Ljava/lang/CharSequence;
        //   1465: invokeinterface toString : ()Ljava/lang/String;
        //   1470: astore_2
        //   1471: aload #4
        //   1473: astore_3
        //   1474: aload_0
        //   1475: aload_2
        //   1476: putfield ID : Ljava/lang/String;
        //   1479: aload #4
        //   1481: astore_3
        //   1482: aload_2
        //   1483: ldc_w ':'
        //   1486: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1489: iconst_m1
        //   1490: if_icmple -> 1546
        //   1493: aload #4
        //   1495: astore_3
        //   1496: aload_0
        //   1497: aload_0
        //   1498: getfield ID : Ljava/lang/String;
        //   1501: iconst_0
        //   1502: aload_0
        //   1503: getfield ID : Ljava/lang/String;
        //   1506: ldc_w ':'
        //   1509: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1512: invokevirtual substring : (II)Ljava/lang/String;
        //   1515: invokevirtual trim : ()Ljava/lang/String;
        //   1518: putfield ID : Ljava/lang/String;
        //   1521: aload #4
        //   1523: astore_3
        //   1524: aload_0
        //   1525: aload #10
        //   1527: aload #10
        //   1529: ldc_w ':'
        //   1532: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1535: iconst_1
        //   1536: iadd
        //   1537: invokevirtual substring : (I)Ljava/lang/String;
        //   1540: invokevirtual trim : ()Ljava/lang/String;
        //   1543: putfield mWhat : Ljava/lang/String;
        //   1546: aload #4
        //   1548: astore_3
        //   1549: aload_0
        //   1550: getfield ID : Ljava/lang/String;
        //   1553: ldc_w '@'
        //   1556: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1559: iconst_m1
        //   1560: if_icmple -> 1617
        //   1563: aload #4
        //   1565: astore_3
        //   1566: aload_0
        //   1567: aload_0
        //   1568: getfield ID : Ljava/lang/String;
        //   1571: iconst_0
        //   1572: aload_0
        //   1573: getfield ID : Ljava/lang/String;
        //   1576: ldc_w '@'
        //   1579: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1582: invokevirtual substring : (II)Ljava/lang/String;
        //   1585: putfield mWhat : Ljava/lang/String;
        //   1588: aload #4
        //   1590: astore_3
        //   1591: aload_0
        //   1592: aload_0
        //   1593: getfield ID : Ljava/lang/String;
        //   1596: aload_0
        //   1597: getfield ID : Ljava/lang/String;
        //   1600: ldc_w '@'
        //   1603: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1606: iconst_1
        //   1607: iadd
        //   1608: invokevirtual substring : (I)Ljava/lang/String;
        //   1611: invokevirtual trim : ()Ljava/lang/String;
        //   1614: putfield ID : Ljava/lang/String;
        //   1617: aload #4
        //   1619: astore_3
        //   1620: aload_0
        //   1621: getfield ID : Ljava/lang/String;
        //   1624: ldc ' ('
        //   1626: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1629: iconst_m1
        //   1630: if_icmple -> 1660
        //   1633: aload #4
        //   1635: astore_3
        //   1636: aload_0
        //   1637: aload_0
        //   1638: getfield ID : Ljava/lang/String;
        //   1641: iconst_0
        //   1642: aload_0
        //   1643: getfield ID : Ljava/lang/String;
        //   1646: ldc '('
        //   1648: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1651: invokevirtual substring : (II)Ljava/lang/String;
        //   1654: invokevirtual trim : ()Ljava/lang/String;
        //   1657: putfield ID : Ljava/lang/String;
        //   1660: aload #4
        //   1662: astore_3
        //   1663: aload_0
        //   1664: aload_0
        //   1665: getfield ID : Ljava/lang/String;
        //   1668: invokevirtual trim : ()Ljava/lang/String;
        //   1671: putfield ID : Ljava/lang/String;
        //   1674: iconst_0
        //   1675: istore #14
        //   1677: aload #4
        //   1679: astore_3
        //   1680: iload #14
        //   1682: ldc_w 'aaaaaaaaaaaaaaaaaeeeeeeeeeeeooooooooooooooooouuuuuuuuuuuiiiiiyyyyydx'
        //   1685: invokevirtual length : ()I
        //   1688: if_icmpge -> 1727
        //   1691: aload #4
        //   1693: astore_3
        //   1694: aload_0
        //   1695: aload_0
        //   1696: getfield ID : Ljava/lang/String;
        //   1699: ldc_w '
        //   1702: iload #14
        //   1704: invokevirtual charAt : (I)C
        //   1707: ldc_w 'aaaaaaaaaaaaaaaaaeeeeeeeeeeeooooooooooooooooouuuuuuuuuuuiiiiiyyyyydx'
        //   1710: iload #14
        //   1712: invokevirtual charAt : (I)C
        //   1715: invokevirtual replace : (CC)Ljava/lang/String;
        //   1718: putfield ID : Ljava/lang/String;
        //   1721: iinc #14, 1
        //   1724: goto -> 1677
        //   1727: iconst_0
        //   1728: istore #14
        //   1730: aload #4
        //   1732: astore_3
        //   1733: iload #14
        //   1735: aload_0
        //   1736: getfield ID : Ljava/lang/String;
        //   1739: invokevirtual length : ()I
        //   1742: if_icmpge -> 1847
        //   1745: aload #4
        //   1747: astore_3
        //   1748: aload_0
        //   1749: getfield ID : Ljava/lang/String;
        //   1752: iload #14
        //   1754: invokevirtual charAt : (I)C
        //   1757: bipush #127
        //   1759: if_icmpgt -> 1779
        //   1762: aload #4
        //   1764: astore_3
        //   1765: aload_0
        //   1766: getfield ID : Ljava/lang/String;
        //   1769: iload #14
        //   1771: invokevirtual charAt : (I)C
        //   1774: bipush #31
        //   1776: if_icmpge -> 1841
        //   1779: aload #4
        //   1781: astore_3
        //   1782: new java/lang/StringBuilder
        //   1785: astore_2
        //   1786: aload #4
        //   1788: astore_3
        //   1789: aload_2
        //   1790: invokespecial <init> : ()V
        //   1793: aload #4
        //   1795: astore_3
        //   1796: aload_2
        //   1797: aload_0
        //   1798: getfield ID : Ljava/lang/String;
        //   1801: iconst_0
        //   1802: iload #14
        //   1804: invokevirtual substring : (II)Ljava/lang/String;
        //   1807: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1810: pop
        //   1811: aload #4
        //   1813: astore_3
        //   1814: aload_2
        //   1815: aload_0
        //   1816: getfield ID : Ljava/lang/String;
        //   1819: iload #14
        //   1821: iconst_1
        //   1822: iadd
        //   1823: invokevirtual substring : (I)Ljava/lang/String;
        //   1826: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1829: pop
        //   1830: aload #4
        //   1832: astore_3
        //   1833: aload_0
        //   1834: aload_2
        //   1835: invokevirtual toString : ()Ljava/lang/String;
        //   1838: putfield ID : Ljava/lang/String;
        //   1841: iinc #14, 1
        //   1844: goto -> 1730
        //   1847: ldc_w 'WA'
        //   1850: astore_2
        //   1851: aload_2
        //   1852: astore_3
        //   1853: aload #8
        //   1855: aload_0
        //   1856: getfield mWhat : Ljava/lang/String;
        //   1859: invokevirtual trim : ()Ljava/lang/String;
        //   1862: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1865: iconst_m1
        //   1866: if_icmple -> 1886
        //   1869: aload_2
        //   1870: astore_3
        //   1871: aload_0
        //   1872: getfield mWhat : Ljava/lang/String;
        //   1875: invokevirtual length : ()I
        //   1878: ifle -> 1886
        //   1881: aconst_null
        //   1882: astore_2
        //   1883: goto -> 2269
        //   1886: aload_2
        //   1887: astore_3
        //   1888: aload_0
        //   1889: aload #8
        //   1891: putfield mWhat : Ljava/lang/String;
        //   1894: aload_2
        //   1895: astore_3
        //   1896: getstatic tamhoang/ldpro4/MainActivity.Json_Tinnhan : Lorg/json/JSONObject;
        //   1899: astore #10
        //   1901: aload_2
        //   1902: astore_3
        //   1903: new java/lang/StringBuilder
        //   1906: astore #4
        //   1908: aload_2
        //   1909: astore_3
        //   1910: aload #4
        //   1912: invokespecial <init> : ()V
        //   1915: aload_2
        //   1916: astore_3
        //   1917: aload #4
        //   1919: aload_2
        //   1920: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1923: pop
        //   1924: aload_2
        //   1925: astore_3
        //   1926: aload #4
        //   1928: ldc_w ' - '
        //   1931: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1934: pop
        //   1935: aload_2
        //   1936: astore_3
        //   1937: aload #4
        //   1939: aload_0
        //   1940: getfield ID : Ljava/lang/String;
        //   1943: invokevirtual trim : ()Ljava/lang/String;
        //   1946: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1949: pop
        //   1950: aload_2
        //   1951: astore_3
        //   1952: aload #10
        //   1954: aload #4
        //   1956: invokevirtual toString : ()Ljava/lang/String;
        //   1959: invokevirtual has : (Ljava/lang/String;)Z
        //   1962: ifeq -> 2158
        //   1965: aload_2
        //   1966: astore_3
        //   1967: new org/json/JSONObject
        //   1970: astore #4
        //   1972: aload_2
        //   1973: astore_3
        //   1974: getstatic tamhoang/ldpro4/MainActivity.Json_Tinnhan : Lorg/json/JSONObject;
        //   1977: astore #7
        //   1979: aload_2
        //   1980: astore_3
        //   1981: new java/lang/StringBuilder
        //   1984: astore #10
        //   1986: aload_2
        //   1987: astore_3
        //   1988: aload #10
        //   1990: invokespecial <init> : ()V
        //   1993: aload_2
        //   1994: astore_3
        //   1995: aload #10
        //   1997: aload_2
        //   1998: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2001: pop
        //   2002: aload_2
        //   2003: astore_3
        //   2004: aload #10
        //   2006: ldc_w ' - '
        //   2009: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2012: pop
        //   2013: aload_2
        //   2014: astore_3
        //   2015: aload #10
        //   2017: aload_0
        //   2018: getfield ID : Ljava/lang/String;
        //   2021: invokevirtual trim : ()Ljava/lang/String;
        //   2024: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2027: pop
        //   2028: aload_2
        //   2029: astore_3
        //   2030: aload #4
        //   2032: aload #7
        //   2034: aload #10
        //   2036: invokevirtual toString : ()Ljava/lang/String;
        //   2039: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2042: invokespecial <init> : (Ljava/lang/String;)V
        //   2045: aload_2
        //   2046: astore_3
        //   2047: aload #4
        //   2049: aload #8
        //   2051: invokevirtual trim : ()Ljava/lang/String;
        //   2054: invokevirtual has : (Ljava/lang/String;)Z
        //   2057: ifeq -> 2065
        //   2060: aconst_null
        //   2061: astore_2
        //   2062: goto -> 2155
        //   2065: aload_2
        //   2066: astore_3
        //   2067: aload #4
        //   2069: aload #8
        //   2071: invokevirtual trim : ()Ljava/lang/String;
        //   2074: ldc_w 'OK'
        //   2077: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   2080: pop
        //   2081: aload_2
        //   2082: astore_3
        //   2083: getstatic tamhoang/ldpro4/MainActivity.Json_Tinnhan : Lorg/json/JSONObject;
        //   2086: astore #7
        //   2088: aload_2
        //   2089: astore_3
        //   2090: new java/lang/StringBuilder
        //   2093: astore #10
        //   2095: aload_2
        //   2096: astore_3
        //   2097: aload #10
        //   2099: invokespecial <init> : ()V
        //   2102: aload_2
        //   2103: astore_3
        //   2104: aload #10
        //   2106: aload_2
        //   2107: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2110: pop
        //   2111: aload_2
        //   2112: astore_3
        //   2113: aload #10
        //   2115: ldc_w ' - '
        //   2118: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2121: pop
        //   2122: aload_2
        //   2123: astore_3
        //   2124: aload #10
        //   2126: aload_0
        //   2127: getfield ID : Ljava/lang/String;
        //   2130: invokevirtual trim : ()Ljava/lang/String;
        //   2133: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2136: pop
        //   2137: aload_2
        //   2138: astore_3
        //   2139: aload #7
        //   2141: aload #10
        //   2143: invokevirtual toString : ()Ljava/lang/String;
        //   2146: aload #4
        //   2148: invokevirtual toString : ()Ljava/lang/String;
        //   2151: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   2154: pop
        //   2155: goto -> 2269
        //   2158: aload_2
        //   2159: astore_3
        //   2160: new org/json/JSONObject
        //   2163: astore #7
        //   2165: aload_2
        //   2166: astore_3
        //   2167: aload #7
        //   2169: invokespecial <init> : ()V
        //   2172: aload_2
        //   2173: astore_3
        //   2174: aload #7
        //   2176: aload #8
        //   2178: invokevirtual trim : ()Ljava/lang/String;
        //   2181: ldc_w 'OK'
        //   2184: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   2187: pop
        //   2188: aload_2
        //   2189: astore_3
        //   2190: getstatic tamhoang/ldpro4/MainActivity.Json_Tinnhan : Lorg/json/JSONObject;
        //   2193: astore #10
        //   2195: aload_2
        //   2196: astore_3
        //   2197: new java/lang/StringBuilder
        //   2200: astore #4
        //   2202: aload_2
        //   2203: astore_3
        //   2204: aload #4
        //   2206: invokespecial <init> : ()V
        //   2209: aload_2
        //   2210: astore_3
        //   2211: aload #4
        //   2213: aload_2
        //   2214: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2217: pop
        //   2218: aload_2
        //   2219: astore_3
        //   2220: aload #4
        //   2222: ldc_w ' - '
        //   2225: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2228: pop
        //   2229: aload_2
        //   2230: astore_3
        //   2231: aload #4
        //   2233: aload_0
        //   2234: getfield ID : Ljava/lang/String;
        //   2237: invokevirtual trim : ()Ljava/lang/String;
        //   2240: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2243: pop
        //   2244: aload_2
        //   2245: astore_3
        //   2246: aload #10
        //   2248: aload #4
        //   2250: invokevirtual toString : ()Ljava/lang/String;
        //   2253: aload #7
        //   2255: invokevirtual toString : ()Ljava/lang/String;
        //   2258: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   2261: pop
        //   2262: goto -> 2269
        //   2265: astore_1
        //   2266: goto -> 4399
        //   2269: aload_2
        //   2270: astore_3
        //   2271: new java/lang/StringBuilder
        //   2274: astore #4
        //   2276: aload_2
        //   2277: astore_3
        //   2278: aload #4
        //   2280: invokespecial <init> : ()V
        //   2283: aload_2
        //   2284: astore_3
        //   2285: aload #4
        //   2287: aload_2
        //   2288: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2291: pop
        //   2292: aload_2
        //   2293: astore_3
        //   2294: aload #4
        //   2296: ldc_w ' - '
        //   2299: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2302: pop
        //   2303: aload_2
        //   2304: astore_3
        //   2305: aload #4
        //   2307: aload_0
        //   2308: getfield ID : Ljava/lang/String;
        //   2311: invokevirtual trim : ()Ljava/lang/String;
        //   2314: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2317: pop
        //   2318: aload_2
        //   2319: astore_3
        //   2320: aload #4
        //   2322: invokevirtual toString : ()Ljava/lang/String;
        //   2325: astore #4
        //   2327: aload_2
        //   2328: astore_3
        //   2329: getstatic tamhoang/ldpro4/MainActivity.arr_TenKH : Ljava/util/ArrayList;
        //   2332: aload #4
        //   2334: invokevirtual indexOf : (Ljava/lang/Object;)I
        //   2337: istore #14
        //   2339: iload #14
        //   2341: iconst_m1
        //   2342: if_icmpne -> 2591
        //   2345: aload_2
        //   2346: astore_3
        //   2347: aload #4
        //   2349: ldc_w 'null'
        //   2352: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2355: iconst_m1
        //   2356: if_icmpne -> 2591
        //   2359: aload_2
        //   2360: astore_3
        //   2361: new android/app/Notification$WearableExtender
        //   2364: astore #10
        //   2366: aload_2
        //   2367: astore_3
        //   2368: aload #10
        //   2370: aload #9
        //   2372: invokespecial <init> : (Landroid/app/Notification;)V
        //   2375: aload_2
        //   2376: astore_3
        //   2377: new java/util/ArrayList
        //   2380: astore #9
        //   2382: aload_2
        //   2383: astore_3
        //   2384: aload #9
        //   2386: invokespecial <init> : ()V
        //   2389: aload_2
        //   2390: astore_3
        //   2391: aload #9
        //   2393: aload #10
        //   2395: invokevirtual getActions : ()Ljava/util/List;
        //   2398: invokevirtual addAll : (Ljava/util/Collection;)Z
        //   2401: pop
        //   2402: aload_2
        //   2403: astore_3
        //   2404: aload #9
        //   2406: invokevirtual iterator : ()Ljava/util/Iterator;
        //   2409: astore #7
        //   2411: aload_2
        //   2412: astore_3
        //   2413: aload #7
        //   2415: invokeinterface hasNext : ()Z
        //   2420: ifeq -> 2591
        //   2423: aload_2
        //   2424: astore_3
        //   2425: aload #7
        //   2427: invokeinterface next : ()Ljava/lang/Object;
        //   2432: checkcast android/app/Notification$Action
        //   2435: astore #9
        //   2437: aload_2
        //   2438: astore_3
        //   2439: aload #9
        //   2441: getfield title : Ljava/lang/CharSequence;
        //   2444: invokeinterface toString : ()Ljava/lang/String;
        //   2449: ldc_w 'Trl
        //   2452: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2455: iconst_m1
        //   2456: if_icmpgt -> 2481
        //   2459: aload_2
        //   2460: astore_3
        //   2461: aload #9
        //   2463: getfield title : Ljava/lang/CharSequence;
        //   2466: invokeinterface toString : ()Ljava/lang/String;
        //   2471: ldc_w 'Reply'
        //   2474: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2477: iconst_m1
        //   2478: if_icmple -> 2588
        //   2481: aload_2
        //   2482: astore_3
        //   2483: getstatic tamhoang/ldpro4/MainActivity.arr_TenKH : Ljava/util/ArrayList;
        //   2486: aload #4
        //   2488: invokevirtual add : (Ljava/lang/Object;)Z
        //   2491: pop
        //   2492: aload_2
        //   2493: astore_3
        //   2494: new tamhoang/ldpro4/data/Contact
        //   2497: astore #10
        //   2499: aload_2
        //   2500: astore_3
        //   2501: aload #10
        //   2503: invokespecial <init> : ()V
        //   2506: aload_2
        //   2507: astore_3
        //   2508: aload #10
        //   2510: aload #4
        //   2512: putfield name : Ljava/lang/String;
        //   2515: aload_2
        //   2516: astore_3
        //   2517: aload #10
        //   2519: aload_2
        //   2520: putfield app : Ljava/lang/String;
        //   2523: aload_2
        //   2524: astore_3
        //   2525: aload #10
        //   2527: aload #9
        //   2529: getfield actionIntent : Landroid/app/PendingIntent;
        //   2532: putfield pendingIntent : Landroid/app/PendingIntent;
        //   2535: aload_2
        //   2536: astore_3
        //   2537: aload #10
        //   2539: aload #9
        //   2541: invokevirtual getRemoteInputs : ()[Landroid/app/RemoteInput;
        //   2544: iconst_0
        //   2545: aaload
        //   2546: putfield remoteInput : Landroid/app/RemoteInput;
        //   2549: aload_2
        //   2550: astore_3
        //   2551: aload #10
        //   2553: aload_1
        //   2554: invokevirtual getNotification : ()Landroid/app/Notification;
        //   2557: getfield extras : Landroid/os/Bundle;
        //   2560: putfield remoteExtras : Landroid/os/Bundle;
        //   2563: aload_2
        //   2564: astore_3
        //   2565: getstatic tamhoang/ldpro4/MainActivity.contactslist : Ljava/util/ArrayList;
        //   2568: aload #10
        //   2570: invokevirtual add : (Ljava/lang/Object;)Z
        //   2573: pop
        //   2574: aload_2
        //   2575: astore_3
        //   2576: getstatic tamhoang/ldpro4/MainActivity.Notifi : Ltamhoang/ldpro4/NotificationReader;
        //   2579: ifnonnull -> 2588
        //   2582: aload_2
        //   2583: astore_3
        //   2584: aload_0
        //   2585: putstatic tamhoang/ldpro4/MainActivity.Notifi : Ltamhoang/ldpro4/NotificationReader;
        //   2588: goto -> 2411
        //   2591: aload_2
        //   2592: astore_3
        //   2593: aload #8
        //   2595: invokevirtual toLowerCase : ()Ljava/lang/String;
        //   2598: ldc_w 'ldpro'
        //   2601: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2604: istore #14
        //   2606: iload #14
        //   2608: iconst_m1
        //   2609: if_icmple -> 2643
        //   2612: aload_2
        //   2613: astore_3
        //   2614: aload #8
        //   2616: invokevirtual trim : ()Ljava/lang/String;
        //   2619: invokevirtual length : ()I
        //   2622: iconst_5
        //   2623: if_icmpne -> 2643
        //   2626: aload_2
        //   2627: astore_3
        //   2628: aload_0
        //   2629: putstatic tamhoang/ldpro4/MainActivity.Notifi : Ltamhoang/ldpro4/NotificationReader;
        //   2632: aload_2
        //   2633: astore_3
        //   2634: aload_0
        //   2635: aload #4
        //   2637: ldc_w 'Tin m
        //   2640: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   2643: aload_2
        //   2644: ifnull -> 2943
        //   2647: aload #4
        //   2649: ldc_w 'null'
        //   2652: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2655: iconst_m1
        //   2656: if_icmpne -> 2943
        //   2659: aload_2
        //   2660: ldc_w 'null'
        //   2663: if_acmpeq -> 2943
        //   2666: aload_0
        //   2667: getfield db : Ltamhoang/ldpro4/data/Database;
        //   2670: astore #9
        //   2672: new java/lang/StringBuilder
        //   2675: astore_3
        //   2676: aload_3
        //   2677: invokespecial <init> : ()V
        //   2680: aload_3
        //   2681: ldc_w 'Select * From Chat_database WHERE ngay_nhan = ''
        //   2684: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2687: pop
        //   2688: aload #5
        //   2690: astore_1
        //   2691: aload_3
        //   2692: aload_1
        //   2693: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2696: pop
        //   2697: aload_3
        //   2698: ldc_w '' And Ten_kh = ''
        //   2701: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2704: pop
        //   2705: aload_3
        //   2706: aload #4
        //   2708: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2711: pop
        //   2712: aload_3
        //   2713: ldc_w '' AND nd_goc = ''
        //   2716: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2719: pop
        //   2720: aload_3
        //   2721: aload #8
        //   2723: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2726: pop
        //   2727: aload_3
        //   2728: ldc_w '''
        //   2731: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2734: pop
        //   2735: aload #9
        //   2737: aload_3
        //   2738: invokevirtual toString : ()Ljava/lang/String;
        //   2741: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   2744: astore_3
        //   2745: aload_3
        //   2746: invokeinterface getCount : ()I
        //   2751: ifne -> 2922
        //   2754: new java/lang/StringBuilder
        //   2757: astore #9
        //   2759: aload #9
        //   2761: invokespecial <init> : ()V
        //   2764: aload #9
        //   2766: ldc_w 'Insert into Chat_database Values( null,''
        //   2769: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2772: pop
        //   2773: aload #9
        //   2775: aload_1
        //   2776: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2779: pop
        //   2780: aload #9
        //   2782: ldc_w '', ''
        //   2785: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2788: pop
        //   2789: aload_2
        //   2790: astore_1
        //   2791: aload #9
        //   2793: aload #6
        //   2795: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2798: pop
        //   2799: aload_2
        //   2800: astore_1
        //   2801: aload #9
        //   2803: ldc_w '', 1, ''
        //   2806: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2809: pop
        //   2810: aload_2
        //   2811: astore_1
        //   2812: aload #9
        //   2814: aload #4
        //   2816: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2819: pop
        //   2820: aload_2
        //   2821: astore_1
        //   2822: aload #9
        //   2824: ldc_w '', ''
        //   2827: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2830: pop
        //   2831: aload_2
        //   2832: astore_1
        //   2833: aload #9
        //   2835: aload #4
        //   2837: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2840: pop
        //   2841: aload_2
        //   2842: astore_1
        //   2843: aload #9
        //   2845: ldc_w '', ''
        //   2848: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2851: pop
        //   2852: aload_2
        //   2853: astore_1
        //   2854: aload #9
        //   2856: aload_2
        //   2857: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2860: pop
        //   2861: aload_2
        //   2862: astore_1
        //   2863: aload #9
        //   2865: ldc_w '',''
        //   2868: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2871: pop
        //   2872: aload_2
        //   2873: astore_1
        //   2874: aload #9
        //   2876: aload #8
        //   2878: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2881: pop
        //   2882: aload_2
        //   2883: astore_1
        //   2884: aload #9
        //   2886: ldc_w '',1)'
        //   2889: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2892: pop
        //   2893: aload_2
        //   2894: astore_1
        //   2895: aload #9
        //   2897: invokevirtual toString : ()Ljava/lang/String;
        //   2900: astore #9
        //   2902: aload_2
        //   2903: astore_1
        //   2904: aload_0
        //   2905: getfield db : Ltamhoang/ldpro4/data/Database;
        //   2908: aload #9
        //   2910: invokevirtual QueryData : (Ljava/lang/String;)V
        //   2913: aload_2
        //   2914: astore_1
        //   2915: iconst_1
        //   2916: putstatic tamhoang/ldpro4/MainActivity.sms : Z
        //   2919: goto -> 2924
        //   2922: aconst_null
        //   2923: astore_2
        //   2924: aload_2
        //   2925: astore_1
        //   2926: aload_3
        //   2927: invokeinterface close : ()V
        //   2932: goto -> 2943
        //   2935: astore_1
        //   2936: goto -> 4399
        //   2939: astore_1
        //   2940: goto -> 4399
        //   2943: ldc_w '''
        //   2946: astore_3
        //   2947: aload_2
        //   2948: ifnull -> 4383
        //   2951: aload #4
        //   2953: ldc_w 'null'
        //   2956: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2959: iconst_m1
        //   2960: if_icmpne -> 4383
        //   2963: aload_2
        //   2964: ldc_w 'null'
        //   2967: if_acmpeq -> 4383
        //   2970: aload #8
        //   2972: invokevirtual length : ()I
        //   2975: iconst_5
        //   2976: if_icmple -> 4383
        //   2979: aload_0
        //   2980: aload #8
        //   2982: aload_3
        //   2983: ldc_w ' '
        //   2986: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   2989: putfield body : Ljava/lang/String;
        //   2992: aload_0
        //   2993: aload #4
        //   2995: putfield Ten_KH : Ljava/lang/String;
        //   2998: getstatic tamhoang/ldpro4/MainActivity.DSkhachhang : Ljava/util/ArrayList;
        //   3001: aload_0
        //   3002: getfield Ten_KH : Ljava/lang/String;
        //   3005: invokevirtual indexOf : (Ljava/lang/Object;)I
        //   3008: istore #14
        //   3010: iload #14
        //   3012: iconst_m1
        //   3013: if_icmple -> 3105
        //   3016: aload_2
        //   3017: astore_1
        //   3018: aload_0
        //   3019: getfield body : Ljava/lang/String;
        //   3022: ldc_w 'Ok'
        //   3025: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   3028: ifne -> 3105
        //   3031: aload_2
        //   3032: astore_1
        //   3033: aload_0
        //   3034: getfield body : Ljava/lang/String;
        //   3037: ldc_w 'B
        //   3040: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   3043: ifne -> 3098
        //   3046: aload_2
        //   3047: astore_1
        //   3048: aload_0
        //   3049: getfield body : Ljava/lang/String;
        //   3052: invokevirtual toLowerCase : ()Ljava/lang/String;
        //   3055: ldc_w 'ldpro'
        //   3058: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   3061: ifne -> 3098
        //   3064: aload_2
        //   3065: astore_1
        //   3066: aload_0
        //   3067: getfield body : Ljava/lang/String;
        //   3070: ldc_w 'Thi
        //   3073: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   3076: ifne -> 3098
        //   3079: aload_2
        //   3080: astore_1
        //   3081: aload_0
        //   3082: getfield body : Ljava/lang/String;
        //   3085: ldc_w 'Success'
        //   3088: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   3091: istore #11
        //   3093: iload #11
        //   3095: ifeq -> 3119
        //   3098: goto -> 3105
        //   3101: astore_1
        //   3102: goto -> 4399
        //   3105: aload_0
        //   3106: getfield body : Ljava/lang/String;
        //   3109: ldc_w 'Tra lai'
        //   3112: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3115: iconst_m1
        //   3116: if_icmple -> 4376
        //   3119: new java/lang/StringBuilder
        //   3122: astore_1
        //   3123: aload_1
        //   3124: invokespecial <init> : ()V
        //   3127: aload_1
        //   3128: ldc_w 'Select * FROM tbl_kh_new WHERE ten_kh =''
        //   3131: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3134: pop
        //   3135: aload_1
        //   3136: aload_0
        //   3137: getfield Ten_KH : Ljava/lang/String;
        //   3140: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3143: pop
        //   3144: aload_1
        //   3145: aload_3
        //   3146: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3149: pop
        //   3150: aload_1
        //   3151: invokevirtual toString : ()Ljava/lang/String;
        //   3154: astore_1
        //   3155: aload_0
        //   3156: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3159: aload_1
        //   3160: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   3163: astore #4
        //   3165: aload #4
        //   3167: invokeinterface moveToFirst : ()Z
        //   3172: pop
        //   3173: new org/json/JSONObject
        //   3176: astore_1
        //   3177: aload_1
        //   3178: aload #4
        //   3180: iconst_5
        //   3181: invokeinterface getString : (I)Ljava/lang/String;
        //   3186: invokespecial <init> : (Ljava/lang/String;)V
        //   3189: aload_0
        //   3190: aload_1
        //   3191: putfield json : Lorg/json/JSONObject;
        //   3194: aload_1
        //   3195: ldc_w 'caidat_tg'
        //   3198: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   3201: astore_1
        //   3202: aload_0
        //   3203: aload_1
        //   3204: putfield caidat_tg : Lorg/json/JSONObject;
        //   3207: aload_1
        //   3208: ldc_w 'tg_debc'
        //   3211: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3214: invokestatic CheckTime : (Ljava/lang/String;)Z
        //   3217: istore #11
        //   3219: iload #11
        //   3221: ifne -> 4064
        //   3224: new java/lang/StringBuilder
        //   3227: astore_1
        //   3228: aload_1
        //   3229: invokespecial <init> : ()V
        //   3232: aload_1
        //   3233: ldc_w 'Select max(so_tin_nhan) from tbl_tinnhanS WHERE ngay_nhan = ''
        //   3236: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3239: pop
        //   3240: aload_1
        //   3241: aload #5
        //   3243: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3246: pop
        //   3247: aload_1
        //   3248: ldc_w '' AND ten_kh = ''
        //   3251: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3254: pop
        //   3255: aload_1
        //   3256: aload_0
        //   3257: getfield Ten_KH : Ljava/lang/String;
        //   3260: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3263: pop
        //   3264: aload_1
        //   3265: ldc_w '' AND type_kh = 1'
        //   3268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3271: pop
        //   3272: aload_1
        //   3273: invokevirtual toString : ()Ljava/lang/String;
        //   3276: astore_1
        //   3277: aload_0
        //   3278: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3281: aload_1
        //   3282: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   3285: astore_3
        //   3286: aload_3
        //   3287: invokeinterface moveToFirst : ()Z
        //   3292: pop
        //   3293: aload_0
        //   3294: aload_3
        //   3295: iconst_0
        //   3296: invokeinterface getInt : (I)I
        //   3301: iconst_1
        //   3302: iadd
        //   3303: putfield soTN : I
        //   3306: aload_0
        //   3307: getfield body : Ljava/lang/String;
        //   3310: ldc_w 'Tra lai'
        //   3313: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3316: iconst_m1
        //   3317: if_icmpne -> 3477
        //   3320: new java/lang/StringBuilder
        //   3323: astore_1
        //   3324: aload_1
        //   3325: invokespecial <init> : ()V
        //   3328: aload_1
        //   3329: ldc_w 'Insert Into tbl_tinnhanS values (null, ''
        //   3332: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3335: pop
        //   3336: aload_1
        //   3337: aload #5
        //   3339: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3342: pop
        //   3343: aload_1
        //   3344: ldc_w '', ''
        //   3347: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3350: pop
        //   3351: aload_1
        //   3352: aload #6
        //   3354: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3357: pop
        //   3358: aload_1
        //   3359: ldc_w '',1, ''
        //   3362: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3365: pop
        //   3366: aload_1
        //   3367: aload_0
        //   3368: getfield Ten_KH : Ljava/lang/String;
        //   3371: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3374: pop
        //   3375: aload_1
        //   3376: ldc_w '', ''
        //   3379: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3382: pop
        //   3383: aload_1
        //   3384: aload #4
        //   3386: iconst_1
        //   3387: invokeinterface getString : (I)Ljava/lang/String;
        //   3392: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3395: pop
        //   3396: aload_1
        //   3397: ldc_w '',''
        //   3400: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3403: pop
        //   3404: aload_1
        //   3405: aload_2
        //   3406: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3409: pop
        //   3410: aload_1
        //   3411: ldc_w '', '
        //   3414: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3417: pop
        //   3418: aload_1
        //   3419: aload_0
        //   3420: getfield soTN : I
        //   3423: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   3426: pop
        //   3427: aload_1
        //   3428: ldc_w ', ''
        //   3431: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3434: pop
        //   3435: aload_1
        //   3436: aload_0
        //   3437: getfield body : Ljava/lang/String;
        //   3440: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3443: pop
        //   3444: aload_1
        //   3445: ldc_w '',null,''
        //   3448: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3451: pop
        //   3452: aload_1
        //   3453: aload_0
        //   3454: getfield body : Ljava/lang/String;
        //   3457: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3460: pop
        //   3461: aload_1
        //   3462: ldc_w '', 'ko',0,1,1, null)'
        //   3465: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3468: pop
        //   3469: aload_1
        //   3470: invokevirtual toString : ()Ljava/lang/String;
        //   3473: astore_1
        //   3474: goto -> 3631
        //   3477: new java/lang/StringBuilder
        //   3480: astore_1
        //   3481: aload_1
        //   3482: invokespecial <init> : ()V
        //   3485: aload_1
        //   3486: ldc_w 'Insert Into tbl_tinnhanS values (null, ''
        //   3489: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3492: pop
        //   3493: aload_1
        //   3494: aload #5
        //   3496: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3499: pop
        //   3500: aload_1
        //   3501: ldc_w '', ''
        //   3504: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3507: pop
        //   3508: aload_1
        //   3509: aload #6
        //   3511: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3514: pop
        //   3515: aload_1
        //   3516: ldc_w '',1, ''
        //   3519: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3522: pop
        //   3523: aload_1
        //   3524: aload_0
        //   3525: getfield Ten_KH : Ljava/lang/String;
        //   3528: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3531: pop
        //   3532: aload_1
        //   3533: ldc_w '', ''
        //   3536: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3539: pop
        //   3540: aload_1
        //   3541: aload #4
        //   3543: iconst_1
        //   3544: invokeinterface getString : (I)Ljava/lang/String;
        //   3549: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3552: pop
        //   3553: aload_1
        //   3554: ldc_w '',''
        //   3557: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3560: pop
        //   3561: aload_1
        //   3562: aload_2
        //   3563: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3566: pop
        //   3567: aload_1
        //   3568: ldc_w '', '
        //   3571: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3574: pop
        //   3575: aload_1
        //   3576: aload_0
        //   3577: getfield soTN : I
        //   3580: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   3583: pop
        //   3584: aload_1
        //   3585: ldc_w ', ''
        //   3588: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3591: pop
        //   3592: aload_1
        //   3593: aload_0
        //   3594: getfield body : Ljava/lang/String;
        //   3597: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3600: pop
        //   3601: aload_1
        //   3602: ldc_w '',null,''
        //   3605: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3608: pop
        //   3609: aload_1
        //   3610: aload_0
        //   3611: getfield body : Ljava/lang/String;
        //   3614: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3617: pop
        //   3618: aload_1
        //   3619: ldc_w '', 'ko',0,0,0, null)'
        //   3622: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3625: pop
        //   3626: aload_1
        //   3627: invokevirtual toString : ()Ljava/lang/String;
        //   3630: astore_1
        //   3631: aload_0
        //   3632: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3635: aload_1
        //   3636: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3639: getstatic tamhoang/ldpro4/MainActivity.myDate : Ljava/lang/String;
        //   3642: invokestatic CheckDate : (Ljava/lang/String;)Z
        //   3645: ifeq -> 4048
        //   3648: aload_0
        //   3649: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3652: astore_2
        //   3653: new java/lang/StringBuilder
        //   3656: astore_1
        //   3657: aload_1
        //   3658: invokespecial <init> : ()V
        //   3661: aload_1
        //   3662: ldc_w 'Select * from tbl_tinnhanS WHERE ngay_nhan = ''
        //   3665: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3668: pop
        //   3669: aload_1
        //   3670: aload #5
        //   3672: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3675: pop
        //   3676: aload_1
        //   3677: ldc_w '' AND ten_kh = ''
        //   3680: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3683: pop
        //   3684: aload_1
        //   3685: aload_0
        //   3686: getfield Ten_KH : Ljava/lang/String;
        //   3689: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3692: pop
        //   3693: aload_1
        //   3694: ldc_w '' AND so_tin_nhan = '
        //   3697: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3700: pop
        //   3701: aload_1
        //   3702: aload_0
        //   3703: getfield soTN : I
        //   3706: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   3709: pop
        //   3710: aload_1
        //   3711: ldc_w ' AND type_kh = 1'
        //   3714: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3717: pop
        //   3718: aload_2
        //   3719: aload_1
        //   3720: invokevirtual toString : ()Ljava/lang/String;
        //   3723: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   3726: astore_1
        //   3727: aload_1
        //   3728: invokeinterface moveToFirst : ()Z
        //   3733: pop
        //   3734: aload_0
        //   3735: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3738: aload_1
        //   3739: iconst_0
        //   3740: invokeinterface getInt : (I)I
        //   3745: iconst_1
        //   3746: invokevirtual Update_TinNhanGoc : (II)V
        //   3749: goto -> 3884
        //   3752: astore_2
        //   3753: aload_0
        //   3754: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3757: astore #8
        //   3759: new java/lang/StringBuilder
        //   3762: astore_2
        //   3763: aload_2
        //   3764: invokespecial <init> : ()V
        //   3767: aload_2
        //   3768: ldc_w 'Update tbl_tinnhanS set phat_hien_loi = 'ko' WHERE id = '
        //   3771: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3774: pop
        //   3775: aload_2
        //   3776: aload_1
        //   3777: iconst_0
        //   3778: invokeinterface getInt : (I)I
        //   3783: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   3786: pop
        //   3787: aload #8
        //   3789: aload_2
        //   3790: invokevirtual toString : ()Ljava/lang/String;
        //   3793: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3796: aload_0
        //   3797: getfield db : Ltamhoang/ldpro4/data/Database;
        //   3800: astore_2
        //   3801: new java/lang/StringBuilder
        //   3804: astore #8
        //   3806: aload #8
        //   3808: invokespecial <init> : ()V
        //   3811: aload #8
        //   3813: ldc_w 'Delete From tbl_soctS WHERE ngay_nhan = ''
        //   3816: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3819: pop
        //   3820: aload #8
        //   3822: aload #5
        //   3824: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3827: pop
        //   3828: aload #8
        //   3830: ldc_w '' AND ten_kh = ''
        //   3833: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3836: pop
        //   3837: aload #8
        //   3839: aload_0
        //   3840: getfield Ten_KH : Ljava/lang/String;
        //   3843: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3846: pop
        //   3847: aload #8
        //   3849: ldc_w '' AND so_tin_nhan = '
        //   3852: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3855: pop
        //   3856: aload #8
        //   3858: aload_0
        //   3859: getfield soTN : I
        //   3862: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   3865: pop
        //   3866: aload #8
        //   3868: ldc_w ' AND type_kh = 1'
        //   3871: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3874: pop
        //   3875: aload_2
        //   3876: aload #8
        //   3878: invokevirtual toString : ()Ljava/lang/String;
        //   3881: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3884: ldc_w '18:30'
        //   3887: invokestatic CheckTime : (Ljava/lang/String;)Z
        //   3890: ifne -> 4042
        //   3893: aload_0
        //   3894: getfield body : Ljava/lang/String;
        //   3897: ldc_w 'Tra lai'
        //   3900: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3903: iconst_m1
        //   3904: if_icmpne -> 4042
        //   3907: getstatic tamhoang/ldpro4/MainActivity.handler : Landroid/os/Handler;
        //   3910: ifnonnull -> 3938
        //   3913: new android/os/Handler
        //   3916: astore_2
        //   3917: aload_2
        //   3918: invokespecial <init> : ()V
        //   3921: aload_2
        //   3922: putstatic tamhoang/ldpro4/MainActivity.handler : Landroid/os/Handler;
        //   3925: getstatic tamhoang/ldpro4/MainActivity.handler : Landroid/os/Handler;
        //   3928: getstatic tamhoang/ldpro4/MainActivity.runnable : Ljava/lang/Runnable;
        //   3931: ldc2_w 1000
        //   3934: invokevirtual postDelayed : (Ljava/lang/Runnable;J)Z
        //   3937: pop
        //   3938: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   3941: aload_0
        //   3942: getfield Ten_KH : Ljava/lang/String;
        //   3945: invokevirtual has : (Ljava/lang/String;)Z
        //   3948: ifne -> 3986
        //   3951: new org/json/JSONObject
        //   3954: astore_2
        //   3955: aload_2
        //   3956: invokespecial <init> : ()V
        //   3959: aload_2
        //   3960: ldc_w 'Time'
        //   3963: iconst_0
        //   3964: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   3967: pop
        //   3968: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   3971: aload_0
        //   3972: getfield Ten_KH : Ljava/lang/String;
        //   3975: aload_2
        //   3976: invokevirtual toString : ()Ljava/lang/String;
        //   3979: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   3982: pop
        //   3983: goto -> 4028
        //   3986: new org/json/JSONObject
        //   3989: astore_2
        //   3990: aload_2
        //   3991: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   3994: aload_0
        //   3995: getfield Ten_KH : Ljava/lang/String;
        //   3998: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   4001: invokespecial <init> : (Ljava/lang/String;)V
        //   4004: aload_2
        //   4005: ldc_w 'Time'
        //   4008: iconst_0
        //   4009: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   4012: pop
        //   4013: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   4016: aload_0
        //   4017: getfield Ten_KH : Ljava/lang/String;
        //   4020: aload_2
        //   4021: invokevirtual toString : ()Ljava/lang/String;
        //   4024: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   4027: pop
        //   4028: aload_0
        //   4029: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4032: aload_1
        //   4033: iconst_0
        //   4034: invokeinterface getInt : (I)I
        //   4039: invokevirtual Gui_Tin_Nhan : (I)V
        //   4042: aload_1
        //   4043: invokeinterface close : ()V
        //   4048: aload_3
        //   4049: invokeinterface close : ()V
        //   4054: aload #4
        //   4056: invokeinterface close : ()V
        //   4061: goto -> 4352
        //   4064: new java/lang/StringBuilder
        //   4067: astore_1
        //   4068: aload_1
        //   4069: invokespecial <init> : ()V
        //   4072: aload_1
        //   4073: ldc_w 'Select max(so_tin_nhan) from tbl_tinnhanS WHERE ngay_nhan = ''
        //   4076: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4079: pop
        //   4080: aload_1
        //   4081: aload #5
        //   4083: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4086: pop
        //   4087: aload_1
        //   4088: ldc_w '' AND ten_kh = ''
        //   4091: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4094: pop
        //   4095: aload_1
        //   4096: aload_0
        //   4097: getfield Ten_KH : Ljava/lang/String;
        //   4100: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4103: pop
        //   4104: aload_1
        //   4105: aload_3
        //   4106: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4109: pop
        //   4110: aload_1
        //   4111: invokevirtual toString : ()Ljava/lang/String;
        //   4114: astore_1
        //   4115: aload_0
        //   4116: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4119: aload_1
        //   4120: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   4123: astore_1
        //   4124: aload_1
        //   4125: invokeinterface moveToFirst : ()Z
        //   4130: pop
        //   4131: aload_0
        //   4132: aload_1
        //   4133: iconst_0
        //   4134: invokeinterface getInt : (I)I
        //   4139: iconst_1
        //   4140: iadd
        //   4141: putfield soTN : I
        //   4144: new java/lang/StringBuilder
        //   4147: astore_3
        //   4148: aload_3
        //   4149: invokespecial <init> : ()V
        //   4152: aload_3
        //   4153: ldc_w 'Insert Into tbl_tinnhanS values (null, ''
        //   4156: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4159: pop
        //   4160: aload_3
        //   4161: aload #5
        //   4163: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4166: pop
        //   4167: aload_3
        //   4168: ldc_w '', ''
        //   4171: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4174: pop
        //   4175: aload_3
        //   4176: aload #6
        //   4178: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4181: pop
        //   4182: aload_3
        //   4183: ldc_w '',1, ''
        //   4186: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4189: pop
        //   4190: aload_3
        //   4191: aload_0
        //   4192: getfield Ten_KH : Ljava/lang/String;
        //   4195: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4198: pop
        //   4199: aload_3
        //   4200: ldc_w '', ''
        //   4203: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4206: pop
        //   4207: aload_3
        //   4208: aload #4
        //   4210: iconst_1
        //   4211: invokeinterface getString : (I)Ljava/lang/String;
        //   4216: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4219: pop
        //   4220: aload_3
        //   4221: ldc_w '',''
        //   4224: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4227: pop
        //   4228: aload_3
        //   4229: aload_2
        //   4230: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4233: pop
        //   4234: aload_3
        //   4235: ldc_w '', '
        //   4238: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4241: pop
        //   4242: aload_3
        //   4243: aload_0
        //   4244: getfield soTN : I
        //   4247: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   4250: pop
        //   4251: aload_3
        //   4252: ldc_w ', ''
        //   4255: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4258: pop
        //   4259: aload_3
        //   4260: aload_0
        //   4261: getfield body : Ljava/lang/String;
        //   4264: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4267: pop
        //   4268: aload_3
        //   4269: ldc_w '',null,''
        //   4272: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4275: pop
        //   4276: aload_3
        //   4277: aload_0
        //   4278: getfield body : Ljava/lang/String;
        //   4281: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4284: pop
        //   4285: aload_3
        //   4286: ldc_w '', 'Hginhsnull)'
        //   4289: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4292: pop
        //   4293: aload_3
        //   4294: invokevirtual toString : ()Ljava/lang/String;
        //   4297: astore_2
        //   4298: aload_0
        //   4299: getfield db : Ltamhoang/ldpro4/data/Database;
        //   4302: aload_2
        //   4303: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4306: aload_1
        //   4307: invokeinterface close : ()V
        //   4312: aload #4
        //   4314: invokeinterface close : ()V
        //   4319: ldc_w '18:30'
        //   4322: invokestatic CheckTime : (Ljava/lang/String;)Z
        //   4325: ifne -> 4352
        //   4328: getstatic tamhoang/ldpro4/MainActivity.jSon_Setting : Lorg/json/JSONObject;
        //   4331: ldc_w 'tin_qua_gio'
        //   4334: invokevirtual getInt : (Ljava/lang/String;)I
        //   4337: iconst_1
        //   4338: if_icmpne -> 4352
        //   4341: aload_0
        //   4342: aload_0
        //   4343: getfield Ten_KH : Ljava/lang/String;
        //   4346: ldc_w 'Hginh
        //   4349: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   4352: aload #4
        //   4354: invokeinterface isClosed : ()Z
        //   4359: ifne -> 4383
        //   4362: aload #4
        //   4364: invokeinterface close : ()V
        //   4369: goto -> 4383
        //   4372: astore_1
        //   4373: goto -> 4399
        //   4376: goto -> 4383
        //   4379: astore_1
        //   4380: goto -> 4399
        //   4383: goto -> 4399
        //   4386: astore_1
        //   4387: goto -> 4399
        //   4390: astore_1
        //   4391: goto -> 4399
        //   4394: astore_1
        //   4395: goto -> 4399
        //   4398: astore_1
        //   4399: return
        // Exception table:
        //   from	to	target	type
        //   120	169	4398	java/lang/Exception
        //   169	180	4394	java/lang/Exception
        //   185	312	836	java/lang/Exception
        //   312	331	808	java/lang/Exception
        //   337	393	808	java/lang/Exception
        //   393	404	808	java/lang/Exception
        //   411	415	1408	java/lang/Exception
        //   418	423	1408	java/lang/Exception
        //   426	431	1408	java/lang/Exception
        //   434	443	1408	java/lang/Exception
        //   446	455	1408	java/lang/Exception
        //   458	471	1408	java/lang/Exception
        //   474	486	1408	java/lang/Exception
        //   489	493	1408	java/lang/Exception
        //   496	501	1408	java/lang/Exception
        //   504	509	1408	java/lang/Exception
        //   512	517	1408	java/lang/Exception
        //   520	529	1408	java/lang/Exception
        //   532	541	1408	java/lang/Exception
        //   544	557	1408	java/lang/Exception
        //   560	574	1408	java/lang/Exception
        //   577	589	1408	java/lang/Exception
        //   597	610	1408	java/lang/Exception
        //   613	618	1408	java/lang/Exception
        //   621	626	1408	java/lang/Exception
        //   629	634	1408	java/lang/Exception
        //   637	646	1408	java/lang/Exception
        //   649	658	1408	java/lang/Exception
        //   661	674	1408	java/lang/Exception
        //   677	692	1408	java/lang/Exception
        //   698	703	1408	java/lang/Exception
        //   706	711	1408	java/lang/Exception
        //   714	728	1408	java/lang/Exception
        //   731	736	1408	java/lang/Exception
        //   739	743	1408	java/lang/Exception
        //   746	750	1408	java/lang/Exception
        //   753	761	1408	java/lang/Exception
        //   764	772	1408	java/lang/Exception
        //   775	787	1408	java/lang/Exception
        //   790	805	1408	java/lang/Exception
        //   842	853	4390	java/lang/Exception
        //   861	873	1408	java/lang/Exception
        //   876	888	1408	java/lang/Exception
        //   891	903	1408	java/lang/Exception
        //   906	918	1408	java/lang/Exception
        //   921	934	1408	java/lang/Exception
        //   937	942	1408	java/lang/Exception
        //   945	956	1408	java/lang/Exception
        //   959	983	1408	java/lang/Exception
        //   986	1000	1408	java/lang/Exception
        //   1008	1012	2265	java/lang/Exception
        //   1015	1020	2265	java/lang/Exception
        //   1023	1028	2265	java/lang/Exception
        //   1031	1040	2265	java/lang/Exception
        //   1043	1052	2265	java/lang/Exception
        //   1055	1068	2265	java/lang/Exception
        //   1071	1083	2265	java/lang/Exception
        //   1086	1090	2265	java/lang/Exception
        //   1093	1098	2265	java/lang/Exception
        //   1101	1106	2265	java/lang/Exception
        //   1109	1114	2265	java/lang/Exception
        //   1117	1126	2265	java/lang/Exception
        //   1129	1138	2265	java/lang/Exception
        //   1141	1154	2265	java/lang/Exception
        //   1157	1171	2265	java/lang/Exception
        //   1174	1186	2265	java/lang/Exception
        //   1195	1208	2265	java/lang/Exception
        //   1211	1216	2265	java/lang/Exception
        //   1219	1224	2265	java/lang/Exception
        //   1227	1232	2265	java/lang/Exception
        //   1235	1244	2265	java/lang/Exception
        //   1247	1256	2265	java/lang/Exception
        //   1259	1272	2265	java/lang/Exception
        //   1275	1290	2265	java/lang/Exception
        //   1296	1300	2265	java/lang/Exception
        //   1303	1307	2265	java/lang/Exception
        //   1310	1323	2265	java/lang/Exception
        //   1326	1331	2265	java/lang/Exception
        //   1334	1339	2265	java/lang/Exception
        //   1342	1347	2265	java/lang/Exception
        //   1350	1359	2265	java/lang/Exception
        //   1362	1371	2265	java/lang/Exception
        //   1374	1387	2265	java/lang/Exception
        //   1390	1405	2265	java/lang/Exception
        //   1418	1429	4386	java/lang/Exception
        //   1443	1455	2265	java/lang/Exception
        //   1458	1471	2265	java/lang/Exception
        //   1474	1479	2265	java/lang/Exception
        //   1482	1493	2265	java/lang/Exception
        //   1496	1521	2265	java/lang/Exception
        //   1524	1546	2265	java/lang/Exception
        //   1549	1563	2265	java/lang/Exception
        //   1566	1588	2265	java/lang/Exception
        //   1591	1617	2265	java/lang/Exception
        //   1620	1633	2265	java/lang/Exception
        //   1636	1660	2265	java/lang/Exception
        //   1663	1674	2265	java/lang/Exception
        //   1680	1691	2265	java/lang/Exception
        //   1694	1721	2265	java/lang/Exception
        //   1733	1745	2265	java/lang/Exception
        //   1748	1762	2265	java/lang/Exception
        //   1765	1779	2265	java/lang/Exception
        //   1782	1786	2265	java/lang/Exception
        //   1789	1793	2265	java/lang/Exception
        //   1796	1811	2265	java/lang/Exception
        //   1814	1830	2265	java/lang/Exception
        //   1833	1841	2265	java/lang/Exception
        //   1853	1869	2265	java/lang/Exception
        //   1871	1881	2265	java/lang/Exception
        //   1888	1894	2265	java/lang/Exception
        //   1896	1901	2265	java/lang/Exception
        //   1903	1908	2265	java/lang/Exception
        //   1910	1915	2265	java/lang/Exception
        //   1917	1924	2265	java/lang/Exception
        //   1926	1935	2265	java/lang/Exception
        //   1937	1950	2265	java/lang/Exception
        //   1952	1965	2265	java/lang/Exception
        //   1967	1972	2265	java/lang/Exception
        //   1974	1979	2265	java/lang/Exception
        //   1981	1986	2265	java/lang/Exception
        //   1988	1993	2265	java/lang/Exception
        //   1995	2002	2265	java/lang/Exception
        //   2004	2013	2265	java/lang/Exception
        //   2015	2028	2265	java/lang/Exception
        //   2030	2045	2265	java/lang/Exception
        //   2047	2060	2265	java/lang/Exception
        //   2067	2081	2265	java/lang/Exception
        //   2083	2088	2265	java/lang/Exception
        //   2090	2095	2265	java/lang/Exception
        //   2097	2102	2265	java/lang/Exception
        //   2104	2111	2265	java/lang/Exception
        //   2113	2122	2265	java/lang/Exception
        //   2124	2137	2265	java/lang/Exception
        //   2139	2155	2265	java/lang/Exception
        //   2160	2165	2265	java/lang/Exception
        //   2167	2172	2265	java/lang/Exception
        //   2174	2188	2265	java/lang/Exception
        //   2190	2195	2265	java/lang/Exception
        //   2197	2202	2265	java/lang/Exception
        //   2204	2209	2265	java/lang/Exception
        //   2211	2218	2265	java/lang/Exception
        //   2220	2229	2265	java/lang/Exception
        //   2231	2244	2265	java/lang/Exception
        //   2246	2262	2265	java/lang/Exception
        //   2271	2276	4386	java/lang/Exception
        //   2278	2283	4386	java/lang/Exception
        //   2285	2292	4386	java/lang/Exception
        //   2294	2303	4386	java/lang/Exception
        //   2305	2318	4386	java/lang/Exception
        //   2320	2327	4386	java/lang/Exception
        //   2329	2339	4386	java/lang/Exception
        //   2347	2359	2265	java/lang/Exception
        //   2361	2366	2265	java/lang/Exception
        //   2368	2375	2265	java/lang/Exception
        //   2377	2382	2265	java/lang/Exception
        //   2384	2389	2265	java/lang/Exception
        //   2391	2402	2265	java/lang/Exception
        //   2404	2411	2265	java/lang/Exception
        //   2413	2423	2265	java/lang/Exception
        //   2425	2437	2265	java/lang/Exception
        //   2439	2459	2265	java/lang/Exception
        //   2461	2481	2265	java/lang/Exception
        //   2483	2492	2265	java/lang/Exception
        //   2494	2499	2265	java/lang/Exception
        //   2501	2506	2265	java/lang/Exception
        //   2508	2515	2265	java/lang/Exception
        //   2517	2523	2265	java/lang/Exception
        //   2525	2535	2265	java/lang/Exception
        //   2537	2549	2265	java/lang/Exception
        //   2551	2563	2265	java/lang/Exception
        //   2565	2574	2265	java/lang/Exception
        //   2576	2582	2265	java/lang/Exception
        //   2584	2588	2265	java/lang/Exception
        //   2593	2606	4386	java/lang/Exception
        //   2614	2626	2265	java/lang/Exception
        //   2628	2632	2265	java/lang/Exception
        //   2634	2643	2265	java/lang/Exception
        //   2647	2659	2939	java/lang/Exception
        //   2666	2688	2939	java/lang/Exception
        //   2691	2789	2935	java/lang/Exception
        //   2791	2799	3101	java/lang/Exception
        //   2801	2810	3101	java/lang/Exception
        //   2812	2820	3101	java/lang/Exception
        //   2822	2831	3101	java/lang/Exception
        //   2833	2841	3101	java/lang/Exception
        //   2843	2852	3101	java/lang/Exception
        //   2854	2861	3101	java/lang/Exception
        //   2863	2872	3101	java/lang/Exception
        //   2874	2882	3101	java/lang/Exception
        //   2884	2893	3101	java/lang/Exception
        //   2895	2902	3101	java/lang/Exception
        //   2904	2913	3101	java/lang/Exception
        //   2915	2919	3101	java/lang/Exception
        //   2926	2932	3101	java/lang/Exception
        //   2951	2963	4379	java/lang/Exception
        //   2970	3010	4379	java/lang/Exception
        //   3018	3031	3101	java/lang/Exception
        //   3033	3046	3101	java/lang/Exception
        //   3048	3064	3101	java/lang/Exception
        //   3066	3079	3101	java/lang/Exception
        //   3081	3093	3101	java/lang/Exception
        //   3105	3119	4379	java/lang/Exception
        //   3119	3219	4379	java/lang/Exception
        //   3224	3474	4372	java/lang/Exception
        //   3477	3631	4372	java/lang/Exception
        //   3631	3734	4372	java/lang/Exception
        //   3734	3749	3752	java/lang/Exception
        //   3753	3884	4372	java/lang/Exception
        //   3884	3938	4372	java/lang/Exception
        //   3938	3983	4372	java/lang/Exception
        //   3986	4028	4372	java/lang/Exception
        //   4028	4042	4372	java/lang/Exception
        //   4042	4048	4372	java/lang/Exception
        //   4048	4061	4372	java/lang/Exception
        //   4064	4352	4372	java/lang/Exception
        //   4352	4369	4372	java/lang/Exception
    }

    public void onNotificationRemoved(StatusBarNotification paramStatusBarNotification) {
        super.onNotificationRemoved(paramStatusBarNotification);
    }
}
