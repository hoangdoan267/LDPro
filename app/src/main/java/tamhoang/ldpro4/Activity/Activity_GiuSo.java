package tamhoang.ldpro4.Activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import tamhoang.ldpro4.Congthuc.BaseToolBarActivity;
import tamhoang.ldpro4.Congthuc.Congthuc;
import tamhoang.ldpro4.data.Database;

public class Activity_GiuSo extends BaseToolBarActivity {
    Button btnThemXien;

    Button btnThemdan;

    Button btnXoaDan;

    Button btnXoaXien;

    Database db;

    EditText edtNhapDan;

    EditText giu3cang;

    EditText giuxien2;

    EditText giuxien3;

    EditText giuxien4;

    RadioButton radioDeA;

    RadioButton radioDeB;

    RadioButton radioDeC;

    RadioButton radioDeD;

    RadioButton radioLo;

    Spinner spr_KH;

    protected int getLayoutId() {
        return 2131427366;
    }

    public void init() {
        this.btnThemdan = (Button)findViewById(2131230807);
        this.btnXoaDan = (Button)findViewById(2131230809);
        this.btnThemXien = (Button)findViewById(2131230802);
        this.btnXoaXien = (Button)findViewById(2131230812);
        this.radioDeA = (RadioButton)findViewById(2131231160);
        this.radioDeB = (RadioButton)findViewById(2131231161);
        this.radioDeC = (RadioButton)findViewById(2131231162);
        this.radioDeD = (RadioButton)findViewById(2131231163);
        this.radioLo = (RadioButton)findViewById(2131231178);
        this.edtNhapDan = (EditText)findViewById(2131230928);
        this.spr_KH = (Spinner)findViewById(2131231265);
        this.giuxien2 = (EditText)findViewById(2131230982);
        this.giuxien3 = (EditText)findViewById(2131230983);
        this.giuxien4 = (EditText)findViewById(2131230984);
        this.giu3cang = (EditText)findViewById(2131230981);
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131427366);
        this.db = new Database((Context)this);
        init();
        this.btnThemdan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                byte b1 = 1;
                byte b2 = 1;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("de ");
                stringBuilder.append(Activity_GiuSo.this.edtNhapDan.getText().toString());
                String str2 = stringBuilder.toString();
                String str1 = str2;
                if (str2.length() > 7) {
                    str1 = str2;
                    try {
                        str2 = Congthuc.convertKhongDau(str2);
                        str1 = str2;
                        str2 = Congthuc.NhanTinNhan(str2);
                        str1 = str2;
                        str2 = str2.replace("de dit db:", "de:");
                        b1 = b2;
                        str1 = str2;
                        if (str2.indexOf("Khhi) > -1) {
                                str1 = str2;
                        Toast.makeText((Context)Activity_GiuSo.this, str2, 1).show();
                        b1 = 0;
                    }
                    str1 = str2;
                } catch (Exception exception) {
                    Toast.makeText((Context)Activity_GiuSo.this, "Thblhsl, 1).show();
                            b1 = 0;
                }
            }
            if (b1 == 1) {
                Toast.makeText((Context)Activity_GiuSo.this, "sdgi, 1).show();
                if (Activity_GiuSo.this.radioDeB.isChecked()) {
                    if (str1.length() > 7) {
                        Activity_GiuSo.this.db.QueryData("Update So_om Set Om_DeB =0");
                        Database database = Activity_GiuSo.this.db;
                        StringBuilder stringBuilder1 = new StringBuilder();
                        stringBuilder1.append("UPDATE So_om SET Sphu1 = '");
                        stringBuilder1.append(Activity_GiuSo.this.edtNhapDan.getText().toString());
                        stringBuilder1.append("' WHERE ID = 21");
                        database.QueryData(stringBuilder1.toString());
                        do {
                            String str5 = str1.substring(0, str1.indexOf("\n") + 1);
                            String str3 = str1.substring(str5.indexOf(":") + 1, str5.indexOf("\n") + 1);
                            String[] arrayOfString = str3.substring(0, str3.indexOf(",x")).split(",");
                            String str4 = str5.substring(str5.indexOf(",x") + 2, str5.indexOf("\n"));
                            for (b2 = 0; b2 < arrayOfString.length; b2++) {
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Update So_om Set Om_DeB = Om_DeB +");
                                stringBuilder2.append(str4);
                                stringBuilder2.append(" WHERE So = '");
                                stringBuilder2.append(arrayOfString[b2]);
                                stringBuilder2.append("'");
                                String str = stringBuilder2.toString();
                                Activity_GiuSo.this.db.QueryData(str);
                            }
                            str1 = str1.replaceAll(str5, "");
                        } while (str1.length() > 0);
                    }
                } else if (Activity_GiuSo.this.radioDeA.isChecked()) {
                    if (str1.length() > 7) {
                        String str;
                        Activity_GiuSo.this.db.QueryData("Update So_om Set Om_DeA =0");
                        Database database = Activity_GiuSo.this.db;
                        StringBuilder stringBuilder1 = new StringBuilder();
                        stringBuilder1.append("UPDATE So_om SET Sphu1 = '");
                        stringBuilder1.append(Activity_GiuSo.this.edtNhapDan.getText().toString());
                        stringBuilder1.append("' WHERE ID = 20");
                        database.QueryData(stringBuilder1.toString());
                        do {
                            String str4 = str1.substring(0, str1.indexOf("\n") + 1);
                            str = str1.substring(str4.indexOf(":") + 1, str4.indexOf("\n") + 1);
                            String[] arrayOfString = str.substring(0, str.indexOf(",x")).split(",");
                            String str3 = str4.substring(str4.indexOf(",x") + 2, str4.indexOf("\n"));
                            for (b1 = 0; b1 < arrayOfString.length; b1++) {
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Update So_om Set Om_DeA = Om_DeA +");
                                stringBuilder2.append(str3);
                                stringBuilder2.append(" WHERE So = '");
                                stringBuilder2.append(arrayOfString[b1]);
                                stringBuilder2.append("'");
                                String str5 = stringBuilder2.toString();
                                Activity_GiuSo.this.db.QueryData(str5);
                            }
                            str = str1.replaceAll(str4, "");
                            str1 = str;
                        } while (str.length() > 0);
                    }
                } else if (Activity_GiuSo.this.radioDeC.isChecked()) {
                    if (str1.length() > 7) {
                        String str;
                        Activity_GiuSo.this.db.QueryData("Update So_om Set Om_DeC =0");
                        Database database = Activity_GiuSo.this.db;
                        StringBuilder stringBuilder1 = new StringBuilder();
                        stringBuilder1.append("UPDATE So_om SET Sphu1 = '");
                        stringBuilder1.append(Activity_GiuSo.this.edtNhapDan.getText().toString());
                        stringBuilder1.append("' WHERE ID = 22");
                        database.QueryData(stringBuilder1.toString());
                        do {
                            String str3 = str1.substring(0, str1.indexOf("\n") + 1);
                            str = str1.substring(str3.indexOf(":") + 1, str3.indexOf("\n") + 1);
                            String[] arrayOfString = str.substring(0, str.indexOf(",x")).split(",");
                            String str4 = str3.substring(str3.indexOf(",x") + 2, str3.indexOf("\n"));
                            for (b1 = 0; b1 < arrayOfString.length; b1++) {
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Update So_om Set Om_DeC = Om_DeC +");
                                stringBuilder2.append(str4);
                                stringBuilder2.append(" WHERE So = '");
                                stringBuilder2.append(arrayOfString[b1]);
                                stringBuilder2.append("'");
                                String str5 = stringBuilder2.toString();
                                Activity_GiuSo.this.db.QueryData(str5);
                            }
                            str = str1.replaceAll(str3, "");
                            str1 = str;
                        } while (str.length() > 0);
                    }
                } else if (Activity_GiuSo.this.radioDeD.isChecked()) {
                    if (str1.length() > 7) {
                        String str;
                        Activity_GiuSo.this.db.QueryData("Update So_om Set Om_DeD =0");
                        Database database = Activity_GiuSo.this.db;
                        StringBuilder stringBuilder1 = new StringBuilder();
                        stringBuilder1.append("UPDATE So_om SET Sphu1 = '");
                        stringBuilder1.append(Activity_GiuSo.this.edtNhapDan.getText().toString());
                        stringBuilder1.append("' WHERE ID = 23");
                        database.QueryData(stringBuilder1.toString());
                        do {
                            String str3 = str1.substring(0, str1.indexOf("\n") + 1);
                            str = str1.substring(str3.indexOf(":") + 1, str3.indexOf("\n") + 1);
                            String[] arrayOfString = str.substring(0, str.indexOf(",x")).split(",");
                            String str4 = str3.substring(str3.indexOf(",x") + 2, str3.indexOf("\n"));
                            for (b1 = 0; b1 < arrayOfString.length; b1++) {
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Update So_om Set Om_DeD = Om_DeD +");
                                stringBuilder2.append(str4);
                                stringBuilder2.append(" WHERE So = '");
                                stringBuilder2.append(arrayOfString[b1]);
                                stringBuilder2.append("'");
                                String str5 = stringBuilder2.toString();
                                Activity_GiuSo.this.db.QueryData(str5);
                            }
                            str = str1.replaceAll(str3, "");
                            str1 = str;
                        } while (str.length() > 0);
                    }
                } else if (Activity_GiuSo.this.radioLo.isChecked() && str1.length() > 7) {
                    String str;
                    Activity_GiuSo.this.db.QueryData("Update So_om Set Om_Lo =0");
                    Database database = Activity_GiuSo.this.db;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append("UPDATE So_om SET Sphu1 = '");
                    stringBuilder1.append(Activity_GiuSo.this.edtNhapDan.getText().toString());
                    stringBuilder1.append("' WHERE ID = 24");
                    database.QueryData(stringBuilder1.toString());
                    do {
                        String str4 = str1.substring(0, str1.indexOf("\n") + 1);
                        str = str1.substring(str4.indexOf(":") + 1, str4.indexOf("\n") + 1);
                        String[] arrayOfString = str.substring(0, str.indexOf(",x")).split(",");
                        String str3 = str4.substring(str4.indexOf(",x") + 2, str4.indexOf("\n"));
                        for (b1 = 0; b1 < arrayOfString.length; b1++) {
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Update So_om Set Om_Lo = Om_Lo +");
                            stringBuilder2.append(str3);
                            stringBuilder2.append(" WHERE So = '");
                            stringBuilder2.append(arrayOfString[b1]);
                            stringBuilder2.append("'");
                            String str5 = stringBuilder2.toString();
                            Activity_GiuSo.this.db.QueryData(str5);
                        }
                        str = str1.replaceAll(str4, "");
                        str1 = str;
                    } while (str.length() > 0);
                }
            }
        }
    });
    this.btnXoaDan.setOnClickListener(new View.OnClickListener() {
        public void onClick(View param1View) {
            if (Activity_GiuSo.this.radioDeA.isChecked()) {
                Activity_GiuSo.this.db.QueryData("UPdate so_Om set Om_DeA = 0");
                Activity_GiuSo.this.db.QueryData("UPDATE So_om SET Sphu1 = null WHERE ID = 20");
            }
            if (Activity_GiuSo.this.radioDeB.isChecked()) {
                Activity_GiuSo.this.db.QueryData("UPdate so_Om set Om_DeB = 0");
                Activity_GiuSo.this.db.QueryData("UPDATE So_om SET Sphu1 = null WHERE ID = 21");
            }
            if (Activity_GiuSo.this.radioDeC.isChecked()) {
                Activity_GiuSo.this.db.QueryData("UPdate so_Om set Om_DeC = 0");
                Activity_GiuSo.this.db.QueryData("UPDATE So_om SET Sphu1 = null WHERE ID = 22");
            }
            if (Activity_GiuSo.this.radioDeD.isChecked()) {
                Activity_GiuSo.this.db.QueryData("UPdate so_Om set Om_DeD = 0");
                Activity_GiuSo.this.db.QueryData("UPDATE So_om SET Sphu1 = null WHERE ID = 23");
            }
            if (Activity_GiuSo.this.radioLo.isChecked()) {
                Activity_GiuSo.this.db.QueryData("UPdate so_Om set Om_Lo = 0");
                Activity_GiuSo.this.db.QueryData("UPDATE So_om SET Sphu1 = null WHERE ID = 24");
            }
            Activity_GiuSo.this.edtNhapDan.setText("");
            Toast.makeText((Context)Activity_GiuSo.this, "xdgi, 1).show();
        }
    });
    this.btnThemXien.setOnClickListener(new View.OnClickListener() {
        public void onClick(View param1View) {
            int i = 0;
            int j = 0;
            int k = 0;
            int m = 0;
            if (Activity_GiuSo.this.giuxien2.getText().toString().length() > 0)
                i = Integer.parseInt(Activity_GiuSo.this.giuxien2.getText().toString());
            if (Activity_GiuSo.this.giuxien3.getText().toString().length() > 0)
                j = Integer.parseInt(Activity_GiuSo.this.giuxien3.getText().toString());
            if (Activity_GiuSo.this.giuxien4.getText().toString().length() > 0)
                k = Integer.parseInt(Activity_GiuSo.this.giuxien4.getText().toString());
            if (Activity_GiuSo.this.giu3cang.getText().toString().length() > 0)
                m = Integer.parseInt(Activity_GiuSo.this.giu3cang.getText().toString());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Update So_om Set Om_Xi2 = ");
            stringBuilder.append(i);
            stringBuilder.append(", Om_Xi3 = ");
            stringBuilder.append(j);
            stringBuilder.append(", Om_Xi4 = ");
            stringBuilder.append(k);
            stringBuilder.append(", Om_bc = ");
            stringBuilder.append(m);
            stringBuilder.append(" WHERE ID = 1");
            String str = stringBuilder.toString();
            Activity_GiuSo.this.db.QueryData(str);
            Toast.makeText((Context)Activity_GiuSo.this, "lgixi, 1).show();
        }
    });
    this.btnXoaXien.setOnClickListener(new View.OnClickListener() {
        public void onClick(View param1View) {
            Activity_GiuSo.this.db.QueryData("Update So_om Set Om_Xi2 = 0, Om_Xi3 = 0, Om_Xi4 = 0, Om_bc = 0 WHERE ID = 1");
            Toast.makeText((Context)Activity_GiuSo.this, "xgixi, 1).show();
                    Activity_GiuSo.this.giuxien2.setText("");
            Activity_GiuSo.this.giuxien3.setText("");
            Activity_GiuSo.this.giuxien4.setText("");
            Activity_GiuSo.this.giu3cang.setText("");
        }
    });
    this.radioDeA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            if (param1Boolean == true) {
                Cursor cursor = Activity_GiuSo.this.db.GetData("Select Sphu1 FROM So_om WHERE ID = 20");
                if (cursor.moveToFirst()) {
                    Activity_GiuSo.this.edtNhapDan.setText(cursor.getString(0));
                    if (cursor != null && !cursor.isClosed())
                        cursor.close();
                }
            }
        }
    });
    this.radioDeB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            if (param1Boolean == true) {
                Cursor cursor = Activity_GiuSo.this.db.GetData("Select Sphu1 FROM So_om WHERE ID = 21");
                if (cursor.moveToFirst()) {
                    Activity_GiuSo.this.edtNhapDan.setText(cursor.getString(0));
                    if (cursor != null && !cursor.isClosed())
                        cursor.close();
                }
            }
        }
    });
    this.radioDeC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            if (param1Boolean == true) {
                Cursor cursor = Activity_GiuSo.this.db.GetData("Select Sphu1 FROM So_om WHERE ID = 22");
                if (cursor.moveToFirst()) {
                    Activity_GiuSo.this.edtNhapDan.setText(cursor.getString(0));
                    if (cursor != null && !cursor.isClosed())
                        cursor.close();
                }
            }
        }
    });
    this.radioDeD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            if (param1Boolean == true) {
                Cursor cursor = Activity_GiuSo.this.db.GetData("Select Sphu1 FROM So_om WHERE ID = 23");
                if (cursor.moveToFirst()) {
                    Activity_GiuSo.this.edtNhapDan.setText(cursor.getString(0));
                    if (cursor != null && !cursor.isClosed())
                        cursor.close();
                }
            }
        }
    });
    this.radioLo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            if (param1Boolean == true && param1Boolean == true) {
                Cursor cursor = Activity_GiuSo.this.db.GetData("Select Sphu1 FROM So_om WHERE ID = 24");
                if (cursor.moveToFirst()) {
                    Activity_GiuSo.this.edtNhapDan.setText(cursor.getString(0));
                    if (cursor != null && !cursor.isClosed())
                        cursor.close();
                }
            }
        }
    });
    Cursor cursor = this.db.GetData("Select Sphu1 FROM So_om WHERE ID = 21");
    if (cursor.moveToFirst()) {
        this.edtNhapDan.setText(cursor.getString(0));
        if (cursor != null && !cursor.isClosed())
            cursor.close();
    }
    cursor = this.db.GetData("Select * From so_om WHERE id = 1");
    if (cursor.moveToFirst()) {
        EditText editText1 = this.giuxien2;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(cursor.getString(7));
        stringBuilder2.append("");
        editText1.setText(stringBuilder2.toString());
        EditText editText2 = this.giuxien3;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(cursor.getString(8));
        stringBuilder1.append("");
        editText2.setText(stringBuilder1.toString());
        editText2 = this.giuxien4;
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(cursor.getString(9));
        stringBuilder1.append("");
        editText2.setText(stringBuilder1.toString());
        editText2 = this.giu3cang;
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(cursor.getString(10));
        stringBuilder1.append("");
        editText2.setText(stringBuilder1.toString());
        if (cursor != null && !cursor.isClosed())
            cursor.close();
    }
}
}
