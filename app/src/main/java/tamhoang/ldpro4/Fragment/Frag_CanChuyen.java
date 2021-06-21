package tamhoang.ldpro4.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Congthuc.Congthuc;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.RangeSeekBar;
import tamhoang.ldpro4.data.Database;

public class Frag_CanChuyen extends Fragment {
    boolean Dachuyen = false;

    String DangXuat = null;

    Button btn_Xuatso;

    CheckBox check_x2;

    CheckBox check_x3;

    CheckBox check_x4;

    CheckBox check_xn;

    Database db;

    EditText edt_tien;

    Handler handler;

    JSONObject jsonKhongmax;

    String lay_x2;

    String lay_x3;

    String lay_x4;

    LinearLayout layout;

    LinearLayout li_loaide;

    LinearLayout ln_xi;

    public List<String> mAppuse = new ArrayList<String>();

    public List<String> mContact = new ArrayList<String>();

    public List<String> mKhongMax = new ArrayList<String>();

    public List<String> mMobile = new ArrayList<String>();

    public List<Integer> mNhay = new ArrayList<Integer>();

    public List<String> mSo = new ArrayList<String>();

    int mSpiner = 0;

    public List<String> mTienNhan = new ArrayList<String>();

    public List<String> mTienOm = new ArrayList<String>();

    public List<String> mTienTon = new ArrayList<String>();

    public List<String> mTienchuyen = new ArrayList<String>();

    int max = 100;

    int min = 0;

    ListView no_rp_number;

    RadioButton radio_bc;

    RadioButton radio_de;

    RadioButton radio_dea;

    RadioButton radio_deb;

    RadioButton radio_dec;

    RadioButton radio_ded;

    RadioButton radio_lo;

    RadioButton radio_xi;

    RangeSeekBar<Integer> rangeSeekBar;

    private Runnable runnable = new Runnable() {
        public void run() {
            new MainActivity();
            if (MainActivity.sms == true) {
                Frag_CanChuyen.this.xemlv();
                MainActivity.sms = false;
            }
            Frag_CanChuyen.this.handler.postDelayed(this, 1000L);
        }
    };

    String sapxep;

    public View v;

    String xuatDan;

