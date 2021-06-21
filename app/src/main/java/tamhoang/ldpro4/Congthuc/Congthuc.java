package tamhoang.ldpro4.Congthuc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import tamhoang.ldpro4.MainActivity;

public class Congthuc {
    public static String[][] mang;

    public static boolean CheckDate(String paramString) {
        String str = paramString;
        if (paramString == null)
            str = "01/01/2018";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(str));
            calendar.add(5, 1);
            Date date2 = simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime()));
            Date date1 = new Date();
            this();
            boolean bool = date1.before(date2);
            if (bool)
                return true;
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        return false;
    }

    public static boolean CheckTime(String paramString) {
        Date date = parseDate(paramString);
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(11);
        int j = calendar.get(12);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append(":");
        stringBuilder.append(j);
        return parseDate(stringBuilder.toString()).after(date);
    }

    public static String FixDan(String paramString) {
        String str = paramString.replaceAll(":", "").replaceAll(" //. ", "").replaceAll(" , ", "").replaceAll("\\.", ",");
        paramString = "";
        String[] arrayOfString = str.split(",");
        byte b = 0;
        while (true) {
            str = paramString;
            if (b < arrayOfString.length) {
                if (isNumeric(arrayOfString[b]) == true && arrayOfString[b].length() == 2) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(paramString);
                    stringBuilder.append(arrayOfString[b]);
                    stringBuilder.append(",");
                    paramString = stringBuilder.toString();
                } else if (isNumeric(arrayOfString[b]) == true && arrayOfString[b].length() == 3) {
                    if (arrayOfString[b].charAt(0) != arrayOfString[b].charAt(2)) {
                        stringBuilder1 = new StringBuilder();
                        stringBuilder1.append("Khhi");
                        stringBuilder1.append(arrayOfString[b]);
                        str = stringBuilder1.toString();
                        break;
                    }
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append((String)stringBuilder1);
                    stringBuilder2.append(arrayOfString[b].substring(0, 2));
                    stringBuilder2.append(",");
                    String str2 = stringBuilder2.toString();
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(str2);
                    stringBuilder1.append(arrayOfString[b].substring(1, 3));
                    stringBuilder1.append(",");
                    String str1 = stringBuilder1.toString();
                } else if (arrayOfString[b].length() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Khhi");
                    stringBuilder.append(arrayOfString[b]);
                    str = stringBuilder.toString();
                    break;
                }
                b++;
                continue;
            }
            break;
        }
        return str;
    }

    public static String GhepBo(String paramString) {
        String[] arrayOfString = new String[15];
        String str = "";
        arrayOfString[0] = "00,050,55";
        arrayOfString[1] = "010,060,515,565";
        arrayOfString[2] = "020,070,525,575";
        arrayOfString[3] = "030,080,535,585";
        arrayOfString[4] = "040,090,545,595";
        arrayOfString[5] = "11,66,161";
        arrayOfString[6] = "121,171,626,676";
        arrayOfString[7] = "131,181,636,686";
        arrayOfString[8] = "141,191,646,696";
        arrayOfString[9] = "22,77,272";
        arrayOfString[10] = "232,282,737,787";
        arrayOfString[11] = "242,292,747,797";
        arrayOfString[12] = "33,88,383";
        arrayOfString[13] = "343,393,848,898";
        arrayOfString[14] = "44,494,99";
        byte b = 0;
        while (b < paramString.length() - 1) {
            String str1 = str;
            if (isNumeric(paramString.substring(b, b + 2)) == true) {
                byte b1 = 0;
                while (true) {
                    str1 = str;
                    if (b1 < arrayOfString.length) {
                        if (arrayOfString[b1].indexOf(paramString.substring(b, b + 2)) > -1) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(str);
                            stringBuilder.append(arrayOfString[b1]);
                            stringBuilder.append(",");
                            str1 = stringBuilder.toString();
                            break;
                        }
                        b1++;
                        continue;
                    }
                    break;
                }
            }
            b++;
            str = str1;
        }
        return FixDan(str);
    }

    public static String GhepDau(String paramString) {
        StringBuilder stringBuilder;
        String str1;
        String[] arrayOfString = new String[15];
        String str2 = "";
        if (!xuLymem(paramString).booleanValue()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Khhi");
            stringBuilder.append(paramString);
            return stringBuilder.toString();
        }
        arrayOfString[0] = "00,01,02,03,04,05,06,07,08,09,";
        arrayOfString[1] = "10,11,12,13,14,15,16,17,18,19,";
        arrayOfString[2] = "20,21,22,23,24,25,26,27,28,29,";
        arrayOfString[3] = "30,31,32,33,34,35,36,37,38,39,";
        arrayOfString[4] = "40,41,42,43,44,45,46,47,48,49,";
        arrayOfString[5] = "50,51,52,53,54,55,56,57,58,59,";
        arrayOfString[6] = "60,61,62,63,64,65,66,67,68,69,";
        arrayOfString[7] = "70,71,72,73,74,75,76,77,78,79,";
        arrayOfString[8] = "80,81,82,83,84,85,86,87,88,89,";
        arrayOfString[9] = "90,91,92,93,94,95,96,97,98,99,";
        byte b = 0;
        while (b < paramString.length()) {
            String str;
            StringBuilder stringBuilder1 = stringBuilder;
            if (isNumeric(paramString.substring(b, b + 1)) == true) {
                stringBuilder1 = new StringBuilder();
                stringBuilder1.append((String)stringBuilder);
                stringBuilder1.append(arrayOfString[Character.getNumericValue(paramString.charAt(b))]);
                str = stringBuilder1.toString();
            }
            b++;
            str1 = str;
        }
        return str1;
    }

    public static String GhepDit(String paramString) {
        StringBuilder stringBuilder;
        String str1;
        String[] arrayOfString = new String[15];
        String str2 = "";
        if (!xuLymem(paramString).booleanValue()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Khhi");
            stringBuilder.append(paramString);
            return stringBuilder.toString();
        }
        arrayOfString[0] = "00,10,20,30,40,50,60,70,80,90,";
        arrayOfString[1] = "01,11,21,31,41,51,61,71,81,91,";
        arrayOfString[2] = "02,12,22,32,42,52,62,72,82,92,";
        arrayOfString[3] = "03,13,23,33,43,53,63,73,83,93,";
        arrayOfString[4] = "04,14,24,34,44,54,64,74,84,94,";
        arrayOfString[5] = "05,15,25,35,45,55,65,75,85,95,";
        arrayOfString[6] = "06,16,26,36,46,56,66,76,86,96,";
        arrayOfString[7] = "07,17,27,37,47,57,67,77,87,97,";
        arrayOfString[8] = "08,18,28,38,48,58,68,78,88,98,";
        arrayOfString[9] = "09,19,29,39,49,59,69,79,89,99,";
        byte b = 0;
        while (b < paramString.length()) {
            String str;
            StringBuilder stringBuilder1 = stringBuilder;
            if (isNumeric(paramString.substring(b, b + 1)) == true) {
                stringBuilder1 = new StringBuilder();
                stringBuilder1.append((String)stringBuilder);
                stringBuilder1.append(arrayOfString[Character.getNumericValue(paramString.charAt(b))]);
                str = stringBuilder1.toString();
            }
            b++;
            str1 = str;
        }
        return str1;
    }

    public static String GhepTong(String paramString) {
        StringBuilder stringBuilder;
        String str1;
        String[] arrayOfString = new String[15];
        String str2 = "";
        if (!xuLymem(paramString).booleanValue()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Khhi");
            stringBuilder.append(paramString);
            return stringBuilder.toString();
        }
        arrayOfString[0] = "00,19,28,37,46,55,64,73,82,91,";
        arrayOfString[1] = "01,10,29,38,47,56,65,74,83,92,";
        arrayOfString[2] = "02,11,20,39,48,57,66,75,84,93,";
        arrayOfString[3] = "03,12,21,30,49,58,67,76,85,94,";
        arrayOfString[4] = "04,13,22,31,40,59,68,77,86,95,";
        arrayOfString[5] = "05,14,23,32,41,50,69,78,87,96,";
        arrayOfString[6] = "06,15,24,33,42,51,60,79,88,97,";
        arrayOfString[7] = "07,16,25,34,43,52,61,70,89,98,";
        arrayOfString[8] = "08,17,26,35,44,53,62,71,80,99,";
        arrayOfString[9] = "09,18,27,36,45,54,63,72,81,90,";
        byte b = 0;
        while (b < paramString.length()) {
            String str;
            StringBuilder stringBuilder1 = stringBuilder;
            if (isNumeric(paramString.substring(b, b + 1)) == true) {
                stringBuilder1 = new StringBuilder();
                stringBuilder1.append((String)stringBuilder);
                stringBuilder1.append(arrayOfString[Character.getNumericValue(paramString.charAt(b))]);
                str = stringBuilder1.toString();
            }
            b++;
            str1 = str;
        }
        return str1;
    }

    public static String NhanTinNhan(String paramString) {
        // Byte code:
        //   0: ldc ''
        //   2: astore_1
        //   3: ldc '\\n'
        //   5: astore_2
        //   6: ldc ' '
        //   8: astore_3
        //   9: aload_0
        //   10: ldc '\\n'
        //   12: ldc ' '
        //   14: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   17: astore_0
        //   18: ldc '\.'
        //   20: astore #4
        //   22: ldc ','
        //   24: astore #5
        //   26: aload_0
        //   27: ldc '\.'
        //   29: ldc ','
        //   31: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   34: invokestatic convertKhongDau : (Ljava/lang/String;)Ljava/lang/String;
        //   37: invokestatic fixTinNhan1 : (Ljava/lang/String;)Ljava/lang/String;
        //   40: invokestatic Xuly_DauTN : (Ljava/lang/String;)Ljava/lang/String;
        //   43: astore_0
        //   44: aload_0
        //   45: invokevirtual length : ()I
        //   48: istore #6
        //   50: ldc 'Khhi'
        //   52: astore #7
        //   54: iload #6
        //   56: iconst_3
        //   57: if_icmpge -> 86
        //   60: new java/lang/StringBuilder
        //   63: dup
        //   64: invokespecial <init> : ()V
        //   67: astore_1
        //   68: aload_1
        //   69: ldc 'Khhi'
        //   71: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   74: pop
        //   75: aload_1
        //   76: aload_0
        //   77: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   80: pop
        //   81: aload_1
        //   82: invokevirtual toString : ()Ljava/lang/String;
        //   85: areturn
        //   86: aload_0
        //   87: invokestatic fixTinNhan : (Ljava/lang/String;)Ljava/lang/String;
        //   90: astore_0
        //   91: aload_0
        //   92: ldc_w 'de'
        //   95: invokevirtual indexOf : (Ljava/lang/String;)I
        //   98: iconst_m1
        //   99: if_icmpne -> 117
        //   102: aload_0
        //   103: ldc_w 'lo'
        //   106: invokevirtual indexOf : (Ljava/lang/String;)I
        //   109: iconst_m1
        //   110: if_icmpne -> 117
        //   113: ldc_w 'Khhid
        //   116: areturn
        //   117: aload_0
        //   118: invokevirtual length : ()I
        //   121: bipush #8
        //   123: if_icmpge -> 152
        //   126: new java/lang/StringBuilder
        //   129: dup
        //   130: invokespecial <init> : ()V
        //   133: astore_1
        //   134: aload_1
        //   135: ldc 'Khhi'
        //   137: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   140: pop
        //   141: aload_1
        //   142: aload_0
        //   143: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   146: pop
        //   147: aload_1
        //   148: invokevirtual toString : ()Ljava/lang/String;
        //   151: areturn
        //   152: aload_0
        //   153: invokestatic PhanTichTinNhan : (Ljava/lang/String;)Ljava/lang/String;
        //   156: astore_0
        //   157: aload_0
        //   158: ldc_w 'x '
        //   161: invokevirtual indexOf : (Ljava/lang/String;)I
        //   164: istore #6
        //   166: iconst_0
        //   167: istore #8
        //   169: ldc_w 'Khhi
        //   172: astore #9
        //   174: iload #6
        //   176: iconst_m1
        //   177: if_icmpne -> 217
        //   180: aload_0
        //   181: ldc_w 'Khhi
        //   184: invokevirtual indexOf : (Ljava/lang/String;)I
        //   187: iconst_m1
        //   188: if_icmpne -> 217
        //   191: new java/lang/StringBuilder
        //   194: dup
        //   195: invokespecial <init> : ()V
        //   198: astore_1
        //   199: aload_1
        //   200: ldc 'Khhi'
        //   202: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   205: pop
        //   206: aload_1
        //   207: aload_0
        //   208: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   211: pop
        //   212: aload_1
        //   213: invokevirtual toString : ()Ljava/lang/String;
        //   216: areturn
        //   217: invokestatic getInstance : ()Ljava/util/Calendar;
        //   220: astore #10
        //   222: aload #10
        //   224: new java/util/Date
        //   227: dup
        //   228: invokespecial <init> : ()V
        //   231: invokevirtual setTime : (Ljava/util/Date;)V
        //   234: iconst_0
        //   235: istore #11
        //   237: new java/text/SimpleDateFormat
        //   240: dup
        //   241: ldc_w 'HH:mm:ss'
        //   244: invokespecial <init> : (Ljava/lang/String;)V
        //   247: astore #12
        //   249: aload #12
        //   251: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   254: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   257: aload #12
        //   259: aload #10
        //   261: invokevirtual getTime : ()Ljava/util/Date;
        //   264: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
        //   267: astore #13
        //   269: aload_0
        //   270: ldc_w 'Khhi
        //   273: invokevirtual indexOf : (Ljava/lang/String;)I
        //   276: istore #6
        //   278: aconst_null
        //   279: astore #14
        //   281: iload #6
        //   283: iconst_m1
        //   284: if_icmple -> 290
        //   287: goto -> 2230
        //   290: sipush #300
        //   293: iconst_4
        //   294: multianewarray[[Ljava/lang/String; 2
        //   298: astore #15
        //   300: aload_0
        //   301: ldc ' , '
        //   303: ldc ' '
        //   305: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   308: astore #12
        //   310: iconst_1
        //   311: istore #6
        //   313: aload_1
        //   314: astore_0
        //   315: aload #12
        //   317: astore_1
        //   318: iload #6
        //   320: bipush #10
        //   322: if_icmpge -> 341
        //   325: aload_1
        //   326: ldc_w '  '
        //   329: ldc ' '
        //   331: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   334: astore_1
        //   335: iinc #6, 1
        //   338: goto -> 318
        //   341: new java/lang/StringBuilder
        //   344: dup
        //   345: invokespecial <init> : ()V
        //   348: astore #12
        //   350: ldc ''
        //   352: astore #16
        //   354: aload #12
        //   356: aload_1
        //   357: invokevirtual trim : ()Ljava/lang/String;
        //   360: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   363: pop
        //   364: aload #12
        //   366: ldc ' '
        //   368: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   371: pop
        //   372: aload #12
        //   374: invokevirtual toString : ()Ljava/lang/String;
        //   377: astore #17
        //   379: iconst_0
        //   380: istore #18
        //   382: aload_0
        //   383: astore #12
        //   385: iconst_m1
        //   386: istore #19
        //   388: aload #16
        //   390: astore_0
        //   391: aload #14
        //   393: astore_1
        //   394: iload #6
        //   396: istore #20
        //   398: aload #17
        //   400: ldc_w ' x '
        //   403: iload #19
        //   405: iconst_1
        //   406: iadd
        //   407: invokevirtual indexOf : (Ljava/lang/String;I)I
        //   410: istore #6
        //   412: iload #6
        //   414: istore #19
        //   416: iload #6
        //   418: iconst_m1
        //   419: if_icmpeq -> 1872
        //   422: ldc ''
        //   424: astore #12
        //   426: iload #19
        //   428: istore #6
        //   430: aload #5
        //   432: astore #14
        //   434: aload_2
        //   435: astore #5
        //   437: iload #6
        //   439: aload #17
        //   441: invokevirtual length : ()I
        //   444: if_icmpge -> 539
        //   447: aload #17
        //   449: iload #6
        //   451: invokevirtual charAt : (I)C
        //   454: bipush #32
        //   456: if_icmpne -> 470
        //   459: aload #12
        //   461: invokevirtual length : ()I
        //   464: ifle -> 470
        //   467: goto -> 539
        //   470: aload #12
        //   472: astore_2
        //   473: ldc_w '0123456789,tr'
        //   476: aload #17
        //   478: iload #6
        //   480: iload #6
        //   482: iconst_1
        //   483: iadd
        //   484: invokevirtual substring : (II)Ljava/lang/String;
        //   487: invokevirtual indexOf : (Ljava/lang/String;)I
        //   490: iconst_m1
        //   491: if_icmple -> 530
        //   494: new java/lang/StringBuilder
        //   497: dup
        //   498: invokespecial <init> : ()V
        //   501: astore_2
        //   502: aload_2
        //   503: aload #12
        //   505: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   508: pop
        //   509: aload_2
        //   510: aload #17
        //   512: iload #6
        //   514: iload #6
        //   516: iconst_1
        //   517: iadd
        //   518: invokevirtual substring : (II)Ljava/lang/String;
        //   521: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   524: pop
        //   525: aload_2
        //   526: invokevirtual toString : ()Ljava/lang/String;
        //   529: astore_2
        //   530: iinc #6, 1
        //   533: aload_2
        //   534: astore #12
        //   536: goto -> 437
        //   539: aload #14
        //   541: astore_2
        //   542: ldc ''
        //   544: astore #14
        //   546: iload #6
        //   548: istore #21
        //   550: iload #21
        //   552: aload #17
        //   554: invokevirtual length : ()I
        //   557: if_icmpge -> 631
        //   560: aload #17
        //   562: iload #21
        //   564: invokevirtual charAt : (I)C
        //   567: invokestatic isLetter : (C)Z
        //   570: ifne -> 584
        //   573: aload #14
        //   575: invokevirtual length : ()I
        //   578: ifle -> 584
        //   581: goto -> 631
        //   584: new java/lang/StringBuilder
        //   587: dup
        //   588: invokespecial <init> : ()V
        //   591: astore #16
        //   593: aload #16
        //   595: aload #14
        //   597: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   600: pop
        //   601: aload #16
        //   603: aload #17
        //   605: iload #21
        //   607: iload #21
        //   609: iconst_1
        //   610: iadd
        //   611: invokevirtual substring : (II)Ljava/lang/String;
        //   614: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   617: pop
        //   618: aload #16
        //   620: invokevirtual toString : ()Ljava/lang/String;
        //   623: astore #14
        //   625: iinc #21, 1
        //   628: goto -> 550
        //   631: iload #21
        //   633: istore #8
        //   635: iload #6
        //   637: iload #21
        //   639: if_icmpne -> 648
        //   642: iload #21
        //   644: iconst_1
        //   645: isub
        //   646: istore #8
        //   648: aload #14
        //   650: ldc_w 'dau'
        //   653: invokevirtual indexOf : (Ljava/lang/String;)I
        //   656: iconst_m1
        //   657: if_icmpgt -> 1158
        //   660: aload #14
        //   662: ldc_w 'dit'
        //   665: invokevirtual indexOf : (Ljava/lang/String;)I
        //   668: iconst_m1
        //   669: if_icmpgt -> 1155
        //   672: aload #14
        //   674: ldc_w 'tong'
        //   677: invokevirtual indexOf : (Ljava/lang/String;)I
        //   680: iconst_m1
        //   681: if_icmpgt -> 1152
        //   684: aload #14
        //   686: ldc_w 'cham'
        //   689: invokevirtual indexOf : (Ljava/lang/String;)I
        //   692: iconst_m1
        //   693: if_icmpgt -> 1149
        //   696: aload #14
        //   698: ldc_w 'dan'
        //   701: invokevirtual indexOf : (Ljava/lang/String;)I
        //   704: iconst_m1
        //   705: if_icmpgt -> 1146
        //   708: aload #14
        //   710: ldc_w 'boj'
        //   713: invokevirtual indexOf : (Ljava/lang/String;)I
        //   716: iconst_m1
        //   717: if_icmpgt -> 1143
        //   720: aload #14
        //   722: ldc_w 'lo'
        //   725: invokevirtual indexOf : (Ljava/lang/String;)I
        //   728: iconst_m1
        //   729: if_icmpgt -> 1140
        //   732: aload #14
        //   734: ldc_w 'de'
        //   737: invokevirtual indexOf : (Ljava/lang/String;)I
        //   740: iconst_m1
        //   741: if_icmpgt -> 1137
        //   744: aload #14
        //   746: ldc_w 'xi'
        //   749: invokevirtual indexOf : (Ljava/lang/String;)I
        //   752: iconst_m1
        //   753: if_icmpgt -> 1134
        //   756: aload #14
        //   758: ldc_w 'xn'
        //   761: invokevirtual indexOf : (Ljava/lang/String;)I
        //   764: iconst_m1
        //   765: if_icmpgt -> 1131
        //   768: aload #14
        //   770: ldc_w 'hc'
        //   773: invokevirtual indexOf : (Ljava/lang/String;)I
        //   776: iconst_m1
        //   777: if_icmpgt -> 1128
        //   780: aload #14
        //   782: ldc_w 'xq'
        //   785: invokevirtual indexOf : (Ljava/lang/String;)I
        //   788: iconst_m1
        //   789: if_icmpgt -> 1125
        //   792: aload #14
        //   794: ldc_w 'xg'
        //   797: invokevirtual indexOf : (Ljava/lang/String;)I
        //   800: iconst_m1
        //   801: if_icmpgt -> 1122
        //   804: aload #14
        //   806: ldc_w 'bc'
        //   809: invokevirtual indexOf : (Ljava/lang/String;)I
        //   812: iconst_m1
        //   813: if_icmpgt -> 1119
        //   816: aload #14
        //   818: ldc_w 'kep'
        //   821: invokevirtual indexOf : (Ljava/lang/String;)I
        //   824: iconst_m1
        //   825: if_icmpgt -> 1116
        //   828: aload #14
        //   830: ldc_w 'sat'
        //   833: invokevirtual indexOf : (Ljava/lang/String;)I
        //   836: iconst_m1
        //   837: if_icmpgt -> 1113
        //   840: aload #14
        //   842: ldc_w 'to'
        //   845: invokevirtual indexOf : (Ljava/lang/String;)I
        //   848: iconst_m1
        //   849: if_icmpgt -> 1110
        //   852: aload #14
        //   854: ldc_w 'nho'
        //   857: invokevirtual indexOf : (Ljava/lang/String;)I
        //   860: iconst_m1
        //   861: if_icmpgt -> 1107
        //   864: aload #14
        //   866: ldc_w 'chan'
        //   869: invokevirtual indexOf : (Ljava/lang/String;)I
        //   872: iconst_m1
        //   873: if_icmpgt -> 1104
        //   876: aload #14
        //   878: ldc_w 'le'
        //   881: invokevirtual indexOf : (Ljava/lang/String;)I
        //   884: iconst_m1
        //   885: if_icmpgt -> 1101
        //   888: aload #14
        //   890: ldc_w 'ko'
        //   893: invokevirtual indexOf : (Ljava/lang/String;)I
        //   896: iconst_m1
        //   897: if_icmpgt -> 1098
        //   900: aload #14
        //   902: ldc_w 'chia'
        //   905: invokevirtual indexOf : (Ljava/lang/String;)I
        //   908: iconst_m1
        //   909: if_icmpgt -> 1095
        //   912: aload #14
        //   914: ldc_w 'duoi'
        //   917: invokevirtual indexOf : (Ljava/lang/String;)I
        //   920: iconst_m1
        //   921: if_icmpgt -> 1092
        //   924: aload #14
        //   926: ldc_w 'be'
        //   929: invokevirtual indexOf : (Ljava/lang/String;)I
        //   932: iconst_m1
        //   933: if_icmple -> 939
        //   936: goto -> 1158
        //   939: aload #14
        //   941: ldc_w 'x '
        //   944: invokevirtual indexOf : (Ljava/lang/String;)I
        //   947: iconst_m1
        //   948: if_icmple -> 1021
        //   951: iload #6
        //   953: iconst_1
        //   954: isub
        //   955: istore #8
        //   957: iload #8
        //   959: ifle -> 1014
        //   962: aload #17
        //   964: iload #8
        //   966: iload #8
        //   968: iconst_1
        //   969: iadd
        //   970: invokevirtual substring : (II)Ljava/lang/String;
        //   973: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   976: ifne -> 1008
        //   979: aload #17
        //   981: iload #18
        //   983: iload #8
        //   985: iconst_1
        //   986: iadd
        //   987: invokevirtual substring : (II)Ljava/lang/String;
        //   990: astore_0
        //   991: iload #8
        //   993: iconst_1
        //   994: iadd
        //   995: istore #6
        //   997: aload #17
        //   999: iload #6
        //   1001: invokevirtual substring : (I)Ljava/lang/String;
        //   1004: astore_1
        //   1005: goto -> 1176
        //   1008: iinc #8, -1
        //   1011: goto -> 957
        //   1014: iload #18
        //   1016: istore #6
        //   1018: goto -> 1176
        //   1021: aload #17
        //   1023: iload #18
        //   1025: iload #8
        //   1027: invokevirtual substring : (II)Ljava/lang/String;
        //   1030: astore_0
        //   1031: aload_0
        //   1032: iconst_0
        //   1033: iconst_4
        //   1034: invokevirtual substring : (II)Ljava/lang/String;
        //   1037: ldc_w 'bor'
        //   1040: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1043: iconst_m1
        //   1044: if_icmple -> 1077
        //   1047: new java/lang/StringBuilder
        //   1050: dup
        //   1051: invokespecial <init> : ()V
        //   1054: astore_1
        //   1055: aload_1
        //   1056: ldc_w 'de '
        //   1059: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1062: pop
        //   1063: aload_1
        //   1064: aload_0
        //   1065: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1068: pop
        //   1069: aload_1
        //   1070: invokevirtual toString : ()Ljava/lang/String;
        //   1073: astore_0
        //   1074: goto -> 1077
        //   1077: aload #17
        //   1079: iload #8
        //   1081: invokevirtual substring : (I)Ljava/lang/String;
        //   1084: astore_1
        //   1085: iload #8
        //   1087: istore #6
        //   1089: goto -> 1176
        //   1092: goto -> 1158
        //   1095: goto -> 1158
        //   1098: goto -> 1158
        //   1101: goto -> 1158
        //   1104: goto -> 1158
        //   1107: goto -> 1158
        //   1110: goto -> 1158
        //   1113: goto -> 1158
        //   1116: goto -> 1158
        //   1119: goto -> 1158
        //   1122: goto -> 1158
        //   1125: goto -> 1158
        //   1128: goto -> 1158
        //   1131: goto -> 1158
        //   1134: goto -> 1158
        //   1137: goto -> 1158
        //   1140: goto -> 1158
        //   1143: goto -> 1158
        //   1146: goto -> 1158
        //   1149: goto -> 1158
        //   1152: goto -> 1158
        //   1155: goto -> 1158
        //   1158: aload #17
        //   1160: iload #18
        //   1162: iload #6
        //   1164: invokevirtual substring : (II)Ljava/lang/String;
        //   1167: astore_0
        //   1168: aload #17
        //   1170: iload #6
        //   1172: invokevirtual substring : (I)Ljava/lang/String;
        //   1175: astore_1
        //   1176: aload_0
        //   1177: ldc_w '\n'
        //   1180: ldc ''
        //   1182: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1185: astore #14
        //   1187: iinc #11, 1
        //   1190: aload #15
        //   1192: iload #11
        //   1194: aaload
        //   1195: iconst_0
        //   1196: aload_0
        //   1197: aastore
        //   1198: aload_0
        //   1199: ldc_w 'lo'
        //   1202: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1205: iconst_m1
        //   1206: if_icmple -> 1248
        //   1209: aload #15
        //   1211: iload #11
        //   1213: aaload
        //   1214: iconst_1
        //   1215: ldc_w 'lo'
        //   1218: aastore
        //   1219: aload_0
        //   1220: ldc_w 'lo'
        //   1223: ldc ''
        //   1225: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1228: ldc_w 'lo:'
        //   1231: ldc ''
        //   1233: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1236: ldc_w 'lo :'
        //   1239: ldc ''
        //   1241: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1244: astore_0
        //   1245: goto -> 1640
        //   1248: aload_0
        //   1249: ldc_w 'dea'
        //   1252: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1255: iconst_m1
        //   1256: if_icmple -> 1298
        //   1259: aload #15
        //   1261: iload #11
        //   1263: aaload
        //   1264: iconst_1
        //   1265: ldc_w 'de dau db'
        //   1268: aastore
        //   1269: aload_0
        //   1270: ldc_w 'dea'
        //   1273: ldc ''
        //   1275: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1278: ldc_w 'dea:'
        //   1281: ldc ''
        //   1283: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1286: ldc_w 'dea :'
        //   1289: ldc ''
        //   1291: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1294: astore_0
        //   1295: goto -> 1640
        //   1298: aload_0
        //   1299: ldc_w 'deb'
        //   1302: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1305: iconst_m1
        //   1306: if_icmple -> 1348
        //   1309: aload #15
        //   1311: iload #11
        //   1313: aaload
        //   1314: iconst_1
        //   1315: ldc_w 'de dit db'
        //   1318: aastore
        //   1319: aload_0
        //   1320: ldc_w 'deb'
        //   1323: ldc ''
        //   1325: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1328: ldc_w 'deb:'
        //   1331: ldc ''
        //   1333: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1336: ldc_w 'deb :'
        //   1339: ldc ''
        //   1341: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1344: astore_0
        //   1345: goto -> 1640
        //   1348: aload_0
        //   1349: ldc_w 'dec'
        //   1352: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1355: iconst_m1
        //   1356: if_icmple -> 1398
        //   1359: aload #15
        //   1361: iload #11
        //   1363: aaload
        //   1364: iconst_1
        //   1365: ldc_w 'de dau nhat'
        //   1368: aastore
        //   1369: aload_0
        //   1370: ldc_w 'dec'
        //   1373: ldc ''
        //   1375: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1378: ldc_w 'dec:'
        //   1381: ldc ''
        //   1383: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1386: ldc_w 'dec :'
        //   1389: ldc ''
        //   1391: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1394: astore_0
        //   1395: goto -> 1640
        //   1398: aload_0
        //   1399: ldc_w 'ded'
        //   1402: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1405: iconst_m1
        //   1406: if_icmple -> 1448
        //   1409: aload #15
        //   1411: iload #11
        //   1413: aaload
        //   1414: iconst_1
        //   1415: ldc_w 'de dit nhat'
        //   1418: aastore
        //   1419: aload_0
        //   1420: ldc_w 'ded'
        //   1423: ldc ''
        //   1425: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1428: ldc_w 'ded:'
        //   1431: ldc ''
        //   1433: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1436: ldc_w 'ded :'
        //   1439: ldc ''
        //   1441: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1444: astore_0
        //   1445: goto -> 1640
        //   1448: aload_0
        //   1449: ldc_w 'de'
        //   1452: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1455: iconst_m1
        //   1456: if_icmple -> 1498
        //   1459: aload #15
        //   1461: iload #11
        //   1463: aaload
        //   1464: iconst_1
        //   1465: ldc_w 'de dit db'
        //   1468: aastore
        //   1469: aload_0
        //   1470: ldc_w 'de'
        //   1473: ldc ''
        //   1475: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1478: ldc_w 'de:'
        //   1481: ldc ''
        //   1483: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1486: ldc_w 'de :'
        //   1489: ldc ''
        //   1491: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1494: astore_0
        //   1495: goto -> 1640
        //   1498: aload_0
        //   1499: ldc_w 'bc'
        //   1502: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1505: iconst_m1
        //   1506: if_icmple -> 1548
        //   1509: aload #15
        //   1511: iload #11
        //   1513: aaload
        //   1514: iconst_1
        //   1515: ldc_w 'bc'
        //   1518: aastore
        //   1519: aload_0
        //   1520: ldc_w 'bc'
        //   1523: ldc ''
        //   1525: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1528: ldc_w 'bc:'
        //   1531: ldc ''
        //   1533: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1536: ldc_w 'bc :'
        //   1539: ldc ''
        //   1541: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1544: astore_0
        //   1545: goto -> 1640
        //   1548: aload_0
        //   1549: ldc_w 'xi'
        //   1552: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1555: iconst_m1
        //   1556: if_icmple -> 1590
        //   1559: aload #15
        //   1561: iload #11
        //   1563: aaload
        //   1564: iconst_1
        //   1565: ldc_w 'xi'
        //   1568: aastore
        //   1569: aload_0
        //   1570: ldc_w 'xi'
        //   1573: ldc ''
        //   1575: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1578: ldc_w 'xien'
        //   1581: ldc ''
        //   1583: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1586: astore_0
        //   1587: goto -> 1640
        //   1590: aload_0
        //   1591: ldc_w 'xq'
        //   1594: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1597: iconst_m1
        //   1598: if_icmple -> 1624
        //   1601: aload #15
        //   1603: iload #11
        //   1605: aaload
        //   1606: iconst_1
        //   1607: ldc_w 'xq'
        //   1610: aastore
        //   1611: aload_0
        //   1612: ldc_w 'xq'
        //   1615: ldc ''
        //   1617: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1620: astore_0
        //   1621: goto -> 1640
        //   1624: aload #15
        //   1626: iload #11
        //   1628: aaload
        //   1629: iconst_1
        //   1630: aload #15
        //   1632: iload #11
        //   1634: iconst_1
        //   1635: isub
        //   1636: aaload
        //   1637: iconst_1
        //   1638: aaload
        //   1639: aastore
        //   1640: aload #15
        //   1642: iload #11
        //   1644: aaload
        //   1645: iconst_2
        //   1646: aload_0
        //   1647: iconst_0
        //   1648: aload_0
        //   1649: ldc_w 'x'
        //   1652: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1655: invokevirtual substring : (II)Ljava/lang/String;
        //   1658: invokestatic XulyLoDe : (Ljava/lang/String;)Ljava/lang/String;
        //   1661: aastore
        //   1662: aload #15
        //   1664: iload #11
        //   1666: aaload
        //   1667: iconst_3
        //   1668: aload_0
        //   1669: aload_0
        //   1670: ldc_w 'x'
        //   1673: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1676: aload_0
        //   1677: invokevirtual length : ()I
        //   1680: invokevirtual substring : (II)Ljava/lang/String;
        //   1683: invokestatic XulyTien : (Ljava/lang/String;)Ljava/lang/String;
        //   1686: aastore
        //   1687: ldc ''
        //   1689: astore #16
        //   1691: aload #15
        //   1693: iload #11
        //   1695: aaload
        //   1696: iconst_2
        //   1697: aaload
        //   1698: astore #22
        //   1700: aload #9
        //   1702: astore #12
        //   1704: aload #22
        //   1706: aload #12
        //   1708: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1711: iconst_m1
        //   1712: if_icmpgt -> 1831
        //   1715: aload #15
        //   1717: iload #11
        //   1719: aaload
        //   1720: iconst_2
        //   1721: aaload
        //   1722: invokevirtual length : ()I
        //   1725: iconst_3
        //   1726: if_icmpge -> 1732
        //   1729: goto -> 1831
        //   1732: aload #15
        //   1734: iload #11
        //   1736: aaload
        //   1737: iconst_3
        //   1738: aaload
        //   1739: aload #12
        //   1741: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1744: iconst_m1
        //   1745: if_icmple -> 1798
        //   1748: new java/lang/StringBuilder
        //   1751: dup
        //   1752: invokespecial <init> : ()V
        //   1755: astore #12
        //   1757: aload #12
        //   1759: aload #7
        //   1761: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1764: pop
        //   1765: aload #12
        //   1767: aload_0
        //   1768: aload_0
        //   1769: ldc_w 'x'
        //   1772: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1775: invokevirtual substring : (I)Ljava/lang/String;
        //   1778: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1781: pop
        //   1782: aload #12
        //   1784: invokevirtual toString : ()Ljava/lang/String;
        //   1787: astore #14
        //   1789: aload_0
        //   1790: astore #12
        //   1792: aload #14
        //   1794: astore_0
        //   1795: goto -> 1892
        //   1798: aload #7
        //   1800: astore #14
        //   1802: aload #5
        //   1804: astore #7
        //   1806: aload_2
        //   1807: astore #5
        //   1809: aload #12
        //   1811: astore #9
        //   1813: aload #16
        //   1815: astore #12
        //   1817: iload #6
        //   1819: istore #18
        //   1821: aload #7
        //   1823: astore_2
        //   1824: aload #14
        //   1826: astore #7
        //   1828: goto -> 398
        //   1831: new java/lang/StringBuilder
        //   1834: dup
        //   1835: invokespecial <init> : ()V
        //   1838: astore #12
        //   1840: aload #12
        //   1842: aload #7
        //   1844: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1847: pop
        //   1848: aload #12
        //   1850: aload #14
        //   1852: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1855: pop
        //   1856: aload #12
        //   1858: invokevirtual toString : ()Ljava/lang/String;
        //   1861: astore #14
        //   1863: aload_0
        //   1864: astore #12
        //   1866: aload #14
        //   1868: astore_0
        //   1869: goto -> 1892
        //   1872: aload_2
        //   1873: astore #14
        //   1875: aload #12
        //   1877: astore #13
        //   1879: aload #5
        //   1881: astore_2
        //   1882: aload #14
        //   1884: astore #5
        //   1886: aload_0
        //   1887: astore #12
        //   1889: aload #13
        //   1891: astore_0
        //   1892: aload_1
        //   1893: aload_3
        //   1894: ldc ''
        //   1896: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1899: aload #4
        //   1901: ldc ''
        //   1903: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1906: aload_2
        //   1907: ldc ''
        //   1909: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1912: astore #4
        //   1914: aload_0
        //   1915: aload #9
        //   1917: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1920: iconst_m1
        //   1921: if_icmple -> 1927
        //   1924: goto -> 2230
        //   1927: aload #4
        //   1929: invokevirtual length : ()I
        //   1932: ifle -> 1964
        //   1935: new java/lang/StringBuilder
        //   1938: dup
        //   1939: invokespecial <init> : ()V
        //   1942: astore_0
        //   1943: aload_0
        //   1944: aload #7
        //   1946: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1949: pop
        //   1950: aload_0
        //   1951: aload_1
        //   1952: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1955: pop
        //   1956: aload_0
        //   1957: invokevirtual toString : ()Ljava/lang/String;
        //   1960: astore_0
        //   1961: goto -> 2230
        //   1964: ldc ''
        //   1966: astore_1
        //   1967: aload_0
        //   1968: aload #9
        //   1970: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1973: iconst_m1
        //   1974: if_icmpne -> 2230
        //   1977: iconst_1
        //   1978: istore #6
        //   1980: aload_1
        //   1981: astore_0
        //   1982: iload #6
        //   1984: sipush #300
        //   1987: if_icmpge -> 2227
        //   1990: aload #15
        //   1992: iload #6
        //   1994: aaload
        //   1995: iconst_3
        //   1996: aaload
        //   1997: ifnonnull -> 2013
        //   2000: aload #15
        //   2002: iload #6
        //   2004: aaload
        //   2005: iconst_0
        //   2006: aaload
        //   2007: ifnonnull -> 2013
        //   2010: goto -> 2227
        //   2013: aload #15
        //   2015: iload #6
        //   2017: aaload
        //   2018: iconst_3
        //   2019: aaload
        //   2020: ifnonnull -> 2038
        //   2023: aload #15
        //   2025: iload #6
        //   2027: aaload
        //   2028: iconst_0
        //   2029: aaload
        //   2030: ifnull -> 2038
        //   2033: aload_0
        //   2034: astore_1
        //   2035: goto -> 2187
        //   2038: aload #15
        //   2040: iload #6
        //   2042: aaload
        //   2043: iconst_2
        //   2044: aaload
        //   2045: aload #9
        //   2047: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2050: iconst_m1
        //   2051: if_icmpne -> 2150
        //   2054: aload #15
        //   2056: iload #6
        //   2058: aaload
        //   2059: iconst_3
        //   2060: aaload
        //   2061: aload #9
        //   2063: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2066: iconst_m1
        //   2067: if_icmpne -> 2150
        //   2070: new java/lang/StringBuilder
        //   2073: dup
        //   2074: invokespecial <init> : ()V
        //   2077: astore_1
        //   2078: aload_1
        //   2079: aload_0
        //   2080: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2083: pop
        //   2084: aload_1
        //   2085: aload #15
        //   2087: iload #6
        //   2089: aaload
        //   2090: iconst_1
        //   2091: aaload
        //   2092: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2095: pop
        //   2096: aload_1
        //   2097: ldc ':'
        //   2099: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2102: pop
        //   2103: aload_1
        //   2104: aload #15
        //   2106: iload #6
        //   2108: aaload
        //   2109: iconst_2
        //   2110: aaload
        //   2111: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2114: pop
        //   2115: aload_1
        //   2116: ldc_w 'x'
        //   2119: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2122: pop
        //   2123: aload_1
        //   2124: aload #15
        //   2126: iload #6
        //   2128: aaload
        //   2129: iconst_3
        //   2130: aaload
        //   2131: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2134: pop
        //   2135: aload_1
        //   2136: aload #5
        //   2138: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2141: pop
        //   2142: aload_1
        //   2143: invokevirtual toString : ()Ljava/lang/String;
        //   2146: astore_1
        //   2147: goto -> 2187
        //   2150: aload #15
        //   2152: iload #6
        //   2154: aaload
        //   2155: iconst_2
        //   2156: aaload
        //   2157: aload #9
        //   2159: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2162: iconst_m1
        //   2163: if_icmpgt -> 2195
        //   2166: aload_0
        //   2167: astore_1
        //   2168: aload #15
        //   2170: iload #6
        //   2172: aaload
        //   2173: iconst_3
        //   2174: aaload
        //   2175: aload #9
        //   2177: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2180: iconst_m1
        //   2181: if_icmple -> 2187
        //   2184: goto -> 2195
        //   2187: iinc #6, 1
        //   2190: aload_1
        //   2191: astore_0
        //   2192: goto -> 1982
        //   2195: new java/lang/StringBuilder
        //   2198: dup
        //   2199: invokespecial <init> : ()V
        //   2202: astore_1
        //   2203: aload_1
        //   2204: aload #7
        //   2206: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2209: pop
        //   2210: aload_1
        //   2211: aload #15
        //   2213: iload #6
        //   2215: aaload
        //   2216: iconst_0
        //   2217: aaload
        //   2218: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2221: pop
        //   2222: aload_1
        //   2223: invokevirtual toString : ()Ljava/lang/String;
        //   2226: pop
        //   2227: goto -> 2230
        //   2230: aload_0
        //   2231: areturn
    }

    public static String PhanTichTinNhan(String paramString) {
        String str2 = paramString.replace("  ", " ");
        if (str2.indexOf("Khhi) > -1)
        return str2;
        if (str2.substring(0, 5).indexOf("de") == -1 && str2.substring(0, 5).indexOf("lo") == -1 && str2.substring(0, 5).indexOf("hc") == -1 && str2.substring(0, 5).indexOf("xi") == -1 && str2.substring(0, 5).indexOf("xq") == -1 && str2.substring(0, 5).indexOf("xn") == -1 && str2.substring(0, 5).indexOf("bc") == -1 && str2.substring(0, 5).indexOf("xg") == -1)
            return "Khhid;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append("      ");
        String str1 = stringBuilder.toString();
        str1.toCharArray();
        int i = 3;
        while (i < str1.length() - 4) {
            String str;
            int j = i;
            str2 = str1;
            if (isNumeric(str1.substring(i, i + 1))) {
                j = i;
                str2 = str1;
                if (str1.charAt(i + 1) == ' ') {
                    j = i;
                    str2 = str1;
                    if ("ndk".indexOf(str1.substring(i + 2, i + 3)) > -1) {
                        j = i;
                        str2 = str1;
                        if (str1.charAt(i + 3) == ' ') {
                            for (j = i; j > 0 && isNumeric(str1.substring(j, j + 1)); j--);
                            StringBuilder stringBuilder1 = new StringBuilder();
                            stringBuilder1.append(str1.substring(0, j));
                            stringBuilder1.append(" x ");
                            stringBuilder1.append(str1.substring(j));
                            str = stringBuilder1.toString();
                            j = i + 6;
                        }
                    }
                }
            }
            i = j + 1;
            str1 = str;
        }
        for (i = 1; i < 10; i++)
            str1 = str1.replaceAll("  ", " ");
        for (i = 1; i < 4; i++)
            str1 = str1.replaceAll("  ", " ").replaceAll(": x", " x").replaceAll(":x", " x").replaceAll("x x", "x").replaceAll("xx", "x").replaceAll(", x", " x").replaceAll(",x", " x").replaceAll("-x", " x").replaceAll("- x", " x");
        return str1;
    }

    public static String ToMauError(String paramString1, String paramString2) {
        String str1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ldpro");
        stringBuilder.append(paramString1);
        stringBuilder.append("</font>");
        String str2 = stringBuilder.toString();
        if (paramString2.indexOf(paramString1) > -1) {
            paramString1 = paramString2.replace(paramString1, str2);
        } else {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("ldpro");
            stringBuilder1.append(paramString2);
            stringBuilder1.append("</font>");
            str1 = stringBuilder1.toString();
        }
        return str1;
    }

    public static String Xu3cang(String paramString) {
        StringBuilder stringBuilder;
        String str1 = "";
        if (paramString.length() < 2)
            str1 = "Khhi;
        String str2 = str1;
        if (paramString.replaceAll(" ", "").length() > 0) {
            String[] arrayOfString = paramString.trim().replaceAll(":", " ").replaceAll(" //. ", "").replaceAll(" , ", "").replaceAll(";", " ").replaceAll("/", "").replaceAll("\\.", ",").replaceAll(" ", ",").split(",");
            byte b = 0;
            while (true) {
                str2 = str1;
                if (b < arrayOfString.length) {
                    if (isNumeric(arrayOfString[b]) == true && arrayOfString[b].length() == 3) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(str1);
                        stringBuilder.append(arrayOfString[b]);
                        stringBuilder.append(",");
                        str1 = stringBuilder.toString();
                    } else if (arrayOfString[b].length() > 0) {
                        StringBuilder stringBuilder1 = new StringBuilder();
                        stringBuilder1.append("Khhi");
                        stringBuilder1.append(arrayOfString[b]);
                        return stringBuilder1.toString();
                    }
                    b++;
                    continue;
                }
                break;
            }
        }
        return (String)stringBuilder;
    }

    public static String XulyLoDe(String paramString) {
        int i = paramString.indexOf("bor trung");
        String str = "";
        if (i > -1) {
            String[] arrayOfString1 = paramString.split("bor trung ");
            String str1 = "";
            String str2 = "";
            if (arrayOfString1[0].indexOf("bor trung") > -1) {
                str = XulySo(arrayOfString1[0].replaceAll("bor trung", ""));
            } else {
                str = XulySo(arrayOfString1[0]);
            }
            if (str.indexOf("Khhi) > -1)
            return str;
            String[] arrayOfString2 = str.split(",");
            String str3 = str2;
            String str4 = str1;
            if (arrayOfString1.length > 1)
                if (arrayOfString1[1].length() > 0 && arrayOfString1[1].indexOf("bor") > -1) {
                    str = XulySo(arrayOfString1[1].replaceAll("bor", ""));
                    str3 = str2;
                    str4 = str;
                    if (str.indexOf("Khhi) > -1)
                    return str;
                } else {
                    str3 = str2;
                    str4 = str1;
                    if (arrayOfString1[1].length() > 0) {
                        str3 = str2;
                        str4 = str1;
                        if (arrayOfString1[1].indexOf("trung") > -1) {
                            str = XulySo(arrayOfString1[1].replaceAll("trung", ""));
                            str3 = str;
                            str4 = str1;
                            if (str.indexOf("Khhi) > -1)
                            return str;
                        }
                    }
                }
            str1 = "";
            i = 0;
            try {
                while (i < arrayOfString2.length) {
                    String str5;
                    if (str4.length() == 0 && str3.length() == 0) {
                        str = str1;
                        if (str1.indexOf(arrayOfString2[i]) == -1) {
                            StringBuilder stringBuilder = new StringBuilder();
                            this();
                            stringBuilder.append(str1);
                            stringBuilder.append(arrayOfString2[i]);
                            stringBuilder.append(",");
                            str5 = stringBuilder.toString();
                        }
                    } else if (str4.length() > 0 && str3.length() == 0) {
                        str = str1;
                        if (str1.indexOf(arrayOfString2[i]) == -1) {
                            str = str1;
                            if (str4.indexOf(arrayOfString2[i]) == -1) {
                                StringBuilder stringBuilder = new StringBuilder();
                                this();
                                stringBuilder.append(str1);
                                stringBuilder.append(arrayOfString2[i]);
                                stringBuilder.append(",");
                                str5 = stringBuilder.toString();
                            }
                        }
                    } else {
                        str = str1;
                        if (str4.length() == 0) {
                            str = str1;
                            if (str3.length() > 0) {
                                str = str1;
                                if (str1.indexOf(arrayOfString2[i]) == -1) {
                                    str = str1;
                                    if (str3.indexOf(arrayOfString2[i]) > -1) {
                                        StringBuilder stringBuilder = new StringBuilder();
                                        this();
                                        stringBuilder.append(str1);
                                        stringBuilder.append(arrayOfString2[i]);
                                        stringBuilder.append(",");
                                        str5 = stringBuilder.toString();
                                    }
                                }
                            }
                        }
                    }
                    i++;
                    str1 = str5;
                }
                return str1;
            } catch (Exception exception) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Khhi");
                stringBuilder.append(paramString);
                return stringBuilder.toString();
            }
        }
        if (paramString.indexOf("trung") > -1 || paramString.indexOf("bor") > -1) {
            StringBuilder stringBuilder;
            if (paramString.indexOf("trung") > -1 && paramString.indexOf("bor") > -1) {
                if (paramString.indexOf("trung") < paramString.indexOf("bor")) {
                    if (paramString.substring(0, paramString.indexOf("trung")).length() > 1)
                        try {
                            str = XulySo(paramString.substring(0, paramString.indexOf("trung")));
                            if (str.indexOf("Khhi) > -1) {
                            if (str.length() > 11)
                                return str;
                            StringBuilder stringBuilder3 = new StringBuilder();
                            this();
                            stringBuilder3.append("Khhi");
                            stringBuilder3.append(paramString);
                            return stringBuilder3.toString();
                        }
                    if (paramString.substring(paramString.indexOf("trung") + 5, paramString.indexOf("bor")).length() > 1)
                        try {
                            String str1 = XulySo(paramString.substring(paramString.indexOf("trung") + 5, paramString.indexOf("bor")));
                            if (str1.indexOf("Khhi) > -1) {
                            if (str1.length() > 11)
                                return str1;
                            StringBuilder stringBuilder4 = new StringBuilder();
                            this();
                            stringBuilder4.append("Khhi");
                            stringBuilder4.append(paramString);
                            return stringBuilder4.toString();
                        }
                    if (paramString.substring(paramString.indexOf("bor") + 3).replaceAll("bor", "").length() > 1)
                        try {
                            String str2 = XulySo(paramString.substring(paramString.indexOf("bor") + 3).replaceAll("bor", ""));
                            if (str2.indexOf("Khhi) > -1) {
                            if (str2.length() > 11)
                                return str2;
                            StringBuilder stringBuilder4 = new StringBuilder();
                            this();
                            stringBuilder4.append("Khhi");
                            stringBuilder4.append(paramString);
                            return stringBuilder4.toString();
                        }
                    String[] arrayOfString = str.split(",");
                    paramString = "";
                    i = 0;
                    while (i < arrayOfString.length) {
                        String str3;
                        str = paramString;
                        if (str1.indexOf(arrayOfString[i]) > -1) {
                            str = paramString;
                            if (str2.indexOf(arrayOfString[i]) == -1) {
                                StringBuilder stringBuilder4 = new StringBuilder();
                                stringBuilder4.append(paramString);
                                stringBuilder4.append(arrayOfString[i]);
                                stringBuilder4.append(",");
                                str3 = stringBuilder4.toString();
                            }
                        }
                        i++;
                        paramString = str3;
                    }
                    return paramString;
                } catch (Exception exception) {
                    StringBuilder stringBuilder4 = new StringBuilder();
                    stringBuilder4.append("Khhi");
                    stringBuilder4.append(paramString.substring(paramString.indexOf("bor") + 3).replaceAll("bor", ""));
                    return stringBuilder4.toString();
                }
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("Khhi");
                stringBuilder3.append(paramString);
                return stringBuilder3.toString();
            } catch (Exception exception) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("Khhi");
                stringBuilder3.append(paramString);
                return stringBuilder3.toString();
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Khhi");
            stringBuilder2.append(paramString);
            return stringBuilder2.toString();
        } catch (Exception exception) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Khhi");
            stringBuilder2.append(paramString);
            return stringBuilder2.toString();
        }
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(paramString);
        return stringBuilder1.toString();
    }
        if (paramString.substring(0, paramString.indexOf("bor")).length() > 1)
            try {
        str = XulySo(paramString.substring(0, paramString.indexOf("bor")));
        if (str.indexOf("Khhi) > -1) {
        if (str.length() > 11)
            return str;
        StringBuilder stringBuilder2 = new StringBuilder();
        this();
        stringBuilder2.append("Khhi");
        stringBuilder2.append(paramString);
        return stringBuilder2.toString();
    }
            if (paramString.substring(paramString.indexOf("bor") + 4, paramString.indexOf("trung")).length() > 1)
            try {
        String str1 = XulySo(paramString.substring(paramString.indexOf("bor") + 4, paramString.indexOf("trung")));
        if (str1.indexOf("Khhi) > -1) {
        if (str1.length() > 11)
            return str1;
        StringBuilder stringBuilder3 = new StringBuilder();
        this();
        stringBuilder3.append("Khhi");
        stringBuilder3.append(paramString);
        return stringBuilder3.toString();
    }
                if (paramString.substring(paramString.indexOf("trung") + 5).length() > 1)
            try {
        String str2 = XulySo(paramString.substring(paramString.indexOf("trung") + 5).replaceAll("trung", ""));
        if (str2.indexOf("Khhi) > -1) {
        if (str2.length() > 11)
            return str2;
        StringBuilder stringBuilder3 = new StringBuilder();
        this();
        stringBuilder3.append("Khhi");
        stringBuilder3.append(paramString);
        return stringBuilder3.toString();
    }
    String[] arrayOfString = str.split(",");
    paramString = "";
    i = 0;
                    while (i < arrayOfString.length) {
        String str3;
        str = paramString;
        if (str2.indexOf(arrayOfString[i]) > -1) {
            str = paramString;
            if (str1.indexOf(arrayOfString[i]) == -1) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(paramString);
                stringBuilder3.append(arrayOfString[i]);
                stringBuilder3.append(",");
                str3 = stringBuilder3.toString();
            }
        }
        i++;
        paramString = str3;
    }
                    return paramString;
} catch (Exception exception) {
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Khhi");
        stringBuilder3.append(paramString);
        return stringBuilder3.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append(paramString);
        return stringBuilder2.toString();
        } catch (Exception exception) {
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append(paramString);
        return stringBuilder2.toString();
        }
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(paramString);
        return stringBuilder1.toString();
        } catch (Exception exception) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(paramString);
        return stringBuilder1.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append(paramString);
        return stringBuilder.toString();
        }
        if (paramString.indexOf("trung") == -1 && paramString.indexOf("bor") > -1) {
        String[] arrayOfString = paramString.split("bor");
        ArrayList<String> arrayList = new ArrayList();
        i = 0;
        while (i < arrayOfString.length) {
        String str1 = XulySo(arrayOfString[i]);
        if (str1.indexOf("Khhi) == -1) {
        arrayList.add(str1);
        i++;
        continue;
        }
        return str1;
        }
        try {
        String str1;
        String[] arrayOfString1 = ((String)arrayList.get(0)).split(",");
        i = 0;
        while (i < arrayOfString1.length) {
        String str2;
        byte b1 = 0;
        for (byte b2 = 1; b2 < arrayList.size() && ((String)arrayList.get(b2)).indexOf(arrayOfString1[i]) <= -1; b2++)
        b1++;
        StringBuilder stringBuilder2 = stringBuilder;
        if (b1 == arrayList.size() - 1) {
        stringBuilder2 = new StringBuilder();
        this();
        stringBuilder2.append((String)stringBuilder);
        stringBuilder2.append(arrayOfString1[i]);
        stringBuilder2.append(",");
        str2 = stringBuilder2.toString();
        }
        i++;
        str1 = str2;
        }
        if (str1.length() > 0)
        return str1;
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(paramString.substring(paramString.indexOf("bor")));
        return stringBuilder1.toString();
        } catch (Exception exception) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append(paramString);
        return stringBuilder.toString();
        }
        }
        if (paramString.indexOf("trung") > -1 && paramString.indexOf("bor") == -1) {
        String[] arrayOfString = paramString.split("trung");
        ArrayList<String> arrayList = new ArrayList();
        i = 0;
        while (i < arrayOfString.length) {
        String str1 = XulySo(arrayOfString[i]);
        if (str1.indexOf("Khhi) == -1) {
        arrayList.add(str1);
        i++;
        continue;
        }
        return str1;
        }
        try {
        String str1;
        String[] arrayOfString1 = ((String)arrayList.get(0)).split(",");
        i = 0;
        while (i < arrayOfString1.length) {
        String str2;
        byte b1 = 0;
        for (byte b2 = 1; b2 < arrayList.size() && ((String)arrayList.get(b2)).indexOf(arrayOfString1[i]) > -1; b2++)
        b1++;
        StringBuilder stringBuilder1 = stringBuilder;
        if (b1 == arrayList.size() - 1) {
        stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append((String)stringBuilder);
        stringBuilder1.append(arrayOfString1[i]);
        stringBuilder1.append(",");
        str2 = stringBuilder1.toString();
        }
        i++;
        str1 = str2;
        }
        return str1;
        } catch (Exception exception) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(paramString);
        return stringBuilder1.toString();
        }
        }
        return XulySo(paramString);
        }
        try {
        str = XulySo(paramString);
        if (str.indexOf("Khhi) > -1) {
        if (str.length() > 11)
        return str;
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Khhi");
        stringBuilder.append(paramString);
        return stringBuilder.toString();
        }
        } catch (Exception exception) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append(paramString);
        return stringBuilder.toString();
        }
        return XulySo(paramString);
        }

public static String XulySo(String paramString) {
        String str1 = paramString.replaceAll("tong ko chia", "ko chia 3").replaceAll("tong chia 3 du 1", "chia 3 du 1").replaceAll("tong chia 3 du 2", "chia 3 du 2");
        if (str1.indexOf("tong 10") > -1)
        return "Khhitong 10";
        str1 = str1.replaceAll(":", " ").replaceAll(";", " ");
        String str2 = ", ";
        String str3 = str1.replaceAll(" ,", ", ").replaceAll("tong > 10", "so 29,38,39,47,48,49,56,57,58,59,65,66,67,68,69,74,75,76,77,78,79,83,84,85,86,87,88,89,92,93,94,95,96,97,98,99,").replaceAll("tong < 10", "so 01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,20,21,22,23,24,25,26,27,30,31,32,33,34,35,36,40,41,42,43,44,45,50,51,52,53,54,60,61,62,63,70,71,72,80,81,90,").replaceAll("dau > dit", "so 10,20,21,30,31,32,40,41,42,43,50,51,52,53,54,60,61,62,63,64,65,70,71,72,73,74,75,76,80,81,82,83,84,85,86,87,90,91,92,93,94,95,96,97,98,").replaceAll("dit < dau", "so 10,20,21,30,31,32,40,41,42,43,50,51,52,53,54,60,61,62,63,64,65,70,71,72,73,74,75,76,80,81,82,83,84,85,86,87,90,91,92,93,94,95,96,97,98,").replaceAll("dau < dit", "so 01,02,03,04,05,06,07,08,09,12,13,14,15,16,17,18,19,23,24,25,26,27,28,29,34,35,36,37,38,39,45,46,47,48,49,56,57,58,59,67,68,69,78,79,89,").replaceAll("dit > dau", "so 01,02,03,04,05,06,07,08,09,12,13,14,15,16,17,18,19,23,24,25,26,27,28,29,34,35,36,37,38,39,45,46,47,48,49,56,57,58,59,67,68,69,78,79,89,").replaceAll("ko chia 3", "so 00,01,04,07,10,13,16,19,22,25,28,31,34,37,40,43,46,49,52,55,58,61,64,67,70,73,76,79,82,85,88,91,94,97,02,05,08,11,14,17,20,23,26,29,32,35,38,41,44,47,50,53,56,59,62,65,68,71,74,77,80,83,86,89,92,95,98,").replaceAll("tong chia 3", "so 03,06,09,12,15,18,21,24,27,30,33,36,39,42,45,48,51,54,57,60,63,66,69,72,75,78,81,84,87,90,93,96,99, ").replaceAll("chia 3 du 1", "so 01,04,07,10,13,16,19,22,25,28,31,34,37,40,43,46,49,52,55,58,61,64,67,70,73,76,79,82,85,88,91,94,97, ").replaceAll("chia 3 du 2", "so 02,05,08,11,14,17,20,23,26,29,32,35,38,41,44,47,50,53,56,59,62,65,68,71,74,77,80,83,86,89,92,95,98, ");
        if (str3.trim().length() < 2)
        return "Khhi";
        int i = 0;
        String str4 = "";
        str1 = "";
        String str5 = null;
        String str6 = "";
        String str7 = "";
        String str8 = "";
        String str9 = "";
        String str10 = "";
        String str11 = "";
        String str12 = "";
        while (true) {
        StringBuilder stringBuilder1;
        StringBuilder stringBuilder2;
        String str19;
        String str23;
        int j = i + 1;
        if (j > 50) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append(paramString);
        return stringBuilder2.toString();
        }
        int k = str3.indexOf(str4);
        if ((k > -1 && str3.length() == str4.length()) || str3.length() == 0) {
        str10 = str9;
        str9 = str3;
        str3 = str10;
        str10 = str8;
        continue;
        }
        str4 = str3;
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(" ");
        stringBuilder3.append(str3.trim());
        stringBuilder3.append(" ");
        String str20 = stringBuilder3.toString();
        k = str20.indexOf("den");
        String str21 = "0";
        str3 = str8;
        if (k > -1) {
        str19 = str20;
        if (str20.substring(0, 3).indexOf("tu") > -1)
        str19 = str20.substring(3);
        i = str19.indexOf("den");
        for (k = i; k > -1 && !isNumeric(str19.substring(k, k + 2)); k--);
        str20 = str19.substring(k, k + 2);
        while (i < str19.length() && !isNumeric(str19.substring(i, i + 2)))
        i++;
        String str = str19.substring(i, i + 2);
        if (Integer.parseInt(str20) < Integer.parseInt(str) && str20.length() > 0 && str.length() > 0) {
        int m = Integer.parseInt(str20);
        while (m < Integer.parseInt(str) + 1) {
        String str25;
        if (m < 10) {
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append((String)stringBuilder2);
        stringBuilder4.append("0");
        stringBuilder4.append(m);
        stringBuilder4.append(",");
        str25 = stringBuilder4.toString();
        } else {
        StringBuilder stringBuilder4 = stringBuilder2;
        if (m > 9) {
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append((String)stringBuilder2);
        stringBuilder4.append(m);
        stringBuilder4.append(",");
        str25 = stringBuilder4.toString();
        }
        }
        m++;
        str13 = str25;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str19.substring(0, k));
        stringBuilder.append(" ");
        stringBuilder.append(str19.substring(i + 2));
        str19 = stringBuilder.toString();
        String str24 = str20;
        } else {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append(paramString);
        return stringBuilder2.toString();
        }
        } else {
        str8 = "";
        str19 = str20;
        }
        k = str19.indexOf("ghep dit");
        String str22 = "Khhi;
        if (k > -1) {
        for (k = str19.indexOf("ghep dit");; k = m) {
        int m = k - 1;
        if (str19.substring(m, m + 3).equals("dau")) {
        String str;
        for (k = str19.indexOf("ghep dit") + 9; k < str19.length() - 1; k--) {
        if (str2.indexOf(str19.substring(k, k + 1)) > -1 || isNumeric(str19.substring(k, k + 1)) == true) {
        k++;
        continue;
        }
        }
        str6 = str19.substring(m, str19.indexOf("ghep dit")).replaceAll("dau", "");
        str23 = str19.substring(str19.indexOf("ghep dit"), k + 1).replaceAll("ghep dit", "");
        if (str6.length() == 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str19);
        return stringBuilder1.toString();
        }
        if (str23.length() == 0) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append((String)stringBuilder1);
        return stringBuilder2.toString();
        }
        if (!xuLymem(str6).booleanValue()) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str19.substring(m, str19.indexOf("ghep dit")));
        return stringBuilder1.toString();
        }
        if (!xuLymem(str23).booleanValue()) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str19.substring(str19.indexOf("ghep dit"), k + 1));
        return stringBuilder1.toString();
        }
        if (xuLymem(str6).booleanValue() && xuLymem(str23).booleanValue()) {
        str8 = GhepDau(str6);
        if (str8.indexOf("Khhi) > -1) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append((String)stringBuilder1);
        return stringBuilder2.toString();
        }
        str20 = GhepDit(str23);
        if (str20.indexOf("Khhi) > -1) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append((String)stringBuilder1);
        return stringBuilder2.toString();
        }
        i = 0;
        StringBuilder stringBuilder4 = stringBuilder2;
        str = str4;
        str7 = str22;
        while (i < 100) {
        if (i < 10) {
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append("0");
        stringBuilder5.append(i);
        if (str8.indexOf(stringBuilder5.toString()) > -1) {
        stringBuilder5 = new StringBuilder();
        stringBuilder5.append("0");
        stringBuilder5.append(i);
        if (str20.indexOf(stringBuilder5.toString()) > -1) {
        stringBuilder5 = new StringBuilder();
        stringBuilder5.append(str18);
        stringBuilder5.append("0");
        stringBuilder5.append(i);
        stringBuilder5.append(",");
        String str24 = stringBuilder5.toString();
        continue;
        }
        }
        }
        str9 = str18;
        if (i > 9) {
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append("");
        stringBuilder5.append(i);
        str9 = str18;
        if (str8.indexOf(stringBuilder5.toString()) > -1) {
        stringBuilder5 = new StringBuilder();
        stringBuilder5.append("");
        stringBuilder5.append(i);
        str9 = str18;
        if (str20.indexOf(stringBuilder5.toString()) > -1) {
        StringBuilder stringBuilder6 = new StringBuilder();
        stringBuilder6.append(str18);
        stringBuilder6.append(i);
        stringBuilder6.append(",");
        String str24 = stringBuilder6.toString();
        }
        }
        }
        continue;
        i++;
        str18 = str9;
        }
        str9 = str20;
        str4 = str18;
        str18 = str9;
        str22 = str;
        } else {
        str7 = "Khhi;
        str8 = str18;
        str22 = str4;
        str18 = str9;
        str4 = str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str19.substring(0, m));
        stringBuilder.append(" ");
        stringBuilder.append(str19.substring(k + 1));
        str9 = stringBuilder.toString().trim();
        str13 = str4;
        break;
        }
        }
        } else {
        str20 = "Khhi;
        str22 = str4;
        str8 = str18;
        str4 = str7;
        str7 = str20;
        str18 = str9;
        str23 = str6;
        str6 = str4;
        str9 = str19;
        }
        boolean bool = true;
        if (str9.trim().length() == 0) {
        str7 = str3;
        str3 = str18;
        str18 = str7;
        str7 = str6;
        continue;
        }
        if (str9.indexOf("ghep dau") > -1) {
        for (k = str9.indexOf("ghep dau");; k = m) {
        int m = k - 1;
        if (str9.substring(m, m + 3).equals("dit")) {
        String str;
        for (k = str9.indexOf("ghep dau") + 9; k < str9.length() - 1; k--) {
        if (str2.indexOf(str9.substring(k, k + 1)) > -1 || isNumeric(str9.substring(k, k + 1)) == true) {
        k++;
        continue;
        }
        }
        str6 = str9.substring(m, str9.indexOf("ghep dau")).replaceAll("dit", "");
        str19 = str9.substring(str9.indexOf("ghep dau"), k + 1).replaceAll("ghep dau", "");
        if (str6.length() == 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9);
        return stringBuilder1.toString();
        }
        if (str19.length() == 0) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append((String)stringBuilder1);
        return stringBuilder2.toString();
        }
        if (!xuLymem(str6).booleanValue()) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(m, str9.indexOf("ghep dau")));
        return stringBuilder1.toString();
        }
        if (!xuLymem(str19).booleanValue()) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(str9.indexOf("ghep dau"), k + 1));
        return stringBuilder1.toString();
        }
        if (xuLymem(str6).booleanValue() && xuLymem(str19).booleanValue()) {
        str8 = GhepDau(str19);
        str18 = str7;
        if (str8.indexOf(str18) > -1) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append((String)stringBuilder1);
        return stringBuilder2.toString();
        }
        str20 = GhepDit(str6);
        if (str20.indexOf(str18) > -1) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append((String)stringBuilder1);
        return stringBuilder2.toString();
        }
        i = 0;
        while (i < 100) {
        if (i < 10) {
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append("0");
        stringBuilder4.append(i);
        if (str8.indexOf(stringBuilder4.toString()) > -1) {
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("0");
        stringBuilder4.append(i);
        if (str20.indexOf(stringBuilder4.toString()) > -1) {
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append(str13);
        stringBuilder4.append("0");
        stringBuilder4.append(i);
        stringBuilder4.append(",");
        str = stringBuilder4.toString();
        continue;
        }
        }
        }
        str7 = str13;
        if (i > 9) {
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append("");
        stringBuilder4.append(i);
        str7 = str13;
        if (str8.indexOf(stringBuilder4.toString()) > -1) {
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("");
        stringBuilder4.append(i);
        str7 = str13;
        if (str20.indexOf(stringBuilder4.toString()) > -1) {
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append(str13);
        stringBuilder5.append(i);
        stringBuilder5.append(",");
        str = stringBuilder5.toString();
        }
        }
        }
        continue;
        i++;
        str13 = str7;
        }
        str4 = str18;
        str18 = str8;
        } else {
        str4 = str;
        str20 = str18;
        str18 = str8;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str9.substring(0, m));
        stringBuilder.append(" ");
        stringBuilder.append(str9.substring(k + 1));
        str9 = stringBuilder.toString().trim();
        str17 = str6;
        break;
        }
        }
        } else {
        str4 = str17;
        str20 = str18;
        str19 = str23;
        str17 = str6;
        str18 = str8;
        }
        if (str9.trim().length() == 0) {
        str18 = str3;
        str3 = str20;
        continue;
        }
        if (str9.indexOf("ghepdd") > -1) {
        int m = str9.indexOf("ghepdd");
        for (k = str9.indexOf("ghepdd") + 11; k < str9.length(); k--) {
        if (str2.indexOf(str9.substring(k, k + 1)) > -1 || isNumeric(str9.substring(k, k + 1)) == true) {
        k++;
        continue;
        }
        }
        str20 = str9.substring(str9.indexOf("ghepdd"), k).replaceAll("ghepdd", "").trim();
        if (str20.length() == 0) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append((String)stringBuilder1);
        return stringBuilder2.toString();
        }
        if (xuLymem(str20).booleanValue()) {
        String[] arrayOfString = str20.split(",");
        i = 0;
        str6 = str19;
        while (i < arrayOfString.length) {
        if (arrayOfString[i].length() == 2 && isNumeric(arrayOfString[i]) && i > 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(str9.indexOf(arrayOfString[i]), m));
        return stringBuilder1.toString();
        }
        if (!isNumeric(arrayOfString[i])) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(str9.indexOf(arrayOfString[i]), m));
        return stringBuilder1.toString();
        }
        i++;
        }
        str5 = GhepDau(str20);
        str18 = str4;
        if (str5.indexOf(str18) > -1) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append((String)stringBuilder1);
        return stringBuilder2.toString();
        }
        str23 = GhepDit(str20);
        if (str23.indexOf(str18) > -1) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append((String)stringBuilder1);
        return stringBuilder2.toString();
        }
        i = 0;
        while (i < 100) {
        if (i < 10) {
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append("0");
        stringBuilder4.append(i);
        if (str5.indexOf(stringBuilder4.toString()) > -1) {
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("0");
        stringBuilder4.append(i);
        if (str23.indexOf(stringBuilder4.toString()) > -1) {
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append(str13);
        stringBuilder4.append("0");
        stringBuilder4.append(i);
        stringBuilder4.append(",");
        String str24 = stringBuilder4.toString();
        continue;
        }
        }
        }
        str4 = str13;
        if (i > 9) {
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append("");
        stringBuilder4.append(i);
        str4 = str13;
        if (str5.indexOf(stringBuilder4.toString()) > -1) {
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("");
        stringBuilder4.append(i);
        str4 = str13;
        if (str23.indexOf(stringBuilder4.toString()) > -1) {
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append(str13);
        stringBuilder5.append(i);
        stringBuilder5.append(",");
        String str24 = stringBuilder5.toString();
        }
        }
        }
        continue;
        i++;
        str13 = str4;
        }
        str19 = str18;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str9.substring(0, m));
        stringBuilder.append(" ");
        stringBuilder.append(str9.substring(k));
        str9 = stringBuilder.toString().trim();
        String str = str23;
        str4 = str13;
        str13 = str20;
        str18 = str5;
        } else {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(str9.indexOf("ghepdd"), k));
        return stringBuilder1.toString();
        }
        } else {
        str6 = str19;
        str19 = str4;
        str8 = str20;
        str4 = str13;
        str13 = str5;
        }
        if (str9.indexOf("dan") > -1 && str9.indexOf("dan") < 5) {
        StringBuilder stringBuilder4;
        if (str9.length() < 5) {
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Khhi");
        stringBuilder4.append((String)stringBuilder1);
        return stringBuilder4.toString();
        }
        k = -1;
        StringBuilder stringBuilder5 = stringBuilder4;
        String str = str6;
        str20 = str18;
        str18 = str21;
        while (true) {
        StringBuilder stringBuilder6;
        String str24;
        bool = true;
        int m = str9.indexOf("dan", k + 1);
        str6 = str20;
        if (m != -1) {
        String str26;
        for (k = m + 4; k < str9.length(); k--) {
        if (isNumeric(str9.substring(k, k + 1)) || str2.indexOf(str9.substring(k, k + 1)) > -1) {
        k++;
        continue;
        }
        }
        str20 = str9.substring(m + 4, k);
        if (str20.length() == 0) {
        stringBuilder6 = new StringBuilder();
        stringBuilder6.append("Khhi");
        stringBuilder6.append((String)stringBuilder1);
        return stringBuilder6.toString();
        }
        str20 = str20.trim().replaceAll(" ", ",").trim().replaceAll(",,", ",").trim().replaceAll(",,", ",");
        if (str20.length() == 3 && str20.indexOf(",") > -1)
        str20 = str20.replace(",", "");
        String[] arrayOfString = str20.split(",");
        i = 0;
        str23 = str8;
        str8 = str6;
        str21 = str11;
        str6 = str4;
        str4 = str20;
        while (i < arrayOfString.length) {
        arrayOfString[i] = arrayOfString[i].replaceAll(" ", "");
        if (arrayOfString[i].length() != 2 || !isNumeric(arrayOfString[i])) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(str9.indexOf(arrayOfString[i]), k));
        return stringBuilder1.toString();
        }
        if (Integer.parseInt(arrayOfString[i].substring(0, 1)) >= Integer.parseInt(arrayOfString[i].substring(1, 2)) - 1) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(str9.indexOf(arrayOfString[i]), k));
        return stringBuilder1.toString();
        }
        if (!isNumeric(arrayOfString[i])) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(m, k));
        return stringBuilder1.toString();
        }
        str = "";
        if (arrayOfString[i].length() == 2 && isNumeric(arrayOfString[i])) {
        int n = Integer.parseInt(arrayOfString[i].substring(0, 1));
        str17 = "";
        while (n < Integer.parseInt(arrayOfString[i].substring(1)) + 1) {
        StringBuilder stringBuilder8 = new StringBuilder();
        stringBuilder8.append(str17);
        stringBuilder8.append(n);
        str17 = stringBuilder8.toString();
        stringBuilder8 = new StringBuilder();
        stringBuilder8.append(str);
        stringBuilder8.append(n);
        str = stringBuilder8.toString();
        n++;
        }
        str11 = GhepDau(str17);
        n = str11.indexOf(str19);
        str20 = str17;
        if (n > -1) {
        stringBuilder6 = new StringBuilder();
        stringBuilder6.append("Khhi");
        stringBuilder6.append((String)stringBuilder1);
        return stringBuilder6.toString();
        }
        String[] arrayOfString1 = str11.split(",");
        str17 = GhepDit((String)stringBuilder6);
        n = str17.indexOf(str19);
        String str27 = str19;
        if (n > -1) {
        stringBuilder6 = new StringBuilder();
        stringBuilder6.append("Khhi");
        stringBuilder6.append((String)stringBuilder1);
        return stringBuilder6.toString();
        }
        str8 = str17;
        n = 0;
        while (n < arrayOfString1.length) {
        String str28;
        str19 = str6;
        if (str8.indexOf(arrayOfString1[n]) > -1) {
        StringBuilder stringBuilder8 = new StringBuilder();
        stringBuilder8.append(str6);
        stringBuilder8.append(arrayOfString1[n]);
        stringBuilder8.append(",");
        str28 = stringBuilder8.toString();
        }
        n++;
        str6 = str28;
        }
        i++;
        str21 = str8;
        str8 = str11;
        str26 = str17;
        str17 = str20;
        str19 = str27;
        continue;
        }
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(m, k));
        return stringBuilder1.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str9.substring(0, m));
        stringBuilder.append(" ");
        stringBuilder.append(str9.substring(k));
        str9 = stringBuilder.toString().trim();
        k = 0;
        String str25 = str8;
        str24 = str4;
        str4 = str6;
        str11 = str21;
        str8 = str26;
        continue;
        }
        str23 = str18;
        str18 = str19;
        str20 = str6;
        str19 = str24;
        str6 = str23;
        if (str9.trim().length() == 0) {
        str18 = str3;
        str3 = str8;
        str = str4;
        continue;
        }
        k = str9.indexOf("boj");
        if (k > -1 && str9.indexOf("boj") < 5) {
        if (str9.length() < 5) {
        stringBuilder6 = new StringBuilder();
        stringBuilder6.append("Khhi");
        stringBuilder6.append((String)stringBuilder1);
        return stringBuilder6.toString();
        }
        k = -1;
        while (true) {
        StringBuilder stringBuilder8;
        String str27;
        int n = str9.indexOf("boj", k + 1);
        if (n != -1) {
        for (k = n + 4; k < str9.length(); k--) {
        if (str2.indexOf(str9.substring(k, k + 1)) > -1 || isNumeric(str9.substring(k, k + 1)) == true) {
        k++;
        continue;
        }
        }
        try {
        str19 = str9.substring(n, k);
        str19 = str19.replaceAll("boj", "").trim();
        if (str19.trim().length() == 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9);
        return stringBuilder1.toString();
        }
        str19 = str19.trim().replaceAll(" ", ",").trim().replaceAll(",,", ",");
        String[] arrayOfString = str19.split(",");
        i = 0;
        str24 = str4;
        str4 = str19;
        while (i < arrayOfString.length) {
        if (isNumeric(arrayOfString[i]) == true && arrayOfString[i].length() == 2) {
        StringBuilder stringBuilder10 = new StringBuilder();
        stringBuilder10.append(str24);
        stringBuilder10.append(GhepBo(arrayOfString[i]));
        str24 = stringBuilder10.toString();
        i++;
        continue;
        }
        if (!isNumeric(arrayOfString[i])) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(n, k));
        return stringBuilder1.toString();
        }
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(str9.indexOf(arrayOfString[i]), k));
        return stringBuilder1.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str9.substring(0, n));
        stringBuilder.append(" ");
        stringBuilder.append(str9.substring(k));
        str9 = stringBuilder.toString().trim();
        k = 0;
        str27 = str4;
        str4 = str24;
        } catch (Exception exception) {
        stringBuilder8 = new StringBuilder();
        stringBuilder8.append("Khhi");
        stringBuilder8.append((String)stringBuilder1);
        return stringBuilder8.toString();
        }
        continue;
        }
        StringBuilder stringBuilder9 = stringBuilder8;
        String str25 = str17;
        if (str9.trim().length() == 0) {
        str18 = str3;
        str3 = str8;
        str17 = str25;
        str25 = str4;
        continue;
        }
        if (str9.indexOf("cham tong") > -1 && str9.indexOf("cham tong") < 5) {
        if (str9.length() > 10) {
        if (str9.substring(0, 11).indexOf("cham tong") == -1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append((String)stringBuilder1);
        return stringBuilder.toString();
        }
        k = -1;
        str17 = str3;
        str3 = str20;
        while (true) {
        StringBuilder stringBuilder;
        bool = true;
        n = str9.indexOf("cham tong", k + 1);
        if (n != -1) {
        k = n + 10;
        while (true) {
        i = k;
        if (k < str9.length()) {
        if ("0123456789, ".indexOf(str9.substring(k, k + 1)) > -1) {
        k++;
        continue;
        }
        i = k - 1;
        }
        break;
        }
        if (str9.length() > 10) {
        str17 = str9.substring(n, i).replaceAll("cham tong", "").trim().replaceAll(" ", ",");
        } else {
        str17 = "";
        }
        if (str17.length() == 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(n, i));
        return stringBuilder1.toString();
        }
        String[] arrayOfString = str17.split(",");
        for (k = 0; k < arrayOfString.length; k++) {
        if (arrayOfString[k].length() == 2 && isNumeric(arrayOfString[k]) && k > 0 && !xuLymem(str17).booleanValue()) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(str9.indexOf(arrayOfString[k]), i));
        return stringBuilder1.toString();
        }
        if (arrayOfString[k].length() == 3 && isNumeric(arrayOfString[k]) && k > 0 && !xuLymem(str17).booleanValue()) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(str9.indexOf(arrayOfString[k]), i));
        return stringBuilder1.toString();
        }
        if (!isNumeric(arrayOfString[k])) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(n, i));
        return stringBuilder1.toString();
        }
        }
        str27 = GhepDau(str17);
        if (str27.indexOf(str18) > -1) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append((String)stringBuilder1);
        return stringBuilder.toString();
        }
        str26 = GhepDit(str17);
        if (str26.indexOf(str18) > -1) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append((String)stringBuilder1);
        return stringBuilder.toString();
        }
        str20 = GhepTong(str17);
        if (str20.indexOf(str18) > -1) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append((String)stringBuilder1);
        return stringBuilder.toString();
        }
        k = 0;
        str3 = str16;
        while (true) {
        k++;
        str4 = str16;
        }
        continue;
        }
        str20 = str2;
        str2 = str3;
        str3 = str16;
        str16 = str18;
        str18 = str26;
        str26 = str3;
        str3 = str18;
        str18 = str17;
        str17 = str20;
        if (str9.trim().length() == 0) {
        StringBuilder stringBuilder10 = stringBuilder;
        String str28 = str4;
        continue;
        }
        if (str9.indexOf("cham") > -1 && str9.indexOf("cham") < 5) {
        if (str9.length() < 6) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append((String)stringBuilder1);
        return stringBuilder.toString();
        }
        k = -1;
        while (true) {
        StringBuilder stringBuilder10;
        bool = true;
        n = str9.indexOf("cham", k + 1);
        if (n != -1) {
        k = n + 5;
        while (true) {
        i = k;
        if (k < str9.length()) {
        if ("0123456789, ".indexOf(str9.substring(k, k + 1)) > -1) {
        k++;
        continue;
        }
        i = k - 1;
        }
        break;
        }
        if (str9.length() > 5) {
        str2 = str9.substring(n, i).replaceAll("cham", "").trim().replaceAll(" ", ",");
        } else {
        str2 = "";
        }
        if (str2.length() == 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(n, i));
        return stringBuilder1.toString();
        }
        String[] arrayOfString = str2.split(",");
        for (k = 0; k < arrayOfString.length; k++) {
        if (arrayOfString[k].length() == 2 && isNumeric(arrayOfString[k]) && k > 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(str9.indexOf(arrayOfString[k]), i));
        return stringBuilder1.toString();
        }
        if (arrayOfString[k].length() == 3 && isNumeric(arrayOfString[k]) && k > 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(str9.indexOf(arrayOfString[k]), i));
        return stringBuilder1.toString();
        }
        if (!isNumeric(arrayOfString[k])) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(n, i));
        return stringBuilder1.toString();
        }
        }
        str20 = GhepDau(str2);
        if (str20.indexOf(str16) > -1) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append((String)stringBuilder1);
        return stringBuilder.toString();
        }
        str19 = GhepDit(str2);
        if (str19.indexOf(str16) > -1) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append((String)stringBuilder1);
        return stringBuilder.toString();
        }
        k = 0;
        while (true) {
        k++;
        str4 = str15;
        }
        continue;
        }
        str23 = str16;
        if (str9.trim().length() == 0) {
        stringBuilder10 = stringBuilder;
        String str29 = str4;
        continue;
        }
        if (str9.indexOf("tong") > -1 && str9.indexOf("tong") < 5) {
        StringBuilder stringBuilder11;
        if (str9.length() < 6) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append((String)stringBuilder1);
        return stringBuilder.toString();
        }
        k = -1;
        str16 = str18;
        str26 = str19;
        str18 = str15;
        while (true) {
        String str29;
        bool = true;
        n = str9.indexOf("tong", k + 1);
        if (n != -1) {
        k = n + 5;
        str15 = str28;
        while (k < str9.length()) {
        str16 = str9.substring(k, k + 1);
        str28 = str15;
        if (str28.indexOf(str16) > -1 || isNumeric(str9.substring(k, k + 1)) == true) {
        k++;
        str15 = str28;
        continue;
        }
        k--;
        }
        if (str9.substring(n, k).length() > 5) {
        str28 = str9.substring(n, k).replaceAll("tong", "").trim().replaceAll(" ", ",");
        } else {
        str28 = "";
        }
        if (str28.length() == 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9);
        return stringBuilder1.toString();
        }
        String[] arrayOfString = str28.split(",");
        for (i = 0; i < arrayOfString.length; i++) {
        if (arrayOfString[i].length() == 2 && isNumeric(arrayOfString[i]) && i > 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(str9.indexOf(arrayOfString[i]), k));
        return stringBuilder1.toString();
        }
        if (arrayOfString[i].length() == 3 && isNumeric(arrayOfString[i]) && i > 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(str9.indexOf(arrayOfString[i]), k));
        return stringBuilder1.toString();
        }
        if (!isNumeric(arrayOfString[i])) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(n, k));
        return stringBuilder1.toString();
        }
        }
        str29 = GhepTong(str28);
        if (str29.indexOf(str23) > -1) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append((String)stringBuilder1);
        return stringBuilder.toString();
        }
        continue;
        }
        str19 = str28;
        StringBuilder stringBuilder12 = stringBuilder11;
        str15 = str29;
        if (str9.trim().length() == 0) {
        String str31 = str18;
        str18 = str15;
        str15 = str31;
        stringBuilder12 = stringBuilder;
        String str30 = str4;
        continue;
        }
        if (str9.indexOf("ghep") == -1 && str9.indexOf("dau dit") > -1) {
        k = -1;
        StringBuilder stringBuilder13 = stringBuilder12;
        String str30 = str18;
        while (true) {
        String str31;
        bool = true;
        n = str9.indexOf("dau dit", k + 1);
        if (n != -1) {
        k = n + 8;
        while (true) {
        i = k;
        if (k < str9.length()) {
        if (str19.indexOf(str9.substring(k, k + 1)) > -1 || isNumeric(str9.substring(k, k + 1)) == true) {
        k++;
        continue;
        }
        i = k - 1;
        }
        break;
        }
        if (str9.length() > 8) {
        str18 = str9.substring(n, i);
        if (str18.substring(0, 8).indexOf("dau dit") > -1)
        str18 = str18.replaceAll("dau dit", "").trim().replaceAll(" ", ",");
        } else {
        str18 = "";
        }
        if (str18.length() == 0) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append((String)stringBuilder1);
        return stringBuilder.toString();
        }
        String[] arrayOfString = str18.split(",");
        for (k = 0; k < arrayOfString.length; k++) {
        if (arrayOfString[k].length() == 2 && isNumeric(arrayOfString[k]) && k > 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(str9.indexOf(arrayOfString[k]), i));
        return stringBuilder1.toString();
        }
        if (arrayOfString[k].length() == 3 && isNumeric(arrayOfString[k]) && k > 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(str9.indexOf(arrayOfString[k]), i));
        return stringBuilder1.toString();
        }
        if (!isNumeric(arrayOfString[k])) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str9.substring(n, i));
        return stringBuilder1.toString();
        }
        }
        str14 = GhepDau(str18);
        if (str14.indexOf(str23) > -1) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append((String)stringBuilder1);
        return stringBuilder.toString();
        }
        StringBuilder stringBuilder14 = new StringBuilder();
        stringBuilder14.append(str4);
        stringBuilder14.append(str14);
        str4 = stringBuilder14.toString();
        str32 = GhepDit(str18);
        if (str32.indexOf(str23) > -1) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append((String)stringBuilder1);
        return stringBuilder.toString();
        }
        stringBuilder13 = new StringBuilder();
        stringBuilder13.append(str4);
        stringBuilder13.append(str32);
        str4 = stringBuilder13.toString();
        stringBuilder13 = new StringBuilder();
        stringBuilder13.append(str9.substring(0, n));
        stringBuilder13.append(" ");
        stringBuilder13.append(str9.substring(i));
        str9 = stringBuilder13.toString().trim();
        k = 0;
        str31 = str18;
        continue;
        }
        str18 = str32;
        String str32 = str31;
        break;
        }
        }
        if (str9.trim().length() == 0) {
        String str31 = str15;
        str15 = str18;
        str18 = str31;
        stringBuilder12 = stringBuilder;
        String str30 = str4;
        continue;
        }
        if (str9.indexOf("dau") > -1 && str9.indexOf("dau") < 5) {
        if (str9.indexOf("ghep") == -1 && str9.indexOf("dau dit") == -1) {
        if (str9.length() < 5) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append((String)stringBuilder1);
        return stringBuilder.toString();
        }
        k = -1;
        StringBuilder stringBuilder13 = stringBuilder12;
        str28 = str18;
        while (true) {
        bool = true;
        n = str9.indexOf("dau", k + 1);
        stringBuilder13 = new StringBuilder();
        stringBuilder13.append(str4);
        stringBuilder13.append(str14);
        str4 = stringBuilder13.toString();
        stringBuilder13 = new StringBuilder();
        stringBuilder13.append(str9.substring(0, n));
        stringBuilder13.append(" ");
        stringBuilder13.append(str9.substring(i));
        str9 = stringBuilder13.toString().trim();
        k = 0;
        str30 = str18;
        }
        break;
        }
        str20 = str14;
        continue;
        }
        str20 = str14;
        continue;
        stringBuilder11 = new StringBuilder();
        stringBuilder11.append(str4);
        stringBuilder11.append(str16);
        str4 = stringBuilder11.toString();
        stringBuilder11 = new StringBuilder();
        stringBuilder11.append(str9.substring(0, n));
        stringBuilder11.append(" ");
        stringBuilder11.append(str9.substring(k));
        str9 = stringBuilder11.toString().trim();
        k = 0;
        stringBuilder11 = stringBuilder10;
        str28 = str15;
        }
        break;
        }
        str26 = str15;
        str16 = str28;
        str15 = str18;
        str18 = str26;
        String str28 = str19;
        str19 = str16;
        continue;
        stringBuilder10 = new StringBuilder();
        stringBuilder10.append(str9.substring(0, n));
        stringBuilder10.append(" ");
        stringBuilder10.append(str9.substring(i));
        str9 = stringBuilder10.toString().trim();
        k = 0;
        str15 = str27;
        str27 = str2;
        str2 = str20;
        }
        break;
        }
        str23 = str16;
        continue;
        stringBuilder = new StringBuilder();
        stringBuilder.append(str9.substring(0, n));
        stringBuilder.append(" ");
        stringBuilder.append(str9.substring(i));
        str9 = stringBuilder.toString().trim();
        k = 0;
        str16 = str20;
        str20 = str3;
        str3 = str27;
        str27 = str17;
        str17 = str16;
        str16 = str20;
        }
        break;
        }
        stringBuilder6 = new StringBuilder();
        stringBuilder6.append("Khhi");
        stringBuilder6.append((String)stringBuilder1);
        return stringBuilder6.toString();
        }
        str17 = str14;
        str23 = str16;
        str16 = str18;
        str14 = str20;
        str18 = str15;
        str15 = str26;
        String str26 = str23;
        continue;
        }
        break;
        }
        StringBuilder stringBuilder7 = stringBuilder6;
        str13 = str17;
        continue;
        }
        } else {
        str23 = "0";
        str5 = str19;
        str20 = str16;
        str16 = str23;
        str19 = str13;
        str13 = str20;
        str20 = str18;
        str18 = str5;
        continue;
        }
        String str17 = str14;
        String str18 = str20;
        str8 = str15;
        String str14 = str19;
        String str15 = str9;
        str12 = str16;
        str9 = str17;
        str17 = str13;
        String str16 = str5;
        str5 = str23;
        String str13 = str4;
        str4 = str22;
        }
        }

