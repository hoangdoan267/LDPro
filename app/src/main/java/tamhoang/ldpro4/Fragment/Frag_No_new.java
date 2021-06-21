package tamhoang.ldpro4.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Activity.Activity_khach;
import tamhoang.ldpro4.Congthuc.Congthuc;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.NotificationReader;
import tamhoang.ldpro4.data.Database;

public class Frag_No_new extends Fragment {
    boolean Running = true;

    TextView bc_Chuyen;

    TextView bc_ChuyenAn;

    TextView bc_Nhan;

    TextView bc_NhanAn;

    TextView bca_Chuyen;

    TextView bca_ChuyenAn;

    TextView bca_Nhan;

    TextView bca_NhanAn;

    Button btn_nt;

    JSONObject caidat_tg;

    int currentIndex;

    Database db;

    TextView dea_Chuyen;

    TextView dea_ChuyenAn;

    TextView dea_Nhan;

    TextView dea_NhanAn;

    TextView deb_Chuyen;

    TextView deb_ChuyenAn;

    TextView deb_Nhan;

    TextView deb_NhanAn;

    TextView dec_Chuyen;

    TextView dec_ChuyenAn;

    TextView dec_Nhan;

    TextView dec_NhanAn;

    TextView ded_Chuyen;

    TextView ded_ChuyenAn;

    TextView ded_Nhan;

    TextView ded_NhanAn;

    TextView det_Chuyen;

    TextView det_ChuyenAn;

    TextView det_Nhan;

    TextView det_NhanAn;

    Handler handler;

    boolean isSuccess = false;

    JSONObject json;

    List<JSONObject> jsonKhachHang;

    LinearLayout li_bca;

    LinearLayout li_dea;

    LinearLayout li_dec;

    LinearLayout li_ded;

    LinearLayout li_det;

    LinearLayout li_loa;

    LinearLayout li_xia2;

    LinearLayout li_xn;

    TextView lo_Chuyen;

    TextView lo_ChuyenAn;

    TextView lo_Nhan;

    TextView lo_NhanAn;

    TextView loa_Chuyen;

    TextView loa_ChuyenAn;

    TextView loa_Nhan;

    TextView loa_NhanAn;

    ListView lv_baocaoKhach;

    LayoutInflater mInflate;

    private List<String> mSDT = new ArrayList<String>();

    private List<String> mTenKH = new ArrayList<String>();

    int position;

    private ProgressBar progressBar;

    private Runnable runnable = new Runnable() {
        public void run() {
            new MainActivity();
            if (MainActivity.sms == true) {
                Frag_No_new.this.lv_baoCao();
                MainActivity.sms = false;
            }
            Frag_No_new.this.handler.postDelayed(this, 1000L);
        }
    };

    TextView tv_TongGiu;

    TextView tv_TongTienChuyen;

    TextView tv_TongTienNhan;

    View v;

    TextView xi2_Chuyen;

    TextView xi2_ChuyenAn;

    TextView xi2_Nhan;

    TextView xi2_NhanAn;

    TextView xia2_Chuyen;

    TextView xia2_ChuyenAn;

    TextView xia2_Nhan;

    TextView xia2_NhanAn;

    TextView xn_Chuyen;

    TextView xn_ChuyenAn;

    TextView xn_Nhan;

    TextView xn_NhanAn;

    private void TinhlaitienKhachnay(String paramString1, String paramString2) {
        Database database2 = this.db;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Delete From tbl_soctS WHERE  ngay_nhan = '");
        stringBuilder1.append(paramString1);
        stringBuilder1.append("' AND ten_kh = '");
        stringBuilder1.append(this.mTenKH.get(this.position));
        stringBuilder1.append("'");
        database2.QueryData(stringBuilder1.toString());
        Database database1 = this.db;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Select * FROM tbl_tinnhanS WHERE  ngay_nhan = '");
        stringBuilder2.append(paramString1);
        stringBuilder2.append("' AND phat_hien_loi = 'ok' AND ten_kh = '");
        stringBuilder2.append(this.mTenKH.get(this.position));
        stringBuilder2.append("'");
        Cursor cursor = database1.GetData(stringBuilder2.toString());
        while (cursor.moveToNext()) {
            String str = cursor.getString(10).replaceAll("\\*", "");
            Database database = this.db;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Update tbl_tinnhanS set nd_phantich = '");
            stringBuilder.append(str);
            stringBuilder.append("' WHERE id = ");
            stringBuilder.append(cursor.getInt(0));
            database.QueryData(stringBuilder.toString());
            this.db.NhapSoChiTiet(cursor.getInt(0));
        }
        Tinhtien();
        lv_baoCao();
        if (!cursor.isClosed())
            cursor.close();
    }

    private void Tinhtien() {
        new MainActivity();
        String str = MainActivity.Get_date();
        Database database = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select * From Ketqua WHERE ngay = '");
        stringBuilder.append(str);
        stringBuilder.append("'");
        Cursor cursor = database.GetData(stringBuilder.toString());
        cursor.moveToFirst();
        byte b = 2;
        while (b < 29) {
            try {
                if (cursor.isNull(b) || !Congthuc.isNumeric(cursor.getString(b)))
                    break;
                b++;
            } catch (Exception exception) {
                // Byte code: goto -> 125
            }
        }
        if (b >= 29)
            this.db.Tinhtien((String)exception);
        if (cursor != null && !cursor.isClosed())
            cursor.close();
    }