    public void Dialog(int paramInt) {
        final Dialog dialog = new Dialog((Context)getActivity());
        dialog.setContentView(2131427394);
        dialog.getWindow().setLayout(-1, -2);
        final String Chuyendi = this.xuatDan.replaceAll(",x", "x");
        this.Dachuyen = false;
        Spinner spinner = (Spinner)dialog.findViewById(2131231269);
        final EditText edt_XuatDan = (EditText)dialog.findViewById(2131230931);
        Button button = (Button)dialog.findViewById(2131230816);
        editText.setText("");
        editText.setText(this.xuatDan.replaceAll(",x", "x"));
        try {
            Cursor cursor = this.db.GetData("Select * From tbl_kh_new WHERE type_kh <> 1 ORDER BY ten_kh");
            this.mContact.clear();
            this.mMobile.clear();
            this.mKhongMax.clear();
            this.mAppuse.clear();
            while (cursor.moveToNext()) {
                if (cursor.getString(2).indexOf("sms") > -1 || cursor.getString(2).indexOf("TL") > -1) {
                    this.mContact.add(cursor.getString(0));
                    this.mMobile.add(cursor.getString(1));
                    this.mKhongMax.add(cursor.getString(6));
                    this.mAppuse.add(cursor.getString(2));
                    continue;
                }
                if (MainActivity.arr_TenKH.indexOf(cursor.getString(1)) > -1) {
                    this.mContact.add(cursor.getString(0));
                    this.mMobile.add(cursor.getString(1));
                    this.mKhongMax.add(cursor.getString(6));
                    this.mAppuse.add(cursor.getString(2));
                }
            }
            if (cursor != null && !cursor.isClosed())
                cursor.close();
            ArrayAdapter arrayAdapter = new ArrayAdapter();
            this((Context)getActivity(), 2131427455, this.mContact);
            spinner.setAdapter((SpinnerAdapter)arrayAdapter);
        } catch (SQLException sQLException) {}
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_CanChuyen.this.mSpiner = param1Int;
                try {
                    Frag_CanChuyen frag_CanChuyen = Frag_CanChuyen.this;
                    JSONObject jSONObject = new JSONObject();
                    this(Frag_CanChuyen.this.mKhongMax.get(Frag_CanChuyen.this.mSpiner));
                    frag_CanChuyen.jsonKhongmax = jSONObject;
                    if (Frag_CanChuyen.this.radio_deb.isChecked() && Frag_CanChuyen.this.radio_de.isChecked() && Frag_CanChuyen.this.jsonKhongmax.getString("danDe").length() > 0) {
                        edt_XuatDan.setText(Frag_CanChuyen.this.TaoTinDe(Frag_CanChuyen.this.mContact.get(Frag_CanChuyen.this.mSpiner)));
                    } else if (Frag_CanChuyen.this.radio_lo.isChecked() && Frag_CanChuyen.this.jsonKhongmax.getString("danLo").length() > 0) {
                        edt_XuatDan.setText(Frag_CanChuyen.this.TaoTinLo(Frag_CanChuyen.this.mContact.get(Frag_CanChuyen.this.mSpiner)));
                    } else if (Frag_CanChuyen.this.radio_xi.isChecked() && (Frag_CanChuyen.this.jsonKhongmax.getInt("xien2") > 0 || Frag_CanChuyen.this.jsonKhongmax.getInt("xien3") > 0 || Frag_CanChuyen.this.jsonKhongmax.getInt("xien4") > 0)) {
                        edt_XuatDan.setText(Frag_CanChuyen.this.TaoTinXi(Frag_CanChuyen.this.mContact.get(Frag_CanChuyen.this.mSpiner)));
                    } else if (Frag_CanChuyen.this.radio_bc.isChecked() && Frag_CanChuyen.this.jsonKhongmax.getInt("cang") > 0) {
                        edt_XuatDan.setText(Frag_CanChuyen.this.TaoTinCang(Frag_CanChuyen.this.mContact.get(Frag_CanChuyen.this.mSpiner)));
                    } else {
                        edt_XuatDan.setText(Chuyendi);
                    }
                } catch (JSONException jSONException) {
                    jSONException.printStackTrace();
                }
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                String str1;
                int j;
                new MainActivity();
                String str2 = MainActivity.Get_date();
                int i = 0;
                try {
                    j = MainActivity.jSon_Setting.getInt("gioi_han_tin");
                } catch (JSONException jSONException) {
                    jSONException.printStackTrace();
                    j = i;
                }
                i = 2000;
                if (j == 1) {
                    i = 3000;
                } else if (j == 2) {
                    i = 155;
                } else if (j == 3) {
                    i = 315;
                } else if (j == 4) {
                    i = 475;
                } else if (j == 5) {
                    i = 995;
                } else if (j == 6) {
                    i = 2000;
                }
                int k = Frag_CanChuyen.this.mMobile.size();
                String str3 = "";
                if (k > 0 && edt_XuatDan.getText().toString().length() > 0 && !Frag_CanChuyen.this.Dachuyen) {
                    Frag_CanChuyen.this.Dachuyen = true;
                    String str = edt_XuatDan.getText().toString().replaceAll("'", " ").trim();
                    edt_XuatDan.setText("");
                    dialog.dismiss();
                    if (str.trim().length() < i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Select max(so_tin_nhan) from tbl_tinnhanS WHERE ngay_nhan = '");
                        stringBuilder.append(str2);
                        stringBuilder.append("' AND so_dienthoai = '");
                        stringBuilder.append(Frag_CanChuyen.this.mMobile.get(Frag_CanChuyen.this.mSpiner));
                        stringBuilder.append("' AND type_kh = 2");
                        String str4 = stringBuilder.toString();
                        Cursor cursor = Frag_CanChuyen.this.db.GetData(str4);
                        cursor.moveToFirst();
                        i = cursor.getInt(0);
                        Frag_CanChuyen.this.Xulytin(i + 1, str.replaceAll("'", " ").trim(), 1);
                        if (cursor != null)
                            cursor.close();
                        str1 = "";
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Select max(so_tin_nhan) from tbl_tinnhanS WHERE ngay_nhan = '");
                        stringBuilder.append(str2);
                        stringBuilder.append("' AND so_dienthoai = '");
                        stringBuilder.append(Frag_CanChuyen.this.mMobile.get(Frag_CanChuyen.this.mSpiner));
                        stringBuilder.append("' AND type_kh = 2");
                        String str4 = stringBuilder.toString();
                        Cursor cursor = Frag_CanChuyen.this.db.GetData(str4);
                        cursor.moveToFirst();
                        k = cursor.getInt(0) + 1;
                        String str5 = "";
                        String[] arrayOfString = null;
                        if (str.substring(0, 3).indexOf("De") > -1) {
                            str5 = "De:";
                            str1 = str.replaceAll("De:", "");
                            arrayOfString = str1.split(" ");
                        } else if (str.substring(0, 3).indexOf("Lo") > -1) {
                            str5 = "Lo:";
                            str1 = str.replaceAll("Lo:", "");
                            arrayOfString = str1.split(" ");
                        } else if (str.substring(0, 5).indexOf("Cang") > -1) {
                            str5 = "Cang:";
                            str1 = str.replaceAll("Cang:\n", "");
                            arrayOfString = str1.split(" ");
                        } else {
                            str1 = str;
                            if (str.substring(0, 3).indexOf("Xi") > -1) {
                                str5 = "Xien:";
                                str1 = str.replaceAll("Xien:\n", "").replaceAll("d:", "0").replaceAll("\n", " ");
                                arrayOfString = str1.split(" ");
                            }
                        }
                        str = "";
                        if (str5 != "Xien:") {
                            String str6;
                            byte b = 0;
                            String str7 = str1;
                            int m = j;
                            str1 = str;
                            while (b < arrayOfString.length) {
                                str = arrayOfString[b];
                                String str8 = arrayOfString[b];
                                String str9 = "x";
                                str = str.substring(0, str8.indexOf("x"));
                                str8 = arrayOfString[b].substring(arrayOfString[b].indexOf("x")).replaceAll(",", str3);
                                String[] arrayOfString1 = str.split(",");
                                byte b1 = 0;
                                str = str1;
                                while (true) {
                                    str1 = str;
                                    j = k;
                                    if (b1 < arrayOfString1.length) {
                                        str = str.replaceAll(",x", str9);
                                        if (str.length() == 0) {
                                            if (arrayOfString1.length == 1) {
                                                StringBuilder stringBuilder1 = new StringBuilder();
                                                stringBuilder1.append(str5);
                                                stringBuilder1.append("\n");
                                                stringBuilder1.append(arrayOfString1[b1]);
                                                stringBuilder1.append(",");
                                                stringBuilder1.append(str8);
                                                stringBuilder1.append(" ");
                                                str6 = stringBuilder1.toString();
                                            } else {
                                                StringBuilder stringBuilder1 = new StringBuilder();
                                                stringBuilder1.append(str5);
                                                stringBuilder1.append("\n");
                                                stringBuilder1.append(arrayOfString1[b1]);
                                                stringBuilder1.append(",");
                                                str6 = stringBuilder1.toString();
                                            }
                                        } else if (str.length() + str8.length() + str8.length() < i) {
                                            if (b1 < arrayOfString1.length - 1) {
                                                StringBuilder stringBuilder1 = new StringBuilder();
                                                stringBuilder1.append(str);
                                                stringBuilder1.append(arrayOfString1[b1]);
                                                stringBuilder1.append(",");
                                                str6 = stringBuilder1.toString();
                                            } else {
                                                StringBuilder stringBuilder1 = new StringBuilder();
                                                stringBuilder1.append(str);
                                                stringBuilder1.append(arrayOfString1[b1]);
                                                stringBuilder1.append(",");
                                                stringBuilder1.append(str8);
                                                stringBuilder1.append(" ");
                                                str6 = stringBuilder1.toString();
                                                j = k;
                                                break;
                                            }
                                        } else {
                                            str1 = str;
                                            if (b1 > 0) {
                                                StringBuilder stringBuilder1 = new StringBuilder();
                                                stringBuilder1.append(str);
                                                stringBuilder1.append(str8);
                                                str6 = stringBuilder1.toString();
                                            }
                                            Frag_CanChuyen.this.Xulytin(k, str6, 1);
                                            j = k + 1;
                                            if (b1 < arrayOfString1.length - 1) {
                                                StringBuilder stringBuilder1 = new StringBuilder();
                                                stringBuilder1.append(str5);
                                                stringBuilder1.append("\n");
                                                stringBuilder1.append(arrayOfString1[b1]);
                                                stringBuilder1.append(",");
                                                String str10 = stringBuilder1.toString();
                                                k = j;
                                            } else {
                                                StringBuilder stringBuilder1 = new StringBuilder();
                                                stringBuilder1.append(str5);
                                                stringBuilder1.append("\n");
                                                stringBuilder1.append(arrayOfString1[b1]);
                                                stringBuilder1.append(",");
                                                stringBuilder1.append(str8);
                                                stringBuilder1.append(" ");
                                                str6 = stringBuilder1.toString();
                                                break;
                                            }
                                        }
                                        b1++;
                                        str = str6;
                                        continue;
                                    }
                                    break;
                                }
                                b++;
                                k = j;
                            }
                            str5 = str3;
                            if (str6.length() > 0) {
                                Frag_CanChuyen.this.Xulytin(k, str6, 1);
                                str5 = str3;
                            }
                        } else {
                            String str6;
                            str3 = "";
                            j = 0;
                            str1 = str;
                            while (j < arrayOfString.length) {
                                if (str1.length() == 0) {
                                    StringBuilder stringBuilder1 = new StringBuilder();
                                    stringBuilder1.append(str5);
                                    stringBuilder1.append("\n");
                                    stringBuilder1.append(arrayOfString[j]);
                                    stringBuilder1.append(" ");
                                    str6 = stringBuilder1.toString();
                                } else if (str6.length() + arrayOfString[j].length() < i) {
                                    StringBuilder stringBuilder1 = new StringBuilder();
                                    stringBuilder1.append(str6);
                                    stringBuilder1.append(arrayOfString[j]);
                                    stringBuilder1.append(" ");
                                    str6 = stringBuilder1.toString();
                                } else {
                                    Frag_CanChuyen.this.Xulytin(k, str6, 1);
                                    k++;
                                    StringBuilder stringBuilder1 = new StringBuilder();
                                    stringBuilder1.append(str5);
                                    stringBuilder1.append("\n");
                                    stringBuilder1.append(arrayOfString[j]);
                                    stringBuilder1.append(" ");
                                    str6 = stringBuilder1.toString();
                                }
                                j++;
                            }
                            str5 = str3;
                            if (str6.length() > 0) {
                                Frag_CanChuyen.this.Xulytin(k, str6, 1);
                                str5 = str3;
                            }
                        }
                        str1 = str5;
                        if (cursor != null) {
                            cursor.close();
                            str1 = str5;
                        }
                    }
                    Toast.makeText((Context)Frag_CanChuyen.this.getActivity(), "chuytin!", 1).show();
                } else {
                    str1 = "";
                    if (edt_XuatDan.getText().toString().length() != 0)
                        if (Frag_CanChuyen.this.Dachuyen == true) {
                            dialog.cancel();
                        } else {
                            Toast.makeText((Context)Frag_CanChuyen.this.getActivity(), "Chcchchuytin!", 1).show();
                        }
                }
                Frag_CanChuyen.this.xemlv();
                Frag_CanChuyen.this.min = 0;
                Frag_CanChuyen.this.max = 100;
                Frag_CanChuyen.this.rangeSeekBar.setSelectedMinValue(Integer.valueOf(0));
                Frag_CanChuyen.this.rangeSeekBar.setSelectedMaxValue(Integer.valueOf(100));
                Frag_CanChuyen.this.edt_tien.setText(str1);
            }
        });
        dialog.getWindow().setLayout(-1, -2);
        dialog.setCancelable(true);
        dialog.setTitle("Xem d);
                dialog.show();
    }

    public String TaoTinCang(String paramString) {
        // Byte code:
        //   0: ldc_w 'Se_chuyen'
        //   3: astore_2
        //   4: dconst_0
        //   5: dstore_3
        //   6: new org/json/JSONObject
        //   9: dup
        //   10: invokespecial <init> : ()V
        //   13: astore #5
        //   15: new tamhoang/ldpro4/MainActivity
        //   18: dup
        //   19: invokespecial <init> : ()V
        //   22: astore #6
        //   24: invokestatic Get_date : ()Ljava/lang/String;
        //   27: astore #7
        //   29: new java/lang/StringBuilder
        //   32: dup
        //   33: invokespecial <init> : ()V
        //   36: astore #8
        //   38: aload #8
        //   40: ldc_w 'Select so_chon, Sum(diem) FROM tbl_soctS Where ten_kh = ''
        //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   46: pop
        //   47: aload #8
        //   49: aload_1
        //   50: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   53: pop
        //   54: aload #8
        //   56: ldc_w '' AND type_kh = 2 AND the_loai = 'bc' AND ngay_nhan = ''
        //   59: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   62: pop
        //   63: aload #8
        //   65: aload #7
        //   67: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   70: pop
        //   71: aload #8
        //   73: ldc_w '' Group by so_chon'
        //   76: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   79: pop
        //   80: aload #8
        //   82: invokevirtual toString : ()Ljava/lang/String;
        //   85: astore #8
        //   87: aload_0
        //   88: getfield db : Ltamhoang/ldpro4/data/Database;
        //   91: aload #8
        //   93: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   96: astore #9
        //   98: aload #9
        //   100: invokeinterface moveToNext : ()Z
        //   105: ifeq -> 159
        //   108: new org/json/JSONObject
        //   111: astore_1
        //   112: aload_1
        //   113: invokespecial <init> : ()V
        //   116: aload_1
        //   117: ldc_w 'Da_chuyen'
        //   120: aload #9
        //   122: iconst_1
        //   123: invokeinterface getInt : (I)I
        //   128: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   131: pop
        //   132: aload_1
        //   133: ldc_w 'Se_chuyen'
        //   136: iconst_0
        //   137: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   140: pop
        //   141: aload #5
        //   143: aload #9
        //   145: iconst_0
        //   146: invokeinterface getString : (I)Ljava/lang/String;
        //   151: aload_1
        //   152: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   155: pop
        //   156: goto -> 98
        //   159: aload #9
        //   161: invokeinterface isClosed : ()Z
        //   166: ifne -> 201
        //   169: aload #9
        //   171: invokeinterface close : ()V
        //   176: goto -> 201
        //   179: astore_1
        //   180: goto -> 1045
        //   183: astore_1
        //   184: aload_1
        //   185: invokevirtual printStackTrace : ()V
        //   188: aload #9
        //   190: invokeinterface isClosed : ()Z
        //   195: ifne -> 201
        //   198: goto -> 169
        //   201: iconst_0
        //   202: istore #10
        //   204: aload #7
        //   206: astore_1
        //   207: dload_3
        //   208: dstore #11
        //   210: iload #10
        //   212: aload_0
        //   213: getfield mSo : Ljava/util/List;
        //   216: invokeinterface size : ()I
        //   221: if_icmpge -> 606
        //   224: dload_3
        //   225: dstore #11
        //   227: aload_0
        //   228: getfield mSo : Ljava/util/List;
        //   231: iload #10
        //   233: invokeinterface get : (I)Ljava/lang/Object;
        //   238: checkcast java/lang/String
        //   241: astore #7
        //   243: dload_3
        //   244: dstore #11
        //   246: aload_0
        //   247: getfield jsonKhongmax : Lorg/json/JSONObject;
        //   250: ldc_w 'cang'
        //   253: invokevirtual getInt : (Ljava/lang/String;)I
        //   256: i2d
        //   257: dstore_3
        //   258: dload_3
        //   259: dstore #11
        //   261: aload_0
        //   262: getfield mTienTon : Ljava/util/List;
        //   265: iload #10
        //   267: invokeinterface get : (I)Ljava/lang/Object;
        //   272: checkcast java/lang/String
        //   275: astore #9
        //   277: aload #9
        //   279: ldc_w '.'
        //   282: ldc ''
        //   284: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   287: invokestatic parseInt : (Ljava/lang/String;)I
        //   290: istore #13
        //   292: aload #5
        //   294: aload #7
        //   296: invokevirtual has : (Ljava/lang/String;)Z
        //   299: istore #14
        //   301: iload #14
        //   303: ifeq -> 493
        //   306: iload #13
        //   308: ifle -> 493
        //   311: new org/json/JSONObject
        //   314: astore #9
        //   316: aload #9
        //   318: invokespecial <init> : ()V
        //   321: aload #5
        //   323: aload #7
        //   325: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   328: ldc_w 'Da_chuyen'
        //   331: invokevirtual getDouble : (Ljava/lang/String;)D
        //   334: dstore #11
        //   336: iload #13
        //   338: i2d
        //   339: dstore #15
        //   341: dload #15
        //   343: invokestatic isNaN : (D)Z
        //   346: pop
        //   347: dload #11
        //   349: dload #15
        //   351: dadd
        //   352: dload_3
        //   353: dcmpg
        //   354: ifgt -> 407
        //   357: aload #9
        //   359: ldc_w 'So_chon'
        //   362: aload #7
        //   364: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   367: pop
        //   368: aload #9
        //   370: ldc_w 'Da_chuyen'
        //   373: aload #5
        //   375: aload #7
        //   377: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   380: ldc_w 'Da_chuyen'
        //   383: invokevirtual getInt : (Ljava/lang/String;)I
        //   386: iload #13
        //   388: iadd
        //   389: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   392: pop
        //   393: aload #9
        //   395: ldc_w 'Se_chuyen'
        //   398: iload #13
        //   400: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   403: pop
        //   404: goto -> 472
        //   407: aload #9
        //   409: ldc_w 'So_chon'
        //   412: aload #7
        //   414: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   417: pop
        //   418: aload #9
        //   420: ldc_w 'Da_chuyen'
        //   423: dload_3
        //   424: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   427: pop
        //   428: aload #5
        //   430: aload #7
        //   432: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   435: ldc_w 'Da_chuyen'
        //   438: invokevirtual getInt : (Ljava/lang/String;)I
        //   441: istore #13
        //   443: iload #13
        //   445: i2d
        //   446: dstore #11
        //   448: dload_3
        //   449: invokestatic isNaN : (D)Z
        //   452: pop
        //   453: dload #11
        //   455: invokestatic isNaN : (D)Z
        //   458: pop
        //   459: aload #9
        //   461: ldc_w 'Se_chuyen'
        //   464: dload_3
        //   465: dload #11
        //   467: dsub
        //   468: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   471: pop
        //   472: aload #5
        //   474: aload #7
        //   476: aload #9
        //   478: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   481: pop
        //   482: goto -> 588
        //   485: astore_1
        //   486: goto -> 613
        //   489: astore_1
        //   490: goto -> 613
        //   493: new org/json/JSONObject
        //   496: astore #9
        //   498: aload #9
        //   500: invokespecial <init> : ()V
        //   503: iload #13
        //   505: i2d
        //   506: dload_3
        //   507: dcmpg
        //   508: ifgt -> 547
        //   511: aload #9
        //   513: ldc_w 'So_chon'
        //   516: aload #7
        //   518: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   521: pop
        //   522: aload #9
        //   524: ldc_w 'Da_chuyen'
        //   527: iload #13
        //   529: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   532: pop
        //   533: aload #9
        //   535: ldc_w 'Se_chuyen'
        //   538: iload #13
        //   540: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   543: pop
        //   544: goto -> 578
        //   547: aload #9
        //   549: ldc_w 'So_chon'
        //   552: aload #7
        //   554: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   557: pop
        //   558: aload #9
        //   560: ldc_w 'Da_chuyen'
        //   563: dload_3
        //   564: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   567: pop
        //   568: aload #9
        //   570: ldc_w 'Se_chuyen'
        //   573: dload_3
        //   574: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   577: pop
        //   578: aload #5
        //   580: aload #7
        //   582: aload #9
        //   584: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   587: pop
        //   588: iinc #10, 1
        //   591: goto -> 207
        //   594: astore_1
        //   595: goto -> 613
        //   598: astore_1
        //   599: goto -> 613
        //   602: astore_1
        //   603: goto -> 613
        //   606: goto -> 617
        //   609: astore_1
        //   610: dload #11
        //   612: dstore_3
        //   613: aload_1
        //   614: invokevirtual printStackTrace : ()V
        //   617: aload #5
        //   619: invokevirtual keys : ()Ljava/util/Iterator;
        //   622: astore #8
        //   624: new java/util/ArrayList
        //   627: dup
        //   628: invokespecial <init> : ()V
        //   631: astore #7
        //   633: aload #8
        //   635: invokeinterface hasNext : ()Z
        //   640: ifeq -> 694
        //   643: aload #8
        //   645: invokeinterface next : ()Ljava/lang/Object;
        //   650: checkcast java/lang/String
        //   653: astore_1
        //   654: aload #5
        //   656: aload_1
        //   657: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   660: ldc_w 'Se_chuyen'
        //   663: invokevirtual getInt : (Ljava/lang/String;)I
        //   666: ifle -> 683
        //   669: aload #7
        //   671: aload #5
        //   673: aload_1
        //   674: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   677: invokeinterface add : (Ljava/lang/Object;)Z
        //   682: pop
        //   683: goto -> 691
        //   686: astore_1
        //   687: aload_1
        //   688: invokevirtual printStackTrace : ()V
        //   691: goto -> 633
        //   694: aload #7
        //   696: new tamhoang/ldpro4/Fragment/Frag_CanChuyen$19
        //   699: dup
        //   700: aload_0
        //   701: invokespecial <init> : (Ltamhoang/ldpro4/Fragment/Frag_CanChuyen;)V
        //   704: invokestatic sort : (Ljava/util/List;Ljava/util/Comparator;)V
        //   707: aload_0
        //   708: ldc_w 'Cang:'
        //   711: putfield xuatDan : Ljava/lang/String;
        //   714: iconst_0
        //   715: istore #17
        //   717: iconst_0
        //   718: istore #13
        //   720: iconst_0
        //   721: istore #10
        //   723: aload #5
        //   725: astore_1
        //   726: aload_2
        //   727: astore #6
        //   729: aload #7
        //   731: invokeinterface size : ()I
        //   736: istore #18
        //   738: iload #13
        //   740: iload #18
        //   742: if_icmpge -> 964
        //   745: aload #7
        //   747: iload #13
        //   749: invokeinterface get : (I)Ljava/lang/Object;
        //   754: checkcast org/json/JSONObject
        //   757: astore_2
        //   758: aload_2
        //   759: aload #6
        //   761: invokevirtual getInt : (Ljava/lang/String;)I
        //   764: istore #18
        //   766: iload #18
        //   768: ifle -> 954
        //   771: iload #17
        //   773: iload #18
        //   775: if_icmple -> 893
        //   778: new java/lang/StringBuilder
        //   781: astore #5
        //   783: aload #5
        //   785: invokespecial <init> : ()V
        //   788: aload #5
        //   790: aload_0
        //   791: getfield xuatDan : Ljava/lang/String;
        //   794: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   797: pop
        //   798: aload #5
        //   800: ldc 'x'
        //   802: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   805: pop
        //   806: aload #5
        //   808: iload #17
        //   810: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   813: pop
        //   814: aload #5
        //   816: ldc_w 'n '
        //   819: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   822: pop
        //   823: aload_0
        //   824: aload #5
        //   826: invokevirtual toString : ()Ljava/lang/String;
        //   829: putfield xuatDan : Ljava/lang/String;
        //   832: new java/lang/StringBuilder
        //   835: astore #5
        //   837: aload #5
        //   839: invokespecial <init> : ()V
        //   842: aload #5
        //   844: aload_0
        //   845: getfield xuatDan : Ljava/lang/String;
        //   848: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   851: pop
        //   852: aload #5
        //   854: aload_2
        //   855: ldc_w 'So_chon'
        //   858: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   861: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   864: pop
        //   865: aload #5
        //   867: ldc_w ','
        //   870: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   873: pop
        //   874: aload_0
        //   875: aload #5
        //   877: invokevirtual toString : ()Ljava/lang/String;
        //   880: putfield xuatDan : Ljava/lang/String;
        //   883: iconst_0
        //   884: istore #10
        //   886: goto -> 944
        //   889: astore_1
        //   890: goto -> 1035
        //   893: new java/lang/StringBuilder
        //   896: astore #5
        //   898: aload #5
        //   900: invokespecial <init> : ()V
        //   903: aload #5
        //   905: aload_0
        //   906: getfield xuatDan : Ljava/lang/String;
        //   909: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   912: pop
        //   913: aload #5
        //   915: aload_2
        //   916: ldc_w 'So_chon'
        //   919: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   922: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   925: pop
        //   926: aload #5
        //   928: ldc_w ','
        //   931: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   934: pop
        //   935: aload_0
        //   936: aload #5
        //   938: invokevirtual toString : ()Ljava/lang/String;
        //   941: putfield xuatDan : Ljava/lang/String;
        //   944: iinc #10, 1
        //   947: iload #18
        //   949: istore #17
        //   951: goto -> 954
        //   954: iinc #13, 1
        //   957: goto -> 729
        //   960: astore_1
        //   961: goto -> 1035
        //   964: aload_0
        //   965: getfield xuatDan : Ljava/lang/String;
        //   968: invokevirtual length : ()I
        //   971: iconst_4
        //   972: if_icmple -> 1027
        //   975: iload #10
        //   977: ifle -> 1027
        //   980: new java/lang/StringBuilder
        //   983: astore_1
        //   984: aload_1
        //   985: invokespecial <init> : ()V
        //   988: aload_1
        //   989: aload_0
        //   990: getfield xuatDan : Ljava/lang/String;
        //   993: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   996: pop
        //   997: aload_1
        //   998: ldc 'x'
        //   1000: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1003: pop
        //   1004: aload_1
        //   1005: iload #17
        //   1007: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1010: pop
        //   1011: aload_1
        //   1012: ldc_w 'n '
        //   1015: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1018: pop
        //   1019: aload_0
        //   1020: aload_1
        //   1021: invokevirtual toString : ()Ljava/lang/String;
        //   1024: putfield xuatDan : Ljava/lang/String;
        //   1027: goto -> 1039
        //   1030: astore_1
        //   1031: goto -> 1035
        //   1034: astore_1
        //   1035: aload_1
        //   1036: invokevirtual printStackTrace : ()V
        //   1039: aload_0
        //   1040: getfield xuatDan : Ljava/lang/String;
        //   1043: areturn
        //   1044: astore_1
        //   1045: aload #9
        //   1047: invokeinterface isClosed : ()Z
        //   1052: ifne -> 1062
        //   1055: aload #9
        //   1057: invokeinterface close : ()V
        //   1062: goto -> 1067
        //   1065: aload_1
        //   1066: athrow
        //   1067: goto -> 1065
        // Exception table:
        //   from	to	target	type
        //   98	156	183	org/json/JSONException
        //   98	156	179	finally
        //   184	188	1044	finally
        //   210	224	609	org/json/JSONException
        //   227	243	609	org/json/JSONException
        //   246	258	609	org/json/JSONException
        //   261	277	609	org/json/JSONException
        //   277	301	602	org/json/JSONException
        //   311	336	489	org/json/JSONException
        //   357	404	485	org/json/JSONException
        //   407	443	485	org/json/JSONException
        //   459	472	485	org/json/JSONException
        //   472	482	485	org/json/JSONException
        //   493	503	598	org/json/JSONException
        //   511	544	594	org/json/JSONException
        //   547	578	594	org/json/JSONException
        //   578	588	594	org/json/JSONException
        //   654	683	686	org/json/JSONException
        //   729	738	1034	org/json/JSONException
        //   745	766	960	org/json/JSONException
        //   778	788	889	org/json/JSONException
        //   788	883	1030	org/json/JSONException
        //   893	944	1030	org/json/JSONException
        //   964	975	1030	org/json/JSONException
        //   980	1027	1030	org/json/JSONException
    }

    public String TaoTinDe(String paramString) {
        // Byte code:
        //   0: ldc_w 'Se_chuyen'
        //   3: astore_2
        //   4: dconst_0
        //   5: dstore_3
        //   6: new org/json/JSONObject
        //   9: dup
        //   10: invokespecial <init> : ()V
        //   13: astore #5
        //   15: new tamhoang/ldpro4/MainActivity
        //   18: dup
        //   19: invokespecial <init> : ()V
        //   22: astore #6
        //   24: invokestatic Get_date : ()Ljava/lang/String;
        //   27: astore #7
        //   29: new java/lang/StringBuilder
        //   32: dup
        //   33: invokespecial <init> : ()V
        //   36: astore #8
        //   38: aload #8
        //   40: ldc_w 'Select so_chon, Sum(diem) FROM tbl_soctS Where ten_kh = ''
        //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   46: pop
        //   47: aload #8
        //   49: aload_1
        //   50: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   53: pop
        //   54: aload #8
        //   56: ldc_w '' AND type_kh = 2 AND (the_loai = 'deb' or the_loai = 'det') AND ngay_nhan = ''
        //   59: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   62: pop
        //   63: aload #8
        //   65: aload #7
        //   67: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   70: pop
        //   71: aload #8
        //   73: ldc_w '' Group by so_chon'
        //   76: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   79: pop
        //   80: aload #8
        //   82: invokevirtual toString : ()Ljava/lang/String;
        //   85: astore #8
        //   87: aload_0
        //   88: getfield db : Ltamhoang/ldpro4/data/Database;
        //   91: aload #8
        //   93: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   96: astore #9
        //   98: aload #9
        //   100: invokeinterface moveToNext : ()Z
        //   105: ifeq -> 159
        //   108: new org/json/JSONObject
        //   111: astore_1
        //   112: aload_1
        //   113: invokespecial <init> : ()V
        //   116: aload_1
        //   117: ldc_w 'Da_chuyen'
        //   120: aload #9
        //   122: iconst_1
        //   123: invokeinterface getInt : (I)I
        //   128: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   131: pop
        //   132: aload_1
        //   133: ldc_w 'Se_chuyen'
        //   136: iconst_0
        //   137: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   140: pop
        //   141: aload #5
        //   143: aload #9
        //   145: iconst_0
        //   146: invokeinterface getString : (I)Ljava/lang/String;
        //   151: aload_1
        //   152: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   155: pop
        //   156: goto -> 98
        //   159: aload #9
        //   161: invokeinterface isClosed : ()Z
        //   166: ifne -> 201
        //   169: aload #9
        //   171: invokeinterface close : ()V
        //   176: goto -> 201
        //   179: astore_1
        //   180: goto -> 1082
        //   183: astore_1
        //   184: aload_1
        //   185: invokevirtual printStackTrace : ()V
        //   188: aload #9
        //   190: invokeinterface isClosed : ()Z
        //   195: ifne -> 201
        //   198: goto -> 169
        //   201: iconst_0
        //   202: istore #10
        //   204: aload #7
        //   206: astore_1
        //   207: iload #10
        //   209: aload_0
        //   210: getfield mSo : Ljava/util/List;
        //   213: invokeinterface size : ()I
        //   218: if_icmpge -> 655
        //   221: aload_0
        //   222: getfield mSo : Ljava/util/List;
        //   225: iload #10
        //   227: invokeinterface get : (I)Ljava/lang/Object;
        //   232: checkcast java/lang/String
        //   235: astore #7
        //   237: new org/json/JSONObject
        //   240: astore #11
        //   242: aload_0
        //   243: getfield jsonKhongmax : Lorg/json/JSONObject;
        //   246: astore #9
        //   248: aload #11
        //   250: aload #9
        //   252: ldc_w 'soDe'
        //   255: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   258: invokespecial <init> : (Ljava/lang/String;)V
        //   261: aload #11
        //   263: aload_0
        //   264: getfield mSo : Ljava/util/List;
        //   267: iload #10
        //   269: invokeinterface get : (I)Ljava/lang/Object;
        //   274: checkcast java/lang/String
        //   277: invokevirtual has : (Ljava/lang/String;)Z
        //   280: istore #12
        //   282: iload #12
        //   284: ifeq -> 319
        //   287: aload #11
        //   289: aload_0
        //   290: getfield mSo : Ljava/util/List;
        //   293: iload #10
        //   295: invokeinterface get : (I)Ljava/lang/Object;
        //   300: checkcast java/lang/String
        //   303: invokevirtual getInt : (Ljava/lang/String;)I
        //   306: istore #13
        //   308: iload #13
        //   310: i2d
        //   311: dstore_3
        //   312: goto -> 323
        //   315: astore_1
        //   316: goto -> 659
        //   319: ldc2_w 100000.0
        //   322: dstore_3
        //   323: aload_0
        //   324: getfield mTienTon : Ljava/util/List;
        //   327: iload #10
        //   329: invokeinterface get : (I)Ljava/lang/Object;
        //   334: checkcast java/lang/String
        //   337: astore #9
        //   339: aload #9
        //   341: ldc_w '.'
        //   344: ldc ''
        //   346: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   349: invokestatic parseInt : (Ljava/lang/String;)I
        //   352: istore #13
        //   354: aload #5
        //   356: aload #7
        //   358: invokevirtual has : (Ljava/lang/String;)Z
        //   361: ifeq -> 538
        //   364: iload #13
        //   366: ifle -> 538
        //   369: new org/json/JSONObject
        //   372: astore #9
        //   374: aload #9
        //   376: invokespecial <init> : ()V
        //   379: aload #5
        //   381: aload #7
        //   383: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   386: ldc_w 'Da_chuyen'
        //   389: invokevirtual getDouble : (Ljava/lang/String;)D
        //   392: dstore #14
        //   394: iload #13
        //   396: i2d
        //   397: dstore #16
        //   399: dload #16
        //   401: invokestatic isNaN : (D)Z
        //   404: pop
        //   405: dload #14
        //   407: dload #16
        //   409: dadd
        //   410: dload_3
        //   411: dcmpg
        //   412: ifgt -> 465
        //   415: aload #9
        //   417: ldc_w 'So_chon'
        //   420: aload #7
        //   422: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   425: pop
        //   426: aload #9
        //   428: ldc_w 'Da_chuyen'
        //   431: aload #5
        //   433: aload #7
        //   435: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   438: ldc_w 'Da_chuyen'
        //   441: invokevirtual getInt : (Ljava/lang/String;)I
        //   444: iload #13
        //   446: iadd
        //   447: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   450: pop
        //   451: aload #9
        //   453: ldc_w 'Se_chuyen'
        //   456: iload #13
        //   458: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   461: pop
        //   462: goto -> 525
        //   465: aload #9
        //   467: ldc_w 'So_chon'
        //   470: aload #7
        //   472: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   475: pop
        //   476: aload #9
        //   478: ldc_w 'Da_chuyen'
        //   481: dload_3
        //   482: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   485: pop
        //   486: aload #5
        //   488: aload #7
        //   490: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   493: ldc_w 'Da_chuyen'
        //   496: invokevirtual getInt : (Ljava/lang/String;)I
        //   499: istore #13
        //   501: iload #13
        //   503: i2d
        //   504: dstore #14
        //   506: dload #14
        //   508: invokestatic isNaN : (D)Z
        //   511: pop
        //   512: aload #9
        //   514: ldc_w 'Se_chuyen'
        //   517: dload_3
        //   518: dload #14
        //   520: dsub
        //   521: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   524: pop
        //   525: aload #5
        //   527: aload #7
        //   529: aload #9
        //   531: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   534: pop
        //   535: goto -> 633
        //   538: new org/json/JSONObject
        //   541: astore #9
        //   543: aload #9
        //   545: invokespecial <init> : ()V
        //   548: iload #13
        //   550: i2d
        //   551: dload_3
        //   552: dcmpg
        //   553: ifgt -> 592
        //   556: aload #9
        //   558: ldc_w 'So_chon'
        //   561: aload #7
        //   563: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   566: pop
        //   567: aload #9
        //   569: ldc_w 'Da_chuyen'
        //   572: iload #13
        //   574: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   577: pop
        //   578: aload #9
        //   580: ldc_w 'Se_chuyen'
        //   583: iload #13
        //   585: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   588: pop
        //   589: goto -> 623
        //   592: aload #9
        //   594: ldc_w 'So_chon'
        //   597: aload #7
        //   599: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   602: pop
        //   603: aload #9
        //   605: ldc_w 'Da_chuyen'
        //   608: dload_3
        //   609: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   612: pop
        //   613: aload #9
        //   615: ldc_w 'Se_chuyen'
        //   618: dload_3
        //   619: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   622: pop
        //   623: aload #5
        //   625: aload #7
        //   627: aload #9
        //   629: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   632: pop
        //   633: iinc #10, 1
        //   636: goto -> 207
        //   639: astore_1
        //   640: goto -> 659
        //   643: astore_1
        //   644: goto -> 659
        //   647: astore_1
        //   648: goto -> 659
        //   651: astore_1
        //   652: goto -> 659
        //   655: goto -> 663
        //   658: astore_1
        //   659: aload_1
        //   660: invokevirtual printStackTrace : ()V
        //   663: aload #5
        //   665: invokevirtual keys : ()Ljava/util/Iterator;
        //   668: astore_1
        //   669: new java/util/ArrayList
        //   672: dup
        //   673: invokespecial <init> : ()V
        //   676: astore #8
        //   678: aload_1
        //   679: invokeinterface hasNext : ()Z
        //   684: ifeq -> 742
        //   687: aload_1
        //   688: invokeinterface next : ()Ljava/lang/Object;
        //   693: checkcast java/lang/String
        //   696: astore #6
        //   698: aload #5
        //   700: aload #6
        //   702: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   705: ldc_w 'Se_chuyen'
        //   708: invokevirtual getInt : (Ljava/lang/String;)I
        //   711: ifle -> 729
        //   714: aload #8
        //   716: aload #5
        //   718: aload #6
        //   720: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   723: invokeinterface add : (Ljava/lang/Object;)Z
        //   728: pop
        //   729: goto -> 739
        //   732: astore #6
        //   734: aload #6
        //   736: invokevirtual printStackTrace : ()V
        //   739: goto -> 678
        //   742: aload #8
        //   744: new tamhoang/ldpro4/Fragment/Frag_CanChuyen$17
        //   747: dup
        //   748: aload_0
        //   749: invokespecial <init> : (Ltamhoang/ldpro4/Fragment/Frag_CanChuyen;)V
        //   752: invokestatic sort : (Ljava/util/List;Ljava/util/Comparator;)V
        //   755: aload_0
        //   756: ldc_w 'De:'
        //   759: putfield xuatDan : Ljava/lang/String;
        //   762: iconst_0
        //   763: istore #18
        //   765: iconst_0
        //   766: istore #13
        //   768: iconst_0
        //   769: istore #10
        //   771: aload_2
        //   772: astore #6
        //   774: aload #8
        //   776: invokeinterface size : ()I
        //   781: istore #19
        //   783: iload #13
        //   785: iload #19
        //   787: if_icmpge -> 1001
        //   790: aload #8
        //   792: iload #13
        //   794: invokeinterface get : (I)Ljava/lang/Object;
        //   799: checkcast org/json/JSONObject
        //   802: astore_2
        //   803: aload_2
        //   804: aload #6
        //   806: invokevirtual getInt : (Ljava/lang/String;)I
        //   809: istore #19
        //   811: iload #19
        //   813: ifle -> 995
        //   816: iload #18
        //   818: iload #19
        //   820: if_icmple -> 934
        //   823: new java/lang/StringBuilder
        //   826: astore #7
        //   828: aload #7
        //   830: invokespecial <init> : ()V
        //   833: aload #7
        //   835: aload_0
        //   836: getfield xuatDan : Ljava/lang/String;
        //   839: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   842: pop
        //   843: aload #7
        //   845: ldc 'x'
        //   847: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   850: pop
        //   851: aload #7
        //   853: iload #18
        //   855: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   858: pop
        //   859: aload #7
        //   861: ldc_w 'n '
        //   864: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   867: pop
        //   868: aload_0
        //   869: aload #7
        //   871: invokevirtual toString : ()Ljava/lang/String;
        //   874: putfield xuatDan : Ljava/lang/String;
        //   877: new java/lang/StringBuilder
        //   880: astore #7
        //   882: aload #7
        //   884: invokespecial <init> : ()V
        //   887: aload #7
        //   889: aload_0
        //   890: getfield xuatDan : Ljava/lang/String;
        //   893: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   896: pop
        //   897: aload #7
        //   899: aload_2
        //   900: ldc_w 'So_chon'
        //   903: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   906: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   909: pop
        //   910: aload #7
        //   912: ldc_w ','
        //   915: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   918: pop
        //   919: aload_0
        //   920: aload #7
        //   922: invokevirtual toString : ()Ljava/lang/String;
        //   925: putfield xuatDan : Ljava/lang/String;
        //   928: iconst_0
        //   929: istore #10
        //   931: goto -> 985
        //   934: new java/lang/StringBuilder
        //   937: astore #7
        //   939: aload #7
        //   941: invokespecial <init> : ()V
        //   944: aload #7
        //   946: aload_0
        //   947: getfield xuatDan : Ljava/lang/String;
        //   950: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   953: pop
        //   954: aload #7
        //   956: aload_2
        //   957: ldc_w 'So_chon'
        //   960: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   963: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   966: pop
        //   967: aload #7
        //   969: ldc_w ','
        //   972: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   975: pop
        //   976: aload_0
        //   977: aload #7
        //   979: invokevirtual toString : ()Ljava/lang/String;
        //   982: putfield xuatDan : Ljava/lang/String;
        //   985: iinc #10, 1
        //   988: iload #19
        //   990: istore #18
        //   992: goto -> 995
        //   995: iinc #13, 1
        //   998: goto -> 774
        //   1001: aload_0
        //   1002: getfield xuatDan : Ljava/lang/String;
        //   1005: invokevirtual length : ()I
        //   1008: iconst_4
        //   1009: if_icmple -> 1064
        //   1012: iload #10
        //   1014: ifle -> 1064
        //   1017: new java/lang/StringBuilder
        //   1020: astore_1
        //   1021: aload_1
        //   1022: invokespecial <init> : ()V
        //   1025: aload_1
        //   1026: aload_0
        //   1027: getfield xuatDan : Ljava/lang/String;
        //   1030: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1033: pop
        //   1034: aload_1
        //   1035: ldc 'x'
        //   1037: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1040: pop
        //   1041: aload_1
        //   1042: iload #18
        //   1044: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1047: pop
        //   1048: aload_1
        //   1049: ldc_w 'n '
        //   1052: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1055: pop
        //   1056: aload_0
        //   1057: aload_1
        //   1058: invokevirtual toString : ()Ljava/lang/String;
        //   1061: putfield xuatDan : Ljava/lang/String;
        //   1064: goto -> 1076
        //   1067: astore_1
        //   1068: goto -> 1072
        //   1071: astore_1
        //   1072: aload_1
        //   1073: invokevirtual printStackTrace : ()V
        //   1076: aload_0
        //   1077: getfield xuatDan : Ljava/lang/String;
        //   1080: areturn
        //   1081: astore_1
        //   1082: aload #9
        //   1084: invokeinterface isClosed : ()Z
        //   1089: ifne -> 1099
        //   1092: aload #9
        //   1094: invokeinterface close : ()V
        //   1099: goto -> 1104
        //   1102: aload_1
        //   1103: athrow
        //   1104: goto -> 1102
        // Exception table:
        //   from	to	target	type
        //   98	156	183	org/json/JSONException
        //   98	156	179	finally
        //   184	188	1081	finally
        //   207	248	658	org/json/JSONException
        //   248	282	651	org/json/JSONException
        //   287	308	315	org/json/JSONException
        //   323	339	647	org/json/JSONException
        //   339	364	643	org/json/JSONException
        //   369	394	643	org/json/JSONException
        //   415	462	639	org/json/JSONException
        //   465	501	639	org/json/JSONException
        //   512	525	639	org/json/JSONException
        //   525	535	639	org/json/JSONException
        //   538	548	639	org/json/JSONException
        //   556	589	639	org/json/JSONException
        //   592	623	639	org/json/JSONException
        //   623	633	639	org/json/JSONException
        //   698	729	732	org/json/JSONException
        //   774	783	1071	org/json/JSONException
        //   790	811	1071	org/json/JSONException
        //   823	928	1067	org/json/JSONException
        //   934	985	1067	org/json/JSONException
        //   1001	1012	1067	org/json/JSONException
        //   1017	1064	1067	org/json/JSONException
    }

    public String TaoTinLo(String paramString) {
        // Byte code:
        //   0: ldc_w 'Se_chuyen'
        //   3: astore_2
        //   4: dconst_0
        //   5: dstore_3
        //   6: new org/json/JSONObject
        //   9: dup
        //   10: invokespecial <init> : ()V
        //   13: astore #5
        //   15: new tamhoang/ldpro4/MainActivity
        //   18: dup
        //   19: invokespecial <init> : ()V
        //   22: astore #6
        //   24: invokestatic Get_date : ()Ljava/lang/String;
        //   27: astore #7
        //   29: new java/lang/StringBuilder
        //   32: dup
        //   33: invokespecial <init> : ()V
        //   36: astore #8
        //   38: aload #8
        //   40: ldc_w 'Select so_chon, Sum(diem) FROM tbl_soctS Where ten_kh = ''
        //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   46: pop
        //   47: aload #8
        //   49: aload_1
        //   50: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   53: pop
        //   54: aload #8
        //   56: ldc_w '' AND type_kh = 2 AND the_loai = 'lo' AND ngay_nhan = ''
        //   59: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   62: pop
        //   63: aload #8
        //   65: aload #7
        //   67: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   70: pop
        //   71: aload #8
        //   73: ldc_w '' Group by so_chon'
        //   76: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   79: pop
        //   80: aload #8
        //   82: invokevirtual toString : ()Ljava/lang/String;
        //   85: astore #8
        //   87: aload_0
        //   88: getfield db : Ltamhoang/ldpro4/data/Database;
        //   91: aload #8
        //   93: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   96: astore #9
        //   98: aload #9
        //   100: invokeinterface moveToNext : ()Z
        //   105: ifeq -> 159
        //   108: new org/json/JSONObject
        //   111: astore_1
        //   112: aload_1
        //   113: invokespecial <init> : ()V
        //   116: aload_1
        //   117: ldc_w 'Da_chuyen'
        //   120: aload #9
        //   122: iconst_1
        //   123: invokeinterface getInt : (I)I
        //   128: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   131: pop
        //   132: aload_1
        //   133: ldc_w 'Se_chuyen'
        //   136: iconst_0
        //   137: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   140: pop
        //   141: aload #5
        //   143: aload #9
        //   145: iconst_0
        //   146: invokeinterface getString : (I)Ljava/lang/String;
        //   151: aload_1
        //   152: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   155: pop
        //   156: goto -> 98
        //   159: aload #9
        //   161: invokeinterface isClosed : ()Z
        //   166: ifne -> 201
        //   169: aload #9
        //   171: invokeinterface close : ()V
        //   176: goto -> 201
        //   179: astore_1
        //   180: goto -> 1082
        //   183: astore_1
        //   184: aload_1
        //   185: invokevirtual printStackTrace : ()V
        //   188: aload #9
        //   190: invokeinterface isClosed : ()Z
        //   195: ifne -> 201
        //   198: goto -> 169
        //   201: iconst_0
        //   202: istore #10
        //   204: aload #7
        //   206: astore_1
        //   207: iload #10
        //   209: aload_0
        //   210: getfield mSo : Ljava/util/List;
        //   213: invokeinterface size : ()I
        //   218: if_icmpge -> 655
        //   221: aload_0
        //   222: getfield mSo : Ljava/util/List;
        //   225: iload #10
        //   227: invokeinterface get : (I)Ljava/lang/Object;
        //   232: checkcast java/lang/String
        //   235: astore #7
        //   237: new org/json/JSONObject
        //   240: astore #9
        //   242: aload_0
        //   243: getfield jsonKhongmax : Lorg/json/JSONObject;
        //   246: astore #11
        //   248: aload #9
        //   250: aload #11
        //   252: ldc_w 'soLo'
        //   255: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   258: invokespecial <init> : (Ljava/lang/String;)V
        //   261: aload #9
        //   263: aload_0
        //   264: getfield mSo : Ljava/util/List;
        //   267: iload #10
        //   269: invokeinterface get : (I)Ljava/lang/Object;
        //   274: checkcast java/lang/String
        //   277: invokevirtual has : (Ljava/lang/String;)Z
        //   280: istore #12
        //   282: iload #12
        //   284: ifeq -> 319
        //   287: aload #9
        //   289: aload_0
        //   290: getfield mSo : Ljava/util/List;
        //   293: iload #10
        //   295: invokeinterface get : (I)Ljava/lang/Object;
        //   300: checkcast java/lang/String
        //   303: invokevirtual getInt : (Ljava/lang/String;)I
        //   306: istore #13
        //   308: iload #13
        //   310: i2d
        //   311: dstore_3
        //   312: goto -> 323
        //   315: astore_1
        //   316: goto -> 659
        //   319: ldc2_w 100000.0
        //   322: dstore_3
        //   323: aload_0
        //   324: getfield mTienTon : Ljava/util/List;
        //   327: iload #10
        //   329: invokeinterface get : (I)Ljava/lang/Object;
        //   334: checkcast java/lang/String
        //   337: astore #9
        //   339: aload #9
        //   341: ldc_w '.'
        //   344: ldc ''
        //   346: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   349: invokestatic parseInt : (Ljava/lang/String;)I
        //   352: istore #13
        //   354: aload #5
        //   356: aload #7
        //   358: invokevirtual has : (Ljava/lang/String;)Z
        //   361: ifeq -> 538
        //   364: iload #13
        //   366: ifle -> 538
        //   369: new org/json/JSONObject
        //   372: astore #9
        //   374: aload #9
        //   376: invokespecial <init> : ()V
        //   379: aload #5
        //   381: aload #7
        //   383: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   386: ldc_w 'Da_chuyen'
        //   389: invokevirtual getDouble : (Ljava/lang/String;)D
        //   392: dstore #14
        //   394: iload #13
        //   396: i2d
        //   397: dstore #16
        //   399: dload #16
        //   401: invokestatic isNaN : (D)Z
        //   404: pop
        //   405: dload #14
        //   407: dload #16
        //   409: dadd
        //   410: dload_3
        //   411: dcmpg
        //   412: ifgt -> 465
        //   415: aload #9
        //   417: ldc_w 'So_chon'
        //   420: aload #7
        //   422: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   425: pop
        //   426: aload #9
        //   428: ldc_w 'Da_chuyen'
        //   431: aload #5
        //   433: aload #7
        //   435: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   438: ldc_w 'Da_chuyen'
        //   441: invokevirtual getInt : (Ljava/lang/String;)I
        //   444: iload #13
        //   446: iadd
        //   447: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   450: pop
        //   451: aload #9
        //   453: ldc_w 'Se_chuyen'
        //   456: iload #13
        //   458: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   461: pop
        //   462: goto -> 525
        //   465: aload #9
        //   467: ldc_w 'So_chon'
        //   470: aload #7
        //   472: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   475: pop
        //   476: aload #9
        //   478: ldc_w 'Da_chuyen'
        //   481: dload_3
        //   482: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   485: pop
        //   486: aload #5
        //   488: aload #7
        //   490: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   493: ldc_w 'Da_chuyen'
        //   496: invokevirtual getInt : (Ljava/lang/String;)I
        //   499: istore #13
        //   501: iload #13
        //   503: i2d
        //   504: dstore #14
        //   506: dload #14
        //   508: invokestatic isNaN : (D)Z
        //   511: pop
        //   512: aload #9
        //   514: ldc_w 'Se_chuyen'
        //   517: dload_3
        //   518: dload #14
        //   520: dsub
        //   521: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   524: pop
        //   525: aload #5
        //   527: aload #7
        //   529: aload #9
        //   531: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   534: pop
        //   535: goto -> 633
        //   538: new org/json/JSONObject
        //   541: astore #9
        //   543: aload #9
        //   545: invokespecial <init> : ()V
        //   548: iload #13
        //   550: i2d
        //   551: dload_3
        //   552: dcmpg
        //   553: ifgt -> 592
        //   556: aload #9
        //   558: ldc_w 'So_chon'
        //   561: aload #7
        //   563: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   566: pop
        //   567: aload #9
        //   569: ldc_w 'Da_chuyen'
        //   572: iload #13
        //   574: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   577: pop
        //   578: aload #9
        //   580: ldc_w 'Se_chuyen'
        //   583: iload #13
        //   585: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   588: pop
        //   589: goto -> 623
        //   592: aload #9
        //   594: ldc_w 'So_chon'
        //   597: aload #7
        //   599: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   602: pop
        //   603: aload #9
        //   605: ldc_w 'Da_chuyen'
        //   608: dload_3
        //   609: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   612: pop
        //   613: aload #9
        //   615: ldc_w 'Se_chuyen'
        //   618: dload_3
        //   619: invokevirtual put : (Ljava/lang/String;D)Lorg/json/JSONObject;
        //   622: pop
        //   623: aload #5
        //   625: aload #7
        //   627: aload #9
        //   629: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   632: pop
        //   633: iinc #10, 1
        //   636: goto -> 207
        //   639: astore_1
        //   640: goto -> 659
        //   643: astore_1
        //   644: goto -> 659
        //   647: astore_1
        //   648: goto -> 659
        //   651: astore_1
        //   652: goto -> 659
        //   655: goto -> 663
        //   658: astore_1
        //   659: aload_1
        //   660: invokevirtual printStackTrace : ()V
        //   663: aload #5
        //   665: invokevirtual keys : ()Ljava/util/Iterator;
        //   668: astore_1
        //   669: new java/util/ArrayList
        //   672: dup
        //   673: invokespecial <init> : ()V
        //   676: astore #6
        //   678: aload_1
        //   679: invokeinterface hasNext : ()Z
        //   684: ifeq -> 742
        //   687: aload_1
        //   688: invokeinterface next : ()Ljava/lang/Object;
        //   693: checkcast java/lang/String
        //   696: astore #8
        //   698: aload #5
        //   700: aload #8
        //   702: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   705: ldc_w 'Se_chuyen'
        //   708: invokevirtual getInt : (Ljava/lang/String;)I
        //   711: ifle -> 729
        //   714: aload #6
        //   716: aload #5
        //   718: aload #8
        //   720: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   723: invokeinterface add : (Ljava/lang/Object;)Z
        //   728: pop
        //   729: goto -> 739
        //   732: astore #8
        //   734: aload #8
        //   736: invokevirtual printStackTrace : ()V
        //   739: goto -> 678
        //   742: aload #6
        //   744: new tamhoang/ldpro4/Fragment/Frag_CanChuyen$18
        //   747: dup
        //   748: aload_0
        //   749: invokespecial <init> : (Ltamhoang/ldpro4/Fragment/Frag_CanChuyen;)V
        //   752: invokestatic sort : (Ljava/util/List;Ljava/util/Comparator;)V
        //   755: aload_0
        //   756: ldc_w 'Lo:'
        //   759: putfield xuatDan : Ljava/lang/String;
        //   762: iconst_0
        //   763: istore #18
        //   765: iconst_0
        //   766: istore #13
        //   768: iconst_0
        //   769: istore #10
        //   771: aload_2
        //   772: astore #8
        //   774: aload #6
        //   776: invokeinterface size : ()I
        //   781: istore #19
        //   783: iload #13
        //   785: iload #19
        //   787: if_icmpge -> 1001
        //   790: aload #6
        //   792: iload #13
        //   794: invokeinterface get : (I)Ljava/lang/Object;
        //   799: checkcast org/json/JSONObject
        //   802: astore_2
        //   803: aload_2
        //   804: aload #8
        //   806: invokevirtual getInt : (Ljava/lang/String;)I
        //   809: istore #19
        //   811: iload #19
        //   813: ifle -> 995
        //   816: iload #18
        //   818: iload #19
        //   820: if_icmple -> 934
        //   823: new java/lang/StringBuilder
        //   826: astore #7
        //   828: aload #7
        //   830: invokespecial <init> : ()V
        //   833: aload #7
        //   835: aload_0
        //   836: getfield xuatDan : Ljava/lang/String;
        //   839: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   842: pop
        //   843: aload #7
        //   845: ldc 'x'
        //   847: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   850: pop
        //   851: aload #7
        //   853: iload #18
        //   855: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   858: pop
        //   859: aload #7
        //   861: ldc_w 'd '
        //   864: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   867: pop
        //   868: aload_0
        //   869: aload #7
        //   871: invokevirtual toString : ()Ljava/lang/String;
        //   874: putfield xuatDan : Ljava/lang/String;
        //   877: new java/lang/StringBuilder
        //   880: astore #7
        //   882: aload #7
        //   884: invokespecial <init> : ()V
        //   887: aload #7
        //   889: aload_0
        //   890: getfield xuatDan : Ljava/lang/String;
        //   893: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   896: pop
        //   897: aload #7
        //   899: aload_2
        //   900: ldc_w 'So_chon'
        //   903: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   906: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   909: pop
        //   910: aload #7
        //   912: ldc_w ','
        //   915: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   918: pop
        //   919: aload_0
        //   920: aload #7
        //   922: invokevirtual toString : ()Ljava/lang/String;
        //   925: putfield xuatDan : Ljava/lang/String;
        //   928: iconst_0
        //   929: istore #10
        //   931: goto -> 985
        //   934: new java/lang/StringBuilder
        //   937: astore #7
        //   939: aload #7
        //   941: invokespecial <init> : ()V
        //   944: aload #7
        //   946: aload_0
        //   947: getfield xuatDan : Ljava/lang/String;
        //   950: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   953: pop
        //   954: aload #7
        //   956: aload_2
        //   957: ldc_w 'So_chon'
        //   960: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   963: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   966: pop
        //   967: aload #7
        //   969: ldc_w ','
        //   972: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   975: pop
        //   976: aload_0
        //   977: aload #7
        //   979: invokevirtual toString : ()Ljava/lang/String;
        //   982: putfield xuatDan : Ljava/lang/String;
        //   985: iinc #10, 1
        //   988: iload #19
        //   990: istore #18
        //   992: goto -> 995
        //   995: iinc #13, 1
        //   998: goto -> 774
        //   1001: aload_0
        //   1002: getfield xuatDan : Ljava/lang/String;
        //   1005: invokevirtual length : ()I
        //   1008: iconst_4
        //   1009: if_icmple -> 1064
        //   1012: iload #10
        //   1014: ifle -> 1064
        //   1017: new java/lang/StringBuilder
        //   1020: astore_1
        //   1021: aload_1
        //   1022: invokespecial <init> : ()V
        //   1025: aload_1
        //   1026: aload_0
        //   1027: getfield xuatDan : Ljava/lang/String;
        //   1030: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1033: pop
        //   1034: aload_1
        //   1035: ldc 'x'
        //   1037: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1040: pop
        //   1041: aload_1
        //   1042: iload #18
        //   1044: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1047: pop
        //   1048: aload_1
        //   1049: ldc_w 'd '
        //   1052: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1055: pop
        //   1056: aload_0
        //   1057: aload_1
        //   1058: invokevirtual toString : ()Ljava/lang/String;
        //   1061: putfield xuatDan : Ljava/lang/String;
        //   1064: goto -> 1076
        //   1067: astore_1
        //   1068: goto -> 1072
        //   1071: astore_1
        //   1072: aload_1
        //   1073: invokevirtual printStackTrace : ()V
        //   1076: aload_0
        //   1077: getfield xuatDan : Ljava/lang/String;
        //   1080: areturn
        //   1081: astore_1
        //   1082: aload #9
        //   1084: invokeinterface isClosed : ()Z
        //   1089: ifne -> 1099
        //   1092: aload #9
        //   1094: invokeinterface close : ()V
        //   1099: goto -> 1104
        //   1102: aload_1
        //   1103: athrow
        //   1104: goto -> 1102
        // Exception table:
        //   from	to	target	type
        //   98	156	183	org/json/JSONException
        //   98	156	179	finally
        //   184	188	1081	finally
        //   207	248	658	org/json/JSONException
        //   248	282	651	org/json/JSONException
        //   287	308	315	org/json/JSONException
        //   323	339	647	org/json/JSONException
        //   339	364	643	org/json/JSONException
        //   369	394	643	org/json/JSONException
        //   415	462	639	org/json/JSONException
        //   465	501	639	org/json/JSONException
        //   512	525	639	org/json/JSONException
        //   525	535	639	org/json/JSONException
        //   538	548	639	org/json/JSONException
        //   556	589	639	org/json/JSONException
        //   592	623	639	org/json/JSONException
        //   623	633	639	org/json/JSONException
        //   698	729	732	org/json/JSONException
        //   774	783	1071	org/json/JSONException
        //   790	811	1071	org/json/JSONException
        //   823	928	1067	org/json/JSONException
        //   934	985	1067	org/json/JSONException
        //   1001	1012	1067	org/json/JSONException
        //   1017	1064	1067	org/json/JSONException
    }

    public String TaoTinXi(String paramString) {
        // Byte code:
        //   0: ldc_w 'xien4'
        //   3: astore_2
        //   4: ldc_w 'xien3'
        //   7: astore_3
        //   8: ldc_w 'xien2'
        //   11: astore #4
        //   13: new org/json/JSONObject
        //   16: dup
        //   17: invokespecial <init> : ()V
        //   20: astore #5
        //   22: new tamhoang/ldpro4/MainActivity
        //   25: dup
        //   26: invokespecial <init> : ()V
        //   29: astore #6
        //   31: invokestatic Get_date : ()Ljava/lang/String;
        //   34: astore #7
        //   36: new java/lang/StringBuilder
        //   39: dup
        //   40: invokespecial <init> : ()V
        //   43: astore #8
        //   45: aload #8
        //   47: ldc_w 'Select so_chon, Sum(diem) FROM tbl_soctS Where ten_kh = ''
        //   50: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   53: pop
        //   54: aload #8
        //   56: aload_1
        //   57: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   60: pop
        //   61: aload #8
        //   63: ldc_w '' AND type_kh = 2 AND the_loai = 'xi' AND ngay_nhan = ''
        //   66: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   69: pop
        //   70: aload #8
        //   72: aload #7
        //   74: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   77: pop
        //   78: aload #8
        //   80: ldc_w '' Group by so_chon'
        //   83: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   86: pop
        //   87: aload #8
        //   89: invokevirtual toString : ()Ljava/lang/String;
        //   92: astore_1
        //   93: aload_0
        //   94: getfield db : Ltamhoang/ldpro4/data/Database;
        //   97: aload_1
        //   98: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   101: astore #8
        //   103: aload #8
        //   105: invokeinterface moveToNext : ()Z
        //   110: ifeq -> 164
        //   113: new org/json/JSONObject
        //   116: astore_1
        //   117: aload_1
        //   118: invokespecial <init> : ()V
        //   121: aload_1
        //   122: ldc_w 'Da_chuyen'
        //   125: aload #8
        //   127: iconst_1
        //   128: invokeinterface getInt : (I)I
        //   133: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   136: pop
        //   137: aload_1
        //   138: ldc_w 'Se_chuyen'
        //   141: iconst_0
        //   142: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   145: pop
        //   146: aload #5
        //   148: aload #8
        //   150: iconst_0
        //   151: invokeinterface getString : (I)Ljava/lang/String;
        //   156: aload_1
        //   157: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   160: pop
        //   161: goto -> 103
        //   164: aload #8
        //   166: invokeinterface isClosed : ()Z
        //   171: ifne -> 206
        //   174: aload #8
        //   176: invokeinterface close : ()V
        //   181: goto -> 206
        //   184: astore_1
        //   185: goto -> 1012
        //   188: astore_1
        //   189: aload_1
        //   190: invokevirtual printStackTrace : ()V
        //   193: aload #8
        //   195: invokeinterface isClosed : ()Z
        //   200: ifne -> 206
        //   203: goto -> 174
        //   206: iconst_0
        //   207: istore #9
        //   209: aload #4
        //   211: astore_1
        //   212: iload #9
        //   214: aload_0
        //   215: getfield mSo : Ljava/util/List;
        //   218: invokeinterface size : ()I
        //   223: if_icmpge -> 686
        //   226: aload_0
        //   227: getfield mSo : Ljava/util/List;
        //   230: iload #9
        //   232: invokeinterface get : (I)Ljava/lang/Object;
        //   237: checkcast java/lang/String
        //   240: astore #4
        //   242: iconst_0
        //   243: istore #10
        //   245: aload #4
        //   247: invokevirtual length : ()I
        //   250: istore #11
        //   252: iload #11
        //   254: iconst_5
        //   255: if_icmpne -> 290
        //   258: aload_0
        //   259: getfield jsonKhongmax : Lorg/json/JSONObject;
        //   262: aload_1
        //   263: invokevirtual getInt : (Ljava/lang/String;)I
        //   266: ifne -> 277
        //   269: ldc_w 100000
        //   272: istore #10
        //   274: goto -> 371
        //   277: aload_0
        //   278: getfield jsonKhongmax : Lorg/json/JSONObject;
        //   281: aload_1
        //   282: invokevirtual getInt : (Ljava/lang/String;)I
        //   285: istore #10
        //   287: goto -> 371
        //   290: aload #4
        //   292: invokevirtual length : ()I
        //   295: bipush #8
        //   297: if_icmpne -> 332
        //   300: aload_0
        //   301: getfield jsonKhongmax : Lorg/json/JSONObject;
        //   304: aload_3
        //   305: invokevirtual getInt : (Ljava/lang/String;)I
        //   308: ifne -> 319
        //   311: ldc_w 100000
        //   314: istore #10
        //   316: goto -> 371
        //   319: aload_0
        //   320: getfield jsonKhongmax : Lorg/json/JSONObject;
        //   323: aload_3
        //   324: invokevirtual getInt : (Ljava/lang/String;)I
        //   327: istore #10
        //   329: goto -> 371
        //   332: aload #4
        //   334: invokevirtual length : ()I
        //   337: bipush #11
        //   339: if_icmpne -> 371
        //   342: aload_0
        //   343: getfield jsonKhongmax : Lorg/json/JSONObject;
        //   346: aload_2
        //   347: invokevirtual getInt : (Ljava/lang/String;)I
        //   350: ifne -> 361
        //   353: ldc_w 100000
        //   356: istore #10
        //   358: goto -> 371
        //   361: aload_0
        //   362: getfield jsonKhongmax : Lorg/json/JSONObject;
        //   365: aload_2
        //   366: invokevirtual getInt : (Ljava/lang/String;)I
        //   369: istore #10
        //   371: aload_0
        //   372: getfield mTienTon : Ljava/util/List;
        //   375: iload #9
        //   377: invokeinterface get : (I)Ljava/lang/Object;
        //   382: checkcast java/lang/String
        //   385: ldc_w '.'
        //   388: ldc ''
        //   390: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   393: invokestatic parseInt : (Ljava/lang/String;)I
        //   396: istore #11
        //   398: aload #5
        //   400: aload #4
        //   402: invokevirtual has : (Ljava/lang/String;)Z
        //   405: ifeq -> 571
        //   408: iload #11
        //   410: ifle -> 571
        //   413: new org/json/JSONObject
        //   416: astore #8
        //   418: aload #8
        //   420: invokespecial <init> : ()V
        //   423: aload #5
        //   425: aload #4
        //   427: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   430: ldc_w 'Da_chuyen'
        //   433: invokevirtual getDouble : (Ljava/lang/String;)D
        //   436: dstore #12
        //   438: iload #11
        //   440: i2d
        //   441: dstore #14
        //   443: dload #14
        //   445: invokestatic isNaN : (D)Z
        //   448: pop
        //   449: dload #12
        //   451: dload #14
        //   453: dadd
        //   454: iload #10
        //   456: i2d
        //   457: dcmpg
        //   458: ifgt -> 511
        //   461: aload #8
        //   463: ldc_w 'So_chon'
        //   466: aload #4
        //   468: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   471: pop
        //   472: aload #8
        //   474: ldc_w 'Da_chuyen'
        //   477: aload #5
        //   479: aload #4
        //   481: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   484: ldc_w 'Da_chuyen'
        //   487: invokevirtual getInt : (Ljava/lang/String;)I
        //   490: iload #11
        //   492: iadd
        //   493: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   496: pop
        //   497: aload #8
        //   499: ldc_w 'Se_chuyen'
        //   502: iload #11
        //   504: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   507: pop
        //   508: goto -> 558
        //   511: aload #8
        //   513: ldc_w 'So_chon'
        //   516: aload #4
        //   518: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   521: pop
        //   522: aload #8
        //   524: ldc_w 'Da_chuyen'
        //   527: iload #10
        //   529: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   532: pop
        //   533: aload #8
        //   535: ldc_w 'Se_chuyen'
        //   538: iload #10
        //   540: aload #5
        //   542: aload #4
        //   544: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   547: ldc_w 'Da_chuyen'
        //   550: invokevirtual getInt : (Ljava/lang/String;)I
        //   553: isub
        //   554: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   557: pop
        //   558: aload #5
        //   560: aload #4
        //   562: aload #8
        //   564: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   567: pop
        //   568: goto -> 672
        //   571: iload #11
        //   573: ifle -> 686
        //   576: new org/json/JSONObject
        //   579: astore #8
        //   581: aload #8
        //   583: invokespecial <init> : ()V
        //   586: iload #11
        //   588: iload #10
        //   590: if_icmpgt -> 629
        //   593: aload #8
        //   595: ldc_w 'So_chon'
        //   598: aload #4
        //   600: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   603: pop
        //   604: aload #8
        //   606: ldc_w 'Da_chuyen'
        //   609: iload #11
        //   611: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   614: pop
        //   615: aload #8
        //   617: ldc_w 'Se_chuyen'
        //   620: iload #11
        //   622: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   625: pop
        //   626: goto -> 662
        //   629: aload #8
        //   631: ldc_w 'So_chon'
        //   634: aload #4
        //   636: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   639: pop
        //   640: aload #8
        //   642: ldc_w 'Da_chuyen'
        //   645: iload #10
        //   647: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   650: pop
        //   651: aload #8
        //   653: ldc_w 'Se_chuyen'
        //   656: iload #10
        //   658: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   661: pop
        //   662: aload #5
        //   664: aload #4
        //   666: aload #8
        //   668: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   671: pop
        //   672: iinc #9, 1
        //   675: goto -> 212
        //   678: astore_1
        //   679: goto -> 690
        //   682: astore_1
        //   683: goto -> 690
        //   686: goto -> 694
        //   689: astore_1
        //   690: aload_1
        //   691: invokevirtual printStackTrace : ()V
        //   694: aload #5
        //   696: invokevirtual keys : ()Ljava/util/Iterator;
        //   699: astore #7
        //   701: new java/util/ArrayList
        //   704: dup
        //   705: invokespecial <init> : ()V
        //   708: astore_1
        //   709: aload #7
        //   711: invokeinterface hasNext : ()Z
        //   716: ifeq -> 774
        //   719: aload #7
        //   721: invokeinterface next : ()Ljava/lang/Object;
        //   726: checkcast java/lang/String
        //   729: astore #6
        //   731: aload #5
        //   733: aload #6
        //   735: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   738: ldc_w 'Se_chuyen'
        //   741: invokevirtual getInt : (Ljava/lang/String;)I
        //   744: ifle -> 761
        //   747: aload_1
        //   748: aload #5
        //   750: aload #6
        //   752: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   755: invokeinterface add : (Ljava/lang/Object;)Z
        //   760: pop
        //   761: goto -> 771
        //   764: astore #6
        //   766: aload #6
        //   768: invokevirtual printStackTrace : ()V
        //   771: goto -> 709
        //   774: aload_1
        //   775: new tamhoang/ldpro4/Fragment/Frag_CanChuyen$20
        //   778: dup
        //   779: aload_0
        //   780: invokespecial <init> : (Ltamhoang/ldpro4/Fragment/Frag_CanChuyen;)V
        //   783: invokestatic sort : (Ljava/util/List;Ljava/util/Comparator;)V
        //   786: aload_0
        //   787: ldc_w 'Xien:\\n'
        //   790: putfield xuatDan : Ljava/lang/String;
        //   793: iconst_0
        //   794: istore #10
        //   796: iload #10
        //   798: aload_1
        //   799: invokeinterface size : ()I
        //   804: if_icmpge -> 1006
        //   807: aload_1
        //   808: iload #10
        //   810: invokeinterface get : (I)Ljava/lang/Object;
        //   815: checkcast org/json/JSONObject
        //   818: astore #7
        //   820: getstatic tamhoang/ldpro4/MainActivity.jSon_Setting : Lorg/json/JSONObject;
        //   823: ldc_w 'chuyen_xien'
        //   826: invokevirtual getInt : (Ljava/lang/String;)I
        //   829: istore #9
        //   831: iload #9
        //   833: ifle -> 916
        //   836: new java/lang/StringBuilder
        //   839: astore #6
        //   841: aload #6
        //   843: invokespecial <init> : ()V
        //   846: aload #6
        //   848: aload_0
        //   849: getfield xuatDan : Ljava/lang/String;
        //   852: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   855: pop
        //   856: aload #6
        //   858: aload #7
        //   860: ldc_w 'So_chon'
        //   863: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   866: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   869: pop
        //   870: aload #6
        //   872: ldc 'x'
        //   874: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   877: pop
        //   878: aload #6
        //   880: aload #7
        //   882: ldc_w 'Se_chuyen'
        //   885: invokevirtual getInt : (Ljava/lang/String;)I
        //   888: bipush #10
        //   890: idiv
        //   891: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   894: pop
        //   895: aload #6
        //   897: ldc_w 'd '
        //   900: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   903: pop
        //   904: aload_0
        //   905: aload #6
        //   907: invokevirtual toString : ()Ljava/lang/String;
        //   910: putfield xuatDan : Ljava/lang/String;
        //   913: goto -> 990
        //   916: new java/lang/StringBuilder
        //   919: astore #6
        //   921: aload #6
        //   923: invokespecial <init> : ()V
        //   926: aload #6
        //   928: aload_0
        //   929: getfield xuatDan : Ljava/lang/String;
        //   932: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   935: pop
        //   936: aload #6
        //   938: aload #7
        //   940: ldc_w 'So_chon'
        //   943: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   946: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   949: pop
        //   950: aload #6
        //   952: ldc 'x'
        //   954: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   957: pop
        //   958: aload #6
        //   960: aload #7
        //   962: ldc_w 'Se_chuyen'
        //   965: invokevirtual getInt : (Ljava/lang/String;)I
        //   968: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   971: pop
        //   972: aload #6
        //   974: ldc_w 'n '
        //   977: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   980: pop
        //   981: aload_0
        //   982: aload #6
        //   984: invokevirtual toString : ()Ljava/lang/String;
        //   987: putfield xuatDan : Ljava/lang/String;
        //   990: goto -> 1000
        //   993: astore #7
        //   995: aload #7
        //   997: invokevirtual printStackTrace : ()V
        //   1000: iinc #10, 1
        //   1003: goto -> 796
        //   1006: aload_0
        //   1007: getfield xuatDan : Ljava/lang/String;
        //   1010: areturn
        //   1011: astore_1
        //   1012: aload #8
        //   1014: invokeinterface isClosed : ()Z
        //   1019: ifne -> 1029
        //   1022: aload #8
        //   1024: invokeinterface close : ()V
        //   1029: goto -> 1034
        //   1032: aload_1
        //   1033: athrow
        //   1034: goto -> 1032
        // Exception table:
        //   from	to	target	type
        //   103	161	188	org/json/JSONException
        //   103	161	184	finally
        //   189	193	1011	finally
        //   212	242	689	org/json/JSONException
        //   245	252	682	org/json/JSONException
        //   258	269	678	org/json/JSONException
        //   277	287	678	org/json/JSONException
        //   290	311	678	org/json/JSONException
        //   319	329	678	org/json/JSONException
        //   332	353	678	org/json/JSONException
        //   361	371	678	org/json/JSONException
        //   371	408	678	org/json/JSONException
        //   413	438	678	org/json/JSONException
        //   461	508	678	org/json/JSONException
        //   511	558	678	org/json/JSONException
        //   558	568	678	org/json/JSONException
        //   576	586	678	org/json/JSONException
        //   593	626	678	org/json/JSONException
        //   629	662	678	org/json/JSONException
        //   662	672	678	org/json/JSONException
        //   731	761	764	org/json/JSONException
        //   820	831	993	org/json/JSONException
        //   836	913	993	org/json/JSONException
        //   916	990	993	org/json/JSONException
    }

    public void Xulytin(int paramInt1, String paramString, int paramInt2) {
        // Byte code:
        //   0: new tamhoang/ldpro4/MainActivity
        //   3: dup
        //   4: invokespecial <init> : ()V
        //   7: astore #4
        //   9: invokestatic Get_date : ()Ljava/lang/String;
        //   12: astore #5
        //   14: invokestatic getInstance : ()Ljava/util/Calendar;
        //   17: astore #6
        //   19: aload #6
        //   21: new java/util/Date
        //   24: dup
        //   25: invokespecial <init> : ()V
        //   28: invokevirtual setTime : (Ljava/util/Date;)V
        //   31: new java/text/SimpleDateFormat
        //   34: dup
        //   35: ldc_w 'HH:mm:ss'
        //   38: invokespecial <init> : (Ljava/lang/String;)V
        //   41: astore #7
        //   43: aload #7
        //   45: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   48: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   51: aload #7
        //   53: aload #6
        //   55: invokevirtual getTime : ()Ljava/util/Date;
        //   58: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
        //   61: astore #8
        //   63: aload_0
        //   64: getfield mSpiner : I
        //   67: iconst_m1
        //   68: if_icmple -> 1318
        //   71: aload_0
        //   72: getfield db : Ltamhoang/ldpro4/data/Database;
        //   75: astore #7
        //   77: new java/lang/StringBuilder
        //   80: dup
        //   81: invokespecial <init> : ()V
        //   84: astore #6
        //   86: aload #6
        //   88: ldc_w 'Select * From tbl_kh_new Where sdt = ''
        //   91: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   94: pop
        //   95: aload #6
        //   97: aload_0
        //   98: getfield mMobile : Ljava/util/List;
        //   101: aload_0
        //   102: getfield mSpiner : I
        //   105: invokeinterface get : (I)Ljava/lang/Object;
        //   110: checkcast java/lang/String
        //   113: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   116: pop
        //   117: aload #6
        //   119: ldc_w '''
        //   122: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   125: pop
        //   126: aload #7
        //   128: aload #6
        //   130: invokevirtual toString : ()Ljava/lang/String;
        //   133: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   136: astore #6
        //   138: aload #6
        //   140: invokeinterface moveToFirst : ()Z
        //   145: ifeq -> 1315
        //   148: aload #6
        //   150: iconst_3
        //   151: invokeinterface getInt : (I)I
        //   156: iconst_3
        //   157: if_icmpne -> 166
        //   160: iconst_2
        //   161: istore #9
        //   163: goto -> 176
        //   166: aload #6
        //   168: iconst_3
        //   169: invokeinterface getInt : (I)I
        //   174: istore #9
        //   176: new java/lang/StringBuilder
        //   179: dup
        //   180: invokespecial <init> : ()V
        //   183: astore #7
        //   185: aload #7
        //   187: ldc_w 'Insert Into tbl_tinnhanS values (null, ''
        //   190: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   193: pop
        //   194: aload #7
        //   196: aload #5
        //   198: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   201: pop
        //   202: aload #7
        //   204: ldc_w '', ''
        //   207: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   210: pop
        //   211: aload #7
        //   213: aload #8
        //   215: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   218: pop
        //   219: aload #7
        //   221: ldc_w '', '
        //   224: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   227: pop
        //   228: aload #7
        //   230: iload #9
        //   232: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   235: pop
        //   236: aload #7
        //   238: ldc_w ', ''
        //   241: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   244: pop
        //   245: aload #7
        //   247: aload_0
        //   248: getfield mContact : Ljava/util/List;
        //   251: aload_0
        //   252: getfield mSpiner : I
        //   255: invokeinterface get : (I)Ljava/lang/Object;
        //   260: checkcast java/lang/String
        //   263: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   266: pop
        //   267: aload #7
        //   269: ldc_w '', ''
        //   272: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   275: pop
        //   276: aload #7
        //   278: aload_0
        //   279: getfield mMobile : Ljava/util/List;
        //   282: aload_0
        //   283: getfield mSpiner : I
        //   286: invokeinterface get : (I)Ljava/lang/Object;
        //   291: checkcast java/lang/String
        //   294: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   297: pop
        //   298: aload #7
        //   300: ldc_w '', 2, '
        //   303: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   306: pop
        //   307: aload #7
        //   309: iload_1
        //   310: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   313: pop
        //   314: aload #7
        //   316: ldc_w ', 'Tin '
        //   319: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   322: pop
        //   323: aload #7
        //   325: iload_1
        //   326: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   329: pop
        //   330: aload #7
        //   332: ldc_w ':\\n'
        //   335: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   338: pop
        //   339: aload #7
        //   341: aload_2
        //   342: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   345: pop
        //   346: aload #7
        //   348: ldc_w '',null, ''
        //   351: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   354: pop
        //   355: aload #7
        //   357: aload_2
        //   358: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   361: pop
        //   362: aload #7
        //   364: ldc_w '', 'ko',0, 0, 1, null)'
        //   367: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   370: pop
        //   371: aload #7
        //   373: invokevirtual toString : ()Ljava/lang/String;
        //   376: astore #7
        //   378: aload_0
        //   379: getfield db : Ltamhoang/ldpro4/data/Database;
        //   382: aload #7
        //   384: invokevirtual QueryData : (Ljava/lang/String;)V
        //   387: aload_0
        //   388: getfield db : Ltamhoang/ldpro4/data/Database;
        //   391: astore #7
        //   393: new java/lang/StringBuilder
        //   396: dup
        //   397: invokespecial <init> : ()V
        //   400: astore #10
        //   402: aload #10
        //   404: ldc_w 'Select id From tbl_tinnhanS WHERE ngay_nhan = ''
        //   407: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   410: pop
        //   411: aload #10
        //   413: aload #5
        //   415: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   418: pop
        //   419: aload #10
        //   421: ldc_w '' AND so_dienthoai = ''
        //   424: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   427: pop
        //   428: aload #10
        //   430: aload_0
        //   431: getfield mMobile : Ljava/util/List;
        //   434: aload_0
        //   435: getfield mSpiner : I
        //   438: invokeinterface get : (I)Ljava/lang/Object;
        //   443: checkcast java/lang/String
        //   446: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   449: pop
        //   450: aload #10
        //   452: ldc_w '' AND type_kh = 2 AND so_tin_nhan = '
        //   455: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   458: pop
        //   459: aload #10
        //   461: iload_1
        //   462: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   465: pop
        //   466: aload #7
        //   468: aload #10
        //   470: invokevirtual toString : ()Ljava/lang/String;
        //   473: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   476: astore #7
        //   478: aload #7
        //   480: invokeinterface moveToFirst : ()Z
        //   485: pop
        //   486: getstatic tamhoang/ldpro4/MainActivity.myDate : Ljava/lang/String;
        //   489: invokestatic CheckDate : (Ljava/lang/String;)Z
        //   492: ifeq -> 1219
        //   495: aload_0
        //   496: getfield db : Ltamhoang/ldpro4/data/Database;
        //   499: aload #7
        //   501: iconst_0
        //   502: invokeinterface getInt : (I)I
        //   507: aload #6
        //   509: iconst_3
        //   510: invokeinterface getInt : (I)I
        //   515: invokevirtual Update_TinNhanGoc : (II)V
        //   518: new java/lang/StringBuilder
        //   521: astore #10
        //   523: aload #10
        //   525: invokespecial <init> : ()V
        //   528: aload #10
        //   530: ldc_w 'Tin '
        //   533: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   536: pop
        //   537: aload #10
        //   539: iload_1
        //   540: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   543: pop
        //   544: aload #10
        //   546: ldc_w ':\\n'
        //   549: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   552: pop
        //   553: aload #10
        //   555: aload_2
        //   556: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   559: pop
        //   560: aload #10
        //   562: invokevirtual toString : ()Ljava/lang/String;
        //   565: astore_2
        //   566: iload_3
        //   567: iconst_1
        //   568: if_icmpne -> 798
        //   571: aload #6
        //   573: iconst_2
        //   574: invokeinterface getString : (I)Ljava/lang/String;
        //   579: ldc 'TL'
        //   581: invokevirtual indexOf : (Ljava/lang/String;)I
        //   584: iconst_m1
        //   585: if_icmple -> 798
        //   588: new android/os/Handler
        //   591: astore #10
        //   593: aload #10
        //   595: invokestatic getMainLooper : ()Landroid/os/Looper;
        //   598: invokespecial <init> : (Landroid/os/Looper;)V
        //   601: new tamhoang/ldpro4/Fragment/Frag_CanChuyen$23
        //   604: astore #11
        //   606: aload #11
        //   608: aload_0
        //   609: aload #4
        //   611: aload_2
        //   612: invokespecial <init> : (Ltamhoang/ldpro4/Fragment/Frag_CanChuyen;Ltamhoang/ldpro4/MainActivity;Ljava/lang/String;)V
        //   615: aload #10
        //   617: aload #11
        //   619: invokevirtual post : (Ljava/lang/Runnable;)Z
        //   622: pop
        //   623: new java/lang/StringBuilder
        //   626: astore #4
        //   628: aload #4
        //   630: invokespecial <init> : ()V
        //   633: aload #4
        //   635: ldc_w 'Insert into Chat_database Values( null,''
        //   638: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   641: pop
        //   642: aload #4
        //   644: aload #5
        //   646: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   649: pop
        //   650: aload #4
        //   652: ldc_w '', ''
        //   655: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   658: pop
        //   659: aload #4
        //   661: aload #8
        //   663: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   666: pop
        //   667: aload #4
        //   669: ldc_w '', 2, ''
        //   672: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   675: pop
        //   676: aload #4
        //   678: aload_0
        //   679: getfield mContact : Ljava/util/List;
        //   682: aload_0
        //   683: getfield mSpiner : I
        //   686: invokeinterface get : (I)Ljava/lang/Object;
        //   691: checkcast java/lang/String
        //   694: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   697: pop
        //   698: aload #4
        //   700: ldc_w '', ''
        //   703: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   706: pop
        //   707: aload #4
        //   709: aload_0
        //   710: getfield mMobile : Ljava/util/List;
        //   713: aload_0
        //   714: getfield mSpiner : I
        //   717: invokeinterface get : (I)Ljava/lang/Object;
        //   722: checkcast java/lang/String
        //   725: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   728: pop
        //   729: aload #4
        //   731: ldc_w '', ''
        //   734: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   737: pop
        //   738: aload #4
        //   740: aload #6
        //   742: iconst_2
        //   743: invokeinterface getString : (I)Ljava/lang/String;
        //   748: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   751: pop
        //   752: aload #4
        //   754: ldc_w '',''
        //   757: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   760: pop
        //   761: aload #4
        //   763: aload_2
        //   764: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   767: pop
        //   768: aload #4
        //   770: ldc_w '',1)'
        //   773: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   776: pop
        //   777: aload #4
        //   779: invokevirtual toString : ()Ljava/lang/String;
        //   782: astore_2
        //   783: aload_0
        //   784: getfield db : Ltamhoang/ldpro4/data/Database;
        //   787: aload_2
        //   788: invokevirtual QueryData : (Ljava/lang/String;)V
        //   791: goto -> 1073
        //   794: astore_2
        //   795: goto -> 1077
        //   798: iload_3
        //   799: iconst_1
        //   800: if_icmpne -> 851
        //   803: aload #6
        //   805: iconst_2
        //   806: invokeinterface getString : (I)Ljava/lang/String;
        //   811: ldc 'sms'
        //   813: invokevirtual indexOf : (Ljava/lang/String;)I
        //   816: iconst_m1
        //   817: if_icmple -> 851
        //   820: aload_0
        //   821: getfield db : Ltamhoang/ldpro4/data/Database;
        //   824: aload_0
        //   825: getfield mMobile : Ljava/util/List;
        //   828: aload_0
        //   829: getfield mSpiner : I
        //   832: invokeinterface get : (I)Ljava/lang/Object;
        //   837: checkcast java/lang/String
        //   840: aload_2
        //   841: invokevirtual SendSMS : (Ljava/lang/String;Ljava/lang/String;)V
        //   844: goto -> 1073
        //   847: astore_2
        //   848: goto -> 1077
        //   851: iload_3
        //   852: iconst_1
        //   853: if_icmpne -> 1073
        //   856: aload #6
        //   858: iconst_2
        //   859: invokeinterface getString : (I)Ljava/lang/String;
        //   864: ldc 'sms'
        //   866: invokevirtual indexOf : (Ljava/lang/String;)I
        //   869: iconst_m1
        //   870: if_icmpne -> 1073
        //   873: new tamhoang/ldpro4/NotificationReader
        //   876: astore #4
        //   878: aload #4
        //   880: invokespecial <init> : ()V
        //   883: aload #4
        //   885: aload_0
        //   886: getfield mMobile : Ljava/util/List;
        //   889: aload_0
        //   890: getfield mSpiner : I
        //   893: invokeinterface get : (I)Ljava/lang/Object;
        //   898: checkcast java/lang/String
        //   901: aload_2
        //   902: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   905: new java/lang/StringBuilder
        //   908: astore #4
        //   910: aload #4
        //   912: invokespecial <init> : ()V
        //   915: aload #4
        //   917: ldc_w 'Insert into Chat_database Values( null,''
        //   920: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   923: pop
        //   924: aload #4
        //   926: aload #5
        //   928: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   931: pop
        //   932: aload #4
        //   934: ldc_w '', ''
        //   937: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   940: pop
        //   941: aload #4
        //   943: aload #8
        //   945: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   948: pop
        //   949: aload #4
        //   951: ldc_w '', 2, ''
        //   954: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   957: pop
        //   958: aload #4
        //   960: aload_0
        //   961: getfield mContact : Ljava/util/List;
        //   964: aload_0
        //   965: getfield mSpiner : I
        //   968: invokeinterface get : (I)Ljava/lang/Object;
        //   973: checkcast java/lang/String
        //   976: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   979: pop
        //   980: aload #4
        //   982: ldc_w '', ''
        //   985: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   988: pop
        //   989: aload #4
        //   991: aload_0
        //   992: getfield mMobile : Ljava/util/List;
        //   995: aload_0
        //   996: getfield mSpiner : I
        //   999: invokeinterface get : (I)Ljava/lang/Object;
        //   1004: checkcast java/lang/String
        //   1007: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1010: pop
        //   1011: aload #4
        //   1013: ldc_w '', ''
        //   1016: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1019: pop
        //   1020: aload #4
        //   1022: aload #6
        //   1024: iconst_2
        //   1025: invokeinterface getString : (I)Ljava/lang/String;
        //   1030: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1033: pop
        //   1034: aload #4
        //   1036: ldc_w '',''
        //   1039: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1042: pop
        //   1043: aload #4
        //   1045: aload_2
        //   1046: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1049: pop
        //   1050: aload #4
        //   1052: ldc_w '',1)'
        //   1055: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1058: pop
        //   1059: aload #4
        //   1061: invokevirtual toString : ()Ljava/lang/String;
        //   1064: astore_2
        //   1065: aload_0
        //   1066: getfield db : Ltamhoang/ldpro4/data/Database;
        //   1069: aload_2
        //   1070: invokevirtual QueryData : (Ljava/lang/String;)V
        //   1073: goto -> 1288
        //   1076: astore_2
        //   1077: aload_0
        //   1078: getfield db : Ltamhoang/ldpro4/data/Database;
        //   1081: astore_2
        //   1082: new java/lang/StringBuilder
        //   1085: dup
        //   1086: invokespecial <init> : ()V
        //   1089: astore #8
        //   1091: aload #8
        //   1093: ldc_w 'Update tbl_tinnhanS set phat_hien_loi = 'ko' WHERE id = '
        //   1096: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1099: pop
        //   1100: aload #8
        //   1102: aload #7
        //   1104: iconst_0
        //   1105: invokeinterface getInt : (I)I
        //   1110: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1113: pop
        //   1114: aload_2
        //   1115: aload #8
        //   1117: invokevirtual toString : ()Ljava/lang/String;
        //   1120: invokevirtual QueryData : (Ljava/lang/String;)V
        //   1123: new java/lang/StringBuilder
        //   1126: dup
        //   1127: invokespecial <init> : ()V
        //   1130: astore_2
        //   1131: aload_2
        //   1132: ldc_w 'Delete From tbl_soctS WHERE ngay_nhan = ''
        //   1135: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1138: pop
        //   1139: aload_2
        //   1140: aload #5
        //   1142: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1145: pop
        //   1146: aload_2
        //   1147: ldc_w '' AND so_dienthoai = ''
        //   1150: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1153: pop
        //   1154: aload_2
        //   1155: aload_0
        //   1156: getfield mMobile : Ljava/util/List;
        //   1159: aload_0
        //   1160: getfield mSpiner : I
        //   1163: invokeinterface get : (I)Ljava/lang/Object;
        //   1168: checkcast java/lang/String
        //   1171: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1174: pop
        //   1175: aload_2
        //   1176: ldc_w '' AND so_tin_nhan = '
        //   1179: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1182: pop
        //   1183: aload_2
        //   1184: iload_1
        //   1185: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1188: pop
        //   1189: aload_2
        //   1190: invokevirtual toString : ()Ljava/lang/String;
        //   1193: astore_2
        //   1194: aload_0
        //   1195: getfield db : Ltamhoang/ldpro4/data/Database;
        //   1198: aload_2
        //   1199: invokevirtual QueryData : (Ljava/lang/String;)V
        //   1202: aload_0
        //   1203: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   1206: ldc_w 'xra l
        //   1209: iconst_1
        //   1210: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   1213: invokevirtual show : ()V
        //   1216: goto -> 1288
        //   1219: aload_0
        //   1220: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
        //   1223: astore_2
        //   1224: new java/lang/StringBuilder
        //   1227: astore #5
        //   1229: aload #5
        //   1231: invokespecial <init> : ()V
        //   1234: aload #5
        //   1236: ldc_w 'hhsd\\n\\nHlihlhoS'
        //   1239: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1242: pop
        //   1243: aload #5
        //   1245: getstatic tamhoang/ldpro4/MainActivity.listKH : Lorg/json/JSONObject;
        //   1248: ldc_w 'k_tra'
        //   1251: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1254: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1257: pop
        //   1258: aload #5
        //   1260: ldc_w ' gia h
        //   1263: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1266: pop
        //   1267: aload_2
        //   1268: aload #5
        //   1270: invokevirtual toString : ()Ljava/lang/String;
        //   1273: iconst_1
        //   1274: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   1277: invokevirtual show : ()V
        //   1280: goto -> 1288
        //   1283: astore_2
        //   1284: aload_2
        //   1285: invokevirtual printStackTrace : ()V
        //   1288: aload #7
        //   1290: ifnull -> 1300
        //   1293: aload #7
        //   1295: invokeinterface close : ()V
        //   1300: aload #6
        //   1302: ifnull -> 1318
        //   1305: aload #6
        //   1307: invokeinterface close : ()V
        //   1312: goto -> 1318
        //   1315: goto -> 1318
        //   1318: return
        // Exception table:
        //   from	to	target	type
        //   495	566	1076	java/lang/Exception
        //   571	791	794	java/lang/Exception
        //   803	844	847	java/lang/Exception
        //   856	1073	847	java/lang/Exception
        //   1219	1280	1283	org/json/JSONException
    }

    public void btn_click() {
        this.xuatDan = "";
        new MainActivity();
        String str1 = MainActivity.Get_date();
        xemlv();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
        simpleDateFormat1.setTimeZone(TimeZone.getDefault());
        simpleDateFormat2.setTimeZone(TimeZone.getDefault());
        String str2 = simpleDateFormat1.format(calendar.getTime());
        int i = 1;
        int j = 1;
        try {
            if (MainActivity.jSon_Setting.getInt("lam_tron") == 0) {
                j = 1;
            } else if (MainActivity.jSon_Setting.getInt("lam_tron") == 1) {
                j = 10;
            } else if (MainActivity.jSon_Setting.getInt("lam_tron") == 2) {
                j = 50;
            } else {
                int k = MainActivity.jSon_Setting.getInt("lam_tron");
                if (k == 3)
                    j = 100;
            }
            i = j;
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        if (str2.indexOf(str1) > -1) {
            byte b;
            if (this.edt_tien.getText().toString().length() == 0 || this.edt_tien.getText().toString() == "0") {
                b = 0;
            } else {
                str1 = this.edt_tien.getText().toString().replaceAll("%", "").replaceAll("n", "").replaceAll("k", "").replaceAll("d", "").replaceAll(">", "").replaceAll("\\.", "").replaceAll(",", "");
                if (Congthuc.isNumeric(str1)) {
                    b = Integer.parseInt(str1);
                } else {
                    b = 0;
                }
            }
            str1 = this.DangXuat;
            if (str1 == "(the_loai = 'deb' or the_loai = 'det')" || str1 == "the_loai = 'lo'" || str1 == "the_loai = 'dea'" || str1 == "the_loai = 'dec'" || str1 == "the_loai = 'ded'") {
                String str = this.DangXuat;
                if (str == "(the_loai = 'deb' or the_loai = 'det')") {
                    this.xuatDan = this.db.XuatDanTon2("deb", this.edt_tien.getText().toString(), this.min, this.max);
                } else if (str == "the_loai = 'dea'") {
                    this.xuatDan = this.db.XuatDanTon2("dea", this.edt_tien.getText().toString(), this.min, this.max);
                } else if (str == "the_loai = 'dec'") {
                    this.xuatDan = this.db.XuatDanTon2("dec", this.edt_tien.getText().toString(), this.min, this.max);
                } else if (str == "the_loai = 'ded'") {
                    this.xuatDan = this.db.XuatDanTon2("ded", this.edt_tien.getText().toString(), this.min, this.max);
                } else {
                    this.xuatDan = this.db.XuatDanTon2("lo", this.edt_tien.getText().toString(), this.min, this.max);
                }
            } else if (str1 == "the_loai = 'xi'") {
                this.xuatDan = "Xien:\n";
                for (int k = this.min; k < this.mSo.size(); k++) {
                    if (this.edt_tien.getText().toString().indexOf("%") > -1) {
                        j = Integer.parseInt(((String)this.mTienTon.get(k)).replace(".", "")) / i * i;
                    } else if (this.edt_tien.getText().toString().indexOf(">") > -1) {
                        j = Integer.parseInt(((String)this.mTienTon.get(k)).replace(".", "")) / i * i;
                    } else if (!b) {
                        j = Integer.parseInt(((String)this.mTienTon.get(k)).replace(".", "")) / i * i;
                    } else if (Integer.parseInt(((String)this.mTienTon.get(k)).replace(".", "")) > b) {
                        j = b / i * i;
                    } else {
                        j = Integer.parseInt(((String)this.mTienTon.get(k)).replace(".", "")) / i * i;
                    }
                    if (this.edt_tien.getText().toString().indexOf("%") > -1) {
                        if (j > 0)
                            try {
                                if (MainActivity.jSon_Setting.getInt("chuyen_xien") > 0) {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    this();
                                    stringBuilder.append(this.xuatDan);
                                    stringBuilder.append(this.mSo.get(k));
                                    stringBuilder.append("x");
                                    stringBuilder.append(j * b / 1000);
                                    stringBuilder.append("d ");
                                    this.xuatDan = stringBuilder.toString();
                                } else if (MainActivity.jSon_Setting.getInt("chuyen_xien") == 0) {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    this();
                                    stringBuilder.append(this.xuatDan);
                                    stringBuilder.append(this.mSo.get(k));
                                    stringBuilder.append("x");
                                    stringBuilder.append(j * b / 100);
                                    stringBuilder.append("n ");
                                    this.xuatDan = stringBuilder.toString();
                                }
                            } catch (JSONException null) {
                            jSONException.printStackTrace();
                        }
                    } else if (this.edt_tien.getText().toString().indexOf(">") > -1) {
                        if (j > b)
                            try {
                                if (MainActivity.jSon_Setting.getInt("chuyen_xien") > 0) {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    this();
                                    stringBuilder.append(this.xuatDan);
                                    stringBuilder.append(this.mSo.get(k));
                                    stringBuilder.append("x");
                                    stringBuilder.append((j - b) / 10);
                                    stringBuilder.append("d ");
                                    this.xuatDan = stringBuilder.toString();
                                } else if (MainActivity.jSon_Setting.getInt("chuyen_xien") == 0) {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    this();
                                    stringBuilder.append(this.xuatDan);
                                    stringBuilder.append(this.mSo.get(k));
                                    stringBuilder.append("x");
                                    stringBuilder.append(j - b);
                                    stringBuilder.append("n ");
                                    this.xuatDan = stringBuilder.toString();
                                }
                            } catch (JSONException null) {
                            jSONException.printStackTrace();
                        }
                    } else if (this.edt_tien.getText().toString().indexOf(">") == -1 && this.edt_tien.getText().toString().indexOf("%") == -1 && j > 0) {
                        try {
                            if (MainActivity.jSon_Setting.getInt("chuyen_xien") > 0) {
                                StringBuilder stringBuilder = new StringBuilder();
                                this();
                                stringBuilder.append(this.xuatDan);
                                stringBuilder.append(this.mSo.get(k));
                                stringBuilder.append("x");
                                stringBuilder.append(j / 10);
                                stringBuilder.append("d ");
                                this.xuatDan = stringBuilder.toString();
                            } else if (MainActivity.jSon_Setting.getInt("chuyen_xien") == 0) {
                                StringBuilder stringBuilder = new StringBuilder();
                                this();
                                stringBuilder.append(this.xuatDan);
                                stringBuilder.append(this.mSo.get(k));
                                stringBuilder.append("x");
                                stringBuilder.append(j);
                                stringBuilder.append("n ");
                                this.xuatDan = stringBuilder.toString();
                            }
                        } catch (JSONException jSONException) {
                            jSONException.printStackTrace();
                        }
                    }
                }
            } else if (str1 == "the_loai = 'bc'") {
                this.xuatDan = "Cang:\n";
                int k = this.min;
                int m = 0;
                JSONException jSONException1 = jSONException;
                while (k < this.mSo.size()) {
                    if (b == 0) {
                        j = Integer.parseInt(((String)this.mTienTon.get(k)).replace(".", "")) / i * i;
                    } else if (this.edt_tien.getText().toString().indexOf("%") > -1) {
                        j = Integer.parseInt(((String)this.mTienTon.get(k)).replace(".", "")) * b / i / 100 * i;
                    } else if (this.edt_tien.getText().toString().indexOf(">") > -1) {
                        j = (Integer.parseInt(((String)this.mTienTon.get(k)).replace(".", "")) - b) / i * i;
                    } else if (Integer.parseInt(((String)this.mTienTon.get(k)).replace(".", "")) > b) {
                        j = b / i * i;
                    } else {
                        j = Integer.parseInt(((String)this.mTienTon.get(k)).replace(".", "")) / i * i;
                    }
                    if (j > 0) {
                        if (m > j) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(this.xuatDan);
                            stringBuilder.append("x");
                            stringBuilder.append(m);
                            stringBuilder.append("n ");
                            this.xuatDan = stringBuilder.toString();
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(this.xuatDan);
                            stringBuilder.append(this.mSo.get(k));
                            stringBuilder.append(",");
                            this.xuatDan = stringBuilder.toString();
                        } else {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(this.xuatDan);
                            stringBuilder.append(this.mSo.get(k));
                            stringBuilder.append(",");
                            this.xuatDan = stringBuilder.toString();
                        }
                    } else {
                        j = m;
                    }
                    k++;
                    m = j;
                }
                if (this.xuatDan.length() > 4) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(this.xuatDan);
                    stringBuilder.append("x");
                    stringBuilder.append(m);
                    stringBuilder.append("n");
                    this.xuatDan = stringBuilder.toString();
                }
            } else if (str1 == "the_loai = 'xn'") {
                this.xuatDan = "Xnhay:\n";
                for (int k = this.min; k < this.mSo.size(); k++) {
                    if (this.edt_tien.getText().toString().indexOf("%") > -1) {
                        j = Integer.parseInt(((String)this.mTienTon.get(k)).replace(".", "")) / i * i;
                    } else if (this.edt_tien.getText().toString().indexOf(">") > -1) {
                        j = Integer.parseInt(((String)this.mTienTon.get(k)).replace(".", "")) / i * i;
                    } else if (b == 0) {
                        j = Integer.parseInt(((String)this.mTienTon.get(k)).replace(".", "")) / i * i;
                    } else if (Integer.parseInt(((String)this.mTienTon.get(k)).replace(".", "")) > b) {
                        j = b / i * i;
                    } else {
                        j = Integer.parseInt(((String)this.mTienTon.get(k)).replace(".", "")) / i * i;
                    }
                    if (this.edt_tien.getText().toString().indexOf("%") > -1) {
                        if (j > 0)
                            try {
                                if (MainActivity.jSon_Setting.getInt("chuyen_xien") > 0) {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    this();
                                    stringBuilder.append(this.xuatDan);
                                    stringBuilder.append(this.mSo.get(k));
                                    stringBuilder.append("x");
                                    stringBuilder.append(j * b / 1000);
                                    stringBuilder.append("d\n");
                                    this.xuatDan = stringBuilder.toString();
                                } else if (MainActivity.jSon_Setting.getInt("chuyen_xien") == 0) {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    this();
                                    stringBuilder.append(this.xuatDan);
                                    stringBuilder.append(this.mSo.get(k));
                                    stringBuilder.append("x");
                                    stringBuilder.append(j * b / 100);
                                    stringBuilder.append("n\n");
                                    this.xuatDan = stringBuilder.toString();
                                }
                            } catch (JSONException jSONException1) {
                                jSONException1.printStackTrace();
                            }
                    } else if (this.edt_tien.getText().toString().indexOf(">") > -1) {
                        if (j > b)
                            try {
                                if (MainActivity.jSon_Setting.getInt("chuyen_xien") > 0) {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    this();
                                    stringBuilder.append(this.xuatDan);
                                    stringBuilder.append(this.mSo.get(k));
                                    stringBuilder.append("x");
                                    stringBuilder.append((j - b) / 10);
                                    stringBuilder.append("d\n");
                                    this.xuatDan = stringBuilder.toString();
                                } else if (MainActivity.jSon_Setting.getInt("chuyen_xien") == 0) {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    this();
                                    stringBuilder.append(this.xuatDan);
                                    stringBuilder.append(this.mSo.get(k));
                                    stringBuilder.append("x");
                                    stringBuilder.append(j - b);
                                    stringBuilder.append("n\n");
                                    this.xuatDan = stringBuilder.toString();
                                }
                            } catch (JSONException jSONException1) {
                                jSONException1.printStackTrace();
                            }
                    } else if (this.edt_tien.getText().toString().indexOf(">") == -1 && this.edt_tien.getText().toString().indexOf("%") == -1 && j > 0) {
                        try {
                            if (MainActivity.jSon_Setting.getInt("chuyen_xien") > 0) {
                                StringBuilder stringBuilder = new StringBuilder();
                                this();
                                stringBuilder.append(this.xuatDan);
                                stringBuilder.append(this.mSo.get(k));
                                stringBuilder.append("x");
                                stringBuilder.append(j / 10);
                                stringBuilder.append("d\n");
                                this.xuatDan = stringBuilder.toString();
                            } else if (MainActivity.jSon_Setting.getInt("chuyen_xien") == 0) {
                                StringBuilder stringBuilder = new StringBuilder();
                                this();
                                stringBuilder.append(this.xuatDan);
                                stringBuilder.append(this.mSo.get(k));
                                stringBuilder.append("x");
                                stringBuilder.append(j);
                                stringBuilder.append("n\n");
                                this.xuatDan = stringBuilder.toString();
                            }
                        } catch (JSONException jSONException1) {
                            jSONException1.printStackTrace();
                        }
                    }
                }
            }
            if (this.xuatDan.indexOf(":") > -1) {
                String str = this.xuatDan;
                if (str.substring(str.indexOf(":")).length() > 7) {
                    if (!getActivity().isFinishing())
                        Dialog(1);
                    return;
                }
            }
            Toast.makeText((Context)getActivity(), "Khcsli, 1).show();
        } else {
            Toast.makeText((Context)getActivity(), "Khlvivdlingc, 1).show();
        }
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        Integer integer2 = Integer.valueOf(0);
        View view = paramLayoutInflater.inflate(2131427393, paramViewGroup, false);
        this.v = view;
        this.radio_de = (RadioButton)view.findViewById(2131231172);
        this.radio_lo = (RadioButton)this.v.findViewById(2131231178);
        this.radio_xi = (RadioButton)this.v.findViewById(2131231180);
        this.radio_bc = (RadioButton)this.v.findViewById(2131231171);
        this.radio_dea = (RadioButton)this.v.findViewById(2131231173);
        this.radio_deb = (RadioButton)this.v.findViewById(2131231174);
        this.radio_dec = (RadioButton)this.v.findViewById(2131231175);
        this.radio_ded = (RadioButton)this.v.findViewById(2131231176);
        this.btn_Xuatso = (Button)this.v.findViewById(2131230813);
        LinearLayout linearLayout2 = (LinearLayout)this.v.findViewById(2131231069);
        this.ln_xi = linearLayout2;
        linearLayout2.setVisibility(8);
        linearLayout2 = (LinearLayout)this.v.findViewById(2131231027);
        this.li_loaide = linearLayout2;
        linearLayout2.setVisibility(8);
        this.edt_tien = (EditText)this.v.findViewById(2131230962);
        this.check_x2 = (CheckBox)this.v.findViewById(2131230844);
        this.check_x3 = (CheckBox)this.v.findViewById(2131230845);
        this.check_x4 = (CheckBox)this.v.findViewById(2131230846);
        this.check_xn = (CheckBox)this.v.findViewById(2131230847);
        this.no_rp_number = (ListView)this.v.findViewById(2131231103);
        this.db = new Database((Context)getActivity());
        Handler handler = new Handler();
        this.handler = handler;
        handler.postDelayed(this.runnable, 1000L);
        RangeSeekBar<Integer> rangeSeekBar = new RangeSeekBar((Context)getActivity());
        this.rangeSeekBar = rangeSeekBar;
        Integer integer1 = Integer.valueOf(100);
        rangeSeekBar.setRangeValues(integer2, integer1);
        this.rangeSeekBar.setSelectedMinValue(integer2);
        this.rangeSeekBar.setSelectedMaxValue(integer1);
        LinearLayout linearLayout1 = (LinearLayout)this.v.findViewById(2131231222);
        this.layout = linearLayout1;
        linearLayout1.addView((View)this.rangeSeekBar);
        this.rangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> param1RangeSeekBar, Integer param1Integer1, Integer param1Integer2) {
                Frag_CanChuyen.this.min = param1Integer1.intValue();
                Frag_CanChuyen.this.max = param1Integer2.intValue();
            }
        });
        this.radio_de.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_CanChuyen.this.radio_de.isChecked()) {
                    Frag_CanChuyen.this.layout.setVisibility(0);
                    new MainActivity();
                    String str = MainActivity.Get_date();
                    try {
                        Database database = Frag_CanChuyen.this.db;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append("Select sum((the_loai = 'dea')* diem) as de_a\n,sum((the_loai = 'deb')* diem) as de_b\n,sum((the_loai = 'det')* diem) as de_t\n,sum((the_loai = 'dec')* diem) as de_c\n,sum((the_loai = 'ded')* diem) as de_d\nFrom tbl_soctS \nWhere ngay_nhan = '");
                        stringBuilder.append(str);
                        stringBuilder.append("'");
                        Cursor cursor = database.GetData(stringBuilder.toString());
                        if (cursor.moveToFirst() && cursor != null) {
                            int[] arrayOfInt = new int[5];
                            if (cursor.getDouble(0) > 0.0D) {
                                arrayOfInt[0] = 1;
                                Frag_CanChuyen.this.radio_dea.setEnabled(true);
                            } else {
                                arrayOfInt[0] = 0;
                                Frag_CanChuyen.this.radio_dea.setEnabled(false);
                            }
                            if (cursor.getDouble(1) > 0.0D) {
                                arrayOfInt[1] = 1;
                                Frag_CanChuyen.this.radio_deb.setEnabled(true);
                            } else {
                                arrayOfInt[1] = 0;
                                Frag_CanChuyen.this.radio_deb.setEnabled(false);
                            }
                            if (cursor.getDouble(2) > 0.0D) {
                                arrayOfInt[2] = 1;
                            } else {
                                arrayOfInt[2] = 0;
                            }
                            if (cursor.getDouble(3) > 0.0D) {
                                arrayOfInt[3] = 1;
                                Frag_CanChuyen.this.radio_dec.setEnabled(true);
                            } else {
                                arrayOfInt[3] = 0;
                                Frag_CanChuyen.this.radio_dec.setEnabled(false);
                            }
                            if (cursor.getDouble(4) > 0.0D) {
                                arrayOfInt[4] = 1;
                                Frag_CanChuyen.this.radio_ded.setEnabled(true);
                            } else {
                                arrayOfInt[4] = 0;
                                Frag_CanChuyen.this.radio_ded.setEnabled(false);
                            }
                            if (arrayOfInt[0] == 0 && (arrayOfInt[1] == 1 || arrayOfInt[2] == 1) && arrayOfInt[3] == 0 && arrayOfInt[4] == 0) {
                                Frag_CanChuyen.this.DangXuat = "(the_loai = 'deb' or the_loai = 'det')";
                                Frag_CanChuyen.this.ln_xi.setVisibility(8);
                                Frag_CanChuyen.this.li_loaide.setVisibility(8);
                                Frag_CanChuyen.this.radio_deb.setChecked(true);
                                Frag_CanChuyen.this.xem_RecycView();
                            } else if (arrayOfInt[0] == 0 && arrayOfInt[1] == 0 && arrayOfInt[2] == 0 && arrayOfInt[3] == 0 && arrayOfInt[4] == 0) {
                                Frag_CanChuyen.this.DangXuat = "(the_loai = 'deb' or the_loai = 'det')";
                                Frag_CanChuyen.this.ln_xi.setVisibility(8);
                                Frag_CanChuyen.this.li_loaide.setVisibility(8);
                                Frag_CanChuyen.this.radio_deb.setChecked(true);
                                Frag_CanChuyen.this.xem_RecycView();
                            } else {
                                Frag_CanChuyen.this.DangXuat = "(the_loai = 'deb' or the_loai = 'det')";
                                Frag_CanChuyen.this.ln_xi.setVisibility(8);
                                Frag_CanChuyen.this.li_loaide.setVisibility(0);
                                Frag_CanChuyen.this.radio_deb.setChecked(true);
                                Frag_CanChuyen.this.xem_RecycView();
                            }
                            if (!cursor.isClosed() && cursor != null && !cursor.isClosed())
                                cursor.close();
                        } else {
                            Frag_CanChuyen.this.DangXuat = "(the_loai = 'deb' or the_loai = 'det')";
                        }
                    } catch (SQLException sQLException) {
                        Frag_CanChuyen.this.DangXuat = "(the_loai = 'deb' or the_loai = 'det')";
                    }
                }
            }
        });
        this.radio_dea.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_CanChuyen.this.radio_dea.isChecked()) {
                    Frag_CanChuyen.this.DangXuat = "the_loai = 'dea'";
                    Frag_CanChuyen.this.ln_xi.setVisibility(8);
                    Frag_CanChuyen.this.xem_RecycView();
                }
            }
        });
        this.radio_deb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_CanChuyen.this.radio_deb.isChecked()) {
                    Frag_CanChuyen.this.DangXuat = "(the_loai = 'deb' or the_loai = 'det')";
                    Frag_CanChuyen.this.ln_xi.setVisibility(8);
                    Frag_CanChuyen.this.xem_RecycView();
                }
            }
        });
        this.radio_dec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_CanChuyen.this.radio_dec.isChecked()) {
                    Frag_CanChuyen.this.DangXuat = "the_loai = 'dec'";
                    Frag_CanChuyen.this.ln_xi.setVisibility(8);
                    Frag_CanChuyen.this.xem_RecycView();
                }
            }
        });
        this.radio_ded.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_CanChuyen.this.radio_ded.isChecked()) {
                    Frag_CanChuyen.this.DangXuat = "the_loai = 'ded'";
                    Frag_CanChuyen.this.ln_xi.setVisibility(8);
                    Frag_CanChuyen.this.xem_RecycView();
                }
            }
        });
        this.radio_lo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_CanChuyen.this.radio_lo.isChecked()) {
                    Frag_CanChuyen.this.DangXuat = "the_loai = 'lo'";
                    Frag_CanChuyen.this.ln_xi.setVisibility(8);
                    Frag_CanChuyen.this.li_loaide.setVisibility(8);
                    Frag_CanChuyen.this.layout.setVisibility(0);
                    Frag_CanChuyen.this.xem_RecycView();
                }
            }
        });
        this.radio_xi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_CanChuyen.this.radio_xi.isChecked()) {
                    new MainActivity();
                    Frag_CanChuyen.this.DangXuat = "the_loai = 'xi'";
                    Frag_CanChuyen.this.layout.setVisibility(8);
                    Frag_CanChuyen.this.ln_xi.setVisibility(0);
                    Frag_CanChuyen.this.li_loaide.setVisibility(8);
                    try {
                        Database database = Frag_CanChuyen.this.db;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append("Select count(id) From tbl_soctS WHERE the_loai = 'xn' AND ngay_nhan = '");
                        stringBuilder.append(MainActivity.Get_date());
                        stringBuilder.append("'");
                        Cursor cursor = database.GetData(stringBuilder.toString());
                        if (cursor.moveToFirst() && cursor.getInt(0) > 0)
                            Frag_CanChuyen.this.check_xn.setVisibility(0);
                        Frag_CanChuyen.this.xem_RecycView();
                    } catch (SQLException sQLException) {}
                }
            }
        });
        this.radio_bc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_CanChuyen.this.radio_bc.isChecked()) {
                    Frag_CanChuyen.this.DangXuat = "the_loai = 'bc'";
                    Frag_CanChuyen.this.layout.setVisibility(8);
                    Frag_CanChuyen.this.ln_xi.setVisibility(8);
                    Frag_CanChuyen.this.li_loaide.setVisibility(8);
                    Frag_CanChuyen.this.xem_RecycView();
                }
            }
        });
        this.check_x2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_CanChuyen.this.check_x2.isChecked()) {
                    Frag_CanChuyen.this.DangXuat = "the_loai = 'xi'";
                    Frag_CanChuyen.this.lay_x2 = "length(so_chon) = 5 ";
                    Frag_CanChuyen.this.check_xn.setChecked(false);
                } else {
                    Frag_CanChuyen.this.lay_x2 = "";
                }
                Frag_CanChuyen.this.xem_RecycView();
            }
        });
        this.check_x3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_CanChuyen.this.check_x3.isChecked()) {
                    Frag_CanChuyen.this.DangXuat = "the_loai = 'xi'";
                    Frag_CanChuyen.this.lay_x3 = "OR length(so_chon) = 8 ";
                    Frag_CanChuyen.this.check_xn.setChecked(false);
                } else {
                    Frag_CanChuyen.this.lay_x3 = "";
                }
                Frag_CanChuyen.this.xem_RecycView();
            }
        });
        this.check_x4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (Frag_CanChuyen.this.check_x4.isChecked()) {
                    Frag_CanChuyen.this.DangXuat = "the_loai = 'xi'";
                    Frag_CanChuyen.this.lay_x4 = "OR length(so_chon) = 11 ";
                    Frag_CanChuyen.this.check_xn.setChecked(false);
                } else {
                    Frag_CanChuyen.this.lay_x4 = "";
                }
                Frag_CanChuyen.this.xem_RecycView();
            }
        });
        this.check_xn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (Frag_CanChuyen.this.check_xn.isChecked()) {
                    Frag_CanChuyen.this.DangXuat = "the_loai = 'xn'";
                    Frag_CanChuyen.this.check_x2.setChecked(false);
                    Frag_CanChuyen.this.check_x3.setChecked(false);
                    Frag_CanChuyen.this.check_x4.setChecked(false);
                    Frag_CanChuyen.this.xem_RecycView();
                }
            }
        });
        this.btn_Xuatso.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (Congthuc.isNumeric(Frag_CanChuyen.this.edt_tien.getText().toString().replaceAll("%", "").replaceAll("n", "").replaceAll("k", "").replaceAll("d", "").replaceAll(">", "").replaceAll("\\.", "")) || Frag_CanChuyen.this.edt_tien.getText().toString().length() == 0) {
                    Frag_CanChuyen.this.btn_click();
                    return;
                }
                Toast.makeText((Context)Frag_CanChuyen.this.getActivity(), "Kitra lti, 1).show();
            }
        });
        this.lay_x2 = "length(so_chon) = 5 ";
        this.lay_x3 = "OR length(so_chon) = 8 ";
        this.lay_x4 = "OR length(so_chon) = 11 ";
        this.no_rp_number.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                try {
                    String str = MainActivity.Get_date();
                    StringBuilder stringBuilder = new StringBuilder();
                    this();
                    stringBuilder.append("Select ten_kh, sum(diem_quydoi) From tbl_soctS WHERE so_chon = '");
                    stringBuilder.append(Frag_CanChuyen.this.mSo.get(param1Int));
                    stringBuilder.append("' AND ngay_nhan = '");
                    stringBuilder.append(str);
                    stringBuilder.append("' AND type_kh = 1 AND ");
                    stringBuilder.append(Frag_CanChuyen.this.DangXuat);
                    stringBuilder.append(" GROUP BY so_dienthoai");
                    str = stringBuilder.toString();
                    Cursor cursor = Frag_CanChuyen.this.db.GetData(str);
                    for (str = ""; cursor.moveToNext(); str = stringBuilder1.toString()) {
                        StringBuilder stringBuilder1 = new StringBuilder();
                        this();
                        stringBuilder1.append(str);
                        stringBuilder1.append(cursor.getString(0));
                        stringBuilder1.append(": ");
                        stringBuilder1.append(cursor.getString(1));
                        stringBuilder1.append("\n");
                    }
                    Toast.makeText((Context)Frag_CanChuyen.this.getActivity(), str, 1).show();
                } catch (SQLException sQLException) {}
            }
        });
        try {
            if (MainActivity.jSon_Setting.getInt("bao_cao_so") == 0) {
                this.sapxep = "diem DESC";
            } else {
                this.sapxep = "ton DESC, diem DESC";
            }
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        xemlv();
        return this.v;
    }

    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacks(this.runnable);
    }

    public void onResume() {
        xem_RecycView();
        super.onResume();
    }

    public void xem_RecycView() {
        String str2;
        new MainActivity();
        String str1 = MainActivity.Get_date();
        StringBuilder stringBuilder = null;
        this.mSo.clear();
        this.mTienNhan.clear();
        this.mTienOm.clear();
        this.mTienchuyen.clear();
        this.mTienTon.clear();
        this.mNhay.clear();
        String str3 = this.DangXuat;
        if (str3 == "(the_loai = 'deb' or the_loai = 'det')") {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Select tbl_soctS.So_chon\n, Sum((tbl_soctS.type_kh = 1) * (100-tbl_soctS.diem_khachgiu)*diem_quydoi/100) as diem\n, so_om.Om_deB + sum(tbl_soctS.diem_dly_giu*tbl_soctS.diem_quydoi/100) as So_Om\n, Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) as chuyen\n, Sum((tbl_soctS.type_kh =1) * (100-tbl_soctS.diem_khachgiu-tbl_soctS.diem_dly_giu)*diem_quydoi/100) - Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) - so_om.Om_deB as ton\n, so_nhay  From so_om Left Join tbl_soctS On tbl_soctS.so_chon = so_om.So\n Where tbl_soctS.ngay_nhan='");
            stringBuilder.append(str1);
            stringBuilder.append("' AND (tbl_soctS.the_loai='deb' OR tbl_soctS.the_loai='det') GROUP by so_om.So Order by ");
            stringBuilder.append(this.sapxep);
            str2 = stringBuilder.toString();
        } else if (str3 == "the_loai = 'lo'") {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Select tbl_soctS.So_chon\n, Sum((tbl_soctS.type_kh = 1) * (100-tbl_soctS.diem_khachgiu)*diem_quydoi/100) as diem\n, so_om.Om_Lo + sum(tbl_soctS.diem_dly_giu*tbl_soctS.diem_quydoi/100) as So_Om\n, Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) as chuyen\n, Sum((tbl_soctS.type_kh =1) * (100-tbl_soctS.diem_khachgiu-tbl_soctS.diem_dly_giu)*diem_quydoi/100) - Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) - so_om.Om_Lo as ton\n, so_nhay  From so_om Left Join tbl_soctS On tbl_soctS.so_chon = so_om.So \n Where tbl_soctS.ngay_nhan='");
            stringBuilder.append(str1);
            stringBuilder.append("' AND tbl_soctS.the_loai='lo' \n GROUP by so_om.So Order by ");
            stringBuilder.append(this.sapxep);
            str2 = stringBuilder.toString();
        } else if (str3 == "the_loai = 'dea'") {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Select tbl_soctS.So_chon\n, Sum((tbl_soctS.type_kh = 1) * (100-tbl_soctS.diem_khachgiu)*diem_quydoi/100) as diem\n, so_om.Om_DeA + sum(tbl_soctS.diem_dly_giu*tbl_soctS.diem_quydoi/100) as So_Om\n, Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) as chuyen\n, Sum((tbl_soctS.type_kh =1) * (100-tbl_soctS.diem_khachgiu-tbl_soctS.diem_dly_giu)*diem_quydoi/100) - Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) - so_om.Om_DeA as ton\n, so_nhay  From so_om Left Join tbl_soctS On tbl_soctS.so_chon = so_om.So \n Where tbl_soctS.ngay_nhan='");
            stringBuilder.append(str1);
            stringBuilder.append("' AND tbl_soctS.the_loai='dea' GROUP by so_chon Order by ");
            stringBuilder.append(this.sapxep);
            str2 = stringBuilder.toString();
        } else if (str3 == "the_loai = 'dec'") {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Select tbl_soctS.So_chon\n, Sum((tbl_soctS.type_kh = 1) * (100-tbl_soctS.diem_khachgiu)*diem_quydoi/100) as diem\n, so_om.Om_DeC + sum(tbl_soctS.diem_dly_giu*tbl_soctS.diem_quydoi/100) as So_Om\n, Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) as chuyen\n, Sum((tbl_soctS.type_kh =1) * (100-tbl_soctS.diem_khachgiu-tbl_soctS.diem_dly_giu)*diem_quydoi/100) - Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) - so_om.Om_DeC as ton\n, so_nhay  From so_om Left Join tbl_soctS On tbl_soctS.so_chon = so_om.So \n Where tbl_soctS.ngay_nhan='");
            stringBuilder.append(str1);
            stringBuilder.append("' AND tbl_soctS.the_loai='dec' GROUP by so_chon Order by ");
            stringBuilder.append(this.sapxep);
            str2 = stringBuilder.toString();
        } else if (str3 == "the_loai = 'ded'") {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Select tbl_soctS.So_chon\n, Sum((tbl_soctS.type_kh = 1) * (100-tbl_soctS.diem_khachgiu)*diem_quydoi/100) as diem\n, so_om.Om_DeD + sum(tbl_soctS.diem_dly_giu*tbl_soctS.diem_quydoi/100) as So_Om\n, Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) as chuyen\n, Sum((tbl_soctS.type_kh =1) * (100-tbl_soctS.diem_khachgiu-tbl_soctS.diem_dly_giu)*diem_quydoi/100) - Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) - so_om.Om_DeD as ton\n, so_nhay  From so_om Left Join tbl_soctS On tbl_soctS.so_chon = so_om.So \n Where tbl_soctS.ngay_nhan='");
            stringBuilder.append(str1);
            stringBuilder.append("' AND tbl_soctS.the_loai='ded' GROUP by so_chon Order by ");
            stringBuilder.append(this.sapxep);
            str2 = stringBuilder.toString();
        } else {
            Cursor cursor1;
            if (str3 == "the_loai = 'xi'") {
                if (this.lay_x2 == "" && this.lay_x3 == "" && this.lay_x4 == "") {
                    str2 = "";
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(" And (");
                    stringBuilder.append(this.lay_x2);
                    stringBuilder.append(this.lay_x3);
                    stringBuilder.append(this.lay_x4);
                    stringBuilder.append(")");
                    str2 = stringBuilder.toString().replaceAll("\\(OR", "(");
                }
                cursor1 = this.db.GetData("Select * From So_om WHERE ID = 1");
                cursor1.moveToFirst();
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("SELECT so_chon, sum((type_kh =1)*(100-diem_khachgiu)*diem_quydoi)/100 AS diem, ((length(so_chon) = 5) * ");
                stringBuilder1.append(cursor1.getString(7));
                stringBuilder1.append(" +(length(so_chon) = 8) * ");
                stringBuilder1.append(cursor1.getString(8));
                stringBuilder1.append(" +(length(so_chon) = 11) * ");
                stringBuilder1.append(cursor1.getString(9));
                stringBuilder1.append(" + sum(diem_dly_giu*diem_quydoi/100)) AS Om, SUm((type_kh =2)*diem) as chuyen , SUm((type_kh =1)*(100-diem_khachgiu-diem_dly_giu)*diem_quydoi/100)-SUm((type_kh =2)*diem) -  ((length(so_chon) = 5) * ");
                stringBuilder1.append(cursor1.getString(7));
                stringBuilder1.append(" +(length(so_chon) = 8) * ");
                stringBuilder1.append(cursor1.getString(8));
                stringBuilder1.append(" +(length(so_chon) = 11) * ");
                stringBuilder1.append(cursor1.getString(9));
                stringBuilder1.append(") AS ton, so_nhay   From tbl_soctS Where ngay_nhan='");
                stringBuilder1.append(str1);
                stringBuilder1.append("' AND the_loai='xi'");
                stringBuilder1.append(str2);
                stringBuilder1.append("  GROUP by so_chon Order by ton DESC, diem DESC");
                str2 = stringBuilder1.toString();
                if (cursor1 != null && !cursor1.isClosed())
                    cursor1.close();
            } else if (cursor1 == "the_loai = 'bc'") {
                Cursor cursor2 = this.db.GetData("Select * From So_om WHERE ID = 1");
                cursor2.moveToFirst();
                cursor1 = cursor2;
                if (cursor2.getInt(10) == 1) {
                    this.db.QueryData("Update so_om set om_bc=0 WHERE id = 1");
                    cursor1 = this.db.GetData("Select * From So_om WHERE ID = 1");
                    cursor1.moveToFirst();
                }
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("SELECT so_chon, sum((type_kh = 1)*(100-diem_khachgiu)*diem_quydoi/100) AS diem, ");
                stringBuilder1.append(cursor1.getString(10));
                stringBuilder1.append(" + sum(diem_dly_giu*diem_quydoi)/100 AS Om, SUm((type_kh = 2)*diem) as Chuyen, sum((type_kh =1)*(100-diem_khachgiu-diem_dly_giu)*diem_quydoi/100) - sum((type_kh =2)*diem) -");
                stringBuilder1.append(cursor1.getString(10));
                stringBuilder1.append(" AS ton, so_nhay   From tbl_soctS Where ngay_nhan='");
                stringBuilder1.append(str1);
                stringBuilder1.append("' AND the_loai='bc' GROUP by so_chon Order by ton DESC, diem DESC");
                str1 = stringBuilder1.toString();
                str2 = str1;
                if (cursor1 != null) {
                    str2 = str1;
                    if (!cursor1.isClosed()) {
                        cursor1.close();
                        str2 = str1;
                    }
                }
            } else if (cursor1 == "the_loai = 'xn'") {
                stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT so_chon, sum((type_kh =1)*(diem_quydoi)) AS diem, sum(tbl_soctS.diem_dly_giu) AS Om, SUm((type_kh =2)*diem) as chuyen , SUm((type_kh =1)*diem_ton-(type_kh =2)*diem_ton) AS ton, so_nhay   From tbl_soctS Where ngay_nhan='");
                stringBuilder.append(str1);
                stringBuilder.append("' AND the_loai='xn' GROUP by so_chon Order by ton DESC, diem DESC");
                str2 = stringBuilder.toString();
            }
        }
        Cursor cursor = this.db.GetData(str2);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                this.mSo.add(cursor.getString(0));
                this.mTienNhan.add(decimalFormat.format(cursor.getInt(1)));
                this.mTienOm.add(decimalFormat.format(cursor.getInt(2)));
                this.mTienchuyen.add(decimalFormat.format(cursor.getInt(3)));
                this.mTienTon.add(decimalFormat.format(cursor.getInt(4)));
                this.mNhay.add(Integer.valueOf(cursor.getInt(5)));
            }
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        if (getActivity() != null)
            this.no_rp_number.setAdapter((ListAdapter)new So_OmAdapter((Context)getActivity(), 2131427395, this.mSo));
    }

    public void xemlv() {
        if (this.DangXuat != null) {
            xem_RecycView();
        } else {
            this.radio_de.setChecked(true);
        }
    }

    class So_OmAdapter extends ArrayAdapter {
        public So_OmAdapter(Context param1Context, int param1Int, List<String> param1List) {
            super(param1Context, param1Int, param1List);
        }

        public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
            StringBuilder stringBuilder;
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService("layout_inflater");
            ViewHolder viewHolder = new ViewHolder();
            if (param1View == null) {
                param1View = layoutInflater.inflate(2131427395, null);
                viewHolder.tview5 = (TextView)param1View.findViewById(2131230742);
                viewHolder.tview7 = (TextView)param1View.findViewById(2131231439);
                viewHolder.tview8 = (TextView)param1View.findViewById(2131231440);
                viewHolder.tview1 = (TextView)param1View.findViewById(2131231424);
                viewHolder.tview4 = (TextView)param1View.findViewById(2131231441);
                viewHolder.tview2 = (TextView)param1View.findViewById(2131231286);
                param1View.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder)param1View.getTag();
            }
            if (((Integer)Frag_CanChuyen.this.mNhay.get(param1Int)).intValue() > 0) {
                viewHolder.tview5.setTextColor(-65536);
                viewHolder.tview7.setTextColor(-65536);
                viewHolder.tview8.setTextColor(-65536);
                viewHolder.tview1.setTextColor(-65536);
                viewHolder.tview4.setTextColor(-65536);
                if (((Integer)Frag_CanChuyen.this.mNhay.get(param1Int)).intValue() == 1) {
                    TextView textView1 = viewHolder.tview5;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Frag_CanChuyen.this.mSo.get(param1Int));
                    stringBuilder1.append("*");
                    textView1.setText(stringBuilder1.toString());
                } else if (((Integer)Frag_CanChuyen.this.mNhay.get(param1Int)).intValue() == 2) {
                    TextView textView1 = viewHolder.tview5;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Frag_CanChuyen.this.mSo.get(param1Int));
                    stringBuilder1.append("**");
                    textView1.setText(stringBuilder1.toString());
                } else if (((Integer)Frag_CanChuyen.this.mNhay.get(param1Int)).intValue() == 3) {
                    TextView textView1 = viewHolder.tview5;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Frag_CanChuyen.this.mSo.get(param1Int));
                    stringBuilder1.append("***");
                    textView1.setText(stringBuilder1.toString());
                } else if (((Integer)Frag_CanChuyen.this.mNhay.get(param1Int)).intValue() == 4) {
                    TextView textView1 = viewHolder.tview5;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Frag_CanChuyen.this.mSo.get(param1Int));
                    stringBuilder1.append("****");
                    textView1.setText(stringBuilder1.toString());
                } else if (((Integer)Frag_CanChuyen.this.mNhay.get(param1Int)).intValue() == 5) {
                    TextView textView1 = viewHolder.tview5;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Frag_CanChuyen.this.mSo.get(param1Int));
                    stringBuilder1.append("*****");
                    textView1.setText(stringBuilder1.toString());
                } else if (((Integer)Frag_CanChuyen.this.mNhay.get(param1Int)).intValue() == 6) {
                    TextView textView1 = viewHolder.tview5;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(Frag_CanChuyen.this.mSo.get(param1Int));
                    stringBuilder1.append("******");
                    textView1.setText(stringBuilder1.toString());
                }
                viewHolder.tview7.setText(Frag_CanChuyen.this.mTienNhan.get(param1Int));
                viewHolder.tview8.setText(Frag_CanChuyen.this.mTienOm.get(param1Int));
                viewHolder.tview1.setText(Frag_CanChuyen.this.mTienchuyen.get(param1Int));
                viewHolder.tview4.setText(Frag_CanChuyen.this.mTienTon.get(param1Int));
                TextView textView = viewHolder.tview2;
                stringBuilder = new StringBuilder();
                stringBuilder.append(param1Int + 1);
                stringBuilder.append("");
                textView.setText(stringBuilder.toString());
            } else {
                ((ViewHolder)stringBuilder).tview5.setTextColor(-16777216);
                ((ViewHolder)stringBuilder).tview7.setTextColor(-16777216);
                ((ViewHolder)stringBuilder).tview8.setTextColor(-16777216);
                ((ViewHolder)stringBuilder).tview1.setTextColor(-16777216);
                ((ViewHolder)stringBuilder).tview4.setTextColor(-16777216);
                ((ViewHolder)stringBuilder).tview5.setText(Frag_CanChuyen.this.mSo.get(param1Int));
                ((ViewHolder)stringBuilder).tview7.setText(Frag_CanChuyen.this.mTienNhan.get(param1Int));
                ((ViewHolder)stringBuilder).tview8.setText(Frag_CanChuyen.this.mTienOm.get(param1Int));
                ((ViewHolder)stringBuilder).tview1.setText(Frag_CanChuyen.this.mTienchuyen.get(param1Int));
                ((ViewHolder)stringBuilder).tview4.setText(Frag_CanChuyen.this.mTienTon.get(param1Int));
                TextView textView = ((ViewHolder)stringBuilder).tview2;
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append(param1Int + 1);
                stringBuilder1.append("");
                textView.setText(stringBuilder1.toString());
            }
            return param1View;
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