public static String XulyTien(String paramString) {
        String str1 = "";
        String str2 = "";
        if (paramString.length() - paramString.replaceAll("x", "").length() > 1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append(paramString);
        return stringBuilder.toString();
        }
        if (paramString.length() == 0)
        return "Khhi";
        String str3 = paramString.replaceAll("x", "").replaceAll(" ", "").trim();
        if (str3.length() > 0) {
        StringBuilder stringBuilder1;
        StringBuilder stringBuilder2;
        String[] arrayOfString;
        if (str3.endsWith("tr")) {
        str3 = str3.replaceAll("tr", "").trim().replaceAll("\\.", "");
        str3.indexOf(",");
        arrayOfString = str3.split(",");
        if (arrayOfString.length > 2) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str3);
        return stringBuilder1.toString();
        }
        if (arrayOfString.length == 1) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(arrayOfString[0]);
        stringBuilder1.append("000");
        return stringBuilder1.toString();
        }
        if (arrayOfString.length == 2) {
        if (arrayOfString[1].length() == 0) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(arrayOfString[0]);
        stringBuilder.append("000");
        return stringBuilder.toString();
        }
        if (arrayOfString[1].length() == 1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(arrayOfString[0]);
        stringBuilder.append(arrayOfString[1]);
        stringBuilder.append("00");
        return stringBuilder.toString();
        }
        if (arrayOfString[1].length() == 2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(arrayOfString[0]);
        stringBuilder.append(arrayOfString[1]);
        stringBuilder.append("0");
        return stringBuilder.toString();
        }
        if (arrayOfString[1].length() == 3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(arrayOfString[0]);
        stringBuilder.append(arrayOfString[1]);
        return stringBuilder.toString();
        }
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(str3);
        return stringBuilder1.toString();
        }
        } else {
        String str;
        byte b;
        for (b = 0; b < str3.length() && isNumeric(str3.substring(b, b + 1)) != true; b++);
        String[] arrayOfString1 = arrayOfString;
        while (b < str3.length() && isNumeric(str3.substring(b, b + 1)) == true) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((String)arrayOfString1);
        stringBuilder.append(str3.substring(b, b + 1));
        str = stringBuilder.toString();
        b++;
        }
        if (str3.replaceAll(str, "").replaceAll("ng", "").replaceAll("n", "").replaceAll("d", "").replaceAll("k", "").replaceAll(",", "").replaceAll("\\.", "").replaceAll("/", "").replaceAll(" ", "").length() > 0) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append((String)stringBuilder1);
        return stringBuilder2.toString();
        }
        }
        try {
        if (Integer.parseInt((String)stringBuilder2) > 0)
        return (String)stringBuilder2;
        stringBuilder2 = new StringBuilder();
        this();
        stringBuilder2.append("Khhi");
        stringBuilder2.append((String)stringBuilder1);
        return stringBuilder2.toString();
        } catch (Exception exception) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append((String)stringBuilder1);
        return stringBuilder.toString();
        }
        }
        return "Khhi;
        }

