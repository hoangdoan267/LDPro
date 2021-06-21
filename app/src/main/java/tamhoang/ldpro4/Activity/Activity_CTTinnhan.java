package tamhoang.ldpro4.Activity;

import android.os.Bundle;
import tamhoang.ldpro4.Congthuc.BaseToolBarActivity;
import tamhoang.ldpro4.data.Database;

public class Activity_CTTinnhan extends BaseToolBarActivity {
    Database db;

    String id = "";

    String type_kh = "";

    protected int getLayoutId() {
        return 2131427365;
    }

    protected void onCreate(Bundle paramBundle) {
        // Byte code:
        //   0: ldc 'loa'
        //   2: astore_2
        //   3: ldc 'deb'
        //   5: astore_3
        //   6: ldc 'xn'
        //   8: astore #4
        //   10: ldc 'det'
        //   12: astore #5
        //   14: aload_0
        //   15: aload_1
        //   16: invokespecial onCreate : (Landroid/os/Bundle;)V
        //   19: aload_0
        //   20: ldc 2131427365
        //   22: invokevirtual setContentView : (I)V
        //   25: dconst_0
        //   26: dstore #6
        //   28: dconst_0
        //   29: dstore #8
        //   31: aload_0
        //   32: aload_0
        //   33: invokevirtual getIntent : ()Landroid/content/Intent;
        //   36: ldc 'm_ID'
        //   38: invokevirtual getStringExtra : (Ljava/lang/String;)Ljava/lang/String;
        //   41: putfield id : Ljava/lang/String;
        //   44: aload_0
        //   45: new tamhoang/ldpro4/data/Database
        //   48: dup
        //   49: aload_0
        //   50: invokespecial <init> : (Landroid/content/Context;)V
        //   53: putfield db : Ltamhoang/ldpro4/data/Database;
        //   56: new java/text/DecimalFormat
        //   59: dup
        //   60: ldc '###,###'
        //   62: invokespecial <init> : (Ljava/lang/String;)V
        //   65: astore #10
        //   67: aload_0
        //   68: getfield db : Ltamhoang/ldpro4/data/Database;
        //   71: astore #11
        //   73: new java/lang/StringBuilder
        //   76: dup
        //   77: invokespecial <init> : ()V
        //   80: astore_1
        //   81: aload_1
        //   82: ldc 'Select * From tbl_tinnhanS Where ID = '
        //   84: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   87: pop
        //   88: aload_1
        //   89: aload_0
        //   90: getfield id : Ljava/lang/String;
        //   93: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   96: pop
        //   97: aload #11
        //   99: aload_1
        //   100: invokevirtual toString : ()Ljava/lang/String;
        //   103: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   106: astore #11
        //   108: aload #11
        //   110: invokeinterface moveToFirst : ()Z
        //   115: pop
        //   116: new java/lang/StringBuilder
        //   119: dup
        //   120: invokespecial <init> : ()V
        //   123: astore_1
        //   124: aload_1
        //   125: ldc 'SElect CASE \\nWHEN the_loai = 'xi' And length(so_chon) = 5 THEN 'xi2' \\nWHEN the_loai = 'xi' And length(so_chon) = 8 THEN 'xi3' \\nWHEN the_loai = 'xi' And length(so_chon) = 11 THEN 'xi4' \\nWHEN the_loai = 'xia' And length(so_chon) = 5 THEN 'xia2' \\nWHEN the_loai = 'xia' And length(so_chon) = 8 THEN 'xia3' \\nWHEN the_loai = 'xia' And length(so_chon) = 11 THEN 'xia4' \\nELSE the_loai END theloai, sum(diem), sum(diem*so_nhay) as An\\n, sum (tong_tien)/1000 as kq \\n, sum(Ket_qua)/1000 as tienCuoi\\n From tbl_soctS \\n Where ten_kh = ''
        //   127: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   130: pop
        //   131: aload_1
        //   132: aload #11
        //   134: iconst_4
        //   135: invokeinterface getString : (I)Ljava/lang/String;
        //   140: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   143: pop
        //   144: aload_1
        //   145: ldc '' and ngay_nhan = ''
        //   147: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   150: pop
        //   151: aload_1
        //   152: aload #11
        //   154: iconst_1
        //   155: invokeinterface getString : (I)Ljava/lang/String;
        //   160: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   163: pop
        //   164: aload_1
        //   165: ldc '' and so_tin_nhan = '
        //   167: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: pop
        //   171: aload_1
        //   172: aload #11
        //   174: bipush #7
        //   176: invokeinterface getString : (I)Ljava/lang/String;
        //   181: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   184: pop
        //   185: aload_1
        //   186: ldc ' Group by theloai'
        //   188: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   191: pop
        //   192: aload_1
        //   193: invokevirtual toString : ()Ljava/lang/String;
        //   196: astore #12
        //   198: aload_0
        //   199: getfield db : Ltamhoang/ldpro4/data/Database;
        //   202: aload #12
        //   204: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   207: astore #13
        //   209: new org/json/JSONObject
        //   212: dup
        //   213: invokespecial <init> : ()V
        //   216: astore_1
        //   217: aload #13
        //   219: invokeinterface moveToNext : ()Z
        //   224: istore #14
        //   226: iload #14
        //   228: ifeq -> 346
        //   231: new org/json/JSONObject
        //   234: astore #15
        //   236: aload #15
        //   238: invokespecial <init> : ()V
        //   241: aload #15
        //   243: ldc 'diem'
        //   245: aload #13
        //   247: iconst_1
        //   248: invokeinterface getDouble : (I)D
        //   253: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   256: pop
        //   257: aload #15
        //   259: ldc 'diem_an'
        //   261: aload #13
        //   263: iconst_2
        //   264: invokeinterface getDouble : (I)D
        //   269: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   272: pop
        //   273: aload #15
        //   275: ldc 'tong_tien'
        //   277: aload #13
        //   279: iconst_3
        //   280: invokeinterface getDouble : (I)D
        //   285: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   288: pop
        //   289: aload #15
        //   291: ldc 'ket_qua'
        //   293: aload #13
        //   295: iconst_4
        //   296: invokeinterface getDouble : (I)D
        //   301: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   304: pop
        //   305: aload #13
        //   307: iconst_0
        //   308: invokeinterface getString : (I)Ljava/lang/String;
        //   313: astore #16
        //   315: aload #15
        //   317: invokevirtual toString : ()Ljava/lang/String;
        //   320: astore #15
        //   322: dload #8
        //   324: dstore #17
        //   326: aload_1
        //   327: aload #16
        //   329: aload #15
        //   331: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   334: pop
        //   335: goto -> 217
        //   338: astore_1
        //   339: dload #8
        //   341: dstore #17
        //   343: goto -> 4180
        //   346: aload_1
        //   347: astore #12
        //   349: aload #12
        //   351: astore #16
        //   353: dload #8
        //   355: dstore #17
        //   357: aload #12
        //   359: ldc 'dea'
        //   361: invokevirtual has : (Ljava/lang/String;)Z
        //   364: istore #14
        //   366: iload #14
        //   368: ifeq -> 574
        //   371: dload #8
        //   373: dstore #17
        //   375: aload_0
        //   376: ldc 2131231038
        //   378: invokevirtual findViewById : (I)Landroid/view/View;
        //   381: checkcast android/widget/LinearLayout
        //   384: iconst_0
        //   385: invokevirtual setVisibility : (I)V
        //   388: dload #8
        //   390: dstore #17
        //   392: aload_0
        //   393: ldc 2131231426
        //   395: invokevirtual findViewById : (I)Landroid/view/View;
        //   398: checkcast android/widget/TextView
        //   401: astore #15
        //   403: dload #8
        //   405: dstore #17
        //   407: new org/json/JSONObject
        //   410: astore #16
        //   412: dload #8
        //   414: dstore #17
        //   416: aload #16
        //   418: aload #12
        //   420: ldc 'dea'
        //   422: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   425: invokespecial <init> : (Ljava/lang/String;)V
        //   428: dload #8
        //   430: dstore #17
        //   432: aload #15
        //   434: aload #10
        //   436: aload #16
        //   438: ldc 'diem'
        //   440: invokevirtual getDouble : (Ljava/lang/String;)D
        //   443: invokevirtual format : (D)Ljava/lang/String;
        //   446: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   449: dload #8
        //   451: dstore #17
        //   453: aload_0
        //   454: ldc 2131231341
        //   456: invokevirtual findViewById : (I)Landroid/view/View;
        //   459: checkcast android/widget/TextView
        //   462: aload #10
        //   464: aload #16
        //   466: ldc 'diem_an'
        //   468: invokevirtual getDouble : (Ljava/lang/String;)D
        //   471: invokevirtual format : (D)Ljava/lang/String;
        //   474: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   477: dload #8
        //   479: dstore #17
        //   481: aload_0
        //   482: ldc 2131231478
        //   484: invokevirtual findViewById : (I)Landroid/view/View;
        //   487: checkcast android/widget/TextView
        //   490: aload #10
        //   492: aload #16
        //   494: ldc 'tong_tien'
        //   496: invokevirtual getDouble : (Ljava/lang/String;)D
        //   499: invokevirtual format : (D)Ljava/lang/String;
        //   502: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   505: dload #8
        //   507: dstore #17
        //   509: aload_0
        //   510: ldc 2131231494
        //   512: invokevirtual findViewById : (I)Landroid/view/View;
        //   515: checkcast android/widget/TextView
        //   518: aload #10
        //   520: aload #16
        //   522: ldc 'ket_qua'
        //   524: invokevirtual getDouble : (Ljava/lang/String;)D
        //   527: invokevirtual format : (D)Ljava/lang/String;
        //   530: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   533: dload #8
        //   535: dstore #17
        //   537: dconst_0
        //   538: aload #16
        //   540: ldc 'tong_tien'
        //   542: invokevirtual getDouble : (Ljava/lang/String;)D
        //   545: dadd
        //   546: dstore #6
        //   548: dload #8
        //   550: dstore #17
        //   552: aload #16
        //   554: ldc 'ket_qua'
        //   556: invokevirtual getDouble : (Ljava/lang/String;)D
        //   559: dstore #19
        //   561: dconst_0
        //   562: dload #19
        //   564: dadd
        //   565: dstore #8
        //   567: goto -> 574
        //   570: astore_1
        //   571: goto -> 4180
        //   574: aload #12
        //   576: astore #16
        //   578: dload #8
        //   580: dstore #17
        //   582: aload #12
        //   584: ldc 'dec'
        //   586: invokevirtual has : (Ljava/lang/String;)Z
        //   589: istore #14
        //   591: dload #6
        //   593: dstore #21
        //   595: dload #8
        //   597: dstore #19
        //   599: iload #14
        //   601: ifeq -> 794
        //   604: dload #8
        //   606: dstore #17
        //   608: aload_0
        //   609: ldc 2131231040
        //   611: invokevirtual findViewById : (I)Landroid/view/View;
        //   614: checkcast android/widget/LinearLayout
        //   617: iconst_0
        //   618: invokevirtual setVisibility : (I)V
        //   621: dload #8
        //   623: dstore #17
        //   625: new org/json/JSONObject
        //   628: astore #16
        //   630: dload #8
        //   632: dstore #17
        //   634: aload #16
        //   636: aload #12
        //   638: ldc 'dec'
        //   640: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   643: invokespecial <init> : (Ljava/lang/String;)V
        //   646: dload #8
        //   648: dstore #17
        //   650: aload_0
        //   651: ldc 2131231427
        //   653: invokevirtual findViewById : (I)Landroid/view/View;
        //   656: checkcast android/widget/TextView
        //   659: aload #10
        //   661: aload #16
        //   663: ldc 'diem'
        //   665: invokevirtual getDouble : (Ljava/lang/String;)D
        //   668: invokevirtual format : (D)Ljava/lang/String;
        //   671: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   674: dload #8
        //   676: dstore #17
        //   678: aload_0
        //   679: ldc 2131231342
        //   681: invokevirtual findViewById : (I)Landroid/view/View;
        //   684: checkcast android/widget/TextView
        //   687: aload #10
        //   689: aload #16
        //   691: ldc 'diem_an'
        //   693: invokevirtual getDouble : (Ljava/lang/String;)D
        //   696: invokevirtual format : (D)Ljava/lang/String;
        //   699: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   702: dload #8
        //   704: dstore #17
        //   706: aload_0
        //   707: ldc 2131231479
        //   709: invokevirtual findViewById : (I)Landroid/view/View;
        //   712: checkcast android/widget/TextView
        //   715: aload #10
        //   717: aload #16
        //   719: ldc 'tong_tien'
        //   721: invokevirtual getDouble : (Ljava/lang/String;)D
        //   724: invokevirtual format : (D)Ljava/lang/String;
        //   727: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   730: dload #8
        //   732: dstore #17
        //   734: aload_0
        //   735: ldc 2131231495
        //   737: invokevirtual findViewById : (I)Landroid/view/View;
        //   740: checkcast android/widget/TextView
        //   743: aload #10
        //   745: aload #16
        //   747: ldc 'ket_qua'
        //   749: invokevirtual getDouble : (Ljava/lang/String;)D
        //   752: invokevirtual format : (D)Ljava/lang/String;
        //   755: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   758: dload #8
        //   760: dstore #17
        //   762: dload #6
        //   764: aload #16
        //   766: ldc 'tong_tien'
        //   768: invokevirtual getDouble : (Ljava/lang/String;)D
        //   771: dadd
        //   772: dstore #21
        //   774: dload #8
        //   776: dstore #17
        //   778: aload #16
        //   780: ldc 'ket_qua'
        //   782: invokevirtual getDouble : (Ljava/lang/String;)D
        //   785: dstore #19
        //   787: dload #8
        //   789: dload #19
        //   791: dadd
        //   792: dstore #19
        //   794: aload #12
        //   796: astore #16
        //   798: dload #19
        //   800: dstore #17
        //   802: aload #12
        //   804: ldc 'ded'
        //   806: invokevirtual has : (Ljava/lang/String;)Z
        //   809: istore #14
        //   811: dload #21
        //   813: dstore #6
        //   815: dload #19
        //   817: dstore #8
        //   819: iload #14
        //   821: ifeq -> 1014
        //   824: dload #19
        //   826: dstore #17
        //   828: aload_0
        //   829: ldc 2131231041
        //   831: invokevirtual findViewById : (I)Landroid/view/View;
        //   834: checkcast android/widget/LinearLayout
        //   837: iconst_0
        //   838: invokevirtual setVisibility : (I)V
        //   841: dload #19
        //   843: dstore #17
        //   845: new org/json/JSONObject
        //   848: astore #16
        //   850: dload #19
        //   852: dstore #17
        //   854: aload #16
        //   856: aload #12
        //   858: ldc 'ded'
        //   860: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   863: invokespecial <init> : (Ljava/lang/String;)V
        //   866: dload #19
        //   868: dstore #17
        //   870: aload_0
        //   871: ldc 2131231428
        //   873: invokevirtual findViewById : (I)Landroid/view/View;
        //   876: checkcast android/widget/TextView
        //   879: aload #10
        //   881: aload #16
        //   883: ldc 'diem'
        //   885: invokevirtual getDouble : (Ljava/lang/String;)D
        //   888: invokevirtual format : (D)Ljava/lang/String;
        //   891: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   894: dload #19
        //   896: dstore #17
        //   898: aload_0
        //   899: ldc 2131231343
        //   901: invokevirtual findViewById : (I)Landroid/view/View;
        //   904: checkcast android/widget/TextView
        //   907: aload #10
        //   909: aload #16
        //   911: ldc 'diem_an'
        //   913: invokevirtual getDouble : (Ljava/lang/String;)D
        //   916: invokevirtual format : (D)Ljava/lang/String;
        //   919: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   922: dload #19
        //   924: dstore #17
        //   926: aload_0
        //   927: ldc 2131231480
        //   929: invokevirtual findViewById : (I)Landroid/view/View;
        //   932: checkcast android/widget/TextView
        //   935: aload #10
        //   937: aload #16
        //   939: ldc 'tong_tien'
        //   941: invokevirtual getDouble : (Ljava/lang/String;)D
        //   944: invokevirtual format : (D)Ljava/lang/String;
        //   947: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   950: dload #19
        //   952: dstore #17
        //   954: aload_0
        //   955: ldc 2131231496
        //   957: invokevirtual findViewById : (I)Landroid/view/View;
        //   960: checkcast android/widget/TextView
        //   963: aload #10
        //   965: aload #16
        //   967: ldc 'ket_qua'
        //   969: invokevirtual getDouble : (Ljava/lang/String;)D
        //   972: invokevirtual format : (D)Ljava/lang/String;
        //   975: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   978: dload #19
        //   980: dstore #17
        //   982: dload #21
        //   984: aload #16
        //   986: ldc 'tong_tien'
        //   988: invokevirtual getDouble : (Ljava/lang/String;)D
        //   991: dadd
        //   992: dstore #6
        //   994: dload #19
        //   996: dstore #17
        //   998: aload #16
        //   1000: ldc 'ket_qua'
        //   1002: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1005: dstore #8
        //   1007: dload #19
        //   1009: dload #8
        //   1011: dadd
        //   1012: dstore #8
        //   1014: aload #12
        //   1016: astore #16
        //   1018: dload #8
        //   1020: dstore #17
        //   1022: aload #12
        //   1024: aload #5
        //   1026: invokevirtual has : (Ljava/lang/String;)Z
        //   1029: istore #14
        //   1031: dload #6
        //   1033: dstore #21
        //   1035: dload #8
        //   1037: dstore #19
        //   1039: iload #14
        //   1041: ifeq -> 1234
        //   1044: dload #8
        //   1046: dstore #17
        //   1048: aload_0
        //   1049: ldc 2131231042
        //   1051: invokevirtual findViewById : (I)Landroid/view/View;
        //   1054: checkcast android/widget/LinearLayout
        //   1057: iconst_0
        //   1058: invokevirtual setVisibility : (I)V
        //   1061: dload #8
        //   1063: dstore #17
        //   1065: new org/json/JSONObject
        //   1068: astore #16
        //   1070: dload #8
        //   1072: dstore #17
        //   1074: aload #16
        //   1076: aload #12
        //   1078: aload #5
        //   1080: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1083: invokespecial <init> : (Ljava/lang/String;)V
        //   1086: dload #8
        //   1088: dstore #17
        //   1090: aload_0
        //   1091: ldc 2131231429
        //   1093: invokevirtual findViewById : (I)Landroid/view/View;
        //   1096: checkcast android/widget/TextView
        //   1099: aload #10
        //   1101: aload #16
        //   1103: ldc 'diem'
        //   1105: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1108: invokevirtual format : (D)Ljava/lang/String;
        //   1111: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1114: dload #8
        //   1116: dstore #17
        //   1118: aload_0
        //   1119: ldc 2131231344
        //   1121: invokevirtual findViewById : (I)Landroid/view/View;
        //   1124: checkcast android/widget/TextView
        //   1127: aload #10
        //   1129: aload #16
        //   1131: ldc 'diem_an'
        //   1133: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1136: invokevirtual format : (D)Ljava/lang/String;
        //   1139: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1142: dload #8
        //   1144: dstore #17
        //   1146: aload_0
        //   1147: ldc 2131231481
        //   1149: invokevirtual findViewById : (I)Landroid/view/View;
        //   1152: checkcast android/widget/TextView
        //   1155: aload #10
        //   1157: aload #16
        //   1159: ldc 'tong_tien'
        //   1161: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1164: invokevirtual format : (D)Ljava/lang/String;
        //   1167: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1170: dload #8
        //   1172: dstore #17
        //   1174: aload_0
        //   1175: ldc 2131231497
        //   1177: invokevirtual findViewById : (I)Landroid/view/View;
        //   1180: checkcast android/widget/TextView
        //   1183: aload #10
        //   1185: aload #16
        //   1187: ldc 'ket_qua'
        //   1189: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1192: invokevirtual format : (D)Ljava/lang/String;
        //   1195: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1198: dload #8
        //   1200: dstore #17
        //   1202: dload #6
        //   1204: aload #16
        //   1206: ldc 'tong_tien'
        //   1208: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1211: dadd
        //   1212: dstore #21
        //   1214: dload #8
        //   1216: dstore #17
        //   1218: aload #16
        //   1220: ldc 'ket_qua'
        //   1222: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1225: dstore #19
        //   1227: dload #8
        //   1229: dload #19
        //   1231: dadd
        //   1232: dstore #19
        //   1234: aload #12
        //   1236: astore #16
        //   1238: dload #19
        //   1240: dstore #17
        //   1242: aload #12
        //   1244: aload #4
        //   1246: invokevirtual has : (Ljava/lang/String;)Z
        //   1249: istore #14
        //   1251: dload #21
        //   1253: dstore #6
        //   1255: dload #19
        //   1257: dstore #8
        //   1259: iload #14
        //   1261: ifeq -> 1454
        //   1264: dload #19
        //   1266: dstore #17
        //   1268: aload_0
        //   1269: ldc 2131231035
        //   1271: invokevirtual findViewById : (I)Landroid/view/View;
        //   1274: checkcast android/widget/LinearLayout
        //   1277: iconst_0
        //   1278: invokevirtual setVisibility : (I)V
        //   1281: dload #19
        //   1283: dstore #17
        //   1285: new org/json/JSONObject
        //   1288: astore #5
        //   1290: dload #19
        //   1292: dstore #17
        //   1294: aload #5
        //   1296: aload #12
        //   1298: aload #4
        //   1300: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1303: invokespecial <init> : (Ljava/lang/String;)V
        //   1306: dload #19
        //   1308: dstore #17
        //   1310: aload_0
        //   1311: ldc 2131231463
        //   1313: invokevirtual findViewById : (I)Landroid/view/View;
        //   1316: checkcast android/widget/TextView
        //   1319: aload #10
        //   1321: aload #5
        //   1323: ldc 'diem'
        //   1325: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1328: invokevirtual format : (D)Ljava/lang/String;
        //   1331: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1334: dload #19
        //   1336: dstore #17
        //   1338: aload_0
        //   1339: ldc 2131231414
        //   1341: invokevirtual findViewById : (I)Landroid/view/View;
        //   1344: checkcast android/widget/TextView
        //   1347: aload #10
        //   1349: aload #5
        //   1351: ldc 'diem_an'
        //   1353: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1356: invokevirtual format : (D)Ljava/lang/String;
        //   1359: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1362: dload #19
        //   1364: dstore #17
        //   1366: aload_0
        //   1367: ldc 2131231521
        //   1369: invokevirtual findViewById : (I)Landroid/view/View;
        //   1372: checkcast android/widget/TextView
        //   1375: aload #10
        //   1377: aload #5
        //   1379: ldc 'tong_tien'
        //   1381: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1384: invokevirtual format : (D)Ljava/lang/String;
        //   1387: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1390: dload #19
        //   1392: dstore #17
        //   1394: aload_0
        //   1395: ldc 2131231511
        //   1397: invokevirtual findViewById : (I)Landroid/view/View;
        //   1400: checkcast android/widget/TextView
        //   1403: aload #10
        //   1405: aload #5
        //   1407: ldc 'ket_qua'
        //   1409: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1412: invokevirtual format : (D)Ljava/lang/String;
        //   1415: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1418: dload #19
        //   1420: dstore #17
        //   1422: dload #21
        //   1424: aload #5
        //   1426: ldc 'tong_tien'
        //   1428: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1431: dadd
        //   1432: dstore #6
        //   1434: dload #19
        //   1436: dstore #17
        //   1438: aload #5
        //   1440: ldc 'ket_qua'
        //   1442: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1445: dstore #8
        //   1447: dload #19
        //   1449: dload #8
        //   1451: dadd
        //   1452: dstore #8
        //   1454: aload #12
        //   1456: astore #16
        //   1458: dload #8
        //   1460: dstore #17
        //   1462: aload #12
        //   1464: aload_3
        //   1465: invokevirtual has : (Ljava/lang/String;)Z
        //   1468: istore #14
        //   1470: dload #6
        //   1472: dstore #21
        //   1474: dload #8
        //   1476: dstore #19
        //   1478: iload #14
        //   1480: ifeq -> 1672
        //   1483: dload #8
        //   1485: dstore #17
        //   1487: aload_0
        //   1488: ldc 2131231039
        //   1490: invokevirtual findViewById : (I)Landroid/view/View;
        //   1493: checkcast android/widget/LinearLayout
        //   1496: iconst_0
        //   1497: invokevirtual setVisibility : (I)V
        //   1500: dload #8
        //   1502: dstore #17
        //   1504: new org/json/JSONObject
        //   1507: astore #4
        //   1509: dload #8
        //   1511: dstore #17
        //   1513: aload #4
        //   1515: aload #12
        //   1517: aload_3
        //   1518: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1521: invokespecial <init> : (Ljava/lang/String;)V
        //   1524: dload #8
        //   1526: dstore #17
        //   1528: aload_0
        //   1529: ldc 2131231425
        //   1531: invokevirtual findViewById : (I)Landroid/view/View;
        //   1534: checkcast android/widget/TextView
        //   1537: aload #10
        //   1539: aload #4
        //   1541: ldc 'diem'
        //   1543: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1546: invokevirtual format : (D)Ljava/lang/String;
        //   1549: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1552: dload #8
        //   1554: dstore #17
        //   1556: aload_0
        //   1557: ldc 2131231340
        //   1559: invokevirtual findViewById : (I)Landroid/view/View;
        //   1562: checkcast android/widget/TextView
        //   1565: aload #10
        //   1567: aload #4
        //   1569: ldc 'diem_an'
        //   1571: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1574: invokevirtual format : (D)Ljava/lang/String;
        //   1577: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1580: dload #8
        //   1582: dstore #17
        //   1584: aload_0
        //   1585: ldc 2131231477
        //   1587: invokevirtual findViewById : (I)Landroid/view/View;
        //   1590: checkcast android/widget/TextView
        //   1593: aload #10
        //   1595: aload #4
        //   1597: ldc 'tong_tien'
        //   1599: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1602: invokevirtual format : (D)Ljava/lang/String;
        //   1605: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1608: dload #8
        //   1610: dstore #17
        //   1612: aload_0
        //   1613: ldc 2131231493
        //   1615: invokevirtual findViewById : (I)Landroid/view/View;
        //   1618: checkcast android/widget/TextView
        //   1621: aload #10
        //   1623: aload #4
        //   1625: ldc 'ket_qua'
        //   1627: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1630: invokevirtual format : (D)Ljava/lang/String;
        //   1633: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1636: dload #8
        //   1638: dstore #17
        //   1640: dload #6
        //   1642: aload #4
        //   1644: ldc 'tong_tien'
        //   1646: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1649: dadd
        //   1650: dstore #21
        //   1652: dload #8
        //   1654: dstore #17
        //   1656: aload #4
        //   1658: ldc 'ket_qua'
        //   1660: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1663: dstore #19
        //   1665: dload #8
        //   1667: dload #19
        //   1669: dadd
        //   1670: dstore #19
        //   1672: aload #12
        //   1674: astore #16
        //   1676: dload #19
        //   1678: dstore #17
        //   1680: aload #12
        //   1682: aload_2
        //   1683: invokevirtual has : (Ljava/lang/String;)Z
        //   1686: istore #14
        //   1688: dload #21
        //   1690: dstore #23
        //   1692: dload #19
        //   1694: dstore #8
        //   1696: iload #14
        //   1698: ifeq -> 1890
        //   1701: dload #19
        //   1703: dstore #17
        //   1705: aload_0
        //   1706: ldc 2131231044
        //   1708: invokevirtual findViewById : (I)Landroid/view/View;
        //   1711: checkcast android/widget/LinearLayout
        //   1714: iconst_0
        //   1715: invokevirtual setVisibility : (I)V
        //   1718: dload #19
        //   1720: dstore #17
        //   1722: new org/json/JSONObject
        //   1725: astore #4
        //   1727: dload #19
        //   1729: dstore #17
        //   1731: aload #4
        //   1733: aload #12
        //   1735: aload_2
        //   1736: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1739: invokespecial <init> : (Ljava/lang/String;)V
        //   1742: dload #19
        //   1744: dstore #17
        //   1746: aload_0
        //   1747: ldc 2131231455
        //   1749: invokevirtual findViewById : (I)Landroid/view/View;
        //   1752: checkcast android/widget/TextView
        //   1755: aload #10
        //   1757: aload #4
        //   1759: ldc 'diem'
        //   1761: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1764: invokevirtual format : (D)Ljava/lang/String;
        //   1767: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1770: dload #19
        //   1772: dstore #17
        //   1774: aload_0
        //   1775: ldc 2131231406
        //   1777: invokevirtual findViewById : (I)Landroid/view/View;
        //   1780: checkcast android/widget/TextView
        //   1783: aload #10
        //   1785: aload #4
        //   1787: ldc 'diem_an'
        //   1789: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1792: invokevirtual format : (D)Ljava/lang/String;
        //   1795: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1798: dload #19
        //   1800: dstore #17
        //   1802: aload_0
        //   1803: ldc 2131231488
        //   1805: invokevirtual findViewById : (I)Landroid/view/View;
        //   1808: checkcast android/widget/TextView
        //   1811: aload #10
        //   1813: aload #4
        //   1815: ldc 'tong_tien'
        //   1817: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1820: invokevirtual format : (D)Ljava/lang/String;
        //   1823: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1826: dload #19
        //   1828: dstore #17
        //   1830: aload_0
        //   1831: ldc 2131231503
        //   1833: invokevirtual findViewById : (I)Landroid/view/View;
        //   1836: checkcast android/widget/TextView
        //   1839: aload #10
        //   1841: aload #4
        //   1843: ldc 'ket_qua'
        //   1845: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1848: invokevirtual format : (D)Ljava/lang/String;
        //   1851: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1854: dload #19
        //   1856: dstore #17
        //   1858: dload #21
        //   1860: aload #4
        //   1862: ldc 'tong_tien'
        //   1864: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1867: dadd
        //   1868: dstore #23
        //   1870: dload #19
        //   1872: dstore #17
        //   1874: aload #4
        //   1876: ldc 'ket_qua'
        //   1878: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1881: dstore #8
        //   1883: dload #19
        //   1885: dload #8
        //   1887: dadd
        //   1888: dstore #8
        //   1890: aload #12
        //   1892: astore #16
        //   1894: dload #8
        //   1896: dstore #17
        //   1898: aload #12
        //   1900: ldc 'bca'
        //   1902: invokevirtual has : (Ljava/lang/String;)Z
        //   1905: istore #14
        //   1907: dload #23
        //   1909: dstore #6
        //   1911: dload #8
        //   1913: dstore #19
        //   1915: iload #14
        //   1917: ifeq -> 2110
        //   1920: dload #8
        //   1922: dstore #17
        //   1924: aload_0
        //   1925: ldc 2131231036
        //   1927: invokevirtual findViewById : (I)Landroid/view/View;
        //   1930: checkcast android/widget/LinearLayout
        //   1933: iconst_0
        //   1934: invokevirtual setVisibility : (I)V
        //   1937: dload #8
        //   1939: dstore #17
        //   1941: new org/json/JSONObject
        //   1944: astore #4
        //   1946: dload #8
        //   1948: dstore #17
        //   1950: aload #4
        //   1952: aload #12
        //   1954: ldc 'bca'
        //   1956: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1959: invokespecial <init> : (Ljava/lang/String;)V
        //   1962: dload #8
        //   1964: dstore #17
        //   1966: aload_0
        //   1967: ldc 2131231448
        //   1969: invokevirtual findViewById : (I)Landroid/view/View;
        //   1972: checkcast android/widget/TextView
        //   1975: aload #10
        //   1977: aload #4
        //   1979: ldc 'diem'
        //   1981: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1984: invokevirtual format : (D)Ljava/lang/String;
        //   1987: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1990: dload #8
        //   1992: dstore #17
        //   1994: aload_0
        //   1995: ldc 2131231399
        //   1997: invokevirtual findViewById : (I)Landroid/view/View;
        //   2000: checkcast android/widget/TextView
        //   2003: aload #10
        //   2005: aload #4
        //   2007: ldc 'diem_an'
        //   2009: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2012: invokevirtual format : (D)Ljava/lang/String;
        //   2015: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2018: dload #8
        //   2020: dstore #17
        //   2022: aload_0
        //   2023: ldc 2131231476
        //   2025: invokevirtual findViewById : (I)Landroid/view/View;
        //   2028: checkcast android/widget/TextView
        //   2031: aload #10
        //   2033: aload #4
        //   2035: ldc 'tong_tien'
        //   2037: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2040: invokevirtual format : (D)Ljava/lang/String;
        //   2043: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2046: dload #8
        //   2048: dstore #17
        //   2050: aload_0
        //   2051: ldc 2131231492
        //   2053: invokevirtual findViewById : (I)Landroid/view/View;
        //   2056: checkcast android/widget/TextView
        //   2059: aload #10
        //   2061: aload #4
        //   2063: ldc 'ket_qua'
        //   2065: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2068: invokevirtual format : (D)Ljava/lang/String;
        //   2071: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2074: dload #8
        //   2076: dstore #17
        //   2078: dload #23
        //   2080: aload #4
        //   2082: ldc 'tong_tien'
        //   2084: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2087: dadd
        //   2088: dstore #6
        //   2090: dload #8
        //   2092: dstore #17
        //   2094: aload #4
        //   2096: ldc 'ket_qua'
        //   2098: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2101: dstore #19
        //   2103: dload #8
        //   2105: dload #19
        //   2107: dadd
        //   2108: dstore #19
        //   2110: aload #12
        //   2112: astore #16
        //   2114: dload #19
        //   2116: dstore #17
        //   2118: aload #12
        //   2120: ldc 'lo'
        //   2122: invokevirtual has : (Ljava/lang/String;)Z
        //   2125: istore #14
        //   2127: dload #6
        //   2129: dstore #23
        //   2131: dload #19
        //   2133: dstore #8
        //   2135: iload #14
        //   2137: ifeq -> 2330
        //   2140: dload #19
        //   2142: dstore #17
        //   2144: aload_0
        //   2145: ldc 2131231043
        //   2147: invokevirtual findViewById : (I)Landroid/view/View;
        //   2150: checkcast android/widget/LinearLayout
        //   2153: iconst_0
        //   2154: invokevirtual setVisibility : (I)V
        //   2157: dload #19
        //   2159: dstore #17
        //   2161: new org/json/JSONObject
        //   2164: astore #4
        //   2166: dload #19
        //   2168: dstore #17
        //   2170: aload #4
        //   2172: aload #12
        //   2174: ldc 'lo'
        //   2176: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2179: invokespecial <init> : (Ljava/lang/String;)V
        //   2182: dload #19
        //   2184: dstore #17
        //   2186: aload_0
        //   2187: ldc 2131231454
        //   2189: invokevirtual findViewById : (I)Landroid/view/View;
        //   2192: checkcast android/widget/TextView
        //   2195: aload #10
        //   2197: aload #4
        //   2199: ldc 'diem'
        //   2201: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2204: invokevirtual format : (D)Ljava/lang/String;
        //   2207: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2210: dload #19
        //   2212: dstore #17
        //   2214: aload_0
        //   2215: ldc 2131231405
        //   2217: invokevirtual findViewById : (I)Landroid/view/View;
        //   2220: checkcast android/widget/TextView
        //   2223: aload #10
        //   2225: aload #4
        //   2227: ldc 'diem_an'
        //   2229: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2232: invokevirtual format : (D)Ljava/lang/String;
        //   2235: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2238: dload #19
        //   2240: dstore #17
        //   2242: aload_0
        //   2243: ldc 2131231487
        //   2245: invokevirtual findViewById : (I)Landroid/view/View;
        //   2248: checkcast android/widget/TextView
        //   2251: aload #10
        //   2253: aload #4
        //   2255: ldc 'tong_tien'
        //   2257: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2260: invokevirtual format : (D)Ljava/lang/String;
        //   2263: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2266: dload #19
        //   2268: dstore #17
        //   2270: aload_0
        //   2271: ldc 2131231502
        //   2273: invokevirtual findViewById : (I)Landroid/view/View;
        //   2276: checkcast android/widget/TextView
        //   2279: aload #10
        //   2281: aload #4
        //   2283: ldc 'ket_qua'
        //   2285: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2288: invokevirtual format : (D)Ljava/lang/String;
        //   2291: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2294: dload #19
        //   2296: dstore #17
        //   2298: dload #6
        //   2300: aload #4
        //   2302: ldc 'tong_tien'
        //   2304: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2307: dadd
        //   2308: dstore #23
        //   2310: dload #19
        //   2312: dstore #17
        //   2314: aload #4
        //   2316: ldc 'ket_qua'
        //   2318: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2321: dstore #8
        //   2323: dload #19
        //   2325: dload #8
        //   2327: dadd
        //   2328: dstore #8
        //   2330: aload #12
        //   2332: astore #16
        //   2334: dload #8
        //   2336: dstore #17
        //   2338: aload #12
        //   2340: ldc 'xi2'
        //   2342: invokevirtual has : (Ljava/lang/String;)Z
        //   2345: istore #14
        //   2347: dload #23
        //   2349: dstore #21
        //   2351: dload #8
        //   2353: dstore #19
        //   2355: iload #14
        //   2357: ifeq -> 2550
        //   2360: dload #8
        //   2362: dstore #17
        //   2364: aload_0
        //   2365: ldc 2131231074
        //   2367: invokevirtual findViewById : (I)Landroid/view/View;
        //   2370: checkcast android/widget/LinearLayout
        //   2373: iconst_0
        //   2374: invokevirtual setVisibility : (I)V
        //   2377: dload #8
        //   2379: dstore #17
        //   2381: new org/json/JSONObject
        //   2384: astore #4
        //   2386: dload #8
        //   2388: dstore #17
        //   2390: aload #4
        //   2392: aload #12
        //   2394: ldc 'xi2'
        //   2396: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2399: invokespecial <init> : (Ljava/lang/String;)V
        //   2402: dload #8
        //   2404: dstore #17
        //   2406: aload_0
        //   2407: ldc 2131231457
        //   2409: invokevirtual findViewById : (I)Landroid/view/View;
        //   2412: checkcast android/widget/TextView
        //   2415: aload #10
        //   2417: aload #4
        //   2419: ldc 'diem'
        //   2421: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2424: invokevirtual format : (D)Ljava/lang/String;
        //   2427: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2430: dload #8
        //   2432: dstore #17
        //   2434: aload_0
        //   2435: ldc 2131231408
        //   2437: invokevirtual findViewById : (I)Landroid/view/View;
        //   2440: checkcast android/widget/TextView
        //   2443: aload #10
        //   2445: aload #4
        //   2447: ldc 'diem_an'
        //   2449: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2452: invokevirtual format : (D)Ljava/lang/String;
        //   2455: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2458: dload #8
        //   2460: dstore #17
        //   2462: aload_0
        //   2463: ldc 2131231515
        //   2465: invokevirtual findViewById : (I)Landroid/view/View;
        //   2468: checkcast android/widget/TextView
        //   2471: aload #10
        //   2473: aload #4
        //   2475: ldc 'tong_tien'
        //   2477: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2480: invokevirtual format : (D)Ljava/lang/String;
        //   2483: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2486: dload #8
        //   2488: dstore #17
        //   2490: aload_0
        //   2491: ldc 2131231505
        //   2493: invokevirtual findViewById : (I)Landroid/view/View;
        //   2496: checkcast android/widget/TextView
        //   2499: aload #10
        //   2501: aload #4
        //   2503: ldc 'ket_qua'
        //   2505: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2508: invokevirtual format : (D)Ljava/lang/String;
        //   2511: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2514: dload #8
        //   2516: dstore #17
        //   2518: dload #23
        //   2520: aload #4
        //   2522: ldc 'tong_tien'
        //   2524: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2527: dadd
        //   2528: dstore #21
        //   2530: dload #8
        //   2532: dstore #17
        //   2534: aload #4
        //   2536: ldc 'ket_qua'
        //   2538: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2541: dstore #19
        //   2543: dload #8
        //   2545: dload #19
        //   2547: dadd
        //   2548: dstore #19
        //   2550: aload #12
        //   2552: astore #16
        //   2554: dload #19
        //   2556: dstore #17
        //   2558: aload #12
        //   2560: ldc 'xi3'
        //   2562: invokevirtual has : (Ljava/lang/String;)Z
        //   2565: istore #14
        //   2567: dload #21
        //   2569: dstore #6
        //   2571: dload #19
        //   2573: dstore #8
        //   2575: iload #14
        //   2577: ifeq -> 2770
        //   2580: dload #19
        //   2582: dstore #17
        //   2584: aload_0
        //   2585: ldc 2131231075
        //   2587: invokevirtual findViewById : (I)Landroid/view/View;
        //   2590: checkcast android/widget/LinearLayout
        //   2593: iconst_0
        //   2594: invokevirtual setVisibility : (I)V
        //   2597: dload #19
        //   2599: dstore #17
        //   2601: new org/json/JSONObject
        //   2604: astore #4
        //   2606: dload #19
        //   2608: dstore #17
        //   2610: aload #4
        //   2612: aload #12
        //   2614: ldc 'xi3'
        //   2616: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2619: invokespecial <init> : (Ljava/lang/String;)V
        //   2622: dload #19
        //   2624: dstore #17
        //   2626: aload_0
        //   2627: ldc 2131231458
        //   2629: invokevirtual findViewById : (I)Landroid/view/View;
        //   2632: checkcast android/widget/TextView
        //   2635: aload #10
        //   2637: aload #4
        //   2639: ldc 'diem'
        //   2641: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2644: invokevirtual format : (D)Ljava/lang/String;
        //   2647: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2650: dload #19
        //   2652: dstore #17
        //   2654: aload_0
        //   2655: ldc 2131231409
        //   2657: invokevirtual findViewById : (I)Landroid/view/View;
        //   2660: checkcast android/widget/TextView
        //   2663: aload #10
        //   2665: aload #4
        //   2667: ldc 'diem_an'
        //   2669: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2672: invokevirtual format : (D)Ljava/lang/String;
        //   2675: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2678: dload #19
        //   2680: dstore #17
        //   2682: aload_0
        //   2683: ldc 2131231516
        //   2685: invokevirtual findViewById : (I)Landroid/view/View;
        //   2688: checkcast android/widget/TextView
        //   2691: aload #10
        //   2693: aload #4
        //   2695: ldc 'tong_tien'
        //   2697: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2700: invokevirtual format : (D)Ljava/lang/String;
        //   2703: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2706: dload #19
        //   2708: dstore #17
        //   2710: aload_0
        //   2711: ldc 2131231506
        //   2713: invokevirtual findViewById : (I)Landroid/view/View;
        //   2716: checkcast android/widget/TextView
        //   2719: aload #10
        //   2721: aload #4
        //   2723: ldc 'ket_qua'
        //   2725: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2728: invokevirtual format : (D)Ljava/lang/String;
        //   2731: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2734: dload #19
        //   2736: dstore #17
        //   2738: dload #21
        //   2740: aload #4
        //   2742: ldc 'tong_tien'
        //   2744: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2747: dadd
        //   2748: dstore #6
        //   2750: dload #19
        //   2752: dstore #17
        //   2754: aload #4
        //   2756: ldc 'ket_qua'
        //   2758: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2761: dstore #8
        //   2763: dload #19
        //   2765: dload #8
        //   2767: dadd
        //   2768: dstore #8
        //   2770: aload #12
        //   2772: astore #16
        //   2774: dload #8
        //   2776: dstore #17
        //   2778: aload #12
        //   2780: ldc 'xi4'
        //   2782: invokevirtual has : (Ljava/lang/String;)Z
        //   2785: istore #14
        //   2787: dload #6
        //   2789: dstore #21
        //   2791: dload #8
        //   2793: dstore #19
        //   2795: iload #14
        //   2797: ifeq -> 2990
        //   2800: dload #8
        //   2802: dstore #17
        //   2804: aload_0
        //   2805: ldc 2131231076
        //   2807: invokevirtual findViewById : (I)Landroid/view/View;
        //   2810: checkcast android/widget/LinearLayout
        //   2813: iconst_0
        //   2814: invokevirtual setVisibility : (I)V
        //   2817: dload #8
        //   2819: dstore #17
        //   2821: new org/json/JSONObject
        //   2824: astore #4
        //   2826: dload #8
        //   2828: dstore #17
        //   2830: aload #4
        //   2832: aload #12
        //   2834: ldc 'xi4'
        //   2836: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2839: invokespecial <init> : (Ljava/lang/String;)V
        //   2842: dload #8
        //   2844: dstore #17
        //   2846: aload_0
        //   2847: ldc 2131231459
        //   2849: invokevirtual findViewById : (I)Landroid/view/View;
        //   2852: checkcast android/widget/TextView
        //   2855: aload #10
        //   2857: aload #4
        //   2859: ldc 'diem'
        //   2861: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2864: invokevirtual format : (D)Ljava/lang/String;
        //   2867: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2870: dload #8
        //   2872: dstore #17
        //   2874: aload_0
        //   2875: ldc 2131231410
        //   2877: invokevirtual findViewById : (I)Landroid/view/View;
        //   2880: checkcast android/widget/TextView
        //   2883: aload #10
        //   2885: aload #4
        //   2887: ldc 'diem_an'
        //   2889: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2892: invokevirtual format : (D)Ljava/lang/String;
        //   2895: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2898: dload #8
        //   2900: dstore #17
        //   2902: aload_0
        //   2903: ldc 2131231517
        //   2905: invokevirtual findViewById : (I)Landroid/view/View;
        //   2908: checkcast android/widget/TextView
        //   2911: aload #10
        //   2913: aload #4
        //   2915: ldc 'tong_tien'
        //   2917: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2920: invokevirtual format : (D)Ljava/lang/String;
        //   2923: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2926: dload #8
        //   2928: dstore #17
        //   2930: aload_0
        //   2931: ldc 2131231507
        //   2933: invokevirtual findViewById : (I)Landroid/view/View;
        //   2936: checkcast android/widget/TextView
        //   2939: aload #10
        //   2941: aload #4
        //   2943: ldc 'ket_qua'
        //   2945: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2948: invokevirtual format : (D)Ljava/lang/String;
        //   2951: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2954: dload #8
        //   2956: dstore #17
        //   2958: dload #6
        //   2960: aload #4
        //   2962: ldc 'tong_tien'
        //   2964: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2967: dadd
        //   2968: dstore #21
        //   2970: dload #8
        //   2972: dstore #17
        //   2974: aload #4
        //   2976: ldc 'ket_qua'
        //   2978: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2981: dstore #19
        //   2983: dload #8
        //   2985: dload #19
        //   2987: dadd
        //   2988: dstore #19
        //   2990: aload #12
        //   2992: astore #16
        //   2994: dload #19
        //   2996: dstore #17
        //   2998: aload #12
        //   3000: ldc 'xia2'
        //   3002: invokevirtual has : (Ljava/lang/String;)Z
        //   3005: istore #14
        //   3007: iload #14
        //   3009: ifeq -> 3201
        //   3012: aload_0
        //   3013: ldc 2131231077
        //   3015: invokevirtual findViewById : (I)Landroid/view/View;
        //   3018: checkcast android/widget/LinearLayout
        //   3021: iconst_0
        //   3022: invokevirtual setVisibility : (I)V
        //   3025: new org/json/JSONObject
        //   3028: astore #4
        //   3030: aload #4
        //   3032: aload #12
        //   3034: ldc 'xia2'
        //   3036: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3039: invokespecial <init> : (Ljava/lang/String;)V
        //   3042: aload_0
        //   3043: ldc 2131231460
        //   3045: invokevirtual findViewById : (I)Landroid/view/View;
        //   3048: checkcast android/widget/TextView
        //   3051: aload #10
        //   3053: aload #4
        //   3055: ldc 'diem'
        //   3057: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3060: invokevirtual format : (D)Ljava/lang/String;
        //   3063: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3066: aload_0
        //   3067: ldc 2131231411
        //   3069: invokevirtual findViewById : (I)Landroid/view/View;
        //   3072: checkcast android/widget/TextView
        //   3075: astore #5
        //   3077: dload #19
        //   3079: dstore #17
        //   3081: aload #5
        //   3083: aload #10
        //   3085: aload #4
        //   3087: ldc 'diem_an'
        //   3089: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3092: invokevirtual format : (D)Ljava/lang/String;
        //   3095: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3098: dload #19
        //   3100: dstore #17
        //   3102: aload_0
        //   3103: ldc 2131231518
        //   3105: invokevirtual findViewById : (I)Landroid/view/View;
        //   3108: checkcast android/widget/TextView
        //   3111: aload #10
        //   3113: aload #4
        //   3115: ldc 'tong_tien'
        //   3117: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3120: invokevirtual format : (D)Ljava/lang/String;
        //   3123: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3126: dload #19
        //   3128: dstore #17
        //   3130: aload_0
        //   3131: ldc 2131231508
        //   3133: invokevirtual findViewById : (I)Landroid/view/View;
        //   3136: checkcast android/widget/TextView
        //   3139: aload #10
        //   3141: aload #4
        //   3143: ldc 'ket_qua'
        //   3145: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3148: invokevirtual format : (D)Ljava/lang/String;
        //   3151: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3154: dload #19
        //   3156: dstore #17
        //   3158: dload #21
        //   3160: aload #4
        //   3162: ldc 'tong_tien'
        //   3164: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3167: dadd
        //   3168: dstore #8
        //   3170: dload #19
        //   3172: dstore #17
        //   3174: aload #4
        //   3176: ldc 'ket_qua'
        //   3178: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3181: dstore #6
        //   3183: dload #19
        //   3185: dload #6
        //   3187: dadd
        //   3188: dstore #17
        //   3190: goto -> 3209
        //   3193: astore_1
        //   3194: dload #19
        //   3196: dstore #17
        //   3198: goto -> 4180
        //   3201: dload #19
        //   3203: dstore #17
        //   3205: dload #21
        //   3207: dstore #8
        //   3209: dload #17
        //   3211: dstore #6
        //   3213: aload_1
        //   3214: ldc 'xia3'
        //   3216: invokevirtual has : (Ljava/lang/String;)Z
        //   3219: istore #14
        //   3221: iload #14
        //   3223: ifeq -> 3394
        //   3226: aload_0
        //   3227: ldc 2131231078
        //   3229: invokevirtual findViewById : (I)Landroid/view/View;
        //   3232: checkcast android/widget/LinearLayout
        //   3235: iconst_0
        //   3236: invokevirtual setVisibility : (I)V
        //   3239: new org/json/JSONObject
        //   3242: astore #4
        //   3244: aload #4
        //   3246: aload_1
        //   3247: ldc 'xia3'
        //   3249: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3252: invokespecial <init> : (Ljava/lang/String;)V
        //   3255: aload_0
        //   3256: ldc 2131231461
        //   3258: invokevirtual findViewById : (I)Landroid/view/View;
        //   3261: checkcast android/widget/TextView
        //   3264: aload #10
        //   3266: aload #4
        //   3268: ldc 'diem'
        //   3270: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3273: invokevirtual format : (D)Ljava/lang/String;
        //   3276: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3279: aload_0
        //   3280: ldc 2131231412
        //   3282: invokevirtual findViewById : (I)Landroid/view/View;
        //   3285: checkcast android/widget/TextView
        //   3288: astore #5
        //   3290: aload #5
        //   3292: aload #10
        //   3294: aload #4
        //   3296: ldc 'diem_an'
        //   3298: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3301: invokevirtual format : (D)Ljava/lang/String;
        //   3304: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3307: aload_0
        //   3308: ldc 2131231519
        //   3310: invokevirtual findViewById : (I)Landroid/view/View;
        //   3313: checkcast android/widget/TextView
        //   3316: aload #10
        //   3318: aload #4
        //   3320: ldc 'tong_tien'
        //   3322: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3325: invokevirtual format : (D)Ljava/lang/String;
        //   3328: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3331: aload_0
        //   3332: ldc 2131231509
        //   3334: invokevirtual findViewById : (I)Landroid/view/View;
        //   3337: checkcast android/widget/TextView
        //   3340: aload #10
        //   3342: aload #4
        //   3344: ldc 'ket_qua'
        //   3346: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3349: invokevirtual format : (D)Ljava/lang/String;
        //   3352: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3355: dload #8
        //   3357: aload #4
        //   3359: ldc 'tong_tien'
        //   3361: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3364: dadd
        //   3365: dstore #8
        //   3367: aload #4
        //   3369: ldc 'ket_qua'
        //   3371: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3374: dstore #19
        //   3376: dload #17
        //   3378: dload #19
        //   3380: dadd
        //   3381: dstore #19
        //   3383: goto -> 3398
        //   3386: astore_1
        //   3387: goto -> 4180
        //   3390: astore_1
        //   3391: goto -> 4180
        //   3394: dload #17
        //   3396: dstore #19
        //   3398: aload_1
        //   3399: astore #4
        //   3401: aload #4
        //   3403: astore #16
        //   3405: dload #19
        //   3407: dstore #17
        //   3409: aload #4
        //   3411: ldc 'xia4'
        //   3413: invokevirtual has : (Ljava/lang/String;)Z
        //   3416: istore #14
        //   3418: iload #14
        //   3420: ifeq -> 3612
        //   3423: aload_0
        //   3424: ldc 2131231079
        //   3426: invokevirtual findViewById : (I)Landroid/view/View;
        //   3429: checkcast android/widget/LinearLayout
        //   3432: iconst_0
        //   3433: invokevirtual setVisibility : (I)V
        //   3436: new org/json/JSONObject
        //   3439: astore #5
        //   3441: aload #5
        //   3443: aload #4
        //   3445: ldc 'xia4'
        //   3447: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3450: invokespecial <init> : (Ljava/lang/String;)V
        //   3453: aload_0
        //   3454: ldc 2131231462
        //   3456: invokevirtual findViewById : (I)Landroid/view/View;
        //   3459: checkcast android/widget/TextView
        //   3462: aload #10
        //   3464: aload #5
        //   3466: ldc 'diem'
        //   3468: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3471: invokevirtual format : (D)Ljava/lang/String;
        //   3474: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3477: aload_0
        //   3478: ldc 2131231413
        //   3480: invokevirtual findViewById : (I)Landroid/view/View;
        //   3483: checkcast android/widget/TextView
        //   3486: astore #4
        //   3488: dload #19
        //   3490: dstore #17
        //   3492: aload #4
        //   3494: aload #10
        //   3496: aload #5
        //   3498: ldc 'diem_an'
        //   3500: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3503: invokevirtual format : (D)Ljava/lang/String;
        //   3506: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3509: dload #19
        //   3511: dstore #17
        //   3513: aload_0
        //   3514: ldc 2131231520
        //   3516: invokevirtual findViewById : (I)Landroid/view/View;
        //   3519: checkcast android/widget/TextView
        //   3522: aload #10
        //   3524: aload #5
        //   3526: ldc 'tong_tien'
        //   3528: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3531: invokevirtual format : (D)Ljava/lang/String;
        //   3534: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3537: dload #19
        //   3539: dstore #17
        //   3541: aload_0
        //   3542: ldc 2131231510
        //   3544: invokevirtual findViewById : (I)Landroid/view/View;
        //   3547: checkcast android/widget/TextView
        //   3550: aload #10
        //   3552: aload #5
        //   3554: ldc 'ket_qua'
        //   3556: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3559: invokevirtual format : (D)Ljava/lang/String;
        //   3562: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3565: dload #19
        //   3567: dstore #17
        //   3569: dload #8
        //   3571: aload #5
        //   3573: ldc 'tong_tien'
        //   3575: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3578: dadd
        //   3579: dstore #8
        //   3581: dload #19
        //   3583: dstore #17
        //   3585: aload #5
        //   3587: ldc 'ket_qua'
        //   3589: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3592: dstore #6
        //   3594: dload #19
        //   3596: dload #6
        //   3598: dadd
        //   3599: dstore #19
        //   3601: goto -> 3612
        //   3604: astore_1
        //   3605: dload #19
        //   3607: dstore #17
        //   3609: goto -> 3198
        //   3612: dload #19
        //   3614: dstore #6
        //   3616: aload_1
        //   3617: ldc_w 'bc'
        //   3620: invokevirtual has : (Ljava/lang/String;)Z
        //   3623: istore #14
        //   3625: iload #14
        //   3627: ifeq -> 3832
        //   3630: dload #19
        //   3632: dstore #17
        //   3634: aload_0
        //   3635: ldc_w 2131231059
        //   3638: invokevirtual findViewById : (I)Landroid/view/View;
        //   3641: checkcast android/widget/LinearLayout
        //   3644: iconst_0
        //   3645: invokevirtual setVisibility : (I)V
        //   3648: dload #19
        //   3650: dstore #17
        //   3652: new org/json/JSONObject
        //   3655: astore #4
        //   3657: dload #19
        //   3659: dstore #17
        //   3661: aload #4
        //   3663: aload_1
        //   3664: ldc_w 'bc'
        //   3667: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3670: invokespecial <init> : (Ljava/lang/String;)V
        //   3673: dload #19
        //   3675: dstore #17
        //   3677: aload_0
        //   3678: ldc_w 2131231447
        //   3681: invokevirtual findViewById : (I)Landroid/view/View;
        //   3684: checkcast android/widget/TextView
        //   3687: aload #10
        //   3689: aload #4
        //   3691: ldc 'diem'
        //   3693: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3696: invokevirtual format : (D)Ljava/lang/String;
        //   3699: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3702: dload #19
        //   3704: dstore #17
        //   3706: aload_0
        //   3707: ldc_w 2131231398
        //   3710: invokevirtual findViewById : (I)Landroid/view/View;
        //   3713: checkcast android/widget/TextView
        //   3716: aload #10
        //   3718: aload #4
        //   3720: ldc 'diem_an'
        //   3722: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3725: invokevirtual format : (D)Ljava/lang/String;
        //   3728: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3731: dload #19
        //   3733: dstore #17
        //   3735: aload_0
        //   3736: ldc_w 2131231475
        //   3739: invokevirtual findViewById : (I)Landroid/view/View;
        //   3742: checkcast android/widget/TextView
        //   3745: aload #10
        //   3747: aload #4
        //   3749: ldc 'tong_tien'
        //   3751: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3754: invokevirtual format : (D)Ljava/lang/String;
        //   3757: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3760: dload #19
        //   3762: dstore #17
        //   3764: aload_0
        //   3765: ldc_w 2131231491
        //   3768: invokevirtual findViewById : (I)Landroid/view/View;
        //   3771: checkcast android/widget/TextView
        //   3774: aload #10
        //   3776: aload #4
        //   3778: ldc 'ket_qua'
        //   3780: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3783: invokevirtual format : (D)Ljava/lang/String;
        //   3786: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3789: dload #19
        //   3791: dstore #17
        //   3793: dload #8
        //   3795: aload #4
        //   3797: ldc 'tong_tien'
        //   3799: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3802: dadd
        //   3803: dstore #8
        //   3805: dload #19
        //   3807: dstore #17
        //   3809: aload #4
        //   3811: ldc 'ket_qua'
        //   3813: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3816: dstore #6
        //   3818: dload #19
        //   3820: dload #6
        //   3822: dadd
        //   3823: dstore #17
        //   3825: goto -> 3836
        //   3828: astore_1
        //   3829: goto -> 4180
        //   3832: dload #19
        //   3834: dstore #17
        //   3836: aload_0
        //   3837: ldc_w 2131231513
        //   3840: invokevirtual findViewById : (I)Landroid/view/View;
        //   3843: checkcast android/widget/TextView
        //   3846: astore #5
        //   3848: aload #5
        //   3850: aload #10
        //   3852: dload #8
        //   3854: invokevirtual format : (D)Ljava/lang/String;
        //   3857: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3860: aload_0
        //   3861: ldc_w 2131231490
        //   3864: invokevirtual findViewById : (I)Landroid/view/View;
        //   3867: checkcast android/widget/TextView
        //   3870: astore_2
        //   3871: aload_2
        //   3872: aload #10
        //   3874: dload #17
        //   3876: invokevirtual format : (D)Ljava/lang/String;
        //   3879: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3882: aload_0
        //   3883: ldc_w 2131231522
        //   3886: invokevirtual findViewById : (I)Landroid/view/View;
        //   3889: checkcast android/widget/TextView
        //   3892: astore #4
        //   3894: aload #11
        //   3896: astore_1
        //   3897: aload #4
        //   3899: aload_1
        //   3900: bipush #8
        //   3902: invokeinterface getString : (I)Ljava/lang/String;
        //   3907: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3910: aload_0
        //   3911: ldc_w 2131231486
        //   3914: invokevirtual findViewById : (I)Landroid/view/View;
        //   3917: checkcast android/widget/TextView
        //   3920: aload_1
        //   3921: iconst_4
        //   3922: invokeinterface getString : (I)Ljava/lang/String;
        //   3927: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3930: aload_0
        //   3931: ldc_w 2131231512
        //   3934: invokevirtual findViewById : (I)Landroid/view/View;
        //   3937: checkcast android/widget/TextView
        //   3940: aload_1
        //   3941: bipush #7
        //   3943: invokeinterface getString : (I)Ljava/lang/String;
        //   3948: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3951: aload_0
        //   3952: ldc_w 2131231489
        //   3955: invokevirtual findViewById : (I)Landroid/view/View;
        //   3958: checkcast android/widget/TextView
        //   3961: astore_3
        //   3962: aload_3
        //   3963: aload_1
        //   3964: iconst_2
        //   3965: invokeinterface getString : (I)Ljava/lang/String;
        //   3970: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3973: aload_0
        //   3974: ldc_w 2131231471
        //   3977: invokevirtual findViewById : (I)Landroid/view/View;
        //   3980: checkcast android/widget/TextView
        //   3983: astore_3
        //   3984: aload_1
        //   3985: bipush #10
        //   3987: invokeinterface getString : (I)Ljava/lang/String;
        //   3992: astore_1
        //   3993: new android/text/SpannableString
        //   3996: astore #12
        //   3998: aload #12
        //   4000: aload_1
        //   4001: invokespecial <init> : (Ljava/lang/CharSequence;)V
        //   4004: iconst_0
        //   4005: istore #25
        //   4007: iload #25
        //   4009: aload_1
        //   4010: invokevirtual length : ()I
        //   4013: iconst_1
        //   4014: isub
        //   4015: if_icmpge -> 4138
        //   4018: aload_1
        //   4019: iload #25
        //   4021: iload #25
        //   4023: iconst_2
        //   4024: iadd
        //   4025: invokevirtual substring : (II)Ljava/lang/String;
        //   4028: ldc_w '*'
        //   4031: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4034: iconst_m1
        //   4035: if_icmple -> 4132
        //   4038: iload #25
        //   4040: istore #26
        //   4042: iload #26
        //   4044: ifle -> 4129
        //   4047: aload_1
        //   4048: iload #26
        //   4050: iload #26
        //   4052: iconst_1
        //   4053: iadd
        //   4054: invokevirtual substring : (II)Ljava/lang/String;
        //   4057: ldc_w ','
        //   4060: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4063: iconst_m1
        //   4064: if_icmpgt -> 4126
        //   4067: aload_1
        //   4068: iload #26
        //   4070: iload #26
        //   4072: iconst_1
        //   4073: iadd
        //   4074: invokevirtual substring : (II)Ljava/lang/String;
        //   4077: ldc_w ':'
        //   4080: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4083: iconst_m1
        //   4084: if_icmple -> 4090
        //   4087: goto -> 4129
        //   4090: new android/text/style/ForegroundColorSpan
        //   4093: astore #16
        //   4095: aload #16
        //   4097: ldc_w -65536
        //   4100: invokespecial <init> : (I)V
        //   4103: aload #12
        //   4105: aload #16
        //   4107: iload #26
        //   4109: iload #25
        //   4111: iconst_1
        //   4112: iadd
        //   4113: bipush #33
        //   4115: invokeinterface setSpan : (Ljava/lang/Object;III)V
        //   4120: iinc #26, -1
        //   4123: goto -> 4042
        //   4126: goto -> 4129
        //   4129: goto -> 4132
        //   4132: iinc #25, 1
        //   4135: goto -> 4007
        //   4138: aload_3
        //   4139: aload #12
        //   4141: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   4144: goto -> 4184
        //   4147: astore_1
        //   4148: goto -> 4180
        //   4151: astore_1
        //   4152: goto -> 4180
        //   4155: astore_1
        //   4156: goto -> 4180
        //   4159: astore_1
        //   4160: goto -> 4180
        //   4163: astore_1
        //   4164: dload #6
        //   4166: dstore #17
        //   4168: goto -> 4180
        //   4171: astore_1
        //   4172: goto -> 4180
        //   4175: astore_1
        //   4176: dload #8
        //   4178: dstore #17
        //   4180: aload_1
        //   4181: invokevirtual printStackTrace : ()V
        //   4184: aload #11
        //   4186: invokeinterface close : ()V
        //   4191: aload #13
        //   4193: invokeinterface close : ()V
        //   4198: return
        // Exception table:
        //   from	to	target	type
        //   217	226	4175	org/json/JSONException
        //   231	322	338	org/json/JSONException
        //   326	335	570	org/json/JSONException
        //   357	366	4171	org/json/JSONException
        //   375	388	570	org/json/JSONException
        //   392	403	570	org/json/JSONException
        //   407	412	570	org/json/JSONException
        //   416	428	570	org/json/JSONException
        //   432	449	570	org/json/JSONException
        //   453	477	570	org/json/JSONException
        //   481	505	570	org/json/JSONException
        //   509	533	570	org/json/JSONException
        //   537	548	570	org/json/JSONException
        //   552	561	570	org/json/JSONException
        //   582	591	4171	org/json/JSONException
        //   608	621	570	org/json/JSONException
        //   625	630	570	org/json/JSONException
        //   634	646	570	org/json/JSONException
        //   650	674	570	org/json/JSONException
        //   678	702	570	org/json/JSONException
        //   706	730	570	org/json/JSONException
        //   734	758	570	org/json/JSONException
        //   762	774	570	org/json/JSONException
        //   778	787	570	org/json/JSONException
        //   802	811	4171	org/json/JSONException
        //   828	841	570	org/json/JSONException
        //   845	850	570	org/json/JSONException
        //   854	866	570	org/json/JSONException
        //   870	894	570	org/json/JSONException
        //   898	922	570	org/json/JSONException
        //   926	950	570	org/json/JSONException
        //   954	978	570	org/json/JSONException
        //   982	994	570	org/json/JSONException
        //   998	1007	570	org/json/JSONException
        //   1022	1031	4171	org/json/JSONException
        //   1048	1061	570	org/json/JSONException
        //   1065	1070	570	org/json/JSONException
        //   1074	1086	570	org/json/JSONException
        //   1090	1114	570	org/json/JSONException
        //   1118	1142	570	org/json/JSONException
        //   1146	1170	570	org/json/JSONException
        //   1174	1198	570	org/json/JSONException
        //   1202	1214	570	org/json/JSONException
        //   1218	1227	570	org/json/JSONException
        //   1242	1251	4171	org/json/JSONException
        //   1268	1281	570	org/json/JSONException
        //   1285	1290	570	org/json/JSONException
        //   1294	1306	570	org/json/JSONException
        //   1310	1334	570	org/json/JSONException
        //   1338	1362	570	org/json/JSONException
        //   1366	1390	570	org/json/JSONException
        //   1394	1418	570	org/json/JSONException
        //   1422	1434	570	org/json/JSONException
        //   1438	1447	570	org/json/JSONException
        //   1462	1470	4171	org/json/JSONException
        //   1487	1500	570	org/json/JSONException
        //   1504	1509	570	org/json/JSONException
        //   1513	1524	570	org/json/JSONException
        //   1528	1552	570	org/json/JSONException
        //   1556	1580	570	org/json/JSONException
        //   1584	1608	570	org/json/JSONException
        //   1612	1636	570	org/json/JSONException
        //   1640	1652	570	org/json/JSONException
        //   1656	1665	570	org/json/JSONException
        //   1680	1688	4171	org/json/JSONException
        //   1705	1718	570	org/json/JSONException
        //   1722	1727	570	org/json/JSONException
        //   1731	1742	570	org/json/JSONException
        //   1746	1770	570	org/json/JSONException
        //   1774	1798	570	org/json/JSONException
        //   1802	1826	570	org/json/JSONException
        //   1830	1854	570	org/json/JSONException
        //   1858	1870	570	org/json/JSONException
        //   1874	1883	570	org/json/JSONException
        //   1898	1907	4171	org/json/JSONException
        //   1924	1937	570	org/json/JSONException
        //   1941	1946	570	org/json/JSONException
        //   1950	1962	570	org/json/JSONException
        //   1966	1990	570	org/json/JSONException
        //   1994	2018	570	org/json/JSONException
        //   2022	2046	570	org/json/JSONException
        //   2050	2074	570	org/json/JSONException
        //   2078	2090	570	org/json/JSONException
        //   2094	2103	570	org/json/JSONException
        //   2118	2127	4171	org/json/JSONException
        //   2144	2157	570	org/json/JSONException
        //   2161	2166	570	org/json/JSONException
        //   2170	2182	570	org/json/JSONException
        //   2186	2210	570	org/json/JSONException
        //   2214	2238	570	org/json/JSONException
        //   2242	2266	570	org/json/JSONException
        //   2270	2294	570	org/json/JSONException
        //   2298	2310	570	org/json/JSONException
        //   2314	2323	570	org/json/JSONException
        //   2338	2347	4171	org/json/JSONException
        //   2364	2377	570	org/json/JSONException
        //   2381	2386	570	org/json/JSONException
        //   2390	2402	570	org/json/JSONException
        //   2406	2430	570	org/json/JSONException
        //   2434	2458	570	org/json/JSONException
        //   2462	2486	570	org/json/JSONException
        //   2490	2514	570	org/json/JSONException
        //   2518	2530	570	org/json/JSONException
        //   2534	2543	570	org/json/JSONException
        //   2558	2567	4171	org/json/JSONException
        //   2584	2597	570	org/json/JSONException
        //   2601	2606	570	org/json/JSONException
        //   2610	2622	570	org/json/JSONException
        //   2626	2650	570	org/json/JSONException
        //   2654	2678	570	org/json/JSONException
        //   2682	2706	570	org/json/JSONException
        //   2710	2734	570	org/json/JSONException
        //   2738	2750	570	org/json/JSONException
        //   2754	2763	570	org/json/JSONException
        //   2778	2787	4171	org/json/JSONException
        //   2804	2817	570	org/json/JSONException
        //   2821	2826	570	org/json/JSONException
        //   2830	2842	570	org/json/JSONException
        //   2846	2870	570	org/json/JSONException
        //   2874	2898	570	org/json/JSONException
        //   2902	2926	570	org/json/JSONException
        //   2930	2954	570	org/json/JSONException
        //   2958	2970	570	org/json/JSONException
        //   2974	2983	570	org/json/JSONException
        //   2998	3007	4171	org/json/JSONException
        //   3012	3077	3193	org/json/JSONException
        //   3081	3098	3828	org/json/JSONException
        //   3102	3126	3828	org/json/JSONException
        //   3130	3154	3828	org/json/JSONException
        //   3158	3170	3828	org/json/JSONException
        //   3174	3183	3828	org/json/JSONException
        //   3213	3221	4163	org/json/JSONException
        //   3226	3290	3390	org/json/JSONException
        //   3290	3376	3386	org/json/JSONException
        //   3409	3418	4171	org/json/JSONException
        //   3423	3488	3604	org/json/JSONException
        //   3492	3509	3828	org/json/JSONException
        //   3513	3537	3828	org/json/JSONException
        //   3541	3565	3828	org/json/JSONException
        //   3569	3581	3828	org/json/JSONException
        //   3585	3594	3828	org/json/JSONException
        //   3616	3625	4163	org/json/JSONException
        //   3634	3648	3828	org/json/JSONException
        //   3652	3657	3828	org/json/JSONException
        //   3661	3673	3828	org/json/JSONException
        //   3677	3702	3828	org/json/JSONException
        //   3706	3731	3828	org/json/JSONException
        //   3735	3760	3828	org/json/JSONException
        //   3764	3789	3828	org/json/JSONException
        //   3793	3805	3828	org/json/JSONException
        //   3809	3818	3828	org/json/JSONException
        //   3836	3894	4159	org/json/JSONException
        //   3897	3962	4155	org/json/JSONException
        //   3962	3993	4151	org/json/JSONException
        //   3993	4004	4147	org/json/JSONException
        //   4007	4038	4147	org/json/JSONException
        //   4047	4087	4147	org/json/JSONException
        //   4090	4120	4147	org/json/JSONException
        //   4138	4144	4147	org/json/JSONException
    }
}
