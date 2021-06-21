package tamhoang.ldpro4.Activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Congthuc.BaseToolBarActivity;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.data.Database;

public class Activity_AccWeb extends BaseToolBarActivity {
    Button btn_them_trang;

    Database db;

    EditText edt_Giabc;

    EditText edt_Giadea;

    EditText edt_Giadeb;

    EditText edt_Giadec;

    EditText edt_Giaded;

    EditText edt_Gialo;

    EditText edt_Giaxi2;

    EditText edt_Giaxi3;

    EditText edt_Giaxi4;

    EditText edt_account;

    EditText edt_password;

    LinearLayout liner_caidat;

    String new_web = "";

    TextView tview_Maxbc;

    TextView tview_Maxdea;

    TextView tview_Maxdeb;

    TextView tview_Maxdec;

    TextView tview_Maxded;

    TextView tview_Maxlo;

    TextView tview_Maxxi2;

    TextView tview_Maxxi3;

    TextView tview_Maxxi4;

    private void init() {
        this.liner_caidat = (LinearLayout)findViewById(2131231037);
        this.btn_them_trang = (Button)findViewById(2131230831);
        this.edt_account = (EditText)findViewById(2131230933);
        this.edt_password = (EditText)findViewById(2131230958);
        this.edt_Giadea = (EditText)findViewById(2131230920);
        this.edt_Giadeb = (EditText)findViewById(2131230921);
        this.edt_Giadec = (EditText)findViewById(2131230922);
        this.edt_Giaded = (EditText)findViewById(2131230923);
        this.edt_Gialo = (EditText)findViewById(2131230924);
        this.tview_Maxdea = (TextView)findViewById(2131231545);
        this.tview_Maxdeb = (TextView)findViewById(2131231546);
        this.tview_Maxdec = (TextView)findViewById(2131231547);
        this.tview_Maxded = (TextView)findViewById(2131231548);
        this.tview_Maxlo = (TextView)findViewById(2131231549);
        this.tview_Maxxi2 = (TextView)findViewById(2131231550);
        this.tview_Maxxi3 = (TextView)findViewById(2131231551);
        this.tview_Maxxi4 = (TextView)findViewById(2131231552);
        this.edt_Giaxi2 = (EditText)findViewById(2131230925);
        this.edt_Giaxi3 = (EditText)findViewById(2131230926);
        this.edt_Giaxi4 = (EditText)findViewById(2131230927);
    }

    public void RUN() {
        OkHttpClient okHttpClient = new OkHttpClient();
        JSONObject jSONObject = new JSONObject();
        AtomicReference<String> atomicReference = new AtomicReference<String>("");
        if (Build.VERSION.SDK_INT >= 24)
            CompletableFuture.runAsync(new _$$Lambda$Activity_AccWeb$MH_l5o6Z1Iu2O_EGfjPTXzxzUBg(this, jSONObject, atomicReference, okHttpClient));
    }