    private void XemListview() {
        // Byte code:
        //   0: new tamhoang/ldpro4/MainActivity
        //   3: dup
        //   4: invokespecial <init> : ()V
        //   7: astore_1
        //   8: invokestatic Get_date : ()Ljava/lang/String;
        //   11: astore_2
        //   12: aload_0
        //   13: new java/util/ArrayList
        //   16: dup
        //   17: invokespecial <init> : ()V
        //   20: putfield jsonKhachHang : Ljava/util/List;
        //   23: ldc_w '###,###'
        //   26: astore_3
        //   27: new java/text/DecimalFormat
        //   30: dup
        //   31: ldc_w '###,###'
        //   34: invokespecial <init> : (Ljava/lang/String;)V
        //   37: astore #4
        //   39: aload_0
        //   40: getfield mTenKH : Ljava/util/List;
        //   43: invokeinterface clear : ()V
        //   48: aload_0
        //   49: getfield mSDT : Ljava/util/List;
        //   52: invokeinterface clear : ()V
        //   57: new java/lang/StringBuilder
        //   60: dup
        //   61: invokespecial <init> : ()V
        //   64: astore #5
        //   66: aload #5
        //   68: ldc_w 'Select ten_kh, so_dienthoai, the_loai\\n, sum((type_kh = 1)*(100-diem_khachgiu)*diem/100) as mDiem\\n, CASE WHEN the_loai = 'xi' OR the_loai = 'xia' \\n THEN sum((type_kh = 1)*(100-diem_khachgiu)*diem/100*so_nhay*lan_an/1000) \\n ELSE sum((type_kh = 1)*(100-diem_khachgiu)*diem/100*so_nhay)  END nAn\\n, sum((type_kh = 1)*ket_qua*(100-diem_khachgiu)/100/1000) as mKetqua\\n, sum((type_kh = 2)*(100-diem_khachgiu)*diem/100) as mDiem\\n, CASE WHEN the_loai = 'xi' OR the_loai = 'xia' \\n THEN sum((type_kh = 2)*(100-diem_khachgiu)*diem/100*so_nhay*lan_an/1000) \\n ELSE sum((type_kh = 2)*(100-diem_khachgiu)*diem/100*so_nhay)  END nAn\\n, sum((type_kh = 2)* ket_qua/1000) as mKetqua\\n  From tbl_soctS Where ngay_nhan = ''
        //   71: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   74: pop
        //   75: aload #5
        //   77: aload_2
        //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   81: pop
        //   82: aload #5
        //   84: ldc_w ''\\n  AND the_loai <> 'tt' GROUP by ten_kh, the_loai'
        //   87: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   90: pop
        //   91: aload #5
        //   93: invokevirtual toString : ()Ljava/lang/String;
        //   96: astore #5
        //   98: aload_1
        //   99: astore #6
        //   101: aload_2
        //   102: astore #6
        //   104: aload_3
        //   105: astore #6
        //   107: aload #4
        //   109: astore #6
        //   111: aload_1
        //   112: astore #6
        //   114: aload_2
        //   115: astore #6
        //   117: aload_3
        //   118: astore #6
        //   120: aload #4
        //   122: astore #6
        //   124: aload_0
        //   125: getfield db : Ltamhoang/ldpro4/data/Database;
        //   128: aload #5
        //   130: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   133: astore #7
        //   135: aload_1
        //   136: astore #6
        //   138: aload_2
        //   139: astore #6
        //   141: aload_3
        //   142: astore #6
        //   144: aload #4
        //   146: astore #6
        //   148: aload_1
        //   149: astore #6
        //   151: aload_2
        //   152: astore #6
        //   154: aload_3
        //   155: astore #6
        //   157: aload #4
        //   159: astore #6
        //   161: new org/json/JSONObject
        //   164: astore #8
        //   166: aload_1
        //   167: astore #6
        //   169: aload_2
        //   170: astore #6
        //   172: aload_3
        //   173: astore #6
        //   175: aload #4
        //   177: astore #6
        //   179: aload_1
        //   180: astore #6
        //   182: aload_2
        //   183: astore #6
        //   185: aload_3
        //   186: astore #6
        //   188: aload #4
        //   190: astore #6
        //   192: aload #8
        //   194: invokespecial <init> : ()V
        //   197: dconst_0
        //   198: dstore #9
        //   200: dconst_0
        //   201: dstore #11
        //   203: aload #7
        //   205: ifnull -> 820
        //   208: ldc ''
        //   210: astore #5
        //   212: aload_1
        //   213: astore #6
        //   215: aload_2
        //   216: astore #6
        //   218: aload_3
        //   219: astore #6
        //   221: aload #4
        //   223: astore #6
        //   225: aload_1
        //   226: astore #6
        //   228: aload_2
        //   229: astore #6
        //   231: aload_3
        //   232: astore #6
        //   234: aload #4
        //   236: astore #6
        //   238: aload #7
        //   240: invokeinterface moveToNext : ()Z
        //   245: istore #13
        //   247: iload #13
        //   249: ifeq -> 714
        //   252: aload #5
        //   254: invokevirtual length : ()I
        //   257: istore #14
        //   259: iload #14
        //   261: ifne -> 321
        //   264: aload_0
        //   265: getfield mTenKH : Ljava/util/List;
        //   268: aload #7
        //   270: iconst_0
        //   271: invokeinterface getString : (I)Ljava/lang/String;
        //   276: invokeinterface add : (Ljava/lang/Object;)Z
        //   281: pop
        //   282: aload_0
        //   283: getfield mSDT : Ljava/util/List;
        //   286: aload #7
        //   288: iconst_1
        //   289: invokeinterface getString : (I)Ljava/lang/String;
        //   294: invokeinterface add : (Ljava/lang/Object;)Z
        //   299: pop
        //   300: aload #7
        //   302: iconst_0
        //   303: invokeinterface getString : (I)Ljava/lang/String;
        //   308: astore #5
        //   310: goto -> 484
        //   313: astore_2
        //   314: goto -> 824
        //   317: astore_2
        //   318: goto -> 832
        //   321: aload #5
        //   323: aload #7
        //   325: iconst_0
        //   326: invokeinterface getString : (I)Ljava/lang/String;
        //   331: invokevirtual indexOf : (Ljava/lang/String;)I
        //   334: istore #14
        //   336: iload #14
        //   338: ifeq -> 484
        //   341: aload #8
        //   343: ldc_w 'Tien_Nhan'
        //   346: aload #4
        //   348: dload #9
        //   350: invokevirtual format : (D)Ljava/lang/String;
        //   353: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   356: pop
        //   357: aload #8
        //   359: ldc_w 'Tien_Chuyen'
        //   362: aload #4
        //   364: dload #11
        //   366: invokevirtual format : (D)Ljava/lang/String;
        //   369: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   372: pop
        //   373: aload #8
        //   375: ldc_w 'Tong_Tien'
        //   378: aload #4
        //   380: dload #9
        //   382: dload #11
        //   384: dadd
        //   385: invokevirtual format : (D)Ljava/lang/String;
        //   388: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   391: pop
        //   392: aload_0
        //   393: getfield jsonKhachHang : Ljava/util/List;
        //   396: aload #8
        //   398: invokeinterface add : (Ljava/lang/Object;)Z
        //   403: pop
        //   404: dconst_0
        //   405: dstore #9
        //   407: dconst_0
        //   408: dstore #11
        //   410: aload_0
        //   411: getfield mTenKH : Ljava/util/List;
        //   414: aload #7
        //   416: iconst_0
        //   417: invokeinterface getString : (I)Ljava/lang/String;
        //   422: invokeinterface add : (Ljava/lang/Object;)Z
        //   427: pop
        //   428: aload_0
        //   429: getfield mSDT : Ljava/util/List;
        //   432: aload #7
        //   434: iconst_1
        //   435: invokeinterface getString : (I)Ljava/lang/String;
        //   440: invokeinterface add : (Ljava/lang/Object;)Z
        //   445: pop
        //   446: aload #7
        //   448: iconst_0
        //   449: invokeinterface getString : (I)Ljava/lang/String;
        //   454: astore #5
        //   456: new org/json/JSONObject
        //   459: dup
        //   460: invokespecial <init> : ()V
        //   463: astore #8
        //   465: goto -> 484
        //   468: astore_2
        //   469: goto -> 824
        //   472: astore_2
        //   473: goto -> 832
        //   476: astore_2
        //   477: goto -> 824
        //   480: astore_2
        //   481: goto -> 832
        //   484: new org/json/JSONObject
        //   487: astore #6
        //   489: aload #6
        //   491: invokespecial <init> : ()V
        //   494: aload #6
        //   496: ldc_w 'DiemNhan'
        //   499: aload #4
        //   501: aload #7
        //   503: iconst_3
        //   504: invokeinterface getDouble : (I)D
        //   509: invokevirtual format : (D)Ljava/lang/String;
        //   512: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   515: pop
        //   516: aload #6
        //   518: ldc_w 'AnNhan'
        //   521: aload #4
        //   523: aload #7
        //   525: iconst_4
        //   526: invokeinterface getDouble : (I)D
        //   531: invokevirtual format : (D)Ljava/lang/String;
        //   534: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   537: pop
        //   538: aload #6
        //   540: ldc_w 'KQNhan'
        //   543: aload #4
        //   545: aload #7
        //   547: iconst_5
        //   548: invokeinterface getDouble : (I)D
        //   553: invokevirtual format : (D)Ljava/lang/String;
        //   556: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   559: pop
        //   560: aload #6
        //   562: ldc_w 'DiemChuyen'
        //   565: aload #4
        //   567: aload #7
        //   569: bipush #6
        //   571: invokeinterface getDouble : (I)D
        //   576: invokevirtual format : (D)Ljava/lang/String;
        //   579: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   582: pop
        //   583: aload #6
        //   585: ldc_w 'AnChuyen'
        //   588: aload #4
        //   590: aload #7
        //   592: bipush #7
        //   594: invokeinterface getDouble : (I)D
        //   599: invokevirtual format : (D)Ljava/lang/String;
        //   602: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   605: pop
        //   606: aload #7
        //   608: bipush #8
        //   610: invokeinterface getDouble : (I)D
        //   615: dstore #15
        //   617: aload #6
        //   619: ldc_w 'KQChuyen'
        //   622: aload #4
        //   624: dload #15
        //   626: invokevirtual format : (D)Ljava/lang/String;
        //   629: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   632: pop
        //   633: dload #9
        //   635: aload #7
        //   637: iconst_5
        //   638: invokeinterface getDouble : (I)D
        //   643: dadd
        //   644: dstore #9
        //   646: dload #11
        //   648: aload #7
        //   650: bipush #8
        //   652: invokeinterface getDouble : (I)D
        //   657: dadd
        //   658: dstore #11
        //   660: aload #8
        //   662: aload #7
        //   664: iconst_2
        //   665: invokeinterface getString : (I)Ljava/lang/String;
        //   670: aload #6
        //   672: invokevirtual toString : ()Ljava/lang/String;
        //   675: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   678: pop
        //   679: goto -> 212
        //   682: astore_2
        //   683: goto -> 824
        //   686: astore_2
        //   687: goto -> 832
        //   690: astore_2
        //   691: goto -> 824
        //   694: astore_2
        //   695: goto -> 832
        //   698: astore_2
        //   699: goto -> 824
        //   702: astore_2
        //   703: goto -> 832
        //   706: astore_2
        //   707: goto -> 824
        //   710: astore_2
        //   711: goto -> 832
        //   714: aload #8
        //   716: ldc_w 'Tien_Nhan'
        //   719: aload #4
        //   721: dload #9
        //   723: invokevirtual format : (D)Ljava/lang/String;
        //   726: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   729: pop
        //   730: aload #8
        //   732: ldc_w 'Tien_Chuyen'
        //   735: aload #4
        //   737: dload #11
        //   739: invokevirtual format : (D)Ljava/lang/String;
        //   742: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   745: pop
        //   746: aload #8
        //   748: ldc_w 'Tong_Tien'
        //   751: aload #4
        //   753: dload #9
        //   755: dload #11
        //   757: dadd
        //   758: invokevirtual format : (D)Ljava/lang/String;
        //   761: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   764: pop
        //   765: aload #7
        //   767: invokeinterface getCount : ()I
        //   772: ifle -> 787
        //   775: aload_0
        //   776: getfield jsonKhachHang : Ljava/util/List;
        //   779: aload #8
        //   781: invokeinterface add : (Ljava/lang/Object;)Z
        //   786: pop
        //   787: aload #7
        //   789: ifnull -> 832
        //   792: aload #7
        //   794: invokeinterface isClosed : ()Z
        //   799: ifne -> 832
        //   802: aload #7
        //   804: invokeinterface close : ()V
        //   809: goto -> 832
        //   812: astore_2
        //   813: goto -> 824
        //   816: astore_2
        //   817: goto -> 832
        //   820: goto -> 832
        //   823: astore_2
        //   824: aload_2
        //   825: invokevirtual printStackTrace : ()V
        //   828: goto -> 832
        //   831: astore_2
        //   832: aload_0
        //   833: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   836: ifnull -> 865
        //   839: aload_0
        //   840: getfield lv_baocaoKhach : Landroid/widget/ListView;
        //   843: new tamhoang/ldpro4/Fragment/Frag_No_new$NoRP_TN_Adapter
        //   846: dup
        //   847: aload_0
        //   848: aload_0
        //   849: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   852: ldc_w 2131427418
        //   855: aload_0
        //   856: getfield jsonKhachHang : Ljava/util/List;
        //   859: invokespecial <init> : (Ltamhoang/ldpro4/Fragment/Frag_No_new;Landroid/content/Context;ILjava/util/List;)V
        //   862: invokevirtual setAdapter : (Landroid/widget/ListAdapter;)V
        //   865: return
        // Exception table:
        //   from	to	target	type
        //   124	135	831	android/database/SQLException
        //   124	135	823	org/json/JSONException
        //   161	166	831	android/database/SQLException
        //   161	166	823	org/json/JSONException
        //   192	197	831	android/database/SQLException
        //   192	197	823	org/json/JSONException
        //   238	247	831	android/database/SQLException
        //   238	247	823	org/json/JSONException
        //   252	259	710	android/database/SQLException
        //   252	259	706	org/json/JSONException
        //   264	310	317	android/database/SQLException
        //   264	310	313	org/json/JSONException
        //   321	336	702	android/database/SQLException
        //   321	336	698	org/json/JSONException
        //   341	373	480	android/database/SQLException
        //   341	373	476	org/json/JSONException
        //   373	404	472	android/database/SQLException
        //   373	404	468	org/json/JSONException
        //   410	465	472	android/database/SQLException
        //   410	465	468	org/json/JSONException
        //   484	606	694	android/database/SQLException
        //   484	606	690	org/json/JSONException
        //   606	617	686	android/database/SQLException
        //   606	617	682	org/json/JSONException
        //   617	679	816	android/database/SQLException
        //   617	679	812	org/json/JSONException
        //   714	787	816	android/database/SQLException
        //   714	787	812	org/json/JSONException
        //   792	809	816	android/database/SQLException
        //   792	809	812	org/json/JSONException
    }

    private void init() {
        this.xn_Nhan = (TextView)this.v.findViewById(2131231575);
        this.xn_NhanAn = (TextView)this.v.findViewById(2131231576);
        this.xn_Chuyen = (TextView)this.v.findViewById(2131231573);
        this.xn_ChuyenAn = (TextView)this.v.findViewById(2131231574);
        this.dea_Nhan = (TextView)this.v.findViewById(2131230867);
        this.deb_Nhan = (TextView)this.v.findViewById(2131230871);
        this.det_Nhan = (TextView)this.v.findViewById(2131230894);
        this.dec_Nhan = (TextView)this.v.findViewById(2131230875);
        this.ded_Nhan = (TextView)this.v.findViewById(2131230882);
        this.lo_Nhan = (TextView)this.v.findViewById(2131231082);
        this.loa_Nhan = (TextView)this.v.findViewById(2131231086);
        this.bc_Nhan = (TextView)this.v.findViewById(2131230786);
        this.bca_Nhan = (TextView)this.v.findViewById(2131230790);
        this.dea_NhanAn = (TextView)this.v.findViewById(2131230868);
        this.deb_NhanAn = (TextView)this.v.findViewById(2131230872);
        this.det_NhanAn = (TextView)this.v.findViewById(2131230895);
        this.dec_NhanAn = (TextView)this.v.findViewById(2131230876);
        this.ded_NhanAn = (TextView)this.v.findViewById(2131230883);
        this.lo_NhanAn = (TextView)this.v.findViewById(2131231083);
        this.loa_NhanAn = (TextView)this.v.findViewById(2131231087);
        this.bc_NhanAn = (TextView)this.v.findViewById(2131230787);
        this.dea_Chuyen = (TextView)this.v.findViewById(2131230865);
        this.deb_Chuyen = (TextView)this.v.findViewById(2131230869);
        this.det_Chuyen = (TextView)this.v.findViewById(2131230892);
        this.dec_Chuyen = (TextView)this.v.findViewById(2131230873);
        this.ded_Chuyen = (TextView)this.v.findViewById(2131230880);
        this.lo_Chuyen = (TextView)this.v.findViewById(2131231080);
        this.loa_Chuyen = (TextView)this.v.findViewById(2131231084);
        this.bc_Chuyen = (TextView)this.v.findViewById(2131230784);
        this.dea_ChuyenAn = (TextView)this.v.findViewById(2131230866);
        this.deb_ChuyenAn = (TextView)this.v.findViewById(2131230870);
        this.det_ChuyenAn = (TextView)this.v.findViewById(2131230893);
        this.dec_ChuyenAn = (TextView)this.v.findViewById(2131230874);
        this.ded_ChuyenAn = (TextView)this.v.findViewById(2131230881);
        this.lo_ChuyenAn = (TextView)this.v.findViewById(2131231081);
        this.loa_ChuyenAn = (TextView)this.v.findViewById(2131231085);
        this.bc_ChuyenAn = (TextView)this.v.findViewById(2131230785);
        this.tv_TongGiu = (TextView)this.v.findViewById(2131231390);
        this.tv_TongTienNhan = (TextView)this.v.findViewById(2131231394);
        this.tv_TongTienChuyen = (TextView)this.v.findViewById(2131231393);
        this.li_dea = (LinearLayout)this.v.findViewById(2131231020);
        this.li_det = (LinearLayout)this.v.findViewById(2131231023);
        this.li_dec = (LinearLayout)this.v.findViewById(2131231021);
        this.li_ded = (LinearLayout)this.v.findViewById(2131231022);
        this.li_loa = (LinearLayout)this.v.findViewById(2131231026);
        this.li_bca = (LinearLayout)this.v.findViewById(2131231018);
        this.li_xia2 = (LinearLayout)this.v.findViewById(2131231030);
        this.li_xn = (LinearLayout)this.v.findViewById(2131231031);
        this.xi2_Nhan = (TextView)this.v.findViewById(2131231567);
        this.xi2_NhanAn = (TextView)this.v.findViewById(2131231568);
        this.xi2_Chuyen = (TextView)this.v.findViewById(2131231565);
        this.xi2_ChuyenAn = (TextView)this.v.findViewById(2131231566);
        this.xia2_Nhan = (TextView)this.v.findViewById(2131231571);
        this.xia2_NhanAn = (TextView)this.v.findViewById(2131231572);
        this.xia2_Chuyen = (TextView)this.v.findViewById(2131231569);
        this.xia2_ChuyenAn = (TextView)this.v.findViewById(2131231570);
        this.bca_Nhan = (TextView)this.v.findViewById(2131230790);
        this.bca_NhanAn = (TextView)this.v.findViewById(2131230791);
        this.bca_Chuyen = (TextView)this.v.findViewById(2131230788);
        this.bca_ChuyenAn = (TextView)this.v.findViewById(2131230789);
        this.lv_baocaoKhach = (ListView)this.v.findViewById(2131231092);
    }