public static String XulyXien(String paramString) {
        // Byte code:
        //   0: ldc_w ' ,'
        //   3: astore_1
        //   4: iconst_0
        //   5: istore_2
        //   6: iconst_0
        //   7: istore_3
        //   8: iconst_0
        //   9: istore #4
        //   11: iconst_0
        //   12: istore #5
        //   14: ldc ''
        //   16: astore #6
        //   18: iconst_1
        //   19: istore #7
        //   21: iconst_1
        //   22: istore #8
        //   24: aload_0
        //   25: invokevirtual length : ()I
        //   28: bipush #6
        //   30: if_icmple -> 291
        //   33: aload_0
        //   34: invokevirtual trim : ()Ljava/lang/String;
        //   37: ldc_w '[,.;-]'
        //   40: ldc ' '
        //   42: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   45: astore #9
        //   47: aload #9
        //   49: ldc_w '2 '
        //   52: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   55: ifne -> 86
        //   58: aload #9
        //   60: ldc_w '3 '
        //   63: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   66: ifne -> 86
        //   69: aload #9
        //   71: ldc_w '4 '
        //   74: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   77: ifeq -> 83
        //   80: goto -> 86
        //   83: goto -> 320
        //   86: aload #9
        //   88: ldc_w '2 '
        //   91: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   94: ifeq -> 102
        //   97: iconst_5
        //   98: istore_2
        //   99: goto -> 136
        //   102: aload #9
        //   104: ldc_w '3 '
        //   107: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   110: ifeq -> 119
        //   113: bipush #8
        //   115: istore_2
        //   116: goto -> 136
        //   119: iload #5
        //   121: istore_2
        //   122: aload #9
        //   124: ldc_w '4 '
        //   127: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   130: ifeq -> 136
        //   133: bipush #11
        //   135: istore_2
        //   136: aload #9
        //   138: iconst_2
        //   139: invokevirtual substring : (I)Ljava/lang/String;
        //   142: invokestatic XulySo : (Ljava/lang/String;)Ljava/lang/String;
        //   145: astore_0
        //   146: aload_0
        //   147: astore #9
        //   149: aload_0
        //   150: invokevirtual length : ()I
        //   153: iload_3
        //   154: isub
        //   155: iload_2
        //   156: if_icmple -> 285
        //   159: iload_3
        //   160: iload_2
        //   161: iadd
        //   162: istore_3
        //   163: new java/lang/StringBuilder
        //   166: dup
        //   167: invokespecial <init> : ()V
        //   170: astore #9
        //   172: aload #9
        //   174: aload_0
        //   175: iconst_0
        //   176: iload_3
        //   177: invokevirtual substring : (II)Ljava/lang/String;
        //   180: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   183: pop
        //   184: aload #9
        //   186: ldc ' '
        //   188: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   191: pop
        //   192: aload #9
        //   194: aload_0
        //   195: iload_3
        //   196: iconst_1
        //   197: iadd
        //   198: invokevirtual substring : (I)Ljava/lang/String;
        //   201: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   204: pop
        //   205: aload #9
        //   207: invokevirtual toString : ()Ljava/lang/String;
        //   210: astore_0
        //   211: aload_0
        //   212: iload_3
        //   213: iconst_1
        //   214: iadd
        //   215: invokevirtual substring : (I)Ljava/lang/String;
        //   218: invokevirtual length : ()I
        //   221: iload_2
        //   222: if_icmpge -> 279
        //   225: aload_0
        //   226: iload_3
        //   227: iconst_1
        //   228: iadd
        //   229: invokevirtual substring : (I)Ljava/lang/String;
        //   232: invokevirtual length : ()I
        //   235: iconst_2
        //   236: if_icmple -> 279
        //   239: new java/lang/StringBuilder
        //   242: dup
        //   243: invokespecial <init> : ()V
        //   246: astore #9
        //   248: aload #9
        //   250: ldc 'Khhi'
        //   252: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   255: pop
        //   256: aload #9
        //   258: aload_0
        //   259: iload_3
        //   260: iconst_1
        //   261: iadd
        //   262: invokevirtual substring : (I)Ljava/lang/String;
        //   265: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   268: pop
        //   269: aload #9
        //   271: invokevirtual toString : ()Ljava/lang/String;
        //   274: astore #9
        //   276: goto -> 285
        //   279: iinc #3, 1
        //   282: goto -> 146
        //   285: aload #9
        //   287: invokevirtual trim : ()Ljava/lang/String;
        //   290: areturn
        //   291: aload_0
        //   292: invokevirtual trim : ()Ljava/lang/String;
        //   295: ldc_w '2 '
        //   298: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   301: ifeq -> 320
        //   304: aload_0
        //   305: invokevirtual trim : ()Ljava/lang/String;
        //   308: ldc_w '2 '
        //   311: ldc ''
        //   313: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   316: astore_0
        //   317: goto -> 320
        //   320: aload_0
        //   321: ldc_w ';'
        //   324: invokevirtual indexOf : (Ljava/lang/String;)I
        //   327: iconst_m1
        //   328: if_icmple -> 713
        //   331: aload_0
        //   332: astore #9
        //   334: aload #9
        //   336: ldc_w ';'
        //   339: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   342: astore #10
        //   344: iconst_0
        //   345: istore_3
        //   346: iload #8
        //   348: istore_2
        //   349: aload #6
        //   351: astore_0
        //   352: iload_3
        //   353: aload #10
        //   355: arraylength
        //   356: if_icmpge -> 710
        //   359: aload #10
        //   361: iload_3
        //   362: aload #10
        //   364: iload_3
        //   365: aaload
        //   366: ldc ' '
        //   368: ldc ','
        //   370: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   373: aastore
        //   374: aload #10
        //   376: iload_3
        //   377: aload #10
        //   379: iload_3
        //   380: aaload
        //   381: ldc_w ',,'
        //   384: ldc ','
        //   386: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   389: aastore
        //   390: aload #10
        //   392: iload_3
        //   393: aaload
        //   394: ldc ','
        //   396: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   399: astore_1
        //   400: iconst_0
        //   401: istore #7
        //   403: iload #7
        //   405: aload_1
        //   406: arraylength
        //   407: if_icmpge -> 652
        //   410: aload_1
        //   411: iload #7
        //   413: aaload
        //   414: invokevirtual length : ()I
        //   417: iconst_3
        //   418: if_icmpne -> 594
        //   421: aload_1
        //   422: iload #7
        //   424: aaload
        //   425: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   428: ifeq -> 594
        //   431: aload_1
        //   432: iload #7
        //   434: aaload
        //   435: iconst_0
        //   436: iconst_1
        //   437: invokevirtual substring : (II)Ljava/lang/String;
        //   440: astore #6
        //   442: aload_1
        //   443: iload #7
        //   445: aaload
        //   446: iconst_1
        //   447: iconst_2
        //   448: invokevirtual substring : (II)Ljava/lang/String;
        //   451: astore #11
        //   453: aload_1
        //   454: iload #7
        //   456: aaload
        //   457: iconst_2
        //   458: iconst_3
        //   459: invokevirtual substring : (II)Ljava/lang/String;
        //   462: astore #12
        //   464: aload #6
        //   466: aload #11
        //   468: invokevirtual indexOf : (Ljava/lang/String;)I
        //   471: iconst_m1
        //   472: if_icmpne -> 586
        //   475: aload #6
        //   477: aload #12
        //   479: invokevirtual indexOf : (Ljava/lang/String;)I
        //   482: iconst_m1
        //   483: if_icmple -> 586
        //   486: aload_1
        //   487: iload #7
        //   489: aaload
        //   490: astore #6
        //   492: new java/lang/StringBuilder
        //   495: dup
        //   496: invokespecial <init> : ()V
        //   499: astore #11
        //   501: aload #11
        //   503: aload_1
        //   504: iload #7
        //   506: aaload
        //   507: iconst_0
        //   508: iconst_1
        //   509: invokevirtual substring : (II)Ljava/lang/String;
        //   512: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   515: pop
        //   516: aload #11
        //   518: aload_1
        //   519: iload #7
        //   521: aaload
        //   522: iconst_1
        //   523: iconst_2
        //   524: invokevirtual substring : (II)Ljava/lang/String;
        //   527: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   530: pop
        //   531: aload #11
        //   533: ldc ','
        //   535: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   538: pop
        //   539: aload #11
        //   541: aload_1
        //   542: iload #7
        //   544: aaload
        //   545: iconst_1
        //   546: iconst_2
        //   547: invokevirtual substring : (II)Ljava/lang/String;
        //   550: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   553: pop
        //   554: aload #11
        //   556: aload_1
        //   557: iload #7
        //   559: aaload
        //   560: iconst_0
        //   561: iconst_1
        //   562: invokevirtual substring : (II)Ljava/lang/String;
        //   565: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   568: pop
        //   569: aload #9
        //   571: aload #6
        //   573: aload #11
        //   575: invokevirtual toString : ()Ljava/lang/String;
        //   578: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   581: astore #6
        //   583: goto -> 642
        //   586: ldc ''
        //   588: astore_0
        //   589: iconst_0
        //   590: istore_2
        //   591: goto -> 652
        //   594: aload_1
        //   595: iload #7
        //   597: aaload
        //   598: invokevirtual length : ()I
        //   601: iconst_2
        //   602: if_icmpne -> 619
        //   605: aload #9
        //   607: astore #6
        //   609: aload_1
        //   610: iload #7
        //   612: aaload
        //   613: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   616: ifne -> 583
        //   619: aload #9
        //   621: astore #6
        //   623: aload_1
        //   624: iload #7
        //   626: aaload
        //   627: invokevirtual length : ()I
        //   630: iconst_1
        //   631: if_icmple -> 642
        //   634: ldc ''
        //   636: astore_0
        //   637: iconst_0
        //   638: istore_2
        //   639: goto -> 652
        //   642: iinc #7, 1
        //   645: aload #6
        //   647: astore #9
        //   649: goto -> 403
        //   652: aload_0
        //   653: astore #6
        //   655: iload_2
        //   656: iconst_1
        //   657: if_icmpne -> 701
        //   660: new java/lang/StringBuilder
        //   663: dup
        //   664: invokespecial <init> : ()V
        //   667: astore #6
        //   669: aload #6
        //   671: aload_0
        //   672: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   675: pop
        //   676: aload #6
        //   678: aload #10
        //   680: iload_3
        //   681: aaload
        //   682: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   685: pop
        //   686: aload #6
        //   688: ldc ' '
        //   690: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   693: pop
        //   694: aload #6
        //   696: invokevirtual toString : ()Ljava/lang/String;
        //   699: astore #6
        //   701: iinc #3, 1
        //   704: aload #6
        //   706: astore_0
        //   707: goto -> 352
        //   710: goto -> 1831
        //   713: aload_0
        //   714: ldc ' '
        //   716: ldc ''
        //   718: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   721: astore #9
        //   723: aload #9
        //   725: ldc ','
        //   727: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   730: astore #11
        //   732: iconst_0
        //   733: istore_3
        //   734: iload_3
        //   735: aload #11
        //   737: arraylength
        //   738: if_icmpge -> 983
        //   741: aload #11
        //   743: iload_3
        //   744: aaload
        //   745: invokevirtual length : ()I
        //   748: iconst_3
        //   749: if_icmpne -> 924
        //   752: aload #11
        //   754: iload_3
        //   755: aaload
        //   756: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   759: ifeq -> 924
        //   762: aload #11
        //   764: iload_3
        //   765: aaload
        //   766: iconst_0
        //   767: iconst_1
        //   768: invokevirtual substring : (II)Ljava/lang/String;
        //   771: astore #13
        //   773: aload #11
        //   775: iload_3
        //   776: aaload
        //   777: iconst_1
        //   778: iconst_2
        //   779: invokevirtual substring : (II)Ljava/lang/String;
        //   782: astore #12
        //   784: aload #11
        //   786: iload_3
        //   787: aaload
        //   788: iconst_2
        //   789: iconst_3
        //   790: invokevirtual substring : (II)Ljava/lang/String;
        //   793: astore #10
        //   795: aload #13
        //   797: aload #12
        //   799: invokevirtual indexOf : (Ljava/lang/String;)I
        //   802: iconst_m1
        //   803: if_icmpne -> 917
        //   806: aload #13
        //   808: aload #10
        //   810: invokevirtual indexOf : (Ljava/lang/String;)I
        //   813: iconst_m1
        //   814: if_icmple -> 917
        //   817: aload #11
        //   819: iload_3
        //   820: aaload
        //   821: astore #10
        //   823: new java/lang/StringBuilder
        //   826: dup
        //   827: invokespecial <init> : ()V
        //   830: astore #12
        //   832: aload #12
        //   834: aload #11
        //   836: iload_3
        //   837: aaload
        //   838: iconst_0
        //   839: iconst_1
        //   840: invokevirtual substring : (II)Ljava/lang/String;
        //   843: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   846: pop
        //   847: aload #12
        //   849: aload #11
        //   851: iload_3
        //   852: aaload
        //   853: iconst_1
        //   854: iconst_2
        //   855: invokevirtual substring : (II)Ljava/lang/String;
        //   858: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   861: pop
        //   862: aload #12
        //   864: ldc ','
        //   866: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   869: pop
        //   870: aload #12
        //   872: aload #11
        //   874: iload_3
        //   875: aaload
        //   876: iconst_1
        //   877: iconst_2
        //   878: invokevirtual substring : (II)Ljava/lang/String;
        //   881: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   884: pop
        //   885: aload #12
        //   887: aload #11
        //   889: iload_3
        //   890: aaload
        //   891: iconst_0
        //   892: iconst_1
        //   893: invokevirtual substring : (II)Ljava/lang/String;
        //   896: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   899: pop
        //   900: aload #9
        //   902: aload #10
        //   904: aload #12
        //   906: invokevirtual toString : ()Ljava/lang/String;
        //   909: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   912: astore #10
        //   914: goto -> 921
        //   917: aload #9
        //   919: astore #10
        //   921: goto -> 973
        //   924: aload #11
        //   926: iload_3
        //   927: aaload
        //   928: invokevirtual length : ()I
        //   931: iconst_2
        //   932: if_icmpne -> 949
        //   935: aload #9
        //   937: astore #10
        //   939: aload #11
        //   941: iload_3
        //   942: aaload
        //   943: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   946: ifne -> 921
        //   949: aload #9
        //   951: astore #10
        //   953: aload #11
        //   955: iload_3
        //   956: aaload
        //   957: invokevirtual length : ()I
        //   960: iconst_1
        //   961: if_icmple -> 973
        //   964: iconst_0
        //   965: istore_3
        //   966: ldc ''
        //   968: astore #6
        //   970: goto -> 986
        //   973: iinc #3, 1
        //   976: aload #10
        //   978: astore #9
        //   980: goto -> 734
        //   983: iload #7
        //   985: istore_3
        //   986: aload #9
        //   988: ldc ','
        //   990: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   993: astore #10
        //   995: aload #10
        //   997: arraylength
        //   998: iconst_2
        //   999: if_icmplt -> 1009
        //   1002: aload #10
        //   1004: arraylength
        //   1005: iconst_4
        //   1006: if_icmple -> 1011
        //   1009: iconst_0
        //   1010: istore_3
        //   1011: iload_3
        //   1012: ifne -> 1828
        //   1015: aload_0
        //   1016: astore #9
        //   1018: iconst_0
        //   1019: istore #7
        //   1021: aload #6
        //   1023: astore_0
        //   1024: aload #9
        //   1026: astore #6
        //   1028: aload_1
        //   1029: astore #9
        //   1031: iload #7
        //   1033: aload #9
        //   1035: invokevirtual length : ()I
        //   1038: if_icmpge -> 1825
        //   1041: iconst_1
        //   1042: istore_2
        //   1043: aload #6
        //   1045: aload #9
        //   1047: iload #7
        //   1049: iload #7
        //   1051: iconst_1
        //   1052: iadd
        //   1053: invokevirtual substring : (II)Ljava/lang/String;
        //   1056: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   1059: astore #12
        //   1061: iconst_0
        //   1062: istore_3
        //   1063: iload_3
        //   1064: aload #12
        //   1066: arraylength
        //   1067: if_icmpge -> 1807
        //   1070: aload #12
        //   1072: iload_3
        //   1073: aaload
        //   1074: invokevirtual trim : ()Ljava/lang/String;
        //   1077: invokevirtual length : ()I
        //   1080: ifne -> 1086
        //   1083: goto -> 1807
        //   1086: aload #12
        //   1088: iload_3
        //   1089: aaload
        //   1090: invokevirtual trim : ()Ljava/lang/String;
        //   1093: astore #11
        //   1095: aload #11
        //   1097: ldc ' '
        //   1099: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   1102: arraylength
        //   1103: iconst_1
        //   1104: if_icmpne -> 1445
        //   1107: aload #11
        //   1109: ldc ','
        //   1111: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   1114: astore #13
        //   1116: aload #13
        //   1118: arraylength
        //   1119: iconst_1
        //   1120: if_icmpne -> 1129
        //   1123: ldc ''
        //   1125: astore_0
        //   1126: goto -> 1807
        //   1129: iconst_0
        //   1130: istore #8
        //   1132: aload #11
        //   1134: astore #10
        //   1136: iload #8
        //   1138: aload #13
        //   1140: arraylength
        //   1141: if_icmpge -> 1385
        //   1144: aload #13
        //   1146: iload #8
        //   1148: aaload
        //   1149: invokevirtual length : ()I
        //   1152: iconst_3
        //   1153: if_icmpne -> 1327
        //   1156: aload #13
        //   1158: iload #8
        //   1160: aaload
        //   1161: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   1164: ifeq -> 1327
        //   1167: aload #13
        //   1169: iload #8
        //   1171: aaload
        //   1172: iconst_0
        //   1173: iconst_1
        //   1174: invokevirtual substring : (II)Ljava/lang/String;
        //   1177: aload #13
        //   1179: iload #8
        //   1181: aaload
        //   1182: iconst_1
        //   1183: iconst_2
        //   1184: invokevirtual substring : (II)Ljava/lang/String;
        //   1187: if_acmpeq -> 1321
        //   1190: aload #13
        //   1192: iload #8
        //   1194: aaload
        //   1195: iconst_0
        //   1196: iconst_1
        //   1197: invokevirtual substring : (II)Ljava/lang/String;
        //   1200: aload #13
        //   1202: iload #8
        //   1204: aaload
        //   1205: iconst_2
        //   1206: iconst_3
        //   1207: invokevirtual substring : (II)Ljava/lang/String;
        //   1210: if_acmpne -> 1315
        //   1213: aload #13
        //   1215: iload #8
        //   1217: aaload
        //   1218: astore_1
        //   1219: new java/lang/StringBuilder
        //   1222: dup
        //   1223: invokespecial <init> : ()V
        //   1226: astore #11
        //   1228: aload #11
        //   1230: aload #13
        //   1232: iload #8
        //   1234: aaload
        //   1235: iconst_0
        //   1236: iconst_1
        //   1237: invokevirtual substring : (II)Ljava/lang/String;
        //   1240: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1243: pop
        //   1244: aload #11
        //   1246: aload #13
        //   1248: iload #8
        //   1250: aaload
        //   1251: iconst_1
        //   1252: iconst_2
        //   1253: invokevirtual substring : (II)Ljava/lang/String;
        //   1256: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1259: pop
        //   1260: aload #11
        //   1262: ldc ','
        //   1264: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1267: pop
        //   1268: aload #11
        //   1270: aload #13
        //   1272: iload #8
        //   1274: aaload
        //   1275: iconst_1
        //   1276: iconst_2
        //   1277: invokevirtual substring : (II)Ljava/lang/String;
        //   1280: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1283: pop
        //   1284: aload #11
        //   1286: aload #13
        //   1288: iload #8
        //   1290: aaload
        //   1291: iconst_0
        //   1292: iconst_1
        //   1293: invokevirtual substring : (II)Ljava/lang/String;
        //   1296: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1299: pop
        //   1300: aload #10
        //   1302: aload_1
        //   1303: aload #11
        //   1305: invokevirtual toString : ()Ljava/lang/String;
        //   1308: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1311: astore_1
        //   1312: goto -> 1376
        //   1315: aload #10
        //   1317: astore_1
        //   1318: goto -> 1376
        //   1321: aload #10
        //   1323: astore_1
        //   1324: goto -> 1376
        //   1327: aload #13
        //   1329: iload #8
        //   1331: aaload
        //   1332: invokevirtual length : ()I
        //   1335: iconst_2
        //   1336: if_icmpne -> 1353
        //   1339: aload #10
        //   1341: astore_1
        //   1342: aload #13
        //   1344: iload #8
        //   1346: aaload
        //   1347: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   1350: ifne -> 1376
        //   1353: aload #10
        //   1355: astore_1
        //   1356: aload #13
        //   1358: iload #8
        //   1360: aaload
        //   1361: invokevirtual length : ()I
        //   1364: iconst_1
        //   1365: if_icmple -> 1376
        //   1368: iconst_0
        //   1369: istore_2
        //   1370: ldc ''
        //   1372: astore_0
        //   1373: goto -> 1385
        //   1376: iinc #8, 1
        //   1379: aload_1
        //   1380: astore #10
        //   1382: goto -> 1136
        //   1385: iload_2
        //   1386: iconst_1
        //   1387: if_icmpne -> 1442
        //   1390: aload #10
        //   1392: invokevirtual length : ()I
        //   1395: iconst_4
        //   1396: if_icmple -> 1442
        //   1399: new java/lang/StringBuilder
        //   1402: dup
        //   1403: invokespecial <init> : ()V
        //   1406: astore_1
        //   1407: aload_1
        //   1408: aload_0
        //   1409: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1412: pop
        //   1413: aload_1
        //   1414: aload #10
        //   1416: ldc ' '
        //   1418: ldc ','
        //   1420: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1423: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1426: pop
        //   1427: aload_1
        //   1428: ldc ' '
        //   1430: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1433: pop
        //   1434: aload_1
        //   1435: invokevirtual toString : ()Ljava/lang/String;
        //   1438: astore_0
        //   1439: goto -> 1801
        //   1442: goto -> 1801
        //   1445: aload #9
        //   1447: astore_1
        //   1448: aload #6
        //   1450: astore #10
        //   1452: aload #11
        //   1454: ldc ' '
        //   1456: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   1459: astore #13
        //   1461: aload #13
        //   1463: arraylength
        //   1464: iconst_1
        //   1465: if_icmpne -> 1474
        //   1468: ldc ''
        //   1470: astore_0
        //   1471: goto -> 1807
        //   1474: iconst_0
        //   1475: istore #8
        //   1477: aload #11
        //   1479: astore #9
        //   1481: iload #8
        //   1483: aload #13
        //   1485: arraylength
        //   1486: if_icmpge -> 1725
        //   1489: aload #13
        //   1491: iload #8
        //   1493: aaload
        //   1494: invokevirtual length : ()I
        //   1497: iconst_3
        //   1498: if_icmpne -> 1677
        //   1501: aload #13
        //   1503: iload #8
        //   1505: aaload
        //   1506: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   1509: ifeq -> 1677
        //   1512: aload #13
        //   1514: iload #8
        //   1516: aaload
        //   1517: iconst_0
        //   1518: iconst_1
        //   1519: invokevirtual substring : (II)Ljava/lang/String;
        //   1522: aload #13
        //   1524: iload #8
        //   1526: aaload
        //   1527: iconst_1
        //   1528: iconst_2
        //   1529: invokevirtual substring : (II)Ljava/lang/String;
        //   1532: if_acmpeq -> 1670
        //   1535: aload #13
        //   1537: iload #8
        //   1539: aaload
        //   1540: iconst_0
        //   1541: iconst_1
        //   1542: invokevirtual substring : (II)Ljava/lang/String;
        //   1545: aload #13
        //   1547: iload #8
        //   1549: aaload
        //   1550: iconst_2
        //   1551: iconst_3
        //   1552: invokevirtual substring : (II)Ljava/lang/String;
        //   1555: if_acmpne -> 1663
        //   1558: aload #13
        //   1560: iload #8
        //   1562: aaload
        //   1563: astore #11
        //   1565: new java/lang/StringBuilder
        //   1568: dup
        //   1569: invokespecial <init> : ()V
        //   1572: astore #6
        //   1574: aload #6
        //   1576: aload #13
        //   1578: iload #8
        //   1580: aaload
        //   1581: iconst_0
        //   1582: iconst_1
        //   1583: invokevirtual substring : (II)Ljava/lang/String;
        //   1586: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1589: pop
        //   1590: aload #6
        //   1592: aload #13
        //   1594: iload #8
        //   1596: aaload
        //   1597: iconst_1
        //   1598: iconst_2
        //   1599: invokevirtual substring : (II)Ljava/lang/String;
        //   1602: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1605: pop
        //   1606: aload #6
        //   1608: ldc ','
        //   1610: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1613: pop
        //   1614: aload #6
        //   1616: aload #13
        //   1618: iload #8
        //   1620: aaload
        //   1621: iconst_1
        //   1622: iconst_2
        //   1623: invokevirtual substring : (II)Ljava/lang/String;
        //   1626: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1629: pop
        //   1630: aload #6
        //   1632: aload #13
        //   1634: iload #8
        //   1636: aaload
        //   1637: iconst_0
        //   1638: iconst_1
        //   1639: invokevirtual substring : (II)Ljava/lang/String;
        //   1642: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1645: pop
        //   1646: aload #9
        //   1648: aload #11
        //   1650: aload #6
        //   1652: invokevirtual toString : ()Ljava/lang/String;
        //   1655: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1658: astore #6
        //   1660: goto -> 1707
        //   1663: aload #9
        //   1665: astore #6
        //   1667: goto -> 1707
        //   1670: aload #9
        //   1672: astore #6
        //   1674: goto -> 1707
        //   1677: aload #13
        //   1679: iload #8
        //   1681: aaload
        //   1682: invokevirtual length : ()I
        //   1685: iconst_2
        //   1686: if_icmpne -> 1717
        //   1689: aload #9
        //   1691: astore #6
        //   1693: aload #13
        //   1695: iload #8
        //   1697: aaload
        //   1698: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   1701: ifne -> 1707
        //   1704: goto -> 1717
        //   1707: iinc #8, 1
        //   1710: aload #6
        //   1712: astore #9
        //   1714: goto -> 1481
        //   1717: iconst_0
        //   1718: istore_2
        //   1719: ldc ''
        //   1721: astore_0
        //   1722: goto -> 1725
        //   1725: iload_2
        //   1726: iconst_1
        //   1727: if_icmpne -> 1794
        //   1730: aload #9
        //   1732: invokevirtual length : ()I
        //   1735: iconst_4
        //   1736: if_icmple -> 1794
        //   1739: new java/lang/StringBuilder
        //   1742: dup
        //   1743: invokespecial <init> : ()V
        //   1746: astore #6
        //   1748: aload #6
        //   1750: aload_0
        //   1751: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1754: pop
        //   1755: aload #6
        //   1757: aload #9
        //   1759: ldc ' '
        //   1761: ldc ','
        //   1763: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1766: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1769: pop
        //   1770: aload #6
        //   1772: ldc ' '
        //   1774: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1777: pop
        //   1778: aload #6
        //   1780: invokevirtual toString : ()Ljava/lang/String;
        //   1783: astore_0
        //   1784: aload_1
        //   1785: astore #9
        //   1787: aload #10
        //   1789: astore #6
        //   1791: goto -> 1801
        //   1794: aload #10
        //   1796: astore #6
        //   1798: aload_1
        //   1799: astore #9
        //   1801: iinc #3, 1
        //   1804: goto -> 1063
        //   1807: aload_0
        //   1808: invokevirtual length : ()I
        //   1811: ifle -> 1817
        //   1814: goto -> 1831
        //   1817: iinc #7, 1
        //   1820: iload_3
        //   1821: istore_2
        //   1822: goto -> 1031
        //   1825: goto -> 1831
        //   1828: aload #9
        //   1830: astore_0
        //   1831: aload_0
        //   1832: invokevirtual trim : ()Ljava/lang/String;
        //   1835: areturn
        }

