package tamhoang.ldpro4.receivers;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import org.json.JSONObject;
import tamhoang.ldpro4.data.Database;
import tamhoang.ldpro4.services.SaveSmsService;

public class SMSReceiver extends BroadcastReceiver {
    String Ten_KH;

    String body = "";

    JSONObject caidat_gia;

    JSONObject caidat_tg;

    Database db;

    JSONObject json;

    Context mContext;

    String mGionhan;

    String mNgayNhan;

    String mSDT;

    SmsMessage[] messages = null;

    int soTN;

    private SmsMessage getIncomingMessage(Object paramObject, Bundle paramBundle) {
        if (Build.VERSION.SDK_INT >= 23) {
            String str = paramBundle.getString("format");
            paramObject = SmsMessage.createFromPdu((byte[])paramObject, str);
        } else {
            paramObject = SmsMessage.createFromPdu((byte[])paramObject);
        }
        return (SmsMessage)paramObject;
    }

    private void issueNotification(Context paramContext, String paramString1, String paramString2) {
        Bitmap bitmap = BitmapFactory.decodeResource(paramContext.getResources(), 2131558404);
        NotificationCompat.Builder builder = (new NotificationCompat.Builder(paramContext)).setLargeIcon(bitmap).setSmallIcon(2131558404).setContentTitle(paramString1).setStyle((NotificationCompat.Style)(new NotificationCompat.BigTextStyle()).bigText(paramString2)).setAutoCancel(true).setContentText(paramString2);
        ((NotificationManager)paramContext.getSystemService("notification")).notify(101, builder.build());
    }

    private void saveSmsInInbox(Context paramContext, SmsMessage paramSmsMessage) {
        Intent intent = new Intent(paramContext, SaveSmsService.class);
        intent.putExtra("sender_no", paramSmsMessage.getDisplayOriginatingAddress());
        intent.putExtra("message", paramSmsMessage.getDisplayMessageBody());
        intent.putExtra("date", paramSmsMessage.getTimestampMillis());
        paramContext.startService(intent);
    }