    private void lv_baoCao() {
        // Byte code:
        //   0: ldc_w 'bc'
        //   3: astore_1
        //   4: ldc_w 'xi'
        //   7: astore_2
        //   8: ldc_w 'loa'
        //   11: astore_3
        //   12: ldc_w 'lo'
        //   15: astore #4
        //   17: ldc_w 'ded'
        //   20: astore #5
        //   22: ldc_w 'dec'
        //   25: astore #6
        //   27: ldc_w 'det'
        //   30: astore #7
        //   32: ldc_w 'deb'
        //   35: astore #8
        //   37: new tamhoang/ldpro4/MainActivity
        //   40: dup
        //   41: invokespecial <init> : ()V
        //   44: pop
        //   45: invokestatic Get_date : ()Ljava/lang/String;
        //   48: astore #9
        //   50: new java/text/DecimalFormat
        //   53: dup
        //   54: ldc_w '###,###'
        //   57: invokespecial <init> : (Ljava/lang/String;)V
        //   60: astore #10
        //   62: new java/lang/StringBuilder
        //   65: dup
        //   66: invokespecial <init> : ()V
        //   69: astore #11
        //   71: aload #11
        //   73: ldc_w 'Select the_loai\\n, sum((type_kh = 1)*(100-diem_khachgiu)*diem/100) as mDiem\\n, CASE WHEN the_loai = 'xi' OR the_loai = 'xia' \\n THEN sum((type_kh = 1)*(100-diem_khachgiu)*diem/100*so_nhay*lan_an/1000) \\n ELSE sum((type_kh = 1)*(100-diem_khachgiu)*diem/100*so_nhay)  END nAn\\n, sum((type_kh = 1)*ket_qua*(100-diem_khachgiu)/100/1000) as mKetqua\\n, sum((type_kh = 2)*(100-diem_khachgiu)*diem/100) as mDiem\\n, CASE WHEN the_loai = 'xi' OR the_loai = 'xia' \\n THEN sum((type_kh = 2)*(100-diem_khachgiu)*diem/100*so_nhay*lan_an/1000) \\n ELSE sum((type_kh = 2)*(100-diem_khachgiu)*diem/100*so_nhay)  END nAn\\n, sum((type_kh = 2)*ket_qua*(100-diem_khachgiu)/100/1000) as mKetqua\\n  From tbl_soctS Where ngay_nhan = ''
        //   76: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   79: pop
        //   80: aload #11
        //   82: aload #9
        //   84: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   87: pop
        //   88: aload #11
        //   90: ldc_w ''\\n  AND the_loai <> 'tt' GROUP by the_loai'
        //   93: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   96: pop
        //   97: aload #11
        //   99: invokevirtual toString : ()Ljava/lang/String;
        //   102: astore #11
        //   104: aload_0
        //   105: getfield db : Ltamhoang/ldpro4/data/Database;
        //   108: aload #11
        //   110: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   113: astore #9
        //   115: aload #9
        //   117: ifnull -> 3251
        //   120: new org/json/JSONObject
        //   123: dup
        //   124: invokespecial <init> : ()V
        //   127: astore #11
        //   129: dconst_0
        //   130: dstore #12
        //   132: dconst_0
        //   133: dstore #14
        //   135: aload #9
        //   137: invokeinterface moveToNext : ()Z
        //   142: istore #16
        //   144: iload #16
        //   146: ifeq -> 359
        //   149: new org/json/JSONObject
        //   152: astore #17
        //   154: aload #17
        //   156: invokespecial <init> : ()V
        //   159: aload #17
        //   161: ldc_w 'DiemNhan'
        //   164: aload #10
        //   166: aload #9
        //   168: iconst_1
        //   169: invokeinterface getDouble : (I)D
        //   174: invokevirtual format : (D)Ljava/lang/String;
        //   177: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   180: pop
        //   181: aload #17
        //   183: ldc_w 'AnNhan'
        //   186: aload #10
        //   188: aload #9
        //   190: iconst_2
        //   191: invokeinterface getDouble : (I)D
        //   196: invokevirtual format : (D)Ljava/lang/String;
        //   199: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   202: pop
        //   203: aload #17
        //   205: ldc_w 'KQNhan'
        //   208: aload #10
        //   210: aload #9
        //   212: iconst_3
        //   213: invokeinterface getDouble : (I)D
        //   218: invokevirtual format : (D)Ljava/lang/String;
        //   221: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   224: pop
        //   225: aload #17
        //   227: ldc_w 'DiemChuyen'
        //   230: aload #10
        //   232: aload #9
        //   234: iconst_4
        //   235: invokeinterface getDouble : (I)D
        //   240: invokevirtual format : (D)Ljava/lang/String;
        //   243: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   246: pop
        //   247: aload #17
        //   249: ldc_w 'AnChuyen'
        //   252: aload #10
        //   254: aload #9
        //   256: iconst_5
        //   257: invokeinterface getDouble : (I)D
        //   262: invokevirtual format : (D)Ljava/lang/String;
        //   265: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   268: pop
        //   269: aload #17
        //   271: ldc_w 'KQChuyen'
        //   274: aload #10
        //   276: aload #9
        //   278: bipush #6
        //   280: invokeinterface getDouble : (I)D
        //   285: invokevirtual format : (D)Ljava/lang/String;
        //   288: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   291: pop
        //   292: dload #12
        //   294: aload #9
        //   296: iconst_3
        //   297: invokeinterface getDouble : (I)D
        //   302: dadd
        //   303: dstore #12
        //   305: dload #14
        //   307: aload #9
        //   309: bipush #6
        //   311: invokeinterface getDouble : (I)D
        //   316: dadd
        //   317: dstore #14
        //   319: aload #9
        //   321: iconst_0
        //   322: invokeinterface getString : (I)Ljava/lang/String;
        //   327: astore #18
        //   329: aload #17
        //   331: invokevirtual toString : ()Ljava/lang/String;
        //   334: astore #17
        //   336: aload #11
        //   338: aload #18
        //   340: aload #17
        //   342: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   345: pop
        //   346: goto -> 135
        //   349: astore #11
        //   351: goto -> 3222
        //   354: astore #11
        //   356: goto -> 3222
        //   359: aload #11
        //   361: invokevirtual length : ()I
        //   364: ifle -> 3212
        //   367: aload #11
        //   369: ldc_w 'dea'
        //   372: invokevirtual has : (Ljava/lang/String;)Z
        //   375: istore #16
        //   377: iload #16
        //   379: ifeq -> 621
        //   382: aload_0
        //   383: getfield li_dea : Landroid/widget/LinearLayout;
        //   386: iconst_0
        //   387: invokevirtual setVisibility : (I)V
        //   390: new org/json/JSONObject
        //   393: astore #18
        //   395: aload #18
        //   397: aload #11
        //   399: ldc_w 'dea'
        //   402: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   405: invokespecial <init> : (Ljava/lang/String;)V
        //   408: aload #18
        //   410: ldc_w 'DiemNhan'
        //   413: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   416: invokevirtual length : ()I
        //   419: ifle -> 512
        //   422: aload_0
        //   423: getfield dea_Nhan : Landroid/widget/TextView;
        //   426: astore #19
        //   428: new java/lang/StringBuilder
        //   431: astore #17
        //   433: aload #17
        //   435: invokespecial <init> : ()V
        //   438: aload #17
        //   440: aload #18
        //   442: ldc_w 'DiemNhan'
        //   445: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   448: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   451: pop
        //   452: aload #17
        //   454: ldc_w '('
        //   457: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   460: pop
        //   461: aload #17
        //   463: aload #18
        //   465: ldc_w 'AnNhan'
        //   468: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   471: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   474: pop
        //   475: aload #17
        //   477: ldc_w ')'
        //   480: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   483: pop
        //   484: aload #19
        //   486: aload #17
        //   488: invokevirtual toString : ()Ljava/lang/String;
        //   491: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   494: aload_0
        //   495: getfield dea_NhanAn : Landroid/widget/TextView;
        //   498: aload #18
        //   500: ldc_w 'KQNhan'
        //   503: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   506: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   509: goto -> 512
        //   512: aload #18
        //   514: ldc_w 'DiemChuyen'
        //   517: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   520: invokevirtual length : ()I
        //   523: ifle -> 621
        //   526: aload_0
        //   527: getfield dea_Chuyen : Landroid/widget/TextView;
        //   530: astore #19
        //   532: new java/lang/StringBuilder
        //   535: astore #17
        //   537: aload #17
        //   539: invokespecial <init> : ()V
        //   542: aload #17
        //   544: aload #18
        //   546: ldc_w 'DiemChuyen'
        //   549: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   552: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   555: pop
        //   556: aload #17
        //   558: ldc_w '('
        //   561: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   564: pop
        //   565: aload #17
        //   567: aload #18
        //   569: ldc_w 'AnChuyen'
        //   572: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   575: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   578: pop
        //   579: aload #17
        //   581: ldc_w ')'
        //   584: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   587: pop
        //   588: aload #19
        //   590: aload #17
        //   592: invokevirtual toString : ()Ljava/lang/String;
        //   595: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   598: aload_0
        //   599: getfield dea_ChuyenAn : Landroid/widget/TextView;
        //   602: aload #18
        //   604: ldc_w 'KQChuyen'
        //   607: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   610: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   613: goto -> 621
        //   616: astore #11
        //   618: goto -> 3222
        //   621: aload #11
        //   623: aload #8
        //   625: invokevirtual has : (Ljava/lang/String;)Z
        //   628: istore #16
        //   630: iload #16
        //   632: ifeq -> 862
        //   635: new org/json/JSONObject
        //   638: astore #18
        //   640: aload #18
        //   642: aload #11
        //   644: aload #8
        //   646: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   649: invokespecial <init> : (Ljava/lang/String;)V
        //   652: aload #18
        //   654: ldc_w 'DiemNhan'
        //   657: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   660: invokevirtual length : ()I
        //   663: ifle -> 753
        //   666: aload_0
        //   667: getfield deb_Nhan : Landroid/widget/TextView;
        //   670: astore #8
        //   672: new java/lang/StringBuilder
        //   675: astore #17
        //   677: aload #17
        //   679: invokespecial <init> : ()V
        //   682: aload #17
        //   684: aload #18
        //   686: ldc_w 'DiemNhan'
        //   689: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   692: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   695: pop
        //   696: aload #17
        //   698: ldc_w '('
        //   701: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   704: pop
        //   705: aload #17
        //   707: aload #18
        //   709: ldc_w 'AnNhan'
        //   712: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   715: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   718: pop
        //   719: aload #17
        //   721: ldc_w ')'
        //   724: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   727: pop
        //   728: aload #8
        //   730: aload #17
        //   732: invokevirtual toString : ()Ljava/lang/String;
        //   735: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   738: aload_0
        //   739: getfield deb_NhanAn : Landroid/widget/TextView;
        //   742: aload #18
        //   744: ldc_w 'KQNhan'
        //   747: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   750: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   753: aload #18
        //   755: ldc_w 'DiemChuyen'
        //   758: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   761: invokevirtual length : ()I
        //   764: ifle -> 862
        //   767: aload_0
        //   768: getfield deb_Chuyen : Landroid/widget/TextView;
        //   771: astore #8
        //   773: new java/lang/StringBuilder
        //   776: astore #17
        //   778: aload #17
        //   780: invokespecial <init> : ()V
        //   783: aload #17
        //   785: aload #18
        //   787: ldc_w 'DiemChuyen'
        //   790: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   793: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   796: pop
        //   797: aload #17
        //   799: ldc_w '('
        //   802: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   805: pop
        //   806: aload #17
        //   808: aload #18
        //   810: ldc_w 'AnChuyen'
        //   813: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   816: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   819: pop
        //   820: aload #17
        //   822: ldc_w ')'
        //   825: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   828: pop
        //   829: aload #8
        //   831: aload #17
        //   833: invokevirtual toString : ()Ljava/lang/String;
        //   836: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   839: aload_0
        //   840: getfield deb_ChuyenAn : Landroid/widget/TextView;
        //   843: aload #18
        //   845: ldc_w 'KQChuyen'
        //   848: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   851: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   854: goto -> 862
        //   857: astore #11
        //   859: goto -> 3222
        //   862: aload #11
        //   864: aload #7
        //   866: invokevirtual has : (Ljava/lang/String;)Z
        //   869: istore #16
        //   871: iload #16
        //   873: ifeq -> 1103
        //   876: aload_0
        //   877: getfield li_det : Landroid/widget/LinearLayout;
        //   880: iconst_0
        //   881: invokevirtual setVisibility : (I)V
        //   884: new org/json/JSONObject
        //   887: astore #8
        //   889: aload #8
        //   891: aload #11
        //   893: aload #7
        //   895: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   898: invokespecial <init> : (Ljava/lang/String;)V
        //   901: aload #8
        //   903: ldc_w 'DiemNhan'
        //   906: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   909: invokevirtual length : ()I
        //   912: ifle -> 1002
        //   915: aload_0
        //   916: getfield det_Nhan : Landroid/widget/TextView;
        //   919: astore #18
        //   921: new java/lang/StringBuilder
        //   924: astore #7
        //   926: aload #7
        //   928: invokespecial <init> : ()V
        //   931: aload #7
        //   933: aload #8
        //   935: ldc_w 'DiemNhan'
        //   938: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   941: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   944: pop
        //   945: aload #7
        //   947: ldc_w '('
        //   950: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   953: pop
        //   954: aload #7
        //   956: aload #8
        //   958: ldc_w 'AnNhan'
        //   961: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   964: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   967: pop
        //   968: aload #7
        //   970: ldc_w ')'
        //   973: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   976: pop
        //   977: aload #18
        //   979: aload #7
        //   981: invokevirtual toString : ()Ljava/lang/String;
        //   984: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   987: aload_0
        //   988: getfield det_NhanAn : Landroid/widget/TextView;
        //   991: aload #8
        //   993: ldc_w 'KQNhan'
        //   996: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   999: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1002: aload #8
        //   1004: ldc_w 'DiemChuyen'
        //   1007: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1010: invokevirtual length : ()I
        //   1013: ifle -> 1103
        //   1016: aload_0
        //   1017: getfield det_Chuyen : Landroid/widget/TextView;
        //   1020: astore #18
        //   1022: new java/lang/StringBuilder
        //   1025: astore #7
        //   1027: aload #7
        //   1029: invokespecial <init> : ()V
        //   1032: aload #7
        //   1034: aload #8
        //   1036: ldc_w 'DiemChuyen'
        //   1039: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1042: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1045: pop
        //   1046: aload #7
        //   1048: ldc_w '('
        //   1051: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1054: pop
        //   1055: aload #7
        //   1057: aload #8
        //   1059: ldc_w 'AnChuyen'
        //   1062: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1065: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1068: pop
        //   1069: aload #7
        //   1071: ldc_w ')'
        //   1074: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1077: pop
        //   1078: aload #18
        //   1080: aload #7
        //   1082: invokevirtual toString : ()Ljava/lang/String;
        //   1085: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1088: aload_0
        //   1089: getfield det_ChuyenAn : Landroid/widget/TextView;
        //   1092: aload #8
        //   1094: ldc_w 'KQChuyen'
        //   1097: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1100: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1103: aload #11
        //   1105: aload #6
        //   1107: invokevirtual has : (Ljava/lang/String;)Z
        //   1110: istore #16
        //   1112: iload #16
        //   1114: ifeq -> 1344
        //   1117: aload_0
        //   1118: getfield li_dec : Landroid/widget/LinearLayout;
        //   1121: iconst_0
        //   1122: invokevirtual setVisibility : (I)V
        //   1125: new org/json/JSONObject
        //   1128: astore #8
        //   1130: aload #8
        //   1132: aload #11
        //   1134: aload #6
        //   1136: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1139: invokespecial <init> : (Ljava/lang/String;)V
        //   1142: aload #8
        //   1144: ldc_w 'DiemNhan'
        //   1147: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1150: invokevirtual length : ()I
        //   1153: ifle -> 1243
        //   1156: aload_0
        //   1157: getfield dec_Nhan : Landroid/widget/TextView;
        //   1160: astore #7
        //   1162: new java/lang/StringBuilder
        //   1165: astore #6
        //   1167: aload #6
        //   1169: invokespecial <init> : ()V
        //   1172: aload #6
        //   1174: aload #8
        //   1176: ldc_w 'DiemNhan'
        //   1179: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1182: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1185: pop
        //   1186: aload #6
        //   1188: ldc_w '('
        //   1191: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1194: pop
        //   1195: aload #6
        //   1197: aload #8
        //   1199: ldc_w 'AnNhan'
        //   1202: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1205: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1208: pop
        //   1209: aload #6
        //   1211: ldc_w ')'
        //   1214: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1217: pop
        //   1218: aload #7
        //   1220: aload #6
        //   1222: invokevirtual toString : ()Ljava/lang/String;
        //   1225: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1228: aload_0
        //   1229: getfield dec_NhanAn : Landroid/widget/TextView;
        //   1232: aload #8
        //   1234: ldc_w 'KQNhan'
        //   1237: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1240: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1243: aload #8
        //   1245: ldc_w 'DiemChuyen'
        //   1248: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1251: invokevirtual length : ()I
        //   1254: ifle -> 1344
        //   1257: aload_0
        //   1258: getfield dec_Chuyen : Landroid/widget/TextView;
        //   1261: astore #6
        //   1263: new java/lang/StringBuilder
        //   1266: astore #7
        //   1268: aload #7
        //   1270: invokespecial <init> : ()V
        //   1273: aload #7
        //   1275: aload #8
        //   1277: ldc_w 'DiemChuyen'
        //   1280: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1283: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1286: pop
        //   1287: aload #7
        //   1289: ldc_w '('
        //   1292: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1295: pop
        //   1296: aload #7
        //   1298: aload #8
        //   1300: ldc_w 'AnChuyen'
        //   1303: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1306: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1309: pop
        //   1310: aload #7
        //   1312: ldc_w ')'
        //   1315: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1318: pop
        //   1319: aload #6
        //   1321: aload #7
        //   1323: invokevirtual toString : ()Ljava/lang/String;
        //   1326: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1329: aload_0
        //   1330: getfield dec_ChuyenAn : Landroid/widget/TextView;
        //   1333: aload #8
        //   1335: ldc_w 'KQChuyen'
        //   1338: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1341: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1344: aload #11
        //   1346: aload #5
        //   1348: invokevirtual has : (Ljava/lang/String;)Z
        //   1351: istore #16
        //   1353: iload #16
        //   1355: ifeq -> 1585
        //   1358: aload_0
        //   1359: getfield li_ded : Landroid/widget/LinearLayout;
        //   1362: iconst_0
        //   1363: invokevirtual setVisibility : (I)V
        //   1366: new org/json/JSONObject
        //   1369: astore #6
        //   1371: aload #6
        //   1373: aload #11
        //   1375: aload #5
        //   1377: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1380: invokespecial <init> : (Ljava/lang/String;)V
        //   1383: aload #6
        //   1385: ldc_w 'DiemNhan'
        //   1388: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1391: invokevirtual length : ()I
        //   1394: ifle -> 1484
        //   1397: aload_0
        //   1398: getfield ded_Nhan : Landroid/widget/TextView;
        //   1401: astore #8
        //   1403: new java/lang/StringBuilder
        //   1406: astore #5
        //   1408: aload #5
        //   1410: invokespecial <init> : ()V
        //   1413: aload #5
        //   1415: aload #6
        //   1417: ldc_w 'DiemNhan'
        //   1420: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1423: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1426: pop
        //   1427: aload #5
        //   1429: ldc_w '('
        //   1432: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1435: pop
        //   1436: aload #5
        //   1438: aload #6
        //   1440: ldc_w 'AnNhan'
        //   1443: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1446: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1449: pop
        //   1450: aload #5
        //   1452: ldc_w ')'
        //   1455: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1458: pop
        //   1459: aload #8
        //   1461: aload #5
        //   1463: invokevirtual toString : ()Ljava/lang/String;
        //   1466: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1469: aload_0
        //   1470: getfield ded_NhanAn : Landroid/widget/TextView;
        //   1473: aload #6
        //   1475: ldc_w 'KQNhan'
        //   1478: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1481: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1484: aload #6
        //   1486: ldc_w 'DiemChuyen'
        //   1489: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1492: invokevirtual length : ()I
        //   1495: ifle -> 1585
        //   1498: aload_0
        //   1499: getfield ded_Chuyen : Landroid/widget/TextView;
        //   1502: astore #5
        //   1504: new java/lang/StringBuilder
        //   1507: astore #8
        //   1509: aload #8
        //   1511: invokespecial <init> : ()V
        //   1514: aload #8
        //   1516: aload #6
        //   1518: ldc_w 'DiemChuyen'
        //   1521: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1524: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1527: pop
        //   1528: aload #8
        //   1530: ldc_w '('
        //   1533: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1536: pop
        //   1537: aload #8
        //   1539: aload #6
        //   1541: ldc_w 'AnChuyen'
        //   1544: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1547: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1550: pop
        //   1551: aload #8
        //   1553: ldc_w ')'
        //   1556: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1559: pop
        //   1560: aload #5
        //   1562: aload #8
        //   1564: invokevirtual toString : ()Ljava/lang/String;
        //   1567: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1570: aload_0
        //   1571: getfield ded_ChuyenAn : Landroid/widget/TextView;
        //   1574: aload #6
        //   1576: ldc_w 'KQChuyen'
        //   1579: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1582: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1585: aload #11
        //   1587: aload #4
        //   1589: invokevirtual has : (Ljava/lang/String;)Z
        //   1592: istore #16
        //   1594: iload #16
        //   1596: ifeq -> 1818
        //   1599: new org/json/JSONObject
        //   1602: astore #5
        //   1604: aload #5
        //   1606: aload #11
        //   1608: aload #4
        //   1610: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1613: invokespecial <init> : (Ljava/lang/String;)V
        //   1616: aload #5
        //   1618: ldc_w 'DiemNhan'
        //   1621: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1624: invokevirtual length : ()I
        //   1627: ifle -> 1717
        //   1630: aload_0
        //   1631: getfield lo_Nhan : Landroid/widget/TextView;
        //   1634: astore #4
        //   1636: new java/lang/StringBuilder
        //   1639: astore #6
        //   1641: aload #6
        //   1643: invokespecial <init> : ()V
        //   1646: aload #6
        //   1648: aload #5
        //   1650: ldc_w 'DiemNhan'
        //   1653: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1656: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1659: pop
        //   1660: aload #6
        //   1662: ldc_w '('
        //   1665: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1668: pop
        //   1669: aload #6
        //   1671: aload #5
        //   1673: ldc_w 'AnNhan'
        //   1676: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1679: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1682: pop
        //   1683: aload #6
        //   1685: ldc_w ')'
        //   1688: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1691: pop
        //   1692: aload #4
        //   1694: aload #6
        //   1696: invokevirtual toString : ()Ljava/lang/String;
        //   1699: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1702: aload_0
        //   1703: getfield lo_NhanAn : Landroid/widget/TextView;
        //   1706: aload #5
        //   1708: ldc_w 'KQNhan'
        //   1711: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1714: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1717: aload #5
        //   1719: ldc_w 'DiemChuyen'
        //   1722: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1725: invokevirtual length : ()I
        //   1728: ifle -> 1818
        //   1731: aload_0
        //   1732: getfield lo_Chuyen : Landroid/widget/TextView;
        //   1735: astore #6
        //   1737: new java/lang/StringBuilder
        //   1740: astore #4
        //   1742: aload #4
        //   1744: invokespecial <init> : ()V
        //   1747: aload #4
        //   1749: aload #5
        //   1751: ldc_w 'DiemChuyen'
        //   1754: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1757: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1760: pop
        //   1761: aload #4
        //   1763: ldc_w '('
        //   1766: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1769: pop
        //   1770: aload #4
        //   1772: aload #5
        //   1774: ldc_w 'AnChuyen'
        //   1777: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1780: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1783: pop
        //   1784: aload #4
        //   1786: ldc_w ')'
        //   1789: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1792: pop
        //   1793: aload #6
        //   1795: aload #4
        //   1797: invokevirtual toString : ()Ljava/lang/String;
        //   1800: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1803: aload_0
        //   1804: getfield lo_ChuyenAn : Landroid/widget/TextView;
        //   1807: aload #5
        //   1809: ldc_w 'KQChuyen'
        //   1812: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1815: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1818: aload #11
        //   1820: aload_3
        //   1821: invokevirtual has : (Ljava/lang/String;)Z
        //   1824: istore #16
        //   1826: iload #16
        //   1828: ifeq -> 2053
        //   1831: aload_0
        //   1832: getfield li_loa : Landroid/widget/LinearLayout;
        //   1835: iconst_0
        //   1836: invokevirtual setVisibility : (I)V
        //   1839: new org/json/JSONObject
        //   1842: astore #4
        //   1844: aload #4
        //   1846: aload #11
        //   1848: aload_3
        //   1849: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1852: invokespecial <init> : (Ljava/lang/String;)V
        //   1855: aload #4
        //   1857: ldc_w 'DiemNhan'
        //   1860: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1863: invokevirtual length : ()I
        //   1866: ifle -> 1954
        //   1869: aload_0
        //   1870: getfield loa_Nhan : Landroid/widget/TextView;
        //   1873: astore_3
        //   1874: new java/lang/StringBuilder
        //   1877: astore #5
        //   1879: aload #5
        //   1881: invokespecial <init> : ()V
        //   1884: aload #5
        //   1886: aload #4
        //   1888: ldc_w 'DiemNhan'
        //   1891: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1894: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1897: pop
        //   1898: aload #5
        //   1900: ldc_w '('
        //   1903: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1906: pop
        //   1907: aload #5
        //   1909: aload #4
        //   1911: ldc_w 'AnNhan'
        //   1914: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1917: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1920: pop
        //   1921: aload #5
        //   1923: ldc_w ')'
        //   1926: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1929: pop
        //   1930: aload_3
        //   1931: aload #5
        //   1933: invokevirtual toString : ()Ljava/lang/String;
        //   1936: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1939: aload_0
        //   1940: getfield loa_NhanAn : Landroid/widget/TextView;
        //   1943: aload #4
        //   1945: ldc_w 'KQNhan'
        //   1948: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1951: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   1954: aload #4
        //   1956: ldc_w 'DiemChuyen'
        //   1959: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1962: invokevirtual length : ()I
        //   1965: ifle -> 2053
        //   1968: aload_0
        //   1969: getfield loa_Chuyen : Landroid/widget/TextView;
        //   1972: astore_3
        //   1973: new java/lang/StringBuilder
        //   1976: astore #5
        //   1978: aload #5
        //   1980: invokespecial <init> : ()V
        //   1983: aload #5
        //   1985: aload #4
        //   1987: ldc_w 'DiemChuyen'
        //   1990: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1993: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1996: pop
        //   1997: aload #5
        //   1999: ldc_w '('
        //   2002: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2005: pop
        //   2006: aload #5
        //   2008: aload #4
        //   2010: ldc_w 'AnChuyen'
        //   2013: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2016: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2019: pop
        //   2020: aload #5
        //   2022: ldc_w ')'
        //   2025: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2028: pop
        //   2029: aload_3
        //   2030: aload #5
        //   2032: invokevirtual toString : ()Ljava/lang/String;
        //   2035: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2038: aload_0
        //   2039: getfield loa_ChuyenAn : Landroid/widget/TextView;
        //   2042: aload #4
        //   2044: ldc_w 'KQChuyen'
        //   2047: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2050: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2053: aload #11
        //   2055: aload_2
        //   2056: invokevirtual has : (Ljava/lang/String;)Z
        //   2059: istore #16
        //   2061: iload #16
        //   2063: ifeq -> 2265
        //   2066: new org/json/JSONObject
        //   2069: astore_3
        //   2070: aload_3
        //   2071: aload #11
        //   2073: aload_2
        //   2074: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2077: invokespecial <init> : (Ljava/lang/String;)V
        //   2080: aload_3
        //   2081: ldc_w 'DiemNhan'
        //   2084: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2087: invokevirtual length : ()I
        //   2090: ifle -> 2170
        //   2093: aload_0
        //   2094: getfield xi2_Nhan : Landroid/widget/TextView;
        //   2097: astore #4
        //   2099: new java/lang/StringBuilder
        //   2102: astore_2
        //   2103: aload_2
        //   2104: invokespecial <init> : ()V
        //   2107: aload_2
        //   2108: aload_3
        //   2109: ldc_w 'DiemNhan'
        //   2112: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2115: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2118: pop
        //   2119: aload_2
        //   2120: ldc_w '('
        //   2123: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2126: pop
        //   2127: aload_2
        //   2128: aload_3
        //   2129: ldc_w 'AnNhan'
        //   2132: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2135: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2138: pop
        //   2139: aload_2
        //   2140: ldc_w ')'
        //   2143: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2146: pop
        //   2147: aload #4
        //   2149: aload_2
        //   2150: invokevirtual toString : ()Ljava/lang/String;
        //   2153: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2156: aload_0
        //   2157: getfield xi2_NhanAn : Landroid/widget/TextView;
        //   2160: aload_3
        //   2161: ldc_w 'KQNhan'
        //   2164: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2167: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2170: aload_3
        //   2171: ldc_w 'DiemChuyen'
        //   2174: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2177: invokevirtual length : ()I
        //   2180: ifle -> 2265
        //   2183: aload_0
        //   2184: getfield xi2_Chuyen : Landroid/widget/TextView;
        //   2187: astore_2
        //   2188: new java/lang/StringBuilder
        //   2191: astore #4
        //   2193: aload #4
        //   2195: invokespecial <init> : ()V
        //   2198: aload #4
        //   2200: aload_3
        //   2201: ldc_w 'DiemChuyen'
        //   2204: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2207: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2210: pop
        //   2211: aload #4
        //   2213: ldc_w '('
        //   2216: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2219: pop
        //   2220: aload #4
        //   2222: aload_3
        //   2223: ldc_w 'AnChuyen'
        //   2226: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2229: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2232: pop
        //   2233: aload #4
        //   2235: ldc_w ')'
        //   2238: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2241: pop
        //   2242: aload_2
        //   2243: aload #4
        //   2245: invokevirtual toString : ()Ljava/lang/String;
        //   2248: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2251: aload_0
        //   2252: getfield xi2_ChuyenAn : Landroid/widget/TextView;
        //   2255: aload_3
        //   2256: ldc_w 'KQChuyen'
        //   2259: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2262: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2265: aload #11
        //   2267: ldc_w 'xn'
        //   2270: invokevirtual has : (Ljava/lang/String;)Z
        //   2273: istore #16
        //   2275: iload #16
        //   2277: ifeq -> 2489
        //   2280: aload_0
        //   2281: getfield li_xn : Landroid/widget/LinearLayout;
        //   2284: iconst_0
        //   2285: invokevirtual setVisibility : (I)V
        //   2288: new org/json/JSONObject
        //   2291: astore_2
        //   2292: aload_2
        //   2293: aload #11
        //   2295: ldc_w 'xn'
        //   2298: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2301: invokespecial <init> : (Ljava/lang/String;)V
        //   2304: aload_2
        //   2305: ldc_w 'DiemNhan'
        //   2308: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2311: invokevirtual length : ()I
        //   2314: ifle -> 2399
        //   2317: aload_0
        //   2318: getfield xn_Nhan : Landroid/widget/TextView;
        //   2321: astore_3
        //   2322: new java/lang/StringBuilder
        //   2325: astore #4
        //   2327: aload #4
        //   2329: invokespecial <init> : ()V
        //   2332: aload #4
        //   2334: aload_2
        //   2335: ldc_w 'DiemNhan'
        //   2338: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2341: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2344: pop
        //   2345: aload #4
        //   2347: ldc_w '('
        //   2350: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2353: pop
        //   2354: aload #4
        //   2356: aload_2
        //   2357: ldc_w 'AnNhan'
        //   2360: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2363: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2366: pop
        //   2367: aload #4
        //   2369: ldc_w ')'
        //   2372: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2375: pop
        //   2376: aload_3
        //   2377: aload #4
        //   2379: invokevirtual toString : ()Ljava/lang/String;
        //   2382: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2385: aload_0
        //   2386: getfield xn_NhanAn : Landroid/widget/TextView;
        //   2389: aload_2
        //   2390: ldc_w 'KQNhan'
        //   2393: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2396: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2399: aload_2
        //   2400: ldc_w 'DiemChuyen'
        //   2403: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2406: invokevirtual length : ()I
        //   2409: ifle -> 2489
        //   2412: aload_0
        //   2413: getfield xn_Chuyen : Landroid/widget/TextView;
        //   2416: astore #4
        //   2418: new java/lang/StringBuilder
        //   2421: astore_3
        //   2422: aload_3
        //   2423: invokespecial <init> : ()V
        //   2426: aload_3
        //   2427: aload_2
        //   2428: ldc_w 'DiemChuyen'
        //   2431: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2434: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2437: pop
        //   2438: aload_3
        //   2439: ldc_w '('
        //   2442: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2445: pop
        //   2446: aload_3
        //   2447: aload_2
        //   2448: ldc_w 'AnChuyen'
        //   2451: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2454: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2457: pop
        //   2458: aload_3
        //   2459: ldc_w ')'
        //   2462: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2465: pop
        //   2466: aload #4
        //   2468: aload_3
        //   2469: invokevirtual toString : ()Ljava/lang/String;
        //   2472: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2475: aload_0
        //   2476: getfield xn_ChuyenAn : Landroid/widget/TextView;
        //   2479: aload_2
        //   2480: ldc_w 'KQChuyen'
        //   2483: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2486: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2489: aload #11
        //   2491: ldc_w 'xia'
        //   2494: invokevirtual has : (Ljava/lang/String;)Z
        //   2497: istore #16
        //   2499: iload #16
        //   2501: ifeq -> 2718
        //   2504: aload_0
        //   2505: getfield li_xia2 : Landroid/widget/LinearLayout;
        //   2508: iconst_0
        //   2509: invokevirtual setVisibility : (I)V
        //   2512: new org/json/JSONObject
        //   2515: astore_2
        //   2516: aload_2
        //   2517: aload #11
        //   2519: ldc_w 'xia'
        //   2522: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2525: invokespecial <init> : (Ljava/lang/String;)V
        //   2528: aload_2
        //   2529: ldc_w 'DiemNhan'
        //   2532: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2535: invokevirtual length : ()I
        //   2538: ifle -> 2623
        //   2541: aload_0
        //   2542: getfield xia2_Nhan : Landroid/widget/TextView;
        //   2545: astore_3
        //   2546: new java/lang/StringBuilder
        //   2549: astore #4
        //   2551: aload #4
        //   2553: invokespecial <init> : ()V
        //   2556: aload #4
        //   2558: aload_2
        //   2559: ldc_w 'DiemNhan'
        //   2562: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2565: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2568: pop
        //   2569: aload #4
        //   2571: ldc_w '('
        //   2574: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2577: pop
        //   2578: aload #4
        //   2580: aload_2
        //   2581: ldc_w 'AnNhan'
        //   2584: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2587: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2590: pop
        //   2591: aload #4
        //   2593: ldc_w ')'
        //   2596: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2599: pop
        //   2600: aload_3
        //   2601: aload #4
        //   2603: invokevirtual toString : ()Ljava/lang/String;
        //   2606: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2609: aload_0
        //   2610: getfield xia2_NhanAn : Landroid/widget/TextView;
        //   2613: aload_2
        //   2614: ldc_w 'KQNhan'
        //   2617: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2620: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2623: aload_2
        //   2624: ldc_w 'DiemChuyen'
        //   2627: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2630: invokevirtual length : ()I
        //   2633: ifle -> 2718
        //   2636: aload_0
        //   2637: getfield xia2_Chuyen : Landroid/widget/TextView;
        //   2640: astore_3
        //   2641: new java/lang/StringBuilder
        //   2644: astore #4
        //   2646: aload #4
        //   2648: invokespecial <init> : ()V
        //   2651: aload #4
        //   2653: aload_2
        //   2654: ldc_w 'DiemChuyen'
        //   2657: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2660: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2663: pop
        //   2664: aload #4
        //   2666: ldc_w '('
        //   2669: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2672: pop
        //   2673: aload #4
        //   2675: aload_2
        //   2676: ldc_w 'AnChuyen'
        //   2679: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2682: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2685: pop
        //   2686: aload #4
        //   2688: ldc_w ')'
        //   2691: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2694: pop
        //   2695: aload_3
        //   2696: aload #4
        //   2698: invokevirtual toString : ()Ljava/lang/String;
        //   2701: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2704: aload_0
        //   2705: getfield xia2_ChuyenAn : Landroid/widget/TextView;
        //   2708: aload_2
        //   2709: ldc_w 'KQChuyen'
        //   2712: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2715: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2718: aload #11
        //   2720: aload_1
        //   2721: invokevirtual has : (Ljava/lang/String;)Z
        //   2724: istore #16
        //   2726: iload #16
        //   2728: ifeq -> 2921
        //   2731: new org/json/JSONObject
        //   2734: astore_2
        //   2735: aload_2
        //   2736: aload #11
        //   2738: aload_1
        //   2739: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2742: invokespecial <init> : (Ljava/lang/String;)V
        //   2745: aload_2
        //   2746: ldc_w 'DiemNhan'
        //   2749: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2752: invokevirtual length : ()I
        //   2755: ifle -> 2833
        //   2758: aload_0
        //   2759: getfield bc_Nhan : Landroid/widget/TextView;
        //   2762: astore_1
        //   2763: new java/lang/StringBuilder
        //   2766: astore_3
        //   2767: aload_3
        //   2768: invokespecial <init> : ()V
        //   2771: aload_3
        //   2772: aload_2
        //   2773: ldc_w 'DiemNhan'
        //   2776: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2779: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2782: pop
        //   2783: aload_3
        //   2784: ldc_w '('
        //   2787: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2790: pop
        //   2791: aload_3
        //   2792: aload_2
        //   2793: ldc_w 'AnNhan'
        //   2796: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2799: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2802: pop
        //   2803: aload_3
        //   2804: ldc_w ')'
        //   2807: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2810: pop
        //   2811: aload_1
        //   2812: aload_3
        //   2813: invokevirtual toString : ()Ljava/lang/String;
        //   2816: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2819: aload_0
        //   2820: getfield bc_NhanAn : Landroid/widget/TextView;
        //   2823: aload_2
        //   2824: ldc_w 'KQNhan'
        //   2827: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2830: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2833: aload_2
        //   2834: ldc_w 'DiemChuyen'
        //   2837: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2840: invokevirtual length : ()I
        //   2843: ifle -> 2921
        //   2846: aload_0
        //   2847: getfield bc_Chuyen : Landroid/widget/TextView;
        //   2850: astore_3
        //   2851: new java/lang/StringBuilder
        //   2854: astore_1
        //   2855: aload_1
        //   2856: invokespecial <init> : ()V
        //   2859: aload_1
        //   2860: aload_2
        //   2861: ldc_w 'DiemChuyen'
        //   2864: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2867: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2870: pop
        //   2871: aload_1
        //   2872: ldc_w '('
        //   2875: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2878: pop
        //   2879: aload_1
        //   2880: aload_2
        //   2881: ldc_w 'AnChuyen'
        //   2884: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2887: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2890: pop
        //   2891: aload_1
        //   2892: ldc_w ')'
        //   2895: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2898: pop
        //   2899: aload_3
        //   2900: aload_1
        //   2901: invokevirtual toString : ()Ljava/lang/String;
        //   2904: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2907: aload_0
        //   2908: getfield bc_ChuyenAn : Landroid/widget/TextView;
        //   2911: aload_2
        //   2912: ldc_w 'KQChuyen'
        //   2915: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2918: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   2921: aload #11
        //   2923: ldc_w 'bca'
        //   2926: invokevirtual has : (Ljava/lang/String;)Z
        //   2929: istore #16
        //   2931: iload #16
        //   2933: ifeq -> 3140
        //   2936: aload_0
        //   2937: getfield li_bca : Landroid/widget/LinearLayout;
        //   2940: iconst_0
        //   2941: invokevirtual setVisibility : (I)V
        //   2944: new org/json/JSONObject
        //   2947: astore_1
        //   2948: aload_1
        //   2949: aload #11
        //   2951: ldc_w 'bca'
        //   2954: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2957: invokespecial <init> : (Ljava/lang/String;)V
        //   2960: aload_1
        //   2961: ldc_w 'DiemNhan'
        //   2964: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2967: invokevirtual length : ()I
        //   2970: ifle -> 3050
        //   2973: aload_0
        //   2974: getfield bca_Nhan : Landroid/widget/TextView;
        //   2977: astore #11
        //   2979: new java/lang/StringBuilder
        //   2982: astore_2
        //   2983: aload_2
        //   2984: invokespecial <init> : ()V
        //   2987: aload_2
        //   2988: aload_1
        //   2989: ldc_w 'DiemNhan'
        //   2992: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2995: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2998: pop
        //   2999: aload_2
        //   3000: ldc_w '('
        //   3003: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3006: pop
        //   3007: aload_2
        //   3008: aload_1
        //   3009: ldc_w 'AnNhan'
        //   3012: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3015: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3018: pop
        //   3019: aload_2
        //   3020: ldc_w ')'
        //   3023: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3026: pop
        //   3027: aload #11
        //   3029: aload_2
        //   3030: invokevirtual toString : ()Ljava/lang/String;
        //   3033: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3036: aload_0
        //   3037: getfield bca_NhanAn : Landroid/widget/TextView;
        //   3040: aload_1
        //   3041: ldc_w 'KQNhan'
        //   3044: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3047: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3050: aload_1
        //   3051: ldc_w 'DiemChuyen'
        //   3054: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3057: invokevirtual length : ()I
        //   3060: ifle -> 3140
        //   3063: aload_0
        //   3064: getfield bca_Chuyen : Landroid/widget/TextView;
        //   3067: astore #11
        //   3069: new java/lang/StringBuilder
        //   3072: astore_2
        //   3073: aload_2
        //   3074: invokespecial <init> : ()V
        //   3077: aload_2
        //   3078: aload_1
        //   3079: ldc_w 'DiemChuyen'
        //   3082: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3085: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3088: pop
        //   3089: aload_2
        //   3090: ldc_w '('
        //   3093: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3096: pop
        //   3097: aload_2
        //   3098: aload_1
        //   3099: ldc_w 'AnChuyen'
        //   3102: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3105: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3108: pop
        //   3109: aload_2
        //   3110: ldc_w ')'
        //   3113: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3116: pop
        //   3117: aload #11
        //   3119: aload_2
        //   3120: invokevirtual toString : ()Ljava/lang/String;
        //   3123: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3126: aload_0
        //   3127: getfield bca_ChuyenAn : Landroid/widget/TextView;
        //   3130: aload_1
        //   3131: ldc_w 'KQChuyen'
        //   3134: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3137: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3140: aload_0
        //   3141: getfield tv_TongTienNhan : Landroid/widget/TextView;
        //   3144: astore #11
        //   3146: aload #11
        //   3148: aload #10
        //   3150: dload #12
        //   3152: invokevirtual format : (D)Ljava/lang/String;
        //   3155: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3158: aload_0
        //   3159: getfield tv_TongTienChuyen : Landroid/widget/TextView;
        //   3162: astore #11
        //   3164: aload #11
        //   3166: aload #10
        //   3168: dload #14
        //   3170: invokevirtual format : (D)Ljava/lang/String;
        //   3173: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3176: aload_0
        //   3177: getfield tv_TongGiu : Landroid/widget/TextView;
        //   3180: aload #10
        //   3182: dload #12
        //   3184: dneg
        //   3185: dload #14
        //   3187: dsub
        //   3188: invokevirtual format : (D)Ljava/lang/String;
        //   3191: invokevirtual setText : (Ljava/lang/CharSequence;)V
        //   3194: goto -> 3222
        //   3197: astore #11
        //   3199: goto -> 3222
        //   3202: astore #11
        //   3204: goto -> 3222
        //   3207: astore #11
        //   3209: goto -> 3222
        //   3212: goto -> 3222
        //   3215: astore #11
        //   3217: goto -> 3222
        //   3220: astore #11
        //   3222: aload #9
        //   3224: ifnull -> 3244
        //   3227: aload #9
        //   3229: invokeinterface isClosed : ()Z
        //   3234: ifne -> 3244
        //   3237: aload #9
        //   3239: invokeinterface close : ()V
        //   3244: aload_0
        //   3245: invokespecial XemListview : ()V
        //   3248: goto -> 3251
        //   3251: return
        // Exception table:
        //   from	to	target	type
        //   135	144	3220	org/json/JSONException
        //   149	336	354	org/json/JSONException
        //   336	346	349	org/json/JSONException
        //   359	377	3215	org/json/JSONException
        //   382	438	616	org/json/JSONException
        //   438	509	857	org/json/JSONException
        //   512	613	857	org/json/JSONException
        //   621	630	3207	org/json/JSONException
        //   635	753	857	org/json/JSONException
        //   753	854	857	org/json/JSONException
        //   862	871	3207	org/json/JSONException
        //   876	1002	857	org/json/JSONException
        //   1002	1103	857	org/json/JSONException
        //   1103	1112	3207	org/json/JSONException
        //   1117	1243	857	org/json/JSONException
        //   1243	1344	857	org/json/JSONException
        //   1344	1353	3207	org/json/JSONException
        //   1358	1484	857	org/json/JSONException
        //   1484	1585	857	org/json/JSONException
        //   1585	1594	3207	org/json/JSONException
        //   1599	1717	857	org/json/JSONException
        //   1717	1818	857	org/json/JSONException
        //   1818	1826	3207	org/json/JSONException
        //   1831	1954	857	org/json/JSONException
        //   1954	2053	857	org/json/JSONException
        //   2053	2061	3207	org/json/JSONException
        //   2066	2170	857	org/json/JSONException
        //   2170	2265	857	org/json/JSONException
        //   2265	2275	3207	org/json/JSONException
        //   2280	2399	857	org/json/JSONException
        //   2399	2489	857	org/json/JSONException
        //   2489	2499	3207	org/json/JSONException
        //   2504	2623	857	org/json/JSONException
        //   2623	2718	857	org/json/JSONException
        //   2718	2726	3207	org/json/JSONException
        //   2731	2833	857	org/json/JSONException
        //   2833	2921	857	org/json/JSONException
        //   2921	2931	3207	org/json/JSONException
        //   2936	3050	857	org/json/JSONException
        //   3050	3140	857	org/json/JSONException
        //   3140	3146	3207	org/json/JSONException
        //   3146	3164	3202	org/json/JSONException
        //   3164	3194	3197	org/json/JSONException
    }