public static ArrayList<String> XulyXienGhep(String paramString, int paramInt) {
        StringBuilder stringBuilder;
        ArrayList<String> arrayList = new ArrayList();
        if (paramInt == 2) {
        String[] arrayOfString = paramString.split(",");
        for (paramInt = 0; paramInt < arrayOfString.length - 1; paramInt++) {
        for (int i = paramInt + 1; i < arrayOfString.length; i++) {
        if (arrayOfString[paramInt] != arrayOfString[i]) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(arrayOfString[paramInt]);
        stringBuilder.append(",");
        stringBuilder.append(arrayOfString[i]);
        arrayList.add(sortXien(stringBuilder.toString()));
        }
        }
        }
        } else if (paramInt == 3) {
        String[] arrayOfString = stringBuilder.split(",");
        for (paramInt = 0; paramInt < arrayOfString.length - 2; paramInt++) {
        for (int i = paramInt + 1; i < arrayOfString.length - 1; i++) {
        for (int j = i + 1; j < arrayOfString.length; j++) {
        if (arrayOfString[paramInt] != arrayOfString[i] && arrayOfString[paramInt] != arrayOfString[j] && arrayOfString[i] != arrayOfString[j]) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(arrayOfString[paramInt]);
        stringBuilder.append(",");
        stringBuilder.append(arrayOfString[i]);
        stringBuilder.append(",");
        stringBuilder.append(arrayOfString[j]);
        arrayList.add(sortXien(stringBuilder.toString()));
        }
        }
        }
        }
        } else if (paramInt == 4) {
        String[] arrayOfString = stringBuilder.split(",");
        for (paramInt = 0; paramInt < arrayOfString.length - 3; paramInt++) {
        for (int i = paramInt + 1; i < arrayOfString.length - 2; i++) {
        for (int j = i + 1; j < arrayOfString.length - 1; j++) {
        for (int k = j + 1; k < arrayOfString.length; k++) {
        if (arrayOfString[paramInt] != arrayOfString[i] && arrayOfString[paramInt] != arrayOfString[j] && arrayOfString[paramInt] != arrayOfString[k] && arrayOfString[i] != arrayOfString[j] && arrayOfString[i] != arrayOfString[k] && arrayOfString[j] != arrayOfString[k]) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(arrayOfString[paramInt]);
        stringBuilder.append(",");
        stringBuilder.append(arrayOfString[i]);
        stringBuilder.append(",");
        stringBuilder.append(arrayOfString[j]);
        stringBuilder.append(",");
        stringBuilder.append(arrayOfString[k]);
        arrayList.add(sortXien(stringBuilder.toString()));
        }
        }
        }
        }
        }
        }
        return arrayList;
        }

