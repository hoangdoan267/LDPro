package tamhoang.ldpro4.data;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.telephony.SmsManager;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Congthuc.Congthuc;
import tamhoang.ldpro4.MainActivity;

public class Database extends SQLiteOpenHelper {
    protected static SQLiteDatabase db;

    JSONObject caidat_gia;

    JSONObject caidat_tg;

    JSONObject json;

    JSONObject jsonDanSo;

    JSONObject json_Tralai;

    public String[][] mang;

    private Context mcontext;

    public Database(Context paramContext) {
        super(paramContext, stringBuilder.toString(), null, 1);
        this.mcontext = paramContext;
    }

    private void BaoLoiDan(int paramInt) {
        if (this.mang[paramInt][4].indexOf("Khhi) > -1) {
                String[][] arrayOfString = this.mang;
        arrayOfString[paramInt][0] = Congthuc.ToMauError(arrayOfString[paramInt][4].substring(11), this.mang[paramInt][0]);
    }
}

    private void BaoLoiTien(int paramInt) {
        try {
            this.mang[paramInt][5] = Congthuc.XulyTien(this.mang[paramInt][3]);
            if (this.mang[paramInt][5].indexOf("Khhi) > -1 && this.mang[paramInt][5].trim().length() < 13) {
            this.mang[paramInt][0] = Congthuc.ToMauError(this.mang[paramInt][5].substring(11), this.mang[paramInt][0]);
        } else if (this.mang[paramInt][5].indexOf("Khhi) > -1 && this.mang[paramInt][5].trim().length() > 12) {
        this.mang[paramInt][0] = Congthuc.ToMauError(this.mang[paramInt][3], this.mang[paramInt][0]);
    }
} catch (Exception exception) {
        String[][] arrayOfString = this.mang;
        arrayOfString[paramInt][0] = Congthuc.ToMauError(arrayOfString[paramInt][3], arrayOfString[paramInt][0]);
        }
        }

private void XulyMang(int paramInt) {
        StringBuilder stringBuilder;
        if (this.mang[paramInt][1].indexOf("lo dau") > -1) {
        if (this.mang[paramInt][2].indexOf("loa") > -1 && this.mang[paramInt][2].trim().indexOf("loa") > 0) {
        String[] arrayOfString1 = this.mang[paramInt];
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        String[][] arrayOfString2 = this.mang;
        stringBuilder.append(arrayOfString2[paramInt][2].substring(0, arrayOfString2[paramInt][2].indexOf("loa")));
        arrayOfString1[4] = stringBuilder.toString();
        } else {
        String[][] arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][2].replaceFirst("loa :", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("loa:", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("loa", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = Congthuc.XulyLoDe(arrayOfString1[paramInt][4]);
        }
        } else if (this.mang[paramInt][1].indexOf("lo") > -1) {
        if (this.mang[paramInt][2].indexOf("lo") > -1 && this.mang[paramInt][2].trim().indexOf("lo") > 0) {
        String[] arrayOfString1 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        String[][] arrayOfString2 = this.mang;
        stringBuilder1.append(arrayOfString2[paramInt][2].substring(0, arrayOfString2[paramInt][2].indexOf("lo")));
        arrayOfString1[4] = stringBuilder1.toString();
        } else {
        String[][] arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][2].replaceFirst("lo :", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("lo:", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("lo", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = Congthuc.XulyLoDe(arrayOfString1[paramInt][4]);
        }
        } else if (this.mang[paramInt][1].indexOf("de dau db") > -1) {
        if (this.mang[paramInt][2].indexOf("dea") > -1 && this.mang[paramInt][2].trim().indexOf("dea") > 0) {
        String[] arrayOfString2 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        String[][] arrayOfString1 = this.mang;
        stringBuilder1.append(arrayOfString1[paramInt][2].substring(0, arrayOfString1[paramInt][2].indexOf("de")));
        arrayOfString2[4] = stringBuilder1.toString();
        } else {
        String[][] arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][2].replaceFirst("dea :", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("dea:", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("dea", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = Congthuc.XulyLoDe(arrayOfString1[paramInt][4]);
        }
        } else if (this.mang[paramInt][1].indexOf("de dit db") > -1) {
        if (this.mang[paramInt][2].indexOf("deb") > -1 && this.mang[paramInt][2].trim().indexOf("deb") > 0) {
        String[] arrayOfString2 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        String[][] arrayOfString1 = this.mang;
        stringBuilder1.append(arrayOfString1[paramInt][2].substring(0, arrayOfString1[paramInt][2].indexOf("de")));
        arrayOfString2[4] = stringBuilder1.toString();
        } else {
        String[][] arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][2].replaceFirst("deb :", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("deb:", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("deb", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("de :", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("de:", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("de ", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = Congthuc.XulyLoDe(arrayOfString1[paramInt][4]);
        }
        } else if (this.mang[paramInt][1].indexOf("de dau nhat") > -1) {
        if (this.mang[paramInt][2].indexOf("dec") > -1 && this.mang[paramInt][2].trim().indexOf("dec") > 0) {
        String[] arrayOfString2 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        String[][] arrayOfString1 = this.mang;
        stringBuilder1.append(arrayOfString1[paramInt][2].substring(0, arrayOfString1[paramInt][2].indexOf("de")));
        arrayOfString2[4] = stringBuilder1.toString();
        } else {
        String[][] arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][2].replaceFirst("dec :", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("dec:", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("dec", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = Congthuc.XulyLoDe(arrayOfString1[paramInt][4]);
        }
        } else if (this.mang[paramInt][1].indexOf("de dit nhat") > -1) {
        if (this.mang[paramInt][2].indexOf("ded") > -1 && this.mang[paramInt][2].trim().indexOf("ded") > 0) {
        String[] arrayOfString2 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        String[][] arrayOfString1 = this.mang;
        stringBuilder1.append(arrayOfString1[paramInt][2].substring(0, arrayOfString1[paramInt][2].indexOf("de")));
        arrayOfString2[4] = stringBuilder1.toString();
        } else {
        String[][] arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][2].replaceFirst("ded :", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("ded:", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("ded", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = Congthuc.XulyLoDe(arrayOfString1[paramInt][4]);
        }
        } else if (this.mang[paramInt][1].indexOf("de 8") > -1) {
        if (this.mang[paramInt][2].indexOf("det") > -1 && this.mang[paramInt][2].trim().indexOf("det") > 0) {
        String[] arrayOfString1 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        String[][] arrayOfString2 = this.mang;
        stringBuilder1.append(arrayOfString2[paramInt][2].substring(0, arrayOfString2[paramInt][2].indexOf("de")));
        arrayOfString1[4] = stringBuilder1.toString();
        } else {
        String[][] arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][2].replaceFirst("det :", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("det:", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("det", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = Congthuc.XulyLoDe(arrayOfString1[paramInt][4]);
        }
        } else if (this.mang[paramInt][1].indexOf("hai cua") > -1) {
        if (this.mang[paramInt][2].indexOf("hc") > -1 && this.mang[paramInt][2].trim().indexOf("hc") > 0) {
        String[] arrayOfString1 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        String[][] arrayOfString2 = this.mang;
        stringBuilder1.append(arrayOfString2[paramInt][2].substring(0, arrayOfString2[paramInt][2].indexOf("hc")));
        arrayOfString1[4] = stringBuilder1.toString();
        } else {
        String[][] arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][2].replaceFirst("hc :", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("hc:", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("hc", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = Congthuc.XulyLoDe(arrayOfString1[paramInt][4]);
        }
        } else if (this.mang[paramInt][1].indexOf("xn") > -1) {
        if (this.mang[paramInt][2].indexOf("xn") > -1 && this.mang[paramInt][2].trim().indexOf("xn") > 0) {
        String[] arrayOfString2 = this.mang[paramInt];
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        String[][] arrayOfString1 = this.mang;
        stringBuilder.append(arrayOfString1[paramInt][2].substring(0, arrayOfString1[paramInt][2].indexOf("xn")));
        arrayOfString2[4] = stringBuilder.toString();
        } else {
        byte b3;
        String[][] arrayOfString3 = this.mang;
        arrayOfString3[paramInt][4] = arrayOfString3[paramInt][2].replaceFirst("xn :", "");
        arrayOfString3 = this.mang;
        arrayOfString3[paramInt][4] = arrayOfString3[paramInt][4].replaceFirst("xn:", "");
        arrayOfString3 = this.mang;
        arrayOfString3[paramInt][4] = arrayOfString3[paramInt][4].replaceFirst("xn", "");
        if (this.mang[paramInt][2].indexOf("xn 2 ") > -1) {
        arrayOfString3 = this.mang;
        arrayOfString3[paramInt][4] = arrayOfString3[paramInt][4].trim();
        arrayOfString3 = this.mang;
        arrayOfString3[paramInt][4] = arrayOfString3[paramInt][4].substring(2);
        arrayOfString3 = this.mang;
        arrayOfString3[paramInt][4] = Congthuc.XulySo(arrayOfString3[paramInt][4]);
        ArrayList arrayList = Congthuc.XulyXienGhep(this.mang[paramInt][4], 2);
        String str = "";
        for (String str1 : arrayList) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str);
        stringBuilder1.append(str1);
        stringBuilder1.append(" ");
        str = stringBuilder1.toString();
        }
        this.mang[paramInt][4] = str;
        } else {
        arrayOfString3 = this.mang;
        arrayOfString3[paramInt][4] = Congthuc.XulySo(arrayOfString3[paramInt][4].replaceAll("xn", " "));
        String[] arrayOfString4 = this.mang[paramInt];
        stringBuilder = new StringBuilder();
        stringBuilder.append("2 ");
        stringBuilder.append(this.mang[paramInt][4].trim());
        arrayOfString4[4] = Congthuc.XulyXien(stringBuilder.toString());
        }
        String[] arrayOfString2 = this.mang[paramInt][4].split(" ");
        byte b1 = 0;
        byte b2 = 0;
        while (true) {
        b3 = b1;
        if (b2 < arrayOfString2.length) {
        if (arrayOfString2[b2].replaceAll(",", "").length() != 2 || !Congthuc.isNumeric(arrayOfString2[b2].replaceAll(",", ""))) {
        b3 = 1;
        break;
        }
        b2++;
        continue;
        }
        break;
        }
        if (!b3 && arrayOfString2.length < 5) {
        String[][] arrayOfString4 = this.mang;
        arrayOfString4[paramInt][4] = Congthuc.XulySo(arrayOfString4[paramInt][4]);
        }
        String[] arrayOfString1 = this.mang[paramInt][4].split(" ");
        for (b2 = 0; b2 < arrayOfString1.length; b2++) {
        StringBuilder stringBuilder1;
        String str = Congthuc.XulySo(arrayOfString1[b2]);
        if (str.length() < 5 || str.length() > 6 || str.indexOf("Khhi) > -1) {
        if (arrayOfString1[b2].length() > 4) {
        String[] arrayOfString6 = this.mang[paramInt];
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append(this.mang[paramInt][2]);
        arrayOfString6[4] = stringBuilder.toString();
        break;
        }
        String[] arrayOfString5 = this.mang[paramInt];
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(this.mang[paramInt][0]);
        arrayOfString5[4] = stringBuilder1.toString();
        break;
        }
        String[] arrayOfString4 = stringBuilder1.split(",");
        for (b3 = 0; b3 < arrayOfString4.length; b3++) {
        if (arrayOfString4[b3].length() != 2 || !Congthuc.isNumeric(arrayOfString4[b3]))
        if (this.mang[paramInt][4].length() > 4) {
        String[] arrayOfString5 = this.mang[paramInt];
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append(this.mang[paramInt][2]);
        arrayOfString5[4] = stringBuilder2.toString();
        } else {
        String[] arrayOfString5 = this.mang[paramInt];
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append(this.mang[paramInt][0]);
        arrayOfString5[4] = stringBuilder2.toString();
        }
        }
        }
        if (this.mang[paramInt][4].indexOf("Khhi) == -1) {
        this.mang[paramInt][4] = "";
        b2 = 0;
        String str = "";
        while (b2 < arrayOfString1.length) {
        try {
        String str1 = Congthuc.XulySo(arrayOfString1[b2]);
        str = str1;
        } catch (Exception exception) {
        String[] arrayOfString4 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(arrayOfString1[b2]);
        arrayOfString4[4] = stringBuilder1.toString();
        }
        if (str.indexOf("Khhi) == -1) {
        String[] arrayOfString4 = str.split(",");
        b1 = 0;
        for (b3 = 0; b3 < arrayOfString4.length; b3++) {
        if (str.length() - str.replaceAll(arrayOfString4[b3], "").length() > 2)
        b1 = 1;
        }
        if (str.length() < 5 || str.length() > 6 || b1 == 1) {
        arrayOfString4 = this.mang[paramInt];
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append(this.mang[paramInt][2]);
        arrayOfString4[4] = stringBuilder.toString();
        break;
        }
        arrayOfString4 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(this.mang[paramInt][4]);
        stringBuilder1.append(Congthuc.sortXien((String)stringBuilder));
        stringBuilder1.append(" ");
        arrayOfString4[4] = stringBuilder1.toString();
        b2++;
        }
        }
        }
        }
        } else if (this.mang[paramInt][1].indexOf("bc dau") > -1) {
        if (this.mang[paramInt][2].indexOf("bca") > -1 && this.mang[paramInt][2].trim().indexOf("bca") > 0) {
        String[] arrayOfString2 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        String[][] arrayOfString1 = this.mang;
        stringBuilder1.append(arrayOfString1[paramInt][2].substring(0, arrayOfString1[paramInt][2].indexOf("bca")));
        arrayOfString2[4] = stringBuilder1.toString();
        } else {
        String[][] arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][2].replaceFirst("bca :", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("bca:", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("bca", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = Congthuc.Xu3cang(arrayOfString1[paramInt][4]);
        }
        } else if (this.mang[paramInt][1].indexOf("bc") > -1) {
        if (this.mang[paramInt][2].indexOf("bc") > -1 && this.mang[paramInt][2].trim().indexOf("bc") > 0) {
        String[] arrayOfString2 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        String[][] arrayOfString1 = this.mang;
        stringBuilder1.append(arrayOfString1[paramInt][2].substring(0, arrayOfString1[paramInt][2].indexOf("bc")));
        arrayOfString2[4] = stringBuilder1.toString();
        } else {
        String[][] arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][2].replaceFirst("bc :", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("bc:", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = arrayOfString1[paramInt][4].replaceFirst("bc", "");
        arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = Congthuc.Xu3cang(arrayOfString1[paramInt][4]);
        }
        } else if (this.mang[paramInt][1].indexOf("xi") > -1) {
        if (this.mang[paramInt][2].indexOf("xi") > -1 && this.mang[paramInt][2].trim().indexOf("xi") > 0) {
        String[] arrayOfString2 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        String[][] arrayOfString1 = this.mang;
        stringBuilder1.append(arrayOfString1[paramInt][2].substring(0, arrayOfString1[paramInt][2].indexOf("xi")));
        arrayOfString2[4] = stringBuilder1.toString();
        } else {
        byte b3;
        if (this.mang[paramInt][2].indexOf("xia") > -1) {
        arrayOfString2 = this.mang[paramInt][2].split("xia");
        } else {
        arrayOfString2 = this.mang[paramInt][2].split("xi");
        }
        String str = "";
        if (arrayOfString2.length > 2) {
        byte b = 1;
        while (b < arrayOfString2.length) {
        String str1 = str;
        if (arrayOfString2[b].length() > 4) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str);
        stringBuilder1.append(Congthuc.XulySo(arrayOfString2[b]));
        stringBuilder1.append(" ");
        str = stringBuilder1.toString();
        String str2 = str;
        if (Congthuc.XulySo(arrayOfString2[b]).indexOf("Khhi) > -1) {
        String[] arrayOfString3 = this.mang[paramInt];
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append(arrayOfString2[b]);
        arrayOfString3[4] = stringBuilder2.toString();
        str1 = str;
        }
        }
        b++;
        str = str1;
        }
        this.mang[paramInt][4] = str;
        } else {
        if (this.mang[paramInt][2].indexOf("xia") > -1) {
        String[][] arrayOfString3 = this.mang;
        arrayOfString3[paramInt][4] = arrayOfString3[paramInt][2].replaceFirst("xia", "");
        } else {
        String[][] arrayOfString3 = this.mang;
        arrayOfString3[paramInt][4] = arrayOfString3[paramInt][2].replaceFirst("xi", "");
        }
        str = "";
        }
        if (arrayOfString2.length < 3) {
        String[][] arrayOfString3 = this.mang;
        arrayOfString3[paramInt][4] = Congthuc.XulyXien(arrayOfString3[paramInt][4].trim());
        }
        String[] arrayOfString2 = this.mang[paramInt][4].split(" ");
        byte b1 = 0;
        byte b2 = 0;
        while (true) {
        b3 = b1;
        if (b2 < arrayOfString2.length) {
        if (arrayOfString2[b2].replaceAll(",", "").length() != 2 || !Congthuc.isNumeric(arrayOfString2[b2].replaceAll(",", ""))) {
        b3 = 1;
        break;
        }
        b2++;
        continue;
        }
        break;
        }
        if (!b3 && arrayOfString2.length < 5) {
        String[][] arrayOfString3 = this.mang;
        arrayOfString3[paramInt][4] = Congthuc.XulySo(arrayOfString3[paramInt][4]);
        }
        String[] arrayOfString1 = this.mang[paramInt][4].split(" ");
        for (b2 = 0; b2 < arrayOfString1.length; b2++) {
        StringBuilder stringBuilder1;
        String str1 = Congthuc.XulySo(arrayOfString1[b2]);
        if (str1.length() < 5 || str1.length() > 12 || str1.indexOf("Khhi) > -1) {
        if (arrayOfString1[b2].length() > 4) {
        String[] arrayOfString4 = this.mang[paramInt];
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append(this.mang[paramInt][2]);
        arrayOfString4[4] = stringBuilder.toString();
        break;
        }
        String[] arrayOfString3 = this.mang[paramInt];
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(this.mang[paramInt][0]);
        arrayOfString3[4] = stringBuilder1.toString();
        break;
        }
        if (this.mang[paramInt][1] == "xq" && stringBuilder1.length() < 8) {
        String[] arrayOfString3 = this.mang[paramInt];
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(this.mang[paramInt][0]);
        arrayOfString3[4] = stringBuilder1.toString();
        } else {
        String[] arrayOfString3 = stringBuilder1.split(",");
        for (b3 = 0; b3 < arrayOfString3.length; b3++) {
        if (arrayOfString3[b3].length() != 2 || !Congthuc.isNumeric(arrayOfString3[b3]))
        if (this.mang[paramInt][4].length() > 4) {
        String[] arrayOfString4 = this.mang[paramInt];
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append(this.mang[paramInt][2]);
        arrayOfString4[4] = stringBuilder2.toString();
        } else {
        String[] arrayOfString4 = this.mang[paramInt];
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append(this.mang[paramInt][0]);
        arrayOfString4[4] = stringBuilder2.toString();
        }
        }
        }
        }
        if (this.mang[paramInt][4].indexOf("Khhi) == -1) {
        this.mang[paramInt][4] = "";
        b2 = 0;
        str = "";
        while (b2 < arrayOfString1.length) {
        try {
        String str1 = Congthuc.XulySo(arrayOfString1[b2]);
        str = str1;
        } catch (Exception exception) {
        String[] arrayOfString3 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(arrayOfString1[b2]);
        arrayOfString3[4] = stringBuilder1.toString();
        }
        if (str.indexOf("Khhi) == -1) {
        String[] arrayOfString3;
        arrayOfString2 = str.split(",");
        b1 = 0;
        for (b3 = 0; b3 < arrayOfString2.length; b3++) {
        if (str.length() - str.replaceAll(arrayOfString2[b3], "").length() > 2)
        b1 = 1;
        }
        if (str.length() < 5 || str.length() > 12 || b1 == 1) {
        arrayOfString3 = this.mang[paramInt];
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append(this.mang[paramInt][2]);
        arrayOfString3[4] = stringBuilder2.toString();
        break;
        }
        String[] arrayOfString4 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(this.mang[paramInt][4]);
        stringBuilder1.append(Congthuc.sortXien((String)arrayOfString3));
        stringBuilder1.append(" ");
        arrayOfString4[4] = stringBuilder1.toString();
        b2++;
        }
        }
        }
        }
        } else if (this.mang[paramInt][1].indexOf("xq") > -1) {
        if (this.mang[paramInt][2].indexOf("xq") > -1 && this.mang[paramInt][2].trim().indexOf("xq") > 0) {
        String[] arrayOfString1 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        String[][] arrayOfString2 = this.mang;
        stringBuilder1.append(arrayOfString2[paramInt][2].substring(0, arrayOfString2[paramInt][2].indexOf("xq")));
        arrayOfString1[4] = stringBuilder1.toString();
        } else {
        String[] arrayOfString3;
        byte b3;
        if (this.mang[paramInt][2].indexOf("xqa") > -1) {
        arrayOfString3 = this.mang[paramInt][2].split("xqa");
        this.mang[paramInt][1] = "xq dau";
        } else {
        arrayOfString3 = this.mang[paramInt][2].split("xq");
        }
        String str = "";
        if (arrayOfString3.length > 2) {
        byte b = 1;
        while (b < arrayOfString3.length) {
        String str1 = str;
        if (arrayOfString3[b].length() > 4) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str);
        stringBuilder1.append(Congthuc.XulySo(arrayOfString3[b]));
        stringBuilder1.append(" ");
        str = stringBuilder1.toString();
        String str2 = str;
        if (Congthuc.XulySo(arrayOfString3[b]).indexOf("Khhi) > -1) {
        String[] arrayOfString5 = this.mang[paramInt];
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append(arrayOfString3[b]);
        arrayOfString5[4] = stringBuilder2.toString();
        str1 = str;
        }
        }
        b++;
        str = str1;
        }
        this.mang[paramInt][4] = str;
        } else {
        if (this.mang[paramInt][2].indexOf("xqa") > -1) {
        String[][] arrayOfString5 = this.mang;
        arrayOfString5[paramInt][4] = arrayOfString5[paramInt][2].replaceFirst("xqa", "");
        } else {
        String[][] arrayOfString5 = this.mang;
        arrayOfString5[paramInt][4] = arrayOfString5[paramInt][2].replaceFirst("xq", "");
        }
        str = "";
        }
        String[][] arrayOfString2 = this.mang;
        arrayOfString2[paramInt][4] = Congthuc.XulyXien(arrayOfString2[paramInt][4].trim());
        String[] arrayOfString1 = this.mang[paramInt][4].split(" ");
        byte b1 = 0;
        byte b2 = 0;
        while (true) {
        b3 = b1;
        if (b2 < arrayOfString1.length) {
        if (arrayOfString1[b2].replaceAll(",", "").length() != 2 || !Congthuc.isNumeric(arrayOfString1[b2].replaceAll(",", ""))) {
        b3 = 1;
        break;
        }
        b2++;
        continue;
        }
        break;
        }
        if (!b3 && arrayOfString1.length < 8) {
        String[][] arrayOfString5 = this.mang;
        arrayOfString5[paramInt][4] = Congthuc.XulySo(arrayOfString5[paramInt][4]);
        }
        String[] arrayOfString4 = this.mang[paramInt][4].split(" ");
        b2 = 0;
        while (b2 < arrayOfString4.length) {
        StringBuilder stringBuilder2;
        String[] arrayOfString7;
        StringBuilder stringBuilder1;
        StringBuilder stringBuilder4;
        String[] arrayOfString8;
        String str1 = Congthuc.XulySo(arrayOfString4[b2]);
        if (str1.length() < 8 || str1.length() > 12 || str1.indexOf("Khhi) > -1) {
        if (arrayOfString4[b2].length() > 8) {
        arrayOfString7 = this.mang[paramInt];
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Khhi");
        stringBuilder4.append(this.mang[paramInt][2]);
        arrayOfString7[4] = stringBuilder4.toString();
        break;
        }
        arrayOfString3 = this.mang[paramInt];
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Khhi");
        stringBuilder2.append(this.mang[paramInt][0]);
        arrayOfString3[4] = stringBuilder2.toString();
        break;
        }
        if (this.mang[paramInt][1] == "xq" && str1.length() < 8) {
        String[] arrayOfString9 = this.mang[paramInt];
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append("Khhi");
        stringBuilder5.append(this.mang[paramInt][0]);
        arrayOfString9[4] = stringBuilder5.toString();
        arrayOfString5 = arrayOfString3;
        stringBuilder4 = stringBuilder2;
        arrayOfString7 = arrayOfString5;
        } else {
        arrayOfString5 = arrayOfString5.split(",");
        for (b3 = 0; b3 < arrayOfString5.length; b3++) {
        if (arrayOfString5[b3].length() != 2 || !Congthuc.isNumeric(arrayOfString5[b3]))
        if (this.mang[paramInt][4].length() > 4) {
        String[] arrayOfString9 = this.mang[paramInt];
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append("Khhi");
        stringBuilder5.append(this.mang[paramInt][2]);
        arrayOfString9[4] = stringBuilder5.toString();
        } else {
        String[] arrayOfString9 = this.mang[paramInt];
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append("Khhi");
        stringBuilder5.append(this.mang[paramInt][0]);
        arrayOfString9[4] = stringBuilder5.toString();
        }
        }
        arrayOfString5 = arrayOfString7;
        stringBuilder1 = stringBuilder4;
        arrayOfString8 = arrayOfString5;
        }
        b2++;
        String[] arrayOfString5 = arrayOfString8;
        StringBuilder stringBuilder3 = stringBuilder1;
        String[] arrayOfString6 = arrayOfString5;
        }
        if (this.mang[paramInt][4].indexOf("Khhi) == -1) {
        this.mang[paramInt][4] = "";
        b2 = 0;
        str = "";
        while (b2 < arrayOfString4.length) {
        try {
        String str1 = Congthuc.XulySo(arrayOfString4[b2]);
        str = str1;
        } catch (Exception exception) {
        String[] arrayOfString5 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        stringBuilder1.append(arrayOfString4[b2]);
        arrayOfString5[4] = stringBuilder1.toString();
        }
        if (str.indexOf("Khhi) == -1) {
        arrayOfString3 = str.split(",");
        b1 = 0;
        for (b3 = 0; b3 < arrayOfString3.length; b3++) {
        if (str.length() - str.replaceAll(arrayOfString3[b3], "").length() > 2)
        b1 = 1;
        }
        if (str.length() < 5 || str.length() > 12 || b1 == 1) {
        arrayOfString3 = this.mang[paramInt];
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append(this.mang[paramInt][2]);
        arrayOfString3[4] = stringBuilder.toString();
        break;
        }
        arrayOfString3 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(this.mang[paramInt][4]);
        stringBuilder1.append(Congthuc.sortXien((String)stringBuilder));
        stringBuilder1.append(" ");
        arrayOfString3[4] = stringBuilder1.toString();
        b2++;
        }
        }
        }
        }
        } else if (this.mang[paramInt][1].indexOf("xg") > -1) {
        if (this.mang[paramInt][2].indexOf("xg") > -1 && this.mang[paramInt][2].trim().indexOf("xg") > 0) {
        String[] arrayOfString1 = this.mang[paramInt];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Khhi");
        String[][] arrayOfString2 = this.mang;
        stringBuilder1.append(arrayOfString2[paramInt][2].substring(0, arrayOfString2[paramInt][2].indexOf("xg")));
        arrayOfString1[4] = stringBuilder1.toString();
        } else {
        if (this.mang[paramInt][1].indexOf("xg 2") > -1) {
        String[][] arrayOfString2 = this.mang;
        arrayOfString2[paramInt][4] = arrayOfString2[paramInt][2].replaceFirst("xg 2 ", "");
        } else if (this.mang[paramInt][1].indexOf("xg 3") > -1) {
        String[][] arrayOfString2 = this.mang;
        arrayOfString2[paramInt][4] = arrayOfString2[paramInt][2].replaceFirst("xg 3 ", "");
        } else if (this.mang[paramInt][1].indexOf("xg 4") > -1) {
        String[][] arrayOfString2 = this.mang;
        arrayOfString2[paramInt][4] = arrayOfString2[paramInt][2].replaceFirst("xg 4 ", "");
        }
        ArrayList arrayList = null;
        String str = "";
        String[][] arrayOfString1 = this.mang;
        arrayOfString1[paramInt][4] = Congthuc.XulySo(arrayOfString1[paramInt][4]);
        if (this.mang[paramInt][4].indexOf("Khhi) == -1) {
        if (this.mang[paramInt][1].indexOf("xg 2") > -1) {
        arrayList = Congthuc.XulyXienGhep(this.mang[paramInt][4], 2);
        } else if (this.mang[paramInt][1].indexOf("xg 3") > -1) {
        arrayList = Congthuc.XulyXienGhep(this.mang[paramInt][4], 3);
        } else if (this.mang[paramInt][1].indexOf("xg 4") > -1) {
        arrayList = Congthuc.XulyXienGhep(this.mang[paramInt][4], 4);
        }
        Iterator<String> iterator = arrayList.iterator();
        String str1;
        for (str1 = str; iterator.hasNext(); str1 = stringBuilder1.toString()) {
        String str2 = iterator.next();
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str1);
        stringBuilder1.append(str2);
        stringBuilder1.append(" ");
        }
        this.mang[paramInt][4] = str1;
        }
        }
        }
        String[][] arrayOfString = this.mang;
        if (arrayOfString[paramInt][4] == null) {
        String[] arrayOfString1 = arrayOfString[paramInt];
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append(this.mang[paramInt][0].substring(0, 5));
        arrayOfString1[4] = stringBuilder.toString();
        } else if (stringBuilder[paramInt][4].trim().length() == 10 && this.mang[paramInt][4].indexOf("Khhi) > -1) {
        String[] arrayOfString1 = this.mang[paramInt];
        stringBuilder = new StringBuilder();
        stringBuilder.append("Khhi");
        stringBuilder.append(this.mang[paramInt][0]);
        arrayOfString1[4] = stringBuilder.toString();
        }
        }

private void createNotification(String paramString, Context paramContext) {
        Intent intent = new Intent(paramContext, MainActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(paramContext);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(intent);
        (new Intent(paramContext, MainActivity.class)).addFlags(268435456);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(123, 134217728);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(paramContext);
        builder.setContentTitle("Ld.pro");
        builder.setContentText(paramString);
        builder.setSmallIcon(2131165308);
        builder.setContentIntent(pendingIntent);
        builder.setDefaults(1);
        builder.setVibrate(new long[] { 100L, 2000L, 500L, 2000L });
        builder.setLights(-16711936, 400, 400);
        NotificationManager notificationManager = (NotificationManager)paramContext.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
        NotificationChannel notificationChannel = new NotificationChannel("10001", "NOTIFICATION_CHANNEL_NAME", 3);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(-65536);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[] { 100L, 2000L, 500L, 2000L });
        builder.setChannelId("10001");
        notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(0, builder.build());
        }

private String xuly_Xq(String paramString) {
        String str1;
        StringBuilder stringBuilder;
        String str2 = "";
        String[] arrayOfString = paramString.split(",");
        if (arrayOfString.length == 3) {
        byte b = 0;
        paramString = str2;
        while (b < arrayOfString.length - 1) {
        for (int i = b + 1; i < arrayOfString.length; i++) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString);
        stringBuilder1.append(arrayOfString[b]);
        stringBuilder1.append(",");
        stringBuilder1.append(arrayOfString[i]);
        stringBuilder1.append(" ");
        paramString = stringBuilder1.toString();
        }
        b++;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(arrayOfString[0]);
        stringBuilder.append(",");
        stringBuilder.append(arrayOfString[1]);
        stringBuilder.append(",");
        stringBuilder.append(arrayOfString[2]);
        paramString = stringBuilder.toString();
        } else {
        StringBuilder stringBuilder1 = stringBuilder;
        if (arrayOfString.length == 4) {
        String str;
        byte b;
        for (b = 0; b < arrayOfString.length - 1; b++) {
        for (int i = b + 1; i < arrayOfString.length; i++) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append((String)stringBuilder);
        stringBuilder1.append(arrayOfString[b]);
        stringBuilder1.append(",");
        stringBuilder1.append(arrayOfString[i]);
        stringBuilder1.append(" ");
        str = stringBuilder1.toString();
        }
        }
        b = 0;
        while (b < arrayOfString.length - 2) {
        int i = b + 1;
        String str3 = str;
        while (i < arrayOfString.length - 1) {
        for (int j = i + 1; j < arrayOfString.length; j++) {
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str3);
        stringBuilder2.append(arrayOfString[b]);
        stringBuilder2.append(",");
        stringBuilder2.append(arrayOfString[i]);
        stringBuilder2.append(",");
        stringBuilder2.append(arrayOfString[j]);
        stringBuilder2.append(" ");
        str3 = stringBuilder2.toString();
        }
        i++;
        }
        b++;
        str = str3;
        }
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str);
        stringBuilder1.append(arrayOfString[0]);
        stringBuilder1.append(",");
        stringBuilder1.append(arrayOfString[1]);
        stringBuilder1.append(",");
        stringBuilder1.append(arrayOfString[2]);
        stringBuilder1.append(",");
        stringBuilder1.append(arrayOfString[3]);
        str1 = stringBuilder1.toString();
        }
        }
        return str1;
        }

public void Another_setting() {
        QueryData("CREATE TABLE IF NOT EXISTS tbl_Setting(\n ID INTEGER PRIMARY KEY AUTOINCREMENT,\n Setting TEXT)");
        Cursor cursor = GetData("SELECT * FROM 'tbl_Setting'");
        if (cursor.getCount() == 0) {
        JSONObject jSONObject = new JSONObject();
        try {
        jSONObject.put("ap_man", 0);
        jSONObject.put("chuyen_xien", 0);
        jSONObject.put("lam_tron", 0);
        jSONObject.put("gioi_han_tin", 1);
        jSONObject.put("tin_qua_gio", 0);
        jSONObject.put("tin_trung", 0);
        jSONObject.put("kieu_bao_cao", 0);
        jSONObject.put("bao_cao_so", 0);
        jSONObject.put("tra_thuong_lo", 0);
        jSONObject.put("canhbaodonvi", 0);
        jSONObject.put("tudongxuly", 0);
        jSONObject.put("tachxien_tinchot", 0);
        jSONObject.put("baotinthieu", 0);
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("insert into tbl_Setting Values( null,'");
        stringBuilder.append(jSONObject.toString());
        stringBuilder.append("')");
        QueryData(stringBuilder.toString());
        } catch (JSONException jSONException) {
        jSONException.printStackTrace();
        }
        cursor.close();
        } else {
        try {
        cursor.moveToFirst();
        JSONObject jSONObject = new JSONObject();
        this(cursor.getString(1));
        if (!jSONObject.has("canhbaodonvi"))
        jSONObject.put("canhbaodonvi", 0);
        if (!jSONObject.has("tachxien_tinchot"))
        jSONObject.put("tachxien_tinchot", 0);
        if (!jSONObject.has("baotinthieu"))
        jSONObject.put("baotinthieu", 0);
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Update tbl_Setting set Setting = '");
        stringBuilder.append(jSONObject.toString());
        stringBuilder.append("'");
        QueryData(stringBuilder.toString());
        } catch (JSONException jSONException) {
        jSONException.printStackTrace();
        }
        }
        }

public void Bang_KQ() {
        QueryData("CREATE TABLE IF NOT EXISTS KetQua(  ID INTEGER PRIMARY KEY AUTOINCREMENT,  Ngay DATE DEFAULT NULL,  GDB VARCHAR(5) DEFAULT NULL,  G11 VARCHAR(5) DEFAULT NULL,  G21 VARCHAR(5) DEFAULT NULL,  G22 VARCHAR(5) DEFAULT NULL,  G31 VARCHAR(5) DEFAULT NULL,  G32 VARCHAR(5) DEFAULT NULL,  G33 VARCHAR(5) DEFAULT NULL,  G34 VARCHAR(5) DEFAULT NULL,  G35 VARCHAR(5) DEFAULT NULL,  G36 VARCHAR(5) DEFAULT NULL,  G41 VARCHAR(4) DEFAULT NULL,  G42 VARCHAR(4) DEFAULT NULL,  G43 VARCHAR(4) DEFAULT NULL,  G44 VARCHAR(4) DEFAULT NULL,  G51 VARCHAR(4) DEFAULT NULL,  G52 VARCHAR(4) DEFAULT NULL,  G53 VARCHAR(4) DEFAULT NULL,  G54 VARCHAR(4) DEFAULT NULL,  G55 VARCHAR(4) DEFAULT NULL,  G56 VARCHAR(4) DEFAULT NULL,  G61 VARCHAR(3) DEFAULT NULL,  G62 VARCHAR(3) DEFAULT NULL,  G63 VARCHAR(3) DEFAULT NULL,  G71 VARCHAR(2) DEFAULT NULL,  G72 VARCHAR(2) DEFAULT NULL,  G73 VARCHAR(2) DEFAULT NULL,  G74 VARCHAR(2) DEFAULT NULL);");
        }

public void Creat_Chaytrang_acc() {
        QueryData("CREATE TABLE IF NOT EXISTS tbl_chaytrang_acc( \n Username VARCHAR(30) PRIMARY KEY,\n Password VARCHAR(20) NOT NULL,\n Setting TEXT NOT NULL,\n Status VARCHAR(20) DEFAULT NULL)");
        }

public void Creat_Chaytrang_ticket() {
        QueryData("CREATE TABLE IF NOT EXISTS tbl_chaytrang_ticket( ID INTEGER PRIMARY KEY AUTOINCREMENT, \nngay_nhan DATE NOT NULL, \nCreatedAt VARCHAR(20) DEFAULT NULL, \nUsername VARCHAR(30), \nTicketNumber INTEGER DEFAULT 0, \nGameType INTEGER DEFAULT 0,\nNumbers Text DEFAULT NULL, \nPoint DOUBLE DEFAULT 0, \nAmount DOUBLE DEFAULT 0, \nCancelledAt INTEGER DEFAULT 1)");
        }

public void Creat_SoCT() {
        QueryData("CREATE TABLE IF NOT EXISTS tbl_soctS(\n ID INTEGER PRIMARY KEY AUTOINCREMENT,\n ngay_nhan DATE NOT NULL,\n type_kh INTEGER DEFAULT 1,\n ten_kh VARCHAR(20) NOT NULL,\n so_dienthoai VARCHAR(20) NOT NULL,\n so_tin_nhan INTEGER DEFAULT 0,\n the_loai VARCHAR(4) DEFAULT NULL,\n so_chon VARCHAR(20) DEFAULT NULL,\n diem DOUBLE DEFAULT 0,\n diem_quydoi DOUBLE DEFAULT 0,\n diem_khachgiu DOUBLE DEFAULT 0,\n diem_dly_giu DOUBLE DEFAULT 0,\n diem_ton DOUBLE DEFAULT 0,\n gia DOUBLE DEFAULT 0,\n lan_an DOUBLE DEFAULT 0,\n so_nhay DOUBLE DEFAULT 0,\n tong_tien DOUBLE DEFAULT 0,\n ket_qua DOUBLE DEFAULT 0)");
        QueryData("CREATE TABLE IF NOT EXISTS tbl_chuyenthang ( ID INTEGER PRIMARY KEY AUTOINCREMENT, kh_nhan VARCHAR(20) NOT NULL, sdt_nhan VARCHAR(15) NOT NULL, kh_chuyen VARCHAR(20) NOT NULL, sdt_chuyen VARCHAR(15) NOT NULL)");
        }

public void Creat_So_Om() {
        QueryData("CREATE TABLE IF NOT EXISTS So_om(  ID INTEGER PRIMARY KEY AUTOINCREMENT,  So VARCHAR(2) DEFAULT NULL,  Om_DeA INTEGER DEFAULT 0,  Om_DeB INTEGER DEFAULT 0,  Om_DeC INTEGER DEFAULT 0,  Om_DeD INTEGER DEFAULT 0,  Om_Lo INTEGER Default 0,  Om_Xi2 INTEGER Default 0,  Om_Xi3 INTEGER Default 0,  Om_Xi4 INTEGER Default 0,  Om_bc INTEGER Default 0,  Sphu1 VARCHAR(200) DEFAULT NULL,  Sphu2 VARCHAR(200) DEFAULT NULL)");
        }

public void Creat_TinNhanGoc() {
        QueryData("CREATE TABLE IF NOT EXISTS tbl_tinnhanS(\n ID INTEGER PRIMARY KEY AUTOINCREMENT,\n ngay_nhan DATE NOT NULL,\n gio_nhan VARCHAR(8) NOT NULL,\n type_kh INTEGER DEFAULT 0,\n ten_kh VARCHAR(20) NOT NULL,\n so_dienthoai VARCHAR(20) NOT NULL,\n use_app VARCHAR(20) NOT NULL,\n so_tin_nhan INTEGER DEFAULT 0,\n nd_goc VARCHAR(500) DEFAULT NULL,\n nd_sua VARCHAR(500) DEFAULT NULL,\n nd_phantich VARCHAR(500) DEFAULT NULL,\n phat_hien_loi VARCHAR(100) DEFAULT NULL,\n tinh_tien INTEGER DEFAULT 0,\n ok_tn INTEGER DEFAULT 0,\n del_sms INTEGER DEFAULT 0,  phan_tich TEXT);");
        }

public void Create_table_Chat() {
        try {
        Cursor cursor = GetData("Select * From Chat_database");
        if (cursor.getColumnCount() == 8)
        QueryData("Drop table Chat_database");
        cursor.close();
        } catch (Exception exception) {}
        QueryData("CREATE TABLE IF NOT EXISTS Chat_database( ID INTEGER PRIMARY KEY AUTOINCREMENT,\n ngay_nhan DATE NOT NULL,\n gio_nhan VARCHAR(8) NOT NULL,\n type_kh INTEGER DEFAULT 0,\n ten_kh VARCHAR(20) NOT NULL,\n so_dienthoai VARCHAR(20) NOT NULL,\n use_app VARCHAR(20) NOT NULL,\n nd_goc VARCHAR(500) DEFAULT NULL,\n del_sms INTEGER DEFAULT 0);");
        }

public Cursor GetData(String paramString) {
        return getReadableDatabase().rawQuery(paramString, null);
        }

public void Gui_Tin_Nhan(int paramInt) {
        // Byte code:
        //   0: invokestatic getInstance : ()Ljava/util/Calendar;
        //   3: astore_2
        //   4: aload_2
        //   5: new java/util/Date
        //   8: dup
        //   9: invokespecial <init> : ()V
        //   12: invokevirtual setTime : (Ljava/util/Date;)V
        //   15: new java/text/SimpleDateFormat
        //   18: dup
        //   19: ldc_w 'yyyy-MM-dd'
        //   22: invokespecial <init> : (Ljava/lang/String;)V
        //   25: astore_3
        //   26: new java/text/SimpleDateFormat
        //   29: dup
        //   30: ldc_w 'HH:mm:ss'
        //   33: invokespecial <init> : (Ljava/lang/String;)V
        //   36: astore #4
        //   38: aload_3
        //   39: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   42: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   45: aload #4
        //   47: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   50: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   53: aload_3
        //   54: aload_2
        //   55: invokevirtual getTime : ()Ljava/util/Date;
        //   58: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
        //   61: astore #5
        //   63: aload #4
        //   65: aload_2
        //   66: invokevirtual getTime : ()Ljava/util/Date;
        //   69: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
        //   72: astore #6
        //   74: new java/lang/StringBuilder
        //   77: dup
        //   78: invokespecial <init> : ()V
        //   81: astore #4
        //   83: aload #4
        //   85: ldc_w 'Select * From tbl_tinnhanS WHERE ID = '
        //   88: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   91: pop
        //   92: aload #4
        //   94: iload_1
        //   95: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   98: pop
        //   99: aload_0
        //   100: aload #4
        //   102: invokevirtual toString : ()Ljava/lang/String;
        //   105: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   108: astore #7
        //   110: aload #7
        //   112: invokeinterface moveToFirst : ()Z
        //   117: pop
        //   118: aload #7
        //   120: bipush #7
        //   122: invokeinterface getInt : (I)I
        //   127: istore #8
        //   129: aload #7
        //   131: iconst_5
        //   132: invokeinterface getString : (I)Ljava/lang/String;
        //   137: astore #9
        //   139: aload #7
        //   141: iconst_1
        //   142: invokeinterface getString : (I)Ljava/lang/String;
        //   147: astore_2
        //   148: new java/lang/StringBuilder
        //   151: dup
        //   152: invokespecial <init> : ()V
        //   155: astore #4
        //   157: aload #4
        //   159: ldc_w 'Select * From tbl_kh_new Where sdt = ''
        //   162: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   165: pop
        //   166: aload #4
        //   168: aload #7
        //   170: iconst_5
        //   171: invokeinterface getString : (I)Ljava/lang/String;
        //   176: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   179: pop
        //   180: aload #4
        //   182: ldc_w '''
        //   185: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   188: pop
        //   189: aload_0
        //   190: aload #4
        //   192: invokevirtual toString : ()Ljava/lang/String;
        //   195: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   198: astore #10
        //   200: aload #10
        //   202: invokeinterface moveToFirst : ()Z
        //   207: pop
        //   208: new org/json/JSONObject
        //   211: astore #4
        //   213: aload #4
        //   215: aload #10
        //   217: iconst_5
        //   218: invokeinterface getString : (I)Ljava/lang/String;
        //   223: invokespecial <init> : (Ljava/lang/String;)V
        //   226: aload_0
        //   227: aload #4
        //   229: putfield json : Lorg/json/JSONObject;
        //   232: aload #4
        //   234: ldc_w 'caidat_tg'
        //   237: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   240: astore #4
        //   242: aload_0
        //   243: aload #4
        //   245: putfield caidat_tg : Lorg/json/JSONObject;
        //   248: aload #4
        //   250: ldc_w 'ok_tin'
        //   253: invokevirtual getInt : (Ljava/lang/String;)I
        //   256: istore #11
        //   258: goto -> 276
        //   261: astore #4
        //   263: goto -> 268
        //   266: astore #4
        //   268: aload #4
        //   270: invokevirtual printStackTrace : ()V
        //   273: iconst_0
        //   274: istore #11
        //   276: iconst_0
        //   277: istore #12
        //   279: iconst_0
        //   280: istore #13
        //   282: aload #7
        //   284: bipush #13
        //   286: invokeinterface getInt : (I)I
        //   291: iconst_1
        //   292: if_icmpne -> 1185
        //   295: iload #11
        //   297: iconst_5
        //   298: if_icmpne -> 748
        //   301: new java/lang/StringBuilder
        //   304: dup
        //   305: invokespecial <init> : ()V
        //   308: astore #4
        //   310: aload #4
        //   312: ldc_w 'Ok Tin '
        //   315: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   318: pop
        //   319: aload #4
        //   321: iload #8
        //   323: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   326: pop
        //   327: aload #4
        //   329: ldc_w '\\n'
        //   332: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   335: pop
        //   336: aload #4
        //   338: aload #7
        //   340: bipush #8
        //   342: invokeinterface getString : (I)Ljava/lang/String;
        //   347: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   350: pop
        //   351: aload #4
        //   353: invokevirtual toString : ()Ljava/lang/String;
        //   356: astore #4
        //   358: aload #7
        //   360: bipush #6
        //   362: invokeinterface getString : (I)Ljava/lang/String;
        //   367: ldc_w 'sms'
        //   370: invokevirtual indexOf : (Ljava/lang/String;)I
        //   373: iconst_m1
        //   374: if_icmple -> 388
        //   377: aload_0
        //   378: aload #9
        //   380: aload #4
        //   382: invokevirtual SendSMS : (Ljava/lang/String;Ljava/lang/String;)V
        //   385: goto -> 677
        //   388: aload #7
        //   390: bipush #6
        //   392: invokeinterface getString : (I)Ljava/lang/String;
        //   397: ldc_w 'TL'
        //   400: invokevirtual indexOf : (Ljava/lang/String;)I
        //   403: iconst_m1
        //   404: if_icmple -> 436
        //   407: new android/os/Handler
        //   410: dup
        //   411: invokestatic getMainLooper : ()Landroid/os/Looper;
        //   414: invokespecial <init> : (Landroid/os/Looper;)V
        //   417: new tamhoang/ldpro4/data/Database$6
        //   420: dup
        //   421: aload_0
        //   422: aload #9
        //   424: aload #4
        //   426: invokespecial <init> : (Ltamhoang/ldpro4/data/Database;Ljava/lang/String;Ljava/lang/String;)V
        //   429: invokevirtual post : (Ljava/lang/Runnable;)Z
        //   432: pop
        //   433: goto -> 677
        //   436: new org/json/JSONObject
        //   439: astore_3
        //   440: aload_3
        //   441: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   444: aload #9
        //   446: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   449: invokespecial <init> : (Ljava/lang/String;)V
        //   452: aload_3
        //   453: ldc_w 'Time'
        //   456: invokevirtual getInt : (Ljava/lang/String;)I
        //   459: istore #14
        //   461: iload #14
        //   463: iconst_3
        //   464: if_icmple -> 496
        //   467: new tamhoang/ldpro4/NotificationReader
        //   470: astore_3
        //   471: aload_3
        //   472: invokespecial <init> : ()V
        //   475: aload_3
        //   476: aload #7
        //   478: iconst_5
        //   479: invokeinterface getString : (I)Ljava/lang/String;
        //   484: aload #4
        //   486: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   489: goto -> 516
        //   492: astore_3
        //   493: goto -> 524
        //   496: aload_3
        //   497: aload #4
        //   499: ldc_w 'OK'
        //   502: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   505: pop
        //   506: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   509: aload #9
        //   511: aload_3
        //   512: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   515: pop
        //   516: goto -> 544
        //   519: astore_3
        //   520: goto -> 524
        //   523: astore_3
        //   524: new tamhoang/ldpro4/NotificationReader
        //   527: dup
        //   528: invokespecial <init> : ()V
        //   531: aload #7
        //   533: iconst_5
        //   534: invokeinterface getString : (I)Ljava/lang/String;
        //   539: aload #4
        //   541: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   544: new java/lang/StringBuilder
        //   547: dup
        //   548: invokespecial <init> : ()V
        //   551: astore_3
        //   552: aload_3
        //   553: ldc_w 'Insert into Chat_database Values( null,''
        //   556: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   559: pop
        //   560: aload_3
        //   561: aload #5
        //   563: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   566: pop
        //   567: aload_3
        //   568: ldc_w '', ''
        //   571: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   574: pop
        //   575: aload_3
        //   576: aload #6
        //   578: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   581: pop
        //   582: aload_3
        //   583: ldc_w '', 2, ''
        //   586: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   589: pop
        //   590: aload_3
        //   591: aload #7
        //   593: iconst_4
        //   594: invokeinterface getString : (I)Ljava/lang/String;
        //   599: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   602: pop
        //   603: aload_3
        //   604: ldc_w '', ''
        //   607: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   610: pop
        //   611: aload_3
        //   612: aload #7
        //   614: iconst_5
        //   615: invokeinterface getString : (I)Ljava/lang/String;
        //   620: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   623: pop
        //   624: aload_3
        //   625: ldc_w '', ''
        //   628: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   631: pop
        //   632: aload_3
        //   633: aload #7
        //   635: bipush #6
        //   637: invokeinterface getString : (I)Ljava/lang/String;
        //   642: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   645: pop
        //   646: aload_3
        //   647: ldc_w '',''
        //   650: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   653: pop
        //   654: aload_3
        //   655: aload #4
        //   657: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   660: pop
        //   661: aload_3
        //   662: ldc_w '',1)'
        //   665: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   668: pop
        //   669: aload_0
        //   670: aload_3
        //   671: invokevirtual toString : ()Ljava/lang/String;
        //   674: invokevirtual QueryData : (Ljava/lang/String;)V
        //   677: new java/lang/StringBuilder
        //   680: dup
        //   681: invokespecial <init> : ()V
        //   684: astore #4
        //   686: aload #4
        //   688: ldc_w 'Update tbl_tinnhanS set ok_tn = 0 WHERE ngay_nhan = ''
        //   691: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   694: pop
        //   695: aload #4
        //   697: aload_2
        //   698: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   701: pop
        //   702: aload #4
        //   704: ldc_w '' AND so_dienthoai = ''
        //   707: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   710: pop
        //   711: aload #4
        //   713: aload #9
        //   715: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   718: pop
        //   719: aload #4
        //   721: ldc_w '' AND so_tin_nhan = '
        //   724: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   727: pop
        //   728: aload #4
        //   730: iload #8
        //   732: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   735: pop
        //   736: aload_0
        //   737: aload #4
        //   739: invokevirtual toString : ()Ljava/lang/String;
        //   742: invokevirtual QueryData : (Ljava/lang/String;)V
        //   745: goto -> 1185
        //   748: ldc_w 'Time'
        //   751: astore_3
        //   752: iload #11
        //   754: iconst_4
        //   755: if_icmpne -> 1182
        //   758: new java/lang/StringBuilder
        //   761: dup
        //   762: invokespecial <init> : ()V
        //   765: astore #4
        //   767: aload #4
        //   769: ldc_w 'Ok Tin '
        //   772: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   775: pop
        //   776: aload #4
        //   778: iload #8
        //   780: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   783: pop
        //   784: aload #4
        //   786: invokevirtual toString : ()Ljava/lang/String;
        //   789: astore #4
        //   791: aload #7
        //   793: bipush #6
        //   795: invokeinterface getString : (I)Ljava/lang/String;
        //   800: ldc_w 'sms'
        //   803: invokevirtual indexOf : (Ljava/lang/String;)I
        //   806: iconst_m1
        //   807: if_icmple -> 821
        //   810: aload_0
        //   811: aload #9
        //   813: aload #4
        //   815: invokevirtual SendSMS : (Ljava/lang/String;Ljava/lang/String;)V
        //   818: goto -> 1111
        //   821: aload #7
        //   823: bipush #6
        //   825: invokeinterface getString : (I)Ljava/lang/String;
        //   830: ldc_w 'TL'
        //   833: invokevirtual indexOf : (Ljava/lang/String;)I
        //   836: iconst_m1
        //   837: if_icmple -> 869
        //   840: new android/os/Handler
        //   843: dup
        //   844: invokestatic getMainLooper : ()Landroid/os/Looper;
        //   847: invokespecial <init> : (Landroid/os/Looper;)V
        //   850: new tamhoang/ldpro4/data/Database$7
        //   853: dup
        //   854: aload_0
        //   855: aload #9
        //   857: aload #4
        //   859: invokespecial <init> : (Ltamhoang/ldpro4/data/Database;Ljava/lang/String;Ljava/lang/String;)V
        //   862: invokevirtual post : (Ljava/lang/Runnable;)Z
        //   865: pop
        //   866: goto -> 1111
        //   869: new org/json/JSONObject
        //   872: astore #15
        //   874: aload #15
        //   876: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   879: aload #9
        //   881: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   884: invokespecial <init> : (Ljava/lang/String;)V
        //   887: aload #15
        //   889: aload_3
        //   890: invokevirtual getInt : (Ljava/lang/String;)I
        //   893: istore #14
        //   895: iload #14
        //   897: iconst_3
        //   898: if_icmple -> 930
        //   901: new tamhoang/ldpro4/NotificationReader
        //   904: astore_3
        //   905: aload_3
        //   906: invokespecial <init> : ()V
        //   909: aload_3
        //   910: aload #7
        //   912: iconst_5
        //   913: invokeinterface getString : (I)Ljava/lang/String;
        //   918: aload #4
        //   920: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   923: goto -> 952
        //   926: astore_3
        //   927: goto -> 964
        //   930: aload #15
        //   932: aload #4
        //   934: ldc_w 'OK'
        //   937: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   940: pop
        //   941: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   944: aload #9
        //   946: aload #15
        //   948: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   951: pop
        //   952: goto -> 984
        //   955: astore_3
        //   956: goto -> 964
        //   959: astore_3
        //   960: goto -> 964
        //   963: astore_3
        //   964: new tamhoang/ldpro4/NotificationReader
        //   967: dup
        //   968: invokespecial <init> : ()V
        //   971: aload #7
        //   973: iconst_5
        //   974: invokeinterface getString : (I)Ljava/lang/String;
        //   979: aload #4
        //   981: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   984: new java/lang/StringBuilder
        //   987: dup
        //   988: invokespecial <init> : ()V
        //   991: astore_3
        //   992: aload_3
        //   993: ldc_w 'Insert into Chat_database Values( null,''
        //   996: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   999: pop
        //   1000: aload_3
        //   1001: aload #5
        //   1003: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1006: pop
        //   1007: aload_3
        //   1008: ldc_w '', ''
        //   1011: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1014: pop
        //   1015: aload_3
        //   1016: aload #6
        //   1018: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1021: pop
        //   1022: aload_3
        //   1023: ldc_w '', 2, ''
        //   1026: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1029: pop
        //   1030: aload_3
        //   1031: aload #7
        //   1033: iconst_5
        //   1034: invokeinterface getString : (I)Ljava/lang/String;
        //   1039: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1042: pop
        //   1043: aload_3
        //   1044: ldc_w '', ''
        //   1047: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1050: pop
        //   1051: aload_3
        //   1052: aload #9
        //   1054: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1057: pop
        //   1058: aload_3
        //   1059: ldc_w '', ''
        //   1062: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1065: pop
        //   1066: aload_3
        //   1067: aload #7
        //   1069: bipush #6
        //   1071: invokeinterface getString : (I)Ljava/lang/String;
        //   1076: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1079: pop
        //   1080: aload_3
        //   1081: ldc_w '',''
        //   1084: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1087: pop
        //   1088: aload_3
        //   1089: aload #4
        //   1091: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1094: pop
        //   1095: aload_3
        //   1096: ldc_w '',1)'
        //   1099: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1102: pop
        //   1103: aload_0
        //   1104: aload_3
        //   1105: invokevirtual toString : ()Ljava/lang/String;
        //   1108: invokevirtual QueryData : (Ljava/lang/String;)V
        //   1111: new java/lang/StringBuilder
        //   1114: dup
        //   1115: invokespecial <init> : ()V
        //   1118: astore #4
        //   1120: aload #4
        //   1122: ldc_w 'Update tbl_tinnhanS set ok_tn = 0 WHERE ngay_nhan = ''
        //   1125: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1128: pop
        //   1129: aload #4
        //   1131: aload_2
        //   1132: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1135: pop
        //   1136: aload #4
        //   1138: ldc_w '' AND so_dienthoai = ''
        //   1141: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1144: pop
        //   1145: aload #4
        //   1147: aload #9
        //   1149: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1152: pop
        //   1153: aload #4
        //   1155: ldc_w '' AND so_tin_nhan = '
        //   1158: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1161: pop
        //   1162: aload #4
        //   1164: iload #8
        //   1166: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1169: pop
        //   1170: aload_0
        //   1171: aload #4
        //   1173: invokevirtual toString : ()Ljava/lang/String;
        //   1176: invokevirtual QueryData : (Ljava/lang/String;)V
        //   1179: goto -> 1185
        //   1182: goto -> 1185
        //   1185: ldc_w 'TL'
        //   1188: astore #15
        //   1190: ldc_w 'Time'
        //   1193: astore #4
        //   1195: aload #7
        //   1197: bipush #11
        //   1199: invokeinterface getString : (I)Ljava/lang/String;
        //   1204: ldc_w 'ok'
        //   1207: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1210: ifne -> 5772
        //   1213: aload #7
        //   1215: bipush #11
        //   1217: invokeinterface getString : (I)Ljava/lang/String;
        //   1222: invokevirtual length : ()I
        //   1225: iconst_2
        //   1226: if_icmpne -> 5772
        //   1229: aload #7
        //   1231: bipush #13
        //   1233: invokeinterface getInt : (I)I
        //   1238: iconst_1
        //   1239: if_icmpne -> 5772
        //   1242: iload #11
        //   1244: ifne -> 1674
        //   1247: new java/lang/StringBuilder
        //   1250: dup
        //   1251: invokespecial <init> : ()V
        //   1254: astore_3
        //   1255: aload_3
        //   1256: ldc_w 'Ok Tin '
        //   1259: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1262: pop
        //   1263: aload_3
        //   1264: iload #8
        //   1266: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1269: pop
        //   1270: aload_3
        //   1271: ldc_w '\\n'
        //   1274: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1277: pop
        //   1278: aload_3
        //   1279: aload #7
        //   1281: bipush #10
        //   1283: invokeinterface getString : (I)Ljava/lang/String;
        //   1288: ldc 'de dit db'
        //   1290: ldc 'de'
        //   1292: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1295: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1298: pop
        //   1299: aload_3
        //   1300: invokevirtual toString : ()Ljava/lang/String;
        //   1303: astore_3
        //   1304: aload #7
        //   1306: bipush #6
        //   1308: invokeinterface getString : (I)Ljava/lang/String;
        //   1313: ldc_w 'sms'
        //   1316: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1319: iconst_m1
        //   1320: if_icmple -> 1333
        //   1323: aload_0
        //   1324: aload #9
        //   1326: aload_3
        //   1327: invokevirtual SendSMS : (Ljava/lang/String;Ljava/lang/String;)V
        //   1330: goto -> 1658
        //   1333: aload #7
        //   1335: bipush #6
        //   1337: invokeinterface getString : (I)Ljava/lang/String;
        //   1342: ldc_w 'TL'
        //   1345: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1348: iconst_m1
        //   1349: if_icmple -> 1380
        //   1352: new android/os/Handler
        //   1355: dup
        //   1356: invokestatic getMainLooper : ()Landroid/os/Looper;
        //   1359: invokespecial <init> : (Landroid/os/Looper;)V
        //   1362: new tamhoang/ldpro4/data/Database$8
        //   1365: dup
        //   1366: aload_0
        //   1367: aload #9
        //   1369: aload_3
        //   1370: invokespecial <init> : (Ltamhoang/ldpro4/data/Database;Ljava/lang/String;Ljava/lang/String;)V
        //   1373: invokevirtual post : (Ljava/lang/Runnable;)Z
        //   1376: pop
        //   1377: goto -> 1658
        //   1380: new org/json/JSONObject
        //   1383: astore #15
        //   1385: aload #15
        //   1387: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   1390: aload #9
        //   1392: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1395: invokespecial <init> : (Ljava/lang/String;)V
        //   1398: aload #15
        //   1400: ldc_w 'Time'
        //   1403: invokevirtual getInt : (Ljava/lang/String;)I
        //   1406: istore #11
        //   1408: ldc_w 'Time'
        //   1411: astore #4
        //   1413: iload #11
        //   1415: iconst_3
        //   1416: if_icmple -> 1451
        //   1419: new tamhoang/ldpro4/NotificationReader
        //   1422: astore #15
        //   1424: aload #15
        //   1426: invokespecial <init> : ()V
        //   1429: aload #15
        //   1431: aload #7
        //   1433: iconst_5
        //   1434: invokeinterface getString : (I)Ljava/lang/String;
        //   1439: aload_3
        //   1440: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   1443: goto -> 1472
        //   1446: astore #15
        //   1448: goto -> 1492
        //   1451: aload #15
        //   1453: aload_3
        //   1454: ldc_w 'OK'
        //   1457: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1460: pop
        //   1461: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   1464: aload #9
        //   1466: aload #15
        //   1468: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1471: pop
        //   1472: goto -> 1511
        //   1475: astore #15
        //   1477: goto -> 1492
        //   1480: astore #4
        //   1482: ldc_w 'Time'
        //   1485: astore #4
        //   1487: goto -> 1492
        //   1490: astore #15
        //   1492: new tamhoang/ldpro4/NotificationReader
        //   1495: dup
        //   1496: invokespecial <init> : ()V
        //   1499: aload #7
        //   1501: iconst_5
        //   1502: invokeinterface getString : (I)Ljava/lang/String;
        //   1507: aload_3
        //   1508: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   1511: new java/lang/StringBuilder
        //   1514: dup
        //   1515: invokespecial <init> : ()V
        //   1518: astore #15
        //   1520: aload #15
        //   1522: ldc_w 'Insert into Chat_database Values( null,''
        //   1525: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1528: pop
        //   1529: aload #15
        //   1531: aload #5
        //   1533: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1536: pop
        //   1537: aload #15
        //   1539: ldc_w '', ''
        //   1542: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1545: pop
        //   1546: aload #15
        //   1548: aload #6
        //   1550: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1553: pop
        //   1554: aload #15
        //   1556: ldc_w '', 2, ''
        //   1559: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1562: pop
        //   1563: aload #15
        //   1565: aload #7
        //   1567: iconst_4
        //   1568: invokeinterface getString : (I)Ljava/lang/String;
        //   1573: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1576: pop
        //   1577: aload #15
        //   1579: ldc_w '', ''
        //   1582: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1585: pop
        //   1586: aload #15
        //   1588: aload #7
        //   1590: iconst_5
        //   1591: invokeinterface getString : (I)Ljava/lang/String;
        //   1596: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1599: pop
        //   1600: aload #15
        //   1602: ldc_w '', ''
        //   1605: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1608: pop
        //   1609: aload #15
        //   1611: aload #7
        //   1613: bipush #6
        //   1615: invokeinterface getString : (I)Ljava/lang/String;
        //   1620: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1623: pop
        //   1624: aload #15
        //   1626: ldc_w '',''
        //   1629: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1632: pop
        //   1633: aload #15
        //   1635: aload_3
        //   1636: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1639: pop
        //   1640: aload #15
        //   1642: ldc_w '',1)'
        //   1645: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1648: pop
        //   1649: aload_0
        //   1650: aload #15
        //   1652: invokevirtual toString : ()Ljava/lang/String;
        //   1655: invokevirtual QueryData : (Ljava/lang/String;)V
        //   1658: ldc_w 'sms'
        //   1661: astore #15
        //   1663: ldc_w 'TL'
        //   1666: astore_3
        //   1667: aload #4
        //   1669: astore #16
        //   1671: goto -> 3441
        //   1674: iload #11
        //   1676: iconst_1
        //   1677: if_icmpne -> 2562
        //   1680: aload #7
        //   1682: bipush #10
        //   1684: invokeinterface getString : (I)Ljava/lang/String;
        //   1689: ldc_w 'B'
        //   1692: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1695: iconst_m1
        //   1696: if_icmpne -> 2097
        //   1699: new java/lang/StringBuilder
        //   1702: dup
        //   1703: invokespecial <init> : ()V
        //   1706: astore_3
        //   1707: aload_3
        //   1708: ldc_w 'Ok Tin '
        //   1711: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1714: pop
        //   1715: aload_3
        //   1716: iload #8
        //   1718: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1721: pop
        //   1722: aload_3
        //   1723: invokevirtual toString : ()Ljava/lang/String;
        //   1726: astore_3
        //   1727: aload #7
        //   1729: bipush #6
        //   1731: invokeinterface getString : (I)Ljava/lang/String;
        //   1736: ldc_w 'sms'
        //   1739: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1742: iconst_m1
        //   1743: if_icmple -> 1756
        //   1746: aload_0
        //   1747: aload #9
        //   1749: aload_3
        //   1750: invokevirtual SendSMS : (Ljava/lang/String;Ljava/lang/String;)V
        //   1753: goto -> 2081
        //   1756: aload #7
        //   1758: bipush #6
        //   1760: invokeinterface getString : (I)Ljava/lang/String;
        //   1765: ldc_w 'TL'
        //   1768: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1771: iconst_m1
        //   1772: if_icmple -> 1803
        //   1775: new android/os/Handler
        //   1778: dup
        //   1779: invokestatic getMainLooper : ()Landroid/os/Looper;
        //   1782: invokespecial <init> : (Landroid/os/Looper;)V
        //   1785: new tamhoang/ldpro4/data/Database$9
        //   1788: dup
        //   1789: aload_0
        //   1790: aload #9
        //   1792: aload_3
        //   1793: invokespecial <init> : (Ltamhoang/ldpro4/data/Database;Ljava/lang/String;Ljava/lang/String;)V
        //   1796: invokevirtual post : (Ljava/lang/Runnable;)Z
        //   1799: pop
        //   1800: goto -> 2081
        //   1803: new org/json/JSONObject
        //   1806: astore #15
        //   1808: aload #15
        //   1810: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   1813: aload #9
        //   1815: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1818: invokespecial <init> : (Ljava/lang/String;)V
        //   1821: aload #15
        //   1823: ldc_w 'Time'
        //   1826: invokevirtual getInt : (Ljava/lang/String;)I
        //   1829: istore #11
        //   1831: ldc_w 'Time'
        //   1834: astore #4
        //   1836: iload #11
        //   1838: iconst_3
        //   1839: if_icmple -> 1874
        //   1842: new tamhoang/ldpro4/NotificationReader
        //   1845: astore #15
        //   1847: aload #15
        //   1849: invokespecial <init> : ()V
        //   1852: aload #15
        //   1854: aload #7
        //   1856: iconst_5
        //   1857: invokeinterface getString : (I)Ljava/lang/String;
        //   1862: aload_3
        //   1863: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   1866: goto -> 1895
        //   1869: astore #15
        //   1871: goto -> 1915
        //   1874: aload #15
        //   1876: aload_3
        //   1877: ldc_w 'OK'
        //   1880: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1883: pop
        //   1884: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   1887: aload #9
        //   1889: aload #15
        //   1891: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1894: pop
        //   1895: goto -> 1934
        //   1898: astore #15
        //   1900: goto -> 1915
        //   1903: astore #4
        //   1905: ldc_w 'Time'
        //   1908: astore #4
        //   1910: goto -> 1915
        //   1913: astore #15
        //   1915: new tamhoang/ldpro4/NotificationReader
        //   1918: dup
        //   1919: invokespecial <init> : ()V
        //   1922: aload #7
        //   1924: iconst_5
        //   1925: invokeinterface getString : (I)Ljava/lang/String;
        //   1930: aload_3
        //   1931: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   1934: new java/lang/StringBuilder
        //   1937: dup
        //   1938: invokespecial <init> : ()V
        //   1941: astore #15
        //   1943: aload #15
        //   1945: ldc_w 'Insert into Chat_database Values( null,''
        //   1948: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1951: pop
        //   1952: aload #15
        //   1954: aload #5
        //   1956: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1959: pop
        //   1960: aload #15
        //   1962: ldc_w '', ''
        //   1965: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1968: pop
        //   1969: aload #15
        //   1971: aload #6
        //   1973: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1976: pop
        //   1977: aload #15
        //   1979: ldc_w '', 2, ''
        //   1982: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1985: pop
        //   1986: aload #15
        //   1988: aload #7
        //   1990: iconst_4
        //   1991: invokeinterface getString : (I)Ljava/lang/String;
        //   1996: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1999: pop
        //   2000: aload #15
        //   2002: ldc_w '', ''
        //   2005: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2008: pop
        //   2009: aload #15
        //   2011: aload #7
        //   2013: iconst_5
        //   2014: invokeinterface getString : (I)Ljava/lang/String;
        //   2019: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2022: pop
        //   2023: aload #15
        //   2025: ldc_w '', ''
        //   2028: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2031: pop
        //   2032: aload #15
        //   2034: aload #7
        //   2036: bipush #6
        //   2038: invokeinterface getString : (I)Ljava/lang/String;
        //   2043: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2046: pop
        //   2047: aload #15
        //   2049: ldc_w '',''
        //   2052: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2055: pop
        //   2056: aload #15
        //   2058: aload_3
        //   2059: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2062: pop
        //   2063: aload #15
        //   2065: ldc_w '',1)'
        //   2068: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2071: pop
        //   2072: aload_0
        //   2073: aload #15
        //   2075: invokevirtual toString : ()Ljava/lang/String;
        //   2078: invokevirtual QueryData : (Ljava/lang/String;)V
        //   2081: ldc_w 'sms'
        //   2084: astore #15
        //   2086: ldc_w 'TL'
        //   2089: astore_3
        //   2090: aload #4
        //   2092: astore #16
        //   2094: goto -> 3441
        //   2097: new java/lang/StringBuilder
        //   2100: dup
        //   2101: invokespecial <init> : ()V
        //   2104: astore_3
        //   2105: aload_3
        //   2106: aload #7
        //   2108: bipush #10
        //   2110: invokeinterface getString : (I)Ljava/lang/String;
        //   2115: iconst_0
        //   2116: aload #7
        //   2118: bipush #10
        //   2120: invokeinterface getString : (I)Ljava/lang/String;
        //   2125: ldc_w '\\n'
        //   2128: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2131: iconst_1
        //   2132: isub
        //   2133: invokevirtual substring : (II)Ljava/lang/String;
        //   2136: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2139: pop
        //   2140: aload_3
        //   2141: ldc_w '\\nOk tin '
        //   2144: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2147: pop
        //   2148: aload_3
        //   2149: iload #8
        //   2151: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   2154: pop
        //   2155: aload_3
        //   2156: ldc_w ': '
        //   2159: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2162: pop
        //   2163: aload_3
        //   2164: invokevirtual toString : ()Ljava/lang/String;
        //   2167: astore #16
        //   2169: aload #7
        //   2171: bipush #6
        //   2173: invokeinterface getString : (I)Ljava/lang/String;
        //   2178: ldc_w 'sms'
        //   2181: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2184: iconst_m1
        //   2185: if_icmple -> 2206
        //   2188: aload_0
        //   2189: aload #9
        //   2191: aload #16
        //   2193: invokevirtual SendSMS : (Ljava/lang/String;Ljava/lang/String;)V
        //   2196: aload #4
        //   2198: astore_3
        //   2199: aload #15
        //   2201: astore #4
        //   2203: goto -> 2548
        //   2206: aload #7
        //   2208: bipush #6
        //   2210: invokeinterface getString : (I)Ljava/lang/String;
        //   2215: ldc_w 'TL'
        //   2218: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2221: iconst_m1
        //   2222: if_icmple -> 2266
        //   2225: new android/os/Handler
        //   2228: dup
        //   2229: invokestatic getMainLooper : ()Landroid/os/Looper;
        //   2232: invokespecial <init> : (Landroid/os/Looper;)V
        //   2235: new tamhoang/ldpro4/data/Database$10
        //   2238: dup
        //   2239: aload_0
        //   2240: aload #9
        //   2242: aload #16
        //   2244: invokespecial <init> : (Ltamhoang/ldpro4/data/Database;Ljava/lang/String;Ljava/lang/String;)V
        //   2247: invokevirtual post : (Ljava/lang/Runnable;)Z
        //   2250: pop
        //   2251: ldc_w 'TL'
        //   2254: astore #15
        //   2256: aload #4
        //   2258: astore_3
        //   2259: aload #15
        //   2261: astore #4
        //   2263: goto -> 2548
        //   2266: new org/json/JSONObject
        //   2269: astore_3
        //   2270: aload_3
        //   2271: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   2274: aload #9
        //   2276: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2279: invokespecial <init> : (Ljava/lang/String;)V
        //   2282: aload_3
        //   2283: ldc_w 'Time'
        //   2286: invokevirtual getInt : (Ljava/lang/String;)I
        //   2289: istore #11
        //   2291: ldc_w 'Time'
        //   2294: astore #4
        //   2296: iload #11
        //   2298: iconst_3
        //   2299: if_icmple -> 2331
        //   2302: new tamhoang/ldpro4/NotificationReader
        //   2305: astore_3
        //   2306: aload_3
        //   2307: invokespecial <init> : ()V
        //   2310: aload_3
        //   2311: aload #7
        //   2313: iconst_5
        //   2314: invokeinterface getString : (I)Ljava/lang/String;
        //   2319: aload #16
        //   2321: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   2324: goto -> 2351
        //   2327: astore_3
        //   2328: goto -> 2372
        //   2331: aload_3
        //   2332: aload #16
        //   2334: ldc_w 'OK'
        //   2337: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   2340: pop
        //   2341: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   2344: aload #9
        //   2346: aload_3
        //   2347: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   2350: pop
        //   2351: aload #4
        //   2353: astore_3
        //   2354: goto -> 2395
        //   2357: astore_3
        //   2358: goto -> 2372
        //   2361: astore #4
        //   2363: ldc_w 'Time'
        //   2366: astore #4
        //   2368: goto -> 2372
        //   2371: astore_3
        //   2372: new tamhoang/ldpro4/NotificationReader
        //   2375: dup
        //   2376: invokespecial <init> : ()V
        //   2379: aload #7
        //   2381: iconst_5
        //   2382: invokeinterface getString : (I)Ljava/lang/String;
        //   2387: aload #16
        //   2389: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   2392: aload #4
        //   2394: astore_3
        //   2395: ldc_w 'TL'
        //   2398: astore #4
        //   2400: new java/lang/StringBuilder
        //   2403: dup
        //   2404: invokespecial <init> : ()V
        //   2407: astore #15
        //   2409: aload #15
        //   2411: ldc_w 'Insert into Chat_database Values( null,''
        //   2414: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2417: pop
        //   2418: aload #15
        //   2420: aload #5
        //   2422: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2425: pop
        //   2426: aload #15
        //   2428: ldc_w '', ''
        //   2431: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2434: pop
        //   2435: aload #15
        //   2437: aload #6
        //   2439: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2442: pop
        //   2443: aload #15
        //   2445: ldc_w '', 2, ''
        //   2448: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2451: pop
        //   2452: aload #15
        //   2454: aload #7
        //   2456: iconst_4
        //   2457: invokeinterface getString : (I)Ljava/lang/String;
        //   2462: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2465: pop
        //   2466: aload #15
        //   2468: ldc_w '', ''
        //   2471: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2474: pop
        //   2475: aload #15
        //   2477: aload #7
        //   2479: iconst_5
        //   2480: invokeinterface getString : (I)Ljava/lang/String;
        //   2485: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2488: pop
        //   2489: aload #15
        //   2491: ldc_w '', ''
        //   2494: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2497: pop
        //   2498: aload #15
        //   2500: aload #7
        //   2502: bipush #6
        //   2504: invokeinterface getString : (I)Ljava/lang/String;
        //   2509: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2512: pop
        //   2513: aload #15
        //   2515: ldc_w '',''
        //   2518: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2521: pop
        //   2522: aload #15
        //   2524: aload #16
        //   2526: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2529: pop
        //   2530: aload #15
        //   2532: ldc_w '',1)'
        //   2535: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2538: pop
        //   2539: aload_0
        //   2540: aload #15
        //   2542: invokevirtual toString : ()Ljava/lang/String;
        //   2545: invokevirtual QueryData : (Ljava/lang/String;)V
        //   2548: ldc_w 'sms'
        //   2551: astore #15
        //   2553: aload_3
        //   2554: astore #16
        //   2556: aload #4
        //   2558: astore_3
        //   2559: goto -> 3441
        //   2562: iload #11
        //   2564: iconst_3
        //   2565: if_icmpne -> 3427
        //   2568: aload #7
        //   2570: bipush #10
        //   2572: invokeinterface getString : (I)Ljava/lang/String;
        //   2577: ldc_w 'B'
        //   2580: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2583: iconst_m1
        //   2584: if_icmpne -> 2980
        //   2587: new java/lang/StringBuilder
        //   2590: dup
        //   2591: invokespecial <init> : ()V
        //   2594: astore #4
        //   2596: aload #4
        //   2598: ldc_w 'Ok Tin '
        //   2601: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2604: pop
        //   2605: aload #4
        //   2607: iload #8
        //   2609: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   2612: pop
        //   2613: aload #4
        //   2615: ldc_w '\\n'
        //   2618: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2621: pop
        //   2622: aload #4
        //   2624: aload #7
        //   2626: bipush #8
        //   2628: invokeinterface getString : (I)Ljava/lang/String;
        //   2633: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2636: pop
        //   2637: aload #4
        //   2639: invokevirtual toString : ()Ljava/lang/String;
        //   2642: astore #4
        //   2644: aload #7
        //   2646: bipush #6
        //   2648: invokeinterface getString : (I)Ljava/lang/String;
        //   2653: ldc_w 'sms'
        //   2656: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2659: iconst_m1
        //   2660: if_icmple -> 2674
        //   2663: aload_0
        //   2664: aload #9
        //   2666: aload #4
        //   2668: invokevirtual SendSMS : (Ljava/lang/String;Ljava/lang/String;)V
        //   2671: goto -> 2963
        //   2674: aload #7
        //   2676: bipush #6
        //   2678: invokeinterface getString : (I)Ljava/lang/String;
        //   2683: ldc_w 'TL'
        //   2686: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2689: iconst_m1
        //   2690: if_icmple -> 2722
        //   2693: new android/os/Handler
        //   2696: dup
        //   2697: invokestatic getMainLooper : ()Landroid/os/Looper;
        //   2700: invokespecial <init> : (Landroid/os/Looper;)V
        //   2703: new tamhoang/ldpro4/data/Database$11
        //   2706: dup
        //   2707: aload_0
        //   2708: aload #9
        //   2710: aload #4
        //   2712: invokespecial <init> : (Ltamhoang/ldpro4/data/Database;Ljava/lang/String;Ljava/lang/String;)V
        //   2715: invokevirtual post : (Ljava/lang/Runnable;)Z
        //   2718: pop
        //   2719: goto -> 2963
        //   2722: new org/json/JSONObject
        //   2725: astore_3
        //   2726: aload_3
        //   2727: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   2730: aload #9
        //   2732: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2735: invokespecial <init> : (Ljava/lang/String;)V
        //   2738: aload_3
        //   2739: ldc_w 'Time'
        //   2742: invokevirtual getInt : (Ljava/lang/String;)I
        //   2745: istore #11
        //   2747: iload #11
        //   2749: iconst_3
        //   2750: if_icmple -> 2782
        //   2753: new tamhoang/ldpro4/NotificationReader
        //   2756: astore_3
        //   2757: aload_3
        //   2758: invokespecial <init> : ()V
        //   2761: aload_3
        //   2762: aload #7
        //   2764: iconst_5
        //   2765: invokeinterface getString : (I)Ljava/lang/String;
        //   2770: aload #4
        //   2772: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   2775: goto -> 2802
        //   2778: astore_3
        //   2779: goto -> 2810
        //   2782: aload_3
        //   2783: aload #4
        //   2785: ldc_w 'OK'
        //   2788: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   2791: pop
        //   2792: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   2795: aload #9
        //   2797: aload_3
        //   2798: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   2801: pop
        //   2802: goto -> 2830
        //   2805: astore_3
        //   2806: goto -> 2810
        //   2809: astore_3
        //   2810: new tamhoang/ldpro4/NotificationReader
        //   2813: dup
        //   2814: invokespecial <init> : ()V
        //   2817: aload #7
        //   2819: iconst_5
        //   2820: invokeinterface getString : (I)Ljava/lang/String;
        //   2825: aload #4
        //   2827: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   2830: new java/lang/StringBuilder
        //   2833: dup
        //   2834: invokespecial <init> : ()V
        //   2837: astore_3
        //   2838: aload_3
        //   2839: ldc_w 'Insert into Chat_database Values( null,''
        //   2842: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2845: pop
        //   2846: aload_3
        //   2847: aload #5
        //   2849: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2852: pop
        //   2853: aload_3
        //   2854: ldc_w '', ''
        //   2857: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2860: pop
        //   2861: aload_3
        //   2862: aload #6
        //   2864: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2867: pop
        //   2868: aload_3
        //   2869: ldc_w '', 2, ''
        //   2872: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2875: pop
        //   2876: aload_3
        //   2877: aload #7
        //   2879: iconst_4
        //   2880: invokeinterface getString : (I)Ljava/lang/String;
        //   2885: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2888: pop
        //   2889: aload_3
        //   2890: ldc_w '', ''
        //   2893: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2896: pop
        //   2897: aload_3
        //   2898: aload #7
        //   2900: iconst_5
        //   2901: invokeinterface getString : (I)Ljava/lang/String;
        //   2906: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2909: pop
        //   2910: aload_3
        //   2911: ldc_w '', ''
        //   2914: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2917: pop
        //   2918: aload_3
        //   2919: aload #7
        //   2921: bipush #6
        //   2923: invokeinterface getString : (I)Ljava/lang/String;
        //   2928: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2931: pop
        //   2932: aload_3
        //   2933: ldc_w '',''
        //   2936: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2939: pop
        //   2940: aload_3
        //   2941: aload #4
        //   2943: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2946: pop
        //   2947: aload_3
        //   2948: ldc_w '',1)'
        //   2951: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2954: pop
        //   2955: aload_0
        //   2956: aload_3
        //   2957: invokevirtual toString : ()Ljava/lang/String;
        //   2960: invokevirtual QueryData : (Ljava/lang/String;)V
        //   2963: ldc_w 'Time'
        //   2966: astore #16
        //   2968: ldc_w 'TL'
        //   2971: astore_3
        //   2972: ldc_w 'sms'
        //   2975: astore #15
        //   2977: goto -> 3441
        //   2980: ldc_w 'sms'
        //   2983: astore #15
        //   2985: ldc_w 'TL'
        //   2988: astore_3
        //   2989: ldc_w 'Time'
        //   2992: astore #16
        //   2994: new java/lang/StringBuilder
        //   2997: dup
        //   2998: invokespecial <init> : ()V
        //   3001: astore #4
        //   3003: aload #4
        //   3005: aload #7
        //   3007: bipush #10
        //   3009: invokeinterface getString : (I)Ljava/lang/String;
        //   3014: iconst_0
        //   3015: aload #7
        //   3017: bipush #10
        //   3019: invokeinterface getString : (I)Ljava/lang/String;
        //   3024: ldc_w '\\n'
        //   3027: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3030: iconst_1
        //   3031: isub
        //   3032: invokevirtual substring : (II)Ljava/lang/String;
        //   3035: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3038: pop
        //   3039: aload #4
        //   3041: ldc_w '\\nOK Tin'
        //   3044: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3047: pop
        //   3048: aload #4
        //   3050: iload #8
        //   3052: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   3055: pop
        //   3056: aload #4
        //   3058: ldc_w '\\n'
        //   3061: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3064: pop
        //   3065: aload #4
        //   3067: aload #7
        //   3069: bipush #8
        //   3071: invokeinterface getString : (I)Ljava/lang/String;
        //   3076: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3079: pop
        //   3080: aload #4
        //   3082: invokevirtual toString : ()Ljava/lang/String;
        //   3085: astore #4
        //   3087: aload #7
        //   3089: bipush #6
        //   3091: invokeinterface getString : (I)Ljava/lang/String;
        //   3096: aload #15
        //   3098: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3101: iconst_m1
        //   3102: if_icmple -> 3116
        //   3105: aload_0
        //   3106: aload #9
        //   3108: aload #4
        //   3110: invokevirtual SendSMS : (Ljava/lang/String;Ljava/lang/String;)V
        //   3113: goto -> 3441
        //   3116: aload #7
        //   3118: bipush #6
        //   3120: invokeinterface getString : (I)Ljava/lang/String;
        //   3125: aload_3
        //   3126: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3129: iconst_m1
        //   3130: if_icmple -> 3162
        //   3133: new android/os/Handler
        //   3136: dup
        //   3137: invokestatic getMainLooper : ()Landroid/os/Looper;
        //   3140: invokespecial <init> : (Landroid/os/Looper;)V
        //   3143: new tamhoang/ldpro4/data/Database$12
        //   3146: dup
        //   3147: aload_0
        //   3148: aload #9
        //   3150: aload #4
        //   3152: invokespecial <init> : (Ltamhoang/ldpro4/data/Database;Ljava/lang/String;Ljava/lang/String;)V
        //   3155: invokevirtual post : (Ljava/lang/Runnable;)Z
        //   3158: pop
        //   3159: goto -> 3441
        //   3162: new org/json/JSONObject
        //   3165: astore #17
        //   3167: aload #17
        //   3169: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   3172: aload #9
        //   3174: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   3177: invokespecial <init> : (Ljava/lang/String;)V
        //   3180: aload #17
        //   3182: aload #16
        //   3184: invokevirtual getInt : (Ljava/lang/String;)I
        //   3187: iconst_3
        //   3188: if_icmple -> 3219
        //   3191: new tamhoang/ldpro4/NotificationReader
        //   3194: astore #16
        //   3196: aload #16
        //   3198: invokespecial <init> : ()V
        //   3201: aload #16
        //   3203: aload #7
        //   3205: iconst_5
        //   3206: invokeinterface getString : (I)Ljava/lang/String;
        //   3211: aload #4
        //   3213: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   3216: goto -> 3241
        //   3219: aload #17
        //   3221: aload #4
        //   3223: ldc_w 'OK'
        //   3226: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   3229: pop
        //   3230: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   3233: aload #9
        //   3235: aload #17
        //   3237: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   3240: pop
        //   3241: goto -> 3271
        //   3244: astore #16
        //   3246: goto -> 3251
        //   3249: astore #16
        //   3251: new tamhoang/ldpro4/NotificationReader
        //   3254: dup
        //   3255: invokespecial <init> : ()V
        //   3258: aload #7
        //   3260: iconst_5
        //   3261: invokeinterface getString : (I)Ljava/lang/String;
        //   3266: aload #4
        //   3268: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   3271: ldc_w 'Time'
        //   3274: astore #16
        //   3276: new java/lang/StringBuilder
        //   3279: dup
        //   3280: invokespecial <init> : ()V
        //   3283: astore #17
        //   3285: aload #17
        //   3287: ldc_w 'Insert into Chat_database Values( null,''
        //   3290: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3293: pop
        //   3294: aload #17
        //   3296: aload #5
        //   3298: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3301: pop
        //   3302: aload #17
        //   3304: ldc_w '', ''
        //   3307: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3310: pop
        //   3311: aload #17
        //   3313: aload #6
        //   3315: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3318: pop
        //   3319: aload #17
        //   3321: ldc_w '', 2, ''
        //   3324: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3327: pop
        //   3328: aload #17
        //   3330: aload #7
        //   3332: iconst_4
        //   3333: invokeinterface getString : (I)Ljava/lang/String;
        //   3338: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3341: pop
        //   3342: aload #17
        //   3344: ldc_w '', ''
        //   3347: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3350: pop
        //   3351: aload #17
        //   3353: aload #7
        //   3355: iconst_5
        //   3356: invokeinterface getString : (I)Ljava/lang/String;
        //   3361: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3364: pop
        //   3365: aload #17
        //   3367: ldc_w '', ''
        //   3370: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3373: pop
        //   3374: aload #17
        //   3376: aload #7
        //   3378: bipush #6
        //   3380: invokeinterface getString : (I)Ljava/lang/String;
        //   3385: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3388: pop
        //   3389: aload #17
        //   3391: ldc_w '',''
        //   3394: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3397: pop
        //   3398: aload #17
        //   3400: aload #4
        //   3402: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3405: pop
        //   3406: aload #17
        //   3408: ldc_w '',1)'
        //   3411: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3414: pop
        //   3415: aload_0
        //   3416: aload #17
        //   3418: invokevirtual toString : ()Ljava/lang/String;
        //   3421: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3424: goto -> 3441
        //   3427: ldc_w 'sms'
        //   3430: astore #15
        //   3432: ldc_w 'TL'
        //   3435: astore_3
        //   3436: ldc_w 'Time'
        //   3439: astore #16
        //   3441: aload_2
        //   3442: astore #17
        //   3444: ldc_w '' AND so_dienthoai = ''
        //   3447: astore #18
        //   3449: ldc_w '',1)'
        //   3452: astore #4
        //   3454: ldc_w '',''
        //   3457: astore #19
        //   3459: ldc_w '', 2, ''
        //   3462: astore #20
        //   3464: new java/lang/StringBuilder
        //   3467: dup
        //   3468: invokespecial <init> : ()V
        //   3471: astore_2
        //   3472: aload_2
        //   3473: ldc_w 'Select * From tbl_chuyenthang WHERE sdt_nhan = ''
        //   3476: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3479: pop
        //   3480: aload_2
        //   3481: aload #9
        //   3483: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3486: pop
        //   3487: aload_2
        //   3488: ldc_w '''
        //   3491: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3494: pop
        //   3495: aload_0
        //   3496: aload_2
        //   3497: invokevirtual toString : ()Ljava/lang/String;
        //   3500: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   3503: astore #21
        //   3505: aload #21
        //   3507: invokeinterface moveToFirst : ()Z
        //   3512: pop
        //   3513: aload #21
        //   3515: invokeinterface getCount : ()I
        //   3520: ifle -> 4820
        //   3523: aload #7
        //   3525: bipush #14
        //   3527: invokeinterface getInt : (I)I
        //   3532: iconst_1
        //   3533: if_icmpne -> 4820
        //   3536: new java/lang/StringBuilder
        //   3539: dup
        //   3540: invokespecial <init> : ()V
        //   3543: astore_2
        //   3544: aload_2
        //   3545: ldc_w 'Select max(so_tin_nhan) From tbl_tinnhanS WHERE so_dienthoai = ''
        //   3548: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3551: pop
        //   3552: aload_2
        //   3553: aload #21
        //   3555: iconst_4
        //   3556: invokeinterface getString : (I)Ljava/lang/String;
        //   3561: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3564: pop
        //   3565: aload_2
        //   3566: ldc_w '' AND ngay_nhan = ''
        //   3569: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3572: pop
        //   3573: aload_2
        //   3574: aload #17
        //   3576: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3579: pop
        //   3580: aload_2
        //   3581: ldc_w '' And type_kh = 2'
        //   3584: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3587: pop
        //   3588: aload_0
        //   3589: aload_2
        //   3590: invokevirtual toString : ()Ljava/lang/String;
        //   3593: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   3596: astore #22
        //   3598: aload #22
        //   3600: invokeinterface moveToFirst : ()Z
        //   3605: pop
        //   3606: aload #22
        //   3608: iconst_0
        //   3609: invokeinterface getInt : (I)I
        //   3614: iconst_1
        //   3615: iadd
        //   3616: istore #11
        //   3618: aload #21
        //   3620: iconst_3
        //   3621: invokeinterface getString : (I)Ljava/lang/String;
        //   3626: aload_3
        //   3627: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3630: iconst_m1
        //   3631: if_icmple -> 3641
        //   3634: ldc_w 'TL'
        //   3637: astore_2
        //   3638: goto -> 3720
        //   3641: aload #21
        //   3643: iconst_3
        //   3644: invokeinterface getString : (I)Ljava/lang/String;
        //   3649: ldc_w 'ZL'
        //   3652: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3655: iconst_m1
        //   3656: if_icmple -> 3666
        //   3659: ldc_w 'ZL'
        //   3662: astore_2
        //   3663: goto -> 3720
        //   3666: aload #21
        //   3668: iconst_3
        //   3669: invokeinterface getString : (I)Ljava/lang/String;
        //   3674: ldc_w 'VB'
        //   3677: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3680: iconst_m1
        //   3681: if_icmple -> 3691
        //   3684: ldc_w 'VB'
        //   3687: astore_2
        //   3688: goto -> 3720
        //   3691: aload #21
        //   3693: iconst_3
        //   3694: invokeinterface getString : (I)Ljava/lang/String;
        //   3699: ldc_w 'WA'
        //   3702: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3705: iconst_m1
        //   3706: if_icmple -> 3716
        //   3709: ldc_w 'WA'
        //   3712: astore_2
        //   3713: goto -> 3720
        //   3716: ldc_w 'sms'
        //   3719: astore_2
        //   3720: new java/lang/StringBuilder
        //   3723: dup
        //   3724: invokespecial <init> : ()V
        //   3727: astore #23
        //   3729: aload #23
        //   3731: ldc_w 'Insert Into tbl_tinnhanS values (null, ''
        //   3734: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3737: pop
        //   3738: aload #23
        //   3740: aload #7
        //   3742: iconst_1
        //   3743: invokeinterface getString : (I)Ljava/lang/String;
        //   3748: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3751: pop
        //   3752: aload #23
        //   3754: ldc_w '', ''
        //   3757: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3760: pop
        //   3761: aload #23
        //   3763: aload #7
        //   3765: iconst_2
        //   3766: invokeinterface getString : (I)Ljava/lang/String;
        //   3771: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3774: pop
        //   3775: aload #23
        //   3777: ldc_w '',2, ''
        //   3780: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3783: pop
        //   3784: aload #23
        //   3786: aload #21
        //   3788: iconst_3
        //   3789: invokeinterface getString : (I)Ljava/lang/String;
        //   3794: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3797: pop
        //   3798: aload #23
        //   3800: ldc_w '', ''
        //   3803: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3806: pop
        //   3807: aload #23
        //   3809: aload #21
        //   3811: iconst_4
        //   3812: invokeinterface getString : (I)Ljava/lang/String;
        //   3817: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3820: pop
        //   3821: aload #23
        //   3823: ldc_w '', ''
        //   3826: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3829: pop
        //   3830: aload #23
        //   3832: aload_2
        //   3833: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3836: pop
        //   3837: aload #23
        //   3839: ldc_w '', '
        //   3842: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3845: pop
        //   3846: aload #23
        //   3848: iload #11
        //   3850: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   3853: pop
        //   3854: aload #23
        //   3856: ldc_w ', ''
        //   3859: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3862: pop
        //   3863: aload #23
        //   3865: aload #7
        //   3867: bipush #8
        //   3869: invokeinterface getString : (I)Ljava/lang/String;
        //   3874: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3877: pop
        //   3878: aload #23
        //   3880: ldc_w '', ''
        //   3883: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3886: pop
        //   3887: aload #23
        //   3889: aload #7
        //   3891: bipush #9
        //   3893: invokeinterface getString : (I)Ljava/lang/String;
        //   3898: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3901: pop
        //   3902: aload #23
        //   3904: aload #19
        //   3906: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3909: pop
        //   3910: aload #23
        //   3912: aload #7
        //   3914: bipush #10
        //   3916: invokeinterface getString : (I)Ljava/lang/String;
        //   3921: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3924: pop
        //   3925: aload #23
        //   3927: ldc_w '', 'ok',0,0,1, ''
        //   3930: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3933: pop
        //   3934: aload #23
        //   3936: aload #7
        //   3938: bipush #15
        //   3940: invokeinterface getString : (I)Ljava/lang/String;
        //   3945: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3948: pop
        //   3949: aload #23
        //   3951: ldc_w '')'
        //   3954: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3957: pop
        //   3958: aload_0
        //   3959: aload #23
        //   3961: invokevirtual toString : ()Ljava/lang/String;
        //   3964: invokevirtual QueryData : (Ljava/lang/String;)V
        //   3967: new java/lang/StringBuilder
        //   3970: dup
        //   3971: invokespecial <init> : ()V
        //   3974: astore #23
        //   3976: aload #23
        //   3978: ldc_w 'Select * From tbl_tinnhanS WHERE ngay_nhan = ''
        //   3981: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3984: pop
        //   3985: aload #23
        //   3987: aload #7
        //   3989: iconst_1
        //   3990: invokeinterface getString : (I)Ljava/lang/String;
        //   3995: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3998: pop
        //   3999: aload #23
        //   4001: aload #18
        //   4003: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4006: pop
        //   4007: aload #23
        //   4009: aload #21
        //   4011: iconst_4
        //   4012: invokeinterface getString : (I)Ljava/lang/String;
        //   4017: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4020: pop
        //   4021: aload #23
        //   4023: ldc_w '' AND so_tin_nhan = '
        //   4026: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4029: pop
        //   4030: aload #23
        //   4032: iload #11
        //   4034: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   4037: pop
        //   4038: aload #23
        //   4040: ldc_w ' AND type_kh = 2'
        //   4043: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4046: pop
        //   4047: aload_0
        //   4048: aload #23
        //   4050: invokevirtual toString : ()Ljava/lang/String;
        //   4053: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   4056: astore #24
        //   4058: aload #24
        //   4060: invokeinterface moveToFirst : ()Z
        //   4065: pop
        //   4066: new java/lang/StringBuilder
        //   4069: dup
        //   4070: invokespecial <init> : ()V
        //   4073: astore #23
        //   4075: aload #23
        //   4077: ldc_w 'Update tbl_tinnhanS set del_sms = 0 WHERE ngay_nhan = ''
        //   4080: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4083: pop
        //   4084: aload #23
        //   4086: aload #17
        //   4088: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4091: pop
        //   4092: aload #23
        //   4094: aload #18
        //   4096: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4099: pop
        //   4100: aload #23
        //   4102: aload #9
        //   4104: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4107: pop
        //   4108: aload #23
        //   4110: ldc_w '' AND so_tin_nhan = '
        //   4113: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4116: pop
        //   4117: aload #23
        //   4119: iload #8
        //   4121: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   4124: pop
        //   4125: aload_0
        //   4126: aload #23
        //   4128: invokevirtual toString : ()Ljava/lang/String;
        //   4131: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4134: aload_0
        //   4135: aload #24
        //   4137: iconst_0
        //   4138: invokeinterface getInt : (I)I
        //   4143: invokevirtual NhapSoChiTiet : (I)V
        //   4146: aload_0
        //   4147: ldc_w 'Select Om_Xi3 FROM so_Om WHERE id = 13'
        //   4150: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   4153: astore #23
        //   4155: aload #23
        //   4157: invokeinterface moveToFirst : ()Z
        //   4162: pop
        //   4163: aload #23
        //   4165: iconst_0
        //   4166: invokeinterface getInt : (I)I
        //   4171: ifne -> 4475
        //   4174: new java/lang/StringBuilder
        //   4177: dup
        //   4178: invokespecial <init> : ()V
        //   4181: astore #25
        //   4183: aload #25
        //   4185: ldc_w 'Tin '
        //   4188: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4191: pop
        //   4192: aload #25
        //   4194: iload #11
        //   4196: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   4199: pop
        //   4200: aload #25
        //   4202: ldc_w ':\\n'
        //   4205: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4208: pop
        //   4209: aload #25
        //   4211: aload #7
        //   4213: bipush #8
        //   4215: invokeinterface getString : (I)Ljava/lang/String;
        //   4220: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4223: pop
        //   4224: aload #25
        //   4226: invokevirtual toString : ()Ljava/lang/String;
        //   4229: astore #26
        //   4231: aload #24
        //   4233: bipush #6
        //   4235: invokeinterface getString : (I)Ljava/lang/String;
        //   4240: aload #15
        //   4242: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4245: iconst_m1
        //   4246: if_icmple -> 4266
        //   4249: aload_0
        //   4250: aload #21
        //   4252: iconst_4
        //   4253: invokeinterface getString : (I)Ljava/lang/String;
        //   4258: aload #26
        //   4260: invokevirtual SendSMS : (Ljava/lang/String;Ljava/lang/String;)V
        //   4263: goto -> 4472
        //   4266: aload #24
        //   4268: bipush #6
        //   4270: invokeinterface getString : (I)Ljava/lang/String;
        //   4275: aload_3
        //   4276: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4279: iconst_m1
        //   4280: if_icmple -> 4312
        //   4283: new android/os/Handler
        //   4286: dup
        //   4287: invokestatic getMainLooper : ()Landroid/os/Looper;
        //   4290: invokespecial <init> : (Landroid/os/Looper;)V
        //   4293: new tamhoang/ldpro4/data/Database$13
        //   4296: dup
        //   4297: aload_0
        //   4298: aload #21
        //   4300: aload #26
        //   4302: invokespecial <init> : (Ltamhoang/ldpro4/data/Database;Landroid/database/Cursor;Ljava/lang/String;)V
        //   4305: invokevirtual post : (Ljava/lang/Runnable;)Z
        //   4308: pop
        //   4309: goto -> 4472
        //   4312: new tamhoang/ldpro4/NotificationReader
        //   4315: dup
        //   4316: invokespecial <init> : ()V
        //   4319: aload #21
        //   4321: iconst_4
        //   4322: invokeinterface getString : (I)Ljava/lang/String;
        //   4327: aload #26
        //   4329: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   4332: new java/lang/StringBuilder
        //   4335: dup
        //   4336: invokespecial <init> : ()V
        //   4339: astore #24
        //   4341: aload #24
        //   4343: ldc_w 'Insert into Chat_database Values( null,''
        //   4346: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4349: pop
        //   4350: aload #24
        //   4352: aload #5
        //   4354: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4357: pop
        //   4358: aload #24
        //   4360: ldc_w '', ''
        //   4363: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4366: pop
        //   4367: aload #24
        //   4369: aload #6
        //   4371: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4374: pop
        //   4375: aload #24
        //   4377: aload #20
        //   4379: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4382: pop
        //   4383: aload #24
        //   4385: aload #21
        //   4387: iconst_3
        //   4388: invokeinterface getString : (I)Ljava/lang/String;
        //   4393: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4396: pop
        //   4397: aload #24
        //   4399: ldc_w '', ''
        //   4402: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4405: pop
        //   4406: aload #24
        //   4408: aload #21
        //   4410: iconst_4
        //   4411: invokeinterface getString : (I)Ljava/lang/String;
        //   4416: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4419: pop
        //   4420: aload #19
        //   4422: astore #25
        //   4424: aload #24
        //   4426: aload #25
        //   4428: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4431: pop
        //   4432: aload #24
        //   4434: aload_2
        //   4435: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4438: pop
        //   4439: aload #24
        //   4441: aload #25
        //   4443: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4446: pop
        //   4447: aload #24
        //   4449: aload #26
        //   4451: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4454: pop
        //   4455: aload #24
        //   4457: aload #4
        //   4459: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4462: pop
        //   4463: aload_0
        //   4464: aload #24
        //   4466: invokevirtual toString : ()Ljava/lang/String;
        //   4469: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4472: goto -> 4773
        //   4475: aload #19
        //   4477: astore #25
        //   4479: new java/lang/StringBuilder
        //   4482: dup
        //   4483: invokespecial <init> : ()V
        //   4486: astore #26
        //   4488: aload #26
        //   4490: ldc_w 'Tin '
        //   4493: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4496: pop
        //   4497: aload #26
        //   4499: iload #11
        //   4501: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   4504: pop
        //   4505: aload #26
        //   4507: ldc_w ':\\n'
        //   4510: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4513: pop
        //   4514: aload #26
        //   4516: aload #7
        //   4518: bipush #9
        //   4520: invokeinterface getString : (I)Ljava/lang/String;
        //   4525: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4528: pop
        //   4529: aload #26
        //   4531: invokevirtual toString : ()Ljava/lang/String;
        //   4534: astore #26
        //   4536: aload #24
        //   4538: bipush #6
        //   4540: invokeinterface getString : (I)Ljava/lang/String;
        //   4545: aload #15
        //   4547: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4550: iconst_m1
        //   4551: if_icmple -> 4571
        //   4554: aload_0
        //   4555: aload #21
        //   4557: iconst_4
        //   4558: invokeinterface getString : (I)Ljava/lang/String;
        //   4563: aload #26
        //   4565: invokevirtual SendSMS : (Ljava/lang/String;Ljava/lang/String;)V
        //   4568: goto -> 4773
        //   4571: aload #24
        //   4573: bipush #6
        //   4575: invokeinterface getString : (I)Ljava/lang/String;
        //   4580: aload_3
        //   4581: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4584: iconst_m1
        //   4585: if_icmple -> 4617
        //   4588: new android/os/Handler
        //   4591: dup
        //   4592: invokestatic getMainLooper : ()Landroid/os/Looper;
        //   4595: invokespecial <init> : (Landroid/os/Looper;)V
        //   4598: new tamhoang/ldpro4/data/Database$14
        //   4601: dup
        //   4602: aload_0
        //   4603: aload #21
        //   4605: aload #26
        //   4607: invokespecial <init> : (Ltamhoang/ldpro4/data/Database;Landroid/database/Cursor;Ljava/lang/String;)V
        //   4610: invokevirtual post : (Ljava/lang/Runnable;)Z
        //   4613: pop
        //   4614: goto -> 4773
        //   4617: new tamhoang/ldpro4/NotificationReader
        //   4620: dup
        //   4621: invokespecial <init> : ()V
        //   4624: aload #21
        //   4626: iconst_4
        //   4627: invokeinterface getString : (I)Ljava/lang/String;
        //   4632: aload #26
        //   4634: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   4637: new java/lang/StringBuilder
        //   4640: dup
        //   4641: invokespecial <init> : ()V
        //   4644: astore #24
        //   4646: aload #24
        //   4648: ldc_w 'Insert into Chat_database Values( null,''
        //   4651: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4654: pop
        //   4655: aload #24
        //   4657: aload #5
        //   4659: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4662: pop
        //   4663: aload #24
        //   4665: ldc_w '', ''
        //   4668: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4671: pop
        //   4672: aload #24
        //   4674: aload #6
        //   4676: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4679: pop
        //   4680: aload #24
        //   4682: aload #20
        //   4684: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4687: pop
        //   4688: aload #24
        //   4690: aload #21
        //   4692: iconst_3
        //   4693: invokeinterface getString : (I)Ljava/lang/String;
        //   4698: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4701: pop
        //   4702: aload #24
        //   4704: ldc_w '', ''
        //   4707: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4710: pop
        //   4711: aload #24
        //   4713: aload #21
        //   4715: iconst_4
        //   4716: invokeinterface getString : (I)Ljava/lang/String;
        //   4721: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4724: pop
        //   4725: aload #24
        //   4727: aload #25
        //   4729: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4732: pop
        //   4733: aload #24
        //   4735: aload_2
        //   4736: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4739: pop
        //   4740: aload #24
        //   4742: aload #25
        //   4744: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4747: pop
        //   4748: aload #24
        //   4750: aload #26
        //   4752: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4755: pop
        //   4756: aload #24
        //   4758: aload #4
        //   4760: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4763: pop
        //   4764: aload_0
        //   4765: aload #24
        //   4767: invokevirtual toString : ()Ljava/lang/String;
        //   4770: invokevirtual QueryData : (Ljava/lang/String;)V
        //   4773: aload #23
        //   4775: ifnull -> 4795
        //   4778: aload #23
        //   4780: invokeinterface isClosed : ()Z
        //   4785: ifne -> 4795
        //   4788: aload #23
        //   4790: invokeinterface close : ()V
        //   4795: aload #22
        //   4797: ifnull -> 4817
        //   4800: aload #22
        //   4802: invokeinterface isClosed : ()Z
        //   4807: ifne -> 4817
        //   4810: aload #22
        //   4812: invokeinterface close : ()V
        //   4817: goto -> 4824
        //   4820: iload #13
        //   4822: istore #11
        //   4824: iload #8
        //   4826: istore #13
        //   4828: aload #18
        //   4830: astore_2
        //   4831: aload #19
        //   4833: astore #25
        //   4835: aload #7
        //   4837: iconst_3
        //   4838: invokeinterface getInt : (I)I
        //   4843: iconst_1
        //   4844: if_icmpne -> 5723
        //   4847: getstatic tamhoang/ldpro4/MainActivity.jSon_Setting : Lorg/json/JSONObject;
        //   4850: ldc_w 'baotinthieu'
        //   4853: invokevirtual getInt : (Ljava/lang/String;)I
        //   4856: ifle -> 5723
        //   4859: new java/lang/StringBuilder
        //   4862: astore #19
        //   4864: aload #19
        //   4866: invokespecial <init> : ()V
        //   4869: aload #19
        //   4871: ldc_w 'Select * From tbl_tinnhanS WHERE ngay_nhan = ''
        //   4874: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4877: pop
        //   4878: aload #17
        //   4880: astore #18
        //   4882: aload #19
        //   4884: aload #18
        //   4886: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4889: pop
        //   4890: aload #19
        //   4892: aload_2
        //   4893: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4896: pop
        //   4897: aload #9
        //   4899: astore #21
        //   4901: aload #19
        //   4903: aload #21
        //   4905: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4908: pop
        //   4909: aload #19
        //   4911: ldc_w '' AND type_kh = 1 ORDER BY so_tin_nhan'
        //   4914: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4917: pop
        //   4918: aload_0
        //   4919: aload #19
        //   4921: invokevirtual toString : ()Ljava/lang/String;
        //   4924: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   4927: astore #17
        //   4929: new org/json/JSONObject
        //   4932: astore #19
        //   4934: aload #19
        //   4936: invokespecial <init> : ()V
        //   4939: aload_2
        //   4940: astore #23
        //   4942: iconst_0
        //   4943: istore #8
        //   4945: aload #19
        //   4947: astore_2
        //   4948: aload #4
        //   4950: astore #19
        //   4952: aload #17
        //   4954: invokeinterface moveToNext : ()Z
        //   4959: istore #27
        //   4961: iload #27
        //   4963: ifeq -> 5065
        //   4966: new java/lang/StringBuilder
        //   4969: astore #4
        //   4971: aload #4
        //   4973: invokespecial <init> : ()V
        //   4976: aload #4
        //   4978: aload #17
        //   4980: bipush #7
        //   4982: invokeinterface getString : (I)Ljava/lang/String;
        //   4987: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4990: pop
        //   4991: aload #4
        //   4993: ldc_w '-'
        //   4996: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4999: pop
        //   5000: aload #4
        //   5002: invokevirtual toString : ()Ljava/lang/String;
        //   5005: astore #22
        //   5007: aload #17
        //   5009: bipush #7
        //   5011: invokeinterface getString : (I)Ljava/lang/String;
        //   5016: astore #4
        //   5018: aload_2
        //   5019: aload #22
        //   5021: aload #4
        //   5023: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   5026: pop
        //   5027: aload #17
        //   5029: bipush #7
        //   5031: invokeinterface getInt : (I)I
        //   5036: istore #8
        //   5038: goto -> 4952
        //   5041: astore #4
        //   5043: aload #17
        //   5045: astore_2
        //   5046: goto -> 5626
        //   5049: astore #4
        //   5051: aload #17
        //   5053: astore_2
        //   5054: goto -> 5626
        //   5057: astore #4
        //   5059: aload #17
        //   5061: astore_2
        //   5062: goto -> 5626
        //   5065: ldc ''
        //   5067: astore #4
        //   5069: iconst_1
        //   5070: istore #12
        //   5072: iload #12
        //   5074: iload #8
        //   5076: if_icmpge -> 5190
        //   5079: new java/lang/StringBuilder
        //   5082: astore #24
        //   5084: aload #24
        //   5086: invokespecial <init> : ()V
        //   5089: aload #24
        //   5091: iload #12
        //   5093: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   5096: pop
        //   5097: aload #24
        //   5099: ldc_w '-'
        //   5102: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5105: pop
        //   5106: aload #4
        //   5108: astore #22
        //   5110: aload_2
        //   5111: aload #24
        //   5113: invokevirtual toString : ()Ljava/lang/String;
        //   5116: invokevirtual has : (Ljava/lang/String;)Z
        //   5119: ifne -> 5164
        //   5122: new java/lang/StringBuilder
        //   5125: astore #22
        //   5127: aload #22
        //   5129: invokespecial <init> : ()V
        //   5132: aload #22
        //   5134: aload #4
        //   5136: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5139: pop
        //   5140: aload #22
        //   5142: iload #12
        //   5144: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   5147: pop
        //   5148: aload #22
        //   5150: ldc_w ','
        //   5153: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5156: pop
        //   5157: aload #22
        //   5159: invokevirtual toString : ()Ljava/lang/String;
        //   5162: astore #22
        //   5164: iinc #12, 1
        //   5167: aload #22
        //   5169: astore #4
        //   5171: goto -> 5072
        //   5174: aload #17
        //   5176: astore_2
        //   5177: astore #4
        //   5179: goto -> 5626
        //   5182: astore #4
        //   5184: aload #17
        //   5186: astore_2
        //   5187: goto -> 5626
        //   5190: aload #17
        //   5192: astore_2
        //   5193: aload #4
        //   5195: invokevirtual length : ()I
        //   5198: ifle -> 5610
        //   5201: new java/lang/StringBuilder
        //   5204: astore #22
        //   5206: aload #22
        //   5208: invokespecial <init> : ()V
        //   5211: aload #22
        //   5213: ldc_w 'Thitin '
        //   5216: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5219: pop
        //   5220: aload #22
        //   5222: aload #4
        //   5224: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5227: pop
        //   5228: aload #22
        //   5230: invokevirtual toString : ()Ljava/lang/String;
        //   5233: astore #4
        //   5235: aload #7
        //   5237: bipush #6
        //   5239: invokeinterface getString : (I)Ljava/lang/String;
        //   5244: aload #15
        //   5246: invokevirtual indexOf : (Ljava/lang/String;)I
        //   5249: istore #8
        //   5251: iload #8
        //   5253: iconst_m1
        //   5254: if_icmple -> 5268
        //   5257: aload_0
        //   5258: aload #21
        //   5260: aload #4
        //   5262: invokevirtual SendSMS : (Ljava/lang/String;Ljava/lang/String;)V
        //   5265: goto -> 5610
        //   5268: aload #7
        //   5270: bipush #6
        //   5272: invokeinterface getString : (I)Ljava/lang/String;
        //   5277: aload_3
        //   5278: invokevirtual indexOf : (Ljava/lang/String;)I
        //   5281: istore #8
        //   5283: iload #8
        //   5285: iconst_m1
        //   5286: if_icmple -> 5328
        //   5289: new android/os/Handler
        //   5292: astore #22
        //   5294: aload #22
        //   5296: invokestatic getMainLooper : ()Landroid/os/Looper;
        //   5299: invokespecial <init> : (Landroid/os/Looper;)V
        //   5302: new tamhoang/ldpro4/data/Database$15
        //   5305: astore #16
        //   5307: aload #16
        //   5309: aload_0
        //   5310: aload #21
        //   5312: aload #4
        //   5314: invokespecial <init> : (Ltamhoang/ldpro4/data/Database;Ljava/lang/String;Ljava/lang/String;)V
        //   5317: aload #22
        //   5319: aload #16
        //   5321: invokevirtual post : (Ljava/lang/Runnable;)Z
        //   5324: pop
        //   5325: goto -> 5610
        //   5328: new org/json/JSONObject
        //   5331: astore #17
        //   5333: aload #17
        //   5335: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   5338: aload #21
        //   5340: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   5343: invokespecial <init> : (Ljava/lang/String;)V
        //   5346: aload #17
        //   5348: aload #16
        //   5350: invokevirtual getInt : (Ljava/lang/String;)I
        //   5353: istore #8
        //   5355: iload #8
        //   5357: iconst_3
        //   5358: if_icmple -> 5394
        //   5361: new tamhoang/ldpro4/NotificationReader
        //   5364: astore #16
        //   5366: aload #16
        //   5368: invokespecial <init> : ()V
        //   5371: aload #16
        //   5373: aload #7
        //   5375: iconst_5
        //   5376: invokeinterface getString : (I)Ljava/lang/String;
        //   5381: aload #4
        //   5383: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   5386: goto -> 5416
        //   5389: astore #16
        //   5391: goto -> 5426
        //   5394: aload #17
        //   5396: aload #4
        //   5398: ldc_w 'OK'
        //   5401: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   5404: pop
        //   5405: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   5408: aload #21
        //   5410: aload #17
        //   5412: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   5415: pop
        //   5416: goto -> 5451
        //   5419: astore #16
        //   5421: goto -> 5426
        //   5424: astore #16
        //   5426: new tamhoang/ldpro4/NotificationReader
        //   5429: astore #16
        //   5431: aload #16
        //   5433: invokespecial <init> : ()V
        //   5436: aload #16
        //   5438: aload #7
        //   5440: iconst_5
        //   5441: invokeinterface getString : (I)Ljava/lang/String;
        //   5446: aload #4
        //   5448: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   5451: new java/lang/StringBuilder
        //   5454: astore #16
        //   5456: aload #16
        //   5458: invokespecial <init> : ()V
        //   5461: aload #16
        //   5463: ldc_w 'Insert into Chat_database Values( null,''
        //   5466: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5469: pop
        //   5470: aload #16
        //   5472: aload #5
        //   5474: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5477: pop
        //   5478: aload #16
        //   5480: ldc_w '', ''
        //   5483: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5486: pop
        //   5487: aload #16
        //   5489: aload #6
        //   5491: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5494: pop
        //   5495: aload #16
        //   5497: aload #20
        //   5499: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5502: pop
        //   5503: aload #16
        //   5505: aload #7
        //   5507: iconst_4
        //   5508: invokeinterface getString : (I)Ljava/lang/String;
        //   5513: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5516: pop
        //   5517: aload #16
        //   5519: ldc_w '', ''
        //   5522: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5525: pop
        //   5526: aload #16
        //   5528: aload #7
        //   5530: iconst_5
        //   5531: invokeinterface getString : (I)Ljava/lang/String;
        //   5536: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5539: pop
        //   5540: aload #16
        //   5542: ldc_w '', ''
        //   5545: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5548: pop
        //   5549: aload #16
        //   5551: aload #7
        //   5553: bipush #6
        //   5555: invokeinterface getString : (I)Ljava/lang/String;
        //   5560: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5563: pop
        //   5564: aload #16
        //   5566: aload #25
        //   5568: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5571: pop
        //   5572: aload #16
        //   5574: aload #4
        //   5576: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5579: pop
        //   5580: aload #16
        //   5582: aload #19
        //   5584: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5587: pop
        //   5588: aload_0
        //   5589: aload #16
        //   5591: invokevirtual toString : ()Ljava/lang/String;
        //   5594: invokevirtual QueryData : (Ljava/lang/String;)V
        //   5597: goto -> 5610
        //   5600: astore #4
        //   5602: goto -> 5626
        //   5605: astore #4
        //   5607: goto -> 5626
        //   5610: aload_2
        //   5611: astore #22
        //   5613: goto -> 5634
        //   5616: astore #4
        //   5618: goto -> 5626
        //   5621: astore #4
        //   5623: aload #17
        //   5625: astore_2
        //   5626: aload #4
        //   5628: invokevirtual printStackTrace : ()V
        //   5631: aload_2
        //   5632: astore #22
        //   5634: aload #18
        //   5636: astore #16
        //   5638: aload #19
        //   5640: astore #21
        //   5642: aload #21
        //   5644: astore #4
        //   5646: aload #16
        //   5648: astore #17
        //   5650: aload #23
        //   5652: astore_2
        //   5653: aload #22
        //   5655: ifnull -> 5723
        //   5658: aload #22
        //   5660: invokeinterface close : ()V
        //   5665: aload #21
        //   5667: astore #4
        //   5669: aload #16
        //   5671: astore #17
        //   5673: aload #23
        //   5675: astore_2
        //   5676: goto -> 5723
        //   5679: aload #18
        //   5681: astore #17
        //   5683: aload #19
        //   5685: astore #4
        //   5687: astore #16
        //   5689: aload #23
        //   5691: astore_2
        //   5692: goto -> 5746
        //   5695: astore #16
        //   5697: aload #18
        //   5699: astore #17
        //   5701: goto -> 5746
        //   5704: astore #16
        //   5706: aload #18
        //   5708: astore #17
        //   5710: goto -> 5746
        //   5713: astore #16
        //   5715: goto -> 5720
        //   5718: astore #16
        //   5720: goto -> 5746
        //   5723: aload #9
        //   5725: astore #19
        //   5727: aload #15
        //   5729: astore #16
        //   5731: aload #4
        //   5733: astore #15
        //   5735: aload_3
        //   5736: astore #9
        //   5738: aload #19
        //   5740: astore_3
        //   5741: goto -> 5819
        //   5744: astore #16
        //   5746: aload #9
        //   5748: astore #19
        //   5750: aload #16
        //   5752: invokevirtual printStackTrace : ()V
        //   5755: aload #15
        //   5757: astore #16
        //   5759: aload #4
        //   5761: astore #15
        //   5763: aload_3
        //   5764: astore #9
        //   5766: aload #19
        //   5768: astore_3
        //   5769: goto -> 5819
        //   5772: ldc_w '',''
        //   5775: astore #25
        //   5777: aload_2
        //   5778: astore #17
        //   5780: ldc_w '' AND so_dienthoai = ''
        //   5783: astore_2
        //   5784: ldc_w 'sms'
        //   5787: astore #16
        //   5789: ldc_w 'TL'
        //   5792: astore #4
        //   5794: ldc_w '',1)'
        //   5797: astore #15
        //   5799: aload #9
        //   5801: astore_3
        //   5802: ldc_w '', 2, ''
        //   5805: astore #20
        //   5807: iload #8
        //   5809: istore #13
        //   5811: iload #12
        //   5813: istore #11
        //   5815: aload #4
        //   5817: astore #9
        //   5819: ldc_w '' AND so_tin_nhan = '
        //   5822: astore #22
        //   5824: new java/lang/StringBuilder
        //   5827: dup
        //   5828: invokespecial <init> : ()V
        //   5831: astore #4
        //   5833: aload #4
        //   5835: ldc_w 'Select * From tbl_tinnhanS WHERE ngay_nhan = ''
        //   5838: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5841: pop
        //   5842: aload #4
        //   5844: aload #17
        //   5846: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5849: pop
        //   5850: aload #4
        //   5852: aload_2
        //   5853: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5856: pop
        //   5857: aload #4
        //   5859: aload_3
        //   5860: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5863: pop
        //   5864: aload #4
        //   5866: aload #22
        //   5868: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5871: pop
        //   5872: aload #4
        //   5874: iload #13
        //   5876: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   5879: pop
        //   5880: aload_0
        //   5881: aload #4
        //   5883: invokevirtual toString : ()Ljava/lang/String;
        //   5886: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   5889: astore #18
        //   5891: aload #18
        //   5893: invokeinterface moveToFirst : ()Z
        //   5898: pop
        //   5899: new java/lang/StringBuilder
        //   5902: dup
        //   5903: invokespecial <init> : ()V
        //   5906: astore #4
        //   5908: aload #4
        //   5910: ldc_w 'Select * From tbl_chuyenthang WHERE sdt_nhan = ''
        //   5913: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5916: pop
        //   5917: aload #4
        //   5919: aload_3
        //   5920: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5923: pop
        //   5924: aload #4
        //   5926: ldc_w '''
        //   5929: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5932: pop
        //   5933: aload_0
        //   5934: aload #4
        //   5936: invokevirtual toString : ()Ljava/lang/String;
        //   5939: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   5942: astore #23
        //   5944: aload #23
        //   5946: invokeinterface moveToFirst : ()Z
        //   5951: pop
        //   5952: aload_0
        //   5953: ldc_w 'Select Om_Xi3 FROM so_Om WHERE id = 13'
        //   5956: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   5959: astore #19
        //   5961: aload #19
        //   5963: invokeinterface moveToFirst : ()Z
        //   5968: pop
        //   5969: aload #23
        //   5971: invokeinterface getCount : ()I
        //   5976: ifle -> 6891
        //   5979: aload #19
        //   5981: iconst_0
        //   5982: invokeinterface getInt : (I)I
        //   5987: ifne -> 6888
        //   5990: aload #18
        //   5992: bipush #14
        //   5994: invokeinterface getInt : (I)I
        //   5999: iconst_1
        //   6000: if_icmpne -> 6885
        //   6003: new java/lang/StringBuilder
        //   6006: dup
        //   6007: invokespecial <init> : ()V
        //   6010: astore #4
        //   6012: aload #4
        //   6014: ldc_w 'Select max(so_tin_nhan) From tbl_tinnhanS WHERE so_dienthoai = ''
        //   6017: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6020: pop
        //   6021: aload #4
        //   6023: aload #23
        //   6025: iconst_4
        //   6026: invokeinterface getString : (I)Ljava/lang/String;
        //   6031: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6034: pop
        //   6035: aload #4
        //   6037: ldc_w '' AND ngay_nhan = ''
        //   6040: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6043: pop
        //   6044: aload #4
        //   6046: aload #17
        //   6048: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6051: pop
        //   6052: aload #4
        //   6054: ldc_w '''
        //   6057: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6060: pop
        //   6061: aload_0
        //   6062: aload #4
        //   6064: invokevirtual toString : ()Ljava/lang/String;
        //   6067: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   6070: astore #21
        //   6072: aload #21
        //   6074: invokeinterface moveToFirst : ()Z
        //   6079: pop
        //   6080: aload #21
        //   6082: iconst_0
        //   6083: invokeinterface getInt : (I)I
        //   6088: iconst_1
        //   6089: iadd
        //   6090: istore #8
        //   6092: aload #23
        //   6094: iconst_3
        //   6095: invokeinterface getString : (I)Ljava/lang/String;
        //   6100: ldc_w 'ZL'
        //   6103: invokevirtual indexOf : (Ljava/lang/String;)I
        //   6106: iconst_m1
        //   6107: if_icmple -> 6118
        //   6110: ldc_w 'ZL'
        //   6113: astore #4
        //   6115: goto -> 6200
        //   6118: aload #23
        //   6120: iconst_3
        //   6121: invokeinterface getString : (I)Ljava/lang/String;
        //   6126: ldc_w 'VB'
        //   6129: invokevirtual indexOf : (Ljava/lang/String;)I
        //   6132: iconst_m1
        //   6133: if_icmple -> 6144
        //   6136: ldc_w 'VB'
        //   6139: astore #4
        //   6141: goto -> 6200
        //   6144: aload #23
        //   6146: iconst_3
        //   6147: invokeinterface getString : (I)Ljava/lang/String;
        //   6152: ldc_w 'WA'
        //   6155: invokevirtual indexOf : (Ljava/lang/String;)I
        //   6158: iconst_m1
        //   6159: if_icmple -> 6170
        //   6162: ldc_w 'WA'
        //   6165: astore #4
        //   6167: goto -> 6200
        //   6170: aload #23
        //   6172: iconst_3
        //   6173: invokeinterface getString : (I)Ljava/lang/String;
        //   6178: aload #9
        //   6180: invokevirtual indexOf : (Ljava/lang/String;)I
        //   6183: iconst_m1
        //   6184: if_icmple -> 6195
        //   6187: ldc_w 'TL'
        //   6190: astore #4
        //   6192: goto -> 6200
        //   6195: ldc_w 'sms'
        //   6198: astore #4
        //   6200: new java/lang/StringBuilder
        //   6203: dup
        //   6204: invokespecial <init> : ()V
        //   6207: astore #24
        //   6209: aload #24
        //   6211: ldc_w 'Insert Into tbl_tinnhanS values (null, ''
        //   6214: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6217: pop
        //   6218: aload #24
        //   6220: aload #7
        //   6222: iconst_1
        //   6223: invokeinterface getString : (I)Ljava/lang/String;
        //   6228: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6231: pop
        //   6232: aload #24
        //   6234: ldc_w '', ''
        //   6237: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6240: pop
        //   6241: aload #24
        //   6243: aload #7
        //   6245: iconst_2
        //   6246: invokeinterface getString : (I)Ljava/lang/String;
        //   6251: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6254: pop
        //   6255: aload #24
        //   6257: ldc_w '',2, ''
        //   6260: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6263: pop
        //   6264: aload #24
        //   6266: aload #23
        //   6268: iconst_3
        //   6269: invokeinterface getString : (I)Ljava/lang/String;
        //   6274: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6277: pop
        //   6278: aload #24
        //   6280: ldc_w '', ''
        //   6283: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6286: pop
        //   6287: aload #24
        //   6289: aload #23
        //   6291: iconst_4
        //   6292: invokeinterface getString : (I)Ljava/lang/String;
        //   6297: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6300: pop
        //   6301: aload #24
        //   6303: ldc_w '', ''
        //   6306: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6309: pop
        //   6310: aload #24
        //   6312: aload #4
        //   6314: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6317: pop
        //   6318: aload #24
        //   6320: ldc_w '', '
        //   6323: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6326: pop
        //   6327: aload #24
        //   6329: iload #8
        //   6331: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   6334: pop
        //   6335: aload #24
        //   6337: ldc_w ', ''
        //   6340: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6343: pop
        //   6344: aload #24
        //   6346: aload #7
        //   6348: bipush #8
        //   6350: invokeinterface getString : (I)Ljava/lang/String;
        //   6355: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6358: pop
        //   6359: aload #24
        //   6361: ldc_w '', null, ''
        //   6364: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6367: pop
        //   6368: aload #24
        //   6370: aload #7
        //   6372: bipush #10
        //   6374: invokeinterface getString : (I)Ljava/lang/String;
        //   6379: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6382: pop
        //   6383: aload #24
        //   6385: ldc_w '', 'ko',0,0,1, null)'
        //   6388: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6391: pop
        //   6392: aload_0
        //   6393: aload #24
        //   6395: invokevirtual toString : ()Ljava/lang/String;
        //   6398: invokevirtual QueryData : (Ljava/lang/String;)V
        //   6401: aload #4
        //   6403: aload #16
        //   6405: invokevirtual indexOf : (Ljava/lang/String;)I
        //   6408: iconst_m1
        //   6409: if_icmple -> 6486
        //   6412: new java/lang/StringBuilder
        //   6415: dup
        //   6416: invokespecial <init> : ()V
        //   6419: astore #4
        //   6421: aload #4
        //   6423: ldc_w 'Tin '
        //   6426: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6429: pop
        //   6430: aload #4
        //   6432: iload #8
        //   6434: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   6437: pop
        //   6438: aload #4
        //   6440: ldc_w ':\\n'
        //   6443: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6446: pop
        //   6447: aload #4
        //   6449: aload #7
        //   6451: bipush #8
        //   6453: invokeinterface getString : (I)Ljava/lang/String;
        //   6458: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6461: pop
        //   6462: aload #4
        //   6464: invokevirtual toString : ()Ljava/lang/String;
        //   6467: astore #4
        //   6469: aload_0
        //   6470: aload #23
        //   6472: iconst_4
        //   6473: invokeinterface getString : (I)Ljava/lang/String;
        //   6478: aload #4
        //   6480: invokevirtual SendSMS : (Ljava/lang/String;Ljava/lang/String;)V
        //   6483: goto -> 6805
        //   6486: aload #7
        //   6488: bipush #6
        //   6490: invokeinterface getString : (I)Ljava/lang/String;
        //   6495: aload #9
        //   6497: invokevirtual indexOf : (Ljava/lang/String;)I
        //   6500: iconst_m1
        //   6501: if_icmple -> 6590
        //   6504: new java/lang/StringBuilder
        //   6507: dup
        //   6508: invokespecial <init> : ()V
        //   6511: astore #4
        //   6513: aload #4
        //   6515: ldc_w 'Tin '
        //   6518: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6521: pop
        //   6522: aload #4
        //   6524: iload #8
        //   6526: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   6529: pop
        //   6530: aload #4
        //   6532: ldc_w ':\\n'
        //   6535: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6538: pop
        //   6539: aload #4
        //   6541: aload #7
        //   6543: bipush #8
        //   6545: invokeinterface getString : (I)Ljava/lang/String;
        //   6550: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6553: pop
        //   6554: aload #4
        //   6556: invokevirtual toString : ()Ljava/lang/String;
        //   6559: astore #4
        //   6561: new android/os/Handler
        //   6564: dup
        //   6565: invokestatic getMainLooper : ()Landroid/os/Looper;
        //   6568: invokespecial <init> : (Landroid/os/Looper;)V
        //   6571: new tamhoang/ldpro4/data/Database$16
        //   6574: dup
        //   6575: aload_0
        //   6576: aload #23
        //   6578: aload #4
        //   6580: invokespecial <init> : (Ltamhoang/ldpro4/data/Database;Landroid/database/Cursor;Ljava/lang/String;)V
        //   6583: invokevirtual post : (Ljava/lang/Runnable;)Z
        //   6586: pop
        //   6587: goto -> 6805
        //   6590: new java/lang/StringBuilder
        //   6593: dup
        //   6594: invokespecial <init> : ()V
        //   6597: astore #16
        //   6599: aload #16
        //   6601: ldc_w 'Tin '
        //   6604: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6607: pop
        //   6608: aload #16
        //   6610: iload #8
        //   6612: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   6615: pop
        //   6616: aload #16
        //   6618: ldc_w ':\\n'
        //   6621: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6624: pop
        //   6625: aload #16
        //   6627: aload #7
        //   6629: bipush #8
        //   6631: invokeinterface getString : (I)Ljava/lang/String;
        //   6636: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6639: pop
        //   6640: aload #16
        //   6642: invokevirtual toString : ()Ljava/lang/String;
        //   6645: astore #16
        //   6647: new tamhoang/ldpro4/NotificationReader
        //   6650: dup
        //   6651: invokespecial <init> : ()V
        //   6654: aload #23
        //   6656: iconst_4
        //   6657: invokeinterface getString : (I)Ljava/lang/String;
        //   6662: aload #16
        //   6664: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   6667: new java/lang/StringBuilder
        //   6670: dup
        //   6671: invokespecial <init> : ()V
        //   6674: astore #9
        //   6676: aload #9
        //   6678: ldc_w 'Insert into Chat_database Values( null,''
        //   6681: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6684: pop
        //   6685: aload #9
        //   6687: aload #5
        //   6689: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6692: pop
        //   6693: aload #9
        //   6695: ldc_w '', ''
        //   6698: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6701: pop
        //   6702: aload #9
        //   6704: aload #6
        //   6706: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6709: pop
        //   6710: aload #9
        //   6712: aload #20
        //   6714: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6717: pop
        //   6718: aload #9
        //   6720: aload #23
        //   6722: iconst_3
        //   6723: invokeinterface getString : (I)Ljava/lang/String;
        //   6728: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6731: pop
        //   6732: aload #9
        //   6734: ldc_w '', ''
        //   6737: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6740: pop
        //   6741: aload #9
        //   6743: aload #23
        //   6745: iconst_4
        //   6746: invokeinterface getString : (I)Ljava/lang/String;
        //   6751: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6754: pop
        //   6755: aload #9
        //   6757: ldc_w '', ''
        //   6760: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6763: pop
        //   6764: aload #9
        //   6766: aload #4
        //   6768: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6771: pop
        //   6772: aload #9
        //   6774: aload #25
        //   6776: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6779: pop
        //   6780: aload #9
        //   6782: aload #16
        //   6784: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6787: pop
        //   6788: aload #9
        //   6790: aload #15
        //   6792: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6795: pop
        //   6796: aload_0
        //   6797: aload #9
        //   6799: invokevirtual toString : ()Ljava/lang/String;
        //   6802: invokevirtual QueryData : (Ljava/lang/String;)V
        //   6805: new java/lang/StringBuilder
        //   6808: dup
        //   6809: invokespecial <init> : ()V
        //   6812: astore #4
        //   6814: aload #4
        //   6816: ldc_w 'Update tbl_tinnhanS set del_sms = 0 WHERE ngay_nhan = ''
        //   6819: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6822: pop
        //   6823: aload #4
        //   6825: aload #17
        //   6827: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6830: pop
        //   6831: aload #4
        //   6833: aload_2
        //   6834: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6837: pop
        //   6838: aload #4
        //   6840: aload_3
        //   6841: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6844: pop
        //   6845: aload #4
        //   6847: aload #22
        //   6849: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6852: pop
        //   6853: aload #4
        //   6855: iload #13
        //   6857: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   6860: pop
        //   6861: aload_0
        //   6862: aload #4
        //   6864: invokevirtual toString : ()Ljava/lang/String;
        //   6867: invokevirtual QueryData : (Ljava/lang/String;)V
        //   6870: aload #21
        //   6872: ifnull -> 6891
        //   6875: aload #21
        //   6877: invokeinterface close : ()V
        //   6882: goto -> 6891
        //   6885: goto -> 6891
        //   6888: goto -> 6891
        //   6891: aload_0
        //   6892: iload_1
        //   6893: invokevirtual TralaiSO : (I)V
        //   6896: aload #18
        //   6898: ifnull -> 6908
        //   6901: aload #18
        //   6903: invokeinterface close : ()V
        //   6908: aload #19
        //   6910: ifnull -> 6920
        //   6913: aload #19
        //   6915: invokeinterface close : ()V
        //   6920: aload #7
        //   6922: ifnull -> 6932
        //   6925: aload #7
        //   6927: invokeinterface close : ()V
        //   6932: aload #10
        //   6934: ifnull -> 6944
        //   6937: aload #10
        //   6939: invokeinterface close : ()V
        //   6944: aload #23
        //   6946: ifnull -> 6956
        //   6949: aload #23
        //   6951: invokeinterface close : ()V
        //   6956: return
        // Exception table:
        //   from	to	target	type
        //   208	213	266	org/json/JSONException
        //   213	258	261	org/json/JSONException
        //   436	461	523	java/lang/Exception
        //   467	475	492	java/lang/Exception
        //   475	489	519	java/lang/Exception
        //   496	516	519	java/lang/Exception
        //   869	887	963	java/lang/Exception
        //   887	895	959	java/lang/Exception
        //   901	909	926	java/lang/Exception
        //   909	923	955	java/lang/Exception
        //   930	952	955	java/lang/Exception
        //   1380	1398	1490	java/lang/Exception
        //   1398	1408	1480	java/lang/Exception
        //   1419	1429	1446	java/lang/Exception
        //   1429	1443	1475	java/lang/Exception
        //   1451	1472	1475	java/lang/Exception
        //   1803	1821	1913	java/lang/Exception
        //   1821	1831	1903	java/lang/Exception
        //   1842	1852	1869	java/lang/Exception
        //   1852	1866	1898	java/lang/Exception
        //   1874	1895	1898	java/lang/Exception
        //   2266	2282	2371	java/lang/Exception
        //   2282	2291	2361	java/lang/Exception
        //   2302	2310	2327	java/lang/Exception
        //   2310	2324	2357	java/lang/Exception
        //   2331	2351	2357	java/lang/Exception
        //   2722	2747	2809	java/lang/Exception
        //   2753	2761	2778	java/lang/Exception
        //   2761	2775	2805	java/lang/Exception
        //   2782	2802	2805	java/lang/Exception
        //   3162	3180	3249	java/lang/Exception
        //   3180	3201	3244	java/lang/Exception
        //   3201	3216	3249	java/lang/Exception
        //   3219	3241	3249	java/lang/Exception
        //   4835	4878	5744	org/json/JSONException
        //   4882	4890	5718	org/json/JSONException
        //   4890	4897	5713	org/json/JSONException
        //   4901	4909	5704	org/json/JSONException
        //   4909	4939	5695	org/json/JSONException
        //   4952	4961	5621	org/json/JSONException
        //   4966	4976	5057	org/json/JSONException
        //   4976	5018	5049	org/json/JSONException
        //   5018	5038	5041	org/json/JSONException
        //   5079	5097	5182	org/json/JSONException
        //   5097	5106	5174	org/json/JSONException
        //   5110	5164	5174	org/json/JSONException
        //   5193	5251	5616	org/json/JSONException
        //   5257	5265	5174	org/json/JSONException
        //   5268	5283	5616	org/json/JSONException
        //   5289	5325	5174	org/json/JSONException
        //   5328	5355	5424	java/lang/Exception
        //   5328	5355	5616	org/json/JSONException
        //   5361	5371	5389	java/lang/Exception
        //   5361	5371	5616	org/json/JSONException
        //   5371	5386	5419	java/lang/Exception
        //   5371	5386	5605	org/json/JSONException
        //   5394	5416	5419	java/lang/Exception
        //   5394	5416	5605	org/json/JSONException
        //   5426	5451	5605	org/json/JSONException
        //   5451	5580	5605	org/json/JSONException
        //   5580	5597	5600	org/json/JSONException
        //   5626	5631	5679	org/json/JSONException
        //   5658	5665	5679	org/json/JSONException
        }

public void LayDanhsachKH() {
        MainActivity.DSkhachhang = new ArrayList();
        Cursor cursor = GetData("Select * From tbl_kh_new WHERE type_kh <> 2");
        if (cursor != null) {
        while (cursor.moveToNext())
        MainActivity.DSkhachhang.add(cursor.getString(1));
        if (cursor != null && !cursor.isClosed())
        cursor.close();
        }
        }

public void List_Khach_Hang() {
        QueryData("CREATE TABLE IF NOT EXISTS tbl_kh_new (ten_kh VARCHAR(30) PRIMARY KEY,sdt VARCHAR(15),use_app Varchar(10), type_kh INTEGER default 0, type_pt Integer default 0, tbl_MB TEXT, tbl_XS TEXT)");
        QueryData("Delete From tbl_kh_new Where substr(sdt,0,3) = 'TL'");
        }

public void NhanTinNhan(Integer paramInteger, int paramInt) throws JSONException {
        // Byte code:
        //   0: aload_0
        //   1: astore_3
        //   2: aload_3
        //   3: sipush #1000
        //   6: bipush #6
        //   8: multianewarray[[Ljava/lang/String; 2
        //   12: putfield mang : [[Ljava/lang/String;
        //   15: new java/lang/StringBuilder
        //   18: dup
        //   19: invokespecial <init> : ()V
        //   22: astore #4
        //   24: aload #4
        //   26: ldc_w 'Select nd_phantich, ten_kh, ok_tn, ngay_nhan From tbl_tinnhanS WHERE id = '
        //   29: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   32: pop
        //   33: aload #4
        //   35: aload_1
        //   36: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   39: pop
        //   40: aload_3
        //   41: aload #4
        //   43: invokevirtual toString : ()Ljava/lang/String;
        //   46: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   49: astore #5
        //   51: aload #5
        //   53: invokeinterface moveToFirst : ()Z
        //   58: pop
        //   59: aload #5
        //   61: iconst_0
        //   62: invokeinterface getString : (I)Ljava/lang/String;
        //   67: ldc 'ldpro'
        //   69: ldc ''
        //   71: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   74: ldc_w '</font>'
        //   77: ldc ''
        //   79: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   82: astore #4
        //   84: iconst_0
        //   85: istore #6
        //   87: ldc_w ','
        //   90: astore #7
        //   92: aload #4
        //   94: ldc_w '-'
        //   97: ldc_w ','
        //   100: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   103: astore #4
        //   105: aconst_null
        //   106: astore #8
        //   108: aload #4
        //   110: ldc_w '/'
        //   113: ldc_w ';'
        //   116: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   119: astore #4
        //   121: ldc ' '
        //   123: astore #9
        //   125: aload #4
        //   127: ldc_w '\\n'
        //   130: ldc ' '
        //   132: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   135: astore #4
        //   137: iconst_0
        //   138: istore #10
        //   140: aload #4
        //   142: ldc_w '\.'
        //   145: ldc_w ','
        //   148: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   151: ldc_w 'x '
        //   154: ldc_w ' x '
        //   157: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   160: astore #4
        //   162: ldc_w 'bc'
        //   165: astore #11
        //   167: aload #4
        //   169: ldc_w 'ba bc'
        //   172: ldc_w 'bc'
        //   175: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   178: astore #4
        //   180: aconst_null
        //   181: astore #12
        //   183: aload #4
        //   185: ldc_w ';,'
        //   188: ldc_w ';'
        //   191: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   194: ldc_w '; ,'
        //   197: ldc_w ';'
        //   200: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   203: astore #4
        //   205: ldc 'lo'
        //   207: astore #13
        //   209: aload #4
        //   211: ldc_w '; lo'
        //   214: ldc 'lo'
        //   216: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   219: ldc_w ';lo'
        //   222: ldc 'lo'
        //   224: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   227: ldc_w '; de'
        //   230: ldc 'de'
        //   232: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   235: ldc_w ';de'
        //   238: ldc 'de'
        //   240: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   243: astore #4
        //   245: ldc_w 'xi'
        //   248: astore #14
        //   250: aload #4
        //   252: ldc_w '; xi'
        //   255: ldc_w 'xi'
        //   258: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   261: ldc_w ';xi'
        //   264: ldc_w 'xi'
        //   267: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   270: ldc_w '; bc'
        //   273: ldc_w 'bc'
        //   276: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   279: ldc_w ';bc'
        //   282: ldc_w 'bc'
        //   285: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   288: astore #4
        //   290: ldc_w 'xq'
        //   293: astore #15
        //   295: aload #4
        //   297: ldc_w '; xq'
        //   300: ldc_w 'xq'
        //   303: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   306: ldc_w ';xq'
        //   309: ldc_w 'xq'
        //   312: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   315: astore #4
        //   317: ldc_w '\\n'
        //   320: astore #16
        //   322: ldc 'xn'
        //   324: astore #17
        //   326: aload #4
        //   328: ldc_w '; xn'
        //   331: ldc 'xn'
        //   333: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   336: ldc_w ';xn'
        //   339: ldc 'xn'
        //   341: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   344: ldc_w 'bo'
        //   347: ldc_w ' bo'
        //   350: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   353: ldc_w 'duoi'
        //   356: ldc_w 'dit'
        //   359: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   362: ldc_w 'dit 10'
        //   365: ldc_w 'duoi 10'
        //   368: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   371: ldc_w 'tong dit'
        //   374: ldc_w 'tong <'
        //   377: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   380: astore #4
        //   382: new java/lang/StringBuilder
        //   385: dup
        //   386: invokespecial <init> : ()V
        //   389: astore #18
        //   391: aload #18
        //   393: ldc_w 'Select * From tbl_kh_new Where ten_kh = ''
        //   396: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   399: pop
        //   400: ldc_w '</font>'
        //   403: astore #19
        //   405: aload #18
        //   407: aload #5
        //   409: iconst_1
        //   410: invokeinterface getString : (I)Ljava/lang/String;
        //   415: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   418: pop
        //   419: aload #18
        //   421: ldc_w '''
        //   424: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   427: pop
        //   428: aload_3
        //   429: aload #18
        //   431: invokevirtual toString : ()Ljava/lang/String;
        //   434: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   437: astore #20
        //   439: aload #20
        //   441: invokeinterface moveToFirst : ()Z
        //   446: pop
        //   447: ldc 'ldpro'
        //   449: astore #21
        //   451: new org/json/JSONObject
        //   454: dup
        //   455: aload #20
        //   457: iconst_5
        //   458: invokeinterface getString : (I)Ljava/lang/String;
        //   463: invokespecial <init> : (Ljava/lang/String;)V
        //   466: astore #18
        //   468: aload_3
        //   469: aload #18
        //   471: putfield json : Lorg/json/JSONObject;
        //   474: aload_3
        //   475: aload #18
        //   477: ldc_w 'caidat_tg'
        //   480: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   483: putfield caidat_tg : Lorg/json/JSONObject;
        //   486: aload #4
        //   488: invokestatic Xuly_DauTN : (Ljava/lang/String;)Ljava/lang/String;
        //   491: astore #4
        //   493: aload #4
        //   495: invokevirtual length : ()I
        //   498: istore #22
        //   500: ldc 'Khhi'
        //   502: astore #23
        //   504: iload #22
        //   506: iconst_3
        //   507: if_icmpge -> 545
        //   510: new java/lang/StringBuilder
        //   513: dup
        //   514: invokespecial <init> : ()V
        //   517: astore #18
        //   519: aload #18
        //   521: ldc 'Khhi'
        //   523: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   526: pop
        //   527: aload #18
        //   529: aload #4
        //   531: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   534: pop
        //   535: aload #18
        //   537: invokevirtual toString : ()Ljava/lang/String;
        //   540: astore #24
        //   542: goto -> 549
        //   545: ldc ''
        //   547: astore #24
        //   549: aload #4
        //   551: ldc_w 'tin'
        //   554: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   557: istore #25
        //   559: ldc_w 't'
        //   562: astore #26
        //   564: iload #25
        //   566: ifeq -> 581
        //   569: aload #4
        //   571: ldc_w 'tin'
        //   574: ldc_w 't'
        //   577: invokevirtual replaceFirst : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   580: pop
        //   581: aload #4
        //   583: invokestatic fixTinNhan : (Ljava/lang/String;)Ljava/lang/String;
        //   586: astore #4
        //   588: aload_3
        //   589: getfield caidat_tg : Lorg/json/JSONObject;
        //   592: astore #27
        //   594: aload #4
        //   596: astore #18
        //   598: aload #4
        //   600: astore #28
        //   602: aload #27
        //   604: ldc_w 'khach_de'
        //   607: invokevirtual getInt : (Ljava/lang/String;)I
        //   610: iconst_1
        //   611: if_icmpne -> 644
        //   614: aload #4
        //   616: astore #28
        //   618: aload #4
        //   620: ldc 'de '
        //   622: ldc 'det'
        //   624: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   627: astore #4
        //   629: aload #4
        //   631: astore #28
        //   633: aload #4
        //   635: ldc 'deb'
        //   637: ldc 'det'
        //   639: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   642: astore #18
        //   644: aload #18
        //   646: astore #28
        //   648: goto -> 662
        //   651: astore #4
        //   653: goto -> 662
        //   656: astore #18
        //   658: aload #4
        //   660: astore #28
        //   662: aload #28
        //   664: invokevirtual trim : ()Ljava/lang/String;
        //   667: ldc_w 't'
        //   670: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   673: ifeq -> 1657
        //   676: getstatic tamhoang/ldpro4/MainActivity.jSon_Setting : Lorg/json/JSONObject;
        //   679: ldc_w 'baotinthieu'
        //   682: invokevirtual getInt : (Ljava/lang/String;)I
        //   685: ifle -> 1657
        //   688: ldc ''
        //   690: astore #4
        //   692: new java/lang/StringBuilder
        //   695: dup
        //   696: invokespecial <init> : ()V
        //   699: astore #18
        //   701: aload #18
        //   703: aload #28
        //   705: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   708: pop
        //   709: aload #18
        //   711: ldc ' '
        //   713: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   716: pop
        //   717: aload #18
        //   719: invokevirtual toString : ()Ljava/lang/String;
        //   722: astore #27
        //   724: iconst_m1
        //   725: istore #22
        //   727: aload #17
        //   729: astore #18
        //   731: aload #15
        //   733: astore #28
        //   735: aload #27
        //   737: astore #15
        //   739: aload #15
        //   741: astore #29
        //   743: aload #4
        //   745: astore #27
        //   747: aload #29
        //   749: ldc_w 'tin'
        //   752: iload #22
        //   754: iconst_1
        //   755: iadd
        //   756: invokevirtual indexOf : (Ljava/lang/String;I)I
        //   759: istore #30
        //   761: iload #30
        //   763: istore #22
        //   765: iload #30
        //   767: iconst_m1
        //   768: if_icmpeq -> 1006
        //   771: iload #22
        //   773: iconst_5
        //   774: iadd
        //   775: istore #30
        //   777: iload #30
        //   779: iload #22
        //   781: bipush #10
        //   783: iadd
        //   784: if_icmpge -> 821
        //   787: aload #4
        //   789: astore #27
        //   791: aload #29
        //   793: iload #22
        //   795: iconst_4
        //   796: iadd
        //   797: iload #30
        //   799: invokevirtual substring : (II)Ljava/lang/String;
        //   802: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   805: istore #25
        //   807: iload #25
        //   809: ifne -> 815
        //   812: goto -> 821
        //   815: iinc #30, 1
        //   818: goto -> 777
        //   821: aload #18
        //   823: astore #17
        //   825: iload #30
        //   827: iload #22
        //   829: isub
        //   830: iconst_5
        //   831: if_icmple -> 995
        //   834: aload #4
        //   836: astore #31
        //   838: aload #4
        //   840: astore #27
        //   842: aload #29
        //   844: iconst_0
        //   845: iload #30
        //   847: invokevirtual substring : (II)Ljava/lang/String;
        //   850: ldc_w 'tin'
        //   853: ldc ''
        //   855: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   858: invokevirtual trim : ()Ljava/lang/String;
        //   861: astore #32
        //   863: aload #4
        //   865: astore #15
        //   867: aload #4
        //   869: astore #31
        //   871: aload #4
        //   873: astore #27
        //   875: aload #32
        //   877: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   880: ifeq -> 887
        //   883: aload #32
        //   885: astore #15
        //   887: aload #15
        //   889: astore #31
        //   891: aload #15
        //   893: astore #27
        //   895: new java/lang/StringBuilder
        //   898: astore #4
        //   900: aload #15
        //   902: astore #31
        //   904: aload #15
        //   906: astore #27
        //   908: aload #4
        //   910: invokespecial <init> : ()V
        //   913: aload #15
        //   915: astore #31
        //   917: aload #15
        //   919: astore #27
        //   921: aload #4
        //   923: aload #29
        //   925: iconst_0
        //   926: iload #22
        //   928: invokevirtual substring : (II)Ljava/lang/String;
        //   931: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   934: pop
        //   935: aload #15
        //   937: astore #31
        //   939: aload #15
        //   941: astore #27
        //   943: aload #4
        //   945: aload #29
        //   947: iload #30
        //   949: invokevirtual substring : (I)Ljava/lang/String;
        //   952: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   955: pop
        //   956: aload #15
        //   958: astore #31
        //   960: aload #15
        //   962: astore #27
        //   964: aload #4
        //   966: invokevirtual toString : ()Ljava/lang/String;
        //   969: astore #32
        //   971: aload #15
        //   973: astore #4
        //   975: aload #17
        //   977: astore #18
        //   979: aload #32
        //   981: astore #15
        //   983: goto -> 739
        //   986: astore #4
        //   988: aload #31
        //   990: astore #4
        //   992: goto -> 1490
        //   995: aload #17
        //   997: astore #18
        //   999: aload #29
        //   1001: astore #15
        //   1003: goto -> 739
        //   1006: aload #28
        //   1008: astore #32
        //   1010: aload #18
        //   1012: astore #33
        //   1014: aload #4
        //   1016: astore #31
        //   1018: aload #4
        //   1020: astore #27
        //   1022: aload #29
        //   1024: invokevirtual trim : ()Ljava/lang/String;
        //   1027: astore #15
        //   1029: aload #15
        //   1031: astore #31
        //   1033: bipush #6
        //   1035: istore #30
        //   1037: aload #4
        //   1039: astore #15
        //   1041: iload #30
        //   1043: ifle -> 1206
        //   1046: aload #15
        //   1048: astore #27
        //   1050: aload #15
        //   1052: astore #17
        //   1054: aload #31
        //   1056: iconst_0
        //   1057: iload #30
        //   1059: invokevirtual substring : (II)Ljava/lang/String;
        //   1062: astore #34
        //   1064: aload #31
        //   1066: astore #29
        //   1068: aload #15
        //   1070: astore #4
        //   1072: aload #15
        //   1074: astore #27
        //   1076: aload #15
        //   1078: astore #17
        //   1080: aload #34
        //   1082: invokevirtual trim : ()Ljava/lang/String;
        //   1085: ldc_w 't'
        //   1088: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1091: iconst_m1
        //   1092: if_icmple -> 1175
        //   1095: aload #15
        //   1097: astore #27
        //   1099: aload #15
        //   1101: astore #17
        //   1103: aload #34
        //   1105: ldc_w 't'
        //   1108: ldc ''
        //   1110: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1113: ldc ' '
        //   1115: ldc ''
        //   1117: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1120: ldc_w ','
        //   1123: ldc ''
        //   1125: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   1128: astore #34
        //   1130: aload #31
        //   1132: astore #29
        //   1134: aload #15
        //   1136: astore #4
        //   1138: aload #15
        //   1140: astore #27
        //   1142: aload #15
        //   1144: astore #17
        //   1146: aload #34
        //   1148: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   1151: ifeq -> 1175
        //   1154: aload #34
        //   1156: astore #4
        //   1158: aload #4
        //   1160: astore #27
        //   1162: aload #4
        //   1164: astore #17
        //   1166: aload #31
        //   1168: iload #30
        //   1170: invokevirtual substring : (I)Ljava/lang/String;
        //   1173: astore #29
        //   1175: iinc #30, -1
        //   1178: aload #29
        //   1180: astore #31
        //   1182: aload #4
        //   1184: astore #15
        //   1186: goto -> 1041
        //   1189: astore_1
        //   1190: aload #27
        //   1192: astore #4
        //   1194: goto -> 1380
        //   1197: astore #4
        //   1199: aload #17
        //   1201: astore #15
        //   1203: goto -> 1498
        //   1206: aload #31
        //   1208: astore #28
        //   1210: aload #15
        //   1212: astore #4
        //   1214: aload #11
        //   1216: astore #34
        //   1218: aload #33
        //   1220: astore #27
        //   1222: aload #32
        //   1224: astore #17
        //   1226: aload #15
        //   1228: invokevirtual length : ()I
        //   1231: ifle -> 1675
        //   1234: new java/lang/StringBuilder
        //   1237: dup
        //   1238: invokespecial <init> : ()V
        //   1241: astore #4
        //   1243: aload #4
        //   1245: ldc_w 'Select * From tbl_tinnhanS WHERE ngay_nhan = ''
        //   1248: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1251: pop
        //   1252: aload #4
        //   1254: aload #5
        //   1256: iconst_3
        //   1257: invokeinterface getString : (I)Ljava/lang/String;
        //   1262: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1265: pop
        //   1266: aload #4
        //   1268: ldc_w '' AND ten_kh = ''
        //   1271: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1274: pop
        //   1275: aload #4
        //   1277: aload #5
        //   1279: iconst_1
        //   1280: invokeinterface getString : (I)Ljava/lang/String;
        //   1285: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1288: pop
        //   1289: aload #4
        //   1291: ldc_w '' AND so_tin_nhan = '
        //   1294: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1297: pop
        //   1298: aload #4
        //   1300: aload #15
        //   1302: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1305: pop
        //   1306: aload_3
        //   1307: aload #4
        //   1309: invokevirtual toString : ()Ljava/lang/String;
        //   1312: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   1315: astore #18
        //   1317: aload #15
        //   1319: astore #4
        //   1321: aload #18
        //   1323: invokeinterface getCount : ()I
        //   1328: ifle -> 1335
        //   1331: ldc ''
        //   1333: astore #4
        //   1335: aload #18
        //   1337: invokeinterface close : ()V
        //   1342: aload #31
        //   1344: astore #28
        //   1346: aload #11
        //   1348: astore #34
        //   1350: aload #33
        //   1352: astore #27
        //   1354: aload #32
        //   1356: astore #17
        //   1358: goto -> 1675
        //   1361: astore #4
        //   1363: aload #31
        //   1365: astore #4
        //   1367: goto -> 1490
        //   1370: astore #15
        //   1372: goto -> 1490
        //   1375: astore_1
        //   1376: aload #27
        //   1378: astore #4
        //   1380: aload #4
        //   1382: invokevirtual length : ()I
        //   1385: ifle -> 1488
        //   1388: new java/lang/StringBuilder
        //   1391: dup
        //   1392: invokespecial <init> : ()V
        //   1395: astore #18
        //   1397: aload #18
        //   1399: ldc_w 'Select * From tbl_tinnhanS WHERE ngay_nhan = ''
        //   1402: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1405: pop
        //   1406: aload #18
        //   1408: aload #5
        //   1410: iconst_3
        //   1411: invokeinterface getString : (I)Ljava/lang/String;
        //   1416: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1419: pop
        //   1420: aload #18
        //   1422: ldc_w '' AND ten_kh = ''
        //   1425: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1428: pop
        //   1429: aload #18
        //   1431: aload #5
        //   1433: iconst_1
        //   1434: invokeinterface getString : (I)Ljava/lang/String;
        //   1439: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1442: pop
        //   1443: aload #18
        //   1445: ldc_w '' AND so_tin_nhan = '
        //   1448: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1451: pop
        //   1452: aload #18
        //   1454: aload #4
        //   1456: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1459: pop
        //   1460: aload_3
        //   1461: aload #18
        //   1463: invokevirtual toString : ()Ljava/lang/String;
        //   1466: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   1469: astore #4
        //   1471: aload #4
        //   1473: invokeinterface getCount : ()I
        //   1478: ifle -> 1481
        //   1481: aload #4
        //   1483: invokeinterface close : ()V
        //   1488: aload_1
        //   1489: athrow
        //   1490: aload #4
        //   1492: astore #15
        //   1494: aload #29
        //   1496: astore #31
        //   1498: aload #28
        //   1500: astore #32
        //   1502: aload #31
        //   1504: astore #28
        //   1506: aload #15
        //   1508: astore #4
        //   1510: aload #11
        //   1512: astore #34
        //   1514: aload #18
        //   1516: astore #27
        //   1518: aload #32
        //   1520: astore #17
        //   1522: aload #15
        //   1524: invokevirtual length : ()I
        //   1527: ifle -> 1675
        //   1530: new java/lang/StringBuilder
        //   1533: dup
        //   1534: invokespecial <init> : ()V
        //   1537: astore #4
        //   1539: aload #4
        //   1541: ldc_w 'Select * From tbl_tinnhanS WHERE ngay_nhan = ''
        //   1544: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1547: pop
        //   1548: aload #4
        //   1550: aload #5
        //   1552: iconst_3
        //   1553: invokeinterface getString : (I)Ljava/lang/String;
        //   1558: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1561: pop
        //   1562: aload #4
        //   1564: ldc_w '' AND ten_kh = ''
        //   1567: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1570: pop
        //   1571: aload #4
        //   1573: aload #5
        //   1575: iconst_1
        //   1576: invokeinterface getString : (I)Ljava/lang/String;
        //   1581: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1584: pop
        //   1585: aload #4
        //   1587: ldc_w '' AND so_tin_nhan = '
        //   1590: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1593: pop
        //   1594: aload #4
        //   1596: aload #15
        //   1598: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1601: pop
        //   1602: aload_3
        //   1603: aload #4
        //   1605: invokevirtual toString : ()Ljava/lang/String;
        //   1608: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   1611: astore #28
        //   1613: aload #15
        //   1615: astore #4
        //   1617: aload #28
        //   1619: invokeinterface getCount : ()I
        //   1624: ifle -> 1631
        //   1627: ldc ''
        //   1629: astore #4
        //   1631: aload #28
        //   1633: invokeinterface close : ()V
        //   1638: aload #31
        //   1640: astore #28
        //   1642: aload #11
        //   1644: astore #34
        //   1646: aload #18
        //   1648: astore #27
        //   1650: aload #32
        //   1652: astore #17
        //   1654: goto -> 1675
        //   1657: ldc_w 'xq'
        //   1660: astore #17
        //   1662: ldc 'xn'
        //   1664: astore #27
        //   1666: ldc_w 'bc'
        //   1669: astore #34
        //   1671: ldc ''
        //   1673: astore #4
        //   1675: ldc 'Khhi
        //   1677: astore #11
        //   1679: aload #28
        //   1681: ldc 'Khhi
        //   1683: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1686: iconst_m1
        //   1687: if_icmple -> 1693
        //   1690: goto -> 1911
        //   1693: aload #28
        //   1695: invokevirtual length : ()I
        //   1698: bipush #8
        //   1700: if_icmpge -> 1738
        //   1703: new java/lang/StringBuilder
        //   1706: dup
        //   1707: invokespecial <init> : ()V
        //   1710: astore #18
        //   1712: aload #18
        //   1714: ldc 'Khhi'
        //   1716: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1719: pop
        //   1720: aload #18
        //   1722: aload #28
        //   1724: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1727: pop
        //   1728: aload #18
        //   1730: invokevirtual toString : ()Ljava/lang/String;
        //   1733: astore #18
        //   1735: goto -> 1915
        //   1738: aload #28
        //   1740: iconst_0
        //   1741: iconst_5
        //   1742: invokevirtual substring : (II)Ljava/lang/String;
        //   1745: ldc 'de'
        //   1747: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1750: iconst_m1
        //   1751: if_icmpne -> 1891
        //   1754: aload #28
        //   1756: iconst_0
        //   1757: iconst_5
        //   1758: invokevirtual substring : (II)Ljava/lang/String;
        //   1761: ldc 'lo'
        //   1763: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1766: iconst_m1
        //   1767: if_icmpne -> 1888
        //   1770: aload #28
        //   1772: iconst_0
        //   1773: iconst_5
        //   1774: invokevirtual substring : (II)Ljava/lang/String;
        //   1777: ldc_w 'xi'
        //   1780: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1783: iconst_m1
        //   1784: if_icmpne -> 1885
        //   1787: aload #28
        //   1789: iconst_0
        //   1790: iconst_5
        //   1791: invokevirtual substring : (II)Ljava/lang/String;
        //   1794: aload #17
        //   1796: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1799: iconst_m1
        //   1800: if_icmpne -> 1882
        //   1803: aload #28
        //   1805: iconst_0
        //   1806: iconst_5
        //   1807: invokevirtual substring : (II)Ljava/lang/String;
        //   1810: ldc 'hc'
        //   1812: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1815: iconst_m1
        //   1816: if_icmpne -> 1879
        //   1819: aload #28
        //   1821: iconst_0
        //   1822: iconst_5
        //   1823: invokevirtual substring : (II)Ljava/lang/String;
        //   1826: aload #27
        //   1828: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1831: iconst_m1
        //   1832: if_icmpne -> 1876
        //   1835: aload #28
        //   1837: iconst_0
        //   1838: iconst_5
        //   1839: invokevirtual substring : (II)Ljava/lang/String;
        //   1842: aload #34
        //   1844: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1847: iconst_m1
        //   1848: if_icmpne -> 1891
        //   1851: aload #28
        //   1853: iconst_0
        //   1854: iconst_5
        //   1855: invokevirtual substring : (II)Ljava/lang/String;
        //   1858: ldc_w 'xg'
        //   1861: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1864: iconst_m1
        //   1865: if_icmpne -> 1891
        //   1868: ldc_w 'Khhid
        //   1871: astore #18
        //   1873: goto -> 1915
        //   1876: goto -> 1891
        //   1879: goto -> 1891
        //   1882: goto -> 1885
        //   1885: goto -> 1891
        //   1888: goto -> 1891
        //   1891: aload #28
        //   1893: ldc_w ' bo '
        //   1896: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1899: iconst_m1
        //   1900: if_icmple -> 1911
        //   1903: ldc_w 'Khhibo '
        //   1906: astore #18
        //   1908: goto -> 1915
        //   1911: aload #24
        //   1913: astore #18
        //   1915: aload #28
        //   1917: astore #15
        //   1919: aload #4
        //   1921: astore #35
        //   1923: ldc ''
        //   1925: astore #24
        //   1927: aload #17
        //   1929: astore #31
        //   1931: aload #28
        //   1933: invokestatic PhanTichTinNhan : (Ljava/lang/String;)Ljava/lang/String;
        //   1936: astore #4
        //   1938: aload #4
        //   1940: ldc 'Khhi
        //   1942: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1945: iconst_m1
        //   1946: if_icmple -> 1956
        //   1949: aload #4
        //   1951: astore #18
        //   1953: goto -> 2000
        //   1956: aload #4
        //   1958: ldc_w 'x '
        //   1961: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1964: iconst_m1
        //   1965: if_icmpne -> 2000
        //   1968: new java/lang/StringBuilder
        //   1971: dup
        //   1972: invokespecial <init> : ()V
        //   1975: astore #18
        //   1977: aload #18
        //   1979: ldc 'Khhi'
        //   1981: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1984: pop
        //   1985: aload #18
        //   1987: aload #4
        //   1989: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1992: pop
        //   1993: aload #18
        //   1995: invokevirtual toString : ()Ljava/lang/String;
        //   1998: astore #18
        //   2000: iload #10
        //   2002: istore #36
        //   2004: aload_3
        //   2005: getfield caidat_tg : Lorg/json/JSONObject;
        //   2008: ldc_w 'tg_loxien'
        //   2011: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2014: invokestatic CheckTime : (Ljava/lang/String;)Z
        //   2017: ifeq -> 2036
        //   2020: iload #10
        //   2022: istore #36
        //   2024: ldc_w '18:30'
        //   2027: invokestatic CheckTime : (Ljava/lang/String;)Z
        //   2030: ifne -> 2036
        //   2033: iconst_1
        //   2034: istore #36
        //   2036: aload #18
        //   2038: ldc 'Khhi
        //   2040: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2043: iconst_m1
        //   2044: if_icmpne -> 10456
        //   2047: aload #4
        //   2049: ldc_w ' , '
        //   2052: ldc ' '
        //   2054: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   2057: ldc_w ' ,'
        //   2060: ldc ' '
        //   2062: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   2065: astore #15
        //   2067: iconst_1
        //   2068: istore #22
        //   2070: iload #22
        //   2072: bipush #10
        //   2074: if_icmpge -> 2104
        //   2077: aload #15
        //   2079: ldc_w '  '
        //   2082: ldc ' '
        //   2084: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   2087: ldc_w ',,'
        //   2090: ldc_w ','
        //   2093: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   2096: astore #15
        //   2098: iinc #22, 1
        //   2101: goto -> 2070
        //   2104: new java/lang/StringBuilder
        //   2107: dup
        //   2108: invokespecial <init> : ()V
        //   2111: astore #4
        //   2113: aload #4
        //   2115: aload #15
        //   2117: invokevirtual trim : ()Ljava/lang/String;
        //   2120: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2123: pop
        //   2124: aload #4
        //   2126: ldc ' '
        //   2128: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2131: pop
        //   2132: aload #4
        //   2134: invokevirtual toString : ()Ljava/lang/String;
        //   2137: astore #29
        //   2139: iconst_0
        //   2140: istore #37
        //   2142: aconst_null
        //   2143: astore #32
        //   2145: iconst_m1
        //   2146: istore #38
        //   2148: aload #24
        //   2150: astore #17
        //   2152: aload #12
        //   2154: astore #33
        //   2156: aload #8
        //   2158: astore #24
        //   2160: aload #23
        //   2162: astore #28
        //   2164: aload #9
        //   2166: astore #4
        //   2168: aload #14
        //   2170: astore #15
        //   2172: aload #26
        //   2174: astore #8
        //   2176: aload #18
        //   2178: astore #9
        //   2180: aload #29
        //   2182: astore #14
        //   2184: aload #14
        //   2186: ldc_w ' x '
        //   2189: iload #38
        //   2191: iconst_1
        //   2192: iadd
        //   2193: invokevirtual indexOf : (Ljava/lang/String;I)I
        //   2196: istore #22
        //   2198: iload #22
        //   2200: istore #38
        //   2202: iload #22
        //   2204: iconst_m1
        //   2205: if_icmpeq -> 10226
        //   2208: ldc ''
        //   2210: astore #18
        //   2212: iload #38
        //   2214: istore #30
        //   2216: aload #28
        //   2218: astore_3
        //   2219: aload #11
        //   2221: astore #28
        //   2223: aload_3
        //   2224: astore #11
        //   2226: iload #30
        //   2228: aload #14
        //   2230: invokevirtual length : ()I
        //   2233: if_icmpge -> 2331
        //   2236: aload #14
        //   2238: iload #30
        //   2240: invokevirtual charAt : (I)C
        //   2243: bipush #32
        //   2245: if_icmpne -> 2259
        //   2248: aload #18
        //   2250: invokevirtual length : ()I
        //   2253: ifle -> 2259
        //   2256: goto -> 2331
        //   2259: aload #18
        //   2261: astore_3
        //   2262: ldc_w '0123456789,tr'
        //   2265: aload #14
        //   2267: iload #30
        //   2269: iload #30
        //   2271: iconst_1
        //   2272: iadd
        //   2273: invokevirtual substring : (II)Ljava/lang/String;
        //   2276: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2279: iconst_m1
        //   2280: if_icmple -> 2319
        //   2283: new java/lang/StringBuilder
        //   2286: dup
        //   2287: invokespecial <init> : ()V
        //   2290: astore_3
        //   2291: aload_3
        //   2292: aload #18
        //   2294: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2297: pop
        //   2298: aload_3
        //   2299: aload #14
        //   2301: iload #30
        //   2303: iload #30
        //   2305: iconst_1
        //   2306: iadd
        //   2307: invokevirtual substring : (II)Ljava/lang/String;
        //   2310: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2313: pop
        //   2314: aload_3
        //   2315: invokevirtual toString : ()Ljava/lang/String;
        //   2318: astore_3
        //   2319: iinc #30, 1
        //   2322: aload_3
        //   2323: astore #18
        //   2325: aload #11
        //   2327: astore_3
        //   2328: goto -> 2223
        //   2331: ldc ''
        //   2333: astore #29
        //   2335: iload #30
        //   2337: istore #22
        //   2339: aload #18
        //   2341: astore #12
        //   2343: iload #22
        //   2345: aload #14
        //   2347: invokevirtual length : ()I
        //   2350: if_icmpge -> 2424
        //   2353: aload #14
        //   2355: iload #22
        //   2357: invokevirtual charAt : (I)C
        //   2360: invokestatic isLetter : (C)Z
        //   2363: ifne -> 2377
        //   2366: aload #29
        //   2368: invokevirtual length : ()I
        //   2371: ifle -> 2377
        //   2374: goto -> 2424
        //   2377: new java/lang/StringBuilder
        //   2380: dup
        //   2381: invokespecial <init> : ()V
        //   2384: astore #18
        //   2386: aload #18
        //   2388: aload #29
        //   2390: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2393: pop
        //   2394: aload #18
        //   2396: aload #14
        //   2398: iload #22
        //   2400: iload #22
        //   2402: iconst_1
        //   2403: iadd
        //   2404: invokevirtual substring : (II)Ljava/lang/String;
        //   2407: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2410: pop
        //   2411: aload #18
        //   2413: invokevirtual toString : ()Ljava/lang/String;
        //   2416: astore #29
        //   2418: iinc #22, 1
        //   2421: goto -> 2343
        //   2424: iload #22
        //   2426: istore #10
        //   2428: iload #30
        //   2430: iload #22
        //   2432: if_icmpne -> 2441
        //   2435: iload #22
        //   2437: iconst_1
        //   2438: isub
        //   2439: istore #10
        //   2441: aload #29
        //   2443: ldc_w 'dau'
        //   2446: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2449: iconst_m1
        //   2450: if_icmpgt -> 3001
        //   2453: aload #29
        //   2455: ldc_w 'dit'
        //   2458: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2461: iconst_m1
        //   2462: if_icmpgt -> 2998
        //   2465: aload #29
        //   2467: ldc_w 'tong'
        //   2470: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2473: iconst_m1
        //   2474: if_icmpgt -> 2995
        //   2477: aload #29
        //   2479: ldc_w 'cham'
        //   2482: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2485: iconst_m1
        //   2486: if_icmpgt -> 2992
        //   2489: aload #29
        //   2491: ldc_w 'dan'
        //   2494: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2497: iconst_m1
        //   2498: if_icmpgt -> 2989
        //   2501: aload #29
        //   2503: ldc_w 'boj'
        //   2506: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2509: iconst_m1
        //   2510: if_icmpgt -> 2986
        //   2513: aload #29
        //   2515: ldc 'lo'
        //   2517: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2520: iconst_m1
        //   2521: if_icmpgt -> 2983
        //   2524: aload #29
        //   2526: ldc 'de'
        //   2528: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2531: iconst_m1
        //   2532: if_icmpgt -> 2980
        //   2535: aload #29
        //   2537: aload #15
        //   2539: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2542: iconst_m1
        //   2543: if_icmpgt -> 2977
        //   2546: aload #29
        //   2548: aload #27
        //   2550: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2553: iconst_m1
        //   2554: if_icmpgt -> 2974
        //   2557: aload #29
        //   2559: ldc 'hc'
        //   2561: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2564: iconst_m1
        //   2565: if_icmpgt -> 2971
        //   2568: aload #29
        //   2570: aload #31
        //   2572: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2575: iconst_m1
        //   2576: if_icmpgt -> 2968
        //   2579: aload #29
        //   2581: ldc_w 'xg'
        //   2584: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2587: iconst_m1
        //   2588: if_icmpgt -> 2965
        //   2591: aload #29
        //   2593: aload #34
        //   2595: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2598: iconst_m1
        //   2599: if_icmpgt -> 2962
        //   2602: aload #29
        //   2604: ldc_w ' x'
        //   2607: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2610: iconst_m1
        //   2611: if_icmpgt -> 2959
        //   2614: aload #29
        //   2616: ldc_w 'kep'
        //   2619: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2622: iconst_m1
        //   2623: if_icmpgt -> 2956
        //   2626: aload #29
        //   2628: ldc_w 'sat'
        //   2631: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2634: iconst_m1
        //   2635: if_icmpgt -> 2953
        //   2638: aload #29
        //   2640: ldc_w 'to'
        //   2643: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2646: iconst_m1
        //   2647: if_icmpgt -> 2950
        //   2650: aload #29
        //   2652: ldc_w 'nho'
        //   2655: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2658: iconst_m1
        //   2659: if_icmpgt -> 2947
        //   2662: aload #29
        //   2664: ldc_w 'chan'
        //   2667: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2670: iconst_m1
        //   2671: if_icmpgt -> 2944
        //   2674: aload #29
        //   2676: ldc_w 'le'
        //   2679: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2682: iconst_m1
        //   2683: if_icmpgt -> 2941
        //   2686: aload #29
        //   2688: ldc_w 'ko'
        //   2691: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2694: iconst_m1
        //   2695: if_icmpgt -> 2938
        //   2698: aload #29
        //   2700: ldc_w 'chia'
        //   2703: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2706: iconst_m1
        //   2707: if_icmpgt -> 2935
        //   2710: aload #29
        //   2712: ldc_w 'duoi'
        //   2715: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2718: iconst_m1
        //   2719: if_icmpgt -> 2932
        //   2722: aload #29
        //   2724: ldc_w 'be'
        //   2727: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2730: iconst_m1
        //   2731: if_icmple -> 2737
        //   2734: goto -> 3001
        //   2737: aload #29
        //   2739: ldc_w 'x '
        //   2742: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2745: iconst_m1
        //   2746: if_icmple -> 2841
        //   2749: iload #30
        //   2751: iconst_1
        //   2752: isub
        //   2753: istore #22
        //   2755: iload #22
        //   2757: ifle -> 2822
        //   2760: aload #14
        //   2762: iload #22
        //   2764: iload #22
        //   2766: iconst_1
        //   2767: iadd
        //   2768: invokevirtual substring : (II)Ljava/lang/String;
        //   2771: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   2774: ifne -> 2816
        //   2777: aload #14
        //   2779: iload #37
        //   2781: iload #22
        //   2783: iconst_1
        //   2784: iadd
        //   2785: invokevirtual substring : (II)Ljava/lang/String;
        //   2788: astore #32
        //   2790: iload #22
        //   2792: iconst_1
        //   2793: iadd
        //   2794: istore #37
        //   2796: aload #14
        //   2798: iload #37
        //   2800: invokevirtual substring : (I)Ljava/lang/String;
        //   2803: astore #18
        //   2805: iload #22
        //   2807: istore #10
        //   2809: iload #37
        //   2811: istore #22
        //   2813: goto -> 3025
        //   2816: iinc #22, -1
        //   2819: goto -> 2755
        //   2822: iload #22
        //   2824: istore #10
        //   2826: aload #32
        //   2828: astore #18
        //   2830: iload #37
        //   2832: istore #22
        //   2834: aload #33
        //   2836: astore #32
        //   2838: goto -> 3025
        //   2841: aload #14
        //   2843: iload #37
        //   2845: iload #10
        //   2847: invokevirtual substring : (II)Ljava/lang/String;
        //   2850: astore #32
        //   2852: aload #32
        //   2854: invokevirtual trim : ()Ljava/lang/String;
        //   2857: invokevirtual length : ()I
        //   2860: iconst_3
        //   2861: if_icmple -> 2916
        //   2864: aload #32
        //   2866: iconst_0
        //   2867: iconst_4
        //   2868: invokevirtual substring : (II)Ljava/lang/String;
        //   2871: ldc_w 'bor'
        //   2874: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2877: iconst_m1
        //   2878: if_icmple -> 2916
        //   2881: new java/lang/StringBuilder
        //   2884: dup
        //   2885: invokespecial <init> : ()V
        //   2888: astore #18
        //   2890: aload #18
        //   2892: ldc 'de '
        //   2894: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2897: pop
        //   2898: aload #18
        //   2900: aload #32
        //   2902: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2905: pop
        //   2906: aload #18
        //   2908: invokevirtual toString : ()Ljava/lang/String;
        //   2911: astore #32
        //   2913: goto -> 2916
        //   2916: aload #14
        //   2918: iload #10
        //   2920: invokevirtual substring : (I)Ljava/lang/String;
        //   2923: astore #18
        //   2925: iload #10
        //   2927: istore #22
        //   2929: goto -> 3025
        //   2932: goto -> 3001
        //   2935: goto -> 3001
        //   2938: goto -> 3001
        //   2941: goto -> 3001
        //   2944: goto -> 3001
        //   2947: goto -> 3001
        //   2950: goto -> 3001
        //   2953: goto -> 3001
        //   2956: goto -> 3001
        //   2959: goto -> 3001
        //   2962: goto -> 3001
        //   2965: goto -> 3001
        //   2968: goto -> 3001
        //   2971: goto -> 3001
        //   2974: goto -> 3001
        //   2977: goto -> 3001
        //   2980: goto -> 3001
        //   2983: goto -> 3001
        //   2986: goto -> 3001
        //   2989: goto -> 3001
        //   2992: goto -> 3001
        //   2995: goto -> 3001
        //   2998: goto -> 3001
        //   3001: aload #14
        //   3003: iload #37
        //   3005: iload #30
        //   3007: invokevirtual substring : (II)Ljava/lang/String;
        //   3010: astore #32
        //   3012: aload #14
        //   3014: iload #30
        //   3016: invokevirtual substring : (I)Ljava/lang/String;
        //   3019: astore #18
        //   3021: iload #30
        //   3023: istore #22
        //   3025: iinc #6, 1
        //   3028: aload #32
        //   3030: invokevirtual trim : ()Ljava/lang/String;
        //   3033: astore #23
        //   3035: aload #23
        //   3037: aload #8
        //   3039: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   3042: istore #25
        //   3044: iload #25
        //   3046: ifeq -> 3470
        //   3049: aload #23
        //   3051: invokevirtual length : ()I
        //   3054: istore #10
        //   3056: iload #10
        //   3058: bipush #6
        //   3060: if_icmple -> 3416
        //   3063: bipush #6
        //   3065: istore #10
        //   3067: aload #17
        //   3069: astore #33
        //   3071: aload #8
        //   3073: astore #17
        //   3075: iload #10
        //   3077: ifle -> 3397
        //   3080: aload #23
        //   3082: iconst_0
        //   3083: iload #10
        //   3085: invokevirtual substring : (II)Ljava/lang/String;
        //   3088: astore #26
        //   3090: aload #18
        //   3092: astore #29
        //   3094: aload #26
        //   3096: invokevirtual trim : ()Ljava/lang/String;
        //   3099: aload #17
        //   3101: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3104: istore #37
        //   3106: iload #37
        //   3108: iconst_m1
        //   3109: if_icmple -> 3290
        //   3112: aload #33
        //   3114: astore #8
        //   3116: aload #26
        //   3118: aload #17
        //   3120: aload #8
        //   3122: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   3125: aload #4
        //   3127: aload #8
        //   3129: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   3132: ldc_w ','
        //   3135: aload #8
        //   3137: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   3140: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   3143: ifeq -> 3243
        //   3146: new java/lang/StringBuilder
        //   3149: astore #18
        //   3151: aload #18
        //   3153: invokespecial <init> : ()V
        //   3156: aload #18
        //   3158: aload #4
        //   3160: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3163: pop
        //   3164: aload #17
        //   3166: astore #26
        //   3168: aload #18
        //   3170: aload #23
        //   3172: iload #10
        //   3174: iconst_1
        //   3175: iadd
        //   3176: invokevirtual substring : (I)Ljava/lang/String;
        //   3179: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3182: pop
        //   3183: aload #18
        //   3185: aload #4
        //   3187: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   3190: pop
        //   3191: aload #18
        //   3193: invokevirtual toString : ()Ljava/lang/String;
        //   3196: astore #18
        //   3198: aload #18
        //   3200: astore #32
        //   3202: goto -> 3290
        //   3205: astore #18
        //   3207: aload #4
        //   3209: astore #17
        //   3211: aload #11
        //   3213: astore_3
        //   3214: aload #28
        //   3216: astore #11
        //   3218: aload #31
        //   3220: astore #18
        //   3222: aload #15
        //   3224: astore #4
        //   3226: aload_3
        //   3227: astore #28
        //   3229: aload #32
        //   3231: astore #15
        //   3233: aload #26
        //   3235: astore_3
        //   3236: aload #29
        //   3238: astore #33
        //   3240: goto -> 10047
        //   3243: goto -> 3290
        //   3246: astore #18
        //   3248: aload #17
        //   3250: astore_3
        //   3251: aload #15
        //   3253: astore #33
        //   3255: aload #4
        //   3257: astore #17
        //   3259: aload #11
        //   3261: astore #15
        //   3263: aload #28
        //   3265: astore #11
        //   3267: aload #31
        //   3269: astore #18
        //   3271: aload #33
        //   3273: astore #4
        //   3275: aload #15
        //   3277: astore #28
        //   3279: aload #32
        //   3281: astore #15
        //   3283: aload #29
        //   3285: astore #33
        //   3287: goto -> 10047
        //   3290: iinc #10, -1
        //   3293: aload #29
        //   3295: astore #18
        //   3297: goto -> 3075
        //   3300: astore #18
        //   3302: aload #33
        //   3304: astore #8
        //   3306: aload #17
        //   3308: astore_3
        //   3309: aload #15
        //   3311: astore #33
        //   3313: aload #4
        //   3315: astore #17
        //   3317: aload #11
        //   3319: astore #15
        //   3321: aload #28
        //   3323: astore #11
        //   3325: aload #31
        //   3327: astore #18
        //   3329: aload #33
        //   3331: astore #4
        //   3333: aload #15
        //   3335: astore #28
        //   3337: aload #32
        //   3339: astore #15
        //   3341: aload #29
        //   3343: astore #33
        //   3345: goto -> 10047
        //   3348: astore_3
        //   3349: aload #18
        //   3351: astore #29
        //   3353: aload #33
        //   3355: astore #8
        //   3357: aload #4
        //   3359: astore #12
        //   3361: aload #11
        //   3363: astore_3
        //   3364: aload #28
        //   3366: astore #11
        //   3368: aload #31
        //   3370: astore #18
        //   3372: aload #15
        //   3374: astore #4
        //   3376: aload_3
        //   3377: astore #28
        //   3379: aload #32
        //   3381: astore #15
        //   3383: aload #17
        //   3385: astore_3
        //   3386: aload #29
        //   3388: astore #33
        //   3390: aload #12
        //   3392: astore #17
        //   3394: goto -> 10047
        //   3397: aload #33
        //   3399: astore #8
        //   3401: aload #17
        //   3403: astore #29
        //   3405: aload #32
        //   3407: astore #17
        //   3409: aload #18
        //   3411: astore #33
        //   3413: goto -> 3490
        //   3416: goto -> 3470
        //   3419: astore_3
        //   3420: aload #18
        //   3422: astore #33
        //   3424: aload #17
        //   3426: astore #18
        //   3428: aload #8
        //   3430: astore_3
        //   3431: aload #15
        //   3433: astore #8
        //   3435: aload #4
        //   3437: astore #17
        //   3439: aload #11
        //   3441: astore #15
        //   3443: aload #28
        //   3445: astore #11
        //   3447: aload #8
        //   3449: astore #4
        //   3451: aload #18
        //   3453: astore #8
        //   3455: aload #15
        //   3457: astore #28
        //   3459: aload #32
        //   3461: astore #15
        //   3463: aload #31
        //   3465: astore #18
        //   3467: goto -> 10047
        //   3470: aload #17
        //   3472: astore #26
        //   3474: aload #32
        //   3476: astore #17
        //   3478: aload #18
        //   3480: astore #33
        //   3482: aload #8
        //   3484: astore #29
        //   3486: aload #26
        //   3488: astore #8
        //   3490: aload_0
        //   3491: astore #39
        //   3493: aload #39
        //   3495: getfield mang : [[Ljava/lang/String;
        //   3498: iload #6
        //   3500: aaload
        //   3501: iconst_0
        //   3502: aload #17
        //   3504: aastore
        //   3505: aload #17
        //   3507: ldc 'loa'
        //   3509: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3512: istore #30
        //   3514: iload #30
        //   3516: iconst_m1
        //   3517: if_icmple -> 3577
        //   3520: aload #39
        //   3522: getfield mang : [[Ljava/lang/String;
        //   3525: iload #6
        //   3527: aaload
        //   3528: iconst_1
        //   3529: ldc 'lo dau'
        //   3531: aastore
        //   3532: goto -> 4182
        //   3535: astore #18
        //   3537: aload #17
        //   3539: astore #18
        //   3541: aload #15
        //   3543: astore_3
        //   3544: aload #4
        //   3546: astore #17
        //   3548: aload #11
        //   3550: astore #15
        //   3552: aload #28
        //   3554: astore #11
        //   3556: aload_3
        //   3557: astore #4
        //   3559: aload #15
        //   3561: astore #28
        //   3563: aload #18
        //   3565: astore #15
        //   3567: aload #31
        //   3569: astore #18
        //   3571: aload #29
        //   3573: astore_3
        //   3574: goto -> 10047
        //   3577: aload #17
        //   3579: ldc 'lo'
        //   3581: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3584: istore #30
        //   3586: iload #30
        //   3588: iconst_m1
        //   3589: if_icmple -> 3607
        //   3592: aload #39
        //   3594: getfield mang : [[Ljava/lang/String;
        //   3597: iload #6
        //   3599: aaload
        //   3600: iconst_1
        //   3601: ldc 'lo'
        //   3603: aastore
        //   3604: goto -> 4182
        //   3607: aload #17
        //   3609: ldc 'dea'
        //   3611: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3614: istore #30
        //   3616: iload #30
        //   3618: iconst_m1
        //   3619: if_icmple -> 3637
        //   3622: aload #39
        //   3624: getfield mang : [[Ljava/lang/String;
        //   3627: iload #6
        //   3629: aaload
        //   3630: iconst_1
        //   3631: ldc 'de dau db'
        //   3633: aastore
        //   3634: goto -> 4182
        //   3637: aload #17
        //   3639: ldc 'deb'
        //   3641: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3644: istore #30
        //   3646: iload #30
        //   3648: iconst_m1
        //   3649: if_icmple -> 3667
        //   3652: aload #39
        //   3654: getfield mang : [[Ljava/lang/String;
        //   3657: iload #6
        //   3659: aaload
        //   3660: iconst_1
        //   3661: ldc 'de dit db'
        //   3663: aastore
        //   3664: goto -> 4182
        //   3667: aload #17
        //   3669: ldc 'det'
        //   3671: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3674: istore #30
        //   3676: iload #30
        //   3678: iconst_m1
        //   3679: if_icmple -> 3697
        //   3682: aload #39
        //   3684: getfield mang : [[Ljava/lang/String;
        //   3687: iload #6
        //   3689: aaload
        //   3690: iconst_1
        //   3691: ldc 'de 8'
        //   3693: aastore
        //   3694: goto -> 4182
        //   3697: aload #17
        //   3699: ldc 'hc'
        //   3701: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3704: istore #30
        //   3706: iload #30
        //   3708: iconst_m1
        //   3709: if_icmple -> 3727
        //   3712: aload #39
        //   3714: getfield mang : [[Ljava/lang/String;
        //   3717: iload #6
        //   3719: aaload
        //   3720: iconst_1
        //   3721: ldc 'hai cua'
        //   3723: aastore
        //   3724: goto -> 4182
        //   3727: aload #17
        //   3729: aload #27
        //   3731: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3734: istore #30
        //   3736: iload #30
        //   3738: iconst_m1
        //   3739: if_icmple -> 3757
        //   3742: aload #39
        //   3744: getfield mang : [[Ljava/lang/String;
        //   3747: iload #6
        //   3749: aaload
        //   3750: iconst_1
        //   3751: aload #27
        //   3753: aastore
        //   3754: goto -> 4182
        //   3757: aload #17
        //   3759: ldc 'dec'
        //   3761: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3764: istore #30
        //   3766: iload #30
        //   3768: iconst_m1
        //   3769: if_icmple -> 3787
        //   3772: aload #39
        //   3774: getfield mang : [[Ljava/lang/String;
        //   3777: iload #6
        //   3779: aaload
        //   3780: iconst_1
        //   3781: ldc 'de dau nhat'
        //   3783: aastore
        //   3784: goto -> 4182
        //   3787: aload #17
        //   3789: ldc 'ded'
        //   3791: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3794: istore #30
        //   3796: iload #30
        //   3798: iconst_m1
        //   3799: if_icmple -> 3817
        //   3802: aload #39
        //   3804: getfield mang : [[Ljava/lang/String;
        //   3807: iload #6
        //   3809: aaload
        //   3810: iconst_1
        //   3811: ldc 'de dit nhat'
        //   3813: aastore
        //   3814: goto -> 4182
        //   3817: aload #17
        //   3819: ldc 'de '
        //   3821: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3824: istore #30
        //   3826: iload #30
        //   3828: iconst_m1
        //   3829: if_icmple -> 3847
        //   3832: aload #39
        //   3834: getfield mang : [[Ljava/lang/String;
        //   3837: iload #6
        //   3839: aaload
        //   3840: iconst_1
        //   3841: ldc 'de dit db'
        //   3843: aastore
        //   3844: goto -> 4182
        //   3847: aload #17
        //   3849: ldc_w 'bca'
        //   3852: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3855: istore #30
        //   3857: iload #30
        //   3859: iconst_m1
        //   3860: if_icmple -> 3879
        //   3863: aload #39
        //   3865: getfield mang : [[Ljava/lang/String;
        //   3868: iload #6
        //   3870: aaload
        //   3871: iconst_1
        //   3872: ldc_w 'bc dau'
        //   3875: aastore
        //   3876: goto -> 4182
        //   3879: aload #17
        //   3881: aload #34
        //   3883: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3886: istore #30
        //   3888: iload #30
        //   3890: iconst_m1
        //   3891: if_icmple -> 3909
        //   3894: aload #39
        //   3896: getfield mang : [[Ljava/lang/String;
        //   3899: iload #6
        //   3901: aaload
        //   3902: iconst_1
        //   3903: aload #34
        //   3905: aastore
        //   3906: goto -> 4182
        //   3909: aload #17
        //   3911: ldc_w 'xia'
        //   3914: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3917: istore #30
        //   3919: iload #30
        //   3921: iconst_m1
        //   3922: if_icmple -> 3941
        //   3925: aload #39
        //   3927: getfield mang : [[Ljava/lang/String;
        //   3930: iload #6
        //   3932: aaload
        //   3933: iconst_1
        //   3934: ldc_w 'xien dau'
        //   3937: aastore
        //   3938: goto -> 4182
        //   3941: aload #17
        //   3943: aload #15
        //   3945: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3948: istore #30
        //   3950: iload #30
        //   3952: iconst_m1
        //   3953: if_icmple -> 3971
        //   3956: aload #39
        //   3958: getfield mang : [[Ljava/lang/String;
        //   3961: iload #6
        //   3963: aaload
        //   3964: iconst_1
        //   3965: aload #15
        //   3967: aastore
        //   3968: goto -> 4182
        //   3971: aload #17
        //   3973: ldc_w 'xqa'
        //   3976: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3979: istore #30
        //   3981: iload #30
        //   3983: iconst_m1
        //   3984: if_icmple -> 4003
        //   3987: aload #39
        //   3989: getfield mang : [[Ljava/lang/String;
        //   3992: iload #6
        //   3994: aaload
        //   3995: iconst_1
        //   3996: ldc_w 'xqa'
        //   3999: aastore
        //   4000: goto -> 4182
        //   4003: aload #31
        //   4005: astore #18
        //   4007: aload #17
        //   4009: aload #18
        //   4011: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4014: istore #30
        //   4016: iload #30
        //   4018: iconst_m1
        //   4019: if_icmple -> 4075
        //   4022: aload #39
        //   4024: getfield mang : [[Ljava/lang/String;
        //   4027: iload #6
        //   4029: aaload
        //   4030: iconst_1
        //   4031: aload #18
        //   4033: aastore
        //   4034: goto -> 4182
        //   4037: astore #31
        //   4039: aload #17
        //   4041: astore #31
        //   4043: aload #15
        //   4045: astore_3
        //   4046: aload #4
        //   4048: astore #17
        //   4050: aload #11
        //   4052: astore #15
        //   4054: aload #28
        //   4056: astore #11
        //   4058: aload_3
        //   4059: astore #4
        //   4061: aload #15
        //   4063: astore #28
        //   4065: aload #31
        //   4067: astore #15
        //   4069: aload #29
        //   4071: astore_3
        //   4072: goto -> 10047
        //   4075: aload #17
        //   4077: ldc_w 'xg'
        //   4080: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4083: istore #30
        //   4085: iload #30
        //   4087: iconst_m1
        //   4088: if_icmple -> 4160
        //   4091: aload #39
        //   4093: getfield mang : [[Ljava/lang/String;
        //   4096: iload #6
        //   4098: aaload
        //   4099: astore #26
        //   4101: aload #17
        //   4103: invokevirtual trim : ()Ljava/lang/String;
        //   4106: astore #32
        //   4108: aload #26
        //   4110: iconst_1
        //   4111: aload #32
        //   4113: iconst_0
        //   4114: iconst_4
        //   4115: invokevirtual substring : (II)Ljava/lang/String;
        //   4118: aastore
        //   4119: goto -> 4182
        //   4122: astore #31
        //   4124: aload #17
        //   4126: astore #31
        //   4128: aload #15
        //   4130: astore_3
        //   4131: aload #4
        //   4133: astore #17
        //   4135: aload #11
        //   4137: astore #15
        //   4139: aload #28
        //   4141: astore #11
        //   4143: aload_3
        //   4144: astore #4
        //   4146: aload #15
        //   4148: astore #28
        //   4150: aload #31
        //   4152: astore #15
        //   4154: aload #29
        //   4156: astore_3
        //   4157: goto -> 10047
        //   4160: aload #39
        //   4162: getfield mang : [[Ljava/lang/String;
        //   4165: iload #6
        //   4167: aaload
        //   4168: iconst_1
        //   4169: aload #39
        //   4171: getfield mang : [[Ljava/lang/String;
        //   4174: iload #6
        //   4176: iconst_1
        //   4177: isub
        //   4178: aaload
        //   4179: iconst_1
        //   4180: aaload
        //   4181: aastore
        //   4182: aload #31
        //   4184: astore #18
        //   4186: aload #17
        //   4188: ldc_w ' x '
        //   4191: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4194: istore #30
        //   4196: iload #30
        //   4198: iconst_m1
        //   4199: if_icmpeq -> 9748
        //   4202: aload #17
        //   4204: invokevirtual trim : ()Ljava/lang/String;
        //   4207: ldc_w 'x '
        //   4210: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4213: iconst_2
        //   4214: if_icmpge -> 4220
        //   4217: goto -> 9748
        //   4220: aload #39
        //   4222: getfield mang : [[Ljava/lang/String;
        //   4225: iload #6
        //   4227: aaload
        //   4228: iconst_2
        //   4229: aload #17
        //   4231: iconst_0
        //   4232: aload #17
        //   4234: ldc_w ' x '
        //   4237: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4240: invokevirtual substring : (II)Ljava/lang/String;
        //   4243: invokevirtual trim : ()Ljava/lang/String;
        //   4246: aastore
        //   4247: aload #39
        //   4249: getfield mang : [[Ljava/lang/String;
        //   4252: iload #6
        //   4254: aaload
        //   4255: iconst_3
        //   4256: aload #17
        //   4258: aload #17
        //   4260: ldc_w ' x '
        //   4263: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4266: invokevirtual substring : (I)Ljava/lang/String;
        //   4269: aastore
        //   4270: iload #6
        //   4272: iconst_1
        //   4273: if_icmple -> 4544
        //   4276: aload #39
        //   4278: getfield mang : [[Ljava/lang/String;
        //   4281: iload #6
        //   4283: iconst_1
        //   4284: isub
        //   4285: aaload
        //   4286: iconst_2
        //   4287: aaload
        //   4288: ldc_w 'xi 2 '
        //   4291: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4294: iconst_m1
        //   4295: if_icmpgt -> 4348
        //   4298: aload #39
        //   4300: getfield mang : [[Ljava/lang/String;
        //   4303: iload #6
        //   4305: iconst_1
        //   4306: isub
        //   4307: aaload
        //   4308: iconst_2
        //   4309: aaload
        //   4310: ldc_w 'xi 3 '
        //   4313: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4316: iconst_m1
        //   4317: if_icmpgt -> 4348
        //   4320: aload #39
        //   4322: getfield mang : [[Ljava/lang/String;
        //   4325: iload #6
        //   4327: iconst_1
        //   4328: isub
        //   4329: aaload
        //   4330: iconst_2
        //   4331: aaload
        //   4332: ldc_w 'xi 4 '
        //   4335: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4338: iconst_m1
        //   4339: if_icmple -> 4345
        //   4342: goto -> 4348
        //   4345: goto -> 4544
        //   4348: aload #39
        //   4350: getfield mang : [[Ljava/lang/String;
        //   4353: iload #6
        //   4355: aaload
        //   4356: iconst_1
        //   4357: aaload
        //   4358: aload #15
        //   4360: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4363: iconst_m1
        //   4364: if_icmple -> 4499
        //   4367: aload #39
        //   4369: getfield mang : [[Ljava/lang/String;
        //   4372: iload #6
        //   4374: aaload
        //   4375: iconst_2
        //   4376: aaload
        //   4377: aload #15
        //   4379: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4382: iconst_m1
        //   4383: if_icmpne -> 4499
        //   4386: aload #39
        //   4388: getfield mang : [[Ljava/lang/String;
        //   4391: iload #6
        //   4393: aaload
        //   4394: astore #32
        //   4396: new java/lang/StringBuilder
        //   4399: astore #26
        //   4401: aload #26
        //   4403: invokespecial <init> : ()V
        //   4406: aload #39
        //   4408: getfield mang : [[Ljava/lang/String;
        //   4411: iload #6
        //   4413: iconst_1
        //   4414: isub
        //   4415: aaload
        //   4416: iconst_2
        //   4417: aaload
        //   4418: astore #23
        //   4420: aload #26
        //   4422: aload #23
        //   4424: iconst_0
        //   4425: iconst_5
        //   4426: invokevirtual substring : (II)Ljava/lang/String;
        //   4429: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4432: pop
        //   4433: aload #26
        //   4435: aload #39
        //   4437: getfield mang : [[Ljava/lang/String;
        //   4440: iload #6
        //   4442: aaload
        //   4443: iconst_2
        //   4444: aaload
        //   4445: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   4448: pop
        //   4449: aload #32
        //   4451: iconst_2
        //   4452: aload #26
        //   4454: invokevirtual toString : ()Ljava/lang/String;
        //   4457: aastore
        //   4458: goto -> 4544
        //   4461: astore #31
        //   4463: aload #17
        //   4465: astore #31
        //   4467: aload #15
        //   4469: astore_3
        //   4470: aload #4
        //   4472: astore #17
        //   4474: aload #11
        //   4476: astore #15
        //   4478: aload #28
        //   4480: astore #11
        //   4482: aload_3
        //   4483: astore #4
        //   4485: aload #15
        //   4487: astore #28
        //   4489: aload #31
        //   4491: astore #15
        //   4493: aload #29
        //   4495: astore_3
        //   4496: goto -> 10047
        //   4499: goto -> 4544
        //   4502: astore #18
        //   4504: aload #17
        //   4506: astore #18
        //   4508: aload #15
        //   4510: astore_3
        //   4511: aload #4
        //   4513: astore #17
        //   4515: aload #11
        //   4517: astore #15
        //   4519: aload #28
        //   4521: astore #11
        //   4523: aload_3
        //   4524: astore #4
        //   4526: aload #15
        //   4528: astore #28
        //   4530: aload #18
        //   4532: astore #15
        //   4534: aload #31
        //   4536: astore #18
        //   4538: aload #29
        //   4540: astore_3
        //   4541: goto -> 10047
        //   4544: aload #27
        //   4546: astore #31
        //   4548: aload #39
        //   4550: iload #6
        //   4552: invokespecial XulyMang : (I)V
        //   4555: aload #39
        //   4557: iload #6
        //   4559: invokespecial BaoLoiTien : (I)V
        //   4562: aload #39
        //   4564: getfield mang : [[Ljava/lang/String;
        //   4567: iload #6
        //   4569: aaload
        //   4570: iconst_1
        //   4571: aaload
        //   4572: astore #32
        //   4574: aload #32
        //   4576: ldc 'lo'
        //   4578: if_acmpeq -> 6885
        //   4581: aload #39
        //   4583: getfield mang : [[Ljava/lang/String;
        //   4586: iload #6
        //   4588: aaload
        //   4589: iconst_1
        //   4590: aaload
        //   4591: ldc 'de'
        //   4593: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4596: iconst_m1
        //   4597: if_icmpgt -> 6885
        //   4600: aload #39
        //   4602: getfield mang : [[Ljava/lang/String;
        //   4605: iload #6
        //   4607: aaload
        //   4608: iconst_1
        //   4609: aaload
        //   4610: ldc 'hai cua'
        //   4612: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4615: iconst_m1
        //   4616: if_icmpgt -> 6885
        //   4619: aload #39
        //   4621: getfield mang : [[Ljava/lang/String;
        //   4624: iload #6
        //   4626: aaload
        //   4627: iconst_1
        //   4628: aaload
        //   4629: aload #34
        //   4631: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4634: iconst_m1
        //   4635: if_icmple -> 4641
        //   4638: goto -> 6885
        //   4641: aload #39
        //   4643: getfield mang : [[Ljava/lang/String;
        //   4646: iload #6
        //   4648: aaload
        //   4649: iconst_1
        //   4650: aaload
        //   4651: aload #15
        //   4653: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   4656: istore #25
        //   4658: iload #25
        //   4660: ifne -> 4730
        //   4663: aload #39
        //   4665: getfield mang : [[Ljava/lang/String;
        //   4668: iload #6
        //   4670: aaload
        //   4671: iconst_1
        //   4672: aaload
        //   4673: aload #18
        //   4675: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   4678: ifne -> 4730
        //   4681: aload #39
        //   4683: getfield mang : [[Ljava/lang/String;
        //   4686: iload #6
        //   4688: aaload
        //   4689: iconst_1
        //   4690: aaload
        //   4691: ldc_w 'xg'
        //   4694: invokevirtual startsWith : (Ljava/lang/String;)Z
        //   4697: istore #25
        //   4699: iload #25
        //   4701: ifeq -> 4707
        //   4704: goto -> 4730
        //   4707: aload #4
        //   4709: astore #32
        //   4711: aload #11
        //   4713: astore #4
        //   4715: aload #28
        //   4717: astore #11
        //   4719: aload #4
        //   4721: astore #28
        //   4723: aload #31
        //   4725: astore #4
        //   4727: goto -> 9538
        //   4730: aload #39
        //   4732: getfield mang : [[Ljava/lang/String;
        //   4735: iload #6
        //   4737: aaload
        //   4738: iconst_4
        //   4739: aaload
        //   4740: astore #27
        //   4742: aload #15
        //   4744: astore_3
        //   4745: aload #27
        //   4747: aload #28
        //   4749: invokevirtual indexOf : (Ljava/lang/String;)I
        //   4752: iconst_m1
        //   4753: if_icmpne -> 6783
        //   4756: aload #15
        //   4758: astore_3
        //   4759: aload #39
        //   4761: getfield mang : [[Ljava/lang/String;
        //   4764: iload #6
        //   4766: aaload
        //   4767: iconst_4
        //   4768: aaload
        //   4769: astore #32
        //   4771: aload #15
        //   4773: astore_3
        //   4774: aload #32
        //   4776: aload #4
        //   4778: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   4781: astore #26
        //   4783: iconst_0
        //   4784: istore #30
        //   4786: iconst_0
        //   4787: istore #10
        //   4789: aload #15
        //   4791: astore #27
        //   4793: aload #27
        //   4795: astore_3
        //   4796: aload #26
        //   4798: arraylength
        //   4799: istore #37
        //   4801: iload #10
        //   4803: iload #37
        //   4805: if_icmpge -> 4902
        //   4808: aload #26
        //   4810: iload #10
        //   4812: aaload
        //   4813: ldc_w ','
        //   4816: aload #8
        //   4818: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   4821: invokevirtual length : ()I
        //   4824: istore #37
        //   4826: iload #37
        //   4828: iconst_2
        //   4829: if_icmpne -> 4860
        //   4832: aload #26
        //   4834: iload #10
        //   4836: aaload
        //   4837: ldc_w ','
        //   4840: aload #8
        //   4842: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   4845: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   4848: ifne -> 4854
        //   4851: goto -> 4860
        //   4854: iinc #10, 1
        //   4857: goto -> 4793
        //   4860: iconst_1
        //   4861: istore #30
        //   4863: goto -> 4902
        //   4866: astore #15
        //   4868: aload #17
        //   4870: astore #15
        //   4872: aload #28
        //   4874: astore #32
        //   4876: aload #4
        //   4878: astore #17
        //   4880: aload #27
        //   4882: astore #4
        //   4884: aload #11
        //   4886: astore #28
        //   4888: aload #29
        //   4890: astore_3
        //   4891: aload #31
        //   4893: astore #27
        //   4895: aload #32
        //   4897: astore #11
        //   4899: goto -> 10047
        //   4902: aload #27
        //   4904: astore #15
        //   4906: aload #32
        //   4908: astore_3
        //   4909: iload #30
        //   4911: ifne -> 4969
        //   4914: aload #32
        //   4916: astore_3
        //   4917: aload #26
        //   4919: arraylength
        //   4920: iconst_5
        //   4921: if_icmpge -> 4969
        //   4924: aload #32
        //   4926: invokestatic XulySo : (Ljava/lang/String;)Ljava/lang/String;
        //   4929: astore_3
        //   4930: goto -> 4969
        //   4933: astore #15
        //   4935: aload #17
        //   4937: astore #15
        //   4939: aload #28
        //   4941: astore #32
        //   4943: aload #4
        //   4945: astore #17
        //   4947: aload #27
        //   4949: astore #4
        //   4951: aload #11
        //   4953: astore #28
        //   4955: aload #29
        //   4957: astore_3
        //   4958: aload #31
        //   4960: astore #27
        //   4962: aload #32
        //   4964: astore #11
        //   4966: goto -> 10047
        //   4969: aload_3
        //   4970: aload #4
        //   4972: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   4975: astore #40
        //   4977: iconst_0
        //   4978: istore #10
        //   4980: aload #40
        //   4982: arraylength
        //   4983: istore #37
        //   4985: iload #10
        //   4987: iload #37
        //   4989: if_icmpge -> 5702
        //   4992: aload #40
        //   4994: iload #10
        //   4996: aaload
        //   4997: invokestatic XulySo : (Ljava/lang/String;)Ljava/lang/String;
        //   5000: astore #23
        //   5002: aload #23
        //   5004: invokevirtual length : ()I
        //   5007: iconst_5
        //   5008: if_icmplt -> 5492
        //   5011: aload #23
        //   5013: invokevirtual length : ()I
        //   5016: bipush #12
        //   5018: if_icmpgt -> 5492
        //   5021: aload #23
        //   5023: aload #28
        //   5025: invokevirtual indexOf : (Ljava/lang/String;)I
        //   5028: iconst_m1
        //   5029: if_icmple -> 5035
        //   5032: goto -> 5492
        //   5035: aload #39
        //   5037: getfield mang : [[Ljava/lang/String;
        //   5040: iload #6
        //   5042: aaload
        //   5043: iconst_1
        //   5044: aaload
        //   5045: astore #27
        //   5047: aload #27
        //   5049: aload #18
        //   5051: if_acmpne -> 5192
        //   5054: aload #23
        //   5056: invokevirtual length : ()I
        //   5059: bipush #8
        //   5061: if_icmpge -> 5192
        //   5064: aload #39
        //   5066: getfield mang : [[Ljava/lang/String;
        //   5069: iload #6
        //   5071: aaload
        //   5072: astore #23
        //   5074: new java/lang/StringBuilder
        //   5077: astore #26
        //   5079: aload #26
        //   5081: invokespecial <init> : ()V
        //   5084: aload #4
        //   5086: astore #32
        //   5088: aload #11
        //   5090: astore #27
        //   5092: aload #26
        //   5094: aload #27
        //   5096: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5099: pop
        //   5100: aload #26
        //   5102: aload #17
        //   5104: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5107: pop
        //   5108: aload #23
        //   5110: iconst_4
        //   5111: aload #26
        //   5113: invokevirtual toString : ()Ljava/lang/String;
        //   5116: aastore
        //   5117: goto -> 5454
        //   5120: astore #4
        //   5122: aload #28
        //   5124: astore #11
        //   5126: aload #15
        //   5128: astore #4
        //   5130: aload #27
        //   5132: astore #28
        //   5134: aload #17
        //   5136: astore #15
        //   5138: aload #29
        //   5140: astore_3
        //   5141: aload #31
        //   5143: astore #27
        //   5145: aload #32
        //   5147: astore #17
        //   5149: goto -> 10047
        //   5152: astore #27
        //   5154: aload #4
        //   5156: astore #32
        //   5158: aload #11
        //   5160: astore #27
        //   5162: aload #28
        //   5164: astore #11
        //   5166: aload #15
        //   5168: astore #4
        //   5170: aload #27
        //   5172: astore #28
        //   5174: aload #17
        //   5176: astore #15
        //   5178: aload #29
        //   5180: astore_3
        //   5181: aload #31
        //   5183: astore #27
        //   5185: aload #32
        //   5187: astore #17
        //   5189: goto -> 10047
        //   5192: aload #4
        //   5194: astore #32
        //   5196: aload #11
        //   5198: astore #26
        //   5200: aload #17
        //   5202: astore #27
        //   5204: aload #23
        //   5206: ldc_w ','
        //   5209: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   5212: astore #41
        //   5214: iconst_0
        //   5215: istore #37
        //   5217: aload #17
        //   5219: astore #27
        //   5221: iload #37
        //   5223: aload #41
        //   5225: arraylength
        //   5226: if_icmpge -> 5454
        //   5229: aload #17
        //   5231: astore #27
        //   5233: aload #41
        //   5235: iload #37
        //   5237: aaload
        //   5238: invokevirtual length : ()I
        //   5241: istore #42
        //   5243: iload #42
        //   5245: iconst_2
        //   5246: if_icmpne -> 5273
        //   5249: aload #17
        //   5251: astore #27
        //   5253: aload #41
        //   5255: iload #37
        //   5257: aaload
        //   5258: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   5261: ifne -> 5267
        //   5264: goto -> 5273
        //   5267: iinc #37, 1
        //   5270: goto -> 5217
        //   5273: aload #17
        //   5275: astore #27
        //   5277: aload #39
        //   5279: getfield mang : [[Ljava/lang/String;
        //   5282: iload #6
        //   5284: aaload
        //   5285: iconst_2
        //   5286: aaload
        //   5287: invokevirtual length : ()I
        //   5290: iconst_4
        //   5291: if_icmple -> 5374
        //   5294: aload #17
        //   5296: astore #27
        //   5298: aload #39
        //   5300: getfield mang : [[Ljava/lang/String;
        //   5303: iload #6
        //   5305: aaload
        //   5306: astore #23
        //   5308: aload #17
        //   5310: astore #27
        //   5312: new java/lang/StringBuilder
        //   5315: astore #32
        //   5317: aload #17
        //   5319: astore #27
        //   5321: aload #32
        //   5323: invokespecial <init> : ()V
        //   5326: aload #17
        //   5328: astore #27
        //   5330: aload #32
        //   5332: aload #26
        //   5334: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5337: pop
        //   5338: aload #17
        //   5340: astore #27
        //   5342: aload #32
        //   5344: aload #39
        //   5346: getfield mang : [[Ljava/lang/String;
        //   5349: iload #6
        //   5351: aaload
        //   5352: iconst_2
        //   5353: aaload
        //   5354: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5357: pop
        //   5358: aload #17
        //   5360: astore #27
        //   5362: aload #23
        //   5364: iconst_4
        //   5365: aload #32
        //   5367: invokevirtual toString : ()Ljava/lang/String;
        //   5370: aastore
        //   5371: goto -> 5454
        //   5374: aload #17
        //   5376: astore #27
        //   5378: aload #39
        //   5380: getfield mang : [[Ljava/lang/String;
        //   5383: iload #6
        //   5385: aaload
        //   5386: astore #23
        //   5388: aload #17
        //   5390: astore #27
        //   5392: new java/lang/StringBuilder
        //   5395: astore #32
        //   5397: aload #17
        //   5399: astore #27
        //   5401: aload #32
        //   5403: invokespecial <init> : ()V
        //   5406: aload #17
        //   5408: astore #27
        //   5410: aload #32
        //   5412: aload #26
        //   5414: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5417: pop
        //   5418: aload #17
        //   5420: astore #27
        //   5422: aload #32
        //   5424: aload #39
        //   5426: getfield mang : [[Ljava/lang/String;
        //   5429: iload #6
        //   5431: aaload
        //   5432: iconst_0
        //   5433: aaload
        //   5434: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5437: pop
        //   5438: aload #17
        //   5440: astore #27
        //   5442: aload #23
        //   5444: iconst_4
        //   5445: aload #32
        //   5447: invokevirtual toString : ()Ljava/lang/String;
        //   5450: aastore
        //   5451: goto -> 5454
        //   5454: iinc #10, 1
        //   5457: goto -> 4980
        //   5460: astore #4
        //   5462: aload #28
        //   5464: astore #11
        //   5466: aload #15
        //   5468: astore #4
        //   5470: aload #26
        //   5472: astore #28
        //   5474: aload #27
        //   5476: astore #15
        //   5478: aload #29
        //   5480: astore_3
        //   5481: aload #31
        //   5483: astore #27
        //   5485: aload #32
        //   5487: astore #17
        //   5489: goto -> 10047
        //   5492: aload #17
        //   5494: astore_3
        //   5495: aload #11
        //   5497: astore #32
        //   5499: aload_3
        //   5500: astore #27
        //   5502: aload #40
        //   5504: iload #10
        //   5506: aaload
        //   5507: invokevirtual length : ()I
        //   5510: iconst_4
        //   5511: if_icmple -> 5588
        //   5514: aload_3
        //   5515: astore #27
        //   5517: aload #39
        //   5519: getfield mang : [[Ljava/lang/String;
        //   5522: iload #6
        //   5524: aaload
        //   5525: astore #26
        //   5527: aload_3
        //   5528: astore #27
        //   5530: new java/lang/StringBuilder
        //   5533: astore #23
        //   5535: aload_3
        //   5536: astore #27
        //   5538: aload #23
        //   5540: invokespecial <init> : ()V
        //   5543: aload_3
        //   5544: astore #27
        //   5546: aload #23
        //   5548: aload #32
        //   5550: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5553: pop
        //   5554: aload_3
        //   5555: astore #27
        //   5557: aload #23
        //   5559: aload #39
        //   5561: getfield mang : [[Ljava/lang/String;
        //   5564: iload #6
        //   5566: aaload
        //   5567: iconst_2
        //   5568: aaload
        //   5569: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5572: pop
        //   5573: aload_3
        //   5574: astore #27
        //   5576: aload #26
        //   5578: iconst_4
        //   5579: aload #23
        //   5581: invokevirtual toString : ()Ljava/lang/String;
        //   5584: aastore
        //   5585: goto -> 5702
        //   5588: aload_3
        //   5589: astore #27
        //   5591: aload #39
        //   5593: getfield mang : [[Ljava/lang/String;
        //   5596: iload #6
        //   5598: aaload
        //   5599: astore #26
        //   5601: aload_3
        //   5602: astore #27
        //   5604: new java/lang/StringBuilder
        //   5607: astore #23
        //   5609: aload_3
        //   5610: astore #27
        //   5612: aload #23
        //   5614: invokespecial <init> : ()V
        //   5617: aload_3
        //   5618: astore #27
        //   5620: aload #23
        //   5622: aload #32
        //   5624: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5627: pop
        //   5628: aload_3
        //   5629: astore #27
        //   5631: aload #23
        //   5633: aload #39
        //   5635: getfield mang : [[Ljava/lang/String;
        //   5638: iload #6
        //   5640: aaload
        //   5641: iconst_0
        //   5642: aaload
        //   5643: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5646: pop
        //   5647: aload_3
        //   5648: astore #27
        //   5650: aload #26
        //   5652: iconst_4
        //   5653: aload #23
        //   5655: invokevirtual toString : ()Ljava/lang/String;
        //   5658: aastore
        //   5659: goto -> 5702
        //   5662: astore #27
        //   5664: aload #4
        //   5666: astore #32
        //   5668: aload #11
        //   5670: astore #27
        //   5672: aload #28
        //   5674: astore #11
        //   5676: aload #15
        //   5678: astore #4
        //   5680: aload #27
        //   5682: astore #28
        //   5684: aload #17
        //   5686: astore #15
        //   5688: aload #29
        //   5690: astore_3
        //   5691: aload #31
        //   5693: astore #27
        //   5695: aload #32
        //   5697: astore #17
        //   5699: goto -> 10047
        //   5702: aload #4
        //   5704: astore #32
        //   5706: aload #11
        //   5708: astore_3
        //   5709: aload #17
        //   5711: astore #27
        //   5713: aload #39
        //   5715: getfield mang : [[Ljava/lang/String;
        //   5718: iload #6
        //   5720: aaload
        //   5721: iconst_5
        //   5722: aaload
        //   5723: aload #28
        //   5725: invokevirtual indexOf : (Ljava/lang/String;)I
        //   5728: iconst_m1
        //   5729: if_icmpne -> 6069
        //   5732: aload #17
        //   5734: astore #27
        //   5736: aload #39
        //   5738: getfield caidat_tg : Lorg/json/JSONObject;
        //   5741: ldc_w 'xien_nhan'
        //   5744: invokevirtual getInt : (Ljava/lang/String;)I
        //   5747: iconst_1
        //   5748: if_icmpne -> 5914
        //   5751: aload #17
        //   5753: astore #27
        //   5755: aload #39
        //   5757: getfield mang : [[Ljava/lang/String;
        //   5760: iload #6
        //   5762: aaload
        //   5763: iconst_3
        //   5764: aaload
        //   5765: ldc_w 'd'
        //   5768: invokevirtual indexOf : (Ljava/lang/String;)I
        //   5771: istore #30
        //   5773: iload #30
        //   5775: iconst_m1
        //   5776: if_icmple -> 5914
        //   5779: aload #39
        //   5781: getfield mang : [[Ljava/lang/String;
        //   5784: iload #6
        //   5786: aaload
        //   5787: astore #26
        //   5789: new java/lang/StringBuilder
        //   5792: astore #27
        //   5794: aload #27
        //   5796: invokespecial <init> : ()V
        //   5799: aload #27
        //   5801: aload #12
        //   5803: invokestatic parseInt : (Ljava/lang/String;)I
        //   5806: bipush #10
        //   5808: imul
        //   5809: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   5812: pop
        //   5813: aload #27
        //   5815: aload #8
        //   5817: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5820: pop
        //   5821: aload #26
        //   5823: iconst_5
        //   5824: aload #27
        //   5826: invokevirtual toString : ()Ljava/lang/String;
        //   5829: aastore
        //   5830: goto -> 6069
        //   5833: astore #27
        //   5835: aload #17
        //   5837: astore #27
        //   5839: aload #39
        //   5841: getfield mang : [[Ljava/lang/String;
        //   5844: iload #6
        //   5846: aaload
        //   5847: astore #12
        //   5849: aload #17
        //   5851: astore #27
        //   5853: new java/lang/StringBuilder
        //   5856: astore #26
        //   5858: aload #17
        //   5860: astore #27
        //   5862: aload #26
        //   5864: invokespecial <init> : ()V
        //   5867: aload #17
        //   5869: astore #27
        //   5871: aload #26
        //   5873: aload_3
        //   5874: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5877: pop
        //   5878: aload #17
        //   5880: astore #27
        //   5882: aload #26
        //   5884: aload #39
        //   5886: getfield mang : [[Ljava/lang/String;
        //   5889: iload #6
        //   5891: aaload
        //   5892: iconst_3
        //   5893: aaload
        //   5894: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5897: pop
        //   5898: aload #17
        //   5900: astore #27
        //   5902: aload #12
        //   5904: iconst_5
        //   5905: aload #26
        //   5907: invokevirtual toString : ()Ljava/lang/String;
        //   5910: aastore
        //   5911: goto -> 5830
        //   5914: aload #17
        //   5916: astore #27
        //   5918: aload #39
        //   5920: getfield caidat_tg : Lorg/json/JSONObject;
        //   5923: ldc_w 'xien_nhan'
        //   5926: invokevirtual getInt : (Ljava/lang/String;)I
        //   5929: istore #30
        //   5931: iload #30
        //   5933: iconst_2
        //   5934: if_icmpne -> 6069
        //   5937: aload #39
        //   5939: getfield mang : [[Ljava/lang/String;
        //   5942: iload #6
        //   5944: aaload
        //   5945: astore #26
        //   5947: new java/lang/StringBuilder
        //   5950: astore #27
        //   5952: aload #27
        //   5954: invokespecial <init> : ()V
        //   5957: aload #27
        //   5959: aload #12
        //   5961: invokestatic parseInt : (Ljava/lang/String;)I
        //   5964: bipush #10
        //   5966: imul
        //   5967: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   5970: pop
        //   5971: aload #27
        //   5973: aload #8
        //   5975: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   5978: pop
        //   5979: aload #26
        //   5981: iconst_5
        //   5982: aload #27
        //   5984: invokevirtual toString : ()Ljava/lang/String;
        //   5987: aastore
        //   5988: goto -> 6069
        //   5991: astore #27
        //   5993: aload #17
        //   5995: astore #27
        //   5997: aload #39
        //   5999: getfield mang : [[Ljava/lang/String;
        //   6002: iload #6
        //   6004: aaload
        //   6005: astore #12
        //   6007: aload #17
        //   6009: astore #27
        //   6011: new java/lang/StringBuilder
        //   6014: astore #26
        //   6016: aload #17
        //   6018: astore #27
        //   6020: aload #26
        //   6022: invokespecial <init> : ()V
        //   6025: aload #17
        //   6027: astore #27
        //   6029: aload #26
        //   6031: aload_3
        //   6032: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6035: pop
        //   6036: aload #17
        //   6038: astore #27
        //   6040: aload #26
        //   6042: aload #39
        //   6044: getfield mang : [[Ljava/lang/String;
        //   6047: iload #6
        //   6049: aaload
        //   6050: iconst_3
        //   6051: aaload
        //   6052: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6055: pop
        //   6056: aload #17
        //   6058: astore #27
        //   6060: aload #12
        //   6062: iconst_5
        //   6063: aload #26
        //   6065: invokevirtual toString : ()Ljava/lang/String;
        //   6068: aastore
        //   6069: iconst_0
        //   6070: istore #30
        //   6072: aload #40
        //   6074: astore #11
        //   6076: aload #11
        //   6078: arraylength
        //   6079: istore #10
        //   6081: iload #30
        //   6083: iload #10
        //   6085: if_icmpge -> 6624
        //   6088: aload #11
        //   6090: iload #30
        //   6092: aaload
        //   6093: invokestatic XulySo : (Ljava/lang/String;)Ljava/lang/String;
        //   6096: astore #4
        //   6098: goto -> 6154
        //   6101: astore #4
        //   6103: aload #39
        //   6105: getfield mang : [[Ljava/lang/String;
        //   6108: iload #6
        //   6110: aaload
        //   6111: astore #4
        //   6113: new java/lang/StringBuilder
        //   6116: astore #27
        //   6118: aload #27
        //   6120: invokespecial <init> : ()V
        //   6123: aload #27
        //   6125: aload_3
        //   6126: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6129: pop
        //   6130: aload #27
        //   6132: aload #11
        //   6134: iload #30
        //   6136: aaload
        //   6137: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6140: pop
        //   6141: aload #4
        //   6143: iconst_4
        //   6144: aload #27
        //   6146: invokevirtual toString : ()Ljava/lang/String;
        //   6149: aastore
        //   6150: aload #24
        //   6152: astore #4
        //   6154: aload #28
        //   6156: astore #24
        //   6158: aload #4
        //   6160: aload #28
        //   6162: invokevirtual indexOf : (Ljava/lang/String;)I
        //   6165: iconst_m1
        //   6166: if_icmpne -> 6464
        //   6169: aload #28
        //   6171: astore #24
        //   6173: aload #4
        //   6175: ldc_w ','
        //   6178: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   6181: astore #12
        //   6183: iconst_0
        //   6184: istore #37
        //   6186: iconst_0
        //   6187: istore #10
        //   6189: aload #11
        //   6191: astore #27
        //   6193: aload #28
        //   6195: astore #24
        //   6197: iload #10
        //   6199: aload #12
        //   6201: arraylength
        //   6202: if_icmpge -> 6251
        //   6205: aload #28
        //   6207: astore #24
        //   6209: aload #4
        //   6211: invokevirtual length : ()I
        //   6214: istore #42
        //   6216: aload #28
        //   6218: astore #11
        //   6220: iload #42
        //   6222: aload #4
        //   6224: aload #12
        //   6226: iload #10
        //   6228: aaload
        //   6229: aload #8
        //   6231: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   6234: invokevirtual length : ()I
        //   6237: isub
        //   6238: iconst_2
        //   6239: if_icmple -> 6245
        //   6242: iconst_1
        //   6243: istore #37
        //   6245: iinc #10, 1
        //   6248: goto -> 6193
        //   6251: aload #28
        //   6253: astore #11
        //   6255: aload #4
        //   6257: invokevirtual length : ()I
        //   6260: iconst_5
        //   6261: if_icmplt -> 6381
        //   6264: aload #28
        //   6266: astore #11
        //   6268: aload #4
        //   6270: invokevirtual length : ()I
        //   6273: bipush #12
        //   6275: if_icmple -> 6281
        //   6278: goto -> 6381
        //   6281: iload #37
        //   6283: iconst_1
        //   6284: if_icmpne -> 6367
        //   6287: aload #28
        //   6289: astore #11
        //   6291: aload #39
        //   6293: getfield mang : [[Ljava/lang/String;
        //   6296: iload #6
        //   6298: aaload
        //   6299: astore #24
        //   6301: aload #28
        //   6303: astore #11
        //   6305: new java/lang/StringBuilder
        //   6308: astore #27
        //   6310: aload #28
        //   6312: astore #11
        //   6314: aload #27
        //   6316: invokespecial <init> : ()V
        //   6319: aload #28
        //   6321: astore #11
        //   6323: aload #27
        //   6325: aload_3
        //   6326: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6329: pop
        //   6330: aload #28
        //   6332: astore #11
        //   6334: aload #27
        //   6336: aload #4
        //   6338: iconst_0
        //   6339: iconst_2
        //   6340: invokevirtual substring : (II)Ljava/lang/String;
        //   6343: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6346: pop
        //   6347: aload #28
        //   6349: astore #11
        //   6351: aload #24
        //   6353: iconst_4
        //   6354: aload #27
        //   6356: invokevirtual toString : ()Ljava/lang/String;
        //   6359: aastore
        //   6360: aload #28
        //   6362: astore #11
        //   6364: goto -> 6544
        //   6367: iinc #30, 1
        //   6370: aload #27
        //   6372: astore #11
        //   6374: aload #4
        //   6376: astore #24
        //   6378: goto -> 6076
        //   6381: aload #28
        //   6383: astore #11
        //   6385: aload #39
        //   6387: getfield mang : [[Ljava/lang/String;
        //   6390: iload #6
        //   6392: aaload
        //   6393: astore #24
        //   6395: aload #28
        //   6397: astore #11
        //   6399: new java/lang/StringBuilder
        //   6402: astore #27
        //   6404: aload #28
        //   6406: astore #11
        //   6408: aload #27
        //   6410: invokespecial <init> : ()V
        //   6413: aload #28
        //   6415: astore #11
        //   6417: aload #27
        //   6419: aload_3
        //   6420: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6423: pop
        //   6424: aload #28
        //   6426: astore #11
        //   6428: aload #27
        //   6430: aload #39
        //   6432: getfield mang : [[Ljava/lang/String;
        //   6435: iload #6
        //   6437: aaload
        //   6438: iconst_2
        //   6439: aaload
        //   6440: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6443: pop
        //   6444: aload #28
        //   6446: astore #11
        //   6448: aload #24
        //   6450: iconst_4
        //   6451: aload #27
        //   6453: invokevirtual toString : ()Ljava/lang/String;
        //   6456: aastore
        //   6457: aload #28
        //   6459: astore #11
        //   6461: goto -> 6544
        //   6464: aload #28
        //   6466: astore #11
        //   6468: aload #39
        //   6470: getfield mang : [[Ljava/lang/String;
        //   6473: iload #6
        //   6475: aaload
        //   6476: astore #27
        //   6478: aload #28
        //   6480: astore #11
        //   6482: new java/lang/StringBuilder
        //   6485: astore #24
        //   6487: aload #28
        //   6489: astore #11
        //   6491: aload #24
        //   6493: invokespecial <init> : ()V
        //   6496: aload #28
        //   6498: astore #11
        //   6500: aload #24
        //   6502: aload_3
        //   6503: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6506: pop
        //   6507: aload #28
        //   6509: astore #11
        //   6511: aload #24
        //   6513: aload #39
        //   6515: getfield mang : [[Ljava/lang/String;
        //   6518: iload #6
        //   6520: aaload
        //   6521: iconst_2
        //   6522: aaload
        //   6523: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6526: pop
        //   6527: aload #28
        //   6529: astore #11
        //   6531: aload #27
        //   6533: iconst_4
        //   6534: aload #24
        //   6536: invokevirtual toString : ()Ljava/lang/String;
        //   6539: aastore
        //   6540: aload #28
        //   6542: astore #11
        //   6544: aload_3
        //   6545: astore #28
        //   6547: aload #4
        //   6549: astore #24
        //   6551: aload #31
        //   6553: astore #4
        //   6555: goto -> 9538
        //   6558: astore #28
        //   6560: aload #4
        //   6562: astore #24
        //   6564: aload #15
        //   6566: astore #4
        //   6568: aload_3
        //   6569: astore #28
        //   6571: aload #17
        //   6573: astore #15
        //   6575: aload #29
        //   6577: astore_3
        //   6578: aload #31
        //   6580: astore #27
        //   6582: aload #32
        //   6584: astore #17
        //   6586: goto -> 10047
        //   6589: astore #11
        //   6591: aload #24
        //   6593: astore #11
        //   6595: aload #4
        //   6597: astore #24
        //   6599: aload #15
        //   6601: astore #4
        //   6603: aload_3
        //   6604: astore #28
        //   6606: aload #17
        //   6608: astore #15
        //   6610: aload #29
        //   6612: astore_3
        //   6613: aload #31
        //   6615: astore #27
        //   6617: aload #32
        //   6619: astore #17
        //   6621: goto -> 10047
        //   6624: aload #28
        //   6626: astore #11
        //   6628: aload_3
        //   6629: astore #28
        //   6631: aload #31
        //   6633: astore #4
        //   6635: goto -> 9538
        //   6638: astore #4
        //   6640: aload #28
        //   6642: astore #11
        //   6644: aload #15
        //   6646: astore #4
        //   6648: aload_3
        //   6649: astore #28
        //   6651: aload #17
        //   6653: astore #15
        //   6655: aload #29
        //   6657: astore_3
        //   6658: aload #31
        //   6660: astore #27
        //   6662: aload #32
        //   6664: astore #17
        //   6666: goto -> 10047
        //   6669: aload #4
        //   6671: astore #17
        //   6673: aload #11
        //   6675: astore_3
        //   6676: astore #4
        //   6678: aload #28
        //   6680: astore #11
        //   6682: aload #15
        //   6684: astore #4
        //   6686: aload_3
        //   6687: astore #28
        //   6689: aload #27
        //   6691: astore #15
        //   6693: aload #29
        //   6695: astore_3
        //   6696: aload #31
        //   6698: astore #27
        //   6700: goto -> 10047
        //   6703: astore #27
        //   6705: aload #28
        //   6707: astore #32
        //   6709: aload #4
        //   6711: astore #12
        //   6713: aload #15
        //   6715: astore #4
        //   6717: aload #11
        //   6719: astore #28
        //   6721: aload #17
        //   6723: astore #15
        //   6725: aload #29
        //   6727: astore_3
        //   6728: aload #31
        //   6730: astore #27
        //   6732: aload #12
        //   6734: astore #17
        //   6736: aload #32
        //   6738: astore #11
        //   6740: goto -> 10047
        //   6743: astore #27
        //   6745: aload #28
        //   6747: astore #32
        //   6749: aload #4
        //   6751: astore #12
        //   6753: aload #15
        //   6755: astore #4
        //   6757: aload #11
        //   6759: astore #28
        //   6761: aload #17
        //   6763: astore #15
        //   6765: aload #29
        //   6767: astore_3
        //   6768: aload #31
        //   6770: astore #27
        //   6772: aload #12
        //   6774: astore #17
        //   6776: aload #32
        //   6778: astore #11
        //   6780: goto -> 10047
        //   6783: aload #28
        //   6785: astore #27
        //   6787: aload #4
        //   6789: astore #32
        //   6791: aload #11
        //   6793: astore #28
        //   6795: aload #31
        //   6797: astore #4
        //   6799: aload #27
        //   6801: astore #11
        //   6803: goto -> 9538
        //   6806: astore #15
        //   6808: aload #28
        //   6810: astore #32
        //   6812: aload #4
        //   6814: astore #12
        //   6816: aload_3
        //   6817: astore #4
        //   6819: aload #17
        //   6821: astore #15
        //   6823: aload #11
        //   6825: astore #28
        //   6827: aload #29
        //   6829: astore_3
        //   6830: aload #31
        //   6832: astore #27
        //   6834: aload #12
        //   6836: astore #17
        //   6838: aload #32
        //   6840: astore #11
        //   6842: goto -> 10047
        //   6845: astore #27
        //   6847: aload #4
        //   6849: astore #32
        //   6851: aload #11
        //   6853: astore #27
        //   6855: aload #28
        //   6857: astore #11
        //   6859: aload #15
        //   6861: astore #4
        //   6863: aload #17
        //   6865: astore #15
        //   6867: aload #27
        //   6869: astore #28
        //   6871: aload #29
        //   6873: astore_3
        //   6874: aload #31
        //   6876: astore #27
        //   6878: aload #32
        //   6880: astore #17
        //   6882: goto -> 10047
        //   6885: aload #28
        //   6887: astore #11
        //   6889: aload #4
        //   6891: astore #32
        //   6893: aload_3
        //   6894: astore #28
        //   6896: aload #39
        //   6898: getfield mang : [[Ljava/lang/String;
        //   6901: iload #6
        //   6903: aaload
        //   6904: iconst_1
        //   6905: aaload
        //   6906: astore #4
        //   6908: aload #4
        //   6910: ldc 'lo'
        //   6912: if_acmpne -> 7129
        //   6915: aload #39
        //   6917: getfield mang : [[Ljava/lang/String;
        //   6920: iload #6
        //   6922: aaload
        //   6923: iconst_3
        //   6924: aaload
        //   6925: ldc_w 'tr'
        //   6928: invokevirtual indexOf : (Ljava/lang/String;)I
        //   6931: iconst_m1
        //   6932: if_icmple -> 7129
        //   6935: aload #39
        //   6937: getfield mang : [[Ljava/lang/String;
        //   6940: iload #6
        //   6942: aaload
        //   6943: astore #27
        //   6945: new java/lang/StringBuilder
        //   6948: astore #4
        //   6950: aload #4
        //   6952: invokespecial <init> : ()V
        //   6955: aload #4
        //   6957: aload #28
        //   6959: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6962: pop
        //   6963: aload #4
        //   6965: aload #39
        //   6967: getfield mang : [[Ljava/lang/String;
        //   6970: iload #6
        //   6972: aaload
        //   6973: iconst_3
        //   6974: aaload
        //   6975: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   6978: pop
        //   6979: aload #27
        //   6981: iconst_5
        //   6982: aload #4
        //   6984: invokevirtual toString : ()Ljava/lang/String;
        //   6987: aastore
        //   6988: aload #39
        //   6990: getfield mang : [[Ljava/lang/String;
        //   6993: iload #6
        //   6995: aaload
        //   6996: astore #27
        //   6998: aload #39
        //   7000: getfield mang : [[Ljava/lang/String;
        //   7003: iload #6
        //   7005: aaload
        //   7006: iconst_0
        //   7007: aaload
        //   7008: astore_3
        //   7009: aload #39
        //   7011: getfield mang : [[Ljava/lang/String;
        //   7014: iload #6
        //   7016: aaload
        //   7017: iconst_3
        //   7018: aaload
        //   7019: astore #4
        //   7021: new java/lang/StringBuilder
        //   7024: astore #12
        //   7026: aload #12
        //   7028: invokespecial <init> : ()V
        //   7031: aload #12
        //   7033: aload #21
        //   7035: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7038: pop
        //   7039: aload #12
        //   7041: aload #39
        //   7043: getfield mang : [[Ljava/lang/String;
        //   7046: iload #6
        //   7048: aaload
        //   7049: iconst_3
        //   7050: aaload
        //   7051: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7054: pop
        //   7055: aload #12
        //   7057: aload #19
        //   7059: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7062: pop
        //   7063: aload #27
        //   7065: iconst_0
        //   7066: aload_3
        //   7067: aload #4
        //   7069: aload #12
        //   7071: invokevirtual toString : ()Ljava/lang/String;
        //   7074: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   7077: aastore
        //   7078: goto -> 7129
        //   7081: astore #4
        //   7083: aload #15
        //   7085: astore #4
        //   7087: aload #17
        //   7089: astore #15
        //   7091: aload #29
        //   7093: astore_3
        //   7094: aload #31
        //   7096: astore #27
        //   7098: aload #32
        //   7100: astore #17
        //   7102: goto -> 10047
        //   7105: astore #4
        //   7107: aload #15
        //   7109: astore #4
        //   7111: aload #17
        //   7113: astore #15
        //   7115: aload #29
        //   7117: astore_3
        //   7118: aload #31
        //   7120: astore #27
        //   7122: aload #32
        //   7124: astore #17
        //   7126: goto -> 10047
        //   7129: aload #19
        //   7131: astore #43
        //   7133: aload #21
        //   7135: astore #44
        //   7137: getstatic tamhoang/ldpro4/MainActivity.jSon_Setting : Lorg/json/JSONObject;
        //   7140: ldc_w 'canhbaodonvi'
        //   7143: invokevirtual getInt : (Ljava/lang/String;)I
        //   7146: istore #30
        //   7148: iload #30
        //   7150: iconst_1
        //   7151: if_icmpne -> 8361
        //   7154: aload #39
        //   7156: getfield mang : [[Ljava/lang/String;
        //   7159: iload #6
        //   7161: aaload
        //   7162: iconst_1
        //   7163: aaload
        //   7164: astore #4
        //   7166: aload #4
        //   7168: ldc 'lo'
        //   7170: if_acmpne -> 7382
        //   7173: aload #39
        //   7175: getfield mang : [[Ljava/lang/String;
        //   7178: iload #6
        //   7180: aaload
        //   7181: iconst_5
        //   7182: aaload
        //   7183: invokestatic parseInt : (Ljava/lang/String;)I
        //   7186: sipush #1000
        //   7189: if_icmple -> 7382
        //   7192: aload #39
        //   7194: getfield mang : [[Ljava/lang/String;
        //   7197: iload #6
        //   7199: aaload
        //   7200: iconst_3
        //   7201: aaload
        //   7202: ldc_w 'd'
        //   7205: invokevirtual indexOf : (Ljava/lang/String;)I
        //   7208: iconst_m1
        //   7209: if_icmpne -> 7382
        //   7212: aload #39
        //   7214: getfield mang : [[Ljava/lang/String;
        //   7217: iload #6
        //   7219: aaload
        //   7220: astore #27
        //   7222: new java/lang/StringBuilder
        //   7225: astore #4
        //   7227: aload #4
        //   7229: invokespecial <init> : ()V
        //   7232: aload #4
        //   7234: aload #28
        //   7236: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7239: pop
        //   7240: aload #4
        //   7242: aload #39
        //   7244: getfield mang : [[Ljava/lang/String;
        //   7247: iload #6
        //   7249: aaload
        //   7250: iconst_3
        //   7251: aaload
        //   7252: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7255: pop
        //   7256: aload #27
        //   7258: iconst_5
        //   7259: aload #4
        //   7261: invokevirtual toString : ()Ljava/lang/String;
        //   7264: aastore
        //   7265: aload #39
        //   7267: getfield mang : [[Ljava/lang/String;
        //   7270: iload #6
        //   7272: aaload
        //   7273: astore #4
        //   7275: aload #39
        //   7277: getfield mang : [[Ljava/lang/String;
        //   7280: iload #6
        //   7282: aaload
        //   7283: iconst_0
        //   7284: aaload
        //   7285: astore #12
        //   7287: aload #39
        //   7289: getfield mang : [[Ljava/lang/String;
        //   7292: iload #6
        //   7294: aaload
        //   7295: iconst_3
        //   7296: aaload
        //   7297: astore_3
        //   7298: new java/lang/StringBuilder
        //   7301: astore #27
        //   7303: aload #27
        //   7305: invokespecial <init> : ()V
        //   7308: aload #27
        //   7310: aload #44
        //   7312: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7315: pop
        //   7316: aload #27
        //   7318: aload #39
        //   7320: getfield mang : [[Ljava/lang/String;
        //   7323: iload #6
        //   7325: aaload
        //   7326: iconst_3
        //   7327: aaload
        //   7328: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7331: pop
        //   7332: aload #27
        //   7334: aload #43
        //   7336: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7339: pop
        //   7340: aload #4
        //   7342: iconst_0
        //   7343: aload #12
        //   7345: aload_3
        //   7346: aload #27
        //   7348: invokevirtual toString : ()Ljava/lang/String;
        //   7351: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   7354: aastore
        //   7355: goto -> 7382
        //   7358: astore #4
        //   7360: aload #15
        //   7362: astore #4
        //   7364: aload #17
        //   7366: astore #15
        //   7368: aload #29
        //   7370: astore_3
        //   7371: aload #31
        //   7373: astore #27
        //   7375: aload #32
        //   7377: astore #17
        //   7379: goto -> 10047
        //   7382: aload #39
        //   7384: getfield mang : [[Ljava/lang/String;
        //   7387: iload #6
        //   7389: aaload
        //   7390: iconst_1
        //   7391: aaload
        //   7392: astore #4
        //   7394: aload #4
        //   7396: ldc 'de dit db'
        //   7398: if_acmpeq -> 7480
        //   7401: aload #39
        //   7403: getfield mang : [[Ljava/lang/String;
        //   7406: iload #6
        //   7408: aaload
        //   7409: iconst_1
        //   7410: aaload
        //   7411: ldc 'de dau db'
        //   7413: if_acmpeq -> 7480
        //   7416: aload #39
        //   7418: getfield mang : [[Ljava/lang/String;
        //   7421: iload #6
        //   7423: aaload
        //   7424: iconst_1
        //   7425: aaload
        //   7426: ldc 'hai cua'
        //   7428: if_acmpeq -> 7480
        //   7431: aload #39
        //   7433: getfield mang : [[Ljava/lang/String;
        //   7436: iload #6
        //   7438: aaload
        //   7439: iconst_1
        //   7440: aaload
        //   7441: ldc 'de 8'
        //   7443: if_acmpeq -> 7480
        //   7446: aload #39
        //   7448: getfield mang : [[Ljava/lang/String;
        //   7451: iload #6
        //   7453: aaload
        //   7454: iconst_1
        //   7455: aaload
        //   7456: ldc 'de dit nhat'
        //   7458: if_acmpeq -> 7480
        //   7461: aload #39
        //   7463: getfield mang : [[Ljava/lang/String;
        //   7466: iload #6
        //   7468: aaload
        //   7469: iconst_1
        //   7470: aaload
        //   7471: astore #4
        //   7473: aload #4
        //   7475: ldc 'de dau nhat'
        //   7477: if_acmpne -> 7706
        //   7480: aload #39
        //   7482: getfield mang : [[Ljava/lang/String;
        //   7485: iload #6
        //   7487: aaload
        //   7488: iconst_5
        //   7489: aaload
        //   7490: invokestatic parseInt : (Ljava/lang/String;)I
        //   7493: istore #30
        //   7495: iload #30
        //   7497: sipush #5000
        //   7500: if_icmple -> 7706
        //   7503: aload #39
        //   7505: getfield mang : [[Ljava/lang/String;
        //   7508: iload #6
        //   7510: aaload
        //   7511: iconst_3
        //   7512: aaload
        //   7513: ldc_w 'n'
        //   7516: invokevirtual indexOf : (Ljava/lang/String;)I
        //   7519: iconst_m1
        //   7520: if_icmpne -> 7706
        //   7523: aload #39
        //   7525: getfield mang : [[Ljava/lang/String;
        //   7528: iload #6
        //   7530: aaload
        //   7531: iconst_3
        //   7532: aaload
        //   7533: ldc_w 'tr'
        //   7536: invokevirtual indexOf : (Ljava/lang/String;)I
        //   7539: iconst_m1
        //   7540: if_icmpne -> 7706
        //   7543: aload #39
        //   7545: getfield mang : [[Ljava/lang/String;
        //   7548: iload #6
        //   7550: aaload
        //   7551: iconst_3
        //   7552: aaload
        //   7553: ldc_w 'k'
        //   7556: invokevirtual indexOf : (Ljava/lang/String;)I
        //   7559: iconst_m1
        //   7560: if_icmpne -> 7706
        //   7563: aload #39
        //   7565: getfield mang : [[Ljava/lang/String;
        //   7568: iload #6
        //   7570: aaload
        //   7571: astore #4
        //   7573: new java/lang/StringBuilder
        //   7576: astore #27
        //   7578: aload #27
        //   7580: invokespecial <init> : ()V
        //   7583: aload #27
        //   7585: aload #28
        //   7587: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7590: pop
        //   7591: aload #27
        //   7593: aload #39
        //   7595: getfield mang : [[Ljava/lang/String;
        //   7598: iload #6
        //   7600: aaload
        //   7601: iconst_3
        //   7602: aaload
        //   7603: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7606: pop
        //   7607: aload #4
        //   7609: iconst_5
        //   7610: aload #27
        //   7612: invokevirtual toString : ()Ljava/lang/String;
        //   7615: aastore
        //   7616: aload #39
        //   7618: getfield mang : [[Ljava/lang/String;
        //   7621: iload #6
        //   7623: aaload
        //   7624: astore #12
        //   7626: aload #39
        //   7628: getfield mang : [[Ljava/lang/String;
        //   7631: iload #6
        //   7633: aaload
        //   7634: iconst_0
        //   7635: aaload
        //   7636: astore #4
        //   7638: aload #39
        //   7640: getfield mang : [[Ljava/lang/String;
        //   7643: iload #6
        //   7645: aaload
        //   7646: iconst_3
        //   7647: aaload
        //   7648: astore_3
        //   7649: new java/lang/StringBuilder
        //   7652: astore #27
        //   7654: aload #27
        //   7656: invokespecial <init> : ()V
        //   7659: aload #27
        //   7661: aload #44
        //   7663: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7666: pop
        //   7667: aload #27
        //   7669: aload #39
        //   7671: getfield mang : [[Ljava/lang/String;
        //   7674: iload #6
        //   7676: aaload
        //   7677: iconst_3
        //   7678: aaload
        //   7679: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7682: pop
        //   7683: aload #27
        //   7685: aload #43
        //   7687: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7690: pop
        //   7691: aload #12
        //   7693: iconst_0
        //   7694: aload #4
        //   7696: aload_3
        //   7697: aload #27
        //   7699: invokevirtual toString : ()Ljava/lang/String;
        //   7702: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   7705: aastore
        //   7706: aload #39
        //   7708: getfield mang : [[Ljava/lang/String;
        //   7711: iload #6
        //   7713: aaload
        //   7714: iconst_1
        //   7715: aaload
        //   7716: astore #4
        //   7718: aload #4
        //   7720: aload #34
        //   7722: if_acmpne -> 7947
        //   7725: aload #39
        //   7727: getfield mang : [[Ljava/lang/String;
        //   7730: iload #6
        //   7732: aaload
        //   7733: iconst_5
        //   7734: aaload
        //   7735: invokestatic parseInt : (Ljava/lang/String;)I
        //   7738: sipush #2000
        //   7741: if_icmple -> 7947
        //   7744: aload #39
        //   7746: getfield mang : [[Ljava/lang/String;
        //   7749: iload #6
        //   7751: aaload
        //   7752: iconst_3
        //   7753: aaload
        //   7754: ldc_w 'n'
        //   7757: invokevirtual indexOf : (Ljava/lang/String;)I
        //   7760: iconst_m1
        //   7761: if_icmpne -> 7947
        //   7764: aload #39
        //   7766: getfield mang : [[Ljava/lang/String;
        //   7769: iload #6
        //   7771: aaload
        //   7772: iconst_3
        //   7773: aaload
        //   7774: ldc_w 'tr'
        //   7777: invokevirtual indexOf : (Ljava/lang/String;)I
        //   7780: iconst_m1
        //   7781: if_icmpne -> 7947
        //   7784: aload #39
        //   7786: getfield mang : [[Ljava/lang/String;
        //   7789: iload #6
        //   7791: aaload
        //   7792: iconst_3
        //   7793: aaload
        //   7794: ldc_w 'k'
        //   7797: invokevirtual indexOf : (Ljava/lang/String;)I
        //   7800: iconst_m1
        //   7801: if_icmpne -> 7947
        //   7804: aload #39
        //   7806: getfield mang : [[Ljava/lang/String;
        //   7809: iload #6
        //   7811: aaload
        //   7812: astore #4
        //   7814: new java/lang/StringBuilder
        //   7817: astore #27
        //   7819: aload #27
        //   7821: invokespecial <init> : ()V
        //   7824: aload #27
        //   7826: aload #28
        //   7828: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7831: pop
        //   7832: aload #27
        //   7834: aload #39
        //   7836: getfield mang : [[Ljava/lang/String;
        //   7839: iload #6
        //   7841: aaload
        //   7842: iconst_3
        //   7843: aaload
        //   7844: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7847: pop
        //   7848: aload #4
        //   7850: iconst_5
        //   7851: aload #27
        //   7853: invokevirtual toString : ()Ljava/lang/String;
        //   7856: aastore
        //   7857: aload #39
        //   7859: getfield mang : [[Ljava/lang/String;
        //   7862: iload #6
        //   7864: aaload
        //   7865: astore #27
        //   7867: aload #39
        //   7869: getfield mang : [[Ljava/lang/String;
        //   7872: iload #6
        //   7874: aaload
        //   7875: iconst_0
        //   7876: aaload
        //   7877: astore_3
        //   7878: aload #39
        //   7880: getfield mang : [[Ljava/lang/String;
        //   7883: iload #6
        //   7885: aaload
        //   7886: iconst_3
        //   7887: aaload
        //   7888: astore #4
        //   7890: new java/lang/StringBuilder
        //   7893: astore #12
        //   7895: aload #12
        //   7897: invokespecial <init> : ()V
        //   7900: aload #12
        //   7902: aload #44
        //   7904: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7907: pop
        //   7908: aload #12
        //   7910: aload #39
        //   7912: getfield mang : [[Ljava/lang/String;
        //   7915: iload #6
        //   7917: aaload
        //   7918: iconst_3
        //   7919: aaload
        //   7920: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7923: pop
        //   7924: aload #12
        //   7926: aload #43
        //   7928: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   7931: pop
        //   7932: aload #27
        //   7934: iconst_0
        //   7935: aload_3
        //   7936: aload #4
        //   7938: aload #12
        //   7940: invokevirtual toString : ()Ljava/lang/String;
        //   7943: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   7946: aastore
        //   7947: aload #39
        //   7949: getfield mang : [[Ljava/lang/String;
        //   7952: iload #6
        //   7954: aaload
        //   7955: iconst_1
        //   7956: aaload
        //   7957: astore #27
        //   7959: aload #15
        //   7961: astore #4
        //   7963: aload #27
        //   7965: aload #4
        //   7967: if_acmpeq -> 8034
        //   7970: aload #39
        //   7972: getfield mang : [[Ljava/lang/String;
        //   7975: iload #6
        //   7977: aaload
        //   7978: iconst_1
        //   7979: aaload
        //   7980: aload #18
        //   7982: if_acmpeq -> 8034
        //   7985: aload #39
        //   7987: getfield mang : [[Ljava/lang/String;
        //   7990: iload #6
        //   7992: aaload
        //   7993: iconst_1
        //   7994: aaload
        //   7995: astore #27
        //   7997: aload #31
        //   7999: astore #4
        //   8001: aload #27
        //   8003: aload #4
        //   8005: if_acmpne -> 8011
        //   8008: goto -> 8034
        //   8011: goto -> 8365
        //   8014: astore #15
        //   8016: aload #17
        //   8018: astore #15
        //   8020: aload #29
        //   8022: astore_3
        //   8023: aload #31
        //   8025: astore #27
        //   8027: aload #32
        //   8029: astore #17
        //   8031: goto -> 10047
        //   8034: aload #39
        //   8036: getfield mang : [[Ljava/lang/String;
        //   8039: iload #6
        //   8041: aaload
        //   8042: iconst_5
        //   8043: aaload
        //   8044: invokestatic parseInt : (Ljava/lang/String;)I
        //   8047: sipush #2000
        //   8050: if_icmple -> 8328
        //   8053: aload #39
        //   8055: getfield mang : [[Ljava/lang/String;
        //   8058: iload #6
        //   8060: aaload
        //   8061: iconst_3
        //   8062: aaload
        //   8063: ldc_w 'n'
        //   8066: invokevirtual indexOf : (Ljava/lang/String;)I
        //   8069: iconst_m1
        //   8070: if_icmpne -> 8321
        //   8073: aload #39
        //   8075: getfield mang : [[Ljava/lang/String;
        //   8078: iload #6
        //   8080: aaload
        //   8081: iconst_3
        //   8082: aaload
        //   8083: ldc_w 'd'
        //   8086: invokevirtual indexOf : (Ljava/lang/String;)I
        //   8089: iconst_m1
        //   8090: if_icmpne -> 8314
        //   8093: aload #39
        //   8095: getfield mang : [[Ljava/lang/String;
        //   8098: iload #6
        //   8100: aaload
        //   8101: iconst_3
        //   8102: aaload
        //   8103: ldc_w 'tr'
        //   8106: invokevirtual indexOf : (Ljava/lang/String;)I
        //   8109: iconst_m1
        //   8110: if_icmpne -> 8307
        //   8113: aload #39
        //   8115: getfield mang : [[Ljava/lang/String;
        //   8118: iload #6
        //   8120: aaload
        //   8121: iconst_3
        //   8122: aaload
        //   8123: ldc_w 'k'
        //   8126: invokevirtual indexOf : (Ljava/lang/String;)I
        //   8129: iconst_m1
        //   8130: if_icmpne -> 8300
        //   8133: aload #39
        //   8135: getfield mang : [[Ljava/lang/String;
        //   8138: iload #6
        //   8140: aaload
        //   8141: astore #4
        //   8143: new java/lang/StringBuilder
        //   8146: astore #27
        //   8148: aload #27
        //   8150: invokespecial <init> : ()V
        //   8153: aload #27
        //   8155: aload #28
        //   8157: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   8160: pop
        //   8161: aload #27
        //   8163: aload #39
        //   8165: getfield mang : [[Ljava/lang/String;
        //   8168: iload #6
        //   8170: aaload
        //   8171: iconst_3
        //   8172: aaload
        //   8173: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   8176: pop
        //   8177: aload #4
        //   8179: iconst_5
        //   8180: aload #27
        //   8182: invokevirtual toString : ()Ljava/lang/String;
        //   8185: aastore
        //   8186: aload #39
        //   8188: getfield mang : [[Ljava/lang/String;
        //   8191: iload #6
        //   8193: aaload
        //   8194: astore #12
        //   8196: aload #39
        //   8198: getfield mang : [[Ljava/lang/String;
        //   8201: iload #6
        //   8203: aaload
        //   8204: iconst_0
        //   8205: aaload
        //   8206: astore_3
        //   8207: aload #39
        //   8209: getfield mang : [[Ljava/lang/String;
        //   8212: iload #6
        //   8214: aaload
        //   8215: iconst_3
        //   8216: aaload
        //   8217: astore #26
        //   8219: new java/lang/StringBuilder
        //   8222: astore #27
        //   8224: aload #27
        //   8226: invokespecial <init> : ()V
        //   8229: aload #27
        //   8231: aload #44
        //   8233: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   8236: pop
        //   8237: aload #31
        //   8239: astore #4
        //   8241: aload #4
        //   8243: astore #31
        //   8245: aload #27
        //   8247: aload #39
        //   8249: getfield mang : [[Ljava/lang/String;
        //   8252: iload #6
        //   8254: aaload
        //   8255: iconst_3
        //   8256: aaload
        //   8257: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   8260: pop
        //   8261: aload #4
        //   8263: astore #31
        //   8265: aload #27
        //   8267: aload #43
        //   8269: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   8272: pop
        //   8273: aload #4
        //   8275: astore #31
        //   8277: aload #12
        //   8279: iconst_0
        //   8280: aload_3
        //   8281: aload #26
        //   8283: aload #27
        //   8285: invokevirtual toString : ()Ljava/lang/String;
        //   8288: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   8291: aastore
        //   8292: goto -> 8365
        //   8295: astore #4
        //   8297: goto -> 8337
        //   8300: aload #31
        //   8302: astore #4
        //   8304: goto -> 8365
        //   8307: aload #31
        //   8309: astore #4
        //   8311: goto -> 8365
        //   8314: aload #31
        //   8316: astore #4
        //   8318: goto -> 8365
        //   8321: aload #31
        //   8323: astore #4
        //   8325: goto -> 8365
        //   8328: aload #31
        //   8330: astore #4
        //   8332: goto -> 8365
        //   8335: astore #4
        //   8337: goto -> 8342
        //   8340: astore #4
        //   8342: aload #15
        //   8344: astore #4
        //   8346: aload #17
        //   8348: astore #12
        //   8350: aload #31
        //   8352: astore #27
        //   8354: aload #32
        //   8356: astore #17
        //   8358: goto -> 9660
        //   8361: aload #31
        //   8363: astore #4
        //   8365: aload #15
        //   8367: astore #40
        //   8369: aload #39
        //   8371: getfield caidat_tg : Lorg/json/JSONObject;
        //   8374: ldc_w 'loi_donvi'
        //   8377: invokevirtual getInt : (Ljava/lang/String;)I
        //   8380: ifle -> 9534
        //   8383: aload #39
        //   8385: getfield mang : [[Ljava/lang/String;
        //   8388: iload #6
        //   8390: aaload
        //   8391: iconst_3
        //   8392: aaload
        //   8393: astore #45
        //   8395: aload #39
        //   8397: getfield mang : [[Ljava/lang/String;
        //   8400: iload #6
        //   8402: aaload
        //   8403: iconst_1
        //   8404: aaload
        //   8405: ldc 'lo'
        //   8407: if_acmpne -> 8686
        //   8410: aload #45
        //   8412: ldc_w 'n'
        //   8415: invokevirtual indexOf : (Ljava/lang/String;)I
        //   8418: istore #30
        //   8420: iload #30
        //   8422: iconst_m1
        //   8423: if_icmpgt -> 8457
        //   8426: aload #4
        //   8428: astore #31
        //   8430: aload #45
        //   8432: ldc_w 'k'
        //   8435: invokevirtual indexOf : (Ljava/lang/String;)I
        //   8438: istore #30
        //   8440: iload #30
        //   8442: iconst_m1
        //   8443: if_icmple -> 8449
        //   8446: goto -> 8457
        //   8449: goto -> 8686
        //   8452: astore #4
        //   8454: goto -> 8342
        //   8457: aload #39
        //   8459: getfield mang : [[Ljava/lang/String;
        //   8462: iload #6
        //   8464: aaload
        //   8465: astore #31
        //   8467: new java/lang/StringBuilder
        //   8470: astore #27
        //   8472: aload #27
        //   8474: invokespecial <init> : ()V
        //   8477: aload #27
        //   8479: aload #28
        //   8481: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   8484: pop
        //   8485: aload #27
        //   8487: aload #39
        //   8489: getfield mang : [[Ljava/lang/String;
        //   8492: iload #6
        //   8494: aaload
        //   8495: iconst_3
        //   8496: aaload
        //   8497: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   8500: pop
        //   8501: aload #31
        //   8503: iconst_5
        //   8504: aload #27
        //   8506: invokevirtual toString : ()Ljava/lang/String;
        //   8509: aastore
        //   8510: aload #39
        //   8512: getfield mang : [[Ljava/lang/String;
        //   8515: iload #6
        //   8517: aaload
        //   8518: astore #46
        //   8520: aload #39
        //   8522: getfield mang : [[Ljava/lang/String;
        //   8525: iload #6
        //   8527: aaload
        //   8528: iconst_0
        //   8529: aaload
        //   8530: astore #47
        //   8532: aload #39
        //   8534: getfield mang : [[Ljava/lang/String;
        //   8537: iload #6
        //   8539: aaload
        //   8540: iconst_3
        //   8541: aaload
        //   8542: astore #48
        //   8544: new java/lang/StringBuilder
        //   8547: astore #49
        //   8549: aload #49
        //   8551: invokespecial <init> : ()V
        //   8554: aload #49
        //   8556: aload #44
        //   8558: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   8561: pop
        //   8562: aload #40
        //   8564: astore #41
        //   8566: aload #28
        //   8568: astore #23
        //   8570: aload #24
        //   8572: astore #26
        //   8574: aload #17
        //   8576: astore #12
        //   8578: aload #4
        //   8580: astore #31
        //   8582: aload #32
        //   8584: astore_3
        //   8585: aload #11
        //   8587: astore #27
        //   8589: aload #49
        //   8591: aload #39
        //   8593: getfield mang : [[Ljava/lang/String;
        //   8596: iload #6
        //   8598: aaload
        //   8599: iconst_3
        //   8600: aaload
        //   8601: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   8604: pop
        //   8605: aload #40
        //   8607: astore #41
        //   8609: aload #28
        //   8611: astore #23
        //   8613: aload #24
        //   8615: astore #26
        //   8617: aload #17
        //   8619: astore #12
        //   8621: aload #4
        //   8623: astore #31
        //   8625: aload #32
        //   8627: astore_3
        //   8628: aload #11
        //   8630: astore #27
        //   8632: aload #49
        //   8634: aload #43
        //   8636: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   8639: pop
        //   8640: aload #40
        //   8642: astore #41
        //   8644: aload #28
        //   8646: astore #23
        //   8648: aload #24
        //   8650: astore #26
        //   8652: aload #17
        //   8654: astore #12
        //   8656: aload #4
        //   8658: astore #31
        //   8660: aload #32
        //   8662: astore_3
        //   8663: aload #11
        //   8665: astore #27
        //   8667: aload #46
        //   8669: iconst_0
        //   8670: aload #47
        //   8672: aload #48
        //   8674: aload #49
        //   8676: invokevirtual toString : ()Ljava/lang/String;
        //   8679: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   8682: aastore
        //   8683: goto -> 8686
        //   8686: aload #40
        //   8688: astore #41
        //   8690: aload #28
        //   8692: astore #23
        //   8694: aload #24
        //   8696: astore #26
        //   8698: aload #17
        //   8700: astore #12
        //   8702: aload #4
        //   8704: astore #31
        //   8706: aload #32
        //   8708: astore_3
        //   8709: aload #11
        //   8711: astore #27
        //   8713: aload #39
        //   8715: getfield mang : [[Ljava/lang/String;
        //   8718: iload #6
        //   8720: aaload
        //   8721: iconst_1
        //   8722: aaload
        //   8723: ldc 'de dit db'
        //   8725: if_acmpeq -> 8938
        //   8728: aload #40
        //   8730: astore #41
        //   8732: aload #28
        //   8734: astore #23
        //   8736: aload #24
        //   8738: astore #26
        //   8740: aload #17
        //   8742: astore #12
        //   8744: aload #4
        //   8746: astore #31
        //   8748: aload #32
        //   8750: astore_3
        //   8751: aload #11
        //   8753: astore #27
        //   8755: aload #39
        //   8757: getfield mang : [[Ljava/lang/String;
        //   8760: iload #6
        //   8762: aaload
        //   8763: iconst_1
        //   8764: aaload
        //   8765: ldc 'de dau db'
        //   8767: if_acmpeq -> 8938
        //   8770: aload #40
        //   8772: astore #41
        //   8774: aload #28
        //   8776: astore #23
        //   8778: aload #24
        //   8780: astore #26
        //   8782: aload #17
        //   8784: astore #12
        //   8786: aload #4
        //   8788: astore #31
        //   8790: aload #32
        //   8792: astore_3
        //   8793: aload #11
        //   8795: astore #27
        //   8797: aload #39
        //   8799: getfield mang : [[Ljava/lang/String;
        //   8802: iload #6
        //   8804: aaload
        //   8805: iconst_1
        //   8806: aaload
        //   8807: ldc 'hai cua'
        //   8809: if_acmpeq -> 8938
        //   8812: aload #40
        //   8814: astore #41
        //   8816: aload #28
        //   8818: astore #23
        //   8820: aload #24
        //   8822: astore #26
        //   8824: aload #17
        //   8826: astore #12
        //   8828: aload #4
        //   8830: astore #31
        //   8832: aload #32
        //   8834: astore_3
        //   8835: aload #11
        //   8837: astore #27
        //   8839: aload #39
        //   8841: getfield mang : [[Ljava/lang/String;
        //   8844: iload #6
        //   8846: aaload
        //   8847: iconst_1
        //   8848: aaload
        //   8849: ldc 'de 8'
        //   8851: if_acmpeq -> 8938
        //   8854: aload #40
        //   8856: astore #41
        //   8858: aload #28
        //   8860: astore #23
        //   8862: aload #24
        //   8864: astore #26
        //   8866: aload #17
        //   8868: astore #12
        //   8870: aload #4
        //   8872: astore #31
        //   8874: aload #32
        //   8876: astore_3
        //   8877: aload #11
        //   8879: astore #27
        //   8881: aload #39
        //   8883: getfield mang : [[Ljava/lang/String;
        //   8886: iload #6
        //   8888: aaload
        //   8889: iconst_1
        //   8890: aaload
        //   8891: ldc 'de dit nhat'
        //   8893: if_acmpeq -> 8938
        //   8896: aload #40
        //   8898: astore #41
        //   8900: aload #28
        //   8902: astore #23
        //   8904: aload #24
        //   8906: astore #26
        //   8908: aload #17
        //   8910: astore #12
        //   8912: aload #4
        //   8914: astore #31
        //   8916: aload #32
        //   8918: astore_3
        //   8919: aload #11
        //   8921: astore #27
        //   8923: aload #39
        //   8925: getfield mang : [[Ljava/lang/String;
        //   8928: iload #6
        //   8930: aaload
        //   8931: iconst_1
        //   8932: aaload
        //   8933: ldc 'de dau nhat'
        //   8935: if_acmpne -> 9527
        //   8938: aload #40
        //   8940: astore #41
        //   8942: aload #28
        //   8944: astore #23
        //   8946: aload #24
        //   8948: astore #26
        //   8950: aload #17
        //   8952: astore #12
        //   8954: aload #4
        //   8956: astore #31
        //   8958: aload #32
        //   8960: astore_3
        //   8961: aload #11
        //   8963: astore #27
        //   8965: aload #45
        //   8967: ldc_w 'd'
        //   8970: invokevirtual indexOf : (Ljava/lang/String;)I
        //   8973: iconst_m1
        //   8974: if_icmple -> 9527
        //   8977: aload #40
        //   8979: astore #41
        //   8981: aload #28
        //   8983: astore #23
        //   8985: aload #24
        //   8987: astore #26
        //   8989: aload #17
        //   8991: astore #12
        //   8993: aload #4
        //   8995: astore #31
        //   8997: aload #32
        //   8999: astore_3
        //   9000: aload #11
        //   9002: astore #27
        //   9004: aload #39
        //   9006: getfield mang : [[Ljava/lang/String;
        //   9009: iload #6
        //   9011: aaload
        //   9012: astore #45
        //   9014: aload #40
        //   9016: astore #41
        //   9018: aload #28
        //   9020: astore #23
        //   9022: aload #24
        //   9024: astore #26
        //   9026: aload #17
        //   9028: astore #12
        //   9030: aload #4
        //   9032: astore #31
        //   9034: aload #32
        //   9036: astore_3
        //   9037: aload #11
        //   9039: astore #27
        //   9041: new java/lang/StringBuilder
        //   9044: astore #15
        //   9046: aload #40
        //   9048: astore #41
        //   9050: aload #28
        //   9052: astore #23
        //   9054: aload #24
        //   9056: astore #26
        //   9058: aload #17
        //   9060: astore #12
        //   9062: aload #4
        //   9064: astore #31
        //   9066: aload #32
        //   9068: astore_3
        //   9069: aload #11
        //   9071: astore #27
        //   9073: aload #15
        //   9075: invokespecial <init> : ()V
        //   9078: aload #40
        //   9080: astore #41
        //   9082: aload #28
        //   9084: astore #23
        //   9086: aload #24
        //   9088: astore #26
        //   9090: aload #17
        //   9092: astore #12
        //   9094: aload #4
        //   9096: astore #31
        //   9098: aload #32
        //   9100: astore_3
        //   9101: aload #11
        //   9103: astore #27
        //   9105: aload #15
        //   9107: aload #28
        //   9109: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   9112: pop
        //   9113: aload #40
        //   9115: astore #41
        //   9117: aload #28
        //   9119: astore #23
        //   9121: aload #24
        //   9123: astore #26
        //   9125: aload #17
        //   9127: astore #12
        //   9129: aload #4
        //   9131: astore #31
        //   9133: aload #32
        //   9135: astore_3
        //   9136: aload #11
        //   9138: astore #27
        //   9140: aload #15
        //   9142: aload #39
        //   9144: getfield mang : [[Ljava/lang/String;
        //   9147: iload #6
        //   9149: aaload
        //   9150: iconst_3
        //   9151: aaload
        //   9152: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   9155: pop
        //   9156: aload #40
        //   9158: astore #41
        //   9160: aload #28
        //   9162: astore #23
        //   9164: aload #24
        //   9166: astore #26
        //   9168: aload #17
        //   9170: astore #12
        //   9172: aload #4
        //   9174: astore #31
        //   9176: aload #32
        //   9178: astore_3
        //   9179: aload #11
        //   9181: astore #27
        //   9183: aload #45
        //   9185: iconst_5
        //   9186: aload #15
        //   9188: invokevirtual toString : ()Ljava/lang/String;
        //   9191: aastore
        //   9192: aload #40
        //   9194: astore #41
        //   9196: aload #28
        //   9198: astore #23
        //   9200: aload #24
        //   9202: astore #26
        //   9204: aload #17
        //   9206: astore #12
        //   9208: aload #4
        //   9210: astore #31
        //   9212: aload #32
        //   9214: astore_3
        //   9215: aload #11
        //   9217: astore #27
        //   9219: aload #39
        //   9221: getfield mang : [[Ljava/lang/String;
        //   9224: iload #6
        //   9226: aaload
        //   9227: astore #45
        //   9229: aload #40
        //   9231: astore #41
        //   9233: aload #28
        //   9235: astore #23
        //   9237: aload #24
        //   9239: astore #26
        //   9241: aload #17
        //   9243: astore #12
        //   9245: aload #4
        //   9247: astore #31
        //   9249: aload #32
        //   9251: astore_3
        //   9252: aload #11
        //   9254: astore #27
        //   9256: aload #39
        //   9258: getfield mang : [[Ljava/lang/String;
        //   9261: iload #6
        //   9263: aaload
        //   9264: iconst_0
        //   9265: aaload
        //   9266: astore #49
        //   9268: aload #40
        //   9270: astore #41
        //   9272: aload #28
        //   9274: astore #23
        //   9276: aload #24
        //   9278: astore #26
        //   9280: aload #17
        //   9282: astore #12
        //   9284: aload #4
        //   9286: astore #31
        //   9288: aload #32
        //   9290: astore_3
        //   9291: aload #11
        //   9293: astore #27
        //   9295: aload #39
        //   9297: getfield mang : [[Ljava/lang/String;
        //   9300: iload #6
        //   9302: aaload
        //   9303: iconst_3
        //   9304: aaload
        //   9305: astore #48
        //   9307: aload #40
        //   9309: astore #41
        //   9311: aload #28
        //   9313: astore #23
        //   9315: aload #24
        //   9317: astore #26
        //   9319: aload #17
        //   9321: astore #12
        //   9323: aload #4
        //   9325: astore #31
        //   9327: aload #32
        //   9329: astore_3
        //   9330: aload #11
        //   9332: astore #27
        //   9334: new java/lang/StringBuilder
        //   9337: astore #15
        //   9339: aload #40
        //   9341: astore #41
        //   9343: aload #28
        //   9345: astore #23
        //   9347: aload #24
        //   9349: astore #26
        //   9351: aload #17
        //   9353: astore #12
        //   9355: aload #4
        //   9357: astore #31
        //   9359: aload #32
        //   9361: astore_3
        //   9362: aload #11
        //   9364: astore #27
        //   9366: aload #15
        //   9368: invokespecial <init> : ()V
        //   9371: aload #40
        //   9373: astore #41
        //   9375: aload #28
        //   9377: astore #23
        //   9379: aload #24
        //   9381: astore #26
        //   9383: aload #17
        //   9385: astore #12
        //   9387: aload #4
        //   9389: astore #31
        //   9391: aload #32
        //   9393: astore_3
        //   9394: aload #11
        //   9396: astore #27
        //   9398: aload #15
        //   9400: aload #44
        //   9402: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   9405: pop
        //   9406: aload #40
        //   9408: astore #41
        //   9410: aload #28
        //   9412: astore #23
        //   9414: aload #24
        //   9416: astore #26
        //   9418: aload #17
        //   9420: astore #12
        //   9422: aload #4
        //   9424: astore #31
        //   9426: aload #32
        //   9428: astore_3
        //   9429: aload #11
        //   9431: astore #27
        //   9433: aload #15
        //   9435: aload #39
        //   9437: getfield mang : [[Ljava/lang/String;
        //   9440: iload #6
        //   9442: aaload
        //   9443: iconst_3
        //   9444: aaload
        //   9445: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   9448: pop
        //   9449: aload #40
        //   9451: astore #41
        //   9453: aload #28
        //   9455: astore #23
        //   9457: aload #24
        //   9459: astore #26
        //   9461: aload #17
        //   9463: astore #12
        //   9465: aload #4
        //   9467: astore #31
        //   9469: aload #32
        //   9471: astore_3
        //   9472: aload #11
        //   9474: astore #27
        //   9476: aload #15
        //   9478: aload #43
        //   9480: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   9483: pop
        //   9484: aload #40
        //   9486: astore #41
        //   9488: aload #28
        //   9490: astore #23
        //   9492: aload #24
        //   9494: astore #26
        //   9496: aload #17
        //   9498: astore #12
        //   9500: aload #4
        //   9502: astore #31
        //   9504: aload #32
        //   9506: astore_3
        //   9507: aload #11
        //   9509: astore #27
        //   9511: aload #45
        //   9513: iconst_0
        //   9514: aload #49
        //   9516: aload #48
        //   9518: aload #15
        //   9520: invokevirtual toString : ()Ljava/lang/String;
        //   9523: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   9526: aastore
        //   9527: aload #40
        //   9529: astore #15
        //   9531: goto -> 9538
        //   9534: aload #40
        //   9536: astore #15
        //   9538: aload #15
        //   9540: astore #41
        //   9542: aload #28
        //   9544: astore #23
        //   9546: aload #24
        //   9548: astore #26
        //   9550: aload #17
        //   9552: astore #12
        //   9554: aload #4
        //   9556: astore #31
        //   9558: aload #32
        //   9560: astore_3
        //   9561: aload #11
        //   9563: astore #27
        //   9565: aload #39
        //   9567: iload #6
        //   9569: invokespecial BaoLoiDan : (I)V
        //   9572: aload #17
        //   9574: astore_3
        //   9575: aload #32
        //   9577: astore #17
        //   9579: goto -> 9849
        //   9582: astore #15
        //   9584: aload #41
        //   9586: astore #4
        //   9588: aload #23
        //   9590: astore #28
        //   9592: aload #26
        //   9594: astore #24
        //   9596: aload #27
        //   9598: astore #11
        //   9600: goto -> 9653
        //   9603: astore #27
        //   9605: goto -> 9634
        //   9608: astore #27
        //   9610: aload #15
        //   9612: astore #4
        //   9614: aload #27
        //   9616: astore #15
        //   9618: aload #17
        //   9620: astore #12
        //   9622: aload #32
        //   9624: astore_3
        //   9625: goto -> 9653
        //   9628: astore #27
        //   9630: aload #31
        //   9632: astore #4
        //   9634: aload #32
        //   9636: astore_3
        //   9637: aload #4
        //   9639: astore #31
        //   9641: aload #17
        //   9643: astore #12
        //   9645: aload #15
        //   9647: astore #4
        //   9649: aload #27
        //   9651: astore #15
        //   9653: aload_3
        //   9654: astore #17
        //   9656: aload #31
        //   9658: astore #27
        //   9660: aload #12
        //   9662: astore #15
        //   9664: aload #29
        //   9666: astore_3
        //   9667: goto -> 10047
        //   9670: astore #27
        //   9672: aload #4
        //   9674: astore #32
        //   9676: aload #11
        //   9678: astore #27
        //   9680: aload #28
        //   9682: astore #11
        //   9684: aload #15
        //   9686: astore #4
        //   9688: aload #27
        //   9690: astore #28
        //   9692: aload #17
        //   9694: astore #15
        //   9696: aload #29
        //   9698: astore_3
        //   9699: aload #31
        //   9701: astore #27
        //   9703: aload #32
        //   9705: astore #17
        //   9707: goto -> 10047
        //   9710: astore #31
        //   9712: aload #15
        //   9714: astore_3
        //   9715: aload #4
        //   9717: astore #31
        //   9719: aload #11
        //   9721: astore #15
        //   9723: aload #28
        //   9725: astore #11
        //   9727: aload_3
        //   9728: astore #4
        //   9730: aload #15
        //   9732: astore #28
        //   9734: aload #17
        //   9736: astore #15
        //   9738: aload #29
        //   9740: astore_3
        //   9741: aload #31
        //   9743: astore #17
        //   9745: goto -> 10047
        //   9748: aload #28
        //   9750: astore #11
        //   9752: aload #4
        //   9754: astore #31
        //   9756: aload #18
        //   9758: astore #32
        //   9760: aload_3
        //   9761: astore #28
        //   9763: aload #39
        //   9765: getfield mang : [[Ljava/lang/String;
        //   9768: iload #6
        //   9770: aaload
        //   9771: iconst_2
        //   9772: aload #17
        //   9774: aastore
        //   9775: aload #39
        //   9777: getfield mang : [[Ljava/lang/String;
        //   9780: iload #6
        //   9782: aaload
        //   9783: iconst_3
        //   9784: aload #8
        //   9786: aastore
        //   9787: aload #39
        //   9789: getfield mang : [[Ljava/lang/String;
        //   9792: iload #6
        //   9794: aaload
        //   9795: astore #12
        //   9797: new java/lang/StringBuilder
        //   9800: astore #4
        //   9802: aload #4
        //   9804: invokespecial <init> : ()V
        //   9807: aload #4
        //   9809: aload #28
        //   9811: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   9814: pop
        //   9815: aload #17
        //   9817: astore_3
        //   9818: aload #4
        //   9820: aload_3
        //   9821: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   9824: pop
        //   9825: aload #12
        //   9827: iconst_4
        //   9828: aload #4
        //   9830: invokevirtual toString : ()Ljava/lang/String;
        //   9833: aastore
        //   9834: aload #39
        //   9836: iload #6
        //   9838: invokespecial BaoLoiDan : (I)V
        //   9841: aload #31
        //   9843: astore #17
        //   9845: aload #27
        //   9847: astore #4
        //   9849: aload #18
        //   9851: astore #31
        //   9853: aload_3
        //   9854: astore #18
        //   9856: aload #29
        //   9858: astore_3
        //   9859: aload #4
        //   9861: astore #27
        //   9863: goto -> 10186
        //   9866: astore #4
        //   9868: aload #15
        //   9870: astore #4
        //   9872: aload_3
        //   9873: astore #15
        //   9875: aload #32
        //   9877: astore #18
        //   9879: aload #29
        //   9881: astore_3
        //   9882: aload #31
        //   9884: astore #17
        //   9886: goto -> 10047
        //   9889: astore #4
        //   9891: aload #15
        //   9893: astore #4
        //   9895: aload #17
        //   9897: astore #15
        //   9899: aload #32
        //   9901: astore #18
        //   9903: aload #29
        //   9905: astore_3
        //   9906: aload #31
        //   9908: astore #17
        //   9910: goto -> 10047
        //   9913: astore #18
        //   9915: aload #15
        //   9917: astore_3
        //   9918: aload #4
        //   9920: astore #32
        //   9922: aload #11
        //   9924: astore #15
        //   9926: aload #28
        //   9928: astore #11
        //   9930: aload #31
        //   9932: astore #18
        //   9934: aload_3
        //   9935: astore #4
        //   9937: aload #15
        //   9939: astore #28
        //   9941: aload #17
        //   9943: astore #15
        //   9945: aload #29
        //   9947: astore_3
        //   9948: aload #32
        //   9950: astore #17
        //   9952: goto -> 10047
        //   9955: astore #18
        //   9957: goto -> 9962
        //   9960: astore #18
        //   9962: aload #28
        //   9964: astore #11
        //   9966: aload #4
        //   9968: astore #32
        //   9970: aload #31
        //   9972: astore #18
        //   9974: aload_3
        //   9975: astore #28
        //   9977: aload #15
        //   9979: astore #4
        //   9981: aload #17
        //   9983: astore #15
        //   9985: aload #29
        //   9987: astore_3
        //   9988: aload #32
        //   9990: astore #17
        //   9992: goto -> 10047
        //   9995: astore_3
        //   9996: aload #15
        //   9998: astore #29
        //   10000: aload #4
        //   10002: astore #15
        //   10004: aload #17
        //   10006: astore #4
        //   10008: aload #11
        //   10010: astore #12
        //   10012: aload #28
        //   10014: astore #11
        //   10016: aload #8
        //   10018: astore_3
        //   10019: aload #15
        //   10021: astore #17
        //   10023: aload #18
        //   10025: astore #33
        //   10027: aload #31
        //   10029: astore #18
        //   10031: aload #32
        //   10033: astore #15
        //   10035: aload #12
        //   10037: astore #28
        //   10039: aload #4
        //   10041: astore #8
        //   10043: aload #29
        //   10045: astore #4
        //   10047: aload #19
        //   10049: astore #31
        //   10051: aload #21
        //   10053: astore #32
        //   10055: aload_0
        //   10056: astore #29
        //   10058: aload #29
        //   10060: getfield mang : [[Ljava/lang/String;
        //   10063: astore #12
        //   10065: aload #12
        //   10067: iload #6
        //   10069: aaload
        //   10070: iconst_0
        //   10071: aload #12
        //   10073: iload #6
        //   10075: aaload
        //   10076: iconst_0
        //   10077: aaload
        //   10078: aload #32
        //   10080: aload #8
        //   10082: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   10085: aastore
        //   10086: aload #29
        //   10088: getfield mang : [[Ljava/lang/String;
        //   10091: astore #12
        //   10093: aload #12
        //   10095: iload #6
        //   10097: aaload
        //   10098: iconst_0
        //   10099: aload #12
        //   10101: iload #6
        //   10103: aaload
        //   10104: iconst_0
        //   10105: aaload
        //   10106: aload #31
        //   10108: aload #8
        //   10110: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   10113: aastore
        //   10114: aload #29
        //   10116: getfield mang : [[Ljava/lang/String;
        //   10119: iload #6
        //   10121: aaload
        //   10122: astore #12
        //   10124: new java/lang/StringBuilder
        //   10127: dup
        //   10128: invokespecial <init> : ()V
        //   10131: astore #26
        //   10133: aload #26
        //   10135: aload #32
        //   10137: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   10140: pop
        //   10141: aload #26
        //   10143: aload #29
        //   10145: getfield mang : [[Ljava/lang/String;
        //   10148: iload #6
        //   10150: aaload
        //   10151: iconst_0
        //   10152: aaload
        //   10153: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   10156: pop
        //   10157: aload #26
        //   10159: aload #31
        //   10161: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   10164: pop
        //   10165: aload #12
        //   10167: iconst_0
        //   10168: aload #26
        //   10170: invokevirtual toString : ()Ljava/lang/String;
        //   10173: aastore
        //   10174: aload #18
        //   10176: astore #31
        //   10178: aload #15
        //   10180: astore #18
        //   10182: aload #4
        //   10184: astore #15
        //   10186: aload_0
        //   10187: astore #32
        //   10189: aload_3
        //   10190: astore #4
        //   10192: aload #8
        //   10194: astore #29
        //   10196: aload #32
        //   10198: astore_3
        //   10199: aload #4
        //   10201: astore #8
        //   10203: aload #33
        //   10205: astore #32
        //   10207: aload #17
        //   10209: astore #4
        //   10211: iload #22
        //   10213: istore #37
        //   10215: aload #18
        //   10217: astore #33
        //   10219: aload #29
        //   10221: astore #17
        //   10223: goto -> 2184
        //   10226: aload_3
        //   10227: astore #8
        //   10229: aload #27
        //   10231: astore #18
        //   10233: aload #11
        //   10235: astore_3
        //   10236: aload #15
        //   10238: astore #33
        //   10240: aload #34
        //   10242: astore #15
        //   10244: aload #31
        //   10246: astore #11
        //   10248: aload #32
        //   10250: invokevirtual length : ()I
        //   10253: ifle -> 10402
        //   10256: aload #32
        //   10258: aload #4
        //   10260: aload #17
        //   10262: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   10265: ldc_w '\.'
        //   10268: aload #17
        //   10270: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   10273: ldc_w ','
        //   10276: aload #17
        //   10278: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   10281: ldc_w ';'
        //   10284: aload #17
        //   10286: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   10289: astore #31
        //   10291: aload #31
        //   10293: astore #27
        //   10295: aload #31
        //   10297: invokevirtual length : ()I
        //   10300: ifle -> 10406
        //   10303: aload #8
        //   10305: getfield mang : [[Ljava/lang/String;
        //   10308: astore #27
        //   10310: aload #27
        //   10312: iload #6
        //   10314: iconst_1
        //   10315: iadd
        //   10316: aaload
        //   10317: iconst_0
        //   10318: aload #32
        //   10320: aastore
        //   10321: aload #27
        //   10323: iload #6
        //   10325: iconst_1
        //   10326: iadd
        //   10327: aaload
        //   10328: iconst_2
        //   10329: aload #32
        //   10331: aastore
        //   10332: aload #27
        //   10334: iload #6
        //   10336: iconst_1
        //   10337: iadd
        //   10338: aaload
        //   10339: iconst_3
        //   10340: aload #32
        //   10342: aastore
        //   10343: aload #27
        //   10345: iload #6
        //   10347: iconst_1
        //   10348: iadd
        //   10349: aaload
        //   10350: astore #29
        //   10352: new java/lang/StringBuilder
        //   10355: dup
        //   10356: invokespecial <init> : ()V
        //   10359: astore #27
        //   10361: aload #27
        //   10363: aload #28
        //   10365: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   10368: pop
        //   10369: aload #27
        //   10371: aload #32
        //   10373: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   10376: pop
        //   10377: aload #29
        //   10379: iconst_4
        //   10380: aload #27
        //   10382: invokevirtual toString : ()Ljava/lang/String;
        //   10385: aastore
        //   10386: aload #8
        //   10388: iload #6
        //   10390: iconst_1
        //   10391: iadd
        //   10392: invokespecial BaoLoiDan : (I)V
        //   10395: aload #31
        //   10397: astore #27
        //   10399: goto -> 10406
        //   10402: aload #32
        //   10404: astore #27
        //   10406: aload #4
        //   10408: astore #27
        //   10410: aload #24
        //   10412: astore #4
        //   10414: aload #9
        //   10416: astore #28
        //   10418: aload #14
        //   10420: astore #24
        //   10422: aload #17
        //   10424: astore #31
        //   10426: aload #8
        //   10428: astore #32
        //   10430: aload #4
        //   10432: astore #17
        //   10434: aload #21
        //   10436: astore #8
        //   10438: aload #19
        //   10440: astore #21
        //   10442: aload #11
        //   10444: astore #4
        //   10446: aload #18
        //   10448: astore #11
        //   10450: aload_3
        //   10451: astore #18
        //   10453: goto -> 10639
        //   10456: aload #27
        //   10458: astore #11
        //   10460: ldc 'Khhi
        //   10462: astore #32
        //   10464: ldc_w 'xi'
        //   10467: astore #33
        //   10469: ldc ' '
        //   10471: astore #29
        //   10473: aload #21
        //   10475: astore #28
        //   10477: ldc ''
        //   10479: astore #9
        //   10481: aload #31
        //   10483: astore #4
        //   10485: aload_3
        //   10486: getfield mang : [[Ljava/lang/String;
        //   10489: astore #17
        //   10491: aload #17
        //   10493: iconst_1
        //   10494: aaload
        //   10495: iconst_0
        //   10496: aload #15
        //   10498: aastore
        //   10499: aload #17
        //   10501: iconst_1
        //   10502: aaload
        //   10503: iconst_4
        //   10504: aload #18
        //   10506: aastore
        //   10507: aload #18
        //   10509: astore #27
        //   10511: aload #27
        //   10513: ldc_w 'Khhid
        //   10516: invokevirtual indexOf : (Ljava/lang/String;)I
        //   10519: iconst_m1
        //   10520: if_icmple -> 10573
        //   10523: aload_3
        //   10524: getfield mang : [[Ljava/lang/String;
        //   10527: iconst_1
        //   10528: aaload
        //   10529: astore #18
        //   10531: new java/lang/StringBuilder
        //   10534: dup
        //   10535: invokespecial <init> : ()V
        //   10538: astore #17
        //   10540: aload #17
        //   10542: ldc 'Khhi'
        //   10544: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   10547: pop
        //   10548: aload #17
        //   10550: aload #15
        //   10552: iconst_0
        //   10553: iconst_5
        //   10554: invokevirtual substring : (II)Ljava/lang/String;
        //   10557: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   10560: pop
        //   10561: aload #18
        //   10563: iconst_4
        //   10564: aload #17
        //   10566: invokevirtual toString : ()Ljava/lang/String;
        //   10569: aastore
        //   10570: goto -> 10573
        //   10573: aload_3
        //   10574: getfield mang : [[Ljava/lang/String;
        //   10577: astore #18
        //   10579: aload #18
        //   10581: iconst_1
        //   10582: aaload
        //   10583: iconst_2
        //   10584: aload #15
        //   10586: aastore
        //   10587: aload #18
        //   10589: iconst_1
        //   10590: aaload
        //   10591: iconst_3
        //   10592: aload #9
        //   10594: aastore
        //   10595: aload_3
        //   10596: iconst_1
        //   10597: invokespecial BaoLoiDan : (I)V
        //   10600: aload #15
        //   10602: astore #24
        //   10604: aload #8
        //   10606: astore #17
        //   10608: aload #32
        //   10610: astore #18
        //   10612: aload #34
        //   10614: astore #15
        //   10616: aload #19
        //   10618: astore #21
        //   10620: aload #28
        //   10622: astore #8
        //   10624: aload_3
        //   10625: astore #32
        //   10627: aload #27
        //   10629: astore #28
        //   10631: aload #9
        //   10633: astore #31
        //   10635: aload #29
        //   10637: astore #27
        //   10639: iconst_0
        //   10640: istore #10
        //   10642: ldc ''
        //   10644: astore #24
        //   10646: ldc ''
        //   10648: astore #29
        //   10650: ldc ''
        //   10652: astore_3
        //   10653: new org/json/JSONObject
        //   10656: dup
        //   10657: invokespecial <init> : ()V
        //   10660: astore #9
        //   10662: iconst_0
        //   10663: istore #30
        //   10665: iconst_1
        //   10666: istore #22
        //   10668: aload #18
        //   10670: astore #19
        //   10672: aload #29
        //   10674: astore #18
        //   10676: aload #28
        //   10678: astore #29
        //   10680: iload #22
        //   10682: sipush #1000
        //   10685: if_icmpge -> 10977
        //   10688: aload #32
        //   10690: getfield mang : [[Ljava/lang/String;
        //   10693: astore #28
        //   10695: aload #28
        //   10697: iload #22
        //   10699: aaload
        //   10700: iconst_0
        //   10701: aaload
        //   10702: ifnonnull -> 10708
        //   10705: goto -> 10977
        //   10708: aload #28
        //   10710: iload #22
        //   10712: aaload
        //   10713: iconst_4
        //   10714: aaload
        //   10715: aload #19
        //   10717: invokevirtual indexOf : (Ljava/lang/String;)I
        //   10720: iconst_m1
        //   10721: if_icmpgt -> 10749
        //   10724: aload #32
        //   10726: getfield mang : [[Ljava/lang/String;
        //   10729: iload #22
        //   10731: aaload
        //   10732: iconst_5
        //   10733: aaload
        //   10734: aload #19
        //   10736: invokevirtual indexOf : (Ljava/lang/String;)I
        //   10739: iconst_m1
        //   10740: if_icmple -> 10746
        //   10743: goto -> 10749
        //   10746: goto -> 10931
        //   10749: aload #32
        //   10751: getfield mang : [[Ljava/lang/String;
        //   10754: iload #22
        //   10756: aaload
        //   10757: iconst_4
        //   10758: aaload
        //   10759: aload #19
        //   10761: invokevirtual indexOf : (Ljava/lang/String;)I
        //   10764: iconst_m1
        //   10765: if_icmple -> 10802
        //   10768: aload #32
        //   10770: getfield mang : [[Ljava/lang/String;
        //   10773: astore_3
        //   10774: aload_3
        //   10775: iload #22
        //   10777: aaload
        //   10778: iconst_4
        //   10779: aaload
        //   10780: astore #28
        //   10782: aload_3
        //   10783: iload #22
        //   10785: aaload
        //   10786: iconst_4
        //   10787: aaload
        //   10788: aload #19
        //   10790: aload #31
        //   10792: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   10795: invokevirtual trim : ()Ljava/lang/String;
        //   10798: astore_3
        //   10799: goto -> 10833
        //   10802: aload #32
        //   10804: getfield mang : [[Ljava/lang/String;
        //   10807: astore_3
        //   10808: aload_3
        //   10809: iload #22
        //   10811: aaload
        //   10812: iconst_5
        //   10813: aaload
        //   10814: astore #28
        //   10816: aload_3
        //   10817: iload #22
        //   10819: aaload
        //   10820: iconst_5
        //   10821: aaload
        //   10822: aload #19
        //   10824: aload #31
        //   10826: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   10829: invokevirtual trim : ()Ljava/lang/String;
        //   10832: astore_3
        //   10833: aload #32
        //   10835: getfield mang : [[Ljava/lang/String;
        //   10838: iload #22
        //   10840: aaload
        //   10841: iconst_0
        //   10842: aaload
        //   10843: aload #8
        //   10845: invokevirtual indexOf : (Ljava/lang/String;)I
        //   10848: iconst_m1
        //   10849: if_icmpne -> 10925
        //   10852: aload #32
        //   10854: getfield mang : [[Ljava/lang/String;
        //   10857: astore #34
        //   10859: aload #34
        //   10861: iload #22
        //   10863: aaload
        //   10864: astore #14
        //   10866: aload #34
        //   10868: iload #22
        //   10870: aaload
        //   10871: iconst_0
        //   10872: aaload
        //   10873: astore #34
        //   10875: new java/lang/StringBuilder
        //   10878: dup
        //   10879: invokespecial <init> : ()V
        //   10882: astore #12
        //   10884: aload #12
        //   10886: aload #8
        //   10888: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   10891: pop
        //   10892: aload #12
        //   10894: aload_3
        //   10895: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   10898: pop
        //   10899: aload #12
        //   10901: aload #21
        //   10903: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   10906: pop
        //   10907: aload #14
        //   10909: iconst_0
        //   10910: aload #34
        //   10912: aload_3
        //   10913: aload #12
        //   10915: invokevirtual toString : ()Ljava/lang/String;
        //   10918: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   10921: aastore
        //   10922: goto -> 10925
        //   10925: iinc #10, 1
        //   10928: aload #28
        //   10930: astore_3
        //   10931: new java/lang/StringBuilder
        //   10934: dup
        //   10935: invokespecial <init> : ()V
        //   10938: astore #28
        //   10940: aload #28
        //   10942: aload #24
        //   10944: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   10947: pop
        //   10948: aload #28
        //   10950: aload #32
        //   10952: getfield mang : [[Ljava/lang/String;
        //   10955: iload #22
        //   10957: aaload
        //   10958: iconst_0
        //   10959: aaload
        //   10960: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   10963: pop
        //   10964: aload #28
        //   10966: invokevirtual toString : ()Ljava/lang/String;
        //   10969: astore #24
        //   10971: iinc #22, 1
        //   10974: goto -> 10680
        //   10977: iload #10
        //   10979: ifne -> 13542
        //   10982: iconst_0
        //   10983: istore #42
        //   10985: iconst_0
        //   10986: istore #6
        //   10988: iconst_0
        //   10989: istore #38
        //   10991: iconst_1
        //   10992: istore #50
        //   10994: aload #18
        //   10996: astore #17
        //   10998: aload #4
        //   11000: astore #8
        //   11002: aload #16
        //   11004: astore #18
        //   11006: aload #9
        //   11008: astore #28
        //   11010: aload #13
        //   11012: astore #21
        //   11014: aload #24
        //   11016: astore #19
        //   11018: aload #7
        //   11020: astore #4
        //   11022: aload #31
        //   11024: astore #24
        //   11026: iload #10
        //   11028: istore #51
        //   11030: iload #50
        //   11032: sipush #1000
        //   11035: if_icmpge -> 13117
        //   11038: aload #32
        //   11040: getfield mang : [[Ljava/lang/String;
        //   11043: iload #50
        //   11045: aaload
        //   11046: iconst_0
        //   11047: aaload
        //   11048: ifnonnull -> 11054
        //   11051: goto -> 13117
        //   11054: new org/json/JSONObject
        //   11057: dup
        //   11058: invokespecial <init> : ()V
        //   11061: astore #14
        //   11063: aload #14
        //   11065: ldc_w 'du_lieu'
        //   11068: aload #32
        //   11070: getfield mang : [[Ljava/lang/String;
        //   11073: iload #50
        //   11075: aaload
        //   11076: iconst_0
        //   11077: aaload
        //   11078: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   11081: pop
        //   11082: aload #14
        //   11084: ldc_w 'the_loai'
        //   11087: aload #32
        //   11089: getfield mang : [[Ljava/lang/String;
        //   11092: iload #50
        //   11094: aaload
        //   11095: iconst_1
        //   11096: aaload
        //   11097: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   11100: pop
        //   11101: aload #14
        //   11103: ldc_w 'dan_so'
        //   11106: aload #32
        //   11108: getfield mang : [[Ljava/lang/String;
        //   11111: iload #50
        //   11113: aaload
        //   11114: iconst_4
        //   11115: aaload
        //   11116: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   11119: pop
        //   11120: aload #14
        //   11122: ldc_w 'so_tien'
        //   11125: aload #32
        //   11127: getfield mang : [[Ljava/lang/String;
        //   11130: iload #50
        //   11132: aaload
        //   11133: iconst_5
        //   11134: aaload
        //   11135: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   11138: pop
        //   11139: aload #32
        //   11141: getfield mang : [[Ljava/lang/String;
        //   11144: iload #50
        //   11146: aaload
        //   11147: iconst_1
        //   11148: aaload
        //   11149: aload #21
        //   11151: invokevirtual indexOf : (Ljava/lang/String;)I
        //   11154: iconst_m1
        //   11155: if_icmple -> 11172
        //   11158: iconst_1
        //   11159: istore #37
        //   11161: iload #6
        //   11163: istore #10
        //   11165: aload #11
        //   11167: astore #31
        //   11169: goto -> 11357
        //   11172: aload #32
        //   11174: getfield mang : [[Ljava/lang/String;
        //   11177: iload #50
        //   11179: aaload
        //   11180: iconst_1
        //   11181: aaload
        //   11182: aload #33
        //   11184: invokevirtual indexOf : (Ljava/lang/String;)I
        //   11187: iconst_m1
        //   11188: if_icmpgt -> 11346
        //   11191: aload #32
        //   11193: getfield mang : [[Ljava/lang/String;
        //   11196: iload #50
        //   11198: aaload
        //   11199: iconst_1
        //   11200: aaload
        //   11201: aload #8
        //   11203: invokevirtual indexOf : (Ljava/lang/String;)I
        //   11206: iconst_m1
        //   11207: if_icmpgt -> 11346
        //   11210: aload #32
        //   11212: getfield mang : [[Ljava/lang/String;
        //   11215: iload #50
        //   11217: aaload
        //   11218: iconst_1
        //   11219: aaload
        //   11220: aload #11
        //   11222: invokevirtual indexOf : (Ljava/lang/String;)I
        //   11225: iconst_m1
        //   11226: if_icmpgt -> 11343
        //   11229: aload #32
        //   11231: getfield mang : [[Ljava/lang/String;
        //   11234: iload #50
        //   11236: aaload
        //   11237: iconst_1
        //   11238: aaload
        //   11239: astore #31
        //   11241: aload #31
        //   11243: ldc_w 'xg'
        //   11246: invokevirtual indexOf : (Ljava/lang/String;)I
        //   11249: iconst_m1
        //   11250: if_icmple -> 11256
        //   11253: goto -> 11346
        //   11256: aload #32
        //   11258: getfield mang : [[Ljava/lang/String;
        //   11261: iload #50
        //   11263: aaload
        //   11264: iconst_1
        //   11265: aaload
        //   11266: ldc 'de dau nhat'
        //   11268: invokevirtual indexOf : (Ljava/lang/String;)I
        //   11271: iconst_m1
        //   11272: if_icmpgt -> 11325
        //   11275: aload #32
        //   11277: getfield mang : [[Ljava/lang/String;
        //   11280: iload #50
        //   11282: aaload
        //   11283: iconst_1
        //   11284: aaload
        //   11285: ldc 'de dit nhat'
        //   11287: invokevirtual indexOf : (Ljava/lang/String;)I
        //   11290: iconst_m1
        //   11291: if_icmpgt -> 11325
        //   11294: iload #42
        //   11296: istore #37
        //   11298: iload #6
        //   11300: istore #10
        //   11302: aload #11
        //   11304: astore #31
        //   11306: aload #32
        //   11308: getfield mang : [[Ljava/lang/String;
        //   11311: iload #50
        //   11313: aaload
        //   11314: iconst_1
        //   11315: aaload
        //   11316: ldc 'hai cua'
        //   11318: invokevirtual indexOf : (Ljava/lang/String;)I
        //   11321: iconst_m1
        //   11322: if_icmple -> 11357
        //   11325: iconst_1
        //   11326: istore #38
        //   11328: iload #42
        //   11330: istore #37
        //   11332: iload #6
        //   11334: istore #10
        //   11336: aload #11
        //   11338: astore #31
        //   11340: goto -> 11357
        //   11343: goto -> 11346
        //   11346: iconst_1
        //   11347: istore #10
        //   11349: aload #11
        //   11351: astore #31
        //   11353: iload #42
        //   11355: istore #37
        //   11357: iload #36
        //   11359: ifeq -> 11828
        //   11362: iload_2
        //   11363: iconst_1
        //   11364: if_icmpne -> 11828
        //   11367: aload #32
        //   11369: getfield mang : [[Ljava/lang/String;
        //   11372: iload #50
        //   11374: aaload
        //   11375: iconst_1
        //   11376: aaload
        //   11377: ldc 'de dit db'
        //   11379: invokevirtual contains : (Ljava/lang/CharSequence;)Z
        //   11382: ifne -> 11649
        //   11385: aload #32
        //   11387: getfield mang : [[Ljava/lang/String;
        //   11390: iload #50
        //   11392: aaload
        //   11393: iconst_1
        //   11394: aaload
        //   11395: ldc 'de dau db'
        //   11397: invokevirtual contains : (Ljava/lang/CharSequence;)Z
        //   11400: ifne -> 11649
        //   11403: aload #32
        //   11405: getfield mang : [[Ljava/lang/String;
        //   11408: iload #50
        //   11410: aaload
        //   11411: iconst_1
        //   11412: aaload
        //   11413: astore #29
        //   11415: aload #15
        //   11417: astore #11
        //   11419: aload #29
        //   11421: aload #11
        //   11423: invokevirtual contains : (Ljava/lang/CharSequence;)Z
        //   11426: ifne -> 11646
        //   11429: aload #32
        //   11431: getfield mang : [[Ljava/lang/String;
        //   11434: iload #50
        //   11436: aaload
        //   11437: iconst_1
        //   11438: aaload
        //   11439: ldc 'de 8'
        //   11441: invokevirtual contains : (Ljava/lang/CharSequence;)Z
        //   11444: ifeq -> 11450
        //   11447: goto -> 11649
        //   11450: aload #32
        //   11452: getfield mang : [[Ljava/lang/String;
        //   11455: iload #50
        //   11457: aaload
        //   11458: iconst_1
        //   11459: aaload
        //   11460: ldc 'hai cua'
        //   11462: invokevirtual indexOf : (Ljava/lang/String;)I
        //   11465: iconst_m1
        //   11466: if_icmple -> 11639
        //   11469: new java/lang/StringBuilder
        //   11472: dup
        //   11473: invokespecial <init> : ()V
        //   11476: astore #15
        //   11478: aload #15
        //   11480: aload #17
        //   11482: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11485: pop
        //   11486: aload #15
        //   11488: ldc_w 'de dit db:'
        //   11491: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11494: pop
        //   11495: aload #15
        //   11497: aload #32
        //   11499: getfield mang : [[Ljava/lang/String;
        //   11502: iload #50
        //   11504: aaload
        //   11505: iconst_4
        //   11506: aaload
        //   11507: invokevirtual trim : ()Ljava/lang/String;
        //   11510: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11513: pop
        //   11514: aload #15
        //   11516: ldc_w 'x'
        //   11519: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11522: pop
        //   11523: aload #15
        //   11525: aload #32
        //   11527: getfield mang : [[Ljava/lang/String;
        //   11530: iload #50
        //   11532: aaload
        //   11533: iconst_5
        //   11534: aaload
        //   11535: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11538: pop
        //   11539: aload #15
        //   11541: aload #18
        //   11543: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11546: pop
        //   11547: aload #15
        //   11549: invokevirtual toString : ()Ljava/lang/String;
        //   11552: astore #15
        //   11554: aload #14
        //   11556: ldc_w 'the_loai'
        //   11559: ldc 'de dit db'
        //   11561: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   11564: pop
        //   11565: aload #32
        //   11567: getfield mang : [[Ljava/lang/String;
        //   11570: iload #50
        //   11572: aaload
        //   11573: iconst_4
        //   11574: aaload
        //   11575: aload #4
        //   11577: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   11580: astore #29
        //   11582: new java/lang/StringBuilder
        //   11585: dup
        //   11586: invokespecial <init> : ()V
        //   11589: astore #17
        //   11591: aload #17
        //   11593: aload #29
        //   11595: arraylength
        //   11596: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   11599: pop
        //   11600: aload #17
        //   11602: ldc_w ' s
        //   11605: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11608: pop
        //   11609: aload #14
        //   11611: ldc_w 'so_luong'
        //   11614: aload #17
        //   11616: invokevirtual toString : ()Ljava/lang/String;
        //   11619: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   11622: pop
        //   11623: aload #28
        //   11625: iload #30
        //   11627: invokestatic valueOf : (I)Ljava/lang/String;
        //   11630: aload #14
        //   11632: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   11635: pop
        //   11636: goto -> 13088
        //   11639: aload #17
        //   11641: astore #15
        //   11643: goto -> 13088
        //   11646: goto -> 11649
        //   11649: new java/lang/StringBuilder
        //   11652: dup
        //   11653: invokespecial <init> : ()V
        //   11656: astore #29
        //   11658: aload #29
        //   11660: aload #17
        //   11662: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11665: pop
        //   11666: aload #15
        //   11668: astore #11
        //   11670: aload #29
        //   11672: aload #32
        //   11674: getfield mang : [[Ljava/lang/String;
        //   11677: iload #50
        //   11679: aaload
        //   11680: iconst_1
        //   11681: aaload
        //   11682: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11685: pop
        //   11686: aload #29
        //   11688: ldc_w ':'
        //   11691: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11694: pop
        //   11695: aload #29
        //   11697: aload #32
        //   11699: getfield mang : [[Ljava/lang/String;
        //   11702: iload #50
        //   11704: aaload
        //   11705: iconst_4
        //   11706: aaload
        //   11707: invokevirtual trim : ()Ljava/lang/String;
        //   11710: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11713: pop
        //   11714: aload #29
        //   11716: ldc_w 'x'
        //   11719: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11722: pop
        //   11723: aload #29
        //   11725: aload #32
        //   11727: getfield mang : [[Ljava/lang/String;
        //   11730: iload #50
        //   11732: aaload
        //   11733: iconst_5
        //   11734: aaload
        //   11735: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11738: pop
        //   11739: aload #29
        //   11741: aload #18
        //   11743: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11746: pop
        //   11747: aload #29
        //   11749: invokevirtual toString : ()Ljava/lang/String;
        //   11752: astore #15
        //   11754: aload #32
        //   11756: getfield mang : [[Ljava/lang/String;
        //   11759: iload #50
        //   11761: aaload
        //   11762: iconst_4
        //   11763: aaload
        //   11764: aload #4
        //   11766: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   11769: astore #29
        //   11771: new java/lang/StringBuilder
        //   11774: dup
        //   11775: invokespecial <init> : ()V
        //   11778: astore #17
        //   11780: aload #17
        //   11782: aload #29
        //   11784: arraylength
        //   11785: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   11788: pop
        //   11789: aload #17
        //   11791: ldc_w ' s
        //   11794: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11797: pop
        //   11798: aload #14
        //   11800: ldc_w 'so_luong'
        //   11803: aload #17
        //   11805: invokevirtual toString : ()Ljava/lang/String;
        //   11808: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   11811: pop
        //   11812: aload #28
        //   11814: iload #30
        //   11816: invokestatic valueOf : (I)Ljava/lang/String;
        //   11819: aload #14
        //   11821: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   11824: pop
        //   11825: goto -> 13088
        //   11828: aload #28
        //   11830: astore #29
        //   11832: aload #15
        //   11834: astore #11
        //   11836: aload #32
        //   11838: getfield mang : [[Ljava/lang/String;
        //   11841: astore #15
        //   11843: aload #15
        //   11845: iload #50
        //   11847: aaload
        //   11848: iconst_1
        //   11849: aaload
        //   11850: ldc 'hai cua'
        //   11852: if_acmpne -> 12278
        //   11855: new java/lang/StringBuilder
        //   11858: dup
        //   11859: invokespecial <init> : ()V
        //   11862: astore #15
        //   11864: aload #15
        //   11866: aload #17
        //   11868: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11871: pop
        //   11872: aload #15
        //   11874: ldc_w 'de dit db:'
        //   11877: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11880: pop
        //   11881: aload #15
        //   11883: aload #32
        //   11885: getfield mang : [[Ljava/lang/String;
        //   11888: iload #50
        //   11890: aaload
        //   11891: iconst_4
        //   11892: aaload
        //   11893: invokevirtual trim : ()Ljava/lang/String;
        //   11896: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11899: pop
        //   11900: aload #15
        //   11902: ldc_w 'x'
        //   11905: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11908: pop
        //   11909: aload #15
        //   11911: aload #32
        //   11913: getfield mang : [[Ljava/lang/String;
        //   11916: iload #50
        //   11918: aaload
        //   11919: iconst_5
        //   11920: aaload
        //   11921: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11924: pop
        //   11925: aload #15
        //   11927: aload #18
        //   11929: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   11932: pop
        //   11933: aload #15
        //   11935: invokevirtual toString : ()Ljava/lang/String;
        //   11938: astore #15
        //   11940: aload #32
        //   11942: getfield mang : [[Ljava/lang/String;
        //   11945: iload #50
        //   11947: aaload
        //   11948: iconst_4
        //   11949: aaload
        //   11950: aload #4
        //   11952: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   11955: astore #17
        //   11957: new org/json/JSONObject
        //   11960: dup
        //   11961: invokespecial <init> : ()V
        //   11964: astore #9
        //   11966: aload #9
        //   11968: ldc_w 'du_lieu'
        //   11971: aload #32
        //   11973: getfield mang : [[Ljava/lang/String;
        //   11976: iload #50
        //   11978: aaload
        //   11979: iconst_0
        //   11980: aaload
        //   11981: ldc 'hc'
        //   11983: ldc 'de'
        //   11985: invokevirtual replaceFirst : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   11988: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   11991: pop
        //   11992: aload #9
        //   11994: ldc_w 'the_loai'
        //   11997: ldc 'de dit db'
        //   11999: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12002: pop
        //   12003: aload #9
        //   12005: ldc_w 'dan_so'
        //   12008: aload #32
        //   12010: getfield mang : [[Ljava/lang/String;
        //   12013: iload #50
        //   12015: aaload
        //   12016: iconst_4
        //   12017: aaload
        //   12018: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12021: pop
        //   12022: aload #9
        //   12024: ldc_w 'so_tien'
        //   12027: aload #32
        //   12029: getfield mang : [[Ljava/lang/String;
        //   12032: iload #50
        //   12034: aaload
        //   12035: iconst_5
        //   12036: aaload
        //   12037: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12040: pop
        //   12041: new java/lang/StringBuilder
        //   12044: dup
        //   12045: invokespecial <init> : ()V
        //   12048: astore #34
        //   12050: aload #34
        //   12052: aload #17
        //   12054: arraylength
        //   12055: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   12058: pop
        //   12059: aload #34
        //   12061: ldc_w ' s
        //   12064: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12067: pop
        //   12068: aload #9
        //   12070: ldc_w 'so_luong'
        //   12073: aload #34
        //   12075: invokevirtual toString : ()Ljava/lang/String;
        //   12078: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12081: pop
        //   12082: aload #29
        //   12084: iload #30
        //   12086: invokestatic valueOf : (I)Ljava/lang/String;
        //   12089: aload #9
        //   12091: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12094: pop
        //   12095: iinc #30, 1
        //   12098: new java/lang/StringBuilder
        //   12101: dup
        //   12102: invokespecial <init> : ()V
        //   12105: astore #9
        //   12107: aload #9
        //   12109: aload #15
        //   12111: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12114: pop
        //   12115: aload #9
        //   12117: ldc_w 'de dit nhat:'
        //   12120: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12123: pop
        //   12124: aload #9
        //   12126: aload #32
        //   12128: getfield mang : [[Ljava/lang/String;
        //   12131: iload #50
        //   12133: aaload
        //   12134: iconst_4
        //   12135: aaload
        //   12136: invokevirtual trim : ()Ljava/lang/String;
        //   12139: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12142: pop
        //   12143: aload #9
        //   12145: ldc_w 'x'
        //   12148: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12151: pop
        //   12152: aload #9
        //   12154: aload #32
        //   12156: getfield mang : [[Ljava/lang/String;
        //   12159: iload #50
        //   12161: aaload
        //   12162: iconst_5
        //   12163: aaload
        //   12164: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12167: pop
        //   12168: aload #9
        //   12170: aload #18
        //   12172: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12175: pop
        //   12176: aload #9
        //   12178: invokevirtual toString : ()Ljava/lang/String;
        //   12181: astore #15
        //   12183: aload #14
        //   12185: ldc_w 'du_lieu'
        //   12188: aload #32
        //   12190: getfield mang : [[Ljava/lang/String;
        //   12193: iload #50
        //   12195: aaload
        //   12196: iconst_0
        //   12197: aaload
        //   12198: ldc 'hc'
        //   12200: ldc_w 'nhat'
        //   12203: invokevirtual replaceFirst : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   12206: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12209: pop
        //   12210: aload #14
        //   12212: ldc_w 'the_loai'
        //   12215: ldc 'de dit nhat'
        //   12217: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12220: pop
        //   12221: new java/lang/StringBuilder
        //   12224: dup
        //   12225: invokespecial <init> : ()V
        //   12228: astore #9
        //   12230: aload #9
        //   12232: aload #17
        //   12234: arraylength
        //   12235: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   12238: pop
        //   12239: aload #9
        //   12241: ldc_w ' s
        //   12244: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12247: pop
        //   12248: aload #14
        //   12250: ldc_w 'so_luong'
        //   12253: aload #9
        //   12255: invokevirtual toString : ()Ljava/lang/String;
        //   12258: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12261: pop
        //   12262: aload #29
        //   12264: iload #30
        //   12266: invokestatic valueOf : (I)Ljava/lang/String;
        //   12269: aload #14
        //   12271: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12274: pop
        //   12275: goto -> 13088
        //   12278: aload #15
        //   12280: iload #50
        //   12282: aaload
        //   12283: iconst_1
        //   12284: aaload
        //   12285: aload #33
        //   12287: invokevirtual indexOf : (Ljava/lang/String;)I
        //   12290: iconst_m1
        //   12291: if_icmpgt -> 12511
        //   12294: aload #32
        //   12296: getfield mang : [[Ljava/lang/String;
        //   12299: iload #50
        //   12301: aaload
        //   12302: iconst_1
        //   12303: aaload
        //   12304: aload #8
        //   12306: invokevirtual indexOf : (Ljava/lang/String;)I
        //   12309: iconst_m1
        //   12310: if_icmpgt -> 12511
        //   12313: aload #32
        //   12315: getfield mang : [[Ljava/lang/String;
        //   12318: iload #50
        //   12320: aaload
        //   12321: iconst_1
        //   12322: aaload
        //   12323: ldc_w 'xg'
        //   12326: invokevirtual indexOf : (Ljava/lang/String;)I
        //   12329: iconst_m1
        //   12330: if_icmple -> 12336
        //   12333: goto -> 12511
        //   12336: new java/lang/StringBuilder
        //   12339: dup
        //   12340: invokespecial <init> : ()V
        //   12343: astore #15
        //   12345: aload #15
        //   12347: aload #17
        //   12349: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12352: pop
        //   12353: aload #15
        //   12355: aload #32
        //   12357: getfield mang : [[Ljava/lang/String;
        //   12360: iload #50
        //   12362: aaload
        //   12363: iconst_1
        //   12364: aaload
        //   12365: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12368: pop
        //   12369: aload #15
        //   12371: ldc_w ':'
        //   12374: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12377: pop
        //   12378: aload #15
        //   12380: aload #32
        //   12382: getfield mang : [[Ljava/lang/String;
        //   12385: iload #50
        //   12387: aaload
        //   12388: iconst_4
        //   12389: aaload
        //   12390: invokevirtual trim : ()Ljava/lang/String;
        //   12393: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12396: pop
        //   12397: aload #15
        //   12399: ldc_w 'x'
        //   12402: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12405: pop
        //   12406: aload #15
        //   12408: aload #32
        //   12410: getfield mang : [[Ljava/lang/String;
        //   12413: iload #50
        //   12415: aaload
        //   12416: iconst_5
        //   12417: aaload
        //   12418: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12421: pop
        //   12422: aload #15
        //   12424: aload #18
        //   12426: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12429: pop
        //   12430: aload #15
        //   12432: invokevirtual toString : ()Ljava/lang/String;
        //   12435: astore #15
        //   12437: aload #32
        //   12439: getfield mang : [[Ljava/lang/String;
        //   12442: iload #50
        //   12444: aaload
        //   12445: iconst_4
        //   12446: aaload
        //   12447: aload #4
        //   12449: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   12452: astore #9
        //   12454: new java/lang/StringBuilder
        //   12457: dup
        //   12458: invokespecial <init> : ()V
        //   12461: astore #17
        //   12463: aload #17
        //   12465: aload #9
        //   12467: arraylength
        //   12468: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   12471: pop
        //   12472: aload #17
        //   12474: ldc_w ' s
        //   12477: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12480: pop
        //   12481: aload #14
        //   12483: ldc_w 'so_luong'
        //   12486: aload #17
        //   12488: invokevirtual toString : ()Ljava/lang/String;
        //   12491: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12494: pop
        //   12495: aload #29
        //   12497: iload #30
        //   12499: invokestatic valueOf : (I)Ljava/lang/String;
        //   12502: aload #14
        //   12504: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12507: pop
        //   12508: goto -> 13088
        //   12511: aload #32
        //   12513: getfield mang : [[Ljava/lang/String;
        //   12516: iload #50
        //   12518: aaload
        //   12519: iconst_4
        //   12520: aaload
        //   12521: astore #15
        //   12523: aload #27
        //   12525: astore #9
        //   12527: aload #15
        //   12529: aload #9
        //   12531: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   12534: astore #34
        //   12536: iconst_0
        //   12537: istore #22
        //   12539: aload #17
        //   12541: astore #15
        //   12543: iload #22
        //   12545: aload #34
        //   12547: arraylength
        //   12548: if_icmpge -> 13088
        //   12551: new java/lang/StringBuilder
        //   12554: dup
        //   12555: invokespecial <init> : ()V
        //   12558: astore #17
        //   12560: aload #17
        //   12562: aload #15
        //   12564: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12567: pop
        //   12568: aload #17
        //   12570: aload #32
        //   12572: getfield mang : [[Ljava/lang/String;
        //   12575: iload #50
        //   12577: aaload
        //   12578: iconst_1
        //   12579: aaload
        //   12580: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12583: pop
        //   12584: aload #17
        //   12586: ldc_w ':'
        //   12589: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12592: pop
        //   12593: aload #17
        //   12595: aload #34
        //   12597: iload #22
        //   12599: aaload
        //   12600: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12603: pop
        //   12604: aload #17
        //   12606: ldc_w 'x'
        //   12609: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12612: pop
        //   12613: aload #17
        //   12615: aload #32
        //   12617: getfield mang : [[Ljava/lang/String;
        //   12620: iload #50
        //   12622: aaload
        //   12623: iconst_5
        //   12624: aaload
        //   12625: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12628: pop
        //   12629: aload #17
        //   12631: aload #18
        //   12633: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12636: pop
        //   12637: aload #17
        //   12639: invokevirtual toString : ()Ljava/lang/String;
        //   12642: astore #15
        //   12644: aload #32
        //   12646: getfield mang : [[Ljava/lang/String;
        //   12649: iload #50
        //   12651: aaload
        //   12652: iconst_1
        //   12653: aaload
        //   12654: aload #8
        //   12656: invokevirtual indexOf : (Ljava/lang/String;)I
        //   12659: iconst_m1
        //   12660: if_icmple -> 12917
        //   12663: aload #32
        //   12665: aload #34
        //   12667: iload #22
        //   12669: aaload
        //   12670: invokespecial xuly_Xq : (Ljava/lang/String;)Ljava/lang/String;
        //   12673: aload #9
        //   12675: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   12678: astore #12
        //   12680: new org/json/JSONObject
        //   12683: dup
        //   12684: invokespecial <init> : ()V
        //   12687: astore #17
        //   12689: new java/lang/StringBuilder
        //   12692: dup
        //   12693: invokespecial <init> : ()V
        //   12696: astore #26
        //   12698: aload #26
        //   12700: aload #32
        //   12702: getfield mang : [[Ljava/lang/String;
        //   12705: iload #50
        //   12707: aaload
        //   12708: iconst_1
        //   12709: aaload
        //   12710: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12713: pop
        //   12714: aload #26
        //   12716: ldc_w ':'
        //   12719: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12722: pop
        //   12723: aload #26
        //   12725: aload #34
        //   12727: iload #22
        //   12729: aaload
        //   12730: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12733: pop
        //   12734: aload #26
        //   12736: ldc_w 'x'
        //   12739: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12742: pop
        //   12743: aload #26
        //   12745: aload #32
        //   12747: getfield mang : [[Ljava/lang/String;
        //   12750: iload #50
        //   12752: aaload
        //   12753: iconst_5
        //   12754: aaload
        //   12755: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12758: pop
        //   12759: aload #17
        //   12761: ldc_w 'du_lieu'
        //   12764: aload #26
        //   12766: invokevirtual toString : ()Ljava/lang/String;
        //   12769: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12772: pop
        //   12773: aload #32
        //   12775: getfield mang : [[Ljava/lang/String;
        //   12778: iload #50
        //   12780: aaload
        //   12781: iconst_1
        //   12782: aaload
        //   12783: ldc_w 'xq dau'
        //   12786: invokevirtual indexOf : (Ljava/lang/String;)I
        //   12789: iconst_m1
        //   12790: if_icmple -> 12808
        //   12793: aload #17
        //   12795: ldc_w 'the_loai'
        //   12798: ldc_w 'xien dau'
        //   12801: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12804: pop
        //   12805: goto -> 12819
        //   12808: aload #17
        //   12810: ldc_w 'the_loai'
        //   12813: aload #33
        //   12815: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12818: pop
        //   12819: aload #17
        //   12821: ldc_w 'dan_so'
        //   12824: aload #32
        //   12826: aload #34
        //   12828: iload #22
        //   12830: aaload
        //   12831: invokespecial xuly_Xq : (Ljava/lang/String;)Ljava/lang/String;
        //   12834: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12837: pop
        //   12838: aload #17
        //   12840: ldc_w 'so_tien'
        //   12843: aload #32
        //   12845: getfield mang : [[Ljava/lang/String;
        //   12848: iload #50
        //   12850: aaload
        //   12851: iconst_5
        //   12852: aaload
        //   12853: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12856: pop
        //   12857: new java/lang/StringBuilder
        //   12860: dup
        //   12861: invokespecial <init> : ()V
        //   12864: astore #26
        //   12866: aload #26
        //   12868: aload #12
        //   12870: arraylength
        //   12871: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   12874: pop
        //   12875: aload #26
        //   12877: ldc_w ' c
        //   12880: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12883: pop
        //   12884: aload #17
        //   12886: ldc_w 'so_luong'
        //   12889: aload #26
        //   12891: invokevirtual toString : ()Ljava/lang/String;
        //   12894: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12897: pop
        //   12898: aload #29
        //   12900: iload #30
        //   12902: invokestatic valueOf : (I)Ljava/lang/String;
        //   12905: aload #17
        //   12907: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12910: pop
        //   12911: iinc #30, 1
        //   12914: goto -> 13082
        //   12917: aload #32
        //   12919: getfield mang : [[Ljava/lang/String;
        //   12922: iload #50
        //   12924: aaload
        //   12925: iconst_1
        //   12926: aaload
        //   12927: ldc_w 'xg'
        //   12930: invokevirtual indexOf : (Ljava/lang/String;)I
        //   12933: iconst_m1
        //   12934: if_icmple -> 13011
        //   12937: aload #32
        //   12939: getfield mang : [[Ljava/lang/String;
        //   12942: iload #50
        //   12944: aaload
        //   12945: iconst_4
        //   12946: aaload
        //   12947: aload #9
        //   12949: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   12952: astore #17
        //   12954: new java/lang/StringBuilder
        //   12957: dup
        //   12958: invokespecial <init> : ()V
        //   12961: astore #12
        //   12963: aload #12
        //   12965: aload #17
        //   12967: arraylength
        //   12968: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   12971: pop
        //   12972: aload #12
        //   12974: ldc_w ' c
        //   12977: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   12980: pop
        //   12981: aload #14
        //   12983: ldc_w 'so_luong'
        //   12986: aload #12
        //   12988: invokevirtual toString : ()Ljava/lang/String;
        //   12991: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   12994: pop
        //   12995: aload #29
        //   12997: iload #30
        //   12999: invokestatic valueOf : (I)Ljava/lang/String;
        //   13002: aload #14
        //   13004: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   13007: pop
        //   13008: goto -> 13082
        //   13011: aload #32
        //   13013: getfield mang : [[Ljava/lang/String;
        //   13016: iload #50
        //   13018: aaload
        //   13019: iconst_4
        //   13020: aaload
        //   13021: aload #9
        //   13023: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   13026: astore #12
        //   13028: new java/lang/StringBuilder
        //   13031: dup
        //   13032: invokespecial <init> : ()V
        //   13035: astore #17
        //   13037: aload #17
        //   13039: aload #12
        //   13041: arraylength
        //   13042: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   13045: pop
        //   13046: aload #17
        //   13048: ldc_w ' c
        //   13051: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13054: pop
        //   13055: aload #14
        //   13057: ldc_w 'so_luong'
        //   13060: aload #17
        //   13062: invokevirtual toString : ()Ljava/lang/String;
        //   13065: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   13068: pop
        //   13069: aload #29
        //   13071: iload #30
        //   13073: invokestatic valueOf : (I)Ljava/lang/String;
        //   13076: aload #14
        //   13078: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   13081: pop
        //   13082: iinc #22, 1
        //   13085: goto -> 12543
        //   13088: iinc #30, 1
        //   13091: iinc #50, 1
        //   13094: iload #37
        //   13096: istore #42
        //   13098: iload #10
        //   13100: istore #6
        //   13102: aload #15
        //   13104: astore #17
        //   13106: aload #11
        //   13108: astore #15
        //   13110: aload #31
        //   13112: astore #11
        //   13114: goto -> 11030
        //   13117: iload #36
        //   13119: ifeq -> 13323
        //   13122: iload_2
        //   13123: iconst_1
        //   13124: if_icmpne -> 13323
        //   13127: iload #42
        //   13129: ifne -> 13148
        //   13132: iload #6
        //   13134: ifne -> 13148
        //   13137: iload #38
        //   13139: ifeq -> 13145
        //   13142: goto -> 13148
        //   13145: goto -> 13323
        //   13148: iload #42
        //   13150: ifeq -> 13190
        //   13153: new java/lang/StringBuilder
        //   13156: dup
        //   13157: invokespecial <init> : ()V
        //   13160: astore #4
        //   13162: aload #4
        //   13164: ldc_w 'B'
        //   13167: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13170: pop
        //   13171: aload #4
        //   13173: ldc_w 'l
        //   13176: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13179: pop
        //   13180: aload #4
        //   13182: invokevirtual toString : ()Ljava/lang/String;
        //   13185: astore #4
        //   13187: goto -> 13195
        //   13190: ldc_w 'B'
        //   13193: astore #4
        //   13195: aload #4
        //   13197: astore #18
        //   13199: iload #6
        //   13201: ifeq -> 13237
        //   13204: new java/lang/StringBuilder
        //   13207: dup
        //   13208: invokespecial <init> : ()V
        //   13211: astore #18
        //   13213: aload #18
        //   13215: aload #4
        //   13217: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13220: pop
        //   13221: aload #18
        //   13223: ldc_w 'xi
        //   13226: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13229: pop
        //   13230: aload #18
        //   13232: invokevirtual toString : ()Ljava/lang/String;
        //   13235: astore #18
        //   13237: aload #18
        //   13239: astore #4
        //   13241: iload #38
        //   13243: ifeq -> 13279
        //   13246: new java/lang/StringBuilder
        //   13249: dup
        //   13250: invokespecial <init> : ()V
        //   13253: astore #4
        //   13255: aload #4
        //   13257: aload #18
        //   13259: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13262: pop
        //   13263: aload #4
        //   13265: ldc_w 'ginh
        //   13268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13271: pop
        //   13272: aload #4
        //   13274: invokevirtual toString : ()Ljava/lang/String;
        //   13277: astore #4
        //   13279: new java/lang/StringBuilder
        //   13282: dup
        //   13283: invokespecial <init> : ()V
        //   13286: astore #18
        //   13288: aload #18
        //   13290: aload #4
        //   13292: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13295: pop
        //   13296: aload #18
        //   13298: ldc_w ' vqugi\\n'
        //   13301: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13304: pop
        //   13305: aload #18
        //   13307: aload #17
        //   13309: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13312: pop
        //   13313: aload #18
        //   13315: invokevirtual toString : ()Ljava/lang/String;
        //   13318: astore #17
        //   13320: goto -> 13328
        //   13323: ldc_w 'B'
        //   13326: astore #4
        //   13328: aload #17
        //   13330: astore #4
        //   13332: aload #17
        //   13334: ifnull -> 13368
        //   13337: aload #17
        //   13339: ldc_w 'xg 2:'
        //   13342: ldc_w 'xi:'
        //   13345: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   13348: ldc_w 'xg 3:'
        //   13351: ldc_w 'xi:'
        //   13354: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   13357: ldc_w 'xg 4:'
        //   13360: ldc_w 'xi:'
        //   13363: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   13366: astore #4
        //   13368: aload #35
        //   13370: aload #24
        //   13372: if_acmpeq -> 13467
        //   13375: new java/lang/StringBuilder
        //   13378: dup
        //   13379: invokespecial <init> : ()V
        //   13382: astore #18
        //   13384: aload #18
        //   13386: ldc_w 'Update tbl_tinnhanS set so_tin_nhan = '
        //   13389: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13392: pop
        //   13393: aload #18
        //   13395: aload #35
        //   13397: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13400: pop
        //   13401: aload #18
        //   13403: ldc_w ',  nd_phantich=''
        //   13406: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13409: pop
        //   13410: aload #18
        //   13412: aload #4
        //   13414: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13417: pop
        //   13418: aload #18
        //   13420: ldc_w '', phan_tich = ''
        //   13423: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13426: pop
        //   13427: aload #18
        //   13429: aload #28
        //   13431: invokevirtual toString : ()Ljava/lang/String;
        //   13434: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13437: pop
        //   13438: aload #18
        //   13440: ldc_w '', phat_hien_loi ='ok' Where id ='
        //   13443: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13446: pop
        //   13447: aload #18
        //   13449: aload_1
        //   13450: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   13453: pop
        //   13454: aload #32
        //   13456: aload #18
        //   13458: invokevirtual toString : ()Ljava/lang/String;
        //   13461: invokevirtual QueryData : (Ljava/lang/String;)V
        //   13464: goto -> 13539
        //   13467: new java/lang/StringBuilder
        //   13470: dup
        //   13471: invokespecial <init> : ()V
        //   13474: astore #18
        //   13476: aload #18
        //   13478: ldc_w 'Update tbl_tinnhanS set nd_phantich=''
        //   13481: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13484: pop
        //   13485: aload #18
        //   13487: aload #4
        //   13489: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13492: pop
        //   13493: aload #18
        //   13495: ldc_w '', phan_tich = ''
        //   13498: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13501: pop
        //   13502: aload #18
        //   13504: aload #28
        //   13506: invokevirtual toString : ()Ljava/lang/String;
        //   13509: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13512: pop
        //   13513: aload #18
        //   13515: ldc_w '', phat_hien_loi ='ok' Where id ='
        //   13518: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13521: pop
        //   13522: aload #18
        //   13524: aload_1
        //   13525: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   13528: pop
        //   13529: aload #32
        //   13531: aload #18
        //   13533: invokevirtual toString : ()Ljava/lang/String;
        //   13536: invokevirtual QueryData : (Ljava/lang/String;)V
        //   13539: goto -> 13705
        //   13542: aload #35
        //   13544: aload #31
        //   13546: if_acmpne -> 13620
        //   13549: new java/lang/StringBuilder
        //   13552: dup
        //   13553: invokespecial <init> : ()V
        //   13556: astore #4
        //   13558: aload #4
        //   13560: ldc_w 'Update tbl_tinnhanS set nd_phantich=''
        //   13563: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13566: pop
        //   13567: aload #4
        //   13569: aload #24
        //   13571: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13574: pop
        //   13575: aload #4
        //   13577: ldc_w '', phat_hien_loi = ''
        //   13580: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13583: pop
        //   13584: aload #4
        //   13586: aload_3
        //   13587: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13590: pop
        //   13591: aload #4
        //   13593: ldc_w ''  Where id ='
        //   13596: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13599: pop
        //   13600: aload #4
        //   13602: aload_1
        //   13603: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   13606: pop
        //   13607: aload #32
        //   13609: aload #4
        //   13611: invokevirtual toString : ()Ljava/lang/String;
        //   13614: invokevirtual QueryData : (Ljava/lang/String;)V
        //   13617: goto -> 13705
        //   13620: new java/lang/StringBuilder
        //   13623: dup
        //   13624: invokespecial <init> : ()V
        //   13627: astore #4
        //   13629: aload #4
        //   13631: ldc_w 'Update tbl_tinnhanS set so_tin_nhan = '
        //   13634: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13637: pop
        //   13638: aload #4
        //   13640: aload #35
        //   13642: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13645: pop
        //   13646: aload #4
        //   13648: ldc_w ', nd_phantich=''
        //   13651: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13654: pop
        //   13655: aload #4
        //   13657: aload #24
        //   13659: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13662: pop
        //   13663: aload #4
        //   13665: ldc_w '', phat_hien_loi = ''
        //   13668: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13671: pop
        //   13672: aload #4
        //   13674: aload_3
        //   13675: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13678: pop
        //   13679: aload #4
        //   13681: ldc_w ''  Where id ='
        //   13684: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   13687: pop
        //   13688: aload #4
        //   13690: aload_1
        //   13691: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   13694: pop
        //   13695: aload #32
        //   13697: aload #4
        //   13699: invokevirtual toString : ()Ljava/lang/String;
        //   13702: invokevirtual QueryData : (Ljava/lang/String;)V
        //   13705: aload #20
        //   13707: ifnull -> 13727
        //   13710: aload #20
        //   13712: invokeinterface isClosed : ()Z
        //   13717: ifne -> 13727
        //   13720: aload #20
        //   13722: invokeinterface close : ()V
        //   13727: aload #5
        //   13729: ifnull -> 13749
        //   13732: aload #5
        //   13734: invokeinterface isClosed : ()Z
        //   13739: ifne -> 13749
        //   13742: aload #5
        //   13744: invokeinterface close : ()V
        //   13749: return
        // Exception table:
        //   from	to	target	type
        //   588	594	656	org/json/JSONException
        //   602	614	651	org/json/JSONException
        //   618	629	651	org/json/JSONException
        //   633	644	651	org/json/JSONException
        //   747	761	1370	java/lang/Exception
        //   747	761	1375	finally
        //   791	807	1370	java/lang/Exception
        //   791	807	1375	finally
        //   842	863	986	java/lang/Exception
        //   842	863	1375	finally
        //   875	883	986	java/lang/Exception
        //   875	883	1375	finally
        //   895	900	986	java/lang/Exception
        //   895	900	1375	finally
        //   908	913	986	java/lang/Exception
        //   908	913	1375	finally
        //   921	935	1361	java/lang/Exception
        //   921	935	1375	finally
        //   943	956	1361	java/lang/Exception
        //   943	956	1375	finally
        //   964	971	1361	java/lang/Exception
        //   964	971	1375	finally
        //   1022	1029	1361	java/lang/Exception
        //   1022	1029	1375	finally
        //   1054	1064	1197	java/lang/Exception
        //   1054	1064	1189	finally
        //   1080	1095	1197	java/lang/Exception
        //   1080	1095	1189	finally
        //   1103	1130	1197	java/lang/Exception
        //   1103	1130	1189	finally
        //   1146	1154	1197	java/lang/Exception
        //   1146	1154	1189	finally
        //   1166	1175	1197	java/lang/Exception
        //   1166	1175	1189	finally
        //   3028	3044	9995	java/lang/Exception
        //   3049	3056	3419	java/lang/Exception
        //   3080	3090	3348	java/lang/Exception
        //   3094	3106	3300	java/lang/Exception
        //   3116	3164	3246	java/lang/Exception
        //   3168	3198	3205	java/lang/Exception
        //   3493	3514	9960	java/lang/Exception
        //   3520	3532	3535	java/lang/Exception
        //   3577	3586	9960	java/lang/Exception
        //   3592	3604	3535	java/lang/Exception
        //   3607	3616	9960	java/lang/Exception
        //   3622	3634	3535	java/lang/Exception
        //   3637	3646	9960	java/lang/Exception
        //   3652	3664	3535	java/lang/Exception
        //   3667	3676	9960	java/lang/Exception
        //   3682	3694	3535	java/lang/Exception
        //   3697	3706	9960	java/lang/Exception
        //   3712	3724	3535	java/lang/Exception
        //   3727	3736	9960	java/lang/Exception
        //   3742	3754	3535	java/lang/Exception
        //   3757	3766	9960	java/lang/Exception
        //   3772	3784	3535	java/lang/Exception
        //   3787	3796	9960	java/lang/Exception
        //   3802	3814	3535	java/lang/Exception
        //   3817	3826	9960	java/lang/Exception
        //   3832	3844	3535	java/lang/Exception
        //   3847	3857	9960	java/lang/Exception
        //   3863	3876	3535	java/lang/Exception
        //   3879	3888	9960	java/lang/Exception
        //   3894	3906	3535	java/lang/Exception
        //   3909	3919	9960	java/lang/Exception
        //   3925	3938	3535	java/lang/Exception
        //   3941	3950	9960	java/lang/Exception
        //   3956	3968	3535	java/lang/Exception
        //   3971	3981	9960	java/lang/Exception
        //   3987	4000	3535	java/lang/Exception
        //   4007	4016	9955	java/lang/Exception
        //   4022	4034	4037	java/lang/Exception
        //   4075	4085	9955	java/lang/Exception
        //   4091	4108	4122	java/lang/Exception
        //   4108	4119	4502	java/lang/Exception
        //   4160	4182	9913	java/lang/Exception
        //   4186	4196	9913	java/lang/Exception
        //   4202	4217	9710	java/lang/Exception
        //   4220	4270	9710	java/lang/Exception
        //   4276	4342	4502	java/lang/Exception
        //   4348	4420	4502	java/lang/Exception
        //   4420	4458	4461	java/lang/Exception
        //   4548	4574	9670	java/lang/Exception
        //   4581	4638	6845	java/lang/Exception
        //   4641	4658	6845	java/lang/Exception
        //   4663	4699	4461	java/lang/Exception
        //   4730	4742	6845	java/lang/Exception
        //   4745	4756	6806	java/lang/Exception
        //   4759	4771	6806	java/lang/Exception
        //   4774	4783	6806	java/lang/Exception
        //   4796	4801	6806	java/lang/Exception
        //   4808	4826	4866	java/lang/Exception
        //   4832	4851	4933	java/lang/Exception
        //   4917	4930	4933	java/lang/Exception
        //   4969	4977	6743	java/lang/Exception
        //   4980	4985	6703	java/lang/Exception
        //   4992	5032	5662	java/lang/Exception
        //   5035	5047	5662	java/lang/Exception
        //   5054	5084	5152	java/lang/Exception
        //   5092	5117	5120	java/lang/Exception
        //   5204	5214	5460	java/lang/Exception
        //   5221	5229	5460	java/lang/Exception
        //   5233	5243	5460	java/lang/Exception
        //   5253	5264	6669	java/lang/Exception
        //   5277	5294	6669	java/lang/Exception
        //   5298	5308	6669	java/lang/Exception
        //   5312	5317	6669	java/lang/Exception
        //   5321	5326	6669	java/lang/Exception
        //   5330	5338	6669	java/lang/Exception
        //   5342	5358	6669	java/lang/Exception
        //   5362	5371	6669	java/lang/Exception
        //   5378	5388	6669	java/lang/Exception
        //   5392	5397	6669	java/lang/Exception
        //   5401	5406	6669	java/lang/Exception
        //   5410	5418	6669	java/lang/Exception
        //   5422	5438	6669	java/lang/Exception
        //   5442	5451	6669	java/lang/Exception
        //   5502	5514	6669	java/lang/Exception
        //   5517	5527	6669	java/lang/Exception
        //   5530	5535	6669	java/lang/Exception
        //   5538	5543	6669	java/lang/Exception
        //   5546	5554	6669	java/lang/Exception
        //   5557	5573	6669	java/lang/Exception
        //   5576	5585	6669	java/lang/Exception
        //   5591	5601	6669	java/lang/Exception
        //   5604	5609	6669	java/lang/Exception
        //   5612	5617	6669	java/lang/Exception
        //   5620	5628	6669	java/lang/Exception
        //   5631	5647	6669	java/lang/Exception
        //   5650	5659	6669	java/lang/Exception
        //   5713	5732	6669	java/lang/Exception
        //   5736	5751	6669	java/lang/Exception
        //   5755	5773	6669	java/lang/Exception
        //   5779	5830	5833	java/lang/Exception
        //   5839	5849	6669	java/lang/Exception
        //   5853	5858	6669	java/lang/Exception
        //   5862	5867	6669	java/lang/Exception
        //   5871	5878	6669	java/lang/Exception
        //   5882	5898	6669	java/lang/Exception
        //   5902	5911	6669	java/lang/Exception
        //   5918	5931	6669	java/lang/Exception
        //   5937	5988	5991	java/lang/Exception
        //   5997	6007	6669	java/lang/Exception
        //   6011	6016	6669	java/lang/Exception
        //   6020	6025	6669	java/lang/Exception
        //   6029	6036	6669	java/lang/Exception
        //   6040	6056	6669	java/lang/Exception
        //   6060	6069	6669	java/lang/Exception
        //   6076	6081	6638	java/lang/Exception
        //   6088	6098	6101	java/lang/Exception
        //   6103	6150	6638	java/lang/Exception
        //   6158	6169	6589	java/lang/Exception
        //   6173	6183	6589	java/lang/Exception
        //   6197	6205	6589	java/lang/Exception
        //   6209	6216	6589	java/lang/Exception
        //   6220	6242	6558	java/lang/Exception
        //   6255	6264	6558	java/lang/Exception
        //   6268	6278	6558	java/lang/Exception
        //   6291	6301	6558	java/lang/Exception
        //   6305	6310	6558	java/lang/Exception
        //   6314	6319	6558	java/lang/Exception
        //   6323	6330	6558	java/lang/Exception
        //   6334	6347	6558	java/lang/Exception
        //   6351	6360	6558	java/lang/Exception
        //   6385	6395	6558	java/lang/Exception
        //   6399	6404	6558	java/lang/Exception
        //   6408	6413	6558	java/lang/Exception
        //   6417	6424	6558	java/lang/Exception
        //   6428	6444	6558	java/lang/Exception
        //   6448	6457	6558	java/lang/Exception
        //   6468	6478	6558	java/lang/Exception
        //   6482	6487	6558	java/lang/Exception
        //   6491	6496	6558	java/lang/Exception
        //   6500	6507	6558	java/lang/Exception
        //   6511	6527	6558	java/lang/Exception
        //   6531	6540	6558	java/lang/Exception
        //   6896	6908	9628	java/lang/Exception
        //   6915	7031	7105	java/lang/Exception
        //   7031	7055	7081	java/lang/Exception
        //   7055	7078	7358	java/lang/Exception
        //   7137	7148	9608	java/lang/Exception
        //   7154	7166	8340	java/lang/Exception
        //   7173	7355	7358	java/lang/Exception
        //   7382	7394	8340	java/lang/Exception
        //   7401	7473	7358	java/lang/Exception
        //   7480	7495	8340	java/lang/Exception
        //   7503	7706	7358	java/lang/Exception
        //   7706	7718	8340	java/lang/Exception
        //   7725	7947	7358	java/lang/Exception
        //   7947	7959	8340	java/lang/Exception
        //   7970	7997	8014	java/lang/Exception
        //   8034	8219	8335	java/lang/Exception
        //   8219	8237	8295	java/lang/Exception
        //   8245	8261	8452	java/lang/Exception
        //   8265	8273	8452	java/lang/Exception
        //   8277	8292	8452	java/lang/Exception
        //   8369	8420	9603	java/lang/Exception
        //   8430	8440	8452	java/lang/Exception
        //   8457	8562	9603	java/lang/Exception
        //   8589	8605	9582	java/lang/Exception
        //   8632	8640	9582	java/lang/Exception
        //   8667	8683	9582	java/lang/Exception
        //   8713	8728	9582	java/lang/Exception
        //   8755	8770	9582	java/lang/Exception
        //   8797	8812	9582	java/lang/Exception
        //   8839	8854	9582	java/lang/Exception
        //   8881	8896	9582	java/lang/Exception
        //   8923	8938	9582	java/lang/Exception
        //   8965	8977	9582	java/lang/Exception
        //   9004	9014	9582	java/lang/Exception
        //   9041	9046	9582	java/lang/Exception
        //   9073	9078	9582	java/lang/Exception
        //   9105	9113	9582	java/lang/Exception
        //   9140	9156	9582	java/lang/Exception
        //   9183	9192	9582	java/lang/Exception
        //   9219	9229	9582	java/lang/Exception
        //   9256	9268	9582	java/lang/Exception
        //   9295	9307	9582	java/lang/Exception
        //   9334	9339	9582	java/lang/Exception
        //   9366	9371	9582	java/lang/Exception
        //   9398	9406	9582	java/lang/Exception
        //   9433	9449	9582	java/lang/Exception
        //   9476	9484	9582	java/lang/Exception
        //   9511	9527	9582	java/lang/Exception
        //   9565	9572	9582	java/lang/Exception
        //   9763	9815	9889	java/lang/Exception
        //   9818	9841	9866	java/lang/Exception
        }

public void NhapSoChiTiet(int paramInt) {
        // Byte code:
        //   0: aload_0
        //   1: astore_2
        //   2: ldc_w 'the_loai'
        //   5: astore_3
        //   6: dconst_0
        //   7: dstore #4
        //   9: new java/lang/StringBuilder
        //   12: dup
        //   13: invokespecial <init> : ()V
        //   16: astore #6
        //   18: aload #6
        //   20: ldc_w 'Select * From tbl_tinnhanS WHERE id = '
        //   23: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   26: pop
        //   27: aload #6
        //   29: iload_1
        //   30: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   33: pop
        //   34: aload_2
        //   35: aload #6
        //   37: invokevirtual toString : ()Ljava/lang/String;
        //   40: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   43: astore #7
        //   45: aload #7
        //   47: invokeinterface moveToFirst : ()Z
        //   52: pop
        //   53: new java/lang/StringBuilder
        //   56: dup
        //   57: invokespecial <init> : ()V
        //   60: astore #6
        //   62: aload #6
        //   64: ldc_w 'Select * From tbl_soctS where ten_kh = ''
        //   67: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   70: pop
        //   71: aload #6
        //   73: aload #7
        //   75: iconst_4
        //   76: invokeinterface getString : (I)Ljava/lang/String;
        //   81: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   84: pop
        //   85: aload #6
        //   87: ldc_w '' And ngay_nhan = ''
        //   90: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   93: pop
        //   94: aload #6
        //   96: aload #7
        //   98: iconst_1
        //   99: invokeinterface getString : (I)Ljava/lang/String;
        //   104: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   107: pop
        //   108: aload #6
        //   110: ldc_w '' And type_kh = '
        //   113: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   116: pop
        //   117: aload #6
        //   119: aload #7
        //   121: iconst_3
        //   122: invokeinterface getString : (I)Ljava/lang/String;
        //   127: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   130: pop
        //   131: aload #6
        //   133: ldc_w ' And so_tin_nhan = '
        //   136: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   139: pop
        //   140: aload #6
        //   142: aload #7
        //   144: bipush #7
        //   146: invokeinterface getString : (I)Ljava/lang/String;
        //   151: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   154: pop
        //   155: aload #6
        //   157: invokevirtual toString : ()Ljava/lang/String;
        //   160: astore #8
        //   162: aload_2
        //   163: aload #8
        //   165: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   168: astore #9
        //   170: aload #9
        //   172: invokeinterface getCount : ()I
        //   177: ifne -> 4499
        //   180: new org/json/JSONObject
        //   183: astore #6
        //   185: aload #6
        //   187: aload #7
        //   189: bipush #15
        //   191: invokeinterface getString : (I)Ljava/lang/String;
        //   196: invokespecial <init> : (Ljava/lang/String;)V
        //   199: aload_2
        //   200: aload #6
        //   202: putfield jsonDanSo : Lorg/json/JSONObject;
        //   205: goto -> 215
        //   208: astore #6
        //   210: aload #6
        //   212: invokevirtual printStackTrace : ()V
        //   215: aload #7
        //   217: iconst_4
        //   218: invokeinterface getString : (I)Ljava/lang/String;
        //   223: astore #10
        //   225: ldc ''
        //   227: astore #11
        //   229: aload #7
        //   231: iconst_1
        //   232: invokeinterface getString : (I)Ljava/lang/String;
        //   237: astore #12
        //   239: new java/lang/StringBuilder
        //   242: dup
        //   243: invokespecial <init> : ()V
        //   246: astore #6
        //   248: ldc ''
        //   250: astore #13
        //   252: aload #6
        //   254: ldc_w 'Select * From tbl_KH_new where ten_kh = ''
        //   257: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   260: pop
        //   261: aload #6
        //   263: aload #10
        //   265: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   268: pop
        //   269: aload #6
        //   271: ldc_w '''
        //   274: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   277: pop
        //   278: aload_2
        //   279: aload #6
        //   281: invokevirtual toString : ()Ljava/lang/String;
        //   284: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   287: astore #14
        //   289: aload #14
        //   291: invokeinterface moveToFirst : ()Z
        //   296: pop
        //   297: new org/json/JSONObject
        //   300: astore #6
        //   302: aload #6
        //   304: aload #14
        //   306: iconst_5
        //   307: invokeinterface getString : (I)Ljava/lang/String;
        //   312: invokespecial <init> : (Ljava/lang/String;)V
        //   315: aload_2
        //   316: aload #6
        //   318: putfield json : Lorg/json/JSONObject;
        //   321: aload_2
        //   322: aload #6
        //   324: ldc_w 'caidat_gia'
        //   327: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   330: putfield caidat_gia : Lorg/json/JSONObject;
        //   333: aload_2
        //   334: aload_2
        //   335: getfield json : Lorg/json/JSONObject;
        //   338: ldc_w 'caidat_tg'
        //   341: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
        //   344: putfield caidat_tg : Lorg/json/JSONObject;
        //   347: goto -> 362
        //   350: astore #6
        //   352: goto -> 357
        //   355: astore #6
        //   357: aload #6
        //   359: invokevirtual printStackTrace : ()V
        //   362: dconst_0
        //   363: dstore #15
        //   365: ldc ''
        //   367: astore #17
        //   369: aload_0
        //   370: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
        //   373: astore #6
        //   375: new android/database/DatabaseUtils$InsertHelper
        //   378: dup
        //   379: aload #6
        //   381: ldc_w 'tbl_soctS'
        //   384: invokespecial <init> : (Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)V
        //   387: astore #18
        //   389: aload #6
        //   391: invokevirtual beginTransaction : ()V
        //   394: aload_2
        //   395: getfield jsonDanSo : Lorg/json/JSONObject;
        //   398: invokevirtual keys : ()Ljava/util/Iterator;
        //   401: astore #19
        //   403: aload #18
        //   405: astore_2
        //   406: aload_0
        //   407: astore #13
        //   409: aload #19
        //   411: invokeinterface hasNext : ()Z
        //   416: ifeq -> 4255
        //   419: aload #19
        //   421: invokeinterface next : ()Ljava/lang/Object;
        //   426: checkcast java/lang/String
        //   429: astore #18
        //   431: new org/json/JSONObject
        //   434: astore #20
        //   436: aload #20
        //   438: aload #13
        //   440: getfield jsonDanSo : Lorg/json/JSONObject;
        //   443: aload #18
        //   445: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   448: invokespecial <init> : (Ljava/lang/String;)V
        //   451: aload #20
        //   453: ldc_w 'dan_so'
        //   456: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   459: astore #21
        //   461: aload #17
        //   463: astore #18
        //   465: aload #20
        //   467: ldc_w 'so_tien'
        //   470: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   473: astore #22
        //   475: aload #22
        //   477: astore #18
        //   479: aload #22
        //   481: astore #17
        //   483: aload #20
        //   485: aload_3
        //   486: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   489: ldc 'de dau db'
        //   491: invokevirtual indexOf : (Ljava/lang/String;)I
        //   494: istore_1
        //   495: aload #9
        //   497: astore #18
        //   499: aload #6
        //   501: astore #17
        //   503: aload_2
        //   504: astore #6
        //   506: ldc_w 'xi'
        //   509: astore #23
        //   511: iload_1
        //   512: iconst_m1
        //   513: if_icmple -> 523
        //   516: ldc 'dea'
        //   518: astore #9
        //   520: goto -> 845
        //   523: aload #20
        //   525: aload_3
        //   526: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   529: ldc 'de dit db'
        //   531: invokevirtual indexOf : (Ljava/lang/String;)I
        //   534: iconst_m1
        //   535: if_icmple -> 545
        //   538: ldc 'deb'
        //   540: astore #9
        //   542: goto -> 845
        //   545: aload #20
        //   547: aload_3
        //   548: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   551: ldc 'de 8'
        //   553: invokevirtual indexOf : (Ljava/lang/String;)I
        //   556: iconst_m1
        //   557: if_icmple -> 567
        //   560: ldc 'det'
        //   562: astore #9
        //   564: goto -> 845
        //   567: aload #20
        //   569: aload_3
        //   570: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   573: ldc 'de dau nhat'
        //   575: invokevirtual indexOf : (Ljava/lang/String;)I
        //   578: iconst_m1
        //   579: if_icmple -> 589
        //   582: ldc 'dec'
        //   584: astore #9
        //   586: goto -> 845
        //   589: aload #20
        //   591: aload_3
        //   592: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   595: ldc 'de dit nhat'
        //   597: invokevirtual indexOf : (Ljava/lang/String;)I
        //   600: iconst_m1
        //   601: if_icmple -> 611
        //   604: ldc 'ded'
        //   606: astore #9
        //   608: goto -> 845
        //   611: aload #20
        //   613: aload_3
        //   614: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   617: ldc_w 'bc dau'
        //   620: invokevirtual indexOf : (Ljava/lang/String;)I
        //   623: istore_1
        //   624: iload_1
        //   625: iconst_m1
        //   626: if_icmple -> 676
        //   629: ldc_w 'bca'
        //   632: astore #9
        //   634: goto -> 845
        //   637: astore #9
        //   639: aload #6
        //   641: astore #7
        //   643: aload #9
        //   645: astore #6
        //   647: goto -> 4481
        //   650: astore #9
        //   652: aload #14
        //   654: astore #22
        //   656: aload #6
        //   658: astore_2
        //   659: aload #7
        //   661: astore #14
        //   663: aload #22
        //   665: astore #6
        //   667: aload_2
        //   668: astore #7
        //   670: aload #18
        //   672: astore_2
        //   673: goto -> 4422
        //   676: aload #20
        //   678: aload_3
        //   679: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   682: ldc_w 'bc'
        //   685: invokevirtual indexOf : (Ljava/lang/String;)I
        //   688: iconst_m1
        //   689: if_icmple -> 700
        //   692: ldc_w 'bc'
        //   695: astore #9
        //   697: goto -> 845
        //   700: aload #20
        //   702: aload_3
        //   703: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   706: ldc 'lo dau'
        //   708: invokevirtual indexOf : (Ljava/lang/String;)I
        //   711: istore_1
        //   712: iload_1
        //   713: iconst_m1
        //   714: if_icmple -> 724
        //   717: ldc 'loa'
        //   719: astore #9
        //   721: goto -> 845
        //   724: aload #20
        //   726: aload_3
        //   727: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   730: ldc 'lo'
        //   732: invokevirtual indexOf : (Ljava/lang/String;)I
        //   735: iconst_m1
        //   736: if_icmple -> 746
        //   739: ldc 'lo'
        //   741: astore #9
        //   743: goto -> 845
        //   746: aload #20
        //   748: aload_3
        //   749: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   752: ldc_w 'xien dau'
        //   755: invokevirtual indexOf : (Ljava/lang/String;)I
        //   758: istore_1
        //   759: iload_1
        //   760: iconst_m1
        //   761: if_icmple -> 772
        //   764: ldc_w 'xia'
        //   767: astore #9
        //   769: goto -> 845
        //   772: aload #20
        //   774: aload_3
        //   775: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   778: ldc_w 'xi'
        //   781: invokevirtual indexOf : (Ljava/lang/String;)I
        //   784: istore_1
        //   785: iload_1
        //   786: iconst_m1
        //   787: if_icmpgt -> 840
        //   790: aload #20
        //   792: aload_3
        //   793: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   796: ldc_w 'xg'
        //   799: invokevirtual indexOf : (Ljava/lang/String;)I
        //   802: iconst_m1
        //   803: if_icmple -> 809
        //   806: goto -> 840
        //   809: aload #20
        //   811: aload_3
        //   812: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   815: ldc 'xn'
        //   817: invokevirtual indexOf : (Ljava/lang/String;)I
        //   820: istore_1
        //   821: iload_1
        //   822: iconst_m1
        //   823: if_icmple -> 833
        //   826: ldc 'xn'
        //   828: astore #9
        //   830: goto -> 845
        //   833: aload #11
        //   835: astore #9
        //   837: goto -> 845
        //   840: ldc_w 'xi'
        //   843: astore #9
        //   845: aload #9
        //   847: ldc 'dea'
        //   849: invokevirtual indexOf : (Ljava/lang/String;)I
        //   852: istore_1
        //   853: iload_1
        //   854: iconst_m1
        //   855: if_icmpgt -> 1151
        //   858: aload #9
        //   860: ldc 'deb'
        //   862: invokevirtual indexOf : (Ljava/lang/String;)I
        //   865: iconst_m1
        //   866: if_icmpgt -> 1109
        //   869: aload #9
        //   871: ldc 'dec'
        //   873: invokevirtual indexOf : (Ljava/lang/String;)I
        //   876: iconst_m1
        //   877: if_icmpgt -> 1106
        //   880: aload #9
        //   882: ldc 'ded'
        //   884: invokevirtual indexOf : (Ljava/lang/String;)I
        //   887: iconst_m1
        //   888: if_icmpgt -> 1103
        //   891: aload #9
        //   893: ldc 'det'
        //   895: invokevirtual indexOf : (Ljava/lang/String;)I
        //   898: iconst_m1
        //   899: if_icmple -> 905
        //   902: goto -> 1151
        //   905: aload #9
        //   907: ldc 'lo'
        //   909: invokevirtual indexOf : (Ljava/lang/String;)I
        //   912: iconst_m1
        //   913: if_icmple -> 949
        //   916: aload #13
        //   918: getfield caidat_tg : Lorg/json/JSONObject;
        //   921: ldc_w 'khgiu_lo'
        //   924: invokevirtual getInt : (Ljava/lang/String;)I
        //   927: istore_1
        //   928: iload_1
        //   929: i2d
        //   930: dstore #24
        //   932: aload #13
        //   934: getfield caidat_tg : Lorg/json/JSONObject;
        //   937: ldc_w 'dlgiu_lo'
        //   940: invokevirtual getInt : (Ljava/lang/String;)I
        //   943: i2d
        //   944: dstore #26
        //   946: goto -> 1179
        //   949: aload #9
        //   951: ldc_w 'xi'
        //   954: invokevirtual indexOf : (Ljava/lang/String;)I
        //   957: iconst_m1
        //   958: if_icmpgt -> 1070
        //   961: aload #9
        //   963: ldc_w 'xq'
        //   966: invokevirtual indexOf : (Ljava/lang/String;)I
        //   969: iconst_m1
        //   970: if_icmple -> 976
        //   973: goto -> 1070
        //   976: aload #9
        //   978: ldc 'xn'
        //   980: invokevirtual indexOf : (Ljava/lang/String;)I
        //   983: iconst_m1
        //   984: if_icmple -> 1018
        //   987: aload #13
        //   989: getfield caidat_tg : Lorg/json/JSONObject;
        //   992: ldc_w 'khgiu_xn'
        //   995: invokevirtual getInt : (Ljava/lang/String;)I
        //   998: i2d
        //   999: dstore #24
        //   1001: aload #13
        //   1003: getfield caidat_tg : Lorg/json/JSONObject;
        //   1006: ldc_w 'dlgiu_xn'
        //   1009: invokevirtual getInt : (Ljava/lang/String;)I
        //   1012: i2d
        //   1013: dstore #26
        //   1015: goto -> 1179
        //   1018: aload #9
        //   1020: ldc_w 'bc'
        //   1023: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1026: iconst_m1
        //   1027: if_icmple -> 1061
        //   1030: aload #13
        //   1032: getfield caidat_tg : Lorg/json/JSONObject;
        //   1035: ldc_w 'khgiu_bc'
        //   1038: invokevirtual getInt : (Ljava/lang/String;)I
        //   1041: i2d
        //   1042: dstore #24
        //   1044: aload #13
        //   1046: getfield caidat_tg : Lorg/json/JSONObject;
        //   1049: ldc_w 'dlgiu_bc'
        //   1052: invokevirtual getInt : (Ljava/lang/String;)I
        //   1055: i2d
        //   1056: dstore #26
        //   1058: goto -> 1179
        //   1061: dconst_0
        //   1062: dstore #24
        //   1064: dconst_0
        //   1065: dstore #26
        //   1067: goto -> 1179
        //   1070: aload #13
        //   1072: getfield caidat_tg : Lorg/json/JSONObject;
        //   1075: ldc_w 'khgiu_xi'
        //   1078: invokevirtual getInt : (Ljava/lang/String;)I
        //   1081: i2d
        //   1082: dstore #24
        //   1084: aload #13
        //   1086: getfield caidat_tg : Lorg/json/JSONObject;
        //   1089: ldc_w 'dlgiu_xi'
        //   1092: invokevirtual getInt : (Ljava/lang/String;)I
        //   1095: istore_1
        //   1096: iload_1
        //   1097: i2d
        //   1098: dstore #26
        //   1100: goto -> 1179
        //   1103: goto -> 1151
        //   1106: goto -> 1151
        //   1109: goto -> 1151
        //   1112: astore #9
        //   1114: aload #6
        //   1116: astore #7
        //   1118: aload #9
        //   1120: astore #6
        //   1122: goto -> 4481
        //   1125: astore #9
        //   1127: aload #14
        //   1129: astore #22
        //   1131: aload #6
        //   1133: astore_2
        //   1134: aload #7
        //   1136: astore #14
        //   1138: aload #22
        //   1140: astore #6
        //   1142: aload_2
        //   1143: astore #7
        //   1145: aload #18
        //   1147: astore_2
        //   1148: goto -> 4422
        //   1151: aload #13
        //   1153: getfield caidat_tg : Lorg/json/JSONObject;
        //   1156: ldc_w 'khgiu_de'
        //   1159: invokevirtual getInt : (Ljava/lang/String;)I
        //   1162: i2d
        //   1163: dstore #24
        //   1165: aload #13
        //   1167: getfield caidat_tg : Lorg/json/JSONObject;
        //   1170: ldc_w 'dlgiu_de'
        //   1173: invokevirtual getInt : (Ljava/lang/String;)I
        //   1176: i2d
        //   1177: dstore #26
        //   1179: aload #21
        //   1181: astore_2
        //   1182: aload_3
        //   1183: astore #11
        //   1185: aload #9
        //   1187: ldc 'dea'
        //   1189: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1192: istore_1
        //   1193: ldc_w 'gia_x2'
        //   1196: astore #28
        //   1198: iload_1
        //   1199: iconst_m1
        //   1200: if_icmple -> 1333
        //   1203: aload #13
        //   1205: getfield caidat_gia : Lorg/json/JSONObject;
        //   1208: ldc 'dea'
        //   1210: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1213: dstore #4
        //   1215: dload #4
        //   1217: dstore #15
        //   1219: dload #4
        //   1221: dstore #15
        //   1223: aload #13
        //   1225: getfield caidat_gia : Lorg/json/JSONObject;
        //   1228: ldc_w 'an_dea'
        //   1231: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1234: dstore #29
        //   1236: dload #4
        //   1238: dstore #15
        //   1240: dload #29
        //   1242: dstore #4
        //   1244: goto -> 1938
        //   1247: astore #9
        //   1249: aload #6
        //   1251: astore #7
        //   1253: aload #9
        //   1255: astore #6
        //   1257: goto -> 4481
        //   1260: astore #9
        //   1262: aload #14
        //   1264: astore #22
        //   1266: aload #6
        //   1268: astore #14
        //   1270: aload #7
        //   1272: astore_2
        //   1273: aload #22
        //   1275: astore #6
        //   1277: aload #14
        //   1279: astore #7
        //   1281: aload_2
        //   1282: astore #14
        //   1284: aload #18
        //   1286: astore_2
        //   1287: goto -> 4422
        //   1290: astore #9
        //   1292: aload #6
        //   1294: astore #7
        //   1296: aload #9
        //   1298: astore #6
        //   1300: goto -> 4481
        //   1303: astore #9
        //   1305: aload #14
        //   1307: astore #22
        //   1309: aload #6
        //   1311: astore #14
        //   1313: aload #7
        //   1315: astore_2
        //   1316: aload #22
        //   1318: astore #6
        //   1320: aload #14
        //   1322: astore #7
        //   1324: aload_2
        //   1325: astore #14
        //   1327: aload #18
        //   1329: astore_2
        //   1330: goto -> 4422
        //   1333: aload #9
        //   1335: ldc 'deb'
        //   1337: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1340: istore_1
        //   1341: iload_1
        //   1342: iconst_m1
        //   1343: if_icmple -> 1390
        //   1346: aload #13
        //   1348: getfield caidat_gia : Lorg/json/JSONObject;
        //   1351: ldc 'deb'
        //   1353: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1356: dstore #4
        //   1358: dload #4
        //   1360: dstore #15
        //   1362: dload #4
        //   1364: dstore #15
        //   1366: aload #13
        //   1368: getfield caidat_gia : Lorg/json/JSONObject;
        //   1371: ldc_w 'an_deb'
        //   1374: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1377: dstore #29
        //   1379: dload #4
        //   1381: dstore #15
        //   1383: dload #29
        //   1385: dstore #4
        //   1387: goto -> 1938
        //   1390: aload #9
        //   1392: ldc 'dec'
        //   1394: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1397: istore_1
        //   1398: iload_1
        //   1399: iconst_m1
        //   1400: if_icmple -> 1447
        //   1403: aload #13
        //   1405: getfield caidat_gia : Lorg/json/JSONObject;
        //   1408: ldc 'dec'
        //   1410: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1413: dstore #4
        //   1415: dload #4
        //   1417: dstore #15
        //   1419: dload #4
        //   1421: dstore #15
        //   1423: aload #13
        //   1425: getfield caidat_gia : Lorg/json/JSONObject;
        //   1428: ldc_w 'an_dec'
        //   1431: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1434: dstore #29
        //   1436: dload #4
        //   1438: dstore #15
        //   1440: dload #29
        //   1442: dstore #4
        //   1444: goto -> 1938
        //   1447: aload #9
        //   1449: ldc 'ded'
        //   1451: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1454: istore_1
        //   1455: iload_1
        //   1456: iconst_m1
        //   1457: if_icmple -> 1504
        //   1460: aload #13
        //   1462: getfield caidat_gia : Lorg/json/JSONObject;
        //   1465: ldc 'ded'
        //   1467: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1470: dstore #4
        //   1472: dload #4
        //   1474: dstore #15
        //   1476: dload #4
        //   1478: dstore #15
        //   1480: aload #13
        //   1482: getfield caidat_gia : Lorg/json/JSONObject;
        //   1485: ldc_w 'an_ded'
        //   1488: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1491: dstore #29
        //   1493: dload #4
        //   1495: dstore #15
        //   1497: dload #29
        //   1499: dstore #4
        //   1501: goto -> 1938
        //   1504: aload #9
        //   1506: ldc 'det'
        //   1508: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1511: istore_1
        //   1512: iload_1
        //   1513: iconst_m1
        //   1514: if_icmple -> 1561
        //   1517: aload #13
        //   1519: getfield caidat_gia : Lorg/json/JSONObject;
        //   1522: ldc 'det'
        //   1524: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1527: dstore #4
        //   1529: dload #4
        //   1531: dstore #15
        //   1533: dload #4
        //   1535: dstore #15
        //   1537: aload #13
        //   1539: getfield caidat_gia : Lorg/json/JSONObject;
        //   1542: ldc_w 'an_det'
        //   1545: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1548: dstore #29
        //   1550: dload #4
        //   1552: dstore #15
        //   1554: dload #29
        //   1556: dstore #4
        //   1558: goto -> 1938
        //   1561: aload #9
        //   1563: ldc 'lo'
        //   1565: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1568: istore_1
        //   1569: iload_1
        //   1570: iconst_m1
        //   1571: if_icmple -> 1618
        //   1574: aload #13
        //   1576: getfield caidat_gia : Lorg/json/JSONObject;
        //   1579: ldc 'lo'
        //   1581: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1584: dstore #4
        //   1586: dload #4
        //   1588: dstore #15
        //   1590: dload #4
        //   1592: dstore #15
        //   1594: aload #13
        //   1596: getfield caidat_gia : Lorg/json/JSONObject;
        //   1599: ldc_w 'an_lo'
        //   1602: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1605: dstore #29
        //   1607: dload #4
        //   1609: dstore #15
        //   1611: dload #29
        //   1613: dstore #4
        //   1615: goto -> 1938
        //   1618: aload #9
        //   1620: ldc_w 'xi'
        //   1623: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1626: istore_1
        //   1627: iload_1
        //   1628: iconst_m1
        //   1629: if_icmple -> 1685
        //   1632: aload_2
        //   1633: invokevirtual length : ()I
        //   1636: iconst_5
        //   1637: if_icmpne -> 1685
        //   1640: aload #13
        //   1642: getfield caidat_gia : Lorg/json/JSONObject;
        //   1645: ldc_w 'gia_x2'
        //   1648: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1651: dstore #4
        //   1653: dload #4
        //   1655: dstore #15
        //   1657: dload #4
        //   1659: dstore #15
        //   1661: aload #13
        //   1663: getfield caidat_gia : Lorg/json/JSONObject;
        //   1666: ldc_w 'an_x2'
        //   1669: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1672: dstore #29
        //   1674: dload #4
        //   1676: dstore #15
        //   1678: dload #29
        //   1680: dstore #4
        //   1682: goto -> 1938
        //   1685: aload #9
        //   1687: ldc_w 'xi'
        //   1690: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1693: istore_1
        //   1694: iload_1
        //   1695: iconst_m1
        //   1696: if_icmple -> 1753
        //   1699: aload_2
        //   1700: invokevirtual length : ()I
        //   1703: bipush #8
        //   1705: if_icmpne -> 1753
        //   1708: aload #13
        //   1710: getfield caidat_gia : Lorg/json/JSONObject;
        //   1713: ldc_w 'gia_x3'
        //   1716: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1719: dstore #4
        //   1721: dload #4
        //   1723: dstore #15
        //   1725: dload #4
        //   1727: dstore #15
        //   1729: aload #13
        //   1731: getfield caidat_gia : Lorg/json/JSONObject;
        //   1734: ldc_w 'an_x3'
        //   1737: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1740: dstore #29
        //   1742: dload #4
        //   1744: dstore #15
        //   1746: dload #29
        //   1748: dstore #4
        //   1750: goto -> 1938
        //   1753: aload #9
        //   1755: ldc_w 'xi'
        //   1758: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1761: istore_1
        //   1762: iload_1
        //   1763: iconst_m1
        //   1764: if_icmple -> 1821
        //   1767: aload_2
        //   1768: invokevirtual length : ()I
        //   1771: bipush #11
        //   1773: if_icmpne -> 1821
        //   1776: aload #13
        //   1778: getfield caidat_gia : Lorg/json/JSONObject;
        //   1781: ldc_w 'gia_x4'
        //   1784: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1787: dstore #4
        //   1789: dload #4
        //   1791: dstore #15
        //   1793: dload #4
        //   1795: dstore #15
        //   1797: aload #13
        //   1799: getfield caidat_gia : Lorg/json/JSONObject;
        //   1802: ldc_w 'an_x4'
        //   1805: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1808: dstore #29
        //   1810: dload #4
        //   1812: dstore #15
        //   1814: dload #29
        //   1816: dstore #4
        //   1818: goto -> 1938
        //   1821: aload #9
        //   1823: ldc 'xn'
        //   1825: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1828: istore_1
        //   1829: iload_1
        //   1830: iconst_m1
        //   1831: if_icmple -> 1879
        //   1834: aload #13
        //   1836: getfield caidat_gia : Lorg/json/JSONObject;
        //   1839: ldc_w 'gia_xn'
        //   1842: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1845: dstore #4
        //   1847: dload #4
        //   1849: dstore #15
        //   1851: dload #4
        //   1853: dstore #15
        //   1855: aload #13
        //   1857: getfield caidat_gia : Lorg/json/JSONObject;
        //   1860: ldc_w 'an_xn'
        //   1863: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1866: dstore #29
        //   1868: dload #4
        //   1870: dstore #15
        //   1872: dload #29
        //   1874: dstore #4
        //   1876: goto -> 1938
        //   1879: aload #9
        //   1881: ldc_w 'bc'
        //   1884: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1887: istore_1
        //   1888: iload_1
        //   1889: iconst_m1
        //   1890: if_icmple -> 1938
        //   1893: aload #13
        //   1895: getfield caidat_gia : Lorg/json/JSONObject;
        //   1898: ldc_w 'gia_bc'
        //   1901: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1904: dstore #4
        //   1906: dload #4
        //   1908: dstore #15
        //   1910: dload #4
        //   1912: dstore #15
        //   1914: aload #13
        //   1916: getfield caidat_gia : Lorg/json/JSONObject;
        //   1919: ldc_w 'an_bc'
        //   1922: invokevirtual getDouble : (Ljava/lang/String;)D
        //   1925: dstore #29
        //   1927: dload #4
        //   1929: dstore #15
        //   1931: dload #29
        //   1933: dstore #4
        //   1935: goto -> 1938
        //   1938: aload #22
        //   1940: invokestatic parseInt : (Ljava/lang/String;)I
        //   1943: istore_1
        //   1944: dload #15
        //   1946: dstore #29
        //   1948: iload_1
        //   1949: i2d
        //   1950: dstore #31
        //   1952: aload #9
        //   1954: ldc 'deb'
        //   1956: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1959: istore_1
        //   1960: iload_1
        //   1961: iconst_m1
        //   1962: if_icmple -> 2044
        //   1965: aload #13
        //   1967: getfield caidat_tg : Lorg/json/JSONObject;
        //   1970: ldc_w 'heso_de'
        //   1973: invokevirtual getInt : (Ljava/lang/String;)I
        //   1976: istore_1
        //   1977: iload_1
        //   1978: iconst_2
        //   1979: if_icmpne -> 2044
        //   1982: dload #31
        //   1984: invokestatic isNaN : (D)Z
        //   1987: pop
        //   1988: ldc2_w 0.875
        //   1991: dload #31
        //   1993: dmul
        //   1994: d2i
        //   1995: i2d
        //   1996: dstore #33
        //   1998: goto -> 2097
        //   2001: astore #9
        //   2003: aload #6
        //   2005: astore #7
        //   2007: aload #9
        //   2009: astore #6
        //   2011: goto -> 4481
        //   2014: astore #9
        //   2016: aload #14
        //   2018: astore #22
        //   2020: aload #6
        //   2022: astore_2
        //   2023: aload #7
        //   2025: astore #14
        //   2027: aload #22
        //   2029: astore #6
        //   2031: aload_2
        //   2032: astore #7
        //   2034: dload #29
        //   2036: dstore #15
        //   2038: aload #18
        //   2040: astore_2
        //   2041: goto -> 4422
        //   2044: aload #9
        //   2046: ldc 'det'
        //   2048: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2051: istore_1
        //   2052: iload_1
        //   2053: iconst_m1
        //   2054: if_icmple -> 2093
        //   2057: aload #13
        //   2059: getfield caidat_tg : Lorg/json/JSONObject;
        //   2062: ldc_w 'heso_de'
        //   2065: invokevirtual getInt : (Ljava/lang/String;)I
        //   2068: istore_1
        //   2069: iload_1
        //   2070: iconst_1
        //   2071: if_icmpne -> 2093
        //   2074: dload #31
        //   2076: invokestatic isNaN : (D)Z
        //   2079: pop
        //   2080: ldc2_w 1.143
        //   2083: dload #31
        //   2085: dmul
        //   2086: d2i
        //   2087: i2d
        //   2088: dstore #33
        //   2090: goto -> 2097
        //   2093: dload #31
        //   2095: dstore #33
        //   2097: aload #7
        //   2099: astore_3
        //   2100: aload_3
        //   2101: iconst_3
        //   2102: invokeinterface getInt : (I)I
        //   2107: istore_1
        //   2108: iload_1
        //   2109: iconst_1
        //   2110: if_icmpne -> 2151
        //   2113: dload #33
        //   2115: dload #24
        //   2117: dmul
        //   2118: ldc2_w 100.0
        //   2121: ddiv
        //   2122: d2i
        //   2123: istore_1
        //   2124: dload #33
        //   2126: dload #26
        //   2128: dmul
        //   2129: ldc2_w 100.0
        //   2132: ddiv
        //   2133: d2i
        //   2134: istore #35
        //   2136: dload #26
        //   2138: dstore #15
        //   2140: dload #24
        //   2142: dstore #26
        //   2144: dload #15
        //   2146: dstore #24
        //   2148: goto -> 2162
        //   2151: dconst_0
        //   2152: dstore #24
        //   2154: dconst_0
        //   2155: dstore #26
        //   2157: iconst_0
        //   2158: istore #35
        //   2160: iconst_0
        //   2161: istore_1
        //   2162: dload #4
        //   2164: dstore #15
        //   2166: iload_1
        //   2167: i2d
        //   2168: dstore #36
        //   2170: dload #36
        //   2172: invokestatic isNaN : (D)Z
        //   2175: pop
        //   2176: dload #33
        //   2178: dstore #38
        //   2180: iload #35
        //   2182: i2d
        //   2183: dstore #4
        //   2185: dload #4
        //   2187: invokestatic isNaN : (D)Z
        //   2190: pop
        //   2191: dload #33
        //   2193: dload #36
        //   2195: dsub
        //   2196: dload #4
        //   2198: dsub
        //   2199: d2i
        //   2200: istore #40
        //   2202: aload #9
        //   2204: astore #13
        //   2206: aload_2
        //   2207: astore #13
        //   2209: dload #15
        //   2211: dstore #4
        //   2213: aload #14
        //   2215: astore #13
        //   2217: aload #6
        //   2219: astore #13
        //   2221: aload #9
        //   2223: astore #21
        //   2225: aload_2
        //   2226: astore #21
        //   2228: dload #29
        //   2230: dstore #4
        //   2232: dload #15
        //   2234: dstore #33
        //   2236: aload #14
        //   2238: astore #21
        //   2240: aload #6
        //   2242: astore #20
        //   2244: ldc_w 'dea,deb,dec,ded,det,lo,loa,bc,bca'
        //   2247: aload #9
        //   2249: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2252: istore #41
        //   2254: ldc_w ','
        //   2257: astore #42
        //   2259: iload #41
        //   2261: iconst_m1
        //   2262: if_icmple -> 2351
        //   2265: aload #9
        //   2267: astore #13
        //   2269: aload_2
        //   2270: astore #13
        //   2272: dload #15
        //   2274: dstore #4
        //   2276: aload #14
        //   2278: astore #13
        //   2280: aload #6
        //   2282: astore #13
        //   2284: aload #9
        //   2286: astore #21
        //   2288: aload_2
        //   2289: astore #21
        //   2291: dload #29
        //   2293: dstore #4
        //   2295: dload #15
        //   2297: dstore #33
        //   2299: aload #14
        //   2301: astore #20
        //   2303: aload #6
        //   2305: astore #21
        //   2307: aload_2
        //   2308: ldc_w ','
        //   2311: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   2314: astore #43
        //   2316: goto -> 2401
        //   2319: astore #6
        //   2321: aload #13
        //   2323: astore #7
        //   2325: goto -> 4481
        //   2328: astore #9
        //   2330: aload #20
        //   2332: astore #6
        //   2334: aload #21
        //   2336: astore #7
        //   2338: aload_3
        //   2339: astore #14
        //   2341: dload #4
        //   2343: dstore #15
        //   2345: aload #18
        //   2347: astore_2
        //   2348: goto -> 4422
        //   2351: aload #9
        //   2353: astore #13
        //   2355: aload_2
        //   2356: astore #13
        //   2358: dload #15
        //   2360: dstore #4
        //   2362: aload #14
        //   2364: astore #13
        //   2366: aload #6
        //   2368: astore #13
        //   2370: aload #9
        //   2372: astore #21
        //   2374: aload_2
        //   2375: astore #21
        //   2377: dload #29
        //   2379: dstore #4
        //   2381: dload #15
        //   2383: dstore #33
        //   2385: aload #14
        //   2387: astore #21
        //   2389: aload #6
        //   2391: astore #20
        //   2393: aload_2
        //   2394: ldc ' '
        //   2396: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   2399: astore #43
        //   2401: aload #9
        //   2403: astore #13
        //   2405: aload_2
        //   2406: astore #13
        //   2408: dload #15
        //   2410: dstore #4
        //   2412: aload #14
        //   2414: astore #13
        //   2416: aload #6
        //   2418: astore #13
        //   2420: aload #9
        //   2422: astore #21
        //   2424: aload_2
        //   2425: astore #21
        //   2427: dload #29
        //   2429: dstore #4
        //   2431: dload #15
        //   2433: dstore #33
        //   2435: aload #14
        //   2437: astore #21
        //   2439: aload #6
        //   2441: astore #20
        //   2443: aload #43
        //   2445: arraylength
        //   2446: istore #44
        //   2448: iconst_0
        //   2449: istore #41
        //   2451: dload #24
        //   2453: dstore #36
        //   2455: dload #26
        //   2457: dstore #45
        //   2459: aload_2
        //   2460: astore #7
        //   2462: iload #35
        //   2464: istore #47
        //   2466: iload #40
        //   2468: istore #35
        //   2470: aload_0
        //   2471: astore_2
        //   2472: iload #41
        //   2474: iload #44
        //   2476: if_icmpge -> 3838
        //   2479: aload #9
        //   2481: astore #13
        //   2483: aload #7
        //   2485: astore #13
        //   2487: dload #15
        //   2489: dstore #4
        //   2491: aload #14
        //   2493: astore #13
        //   2495: aload #6
        //   2497: astore #13
        //   2499: aload #9
        //   2501: astore #21
        //   2503: aload #7
        //   2505: astore #21
        //   2507: dload #29
        //   2509: dstore #4
        //   2511: dload #15
        //   2513: dstore #33
        //   2515: aload #14
        //   2517: astore #21
        //   2519: aload #6
        //   2521: astore #20
        //   2523: aload #43
        //   2525: iload #41
        //   2527: aaload
        //   2528: invokevirtual trim : ()Ljava/lang/String;
        //   2531: astore #48
        //   2533: aload #9
        //   2535: astore #13
        //   2537: aload #7
        //   2539: astore #13
        //   2541: dload #15
        //   2543: dstore #4
        //   2545: aload #14
        //   2547: astore #13
        //   2549: aload #6
        //   2551: astore #13
        //   2553: aload #9
        //   2555: astore #21
        //   2557: aload #7
        //   2559: astore #21
        //   2561: dload #29
        //   2563: dstore #4
        //   2565: dload #15
        //   2567: dstore #33
        //   2569: aload #14
        //   2571: astore #21
        //   2573: aload #6
        //   2575: astore #20
        //   2577: aload #48
        //   2579: aload #42
        //   2581: invokevirtual endsWith : (Ljava/lang/String;)Z
        //   2584: istore #49
        //   2586: iload #49
        //   2588: ifeq -> 2653
        //   2591: aload #9
        //   2593: astore #13
        //   2595: aload #7
        //   2597: astore #13
        //   2599: dload #15
        //   2601: dstore #4
        //   2603: aload #14
        //   2605: astore #13
        //   2607: aload #6
        //   2609: astore #13
        //   2611: aload #9
        //   2613: astore #21
        //   2615: aload #7
        //   2617: astore #21
        //   2619: dload #29
        //   2621: dstore #4
        //   2623: dload #15
        //   2625: dstore #33
        //   2627: aload #14
        //   2629: astore #20
        //   2631: aload #6
        //   2633: astore #21
        //   2635: aload #48
        //   2637: iconst_0
        //   2638: aload #48
        //   2640: invokevirtual length : ()I
        //   2643: iconst_1
        //   2644: isub
        //   2645: invokevirtual substring : (II)Ljava/lang/String;
        //   2648: astore #48
        //   2650: goto -> 2653
        //   2653: aload #9
        //   2655: astore #13
        //   2657: aload #7
        //   2659: astore #13
        //   2661: dload #15
        //   2663: dstore #4
        //   2665: aload #14
        //   2667: astore #13
        //   2669: aload #6
        //   2671: astore #13
        //   2673: aload #9
        //   2675: astore #21
        //   2677: aload #7
        //   2679: astore #21
        //   2681: dload #29
        //   2683: dstore #4
        //   2685: dload #15
        //   2687: dstore #33
        //   2689: aload #14
        //   2691: astore #21
        //   2693: aload #6
        //   2695: astore #20
        //   2697: aload #9
        //   2699: aload #23
        //   2701: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2704: istore #40
        //   2706: iload #40
        //   2708: iconst_m1
        //   2709: if_icmple -> 2879
        //   2712: aload #9
        //   2714: astore #13
        //   2716: aload #7
        //   2718: astore #13
        //   2720: dload #15
        //   2722: dstore #4
        //   2724: aload #14
        //   2726: astore #13
        //   2728: aload #6
        //   2730: astore #13
        //   2732: aload #9
        //   2734: astore #21
        //   2736: aload #7
        //   2738: astore #21
        //   2740: dload #29
        //   2742: dstore #4
        //   2744: dload #15
        //   2746: dstore #33
        //   2748: aload #14
        //   2750: astore #20
        //   2752: aload #6
        //   2754: astore #21
        //   2756: aload #48
        //   2758: invokevirtual length : ()I
        //   2761: iconst_5
        //   2762: if_icmpne -> 2879
        //   2765: aload #9
        //   2767: astore #13
        //   2769: aload #7
        //   2771: astore #13
        //   2773: dload #15
        //   2775: dstore #4
        //   2777: aload #14
        //   2779: astore #13
        //   2781: aload #6
        //   2783: astore #13
        //   2785: aload #9
        //   2787: astore #21
        //   2789: aload #7
        //   2791: astore #21
        //   2793: dload #29
        //   2795: dstore #4
        //   2797: dload #15
        //   2799: dstore #33
        //   2801: aload #14
        //   2803: astore #20
        //   2805: aload #6
        //   2807: astore #21
        //   2809: aload_2
        //   2810: getfield caidat_gia : Lorg/json/JSONObject;
        //   2813: aload #28
        //   2815: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2818: dstore #24
        //   2820: aload #9
        //   2822: astore #13
        //   2824: aload #7
        //   2826: astore #13
        //   2828: dload #15
        //   2830: dstore #4
        //   2832: aload #14
        //   2834: astore #13
        //   2836: aload #6
        //   2838: astore #13
        //   2840: aload #9
        //   2842: astore #21
        //   2844: aload #7
        //   2846: astore #21
        //   2848: dload #24
        //   2850: dstore #4
        //   2852: dload #15
        //   2854: dstore #33
        //   2856: aload #14
        //   2858: astore #20
        //   2860: aload #6
        //   2862: astore #21
        //   2864: aload_2
        //   2865: getfield caidat_gia : Lorg/json/JSONObject;
        //   2868: ldc_w 'an_x2'
        //   2871: invokevirtual getDouble : (Ljava/lang/String;)D
        //   2874: dstore #26
        //   2876: goto -> 3348
        //   2879: aload #9
        //   2881: astore #13
        //   2883: aload #7
        //   2885: astore #13
        //   2887: dload #15
        //   2889: dstore #4
        //   2891: aload #14
        //   2893: astore #13
        //   2895: aload #6
        //   2897: astore #13
        //   2899: aload #9
        //   2901: astore #21
        //   2903: aload #7
        //   2905: astore #21
        //   2907: dload #29
        //   2909: dstore #4
        //   2911: dload #15
        //   2913: dstore #33
        //   2915: aload #14
        //   2917: astore #21
        //   2919: aload #6
        //   2921: astore #20
        //   2923: aload #9
        //   2925: aload #23
        //   2927: invokevirtual indexOf : (Ljava/lang/String;)I
        //   2930: istore #40
        //   2932: iload #40
        //   2934: iconst_m1
        //   2935: if_icmple -> 3107
        //   2938: aload #9
        //   2940: astore #13
        //   2942: aload #7
        //   2944: astore #13
        //   2946: dload #15
        //   2948: dstore #4
        //   2950: aload #14
        //   2952: astore #13
        //   2954: aload #6
        //   2956: astore #13
        //   2958: aload #9
        //   2960: astore #21
        //   2962: aload #7
        //   2964: astore #21
        //   2966: dload #29
        //   2968: dstore #4
        //   2970: dload #15
        //   2972: dstore #33
        //   2974: aload #14
        //   2976: astore #20
        //   2978: aload #6
        //   2980: astore #21
        //   2982: aload #48
        //   2984: invokevirtual length : ()I
        //   2987: bipush #8
        //   2989: if_icmpne -> 3107
        //   2992: aload #9
        //   2994: astore #13
        //   2996: aload #7
        //   2998: astore #13
        //   3000: dload #15
        //   3002: dstore #4
        //   3004: aload #14
        //   3006: astore #13
        //   3008: aload #6
        //   3010: astore #13
        //   3012: aload #9
        //   3014: astore #21
        //   3016: aload #7
        //   3018: astore #21
        //   3020: dload #29
        //   3022: dstore #4
        //   3024: dload #15
        //   3026: dstore #33
        //   3028: aload #14
        //   3030: astore #20
        //   3032: aload #6
        //   3034: astore #21
        //   3036: aload_2
        //   3037: getfield caidat_gia : Lorg/json/JSONObject;
        //   3040: ldc_w 'gia_x3'
        //   3043: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3046: dstore #24
        //   3048: aload #9
        //   3050: astore #13
        //   3052: aload #7
        //   3054: astore #13
        //   3056: dload #15
        //   3058: dstore #4
        //   3060: aload #14
        //   3062: astore #13
        //   3064: aload #6
        //   3066: astore #13
        //   3068: aload #9
        //   3070: astore #21
        //   3072: aload #7
        //   3074: astore #21
        //   3076: dload #24
        //   3078: dstore #4
        //   3080: dload #15
        //   3082: dstore #33
        //   3084: aload #14
        //   3086: astore #20
        //   3088: aload #6
        //   3090: astore #21
        //   3092: aload_2
        //   3093: getfield caidat_gia : Lorg/json/JSONObject;
        //   3096: ldc_w 'an_x3'
        //   3099: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3102: dstore #26
        //   3104: goto -> 3348
        //   3107: aload #9
        //   3109: astore #13
        //   3111: aload #7
        //   3113: astore #13
        //   3115: dload #15
        //   3117: dstore #4
        //   3119: aload #14
        //   3121: astore #13
        //   3123: aload #6
        //   3125: astore #13
        //   3127: aload #9
        //   3129: astore #21
        //   3131: aload #7
        //   3133: astore #21
        //   3135: dload #29
        //   3137: dstore #4
        //   3139: dload #15
        //   3141: dstore #33
        //   3143: aload #14
        //   3145: astore #21
        //   3147: aload #6
        //   3149: astore #20
        //   3151: aload #9
        //   3153: aload #23
        //   3155: invokevirtual indexOf : (Ljava/lang/String;)I
        //   3158: istore #40
        //   3160: dload #29
        //   3162: dstore #24
        //   3164: dload #15
        //   3166: dstore #26
        //   3168: iload #40
        //   3170: iconst_m1
        //   3171: if_icmple -> 3348
        //   3174: aload #9
        //   3176: astore #13
        //   3178: aload #7
        //   3180: astore #13
        //   3182: dload #15
        //   3184: dstore #4
        //   3186: aload #14
        //   3188: astore #13
        //   3190: aload #6
        //   3192: astore #13
        //   3194: aload #9
        //   3196: astore #21
        //   3198: aload #7
        //   3200: astore #21
        //   3202: dload #29
        //   3204: dstore #4
        //   3206: dload #15
        //   3208: dstore #33
        //   3210: aload #14
        //   3212: astore #20
        //   3214: aload #6
        //   3216: astore #21
        //   3218: dload #29
        //   3220: dstore #24
        //   3222: dload #15
        //   3224: dstore #26
        //   3226: aload #48
        //   3228: invokevirtual length : ()I
        //   3231: bipush #11
        //   3233: if_icmpne -> 3348
        //   3236: aload #9
        //   3238: astore #13
        //   3240: aload #7
        //   3242: astore #13
        //   3244: dload #15
        //   3246: dstore #4
        //   3248: aload #14
        //   3250: astore #13
        //   3252: aload #6
        //   3254: astore #13
        //   3256: aload #9
        //   3258: astore #21
        //   3260: aload #7
        //   3262: astore #21
        //   3264: dload #29
        //   3266: dstore #4
        //   3268: dload #15
        //   3270: dstore #33
        //   3272: aload #14
        //   3274: astore #20
        //   3276: aload #6
        //   3278: astore #21
        //   3280: aload_2
        //   3281: getfield caidat_gia : Lorg/json/JSONObject;
        //   3284: ldc_w 'gia_x4'
        //   3287: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3290: dstore #24
        //   3292: aload #9
        //   3294: astore #13
        //   3296: aload #7
        //   3298: astore #13
        //   3300: dload #15
        //   3302: dstore #4
        //   3304: aload #14
        //   3306: astore #13
        //   3308: aload #6
        //   3310: astore #13
        //   3312: aload #9
        //   3314: astore #21
        //   3316: aload #7
        //   3318: astore #21
        //   3320: dload #24
        //   3322: dstore #4
        //   3324: dload #15
        //   3326: dstore #33
        //   3328: aload #14
        //   3330: astore #20
        //   3332: aload #6
        //   3334: astore #21
        //   3336: aload_2
        //   3337: getfield caidat_gia : Lorg/json/JSONObject;
        //   3340: ldc_w 'an_x4'
        //   3343: invokevirtual getDouble : (Ljava/lang/String;)D
        //   3346: dstore #26
        //   3348: dload #31
        //   3350: invokestatic isNaN : (D)Z
        //   3353: pop
        //   3354: aload #9
        //   3356: astore #13
        //   3358: aload #7
        //   3360: astore #13
        //   3362: dload #26
        //   3364: dstore #4
        //   3366: aload #14
        //   3368: astore #13
        //   3370: aload #6
        //   3372: astore #13
        //   3374: aload #9
        //   3376: astore #21
        //   3378: aload #7
        //   3380: astore #21
        //   3382: dload #24
        //   3384: dstore #4
        //   3386: dload #26
        //   3388: dstore #33
        //   3390: aload #14
        //   3392: astore #21
        //   3394: aload #6
        //   3396: astore #20
        //   3398: aload #6
        //   3400: invokevirtual prepareForInsert : ()V
        //   3403: aload #6
        //   3405: astore_2
        //   3406: aload_2
        //   3407: aload_2
        //   3408: ldc_w 'ID'
        //   3411: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3414: aconst_null
        //   3415: checkcast [B
        //   3418: invokevirtual bind : (I[B)V
        //   3421: aload_2
        //   3422: ldc_w 'ngay_nhan'
        //   3425: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3428: istore #40
        //   3430: aload_2
        //   3431: iload #40
        //   3433: aload #12
        //   3435: invokevirtual bind : (ILjava/lang/String;)V
        //   3438: aload_2
        //   3439: ldc_w 'type_kh'
        //   3442: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3445: istore #40
        //   3447: aload_2
        //   3448: iload #40
        //   3450: aload_3
        //   3451: iconst_3
        //   3452: invokeinterface getInt : (I)I
        //   3457: invokevirtual bind : (II)V
        //   3460: aload_2
        //   3461: ldc_w 'ten_kh'
        //   3464: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3467: istore #40
        //   3469: aload_2
        //   3470: iload #40
        //   3472: aload #14
        //   3474: iconst_0
        //   3475: invokeinterface getString : (I)Ljava/lang/String;
        //   3480: invokevirtual bind : (ILjava/lang/String;)V
        //   3483: aload_2
        //   3484: aload_2
        //   3485: ldc_w 'so_dienthoai'
        //   3488: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3491: aload_3
        //   3492: iconst_5
        //   3493: invokeinterface getString : (I)Ljava/lang/String;
        //   3498: invokevirtual bind : (ILjava/lang/String;)V
        //   3501: aload_2
        //   3502: aload_2
        //   3503: ldc_w 'so_tin_nhan'
        //   3506: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3509: aload_3
        //   3510: bipush #7
        //   3512: invokeinterface getInt : (I)I
        //   3517: invokevirtual bind : (II)V
        //   3520: aload_2
        //   3521: aload_2
        //   3522: aload #11
        //   3524: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3527: aload #9
        //   3529: invokevirtual bind : (ILjava/lang/String;)V
        //   3532: aload_2
        //   3533: aload_2
        //   3534: ldc_w 'so_chon'
        //   3537: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3540: aload #48
        //   3542: invokevirtual bind : (ILjava/lang/String;)V
        //   3545: aload_2
        //   3546: aload_2
        //   3547: ldc_w 'diem'
        //   3550: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3553: dload #31
        //   3555: invokevirtual bind : (ID)V
        //   3558: aload_2
        //   3559: aload_2
        //   3560: ldc_w 'diem_quydoi'
        //   3563: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3566: dload #38
        //   3568: invokevirtual bind : (ID)V
        //   3571: aload_2
        //   3572: ldc_w 'diem_khachgiu'
        //   3575: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3578: istore #40
        //   3580: aload_2
        //   3581: iload #40
        //   3583: dload #45
        //   3585: invokevirtual bind : (ID)V
        //   3588: aload_2
        //   3589: ldc_w 'diem_dly_giu'
        //   3592: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3595: istore #40
        //   3597: aload_2
        //   3598: iload #40
        //   3600: dload #36
        //   3602: invokevirtual bind : (ID)V
        //   3605: aload_2
        //   3606: aload_2
        //   3607: ldc_w 'diem_ton'
        //   3610: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3613: iload #35
        //   3615: invokevirtual bind : (II)V
        //   3618: aload_2
        //   3619: aload_2
        //   3620: ldc_w 'gia'
        //   3623: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3626: dload #24
        //   3628: ldc2_w 1000.0
        //   3631: dmul
        //   3632: invokevirtual bind : (ID)V
        //   3635: aload_2
        //   3636: aload_2
        //   3637: ldc_w 'lan_an'
        //   3640: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3643: dload #26
        //   3645: ldc2_w 1000.0
        //   3648: dmul
        //   3649: invokevirtual bind : (ID)V
        //   3652: aload_2
        //   3653: aload_2
        //   3654: ldc_w 'so_nhay'
        //   3657: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3660: iconst_0
        //   3661: invokevirtual bind : (II)V
        //   3664: aload_2
        //   3665: aload_2
        //   3666: ldc_w 'tong_tien'
        //   3669: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3672: dload #31
        //   3674: dload #24
        //   3676: dmul
        //   3677: ldc2_w 1000.0
        //   3680: dmul
        //   3681: invokevirtual bind : (ID)V
        //   3684: aload_2
        //   3685: aload_2
        //   3686: ldc_w 'ket_qua'
        //   3689: invokevirtual getColumnIndex : (Ljava/lang/String;)I
        //   3692: iconst_0
        //   3693: invokevirtual bind : (II)V
        //   3696: aload_2
        //   3697: invokevirtual execute : ()J
        //   3700: pop2
        //   3701: iinc #41, 1
        //   3704: aload_2
        //   3705: astore #6
        //   3707: dload #24
        //   3709: dstore #29
        //   3711: dload #26
        //   3713: dstore #15
        //   3715: goto -> 2470
        //   3718: astore #6
        //   3720: aload_2
        //   3721: astore #7
        //   3723: goto -> 4481
        //   3726: astore #9
        //   3728: aload #14
        //   3730: astore #6
        //   3732: aload_2
        //   3733: astore #7
        //   3735: aload_3
        //   3736: astore #14
        //   3738: dload #24
        //   3740: dstore #15
        //   3742: aload #18
        //   3744: astore_2
        //   3745: goto -> 4422
        //   3748: astore #6
        //   3750: aload_2
        //   3751: astore #7
        //   3753: goto -> 4481
        //   3756: astore #9
        //   3758: aload #14
        //   3760: astore #6
        //   3762: aload_2
        //   3763: astore #7
        //   3765: aload_3
        //   3766: astore #14
        //   3768: dload #24
        //   3770: dstore #15
        //   3772: aload #18
        //   3774: astore_2
        //   3775: goto -> 4422
        //   3778: astore #6
        //   3780: aload_2
        //   3781: astore #7
        //   3783: goto -> 4481
        //   3786: astore #9
        //   3788: aload #14
        //   3790: astore #6
        //   3792: aload_2
        //   3793: astore #7
        //   3795: aload_3
        //   3796: astore #14
        //   3798: dload #24
        //   3800: dstore #15
        //   3802: aload #18
        //   3804: astore_2
        //   3805: goto -> 4422
        //   3808: astore #6
        //   3810: aload_2
        //   3811: astore #7
        //   3813: goto -> 4481
        //   3816: astore #9
        //   3818: aload #14
        //   3820: astore #6
        //   3822: aload_2
        //   3823: astore #7
        //   3825: aload_3
        //   3826: astore #14
        //   3828: dload #24
        //   3830: dstore #15
        //   3832: aload #18
        //   3834: astore_2
        //   3835: goto -> 4422
        //   3838: aload #6
        //   3840: astore_2
        //   3841: aload #9
        //   3843: astore #13
        //   3845: aload_3
        //   3846: astore #9
        //   3848: aload #17
        //   3850: astore #6
        //   3852: aload #7
        //   3854: astore #17
        //   3856: aload #11
        //   3858: astore_3
        //   3859: dload #15
        //   3861: dstore #4
        //   3863: aload #9
        //   3865: astore #7
        //   3867: aload #18
        //   3869: astore #9
        //   3871: aload #13
        //   3873: astore #11
        //   3875: aload #17
        //   3877: astore #13
        //   3879: aload #22
        //   3881: astore #17
        //   3883: dload #29
        //   3885: dstore #15
        //   3887: goto -> 406
        //   3890: astore #6
        //   3892: aload #13
        //   3894: astore #7
        //   3896: goto -> 4481
        //   3899: astore #9
        //   3901: aload #20
        //   3903: astore #7
        //   3905: aload #21
        //   3907: astore #6
        //   3909: aload_3
        //   3910: astore #14
        //   3912: dload #4
        //   3914: dstore #15
        //   3916: aload #18
        //   3918: astore_2
        //   3919: goto -> 4422
        //   3922: astore #9
        //   3924: aload #6
        //   3926: astore #7
        //   3928: aload #9
        //   3930: astore #6
        //   3932: goto -> 4481
        //   3935: astore #9
        //   3937: aload #6
        //   3939: astore #7
        //   3941: aload #14
        //   3943: astore #6
        //   3945: aload_3
        //   3946: astore #14
        //   3948: dload #29
        //   3950: dstore #15
        //   3952: aload #18
        //   3954: astore_2
        //   3955: goto -> 4422
        //   3958: astore #9
        //   3960: aload #6
        //   3962: astore #7
        //   3964: aload #9
        //   3966: astore #6
        //   3968: goto -> 4481
        //   3971: astore #9
        //   3973: aload #6
        //   3975: astore #22
        //   3977: aload #7
        //   3979: astore_2
        //   3980: aload #14
        //   3982: astore #6
        //   3984: aload #22
        //   3986: astore #7
        //   3988: aload_2
        //   3989: astore #14
        //   3991: dload #29
        //   3993: dstore #15
        //   3995: aload #18
        //   3997: astore_2
        //   3998: goto -> 4422
        //   4001: astore #9
        //   4003: aload #6
        //   4005: astore #7
        //   4007: aload #9
        //   4009: astore #6
        //   4011: goto -> 4481
        //   4014: astore #9
        //   4016: aload #6
        //   4018: astore_2
        //   4019: aload #7
        //   4021: astore #22
        //   4023: aload #14
        //   4025: astore #6
        //   4027: aload_2
        //   4028: astore #7
        //   4030: aload #22
        //   4032: astore #14
        //   4034: aload #18
        //   4036: astore_2
        //   4037: goto -> 4422
        //   4040: astore #9
        //   4042: aload #6
        //   4044: astore #7
        //   4046: aload #9
        //   4048: astore #6
        //   4050: goto -> 4481
        //   4053: astore #9
        //   4055: aload #6
        //   4057: astore #22
        //   4059: aload #7
        //   4061: astore_2
        //   4062: aload #14
        //   4064: astore #6
        //   4066: aload #22
        //   4068: astore #7
        //   4070: aload_2
        //   4071: astore #14
        //   4073: aload #18
        //   4075: astore_2
        //   4076: goto -> 4422
        //   4079: astore #9
        //   4081: aload #6
        //   4083: astore #7
        //   4085: aload #9
        //   4087: astore #6
        //   4089: goto -> 4481
        //   4092: astore #9
        //   4094: aload #6
        //   4096: astore_2
        //   4097: aload #7
        //   4099: astore #22
        //   4101: aload #14
        //   4103: astore #6
        //   4105: aload_2
        //   4106: astore #7
        //   4108: aload #22
        //   4110: astore #14
        //   4112: aload #18
        //   4114: astore_2
        //   4115: goto -> 4422
        //   4118: astore #9
        //   4120: aload #6
        //   4122: astore #7
        //   4124: aload #9
        //   4126: astore #6
        //   4128: goto -> 4481
        //   4131: astore #9
        //   4133: aload #6
        //   4135: astore #22
        //   4137: aload #7
        //   4139: astore_2
        //   4140: aload #14
        //   4142: astore #6
        //   4144: aload #22
        //   4146: astore #7
        //   4148: aload_2
        //   4149: astore #14
        //   4151: aload #18
        //   4153: astore_2
        //   4154: goto -> 4422
        //   4157: astore #9
        //   4159: aload #6
        //   4161: astore #17
        //   4163: aload #9
        //   4165: astore #6
        //   4167: aload_2
        //   4168: astore #7
        //   4170: goto -> 4481
        //   4173: astore_3
        //   4174: aload #6
        //   4176: astore #17
        //   4178: aload #9
        //   4180: astore #18
        //   4182: aload #7
        //   4184: astore #22
        //   4186: aload_3
        //   4187: astore #9
        //   4189: aload #14
        //   4191: astore #6
        //   4193: aload_2
        //   4194: astore #7
        //   4196: aload #22
        //   4198: astore #14
        //   4200: aload #18
        //   4202: astore_2
        //   4203: goto -> 4422
        //   4206: astore #9
        //   4208: aload #6
        //   4210: astore #17
        //   4212: aload #9
        //   4214: astore #6
        //   4216: aload_2
        //   4217: astore #7
        //   4219: goto -> 4481
        //   4222: astore_3
        //   4223: aload #6
        //   4225: astore #17
        //   4227: aload #9
        //   4229: astore #18
        //   4231: aload #7
        //   4233: astore #22
        //   4235: aload_3
        //   4236: astore #9
        //   4238: aload #14
        //   4240: astore #6
        //   4242: aload_2
        //   4243: astore #7
        //   4245: aload #22
        //   4247: astore #14
        //   4249: aload #18
        //   4251: astore_2
        //   4252: goto -> 4422
        //   4255: aload #6
        //   4257: astore #17
        //   4259: aload #9
        //   4261: astore #18
        //   4263: aload #7
        //   4265: astore #22
        //   4267: aload #17
        //   4269: invokevirtual setTransactionSuccessful : ()V
        //   4272: aload #17
        //   4274: invokevirtual endTransaction : ()V
        //   4277: aload_2
        //   4278: invokevirtual close : ()V
        //   4281: aload #17
        //   4283: invokevirtual close : ()V
        //   4286: aload #14
        //   4288: astore #6
        //   4290: aload #22
        //   4292: astore #14
        //   4294: aload #18
        //   4296: astore_2
        //   4297: goto -> 4442
        //   4300: astore #6
        //   4302: aload_2
        //   4303: astore #7
        //   4305: goto -> 4481
        //   4308: astore #9
        //   4310: aload #14
        //   4312: astore #6
        //   4314: aload_2
        //   4315: astore #7
        //   4317: aload #22
        //   4319: astore #14
        //   4321: aload #18
        //   4323: astore_2
        //   4324: goto -> 4422
        //   4327: astore #9
        //   4329: aload #6
        //   4331: astore #17
        //   4333: aload #9
        //   4335: astore #6
        //   4337: aload_2
        //   4338: astore #7
        //   4340: goto -> 4481
        //   4343: astore_3
        //   4344: aload #6
        //   4346: astore #17
        //   4348: aload #9
        //   4350: astore #18
        //   4352: aload #7
        //   4354: astore #22
        //   4356: aload_3
        //   4357: astore #9
        //   4359: aload #14
        //   4361: astore #6
        //   4363: aload_2
        //   4364: astore #7
        //   4366: aload #22
        //   4368: astore #14
        //   4370: aload #18
        //   4372: astore_2
        //   4373: goto -> 4422
        //   4376: astore #9
        //   4378: aload #6
        //   4380: astore #17
        //   4382: aload #9
        //   4384: astore #6
        //   4386: aload #18
        //   4388: astore #7
        //   4390: goto -> 4481
        //   4393: astore #17
        //   4395: aload #14
        //   4397: astore #22
        //   4399: aload #9
        //   4401: astore_2
        //   4402: aload #17
        //   4404: astore #9
        //   4406: aload #6
        //   4408: astore #17
        //   4410: aload #7
        //   4412: astore #14
        //   4414: aload #18
        //   4416: astore #7
        //   4418: aload #22
        //   4420: astore #6
        //   4422: aload #9
        //   4424: invokevirtual printStackTrace : ()V
        //   4427: aload #17
        //   4429: invokevirtual endTransaction : ()V
        //   4432: aload #7
        //   4434: invokevirtual close : ()V
        //   4437: aload #17
        //   4439: invokevirtual close : ()V
        //   4442: aload #14
        //   4444: invokeinterface isClosed : ()Z
        //   4449: ifne -> 4459
        //   4452: aload #14
        //   4454: invokeinterface close : ()V
        //   4459: aload #6
        //   4461: invokeinterface isClosed : ()Z
        //   4466: ifne -> 4476
        //   4469: aload #6
        //   4471: invokeinterface close : ()V
        //   4476: goto -> 4502
        //   4479: astore #6
        //   4481: aload #17
        //   4483: invokevirtual endTransaction : ()V
        //   4486: aload #7
        //   4488: invokevirtual close : ()V
        //   4491: aload #17
        //   4493: invokevirtual close : ()V
        //   4496: aload #6
        //   4498: athrow
        //   4499: aload #9
        //   4501: astore_2
        //   4502: aload_2
        //   4503: invokeinterface close : ()V
        //   4508: return
        // Exception table:
        //   from	to	target	type
        //   180	205	208	org/json/JSONException
        //   297	302	355	org/json/JSONException
        //   302	347	350	org/json/JSONException
        //   389	403	4393	java/lang/Exception
        //   389	403	4376	finally
        //   409	436	4343	java/lang/Exception
        //   409	436	4327	finally
        //   436	461	4222	java/lang/Exception
        //   436	461	4206	finally
        //   465	475	4173	java/lang/Exception
        //   465	475	4157	finally
        //   483	495	4173	java/lang/Exception
        //   483	495	4157	finally
        //   523	538	4131	java/lang/Exception
        //   523	538	4118	finally
        //   545	560	4131	java/lang/Exception
        //   545	560	4118	finally
        //   567	582	4131	java/lang/Exception
        //   567	582	4118	finally
        //   589	604	4131	java/lang/Exception
        //   589	604	4118	finally
        //   611	624	4131	java/lang/Exception
        //   611	624	4118	finally
        //   676	692	4131	java/lang/Exception
        //   676	692	4118	finally
        //   700	712	4131	java/lang/Exception
        //   700	712	4118	finally
        //   724	739	4131	java/lang/Exception
        //   724	739	4118	finally
        //   746	759	4131	java/lang/Exception
        //   746	759	4118	finally
        //   772	785	4131	java/lang/Exception
        //   772	785	4118	finally
        //   790	806	650	java/lang/Exception
        //   790	806	637	finally
        //   809	821	650	java/lang/Exception
        //   809	821	637	finally
        //   845	853	4092	java/lang/Exception
        //   845	853	4079	finally
        //   858	902	1125	java/lang/Exception
        //   858	902	1112	finally
        //   905	928	1125	java/lang/Exception
        //   905	928	1112	finally
        //   932	946	1303	java/lang/Exception
        //   932	946	1290	finally
        //   949	973	1303	java/lang/Exception
        //   949	973	1290	finally
        //   976	1015	1303	java/lang/Exception
        //   976	1015	1290	finally
        //   1018	1058	1303	java/lang/Exception
        //   1018	1058	1290	finally
        //   1070	1096	1303	java/lang/Exception
        //   1070	1096	1290	finally
        //   1151	1179	4053	java/lang/Exception
        //   1151	1179	4040	finally
        //   1185	1193	4053	java/lang/Exception
        //   1185	1193	4040	finally
        //   1203	1215	1303	java/lang/Exception
        //   1203	1215	1290	finally
        //   1223	1236	1260	java/lang/Exception
        //   1223	1236	1247	finally
        //   1333	1341	4053	java/lang/Exception
        //   1333	1341	4040	finally
        //   1346	1358	1303	java/lang/Exception
        //   1346	1358	1290	finally
        //   1366	1379	1260	java/lang/Exception
        //   1366	1379	1247	finally
        //   1390	1398	4053	java/lang/Exception
        //   1390	1398	4040	finally
        //   1403	1415	1303	java/lang/Exception
        //   1403	1415	1290	finally
        //   1423	1436	1260	java/lang/Exception
        //   1423	1436	1247	finally
        //   1447	1455	4053	java/lang/Exception
        //   1447	1455	4040	finally
        //   1460	1472	1303	java/lang/Exception
        //   1460	1472	1290	finally
        //   1480	1493	1260	java/lang/Exception
        //   1480	1493	1247	finally
        //   1504	1512	4053	java/lang/Exception
        //   1504	1512	4040	finally
        //   1517	1529	1303	java/lang/Exception
        //   1517	1529	1290	finally
        //   1537	1550	1260	java/lang/Exception
        //   1537	1550	1247	finally
        //   1561	1569	4053	java/lang/Exception
        //   1561	1569	4040	finally
        //   1574	1586	1303	java/lang/Exception
        //   1574	1586	1290	finally
        //   1594	1607	1260	java/lang/Exception
        //   1594	1607	1247	finally
        //   1618	1627	4053	java/lang/Exception
        //   1618	1627	4040	finally
        //   1632	1653	1303	java/lang/Exception
        //   1632	1653	1290	finally
        //   1661	1674	1260	java/lang/Exception
        //   1661	1674	1247	finally
        //   1685	1694	4053	java/lang/Exception
        //   1685	1694	4040	finally
        //   1699	1721	1303	java/lang/Exception
        //   1699	1721	1290	finally
        //   1729	1742	1260	java/lang/Exception
        //   1729	1742	1247	finally
        //   1753	1762	4053	java/lang/Exception
        //   1753	1762	4040	finally
        //   1767	1789	1303	java/lang/Exception
        //   1767	1789	1290	finally
        //   1797	1810	1260	java/lang/Exception
        //   1797	1810	1247	finally
        //   1821	1829	4053	java/lang/Exception
        //   1821	1829	4040	finally
        //   1834	1847	1303	java/lang/Exception
        //   1834	1847	1290	finally
        //   1855	1868	1260	java/lang/Exception
        //   1855	1868	1247	finally
        //   1879	1888	4053	java/lang/Exception
        //   1879	1888	4040	finally
        //   1893	1906	1303	java/lang/Exception
        //   1893	1906	1290	finally
        //   1914	1927	1260	java/lang/Exception
        //   1914	1927	1247	finally
        //   1938	1944	4014	java/lang/Exception
        //   1938	1944	4001	finally
        //   1952	1960	3971	java/lang/Exception
        //   1952	1960	3958	finally
        //   1965	1977	2014	java/lang/Exception
        //   1965	1977	2001	finally
        //   2044	2052	3971	java/lang/Exception
        //   2044	2052	3958	finally
        //   2057	2069	2014	java/lang/Exception
        //   2057	2069	2001	finally
        //   2100	2108	3935	java/lang/Exception
        //   2100	2108	3922	finally
        //   2244	2254	3899	java/lang/Exception
        //   2244	2254	3890	finally
        //   2307	2316	2328	java/lang/Exception
        //   2307	2316	2319	finally
        //   2393	2401	3899	java/lang/Exception
        //   2393	2401	3890	finally
        //   2443	2448	3899	java/lang/Exception
        //   2443	2448	3890	finally
        //   2523	2533	3899	java/lang/Exception
        //   2523	2533	3890	finally
        //   2577	2586	3899	java/lang/Exception
        //   2577	2586	3890	finally
        //   2635	2650	2328	java/lang/Exception
        //   2635	2650	2319	finally
        //   2697	2706	3899	java/lang/Exception
        //   2697	2706	3890	finally
        //   2756	2765	2328	java/lang/Exception
        //   2756	2765	2319	finally
        //   2809	2820	2328	java/lang/Exception
        //   2809	2820	2319	finally
        //   2864	2876	2328	java/lang/Exception
        //   2864	2876	2319	finally
        //   2923	2932	3899	java/lang/Exception
        //   2923	2932	3890	finally
        //   2982	2992	2328	java/lang/Exception
        //   2982	2992	2319	finally
        //   3036	3048	2328	java/lang/Exception
        //   3036	3048	2319	finally
        //   3092	3104	2328	java/lang/Exception
        //   3092	3104	2319	finally
        //   3151	3160	3899	java/lang/Exception
        //   3151	3160	3890	finally
        //   3226	3236	2328	java/lang/Exception
        //   3226	3236	2319	finally
        //   3280	3292	2328	java/lang/Exception
        //   3280	3292	2319	finally
        //   3336	3348	2328	java/lang/Exception
        //   3336	3348	2319	finally
        //   3398	3403	3899	java/lang/Exception
        //   3398	3403	3890	finally
        //   3406	3430	3816	java/lang/Exception
        //   3406	3430	3808	finally
        //   3430	3447	3786	java/lang/Exception
        //   3430	3447	3778	finally
        //   3447	3469	3816	java/lang/Exception
        //   3447	3469	3808	finally
        //   3469	3580	3756	java/lang/Exception
        //   3469	3580	3748	finally
        //   3580	3597	3726	java/lang/Exception
        //   3580	3597	3718	finally
        //   3597	3701	3726	java/lang/Exception
        //   3597	3701	3718	finally
        //   4267	4272	4308	java/lang/Exception
        //   4267	4272	4300	finally
        //   4422	4427	4479	finally
        }

public void QueryData(String paramString) {
        getWritableDatabase().execSQL(paramString);
        }

public void Save_Setting(String paramString, int paramInt) {
        Cursor cursor = GetData("Select * From tbl_Setting WHERE ID = 1");
        if (cursor != null && cursor.moveToFirst())
        try {
        MainActivity.jSon_Setting.put(paramString, paramInt);
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Update tbl_Setting set Setting = '");
        stringBuilder.append(MainActivity.jSon_Setting.toString());
        stringBuilder.append("'");
        QueryData(stringBuilder.toString());
        } catch (JSONException jSONException) {
        jSONException.printStackTrace();
        }
        cursor.close();
        }

public void SendSMS(String paramString1, String paramString2) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendMultipartTextMessage(paramString1, null, smsManager.divideMessage(paramString2), null, null);
        }

public void ThayThePhu() {
        QueryData("CREATE TABLE IF NOT EXISTS thay_the_phu(  ID INTEGER PRIMARY KEY AUTOINCREMENT,  str VARCHAR(20) NOT NULL,  str_rpl VARCHAR(20) NOT NULL)");
        }

public String Tin_Chottien(String paramString) throws JSONException {
        Database database = this;
        String str1 = "AnNhan";
        String str2 = "KQNhan";
        new MainActivity();
        String str3 = MainActivity.Get_date();
        String str4 = MainActivity.Get_ngay();
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        Object object = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select * From tbl_kh_new Where ten_kh = '");
        stringBuilder.append(paramString);
        stringBuilder.append("'");
        Cursor cursor = database.GetData(stringBuilder.toString());
        cursor.moveToFirst();
        try {
        JSONObject jSONObject = new JSONObject();
        try {
        this(cursor.getString(5));
        database.json = jSONObject;
        database.caidat_tg = jSONObject.getJSONObject("caidat_tg");
        } catch (JSONException null) {}
        } catch (JSONException jSONException) {}
        jSONException.printStackTrace();
        }

public String Tin_Chottien_CT(String paramString) {
        // Byte code:
        //   0: new tamhoang/ldpro4/MainActivity
        //   3: dup
        //   4: invokespecial <init> : ()V
        //   7: astore_2
        //   8: invokestatic Get_date : ()Ljava/lang/String;
        //   11: astore_3
        //   12: invokestatic Get_ngay : ()Ljava/lang/String;
        //   15: astore #4
        //   17: ldc_w '###,###'
        //   20: astore #5
        //   22: new java/text/DecimalFormat
        //   25: dup
        //   26: ldc_w '###,###'
        //   29: invokespecial <init> : (Ljava/lang/String;)V
        //   32: astore #6
        //   34: ldc ''
        //   36: astore #7
        //   38: new java/lang/StringBuilder
        //   41: dup
        //   42: invokespecial <init> : ()V
        //   45: astore #8
        //   47: aload #8
        //   49: ldc_w 'Select so_tin_nhan, CASE \\nWHEN the_loai = 'xi' And length(so_chon) = 5 THEN 'xi2' \\nWHEN the_loai = 'xi' And length(so_chon) = 8 THEN 'xi3' \\nWHEN the_loai = 'xi' And length(so_chon) = 11 THEN 'xi4' \\nWHEN the_loai = 'xia' And length(so_chon) = 5 THEN 'xia2' \\nWHEN the_loai = 'xia' And length(so_chon) = 8 THEN 'xia3' \\nWHEN the_loai = 'xia' And length(so_chon) = 11 THEN 'xia4' \\nELSE the_loai END m_theloai\\n, sum(diem) as mDiem\\n, sum(diem*so_nhay) as mAn \\n, sum(ket_qua) as ThanhTien \\nFrom tbl_soctS Where ngay_nhan = ''
        //   52: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   55: pop
        //   56: aload #8
        //   58: aload_3
        //   59: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   62: pop
        //   63: aload #8
        //   65: ldc_w '' And ten_kh = ''
        //   68: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   71: pop
        //   72: aload #8
        //   74: aload_1
        //   75: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   78: pop
        //   79: aload #8
        //   81: ldc_w '' and the_loai <> 'tt' AND type_kh = 1\\nGROUP by so_tin_nhan, m_theloai ORDER by type_kh DESC, ten_kh'
        //   84: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   87: pop
        //   88: aload_0
        //   89: aload #8
        //   91: invokevirtual toString : ()Ljava/lang/String;
        //   94: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   97: astore #9
        //   99: new java/lang/StringBuilder
        //   102: dup
        //   103: invokespecial <init> : ()V
        //   106: astore #8
        //   108: aload #8
        //   110: ldc_w 'Select so_tin_nhan, CASE \\nWHEN the_loai = 'xi' And length(so_chon) = 5 THEN 'xi2' \\nWHEN the_loai = 'xi' And length(so_chon) = 8 THEN 'xi3' \\nWHEN the_loai = 'xi' And length(so_chon) = 11 THEN 'xi4' \\nWHEN the_loai = 'xia' And length(so_chon) = 5 THEN 'xia2' \\nWHEN the_loai = 'xia' And length(so_chon) = 8 THEN 'xia3' \\nWHEN the_loai = 'xia' And length(so_chon) = 11 THEN 'xia4' \\nELSE the_loai END m_theloai\\n, sum(diem) as mDiem\\n, sum(diem*so_nhay) as mAn \\n, sum(ket_qua) as ThanhTien \\nFrom tbl_soctS Where ngay_nhan = ''
        //   113: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   116: pop
        //   117: aload #8
        //   119: aload_3
        //   120: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   123: pop
        //   124: aload #8
        //   126: ldc_w '' And ten_kh = ''
        //   129: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: pop
        //   133: aload #8
        //   135: aload_1
        //   136: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   139: pop
        //   140: aload #8
        //   142: ldc_w '' and the_loai <> 'tt' AND type_kh = 2\\nGROUP by so_tin_nhan, m_theloai ORDER by type_kh DESC, ten_kh'
        //   145: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   148: pop
        //   149: aload #8
        //   151: invokevirtual toString : ()Ljava/lang/String;
        //   154: astore #10
        //   156: aload_0
        //   157: aload #10
        //   159: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   162: astore #11
        //   164: aload_2
        //   165: astore #8
        //   167: aload_3
        //   168: astore #8
        //   170: aload #7
        //   172: astore #8
        //   174: new org/json/JSONObject
        //   177: astore #12
        //   179: aload_2
        //   180: astore #8
        //   182: aload_3
        //   183: astore #8
        //   185: aload #7
        //   187: astore #8
        //   189: aload #12
        //   191: invokespecial <init> : ()V
        //   194: aload_2
        //   195: astore #8
        //   197: aload_3
        //   198: astore #8
        //   200: aload #7
        //   202: astore #8
        //   204: aload #12
        //   206: ldc 'dea'
        //   208: ldc_w 'Dau DB: '
        //   211: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   214: pop
        //   215: aload_2
        //   216: astore #8
        //   218: aload_3
        //   219: astore #8
        //   221: aload #7
        //   223: astore #8
        //   225: aload #12
        //   227: ldc 'deb'
        //   229: ldc_w 'De: '
        //   232: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   235: pop
        //   236: aload_2
        //   237: astore #8
        //   239: aload_3
        //   240: astore #8
        //   242: aload #7
        //   244: astore #8
        //   246: aload #12
        //   248: ldc 'det'
        //   250: ldc_w 'De 8: '
        //   253: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   256: pop
        //   257: aload_2
        //   258: astore #8
        //   260: aload_3
        //   261: astore #8
        //   263: aload #7
        //   265: astore #8
        //   267: aload #12
        //   269: ldc 'dec'
        //   271: ldc_w 'Dau Nhat: '
        //   274: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   277: pop
        //   278: aload_2
        //   279: astore #8
        //   281: aload_3
        //   282: astore #8
        //   284: aload #7
        //   286: astore #8
        //   288: aload #12
        //   290: ldc 'ded'
        //   292: ldc_w 'Dit Nhat: '
        //   295: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   298: pop
        //   299: aload_2
        //   300: astore #8
        //   302: aload_3
        //   303: astore #8
        //   305: aload #7
        //   307: astore #8
        //   309: aload #12
        //   311: ldc 'lo'
        //   313: ldc_w 'Lo: '
        //   316: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   319: pop
        //   320: aload_2
        //   321: astore #8
        //   323: aload_3
        //   324: astore #8
        //   326: aload #7
        //   328: astore #8
        //   330: aload #12
        //   332: ldc_w 'xi2'
        //   335: ldc_w 'Xien 2: '
        //   338: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   341: pop
        //   342: aload_2
        //   343: astore #8
        //   345: aload_3
        //   346: astore #8
        //   348: aload #7
        //   350: astore #8
        //   352: aload #12
        //   354: ldc_w 'xi3'
        //   357: ldc_w 'Xien 3: '
        //   360: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   363: pop
        //   364: aload_2
        //   365: astore #8
        //   367: aload_3
        //   368: astore #8
        //   370: aload #7
        //   372: astore #8
        //   374: aload #12
        //   376: ldc_w 'xi4'
        //   379: ldc_w 'Xien 4: '
        //   382: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   385: pop
        //   386: aload_2
        //   387: astore #8
        //   389: aload_3
        //   390: astore #8
        //   392: aload #7
        //   394: astore #8
        //   396: aload #12
        //   398: ldc 'xn'
        //   400: ldc_w 'X.nhay: '
        //   403: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   406: pop
        //   407: aload_2
        //   408: astore #8
        //   410: aload_3
        //   411: astore #8
        //   413: aload #7
        //   415: astore #8
        //   417: aload #12
        //   419: ldc_w 'bc'
        //   422: ldc_w '3Cang: '
        //   425: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   428: pop
        //   429: aload_2
        //   430: astore #8
        //   432: aload_3
        //   433: astore #8
        //   435: aload #7
        //   437: astore #8
        //   439: aload #12
        //   441: ldc 'loa'
        //   443: ldc_w 'Lo dau: '
        //   446: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   449: pop
        //   450: aload_2
        //   451: astore #8
        //   453: aload_3
        //   454: astore #8
        //   456: aload #7
        //   458: astore #8
        //   460: aload #12
        //   462: ldc_w 'xia2'
        //   465: ldc_w 'Xia 2: '
        //   468: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   471: pop
        //   472: aload_2
        //   473: astore #8
        //   475: aload_3
        //   476: astore #8
        //   478: aload #7
        //   480: astore #8
        //   482: aload #12
        //   484: ldc_w 'xia3'
        //   487: ldc_w 'Xia 3: '
        //   490: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   493: pop
        //   494: aload_2
        //   495: astore #8
        //   497: aload_3
        //   498: astore #8
        //   500: aload #7
        //   502: astore #8
        //   504: aload #12
        //   506: ldc_w 'xia4'
        //   509: ldc_w 'Xia 4: '
        //   512: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   515: pop
        //   516: aload_2
        //   517: astore #8
        //   519: aload_3
        //   520: astore #8
        //   522: aload #7
        //   524: astore #8
        //   526: aload #12
        //   528: ldc_w 'bca'
        //   531: ldc_w '3Cang dau: '
        //   534: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   537: pop
        //   538: aload_2
        //   539: astore #8
        //   541: aload_3
        //   542: astore #8
        //   544: aload #7
        //   546: astore #8
        //   548: aload #9
        //   550: invokeinterface getCount : ()I
        //   555: istore #13
        //   557: aload #7
        //   559: astore_1
        //   560: iload #13
        //   562: ifle -> 572
        //   565: ldc_w '\\nTin nhan:'
        //   568: astore_1
        //   569: goto -> 572
        //   572: iconst_0
        //   573: istore #13
        //   575: aload_2
        //   576: astore #8
        //   578: aload_3
        //   579: astore #8
        //   581: aload_1
        //   582: astore #8
        //   584: aload #9
        //   586: invokeinterface moveToNext : ()Z
        //   591: istore #14
        //   593: ldc_w '\\nTin '
        //   596: astore #15
        //   598: iload #14
        //   600: ifeq -> 971
        //   603: aload_1
        //   604: astore #8
        //   606: iload #13
        //   608: aload #9
        //   610: iconst_0
        //   611: invokeinterface getInt : (I)I
        //   616: if_icmpeq -> 836
        //   619: aload_1
        //   620: astore #8
        //   622: aload #9
        //   624: iconst_0
        //   625: invokeinterface getInt : (I)I
        //   630: istore #13
        //   632: aload_1
        //   633: astore #8
        //   635: new java/lang/StringBuilder
        //   638: astore #7
        //   640: aload_1
        //   641: astore #8
        //   643: aload #7
        //   645: invokespecial <init> : ()V
        //   648: aload_1
        //   649: astore #8
        //   651: aload #7
        //   653: aload_1
        //   654: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   657: pop
        //   658: aload_1
        //   659: astore #8
        //   661: aload #7
        //   663: ldc_w '\\nTin '
        //   666: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   669: pop
        //   670: aload_1
        //   671: astore #8
        //   673: aload #7
        //   675: aload #9
        //   677: iconst_0
        //   678: invokeinterface getString : (I)Ljava/lang/String;
        //   683: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   686: pop
        //   687: aload_1
        //   688: astore #8
        //   690: aload #7
        //   692: ldc_w ':\\n'
        //   695: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   698: pop
        //   699: aload_1
        //   700: astore #8
        //   702: aload #7
        //   704: invokevirtual toString : ()Ljava/lang/String;
        //   707: astore_1
        //   708: aload_1
        //   709: astore #8
        //   711: new java/lang/StringBuilder
        //   714: astore #7
        //   716: aload_1
        //   717: astore #8
        //   719: aload #7
        //   721: invokespecial <init> : ()V
        //   724: aload_1
        //   725: astore #8
        //   727: aload #7
        //   729: aload_1
        //   730: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   733: pop
        //   734: aload_1
        //   735: astore #8
        //   737: aload #7
        //   739: aload #12
        //   741: aload #9
        //   743: iconst_1
        //   744: invokeinterface getString : (I)Ljava/lang/String;
        //   749: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   752: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   755: pop
        //   756: aload_1
        //   757: astore #8
        //   759: aload #7
        //   761: aload #6
        //   763: aload #9
        //   765: iconst_2
        //   766: invokeinterface getDouble : (I)D
        //   771: invokevirtual format : (D)Ljava/lang/String;
        //   774: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   777: pop
        //   778: aload_1
        //   779: astore #8
        //   781: aload #7
        //   783: ldc_w '('
        //   786: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   789: pop
        //   790: aload_1
        //   791: astore #8
        //   793: aload #7
        //   795: aload #6
        //   797: aload #9
        //   799: iconst_3
        //   800: invokeinterface getDouble : (I)D
        //   805: invokevirtual format : (D)Ljava/lang/String;
        //   808: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   811: pop
        //   812: aload_1
        //   813: astore #8
        //   815: aload #7
        //   817: ldc_w ')\\n'
        //   820: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   823: pop
        //   824: aload_1
        //   825: astore #8
        //   827: aload #7
        //   829: invokevirtual toString : ()Ljava/lang/String;
        //   832: astore_1
        //   833: goto -> 575
        //   836: aload_1
        //   837: astore #8
        //   839: new java/lang/StringBuilder
        //   842: astore #7
        //   844: aload_1
        //   845: astore #8
        //   847: aload #7
        //   849: invokespecial <init> : ()V
        //   852: aload_1
        //   853: astore #8
        //   855: aload #7
        //   857: aload_1
        //   858: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   861: pop
        //   862: aload_1
        //   863: astore #8
        //   865: aload #7
        //   867: aload #12
        //   869: aload #9
        //   871: iconst_1
        //   872: invokeinterface getString : (I)Ljava/lang/String;
        //   877: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   880: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   883: pop
        //   884: aload_1
        //   885: astore #8
        //   887: aload #7
        //   889: aload #6
        //   891: aload #9
        //   893: iconst_2
        //   894: invokeinterface getDouble : (I)D
        //   899: invokevirtual format : (D)Ljava/lang/String;
        //   902: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   905: pop
        //   906: aload_1
        //   907: astore #8
        //   909: aload #7
        //   911: ldc_w '('
        //   914: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   917: pop
        //   918: aload_1
        //   919: astore #8
        //   921: aload #7
        //   923: aload #6
        //   925: aload #9
        //   927: iconst_3
        //   928: invokeinterface getDouble : (I)D
        //   933: invokevirtual format : (D)Ljava/lang/String;
        //   936: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   939: pop
        //   940: aload_1
        //   941: astore #8
        //   943: aload #7
        //   945: ldc_w ')\\n'
        //   948: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   951: pop
        //   952: aload_1
        //   953: astore #8
        //   955: aload #7
        //   957: invokevirtual toString : ()Ljava/lang/String;
        //   960: astore_1
        //   961: goto -> 575
        //   964: astore_1
        //   965: aload #8
        //   967: astore_1
        //   968: goto -> 1423
        //   971: iconst_0
        //   972: istore #16
        //   974: aload #11
        //   976: invokeinterface getCount : ()I
        //   981: istore #17
        //   983: aload #15
        //   985: astore #8
        //   987: aload #4
        //   989: astore #7
        //   991: aload #5
        //   993: astore_2
        //   994: aload_1
        //   995: astore_3
        //   996: aload #10
        //   998: astore #18
        //   1000: iload #16
        //   1002: istore #13
        //   1004: iload #17
        //   1006: ifle -> 1070
        //   1009: aload_1
        //   1010: astore #8
        //   1012: new java/lang/StringBuilder
        //   1015: astore_3
        //   1016: aload_1
        //   1017: astore #8
        //   1019: aload_3
        //   1020: invokespecial <init> : ()V
        //   1023: aload_1
        //   1024: astore #8
        //   1026: aload_3
        //   1027: aload_1
        //   1028: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1031: pop
        //   1032: aload_1
        //   1033: astore #8
        //   1035: aload_3
        //   1036: ldc_w '\\n\\nTin Chuyen:'
        //   1039: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1042: pop
        //   1043: aload_1
        //   1044: astore #8
        //   1046: aload_3
        //   1047: invokevirtual toString : ()Ljava/lang/String;
        //   1050: astore_3
        //   1051: iload #16
        //   1053: istore #13
        //   1055: aload #10
        //   1057: astore #18
        //   1059: aload #5
        //   1061: astore_2
        //   1062: aload #4
        //   1064: astore #7
        //   1066: aload #15
        //   1068: astore #8
        //   1070: aload #11
        //   1072: invokeinterface moveToNext : ()Z
        //   1077: ifeq -> 1404
        //   1080: aload #11
        //   1082: iconst_0
        //   1083: invokeinterface getInt : (I)I
        //   1088: istore #16
        //   1090: iload #13
        //   1092: iload #16
        //   1094: if_icmpeq -> 1298
        //   1097: aload_3
        //   1098: astore_1
        //   1099: aload #11
        //   1101: iconst_0
        //   1102: invokeinterface getInt : (I)I
        //   1107: istore #13
        //   1109: aload_3
        //   1110: astore_1
        //   1111: new java/lang/StringBuilder
        //   1114: astore #5
        //   1116: aload_3
        //   1117: astore_1
        //   1118: aload #5
        //   1120: invokespecial <init> : ()V
        //   1123: aload_3
        //   1124: astore_1
        //   1125: aload #5
        //   1127: aload_3
        //   1128: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1131: pop
        //   1132: aload_3
        //   1133: astore_1
        //   1134: aload #5
        //   1136: aload #8
        //   1138: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1141: pop
        //   1142: aload_3
        //   1143: astore_1
        //   1144: aload #5
        //   1146: aload #11
        //   1148: iconst_0
        //   1149: invokeinterface getString : (I)Ljava/lang/String;
        //   1154: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1157: pop
        //   1158: aload_3
        //   1159: astore_1
        //   1160: aload #5
        //   1162: ldc_w ':\\n'
        //   1165: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1168: pop
        //   1169: aload_3
        //   1170: astore_1
        //   1171: aload #5
        //   1173: invokevirtual toString : ()Ljava/lang/String;
        //   1176: astore_3
        //   1177: aload_3
        //   1178: astore_1
        //   1179: new java/lang/StringBuilder
        //   1182: astore #5
        //   1184: aload_3
        //   1185: astore_1
        //   1186: aload #5
        //   1188: invokespecial <init> : ()V
        //   1191: aload_3
        //   1192: astore_1
        //   1193: aload #5
        //   1195: aload_3
        //   1196: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1199: pop
        //   1200: aload_3
        //   1201: astore_1
        //   1202: aload #5
        //   1204: aload #12
        //   1206: aload #11
        //   1208: iconst_1
        //   1209: invokeinterface getString : (I)Ljava/lang/String;
        //   1214: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1217: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1220: pop
        //   1221: aload #5
        //   1223: aload #6
        //   1225: aload #11
        //   1227: iconst_2
        //   1228: invokeinterface getDouble : (I)D
        //   1233: invokevirtual format : (D)Ljava/lang/String;
        //   1236: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1239: pop
        //   1240: aload #5
        //   1242: ldc_w '('
        //   1245: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1248: pop
        //   1249: aload #5
        //   1251: aload #6
        //   1253: aload #11
        //   1255: iconst_3
        //   1256: invokeinterface getDouble : (I)D
        //   1261: invokevirtual format : (D)Ljava/lang/String;
        //   1264: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1267: pop
        //   1268: aload #5
        //   1270: ldc_w ')\\n'
        //   1273: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1276: pop
        //   1277: aload #5
        //   1279: invokevirtual toString : ()Ljava/lang/String;
        //   1282: astore_1
        //   1283: aload_1
        //   1284: astore_3
        //   1285: goto -> 1070
        //   1288: astore_1
        //   1289: aload_3
        //   1290: astore_1
        //   1291: goto -> 1423
        //   1294: astore_3
        //   1295: goto -> 1423
        //   1298: new java/lang/StringBuilder
        //   1301: astore_1
        //   1302: aload_1
        //   1303: invokespecial <init> : ()V
        //   1306: aload_1
        //   1307: aload_3
        //   1308: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1311: pop
        //   1312: aload_1
        //   1313: aload #12
        //   1315: aload #11
        //   1317: iconst_1
        //   1318: invokeinterface getString : (I)Ljava/lang/String;
        //   1323: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1326: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1329: pop
        //   1330: aload_1
        //   1331: aload #6
        //   1333: aload #11
        //   1335: iconst_2
        //   1336: invokeinterface getDouble : (I)D
        //   1341: invokevirtual format : (D)Ljava/lang/String;
        //   1344: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1347: pop
        //   1348: aload_1
        //   1349: ldc_w '('
        //   1352: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1355: pop
        //   1356: aload_1
        //   1357: aload #6
        //   1359: aload #11
        //   1361: iconst_3
        //   1362: invokeinterface getDouble : (I)D
        //   1367: invokevirtual format : (D)Ljava/lang/String;
        //   1370: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1373: pop
        //   1374: aload_1
        //   1375: ldc_w ')\\n'
        //   1378: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1381: pop
        //   1382: aload_1
        //   1383: invokevirtual toString : ()Ljava/lang/String;
        //   1386: astore_1
        //   1387: aload_1
        //   1388: astore_3
        //   1389: goto -> 1070
        //   1392: astore_1
        //   1393: aload_3
        //   1394: astore_1
        //   1395: goto -> 1423
        //   1398: astore_1
        //   1399: aload_3
        //   1400: astore_1
        //   1401: goto -> 1423
        //   1404: aload_3
        //   1405: astore_1
        //   1406: goto -> 1423
        //   1409: astore_1
        //   1410: aload_3
        //   1411: astore_1
        //   1412: goto -> 1423
        //   1415: astore_3
        //   1416: goto -> 1423
        //   1419: astore_1
        //   1420: aload #8
        //   1422: astore_1
        //   1423: aload #9
        //   1425: ifnull -> 1445
        //   1428: aload #9
        //   1430: invokeinterface isClosed : ()Z
        //   1435: ifne -> 1445
        //   1438: aload #9
        //   1440: invokeinterface close : ()V
        //   1445: aload #11
        //   1447: ifnull -> 1467
        //   1450: aload #11
        //   1452: invokeinterface isClosed : ()Z
        //   1457: ifne -> 1467
        //   1460: aload #11
        //   1462: invokeinterface close : ()V
        //   1467: aload_1
        //   1468: areturn
        // Exception table:
        //   from	to	target	type
        //   174	179	1419	org/json/JSONException
        //   189	194	1419	org/json/JSONException
        //   204	215	1419	org/json/JSONException
        //   225	236	1419	org/json/JSONException
        //   246	257	1419	org/json/JSONException
        //   267	278	1419	org/json/JSONException
        //   288	299	1419	org/json/JSONException
        //   309	320	1419	org/json/JSONException
        //   330	342	1419	org/json/JSONException
        //   352	364	1419	org/json/JSONException
        //   374	386	1419	org/json/JSONException
        //   396	407	1419	org/json/JSONException
        //   417	429	1419	org/json/JSONException
        //   439	450	1419	org/json/JSONException
        //   460	472	1419	org/json/JSONException
        //   482	494	1419	org/json/JSONException
        //   504	516	1419	org/json/JSONException
        //   526	538	1419	org/json/JSONException
        //   548	557	1419	org/json/JSONException
        //   584	593	1419	org/json/JSONException
        //   606	619	964	org/json/JSONException
        //   622	632	964	org/json/JSONException
        //   635	640	964	org/json/JSONException
        //   643	648	964	org/json/JSONException
        //   651	658	964	org/json/JSONException
        //   661	670	964	org/json/JSONException
        //   673	687	964	org/json/JSONException
        //   690	699	964	org/json/JSONException
        //   702	708	964	org/json/JSONException
        //   711	716	964	org/json/JSONException
        //   719	724	964	org/json/JSONException
        //   727	734	964	org/json/JSONException
        //   737	756	964	org/json/JSONException
        //   759	778	964	org/json/JSONException
        //   781	790	964	org/json/JSONException
        //   793	812	964	org/json/JSONException
        //   815	824	964	org/json/JSONException
        //   827	833	964	org/json/JSONException
        //   839	844	964	org/json/JSONException
        //   847	852	964	org/json/JSONException
        //   855	862	964	org/json/JSONException
        //   865	884	964	org/json/JSONException
        //   887	906	964	org/json/JSONException
        //   909	918	964	org/json/JSONException
        //   921	940	964	org/json/JSONException
        //   943	952	964	org/json/JSONException
        //   955	961	964	org/json/JSONException
        //   974	983	1415	org/json/JSONException
        //   1012	1016	964	org/json/JSONException
        //   1019	1023	964	org/json/JSONException
        //   1026	1032	964	org/json/JSONException
        //   1035	1043	964	org/json/JSONException
        //   1046	1051	964	org/json/JSONException
        //   1070	1090	1409	org/json/JSONException
        //   1099	1109	1294	org/json/JSONException
        //   1111	1116	1294	org/json/JSONException
        //   1118	1123	1294	org/json/JSONException
        //   1125	1132	1294	org/json/JSONException
        //   1134	1142	1294	org/json/JSONException
        //   1144	1158	1294	org/json/JSONException
        //   1160	1169	1294	org/json/JSONException
        //   1171	1177	1294	org/json/JSONException
        //   1179	1184	1294	org/json/JSONException
        //   1186	1191	1294	org/json/JSONException
        //   1193	1200	1294	org/json/JSONException
        //   1202	1221	1294	org/json/JSONException
        //   1221	1283	1288	org/json/JSONException
        //   1298	1330	1398	org/json/JSONException
        //   1330	1387	1392	org/json/JSONException
        }

public String Tin_Chottien_CT11(String paramString) {
        // Byte code:
        //   0: new tamhoang/ldpro4/MainActivity
        //   3: dup
        //   4: invokespecial <init> : ()V
        //   7: pop
        //   8: invokestatic Get_date : ()Ljava/lang/String;
        //   11: astore_2
        //   12: invokestatic Get_ngay : ()Ljava/lang/String;
        //   15: astore_3
        //   16: ldc_w '###,###'
        //   19: astore #4
        //   21: new java/text/DecimalFormat
        //   24: dup
        //   25: ldc_w '###,###'
        //   28: invokespecial <init> : (Ljava/lang/String;)V
        //   31: astore #5
        //   33: ldc ''
        //   35: astore #6
        //   37: new java/lang/StringBuilder
        //   40: dup
        //   41: invokespecial <init> : ()V
        //   44: astore #7
        //   46: aload #7
        //   48: ldc_w 'Select so_tin_nhan, the_loai\\n, sum(diem) as mDiem\\n, CASE WHEN the_loai = 'xi' Then sum(diem*so_nhay*lan_an/1000) ELSE sum(diem*so_nhay) END as mAn \\n, sum(ket_qua) as ThanhTien \\nFrom tbl_soctS Where ngay_nhan = ''
        //   51: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   54: pop
        //   55: aload #7
        //   57: aload_2
        //   58: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   61: pop
        //   62: aload #7
        //   64: ldc_w '' And ten_kh = ''
        //   67: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   70: pop
        //   71: aload #7
        //   73: aload_1
        //   74: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   77: pop
        //   78: aload #7
        //   80: ldc_w '' and the_loai <> 'tt' AND type_kh = 1\\nGROUP by so_tin_nhan, the_loai ORDER by type_kh DESC, ten_kh'
        //   83: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   86: pop
        //   87: aload #7
        //   89: invokevirtual toString : ()Ljava/lang/String;
        //   92: astore #8
        //   94: aload_0
        //   95: aload #8
        //   97: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   100: astore #9
        //   102: new java/lang/StringBuilder
        //   105: dup
        //   106: invokespecial <init> : ()V
        //   109: astore #7
        //   111: aload #7
        //   113: ldc_w 'Select so_tin_nhan, the_loai \\n, sum(diem) as mDiem\\n, CASE WHEN the_loai = 'xi' Then sum(diem*so_nhay*lan_an/1000) ELSE sum(diem*so_nhay) END as mAn \\n, sum(ket_qua) as ThanhTien \\nFrom tbl_soctS Where ngay_nhan = ''
        //   116: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   119: pop
        //   120: aload #7
        //   122: aload_2
        //   123: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   126: pop
        //   127: aload #7
        //   129: ldc_w '' And ten_kh = ''
        //   132: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: pop
        //   136: aload #7
        //   138: aload_1
        //   139: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: pop
        //   143: aload #7
        //   145: ldc_w '' and the_loai <> 'tt' AND type_kh = 2\\nGROUP by so_tin_nhan, the_loai ORDER by type_kh DESC, ten_kh'
        //   148: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   151: pop
        //   152: aload #7
        //   154: invokevirtual toString : ()Ljava/lang/String;
        //   157: astore #10
        //   159: aload_0
        //   160: aload #10
        //   162: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   165: astore #7
        //   167: new org/json/JSONObject
        //   170: astore #11
        //   172: aload #11
        //   174: invokespecial <init> : ()V
        //   177: aload #11
        //   179: ldc 'dea'
        //   181: ldc_w 'Dau DB: '
        //   184: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   187: pop
        //   188: aload #11
        //   190: ldc 'deb'
        //   192: ldc_w 'De: '
        //   195: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   198: pop
        //   199: aload #11
        //   201: ldc 'det'
        //   203: ldc_w 'De 8: '
        //   206: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   209: pop
        //   210: aload #11
        //   212: ldc 'dec'
        //   214: ldc_w 'Dau Nhat: '
        //   217: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   220: pop
        //   221: aload #11
        //   223: ldc 'ded'
        //   225: ldc_w 'Dit Nhat: '
        //   228: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   231: pop
        //   232: aload #11
        //   234: ldc 'lo'
        //   236: ldc_w 'Lo: '
        //   239: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   242: pop
        //   243: aload #11
        //   245: ldc_w 'xi'
        //   248: ldc_w 'Xien: '
        //   251: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   254: pop
        //   255: aload #11
        //   257: ldc 'xn'
        //   259: ldc_w 'X.nhay: '
        //   262: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   265: pop
        //   266: aload #11
        //   268: ldc_w 'bc'
        //   271: ldc_w '3Cang: '
        //   274: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   277: pop
        //   278: aload #11
        //   280: ldc 'loa'
        //   282: ldc_w 'Lo dau: '
        //   285: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   288: pop
        //   289: aload #11
        //   291: ldc_w 'xia'
        //   294: ldc_w 'Xia: '
        //   297: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   300: pop
        //   301: aload #11
        //   303: ldc_w 'bca'
        //   306: ldc_w '3Cang dau: '
        //   309: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   312: pop
        //   313: aload #9
        //   315: invokeinterface getCount : ()I
        //   320: istore #12
        //   322: iload #12
        //   324: ifle -> 335
        //   327: ldc_w '\\nTin nhan:'
        //   330: astore #6
        //   332: goto -> 335
        //   335: iconst_0
        //   336: istore #12
        //   338: dconst_0
        //   339: dstore #13
        //   341: dconst_0
        //   342: dstore #15
        //   344: ldc ''
        //   346: astore #17
        //   348: aload #9
        //   350: invokeinterface moveToNext : ()Z
        //   355: istore #18
        //   357: ldc_w ':'
        //   360: astore #19
        //   362: ldc_w 'Tong tin '
        //   365: astore #20
        //   367: ldc_w ':\\n'
        //   370: astore #21
        //   372: ldc_w '\\nTin '
        //   375: astore #22
        //   377: ldc_w '('
        //   380: astore #23
        //   382: iload #18
        //   384: ifeq -> 1176
        //   387: aload #6
        //   389: astore_1
        //   390: iload #12
        //   392: aload #9
        //   394: iconst_0
        //   395: invokeinterface getInt : (I)I
        //   400: if_icmpeq -> 910
        //   403: aload #6
        //   405: astore #22
        //   407: dload #13
        //   409: dstore #24
        //   411: iload #12
        //   413: ifle -> 495
        //   416: aload #6
        //   418: astore_1
        //   419: new java/lang/StringBuilder
        //   422: astore #22
        //   424: aload #6
        //   426: astore_1
        //   427: aload #22
        //   429: invokespecial <init> : ()V
        //   432: aload #6
        //   434: astore_1
        //   435: aload #22
        //   437: aload #6
        //   439: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   442: pop
        //   443: aload #6
        //   445: astore_1
        //   446: aload #22
        //   448: aload #17
        //   450: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   453: pop
        //   454: aload #6
        //   456: astore_1
        //   457: aload #22
        //   459: aload #5
        //   461: dload #13
        //   463: invokevirtual format : (D)Ljava/lang/String;
        //   466: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   469: pop
        //   470: aload #6
        //   472: astore_1
        //   473: aload #22
        //   475: ldc_w '\\n'
        //   478: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   481: pop
        //   482: aload #6
        //   484: astore_1
        //   485: aload #22
        //   487: invokevirtual toString : ()Ljava/lang/String;
        //   490: astore #22
        //   492: dconst_0
        //   493: dstore #24
        //   495: aload #22
        //   497: astore_1
        //   498: aload #9
        //   500: iconst_0
        //   501: invokeinterface getInt : (I)I
        //   506: istore #12
        //   508: aload #22
        //   510: astore_1
        //   511: new java/lang/StringBuilder
        //   514: astore #6
        //   516: aload #22
        //   518: astore_1
        //   519: aload #6
        //   521: invokespecial <init> : ()V
        //   524: aload #22
        //   526: astore_1
        //   527: aload #6
        //   529: aload #22
        //   531: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   534: pop
        //   535: aload #22
        //   537: astore_1
        //   538: aload #6
        //   540: ldc_w '\\nTin '
        //   543: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   546: pop
        //   547: aload #22
        //   549: astore_1
        //   550: aload #6
        //   552: aload #9
        //   554: iconst_0
        //   555: invokeinterface getString : (I)Ljava/lang/String;
        //   560: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   563: pop
        //   564: aload #22
        //   566: astore_1
        //   567: aload #6
        //   569: ldc_w ':\\n'
        //   572: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   575: pop
        //   576: aload #22
        //   578: astore_1
        //   579: aload #6
        //   581: invokevirtual toString : ()Ljava/lang/String;
        //   584: astore #6
        //   586: aload #6
        //   588: astore_1
        //   589: new java/lang/StringBuilder
        //   592: astore #22
        //   594: aload #6
        //   596: astore_1
        //   597: aload #22
        //   599: invokespecial <init> : ()V
        //   602: aload #6
        //   604: astore_1
        //   605: aload #22
        //   607: ldc_w 'Tong tin '
        //   610: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   613: pop
        //   614: aload #6
        //   616: astore_1
        //   617: aload #22
        //   619: aload #9
        //   621: iconst_0
        //   622: invokeinterface getString : (I)Ljava/lang/String;
        //   627: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   630: pop
        //   631: aload #6
        //   633: astore_1
        //   634: aload #22
        //   636: ldc_w ':'
        //   639: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   642: pop
        //   643: aload #6
        //   645: astore_1
        //   646: aload #22
        //   648: invokevirtual toString : ()Ljava/lang/String;
        //   651: astore #17
        //   653: aload #6
        //   655: astore_1
        //   656: new java/lang/StringBuilder
        //   659: astore #22
        //   661: aload #6
        //   663: astore_1
        //   664: aload #22
        //   666: invokespecial <init> : ()V
        //   669: aload #6
        //   671: astore_1
        //   672: aload #22
        //   674: aload #6
        //   676: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   679: pop
        //   680: aload #6
        //   682: astore_1
        //   683: aload #22
        //   685: aload #11
        //   687: aload #9
        //   689: iconst_1
        //   690: invokeinterface getString : (I)Ljava/lang/String;
        //   695: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   698: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   701: pop
        //   702: aload #6
        //   704: astore_1
        //   705: aload #22
        //   707: aload #5
        //   709: aload #9
        //   711: iconst_2
        //   712: invokeinterface getDouble : (I)D
        //   717: invokevirtual format : (D)Ljava/lang/String;
        //   720: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   723: pop
        //   724: aload #6
        //   726: astore_1
        //   727: aload #22
        //   729: ldc_w '('
        //   732: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   735: pop
        //   736: aload #6
        //   738: astore_1
        //   739: aload #22
        //   741: aload #5
        //   743: aload #9
        //   745: iconst_3
        //   746: invokeinterface getDouble : (I)D
        //   751: invokevirtual format : (D)Ljava/lang/String;
        //   754: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   757: pop
        //   758: aload #6
        //   760: astore_1
        //   761: aload #22
        //   763: ldc_w ')'
        //   766: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   769: pop
        //   770: aload #6
        //   772: astore_1
        //   773: aload #22
        //   775: invokevirtual toString : ()Ljava/lang/String;
        //   778: astore #6
        //   780: aload #6
        //   782: astore_1
        //   783: new java/lang/StringBuilder
        //   786: astore #22
        //   788: aload #6
        //   790: astore_1
        //   791: aload #22
        //   793: invokespecial <init> : ()V
        //   796: aload #6
        //   798: astore_1
        //   799: aload #22
        //   801: aload #6
        //   803: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   806: pop
        //   807: aload #6
        //   809: astore_1
        //   810: aload #22
        //   812: ldc_w '='
        //   815: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   818: pop
        //   819: aload #6
        //   821: astore_1
        //   822: aload #22
        //   824: aload #5
        //   826: aload #9
        //   828: iconst_4
        //   829: invokeinterface getDouble : (I)D
        //   834: ldc2_w 1000.0
        //   837: ddiv
        //   838: invokevirtual format : (D)Ljava/lang/String;
        //   841: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   844: pop
        //   845: aload #6
        //   847: astore_1
        //   848: aload #22
        //   850: ldc_w '\\n'
        //   853: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   856: pop
        //   857: aload #6
        //   859: astore_1
        //   860: aload #22
        //   862: invokevirtual toString : ()Ljava/lang/String;
        //   865: astore #6
        //   867: aload #6
        //   869: astore_1
        //   870: dload #24
        //   872: aload #9
        //   874: iconst_4
        //   875: invokeinterface getDouble : (I)D
        //   880: ldc2_w 1000.0
        //   883: ddiv
        //   884: dadd
        //   885: dstore #13
        //   887: aload #6
        //   889: astore_1
        //   890: dload #15
        //   892: aload #9
        //   894: iconst_4
        //   895: invokeinterface getDouble : (I)D
        //   900: ldc2_w 1000.0
        //   903: ddiv
        //   904: dadd
        //   905: dstore #15
        //   907: goto -> 348
        //   910: aload #6
        //   912: astore_1
        //   913: new java/lang/StringBuilder
        //   916: astore #22
        //   918: aload #6
        //   920: astore_1
        //   921: aload #22
        //   923: invokespecial <init> : ()V
        //   926: aload #6
        //   928: astore_1
        //   929: aload #22
        //   931: aload #6
        //   933: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   936: pop
        //   937: aload #6
        //   939: astore_1
        //   940: aload #22
        //   942: aload #11
        //   944: aload #9
        //   946: iconst_1
        //   947: invokeinterface getString : (I)Ljava/lang/String;
        //   952: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   955: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   958: pop
        //   959: aload #6
        //   961: astore_1
        //   962: aload #22
        //   964: aload #5
        //   966: aload #9
        //   968: iconst_2
        //   969: invokeinterface getDouble : (I)D
        //   974: invokevirtual format : (D)Ljava/lang/String;
        //   977: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   980: pop
        //   981: aload #6
        //   983: astore_1
        //   984: aload #22
        //   986: ldc_w '('
        //   989: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   992: pop
        //   993: aload #6
        //   995: astore_1
        //   996: aload #22
        //   998: aload #5
        //   1000: aload #9
        //   1002: iconst_3
        //   1003: invokeinterface getDouble : (I)D
        //   1008: invokevirtual format : (D)Ljava/lang/String;
        //   1011: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1014: pop
        //   1015: aload #6
        //   1017: astore_1
        //   1018: aload #22
        //   1020: ldc_w ')'
        //   1023: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1026: pop
        //   1027: aload #6
        //   1029: astore_1
        //   1030: aload #22
        //   1032: invokevirtual toString : ()Ljava/lang/String;
        //   1035: astore #6
        //   1037: aload #6
        //   1039: astore_1
        //   1040: new java/lang/StringBuilder
        //   1043: astore #22
        //   1045: aload #6
        //   1047: astore_1
        //   1048: aload #22
        //   1050: invokespecial <init> : ()V
        //   1053: aload #6
        //   1055: astore_1
        //   1056: aload #22
        //   1058: aload #6
        //   1060: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1063: pop
        //   1064: aload #6
        //   1066: astore_1
        //   1067: aload #22
        //   1069: ldc_w '='
        //   1072: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1075: pop
        //   1076: aload #6
        //   1078: astore_1
        //   1079: aload #22
        //   1081: aload #5
        //   1083: aload #9
        //   1085: iconst_4
        //   1086: invokeinterface getDouble : (I)D
        //   1091: ldc2_w 1000.0
        //   1094: ddiv
        //   1095: invokevirtual format : (D)Ljava/lang/String;
        //   1098: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1101: pop
        //   1102: aload #6
        //   1104: astore_1
        //   1105: aload #22
        //   1107: ldc_w '\\n'
        //   1110: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1113: pop
        //   1114: aload #6
        //   1116: astore_1
        //   1117: aload #22
        //   1119: invokevirtual toString : ()Ljava/lang/String;
        //   1122: astore #6
        //   1124: aload #6
        //   1126: astore_1
        //   1127: dload #13
        //   1129: aload #9
        //   1131: iconst_4
        //   1132: invokeinterface getDouble : (I)D
        //   1137: ldc2_w 1000.0
        //   1140: ddiv
        //   1141: dadd
        //   1142: dstore #13
        //   1144: aload #6
        //   1146: astore_1
        //   1147: aload #9
        //   1149: iconst_4
        //   1150: invokeinterface getDouble : (I)D
        //   1155: dstore #24
        //   1157: dload #15
        //   1159: dload #24
        //   1161: ldc2_w 1000.0
        //   1164: ddiv
        //   1165: dadd
        //   1166: dstore #15
        //   1168: goto -> 348
        //   1171: astore #6
        //   1173: goto -> 2464
        //   1176: ldc_w '='
        //   1179: astore #8
        //   1181: dload #13
        //   1183: dconst_0
        //   1184: dcmpl
        //   1185: ifle -> 1335
        //   1188: aload #6
        //   1190: astore_1
        //   1191: new java/lang/StringBuilder
        //   1194: astore #10
        //   1196: aload #6
        //   1198: astore_1
        //   1199: aload #10
        //   1201: invokespecial <init> : ()V
        //   1204: aload #6
        //   1206: astore_1
        //   1207: aload #10
        //   1209: aload #6
        //   1211: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1214: pop
        //   1215: aload #6
        //   1217: astore_1
        //   1218: aload #10
        //   1220: aload #17
        //   1222: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1225: pop
        //   1226: aload #6
        //   1228: astore_1
        //   1229: aload #10
        //   1231: aload #5
        //   1233: dload #13
        //   1235: invokevirtual format : (D)Ljava/lang/String;
        //   1238: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1241: pop
        //   1242: aload #6
        //   1244: astore_1
        //   1245: aload #10
        //   1247: ldc_w '\\n\\n'
        //   1250: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1253: pop
        //   1254: aload #6
        //   1256: astore_1
        //   1257: aload #10
        //   1259: invokevirtual toString : ()Ljava/lang/String;
        //   1262: astore #6
        //   1264: aload #6
        //   1266: astore_1
        //   1267: new java/lang/StringBuilder
        //   1270: astore #10
        //   1272: aload #6
        //   1274: astore_1
        //   1275: aload #10
        //   1277: invokespecial <init> : ()V
        //   1280: aload #6
        //   1282: astore_1
        //   1283: aload #10
        //   1285: aload #6
        //   1287: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1290: pop
        //   1291: aload #6
        //   1293: astore_1
        //   1294: aload #10
        //   1296: ldc_w 'Tong cong:'
        //   1299: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1302: pop
        //   1303: aload #6
        //   1305: astore_1
        //   1306: aload #10
        //   1308: aload #5
        //   1310: dload #15
        //   1312: invokevirtual format : (D)Ljava/lang/String;
        //   1315: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1318: pop
        //   1319: aload #6
        //   1321: astore_1
        //   1322: aload #10
        //   1324: invokevirtual toString : ()Ljava/lang/String;
        //   1327: astore #6
        //   1329: aload #6
        //   1331: astore_1
        //   1332: goto -> 1338
        //   1335: aload #6
        //   1337: astore_1
        //   1338: ldc_w ')'
        //   1341: astore #26
        //   1343: iconst_0
        //   1344: istore #12
        //   1346: dconst_0
        //   1347: dstore #15
        //   1349: ldc ''
        //   1351: astore #27
        //   1353: aload #7
        //   1355: invokeinterface getCount : ()I
        //   1360: istore #28
        //   1362: iload #28
        //   1364: ifle -> 1444
        //   1367: new java/lang/StringBuilder
        //   1370: astore #6
        //   1372: aload #6
        //   1374: invokespecial <init> : ()V
        //   1377: aload #6
        //   1379: aload_1
        //   1380: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1383: pop
        //   1384: aload #6
        //   1386: ldc_w '\\n\\nTin Chuyen:'
        //   1389: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1392: pop
        //   1393: aload #6
        //   1395: invokevirtual toString : ()Ljava/lang/String;
        //   1398: astore #6
        //   1400: aload #6
        //   1402: astore_1
        //   1403: iconst_0
        //   1404: istore #12
        //   1406: dconst_0
        //   1407: dstore #13
        //   1409: aload #23
        //   1411: astore #4
        //   1413: aload #19
        //   1415: astore #10
        //   1417: aload #20
        //   1419: astore_2
        //   1420: aload #21
        //   1422: astore_3
        //   1423: aload #7
        //   1425: astore #6
        //   1427: aload #8
        //   1429: astore #17
        //   1431: goto -> 1469
        //   1434: astore #6
        //   1436: goto -> 2464
        //   1439: astore #6
        //   1441: goto -> 2464
        //   1444: dconst_0
        //   1445: dstore #13
        //   1447: aload #8
        //   1449: astore #17
        //   1451: aload #7
        //   1453: astore #6
        //   1455: aload #21
        //   1457: astore_3
        //   1458: aload #20
        //   1460: astore_2
        //   1461: aload #19
        //   1463: astore #10
        //   1465: aload #23
        //   1467: astore #4
        //   1469: aload #6
        //   1471: invokeinterface moveToNext : ()Z
        //   1476: istore #18
        //   1478: iload #18
        //   1480: ifeq -> 2281
        //   1483: aload #6
        //   1485: astore #8
        //   1487: aload_1
        //   1488: astore #7
        //   1490: iload #12
        //   1492: aload #8
        //   1494: iconst_0
        //   1495: invokeinterface getInt : (I)I
        //   1500: if_icmpeq -> 2012
        //   1503: aload_1
        //   1504: astore #19
        //   1506: dload #13
        //   1508: dstore #24
        //   1510: iload #12
        //   1512: ifle -> 1593
        //   1515: aload_1
        //   1516: astore #7
        //   1518: new java/lang/StringBuilder
        //   1521: astore #19
        //   1523: aload_1
        //   1524: astore #7
        //   1526: aload #19
        //   1528: invokespecial <init> : ()V
        //   1531: aload_1
        //   1532: astore #7
        //   1534: aload #19
        //   1536: aload_1
        //   1537: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1540: pop
        //   1541: aload_1
        //   1542: astore #7
        //   1544: aload #19
        //   1546: aload #27
        //   1548: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1551: pop
        //   1552: aload_1
        //   1553: astore #7
        //   1555: aload #19
        //   1557: aload #5
        //   1559: dload #13
        //   1561: invokevirtual format : (D)Ljava/lang/String;
        //   1564: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1567: pop
        //   1568: aload_1
        //   1569: astore #7
        //   1571: aload #19
        //   1573: ldc_w '\\n'
        //   1576: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1579: pop
        //   1580: aload_1
        //   1581: astore #7
        //   1583: aload #19
        //   1585: invokevirtual toString : ()Ljava/lang/String;
        //   1588: astore #19
        //   1590: dconst_0
        //   1591: dstore #24
        //   1593: aload #19
        //   1595: astore #7
        //   1597: aload #8
        //   1599: iconst_0
        //   1600: invokeinterface getInt : (I)I
        //   1605: istore #12
        //   1607: aload #19
        //   1609: astore #7
        //   1611: new java/lang/StringBuilder
        //   1614: astore_1
        //   1615: aload #19
        //   1617: astore #7
        //   1619: aload_1
        //   1620: invokespecial <init> : ()V
        //   1623: aload #19
        //   1625: astore #7
        //   1627: aload_1
        //   1628: aload #19
        //   1630: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1633: pop
        //   1634: aload #19
        //   1636: astore #7
        //   1638: aload_1
        //   1639: aload #22
        //   1641: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1644: pop
        //   1645: aload #19
        //   1647: astore #7
        //   1649: aload_1
        //   1650: aload #8
        //   1652: iconst_0
        //   1653: invokeinterface getString : (I)Ljava/lang/String;
        //   1658: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1661: pop
        //   1662: aload #19
        //   1664: astore #7
        //   1666: aload_1
        //   1667: aload_3
        //   1668: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1671: pop
        //   1672: aload #19
        //   1674: astore #7
        //   1676: aload_1
        //   1677: invokevirtual toString : ()Ljava/lang/String;
        //   1680: astore_1
        //   1681: aload_1
        //   1682: astore #7
        //   1684: new java/lang/StringBuilder
        //   1687: astore #27
        //   1689: aload_1
        //   1690: astore #7
        //   1692: aload #27
        //   1694: invokespecial <init> : ()V
        //   1697: aload_1
        //   1698: astore #7
        //   1700: aload #27
        //   1702: aload_2
        //   1703: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1706: pop
        //   1707: aload_1
        //   1708: astore #7
        //   1710: aload #27
        //   1712: aload #9
        //   1714: iconst_0
        //   1715: invokeinterface getString : (I)Ljava/lang/String;
        //   1720: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1723: pop
        //   1724: aload_1
        //   1725: astore #7
        //   1727: aload #27
        //   1729: aload #10
        //   1731: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1734: pop
        //   1735: aload_1
        //   1736: astore #7
        //   1738: aload #27
        //   1740: invokevirtual toString : ()Ljava/lang/String;
        //   1743: astore #27
        //   1745: aload_1
        //   1746: astore #7
        //   1748: new java/lang/StringBuilder
        //   1751: astore #19
        //   1753: aload_1
        //   1754: astore #7
        //   1756: aload #19
        //   1758: invokespecial <init> : ()V
        //   1761: aload_1
        //   1762: astore #7
        //   1764: aload #19
        //   1766: aload_1
        //   1767: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1770: pop
        //   1771: aload_1
        //   1772: astore #7
        //   1774: aload #19
        //   1776: aload #11
        //   1778: aload #8
        //   1780: iconst_1
        //   1781: invokeinterface getString : (I)Ljava/lang/String;
        //   1786: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1789: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1792: pop
        //   1793: aload_1
        //   1794: astore #7
        //   1796: aload #19
        //   1798: aload #5
        //   1800: aload #8
        //   1802: iconst_2
        //   1803: invokeinterface getDouble : (I)D
        //   1808: invokevirtual format : (D)Ljava/lang/String;
        //   1811: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1814: pop
        //   1815: aload_1
        //   1816: astore #7
        //   1818: aload #19
        //   1820: aload #4
        //   1822: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1825: pop
        //   1826: aload_1
        //   1827: astore #7
        //   1829: aload #19
        //   1831: aload #5
        //   1833: aload #8
        //   1835: iconst_3
        //   1836: invokeinterface getDouble : (I)D
        //   1841: invokevirtual format : (D)Ljava/lang/String;
        //   1844: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1847: pop
        //   1848: aload_1
        //   1849: astore #7
        //   1851: aload #19
        //   1853: aload #26
        //   1855: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1858: pop
        //   1859: aload_1
        //   1860: astore #7
        //   1862: aload #19
        //   1864: invokevirtual toString : ()Ljava/lang/String;
        //   1867: astore_1
        //   1868: new java/lang/StringBuilder
        //   1871: astore #7
        //   1873: aload #7
        //   1875: invokespecial <init> : ()V
        //   1878: aload #7
        //   1880: aload_1
        //   1881: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1884: pop
        //   1885: aload #7
        //   1887: aload #17
        //   1889: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1892: pop
        //   1893: aload #9
        //   1895: iconst_4
        //   1896: invokeinterface getDouble : (I)D
        //   1901: dstore #13
        //   1903: dload #13
        //   1905: ldc2_w 1000.0
        //   1908: ddiv
        //   1909: dstore #13
        //   1911: aload #7
        //   1913: aload #5
        //   1915: dload #13
        //   1917: invokevirtual format : (D)Ljava/lang/String;
        //   1920: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1923: pop
        //   1924: aload #7
        //   1926: ldc_w '\\n'
        //   1929: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1932: pop
        //   1933: aload #7
        //   1935: invokevirtual toString : ()Ljava/lang/String;
        //   1938: astore #7
        //   1940: aload #7
        //   1942: astore_1
        //   1943: aload_1
        //   1944: astore #7
        //   1946: dload #24
        //   1948: aload #9
        //   1950: iconst_4
        //   1951: invokeinterface getDouble : (I)D
        //   1956: ldc2_w 1000.0
        //   1959: ddiv
        //   1960: dadd
        //   1961: dstore #13
        //   1963: aload_1
        //   1964: astore #7
        //   1966: aload #9
        //   1968: iconst_4
        //   1969: invokeinterface getDouble : (I)D
        //   1974: ldc2_w 1000.0
        //   1977: ddiv
        //   1978: dstore #24
        //   1980: aload #8
        //   1982: astore #6
        //   1984: dload #15
        //   1986: dload #24
        //   1988: dadd
        //   1989: dstore #15
        //   1991: goto -> 1469
        //   1994: astore #7
        //   1996: aload #8
        //   1998: astore #7
        //   2000: goto -> 2464
        //   2003: astore #7
        //   2005: aload #8
        //   2007: astore #7
        //   2009: goto -> 2464
        //   2012: aload_1
        //   2013: astore #7
        //   2015: new java/lang/StringBuilder
        //   2018: astore #19
        //   2020: aload_1
        //   2021: astore #7
        //   2023: aload #19
        //   2025: invokespecial <init> : ()V
        //   2028: aload_1
        //   2029: astore #7
        //   2031: aload #19
        //   2033: aload_1
        //   2034: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2037: pop
        //   2038: aload_1
        //   2039: astore #7
        //   2041: aload #19
        //   2043: aload #11
        //   2045: aload #8
        //   2047: iconst_1
        //   2048: invokeinterface getString : (I)Ljava/lang/String;
        //   2053: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   2056: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2059: pop
        //   2060: aload_1
        //   2061: astore #7
        //   2063: aload #19
        //   2065: aload #5
        //   2067: aload #8
        //   2069: iconst_2
        //   2070: invokeinterface getDouble : (I)D
        //   2075: invokevirtual format : (D)Ljava/lang/String;
        //   2078: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2081: pop
        //   2082: aload_1
        //   2083: astore #7
        //   2085: aload #19
        //   2087: aload #4
        //   2089: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2092: pop
        //   2093: aload_1
        //   2094: astore #7
        //   2096: aload #19
        //   2098: aload #5
        //   2100: aload #8
        //   2102: iconst_3
        //   2103: invokeinterface getDouble : (I)D
        //   2108: invokevirtual format : (D)Ljava/lang/String;
        //   2111: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2114: pop
        //   2115: aload_1
        //   2116: astore #7
        //   2118: aload #19
        //   2120: aload #26
        //   2122: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2125: pop
        //   2126: aload_1
        //   2127: astore #7
        //   2129: aload #19
        //   2131: invokevirtual toString : ()Ljava/lang/String;
        //   2134: astore_1
        //   2135: aload_1
        //   2136: astore #7
        //   2138: new java/lang/StringBuilder
        //   2141: astore #19
        //   2143: aload_1
        //   2144: astore #7
        //   2146: aload #19
        //   2148: invokespecial <init> : ()V
        //   2151: aload_1
        //   2152: astore #7
        //   2154: aload #19
        //   2156: aload_1
        //   2157: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2160: pop
        //   2161: aload_1
        //   2162: astore #7
        //   2164: aload #19
        //   2166: aload #17
        //   2168: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2171: pop
        //   2172: aload_1
        //   2173: astore #7
        //   2175: aload #19
        //   2177: aload #5
        //   2179: aload #9
        //   2181: iconst_4
        //   2182: invokeinterface getDouble : (I)D
        //   2187: ldc2_w 1000.0
        //   2190: ddiv
        //   2191: invokevirtual format : (D)Ljava/lang/String;
        //   2194: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2197: pop
        //   2198: aload_1
        //   2199: astore #7
        //   2201: aload #19
        //   2203: ldc_w '\\n'
        //   2206: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2209: pop
        //   2210: aload_1
        //   2211: astore #7
        //   2213: aload #19
        //   2215: invokevirtual toString : ()Ljava/lang/String;
        //   2218: astore_1
        //   2219: aload_1
        //   2220: astore #7
        //   2222: dload #13
        //   2224: aload #9
        //   2226: iconst_4
        //   2227: invokeinterface getDouble : (I)D
        //   2232: ldc2_w 1000.0
        //   2235: ddiv
        //   2236: dadd
        //   2237: dstore #13
        //   2239: aload_1
        //   2240: astore #7
        //   2242: aload #9
        //   2244: iconst_4
        //   2245: invokeinterface getDouble : (I)D
        //   2250: ldc2_w 1000.0
        //   2253: ddiv
        //   2254: dstore #24
        //   2256: aload #8
        //   2258: astore #6
        //   2260: dload #15
        //   2262: dload #24
        //   2264: dadd
        //   2265: dstore #15
        //   2267: goto -> 1469
        //   2270: astore_1
        //   2271: aload #7
        //   2273: astore_1
        //   2274: aload #6
        //   2276: astore #7
        //   2278: goto -> 2464
        //   2281: aload #6
        //   2283: astore #8
        //   2285: dload #13
        //   2287: dconst_0
        //   2288: dcmpl
        //   2289: ifle -> 2432
        //   2292: aload_1
        //   2293: astore #7
        //   2295: new java/lang/StringBuilder
        //   2298: astore #10
        //   2300: aload_1
        //   2301: astore #7
        //   2303: aload #10
        //   2305: invokespecial <init> : ()V
        //   2308: aload_1
        //   2309: astore #7
        //   2311: aload #10
        //   2313: aload_1
        //   2314: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2317: pop
        //   2318: aload_1
        //   2319: astore #7
        //   2321: aload #10
        //   2323: aload #27
        //   2325: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2328: pop
        //   2329: aload_1
        //   2330: astore #7
        //   2332: aload #10
        //   2334: aload #5
        //   2336: dload #13
        //   2338: invokevirtual format : (D)Ljava/lang/String;
        //   2341: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2344: pop
        //   2345: aload_1
        //   2346: astore #7
        //   2348: aload #10
        //   2350: ldc_w '\\n\\n'
        //   2353: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2356: pop
        //   2357: aload_1
        //   2358: astore #7
        //   2360: aload #10
        //   2362: invokevirtual toString : ()Ljava/lang/String;
        //   2365: astore_1
        //   2366: aload_1
        //   2367: astore #7
        //   2369: new java/lang/StringBuilder
        //   2372: astore #10
        //   2374: aload_1
        //   2375: astore #7
        //   2377: aload #10
        //   2379: invokespecial <init> : ()V
        //   2382: aload_1
        //   2383: astore #7
        //   2385: aload #10
        //   2387: aload_1
        //   2388: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2391: pop
        //   2392: aload_1
        //   2393: astore #7
        //   2395: aload #10
        //   2397: ldc_w 'Tong cong:'
        //   2400: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2403: pop
        //   2404: aload_1
        //   2405: astore #7
        //   2407: aload #10
        //   2409: aload #5
        //   2411: dload #15
        //   2413: invokevirtual format : (D)Ljava/lang/String;
        //   2416: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   2419: pop
        //   2420: aload_1
        //   2421: astore #7
        //   2423: aload #10
        //   2425: invokevirtual toString : ()Ljava/lang/String;
        //   2428: astore_1
        //   2429: goto -> 2432
        //   2432: aload #8
        //   2434: astore #7
        //   2436: goto -> 2464
        //   2439: astore #7
        //   2441: aload #6
        //   2443: astore #7
        //   2445: goto -> 2464
        //   2448: astore #6
        //   2450: goto -> 2464
        //   2453: astore_1
        //   2454: aload #6
        //   2456: astore_1
        //   2457: goto -> 2464
        //   2460: astore_1
        //   2461: aload #6
        //   2463: astore_1
        //   2464: aload #9
        //   2466: ifnull -> 2486
        //   2469: aload #9
        //   2471: invokeinterface isClosed : ()Z
        //   2476: ifne -> 2486
        //   2479: aload #9
        //   2481: invokeinterface close : ()V
        //   2486: aload #7
        //   2488: ifnull -> 2508
        //   2491: aload #7
        //   2493: invokeinterface isClosed : ()Z
        //   2498: ifne -> 2508
        //   2501: aload #7
        //   2503: invokeinterface close : ()V
        //   2508: aload_1
        //   2509: areturn
        // Exception table:
        //   from	to	target	type
        //   167	322	2460	org/json/JSONException
        //   348	357	2453	org/json/JSONException
        //   390	403	1171	org/json/JSONException
        //   419	424	1171	org/json/JSONException
        //   427	432	1171	org/json/JSONException
        //   435	443	1171	org/json/JSONException
        //   446	454	1171	org/json/JSONException
        //   457	470	1171	org/json/JSONException
        //   473	482	1171	org/json/JSONException
        //   485	492	1171	org/json/JSONException
        //   498	508	1171	org/json/JSONException
        //   511	516	1171	org/json/JSONException
        //   519	524	1171	org/json/JSONException
        //   527	535	1171	org/json/JSONException
        //   538	547	1171	org/json/JSONException
        //   550	564	1171	org/json/JSONException
        //   567	576	1171	org/json/JSONException
        //   579	586	1171	org/json/JSONException
        //   589	594	1171	org/json/JSONException
        //   597	602	1171	org/json/JSONException
        //   605	614	1171	org/json/JSONException
        //   617	631	1171	org/json/JSONException
        //   634	643	1171	org/json/JSONException
        //   646	653	1171	org/json/JSONException
        //   656	661	1171	org/json/JSONException
        //   664	669	1171	org/json/JSONException
        //   672	680	1171	org/json/JSONException
        //   683	702	1171	org/json/JSONException
        //   705	724	1171	org/json/JSONException
        //   727	736	1171	org/json/JSONException
        //   739	758	1171	org/json/JSONException
        //   761	770	1171	org/json/JSONException
        //   773	780	1171	org/json/JSONException
        //   783	788	1171	org/json/JSONException
        //   791	796	1171	org/json/JSONException
        //   799	807	1171	org/json/JSONException
        //   810	819	1171	org/json/JSONException
        //   822	845	1171	org/json/JSONException
        //   848	857	1171	org/json/JSONException
        //   860	867	1171	org/json/JSONException
        //   870	887	1171	org/json/JSONException
        //   890	907	1171	org/json/JSONException
        //   913	918	1171	org/json/JSONException
        //   921	926	1171	org/json/JSONException
        //   929	937	1171	org/json/JSONException
        //   940	959	1171	org/json/JSONException
        //   962	981	1171	org/json/JSONException
        //   984	993	1171	org/json/JSONException
        //   996	1015	1171	org/json/JSONException
        //   1018	1027	1171	org/json/JSONException
        //   1030	1037	1171	org/json/JSONException
        //   1040	1045	1171	org/json/JSONException
        //   1048	1053	1171	org/json/JSONException
        //   1056	1064	1171	org/json/JSONException
        //   1067	1076	1171	org/json/JSONException
        //   1079	1102	1171	org/json/JSONException
        //   1105	1114	1171	org/json/JSONException
        //   1117	1124	1171	org/json/JSONException
        //   1127	1144	1171	org/json/JSONException
        //   1147	1157	1171	org/json/JSONException
        //   1191	1196	1171	org/json/JSONException
        //   1199	1204	1171	org/json/JSONException
        //   1207	1215	1171	org/json/JSONException
        //   1218	1226	1171	org/json/JSONException
        //   1229	1242	1171	org/json/JSONException
        //   1245	1254	1171	org/json/JSONException
        //   1257	1264	1171	org/json/JSONException
        //   1267	1272	1171	org/json/JSONException
        //   1275	1280	1171	org/json/JSONException
        //   1283	1291	1171	org/json/JSONException
        //   1294	1303	1171	org/json/JSONException
        //   1306	1319	1171	org/json/JSONException
        //   1322	1329	1171	org/json/JSONException
        //   1353	1362	2448	org/json/JSONException
        //   1367	1384	1439	org/json/JSONException
        //   1384	1400	1434	org/json/JSONException
        //   1469	1478	2439	org/json/JSONException
        //   1490	1503	2270	org/json/JSONException
        //   1518	1523	2270	org/json/JSONException
        //   1526	1531	2270	org/json/JSONException
        //   1534	1541	2270	org/json/JSONException
        //   1544	1552	2270	org/json/JSONException
        //   1555	1568	2270	org/json/JSONException
        //   1571	1580	2270	org/json/JSONException
        //   1583	1590	2270	org/json/JSONException
        //   1597	1607	2270	org/json/JSONException
        //   1611	1615	2270	org/json/JSONException
        //   1619	1623	2270	org/json/JSONException
        //   1627	1634	2270	org/json/JSONException
        //   1638	1645	2270	org/json/JSONException
        //   1649	1662	2270	org/json/JSONException
        //   1666	1672	2270	org/json/JSONException
        //   1676	1681	2270	org/json/JSONException
        //   1684	1689	2270	org/json/JSONException
        //   1692	1697	2270	org/json/JSONException
        //   1700	1707	2270	org/json/JSONException
        //   1710	1724	2270	org/json/JSONException
        //   1727	1735	2270	org/json/JSONException
        //   1738	1745	2270	org/json/JSONException
        //   1748	1753	2270	org/json/JSONException
        //   1756	1761	2270	org/json/JSONException
        //   1764	1771	2270	org/json/JSONException
        //   1774	1793	2270	org/json/JSONException
        //   1796	1815	2270	org/json/JSONException
        //   1818	1826	2270	org/json/JSONException
        //   1829	1848	2270	org/json/JSONException
        //   1851	1859	2270	org/json/JSONException
        //   1862	1868	2270	org/json/JSONException
        //   1868	1903	2003	org/json/JSONException
        //   1911	1940	1994	org/json/JSONException
        //   1946	1963	2270	org/json/JSONException
        //   1966	1980	2270	org/json/JSONException
        //   2015	2020	2270	org/json/JSONException
        //   2023	2028	2270	org/json/JSONException
        //   2031	2038	2270	org/json/JSONException
        //   2041	2060	2270	org/json/JSONException
        //   2063	2082	2270	org/json/JSONException
        //   2085	2093	2270	org/json/JSONException
        //   2096	2115	2270	org/json/JSONException
        //   2118	2126	2270	org/json/JSONException
        //   2129	2135	2270	org/json/JSONException
        //   2138	2143	2270	org/json/JSONException
        //   2146	2151	2270	org/json/JSONException
        //   2154	2161	2270	org/json/JSONException
        //   2164	2172	2270	org/json/JSONException
        //   2175	2198	2270	org/json/JSONException
        //   2201	2210	2270	org/json/JSONException
        //   2213	2219	2270	org/json/JSONException
        //   2222	2239	2270	org/json/JSONException
        //   2242	2256	2270	org/json/JSONException
        //   2295	2300	2270	org/json/JSONException
        //   2303	2308	2270	org/json/JSONException
        //   2311	2318	2270	org/json/JSONException
        //   2321	2329	2270	org/json/JSONException
        //   2332	2345	2270	org/json/JSONException
        //   2348	2357	2270	org/json/JSONException
        //   2360	2366	2270	org/json/JSONException
        //   2369	2374	2270	org/json/JSONException
        //   2377	2382	2270	org/json/JSONException
        //   2385	2392	2270	org/json/JSONException
        //   2395	2404	2270	org/json/JSONException
        //   2407	2420	2270	org/json/JSONException
        //   2423	2429	2270	org/json/JSONException
        }

public String Tin_Chottien_xien(String paramString) throws JSONException {
        String str1 = "DiemChuyen";
        String str2 = "AnNhan";
        String str3 = "KQNhan";
        new MainActivity();
        String str4 = MainActivity.Get_date();
        String str5 = MainActivity.Get_ngay();
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        Object object = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select * From tbl_kh_new Where ten_kh = '");
        stringBuilder.append(paramString);
        stringBuilder.append("'");
        Cursor cursor = GetData(stringBuilder.toString());
        cursor.moveToFirst();
        try {
        JSONObject jSONObject = new JSONObject();
        try {
        this(cursor.getString(5));
        this.json = jSONObject;
        this.caidat_tg = jSONObject.getJSONObject("caidat_tg");
        } catch (JSONException null) {}
        } catch (JSONException jSONException) {}
        jSONException.printStackTrace();
        }

public void Tinhtien(String paramString) throws JSONException {
        int j;
        String str = "";
        boolean bool = false;
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select * From KetQua WHERE ngay = '");
        stringBuilder.append(paramString);
        stringBuilder.append("'");
        Cursor cursor = GetData(stringBuilder.toString());
        cursor.moveToFirst();
        byte b = 2;
        while (true) {
        j = i;
        if (b < 29) {
        if (cursor.getString(b) == null) {
        j = 1;
        break;
        }
        b++;
        continue;
        }
        break;
        }
        if (!j) {
        String[][] arrayOfString = new String[1000][8];
        stringBuilder = new StringBuilder();
        stringBuilder.append("Select * From KetQua WHERE ngay = '");
        stringBuilder.append(paramString);
        stringBuilder.append("'");
        String str2 = stringBuilder.toString();
        Cursor cursor1 = GetData(str2);
        cursor1.moveToFirst();
        for (b = 0; b < arrayOfString.length; b++) {
        arrayOfString[b][0] = "";
        arrayOfString[b][1] = "";
        arrayOfString[b][2] = "";
        arrayOfString[b][3] = "";
        arrayOfString[b][4] = "";
        arrayOfString[b][5] = "";
        arrayOfString[b][6] = "";
        arrayOfString[b][7] = "";
        }
        QueryData("Delete FROM tbl_tinnhanS WHERE Length(nd_phantich) <5 ");
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append("Update tbl_soctS Set so_nhay = 0, ket_qua = 0 WHERE ngay_nhan = '");
        stringBuilder5.append(paramString);
        stringBuilder5.append("' AND the_loai <> 'tt' AND the_loai <> 'cn'");
        QueryData(stringBuilder5.toString());
        for (b = 2; b < 29; b++) {
        if (b > 1 && b < 12) {
        String[] arrayOfString1 = arrayOfString[Integer.parseInt(cursor1.getString(b).substring(3, 5))];
        stringBuilder5 = new StringBuilder();
        stringBuilder5.append(arrayOfString[Integer.parseInt(cursor1.getString(b).substring(3, 5))][0]);
        stringBuilder5.append("*");
        arrayOfString1[0] = stringBuilder5.toString();
        arrayOfString1 = arrayOfString[Integer.parseInt(cursor1.getString(b).substring(0, 2))];
        stringBuilder5 = new StringBuilder();
        stringBuilder5.append(arrayOfString[Integer.parseInt(cursor1.getString(b).substring(0, 2))][6]);
        stringBuilder5.append("*");
        arrayOfString1[6] = stringBuilder5.toString();
        stringBuilder5 = new StringBuilder();
        stringBuilder5.append("Update tbl_soctS Set so_nhay = so_nhay + 1 WHERE ngay_nhan = '");
        stringBuilder5.append(paramString);
        stringBuilder5.append("' AND the_loai = 'lo' AND so_chon = '");
        stringBuilder5.append(cursor1.getString(b).substring(3, 5));
        stringBuilder5.append("'");
        QueryData(stringBuilder5.toString());
        stringBuilder5 = new StringBuilder();
        stringBuilder5.append("Update tbl_soctS Set so_nhay = so_nhay + 1 WHERE ngay_nhan = '");
        stringBuilder5.append(paramString);
        stringBuilder5.append("' AND the_loai = 'loa' AND so_chon = '");
        stringBuilder5.append(cursor1.getString(b).substring(0, 2));
        stringBuilder5.append("'");
        QueryData(stringBuilder5.toString());
        } else if (b > 11 && b < 22) {
        String[] arrayOfString1 = arrayOfString[Integer.parseInt(cursor1.getString(b).substring(2, 4))];
        stringBuilder5 = new StringBuilder();
        stringBuilder5.append(arrayOfString[Integer.parseInt(cursor1.getString(b).substring(2, 4))][0]);
        stringBuilder5.append("*");
        arrayOfString1[0] = stringBuilder5.toString();
        arrayOfString1 = arrayOfString[Integer.parseInt(cursor1.getString(b).substring(0, 2))];
        stringBuilder5 = new StringBuilder();
        stringBuilder5.append(arrayOfString[Integer.parseInt(cursor1.getString(b).substring(0, 2))][6]);
        stringBuilder5.append("*");
        arrayOfString1[6] = stringBuilder5.toString();
        stringBuilder5 = new StringBuilder();
        stringBuilder5.append("Update tbl_soctS Set so_nhay = so_nhay + 1 WHERE ngay_nhan = '");
        stringBuilder5.append(paramString);
        stringBuilder5.append("' AND the_loai = 'lo' AND so_chon = '");
        stringBuilder5.append(cursor1.getString(b).substring(2, 4));
        stringBuilder5.append("'");
        QueryData(stringBuilder5.toString());
        stringBuilder5 = new StringBuilder();
        stringBuilder5.append("Update tbl_soctS Set so_nhay = so_nhay + 1 WHERE ngay_nhan = '");
        stringBuilder5.append(paramString);
        stringBuilder5.append("' AND the_loai = 'loa' AND so_chon = '");
        stringBuilder5.append(cursor1.getString(b).substring(0, 2));
        stringBuilder5.append("'");
        QueryData(stringBuilder5.toString());
        } else if (b > 21 && b < 25) {
        String[] arrayOfString2 = arrayOfString[Integer.parseInt(cursor1.getString(b).substring(1, 3))];
        stringBuilder5 = new StringBuilder();
        stringBuilder5.append(arrayOfString[Integer.parseInt(cursor1.getString(b).substring(1, 3))][0]);
        stringBuilder5.append("*");
        arrayOfString2[0] = stringBuilder5.toString();
        String[] arrayOfString1 = arrayOfString[Integer.parseInt(cursor1.getString(b).substring(0, 2))];
        StringBuilder stringBuilder7 = new StringBuilder();
        stringBuilder7.append(arrayOfString[Integer.parseInt(cursor1.getString(b).substring(0, 2))][6]);
        stringBuilder7.append("*");
        arrayOfString1[6] = stringBuilder7.toString();
        StringBuilder stringBuilder6 = new StringBuilder();
        stringBuilder6.append("Update tbl_soctS Set so_nhay = so_nhay + 1 WHERE ngay_nhan = '");
        stringBuilder6.append(paramString);
        stringBuilder6.append("' AND the_loai = 'lo' AND so_chon = '");
        stringBuilder6.append(cursor1.getString(b).substring(1, 3));
        stringBuilder6.append("'");
        QueryData(stringBuilder6.toString());
        stringBuilder6 = new StringBuilder();
        stringBuilder6.append("Update tbl_soctS Set so_nhay = so_nhay + 1 WHERE ngay_nhan = '");
        stringBuilder6.append(paramString);
        stringBuilder6.append("' AND the_loai = 'loa' AND so_chon = '");
        stringBuilder6.append(cursor1.getString(b).substring(0, 2));
        stringBuilder6.append("'");
        QueryData(stringBuilder6.toString());
        } else {
        String[] arrayOfString1 = arrayOfString[Integer.parseInt(cursor1.getString(b))];
        StringBuilder stringBuilder7 = new StringBuilder();
        stringBuilder7.append(arrayOfString[Integer.parseInt(cursor1.getString(b))][0]);
        stringBuilder7.append("*");
        arrayOfString1[0] = stringBuilder7.toString();
        String[] arrayOfString2 = arrayOfString[Integer.parseInt(cursor1.getString(b).substring(0, 2))];
        StringBuilder stringBuilder6 = new StringBuilder();
        stringBuilder6.append(arrayOfString[Integer.parseInt(cursor1.getString(b).substring(0, 2))][6]);
        stringBuilder6.append("*");
        arrayOfString2[6] = stringBuilder6.toString();
        stringBuilder6 = new StringBuilder();
        stringBuilder6.append("Update tbl_soctS Set so_nhay = so_nhay + 1 WHERE ngay_nhan = '");
        stringBuilder6.append(paramString);
        stringBuilder6.append("' AND the_loai = 'lo' AND so_chon = '");
        stringBuilder6.append(cursor1.getString(b));
        stringBuilder6.append("'");
        QueryData(stringBuilder6.toString());
        stringBuilder6 = new StringBuilder();
        stringBuilder6.append("Update tbl_soctS Set so_nhay = so_nhay + 1 WHERE ngay_nhan = '");
        stringBuilder6.append(paramString);
        stringBuilder6.append("' AND the_loai = 'loa' AND so_chon = '");
        stringBuilder6.append(cursor1.getString(b));
        stringBuilder6.append("'");
        QueryData(stringBuilder6.toString());
        }
        if (b == 2) {
        arrayOfString[Integer.parseInt(cursor1.getString(b).substring(0, 2))][1] = "*";
        arrayOfString[Integer.parseInt(cursor1.getString(b).substring(3, 5))][2] = "*";
        String str5 = cursor1.getString(b).substring(2, 5);
        StringBuilder stringBuilder6 = new StringBuilder();
        stringBuilder6.append("Update tbl_soctS Set so_nhay = 1 WHERE ngay_nhan = '");
        stringBuilder6.append(paramString);
        stringBuilder6.append("' AND the_loai = 'bc' AND so_chon = '");
        stringBuilder6.append(str5);
        stringBuilder6.append("'");
        QueryData(stringBuilder6.toString());
        arrayOfString[Integer.parseInt(cursor1.getString(b).substring(2, 5))][5] = "*";
        String str4 = cursor1.getString(b).substring(0, 3);
        StringBuilder stringBuilder7 = new StringBuilder();
        stringBuilder7.append("Update tbl_soctS Set so_nhay = 1 WHERE ngay_nhan = '");
        stringBuilder7.append(paramString);
        stringBuilder7.append("' AND the_loai = 'bca' AND so_chon = '");
        stringBuilder7.append(str4);
        stringBuilder7.append("'");
        QueryData(stringBuilder7.toString());
        arrayOfString[Integer.parseInt(cursor1.getString(b).substring(0, 3))][7] = "*";
        if (MainActivity.jSon_Setting.getInt("ap_man") > 0)
        for (i = 0; i < 10; i++) {
        stringBuilder7 = new StringBuilder();
        stringBuilder7.append(i);
        stringBuilder7.append(cursor1.getString(b).substring(3, 5));
        if (Integer.parseInt(stringBuilder7.toString()) != Integer.parseInt(str5)) {
        stringBuilder7 = new StringBuilder();
        stringBuilder7.append("Update tbl_soctS Set so_nhay = 1, lan_an = ");
        stringBuilder7.append(MainActivity.jSon_Setting.getInt("ap_man") * 1000);
        stringBuilder7.append(" WHERE ngay_nhan = '");
        stringBuilder7.append(paramString);
        stringBuilder7.append("' AND the_loai = 'bc' AND so_chon = '");
        stringBuilder7.append(i);
        stringBuilder7.append(cursor1.getString(b).substring(3, 5));
        stringBuilder7.append("'");
        QueryData(stringBuilder7.toString());
        }
        stringBuilder7 = new StringBuilder();
        stringBuilder7.append(i);
        stringBuilder7.append(cursor1.getString(b).substring(3, 5));
        arrayOfString[Integer.parseInt(stringBuilder7.toString())][5] = "*";
        }
        str4 = str5;
        }
        if (b == 3) {
        arrayOfString[Integer.parseInt(cursor1.getString(b).substring(0, 2))][3] = "*";
        arrayOfString[Integer.parseInt(cursor1.getString(b).substring(3, 5))][4] = "*";
        }
        }
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Update tbl_soctS Set so_nhay = 0, ket_qua = -tong_tien WHERE ngay_nhan = '");
        stringBuilder1.append(paramString);
        stringBuilder1.append("' AND the_loai = 'dea' AND so_chon <> '");
        stringBuilder1.append(cursor1.getString(2).substring(0, 2));
        stringBuilder1.append("' AND type_kh = 1");
        QueryData(stringBuilder1.toString());
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Update tbl_soctS Set so_nhay = 1, ket_qua = diem * lan_an -tong_tien WHERE ngay_nhan = '");
        stringBuilder1.append(paramString);
        stringBuilder1.append("' AND the_loai = 'dea' AND so_chon ='");
        stringBuilder1.append(cursor1.getString(2).substring(0, 2));
        stringBuilder1.append("' AND type_kh = 1");
        QueryData(stringBuilder1.toString());
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Update tbl_soctS Set so_nhay = 0, ket_qua = tong_tien WHERE ngay_nhan = '");
        stringBuilder1.append(paramString);
        stringBuilder1.append("' AND the_loai = 'dea' AND so_chon <> '");
        stringBuilder1.append(cursor1.getString(2).substring(0, 2));
        stringBuilder1.append("' AND type_kh = 2");
        QueryData(stringBuilder1.toString());
        stringBuilder5 = new StringBuilder();
        stringBuilder5.append("Update tbl_soctS Set so_nhay = 1, ket_qua = -diem * lan_an +tong_tien WHERE ngay_nhan = '");
        stringBuilder5.append(paramString);
        stringBuilder5.append("' AND the_loai = 'dea' AND so_chon = '");
        String str3 = cursor1.getString(2);
        String str1 = str2;
        stringBuilder5.append(str3.substring(0, 2));
        stringBuilder5.append("' AND type_kh = 2");
        QueryData(stringBuilder5.toString());
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 0, ket_qua = -tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'deb' AND so_chon <> '");
        stringBuilder4.append(cursor1.getString(2).substring(3, 5));
        stringBuilder4.append("' AND type_kh = 1");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 1, ket_qua = diem * lan_an -tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'deb' AND so_chon ='");
        stringBuilder4.append(cursor1.getString(2).substring(3, 5));
        stringBuilder4.append("' AND type_kh = 1");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 0, ket_qua = tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'deb' AND so_chon <> '");
        stringBuilder4.append(cursor1.getString(2).substring(3, 5));
        stringBuilder4.append("' AND type_kh = 2");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 1, ket_qua = -diem * lan_an +tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'deb' AND so_chon = '");
        stringBuilder4.append(cursor1.getString(2).substring(3, 5));
        stringBuilder4.append("' AND type_kh = 2");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 0, ket_qua = -tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'dec' AND so_chon <> '");
        stringBuilder4.append(cursor1.getString(3).substring(0, 2));
        stringBuilder4.append("' AND type_kh = 1");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 1, ket_qua = diem * lan_an -tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'dec' AND so_chon ='");
        stringBuilder4.append(cursor1.getString(3).substring(0, 2));
        stringBuilder4.append("' AND type_kh = 1");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 0, ket_qua = tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'dec' AND so_chon <> '");
        stringBuilder4.append(cursor1.getString(3).substring(0, 2));
        stringBuilder4.append("' AND type_kh = 2");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 1, ket_qua = -diem * lan_an +tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'dec' AND so_chon = '");
        stringBuilder4.append(cursor1.getString(3).substring(0, 2));
        stringBuilder4.append("' AND type_kh = 2");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 0, ket_qua = -tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'ded' AND so_chon <> '");
        stringBuilder4.append(cursor1.getString(3).substring(3, 5));
        stringBuilder4.append("' AND type_kh = 1");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 1, ket_qua = diem * lan_an -tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'ded' AND so_chon ='");
        stringBuilder4.append(cursor1.getString(3).substring(3, 5));
        stringBuilder4.append("' AND type_kh = 1");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 0, ket_qua = tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'ded' AND so_chon <> '");
        stringBuilder4.append(cursor1.getString(3).substring(3, 5));
        stringBuilder4.append("' AND type_kh = 2");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 1, ket_qua = -diem * lan_an +tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'ded' AND so_chon = '");
        stringBuilder4.append(cursor1.getString(3).substring(3, 5));
        stringBuilder4.append("' AND type_kh = 2");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 0, ket_qua = -tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'det' AND so_chon <> '");
        stringBuilder4.append(cursor1.getString(2).substring(3, 5));
        stringBuilder4.append("' AND type_kh = 1");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 1, ket_qua = diem * lan_an-tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'det' AND so_chon ='");
        stringBuilder4.append(cursor1.getString(2).substring(3, 5));
        stringBuilder4.append("' AND type_kh = 1");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 0, ket_qua = tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'det' AND so_chon <> '");
        stringBuilder4.append(cursor1.getString(2).substring(3, 5));
        stringBuilder4.append("' AND type_kh = 2");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Update tbl_soctS Set so_nhay = 1, ket_qua = -diem * lan_an+tong_tien WHERE ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND the_loai = 'det' AND so_chon = '");
        stringBuilder4.append(cursor1.getString(2).substring(3, 5));
        stringBuilder4.append("' AND type_kh = 2");
        QueryData(stringBuilder4.toString());
        stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Select * From tbl_soctS Where ngay_nhan = '");
        stringBuilder4.append(paramString);
        stringBuilder4.append("' AND (the_loai = 'xn' OR the_loai = 'xi')");
        Cursor cursor3 = GetData(stringBuilder4.toString());
        while (true) {
        while (true)
        break;
        if (j == 1) {
        stringBuilder5 = new StringBuilder();
        stringBuilder5.append("Update tbl_soctS Set so_nhay = 1 WHERE ID = ");
        stringBuilder5.append(cursor3.getString(0));
        QueryData(stringBuilder5.toString());
        }
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Select * From tbl_soctS Where ngay_nhan = '");
        stringBuilder3.append(paramString);
        stringBuilder3.append("' AND the_loai = 'xia'");
        Cursor cursor2 = GetData(stringBuilder3.toString());
        while (cursor2.moveToNext()) {
        String[] arrayOfString1 = cursor2.getString(7).split(",");
        i = 1;
        b = 0;
        while (true) {
        j = i;
        if (b < arrayOfString1.length) {
        if (arrayOfString[Integer.parseInt(arrayOfString1[b])][6].length() == 0) {
        j = 0;
        break;
        }
        b++;
        continue;
        }
        break;
        }
        if (j == 1) {
        StringBuilder stringBuilder6 = new StringBuilder();
        stringBuilder6.append("Update tbl_soctS Set so_nhay = 1 WHERE ID = ");
        stringBuilder6.append(cursor2.getString(0));
        QueryData(stringBuilder6.toString());
        }
        }
        if (MainActivity.jSon_Setting.getInt("tra_thuong_lo") > 0) {
        j = MainActivity.jSon_Setting.getInt("tra_thuong_lo") + 1;
        StringBuilder stringBuilder6 = new StringBuilder();
        stringBuilder6.append("Update tbl_soctS Set so_nhay = ");
        stringBuilder6.append(j);
        stringBuilder6.append(" Where so_nhay > ");
        stringBuilder6.append(j);
        QueryData(stringBuilder6.toString());
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Update tbl_soctS set ket_qua = diem * lan_an * so_nhay - tong_tien WHERE ngay_nhan = '");
        stringBuilder2.append(paramString);
        stringBuilder2.append("' AND type_kh = 1 AND the_loai <> 'tt' AND the_loai <> 'cn'");
        QueryData(stringBuilder2.toString());
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Update tbl_soctS set ket_qua = -diem * lan_an * so_nhay + tong_tien WHERE ngay_nhan = '");
        stringBuilder2.append(paramString);
        stringBuilder2.append("' AND type_kh = 2 AND the_loai <> 'tt' AND the_loai <> 'cn'");
        QueryData(stringBuilder2.toString());
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("UPDATE tbl_tinnhanS set tinh_tien = 0 Where ngay_nhan = '");
        stringBuilder2.append(paramString);
        stringBuilder2.append("'");
        QueryData(stringBuilder2.toString());
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Select * From tbl_tinnhanS Where ngay_nhan = '");
        stringBuilder2.append(paramString);
        stringBuilder2.append("' AND tinh_tien = 0 AND phat_hien_loi = 'ok'");
        Cursor cursor4 = GetData(stringBuilder2.toString());
        while (cursor4.moveToNext()) {
        if (cursor4.getInt(12) == 0) {
        paramString = cursor4.getString(10);
        str1 = paramString;
        if (paramString.indexOf("B") == 0)
        str1 = paramString.substring(paramString.indexOf("\n") + 1);
        for (j = 0; j < 9; j++)
        str1 = str1.replaceAll("\\*", "");
        paramString = "";
        b = 0;
        j = -1;
        while (true) {
        i = str1.indexOf("\n", j + 1);
        j = i;
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Update tbl_tinnhanS Set nd_phantich ='");
        stringBuilder2.append(paramString);
        stringBuilder2.append("', tinh_tien = 1 WHERE ID = ");
        stringBuilder2.append(cursor4.getString(0));
        QueryData(stringBuilder2.toString());
        }
        }
        }
        if (cursor != null)
        cursor.close();
        if (cursor4 != null)
        cursor4.close();
        }
        }

public String TraCang(String paramString, int paramInt) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date());
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
        simpleDateFormat1.setTimeZone(TimeZone.getDefault());
        simpleDateFormat2.setTimeZone(TimeZone.getDefault());
        String str1 = simpleDateFormat1.format(calendar1.getTime());
        String str2 = "";
        JSONObject jSONObject1 = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        Calendar calendar2 = calendar1;
        SimpleDateFormat simpleDateFormat3 = simpleDateFormat1;
        try {
        StringBuilder stringBuilder = new StringBuilder();
        Calendar calendar10 = calendar1;
        SimpleDateFormat simpleDateFormat12 = simpleDateFormat1;
        this();
        Calendar calendar9 = calendar1;
        SimpleDateFormat simpleDateFormat11 = simpleDateFormat1;
        stringBuilder.append("Select the_loai, so_chon, Sum(diem_ton *(type_kh = 1)) as Nhan, Sum(diem_ton *(type_kh = 2)) As Tra \nFROM tbl_soctS Where ten_kh = '");
        Calendar calendar8 = calendar1;
        SimpleDateFormat simpleDateFormat10 = simpleDateFormat1;
        stringBuilder.append(paramString);
        Calendar calendar7 = calendar1;
        SimpleDateFormat simpleDateFormat9 = simpleDateFormat1;
        stringBuilder.append("' AND ngay_nhan = '");
        Calendar calendar6 = calendar1;
        SimpleDateFormat simpleDateFormat8 = simpleDateFormat1;
        stringBuilder.append(str1);
        Calendar calendar5 = calendar1;
        SimpleDateFormat simpleDateFormat7 = simpleDateFormat1;
        stringBuilder.append("' AND the_loai = 'bc' Group by so_chon Order by so_chon");
        Calendar calendar4 = calendar1;
        SimpleDateFormat simpleDateFormat6 = simpleDateFormat1;
        String str = stringBuilder.toString();
        Calendar calendar3 = calendar1;
        SimpleDateFormat simpleDateFormat5 = simpleDateFormat1;
        Cursor cursor = GetData(str);
        SimpleDateFormat simpleDateFormat4 = simpleDateFormat1;
        while (true) {
        Calendar calendar = calendar1;
        SimpleDateFormat simpleDateFormat = simpleDateFormat4;
        boolean bool = cursor.moveToNext();
        String str4 = "So_chon";
        if (bool) {
        try {
        jSONObject2.put("So_chon", cursor.getString(1));
        jSONObject2.put("Da_nhan", cursor.getInt(2));
        jSONObject2.put("Da_tra", cursor.getInt(3));
        try {
        jSONObject2.put("Khong_Tien", paramInt);
        jSONObject2.put("Se_tra", jSONObject2.getInt("Da_nhan") - jSONObject2.getInt("Da_tra") - jSONObject2.getInt("Khong_Tien"));
        if (jSONObject2.getInt("Se_tra") > 0)
        jSONObject1.put(cursor.getString(1), jSONObject2.toString());
        continue;
        } catch (JSONException null) {}
        } catch (JSONException null) {}
        } else {
        try {
        if (jSONObject1.length() > 0) {
        Iterator<String> iterator = jSONObject1.keys();
        ArrayList<Calendar> arrayList = new ArrayList();
        this();
        JSONObject jSONObject = jSONObject2;
        try {
        while (true) {
        bool = iterator.hasNext();
        if (bool) {
        String str9 = iterator.next();
        try {
        JSONObject jSONObject4 = new JSONObject();
        try {
        this(jSONObject1.getString(str9));
        jSONObject = jSONObject4;
        continue;
        } catch (JSONException null) {}
        } catch (JSONException jSONException1) {}
        jSONException1.printStackTrace();
        continue;
        }
        Comparator<JSONObject> comparator = new Comparator<JSONObject>() {
public int compare(JSONObject param1JSONObject1, JSONObject param1JSONObject2) {
        Integer integer1;
        Integer integer2;
        Integer integer3 = Integer.valueOf(0);
        Integer integer4 = Integer.valueOf(0);
        try {
        integer1 = Integer.valueOf(param1JSONObject1.getInt("Se_tra"));
        integer3 = integer1;
        int i = param1JSONObject2.getInt("Se_tra");
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
        paramInt = 0;
        String str8 = "";
        byte b = 0;
        Cursor cursor1 = cursor;
        String str7 = str;
        JSONObject jSONObject3 = jSONObject;
        String str6 = str2;
        str2 = str1;
        String str5 = str8;
        try {
        while (true) {
        int i = arrayList.size();
        b++;
        }
        } catch (JSONException null) {}
        break;
        arrayList.add(calendar1);
        calendar11 = calendar1;
        }
        break;
        } catch (JSONException null) {
        String str5 = str2;
        }
        } else {
        return str2;
        }
        } catch (JSONException null) {
        String str5 = str2;
        }
        jSONException.printStackTrace();
        }
        String str3 = str2;
        break;
        }
        } catch (JSONException jSONException) {
        String str = str2;
        }
        jSONException.printStackTrace();
        }

public String TraDe(String paramString1, String paramString2) {
        // Byte code:
        //   0: invokestatic getInstance : ()Ljava/util/Calendar;
        //   3: astore_3
        //   4: aload_3
        //   5: new java/util/Date
        //   8: dup
        //   9: invokespecial <init> : ()V
        //   12: invokevirtual setTime : (Ljava/util/Date;)V
        //   15: new java/text/SimpleDateFormat
        //   18: dup
        //   19: ldc_w 'yyyy-MM-dd'
        //   22: invokespecial <init> : (Ljava/lang/String;)V
        //   25: astore #4
        //   27: new java/text/SimpleDateFormat
        //   30: dup
        //   31: ldc_w 'HH:mm:ss'
        //   34: invokespecial <init> : (Ljava/lang/String;)V
        //   37: astore #5
        //   39: aload #4
        //   41: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   44: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   47: aload #5
        //   49: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   52: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   55: aload #4
        //   57: aload_3
        //   58: invokevirtual getTime : ()Ljava/util/Date;
        //   61: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
        //   64: astore #6
        //   66: ldc ''
        //   68: astore_3
        //   69: new org/json/JSONObject
        //   72: dup
        //   73: invokespecial <init> : ()V
        //   76: astore #7
        //   78: new org/json/JSONObject
        //   81: dup
        //   82: invokespecial <init> : ()V
        //   85: astore #8
        //   87: ldc_w 'De'
        //   90: astore #9
        //   92: ldc 'deb'
        //   94: astore #10
        //   96: ldc 'de dit db'
        //   98: astore #11
        //   100: aload #9
        //   102: astore #12
        //   104: aload #10
        //   106: astore #13
        //   108: aload #11
        //   110: astore #14
        //   112: aload_0
        //   113: getfield caidat_tg : Lorg/json/JSONObject;
        //   116: ldc_w 'khach_de'
        //   119: invokevirtual getInt : (Ljava/lang/String;)I
        //   122: iconst_1
        //   123: if_icmpne -> 139
        //   126: ldc_w 'Det'
        //   129: astore #12
        //   131: ldc 'det'
        //   133: astore #13
        //   135: ldc 'de 8'
        //   137: astore #14
        //   139: aload #14
        //   141: astore #11
        //   143: goto -> 156
        //   146: astore #14
        //   148: aload #10
        //   150: astore #13
        //   152: aload #9
        //   154: astore #12
        //   156: new org/json/JSONObject
        //   159: astore #9
        //   161: aload #9
        //   163: aload_2
        //   164: invokespecial <init> : (Ljava/lang/String;)V
        //   167: new java/lang/StringBuilder
        //   170: astore_2
        //   171: aload_2
        //   172: invokespecial <init> : ()V
        //   175: aload #4
        //   177: astore #10
        //   179: aload #5
        //   181: astore #10
        //   183: aload #6
        //   185: astore #10
        //   187: aload_3
        //   188: astore #10
        //   190: aload_2
        //   191: ldc_w 'Select the_loai, so_chon, Sum(diem_ton*(type_kh = 1)) as Nhan, Sum(diem_ton *(type_kh = 2)) As Tra \\nFROM tbl_soctS Where ten_kh = ''
        //   194: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   197: pop
        //   198: aload #4
        //   200: astore #10
        //   202: aload #5
        //   204: astore #10
        //   206: aload #6
        //   208: astore #10
        //   210: aload_3
        //   211: astore #10
        //   213: aload_2
        //   214: aload_1
        //   215: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   218: pop
        //   219: aload #4
        //   221: astore #10
        //   223: aload #5
        //   225: astore #10
        //   227: aload #6
        //   229: astore #10
        //   231: aload_3
        //   232: astore #10
        //   234: aload_2
        //   235: ldc_w '' AND ngay_nhan = ''
        //   238: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   241: pop
        //   242: aload #4
        //   244: astore #10
        //   246: aload #5
        //   248: astore #10
        //   250: aload #6
        //   252: astore #10
        //   254: aload_3
        //   255: astore #10
        //   257: aload_2
        //   258: aload #6
        //   260: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   263: pop
        //   264: aload #4
        //   266: astore #10
        //   268: aload #5
        //   270: astore #10
        //   272: aload #6
        //   274: astore #10
        //   276: aload_3
        //   277: astore #10
        //   279: aload_2
        //   280: ldc_w '' AND the_loai = ''
        //   283: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   286: pop
        //   287: aload #4
        //   289: astore #10
        //   291: aload #5
        //   293: astore #10
        //   295: aload #6
        //   297: astore #10
        //   299: aload_3
        //   300: astore #10
        //   302: aload_2
        //   303: aload #13
        //   305: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   308: pop
        //   309: aload #4
        //   311: astore #10
        //   313: aload #5
        //   315: astore #10
        //   317: aload #6
        //   319: astore #10
        //   321: aload_3
        //   322: astore #10
        //   324: aload_2
        //   325: ldc_w '' Group by so_chon Order by so_chon'
        //   328: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   331: pop
        //   332: aload #4
        //   334: astore #10
        //   336: aload #5
        //   338: astore #10
        //   340: aload #6
        //   342: astore #10
        //   344: aload_3
        //   345: astore #10
        //   347: aload_2
        //   348: invokevirtual toString : ()Ljava/lang/String;
        //   351: astore #15
        //   353: aload #4
        //   355: astore #10
        //   357: aload #5
        //   359: astore #10
        //   361: aload #6
        //   363: astore #10
        //   365: aload_3
        //   366: astore #10
        //   368: aload_0
        //   369: aload #15
        //   371: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   374: astore_2
        //   375: aload_3
        //   376: astore_1
        //   377: aload #5
        //   379: astore_3
        //   380: aload #4
        //   382: astore #14
        //   384: aload #15
        //   386: astore #4
        //   388: aload #14
        //   390: astore #10
        //   392: aload_3
        //   393: astore #10
        //   395: aload #6
        //   397: astore #10
        //   399: aload_1
        //   400: astore #10
        //   402: aload_2
        //   403: invokeinterface moveToNext : ()Z
        //   408: istore #16
        //   410: ldc_w 'So_chon'
        //   413: astore #5
        //   415: iload #16
        //   417: ifeq -> 598
        //   420: aload #9
        //   422: aload_2
        //   423: iconst_1
        //   424: invokeinterface getString : (I)Ljava/lang/String;
        //   429: invokevirtual has : (Ljava/lang/String;)Z
        //   432: istore #16
        //   434: iload #16
        //   436: ifeq -> 591
        //   439: aload_1
        //   440: astore #10
        //   442: aload #8
        //   444: ldc_w 'So_chon'
        //   447: aload_2
        //   448: iconst_1
        //   449: invokeinterface getString : (I)Ljava/lang/String;
        //   454: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   457: pop
        //   458: aload #8
        //   460: ldc_w 'Da_nhan'
        //   463: aload_2
        //   464: iconst_2
        //   465: invokeinterface getInt : (I)I
        //   470: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   473: pop
        //   474: aload #8
        //   476: ldc_w 'Da_tra'
        //   479: aload_2
        //   480: iconst_3
        //   481: invokeinterface getInt : (I)I
        //   486: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   489: pop
        //   490: aload #8
        //   492: ldc_w 'Khong_Tien'
        //   495: aload #9
        //   497: aload_2
        //   498: iconst_1
        //   499: invokeinterface getString : (I)Ljava/lang/String;
        //   504: invokevirtual getInt : (Ljava/lang/String;)I
        //   507: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   510: pop
        //   511: aload #8
        //   513: ldc_w 'Se_tra'
        //   516: aload #8
        //   518: ldc_w 'Da_nhan'
        //   521: invokevirtual getInt : (Ljava/lang/String;)I
        //   524: aload #8
        //   526: ldc_w 'Da_tra'
        //   529: invokevirtual getInt : (Ljava/lang/String;)I
        //   532: isub
        //   533: aload #8
        //   535: ldc_w 'Khong_Tien'
        //   538: invokevirtual getInt : (Ljava/lang/String;)I
        //   541: isub
        //   542: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   545: pop
        //   546: aload #8
        //   548: ldc_w 'Se_tra'
        //   551: invokevirtual getInt : (Ljava/lang/String;)I
        //   554: ifle -> 581
        //   557: aload #7
        //   559: aload_2
        //   560: iconst_1
        //   561: invokeinterface getString : (I)Ljava/lang/String;
        //   566: aload #8
        //   568: invokevirtual toString : ()Ljava/lang/String;
        //   571: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   574: pop
        //   575: aload #10
        //   577: astore_1
        //   578: goto -> 388
        //   581: aload #10
        //   583: astore_1
        //   584: goto -> 388
        //   587: astore_2
        //   588: goto -> 1465
        //   591: goto -> 388
        //   594: astore_2
        //   595: goto -> 1465
        //   598: aload_2
        //   599: astore #4
        //   601: aload #9
        //   603: astore #6
        //   605: aload_1
        //   606: astore_2
        //   607: aload #7
        //   609: invokevirtual length : ()I
        //   612: ifle -> 1440
        //   615: aload #7
        //   617: invokevirtual keys : ()Ljava/util/Iterator;
        //   620: astore #15
        //   622: new java/util/ArrayList
        //   625: astore #10
        //   627: aload #10
        //   629: invokespecial <init> : ()V
        //   632: aload #8
        //   634: astore_3
        //   635: aload #15
        //   637: invokeinterface hasNext : ()Z
        //   642: istore #16
        //   644: iload #16
        //   646: ifeq -> 713
        //   649: aload #15
        //   651: invokeinterface next : ()Ljava/lang/Object;
        //   656: checkcast java/lang/String
        //   659: astore #8
        //   661: aload_3
        //   662: astore #14
        //   664: new org/json/JSONObject
        //   667: astore #9
        //   669: aload_3
        //   670: astore #14
        //   672: aload #9
        //   674: aload #7
        //   676: aload #8
        //   678: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   681: invokespecial <init> : (Ljava/lang/String;)V
        //   684: aload #9
        //   686: astore_3
        //   687: aload_3
        //   688: astore #14
        //   690: aload #10
        //   692: aload_3
        //   693: invokeinterface add : (Ljava/lang/Object;)Z
        //   698: pop
        //   699: goto -> 710
        //   702: astore_3
        //   703: aload_3
        //   704: invokevirtual printStackTrace : ()V
        //   707: aload #14
        //   709: astore_3
        //   710: goto -> 635
        //   713: new tamhoang/ldpro4/data/Database$2
        //   716: astore_1
        //   717: aload_1
        //   718: aload_0
        //   719: invokespecial <init> : (Ltamhoang/ldpro4/data/Database;)V
        //   722: aload #10
        //   724: aload_1
        //   725: invokestatic sort : (Ljava/util/List;Ljava/util/Comparator;)V
        //   728: iconst_0
        //   729: istore #17
        //   731: ldc ''
        //   733: astore #8
        //   735: iconst_0
        //   736: istore #18
        //   738: aload_2
        //   739: astore_1
        //   740: aload #7
        //   742: astore #14
        //   744: aload #4
        //   746: astore #9
        //   748: aload #5
        //   750: astore #4
        //   752: aload #8
        //   754: astore_2
        //   755: aload #10
        //   757: invokeinterface size : ()I
        //   762: istore #19
        //   764: iload #18
        //   766: iload #19
        //   768: if_icmpge -> 1146
        //   771: aload #10
        //   773: iload #18
        //   775: invokeinterface get : (I)Ljava/lang/Object;
        //   780: checkcast org/json/JSONObject
        //   783: astore #7
        //   785: iload #17
        //   787: aload #7
        //   789: ldc_w 'Se_tra'
        //   792: invokevirtual getInt : (Ljava/lang/String;)I
        //   795: if_icmple -> 1081
        //   798: new org/json/JSONObject
        //   801: astore #15
        //   803: aload #15
        //   805: invokespecial <init> : ()V
        //   808: aload_2
        //   809: ldc_w ','
        //   812: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   815: astore #5
        //   817: new java/lang/StringBuilder
        //   820: astore #8
        //   822: aload #8
        //   824: invokespecial <init> : ()V
        //   827: aload #8
        //   829: aload_2
        //   830: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   833: pop
        //   834: aload #8
        //   836: ldc_w 'x'
        //   839: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   842: pop
        //   843: aload #8
        //   845: iload #17
        //   847: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   850: pop
        //   851: aload #8
        //   853: ldc_w 'n'
        //   856: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   859: pop
        //   860: aload #15
        //   862: ldc_w 'du_lieu'
        //   865: aload #8
        //   867: invokevirtual toString : ()Ljava/lang/String;
        //   870: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   873: pop
        //   874: aload #15
        //   876: ldc_w 'the_loai'
        //   879: aload #11
        //   881: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   884: pop
        //   885: aload #15
        //   887: ldc_w 'dan_so'
        //   890: aload_2
        //   891: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   894: pop
        //   895: aload #15
        //   897: ldc_w 'so_tien'
        //   900: iload #17
        //   902: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   905: pop
        //   906: aload #15
        //   908: ldc_w 'so_luong'
        //   911: aload #5
        //   913: arraylength
        //   914: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   917: pop
        //   918: aload_0
        //   919: getfield json_Tralai : Lorg/json/JSONObject;
        //   922: aload_0
        //   923: getfield json_Tralai : Lorg/json/JSONObject;
        //   926: invokevirtual length : ()I
        //   929: iconst_1
        //   930: iadd
        //   931: invokestatic valueOf : (I)Ljava/lang/String;
        //   934: aload #15
        //   936: invokevirtual toString : ()Ljava/lang/String;
        //   939: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   942: pop
        //   943: new java/lang/StringBuilder
        //   946: astore #5
        //   948: aload #5
        //   950: invokespecial <init> : ()V
        //   953: aload #5
        //   955: aload_2
        //   956: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   959: pop
        //   960: aload #5
        //   962: ldc_w 'x'
        //   965: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   968: pop
        //   969: aload #5
        //   971: iload #17
        //   973: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   976: pop
        //   977: aload #5
        //   979: ldc_w 'n '
        //   982: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   985: pop
        //   986: aload #5
        //   988: invokevirtual toString : ()Ljava/lang/String;
        //   991: astore #5
        //   993: new java/lang/StringBuilder
        //   996: astore_2
        //   997: aload_2
        //   998: invokespecial <init> : ()V
        //   1001: aload_2
        //   1002: aload_1
        //   1003: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1006: pop
        //   1007: aload_2
        //   1008: aload #5
        //   1010: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1013: pop
        //   1014: aload_2
        //   1015: invokevirtual toString : ()Ljava/lang/String;
        //   1018: astore_2
        //   1019: new java/lang/StringBuilder
        //   1022: astore_1
        //   1023: aload_1
        //   1024: invokespecial <init> : ()V
        //   1027: aload_1
        //   1028: aload #7
        //   1030: aload #4
        //   1032: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1035: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1038: pop
        //   1039: aload_1
        //   1040: ldc_w ','
        //   1043: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1046: pop
        //   1047: aload_1
        //   1048: invokevirtual toString : ()Ljava/lang/String;
        //   1051: astore #5
        //   1053: aload #7
        //   1055: ldc_w 'Se_tra'
        //   1058: invokevirtual getInt : (Ljava/lang/String;)I
        //   1061: istore #17
        //   1063: aload_2
        //   1064: astore_1
        //   1065: aload #5
        //   1067: astore_2
        //   1068: goto -> 1136
        //   1071: astore_1
        //   1072: aload_2
        //   1073: astore_3
        //   1074: aload_1
        //   1075: astore_2
        //   1076: aload_3
        //   1077: astore_1
        //   1078: goto -> 1465
        //   1081: new java/lang/StringBuilder
        //   1084: astore #5
        //   1086: aload #5
        //   1088: invokespecial <init> : ()V
        //   1091: aload #5
        //   1093: aload_2
        //   1094: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1097: pop
        //   1098: aload #5
        //   1100: aload #7
        //   1102: aload #4
        //   1104: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1107: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1110: pop
        //   1111: aload #5
        //   1113: ldc_w ','
        //   1116: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1119: pop
        //   1120: aload #5
        //   1122: invokevirtual toString : ()Ljava/lang/String;
        //   1125: astore_2
        //   1126: aload #7
        //   1128: ldc_w 'Se_tra'
        //   1131: invokevirtual getInt : (Ljava/lang/String;)I
        //   1134: istore #17
        //   1136: iinc #18, 1
        //   1139: goto -> 755
        //   1142: astore_2
        //   1143: goto -> 1465
        //   1146: aload_1
        //   1147: astore_3
        //   1148: aload_2
        //   1149: invokevirtual length : ()I
        //   1152: istore #18
        //   1154: iload #18
        //   1156: ifle -> 1374
        //   1159: new org/json/JSONObject
        //   1162: astore #6
        //   1164: aload #6
        //   1166: invokespecial <init> : ()V
        //   1169: aload_2
        //   1170: ldc_w ','
        //   1173: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   1176: astore #9
        //   1178: new java/lang/StringBuilder
        //   1181: astore #14
        //   1183: aload #14
        //   1185: invokespecial <init> : ()V
        //   1188: aload #14
        //   1190: aload_2
        //   1191: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1194: pop
        //   1195: aload #14
        //   1197: ldc_w 'x'
        //   1200: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1203: pop
        //   1204: aload #14
        //   1206: iload #17
        //   1208: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1211: pop
        //   1212: aload #14
        //   1214: ldc_w 'n'
        //   1217: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1220: pop
        //   1221: aload #6
        //   1223: ldc_w 'du_lieu'
        //   1226: aload #14
        //   1228: invokevirtual toString : ()Ljava/lang/String;
        //   1231: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1234: pop
        //   1235: aload #6
        //   1237: ldc_w 'the_loai'
        //   1240: aload #11
        //   1242: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1245: pop
        //   1246: aload #6
        //   1248: ldc_w 'dan_so'
        //   1251: aload_2
        //   1252: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1255: pop
        //   1256: aload #6
        //   1258: ldc_w 'so_tien'
        //   1261: iload #17
        //   1263: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1266: pop
        //   1267: aload #6
        //   1269: ldc_w 'so_luong'
        //   1272: aload #9
        //   1274: arraylength
        //   1275: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1278: pop
        //   1279: iload #17
        //   1281: ifle -> 1374
        //   1284: aload_0
        //   1285: getfield json_Tralai : Lorg/json/JSONObject;
        //   1288: aload_0
        //   1289: getfield json_Tralai : Lorg/json/JSONObject;
        //   1292: invokevirtual length : ()I
        //   1295: iconst_1
        //   1296: iadd
        //   1297: invokestatic valueOf : (I)Ljava/lang/String;
        //   1300: aload #6
        //   1302: invokevirtual toString : ()Ljava/lang/String;
        //   1305: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1308: pop
        //   1309: new java/lang/StringBuilder
        //   1312: astore #14
        //   1314: aload #14
        //   1316: invokespecial <init> : ()V
        //   1319: aload #14
        //   1321: aload_3
        //   1322: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1325: pop
        //   1326: aload #14
        //   1328: aload_2
        //   1329: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1332: pop
        //   1333: aload #14
        //   1335: ldc_w 'x'
        //   1338: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1341: pop
        //   1342: aload #14
        //   1344: iload #17
        //   1346: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1349: pop
        //   1350: aload #14
        //   1352: ldc_w 'n '
        //   1355: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1358: pop
        //   1359: aload #14
        //   1361: invokevirtual toString : ()Ljava/lang/String;
        //   1364: astore_2
        //   1365: aload_2
        //   1366: astore_1
        //   1367: goto -> 1376
        //   1370: astore_2
        //   1371: goto -> 1465
        //   1374: aload_3
        //   1375: astore_1
        //   1376: new java/lang/StringBuilder
        //   1379: astore_2
        //   1380: aload_2
        //   1381: invokespecial <init> : ()V
        //   1384: aload_2
        //   1385: aload #12
        //   1387: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1390: pop
        //   1391: aload_2
        //   1392: ldc_w ': '
        //   1395: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1398: pop
        //   1399: aload_2
        //   1400: aload_1
        //   1401: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1404: pop
        //   1405: aload_2
        //   1406: invokevirtual toString : ()Ljava/lang/String;
        //   1409: astore_2
        //   1410: aload_2
        //   1411: areturn
        //   1412: astore_2
        //   1413: goto -> 1465
        //   1416: astore_2
        //   1417: goto -> 1465
        //   1420: astore_2
        //   1421: aload_3
        //   1422: astore_1
        //   1423: goto -> 1465
        //   1426: astore_2
        //   1427: goto -> 1465
        //   1430: astore_1
        //   1431: aload_2
        //   1432: astore_3
        //   1433: aload_1
        //   1434: astore_2
        //   1435: aload_3
        //   1436: astore_1
        //   1437: goto -> 1465
        //   1440: aload_2
        //   1441: astore_1
        //   1442: goto -> 1469
        //   1445: astore_1
        //   1446: aload_2
        //   1447: astore_3
        //   1448: aload_1
        //   1449: astore_2
        //   1450: aload_3
        //   1451: astore_1
        //   1452: goto -> 1465
        //   1455: astore_2
        //   1456: aload #10
        //   1458: astore_1
        //   1459: goto -> 1465
        //   1462: astore_2
        //   1463: aload_3
        //   1464: astore_1
        //   1465: aload_2
        //   1466: invokevirtual printStackTrace : ()V
        //   1469: aload_1
        //   1470: areturn
        // Exception table:
        //   from	to	target	type
        //   112	126	146	org/json/JSONException
        //   156	167	1462	org/json/JSONException
        //   167	175	1462	org/json/JSONException
        //   190	198	1455	org/json/JSONException
        //   213	219	1455	org/json/JSONException
        //   234	242	1455	org/json/JSONException
        //   257	264	1455	org/json/JSONException
        //   279	287	1455	org/json/JSONException
        //   302	309	1455	org/json/JSONException
        //   324	332	1455	org/json/JSONException
        //   347	353	1455	org/json/JSONException
        //   368	375	1455	org/json/JSONException
        //   402	410	1455	org/json/JSONException
        //   420	434	594	org/json/JSONException
        //   442	575	587	org/json/JSONException
        //   607	632	1445	org/json/JSONException
        //   635	644	1430	org/json/JSONException
        //   649	661	587	org/json/JSONException
        //   664	669	702	org/json/JSONException
        //   672	684	702	org/json/JSONException
        //   690	699	702	org/json/JSONException
        //   703	707	587	org/json/JSONException
        //   713	728	1430	org/json/JSONException
        //   755	764	1426	org/json/JSONException
        //   771	1001	1142	org/json/JSONException
        //   1001	1019	1370	org/json/JSONException
        //   1019	1063	1071	org/json/JSONException
        //   1081	1136	1370	org/json/JSONException
        //   1148	1154	1420	org/json/JSONException
        //   1159	1279	1370	org/json/JSONException
        //   1284	1365	1370	org/json/JSONException
        //   1376	1384	1416	org/json/JSONException
        //   1384	1410	1412	org/json/JSONException
        }

public String TraLo(String paramString1, String paramString2) {
        // Byte code:
        //   0: invokestatic getInstance : ()Ljava/util/Calendar;
        //   3: astore_3
        //   4: aload_3
        //   5: new java/util/Date
        //   8: dup
        //   9: invokespecial <init> : ()V
        //   12: invokevirtual setTime : (Ljava/util/Date;)V
        //   15: new java/text/SimpleDateFormat
        //   18: dup
        //   19: ldc_w 'yyyy-MM-dd'
        //   22: invokespecial <init> : (Ljava/lang/String;)V
        //   25: astore #4
        //   27: new java/text/SimpleDateFormat
        //   30: dup
        //   31: ldc_w 'HH:mm:ss'
        //   34: invokespecial <init> : (Ljava/lang/String;)V
        //   37: astore #5
        //   39: aload #4
        //   41: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   44: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   47: aload #5
        //   49: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   52: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   55: aload #4
        //   57: aload_3
        //   58: invokevirtual getTime : ()Ljava/util/Date;
        //   61: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
        //   64: astore #6
        //   66: ldc ''
        //   68: astore #7
        //   70: new org/json/JSONObject
        //   73: dup
        //   74: invokespecial <init> : ()V
        //   77: astore #8
        //   79: new org/json/JSONObject
        //   82: dup
        //   83: invokespecial <init> : ()V
        //   86: astore #9
        //   88: aload_3
        //   89: astore #10
        //   91: aload #4
        //   93: astore #10
        //   95: aload #5
        //   97: astore #10
        //   99: aload #6
        //   101: astore #10
        //   103: new org/json/JSONObject
        //   106: astore #11
        //   108: aload_3
        //   109: astore #10
        //   111: aload #4
        //   113: astore #10
        //   115: aload #5
        //   117: astore #10
        //   119: aload #6
        //   121: astore #10
        //   123: aload #11
        //   125: aload_2
        //   126: invokespecial <init> : (Ljava/lang/String;)V
        //   129: aload_3
        //   130: astore #10
        //   132: aload #4
        //   134: astore #10
        //   136: aload #5
        //   138: astore #10
        //   140: aload #6
        //   142: astore #10
        //   144: new java/lang/StringBuilder
        //   147: astore_2
        //   148: aload_3
        //   149: astore #10
        //   151: aload #4
        //   153: astore #10
        //   155: aload #5
        //   157: astore #10
        //   159: aload #6
        //   161: astore #10
        //   163: aload_2
        //   164: invokespecial <init> : ()V
        //   167: aload_3
        //   168: astore #10
        //   170: aload #4
        //   172: astore #10
        //   174: aload #5
        //   176: astore #10
        //   178: aload #6
        //   180: astore #10
        //   182: aload_2
        //   183: ldc_w 'Select the_loai, so_chon, Sum(diem_ton *(type_kh = 1)) as Nhan, Sum(diem_ton *(type_kh = 2)) As Tra \\nFROM tbl_soctS Where ten_kh = ''
        //   186: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   189: pop
        //   190: aload_3
        //   191: astore #10
        //   193: aload #4
        //   195: astore #10
        //   197: aload #5
        //   199: astore #10
        //   201: aload #6
        //   203: astore #10
        //   205: aload_2
        //   206: aload_1
        //   207: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   210: pop
        //   211: aload_3
        //   212: astore #10
        //   214: aload #4
        //   216: astore #10
        //   218: aload #5
        //   220: astore #10
        //   222: aload #6
        //   224: astore #10
        //   226: aload_2
        //   227: ldc_w '' AND ngay_nhan = ''
        //   230: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   233: pop
        //   234: aload_3
        //   235: astore #10
        //   237: aload #4
        //   239: astore #10
        //   241: aload #5
        //   243: astore #10
        //   245: aload #6
        //   247: astore #10
        //   249: aload_2
        //   250: aload #6
        //   252: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   255: pop
        //   256: aload_3
        //   257: astore #10
        //   259: aload #4
        //   261: astore #10
        //   263: aload #5
        //   265: astore #10
        //   267: aload #6
        //   269: astore #10
        //   271: aload_2
        //   272: ldc_w '' AND the_loai = 'lo' Group by so_chon Order by so_chon'
        //   275: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   278: pop
        //   279: aload_3
        //   280: astore #10
        //   282: aload #4
        //   284: astore #10
        //   286: aload #5
        //   288: astore #10
        //   290: aload #6
        //   292: astore #10
        //   294: aload_2
        //   295: invokevirtual toString : ()Ljava/lang/String;
        //   298: astore #12
        //   300: aload_3
        //   301: astore #10
        //   303: aload #4
        //   305: astore #10
        //   307: aload #5
        //   309: astore #10
        //   311: aload #6
        //   313: astore #10
        //   315: aload_0
        //   316: aload #12
        //   318: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   321: astore #13
        //   323: aload #5
        //   325: astore_1
        //   326: aload #4
        //   328: astore_2
        //   329: aload_3
        //   330: astore #10
        //   332: aload_2
        //   333: astore #10
        //   335: aload_1
        //   336: astore #10
        //   338: aload #6
        //   340: astore #10
        //   342: aload #13
        //   344: invokeinterface moveToNext : ()Z
        //   349: istore #14
        //   351: ldc_w 'So_chon'
        //   354: astore #15
        //   356: iload #14
        //   358: ifeq -> 531
        //   361: aload #11
        //   363: aload #13
        //   365: iconst_1
        //   366: invokeinterface getString : (I)Ljava/lang/String;
        //   371: invokevirtual has : (Ljava/lang/String;)Z
        //   374: ifeq -> 521
        //   377: aload #9
        //   379: ldc_w 'So_chon'
        //   382: aload #13
        //   384: iconst_1
        //   385: invokeinterface getString : (I)Ljava/lang/String;
        //   390: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   393: pop
        //   394: aload #9
        //   396: ldc_w 'Da_nhan'
        //   399: aload #13
        //   401: iconst_2
        //   402: invokeinterface getInt : (I)I
        //   407: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   410: pop
        //   411: aload #9
        //   413: ldc_w 'Da_tra'
        //   416: aload #13
        //   418: iconst_3
        //   419: invokeinterface getInt : (I)I
        //   424: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   427: pop
        //   428: aload #9
        //   430: ldc_w 'Khong_Tien'
        //   433: aload #11
        //   435: aload #13
        //   437: iconst_1
        //   438: invokeinterface getString : (I)Ljava/lang/String;
        //   443: invokevirtual getInt : (Ljava/lang/String;)I
        //   446: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   449: pop
        //   450: aload #9
        //   452: ldc_w 'Se_tra'
        //   455: aload #9
        //   457: ldc_w 'Da_nhan'
        //   460: invokevirtual getInt : (Ljava/lang/String;)I
        //   463: aload #9
        //   465: ldc_w 'Da_tra'
        //   468: invokevirtual getInt : (Ljava/lang/String;)I
        //   471: isub
        //   472: aload #9
        //   474: ldc_w 'Khong_Tien'
        //   477: invokevirtual getInt : (Ljava/lang/String;)I
        //   480: isub
        //   481: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   484: pop
        //   485: aload #9
        //   487: ldc_w 'Se_tra'
        //   490: invokevirtual getInt : (Ljava/lang/String;)I
        //   493: ifle -> 518
        //   496: aload #8
        //   498: aload #13
        //   500: iconst_1
        //   501: invokeinterface getString : (I)Ljava/lang/String;
        //   506: aload #9
        //   508: invokevirtual toString : ()Ljava/lang/String;
        //   511: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   514: pop
        //   515: goto -> 329
        //   518: goto -> 329
        //   521: goto -> 329
        //   524: astore_1
        //   525: aload #7
        //   527: astore_2
        //   528: goto -> 1392
        //   531: aload #8
        //   533: invokevirtual length : ()I
        //   536: ifle -> 1375
        //   539: aload #8
        //   541: invokevirtual keys : ()Ljava/util/Iterator;
        //   544: astore #16
        //   546: new java/util/ArrayList
        //   549: astore #17
        //   551: aload #17
        //   553: invokespecial <init> : ()V
        //   556: aload #9
        //   558: astore_1
        //   559: aload #16
        //   561: invokeinterface hasNext : ()Z
        //   566: istore #14
        //   568: iload #14
        //   570: ifeq -> 630
        //   573: aload #16
        //   575: invokeinterface next : ()Ljava/lang/Object;
        //   580: checkcast java/lang/String
        //   583: astore #6
        //   585: aload_1
        //   586: astore_2
        //   587: new org/json/JSONObject
        //   590: astore_3
        //   591: aload_1
        //   592: astore_2
        //   593: aload_3
        //   594: aload #8
        //   596: aload #6
        //   598: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   601: invokespecial <init> : (Ljava/lang/String;)V
        //   604: aload_3
        //   605: astore_1
        //   606: aload_1
        //   607: astore_2
        //   608: aload #17
        //   610: aload_1
        //   611: invokeinterface add : (Ljava/lang/Object;)Z
        //   616: pop
        //   617: goto -> 627
        //   620: astore_1
        //   621: aload_1
        //   622: invokevirtual printStackTrace : ()V
        //   625: aload_2
        //   626: astore_1
        //   627: goto -> 559
        //   630: new tamhoang/ldpro4/data/Database$3
        //   633: astore_2
        //   634: aload_2
        //   635: aload_0
        //   636: invokespecial <init> : (Ltamhoang/ldpro4/data/Database;)V
        //   639: aload #17
        //   641: aload_2
        //   642: invokestatic sort : (Ljava/util/List;Ljava/util/Comparator;)V
        //   645: iconst_0
        //   646: istore #18
        //   648: ldc ''
        //   650: astore #9
        //   652: iconst_0
        //   653: istore #19
        //   655: aload #13
        //   657: astore_3
        //   658: aload #12
        //   660: astore #6
        //   662: aload #11
        //   664: astore #4
        //   666: aload_1
        //   667: astore #10
        //   669: aload #8
        //   671: astore #5
        //   673: aload #7
        //   675: astore_2
        //   676: aload #9
        //   678: astore #11
        //   680: aload #15
        //   682: astore #8
        //   684: aload #17
        //   686: astore #7
        //   688: aload #16
        //   690: astore #17
        //   692: aload #7
        //   694: invokeinterface size : ()I
        //   699: istore #20
        //   701: iload #19
        //   703: iload #20
        //   705: if_icmpge -> 1111
        //   708: aload #7
        //   710: iload #19
        //   712: invokeinterface get : (I)Ljava/lang/Object;
        //   717: checkcast org/json/JSONObject
        //   720: astore #12
        //   722: iload #18
        //   724: aload #12
        //   726: ldc_w 'Se_tra'
        //   729: invokevirtual getInt : (Ljava/lang/String;)I
        //   732: if_icmple -> 1020
        //   735: new org/json/JSONObject
        //   738: astore_1
        //   739: aload_1
        //   740: invokespecial <init> : ()V
        //   743: aload #11
        //   745: ldc_w ','
        //   748: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   751: astore #9
        //   753: new java/lang/StringBuilder
        //   756: astore #13
        //   758: aload #13
        //   760: invokespecial <init> : ()V
        //   763: aload #13
        //   765: aload #11
        //   767: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   770: pop
        //   771: aload #13
        //   773: ldc_w 'x'
        //   776: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   779: pop
        //   780: aload #13
        //   782: iload #18
        //   784: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   787: pop
        //   788: aload #13
        //   790: ldc_w 'd'
        //   793: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   796: pop
        //   797: aload_1
        //   798: ldc_w 'du_lieu'
        //   801: aload #13
        //   803: invokevirtual toString : ()Ljava/lang/String;
        //   806: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   809: pop
        //   810: aload_1
        //   811: ldc_w 'the_loai'
        //   814: ldc 'lo'
        //   816: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   819: pop
        //   820: aload_1
        //   821: ldc_w 'dan_so'
        //   824: aload #11
        //   826: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   829: pop
        //   830: aload_1
        //   831: ldc_w 'so_tien'
        //   834: iload #18
        //   836: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   839: pop
        //   840: aload_1
        //   841: ldc_w 'so_luong'
        //   844: aload #9
        //   846: arraylength
        //   847: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   850: pop
        //   851: aload_0
        //   852: getfield json_Tralai : Lorg/json/JSONObject;
        //   855: aload_0
        //   856: getfield json_Tralai : Lorg/json/JSONObject;
        //   859: invokevirtual length : ()I
        //   862: iconst_1
        //   863: iadd
        //   864: invokestatic valueOf : (I)Ljava/lang/String;
        //   867: aload_1
        //   868: invokevirtual toString : ()Ljava/lang/String;
        //   871: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   874: pop
        //   875: new java/lang/StringBuilder
        //   878: astore_1
        //   879: aload_1
        //   880: invokespecial <init> : ()V
        //   883: aload_1
        //   884: aload #11
        //   886: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   889: pop
        //   890: aload_1
        //   891: ldc_w 'x'
        //   894: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   897: pop
        //   898: aload_1
        //   899: iload #18
        //   901: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   904: pop
        //   905: aload_1
        //   906: ldc_w 'd '
        //   909: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   912: pop
        //   913: aload_1
        //   914: invokevirtual toString : ()Ljava/lang/String;
        //   917: astore #13
        //   919: new java/lang/StringBuilder
        //   922: astore #11
        //   924: aload #11
        //   926: invokespecial <init> : ()V
        //   929: aload_2
        //   930: astore_1
        //   931: aload_1
        //   932: astore_2
        //   933: aload #11
        //   935: aload_1
        //   936: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   939: pop
        //   940: aload_1
        //   941: astore_2
        //   942: aload #11
        //   944: aload #13
        //   946: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   949: pop
        //   950: aload_1
        //   951: astore_2
        //   952: aload #11
        //   954: invokevirtual toString : ()Ljava/lang/String;
        //   957: astore_1
        //   958: new java/lang/StringBuilder
        //   961: astore_2
        //   962: aload_2
        //   963: invokespecial <init> : ()V
        //   966: aload_2
        //   967: aload #12
        //   969: aload #8
        //   971: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   974: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   977: pop
        //   978: aload_2
        //   979: ldc_w ','
        //   982: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   985: pop
        //   986: aload_2
        //   987: invokevirtual toString : ()Ljava/lang/String;
        //   990: astore #11
        //   992: aload #12
        //   994: ldc_w 'Se_tra'
        //   997: invokevirtual getInt : (Ljava/lang/String;)I
        //   1000: istore #18
        //   1002: aload_1
        //   1003: astore_2
        //   1004: aload #11
        //   1006: astore_1
        //   1007: goto -> 1098
        //   1010: astore_2
        //   1011: aload_1
        //   1012: astore_3
        //   1013: aload_2
        //   1014: astore_1
        //   1015: aload_3
        //   1016: astore_2
        //   1017: goto -> 1392
        //   1020: aload_2
        //   1021: astore_1
        //   1022: aload_1
        //   1023: astore_2
        //   1024: new java/lang/StringBuilder
        //   1027: astore #13
        //   1029: aload_1
        //   1030: astore_2
        //   1031: aload #13
        //   1033: invokespecial <init> : ()V
        //   1036: aload_1
        //   1037: astore_2
        //   1038: aload #13
        //   1040: aload #11
        //   1042: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1045: pop
        //   1046: aload_1
        //   1047: astore_2
        //   1048: aload #13
        //   1050: aload #12
        //   1052: aload #8
        //   1054: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1057: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1060: pop
        //   1061: aload_1
        //   1062: astore_2
        //   1063: aload #13
        //   1065: ldc_w ','
        //   1068: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1071: pop
        //   1072: aload_1
        //   1073: astore_2
        //   1074: aload #13
        //   1076: invokevirtual toString : ()Ljava/lang/String;
        //   1079: astore #11
        //   1081: aload_1
        //   1082: astore_2
        //   1083: aload #12
        //   1085: ldc_w 'Se_tra'
        //   1088: invokevirtual getInt : (Ljava/lang/String;)I
        //   1091: istore #18
        //   1093: aload_1
        //   1094: astore_2
        //   1095: aload #11
        //   1097: astore_1
        //   1098: iinc #19, 1
        //   1101: aload_1
        //   1102: astore #11
        //   1104: goto -> 692
        //   1107: astore_1
        //   1108: goto -> 1392
        //   1111: aload #11
        //   1113: invokevirtual length : ()I
        //   1116: ifle -> 1315
        //   1119: new org/json/JSONObject
        //   1122: astore #6
        //   1124: aload #6
        //   1126: invokespecial <init> : ()V
        //   1129: aload #11
        //   1131: ldc_w ','
        //   1134: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   1137: astore_3
        //   1138: new java/lang/StringBuilder
        //   1141: astore_1
        //   1142: aload_1
        //   1143: invokespecial <init> : ()V
        //   1146: aload_1
        //   1147: aload #11
        //   1149: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1152: pop
        //   1153: aload_1
        //   1154: ldc_w 'x'
        //   1157: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1160: pop
        //   1161: aload_1
        //   1162: iload #18
        //   1164: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1167: pop
        //   1168: aload_1
        //   1169: ldc_w 'd'
        //   1172: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1175: pop
        //   1176: aload #6
        //   1178: ldc_w 'du_lieu'
        //   1181: aload_1
        //   1182: invokevirtual toString : ()Ljava/lang/String;
        //   1185: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1188: pop
        //   1189: aload #6
        //   1191: ldc_w 'the_loai'
        //   1194: ldc 'lo'
        //   1196: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1199: pop
        //   1200: aload #6
        //   1202: ldc_w 'dan_so'
        //   1205: aload #11
        //   1207: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1210: pop
        //   1211: aload #6
        //   1213: ldc_w 'so_tien'
        //   1216: iload #18
        //   1218: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1221: pop
        //   1222: aload #6
        //   1224: ldc_w 'so_luong'
        //   1227: aload_3
        //   1228: arraylength
        //   1229: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1232: pop
        //   1233: iload #18
        //   1235: ifle -> 1315
        //   1238: aload_0
        //   1239: getfield json_Tralai : Lorg/json/JSONObject;
        //   1242: aload_0
        //   1243: getfield json_Tralai : Lorg/json/JSONObject;
        //   1246: invokevirtual length : ()I
        //   1249: iconst_1
        //   1250: iadd
        //   1251: invokestatic valueOf : (I)Ljava/lang/String;
        //   1254: aload #6
        //   1256: invokevirtual toString : ()Ljava/lang/String;
        //   1259: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1262: pop
        //   1263: new java/lang/StringBuilder
        //   1266: astore_1
        //   1267: aload_1
        //   1268: invokespecial <init> : ()V
        //   1271: aload_1
        //   1272: aload_2
        //   1273: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1276: pop
        //   1277: aload_1
        //   1278: aload #11
        //   1280: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1283: pop
        //   1284: aload_1
        //   1285: ldc_w 'x'
        //   1288: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1291: pop
        //   1292: aload_1
        //   1293: iload #18
        //   1295: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1298: pop
        //   1299: aload_1
        //   1300: ldc_w 'd '
        //   1303: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1306: pop
        //   1307: aload_1
        //   1308: invokevirtual toString : ()Ljava/lang/String;
        //   1311: astore_1
        //   1312: goto -> 1317
        //   1315: aload_2
        //   1316: astore_1
        //   1317: aload_1
        //   1318: astore_2
        //   1319: new java/lang/StringBuilder
        //   1322: astore_3
        //   1323: aload_1
        //   1324: astore_2
        //   1325: aload_3
        //   1326: invokespecial <init> : ()V
        //   1329: aload_1
        //   1330: astore_2
        //   1331: aload_3
        //   1332: ldc_w 'Lo: '
        //   1335: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1338: pop
        //   1339: aload_1
        //   1340: astore_2
        //   1341: aload_3
        //   1342: aload_1
        //   1343: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1346: pop
        //   1347: aload_1
        //   1348: astore_2
        //   1349: aload_3
        //   1350: invokevirtual toString : ()Ljava/lang/String;
        //   1353: astore_1
        //   1354: aload_1
        //   1355: areturn
        //   1356: astore_1
        //   1357: goto -> 1392
        //   1360: astore_1
        //   1361: goto -> 1392
        //   1364: astore_1
        //   1365: goto -> 1392
        //   1368: astore_1
        //   1369: aload #7
        //   1371: astore_2
        //   1372: goto -> 1392
        //   1375: aload #7
        //   1377: astore_2
        //   1378: goto -> 1396
        //   1381: astore_1
        //   1382: aload #7
        //   1384: astore_2
        //   1385: goto -> 1392
        //   1388: astore_1
        //   1389: aload #7
        //   1391: astore_2
        //   1392: aload_1
        //   1393: invokevirtual printStackTrace : ()V
        //   1396: aload_2
        //   1397: areturn
        // Exception table:
        //   from	to	target	type
        //   103	108	1388	org/json/JSONException
        //   123	129	1388	org/json/JSONException
        //   144	148	1388	org/json/JSONException
        //   163	167	1388	org/json/JSONException
        //   182	190	1388	org/json/JSONException
        //   205	211	1388	org/json/JSONException
        //   226	234	1388	org/json/JSONException
        //   249	256	1388	org/json/JSONException
        //   271	279	1388	org/json/JSONException
        //   294	300	1388	org/json/JSONException
        //   315	323	1388	org/json/JSONException
        //   342	351	1388	org/json/JSONException
        //   361	515	524	org/json/JSONException
        //   531	556	1381	org/json/JSONException
        //   559	568	1368	org/json/JSONException
        //   573	585	524	org/json/JSONException
        //   587	591	620	org/json/JSONException
        //   593	604	620	org/json/JSONException
        //   608	617	620	org/json/JSONException
        //   621	625	524	org/json/JSONException
        //   630	645	1368	org/json/JSONException
        //   692	701	1364	org/json/JSONException
        //   708	929	1107	org/json/JSONException
        //   933	940	1356	org/json/JSONException
        //   942	950	1356	org/json/JSONException
        //   952	958	1356	org/json/JSONException
        //   958	1002	1010	org/json/JSONException
        //   1024	1029	1356	org/json/JSONException
        //   1031	1036	1356	org/json/JSONException
        //   1038	1046	1356	org/json/JSONException
        //   1048	1061	1356	org/json/JSONException
        //   1063	1072	1356	org/json/JSONException
        //   1074	1081	1356	org/json/JSONException
        //   1083	1093	1356	org/json/JSONException
        //   1111	1233	1360	org/json/JSONException
        //   1238	1312	1360	org/json/JSONException
        //   1319	1323	1356	org/json/JSONException
        //   1325	1329	1356	org/json/JSONException
        //   1331	1339	1356	org/json/JSONException
        //   1341	1347	1356	org/json/JSONException
        //   1349	1354	1356	org/json/JSONException
        }

public String TraXi(String paramString1, String paramString2) {
        // Byte code:
        //   0: ldc ''
        //   2: astore_3
        //   3: invokestatic getInstance : ()Ljava/util/Calendar;
        //   6: astore #4
        //   8: aload #4
        //   10: new java/util/Date
        //   13: dup
        //   14: invokespecial <init> : ()V
        //   17: invokevirtual setTime : (Ljava/util/Date;)V
        //   20: new java/text/SimpleDateFormat
        //   23: dup
        //   24: ldc_w 'yyyy-MM-dd'
        //   27: invokespecial <init> : (Ljava/lang/String;)V
        //   30: astore #5
        //   32: new java/text/SimpleDateFormat
        //   35: dup
        //   36: ldc_w 'HH:mm:ss'
        //   39: invokespecial <init> : (Ljava/lang/String;)V
        //   42: astore #6
        //   44: aload #5
        //   46: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   49: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   52: aload #6
        //   54: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   57: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   60: aload #5
        //   62: aload #4
        //   64: invokevirtual getTime : ()Ljava/util/Date;
        //   67: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
        //   70: astore #7
        //   72: ldc ''
        //   74: astore #8
        //   76: new org/json/JSONObject
        //   79: dup
        //   80: invokespecial <init> : ()V
        //   83: astore #9
        //   85: new org/json/JSONObject
        //   88: astore #10
        //   90: aload #10
        //   92: aload_2
        //   93: invokespecial <init> : (Ljava/lang/String;)V
        //   96: new java/lang/StringBuilder
        //   99: astore_2
        //   100: aload_2
        //   101: invokespecial <init> : ()V
        //   104: aload #5
        //   106: astore #4
        //   108: aload #6
        //   110: astore #4
        //   112: aload #7
        //   114: astore #4
        //   116: aload #8
        //   118: astore #4
        //   120: aload_2
        //   121: ldc_w 'Select the_loai, so_chon, Sum(diem_ton *(type_kh = 1)) as Nhan, Sum(diem_ton *(type_kh = 2)) As Tra \\nFROM tbl_soctS Where ten_kh = ''
        //   124: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   127: pop
        //   128: aload #5
        //   130: astore #4
        //   132: aload #6
        //   134: astore #4
        //   136: aload #7
        //   138: astore #4
        //   140: aload #8
        //   142: astore #4
        //   144: aload_2
        //   145: aload_1
        //   146: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   149: pop
        //   150: aload #5
        //   152: astore #4
        //   154: aload #6
        //   156: astore #4
        //   158: aload #7
        //   160: astore #4
        //   162: aload #8
        //   164: astore #4
        //   166: aload_2
        //   167: ldc_w '' AND ngay_nhan = ''
        //   170: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   173: pop
        //   174: aload #5
        //   176: astore #4
        //   178: aload #6
        //   180: astore #4
        //   182: aload #7
        //   184: astore #4
        //   186: aload #8
        //   188: astore #4
        //   190: aload_2
        //   191: aload #7
        //   193: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   196: pop
        //   197: aload #5
        //   199: astore #4
        //   201: aload #6
        //   203: astore #4
        //   205: aload #7
        //   207: astore #4
        //   209: aload #8
        //   211: astore #4
        //   213: aload_2
        //   214: ldc_w '' AND the_loai = 'xi' Group by so_chon'
        //   217: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: pop
        //   221: aload #5
        //   223: astore #4
        //   225: aload #6
        //   227: astore #4
        //   229: aload #7
        //   231: astore #4
        //   233: aload #8
        //   235: astore #4
        //   237: aload_2
        //   238: invokevirtual toString : ()Ljava/lang/String;
        //   241: astore #11
        //   243: aload #5
        //   245: astore #4
        //   247: aload #6
        //   249: astore #4
        //   251: aload #7
        //   253: astore #4
        //   255: aload #8
        //   257: astore #4
        //   259: aload_0
        //   260: aload #11
        //   262: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   265: astore #12
        //   267: aload #8
        //   269: astore_2
        //   270: aload #7
        //   272: astore_1
        //   273: aload #5
        //   275: astore #8
        //   277: aload #11
        //   279: astore #5
        //   281: aload #8
        //   283: astore #4
        //   285: aload #6
        //   287: astore #4
        //   289: aload_1
        //   290: astore #4
        //   292: aload_2
        //   293: astore #4
        //   295: aload #12
        //   297: invokeinterface moveToNext : ()Z
        //   302: istore #13
        //   304: ldc_w 'So_chon'
        //   307: astore #4
        //   309: iload #13
        //   311: ifeq -> 662
        //   314: new org/json/JSONObject
        //   317: astore #4
        //   319: aload #4
        //   321: invokespecial <init> : ()V
        //   324: aload #4
        //   326: ldc_w 'So_chon'
        //   329: aload #12
        //   331: iconst_1
        //   332: invokeinterface getString : (I)Ljava/lang/String;
        //   337: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   340: pop
        //   341: aload #4
        //   343: ldc_w 'Da_nhan'
        //   346: aload #12
        //   348: iconst_2
        //   349: invokeinterface getInt : (I)I
        //   354: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   357: pop
        //   358: aload #4
        //   360: ldc_w 'Da_tra'
        //   363: aload #12
        //   365: iconst_3
        //   366: invokeinterface getInt : (I)I
        //   371: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   374: pop
        //   375: aload #10
        //   377: ldc_w 'xien2'
        //   380: invokevirtual has : (Ljava/lang/String;)Z
        //   383: istore #13
        //   385: iload #13
        //   387: ifeq -> 460
        //   390: aload #4
        //   392: ldc_w 'So_chon'
        //   395: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   398: invokevirtual length : ()I
        //   401: iconst_5
        //   402: if_icmpne -> 460
        //   405: aload #4
        //   407: ldc_w 'Khong_Tien'
        //   410: aload #10
        //   412: ldc_w 'xien2'
        //   415: invokevirtual getInt : (Ljava/lang/String;)I
        //   418: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   421: pop
        //   422: aload #4
        //   424: ldc_w 'Se_tra'
        //   427: aload #4
        //   429: ldc_w 'Da_nhan'
        //   432: invokevirtual getInt : (Ljava/lang/String;)I
        //   435: aload #4
        //   437: ldc_w 'Da_tra'
        //   440: invokevirtual getInt : (Ljava/lang/String;)I
        //   443: isub
        //   444: aload #4
        //   446: ldc_w 'Khong_Tien'
        //   449: invokevirtual getInt : (Ljava/lang/String;)I
        //   452: isub
        //   453: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   456: pop
        //   457: goto -> 621
        //   460: aload #10
        //   462: ldc_w 'xien3'
        //   465: invokevirtual has : (Ljava/lang/String;)Z
        //   468: ifeq -> 542
        //   471: aload #4
        //   473: ldc_w 'So_chon'
        //   476: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   479: invokevirtual length : ()I
        //   482: bipush #8
        //   484: if_icmpne -> 542
        //   487: aload #4
        //   489: ldc_w 'Khong_Tien'
        //   492: aload #10
        //   494: ldc_w 'xien3'
        //   497: invokevirtual getInt : (Ljava/lang/String;)I
        //   500: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   503: pop
        //   504: aload #4
        //   506: ldc_w 'Se_tra'
        //   509: aload #4
        //   511: ldc_w 'Da_nhan'
        //   514: invokevirtual getInt : (Ljava/lang/String;)I
        //   517: aload #4
        //   519: ldc_w 'Da_tra'
        //   522: invokevirtual getInt : (Ljava/lang/String;)I
        //   525: isub
        //   526: aload #4
        //   528: ldc_w 'Khong_Tien'
        //   531: invokevirtual getInt : (Ljava/lang/String;)I
        //   534: isub
        //   535: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   538: pop
        //   539: goto -> 621
        //   542: aload #10
        //   544: ldc_w 'xien4'
        //   547: invokevirtual has : (Ljava/lang/String;)Z
        //   550: ifeq -> 621
        //   553: aload #4
        //   555: ldc_w 'So_chon'
        //   558: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   561: invokevirtual length : ()I
        //   564: bipush #11
        //   566: if_icmpne -> 621
        //   569: aload #4
        //   571: ldc_w 'Khong_Tien'
        //   574: aload #10
        //   576: ldc_w 'xien4'
        //   579: invokevirtual getInt : (Ljava/lang/String;)I
        //   582: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   585: pop
        //   586: aload #4
        //   588: ldc_w 'Se_tra'
        //   591: aload #4
        //   593: ldc_w 'Da_nhan'
        //   596: invokevirtual getInt : (Ljava/lang/String;)I
        //   599: aload #4
        //   601: ldc_w 'Da_tra'
        //   604: invokevirtual getInt : (Ljava/lang/String;)I
        //   607: isub
        //   608: aload #4
        //   610: ldc_w 'Khong_Tien'
        //   613: invokevirtual getInt : (Ljava/lang/String;)I
        //   616: isub
        //   617: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   620: pop
        //   621: aload #4
        //   623: ldc_w 'Se_tra'
        //   626: invokevirtual getInt : (Ljava/lang/String;)I
        //   629: ifle -> 651
        //   632: aload #9
        //   634: aload #12
        //   636: iconst_1
        //   637: invokeinterface getString : (I)Ljava/lang/String;
        //   642: aload #4
        //   644: invokevirtual toString : ()Ljava/lang/String;
        //   647: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   650: pop
        //   651: goto -> 281
        //   654: astore_1
        //   655: goto -> 1677
        //   658: astore_1
        //   659: goto -> 1677
        //   662: aload_2
        //   663: astore_1
        //   664: aload #9
        //   666: invokevirtual length : ()I
        //   669: ifle -> 1651
        //   672: aload #9
        //   674: invokevirtual keys : ()Ljava/util/Iterator;
        //   677: astore #14
        //   679: new java/util/ArrayList
        //   682: astore #11
        //   684: aload #11
        //   686: invokespecial <init> : ()V
        //   689: aload #14
        //   691: invokeinterface hasNext : ()Z
        //   696: istore #13
        //   698: iload #13
        //   700: ifeq -> 759
        //   703: aload #14
        //   705: invokeinterface next : ()Ljava/lang/Object;
        //   710: checkcast java/lang/String
        //   713: astore #8
        //   715: new org/json/JSONObject
        //   718: astore #6
        //   720: aload #6
        //   722: aload #9
        //   724: aload #8
        //   726: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   729: invokespecial <init> : (Ljava/lang/String;)V
        //   732: aload #11
        //   734: aload #6
        //   736: invokeinterface add : (Ljava/lang/Object;)Z
        //   741: pop
        //   742: goto -> 752
        //   745: astore #8
        //   747: aload #8
        //   749: invokevirtual printStackTrace : ()V
        //   752: goto -> 689
        //   755: astore_1
        //   756: goto -> 1677
        //   759: new tamhoang/ldpro4/data/Database$4
        //   762: astore_2
        //   763: aload_2
        //   764: aload_0
        //   765: invokespecial <init> : (Ltamhoang/ldpro4/data/Database;)V
        //   768: aload #11
        //   770: aload_2
        //   771: invokestatic sort : (Ljava/util/List;Ljava/util/Comparator;)V
        //   774: iconst_0
        //   775: istore #15
        //   777: ldc ''
        //   779: astore #8
        //   781: iconst_0
        //   782: istore #16
        //   784: aload #12
        //   786: astore #6
        //   788: aload #10
        //   790: astore #5
        //   792: aload #9
        //   794: astore #7
        //   796: aload_1
        //   797: astore_2
        //   798: aload #4
        //   800: astore_1
        //   801: aload_3
        //   802: astore #4
        //   804: aload #14
        //   806: astore_3
        //   807: aload #11
        //   809: invokeinterface size : ()I
        //   814: istore #17
        //   816: iload #16
        //   818: iload #17
        //   820: if_icmpge -> 1385
        //   823: aload #11
        //   825: iload #16
        //   827: invokeinterface get : (I)Ljava/lang/Object;
        //   832: checkcast org/json/JSONObject
        //   835: astore #10
        //   837: aload #10
        //   839: ldc_w 'Se_tra'
        //   842: invokevirtual getInt : (Ljava/lang/String;)I
        //   845: istore #17
        //   847: iload #15
        //   849: iload #17
        //   851: if_icmple -> 1214
        //   854: new org/json/JSONObject
        //   857: astore #9
        //   859: aload #9
        //   861: invokespecial <init> : ()V
        //   864: aload #8
        //   866: ldc ' '
        //   868: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   871: astore #12
        //   873: new java/lang/StringBuilder
        //   876: astore #14
        //   878: aload #14
        //   880: invokespecial <init> : ()V
        //   883: aload #14
        //   885: aload #8
        //   887: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   890: pop
        //   891: aload #14
        //   893: ldc_w 'x'
        //   896: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   899: pop
        //   900: aload #14
        //   902: iload #15
        //   904: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   907: pop
        //   908: aload #14
        //   910: ldc_w 'n'
        //   913: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   916: pop
        //   917: aload #9
        //   919: ldc_w 'du_lieu'
        //   922: aload #14
        //   924: invokevirtual toString : ()Ljava/lang/String;
        //   927: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   930: pop
        //   931: aload #9
        //   933: ldc_w 'the_loai'
        //   936: ldc_w 'xi'
        //   939: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   942: pop
        //   943: aload #9
        //   945: ldc_w 'dan_so'
        //   948: aload #8
        //   950: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   953: pop
        //   954: aload #9
        //   956: ldc_w 'so_tien'
        //   959: iload #15
        //   961: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   964: pop
        //   965: aload #9
        //   967: ldc_w 'so_luong'
        //   970: aload #12
        //   972: arraylength
        //   973: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   976: pop
        //   977: aload_0
        //   978: getfield json_Tralai : Lorg/json/JSONObject;
        //   981: aload_0
        //   982: getfield json_Tralai : Lorg/json/JSONObject;
        //   985: invokevirtual length : ()I
        //   988: iconst_1
        //   989: iadd
        //   990: invokestatic valueOf : (I)Ljava/lang/String;
        //   993: aload #9
        //   995: invokevirtual toString : ()Ljava/lang/String;
        //   998: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1001: pop
        //   1002: new java/lang/StringBuilder
        //   1005: astore #9
        //   1007: aload #9
        //   1009: invokespecial <init> : ()V
        //   1012: aload #9
        //   1014: aload #8
        //   1016: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1019: pop
        //   1020: aload #9
        //   1022: ldc_w 'x'
        //   1025: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1028: pop
        //   1029: aload #9
        //   1031: iload #15
        //   1033: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1036: pop
        //   1037: aload #9
        //   1039: ldc_w 'n\\n'
        //   1042: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1045: pop
        //   1046: aload #9
        //   1048: invokevirtual toString : ()Ljava/lang/String;
        //   1051: astore #8
        //   1053: new java/lang/StringBuilder
        //   1056: astore #9
        //   1058: aload #9
        //   1060: invokespecial <init> : ()V
        //   1063: aload #9
        //   1065: aload #4
        //   1067: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1070: pop
        //   1071: aload_1
        //   1072: astore #4
        //   1074: aload #9
        //   1076: aload #10
        //   1078: aload #4
        //   1080: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1083: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1086: pop
        //   1087: aload #9
        //   1089: ldc_w 'x'
        //   1092: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1095: pop
        //   1096: aload #9
        //   1098: aload #10
        //   1100: ldc_w 'Se_tra'
        //   1103: invokevirtual getInt : (Ljava/lang/String;)I
        //   1106: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1109: pop
        //   1110: aload #9
        //   1112: ldc_w 'n\\n'
        //   1115: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1118: pop
        //   1119: aload #9
        //   1121: invokevirtual toString : ()Ljava/lang/String;
        //   1124: astore #9
        //   1126: new java/lang/StringBuilder
        //   1129: astore #12
        //   1131: aload #12
        //   1133: invokespecial <init> : ()V
        //   1136: aload #12
        //   1138: aload_2
        //   1139: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1142: pop
        //   1143: aload #12
        //   1145: aload #8
        //   1147: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1150: pop
        //   1151: aload #12
        //   1153: invokevirtual toString : ()Ljava/lang/String;
        //   1156: astore #8
        //   1158: new java/lang/StringBuilder
        //   1161: astore_2
        //   1162: aload_2
        //   1163: invokespecial <init> : ()V
        //   1166: aload_2
        //   1167: aload #10
        //   1169: aload #4
        //   1171: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1174: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1177: pop
        //   1178: aload_2
        //   1179: ldc ' '
        //   1181: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1184: pop
        //   1185: aload_2
        //   1186: invokevirtual toString : ()Ljava/lang/String;
        //   1189: astore_2
        //   1190: aload #10
        //   1192: ldc_w 'Se_tra'
        //   1195: invokevirtual getInt : (Ljava/lang/String;)I
        //   1198: istore #15
        //   1200: aload #9
        //   1202: astore #4
        //   1204: goto -> 1357
        //   1207: astore_1
        //   1208: aload #8
        //   1210: astore_2
        //   1211: goto -> 1677
        //   1214: aload_1
        //   1215: astore #9
        //   1217: new java/lang/StringBuilder
        //   1220: astore #12
        //   1222: aload #12
        //   1224: invokespecial <init> : ()V
        //   1227: aload #12
        //   1229: aload #8
        //   1231: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1234: pop
        //   1235: aload #12
        //   1237: aload #10
        //   1239: aload #9
        //   1241: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1244: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1247: pop
        //   1248: aload #12
        //   1250: ldc ' '
        //   1252: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1255: pop
        //   1256: aload #12
        //   1258: invokevirtual toString : ()Ljava/lang/String;
        //   1261: astore #8
        //   1263: new java/lang/StringBuilder
        //   1266: astore #12
        //   1268: aload #12
        //   1270: invokespecial <init> : ()V
        //   1273: aload #12
        //   1275: aload #4
        //   1277: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1280: pop
        //   1281: aload #12
        //   1283: aload #10
        //   1285: aload #9
        //   1287: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1290: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1293: pop
        //   1294: aload #12
        //   1296: ldc_w 'x'
        //   1299: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1302: pop
        //   1303: aload #12
        //   1305: aload #10
        //   1307: ldc_w 'Se_tra'
        //   1310: invokevirtual getInt : (Ljava/lang/String;)I
        //   1313: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1316: pop
        //   1317: aload #12
        //   1319: ldc_w 'n\\n'
        //   1322: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1325: pop
        //   1326: aload #12
        //   1328: invokevirtual toString : ()Ljava/lang/String;
        //   1331: astore #9
        //   1333: aload #10
        //   1335: ldc_w 'Se_tra'
        //   1338: invokevirtual getInt : (Ljava/lang/String;)I
        //   1341: istore #15
        //   1343: aload #8
        //   1345: astore #4
        //   1347: aload_2
        //   1348: astore #8
        //   1350: aload #4
        //   1352: astore_2
        //   1353: aload #9
        //   1355: astore #4
        //   1357: aload #6
        //   1359: astore #9
        //   1361: aload #8
        //   1363: astore #6
        //   1365: iinc #16, 1
        //   1368: aload_2
        //   1369: astore #8
        //   1371: aload #6
        //   1373: astore_2
        //   1374: aload #9
        //   1376: astore #6
        //   1378: goto -> 807
        //   1381: astore_1
        //   1382: goto -> 1677
        //   1385: aload_2
        //   1386: astore_1
        //   1387: aload #8
        //   1389: invokevirtual length : ()I
        //   1392: ifle -> 1599
        //   1395: new org/json/JSONObject
        //   1398: astore #6
        //   1400: aload #6
        //   1402: invokespecial <init> : ()V
        //   1405: aload #8
        //   1407: ldc ' '
        //   1409: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
        //   1412: astore #7
        //   1414: new java/lang/StringBuilder
        //   1417: astore #5
        //   1419: aload #5
        //   1421: invokespecial <init> : ()V
        //   1424: aload #5
        //   1426: aload #8
        //   1428: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1431: pop
        //   1432: aload #5
        //   1434: ldc_w 'x'
        //   1437: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1440: pop
        //   1441: aload #5
        //   1443: iload #15
        //   1445: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   1448: pop
        //   1449: aload #5
        //   1451: ldc_w 'n'
        //   1454: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1457: pop
        //   1458: aload #6
        //   1460: ldc_w 'du_lieu'
        //   1463: aload #5
        //   1465: invokevirtual toString : ()Ljava/lang/String;
        //   1468: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1471: pop
        //   1472: aload #6
        //   1474: ldc_w 'the_loai'
        //   1477: ldc_w 'xi'
        //   1480: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1483: pop
        //   1484: aload #6
        //   1486: ldc_w 'dan_so'
        //   1489: aload #8
        //   1491: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1494: pop
        //   1495: aload #6
        //   1497: ldc_w 'so_tien'
        //   1500: iload #15
        //   1502: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1505: pop
        //   1506: aload #6
        //   1508: ldc_w 'so_luong'
        //   1511: aload #7
        //   1513: arraylength
        //   1514: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   1517: pop
        //   1518: iload #15
        //   1520: ifle -> 1599
        //   1523: aload_0
        //   1524: getfield json_Tralai : Lorg/json/JSONObject;
        //   1527: aload_0
        //   1528: getfield json_Tralai : Lorg/json/JSONObject;
        //   1531: invokevirtual length : ()I
        //   1534: iconst_1
        //   1535: iadd
        //   1536: invokestatic valueOf : (I)Ljava/lang/String;
        //   1539: aload #6
        //   1541: invokevirtual toString : ()Ljava/lang/String;
        //   1544: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1547: pop
        //   1548: new java/lang/StringBuilder
        //   1551: astore #8
        //   1553: aload #8
        //   1555: invokespecial <init> : ()V
        //   1558: aload #8
        //   1560: aload_1
        //   1561: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1564: pop
        //   1565: aload #8
        //   1567: aload #6
        //   1569: ldc_w 'du_lieu'
        //   1572: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1575: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1578: pop
        //   1579: aload #8
        //   1581: ldc_w ' \\n'
        //   1584: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1587: pop
        //   1588: aload #8
        //   1590: invokevirtual toString : ()Ljava/lang/String;
        //   1593: astore_1
        //   1594: aload_1
        //   1595: astore_2
        //   1596: goto -> 1601
        //   1599: aload_1
        //   1600: astore_2
        //   1601: new java/lang/StringBuilder
        //   1604: astore_1
        //   1605: aload_1
        //   1606: invokespecial <init> : ()V
        //   1609: aload_1
        //   1610: ldc_w 'Xien:\\n'
        //   1613: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1616: pop
        //   1617: aload_1
        //   1618: aload #4
        //   1620: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1623: pop
        //   1624: aload_1
        //   1625: ldc_w '\\n'
        //   1628: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1631: pop
        //   1632: aload_1
        //   1633: invokevirtual toString : ()Ljava/lang/String;
        //   1636: astore_1
        //   1637: aload_1
        //   1638: areturn
        //   1639: astore_1
        //   1640: goto -> 1677
        //   1643: astore_1
        //   1644: goto -> 1677
        //   1647: astore_1
        //   1648: goto -> 1677
        //   1651: goto -> 1683
        //   1654: astore_2
        //   1655: aload_1
        //   1656: astore #4
        //   1658: aload_2
        //   1659: astore_1
        //   1660: aload #4
        //   1662: astore_2
        //   1663: goto -> 1677
        //   1666: astore_1
        //   1667: aload #4
        //   1669: astore_2
        //   1670: goto -> 1677
        //   1673: astore_1
        //   1674: aload #8
        //   1676: astore_2
        //   1677: aload_1
        //   1678: invokevirtual printStackTrace : ()V
        //   1681: aload_2
        //   1682: astore_1
        //   1683: aload_1
        //   1684: areturn
        // Exception table:
        //   from	to	target	type
        //   85	104	1673	org/json/JSONException
        //   120	128	1666	org/json/JSONException
        //   144	150	1666	org/json/JSONException
        //   166	174	1666	org/json/JSONException
        //   190	197	1666	org/json/JSONException
        //   213	221	1666	org/json/JSONException
        //   237	243	1666	org/json/JSONException
        //   259	267	1666	org/json/JSONException
        //   295	304	1666	org/json/JSONException
        //   314	324	658	org/json/JSONException
        //   324	385	654	org/json/JSONException
        //   390	457	755	org/json/JSONException
        //   460	539	755	org/json/JSONException
        //   542	621	755	org/json/JSONException
        //   621	651	755	org/json/JSONException
        //   664	689	1654	org/json/JSONException
        //   689	698	1654	org/json/JSONException
        //   703	715	755	org/json/JSONException
        //   715	742	745	org/json/JSONException
        //   747	752	755	org/json/JSONException
        //   759	774	1654	org/json/JSONException
        //   807	816	1647	org/json/JSONException
        //   823	847	1381	org/json/JSONException
        //   854	1071	1381	org/json/JSONException
        //   1074	1136	1381	org/json/JSONException
        //   1136	1158	1643	org/json/JSONException
        //   1158	1200	1207	org/json/JSONException
        //   1217	1343	1643	org/json/JSONException
        //   1387	1518	1643	org/json/JSONException
        //   1523	1594	1643	org/json/JSONException
        //   1601	1637	1639	org/json/JSONException
        }

public void TralaiSO(int paramInt) {
        // Byte code:
        //   0: ldc ''
        //   2: astore_2
        //   3: aload_0
        //   4: new org/json/JSONObject
        //   7: dup
        //   8: invokespecial <init> : ()V
        //   11: putfield json_Tralai : Lorg/json/JSONObject;
        //   14: new java/lang/StringBuilder
        //   17: dup
        //   18: invokespecial <init> : ()V
        //   21: astore_3
        //   22: aload_3
        //   23: ldc_w 'Select * from tbl_tinnhanS where id = '
        //   26: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   29: pop
        //   30: aload_3
        //   31: iload_1
        //   32: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   35: pop
        //   36: aload_0
        //   37: aload_3
        //   38: invokevirtual toString : ()Ljava/lang/String;
        //   41: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   44: astore #4
        //   46: aload #4
        //   48: invokeinterface moveToFirst : ()Z
        //   53: pop
        //   54: new java/lang/StringBuilder
        //   57: astore_3
        //   58: aload_3
        //   59: invokespecial <init> : ()V
        //   62: aload_3
        //   63: ldc_w 'Select * From tbl_kh_new Where ten_kh = ''
        //   66: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   69: pop
        //   70: aload_3
        //   71: aload #4
        //   73: iconst_4
        //   74: invokeinterface getString : (I)Ljava/lang/String;
        //   79: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   82: pop
        //   83: aload_3
        //   84: ldc_w '''
        //   87: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   90: pop
        //   91: aload_0
        //   92: aload_3
        //   93: invokevirtual toString : ()Ljava/lang/String;
        //   96: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   99: astore #5
        //   101: aload #5
        //   103: invokeinterface getCount : ()I
        //   108: ifle -> 1356
        //   111: aload #5
        //   113: invokeinterface moveToFirst : ()Z
        //   118: ifeq -> 1356
        //   121: new org/json/JSONObject
        //   124: astore_3
        //   125: aload_3
        //   126: aload #5
        //   128: bipush #6
        //   130: invokeinterface getString : (I)Ljava/lang/String;
        //   135: invokespecial <init> : (Ljava/lang/String;)V
        //   138: aload_0
        //   139: aload_3
        //   140: putfield json : Lorg/json/JSONObject;
        //   143: aload_3
        //   144: ldc_w 'danDe'
        //   147: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   150: invokevirtual length : ()I
        //   153: istore_1
        //   154: aload_2
        //   155: astore_3
        //   156: iload_1
        //   157: ifle -> 231
        //   160: aload_0
        //   161: getfield json : Lorg/json/JSONObject;
        //   164: ldc_w 'soDe'
        //   167: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   170: astore_3
        //   171: aload_0
        //   172: aload #5
        //   174: iconst_0
        //   175: invokeinterface getString : (I)Ljava/lang/String;
        //   180: aload_3
        //   181: invokevirtual TraDe : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   184: astore #6
        //   186: aload_2
        //   187: astore_3
        //   188: aload #6
        //   190: invokevirtual length : ()I
        //   193: ifle -> 231
        //   196: new java/lang/StringBuilder
        //   199: astore_2
        //   200: aload_2
        //   201: invokespecial <init> : ()V
        //   204: aload_2
        //   205: ldc ''
        //   207: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   210: pop
        //   211: aload_2
        //   212: ldc_w '\\n'
        //   215: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   218: pop
        //   219: aload_2
        //   220: aload #6
        //   222: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   225: pop
        //   226: aload_2
        //   227: invokevirtual toString : ()Ljava/lang/String;
        //   230: astore_3
        //   231: aload_3
        //   232: astore_2
        //   233: aload_0
        //   234: getfield json : Lorg/json/JSONObject;
        //   237: ldc_w 'danLo'
        //   240: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   243: invokevirtual length : ()I
        //   246: ifle -> 319
        //   249: aload_0
        //   250: getfield json : Lorg/json/JSONObject;
        //   253: ldc_w 'soLo'
        //   256: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   259: astore_2
        //   260: aload_0
        //   261: aload #5
        //   263: iconst_0
        //   264: invokeinterface getString : (I)Ljava/lang/String;
        //   269: aload_2
        //   270: invokevirtual TraLo : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   273: astore #6
        //   275: aload_3
        //   276: astore_2
        //   277: aload #6
        //   279: invokevirtual length : ()I
        //   282: ifle -> 319
        //   285: new java/lang/StringBuilder
        //   288: astore_2
        //   289: aload_2
        //   290: invokespecial <init> : ()V
        //   293: aload_2
        //   294: aload_3
        //   295: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   298: pop
        //   299: aload_2
        //   300: ldc_w '\\n'
        //   303: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   306: pop
        //   307: aload_2
        //   308: aload #6
        //   310: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   313: pop
        //   314: aload_2
        //   315: invokevirtual toString : ()Ljava/lang/String;
        //   318: astore_2
        //   319: aload_0
        //   320: getfield json : Lorg/json/JSONObject;
        //   323: ldc_w 'xien2'
        //   326: invokevirtual getInt : (Ljava/lang/String;)I
        //   329: istore_1
        //   330: iload_1
        //   331: ifgt -> 362
        //   334: aload_0
        //   335: getfield json : Lorg/json/JSONObject;
        //   338: ldc_w 'xien3'
        //   341: invokevirtual getInt : (Ljava/lang/String;)I
        //   344: ifgt -> 362
        //   347: aload_2
        //   348: astore_3
        //   349: aload_0
        //   350: getfield json : Lorg/json/JSONObject;
        //   353: ldc_w 'xien4'
        //   356: invokevirtual getInt : (Ljava/lang/String;)I
        //   359: ifle -> 488
        //   362: new org/json/JSONObject
        //   365: astore_3
        //   366: aload_3
        //   367: invokespecial <init> : ()V
        //   370: aload_3
        //   371: ldc_w 'xien2'
        //   374: aload_0
        //   375: getfield json : Lorg/json/JSONObject;
        //   378: ldc_w 'xien2'
        //   381: invokevirtual getInt : (Ljava/lang/String;)I
        //   384: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   387: pop
        //   388: aload_3
        //   389: ldc_w 'xien3'
        //   392: aload_0
        //   393: getfield json : Lorg/json/JSONObject;
        //   396: ldc_w 'xien3'
        //   399: invokevirtual getInt : (Ljava/lang/String;)I
        //   402: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   405: pop
        //   406: aload_3
        //   407: ldc_w 'xien4'
        //   410: aload_0
        //   411: getfield json : Lorg/json/JSONObject;
        //   414: ldc_w 'xien4'
        //   417: invokevirtual getInt : (Ljava/lang/String;)I
        //   420: invokevirtual put : (Ljava/lang/String;I)Lorg/json/JSONObject;
        //   423: pop
        //   424: aload_3
        //   425: invokevirtual toString : ()Ljava/lang/String;
        //   428: astore_3
        //   429: aload_0
        //   430: aload #5
        //   432: iconst_0
        //   433: invokeinterface getString : (I)Ljava/lang/String;
        //   438: aload_3
        //   439: invokevirtual TraXi : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   442: astore #6
        //   444: aload_2
        //   445: astore_3
        //   446: aload #6
        //   448: invokevirtual length : ()I
        //   451: ifle -> 488
        //   454: new java/lang/StringBuilder
        //   457: astore_3
        //   458: aload_3
        //   459: invokespecial <init> : ()V
        //   462: aload_3
        //   463: aload_2
        //   464: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   467: pop
        //   468: aload_3
        //   469: ldc_w '\\n'
        //   472: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   475: pop
        //   476: aload_3
        //   477: aload #6
        //   479: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   482: pop
        //   483: aload_3
        //   484: invokevirtual toString : ()Ljava/lang/String;
        //   487: astore_3
        //   488: aload_3
        //   489: astore_2
        //   490: aload_0
        //   491: getfield json : Lorg/json/JSONObject;
        //   494: ldc_w 'cang'
        //   497: invokevirtual getInt : (Ljava/lang/String;)I
        //   500: ifle -> 571
        //   503: aload_0
        //   504: aload #5
        //   506: iconst_0
        //   507: invokeinterface getString : (I)Ljava/lang/String;
        //   512: aload_0
        //   513: getfield json : Lorg/json/JSONObject;
        //   516: ldc_w 'cang'
        //   519: invokevirtual getInt : (Ljava/lang/String;)I
        //   522: invokevirtual TraCang : (Ljava/lang/String;I)Ljava/lang/String;
        //   525: astore #6
        //   527: aload_3
        //   528: astore_2
        //   529: aload #6
        //   531: invokevirtual length : ()I
        //   534: ifle -> 571
        //   537: new java/lang/StringBuilder
        //   540: astore_2
        //   541: aload_2
        //   542: invokespecial <init> : ()V
        //   545: aload_2
        //   546: aload_3
        //   547: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   550: pop
        //   551: aload_2
        //   552: ldc_w '\\n'
        //   555: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   558: pop
        //   559: aload_2
        //   560: aload #6
        //   562: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   565: pop
        //   566: aload_2
        //   567: invokevirtual toString : ()Ljava/lang/String;
        //   570: astore_2
        //   571: aload_0
        //   572: getfield json_Tralai : Lorg/json/JSONObject;
        //   575: invokevirtual length : ()I
        //   578: ifle -> 1356
        //   581: invokestatic getInstance : ()Ljava/util/Calendar;
        //   584: astore #6
        //   586: new java/util/Date
        //   589: astore_3
        //   590: aload_3
        //   591: invokespecial <init> : ()V
        //   594: aload #6
        //   596: aload_3
        //   597: invokevirtual setTime : (Ljava/util/Date;)V
        //   600: new java/text/SimpleDateFormat
        //   603: astore_3
        //   604: aload_3
        //   605: ldc_w 'yyyy-MM-dd'
        //   608: invokespecial <init> : (Ljava/lang/String;)V
        //   611: new java/text/SimpleDateFormat
        //   614: astore #7
        //   616: aload #7
        //   618: ldc_w 'HH:mm:ss'
        //   621: invokespecial <init> : (Ljava/lang/String;)V
        //   624: aload_3
        //   625: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   628: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   631: aload #7
        //   633: invokestatic getDefault : ()Ljava/util/TimeZone;
        //   636: invokevirtual setTimeZone : (Ljava/util/TimeZone;)V
        //   639: aload_3
        //   640: aload #6
        //   642: invokevirtual getTime : ()Ljava/util/Date;
        //   645: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
        //   648: astore_3
        //   649: aload #7
        //   651: aload #6
        //   653: invokevirtual getTime : ()Ljava/util/Date;
        //   656: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
        //   659: astore #6
        //   661: new java/lang/StringBuilder
        //   664: astore #7
        //   666: aload #7
        //   668: invokespecial <init> : ()V
        //   671: aload #7
        //   673: ldc_w 'Select max(so_tin_nhan) from tbl_tinnhanS WHERE ngay_nhan = ''
        //   676: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   679: pop
        //   680: aload #7
        //   682: aload_3
        //   683: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   686: pop
        //   687: aload #7
        //   689: ldc_w '' AND ten_kh = ''
        //   692: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   695: pop
        //   696: aload #7
        //   698: aload #5
        //   700: iconst_0
        //   701: invokeinterface getString : (I)Ljava/lang/String;
        //   706: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   709: pop
        //   710: aload #7
        //   712: ldc_w '' and type_kh = 2'
        //   715: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   718: pop
        //   719: aload_0
        //   720: aload #7
        //   722: invokevirtual toString : ()Ljava/lang/String;
        //   725: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   728: astore #7
        //   730: aload #7
        //   732: invokeinterface moveToFirst : ()Z
        //   737: pop
        //   738: aload #7
        //   740: iconst_0
        //   741: invokeinterface getInt : (I)I
        //   746: iconst_1
        //   747: iadd
        //   748: istore_1
        //   749: new java/lang/StringBuilder
        //   752: astore #7
        //   754: aload #7
        //   756: invokespecial <init> : ()V
        //   759: aload #7
        //   761: ldc_w 'Tra lai '
        //   764: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   767: pop
        //   768: aload #7
        //   770: iload_1
        //   771: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   774: pop
        //   775: aload #7
        //   777: ldc_w ':'
        //   780: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   783: pop
        //   784: aload #7
        //   786: aload_2
        //   787: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   790: pop
        //   791: aload #7
        //   793: invokevirtual toString : ()Ljava/lang/String;
        //   796: astore_2
        //   797: new java/lang/StringBuilder
        //   800: astore #7
        //   802: aload #7
        //   804: invokespecial <init> : ()V
        //   807: aload #7
        //   809: ldc_w 'Insert Into tbl_tinnhanS values (null, ''
        //   812: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   815: pop
        //   816: aload #7
        //   818: aload_3
        //   819: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   822: pop
        //   823: aload #7
        //   825: ldc_w '', ''
        //   828: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   831: pop
        //   832: aload #7
        //   834: aload #6
        //   836: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   839: pop
        //   840: aload #7
        //   842: ldc_w '',2, ''
        //   845: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   848: pop
        //   849: aload #7
        //   851: aload #5
        //   853: iconst_0
        //   854: invokeinterface getString : (I)Ljava/lang/String;
        //   859: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   862: pop
        //   863: aload #7
        //   865: ldc_w '', ''
        //   868: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   871: pop
        //   872: aload #7
        //   874: aload #5
        //   876: iconst_1
        //   877: invokeinterface getString : (I)Ljava/lang/String;
        //   882: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   885: pop
        //   886: aload #7
        //   888: ldc_w '',''
        //   891: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   894: pop
        //   895: aload #7
        //   897: aload #5
        //   899: iconst_2
        //   900: invokeinterface getString : (I)Ljava/lang/String;
        //   905: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   908: pop
        //   909: aload #7
        //   911: ldc_w '', '
        //   914: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   917: pop
        //   918: aload #7
        //   920: iload_1
        //   921: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   924: pop
        //   925: aload #7
        //   927: ldc_w ', ''
        //   930: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   933: pop
        //   934: aload #7
        //   936: aload_2
        //   937: invokevirtual trim : ()Ljava/lang/String;
        //   940: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   943: pop
        //   944: aload #7
        //   946: ldc_w '',''
        //   949: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   952: pop
        //   953: aload #7
        //   955: aload_2
        //   956: aload_2
        //   957: ldc_w ':'
        //   960: invokevirtual indexOf : (Ljava/lang/String;)I
        //   963: iconst_1
        //   964: iadd
        //   965: invokevirtual substring : (I)Ljava/lang/String;
        //   968: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   971: pop
        //   972: aload #7
        //   974: ldc_w '',''
        //   977: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   980: pop
        //   981: aload #7
        //   983: aload_2
        //   984: aload_2
        //   985: ldc_w ':'
        //   988: invokevirtual indexOf : (Ljava/lang/String;)I
        //   991: iconst_1
        //   992: iadd
        //   993: invokevirtual substring : (I)Ljava/lang/String;
        //   996: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   999: pop
        //   1000: aload #7
        //   1002: ldc_w '', 'ko',0,0,0, ''
        //   1005: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1008: pop
        //   1009: aload #7
        //   1011: aload_0
        //   1012: getfield json_Tralai : Lorg/json/JSONObject;
        //   1015: invokevirtual toString : ()Ljava/lang/String;
        //   1018: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1021: pop
        //   1022: aload #7
        //   1024: ldc_w '')'
        //   1027: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1030: pop
        //   1031: aload_0
        //   1032: aload #7
        //   1034: invokevirtual toString : ()Ljava/lang/String;
        //   1037: invokevirtual QueryData : (Ljava/lang/String;)V
        //   1040: new java/lang/StringBuilder
        //   1043: astore #6
        //   1045: aload #6
        //   1047: invokespecial <init> : ()V
        //   1050: aload #6
        //   1052: ldc_w 'Select id From tbl_tinnhanS where ngay_nhan = ''
        //   1055: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1058: pop
        //   1059: aload #6
        //   1061: aload_3
        //   1062: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1065: pop
        //   1066: aload #6
        //   1068: ldc_w '' AND type_kh = 2 AND ten_kh =''
        //   1071: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1074: pop
        //   1075: aload #6
        //   1077: aload #5
        //   1079: iconst_0
        //   1080: invokeinterface getString : (I)Ljava/lang/String;
        //   1085: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1088: pop
        //   1089: aload #6
        //   1091: ldc_w '' AND nd_goc = ''
        //   1094: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1097: pop
        //   1098: aload #6
        //   1100: aload_2
        //   1101: invokevirtual trim : ()Ljava/lang/String;
        //   1104: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1107: pop
        //   1108: aload #6
        //   1110: ldc_w '''
        //   1113: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1116: pop
        //   1117: aload_0
        //   1118: aload #6
        //   1120: invokevirtual toString : ()Ljava/lang/String;
        //   1123: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   1126: astore_3
        //   1127: aload_3
        //   1128: invokeinterface moveToFirst : ()Z
        //   1133: pop
        //   1134: aload_0
        //   1135: aload_3
        //   1136: iconst_0
        //   1137: invokeinterface getInt : (I)I
        //   1142: iconst_2
        //   1143: invokevirtual Update_TinNhanGoc : (II)V
        //   1146: aload #5
        //   1148: iconst_2
        //   1149: invokeinterface getString : (I)Ljava/lang/String;
        //   1154: ldc_w 'TL'
        //   1157: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1160: iconst_m1
        //   1161: if_icmple -> 1215
        //   1164: aload #5
        //   1166: iconst_1
        //   1167: invokeinterface getLong : (I)J
        //   1172: lstore #8
        //   1174: new android/os/Handler
        //   1177: astore #6
        //   1179: aload #6
        //   1181: invokestatic getMainLooper : ()Landroid/os/Looper;
        //   1184: invokespecial <init> : (Landroid/os/Looper;)V
        //   1187: new tamhoang/ldpro4/data/Database$1
        //   1190: astore #7
        //   1192: aload #7
        //   1194: aload_0
        //   1195: lload #8
        //   1197: invokestatic valueOf : (J)Ljava/lang/Long;
        //   1200: aload_2
        //   1201: invokespecial <init> : (Ltamhoang/ldpro4/data/Database;Ljava/lang/Long;Ljava/lang/String;)V
        //   1204: aload #6
        //   1206: aload #7
        //   1208: invokevirtual post : (Ljava/lang/Runnable;)Z
        //   1211: pop
        //   1212: goto -> 1339
        //   1215: aload #5
        //   1217: iconst_2
        //   1218: invokeinterface getString : (I)Ljava/lang/String;
        //   1223: ldc_w 'sms'
        //   1226: invokevirtual indexOf : (Ljava/lang/String;)I
        //   1229: iconst_m1
        //   1230: if_icmple -> 1249
        //   1233: aload_0
        //   1234: aload #5
        //   1236: iconst_1
        //   1237: invokeinterface getString : (I)Ljava/lang/String;
        //   1242: aload_2
        //   1243: invokevirtual SendSMS : (Ljava/lang/String;Ljava/lang/String;)V
        //   1246: goto -> 1339
        //   1249: new org/json/JSONObject
        //   1252: astore #6
        //   1254: aload #6
        //   1256: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   1259: aload #5
        //   1261: iconst_1
        //   1262: invokeinterface getString : (I)Ljava/lang/String;
        //   1267: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
        //   1270: invokespecial <init> : (Ljava/lang/String;)V
        //   1273: aload #6
        //   1275: ldc_w 'Time'
        //   1278: invokevirtual getInt : (Ljava/lang/String;)I
        //   1281: iconst_3
        //   1282: if_icmple -> 1312
        //   1285: new tamhoang/ldpro4/NotificationReader
        //   1288: astore #6
        //   1290: aload #6
        //   1292: invokespecial <init> : ()V
        //   1295: aload #6
        //   1297: aload #5
        //   1299: iconst_1
        //   1300: invokeinterface getString : (I)Ljava/lang/String;
        //   1305: aload_2
        //   1306: invokevirtual NotificationWearReader : (Ljava/lang/String;Ljava/lang/String;)V
        //   1309: goto -> 1339
        //   1312: aload #6
        //   1314: aload_2
        //   1315: ldc_w 'OK'
        //   1318: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1321: pop
        //   1322: getstatic tamhoang/ldpro4/MainActivity.json_Tinnhan : Lorg/json/JSONObject;
        //   1325: aload #5
        //   1327: iconst_1
        //   1328: invokeinterface getString : (I)Ljava/lang/String;
        //   1333: aload #6
        //   1335: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   1338: pop
        //   1339: aload_0
        //   1340: aload_3
        //   1341: iconst_0
        //   1342: invokeinterface getInt : (I)I
        //   1347: invokevirtual NhapSoChiTiet : (I)V
        //   1350: aload_3
        //   1351: invokeinterface close : ()V
        //   1356: aload #5
        //   1358: invokeinterface close : ()V
        //   1363: goto -> 1372
        //   1366: astore_2
        //   1367: aload_2
        //   1368: invokevirtual getMessage : ()Ljava/lang/String;
        //   1371: pop
        //   1372: aload #4
        //   1374: invokeinterface close : ()V
        //   1379: return
        // Exception table:
        //   from	to	target	type
        //   54	154	1366	java/lang/Exception
        //   160	186	1366	java/lang/Exception
        //   188	231	1366	java/lang/Exception
        //   233	275	1366	java/lang/Exception
        //   277	319	1366	java/lang/Exception
        //   319	330	1366	java/lang/Exception
        //   334	347	1366	java/lang/Exception
        //   349	362	1366	java/lang/Exception
        //   362	444	1366	java/lang/Exception
        //   446	488	1366	java/lang/Exception
        //   490	527	1366	java/lang/Exception
        //   529	571	1366	java/lang/Exception
        //   571	1212	1366	java/lang/Exception
        //   1215	1246	1366	java/lang/Exception
        //   1249	1309	1366	java/lang/Exception
        //   1312	1339	1366	java/lang/Exception
        //   1339	1356	1366	java/lang/Exception
        //   1356	1363	1366	java/lang/Exception
        }

public void Update_TinNhanGoc(int paramInt1, int paramInt2) throws JSONException {
        // Byte code:
        //   0: new java/lang/StringBuilder
        //   3: dup
        //   4: invokespecial <init> : ()V
        //   7: astore_3
        //   8: aload_3
        //   9: ldc_w 'Select * From tbl_tinnhanS WHERE id = '
        //   12: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   15: pop
        //   16: aload_3
        //   17: iload_1
        //   18: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   21: pop
        //   22: aload_0
        //   23: aload_3
        //   24: invokevirtual toString : ()Ljava/lang/String;
        //   27: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   30: astore #4
        //   32: aload #4
        //   34: invokeinterface moveToFirst : ()Z
        //   39: pop
        //   40: aload #4
        //   42: bipush #11
        //   44: invokeinterface getString : (I)Ljava/lang/String;
        //   49: ldc_w 'ok1'
        //   52: invokevirtual indexOf : (Ljava/lang/String;)I
        //   55: iconst_m1
        //   56: if_icmple -> 233
        //   59: new java/lang/StringBuilder
        //   62: dup
        //   63: invokespecial <init> : ()V
        //   66: astore_3
        //   67: aload_3
        //   68: ldc_w 'Select nd_phantich FROM tbl_tinnhanS WHERE id = '
        //   71: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   74: pop
        //   75: aload_3
        //   76: iload_1
        //   77: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   80: pop
        //   81: aload_0
        //   82: aload_3
        //   83: invokevirtual toString : ()Ljava/lang/String;
        //   86: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   89: astore_3
        //   90: aload_3
        //   91: invokeinterface moveToFirst : ()Z
        //   96: pop
        //   97: aload_3
        //   98: iconst_0
        //   99: invokeinterface getString : (I)Ljava/lang/String;
        //   104: astore_3
        //   105: iconst_1
        //   106: istore_2
        //   107: iload_2
        //   108: bipush #6
        //   110: if_icmpge -> 129
        //   113: aload_3
        //   114: ldc_w '\*'
        //   117: ldc ''
        //   119: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   122: astore_3
        //   123: iinc #2, 1
        //   126: goto -> 107
        //   129: new java/lang/StringBuilder
        //   132: dup
        //   133: invokespecial <init> : ()V
        //   136: astore #5
        //   138: aload #5
        //   140: ldc_w 'Update tbl_tinnhanS set nd_phantich = ''
        //   143: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   146: pop
        //   147: aload #5
        //   149: aload_3
        //   150: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   153: pop
        //   154: aload #5
        //   156: ldc_w '', nd_sua = ''
        //   159: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   162: pop
        //   163: aload #5
        //   165: aload_3
        //   166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   169: pop
        //   170: aload #5
        //   172: ldc_w '' WHERE id = '
        //   175: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   178: pop
        //   179: aload #5
        //   181: iload_1
        //   182: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   185: pop
        //   186: aload_0
        //   187: aload #5
        //   189: invokevirtual toString : ()Ljava/lang/String;
        //   192: invokevirtual QueryData : (Ljava/lang/String;)V
        //   195: aload_0
        //   196: iload_1
        //   197: invokevirtual NhapSoChiTiet : (I)V
        //   200: new java/lang/StringBuilder
        //   203: dup
        //   204: invokespecial <init> : ()V
        //   207: astore_3
        //   208: aload_3
        //   209: ldc_w 'Update tbl_tinnhanS set phat_hien_loi = 'ok' WHERE id = '
        //   212: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   215: pop
        //   216: aload_3
        //   217: iload_1
        //   218: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   221: pop
        //   222: aload_0
        //   223: aload_3
        //   224: invokevirtual toString : ()Ljava/lang/String;
        //   227: invokevirtual QueryData : (Ljava/lang/String;)V
        //   230: goto -> 646
        //   233: aload #4
        //   235: bipush #10
        //   237: invokeinterface getString : (I)Ljava/lang/String;
        //   242: invokestatic convertKhongDau : (Ljava/lang/String;)Ljava/lang/String;
        //   245: invokestatic fixTinNhan1 : (Ljava/lang/String;)Ljava/lang/String;
        //   248: astore_3
        //   249: aconst_null
        //   250: astore #6
        //   252: aconst_null
        //   253: astore #7
        //   255: aload_0
        //   256: ldc_w 'Select * From thay_the_phu'
        //   259: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   262: astore #5
        //   264: aload #5
        //   266: invokeinterface moveToNext : ()Z
        //   271: ifeq -> 306
        //   274: aload_3
        //   275: aload #5
        //   277: iconst_1
        //   278: invokeinterface getString : (I)Ljava/lang/String;
        //   283: aload #5
        //   285: iconst_2
        //   286: invokeinterface getString : (I)Ljava/lang/String;
        //   291: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   294: ldc_w '  '
        //   297: ldc ' '
        //   299: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   302: astore_3
        //   303: goto -> 264
        //   306: aload #5
        //   308: ifnull -> 328
        //   311: aload #5
        //   313: invokeinterface isClosed : ()Z
        //   318: ifne -> 328
        //   321: aload #5
        //   323: invokeinterface close : ()V
        //   328: aload_3
        //   329: invokestatic fixTinNhan1 : (Ljava/lang/String;)Ljava/lang/String;
        //   332: astore #8
        //   334: new java/lang/StringBuilder
        //   337: dup
        //   338: invokespecial <init> : ()V
        //   341: astore_3
        //   342: aload_3
        //   343: ldc_w 'Update tbl_tinnhanS set nd_phantich = ''
        //   346: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   349: pop
        //   350: aload_3
        //   351: aload #8
        //   353: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   356: pop
        //   357: aload_3
        //   358: ldc_w '', nd_sua = ''
        //   361: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   364: pop
        //   365: aload_3
        //   366: aload #8
        //   368: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   371: pop
        //   372: aload_3
        //   373: ldc_w '' WHERE id = '
        //   376: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   379: pop
        //   380: aload_3
        //   381: iload_1
        //   382: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   385: pop
        //   386: aload_0
        //   387: aload_3
        //   388: invokevirtual toString : ()Ljava/lang/String;
        //   391: invokevirtual QueryData : (Ljava/lang/String;)V
        //   394: aload #6
        //   396: astore #5
        //   398: aload #8
        //   400: ldc_w 'bo'
        //   403: invokevirtual indexOf : (Ljava/lang/String;)I
        //   406: iconst_m1
        //   407: if_icmple -> 550
        //   410: aload #6
        //   412: astore #5
        //   414: aload #8
        //   416: ldc_w 'bor'
        //   419: invokevirtual indexOf : (Ljava/lang/String;)I
        //   422: iconst_m1
        //   423: if_icmpne -> 550
        //   426: aload #8
        //   428: ldc_w 'bo'
        //   431: invokevirtual indexOf : (Ljava/lang/String;)I
        //   434: iconst_3
        //   435: iadd
        //   436: istore #9
        //   438: aload #7
        //   440: astore_3
        //   441: aload_3
        //   442: astore #5
        //   444: iload #9
        //   446: aload #8
        //   448: invokevirtual length : ()I
        //   451: if_icmpge -> 550
        //   454: aload_3
        //   455: astore #5
        //   457: aload #8
        //   459: iload #9
        //   461: iload #9
        //   463: iconst_1
        //   464: iadd
        //   465: invokevirtual substring : (II)Ljava/lang/String;
        //   468: ldc ' '
        //   470: invokevirtual indexOf : (Ljava/lang/String;)I
        //   473: iconst_m1
        //   474: if_icmpne -> 541
        //   477: aload_3
        //   478: astore #5
        //   480: aload #8
        //   482: iload #9
        //   484: iload #9
        //   486: iconst_1
        //   487: iadd
        //   488: invokevirtual substring : (II)Ljava/lang/String;
        //   491: invokestatic isNumeric : (Ljava/lang/String;)Z
        //   494: ifne -> 541
        //   497: new java/lang/StringBuilder
        //   500: dup
        //   501: invokespecial <init> : ()V
        //   504: astore_3
        //   505: aload_3
        //   506: ldc 'Khhi'
        //   508: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   511: pop
        //   512: aload_3
        //   513: aload #8
        //   515: aload #8
        //   517: ldc_w 'bo'
        //   520: invokevirtual indexOf : (Ljava/lang/String;)I
        //   523: aload #8
        //   525: invokevirtual length : ()I
        //   528: invokevirtual substring : (II)Ljava/lang/String;
        //   531: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   534: pop
        //   535: aload_3
        //   536: invokevirtual toString : ()Ljava/lang/String;
        //   539: astore #5
        //   541: iinc #9, 1
        //   544: aload #5
        //   546: astore_3
        //   547: goto -> 441
        //   550: aload #8
        //   552: ldc 'Khhi
        //   554: invokevirtual indexOf : (Ljava/lang/String;)I
        //   557: iconst_m1
        //   558: if_icmple -> 652
        //   561: new java/lang/StringBuilder
        //   564: dup
        //   565: invokespecial <init> : ()V
        //   568: astore_3
        //   569: aload_3
        //   570: ldc_w 'Update tbl_tinnhanS set nd_phantich = ''
        //   573: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   576: pop
        //   577: aload_3
        //   578: aload #8
        //   580: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   583: pop
        //   584: aload_3
        //   585: ldc_w '', nd_sua = ''
        //   588: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   591: pop
        //   592: aload_3
        //   593: aload #8
        //   595: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   598: pop
        //   599: aload_3
        //   600: ldc_w '',  phat_hien_loi =''
        //   603: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   606: pop
        //   607: aload_3
        //   608: aload #5
        //   610: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   613: pop
        //   614: aload_3
        //   615: ldc_w '' Where id = '
        //   618: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   621: pop
        //   622: aload_3
        //   623: iload_1
        //   624: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   627: pop
        //   628: aload_0
        //   629: aload_3
        //   630: invokevirtual toString : ()Ljava/lang/String;
        //   633: invokevirtual QueryData : (Ljava/lang/String;)V
        //   636: aload_0
        //   637: aload #8
        //   639: aload_0
        //   640: getfield mcontext : Landroid/content/Context;
        //   643: invokespecial createNotification : (Ljava/lang/String;Landroid/content/Context;)V
        //   646: aload #4
        //   648: astore_3
        //   649: goto -> 738
        //   652: aload_0
        //   653: iload_1
        //   654: invokestatic valueOf : (I)Ljava/lang/Integer;
        //   657: iload_2
        //   658: invokevirtual NhanTinNhan : (Ljava/lang/Integer;I)V
        //   661: new java/lang/StringBuilder
        //   664: dup
        //   665: invokespecial <init> : ()V
        //   668: astore_3
        //   669: aload_3
        //   670: ldc_w 'Select * From tbl_tinnhanS WHERE id = '
        //   673: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   676: pop
        //   677: aload_3
        //   678: iload_1
        //   679: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   682: pop
        //   683: aload_0
        //   684: aload_3
        //   685: invokevirtual toString : ()Ljava/lang/String;
        //   688: invokevirtual GetData : (Ljava/lang/String;)Landroid/database/Cursor;
        //   691: astore_3
        //   692: aload_3
        //   693: invokeinterface moveToFirst : ()Z
        //   698: pop
        //   699: aload_3
        //   700: bipush #11
        //   702: invokeinterface getString : (I)Ljava/lang/String;
        //   707: astore #5
        //   709: aload #5
        //   711: ldc 'Khhi
        //   713: invokevirtual indexOf : (Ljava/lang/String;)I
        //   716: iconst_m1
        //   717: if_icmple -> 733
        //   720: aload_0
        //   721: aload #5
        //   723: aload_0
        //   724: getfield mcontext : Landroid/content/Context;
        //   727: invokespecial createNotification : (Ljava/lang/String;Landroid/content/Context;)V
        //   730: goto -> 738
        //   733: aload_0
        //   734: iload_1
        //   735: invokevirtual NhapSoChiTiet : (I)V
        //   738: aload_3
        //   739: ifnull -> 757
        //   742: aload_3
        //   743: invokeinterface isClosed : ()Z
        //   748: ifne -> 757
        //   751: aload_3
        //   752: invokeinterface close : ()V
        //   757: return
        }

public String XuatDanTon2(String paramString1, String paramString2, int paramInt1, int paramInt2) {
        byte b2;
        int m;
        byte b1 = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        String str2 = simpleDateFormat.format(calendar.getTime());
        String str3 = paramString2;
        String str1 = str3;
        if (str3.length() == 0)
        str1 = "0";
        int i = Integer.parseInt(str1.replaceAll("%", "").replaceAll("n", "").replaceAll("k", "").replaceAll("d", "").replaceAll(">", ""));
        str3 = "";
        String str4 = null;
        str1 = "n ";
        if (paramString1 == "deb") {
        paramString1 = "De:";
        str3 = "Om_deB";
        str4 = "(the_loai = 'deb' or the_loai = 'det')";
        str1 = "n ";
        } else if (paramString1 == "dea") {
        paramString1 = "Dau DB:";
        str3 = "Om_deA";
        str4 = "the_loai = 'dea'";
        str1 = "n ";
        } else if (paramString1 == "dec") {
        paramString1 = "Dau nhat:";
        str3 = "Om_deC";
        str4 = "the_loai = 'dec'";
        str1 = "n ";
        } else if (paramString1 == "ded") {
        paramString1 = "Dit nhat:";
        str3 = "Om_deD";
        str4 = "the_loai = 'ded'";
        str1 = "n ";
        } else if (paramString1 == "lo") {
        paramString1 = "Lo:";
        str3 = "Om_lo";
        str4 = "the_loai = 'lo'";
        str1 = "d ";
        } else {
        paramString1 = "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select tbl_soctS.So_chon\n, Sum((tbl_soctS.type_kh = 1) * (100-tbl_soctS.diem_khachgiu)*diem_quydoi/100) as diem\n, so_om.");
        stringBuilder.append(str3);
        stringBuilder.append(" + sum(tbl_soctS.diem_dly_giu*tbl_soctS.diem_quydoi/100) as So_Om\n, Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) as chuyen\n, Sum((tbl_soctS.type_kh =1) * (100-tbl_soctS.diem_khachgiu-tbl_soctS.diem_dly_giu)*diem_quydoi/100) - Sum((tbl_soctS.type_kh =2) * tbl_soctS.diem_quydoi) - so_om.");
        stringBuilder.append(str3);
        stringBuilder.append(" as ton\n, so_nhay  From so_om Left Join tbl_soctS On tbl_soctS.so_chon = so_om.So\n Where tbl_soctS.ngay_nhan='");
        stringBuilder.append(str2);
        stringBuilder.append("' AND ");
        stringBuilder.append(str4);
        stringBuilder.append(" GROUP by so_om.So Order by ton DESC, diem DESC");
        Cursor cursor = GetData(stringBuilder.toString());
        int j = 1;
        int k = 1;
        try {
        if (MainActivity.jSon_Setting.getInt("lam_tron") == 0) {
        k = 1;
        } else if (MainActivity.jSon_Setting.getInt("lam_tron") == 1) {
        k = 10;
        } else if (MainActivity.jSon_Setting.getInt("lam_tron") == 2) {
        k = 50;
        } else {
        b2 = MainActivity.jSon_Setting.getInt("lam_tron");
        if (b2 == 3)
        k = 100;
        }
        j = k;
        } catch (JSONException jSONException) {
        jSONException.printStackTrace();
        }
        if (paramInt1 > 0) {
        k = paramInt1 - 1;
        b2 = 0;
        m = 0;
        SimpleDateFormat simpleDateFormat1 = simpleDateFormat;
        str4 = str2;
        } else {
        b2 = 0;
        m = 0;
        str4 = str2;
        SimpleDateFormat simpleDateFormat1 = simpleDateFormat;
        k = paramInt1;
        }
        while (true) {
        String str5;
        String str6 = paramString2;
        if (cursor.moveToNext()) {
        if (b2 >= k && b2 <= paramInt2 - 1) {
        if (i == 0) {
        paramInt1 = cursor.getInt(4) / j * j;
        } else if (str6.indexOf("%") > -1) {
        paramInt1 = cursor.getInt(4) * i / j / 100 * j;
        } else if (str6.indexOf(">") > -1) {
        paramInt1 = (cursor.getInt(4) - i) / j * j;
        } else if (cursor.getInt(4) > i) {
        paramInt1 = i / j * j;
        } else {
        paramInt1 = cursor.getInt(4) / j * j;
        }
        if (paramInt1 > 0) {
        if (m > paramInt1) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString1);
        stringBuilder1.append("x");
        stringBuilder1.append(m);
        stringBuilder1.append(str1);
        paramString1 = stringBuilder1.toString();
        b1 = 0;
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString1);
        stringBuilder1.append(cursor.getString(0));
        stringBuilder1.append(",");
        paramString1 = stringBuilder1.toString();
        } else {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString1);
        stringBuilder1.append(cursor.getString(0));
        stringBuilder1.append(",");
        paramString1 = stringBuilder1.toString();
        }
        b1++;
        m = paramInt1;
        }
        }
        b2++;
        continue;
        }
        paramString2 = paramString1;
        if (paramString1.length() > 4) {
        paramString2 = paramString1;
        if (b1 > 0) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString1);
        stringBuilder1.append("x");
        stringBuilder1.append(m);
        stringBuilder1.append(str1);
        str5 = stringBuilder1.toString();
        }
        }
        if (cursor != null)
        cursor.close();
        return str5;
        }
        }

public void onCreate(SQLiteDatabase paramSQLiteDatabase) {}

public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {
        if (paramInt1 > 1);
        }
        }
