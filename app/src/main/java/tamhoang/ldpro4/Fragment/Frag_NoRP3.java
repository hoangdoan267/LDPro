package tamhoang.ldpro4.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Activity.Activity_Tinnhan;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.data.Database;

public class Frag_NoRP3 extends Fragment {
    Database db;

    List<JSONObject> jsonValues;

    ListView lv_no_tinnhan;

    String mDate;

    private List<Integer> mID = new ArrayList<Integer>();

    private ArrayList<String> mTen = new ArrayList<String>();

    int sp_Position;

    Spinner sp_khachhang;

    String str = "";

    String tenKhach;

    public void lv_report_sms() {
        MainActivity mainActivity = new MainActivity();
        String str1 = MainActivity.Get_date();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select * From tbl_tinnhanS Where ngay_nhan = '");
        stringBuilder.append(str1);
        stringBuilder.append("' and ten_kh = '");
        stringBuilder.append(this.mTen.get(this.sp_Position));
        stringBuilder.append("' AND phat_hien_loi = 'ok' Order by type_kh, so_tin_nhan");
        String str2 = stringBuilder.toString();
        Cursor cursor = this.db.GetData(str2);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        this.jsonValues = new ArrayList<JSONObject>();
        if (cursor != null) {
            MainActivity mainActivity1 = mainActivity;
            String str = str1;
            try {
                this.mID.clear();
                while (true) {
                    MainActivity mainActivity2 = mainActivity;
                    String str3 = str1;
                    if (cursor.moveToNext()) {
                        MainActivity mainActivity15 = mainActivity;
                        String str16 = str1;
                        StringBuilder stringBuilder1 = new StringBuilder();
                        MainActivity mainActivity14 = mainActivity;
                        String str15 = str1;
                        this();
                        MainActivity mainActivity13 = mainActivity;
                        String str14 = str1;
                        stringBuilder1.append("SElect CASE \nWHEN the_loai = 'xi' And length(so_chon) = 5 THEN 'xi2' \nWHEN the_loai = 'xi' And length(so_chon) = 8 THEN 'xi3' \nWHEN the_loai = 'xi' And length(so_chon) = 11 THEN 'xi4' \nWHEN the_loai = 'xia' And length(so_chon) = 5 THEN 'xia2' \nWHEN the_loai = 'xia' And length(so_chon) = 8 THEN 'xia3' \nWHEN the_loai = 'xia' And length(so_chon) = 11 THEN 'xia4' \nELSE the_loai END theloai, sum(diem), sum(diem*so_nhay) as An\n, sum (tong_tien)/1000 as kq \n, sum(Ket_qua)/1000 as tienCuoi\n From tbl_soctS \n Where ten_kh = '");
                        MainActivity mainActivity12 = mainActivity;
                        String str13 = str1;
                        stringBuilder1.append(this.mTen.get(this.sp_Position));
                        MainActivity mainActivity11 = mainActivity;
                        String str12 = str1;
                        stringBuilder1.append("' and ngay_nhan = '");
                        MainActivity mainActivity10 = mainActivity;
                        String str11 = str1;
                        stringBuilder1.append(str1);
                        MainActivity mainActivity9 = mainActivity;
                        String str10 = str1;
                        stringBuilder1.append("' and So_tin_nhan = ");
                        MainActivity mainActivity8 = mainActivity;
                        String str9 = str1;
                        stringBuilder1.append(cursor.getString(7));
                        MainActivity mainActivity7 = mainActivity;
                        String str8 = str1;
                        stringBuilder1.append(" AND type_kh = ");
                        MainActivity mainActivity6 = mainActivity;
                        String str7 = str1;
                        stringBuilder1.append(cursor.getString(3));
                        MainActivity mainActivity5 = mainActivity;
                        String str6 = str1;
                        stringBuilder1.append(" Group by theloai");
                        MainActivity mainActivity4 = mainActivity;
                        String str5 = str1;
                        String str17 = stringBuilder1.toString();
                        MainActivity mainActivity3 = mainActivity;
                        String str4 = str1;
                        Cursor cursor1 = this.db.GetData(str17);
                        if (cursor1 != null) {
                            MainActivity mainActivity26 = mainActivity;
                            String str28 = str1;
                            JSONObject jSONObject1 = new JSONObject();
                            MainActivity mainActivity25 = mainActivity;
                            String str27 = str1;
                            this();
                            MainActivity mainActivity24 = mainActivity;
                            String str26 = str1;
                            jSONObject1.put("ID", cursor.getInt(0));
                            MainActivity mainActivity23 = mainActivity;
                            String str25 = str1;
                            jSONObject1.put("gio_nhan", cursor.getString(2));
                            MainActivity mainActivity22 = mainActivity;
                            String str24 = str1;
                            jSONObject1.put("type_kh", cursor.getString(3));
                            MainActivity mainActivity21 = mainActivity;
                            String str23 = str1;
                            jSONObject1.put("ten_KH", cursor.getString(4));
                            MainActivity mainActivity20 = mainActivity;
                            String str22 = str1;
                            jSONObject1.put("so_tinnhan", cursor.getString(7));
                            MainActivity mainActivity19 = mainActivity;
                            String str21 = str1;
                            jSONObject1.put("tin_goc", cursor.getString(8));
                            MainActivity mainActivity18 = mainActivity;
                            String str20 = str1;
                            jSONObject1.put("nd_phantich", cursor.getString(10));
                            MainActivity mainActivity17 = mainActivity;
                            String str19 = str1;
                            JSONObject jSONObject2 = new JSONObject();
                            MainActivity mainActivity16 = mainActivity;
                            String str18 = str1;
                            this();
                            double d1 = 0.0D;
                            double d2 = 0.0D;
                            while (true) {
                                MainActivity mainActivity27 = mainActivity;
                                String str29 = str1;
                                boolean bool = cursor1.moveToNext();
                                if (bool)
                                    try {
                                        jSONObject2.put("the_loai", cursor1.getString(0));
                                        jSONObject2.put("diem", decimalFormat.format(cursor1.getInt(1)));
                                        jSONObject2.put("diem_an", decimalFormat.format(cursor1.getInt(2)));
                                        jSONObject2.put("tong_tien", decimalFormat.format(cursor1.getInt(3)));
                                        jSONObject2.put("ket_qua", decimalFormat.format(cursor1.getInt(4)));
                                        d1 += cursor1.getDouble(3);
                                        d2 += cursor1.getDouble(4);
                                        jSONObject1.put(cursor1.getString(0), jSONObject2.toString());
                                        continue;
                                    } catch (JSONException jSONException) {
                                        break;
                                    }
                                try {
                                    jSONObject1.put("tong_tien", decimalFormat.format(d1));
                                    jSONObject1.put("ket_qua", decimalFormat.format(d2));
                                    this.jsonValues.add(jSONObject1);
                                    this.mID.add(Integer.valueOf(cursor.getInt(0)));
                                    cursor1.close();
                                } catch (JSONException jSONException) {
                                    break;
                                }
                            }
                            break;
                        }
                        continue;
                    }
                    cursor.close();
                    break;
                }
            } catch (JSONException jSONException) {}
        }
        if (getActivity() != null)
            this.lv_no_tinnhan.setAdapter((ListAdapter)new TinNhan_Adapter((Context)getActivity(), 2131427377, this.jsonValues));
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View view = paramLayoutInflater.inflate(2131427423, paramViewGroup, false);
        this.db = new Database((Context)getActivity());
        ListView listView = (ListView)view.findViewById(2131231123);
        this.lv_no_tinnhan = listView;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, final int position, long param1Long) {
                String[] arrayOfString = new String[2];
                arrayOfString[0] = "S;
                arrayOfString[1] = "X;
                PopupMenu popupMenu = new PopupMenu((Context)Frag_NoRP3.this.getActivity(), param1View);
                for (byte b = 0; b < arrayOfString.length; b++)
                    popupMenu.getMenu().add(1, b, b, arrayOfString[b]);
                new AlertDialog.Builder((Context)Frag_NoRP3.this.getActivity());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem param2MenuItem) {
                        int i = param2MenuItem.getOrder();
                        if (i != 0) {
                            if (i == 1) {
                                AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_NoRP3.this.getActivity());
                                builder.setTitle("Xotin nhn);
                                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface param3DialogInterface, int param3Int) {
                                                Database database1 = Frag_NoRP3.this.db;
                                                StringBuilder stringBuilder = new StringBuilder();
                                                stringBuilder.append("Select * From tbl_tinnhanS where ID = ");
                                                stringBuilder.append(Frag_NoRP3.this.mID.get(position));
                                                Cursor cursor = database1.GetData(stringBuilder.toString());
                                                cursor.moveToFirst();
                                                Database database2 = Frag_NoRP3.this.db;
                                                stringBuilder = new StringBuilder();
                                                stringBuilder.append("DELETE FROM tbl_tinnhanS WHERE ngay_nhan = '");
                                                stringBuilder.append(cursor.getString(1));
                                                stringBuilder.append("' AND ten_kh = '");
                                                stringBuilder.append(cursor.getString(4));
                                                stringBuilder.append("' AND so_tin_nhan = ");
                                                stringBuilder.append(cursor.getString(7));
                                                stringBuilder.append(" AND type_kh = ");
                                                stringBuilder.append(cursor.getString(3));
                                                database2.QueryData(stringBuilder.toString());
                                                database2 = Frag_NoRP3.this.db;
                                                stringBuilder = new StringBuilder();
                                                stringBuilder.append("DELETE FROM tbl_soctS WHERE ngay_nhan = '");
                                                stringBuilder.append(cursor.getString(1));
                                                stringBuilder.append("' AND ten_kh = '");
                                                stringBuilder.append(cursor.getString(4));
                                                stringBuilder.append("' AND so_tin_nhan = ");
                                                stringBuilder.append(cursor.getString(7));
                                                stringBuilder.append(" AND type_kh = ");
                                                stringBuilder.append(cursor.getString(3));
                                                database2.QueryData(stringBuilder.toString());
                                                cursor.close();
                                                Toast.makeText((Context)Frag_NoRP3.this.getActivity(), "xtin", 1).show();
                                                Frag_NoRP3.this.lv_report_sms();
                                            }
                                        });
                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface param3DialogInterface, int param3Int) {
                                        param3DialogInterface.cancel();
                                    }
                                });
                                builder.create().show();
                            }
                        } else {
                            Intent intent = new Intent((Context)Frag_NoRP3.this.getActivity(), Activity_Tinnhan.class);
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(Frag_NoRP3.this.mID.get(position));
                            stringBuilder.append("");
                            intent.putExtra("m_ID", stringBuilder.toString());
                            Frag_NoRP3.this.startActivity(intent);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        this.sp_khachhang = (Spinner)view.findViewById(2131231252);
        this.mTen.add("Ltheo kh);
                ArrayAdapter arrayAdapter = new ArrayAdapter((Context)getActivity(), 2131427455, this.mTen);
        this.sp_khachhang.setAdapter((SpinnerAdapter)arrayAdapter);
        this.sp_khachhang.setSelection(0);
        this.sp_khachhang.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
                new MainActivity();
                Frag_NoRP3.this.mDate = MainActivity.Get_date();
                Frag_NoRP3.this.mTen.clear();
                Database database = Frag_NoRP3.this.db;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Select ten_kh From tbl_soctS WHERE ngay_nhan = '");
                stringBuilder.append(Frag_NoRP3.this.mDate);
                stringBuilder.append("' GROUP by ten_kh Order by ten_kh");
                Cursor cursor = database.GetData(stringBuilder.toString());
                while (cursor.moveToNext())
                    Frag_NoRP3.this.mTen.add(cursor.getString(0));
                cursor.close();
                if (Frag_NoRP3.this.mTen.size() == 0)
                    Frag_NoRP3.this.mTen.add("Hnay chctin nh);
                            ArrayAdapter arrayAdapter = new ArrayAdapter((Context)Frag_NoRP3.this.getActivity(), 2131427455, Frag_NoRP3.this.mTen);
                Frag_NoRP3.this.sp_khachhang.setAdapter((SpinnerAdapter)arrayAdapter);
                try {
                    Frag_NoRP3.this.sp_khachhang.setSelection(Frag_NoRP3.this.mTen.indexOf(Frag_NoRP3.this.tenKhach));
                } catch (Exception exception) {}
                return false;
            }
        });
        this.sp_khachhang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_NoRP3.this.sp_Position = param1Int;
                Frag_NoRP3 frag_NoRP32 = Frag_NoRP3.this;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" AND tbl_soctS.ten_kh = '");
                stringBuilder.append(Frag_NoRP3.this.mTen.get(Frag_NoRP3.this.sp_Position));
                stringBuilder.append("'");
                frag_NoRP32.str = stringBuilder.toString();
                Frag_NoRP3 frag_NoRP31 = Frag_NoRP3.this;
                frag_NoRP31.tenKhach = frag_NoRP31.mTen.get(Frag_NoRP3.this.sp_Position);
                Frag_NoRP3.this.lv_report_sms();
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        registerForContextMenu((View)this.lv_no_tinnhan);
        return view;
    }

    class TinNhan_Adapter extends ArrayAdapter {
        public TinNhan_Adapter(Context param1Context, int param1Int, List<JSONObject> param1List) {
            super(param1Context, param1Int, param1List);
        }

        public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
            // Byte code:
            //   0: aconst_null
            //   1: astore_3
            //   2: aload_0
            //   3: invokevirtual getContext : ()Landroid/content/Context;
            //   6: ldc 'layout_inflater'
            //   8: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
            //   11: checkcast android/view/LayoutInflater
            //   14: ldc 2131427377
            //   16: aconst_null
            //   17: invokevirtual inflate : (ILandroid/view/ViewGroup;)Landroid/view/View;
            //   20: astore #4
            //   22: aload #4
            //   24: ldc 2131231513
            //   26: invokevirtual findViewById : (I)Landroid/view/View;
            //   29: checkcast android/widget/TextView
            //   32: astore #5
            //   34: aload #4
            //   36: ldc 2131231385
            //   38: invokevirtual findViewById : (I)Landroid/view/View;
            //   41: checkcast android/widget/TextView
            //   44: astore_2
            //   45: aload #4
            //   47: ldc 2131231533
            //   49: invokevirtual findViewById : (I)Landroid/view/View;
            //   52: checkcast android/widget/TextView
            //   55: astore #6
            //   57: aload #4
            //   59: ldc 2131231384
            //   61: invokevirtual findViewById : (I)Landroid/view/View;
            //   64: checkcast android/widget/TextView
            //   67: astore #7
            //   69: aload #4
            //   71: ldc 2131231389
            //   73: invokevirtual findViewById : (I)Landroid/view/View;
            //   76: checkcast android/widget/TextView
            //   79: astore #8
            //   81: aload #4
            //   83: ldc 2131231380
            //   85: invokevirtual findViewById : (I)Landroid/view/View;
            //   88: checkcast android/widget/TextView
            //   91: astore #9
            //   93: aload #4
            //   95: ldc 2131231471
            //   97: invokevirtual findViewById : (I)Landroid/view/View;
            //   100: checkcast android/widget/TextView
            //   103: astore #10
            //   105: aload #4
            //   107: ldc 2131231038
            //   109: invokevirtual findViewById : (I)Landroid/view/View;
            //   112: checkcast android/widget/LinearLayout
            //   115: astore #11
            //   117: aload #4
            //   119: ldc 2131231040
            //   121: invokevirtual findViewById : (I)Landroid/view/View;
            //   124: checkcast android/widget/LinearLayout
            //   127: astore #12
            //   129: aload #4
            //   131: ldc 2131231041
            //   133: invokevirtual findViewById : (I)Landroid/view/View;
            //   136: checkcast android/widget/LinearLayout
            //   139: astore #13
            //   141: aload #4
            //   143: ldc 2131231042
            //   145: invokevirtual findViewById : (I)Landroid/view/View;
            //   148: checkcast android/widget/LinearLayout
            //   151: astore #14
            //   153: aload #4
            //   155: ldc 2131231044
            //   157: invokevirtual findViewById : (I)Landroid/view/View;
            //   160: checkcast android/widget/LinearLayout
            //   163: astore #15
            //   165: aload #4
            //   167: ldc 2131231074
            //   169: invokevirtual findViewById : (I)Landroid/view/View;
            //   172: checkcast android/widget/LinearLayout
            //   175: astore #16
            //   177: aload #4
            //   179: ldc 2131231075
            //   181: invokevirtual findViewById : (I)Landroid/view/View;
            //   184: checkcast android/widget/LinearLayout
            //   187: astore #17
            //   189: aload #4
            //   191: ldc 2131231076
            //   193: invokevirtual findViewById : (I)Landroid/view/View;
            //   196: checkcast android/widget/LinearLayout
            //   199: astore #18
            //   201: aload #4
            //   203: ldc 2131231077
            //   205: invokevirtual findViewById : (I)Landroid/view/View;
            //   208: checkcast android/widget/LinearLayout
            //   211: astore #19
            //   213: aload #4
            //   215: ldc 2131231078
            //   217: invokevirtual findViewById : (I)Landroid/view/View;
            //   220: checkcast android/widget/LinearLayout
            //   223: astore #20
            //   225: aload #4
            //   227: ldc 2131231079
            //   229: invokevirtual findViewById : (I)Landroid/view/View;
            //   232: checkcast android/widget/LinearLayout
            //   235: astore #21
            //   237: aload #4
            //   239: ldc 2131231035
            //   241: invokevirtual findViewById : (I)Landroid/view/View;
            //   244: checkcast android/widget/LinearLayout
            //   247: astore #22
            //   249: aload #4
            //   251: ldc 2131231036
            //   253: invokevirtual findViewById : (I)Landroid/view/View;
            //   256: checkcast android/widget/LinearLayout
            //   259: astore #23
            //   261: aload #4
            //   263: ldc 2131231426
            //   265: invokevirtual findViewById : (I)Landroid/view/View;
            //   268: checkcast android/widget/TextView
            //   271: astore #24
            //   273: aload #4
            //   275: ldc 2131231341
            //   277: invokevirtual findViewById : (I)Landroid/view/View;
            //   280: checkcast android/widget/TextView
            //   283: astore #25
            //   285: aload #4
            //   287: ldc 2131231478
            //   289: invokevirtual findViewById : (I)Landroid/view/View;
            //   292: checkcast android/widget/TextView
            //   295: astore #26
            //   297: aload #4
            //   299: ldc 2131231494
            //   301: invokevirtual findViewById : (I)Landroid/view/View;
            //   304: checkcast android/widget/TextView
            //   307: astore #27
            //   309: aload #4
            //   311: ldc 2131231425
            //   313: invokevirtual findViewById : (I)Landroid/view/View;
            //   316: checkcast android/widget/TextView
            //   319: astore #28
            //   321: aload #4
            //   323: ldc 2131231340
            //   325: invokevirtual findViewById : (I)Landroid/view/View;
            //   328: checkcast android/widget/TextView
            //   331: astore #29
            //   333: aload #4
            //   335: ldc 2131231477
            //   337: invokevirtual findViewById : (I)Landroid/view/View;
            //   340: checkcast android/widget/TextView
            //   343: astore #30
            //   345: aload #4
            //   347: ldc 2131231493
            //   349: invokevirtual findViewById : (I)Landroid/view/View;
            //   352: checkcast android/widget/TextView
            //   355: astore #31
            //   357: aload #4
            //   359: ldc 2131231429
            //   361: invokevirtual findViewById : (I)Landroid/view/View;
            //   364: checkcast android/widget/TextView
            //   367: astore #32
            //   369: aload #4
            //   371: ldc 2131231344
            //   373: invokevirtual findViewById : (I)Landroid/view/View;
            //   376: checkcast android/widget/TextView
            //   379: astore #33
            //   381: aload #4
            //   383: ldc 2131231481
            //   385: invokevirtual findViewById : (I)Landroid/view/View;
            //   388: checkcast android/widget/TextView
            //   391: astore #34
            //   393: aload #4
            //   395: ldc 2131231497
            //   397: invokevirtual findViewById : (I)Landroid/view/View;
            //   400: checkcast android/widget/TextView
            //   403: astore #35
            //   405: aload #4
            //   407: ldc 2131231427
            //   409: invokevirtual findViewById : (I)Landroid/view/View;
            //   412: checkcast android/widget/TextView
            //   415: astore #36
            //   417: aload #4
            //   419: ldc 2131231342
            //   421: invokevirtual findViewById : (I)Landroid/view/View;
            //   424: checkcast android/widget/TextView
            //   427: astore #37
            //   429: aload #4
            //   431: ldc 2131231479
            //   433: invokevirtual findViewById : (I)Landroid/view/View;
            //   436: checkcast android/widget/TextView
            //   439: astore #38
            //   441: aload #4
            //   443: ldc 2131231495
            //   445: invokevirtual findViewById : (I)Landroid/view/View;
            //   448: checkcast android/widget/TextView
            //   451: astore #39
            //   453: aload #4
            //   455: ldc 2131231428
            //   457: invokevirtual findViewById : (I)Landroid/view/View;
            //   460: checkcast android/widget/TextView
            //   463: astore #40
            //   465: aload #4
            //   467: ldc 2131231343
            //   469: invokevirtual findViewById : (I)Landroid/view/View;
            //   472: checkcast android/widget/TextView
            //   475: astore #41
            //   477: aload #4
            //   479: ldc 2131231480
            //   481: invokevirtual findViewById : (I)Landroid/view/View;
            //   484: checkcast android/widget/TextView
            //   487: astore #42
            //   489: aload #4
            //   491: ldc 2131231496
            //   493: invokevirtual findViewById : (I)Landroid/view/View;
            //   496: checkcast android/widget/TextView
            //   499: astore #43
            //   501: aload #4
            //   503: ldc 2131231454
            //   505: invokevirtual findViewById : (I)Landroid/view/View;
            //   508: checkcast android/widget/TextView
            //   511: astore #44
            //   513: aload #4
            //   515: ldc 2131231405
            //   517: invokevirtual findViewById : (I)Landroid/view/View;
            //   520: checkcast android/widget/TextView
            //   523: astore #45
            //   525: aload #4
            //   527: ldc 2131231487
            //   529: invokevirtual findViewById : (I)Landroid/view/View;
            //   532: checkcast android/widget/TextView
            //   535: astore #46
            //   537: aload #4
            //   539: ldc 2131231502
            //   541: invokevirtual findViewById : (I)Landroid/view/View;
            //   544: checkcast android/widget/TextView
            //   547: astore #47
            //   549: aload #4
            //   551: ldc 2131231455
            //   553: invokevirtual findViewById : (I)Landroid/view/View;
            //   556: checkcast android/widget/TextView
            //   559: astore #48
            //   561: aload #4
            //   563: ldc 2131231406
            //   565: invokevirtual findViewById : (I)Landroid/view/View;
            //   568: checkcast android/widget/TextView
            //   571: astore #49
            //   573: aload #4
            //   575: ldc 2131231488
            //   577: invokevirtual findViewById : (I)Landroid/view/View;
            //   580: checkcast android/widget/TextView
            //   583: astore #50
            //   585: aload #4
            //   587: ldc 2131231503
            //   589: invokevirtual findViewById : (I)Landroid/view/View;
            //   592: checkcast android/widget/TextView
            //   595: astore #51
            //   597: aload #4
            //   599: ldc 2131231457
            //   601: invokevirtual findViewById : (I)Landroid/view/View;
            //   604: checkcast android/widget/TextView
            //   607: astore #52
            //   609: aload #4
            //   611: ldc 2131231408
            //   613: invokevirtual findViewById : (I)Landroid/view/View;
            //   616: checkcast android/widget/TextView
            //   619: astore #53
            //   621: aload #4
            //   623: ldc 2131231515
            //   625: invokevirtual findViewById : (I)Landroid/view/View;
            //   628: checkcast android/widget/TextView
            //   631: astore #54
            //   633: aload #4
            //   635: ldc 2131231505
            //   637: invokevirtual findViewById : (I)Landroid/view/View;
            //   640: checkcast android/widget/TextView
            //   643: astore #55
            //   645: aload #4
            //   647: ldc 2131231458
            //   649: invokevirtual findViewById : (I)Landroid/view/View;
            //   652: checkcast android/widget/TextView
            //   655: astore #56
            //   657: aload #4
            //   659: ldc 2131231409
            //   661: invokevirtual findViewById : (I)Landroid/view/View;
            //   664: checkcast android/widget/TextView
            //   667: astore #57
            //   669: aload #4
            //   671: ldc 2131231516
            //   673: invokevirtual findViewById : (I)Landroid/view/View;
            //   676: checkcast android/widget/TextView
            //   679: astore #58
            //   681: aload #4
            //   683: ldc 2131231506
            //   685: invokevirtual findViewById : (I)Landroid/view/View;
            //   688: checkcast android/widget/TextView
            //   691: astore #59
            //   693: aload #4
            //   695: ldc 2131231459
            //   697: invokevirtual findViewById : (I)Landroid/view/View;
            //   700: checkcast android/widget/TextView
            //   703: astore #60
            //   705: aload #4
            //   707: ldc 2131231410
            //   709: invokevirtual findViewById : (I)Landroid/view/View;
            //   712: checkcast android/widget/TextView
            //   715: astore #61
            //   717: aload #4
            //   719: ldc 2131231517
            //   721: invokevirtual findViewById : (I)Landroid/view/View;
            //   724: checkcast android/widget/TextView
            //   727: astore #62
            //   729: aload #4
            //   731: ldc 2131231507
            //   733: invokevirtual findViewById : (I)Landroid/view/View;
            //   736: checkcast android/widget/TextView
            //   739: astore #63
            //   741: aload #4
            //   743: ldc 2131231460
            //   745: invokevirtual findViewById : (I)Landroid/view/View;
            //   748: checkcast android/widget/TextView
            //   751: astore #64
            //   753: aload #4
            //   755: ldc 2131231411
            //   757: invokevirtual findViewById : (I)Landroid/view/View;
            //   760: checkcast android/widget/TextView
            //   763: astore #65
            //   765: aload #4
            //   767: ldc 2131231518
            //   769: invokevirtual findViewById : (I)Landroid/view/View;
            //   772: checkcast android/widget/TextView
            //   775: astore #66
            //   777: aload #4
            //   779: ldc 2131231508
            //   781: invokevirtual findViewById : (I)Landroid/view/View;
            //   784: checkcast android/widget/TextView
            //   787: astore #67
            //   789: aload #4
            //   791: ldc 2131231461
            //   793: invokevirtual findViewById : (I)Landroid/view/View;
            //   796: checkcast android/widget/TextView
            //   799: astore #68
            //   801: aload #4
            //   803: ldc 2131231412
            //   805: invokevirtual findViewById : (I)Landroid/view/View;
            //   808: checkcast android/widget/TextView
            //   811: astore #69
            //   813: aload #4
            //   815: ldc 2131231519
            //   817: invokevirtual findViewById : (I)Landroid/view/View;
            //   820: checkcast android/widget/TextView
            //   823: astore #70
            //   825: aload #4
            //   827: ldc 2131231509
            //   829: invokevirtual findViewById : (I)Landroid/view/View;
            //   832: checkcast android/widget/TextView
            //   835: astore #71
            //   837: aload #4
            //   839: ldc 2131231462
            //   841: invokevirtual findViewById : (I)Landroid/view/View;
            //   844: checkcast android/widget/TextView
            //   847: astore #72
            //   849: aload #4
            //   851: ldc 2131231413
            //   853: invokevirtual findViewById : (I)Landroid/view/View;
            //   856: checkcast android/widget/TextView
            //   859: astore #73
            //   861: aload #4
            //   863: ldc 2131231520
            //   865: invokevirtual findViewById : (I)Landroid/view/View;
            //   868: checkcast android/widget/TextView
            //   871: astore #74
            //   873: aload #4
            //   875: ldc 2131231510
            //   877: invokevirtual findViewById : (I)Landroid/view/View;
            //   880: checkcast android/widget/TextView
            //   883: astore #75
            //   885: aload #4
            //   887: ldc 2131231463
            //   889: invokevirtual findViewById : (I)Landroid/view/View;
            //   892: checkcast android/widget/TextView
            //   895: astore #76
            //   897: aload #4
            //   899: ldc 2131231414
            //   901: invokevirtual findViewById : (I)Landroid/view/View;
            //   904: checkcast android/widget/TextView
            //   907: astore #77
            //   909: aload #4
            //   911: ldc 2131231521
            //   913: invokevirtual findViewById : (I)Landroid/view/View;
            //   916: checkcast android/widget/TextView
            //   919: astore #78
            //   921: aload #4
            //   923: ldc 2131231511
            //   925: invokevirtual findViewById : (I)Landroid/view/View;
            //   928: checkcast android/widget/TextView
            //   931: astore #79
            //   933: aload #4
            //   935: ldc 2131231447
            //   937: invokevirtual findViewById : (I)Landroid/view/View;
            //   940: checkcast android/widget/TextView
            //   943: astore #80
            //   945: aload #4
            //   947: ldc 2131231398
            //   949: invokevirtual findViewById : (I)Landroid/view/View;
            //   952: checkcast android/widget/TextView
            //   955: astore #81
            //   957: aload #4
            //   959: ldc 2131231475
            //   961: invokevirtual findViewById : (I)Landroid/view/View;
            //   964: checkcast android/widget/TextView
            //   967: astore #82
            //   969: aload #4
            //   971: ldc 2131231491
            //   973: invokevirtual findViewById : (I)Landroid/view/View;
            //   976: checkcast android/widget/TextView
            //   979: astore #83
            //   981: aload #4
            //   983: ldc 2131231448
            //   985: invokevirtual findViewById : (I)Landroid/view/View;
            //   988: checkcast android/widget/TextView
            //   991: astore #84
            //   993: aload #4
            //   995: ldc 2131231399
            //   997: invokevirtual findViewById : (I)Landroid/view/View;
            //   1000: checkcast android/widget/TextView
            //   1003: astore #85
            //   1005: aload #4
            //   1007: ldc 2131231476
            //   1009: invokevirtual findViewById : (I)Landroid/view/View;
            //   1012: checkcast android/widget/TextView
            //   1015: astore #86
            //   1017: aload #4
            //   1019: ldc 2131231492
            //   1021: invokevirtual findViewById : (I)Landroid/view/View;
            //   1024: checkcast android/widget/TextView
            //   1027: astore #87
            //   1029: aload_0
            //   1030: getfield this$0 : Ltamhoang/ldpro4/Fragment/Frag_NoRP3;
            //   1033: getfield jsonValues : Ljava/util/List;
            //   1036: iload_1
            //   1037: invokeinterface get : (I)Ljava/lang/Object;
            //   1042: checkcast org/json/JSONObject
            //   1045: astore #88
            //   1047: aload #9
            //   1049: aload #88
            //   1051: ldc 'tin_goc'
            //   1053: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1056: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1059: aload #6
            //   1061: aload #88
            //   1063: ldc 'ten_KH'
            //   1065: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1068: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1071: aload #88
            //   1073: ldc 'type_kh'
            //   1075: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1078: astore #9
            //   1080: aload #9
            //   1082: ldc '2'
            //   1084: invokevirtual indexOf : (Ljava/lang/String;)I
            //   1087: istore_1
            //   1088: iload_1
            //   1089: iconst_m1
            //   1090: if_icmple -> 1110
            //   1093: aload #6
            //   1095: ldc '#1a40ea'
            //   1097: invokestatic parseColor : (Ljava/lang/String;)I
            //   1100: invokevirtual setTextColor : (I)V
            //   1103: goto -> 1110
            //   1106: astore_2
            //   1107: goto -> 3972
            //   1110: aload #8
            //   1112: aload #88
            //   1114: ldc 'so_tinnhan'
            //   1116: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1119: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1122: aload #7
            //   1124: aload #88
            //   1126: ldc 'gio_nhan'
            //   1128: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1131: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1134: aload #5
            //   1136: aload #88
            //   1138: ldc 'tong_tien'
            //   1140: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1143: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1146: aload_2
            //   1147: aload #88
            //   1149: ldc 'ket_qua'
            //   1151: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1154: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1157: aload #88
            //   1159: ldc 'nd_phantich'
            //   1161: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1164: astore #8
            //   1166: new android/text/SpannableString
            //   1169: astore_2
            //   1170: aload_2
            //   1171: aload #8
            //   1173: invokespecial <init> : (Ljava/lang/CharSequence;)V
            //   1176: iconst_0
            //   1177: istore_1
            //   1178: aload #7
            //   1180: astore_3
            //   1181: aload #8
            //   1183: astore #7
            //   1185: aload #7
            //   1187: invokevirtual length : ()I
            //   1190: istore #89
            //   1192: iload_1
            //   1193: iload #89
            //   1195: iconst_1
            //   1196: isub
            //   1197: if_icmpge -> 1392
            //   1200: aload #7
            //   1202: iload_1
            //   1203: iload_1
            //   1204: iconst_2
            //   1205: iadd
            //   1206: invokevirtual substring : (II)Ljava/lang/String;
            //   1209: astore #9
            //   1211: aload_3
            //   1212: astore #8
            //   1214: aload #9
            //   1216: ldc '*'
            //   1218: invokevirtual indexOf : (Ljava/lang/String;)I
            //   1221: iconst_m1
            //   1222: if_icmple -> 1342
            //   1225: iload_1
            //   1226: istore #89
            //   1228: iload #89
            //   1230: ifle -> 1329
            //   1233: aload_3
            //   1234: astore #8
            //   1236: aload #7
            //   1238: iload #89
            //   1240: iload #89
            //   1242: iconst_1
            //   1243: iadd
            //   1244: invokevirtual substring : (II)Ljava/lang/String;
            //   1247: astore #8
            //   1249: aload #8
            //   1251: ldc ','
            //   1253: invokevirtual indexOf : (Ljava/lang/String;)I
            //   1256: iconst_m1
            //   1257: if_icmpgt -> 1316
            //   1260: aload #7
            //   1262: iload #89
            //   1264: iload #89
            //   1266: iconst_1
            //   1267: iadd
            //   1268: invokevirtual substring : (II)Ljava/lang/String;
            //   1271: ldc ':'
            //   1273: invokevirtual indexOf : (Ljava/lang/String;)I
            //   1276: iconst_m1
            //   1277: if_icmple -> 1283
            //   1280: goto -> 1329
            //   1283: new android/text/style/ForegroundColorSpan
            //   1286: astore #8
            //   1288: aload #8
            //   1290: ldc -65536
            //   1292: invokespecial <init> : (I)V
            //   1295: aload_2
            //   1296: aload #8
            //   1298: iload #89
            //   1300: iload_1
            //   1301: iconst_1
            //   1302: iadd
            //   1303: bipush #33
            //   1305: invokeinterface setSpan : (Ljava/lang/Object;III)V
            //   1310: iinc #89, -1
            //   1313: goto -> 1228
            //   1316: goto -> 1329
            //   1319: astore #7
            //   1321: aload_2
            //   1322: astore_3
            //   1323: aload #7
            //   1325: astore_2
            //   1326: goto -> 3972
            //   1329: aload #7
            //   1331: astore #8
            //   1333: aload_3
            //   1334: astore #7
            //   1336: aload #8
            //   1338: astore_3
            //   1339: goto -> 1352
            //   1342: aload_3
            //   1343: astore #8
            //   1345: aload #7
            //   1347: astore_3
            //   1348: aload #8
            //   1350: astore #7
            //   1352: iinc #1, 1
            //   1355: aload #7
            //   1357: astore #8
            //   1359: aload_3
            //   1360: astore #7
            //   1362: aload #8
            //   1364: astore_3
            //   1365: goto -> 1185
            //   1368: astore_3
            //   1369: aload_2
            //   1370: astore #7
            //   1372: aload_3
            //   1373: astore_2
            //   1374: aload #7
            //   1376: astore_3
            //   1377: goto -> 3972
            //   1380: astore_3
            //   1381: aload_2
            //   1382: astore #7
            //   1384: aload_3
            //   1385: astore_2
            //   1386: aload #7
            //   1388: astore_3
            //   1389: goto -> 3972
            //   1392: aload #10
            //   1394: aload_2
            //   1395: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1398: aload #88
            //   1400: ldc 'dea'
            //   1402: invokevirtual has : (Ljava/lang/String;)Z
            //   1405: istore #90
            //   1407: iload #90
            //   1409: ifeq -> 1502
            //   1412: aload #11
            //   1414: iconst_0
            //   1415: invokevirtual setVisibility : (I)V
            //   1418: new org/json/JSONObject
            //   1421: astore_3
            //   1422: aload_3
            //   1423: aload #88
            //   1425: ldc 'dea'
            //   1427: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1430: invokespecial <init> : (Ljava/lang/String;)V
            //   1433: aload #24
            //   1435: aload_3
            //   1436: ldc 'diem'
            //   1438: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1441: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1444: aload #25
            //   1446: aload_3
            //   1447: ldc 'diem_an'
            //   1449: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1452: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1455: aload #26
            //   1457: aload_3
            //   1458: ldc 'tong_tien'
            //   1460: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1463: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1466: aload_3
            //   1467: ldc 'ket_qua'
            //   1469: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1472: astore_3
            //   1473: aload #27
            //   1475: aload_3
            //   1476: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1479: goto -> 1502
            //   1482: astore #7
            //   1484: aload_2
            //   1485: astore_3
            //   1486: aload #7
            //   1488: astore_2
            //   1489: goto -> 3972
            //   1492: astore #7
            //   1494: aload_2
            //   1495: astore_3
            //   1496: aload #7
            //   1498: astore_2
            //   1499: goto -> 3972
            //   1502: aload #88
            //   1504: ldc 'deb'
            //   1506: invokevirtual has : (Ljava/lang/String;)Z
            //   1509: istore #90
            //   1511: iload #90
            //   1513: ifeq -> 1652
            //   1516: new org/json/JSONObject
            //   1519: astore_3
            //   1520: aload_3
            //   1521: aload #88
            //   1523: ldc 'deb'
            //   1525: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1528: invokespecial <init> : (Ljava/lang/String;)V
            //   1531: aload_3
            //   1532: ldc 'diem'
            //   1534: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1537: astore #7
            //   1539: aload #28
            //   1541: aload #7
            //   1543: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1546: aload_3
            //   1547: ldc 'diem_an'
            //   1549: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1552: astore #7
            //   1554: aload #29
            //   1556: aload #7
            //   1558: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1561: aload_3
            //   1562: ldc 'tong_tien'
            //   1564: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1567: astore #7
            //   1569: aload #30
            //   1571: aload #7
            //   1573: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1576: aload_3
            //   1577: ldc 'ket_qua'
            //   1579: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1582: astore_3
            //   1583: aload #31
            //   1585: aload_3
            //   1586: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1589: goto -> 1652
            //   1592: astore_3
            //   1593: aload_2
            //   1594: astore #7
            //   1596: aload_3
            //   1597: astore_2
            //   1598: aload #7
            //   1600: astore_3
            //   1601: goto -> 3972
            //   1604: astore_3
            //   1605: aload_2
            //   1606: astore #7
            //   1608: aload_3
            //   1609: astore_2
            //   1610: aload #7
            //   1612: astore_3
            //   1613: goto -> 3972
            //   1616: astore_3
            //   1617: aload_2
            //   1618: astore #7
            //   1620: aload_3
            //   1621: astore_2
            //   1622: aload #7
            //   1624: astore_3
            //   1625: goto -> 3972
            //   1628: astore_3
            //   1629: aload_2
            //   1630: astore #7
            //   1632: aload_3
            //   1633: astore_2
            //   1634: aload #7
            //   1636: astore_3
            //   1637: goto -> 3972
            //   1640: astore_3
            //   1641: aload_2
            //   1642: astore #7
            //   1644: aload_3
            //   1645: astore_2
            //   1646: aload #7
            //   1648: astore_3
            //   1649: goto -> 3972
            //   1652: aload #88
            //   1654: ldc 'det'
            //   1656: invokevirtual has : (Ljava/lang/String;)Z
            //   1659: istore #90
            //   1661: iload #90
            //   1663: ifeq -> 1802
            //   1666: aload #14
            //   1668: iconst_0
            //   1669: invokevirtual setVisibility : (I)V
            //   1672: new org/json/JSONObject
            //   1675: astore_3
            //   1676: aload_3
            //   1677: aload #88
            //   1679: ldc 'det'
            //   1681: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1684: invokespecial <init> : (Ljava/lang/String;)V
            //   1687: aload_3
            //   1688: ldc 'diem'
            //   1690: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1693: astore #7
            //   1695: aload #32
            //   1697: aload #7
            //   1699: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1702: aload_3
            //   1703: ldc 'diem_an'
            //   1705: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1708: astore #7
            //   1710: aload #33
            //   1712: aload #7
            //   1714: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1717: aload_3
            //   1718: ldc 'tong_tien'
            //   1720: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1723: astore #7
            //   1725: aload #34
            //   1727: aload #7
            //   1729: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1732: aload_3
            //   1733: ldc 'ket_qua'
            //   1735: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1738: astore_3
            //   1739: aload #35
            //   1741: aload_3
            //   1742: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1745: goto -> 1802
            //   1748: astore #7
            //   1750: aload_2
            //   1751: astore_3
            //   1752: aload #7
            //   1754: astore_2
            //   1755: goto -> 3972
            //   1758: astore_3
            //   1759: aload_2
            //   1760: astore #7
            //   1762: aload_3
            //   1763: astore_2
            //   1764: aload #7
            //   1766: astore_3
            //   1767: goto -> 3972
            //   1770: astore #7
            //   1772: aload_2
            //   1773: astore_3
            //   1774: aload #7
            //   1776: astore_2
            //   1777: goto -> 3972
            //   1780: astore_3
            //   1781: aload_2
            //   1782: astore #7
            //   1784: aload_3
            //   1785: astore_2
            //   1786: aload #7
            //   1788: astore_3
            //   1789: goto -> 3972
            //   1792: astore #7
            //   1794: aload_2
            //   1795: astore_3
            //   1796: aload #7
            //   1798: astore_2
            //   1799: goto -> 3972
            //   1802: aload #88
            //   1804: ldc 'dec'
            //   1806: invokevirtual has : (Ljava/lang/String;)Z
            //   1809: istore #90
            //   1811: iload #90
            //   1813: ifeq -> 1950
            //   1816: aload #12
            //   1818: iconst_0
            //   1819: invokevirtual setVisibility : (I)V
            //   1822: new org/json/JSONObject
            //   1825: astore_3
            //   1826: aload_3
            //   1827: aload #88
            //   1829: ldc 'dec'
            //   1831: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1834: invokespecial <init> : (Ljava/lang/String;)V
            //   1837: aload_3
            //   1838: ldc 'diem'
            //   1840: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1843: astore #7
            //   1845: aload #36
            //   1847: aload #7
            //   1849: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1852: aload_3
            //   1853: ldc 'diem_an'
            //   1855: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1858: astore #7
            //   1860: aload #37
            //   1862: aload #7
            //   1864: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1867: aload_3
            //   1868: ldc 'tong_tien'
            //   1870: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1873: astore #7
            //   1875: aload #38
            //   1877: aload #7
            //   1879: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1882: aload_3
            //   1883: ldc 'ket_qua'
            //   1885: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1888: astore_3
            //   1889: aload #39
            //   1891: aload_3
            //   1892: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   1895: goto -> 1950
            //   1898: astore_3
            //   1899: aload_2
            //   1900: astore #7
            //   1902: aload_3
            //   1903: astore_2
            //   1904: aload #7
            //   1906: astore_3
            //   1907: goto -> 3972
            //   1910: astore #7
            //   1912: aload_2
            //   1913: astore_3
            //   1914: aload #7
            //   1916: astore_2
            //   1917: goto -> 3972
            //   1920: astore #7
            //   1922: aload_2
            //   1923: astore_3
            //   1924: aload #7
            //   1926: astore_2
            //   1927: goto -> 3972
            //   1930: astore #7
            //   1932: aload_2
            //   1933: astore_3
            //   1934: aload #7
            //   1936: astore_2
            //   1937: goto -> 3972
            //   1940: astore #7
            //   1942: aload_2
            //   1943: astore_3
            //   1944: aload #7
            //   1946: astore_2
            //   1947: goto -> 3972
            //   1950: aload #88
            //   1952: ldc 'ded'
            //   1954: invokevirtual has : (Ljava/lang/String;)Z
            //   1957: istore #90
            //   1959: iload #90
            //   1961: ifeq -> 2102
            //   1964: aload #13
            //   1966: iconst_0
            //   1967: invokevirtual setVisibility : (I)V
            //   1970: new org/json/JSONObject
            //   1973: astore_3
            //   1974: aload_3
            //   1975: aload #88
            //   1977: ldc 'ded'
            //   1979: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1982: invokespecial <init> : (Ljava/lang/String;)V
            //   1985: aload_3
            //   1986: ldc 'diem'
            //   1988: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   1991: astore #7
            //   1993: aload #40
            //   1995: aload #7
            //   1997: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2000: aload_3
            //   2001: ldc 'diem_an'
            //   2003: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2006: astore #7
            //   2008: aload #41
            //   2010: aload #7
            //   2012: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2015: aload_3
            //   2016: ldc 'tong_tien'
            //   2018: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2021: astore #7
            //   2023: aload #42
            //   2025: aload #7
            //   2027: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2030: aload_3
            //   2031: ldc 'ket_qua'
            //   2033: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2036: astore_3
            //   2037: aload #43
            //   2039: aload_3
            //   2040: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2043: goto -> 2102
            //   2046: astore_3
            //   2047: aload_2
            //   2048: astore #7
            //   2050: aload_3
            //   2051: astore_2
            //   2052: aload #7
            //   2054: astore_3
            //   2055: goto -> 3972
            //   2058: astore #7
            //   2060: aload_2
            //   2061: astore_3
            //   2062: aload #7
            //   2064: astore_2
            //   2065: goto -> 3972
            //   2068: astore_3
            //   2069: aload_2
            //   2070: astore #7
            //   2072: aload_3
            //   2073: astore_2
            //   2074: aload #7
            //   2076: astore_3
            //   2077: goto -> 3972
            //   2080: astore #7
            //   2082: aload_2
            //   2083: astore_3
            //   2084: aload #7
            //   2086: astore_2
            //   2087: goto -> 3972
            //   2090: astore_3
            //   2091: aload_2
            //   2092: astore #7
            //   2094: aload_3
            //   2095: astore_2
            //   2096: aload #7
            //   2098: astore_3
            //   2099: goto -> 3972
            //   2102: aload #88
            //   2104: ldc 'lo'
            //   2106: invokevirtual has : (Ljava/lang/String;)Z
            //   2109: istore #90
            //   2111: iload #90
            //   2113: ifeq -> 2252
            //   2116: new org/json/JSONObject
            //   2119: astore_3
            //   2120: aload_3
            //   2121: aload #88
            //   2123: ldc 'lo'
            //   2125: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2128: invokespecial <init> : (Ljava/lang/String;)V
            //   2131: aload_3
            //   2132: ldc 'diem'
            //   2134: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2137: astore #7
            //   2139: aload #44
            //   2141: aload #7
            //   2143: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2146: aload_3
            //   2147: ldc 'diem_an'
            //   2149: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2152: astore #7
            //   2154: aload #45
            //   2156: aload #7
            //   2158: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2161: aload_3
            //   2162: ldc 'tong_tien'
            //   2164: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2167: astore #7
            //   2169: aload #46
            //   2171: aload #7
            //   2173: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2176: aload_3
            //   2177: ldc 'ket_qua'
            //   2179: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2182: astore_3
            //   2183: aload #47
            //   2185: aload_3
            //   2186: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2189: goto -> 2252
            //   2192: astore_3
            //   2193: aload_2
            //   2194: astore #7
            //   2196: aload_3
            //   2197: astore_2
            //   2198: aload #7
            //   2200: astore_3
            //   2201: goto -> 3972
            //   2204: astore_3
            //   2205: aload_2
            //   2206: astore #7
            //   2208: aload_3
            //   2209: astore_2
            //   2210: aload #7
            //   2212: astore_3
            //   2213: goto -> 3972
            //   2216: astore_3
            //   2217: aload_2
            //   2218: astore #7
            //   2220: aload_3
            //   2221: astore_2
            //   2222: aload #7
            //   2224: astore_3
            //   2225: goto -> 3972
            //   2228: astore_3
            //   2229: aload_2
            //   2230: astore #7
            //   2232: aload_3
            //   2233: astore_2
            //   2234: aload #7
            //   2236: astore_3
            //   2237: goto -> 3972
            //   2240: astore_3
            //   2241: aload_2
            //   2242: astore #7
            //   2244: aload_3
            //   2245: astore_2
            //   2246: aload #7
            //   2248: astore_3
            //   2249: goto -> 3972
            //   2252: aload #88
            //   2254: ldc 'loa'
            //   2256: invokevirtual has : (Ljava/lang/String;)Z
            //   2259: istore #90
            //   2261: iload #90
            //   2263: ifeq -> 2402
            //   2266: aload #15
            //   2268: iconst_0
            //   2269: invokevirtual setVisibility : (I)V
            //   2272: new org/json/JSONObject
            //   2275: astore_3
            //   2276: aload_3
            //   2277: aload #88
            //   2279: ldc 'loa'
            //   2281: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2284: invokespecial <init> : (Ljava/lang/String;)V
            //   2287: aload_3
            //   2288: ldc 'diem'
            //   2290: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2293: astore #7
            //   2295: aload #48
            //   2297: aload #7
            //   2299: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2302: aload_3
            //   2303: ldc 'diem_an'
            //   2305: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2308: astore #7
            //   2310: aload #49
            //   2312: aload #7
            //   2314: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2317: aload_3
            //   2318: ldc 'tong_tien'
            //   2320: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2323: astore #7
            //   2325: aload #50
            //   2327: aload #7
            //   2329: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2332: aload_3
            //   2333: ldc 'ket_qua'
            //   2335: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2338: astore_3
            //   2339: aload #51
            //   2341: aload_3
            //   2342: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2345: goto -> 2402
            //   2348: astore #7
            //   2350: aload_2
            //   2351: astore_3
            //   2352: aload #7
            //   2354: astore_2
            //   2355: goto -> 3972
            //   2358: astore_3
            //   2359: aload_2
            //   2360: astore #7
            //   2362: aload_3
            //   2363: astore_2
            //   2364: aload #7
            //   2366: astore_3
            //   2367: goto -> 3972
            //   2370: astore #7
            //   2372: aload_2
            //   2373: astore_3
            //   2374: aload #7
            //   2376: astore_2
            //   2377: goto -> 3972
            //   2380: astore_3
            //   2381: aload_2
            //   2382: astore #7
            //   2384: aload_3
            //   2385: astore_2
            //   2386: aload #7
            //   2388: astore_3
            //   2389: goto -> 3972
            //   2392: astore #7
            //   2394: aload_2
            //   2395: astore_3
            //   2396: aload #7
            //   2398: astore_2
            //   2399: goto -> 3972
            //   2402: aload #88
            //   2404: ldc 'xi2'
            //   2406: invokevirtual has : (Ljava/lang/String;)Z
            //   2409: istore #90
            //   2411: iload #90
            //   2413: ifeq -> 2550
            //   2416: aload #16
            //   2418: iconst_0
            //   2419: invokevirtual setVisibility : (I)V
            //   2422: new org/json/JSONObject
            //   2425: astore_3
            //   2426: aload_3
            //   2427: aload #88
            //   2429: ldc 'xi2'
            //   2431: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2434: invokespecial <init> : (Ljava/lang/String;)V
            //   2437: aload_3
            //   2438: ldc 'diem'
            //   2440: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2443: astore #7
            //   2445: aload #52
            //   2447: aload #7
            //   2449: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2452: aload_3
            //   2453: ldc 'diem_an'
            //   2455: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2458: astore #7
            //   2460: aload #53
            //   2462: aload #7
            //   2464: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2467: aload_3
            //   2468: ldc 'tong_tien'
            //   2470: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2473: astore #7
            //   2475: aload #54
            //   2477: aload #7
            //   2479: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2482: aload_3
            //   2483: ldc 'ket_qua'
            //   2485: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2488: astore_3
            //   2489: aload #55
            //   2491: aload_3
            //   2492: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2495: goto -> 2550
            //   2498: astore_3
            //   2499: aload_2
            //   2500: astore #7
            //   2502: aload_3
            //   2503: astore_2
            //   2504: aload #7
            //   2506: astore_3
            //   2507: goto -> 3972
            //   2510: astore #7
            //   2512: aload_2
            //   2513: astore_3
            //   2514: aload #7
            //   2516: astore_2
            //   2517: goto -> 3972
            //   2520: astore #7
            //   2522: aload_2
            //   2523: astore_3
            //   2524: aload #7
            //   2526: astore_2
            //   2527: goto -> 3972
            //   2530: astore #7
            //   2532: aload_2
            //   2533: astore_3
            //   2534: aload #7
            //   2536: astore_2
            //   2537: goto -> 3972
            //   2540: astore #7
            //   2542: aload_2
            //   2543: astore_3
            //   2544: aload #7
            //   2546: astore_2
            //   2547: goto -> 3972
            //   2550: aload #88
            //   2552: ldc 'xi3'
            //   2554: invokevirtual has : (Ljava/lang/String;)Z
            //   2557: istore #90
            //   2559: iload #90
            //   2561: ifeq -> 2702
            //   2564: aload #17
            //   2566: iconst_0
            //   2567: invokevirtual setVisibility : (I)V
            //   2570: new org/json/JSONObject
            //   2573: astore_3
            //   2574: aload_3
            //   2575: aload #88
            //   2577: ldc 'xi3'
            //   2579: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2582: invokespecial <init> : (Ljava/lang/String;)V
            //   2585: aload_3
            //   2586: ldc 'diem'
            //   2588: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2591: astore #7
            //   2593: aload #56
            //   2595: aload #7
            //   2597: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2600: aload_3
            //   2601: ldc 'diem_an'
            //   2603: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2606: astore #7
            //   2608: aload #57
            //   2610: aload #7
            //   2612: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2615: aload_3
            //   2616: ldc 'tong_tien'
            //   2618: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2621: astore #7
            //   2623: aload #58
            //   2625: aload #7
            //   2627: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2630: aload_3
            //   2631: ldc 'ket_qua'
            //   2633: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2636: astore_3
            //   2637: aload #59
            //   2639: aload_3
            //   2640: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2643: goto -> 2702
            //   2646: astore_3
            //   2647: aload_2
            //   2648: astore #7
            //   2650: aload_3
            //   2651: astore_2
            //   2652: aload #7
            //   2654: astore_3
            //   2655: goto -> 3972
            //   2658: astore_3
            //   2659: aload_2
            //   2660: astore #7
            //   2662: aload_3
            //   2663: astore_2
            //   2664: aload #7
            //   2666: astore_3
            //   2667: goto -> 3972
            //   2670: astore #7
            //   2672: aload_2
            //   2673: astore_3
            //   2674: aload #7
            //   2676: astore_2
            //   2677: goto -> 3972
            //   2680: astore_3
            //   2681: aload_2
            //   2682: astore #7
            //   2684: aload_3
            //   2685: astore_2
            //   2686: aload #7
            //   2688: astore_3
            //   2689: goto -> 3972
            //   2692: astore #7
            //   2694: aload_2
            //   2695: astore_3
            //   2696: aload #7
            //   2698: astore_2
            //   2699: goto -> 3972
            //   2702: aload #88
            //   2704: ldc 'xi4'
            //   2706: invokevirtual has : (Ljava/lang/String;)Z
            //   2709: istore #90
            //   2711: iload #90
            //   2713: ifeq -> 2854
            //   2716: aload #18
            //   2718: iconst_0
            //   2719: invokevirtual setVisibility : (I)V
            //   2722: new org/json/JSONObject
            //   2725: astore_3
            //   2726: aload_3
            //   2727: aload #88
            //   2729: ldc 'xi4'
            //   2731: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2734: invokespecial <init> : (Ljava/lang/String;)V
            //   2737: aload_3
            //   2738: ldc 'diem'
            //   2740: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2743: astore #7
            //   2745: aload #60
            //   2747: aload #7
            //   2749: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2752: aload_3
            //   2753: ldc 'diem_an'
            //   2755: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2758: astore #7
            //   2760: aload #61
            //   2762: aload #7
            //   2764: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2767: aload_3
            //   2768: ldc 'tong_tien'
            //   2770: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2773: astore #7
            //   2775: aload #62
            //   2777: aload #7
            //   2779: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2782: aload_3
            //   2783: ldc 'ket_qua'
            //   2785: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2788: astore_3
            //   2789: aload #63
            //   2791: aload_3
            //   2792: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2795: goto -> 2854
            //   2798: astore_3
            //   2799: aload_2
            //   2800: astore #7
            //   2802: aload_3
            //   2803: astore_2
            //   2804: aload #7
            //   2806: astore_3
            //   2807: goto -> 3972
            //   2810: astore #7
            //   2812: aload_2
            //   2813: astore_3
            //   2814: aload #7
            //   2816: astore_2
            //   2817: goto -> 3972
            //   2820: astore_3
            //   2821: aload_2
            //   2822: astore #7
            //   2824: aload_3
            //   2825: astore_2
            //   2826: aload #7
            //   2828: astore_3
            //   2829: goto -> 3972
            //   2832: astore #7
            //   2834: aload_2
            //   2835: astore_3
            //   2836: aload #7
            //   2838: astore_2
            //   2839: goto -> 3972
            //   2842: astore_3
            //   2843: aload_2
            //   2844: astore #7
            //   2846: aload_3
            //   2847: astore_2
            //   2848: aload #7
            //   2850: astore_3
            //   2851: goto -> 3972
            //   2854: aload #88
            //   2856: ldc_w 'xia2'
            //   2859: invokevirtual has : (Ljava/lang/String;)Z
            //   2862: istore #90
            //   2864: iload #90
            //   2866: ifeq -> 3004
            //   2869: aload #19
            //   2871: iconst_0
            //   2872: invokevirtual setVisibility : (I)V
            //   2875: new org/json/JSONObject
            //   2878: astore_3
            //   2879: aload_3
            //   2880: aload #88
            //   2882: ldc_w 'xia2'
            //   2885: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2888: invokespecial <init> : (Ljava/lang/String;)V
            //   2891: aload_3
            //   2892: ldc 'diem'
            //   2894: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2897: astore #7
            //   2899: aload #64
            //   2901: aload #7
            //   2903: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2906: aload_3
            //   2907: ldc 'diem_an'
            //   2909: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2912: astore #7
            //   2914: aload #65
            //   2916: aload #7
            //   2918: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2921: aload_3
            //   2922: ldc 'tong_tien'
            //   2924: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2927: astore #7
            //   2929: aload #66
            //   2931: aload #7
            //   2933: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2936: aload_3
            //   2937: ldc 'ket_qua'
            //   2939: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   2942: astore_3
            //   2943: aload #67
            //   2945: aload_3
            //   2946: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   2949: goto -> 3004
            //   2952: astore_3
            //   2953: aload_2
            //   2954: astore #7
            //   2956: aload_3
            //   2957: astore_2
            //   2958: aload #7
            //   2960: astore_3
            //   2961: goto -> 3972
            //   2964: astore #7
            //   2966: aload_2
            //   2967: astore_3
            //   2968: aload #7
            //   2970: astore_2
            //   2971: goto -> 3972
            //   2974: astore #7
            //   2976: aload_2
            //   2977: astore_3
            //   2978: aload #7
            //   2980: astore_2
            //   2981: goto -> 3972
            //   2984: astore #7
            //   2986: aload_2
            //   2987: astore_3
            //   2988: aload #7
            //   2990: astore_2
            //   2991: goto -> 3972
            //   2994: astore #7
            //   2996: aload_2
            //   2997: astore_3
            //   2998: aload #7
            //   3000: astore_2
            //   3001: goto -> 3972
            //   3004: aload #88
            //   3006: ldc_w 'xia3'
            //   3009: invokevirtual has : (Ljava/lang/String;)Z
            //   3012: istore #90
            //   3014: iload #90
            //   3016: ifeq -> 3158
            //   3019: aload #20
            //   3021: iconst_0
            //   3022: invokevirtual setVisibility : (I)V
            //   3025: new org/json/JSONObject
            //   3028: astore_3
            //   3029: aload_3
            //   3030: aload #88
            //   3032: ldc_w 'xia3'
            //   3035: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3038: invokespecial <init> : (Ljava/lang/String;)V
            //   3041: aload_3
            //   3042: ldc 'diem'
            //   3044: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3047: astore #7
            //   3049: aload #68
            //   3051: aload #7
            //   3053: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3056: aload_3
            //   3057: ldc 'diem_an'
            //   3059: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3062: astore #7
            //   3064: aload #69
            //   3066: aload #7
            //   3068: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3071: aload_3
            //   3072: ldc 'tong_tien'
            //   3074: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3077: astore #7
            //   3079: aload #70
            //   3081: aload #7
            //   3083: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3086: aload_3
            //   3087: ldc 'ket_qua'
            //   3089: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3092: astore_3
            //   3093: aload #71
            //   3095: aload_3
            //   3096: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3099: goto -> 3158
            //   3102: astore #7
            //   3104: aload_2
            //   3105: astore_3
            //   3106: aload #7
            //   3108: astore_2
            //   3109: goto -> 3972
            //   3112: astore #7
            //   3114: aload_2
            //   3115: astore_3
            //   3116: aload #7
            //   3118: astore_2
            //   3119: goto -> 3972
            //   3122: astore_3
            //   3123: aload_2
            //   3124: astore #7
            //   3126: aload_3
            //   3127: astore_2
            //   3128: aload #7
            //   3130: astore_3
            //   3131: goto -> 3972
            //   3134: astore_3
            //   3135: aload_2
            //   3136: astore #7
            //   3138: aload_3
            //   3139: astore_2
            //   3140: aload #7
            //   3142: astore_3
            //   3143: goto -> 3972
            //   3146: astore_3
            //   3147: aload_2
            //   3148: astore #7
            //   3150: aload_3
            //   3151: astore_2
            //   3152: aload #7
            //   3154: astore_3
            //   3155: goto -> 3972
            //   3158: aload #88
            //   3160: ldc_w 'xia4'
            //   3163: invokevirtual has : (Ljava/lang/String;)Z
            //   3166: istore #90
            //   3168: iload #90
            //   3170: ifeq -> 3312
            //   3173: aload #21
            //   3175: iconst_0
            //   3176: invokevirtual setVisibility : (I)V
            //   3179: new org/json/JSONObject
            //   3182: astore_3
            //   3183: aload_3
            //   3184: aload #88
            //   3186: ldc_w 'xia4'
            //   3189: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3192: invokespecial <init> : (Ljava/lang/String;)V
            //   3195: aload_3
            //   3196: ldc 'diem'
            //   3198: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3201: astore #7
            //   3203: aload #72
            //   3205: aload #7
            //   3207: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3210: aload_3
            //   3211: ldc 'diem_an'
            //   3213: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3216: astore #7
            //   3218: aload #73
            //   3220: aload #7
            //   3222: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3225: aload_3
            //   3226: ldc 'tong_tien'
            //   3228: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3231: astore #7
            //   3233: aload #74
            //   3235: aload #7
            //   3237: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3240: aload_3
            //   3241: ldc 'ket_qua'
            //   3243: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3246: astore_3
            //   3247: aload #75
            //   3249: aload_3
            //   3250: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3253: goto -> 3312
            //   3256: astore #7
            //   3258: aload_2
            //   3259: astore_3
            //   3260: aload #7
            //   3262: astore_2
            //   3263: goto -> 3972
            //   3266: astore_3
            //   3267: aload_2
            //   3268: astore #7
            //   3270: aload_3
            //   3271: astore_2
            //   3272: aload #7
            //   3274: astore_3
            //   3275: goto -> 3972
            //   3278: astore_3
            //   3279: aload_2
            //   3280: astore #7
            //   3282: aload_3
            //   3283: astore_2
            //   3284: aload #7
            //   3286: astore_3
            //   3287: goto -> 3972
            //   3290: astore #7
            //   3292: aload_2
            //   3293: astore_3
            //   3294: aload #7
            //   3296: astore_2
            //   3297: goto -> 3972
            //   3300: astore_3
            //   3301: aload_2
            //   3302: astore #7
            //   3304: aload_3
            //   3305: astore_2
            //   3306: aload #7
            //   3308: astore_3
            //   3309: goto -> 3972
            //   3312: aload #88
            //   3314: ldc_w 'xn'
            //   3317: invokevirtual has : (Ljava/lang/String;)Z
            //   3320: istore #90
            //   3322: iload #90
            //   3324: ifeq -> 3460
            //   3327: aload #22
            //   3329: iconst_0
            //   3330: invokevirtual setVisibility : (I)V
            //   3333: new org/json/JSONObject
            //   3336: astore_3
            //   3337: aload_3
            //   3338: aload #88
            //   3340: ldc_w 'xn'
            //   3343: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3346: invokespecial <init> : (Ljava/lang/String;)V
            //   3349: aload_3
            //   3350: ldc 'diem'
            //   3352: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3355: astore #7
            //   3357: aload #76
            //   3359: aload #7
            //   3361: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3364: aload_3
            //   3365: ldc 'diem_an'
            //   3367: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3370: astore #7
            //   3372: aload #77
            //   3374: aload #7
            //   3376: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3379: aload_3
            //   3380: ldc 'tong_tien'
            //   3382: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3385: astore #7
            //   3387: aload #78
            //   3389: aload #7
            //   3391: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3394: aload_3
            //   3395: ldc 'ket_qua'
            //   3397: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3400: astore_3
            //   3401: aload #79
            //   3403: aload_3
            //   3404: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3407: goto -> 3460
            //   3410: astore #7
            //   3412: aload_2
            //   3413: astore_3
            //   3414: aload #7
            //   3416: astore_2
            //   3417: goto -> 3972
            //   3420: astore #7
            //   3422: aload_2
            //   3423: astore_3
            //   3424: aload #7
            //   3426: astore_2
            //   3427: goto -> 3972
            //   3430: astore #7
            //   3432: aload_2
            //   3433: astore_3
            //   3434: aload #7
            //   3436: astore_2
            //   3437: goto -> 3972
            //   3440: astore #7
            //   3442: aload_2
            //   3443: astore_3
            //   3444: aload #7
            //   3446: astore_2
            //   3447: goto -> 3972
            //   3450: astore #7
            //   3452: aload_2
            //   3453: astore_3
            //   3454: aload #7
            //   3456: astore_2
            //   3457: goto -> 3972
            //   3460: aload #88
            //   3462: ldc_w 'bc'
            //   3465: invokevirtual has : (Ljava/lang/String;)Z
            //   3468: istore #90
            //   3470: iload #90
            //   3472: ifeq -> 3610
            //   3475: new org/json/JSONObject
            //   3478: astore_3
            //   3479: aload_3
            //   3480: aload #88
            //   3482: ldc_w 'bc'
            //   3485: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3488: invokespecial <init> : (Ljava/lang/String;)V
            //   3491: aload_3
            //   3492: ldc 'diem'
            //   3494: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3497: astore #7
            //   3499: aload #80
            //   3501: aload #7
            //   3503: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3506: aload_3
            //   3507: ldc 'diem_an'
            //   3509: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3512: astore #7
            //   3514: aload #81
            //   3516: aload #7
            //   3518: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3521: aload_3
            //   3522: ldc 'tong_tien'
            //   3524: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3527: astore #7
            //   3529: aload #82
            //   3531: aload #7
            //   3533: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3536: aload_3
            //   3537: ldc 'ket_qua'
            //   3539: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3542: astore_3
            //   3543: aload #83
            //   3545: aload_3
            //   3546: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3549: goto -> 3610
            //   3552: astore_3
            //   3553: aload_2
            //   3554: astore #7
            //   3556: aload_3
            //   3557: astore_2
            //   3558: aload #7
            //   3560: astore_3
            //   3561: goto -> 3972
            //   3564: astore #7
            //   3566: aload_2
            //   3567: astore_3
            //   3568: aload #7
            //   3570: astore_2
            //   3571: goto -> 3972
            //   3574: astore_3
            //   3575: aload_2
            //   3576: astore #7
            //   3578: aload_3
            //   3579: astore_2
            //   3580: aload #7
            //   3582: astore_3
            //   3583: goto -> 3972
            //   3586: astore_3
            //   3587: aload_2
            //   3588: astore #7
            //   3590: aload_3
            //   3591: astore_2
            //   3592: aload #7
            //   3594: astore_3
            //   3595: goto -> 3972
            //   3598: astore_3
            //   3599: aload_2
            //   3600: astore #7
            //   3602: aload_3
            //   3603: astore_2
            //   3604: aload #7
            //   3606: astore_3
            //   3607: goto -> 3972
            //   3610: aload #88
            //   3612: ldc_w 'bca'
            //   3615: invokevirtual has : (Ljava/lang/String;)Z
            //   3618: istore #90
            //   3620: iload #90
            //   3622: ifeq -> 3778
            //   3625: aload #23
            //   3627: iconst_0
            //   3628: invokevirtual setVisibility : (I)V
            //   3631: new org/json/JSONObject
            //   3634: astore_3
            //   3635: aload_3
            //   3636: aload #88
            //   3638: ldc_w 'bca'
            //   3641: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3644: invokespecial <init> : (Ljava/lang/String;)V
            //   3647: aload_3
            //   3648: ldc 'diem'
            //   3650: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3653: astore #7
            //   3655: aload #84
            //   3657: aload #7
            //   3659: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3662: aload_3
            //   3663: ldc 'diem_an'
            //   3665: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3668: astore #7
            //   3670: aload #85
            //   3672: aload #7
            //   3674: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3677: aload_3
            //   3678: ldc 'tong_tien'
            //   3680: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3683: astore #7
            //   3685: aload #86
            //   3687: aload #7
            //   3689: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3692: aload_3
            //   3693: ldc 'ket_qua'
            //   3695: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
            //   3698: astore_3
            //   3699: aload #87
            //   3701: aload_3
            //   3702: invokevirtual setText : (Ljava/lang/CharSequence;)V
            //   3705: goto -> 3778
            //   3708: astore_3
            //   3709: aload_2
            //   3710: astore #7
            //   3712: aload_3
            //   3713: astore_2
            //   3714: aload #7
            //   3716: astore_3
            //   3717: goto -> 3972
            //   3720: astore_3
            //   3721: aload_2
            //   3722: astore #7
            //   3724: aload_3
            //   3725: astore_2
            //   3726: aload #7
            //   3728: astore_3
            //   3729: goto -> 3972
            //   3732: astore #7
            //   3734: aload_2
            //   3735: astore_3
            //   3736: aload #7
            //   3738: astore_2
            //   3739: goto -> 3972
            //   3742: astore_3
            //   3743: aload_2
            //   3744: astore #7
            //   3746: aload_3
            //   3747: astore_2
            //   3748: aload #7
            //   3750: astore_3
            //   3751: goto -> 3972
            //   3754: astore_3
            //   3755: aload_2
            //   3756: astore #7
            //   3758: aload_3
            //   3759: astore_2
            //   3760: aload #7
            //   3762: astore_3
            //   3763: goto -> 3972
            //   3766: astore_3
            //   3767: aload_2
            //   3768: astore #7
            //   3770: aload_3
            //   3771: astore_2
            //   3772: aload #7
            //   3774: astore_3
            //   3775: goto -> 3972
            //   3778: goto -> 3976
            //   3781: astore #7
            //   3783: aload_2
            //   3784: astore_3
            //   3785: aload #7
            //   3787: astore_2
            //   3788: goto -> 3972
            //   3791: astore_3
            //   3792: aload_2
            //   3793: astore #7
            //   3795: aload_3
            //   3796: astore_2
            //   3797: aload #7
            //   3799: astore_3
            //   3800: goto -> 3972
            //   3803: astore_3
            //   3804: aload_2
            //   3805: astore #7
            //   3807: aload_3
            //   3808: astore_2
            //   3809: aload #7
            //   3811: astore_3
            //   3812: goto -> 3972
            //   3815: astore #7
            //   3817: aload_2
            //   3818: astore_3
            //   3819: aload #7
            //   3821: astore_2
            //   3822: goto -> 3972
            //   3825: astore #7
            //   3827: aload_2
            //   3828: astore_3
            //   3829: aload #7
            //   3831: astore_2
            //   3832: goto -> 3972
            //   3835: astore_3
            //   3836: aload_2
            //   3837: astore #7
            //   3839: aload_3
            //   3840: astore_2
            //   3841: aload #7
            //   3843: astore_3
            //   3844: goto -> 3972
            //   3847: astore #7
            //   3849: aload_2
            //   3850: astore_3
            //   3851: aload #7
            //   3853: astore_2
            //   3854: goto -> 3972
            //   3857: astore #7
            //   3859: aload_2
            //   3860: astore_3
            //   3861: aload #7
            //   3863: astore_2
            //   3864: goto -> 3972
            //   3867: astore_3
            //   3868: aload_2
            //   3869: astore #7
            //   3871: aload_3
            //   3872: astore_2
            //   3873: aload #7
            //   3875: astore_3
            //   3876: goto -> 3972
            //   3879: astore #7
            //   3881: aload_2
            //   3882: astore_3
            //   3883: aload #7
            //   3885: astore_2
            //   3886: goto -> 3972
            //   3889: astore #7
            //   3891: aload_2
            //   3892: astore_3
            //   3893: aload #7
            //   3895: astore_2
            //   3896: goto -> 3972
            //   3899: astore_3
            //   3900: aload_2
            //   3901: astore #7
            //   3903: aload_3
            //   3904: astore_2
            //   3905: aload #7
            //   3907: astore_3
            //   3908: goto -> 3972
            //   3911: astore #7
            //   3913: aload_2
            //   3914: astore_3
            //   3915: aload #7
            //   3917: astore_2
            //   3918: goto -> 3972
            //   3921: astore_3
            //   3922: aload_2
            //   3923: astore #7
            //   3925: aload_3
            //   3926: astore_2
            //   3927: aload #7
            //   3929: astore_3
            //   3930: goto -> 3972
            //   3933: astore_3
            //   3934: aload_2
            //   3935: astore #7
            //   3937: aload_3
            //   3938: astore_2
            //   3939: aload #7
            //   3941: astore_3
            //   3942: goto -> 3972
            //   3945: astore #7
            //   3947: aload_2
            //   3948: astore_3
            //   3949: aload #7
            //   3951: astore_2
            //   3952: goto -> 3972
            //   3955: astore_3
            //   3956: aload_2
            //   3957: astore #7
            //   3959: aload_3
            //   3960: astore_2
            //   3961: aload #7
            //   3963: astore_3
            //   3964: goto -> 3972
            //   3967: astore_2
            //   3968: goto -> 3972
            //   3971: astore_2
            //   3972: aload_2
            //   3973: invokevirtual printStackTrace : ()V
            //   3976: aload #4
            //   3978: areturn
            // Exception table:
            //   from	to	target	type
            //   1047	1080	3971	org/json/JSONException
            //   1080	1088	3967	org/json/JSONException
            //   1093	1103	1106	org/json/JSONException
            //   1110	1176	3967	org/json/JSONException
            //   1185	1192	3955	org/json/JSONException
            //   1200	1211	1380	org/json/JSONException
            //   1214	1225	1368	org/json/JSONException
            //   1236	1249	1368	org/json/JSONException
            //   1249	1280	1319	org/json/JSONException
            //   1283	1310	1319	org/json/JSONException
            //   1392	1407	3945	org/json/JSONException
            //   1412	1473	1492	org/json/JSONException
            //   1473	1479	1482	org/json/JSONException
            //   1502	1511	3933	org/json/JSONException
            //   1516	1539	1640	org/json/JSONException
            //   1539	1554	1628	org/json/JSONException
            //   1554	1569	1616	org/json/JSONException
            //   1569	1583	1604	org/json/JSONException
            //   1583	1589	1592	org/json/JSONException
            //   1652	1661	3921	org/json/JSONException
            //   1666	1695	1792	org/json/JSONException
            //   1695	1710	1780	org/json/JSONException
            //   1710	1725	1770	org/json/JSONException
            //   1725	1739	1758	org/json/JSONException
            //   1739	1745	1748	org/json/JSONException
            //   1802	1811	3911	org/json/JSONException
            //   1816	1845	1940	org/json/JSONException
            //   1845	1860	1930	org/json/JSONException
            //   1860	1875	1920	org/json/JSONException
            //   1875	1889	1910	org/json/JSONException
            //   1889	1895	1898	org/json/JSONException
            //   1950	1959	3899	org/json/JSONException
            //   1964	1993	2090	org/json/JSONException
            //   1993	2008	2080	org/json/JSONException
            //   2008	2023	2068	org/json/JSONException
            //   2023	2037	2058	org/json/JSONException
            //   2037	2043	2046	org/json/JSONException
            //   2102	2111	3889	org/json/JSONException
            //   2116	2139	2240	org/json/JSONException
            //   2139	2154	2228	org/json/JSONException
            //   2154	2169	2216	org/json/JSONException
            //   2169	2183	2204	org/json/JSONException
            //   2183	2189	2192	org/json/JSONException
            //   2252	2261	3879	org/json/JSONException
            //   2266	2295	2392	org/json/JSONException
            //   2295	2310	2380	org/json/JSONException
            //   2310	2325	2370	org/json/JSONException
            //   2325	2339	2358	org/json/JSONException
            //   2339	2345	2348	org/json/JSONException
            //   2402	2411	3867	org/json/JSONException
            //   2416	2445	2540	org/json/JSONException
            //   2445	2460	2530	org/json/JSONException
            //   2460	2475	2520	org/json/JSONException
            //   2475	2489	2510	org/json/JSONException
            //   2489	2495	2498	org/json/JSONException
            //   2550	2559	3857	org/json/JSONException
            //   2564	2593	2692	org/json/JSONException
            //   2593	2608	2680	org/json/JSONException
            //   2608	2623	2670	org/json/JSONException
            //   2623	2637	2658	org/json/JSONException
            //   2637	2643	2646	org/json/JSONException
            //   2702	2711	3847	org/json/JSONException
            //   2716	2745	2842	org/json/JSONException
            //   2745	2760	2832	org/json/JSONException
            //   2760	2775	2820	org/json/JSONException
            //   2775	2789	2810	org/json/JSONException
            //   2789	2795	2798	org/json/JSONException
            //   2854	2864	3835	org/json/JSONException
            //   2869	2899	2994	org/json/JSONException
            //   2899	2914	2984	org/json/JSONException
            //   2914	2929	2974	org/json/JSONException
            //   2929	2943	2964	org/json/JSONException
            //   2943	2949	2952	org/json/JSONException
            //   3004	3014	3825	org/json/JSONException
            //   3019	3049	3146	org/json/JSONException
            //   3049	3064	3134	org/json/JSONException
            //   3064	3079	3122	org/json/JSONException
            //   3079	3093	3112	org/json/JSONException
            //   3093	3099	3102	org/json/JSONException
            //   3158	3168	3815	org/json/JSONException
            //   3173	3203	3300	org/json/JSONException
            //   3203	3218	3290	org/json/JSONException
            //   3218	3233	3278	org/json/JSONException
            //   3233	3247	3266	org/json/JSONException
            //   3247	3253	3256	org/json/JSONException
            //   3312	3322	3803	org/json/JSONException
            //   3327	3357	3450	org/json/JSONException
            //   3357	3372	3440	org/json/JSONException
            //   3372	3387	3430	org/json/JSONException
            //   3387	3401	3420	org/json/JSONException
            //   3401	3407	3410	org/json/JSONException
            //   3460	3470	3791	org/json/JSONException
            //   3475	3499	3598	org/json/JSONException
            //   3499	3514	3586	org/json/JSONException
            //   3514	3529	3574	org/json/JSONException
            //   3529	3543	3564	org/json/JSONException
            //   3543	3549	3552	org/json/JSONException
            //   3610	3620	3781	org/json/JSONException
            //   3625	3635	3766	org/json/JSONException
            //   3635	3655	3754	org/json/JSONException
            //   3655	3670	3742	org/json/JSONException
            //   3670	3685	3732	org/json/JSONException
            //   3685	3699	3720	org/json/JSONException
            //   3699	3705	3708	org/json/JSONException
        }
    }
}
