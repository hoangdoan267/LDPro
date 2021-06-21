package tamhoang.ldpro4.Activity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import tamhoang.ldpro4.Congthuc.BaseToolBarActivity;
import tamhoang.ldpro4.data.Database;

public class Activity_thaythe extends BaseToolBarActivity {
    Button btn_Luu;

    Database db;

    ListView lv_thaythe;

    private List<String> mNoidung = new ArrayList<String>();

    private List<String> mThaythe = new ArrayList<String>();

    TextView tvNDthaythe;

    TextView tvThaythe;

    private void listview_thaythe() {
        this.mNoidung.clear();
        this.mThaythe.clear();
        Cursor cursor = this.db.GetData("Select * FROM thay_the_phu");
        if (cursor != null)
            while (cursor.moveToNext()) {
                this.mNoidung.add(cursor.getString(1));
                this.mThaythe.add(cursor.getString(2));
            }
        if (!cursor.isClosed())
            cursor.close();
        this.lv_thaythe.setAdapter((ListAdapter)new Thaythe_Adapter((Context)this, 2131427375, this.mNoidung));
    }

    protected int getLayoutId() {
        return 2131427374;
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131427374);
        this.tvThaythe = (TextView)findViewById(2131231386);
        this.tvNDthaythe = (TextView)findViewById(2131231470);
        this.lv_thaythe = (ListView)findViewById(2131231102);
        Button button = (Button)findViewById(2131230823);
        this.btn_Luu = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Select count(id) From thay_the_phu WHERE str = '");
                stringBuilder.append(Activity_thaythe.this.tvThaythe.getText().toString());
                stringBuilder.append("'");
                String str = stringBuilder.toString();
                Cursor cursor = Activity_thaythe.this.db.GetData(str);
                cursor.moveToFirst();
                if (cursor.getInt(0) == 0) {
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append("Insert into thay_the_phu values (null, '");
                    stringBuilder1.append(Activity_thaythe.this.tvThaythe.getText().toString());
                    stringBuilder1.append("', '");
                    stringBuilder1.append(Activity_thaythe.this.tvNDthaythe.getText().toString());
                    stringBuilder1.append("')");
                    String str1 = stringBuilder1.toString();
                    Activity_thaythe.this.db.QueryData(str1);
                } else {
                    Database database = Activity_thaythe.this.db;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append("Update thay_the_phu set str_rpl = '");
                    stringBuilder1.append(Activity_thaythe.this.tvNDthaythe.getText().toString());
                    stringBuilder1.append("' WHERE str = '");
                    stringBuilder1.append(Activity_thaythe.this.tvThaythe.getText().toString());
                    stringBuilder1.append("'");
                    database.QueryData(stringBuilder1.toString());
                }
                Activity_thaythe.this.tvThaythe.setText("");
                Activity_thaythe.this.tvNDthaythe.setText("");
                Activity_thaythe.this.listview_thaythe();
            }
        });
        this.lv_thaythe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Activity_thaythe.this.tvThaythe.setText(Activity_thaythe.this.mNoidung.get(param1Int));
                Activity_thaythe.this.tvNDthaythe.setText(Activity_thaythe.this.mThaythe.get(param1Int));
            }
        });
        this.db = new Database((Context)this);
        listview_thaythe();
    }

    class Thaythe_Adapter extends ArrayAdapter {
        public Thaythe_Adapter(Context param1Context, int param1Int, List<String> param1List) {
            super(param1Context, param1Int, param1List);
        }

        public View getView(final int position, View param1View, ViewGroup param1ViewGroup) {
            param1View = ((Activity)getContext()).getLayoutInflater().inflate(2131427375, null);
            TextView textView = (TextView)param1View.findViewById(2131231528);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(position + 1);
            stringBuilder.append("");
            textView.setText(stringBuilder.toString());
            ((TextView)param1View.findViewById(2131231417)).setText(Activity_thaythe.this.mNoidung.get(position));
            ((TextView)param1View.findViewById(2131231537)).setText(Activity_thaythe.this.mThaythe.get(position));
            ((TextView)param1View.findViewById(2131231419)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View param2View) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Delete From thay_the_phu WHERE str = '");
                    stringBuilder.append(Activity_thaythe.this.mNoidung.get(position));
                    stringBuilder.append("'");
                    String str = stringBuilder.toString();
                    Activity_thaythe.this.db.QueryData(str);
                    Activity_thaythe.this.listview_thaythe();
                }
            });
            return param1View;
        }
    }

    class null implements View.OnClickListener {
        public void onClick(View param1View) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Delete From thay_the_phu WHERE str = '");
            stringBuilder.append(Activity_thaythe.this.mNoidung.get(position));
            stringBuilder.append("'");
            String str = stringBuilder.toString();
            Activity_thaythe.this.db.QueryData(str);
            Activity_thaythe.this.listview_thaythe();
        }
    }
}