    public void Dialog(String paramString) {
        Dialog dialog = new Dialog((Context)getActivity());
        dialog.setContentView(2131427415);
        new MainActivity();
        String str = MainActivity.Get_date();
        ClipboardManager clipboardManager = (ClipboardManager)getActivity().getSystemService("clipboard");
        Database database = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select * From tbl_kh_new WHERE ten_kh ='");
        stringBuilder.append(paramString);
        stringBuilder.append("'");
        Cursor cursor = database.GetData(stringBuilder.toString());
        cursor.moveToFirst();
        Button button1 = (Button)dialog.findViewById(2131230722);
        Button button2 = (Button)dialog.findViewById(2131231319);
        Button button3 = (Button)dialog.findViewById(2131231121);
        Button button4 = (Button)dialog.findViewById(2131230858);
        Button button5 = (Button)dialog.findViewById(2131230859);
        Button button6 = (Button)dialog.findViewById(2131231578);
        Switch switch_ = (Switch)dialog.findViewById(2131231291);
        LinearLayout linearLayout = (LinearLayout)dialog.findViewById(2131231053);
        SeekBar seekBar2 = (SeekBar)dialog.findViewById(2131231217);
        SeekBar seekBar3 = (SeekBar)dialog.findViewById(2131231219);
        SeekBar seekBar4 = (SeekBar)dialog.findViewById(2131231221);
        SeekBar seekBar5 = (SeekBar)dialog.findViewById(2131231215);
        SeekBar seekBar6 = (SeekBar)dialog.findViewById(2131231216);
        SeekBar seekBar7 = (SeekBar)dialog.findViewById(2131231218);
        SeekBar seekBar1 = (SeekBar)dialog.findViewById(2131231220);
        SeekBar seekBar8 = (SeekBar)dialog.findViewById(2131231214);
        TextView textView1 = (TextView)dialog.findViewById(2131231149);
        TextView textView2 = (TextView)dialog.findViewById(2131231151);
        TextView textView3 = (TextView)dialog.findViewById(2131231153);
        TextView textView4 = (TextView)dialog.findViewById(2131231147);
        TextView textView5 = (TextView)dialog.findViewById(2131231148);
        TextView textView6 = (TextView)dialog.findViewById(2131231150);
        TextView textView7 = (TextView)dialog.findViewById(2131231152);
        TextView textView8 = (TextView)dialog.findViewById(2131231146);
        try {
            JSONObject jSONObject = new JSONObject();
            SeekBar seekBar9 = seekBar8;
            SeekBar seekBar10 = seekBar1;
            try {
                this(cursor.getString(5));
                this.json = jSONObject;
                this.caidat_tg = jSONObject.getJSONObject("caidat_tg");
                StringBuilder stringBuilder1 = new StringBuilder();
                this();
                stringBuilder1.append(this.caidat_tg.getInt("dlgiu_de"));
                stringBuilder1.append("%");
                textView5.setText(stringBuilder1.toString());
                stringBuilder1 = new StringBuilder();
                this();
                stringBuilder1.append(this.caidat_tg.getInt("dlgiu_lo"));
                stringBuilder1.append("%");
                textView6.setText(stringBuilder1.toString());
                stringBuilder1 = new StringBuilder();
                this();
                stringBuilder1.append(this.caidat_tg.getInt("dlgiu_xi"));
                stringBuilder1.append("%");
                textView7.setText(stringBuilder1.toString());
                stringBuilder1 = new StringBuilder();
                this();
                stringBuilder1.append(this.caidat_tg.getInt("dlgiu_bc"));
                stringBuilder1.append("%");
                textView8.setText(stringBuilder1.toString());
                seekBar6.setProgress(this.caidat_tg.getInt("dlgiu_de") / 5);
                seekBar7.setProgress(this.caidat_tg.getInt("dlgiu_lo") / 5);
                int i = this.caidat_tg.getInt("dlgiu_xi") / 5;
                try {
                    seekBar10.setProgress(i);
                    i = this.caidat_tg.getInt("dlgiu_bc") / 5;
                    try {
                        seekBar9.setProgress(i);
                        StringBuilder stringBuilder2 = new StringBuilder();
                        this();
                        stringBuilder2.append(this.caidat_tg.getInt("khgiu_de"));
                        stringBuilder2.append("%");
                        String str1 = stringBuilder2.toString();
                        try {
                            textView1.setText(str1);
                            StringBuilder stringBuilder3 = new StringBuilder();
                            this();
                            stringBuilder3.append(this.caidat_tg.getInt("khgiu_lo"));
                            stringBuilder3.append("%");
                            String str2 = stringBuilder3.toString();
                            try {
                                textView2.setText(str2);
                                StringBuilder stringBuilder4 = new StringBuilder();
                                this();
                                try {
                                    JSONObject jSONObject1 = this.caidat_tg;
                                    try {
                                        stringBuilder4.append(jSONObject1.getInt("khgiu_xi"));
                                        stringBuilder4.append("%");
                                        String str3 = stringBuilder4.toString();
                                        try {
                                            textView3.setText(str3);
                                            StringBuilder stringBuilder5 = new StringBuilder();
                                            this();
                                            try {
                                                JSONObject jSONObject2 = this.caidat_tg;
                                                try {
                                                    stringBuilder5.append(jSONObject2.getInt("khgiu_bc"));
                                                    stringBuilder5.append("%");
                                                    String str4 = stringBuilder5.toString();
                                                    try {
                                                        textView4.setText(str4);
                                                        i = this.caidat_tg.getInt("khgiu_de") / 5;
                                                        try {
                                                            seekBar2.setProgress(i);
                                                            i = this.caidat_tg.getInt("khgiu_lo") / 5;
                                                            try {
                                                                seekBar3.setProgress(i);
                                                                i = this.caidat_tg.getInt("khgiu_xi") / 5;
                                                                try {
                                                                    seekBar4.setProgress(i);
                                                                    i = this.caidat_tg.getInt("khgiu_bc") / 5;
                                                                    try {
                                                                        seekBar5.setProgress(i);
                                                                        cursor.close();
                                                                    } catch (JSONException jSONException) {
                                                                        jSONException.printStackTrace();
                                                                    } catch (Exception null) {}
                                                                } catch (JSONException jSONException) {

                                                                } catch (Exception null) {}
                                                            } catch (JSONException jSONException) {

                                                            } catch (Exception null) {}
                                                        } catch (JSONException jSONException) {

                                                        } catch (Exception null) {}
                                                    } catch (JSONException jSONException) {

                                                    } catch (Exception null) {}
                                                } catch (JSONException jSONException) {

                                                } catch (Exception null) {}
                                            } catch (JSONException jSONException) {

                                            } catch (Exception null) {}
                                        } catch (JSONException jSONException) {

                                        } catch (Exception null) {}
                                    } catch (JSONException jSONException) {

                                    } catch (Exception null) {}
                                } catch (JSONException jSONException) {

                                } catch (Exception null) {}
                            } catch (JSONException jSONException) {

                            } catch (Exception null) {}
                        } catch (JSONException jSONException) {

                        } catch (Exception null) {}
                    } catch (JSONException jSONException) {

                    } catch (Exception null) {}
                } catch (JSONException jSONException) {

                } catch (Exception null) {}
            } catch (JSONException jSONException) {

            } catch (Exception null) {}
        } catch (JSONException jSONException) {

        } catch (Exception exception) {}
        exception.printStackTrace();
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.v = paramLayoutInflater.inflate(2131427417, paramViewGroup, false);
        this.db = new Database((Context)getActivity());
        this.mInflate = paramLayoutInflater;
        init();
        this.progressBar = (ProgressBar)this.v.findViewById(2131231143);
        this.btn_nt = (Button)this.v.findViewById(2131230824);
        this.lv_baocaoKhach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_No_new.this.position = param1Int;
                Frag_No_new frag_No_new = Frag_No_new.this;
                frag_No_new.Dialog(frag_No_new.mTenKH.get(param1Int));
                return false;
            }
        });
        this.btn_nt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (Frag_No_new.this.jsonKhachHang.size() < 1)
                    return;
                AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_No_new.this.getActivity());
                builder.setMessage("Bcmunhtin chtitckh).setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                    param2DialogInterface.cancel();
                    Frag_No_new.this.progressBar.setVisibility(0);
                    Frag_No_new.this.getActivity().getWindow().setFlags(16, 16);
                    for (param2Int = 0; param2Int < Frag_No_new.this.jsonKhachHang.size(); param2Int++) {
                        Frag_No_new.this.currentIndex = param2Int;
                        Database database = Frag_No_new.this.db;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Select * From tbl_kh_new Where ten_kh = '");
                        stringBuilder.append(Frag_No_new.this.mTenKH.get(Frag_No_new.this.currentIndex));
                        stringBuilder.append("'");
                        Cursor cursor = database.GetData(stringBuilder.toString());
                        if (cursor != null && cursor.getCount() > 0) {
                            cursor.moveToFirst();
                            if (cursor.getString(2).indexOf("sms") > -1) {
                                try {
                                    if (MainActivity.jSon_Setting.getInt("tachxien_tinchot") == 0) {
                                        Frag_No_new.this.db.SendSMS(Frag_No_new.this.mSDT.get(Frag_No_new.this.currentIndex), Frag_No_new.this.db.Tin_Chottien(Frag_No_new.this.mTenKH.get(Frag_No_new.this.currentIndex)));
                                    } else {
                                        Frag_No_new.this.db.SendSMS(Frag_No_new.this.mSDT.get(Frag_No_new.this.currentIndex), Frag_No_new.this.db.Tin_Chottien_xien(Frag_No_new.this.mTenKH.get(Frag_No_new.this.currentIndex)));
                                    }
                                    Frag_No_new.this.isSuccess = true;
                                } catch (JSONException jSONException) {
                                    Frag_No_new.this.isSuccess = false;
                                    jSONException.printStackTrace();
                                }
                            } else if (cursor.getString(2).indexOf("TL") > -1) {
                                try {
                                    if (MainActivity.jSon_Setting.getInt("tachxien_tinchot") == 0) {
                                        MainActivity.sendMessage(Long.parseLong(Frag_No_new.this.mSDT.get(Frag_No_new.this.currentIndex)), Frag_No_new.this.db.Tin_Chottien(Frag_No_new.this.mTenKH.get(Frag_No_new.this.currentIndex)));
                                    } else {
                                        MainActivity.sendMessage(Long.parseLong(Frag_No_new.this.mSDT.get(Frag_No_new.this.currentIndex)), Frag_No_new.this.db.Tin_Chottien_xien(Frag_No_new.this.mTenKH.get(Frag_No_new.this.currentIndex)));
                                    }
                                    Frag_No_new.this.isSuccess = true;
                                    try {
                                        Thread.sleep(1000L);
                                    } catch (InterruptedException interruptedException) {
                                        interruptedException.printStackTrace();
                                    }
                                } catch (JSONException jSONException) {
                                    Frag_No_new.this.isSuccess = false;
                                    jSONException.printStackTrace();
                                }
                            } else if (MainActivity.arr_TenKH.indexOf(cursor.getString(1)) > -1) {
                                NotificationReader notificationReader = new NotificationReader();
                                try {
                                    if (MainActivity.jSon_Setting.getInt("tachxien_tinchot") == 0) {
                                        notificationReader.NotificationWearReader(cursor.getString(1), Frag_No_new.this.db.Tin_Chottien(Frag_No_new.this.mTenKH.get(Frag_No_new.this.currentIndex)));
                                    } else {
                                        notificationReader.NotificationWearReader(cursor.getString(1), Frag_No_new.this.db.Tin_Chottien_xien(Frag_No_new.this.mTenKH.get(Frag_No_new.this.currentIndex)));
                                    }
                                } catch (JSONException jSONException) {
                                    Frag_No_new.this.isSuccess = false;
                                    jSONException.printStackTrace();
                                }
                                Frag_No_new.this.isSuccess = true;
                            } else {
                                Frag_No_new.this.isSuccess = false;
                                Toast.makeText((Context)Frag_No_new.this.getActivity(), "Khcngntrong Chatbox", 1).show();
                            }
                        }
                    }
                    (new Handler(Looper.getMainLooper())).postDelayed(new Runnable() {
                        public void run() {
                            if (Frag_No_new.this.isSuccess)
                                Toast.makeText((Context)Frag_No_new.this.getActivity(), "nhchti, 1).show();
                                        Frag_No_new.this.progressBar.setVisibility(8);
                            Frag_No_new.this.getActivity().getWindow().clearFlags(16);
                        }
                    }2000L);
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                    param2DialogInterface.cancel();
                }
            });
            builder.create().show();
        }
    });
    if (!Congthuc.CheckTime("18:30")) {
        Handler handler = new Handler();
        this.handler = handler;
        handler.postDelayed(this.runnable, 1000L);
    }
    lv_baoCao();
    return this.v;
}

    public void onDestroy() {
        try {
            this.mTenKH.clear();
            this.mSDT.clear();
            this.lv_baocaoKhach.setAdapter(null);
        } catch (Exception exception) {}
        super.onDestroy();
    }

    public void onStop() {
        this.Running = false;
        super.onStop();
        try {
            this.handler.removeCallbacks(this.runnable);
        } catch (Exception exception) {}
    }