    protected int getLayoutId() {
        return 2131427356;
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131427356);
        this.db = new Database((Context)this);
        this.new_web = getIntent().getStringExtra("new_web");
        init();
        if (this.new_web.length() == 0) {
            this.liner_caidat.setVisibility(8);
        } else {
            Database database = this.db;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Select * from tbl_chaytrang_acc Where Username = '");
            stringBuilder.append(this.new_web);
            stringBuilder.append("'");
            Cursor cursor = database.GetData(stringBuilder.toString());
            cursor.moveToFirst();
            this.edt_account.setText(cursor.getString(0));
            this.edt_password.setText(cursor.getString(1));
            this.edt_account.setEnabled(false);
            this.edt_password.setEnabled(false);
            this.btn_them_trang.setText("Th/ Strang");
            this.btn_them_trang.setTextColor(-65536);
            try {
                String str;
                JSONObject jSONObject = new JSONObject();
                this(cursor.getString(2));
                this.edt_Giadea.setText(jSONObject.getString("gia_dea"));
                this.edt_Giadeb.setText(jSONObject.getString("gia_deb"));
                this.edt_Giadec.setText(jSONObject.getString("gia_dec"));
                this.edt_Giaded.setText(jSONObject.getString("gia_ded"));
                this.edt_Gialo.setText(jSONObject.getString("gia_lo"));
                EditText editText = this.edt_Giaxi2;
                if (!jSONObject.has("gia_xi2")) {
                    str = "560";
                } else {
                    str = jSONObject.getString("gia_xi2");
                }
                editText.setText(str);
                editText = this.edt_Giaxi3;
                if (!jSONObject.has("gia_xi3")) {
                    str = "520";
                } else {
                    str = jSONObject.getString("gia_xi3");
                }
                editText.setText(str);
                editText = this.edt_Giaxi4;
                if (!jSONObject.has("gia_xi4")) {
                    str = "450";
                } else {
                    str = jSONObject.getString("gia_xi4");
                }
                editText.setText(str);
                this.tview_Maxdea.setText(jSONObject.getString("max_dea"));
                this.tview_Maxdeb.setText(jSONObject.getString("max_deb"));
                this.tview_Maxdec.setText(jSONObject.getString("max_dec"));
                this.tview_Maxded.setText(jSONObject.getString("max_ded"));
                this.tview_Maxlo.setText(jSONObject.getString("max_lo"));
                this.tview_Maxxi2.setText(jSONObject.getString("max_xi2"));
                this.tview_Maxxi3.setText(jSONObject.getString("max_xi3"));
                this.tview_Maxxi4.setText(jSONObject.getString("max_xi4"));
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        }
        this.btn_them_trang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (Activity_AccWeb.this.new_web.length() == 0) {
                    Database database = Activity_AccWeb.this.db;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Select * from tbl_chaytrang_acc Where Username = '");
                    stringBuilder.append(Activity_AccWeb.this.edt_account.getText().toString());
                    stringBuilder.append("'");
                    if (database.GetData(stringBuilder.toString()).getCount() == 0) {
                        Activity_AccWeb.this.RUN();
                    } else {
                        Toast.makeText((Context)Activity_AccWeb.this, "ctkhontrong hth, 0).show();
                    }
                } else {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("gia_dea", Activity_AccWeb.this.edt_Giadea.getText().toString().replaceAll("\\.", ""));
                        jSONObject.put("gia_deb", Activity_AccWeb.this.edt_Giadeb.getText().toString().replaceAll("\\.", ""));
                        jSONObject.put("gia_dec", Activity_AccWeb.this.edt_Giadec.getText().toString().replaceAll("\\.", ""));
                        jSONObject.put("gia_ded", Activity_AccWeb.this.edt_Giaded.getText().toString().replaceAll("\\.", ""));
                        jSONObject.put("gia_lo", Activity_AccWeb.this.edt_Gialo.getText().toString().replaceAll("\\.", ""));
                        jSONObject.put("gia_xi2", Activity_AccWeb.this.edt_Giaxi2.getText().toString().replaceAll("\\.", ""));
                        jSONObject.put("gia_xi3", Activity_AccWeb.this.edt_Giaxi3.getText().toString().replaceAll("\\.", ""));
                        jSONObject.put("gia_xi4", Activity_AccWeb.this.edt_Giaxi4.getText().toString().replaceAll("\\.", ""));
                        jSONObject.put("max_dea", Activity_AccWeb.this.tview_Maxdea.getText().toString().replaceAll("\\.", ""));
                        jSONObject.put("max_deb", Activity_AccWeb.this.tview_Maxdeb.getText().toString().replaceAll("\\.", ""));
                        jSONObject.put("max_dec", Activity_AccWeb.this.tview_Maxdec.getText().toString().replaceAll("\\.", ""));
                        jSONObject.put("max_ded", Activity_AccWeb.this.tview_Maxded.getText().toString().replaceAll("\\.", ""));
                        jSONObject.put("max_lo", Activity_AccWeb.this.tview_Maxlo.getText().toString().replaceAll("\\.", ""));
                        jSONObject.put("max_xi2", Activity_AccWeb.this.tview_Maxxi2.getText().toString().replaceAll("\\.", ""));
                        jSONObject.put("max_xi3", Activity_AccWeb.this.tview_Maxxi3.getText().toString().replaceAll("\\.", ""));
                        jSONObject.put("max_xi4", Activity_AccWeb.this.tview_Maxxi4.getText().toString().replaceAll("\\.", ""));
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                    Toast.makeText((Context)Activity_AccWeb.this, "lthc, 0).show();
                            StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("INSERT OR REPLACE Into tbl_chaytrang_acc (Username, Password, Setting) Values ('");
                    stringBuilder.append(Activity_AccWeb.this.edt_account.getText().toString());
                    stringBuilder.append("', '");
                    stringBuilder.append(Activity_AccWeb.this.edt_password.getText().toString());
                    stringBuilder.append("', '");
                    stringBuilder.append(jSONObject.toString());
                    stringBuilder.append("')");
                    String str = stringBuilder.toString();
                    Activity_AccWeb.this.db.QueryData(str);
                    Activity_AccWeb.this.finish();
                }
            }
        });
    }
}
