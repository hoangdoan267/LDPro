package tamhoang.ldpro4.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Congthuc.BaseToolBarActivity;
import tamhoang.ldpro4.data.Database;

public class Activity_AddKH extends BaseToolBarActivity {
    String app_use;

    Button btn_danhba;

    Button btn_them_KH;

    JSONObject caidat_gia;

    JSONObject caidat_tg;

    Cursor cursor;

    Database db;

    EditText edt_an3c;

    EditText edt_anLo;

    EditText edt_anXN;

    EditText edt_andea;

    EditText edt_andeb;

    EditText edt_andec;

    EditText edt_anded;

    EditText edt_andet;

    EditText edt_anx2;

    EditText edt_anx3;

    EditText edt_anx4;

    EditText edt_gia3c;

    EditText edt_giaXN;

    EditText edt_giadea;

    EditText edt_giadeb;

    EditText edt_giadec;

    EditText edt_giaded;

    EditText edt_giadet;

    EditText edt_gialo;

    EditText edt_giax2;

    EditText edt_giax3;

    EditText edt_giax4;

    EditText edt_sdt;

    EditText edt_ten;

    JSONObject json;

    JSONObject json_KhongMax;

    LinearLayout linner_sodienthoai;

    RadioButton rad_chu;

    RadioButton rad_chu_khach;

    RadioButton rad_khach;

    String so_dienthoai;

    String ten_khach;

    int type;

    protected int getLayoutId() {
        return 2131427357;
    }

    public void init() {
        this.linner_sodienthoai = (LinearLayout)findViewById(2131231046);
        this.edt_ten = (EditText)findViewById(2131230960);
        this.edt_sdt = (EditText)findViewById(2131230959);
        this.rad_chu = (RadioButton)findViewById(2131231154);
        this.rad_khach = (RadioButton)findViewById(2131231158);
        this.rad_chu_khach = (RadioButton)findViewById(2131231155);
        this.edt_giadea = (EditText)findViewById(2131230951);
        this.edt_andea = (EditText)findViewById(2131230940);
        this.edt_giadeb = (EditText)findViewById(2131230952);
        this.edt_andeb = (EditText)findViewById(2131230941);
        this.edt_giadec = (EditText)findViewById(2131230953);
        this.edt_andec = (EditText)findViewById(2131230942);
        this.edt_giaded = (EditText)findViewById(2131230954);
        this.edt_anded = (EditText)findViewById(2131230943);
        this.edt_giadet = (EditText)findViewById(2131230955);
        this.edt_andet = (EditText)findViewById(2131230944);
        this.edt_gialo = (EditText)findViewById(2131230946);
        this.edt_anLo = (EditText)findViewById(2131230935);
        this.edt_giax2 = (EditText)findViewById(2131230947);
        this.edt_anx2 = (EditText)findViewById(2131230936);
        this.edt_giax3 = (EditText)findViewById(2131230948);
        this.edt_anx3 = (EditText)findViewById(2131230937);
        this.edt_giax4 = (EditText)findViewById(2131230949);
        this.edt_anx4 = (EditText)findViewById(2131230938);
        this.edt_giaXN = (EditText)findViewById(2131230950);
        this.edt_anXN = (EditText)findViewById(2131230939);
        this.edt_gia3c = (EditText)findViewById(2131230945);
        this.edt_an3c = (EditText)findViewById(2131230934);
        this.btn_them_KH = (Button)findViewById(2131230830);
        this.btn_danhba = (Button)findViewById(2131230818);
    }