    public void onReceive(Context paramContext, Intent paramIntent) {
        // Byte code:
        //   0: aload_0
        //   1: new tamhoang/ldpro4/data/Database
        //   4: dup
        //   5: aload_1
        //   6: invokespecial <init> : (Landroid/content/Context;)V
        //   9: putfield db : Ltamhoang/ldpro4/data/Database;
        //   12: aload_0
        //   13: aload_1
        //   14: putfield mContext : Landroid/content/Context;
        //   17: iconst_1
        //   18: istore_3
        //   19: iconst_1
        //   20: istore #4
        //   22: iconst_1
        //   23: istore #5
        //   25: aload_2
        //   26: invokevirtual getAction : ()Ljava/lang/String;
        //   29: ldc 'android.provider.Telephony.SMS_RECEIVED'
        //   31: invokevirtual equals : (Ljava/lang/Object;)Z
        //   34: ifeq -> 1967
        //   37: aload_2
        //   38: invokevirtual getExtras : ()Landroid/os/Bundle;
        //   41: astore #6
        //   43: aload #6
        //   45: ldc 'pdus'
        //   47: invokevirtual get : (Ljava/lang/String;)Ljava/lang/Object;
        //   50: checkcast [Ljava/lang/Object;
        //   53: astore #7
        //   55: aload_0
        //   56: aload #7
        //   58: arraylength
        //   59: anewarray android/telephony/SmsMessage
        //   62: putfield messages : [Landroid/telephony/SmsMessage;
        //   65: iconst_0
        //   66: istore #8
        //   68: iload #8
        //   70: aload #7
        //   72: arraylength
        //   73: if_icmpge -> 131
        //   76: aload_0
        //   77: getfield messages : [Landroid/telephony/SmsMessage;
        //   80: iload #8
        //   82: aload #7
        //   84: iload #8
        //   86: aaload
        //   87: checkcast [B
        //   90: invokestatic createFromPdu : ([B)Landroid/telephony/SmsMessage;
        //   93: aastore
        //   94: aload_0
        //   95: aload #7
        //   97: iload #8
        //   99: aaload
        //   100: aload #6
        //   102: invokespecial getIncomingMessage : (Ljava/lang/Object;Landroid/os/Bundle;)Landroid/telephony/SmsMessage;
        //   105: astore_2
        //   106: aload_0
        //   107: aload_1
        //   108: aload_2
        //   109: invokevirtual getDisplayOriginatingAddress : ()Ljava/lang/String;
        //   112: aload_2
        //   113: invokevirtual getDisplayMessageBody : ()Ljava/lang/String;
        //   116: invokespecial issueNotification : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
        //   119: aload_0
        //   120: aload_1
        //   121: aload_2
        //   122: invokespecial saveSmsInInbox : (Landroid/content/Context;Landroid/telephony/SmsMessage;)V
        //   125: iinc #8, 1
        //   128: goto -> 68
        //   131: aload_0
        //   132: getfield messages : [Landroid/telephony/SmsMessage;
        //   135: iconst_0
        //   136: aaload
        //   137: astore_1
        //   138: invokestatic getInstance : ()Ljava/util/Calendar;
        //   141: astore #6
        //   143: aload #6
        //   145: new java/util/Date
        //   148: dup
        //   149: invokespecial <init> : ()V
        //   152: invokevirtual setTime : (Ljava/util/Date;)V
        //   155: new java/text/SimpleDateFormat
        //   158: dup
        //   159: ldc 'yyyy-MM-dd'
        //   161: invokespecial <init> : (Ljava/lang/String;)V
        //   164: astore #7
        //   166: new java/text/SimpleDateFormat
        //   169: dup
        //   170: ldc 'HH:mm:ss'
        //   172: invokespecial <init> : (Ljava/lang/String;)V
        //   175: astore_2
        //   176: aload #7
        //   178: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   181: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   184: aload_2
        //   185: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   188: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   191: aload_0
        //   192: aload #7
        //   194: aload #6
        //   196: invokevirtual getTime : ()Ljava/util/Date;
        //   199: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
        //   202: putfield mNgayNhan : Ljava/lang/String;
        //   205: aload_0
        //   206: aload_2
        //   207: aload #6
        //   209: invokevirtual getTime : ()Ljava/util/Date;
        //   212: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
        //   215: putfield mGionhan : Ljava/lang/String;
        //   218: aload_0
        //   219: ldc ''
        //   221: putfield mSDT : Ljava/lang/String;
        //   224: aload_0
        //   225: getfield messages : [Landroid/telephony/SmsMessage;
        //   228: arraylength
        //   229: istore #8
        //   231: iload #8
        //   233: iconst_1
        //   234: if_icmpeq -> 312
        //   237: aload_1
        //   238: invokevirtual isReplace : ()Z
        //   241: ifeq -> 247
        //   244: goto -> 312
        //   247: new java/lang/StringBuilder
        //   250: astore_2
        //   251: aload_2
        //   252: invokespecial <init> : ()V
        //   255: iconst_0
        //   256: istore #8
        //   258: iload #8
        //   260: aload_0
        //   261: getfield messages : [Landroid/telephony/SmsMessage;
        //   264: arraylength
        //   265: if_icmpge -> 289
        //   268: aload_2
        //   269: aload_0
        //   270: getfield messages : [Landroid/telephony/SmsMessage;
        //   273: iload #8
        //   275: aaload
        //   276: invokevirtual getMessageBody : ()Ljava/lang/String;
        //   279: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   282: pop
        //   283: iinc #8, 1
        //   286: goto -> 258
        //   289: aload_0
        //   290: aload_2
        //   291: invokevirtual toString : ()Ljava/lang/String;
        //   294: ldc_w '''
        //   297: ldc ''
        //   299: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   302: putfield body : Ljava/lang/String;
        //   305: goto -> 328
        //   308: astore_1
        //   309: goto -> 1967
        //   312: aload_0
        //   313: aload_1
        //   314: invokevirtual getDisplayMessageBody : ()Ljava/lang/String;
        //   317: ldc_w '''
        //   320: ldc ''
        //   322: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   325: putfield body : Ljava/lang/String;
        //   328: aload_1
        //   329: invokevirtual getDisplayOriginatingAddress : ()Ljava/lang/String;
        //   332: ldc_w ' '
        //   335: ldc ''
        //   337: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   340: invokevirtual trim : ()Ljava/lang/String;
        //   343: astore_1
        //   344: aload_0
        //   345: aload_1
        //   346: putfield mSDT : Ljava/lang/String;
        //   349: aload_1
        //   350: ldc_w '0'
        //   353: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   356: istore #9
        //   358: iload #9
        //   360: ifeq -> 400
        //   363: new java/lang/StringBuilder
        //   366: astore_1
        //   367: aload_1
        //   368: invokespecial <init> : ()V
        //   371: aload_1
        //   372: ldc_w '+84'
        //   375: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   378: pop
        //   379: aload_1
        //   380: aload_0
        //   381: getfield mSDT : Ljava/lang/String;
        //   384: iconst_1
        //   385: invokevirtual substring : (I)Ljava/lang/String;
        //   388: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   391: pop
        //   392: aload_0
        //   393: aload_1
        //   394: invokevirtual toString : ()Ljava/lang/String;
        //   397: putfield mSDT : Ljava/lang/String;
        //   400: getstatic tamhoang/ldpro4/MainActivity.DSkhachhang : Ljava/util/ArrayList;
        //   403: invokevirtual size : ()I
        //   406: ifne -> 420
        //   409: aload_0
        //   410: getfield db : Ltamhoang/ldpro4/data/Database;
        //   413: invokevirtual LayDanhsachKH : ()V
        //   416: goto -> 420
        //   419: astore_1
        //   420: getstatic tamhoang/ldpro4/MainActivity.DSkhachhang : Ljava/util/ArrayList;
        //   423: aload_0
        //   424: getfield mSDT : Ljava/lang/String;
        //   427: invokevirtual indexOf : (Ljava/lang/Object;)I
        //   430: istore #8
        //   432: iload #8
        //   434: iconst_m1
        //   435: if_icmple -> 481
        //   438: aload_0
        //   439: getfield body : Ljava/lang/String;
        //   442: ldc_w 'Ok'
        //   445: invokevirtual indexOf : (Ljava/lang/String;)I
        //   448: ifeq -> 481
        //   451: aload_0
        //   452: getfield body : Ljava/lang/String;
        //   455: ldc_w 'B
        //   458: invokevirtual indexOf : (Ljava/lang/String;)I
        //   461: ifeq -> 481
        //   464: aload_0
        //   465: getfield body : Ljava/lang/String;
        //   468: ldc_w 'Thi
        //   471: invokevirtual indexOf : (Ljava/lang/String;)I
        //   474: istore #8
        //   476: iload #8
        //   478: ifne -> 495
        //   481: aload_0
        //   482: getfield body : Ljava/lang/String;
        //   485: ldc_w 'Tra lai'
        //   488: invokevirtual indexOf : (Ljava/lang/String;)I
        //   491: iconst_m1
        //   492: if_icmple -> 1963
        //   495: iconst_1
        //   496: putstatic tamhoang/ldpro4/MainActivity.sms : Z
        //   499: iload #4
        //   501: istore #10
        //   503: getstatic tamhoang/ldpro4/MainActivity.jSon_Setting : Lorg/json/JSONObject;
        //   506: ldc_w 'tin_trung'
        //   509: invokevirtual getInt : (Ljava/lang/String;)I
        //   512: ifle -> 700
        //   515: iload #4
        //   517: istore #10
        //   519: new java/lang/StringBuilder
        //   522: astore_1
        //   523: iload #4
        //   525: istore #10
        //   527: aload_1
        //   528: invokespecial <init> : ()V
        //   531: iload #4
        //   533: istore #10
        //   535: aload_1
        //   536: ldc_w 'Select id From tbl_tinnhanS WHERE so_dienthoai = ''
        //   539: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   542: pop
        //   543: iload #4
        //   545: istore #10
        //   547: aload_1
        //   548: aload_0
        //   549: getfield mSDT : Ljava/lang/String;
        //   552: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   555: pop
        //   556: iload #4
        //   558: istore #10
        //   560: aload_1
        //   561: ldc_w '' AND ngay_nhan = ''
        //   564: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   567: pop
        //   568: iload #4
        //   570: istore #10
        //   572: aload_1
        //   573: aload_0
        //   574: getfield mNgayNhan : Ljava/lang/String;
        //   577: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   580: pop
        //   581: iload #4
        //   583: istore #10
        //   585: aload_1
        //   586: ldc_w '' AND nd_goc = ''
        //   589: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   592: pop
        //   593: iload #4
        //   595: istore #10
        //   597: aload_1
        //   598: aload_0
        //   599: getfield body : Ljava/lang/String;
        //   602: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   605: pop
        //   606: iload #4
        //   608: istore #10
        //   610: aload_1
        //   611: ldc_w '''
        //   614: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   617: pop
        //   618: iload #4
        //   620: istore #10
        //   622: aload_1
        //   623: invokevirtual toString : ()Ljava/lang/String;
        //   626: astore_1
        //   627: iload #4
        //   629: istore #10
        //   631: aload_0
        //   632: getfield db : Ltamhoang/ldpro4/data/Database;
        //   635: aload_1
        //   636: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   639: astore_1
        //   640: iload #4
        //   642: istore #10
        //   644: aload_1
        //   645: invokeinterface moveToFirst : ()Z
        //   650: pop
        //   651: iload #5
        //   653: istore #8
        //   655: iload #4
        //   657: istore #10
        //   659: aload_1
        //   660: invokeinterface getCount : ()I
        //   665: ifle -> 671
        //   668: iconst_0
        //   669: istore #8
        //   671: iload #8
        //   673: istore_3
        //   674: iload #8
        //   676: istore #10
        //   678: aload_1
        //   679: invokeinterface isClosed : ()Z
        //   684: ifne -> 700
        //   687: iload #8
        //   689: istore #10
        //   691: aload_1
        //   692: invokeinterface close : ()V
        //   697: iload #8
        //   699: istore_3
        //   700: goto -> 711
        //   703: astore_1
        //   704: aload_1
        //   705: invokevirtual printStackTrace : ()V
        //   708: iload #10
        //   710: istore_3
        //   711: new java/lang/StringBuilder
        //   714: astore_1
        //   715: aload_1
        //   716: invokespecial <init> : ()V
        //   719: aload_1
        //   720: ldc_w 'Select * FROM tbl_kh_new WHERE sdt =''
        //   723: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   726: pop
        //   727: aload_1
        //   728: aload_0
        //   729: getfield mSDT : Ljava/lang/String;
        //   732: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   735: pop
        //   736: aload_1
        //   737: ldc_w '''
        //   740: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   743: pop
        //   744: aload_1
        //   745: invokevirtual toString : ()Ljava/lang/String;
        //   748: astore_1
        //   749: aload_0
        //   750: getfield db : Ltamhoang/ldpro4/data/Database;
        //   753: aload_1
        //   754: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   757: astore_2
        //   758: aload_2
        //   759: invokeinterface moveToFirst : ()Z
        //   764: pop
        //   765: iload_3
        //   766: iconst_1
        //   767: if_icmpne -> 1930
        //   770: new org/json/JSONObject
        //   773: astore_1
        //   774: aload_1
        //   775: aload_2
        //   776: iconst_5
        //   777: invokeinterface getString : (I)Ljava/lang/String;
        //   782: invokespecial <init> : (Ljava/lang/String;)V
        //   785: aload_0
        //   786: aload_1
        //   787: putfield json : Lorg/json/JSONObject;
        //   790: aload_0
        //   791: aload_1
        //   792: ldc_w 'caidat_gia'
        //   795: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   798: putfield caidat_gia : Lorg/json/JSONObject;
        //   801: aload_0
        //   802: aload_0
        //   803: getfield json : Lorg/json/JSONObject;
        //   806: ldc_w 'caidat_tg'
        //   809: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   812: putfield caidat_tg : Lorg/json/JSONObject;
        //   815: goto -> 827
        //   818: astore_1
        //   819: goto -> 823
        //   822: astore_1
        //   823: aload_1
        //   824: invokevirtual printStackTrace : ()V
        //   827: aload_0
        //   828: getfield caidat_tg : Lorg/json/JSONObject;
        //   831: ldc_w 'tg_debc'
        //   834: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   837: invokestatic CheckTime : (Ljava/lang/String;)Z
        //   840: istore #9
        //   842: iload #9
        //   844: ifne -> 1587
        //   847: new java/lang/StringBuilder
        //   850: astore_1
        //   851: aload_1
        //   852: invokespecial <init> : ()V
        //   855: aload_1
        //   856: ldc_w 'Select max(so_tin_nhan) from tbl_tinnhanS WHERE ngay_nhan = ''
        //   859: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   862: pop
        //   863: aload_1
        //   864: aload_0
        //   865: getfield mNgayNhan : Ljava/lang/String;
        //   868: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   871: pop
        //   872: aload_1
        //   873: ldc_w '' AND so_dienthoai = ''
        //   876: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   879: pop
        //   880: aload_1
        //   881: aload_0
        //   882: getfield mSDT : Ljava/lang/String;
        //   885: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   888: pop
        //   889: aload_1
        //   890: ldc_w '' AND type_kh = 1'
        //   893: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   896: pop
        //   897: aload_1
        //   898: invokevirtual toString : ()Ljava/lang/String;
        //   901: astore_1
        //   902: aload_0
        //   903: getfield db : Ltamhoang/ldpro4/data/Database;
        //   906: aload_1
        //   907: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   910: astore #7
        //   912: aload #7
        //   914: invokeinterface moveToFirst : ()Z
        //   919: pop
        //   920: aload_0
        //   921: aload_2
        //   922: iconst_0
        //   923: invokeinterface getString : (I)Ljava/lang/String;
        //   928: putfield Ten_KH : Ljava/lang/String;
        //   931: aload_0
        //   932: aload #7
        //   934: iconst_0
        //   935: invokeinterface getInt : (I)I
        //   940: iconst_1
        //   941: iadd
        //   942: putfield soTN : I
        //   945: aload_0
        //   946: getfield body : Ljava/lang/String;
        //   949: ldc_w 'Tra lai'
        //   952: invokevirtual indexOf : (Ljava/lang/String;)I
        //   955: iconst_m1
        //   956: if_icmpne -> 1105
        //   959: new java/lang/StringBuilder
        //   962: astore_1
        //   963: aload_1
        //   964: invokespecial <init> : ()V
        //   967: aload_1
        //   968: ldc_w 'Insert Into tbl_tinnhanS values (null, ''
        //   971: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   974: pop
        //   975: aload_1
        //   976: aload_0
        //   977: getfield mNgayNhan : Ljava/lang/String;
        //   980: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   983: pop
        //   984: aload_1
        //   985: ldc_w '', ''
        //   988: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   991: pop
        //   992: aload_1
        //   993: aload_0
        //   994: getfield mGionhan : Ljava/lang/String;
        //   997: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1000: pop
        //   1001: aload_1
        //   1002: ldc_w '',1, ''
        //   1005: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1008: pop
        //   1009: aload_1
        //   1010: aload_0
        //   1011: getfield Ten_KH : Ljava/lang/String;
        //   1014: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1017: pop
        //   1018: aload_1
        //   1019: ldc_w '', ''
        //   1022: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1025: pop
        //   1026: aload_1
        //   1027: aload_2
        //   1028: iconst_1
        //   1029: invokeinterface getString : (I)Ljava/lang/String;
        //   1034: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1037: pop
        //   1038: aload_1
        //   1039: ldc_w '','sms', '
        //   1042: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1045: pop
        //   1046: aload_1
        //   1047: aload_0
        //   1048: getfield soTN : I
        //   1051: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1054: pop
        //   1055: aload_1
        //   1056: ldc_w ', ''
        //   1059: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1062: pop
        //   1063: aload_1
        //   1064: aload_0
        //   1065: getfield body : Ljava/lang/String;
        //   1068: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1071: pop
        //   1072: aload_1
        //   1073: ldc_w '',null,''
        //   1076: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1079: pop
        //   1080: aload_1
        //   1081: aload_0
        //   1082: getfield body : Ljava/lang/String;
        //   1085: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1088: pop
        //   1089: aload_1
        //   1090: ldc_w '', 'ko',0,1,1, null)'
        //   1093: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1096: pop
        //   1097: aload_1
        //   1098: invokevirtual toString : ()Ljava/lang/String;
        //   1101: astore_1
        //   1102: goto -> 1248
        //   1105: new java/lang/StringBuilder
        //   1108: astore_1
        //   1109: aload_1
        //   1110: invokespecial <init> : ()V
        //   1113: aload_1
        //   1114: ldc_w 'Insert Into tbl_tinnhanS values (null, ''
        //   1117: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1120: pop
        //   1121: aload_1
        //   1122: aload_0
        //   1123: getfield mNgayNhan : Ljava/lang/String;
        //   1126: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1129: pop
        //   1130: aload_1
        //   1131: ldc_w '', ''
        //   1134: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1137: pop
        //   1138: aload_1
        //   1139: aload_0
        //   1140: getfield mGionhan : Ljava/lang/String;
        //   1143: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1146: pop
        //   1147: aload_1
        //   1148: ldc_w '',1, ''
        //   1151: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1154: pop
        //   1155: aload_1
        //   1156: aload_0
        //   1157: getfield Ten_KH : Ljava/lang/String;
        //   1160: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1163: pop
        //   1164: aload_1
        //   1165: ldc_w '', ''
        //   1168: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1171: pop
        //   1172: aload_1
        //   1173: aload_2
        //   1174: iconst_1
        //   1175: invokeinterface getString : (I)Ljava/lang/String;
        //   1180: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1183: pop
        //   1184: aload_1
        //   1185: ldc_w '','sms', '
        //   1188: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1191: pop
        //   1192: aload_1
        //   1193: aload_0
        //   1194: getfield soTN : I
        //   1197: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1200: pop
        //   1201: aload_1
        //   1202: ldc_w ', ''
        //   1205: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1208: pop
        //   1209: aload_1
        //   1210: aload_0
        //   1211: getfield body : Ljava/lang/String;
        //   1214: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1217: pop
        //   1218: aload_1
        //   1219: ldc_w '',null,''
        //   1222: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1225: pop
        //   1226: aload_1
        //   1227: aload_0
        //   1228: getfield body : Ljava/lang/String;
        //   1231: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1234: pop
        //   1235: aload_1
        //   1236: ldc_w '', 'ko',0,0,0, null)'
        //   1239: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1242: pop
        //   1243: aload_1
        //   1244: invokevirtual toString : ()Ljava/lang/String;
        //   1247: astore_1
        //   1248: aload_0
        //   1249: getfield db : Ltamhoang/ldpro4/data/Database;
        //   1252: aload_1
        //   1253: invokevirtual QueryData : (Ljava/lang/String;)V
        //   1256: getstatic tamhoang/ldpro4/MainActivity.myDate : Ljava/lang/String;
        //   1259: invokestatic CheckDate : (Ljava/lang/String;)Z
        //   1262: ifeq -> 1558
        //   1265: aload_0
        //   1266: getfield db : Ltamhoang/ldpro4/data/Database;
        //   1269: astore #6
        //   1271: new java/lang/StringBuilder
        //   1274: astore_1
        //   1275: aload_1
        //   1276: invokespecial <init> : ()V
        //   1279: aload_1
        //   1280: ldc_w 'Select * from tbl_tinnhanS WHERE ngay_nhan = ''
        //   1283: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1286: pop
        //   1287: aload_1
        //   1288: aload_0
        //   1289: getfield mNgayNhan : Ljava/lang/String;
        //   1292: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1295: pop
        //   1296: aload_1
        //   1297: ldc_w '' AND so_dienthoai = ''
        //   1300: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1303: pop
        //   1304: aload_1
        //   1305: aload_0
        //   1306: getfield mSDT : Ljava/lang/String;
        //   1309: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1312: pop
        //   1313: aload_1
        //   1314: ldc_w '' AND so_tin_nhan = '
        //   1317: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1320: pop
        //   1321: aload_1
        //   1322: aload_0
        //   1323: getfield soTN : I
        //   1326: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1329: pop
        //   1330: aload_1
        //   1331: ldc_w ' AND type_kh = 1'
        //   1334: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1337: pop
        //   1338: aload #6
        //   1340: aload_1
        //   1341: invokevirtual toString : ()Ljava/lang/String;
        //   1344: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   1347: astore_1
        //   1348: aload_1
        //   1349: invokeinterface moveToFirst : ()Z
        //   1354: pop
        //   1355: aload_0
        //   1356: getfield db : Ltamhoang/ldpro4/data/Database;
        //   1359: aload_1
        //   1360: iconst_0
        //   1361: invokeinterface getInt : (I)I
        //   1366: iconst_1
        //   1367: invokevirtual Update_TinNhanGoc : (II)V
        //   1370: goto -> 1515
        //   1373: astore #6
        //   1375: aload_0
        //   1376: getfield db : Ltamhoang/ldpro4/data/Database;
        //   1379: astore #11
        //   1381: new java/lang/StringBuilder
        //   1384: astore #6
        //   1386: aload #6
        //   1388: invokespecial <init> : ()V
        //   1391: aload #6
        //   1393: ldc_w 'Update tbl_tinnhanS set phat_hien_loi = 'ko' WHERE id = '
        //   1396: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1399: pop
        //   1400: aload #6
        //   1402: aload_1
        //   1403: iconst_0
        //   1404: invokeinterface getInt : (I)I
        //   1409: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1412: pop
        //   1413: aload #11
        //   1415: aload #6
        //   1417: invokevirtual toString : ()Ljava/lang/String;
        //   1420: invokevirtual QueryData : (Ljava/lang/String;)V
        //   1423: aload_0
        //   1424: getfield db : Ltamhoang/ldpro4/data/Database;
        //   1427: astore #6
        //   1429: new java/lang/StringBuilder
        //   1432: astore #11
        //   1434: aload #11
        //   1436: invokespecial <init> : ()V
        //   1439: aload #11
        //   1441: ldc_w 'Delete From tbl_soctS WHERE ngay_nhan = ''
        //   1444: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1447: pop
        //   1448: aload #11
        //   1450: aload_0
        //   1451: getfield mNgayNhan : Ljava/lang/String;
        //   1454: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1457: pop
        //   1458: aload #11
        //   1460: ldc_w '' AND so_dienthoai = ''
        //   1463: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1466: pop
        //   1467: aload #11
        //   1469: aload_0
        //   1470: getfield mSDT : Ljava/lang/String;
        //   1473: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1476: pop
        //   1477: aload #11
        //   1479: ldc_w '' AND so_tin_nhan = '
        //   1482: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1485: pop
        //   1486: aload #11
        //   1488: aload_0
        //   1489: getfield soTN : I
        //   1492: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1495: pop
        //   1496: aload #11
        //   1498: ldc_w ' AND type_kh = 1'
        //   1501: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1504: pop
        //   1505: aload #6
        //   1507: aload #11
        //   1509: invokevirtual toString : ()Ljava/lang/String;
        //   1512: invokevirtual QueryData : (Ljava/lang/String;)V
        //   1515: ldc_w '18:30'
        //   1518: invokestatic CheckTime : (Ljava/lang/String;)Z
        //   1521: ifne -> 1552
        //   1524: aload_0
        //   1525: getfield body : Ljava/lang/String;
        //   1528: ldc_w 'Tra lai'
        //   1531: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1534: iconst_m1
        //   1535: if_icmpne -> 1552
        //   1538: aload_0
        //   1539: getfield db : Ltamhoang/ldpro4/data/Database;
        //   1542: aload_1
        //   1543: iconst_0
        //   1544: invokeinterface getInt : (I)I
        //   1549: invokevirtual Gui_Tin_Nhan : (I)V
        //   1552: aload_1
        //   1553: invokeinterface close : ()V
        //   1558: aload #7
        //   1560: ifnull -> 1584
        //   1563: aload #7
        //   1565: invokeinterface isClosed : ()Z
        //   1570: ifne -> 1584
        //   1573: aload #7
        //   1575: invokeinterface close : ()V
        //   1580: goto -> 1584
        //   1583: astore_1
        //   1584: goto -> 1919
        //   1587: new java/lang/StringBuilder
        //   1590: astore_1
        //   1591: aload_1
        //   1592: invokespecial <init> : ()V
        //   1595: aload_1
        //   1596: ldc_w 'Select max(so_tin_nhan) from tbl_tinnhanS WHERE ngay_nhan = ''
        //   1599: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1602: pop
        //   1603: aload_1
        //   1604: aload_0
        //   1605: getfield mNgayNhan : Ljava/lang/String;
        //   1608: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1611: pop
        //   1612: aload_1
        //   1613: ldc_w '' AND so_dienthoai = ''
        //   1616: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1619: pop
        //   1620: aload_1
        //   1621: aload_0
        //   1622: getfield mSDT : Ljava/lang/String;
        //   1625: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1628: pop
        //   1629: aload_1
        //   1630: ldc_w '' AND type_kh = 1'
        //   1633: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1636: pop
        //   1637: aload_1
        //   1638: invokevirtual toString : ()Ljava/lang/String;
        //   1641: astore_1
        //   1642: aload_0
        //   1643: getfield db : Ltamhoang/ldpro4/data/Database;
        //   1646: aload_1
        //   1647: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   1650: astore_1
        //   1651: aload_1
        //   1652: invokeinterface moveToFirst : ()Z
        //   1657: pop
        //   1658: aload_0
        //   1659: aload_2
        //   1660: iconst_0
        //   1661: invokeinterface getString : (I)Ljava/lang/String;
        //   1666: putfield Ten_KH : Ljava/lang/String;
        //   1669: aload_0
        //   1670: aload_1
        //   1671: iconst_0
        //   1672: invokeinterface getInt : (I)I
        //   1677: iconst_1
        //   1678: iadd
        //   1679: putfield soTN : I
        //   1682: new java/lang/StringBuilder
        //   1685: astore #7
        //   1687: aload #7
        //   1689: invokespecial <init> : ()V
        //   1692: aload #7
        //   1694: ldc_w 'Insert Into tbl_tinnhanS values (null, ''
        //   1697: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1700: pop
        //   1701: aload #7
        //   1703: aload_0
        //   1704: getfield mNgayNhan : Ljava/lang/String;
        //   1707: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1710: pop
        //   1711: aload #7
        //   1713: ldc_w '', ''
        //   1716: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1719: pop
        //   1720: aload #7
        //   1722: aload_0
        //   1723: getfield mGionhan : Ljava/lang/String;
        //   1726: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1729: pop
        //   1730: aload #7
        //   1732: ldc_w '',1, ''
        //   1735: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1738: pop
        //   1739: aload #7
        //   1741: aload_0
        //   1742: getfield Ten_KH : Ljava/lang/String;
        //   1745: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1748: pop
        //   1749: aload #7
        //   1751: ldc_w '', ''
        //   1754: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1757: pop
        //   1758: aload #7
        //   1760: aload_2
        //   1761: iconst_1
        //   1762: invokeinterface getString : (I)Ljava/lang/String;
        //   1767: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1770: pop
        //   1771: aload #7
        //   1773: ldc_w '','sms', '
        //   1776: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1779: pop
        //   1780: aload #7
        //   1782: aload_0
        //   1783: getfield soTN : I
        //   1786: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1789: pop
        //   1790: aload #7
        //   1792: ldc_w ', ''
        //   1795: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1798: pop
        //   1799: aload #7
        //   1801: aload_0
        //   1802: getfield body : Ljava/lang/String;
        //   1805: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1808: pop
        //   1809: aload #7
        //   1811: ldc_w '',null,''
        //   1814: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1817: pop
        //   1818: aload #7
        //   1820: aload_0
        //   1821: getfield body : Ljava/lang/String;
        //   1824: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1827: pop
        //   1828: aload #7
        //   1830: ldc_w '', 'Hginhsnull)'
        //   1833: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1836: pop
        //   1837: aload #7
        //   1839: invokevirtual toString : ()Ljava/lang/String;
        //   1842: astore #7
        //   1844: aload_0
        //   1845: getfield db : Ltamhoang/ldpro4/data/Database;
        //   1848: aload #7
        //   1850: invokevirtual QueryData : (Ljava/lang/String;)V
        //   1853: aload_1
        //   1854: ifnull -> 1872
        //   1857: aload_1
        //   1858: invokeinterface isClosed : ()Z
        //   1863: ifne -> 1872
        //   1866: aload_1
        //   1867: invokeinterface close : ()V
        //   1872: ldc_w '18:30'
        //   1875: invokestatic CheckTime : (Ljava/lang/String;)Z
        //   1878: ifne -> 1911
        //   1881: getstatic tamhoang/ldpro4/MainActivity.jSon_Setting : Lorg/json/JSONObject;
        //   1884: ldc_w 'tin_qua_gio'
        //   1887: invokevirtual getInt : (Ljava/lang/String;)I
        //   1890: iconst_1
        //   1891: if_icmpne -> 1911
        //   1894: aload_0
        //   1895: getfield db : Ltamhoang/ldpro4/data/Database;
        //   1898: aload_2
        //   1899: iconst_1
        //   1900: invokeinterface getString : (I)Ljava/lang/String;
        //   1905: ldc_w 'Hginh
        //   1908: invokevirtual SendSMS : (Ljava/lang/String;Ljava/lang/String;)V
        //   1911: goto -> 1919
        //   1914: astore_1
        //   1915: goto -> 1923
        //   1918: astore_1
        //   1919: goto -> 1930
        //   1922: astore_1
        //   1923: aload_1
        //   1924: invokevirtual printStackTrace : ()V
        //   1927: goto -> 1930
        //   1930: aload_2
        //   1931: ifnull -> 1956
        //   1934: aload_2
        //   1935: invokeinterface isClosed : ()Z
        //   1940: ifne -> 1956
        //   1943: aload_2
        //   1944: invokeinterface close : ()V
        //   1949: goto -> 1956
        //   1952: astore_1
        //   1953: goto -> 1967
        //   1956: goto -> 1963
        //   1959: astore_1
        //   1960: goto -> 1967
        //   1963: goto -> 1967
        //   1966: astore_1
        //   1967: return
        // Exception table:
        //   from	to	target	type
        //   224	231	1966	java/lang/Exception
        //   237	244	308	java/lang/Exception
        //   247	255	308	java/lang/Exception
        //   258	283	308	java/lang/Exception
        //   289	305	308	java/lang/Exception
        //   312	328	1966	java/lang/Exception
        //   328	358	1966	java/lang/Exception
        //   363	400	308	java/lang/Exception
        //   400	416	419	java/lang/Exception
        //   420	432	1966	java/lang/Exception
        //   438	476	308	java/lang/Exception
        //   481	495	1966	java/lang/Exception
        //   495	499	1966	java/lang/Exception
        //   503	515	703	org/json/JSONException
        //   503	515	308	java/lang/Exception
        //   519	523	703	org/json/JSONException
        //   519	523	308	java/lang/Exception
        //   527	531	703	org/json/JSONException
        //   527	531	308	java/lang/Exception
        //   535	543	703	org/json/JSONException
        //   535	543	308	java/lang/Exception
        //   547	556	703	org/json/JSONException
        //   547	556	308	java/lang/Exception
        //   560	568	703	org/json/JSONException
        //   560	568	308	java/lang/Exception
        //   572	581	703	org/json/JSONException
        //   572	581	308	java/lang/Exception
        //   585	593	703	org/json/JSONException
        //   585	593	308	java/lang/Exception
        //   597	606	703	org/json/JSONException
        //   597	606	308	java/lang/Exception
        //   610	618	703	org/json/JSONException
        //   610	618	308	java/lang/Exception
        //   622	627	703	org/json/JSONException
        //   622	627	308	java/lang/Exception
        //   631	640	703	org/json/JSONException
        //   631	640	308	java/lang/Exception
        //   644	651	703	org/json/JSONException
        //   644	651	308	java/lang/Exception
        //   659	668	703	org/json/JSONException
        //   659	668	308	java/lang/Exception
        //   678	687	703	org/json/JSONException
        //   678	687	308	java/lang/Exception
        //   691	697	703	org/json/JSONException
        //   691	697	308	java/lang/Exception
        //   704	708	1966	java/lang/Exception
        //   711	765	1959	java/lang/Exception
        //   770	774	822	org/json/JSONException
        //   770	774	308	java/lang/Exception
        //   774	815	818	org/json/JSONException
        //   774	815	308	java/lang/Exception
        //   823	827	1959	java/lang/Exception
        //   827	842	1922	org/json/JSONException
        //   827	842	1959	java/lang/Exception
        //   847	1102	1583	android/database/SQLException
        //   847	1102	1914	org/json/JSONException
        //   847	1102	1952	java/lang/Exception
        //   1105	1248	1583	android/database/SQLException
        //   1105	1248	1914	org/json/JSONException
        //   1105	1248	1952	java/lang/Exception
        //   1248	1355	1583	android/database/SQLException
        //   1248	1355	1914	org/json/JSONException
        //   1248	1355	1952	java/lang/Exception
        //   1355	1370	1373	java/lang/Exception
        //   1355	1370	1583	android/database/SQLException
        //   1355	1370	1914	org/json/JSONException
        //   1375	1515	1583	android/database/SQLException
        //   1375	1515	1914	org/json/JSONException
        //   1375	1515	1952	java/lang/Exception
        //   1515	1552	1583	android/database/SQLException
        //   1515	1552	1914	org/json/JSONException
        //   1515	1552	1952	java/lang/Exception
        //   1552	1558	1583	android/database/SQLException
        //   1552	1558	1914	org/json/JSONException
        //   1552	1558	1952	java/lang/Exception
        //   1563	1580	1583	android/database/SQLException
        //   1563	1580	1914	org/json/JSONException
        //   1563	1580	1952	java/lang/Exception
        //   1587	1853	1918	android/database/SQLException
        //   1587	1853	1914	org/json/JSONException
        //   1587	1853	1952	java/lang/Exception
        //   1857	1872	1918	android/database/SQLException
        //   1857	1872	1914	org/json/JSONException
        //   1857	1872	1952	java/lang/Exception
        //   1872	1911	1918	android/database/SQLException
        //   1872	1911	1914	org/json/JSONException
        //   1872	1911	1952	java/lang/Exception
        //   1923	1927	1952	java/lang/Exception
        //   1934	1949	1952	java/lang/Exception
    }
}