public static String Xuly_DauTN(String paramString) {
        paramString = paramString.replaceAll(" ", " ").replaceAll("tong k ", "tong ko ").replaceAll("tong 0 chia", "tong ko chia").replaceAll("botrung", "bor trung").replaceAll(" , "");
        byte b;
        for (b = 0; b < MainActivity.formList.size(); b++)
        paramString = paramString.replaceAll((String)((HashMap)MainActivity.formList.get(b)).get("datas"), (String)((HashMap)MainActivity.formList.get(b)).get("type")).replaceAll("  ", " ");
        for (b = 0; b < MainActivity.formArray.size(); b++)
        paramString = paramString.replaceAll((String)((HashMap)MainActivity.formArray.get(b)).get("str"), (String)((HashMap)MainActivity.formArray.get(b)).get("repl_str")).replaceAll("  ", " ");
        for (b = 1; b < 10; b++)
        paramString = paramString.replaceAll("  ", " ");
        String str = paramString.replaceAll("xie n", "xi").replaceAll("le ch", "lech").replace("\n", " ").replace("\\.", ",").replaceAll(";,", ";").replaceAll("; ,", ";").replaceAll("; lo", "lo").replaceAll("va ", ";").replaceAll(";lo", "lo").replaceAll("; de", "de").replaceAll(";de", "de").replaceAll("; xi", "xi").replaceAll("dedau", "de dau").replaceAll("dedit", "de dit").replaceAll("decham", "de cham").replaceAll("dedinh", "de cham").replaceAll(";xn", "xn").replaceAll(";xi", "xi").replaceAll("; bc", "bc").replaceAll(";bc", "bc");
        str.replaceAll("bc", " bc ").replace("dan", " dan ").replace("cua", " trung ").replace("chia", " chia ").replace("dau", " dau ").replace("dit", " dit ").replace("tong", " tong ").replace("cham", " cham ").replace("boj", " boj ").replace("bor", " bor ").replace("dea", " dea ").replaceAll("deb", " deb ").replaceAll("dec", " dec ").replaceAll("ded", " ded ").replace("lo ", " lo ").replaceAll("xg", " xg ").replaceAll("xn", " xn ");
        paramString = str;
        if (str.indexOf("dea") == -1) {
        paramString = str;
        if (str.indexOf("deb") == -1) {
        paramString = str;
        if (str.indexOf("dec") == -1) {
        paramString = str;
        if (str.indexOf("ded") == -1) {
        paramString = str;
        if (str.indexOf("det") == -1) {
        paramString = str;
        if (str.indexOf("de") > -1)
        paramString = str.replaceAll("de", "deb ");
        }
        }
        }
        }
        }
        return paramString;
        }

public static final String convertKhongDau(String paramString) {
        // Byte code:
        //   0: new java/lang/StringBuilder
        //   3: dup
        //   4: invokespecial <init> : ()V
        //   7: astore_1
        //   8: aload_1
        //   9: aload_0
        //   10: invokevirtual toLowerCase : ()Ljava/lang/String;
        //   13: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   16: pop
        //   17: aload_1
        //   18: ldc ' '
        //   20: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   23: pop
        //   24: aload_1
        //   25: invokevirtual toString : ()Ljava/lang/String;
        //   28: ldc_w 'b
        //   31: ldc_w 'bor'
        //   34: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   37: ldc_w 'b
        //   40: ldc_w 'boj'
        //   43: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   46: ldc '\.'
        //   48: ldc ','
        //   50: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   53: ldc_w '
        //   56: ldc ''
        //   58: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   61: ldc_w '
        //   64: ldc ''
        //   66: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   69: ldc_w '
        //   72: ldc ''
        //   74: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   77: ldc_w '
        //   80: ldc ''
        //   82: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   85: ldc_w '
        //   88: ldc ''
        //   90: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   93: ldc_w '\+'
        //   96: ldc_w '!'
        //   99: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   102: astore_0
        //   103: iconst_0
        //   104: istore_2
        //   105: iload_2
        //   106: ldc_w 'aaaaaaaaaaaaaaaaaeeeeeeeeeeeooooooooooooooooouuuuuuuuuuuiiiiiyyyyydx'
        //   109: invokevirtual length : ()I
        //   112: if_icmpge -> 140
        //   115: aload_0
        //   116: ldc_w '
        //   119: iload_2
        //   120: invokevirtual charAt : (I)C
        //   123: ldc_w 'aaaaaaaaaaaaaaaaaeeeeeeeeeeeooooooooooooooooouuuuuuuuuuuiiiiiyyyyydx'
        //   126: iload_2
        //   127: invokevirtual charAt : (I)C
        //   130: invokevirtual replace : (CC)Ljava/lang/String;
        //   133: astore_0
        //   134: iinc #2, 1
        //   137: goto -> 105
        //   140: aload_0
        //   141: ldc_w '\\n d'
        //   144: ldc_w '\\nd'
        //   147: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   150: astore_0
        //   151: aload_0
        //   152: ldc_w '\\n
        //   155: invokevirtual indexOf : (Ljava/lang/String;)I
        //   158: iconst_m1
        //   159: if_icmpgt -> 175
        //   162: aload_0
        //   163: astore_1
        //   164: aload_0
        //   165: ldc_w '\\nd'
        //   168: invokevirtual indexOf : (Ljava/lang/String;)I
        //   171: iconst_m1
        //   172: if_icmple -> 323
        //   175: iconst_m1
        //   176: istore_3
        //   177: aload_0
        //   178: ldc_w '\\nd'
        //   181: iload_3
        //   182: iconst_1
        //   183: iadd
        //   184: invokevirtual indexOf : (Ljava/lang/String;I)I
        //   187: istore_3
        //   188: iload_3
        //   189: istore_2
        //   190: aload_0
        //   191: astore_1
        //   192: iload_3
        //   193: iconst_m1
        //   194: if_icmpeq -> 323
        //   197: iload_2
        //   198: istore_3
        //   199: iload_2
        //   200: aload_0
        //   201: invokevirtual length : ()I
        //   204: iconst_1
        //   205: isub
        //   206: if_icmpge -> 177
        //   209: aload_0
        //   210: iload_2
        //   211: iconst_2
        //   212: iadd
        //   213: iload_2
        //   214: iconst_3
        //   215: iadd
        //   216: invokevirtual substring : (II)Ljava/lang/String;
        //   219: astore #4
        //   221: aload #4
        //   223: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   226: ifne -> 253
        //   229: aload #4
        //   231: ldc ' '
        //   233: invokevirtual indexOf : (Ljava/lang/String;)I
        //   236: iconst_m1
        //   237: if_icmpgt -> 253
        //   240: aload_0
        //   241: astore_1
        //   242: aload #4
        //   244: ldc ':'
        //   246: invokevirtual indexOf : (Ljava/lang/String;)I
        //   249: iconst_m1
        //   250: if_icmple -> 316
        //   253: new java/lang/StringBuilder
        //   256: dup
        //   257: invokespecial <init> : ()V
        //   260: astore_1
        //   261: aload_1
        //   262: ldc_w '\\nd'
        //   265: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   268: pop
        //   269: aload_1
        //   270: aload #4
        //   272: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   275: pop
        //   276: aload_1
        //   277: invokevirtual toString : ()Ljava/lang/String;
        //   280: astore #5
        //   282: new java/lang/StringBuilder
        //   285: dup
        //   286: invokespecial <init> : ()V
        //   289: astore_1
        //   290: aload_1
        //   291: ldc_w '!d'
        //   294: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   297: pop
        //   298: aload_1
        //   299: aload #4
        //   301: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   304: pop
        //   305: aload_0
        //   306: aload #5
        //   308: aload_1
        //   309: invokevirtual toString : ()Ljava/lang/String;
        //   312: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   315: astore_1
        //   316: iload_2
        //   317: istore_3
        //   318: aload_1
        //   319: astore_0
        //   320: goto -> 177
        //   323: iconst_1
        //   324: istore_2
        //   325: iload_2
        //   326: bipush #10
        //   328: if_icmpge -> 347
        //   331: aload_1
        //   332: ldc_w '  '
        //   335: ldc ' '
        //   337: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   340: astore_1
        //   341: iinc #2, 1
        //   344: goto -> 325
        //   347: aload_1
        //   348: ldc_w '\s+'
        //   351: ldc ' '
        //   353: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   356: ldc_w 'd e'
        //   359: ldc_w 'de'
        //   362: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   365: ldc_w 'd au'
        //   368: ldc_w 'dau'
        //   371: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   374: ldc_w 'd it'
        //   377: ldc_w 'dit'
        //   380: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   383: ldc_w 'ja'
        //   386: ldc_w 'ia'
        //   389: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   392: ldc_w 'dich'
        //   395: ldc_w 'dit'
        //   398: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   401: ldc_w 'je'
        //   404: ldc_w 'ie'
        //   407: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   410: ldc_w 'nde'
        //   413: ldc_w 'n de'
        //   416: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   419: ldc_w 'nlo'
        //   422: ldc_w 'n lo'
        //   425: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   428: ldc_w 'nxi'
        //   431: ldc_w 'n xi'
        //   434: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   437: ldc_w 'nda'
        //   440: ldc_w 'n da'
        //   443: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   446: ldc_w 'ndi'
        //   449: ldc_w 'n di'
        //   452: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   455: ldc_w 'nto'
        //   458: ldc_w 'n to'
        //   461: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   464: ldc_w 'x i'
        //   467: ldc_w 'xi'
        //   470: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   473: ldc_w 'x j'
        //   476: ldc_w 'xi'
        //   479: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   482: ldc_w 'xj'
        //   485: ldc_w 'xi'
        //   488: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   491: ldc_w 'x 3 bc'
        //   494: ldc_w 'x 3, bc'
        //   497: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   500: areturn
        }

public static String fixTinNhan(String paramString) {
        // Byte code:
        //   0: new java/lang/StringBuilder
        //   3: dup
        //   4: invokespecial <init> : ()V
        //   7: astore_1
        //   8: aload_1
        //   9: aload_0
        //   10: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13: pop
        //   14: aload_1
        //   15: ldc ' '
        //   17: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   20: pop
        //   21: aload_1
        //   22: invokevirtual toString : ()Ljava/lang/String;
        //   25: astore_1
        //   26: aload_1
        //   27: ldc ' '
        //   29: ldc ','
        //   31: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   34: ldc '\.'
        //   36: ldc ','
        //   38: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   41: ldc ':'
        //   43: ldc ','
        //   45: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   48: ldc_w ';'
        //   51: ldc ','
        //   53: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   56: ldc_w '/'
        //   59: ldc ','
        //   61: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   64: ldc ','
        //   66: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   69: pop
        //   70: aload_1
        //   71: astore_0
        //   72: aload_1
        //   73: ldc_w 'Khhi
        //   76: invokevirtual indexOf : (Ljava/lang/String;)I
        //   79: iconst_m1
        //   80: if_icmpne -> 1245
        //   83: iconst_0
        //   84: istore_2
        //   85: aload_1
        //   86: astore_0
        //   87: iload_2
        //   88: aload_0
        //   89: invokevirtual length : ()I
        //   92: if_icmpge -> 168
        //   95: aload_0
        //   96: iload_2
        //   97: invokevirtual charAt : (I)C
        //   100: bipush #127
        //   102: if_icmpgt -> 117
        //   105: aload_0
        //   106: astore_1
        //   107: aload_0
        //   108: iload_2
        //   109: invokevirtual charAt : (I)C
        //   112: bipush #31
        //   114: if_icmpge -> 160
        //   117: new java/lang/StringBuilder
        //   120: dup
        //   121: invokespecial <init> : ()V
        //   124: astore_1
        //   125: aload_1
        //   126: aload_0
        //   127: iconst_0
        //   128: iload_2
        //   129: invokevirtual substring : (II)Ljava/lang/String;
        //   132: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: pop
        //   136: aload_1
        //   137: ldc ' '
        //   139: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: pop
        //   143: aload_1
        //   144: aload_0
        //   145: iload_2
        //   146: iconst_1
        //   147: iadd
        //   148: invokevirtual substring : (I)Ljava/lang/String;
        //   151: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   154: pop
        //   155: aload_1
        //   156: invokevirtual toString : ()Ljava/lang/String;
        //   159: astore_1
        //   160: iinc #2, 1
        //   163: aload_1
        //   164: astore_0
        //   165: goto -> 87
        //   168: aload_0
        //   169: invokevirtual trim : ()Ljava/lang/String;
        //   172: astore_1
        //   173: aload_1
        //   174: astore_0
        //   175: aload_1
        //   176: aload_1
        //   177: invokevirtual length : ()I
        //   180: iconst_1
        //   181: isub
        //   182: invokevirtual charAt : (I)C
        //   185: bipush #120
        //   187: if_icmpne -> 202
        //   190: aload_1
        //   191: iconst_0
        //   192: aload_1
        //   193: invokevirtual length : ()I
        //   196: iconst_1
        //   197: isub
        //   198: invokevirtual substring : (II)Ljava/lang/String;
        //   201: astore_0
        //   202: iconst_m1
        //   203: istore_3
        //   204: aload_0
        //   205: ldc_w 'x '
        //   208: iload_3
        //   209: iconst_1
        //   210: iadd
        //   211: invokevirtual indexOf : (Ljava/lang/String;I)I
        //   214: istore_2
        //   215: iload_2
        //   216: istore #4
        //   218: iload_2
        //   219: iconst_m1
        //   220: if_icmpeq -> 603
        //   223: iload #4
        //   225: iconst_2
        //   226: iadd
        //   227: istore_2
        //   228: iload_2
        //   229: aload_0
        //   230: invokevirtual length : ()I
        //   233: if_icmpge -> 259
        //   236: aload_0
        //   237: iload_2
        //   238: iload_2
        //   239: iconst_1
        //   240: iadd
        //   241: invokevirtual substring : (II)Ljava/lang/String;
        //   244: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   247: ifeq -> 253
        //   250: goto -> 259
        //   253: iinc #2, 1
        //   256: goto -> 228
        //   259: iload_2
        //   260: istore #5
        //   262: iload #5
        //   264: aload_0
        //   265: invokevirtual length : ()I
        //   268: if_icmpge -> 316
        //   271: aload_0
        //   272: iload #5
        //   274: iload #5
        //   276: iconst_1
        //   277: iadd
        //   278: invokevirtual substring : (II)Ljava/lang/String;
        //   281: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   284: ifne -> 310
        //   287: ldc_w ' tr'
        //   290: aload_0
        //   291: iload #5
        //   293: iload #5
        //   295: iconst_1
        //   296: iadd
        //   297: invokevirtual substring : (II)Ljava/lang/String;
        //   300: invokevirtual indexOf : (Ljava/lang/String;)I
        //   303: iconst_m1
        //   304: if_icmpne -> 310
        //   307: goto -> 316
        //   310: iinc #5, 1
        //   313: goto -> 262
        //   316: aload_0
        //   317: iload #4
        //   319: iconst_1
        //   320: iadd
        //   321: iload #5
        //   323: invokevirtual substring : (II)Ljava/lang/String;
        //   326: invokevirtual trim : ()Ljava/lang/String;
        //   329: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   332: ifeq -> 404
        //   335: aload_0
        //   336: iload #4
        //   338: iconst_1
        //   339: iadd
        //   340: iload #5
        //   342: invokevirtual substring : (II)Ljava/lang/String;
        //   345: invokevirtual trim : ()Ljava/lang/String;
        //   348: invokevirtual length : ()I
        //   351: iconst_1
        //   352: if_icmple -> 404
        //   355: new java/lang/StringBuilder
        //   358: dup
        //   359: invokespecial <init> : ()V
        //   362: astore_1
        //   363: aload_1
        //   364: aload_0
        //   365: iconst_0
        //   366: iload #5
        //   368: invokevirtual substring : (II)Ljava/lang/String;
        //   371: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   374: pop
        //   375: aload_1
        //   376: ldc ' '
        //   378: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   381: pop
        //   382: aload_1
        //   383: aload_0
        //   384: iload #5
        //   386: invokevirtual substring : (I)Ljava/lang/String;
        //   389: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   392: pop
        //   393: aload_1
        //   394: invokevirtual toString : ()Ljava/lang/String;
        //   397: astore_0
        //   398: iload #4
        //   400: istore_3
        //   401: goto -> 204
        //   404: iload #5
        //   406: iload_2
        //   407: isub
        //   408: iconst_1
        //   409: if_icmple -> 524
        //   412: aload_0
        //   413: iload #4
        //   415: invokevirtual substring : (I)Ljava/lang/String;
        //   418: ldc_w 'to'
        //   421: invokevirtual indexOf : (Ljava/lang/String;)I
        //   424: iload #5
        //   426: iload #4
        //   428: isub
        //   429: iconst_1
        //   430: isub
        //   431: if_icmpeq -> 524
        //   434: aload_0
        //   435: iload #4
        //   437: invokevirtual substring : (I)Ljava/lang/String;
        //   440: ldc_w 'tin'
        //   443: invokevirtual indexOf : (Ljava/lang/String;)I
        //   446: iload #5
        //   448: iload #4
        //   450: isub
        //   451: iconst_1
        //   452: isub
        //   453: if_icmpeq -> 524
        //   456: aload_0
        //   457: iload #4
        //   459: invokevirtual substring : (I)Ljava/lang/String;
        //   462: ldc ','
        //   464: invokevirtual indexOf : (Ljava/lang/String;)I
        //   467: iload #5
        //   469: iload #4
        //   471: isub
        //   472: if_icmpeq -> 524
        //   475: new java/lang/StringBuilder
        //   478: dup
        //   479: invokespecial <init> : ()V
        //   482: astore_1
        //   483: aload_1
        //   484: aload_0
        //   485: iconst_0
        //   486: iload #5
        //   488: invokevirtual substring : (II)Ljava/lang/String;
        //   491: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   494: pop
        //   495: aload_1
        //   496: ldc ' '
        //   498: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   501: pop
        //   502: aload_1
        //   503: aload_0
        //   504: iload #5
        //   506: invokevirtual substring : (I)Ljava/lang/String;
        //   509: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   512: pop
        //   513: aload_1
        //   514: invokevirtual toString : ()Ljava/lang/String;
        //   517: astore_0
        //   518: iload #4
        //   520: istore_3
        //   521: goto -> 204
        //   524: iload #4
        //   526: istore_3
        //   527: iload #5
        //   529: iload_2
        //   530: isub
        //   531: iconst_1
        //   532: if_icmpne -> 204
        //   535: iload #4
        //   537: istore_3
        //   538: aload_0
        //   539: iload #4
        //   541: invokevirtual substring : (I)Ljava/lang/String;
        //   544: ldc_w 'tr'
        //   547: invokevirtual indexOf : (Ljava/lang/String;)I
        //   550: iconst_m1
        //   551: if_icmpne -> 204
        //   554: new java/lang/StringBuilder
        //   557: dup
        //   558: invokespecial <init> : ()V
        //   561: astore_1
        //   562: aload_1
        //   563: aload_0
        //   564: iconst_0
        //   565: iload #5
        //   567: invokevirtual substring : (II)Ljava/lang/String;
        //   570: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   573: pop
        //   574: aload_1
        //   575: ldc ' '
        //   577: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   580: pop
        //   581: aload_1
        //   582: aload_0
        //   583: iload #5
        //   585: invokevirtual substring : (I)Ljava/lang/String;
        //   588: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   591: pop
        //   592: aload_1
        //   593: invokevirtual toString : ()Ljava/lang/String;
        //   596: astore_0
        //   597: iload #4
        //   599: istore_3
        //   600: goto -> 204
        //   603: new java/lang/StringBuilder
        //   606: dup
        //   607: invokespecial <init> : ()V
        //   610: astore_1
        //   611: aload_1
        //   612: aload_0
        //   613: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   616: pop
        //   617: aload_1
        //   618: ldc ' '
        //   620: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   623: pop
        //   624: aload_1
        //   625: invokevirtual toString : ()Ljava/lang/String;
        //   628: astore_1
        //   629: aload_1
        //   630: invokevirtual length : ()I
        //   633: istore_2
        //   634: iload_2
        //   635: aload_1
        //   636: invokevirtual length : ()I
        //   639: bipush #9
        //   641: isub
        //   642: if_icmple -> 842
        //   645: aload_1
        //   646: iload_2
        //   647: invokevirtual substring : (I)Ljava/lang/String;
        //   650: astore #6
        //   652: aload_1
        //   653: astore_0
        //   654: aload #6
        //   656: invokevirtual trim : ()Ljava/lang/String;
        //   659: ldc_w 't '
        //   662: invokevirtual indexOf : (Ljava/lang/String;)I
        //   665: iconst_m1
        //   666: if_icmple -> 834
        //   669: ldc ''
        //   671: astore_0
        //   672: iload_2
        //   673: istore #4
        //   675: aload_0
        //   676: astore #7
        //   678: iload #4
        //   680: ifle -> 720
        //   683: aload_1
        //   684: iload #4
        //   686: iload_2
        //   687: invokevirtual substring : (II)Ljava/lang/String;
        //   690: astore_0
        //   691: aload_0
        //   692: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   695: ifne -> 714
        //   698: aload_0
        //   699: invokevirtual trim : ()Ljava/lang/String;
        //   702: invokevirtual length : ()I
        //   705: ifle -> 714
        //   708: aload_0
        //   709: astore #7
        //   711: goto -> 720
        //   714: iinc #4, -1
        //   717: goto -> 675
        //   720: aload #7
        //   722: invokevirtual trim : ()Ljava/lang/String;
        //   725: invokevirtual length : ()I
        //   728: iconst_1
        //   729: if_icmpgt -> 742
        //   732: aload_1
        //   733: astore_0
        //   734: aload #7
        //   736: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   739: ifne -> 834
        //   742: aload #6
        //   744: ldc_w 't'
        //   747: ldc ''
        //   749: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   752: ldc ' '
        //   754: ldc ''
        //   756: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   759: ldc ','
        //   761: ldc ''
        //   763: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   766: astore_0
        //   767: aload_0
        //   768: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   771: ifeq -> 793
        //   774: aload_0
        //   775: invokestatic parseInt : (Ljava/lang/String;)I
        //   778: bipush #99
        //   780: if_icmpge -> 793
        //   783: aload_1
        //   784: iconst_0
        //   785: iload_2
        //   786: invokevirtual substring : (II)Ljava/lang/String;
        //   789: astore_0
        //   790: goto -> 834
        //   793: aload_0
        //   794: invokevirtual length : ()I
        //   797: ifne -> 842
        //   800: new java/lang/StringBuilder
        //   803: dup
        //   804: invokespecial <init> : ()V
        //   807: astore_0
        //   808: aload_0
        //   809: aload_1
        //   810: iconst_0
        //   811: iload_2
        //   812: iconst_1
        //   813: iadd
        //   814: invokevirtual substring : (II)Ljava/lang/String;
        //   817: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   820: pop
        //   821: aload_0
        //   822: ldc_w '?'
        //   825: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   828: pop
        //   829: aload_0
        //   830: invokevirtual toString : ()Ljava/lang/String;
        //   833: astore_0
        //   834: iinc #2, -1
        //   837: aload_0
        //   838: astore_1
        //   839: goto -> 634
        //   842: aload_1
        //   843: invokevirtual trim : ()Ljava/lang/String;
        //   846: astore_1
        //   847: aload_1
        //   848: astore_0
        //   849: aload_1
        //   850: aload_1
        //   851: invokevirtual length : ()I
        //   854: iconst_1
        //   855: isub
        //   856: invokevirtual substring : (I)Ljava/lang/String;
        //   859: ldc_w '@'
        //   862: invokevirtual indexOf : (Ljava/lang/String;)I
        //   865: iconst_m1
        //   866: if_icmple -> 948
        //   869: aload_1
        //   870: invokevirtual length : ()I
        //   873: iconst_2
        //   874: isub
        //   875: istore_2
        //   876: iload_2
        //   877: ifle -> 907
        //   880: aload_1
        //   881: iload_2
        //   882: iload_2
        //   883: iconst_1
        //   884: iadd
        //   885: invokevirtual substring : (II)Ljava/lang/String;
        //   888: ldc_w '@'
        //   891: invokevirtual indexOf : (Ljava/lang/String;)I
        //   894: iconst_m1
        //   895: if_icmple -> 901
        //   898: goto -> 907
        //   901: iinc #2, -1
        //   904: goto -> 876
        //   907: aload_1
        //   908: astore_0
        //   909: iload_2
        //   910: aload_1
        //   911: invokevirtual length : ()I
        //   914: bipush #13
        //   916: isub
        //   917: if_icmple -> 948
        //   920: aload_1
        //   921: astore_0
        //   922: aload_1
        //   923: iload_2
        //   924: invokevirtual substring : (I)Ljava/lang/String;
        //   927: ldc_w '@'
        //   930: ldc ''
        //   932: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   935: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   938: ifeq -> 948
        //   941: aload_1
        //   942: iconst_0
        //   943: iload_2
        //   944: invokevirtual substring : (II)Ljava/lang/String;
        //   947: astore_0
        //   948: goto -> 954
        //   951: astore_0
        //   952: aload_1
        //   953: astore_0
        //   954: aload_0
        //   955: astore #7
        //   957: aload_0
        //   958: astore_1
        //   959: getstatic tamhoang/ldpro4/MainActivity.jSon_Setting : Lorg/json/JSONObject;
        //   962: ldc_w 'baotinthieu'
        //   965: invokevirtual getInt : (Ljava/lang/String;)I
        //   968: ifne -> 1064
        //   971: aload_0
        //   972: astore_1
        //   973: aload_0
        //   974: invokevirtual trim : ()Ljava/lang/String;
        //   977: astore_0
        //   978: bipush #6
        //   980: istore_2
        //   981: aload_0
        //   982: astore #7
        //   984: iload_2
        //   985: ifle -> 1064
        //   988: aload_0
        //   989: astore_1
        //   990: aload_0
        //   991: iconst_0
        //   992: iload_2
        //   993: invokevirtual substring : (II)Ljava/lang/String;
        //   996: astore #6
        //   998: aload_0
        //   999: astore #7
        //   1001: aload_0
        //   1002: astore_1
        //   1003: aload #6
        //   1005: invokevirtual trim : ()Ljava/lang/String;
        //   1008: ldc_w 't'
        //   1011: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1014: iconst_m1
        //   1015: if_icmple -> 1055
        //   1018: aload_0
        //   1019: astore #7
        //   1021: aload_0
        //   1022: astore_1
        //   1023: aload #6
        //   1025: ldc_w 't'
        //   1028: ldc ''
        //   1030: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1033: ldc ','
        //   1035: ldc ''
        //   1037: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1040: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   1043: ifeq -> 1055
        //   1046: aload_0
        //   1047: astore_1
        //   1048: aload_0
        //   1049: iload_2
        //   1050: invokevirtual substring : (I)Ljava/lang/String;
        //   1053: astore #7
        //   1055: iinc #2, -1
        //   1058: aload #7
        //   1060: astore_0
        //   1061: goto -> 981
        //   1064: aload #7
        //   1066: astore_1
        //   1067: goto -> 1075
        //   1070: astore_0
        //   1071: aload_0
        //   1072: invokevirtual printStackTrace : ()V
        //   1075: new java/lang/StringBuilder
        //   1078: dup
        //   1079: invokespecial <init> : ()V
        //   1082: astore_0
        //   1083: aload_0
        //   1084: aload_1
        //   1085: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1088: pop
        //   1089: aload_0
        //   1090: ldc ' '
        //   1092: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1095: pop
        //   1096: aload_0
        //   1097: invokevirtual toString : ()Ljava/lang/String;
        //   1100: astore_0
        //   1101: iconst_m1
        //   1102: istore #4
        //   1104: aload_0
        //   1105: ldc_w 'tin'
        //   1108: iload #4
        //   1110: iconst_1
        //   1111: iadd
        //   1112: invokevirtual indexOf : (Ljava/lang/String;I)I
        //   1115: istore #4
        //   1117: iload #4
        //   1119: istore_2
        //   1120: iload #4
        //   1122: iconst_m1
        //   1123: if_icmpeq -> 1213
        //   1126: iload_2
        //   1127: iconst_5
        //   1128: iadd
        //   1129: istore_3
        //   1130: iload_3
        //   1131: iload_2
        //   1132: bipush #10
        //   1134: iadd
        //   1135: if_icmpge -> 1161
        //   1138: aload_0
        //   1139: iload_2
        //   1140: iconst_4
        //   1141: iadd
        //   1142: iload_3
        //   1143: invokevirtual substring : (II)Ljava/lang/String;
        //   1146: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   1149: ifne -> 1155
        //   1152: goto -> 1161
        //   1155: iinc #3, 1
        //   1158: goto -> 1130
        //   1161: iload_2
        //   1162: istore #4
        //   1164: iload_3
        //   1165: iload_2
        //   1166: isub
        //   1167: iconst_5
        //   1168: if_icmple -> 1104
        //   1171: new java/lang/StringBuilder
        //   1174: astore_1
        //   1175: aload_1
        //   1176: invokespecial <init> : ()V
        //   1179: aload_1
        //   1180: aload_0
        //   1181: iconst_0
        //   1182: iload_2
        //   1183: invokevirtual substring : (II)Ljava/lang/String;
        //   1186: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1189: pop
        //   1190: aload_1
        //   1191: aload_0
        //   1192: iload_3
        //   1193: invokevirtual substring : (I)Ljava/lang/String;
        //   1196: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1199: pop
        //   1200: aload_1
        //   1201: invokevirtual toString : ()Ljava/lang/String;
        //   1204: astore_1
        //   1205: aload_1
        //   1206: astore_0
        //   1207: iload_2
        //   1208: istore #4
        //   1210: goto -> 1104
        //   1213: goto -> 1217
        //   1216: astore_1
        //   1217: aload_0
        //   1218: invokevirtual trim : ()Ljava/lang/String;
        //   1221: astore_1
        //   1222: aload_1
        //   1223: astore_0
        //   1224: aload_1
        //   1225: iconst_0
        //   1226: iconst_1
        //   1227: invokevirtual substring : (II)Ljava/lang/String;
        //   1230: ldc ','
        //   1232: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1235: iconst_m1
        //   1236: if_icmple -> 1245
        //   1239: aload_1
        //   1240: iconst_1
        //   1241: invokevirtual substring : (I)Ljava/lang/String;
        //   1244: astore_0
        //   1245: aload_0
        //   1246: areturn
        // Exception table:
        //   from	to	target	type
        //   849	876	951	java/lang/Exception
        //   880	898	951	java/lang/Exception
        //   909	920	951	java/lang/Exception
        //   922	948	951	java/lang/Exception
        //   959	971	1070	org/json/JSONException
        //   973	978	1070	org/json/JSONException
        //   990	998	1070	org/json/JSONException
        //   1003	1018	1070	org/json/JSONException
        //   1023	1046	1070	org/json/JSONException
        //   1048	1055	1070	org/json/JSONException
        //   1104	1117	1216	java/lang/Exception
        //   1138	1152	1216	java/lang/Exception
        //   1171	1205	1216	java/lang/Exception
        }

public static String fixTinNhan1(String paramString) {
        paramString = paramString.replaceAll(" ,", ", ");
        int i = 0;
        int j = paramString.length();
        while (i < j - 1) {
        int k = i + 1;
        int m = paramString.length() - 1;
        if (Character.isLetter(paramString.charAt(k)) && !Character.isLetter(paramString.charAt(k + 1))) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString.substring(0, k + 1));
        stringBuilder1.append(" ");
        stringBuilder1.append(paramString.substring(k + 1));
        paramString = stringBuilder1.toString();
        i = k + 1;
        j = m;
        continue;
        }
        i = k;
        j = m;
        if (!Character.isLetter(paramString.charAt(k))) {
        i = k;
        j = m;
        if (Character.isLetter(paramString.charAt(k + 1))) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString.substring(0, k + 1));
        stringBuilder1.append(" ");
        stringBuilder1.append(paramString.substring(k + 1));
        paramString = stringBuilder1.toString();
        i = k + 1;
        j = m;
        }
        }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(" ");
        paramString = stringBuilder.toString();
        for (i = 1; i < 10; i++)
        paramString = paramString.replaceAll("  ", " ");
        String str = paramString;
        if (paramString.indexOf("(") > -1) {
        str = paramString;
        if (paramString.indexOf(")") > -1) {
        int k = -1;
        while (true) {
        j = paramString.indexOf("(", k + 1);
        i = j;
        str = paramString;
        if (j != -1) {
        for (j = i; j < paramString.length() && paramString.substring(j, j + 1).indexOf(")") <= -1; j++);
        k = i;
        if (isNumeric(paramString.substring(i + 1, j).replaceAll(" ", ""))) {
        k = i;
        while (k < j) {
        str = paramString;
        if (isNumeric(paramString.substring(k - 1, k))) {
        str = paramString;
        if (paramString.substring(k, k + 1).indexOf(" ") > -1) {
        str = paramString;
        if (isNumeric(paramString.substring(k + 1, k + 2))) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString.substring(0, k));
        stringBuilder1.append(",");
        stringBuilder1.append(paramString.substring(k + 1));
        str = stringBuilder1.toString();
        }
        }
        }
        k++;
        paramString = str;
        }
        k = i;
        }
        continue;
        }
        break;
        }
        }
        }
        return str;
        }