class NoRP_TN_Adapter extends ArrayAdapter {
    TextView bc_Chuyen;

    TextView bc_ChuyenAn;

    TextView bc_Nhan;

    TextView bc_NhanAn;

    TextView bca_Chuyen;

    TextView bca_ChuyenAn;

    TextView bca_Nhan;

    TextView bca_NhanAn;

    TextView dea_Chuyen;

    TextView dea_ChuyenAn;

    TextView dea_Nhan;

    TextView dea_NhanAn;

    TextView deb_Chuyen;

    TextView deb_ChuyenAn;

    TextView deb_Nhan;

    TextView deb_NhanAn;

    TextView dec_Chuyen;

    TextView dec_ChuyenAn;

    TextView dec_Nhan;

    TextView dec_NhanAn;

    TextView ded_Chuyen;

    TextView ded_ChuyenAn;

    TextView ded_Nhan;

    TextView ded_NhanAn;

    TextView det_Chuyen;

    TextView det_ChuyenAn;

    TextView det_Nhan;

    TextView det_NhanAn;

    LinearLayout li_bca;

    LinearLayout li_dea;

    LinearLayout li_dec;

    LinearLayout li_ded;

    LinearLayout li_det;

    LinearLayout li_loa;

    LinearLayout li_xi2;

    LinearLayout li_xia2;