    public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        if (paramInt1 == 2015 && paramInt2 == -1) {
            Cursor cursor = getContentResolver().query(paramIntent.getData(), null, null, null, null);
            cursor.moveToFirst();
            paramInt1 = cursor.getColumnIndex("data1");
            cursor.getColumnIndex("display_name");
            String str2 = cursor.getString(cursor.getColumnIndex("display_name"));
            String str3 = cursor.getString(paramInt1).replaceAll(" ", "");
            String str1 = str3;
            if (str3.length() < 12) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("+84");
                stringBuilder.append(str3.substring(1));
                str1 = stringBuilder.toString();
            }
            this.edt_sdt.setText(str1);
            this.edt_ten.setText(str2);
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131427357);
        this.db = new Database((Context)this);
        init();
        Intent intent = getIntent();
        this.ten_khach = intent.getStringExtra("tenKH");
        this.so_dienthoai = intent.getStringExtra("so_dienthoai");
        this.app_use = intent.getStringExtra("use_app");
        this.db = new Database((Context)this);
        int i = 0;
        if (this.ten_khach.length() > 0) {
            this.edt_ten.setText(this.ten_khach);
            this.edt_sdt.setText(this.so_dienthoai);
            Database database = this.db;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Select * From tbl_kh_new where ten_kh = '");
            stringBuilder.append(this.ten_khach);
            stringBuilder.append("'");
            Cursor cursor = database.GetData(stringBuilder.toString());
            this.cursor = cursor;
            cursor.moveToFirst();
            i = this.cursor.getCount();
        }
        if (i > 0) {
            this.edt_sdt.setText(this.cursor.getString(1));
            if (this.cursor.getString(2).indexOf("sms") == -1) {
                this.linner_sodienthoai.setEnabled(false);
                this.edt_ten.setEnabled(false);
                this.edt_sdt.setEnabled(false);
                this.btn_danhba.setEnabled(false);
            }
            if (this.cursor.getCount() > 0) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    this(this.cursor.getString(5));
                    this.json = jSONObject;
                    jSONObject = jSONObject.getJSONObject("caidat_gia");
                    this.caidat_gia = jSONObject;
                    this.edt_giadea.setText(jSONObject.getString("dea"));
                    this.edt_andea.setText(this.caidat_gia.getString("an_dea"));
                    this.edt_giadeb.setText(this.caidat_gia.getString("deb"));
                    this.edt_andeb.setText(this.caidat_gia.getString("an_deb"));
                    this.edt_giadec.setText(this.caidat_gia.getString("dec"));
                    this.edt_andec.setText(this.caidat_gia.getString("an_dec"));
                    this.edt_giaded.setText(this.caidat_gia.getString("ded"));
                    this.edt_anded.setText(this.caidat_gia.getString("an_ded"));
                    this.edt_giadet.setText(this.caidat_gia.getString("det"));
                    this.edt_andet.setText(this.caidat_gia.getString("an_det"));
                    this.edt_gialo.setText(this.caidat_gia.getString("lo"));
                    this.edt_anLo.setText(this.caidat_gia.getString("an_lo"));
                    this.edt_giax2.setText(this.caidat_gia.getString("gia_x2"));
                    this.edt_anx2.setText(this.caidat_gia.getString("an_x2"));
                    this.edt_giax3.setText(this.caidat_gia.getString("gia_x3"));
                    this.edt_anx3.setText(this.caidat_gia.getString("an_x3"));
                    this.edt_giax4.setText(this.caidat_gia.getString("gia_x4"));
                    this.edt_anx4.setText(this.caidat_gia.getString("an_x4"));
                    this.edt_giaXN.setText(this.caidat_gia.getString("gia_xn"));
                    this.edt_anXN.setText(this.caidat_gia.getString("an_xn"));
                    this.edt_gia3c.setText(this.caidat_gia.getString("gia_bc"));
                    this.edt_an3c.setText(this.caidat_gia.getString("an_bc"));
                    if (this.cursor.getInt(3) == 1) {
                        this.rad_khach.setChecked(true);
                        this.rad_chu.setChecked(false);
                        this.rad_chu_khach.setChecked(false);
                    } else if (this.cursor.getInt(3) == 2) {
                        this.rad_khach.setChecked(false);
                        this.rad_chu.setChecked(true);
                        this.rad_chu_khach.setChecked(false);
                    } else if (this.cursor.getInt(3) == 3) {
                        this.rad_khach.setChecked(false);
                        this.rad_chu.setChecked(false);
                        this.rad_chu_khach.setChecked(true);
                    }
                    jSONObject = new JSONObject();
                    this(this.cursor.getString(6));
                    this.json_KhongMax = jSONObject;
                } catch (JSONException jSONException) {
                    jSONException.printStackTrace();
                }
                Cursor cursor = this.cursor;
                if (cursor != null && !cursor.isClosed())
                    this.cursor.close();
            }
        } else if (this.app_use.indexOf("sms") <= -1) {
            this.edt_ten.setText(this.ten_khach);
            this.edt_sdt.setText(this.so_dienthoai);
            this.edt_ten.setEnabled(false);
            this.edt_sdt.setEnabled(false);
            this.btn_danhba.setEnabled(false);
        }
        this.btn_danhba.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                Activity_AddKH.this.startActivityForResult(new Intent("android.intent.action.PICK", ContactsContract.CommonDataKinds.Phone.CONTENT_URI), 2015);
            }
        });
        this.btn_them_KH.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                // Byte code:
                //   0: aload_0
                //   1: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   4: getfield edt_ten : Landroid/widget/EditText;
                //   7: invokevirtual getText : ()Landroid/text/Editable;
                //   10: invokevirtual toString : ()Ljava/lang/String;
                //   13: astore_1
                //   14: aload_0
                //   15: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   18: getfield edt_sdt : Landroid/widget/EditText;
                //   21: invokevirtual getText : ()Landroid/text/Editable;
                //   24: invokevirtual toString : ()Ljava/lang/String;
                //   27: astore_2
                //   28: aload_2
                //   29: invokevirtual length : ()I
                //   32: ifle -> 2193
                //   35: aload_1
                //   36: invokevirtual length : ()I
                //   39: ifle -> 2193
                //   42: aload_2
                //   43: astore_1
                //   44: aload_2
                //   45: ldc '0'
                //   47: invokevirtual startsWith : (Ljava/lang/String;)Z
                //   50: ifeq -> 92
                //   53: aload_2
                //   54: astore_1
                //   55: aload_2
                //   56: invokestatic isNumeric : (Ljava/lang/String;)Z
                //   59: ifeq -> 92
                //   62: new java/lang/StringBuilder
                //   65: dup
                //   66: invokespecial <init> : ()V
                //   69: astore_1
                //   70: aload_1
                //   71: ldc '+84'
                //   73: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   76: pop
                //   77: aload_1
                //   78: aload_2
                //   79: iconst_1
                //   80: invokevirtual substring : (I)Ljava/lang/String;
                //   83: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   86: pop
                //   87: aload_1
                //   88: invokevirtual toString : ()Ljava/lang/String;
                //   91: astore_1
                //   92: aload_0
                //   93: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   96: astore_3
                //   97: aload_3
                //   98: getfield db : Ltamhoang/ldpro4/data/Database;
                //   101: astore #4
                //   103: new java/lang/StringBuilder
                //   106: dup
                //   107: invokespecial <init> : ()V
                //   110: astore_2
                //   111: aload_2
                //   112: ldc 'Select * From tbl_kh_new Where ten_kh <> ''
                //   114: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   117: pop
                //   118: aload_2
                //   119: aload_0
                //   120: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   123: getfield edt_ten : Landroid/widget/EditText;
                //   126: invokevirtual getText : ()Landroid/text/Editable;
                //   129: invokevirtual toString : ()Ljava/lang/String;
                //   132: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   135: pop
                //   136: aload_2
                //   137: ldc '' AND sdt = ''
                //   139: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   142: pop
                //   143: aload_2
                //   144: aload_1
                //   145: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   148: pop
                //   149: aload_2
                //   150: ldc '''
                //   152: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   155: pop
                //   156: aload_3
                //   157: aload #4
                //   159: aload_2
                //   160: invokevirtual toString : ()Ljava/lang/String;
                //   163: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
                //   166: putfield cursor : Landroid/database/Cursor;
                //   169: aload_0
                //   170: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   173: getfield cursor : Landroid/database/Cursor;
                //   176: invokeinterface getCount : ()I
                //   181: ifle -> 218
                //   184: aload_0
                //   185: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   188: ldc 'csSn
                //   190: ldc 'Mkhhchd1 sthovmsthochdcho 1 khh
                //   192: invokevirtual showAlertBox : (Ljava/lang/String;Ljava/lang/String;)Landroid/app/AlertDialog$Builder;
                //   195: ldc 'Ok'
                //   197: new tamhoang/ldpro4/Activity/Activity_AddKH$2$1
                //   200: dup
                //   201: aload_0
                //   202: invokespecial <init> : (Ltamhoang/ldpro4/Activity/Activity_AddKH$2;)V
                //   205: invokevirtual setPositiveButton : (Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;
                //   208: invokevirtual show : ()Landroid/app/AlertDialog;
                //   211: iconst_0
                //   212: invokevirtual setCanceledOnTouchOutside : (Z)V
                //   215: goto -> 2190
                //   218: aload_0
                //   219: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   222: getfield edt_giadea : Landroid/widget/EditText;
                //   225: invokevirtual getText : ()Landroid/text/Editable;
                //   228: invokevirtual toString : ()Ljava/lang/String;
                //   231: astore #5
                //   233: aload_0
                //   234: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   237: getfield edt_andea : Landroid/widget/EditText;
                //   240: invokevirtual getText : ()Landroid/text/Editable;
                //   243: invokevirtual toString : ()Ljava/lang/String;
                //   246: astore #6
                //   248: aload_0
                //   249: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   252: getfield edt_giadeb : Landroid/widget/EditText;
                //   255: invokevirtual getText : ()Landroid/text/Editable;
                //   258: invokevirtual toString : ()Ljava/lang/String;
                //   261: astore #7
                //   263: aload_0
                //   264: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   267: getfield edt_andeb : Landroid/widget/EditText;
                //   270: invokevirtual getText : ()Landroid/text/Editable;
                //   273: invokevirtual toString : ()Ljava/lang/String;
                //   276: astore #8
                //   278: aload_0
                //   279: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   282: getfield edt_giadec : Landroid/widget/EditText;
                //   285: invokevirtual getText : ()Landroid/text/Editable;
                //   288: invokevirtual toString : ()Ljava/lang/String;
                //   291: astore #9
                //   293: aload_0
                //   294: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   297: getfield edt_andec : Landroid/widget/EditText;
                //   300: invokevirtual getText : ()Landroid/text/Editable;
                //   303: invokevirtual toString : ()Ljava/lang/String;
                //   306: astore #10
                //   308: aload_0
                //   309: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   312: getfield edt_giaded : Landroid/widget/EditText;
                //   315: invokevirtual getText : ()Landroid/text/Editable;
                //   318: invokevirtual toString : ()Ljava/lang/String;
                //   321: astore #11
                //   323: aload_0
                //   324: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   327: getfield edt_anded : Landroid/widget/EditText;
                //   330: invokevirtual getText : ()Landroid/text/Editable;
                //   333: invokevirtual toString : ()Ljava/lang/String;
                //   336: astore #12
                //   338: aload_0
                //   339: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   342: getfield edt_giadet : Landroid/widget/EditText;
                //   345: invokevirtual getText : ()Landroid/text/Editable;
                //   348: invokevirtual toString : ()Ljava/lang/String;
                //   351: astore #13
                //   353: aload_0
                //   354: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   357: getfield edt_andet : Landroid/widget/EditText;
                //   360: invokevirtual getText : ()Landroid/text/Editable;
                //   363: invokevirtual toString : ()Ljava/lang/String;
                //   366: astore #14
                //   368: aload_0
                //   369: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   372: getfield edt_gialo : Landroid/widget/EditText;
                //   375: invokevirtual getText : ()Landroid/text/Editable;
                //   378: invokevirtual toString : ()Ljava/lang/String;
                //   381: astore #15
                //   383: aload_0
                //   384: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   387: getfield edt_anLo : Landroid/widget/EditText;
                //   390: invokevirtual getText : ()Landroid/text/Editable;
                //   393: invokevirtual toString : ()Ljava/lang/String;
                //   396: astore #16
                //   398: aload_0
                //   399: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   402: getfield edt_giax2 : Landroid/widget/EditText;
                //   405: invokevirtual getText : ()Landroid/text/Editable;
                //   408: invokevirtual toString : ()Ljava/lang/String;
                //   411: astore #17
                //   413: aload_0
                //   414: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   417: getfield edt_anx2 : Landroid/widget/EditText;
                //   420: invokevirtual getText : ()Landroid/text/Editable;
                //   423: invokevirtual toString : ()Ljava/lang/String;
                //   426: astore #18
                //   428: aload_0
                //   429: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   432: getfield edt_giax3 : Landroid/widget/EditText;
                //   435: invokevirtual getText : ()Landroid/text/Editable;
                //   438: invokevirtual toString : ()Ljava/lang/String;
                //   441: astore #19
                //   443: aload_0
                //   444: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   447: getfield edt_anx3 : Landroid/widget/EditText;
                //   450: invokevirtual getText : ()Landroid/text/Editable;
                //   453: invokevirtual toString : ()Ljava/lang/String;
                //   456: astore #20
                //   458: aload_0
                //   459: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   462: getfield edt_giax4 : Landroid/widget/EditText;
                //   465: invokevirtual getText : ()Landroid/text/Editable;
                //   468: invokevirtual toString : ()Ljava/lang/String;
                //   471: astore #21
                //   473: aload_0
                //   474: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   477: getfield edt_anx4 : Landroid/widget/EditText;
                //   480: invokevirtual getText : ()Landroid/text/Editable;
                //   483: invokevirtual toString : ()Ljava/lang/String;
                //   486: astore #22
                //   488: aload_0
                //   489: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   492: getfield edt_giaXN : Landroid/widget/EditText;
                //   495: invokevirtual getText : ()Landroid/text/Editable;
                //   498: invokevirtual toString : ()Ljava/lang/String;
                //   501: astore #23
                //   503: aload_0
                //   504: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   507: getfield edt_anXN : Landroid/widget/EditText;
                //   510: invokevirtual getText : ()Landroid/text/Editable;
                //   513: invokevirtual toString : ()Ljava/lang/String;
                //   516: astore #24
                //   518: aload_0
                //   519: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   522: getfield edt_gia3c : Landroid/widget/EditText;
                //   525: invokevirtual getText : ()Landroid/text/Editable;
                //   528: invokevirtual toString : ()Ljava/lang/String;
                //   531: astore_3
                //   532: aload_0
                //   533: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   536: getfield edt_an3c : Landroid/widget/EditText;
                //   539: invokevirtual getText : ()Landroid/text/Editable;
                //   542: invokevirtual toString : ()Ljava/lang/String;
                //   545: astore #4
                //   547: aload_0
                //   548: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   551: getfield rad_khach : Landroid/widget/RadioButton;
                //   554: invokevirtual isChecked : ()Z
                //   557: ifeq -> 571
                //   560: aload_0
                //   561: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   564: iconst_1
                //   565: putfield type : I
                //   568: goto -> 571
                //   571: aload_0
                //   572: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   575: getfield rad_chu : Landroid/widget/RadioButton;
                //   578: invokevirtual isChecked : ()Z
                //   581: ifeq -> 592
                //   584: aload_0
                //   585: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   588: iconst_2
                //   589: putfield type : I
                //   592: aload_0
                //   593: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   596: getfield rad_chu_khach : Landroid/widget/RadioButton;
                //   599: invokevirtual isChecked : ()Z
                //   602: ifeq -> 613
                //   605: aload_0
                //   606: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   609: iconst_3
                //   610: putfield type : I
                //   613: aload_0
                //   614: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   617: getfield app_use : Ljava/lang/String;
                //   620: ifnonnull -> 632
                //   623: aload_0
                //   624: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   627: ldc 'sms'
                //   629: putfield app_use : Ljava/lang/String;
                //   632: aload_0
                //   633: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   636: astore #25
                //   638: aload #25
                //   640: getfield db : Ltamhoang/ldpro4/data/Database;
                //   643: astore_2
                //   644: new java/lang/StringBuilder
                //   647: dup
                //   648: invokespecial <init> : ()V
                //   651: astore #26
                //   653: aload #26
                //   655: ldc 'Select * From tbl_kh_new Where ten_kh = ''
                //   657: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   660: pop
                //   661: aload #26
                //   663: aload_0
                //   664: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   667: getfield edt_ten : Landroid/widget/EditText;
                //   670: invokevirtual getText : ()Landroid/text/Editable;
                //   673: invokevirtual toString : ()Ljava/lang/String;
                //   676: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   679: pop
                //   680: aload #26
                //   682: ldc '''
                //   684: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   687: pop
                //   688: aload #25
                //   690: aload_2
                //   691: aload #26
                //   693: invokevirtual toString : ()Ljava/lang/String;
                //   696: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
                //   699: putfield cursor : Landroid/database/Cursor;
                //   702: aload_0
                //   703: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   706: getfield cursor : Landroid/database/Cursor;
                //   709: invokeinterface moveToFirst : ()Z
                //   714: pop
                //   715: aload_0
                //   716: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   719: getfield cursor : Landroid/database/Cursor;
                //   722: invokeinterface getCount : ()I
                //   727: ifle -> 874
                //   730: aload_0
                //   731: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   734: astore #25
                //   736: new org/json/JSONObject
                //   739: astore_2
                //   740: aload_0
                //   741: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   744: getfield cursor : Landroid/database/Cursor;
                //   747: astore #26
                //   749: aload_2
                //   750: aload #26
                //   752: iconst_5
                //   753: invokeinterface getString : (I)Ljava/lang/String;
                //   758: invokespecial <init> : (Ljava/lang/String;)V
                //   761: aload #25
                //   763: aload_2
                //   764: putfield json : Lorg/json/JSONObject;
                //   767: aload_0
                //   768: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   771: aload_0
                //   772: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   775: getfield json : Lorg/json/JSONObject;
                //   778: ldc 'caidat_gia'
                //   780: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
                //   783: putfield caidat_gia : Lorg/json/JSONObject;
                //   786: aload_0
                //   787: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   790: aload_0
                //   791: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   794: getfield json : Lorg/json/JSONObject;
                //   797: ldc 'caidat_tg'
                //   799: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
                //   802: putfield caidat_tg : Lorg/json/JSONObject;
                //   805: aload_0
                //   806: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   809: aload_0
                //   810: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   813: getfield cursor : Landroid/database/Cursor;
                //   816: iconst_2
                //   817: invokeinterface getString : (I)Ljava/lang/String;
                //   822: putfield app_use : Ljava/lang/String;
                //   825: aload_0
                //   826: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   829: astore #25
                //   831: new org/json/JSONObject
                //   834: astore_2
                //   835: aload_2
                //   836: aload_0
                //   837: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   840: getfield cursor : Landroid/database/Cursor;
                //   843: bipush #6
                //   845: invokeinterface getString : (I)Ljava/lang/String;
                //   850: invokespecial <init> : (Ljava/lang/String;)V
                //   853: aload #25
                //   855: aload_2
                //   856: putfield json_KhongMax : Lorg/json/JSONObject;
                //   859: goto -> 871
                //   862: astore_2
                //   863: goto -> 867
                //   866: astore_2
                //   867: aload_2
                //   868: invokevirtual printStackTrace : ()V
                //   871: goto -> 1403
                //   874: aload_0
                //   875: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   878: new org/json/JSONObject
                //   881: dup
                //   882: invokespecial <init> : ()V
                //   885: putfield json : Lorg/json/JSONObject;
                //   888: aload_0
                //   889: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   892: new org/json/JSONObject
                //   895: dup
                //   896: invokespecial <init> : ()V
                //   899: putfield caidat_gia : Lorg/json/JSONObject;
                //   902: aload_0
                //   903: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   906: new org/json/JSONObject
                //   909: dup
                //   910: invokespecial <init> : ()V
                //   913: putfield caidat_tg : Lorg/json/JSONObject;
                //   916: aload_0
                //   917: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   920: new org/json/JSONObject
                //   923: dup
                //   924: invokespecial <init> : ()V
                //   927: putfield json_KhongMax : Lorg/json/JSONObject;
                //   930: aload_0
                //   931: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   934: getfield caidat_tg : Lorg/json/JSONObject;
                //   937: ldc_w 'dlgiu_de'
                //   940: iconst_0
                //   941: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   944: pop
                //   945: aload_0
                //   946: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   949: getfield caidat_tg : Lorg/json/JSONObject;
                //   952: ldc_w 'dlgiu_lo'
                //   955: iconst_0
                //   956: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   959: pop
                //   960: aload_0
                //   961: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   964: getfield caidat_tg : Lorg/json/JSONObject;
                //   967: ldc_w 'dlgiu_xi'
                //   970: iconst_0
                //   971: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   974: pop
                //   975: aload_0
                //   976: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   979: getfield caidat_tg : Lorg/json/JSONObject;
                //   982: ldc_w 'dlgiu_xn'
                //   985: iconst_0
                //   986: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   989: pop
                //   990: aload_0
                //   991: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   994: getfield caidat_tg : Lorg/json/JSONObject;
                //   997: ldc_w 'dlgiu_bc'
                //   1000: iconst_0
                //   1001: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1004: pop
                //   1005: aload_0
                //   1006: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1009: getfield caidat_tg : Lorg/json/JSONObject;
                //   1012: ldc_w 'khgiu_de'
                //   1015: iconst_0
                //   1016: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1019: pop
                //   1020: aload_0
                //   1021: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1024: getfield caidat_tg : Lorg/json/JSONObject;
                //   1027: ldc_w 'khgiu_lo'
                //   1030: iconst_0
                //   1031: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1034: pop
                //   1035: aload_0
                //   1036: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1039: getfield caidat_tg : Lorg/json/JSONObject;
                //   1042: ldc_w 'khgiu_xi'
                //   1045: iconst_0
                //   1046: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1049: pop
                //   1050: aload_0
                //   1051: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1054: getfield caidat_tg : Lorg/json/JSONObject;
                //   1057: ldc_w 'khgiu_xn'
                //   1060: iconst_0
                //   1061: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1064: pop
                //   1065: aload_0
                //   1066: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1069: getfield caidat_tg : Lorg/json/JSONObject;
                //   1072: ldc_w 'khgiu_bc'
                //   1075: iconst_0
                //   1076: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1079: pop
                //   1080: aload_0
                //   1081: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1084: getfield caidat_tg : Lorg/json/JSONObject;
                //   1087: ldc_w 'ok_tin'
                //   1090: iconst_3
                //   1091: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1094: pop
                //   1095: aload_0
                //   1096: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1099: getfield caidat_tg : Lorg/json/JSONObject;
                //   1102: ldc_w 'xien_nhan'
                //   1105: iconst_0
                //   1106: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1109: pop
                //   1110: aload_0
                //   1111: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1114: getfield caidat_tg : Lorg/json/JSONObject;
                //   1117: ldc_w 'chot_sodu'
                //   1120: iconst_0
                //   1121: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1124: pop
                //   1125: aload_0
                //   1126: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1129: getfield caidat_tg : Lorg/json/JSONObject;
                //   1132: ldc_w 'tg_loxien'
                //   1135: ldc_w '18:13'
                //   1138: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1141: pop
                //   1142: aload_0
                //   1143: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1146: getfield caidat_tg : Lorg/json/JSONObject;
                //   1149: ldc_w 'tg_debc'
                //   1152: ldc_w '18:20'
                //   1155: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1158: pop
                //   1159: aload_0
                //   1160: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1163: getfield caidat_tg : Lorg/json/JSONObject;
                //   1166: ldc_w 'loi_donvi'
                //   1169: iconst_0
                //   1170: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1173: pop
                //   1174: aload_0
                //   1175: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1178: getfield caidat_tg : Lorg/json/JSONObject;
                //   1181: ldc_w 'heso_de'
                //   1184: iconst_0
                //   1185: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1188: pop
                //   1189: aload_0
                //   1190: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1193: getfield caidat_tg : Lorg/json/JSONObject;
                //   1196: ldc_w 'maxDe'
                //   1199: iconst_0
                //   1200: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1203: pop
                //   1204: aload_0
                //   1205: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1208: getfield caidat_tg : Lorg/json/JSONObject;
                //   1211: ldc_w 'maxLo'
                //   1214: iconst_0
                //   1215: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1218: pop
                //   1219: aload_0
                //   1220: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1223: getfield caidat_tg : Lorg/json/JSONObject;
                //   1226: ldc_w 'maxXi'
                //   1229: iconst_0
                //   1230: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1233: pop
                //   1234: aload_0
                //   1235: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1238: getfield caidat_tg : Lorg/json/JSONObject;
                //   1241: ldc_w 'maxCang'
                //   1244: iconst_0
                //   1245: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1248: pop
                //   1249: aload_0
                //   1250: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1253: getfield json_KhongMax : Lorg/json/JSONObject;
                //   1256: ldc_w 'danDe'
                //   1259: ldc_w ''
                //   1262: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1265: pop
                //   1266: aload_0
                //   1267: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1270: getfield json_KhongMax : Lorg/json/JSONObject;
                //   1273: ldc_w 'danLo'
                //   1276: ldc_w ''
                //   1279: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1282: pop
                //   1283: new org/json/JSONObject
                //   1286: astore_2
                //   1287: aload_2
                //   1288: invokespecial <init> : ()V
                //   1291: aload_0
                //   1292: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1295: getfield json_KhongMax : Lorg/json/JSONObject;
                //   1298: ldc_w 'soDe'
                //   1301: aload_2
                //   1302: invokevirtual toString : ()Ljava/lang/String;
                //   1305: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1308: pop
                //   1309: new org/json/JSONObject
                //   1312: astore_2
                //   1313: aload_2
                //   1314: invokespecial <init> : ()V
                //   1317: aload_0
                //   1318: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1321: getfield json_KhongMax : Lorg/json/JSONObject;
                //   1324: ldc_w 'soLo'
                //   1327: aload_2
                //   1328: invokevirtual toString : ()Ljava/lang/String;
                //   1331: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1334: pop
                //   1335: aload_0
                //   1336: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1339: getfield json_KhongMax : Lorg/json/JSONObject;
                //   1342: ldc_w 'xien2'
                //   1345: iconst_0
                //   1346: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1349: pop
                //   1350: aload_0
                //   1351: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1354: getfield json_KhongMax : Lorg/json/JSONObject;
                //   1357: ldc_w 'xien3'
                //   1360: iconst_0
                //   1361: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1364: pop
                //   1365: aload_0
                //   1366: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1369: getfield json_KhongMax : Lorg/json/JSONObject;
                //   1372: ldc_w 'xien4'
                //   1375: iconst_0
                //   1376: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1379: pop
                //   1380: aload_0
                //   1381: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1384: getfield json_KhongMax : Lorg/json/JSONObject;
                //   1387: ldc_w 'cang'
                //   1390: iconst_0
                //   1391: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
                //   1394: pop
                //   1395: goto -> 1403
                //   1398: astore_2
                //   1399: aload_2
                //   1400: invokevirtual printStackTrace : ()V
                //   1403: aload_0
                //   1404: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1407: getfield caidat_gia : Lorg/json/JSONObject;
                //   1410: ldc_w 'dea'
                //   1413: aload #5
                //   1415: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1418: pop
                //   1419: aload_0
                //   1420: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1423: getfield caidat_gia : Lorg/json/JSONObject;
                //   1426: ldc_w 'an_dea'
                //   1429: aload #6
                //   1431: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1434: pop
                //   1435: aload_0
                //   1436: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1439: getfield caidat_gia : Lorg/json/JSONObject;
                //   1442: ldc_w 'deb'
                //   1445: aload #7
                //   1447: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1450: pop
                //   1451: aload_0
                //   1452: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1455: getfield caidat_gia : Lorg/json/JSONObject;
                //   1458: ldc_w 'an_deb'
                //   1461: aload #8
                //   1463: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1466: pop
                //   1467: aload_0
                //   1468: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1471: getfield caidat_gia : Lorg/json/JSONObject;
                //   1474: ldc_w 'det'
                //   1477: aload #13
                //   1479: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1482: pop
                //   1483: aload_0
                //   1484: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1487: getfield caidat_gia : Lorg/json/JSONObject;
                //   1490: ldc_w 'an_det'
                //   1493: aload #14
                //   1495: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1498: pop
                //   1499: aload_0
                //   1500: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1503: getfield caidat_gia : Lorg/json/JSONObject;
                //   1506: ldc_w 'dec'
                //   1509: aload #9
                //   1511: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1514: pop
                //   1515: aload_0
                //   1516: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1519: getfield caidat_gia : Lorg/json/JSONObject;
                //   1522: ldc_w 'an_dec'
                //   1525: aload #10
                //   1527: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1530: pop
                //   1531: aload_0
                //   1532: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1535: getfield caidat_gia : Lorg/json/JSONObject;
                //   1538: ldc_w 'ded'
                //   1541: aload #11
                //   1543: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1546: pop
                //   1547: aload_0
                //   1548: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1551: getfield caidat_gia : Lorg/json/JSONObject;
                //   1554: astore_2
                //   1555: aload_2
                //   1556: ldc_w 'an_ded'
                //   1559: aload #12
                //   1561: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1564: pop
                //   1565: aload_0
                //   1566: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1569: getfield caidat_gia : Lorg/json/JSONObject;
                //   1572: astore_2
                //   1573: aload_2
                //   1574: ldc_w 'lo'
                //   1577: aload #15
                //   1579: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1582: pop
                //   1583: aload_0
                //   1584: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1587: getfield caidat_gia : Lorg/json/JSONObject;
                //   1590: astore_2
                //   1591: aload_2
                //   1592: ldc_w 'an_lo'
                //   1595: aload #16
                //   1597: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1600: pop
                //   1601: aload_0
                //   1602: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1605: getfield caidat_gia : Lorg/json/JSONObject;
                //   1608: astore_2
                //   1609: aload_2
                //   1610: ldc_w 'gia_x2'
                //   1613: aload #17
                //   1615: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1618: pop
                //   1619: aload_0
                //   1620: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1623: getfield caidat_gia : Lorg/json/JSONObject;
                //   1626: astore_2
                //   1627: aload_2
                //   1628: ldc_w 'an_x2'
                //   1631: aload #18
                //   1633: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1636: pop
                //   1637: aload_0
                //   1638: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1641: getfield caidat_gia : Lorg/json/JSONObject;
                //   1644: astore_2
                //   1645: aload_2
                //   1646: ldc_w 'gia_x3'
                //   1649: aload #19
                //   1651: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1654: pop
                //   1655: aload_0
                //   1656: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1659: getfield caidat_gia : Lorg/json/JSONObject;
                //   1662: astore_2
                //   1663: aload_2
                //   1664: ldc_w 'an_x3'
                //   1667: aload #20
                //   1669: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1672: pop
                //   1673: aload_0
                //   1674: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1677: getfield caidat_gia : Lorg/json/JSONObject;
                //   1680: astore_2
                //   1681: aload_2
                //   1682: ldc_w 'gia_x4'
                //   1685: aload #21
                //   1687: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1690: pop
                //   1691: aload_0
                //   1692: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1695: getfield caidat_gia : Lorg/json/JSONObject;
                //   1698: astore_2
                //   1699: aload_2
                //   1700: ldc_w 'an_x4'
                //   1703: aload #22
                //   1705: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1708: pop
                //   1709: aload_0
                //   1710: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1713: getfield caidat_gia : Lorg/json/JSONObject;
                //   1716: astore_2
                //   1717: aload_2
                //   1718: ldc_w 'gia_xn'
                //   1721: aload #23
                //   1723: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1726: pop
                //   1727: aload_0
                //   1728: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1731: getfield caidat_gia : Lorg/json/JSONObject;
                //   1734: astore_2
                //   1735: aload_2
                //   1736: ldc_w 'an_xn'
                //   1739: aload #24
                //   1741: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1744: pop
                //   1745: aload_0
                //   1746: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1749: getfield caidat_gia : Lorg/json/JSONObject;
                //   1752: astore_2
                //   1753: aload_2
                //   1754: ldc_w 'gia_bc'
                //   1757: aload_3
                //   1758: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1761: pop
                //   1762: aload_0
                //   1763: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1766: getfield caidat_gia : Lorg/json/JSONObject;
                //   1769: astore_2
                //   1770: aload_2
                //   1771: ldc_w 'an_bc'
                //   1774: aload #4
                //   1776: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1779: pop
                //   1780: aload_0
                //   1781: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1784: getfield json : Lorg/json/JSONObject;
                //   1787: ldc 'caidat_gia'
                //   1789: aload_0
                //   1790: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1793: getfield caidat_gia : Lorg/json/JSONObject;
                //   1796: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1799: pop
                //   1800: aload_0
                //   1801: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1804: getfield json : Lorg/json/JSONObject;
                //   1807: ldc 'caidat_tg'
                //   1809: aload_0
                //   1810: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1813: getfield caidat_tg : Lorg/json/JSONObject;
                //   1816: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   1819: pop
                //   1820: new java/lang/StringBuilder
                //   1823: astore_2
                //   1824: aload_2
                //   1825: invokespecial <init> : ()V
                //   1828: aload_2
                //   1829: ldc_w 'REPLACE Into tbl_kh_new Values (''
                //   1832: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   1835: pop
                //   1836: aload_2
                //   1837: aload_0
                //   1838: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1841: getfield edt_ten : Landroid/widget/EditText;
                //   1844: invokevirtual getText : ()Landroid/text/Editable;
                //   1847: invokevirtual toString : ()Ljava/lang/String;
                //   1850: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   1853: pop
                //   1854: aload_2
                //   1855: ldc_w '',''
                //   1858: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   1861: pop
                //   1862: aload_2
                //   1863: aload_1
                //   1864: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   1867: pop
                //   1868: aload_2
                //   1869: ldc_w '',''
                //   1872: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   1875: pop
                //   1876: aload_2
                //   1877: aload_0
                //   1878: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1881: getfield app_use : Ljava/lang/String;
                //   1884: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   1887: pop
                //   1888: aload_2
                //   1889: ldc_w '','
                //   1892: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   1895: pop
                //   1896: aload_2
                //   1897: aload_0
                //   1898: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1901: getfield type : I
                //   1904: invokevirtual append : (I)Ljava/lang/StringBuilder;
                //   1907: pop
                //   1908: aload_2
                //   1909: ldc_w ',0,''
                //   1912: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   1915: pop
                //   1916: aload_2
                //   1917: aload_0
                //   1918: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1921: getfield json : Lorg/json/JSONObject;
                //   1924: invokevirtual toString : ()Ljava/lang/String;
                //   1927: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   1930: pop
                //   1931: aload_2
                //   1932: ldc_w '',''
                //   1935: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   1938: pop
                //   1939: aload_2
                //   1940: aload_0
                //   1941: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1944: getfield json_KhongMax : Lorg/json/JSONObject;
                //   1947: invokevirtual toString : ()Ljava/lang/String;
                //   1950: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   1953: pop
                //   1954: aload_2
                //   1955: ldc_w '')'
                //   1958: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   1961: pop
                //   1962: aload_2
                //   1963: invokevirtual toString : ()Ljava/lang/String;
                //   1966: astore_1
                //   1967: aload_0
                //   1968: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1971: getfield db : Ltamhoang/ldpro4/data/Database;
                //   1974: aload_1
                //   1975: invokevirtual QueryData : (Ljava/lang/String;)V
                //   1978: aload_0
                //   1979: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1982: getfield type : I
                //   1985: iconst_2
                //   1986: if_icmpne -> 2049
                //   1989: aload_0
                //   1990: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   1993: getfield db : Ltamhoang/ldpro4/data/Database;
                //   1996: astore_2
                //   1997: new java/lang/StringBuilder
                //   2000: astore_1
                //   2001: aload_1
                //   2002: invokespecial <init> : ()V
                //   2005: aload_1
                //   2006: ldc_w 'Delete FROM tbl_chuyenthang WHERE sdt_nhan = ''
                //   2009: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   2012: pop
                //   2013: aload_1
                //   2014: aload_0
                //   2015: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   2018: getfield edt_sdt : Landroid/widget/EditText;
                //   2021: invokevirtual getText : ()Landroid/text/Editable;
                //   2024: invokevirtual toString : ()Ljava/lang/String;
                //   2027: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   2030: pop
                //   2031: aload_1
                //   2032: ldc '''
                //   2034: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   2037: pop
                //   2038: aload_2
                //   2039: aload_1
                //   2040: invokevirtual toString : ()Ljava/lang/String;
                //   2043: invokevirtual QueryData : (Ljava/lang/String;)V
                //   2046: goto -> 2049
                //   2049: aload_0
                //   2050: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   2053: getfield cursor : Landroid/database/Cursor;
                //   2056: invokeinterface getCount : ()I
                //   2061: ifle -> 2081
                //   2064: aload_0
                //   2065: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   2068: ldc_w 'cnhthtin!'
                //   2071: iconst_1
                //   2072: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
                //   2075: invokevirtual show : ()V
                //   2078: goto -> 2095
                //   2081: aload_0
                //   2082: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   2085: ldc_w 'thkhh
                //   2088: iconst_1
                //   2089: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
                //   2092: invokevirtual show : ()V
                //   2095: goto -> 2173
                //   2098: astore_1
                //   2099: goto -> 2159
                //   2102: astore_1
                //   2103: goto -> 2159
                //   2106: astore_1
                //   2107: goto -> 2159
                //   2110: astore_1
                //   2111: goto -> 2159
                //   2114: astore_1
                //   2115: goto -> 2159
                //   2118: astore_1
                //   2119: goto -> 2159
                //   2122: astore_1
                //   2123: goto -> 2159
                //   2126: astore_1
                //   2127: goto -> 2159
                //   2130: astore_1
                //   2131: goto -> 2159
                //   2134: astore_1
                //   2135: goto -> 2159
                //   2138: astore_1
                //   2139: goto -> 2159
                //   2142: astore_1
                //   2143: goto -> 2159
                //   2146: astore_1
                //   2147: goto -> 2159
                //   2150: astore_1
                //   2151: goto -> 2159
                //   2154: astore_1
                //   2155: goto -> 2159
                //   2158: astore_1
                //   2159: aload_0
                //   2160: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   2163: ldc_w 'Sai slihkitra l
                //   2166: iconst_1
                //   2167: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
                //   2170: invokevirtual show : ()V
                //   2173: aload_0
                //   2174: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   2177: getfield db : Ltamhoang/ldpro4/data/Database;
                //   2180: invokevirtual LayDanhsachKH : ()V
                //   2183: aload_0
                //   2184: getfield this$0 : Ltamhoang/ldpro4/Activity/Activity_AddKH;
                //   2187: invokevirtual finish : ()V
                //   2190: goto -> 2193
                //   2193: return
                // Exception table:
                //   from	to	target	type
                //   730	749	866	org/json/JSONException
                //   749	859	862	org/json/JSONException
                //   930	1395	1398	org/json/JSONException
                //   1403	1555	2158	java/lang/Exception
                //   1555	1573	2154	java/lang/Exception
                //   1573	1591	2150	java/lang/Exception
                //   1591	1609	2146	java/lang/Exception
                //   1609	1627	2142	java/lang/Exception
                //   1627	1645	2138	java/lang/Exception
                //   1645	1663	2134	java/lang/Exception
                //   1663	1681	2130	java/lang/Exception
                //   1681	1699	2126	java/lang/Exception
                //   1699	1717	2122	java/lang/Exception
                //   1717	1735	2118	java/lang/Exception
                //   1735	1753	2114	java/lang/Exception
                //   1753	1770	2110	java/lang/Exception
                //   1770	1862	2106	java/lang/Exception
                //   1862	2031	2102	java/lang/Exception
                //   2031	2046	2098	java/lang/Exception
                //   2049	2078	2098	java/lang/Exception
                //   2081	2095	2098	java/lang/Exception
            }
        });
    }

    public AlertDialog.Builder showAlertBox(String paramString1, String paramString2) {
        return (new AlertDialog.Builder((Context)this)).setTitle(paramString1).setMessage(paramString2);
    }
}