public static boolean isNumeric(String paramString) {
        if (paramString == null || paramString.length() == 0)
        return false;
        for (char c : paramString.toCharArray()) {
        if (c < '0' || c > '9')
        return false;
        }
        return Boolean.valueOf(true).booleanValue();
        }

private static Date parseDate(String paramString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.US);
        try {
        return simpleDateFormat.parse(paramString);
        } catch (ParseException parseException) {
        return new Date(0L);
        }
        }

public static String sortXien(String paramString) {
        ArrayList<String> arrayList = new ArrayList();
        String str2 = "";
        String[] arrayOfString = paramString.split(",");
        byte b;
        for (b = 0; b < arrayOfString.length; b++)
        arrayList.add(arrayOfString[b]);
        Collections.sort(arrayList);
        b = 0;
        String str1 = str2;
        while (b < arrayList.size()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str1);
        stringBuilder.append(arrayList.get(b));
        stringBuilder.append(",");
        str1 = stringBuilder.toString();
        b++;
        }
        return str1;
        }

public static Boolean xuLymem(String paramString) {
        Boolean bool1;
        Boolean bool3;
        Boolean bool2 = Boolean.valueOf(true);
        if (paramString.length() == 0)
        bool2 = Boolean.valueOf(false);
        String str = paramString.replaceAll(",", "").trim();
        byte b = 0;
        while (true) {
        bool1 = bool2;
        if (b < 10) {
        Boolean bool;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b);
        stringBuilder.append("");
        String str1 = str.replaceAll(stringBuilder.toString(), "");
        if (str.length() - str1.length() > 1) {
        bool = Boolean.valueOf(false);
        break;
        }
        bool3 = bool;
        b++;
        continue;
        }
        break;
        }
        if (bool3.length() > 0)
        bool1 = Boolean.valueOf(false);
        return bool1;
        }
        }