    TextView lo_Chuyen;

    TextView lo_ChuyenAn;

    TextView lo_Nhan;

    TextView lo_NhanAn;

    TextView loa_Chuyen;

    TextView loa_ChuyenAn;

    TextView loa_Nhan;

    TextView loa_NhanAn;

    TextView tv_TongKhach;

    TextView tv_TongTienChuyen;

    TextView tv_TongTienNhan;

    TextView tv_ket_qua;

    TextView tv_tenKH;

    TextView tv_tongtien;

    TextView xi2_Chuyen;

    TextView xi2_ChuyenAn;

    TextView xi2_Nhan;

    TextView xi2_NhanAn;

    TextView xia2_Chuyen;

    TextView xia2_ChuyenAn;

    TextView xia2_Nhan;

    TextView xia2_NhanAn;

    public NoRP_TN_Adapter(Context param1Context, int param1Int, List<JSONObject> param1List) {
        super(param1Context, param1Int, param1List);
    }

    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
        param1View = ((Activity)getContext()).getLayoutInflater().inflate(2131427418, null);
        this.tv_tongtien = (TextView)param1View.findViewById(2131231513);
        this.tv_ket_qua = (TextView)param1View.findViewById(2131231385);
        this.tv_tenKH = (TextView)param1View.findViewById(2131231532);
        this.dea_Nhan = (TextView)param1View.findViewById(2131230867);
        this.deb_Nhan = (TextView)param1View.findViewById(2131230871);
        this.det_Nhan = (TextView)param1View.findViewById(2131230894);
        this.dec_Nhan = (TextView)param1View.findViewById(2131230875);
        this.ded_Nhan = (TextView)param1View.findViewById(2131230882);
        this.lo_Nhan = (TextView)param1View.findViewById(2131231082);
        this.loa_Nhan = (TextView)param1View.findViewById(2131231086);
        this.bc_Nhan = (TextView)param1View.findViewById(2131230786);
        this.bca_Nhan = (TextView)param1View.findViewById(2131230790);
        this.dea_NhanAn = (TextView)param1View.findViewById(2131230868);
        this.deb_NhanAn = (TextView)param1View.findViewById(2131230872);
        this.det_NhanAn = (TextView)param1View.findViewById(2131230895);
        this.dec_NhanAn = (TextView)param1View.findViewById(2131230876);
        this.ded_NhanAn = (TextView)param1View.findViewById(2131230883);
        this.lo_NhanAn = (TextView)param1View.findViewById(2131231083);
        this.loa_NhanAn = (TextView)param1View.findViewById(2131231087);
        this.bc_NhanAn = (TextView)param1View.findViewById(2131230787);
        this.dea_Chuyen = (TextView)param1View.findViewById(2131230865);
        this.deb_Chuyen = (TextView)param1View.findViewById(2131230869);
        this.det_Chuyen = (TextView)param1View.findViewById(2131230892);
        this.dec_Chuyen = (TextView)param1View.findViewById(2131230873);
        this.ded_Chuyen = (TextView)param1View.findViewById(2131230880);
        this.lo_Chuyen = (TextView)param1View.findViewById(2131231080);
        this.loa_Chuyen = (TextView)param1View.findViewById(2131231084);
        this.bc_Chuyen = (TextView)param1View.findViewById(2131230784);
        this.dea_ChuyenAn = (TextView)param1View.findViewById(2131230866);
        this.deb_ChuyenAn = (TextView)param1View.findViewById(2131230870);
        this.det_ChuyenAn = (TextView)param1View.findViewById(2131230893);
        this.dec_ChuyenAn = (TextView)param1View.findViewById(2131230874);
        this.ded_ChuyenAn = (TextView)param1View.findViewById(2131230881);
        this.lo_ChuyenAn = (TextView)param1View.findViewById(2131231081);
        this.loa_ChuyenAn = (TextView)param1View.findViewById(2131231085);
        this.bc_ChuyenAn = (TextView)param1View.findViewById(2131230785);
        this.tv_TongKhach = (TextView)param1View.findViewById(2131231391);
        this.tv_TongTienNhan = (TextView)param1View.findViewById(2131231394);
        this.tv_TongTienChuyen = (TextView)param1View.findViewById(2131231393);
        this.li_dea = (LinearLayout)param1View.findViewById(2131231020);
        this.li_det = (LinearLayout)param1View.findViewById(2131231023);
        this.li_dec = (LinearLayout)param1View.findViewById(2131231021);
        this.li_ded = (LinearLayout)param1View.findViewById(2131231022);
        this.li_loa = (LinearLayout)param1View.findViewById(2131231026);
        this.li_bca = (LinearLayout)param1View.findViewById(2131231018);
        this.li_xi2 = (LinearLayout)param1View.findViewById(2131231029);
        this.li_xia2 = (LinearLayout)param1View.findViewById(2131231030);
        this.xi2_Nhan = (TextView)param1View.findViewById(2131231567);
        this.xi2_NhanAn = (TextView)param1View.findViewById(2131231568);
        this.xi2_Chuyen = (TextView)param1View.findViewById(2131231565);
        this.xi2_ChuyenAn = (TextView)param1View.findViewById(2131231566);
        this.xia2_Nhan = (TextView)param1View.findViewById(2131231571);
        this.xia2_NhanAn = (TextView)param1View.findViewById(2131231572);
        this.xia2_Chuyen = (TextView)param1View.findViewById(2131231569);
        this.xia2_ChuyenAn = (TextView)param1View.findViewById(2131231570);
        this.bca_Nhan = (TextView)param1View.findViewById(2131230790);
        this.bca_NhanAn = (TextView)param1View.findViewById(2131230791);
        this.bca_Chuyen = (TextView)param1View.findViewById(2131230788);
        this.bca_ChuyenAn = (TextView)param1View.findViewById(2131230789);
        JSONObject jSONObject = Frag_No_new.this.jsonKhachHang.get(param1Int);
        try {
            TextView textView = this.tv_TongTienNhan;
            try {
                textView.setText(jSONObject.getString("Tien_Nhan"));
                this.tv_TongTienChuyen.setText(jSONObject.getString("Tien_Chuyen"));
                this.tv_TongKhach.setText(jSONObject.getString("Tong_Tien"));
                this.tv_tenKH.setText(Frag_No_new.this.mTenKH.get(param1Int));
                boolean bool = jSONObject.has("dea");
                if (bool) {
                    this.li_dea.setVisibility(0);
                    JSONObject jSONObject1 = new JSONObject();
                    this(jSONObject.getString("dea"));
                    if (jSONObject1.getString("DiemNhan").length() > 0) {
                        TextView textView1 = this.dea_Nhan;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemNhan"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnNhan"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.dea_NhanAn.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject1.getString("DiemChuyen").length() > 0) {
                        TextView textView1 = this.dea_Chuyen;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemChuyen"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnChuyen"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.dea_ChuyenAn.setText(jSONObject1.getString("KQChuyen"));
                    }
                }
                if (jSONObject.has("deb")) {
                    JSONObject jSONObject1 = new JSONObject();
                    this(jSONObject.getString("deb"));
                    if (jSONObject1.getString("DiemNhan").length() > 0) {
                        TextView textView1 = this.deb_Nhan;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemNhan"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnNhan"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.deb_NhanAn.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject1.getString("DiemChuyen").length() > 0) {
                        TextView textView1 = this.deb_Chuyen;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemChuyen"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnChuyen"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.deb_ChuyenAn.setText(jSONObject1.getString("KQChuyen"));
                    }
                }
                if (jSONObject.has("det")) {
                    this.li_det.setVisibility(0);
                    JSONObject jSONObject1 = new JSONObject();
                    this(jSONObject.getString("det"));
                    if (jSONObject1.getString("DiemNhan").length() > 0) {
                        TextView textView1 = this.det_Nhan;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemNhan"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnNhan"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.det_NhanAn.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject1.getString("DiemChuyen").length() > 0) {
                        TextView textView1 = this.det_Chuyen;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemChuyen"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnChuyen"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.det_ChuyenAn.setText(jSONObject1.getString("KQChuyen"));
                    }
                }
                if (jSONObject.has("dec")) {
                    this.li_dec.setVisibility(0);
                    JSONObject jSONObject1 = new JSONObject();
                    this(jSONObject.getString("dec"));
                    if (jSONObject1.getString("DiemNhan").length() > 0) {
                        TextView textView1 = this.dec_Nhan;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemNhan"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnNhan"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.dec_NhanAn.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject1.getString("DiemChuyen").length() > 0) {
                        TextView textView1 = this.dec_Chuyen;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemChuyen"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnChuyen"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.dec_ChuyenAn.setText(jSONObject1.getString("KQChuyen"));
                    }
                }
                if (jSONObject.has("ded")) {
                    this.li_ded.setVisibility(0);
                    JSONObject jSONObject1 = new JSONObject();
                    this(jSONObject.getString("ded"));
                    if (jSONObject1.getString("DiemNhan").length() > 0) {
                        TextView textView1 = this.ded_Nhan;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemNhan"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnNhan"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.ded_NhanAn.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject1.getString("DiemChuyen").length() > 0) {
                        TextView textView1 = this.ded_Chuyen;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemChuyen"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnChuyen"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.ded_ChuyenAn.setText(jSONObject1.getString("KQChuyen"));
                    }
                }
                if (jSONObject.has("lo")) {
                    JSONObject jSONObject1 = new JSONObject();
                    this(jSONObject.getString("lo"));
                    if (jSONObject1.getString("DiemNhan").length() > 0) {
                        TextView textView1 = this.lo_Nhan;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemNhan"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnNhan"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.lo_NhanAn.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject1.getString("DiemChuyen").length() > 0) {
                        TextView textView1 = this.lo_Chuyen;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemChuyen"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnChuyen"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.lo_ChuyenAn.setText(jSONObject1.getString("KQChuyen"));
                    }
                }
                if (jSONObject.has("loa")) {
                    this.li_loa.setVisibility(0);
                    JSONObject jSONObject1 = new JSONObject();
                    this(jSONObject.getString("loa"));
                    if (jSONObject1.getString("DiemNhan").length() > 0) {
                        TextView textView1 = this.loa_Nhan;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemNhan"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnNhan"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.loa_NhanAn.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject1.getString("DiemChuyen").length() > 0) {
                        TextView textView1 = this.loa_Chuyen;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemChuyen"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnChuyen"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.loa_ChuyenAn.setText(jSONObject1.getString("KQChuyen"));
                    }
                }
                if (jSONObject.has("xi")) {
                    this.li_xi2.setVisibility(0);
                    JSONObject jSONObject1 = new JSONObject();
                    this(jSONObject.getString("xi"));
                    if (jSONObject1.getString("DiemNhan").length() > 0) {
                        TextView textView1 = this.xi2_Nhan;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemNhan"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnNhan"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.xi2_NhanAn.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject1.getString("DiemChuyen").length() > 0) {
                        TextView textView1 = this.xi2_Chuyen;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemChuyen"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnChuyen"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.xi2_ChuyenAn.setText(jSONObject1.getString("KQChuyen"));
                    }
                }
                if (jSONObject.has("xia")) {
                    this.li_xia2.setVisibility(0);
                    JSONObject jSONObject1 = new JSONObject();
                    this(jSONObject.getString("xia"));
                    if (jSONObject1.getString("DiemNhan").length() > 0) {
                        TextView textView1 = this.xia2_Nhan;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemNhan"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnNhan"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.xia2_NhanAn.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject1.getString("DiemChuyen").length() > 0) {
                        TextView textView1 = this.xia2_Chuyen;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemChuyen"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnChuyen"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.xia2_ChuyenAn.setText(jSONObject1.getString("KQChuyen"));
                    }
                }
                if (jSONObject.has("bc")) {
                    JSONObject jSONObject1 = new JSONObject();
                    this(jSONObject.getString("bc"));
                    if (jSONObject1.getString("DiemNhan").length() > 0) {
                        TextView textView1 = this.bc_Nhan;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemNhan"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnNhan"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.bc_NhanAn.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject1.getString("DiemChuyen").length() > 0) {
                        TextView textView1 = this.bc_Chuyen;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemChuyen"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnChuyen"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.bc_ChuyenAn.setText(jSONObject1.getString("KQChuyen"));
                    }
                }
                if (jSONObject.has("bca")) {
                    this.li_bca.setVisibility(0);
                    JSONObject jSONObject1 = new JSONObject();
                    this(jSONObject.getString("bca"));
                    if (jSONObject1.getString("DiemNhan").length() > 0) {
                        TextView textView1 = this.bca_Nhan;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemNhan"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnNhan"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.bca_NhanAn.setText(jSONObject1.getString("KQNhan"));
                    }
                    if (jSONObject1.getString("DiemChuyen").length() > 0) {
                        TextView textView1 = this.bca_Chuyen;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("DiemChuyen"));
                        stringBuilder.append("(");
                        stringBuilder.append(jSONObject1.getString("AnChuyen"));
                        stringBuilder.append(")");
                        textView1.setText(stringBuilder.toString());
                        this.bca_ChuyenAn.setText(jSONObject1.getString("KQChuyen"));
                    }
                }
            } catch (Exception exception) {}
        } catch (Exception exception) {}
        Toast.makeText((Context)Frag_No_new.this.getActivity(), "OK", 1).show();
    }
}
}
