package tamhoang.ldpro4.Activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Congthuc.BaseToolBarActivity;
import tamhoang.ldpro4.data.Database;

public class Activity_Tinnhan extends BaseToolBarActivity {
    Button btn_suatin;

    Button btn_xoatin;

    Database db;

    EditText editText_suatin;

    String id = "";

    JSONObject json;

    int lv_position = -1;

    ListView lv_suatin;

    private List<String> mDanGoc = new ArrayList<String>();

    private List<String> mPhantich = new ArrayList<String>();

    String ngay_nhan = "";

    String soTN = "";

    String tenKH = "";

    int typeKH;

    protected int getLayoutId() {
        return 2131427376;
    }

    public boolean onContextItemSelected(MenuItem paramMenuItem) {
        super.onContextItemSelected(paramMenuItem);
        if (paramMenuItem.getItemId() == 1) {
            ClipboardManager clipboardManager = (ClipboardManager)getSystemService("clipboard");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mDanGoc.get(this.lv_position));
            stringBuilder.append("\n");
            stringBuilder.append(this.mPhantich.get(this.lv_position));
            clipboardManager.setPrimaryClip(ClipData.newPlainText("Tin ch, stringBuilder.toString()));
                    Toast.makeText((Context)this, "copy thc, 1).show();
        }
        return true;
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131427376);
        this.id = getIntent().getStringExtra("m_ID");
        this.db = new Database((Context)this);
        this.btn_suatin = (Button)findViewById(2131230826);
        this.btn_xoatin = (Button)findViewById(2131230827);
        this.editText_suatin = (EditText)findViewById(2131230916);
        this.lv_suatin = (ListView)findViewById(2131231100);
        Database database = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select * From tbl_tinnhanS WHere id = ");
        stringBuilder.append(this.id);
        Cursor cursor = database.GetData(stringBuilder.toString());
        cursor.moveToFirst();
        if (cursor.getString(6).indexOf("ChayTrang") > -1) {
            Toast.makeText((Context)this, "Khstin chvtrang", 0).show();
            cursor.close();
            finish();
            return;
        }
        this.ngay_nhan = cursor.getString(1);
        this.tenKH = cursor.getString(4);
        this.soTN = cursor.getString(7);
        this.typeKH = cursor.getInt(3);
        if (cursor.getString(11).indexOf("ok") > -1) {
            try {
                this.mDanGoc.clear();
                this.mPhantich.clear();
                JSONObject jSONObject = new JSONObject();
                this(cursor.getString(15));
                this.json = jSONObject;
                this.editText_suatin.setText(cursor.getString(9));
                Iterator<String> iterator = this.json.keys();
                while (iterator.hasNext()) {
                    String str = iterator.next();
                    JSONObject jSONObject1 = this.json.getJSONObject(str);
                    List<String> list = this.mDanGoc;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    this();
                    stringBuilder1.append(jSONObject1.getString("du_lieu"));
                    stringBuilder1.append(" (");
                    stringBuilder1.append(jSONObject1.getString("so_luong"));
                    stringBuilder1.append(")");
                    list.add(stringBuilder1.toString());
                    list = this.mPhantich;
                    stringBuilder1 = new StringBuilder();
                    this();
                    stringBuilder1.append(jSONObject1.getString("dan_so"));
                    stringBuilder1.append("x");
                    stringBuilder1.append(jSONObject1.getString("so_tien"));
                    list.add(stringBuilder1.toString());
                }
                ListView listView = this.lv_suatin;
                TN_Adapter tN_Adapter = new TN_Adapter();
                this(this, (Context)this, 2131427438, this.mDanGoc);
                listView.setAdapter((ListAdapter)tN_Adapter);
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        } else {
            this.editText_suatin.setText(cursor.getString(9));
        }
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService("input_method");
        View view2 = getCurrentFocus();
        View view1 = view2;
        if (view2 == null)
            view1 = new View((Context)this);
        inputMethodManager.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        this.btn_suatin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                Activity_Tinnhan activity_Tinnhan;
                Database database = Activity_Tinnhan.this.db;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("DELETE FROM tbl_soctS WHERE ngay_nhan = '");
                stringBuilder2.append(Activity_Tinnhan.this.ngay_nhan);
                stringBuilder2.append("' AND ten_kh = '");
                stringBuilder2.append(Activity_Tinnhan.this.tenKH);
                stringBuilder2.append("'  AND so_tin_nhan = ");
                stringBuilder2.append(Activity_Tinnhan.this.soTN);
                stringBuilder2.append(" And type_kh = ");
                stringBuilder2.append(Activity_Tinnhan.this.typeKH);
                database.QueryData(stringBuilder2.toString());
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Update tbl_tinnhanS Set nd_phantich = '");
                stringBuilder2.append(Activity_Tinnhan.this.editText_suatin.getText().toString());
                stringBuilder2.append("', phat_hien_loi = 'ko' WHERE id = ");
                stringBuilder2.append(Activity_Tinnhan.this.id);
                String str = stringBuilder2.toString();
                Activity_Tinnhan.this.db.QueryData(str);
                try {
                    Activity_Tinnhan.this.db.Update_TinNhanGoc(Integer.parseInt(Activity_Tinnhan.this.id), Activity_Tinnhan.this.typeKH);
                } catch (Exception exception) {
                    Toast.makeText((Context)Activity_Tinnhan.this, "xra l, 1).show();
                }
                database = Activity_Tinnhan.this.db;
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("Select * FROM tbl_tinnhanS Where id = ");
                stringBuilder1.append(Activity_Tinnhan.this.id);
                Cursor cursor = database.GetData(stringBuilder1.toString());
                cursor.moveToFirst();
                if (cursor.getString(11).indexOf("Khhi) > -1) {
                        String str1 = cursor.getString(10).replace("ldpro", "<font color='#FF0000'>");
                Activity_Tinnhan.this.editText_suatin.setText((CharSequence)Html.fromHtml(str1));
                if (cursor.getString(10).indexOf("ldpro") > -1)
                    Activity_Tinnhan.this.editText_suatin.setSelection(cursor.getString(10).indexOf("ldpro"));
                Activity_Tinnhan.this.mDanGoc.clear();
                Activity_Tinnhan.this.mPhantich.clear();
                ListView listView = Activity_Tinnhan.this.lv_suatin;
                activity_Tinnhan = Activity_Tinnhan.this;
                listView.setAdapter((ListAdapter)new Activity_Tinnhan.TN_Adapter((Context)activity_Tinnhan, 2131427438, activity_Tinnhan.mDanGoc));
            } else {
                Activity_Tinnhan.this.editText_suatin.setText(activity_Tinnhan.getString(9));
                Activity_Tinnhan.this.mDanGoc.clear();
                Activity_Tinnhan.this.mPhantich.clear();
                try {
                    Activity_Tinnhan activity_Tinnhan1 = Activity_Tinnhan.this;
                    JSONObject jSONObject = new JSONObject();
                    this(activity_Tinnhan.getString(15));
                    activity_Tinnhan1.json = jSONObject;
                    Iterator<String> iterator = Activity_Tinnhan.this.json.keys();
                    while (iterator.hasNext()) {
                        String str1 = iterator.next();
                        JSONObject jSONObject1 = Activity_Tinnhan.this.json.getJSONObject(str1);
                        List<String> list = Activity_Tinnhan.this.mDanGoc;
                        StringBuilder stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("du_lieu"));
                        stringBuilder.append(" (");
                        stringBuilder.append(jSONObject1.getString("so_luong"));
                        stringBuilder.append(")");
                        list.add(stringBuilder.toString());
                        list = Activity_Tinnhan.this.mPhantich;
                        stringBuilder = new StringBuilder();
                        this();
                        stringBuilder.append(jSONObject1.getString("dan_so"));
                        stringBuilder.append("x");
                        stringBuilder.append(jSONObject1.getString("so_tien"));
                        list.add(stringBuilder.toString());
                    }
                    ListView listView = Activity_Tinnhan.this.lv_suatin;
                    Activity_Tinnhan.TN_Adapter tN_Adapter = new Activity_Tinnhan.TN_Adapter();
                    this((Context)Activity_Tinnhan.this, 2131427438, Activity_Tinnhan.this.mDanGoc);
                    listView.setAdapter((ListAdapter)tN_Adapter);
                } catch (JSONException jSONException) {
                    jSONException.printStackTrace();
                }
            }
        }
    });
    this.lv_suatin.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        public boolean onItemLongClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
            Activity_Tinnhan.this.lv_position = param1Int;
            return false;
        }
    });
    this.btn_xoatin.setOnClickListener(new View.OnClickListener() {
        public void onClick(View param1View) {
            Activity_Tinnhan.this.finish();
        }
    });
    registerForContextMenu((View)this.lv_suatin);
    cursor.close();
}

    public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo) {
        super.onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
        paramContextMenu.add(0, 1, 0, "Copy ?");
    }

class TN_Adapter extends ArrayAdapter {
    public TN_Adapter(Context param1Context, int param1Int, List<String> param1List) {
        super(param1Context, param1Int, param1List);
    }

    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
        View view = ((Activity)getContext()).getLayoutInflater().inflate(2131427438, null);
        TextView textView1 = (TextView)view.findViewById(2131230863);
        final TextView tv_danpt = (TextView)view.findViewById(2131230864);
        textView1.setText(Activity_Tinnhan.this.mDanGoc.get(param1Int));
        textView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param2View) {
                if (tv_danpt.getVisibility() == 0) {
                    tv_danpt.setVisibility(8);
                } else {
                    tv_danpt.setVisibility(0);
                }
            }
        });
        textView2.setText(Activity_Tinnhan.this.mPhantich.get(param1Int));
        textView2.setVisibility(8);
        return view;
    }
}

class null implements View.OnClickListener {
public void onClick(View param1View) {
        if (tv_danpt.getVisibility() == 0) {
        tv_danpt.setVisibility(8);
        } else {
        tv_danpt.setVisibility(0);
        }
        }
        }
        }
