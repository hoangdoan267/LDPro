package tamhoang.ldpro4.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import tamhoang.ldpro4.Login;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.data.Database;

public class Frag_Home extends Fragment {
    String Imei = null;

    String TK = "";

    Button bt_trangchu;

    Button button_default;

    Database db;

    TextView edtImei;

    TextView tvHansd;

    TextView tvTaiKhoan;

    TextView tv_sodienthoai;

    String viewDate;

    private boolean isNetworkConnected() {
        boolean bool;
        NetworkInfo networkInfo = ((ConnectivityManager)getActivity().getSystemService("connectivity")).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            bool = true;
        } else {
            bool = false;
        }
        return bool;
    }

    public void Kiemtra() {
        String str = Login.Imei;
        this.Imei = str;
        this.edtImei.setText(str);
        if (isNetworkConnected() && this.Imei != null) {
            check();
        } else if (this.Imei == null) {
            startActivity(new Intent((Context)getActivity(), Login.class));
        } else {
            Toast.makeText((Context)getActivity(), "Kitra knInternet!", 1).show();
        }
        this.tvHansd.setText(MainActivity.myDate);
        this.tvTaiKhoan.setText(MainActivity.Acc_manager);
    }

    public void check() {
        if (this.Imei != null)
            try {
                StringRequest stringRequest = new StringRequest() {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
                        hashMap.put("imei", Frag_Home.this.Imei);
                        hashMap.put("serial", Login.serial);
                        return (Map)hashMap;
                    }
                };
                String str = this.viewDate;
                Response.Listener<String> listener = new Response.Listener<String>() {
                    public void onResponse(String param1String) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            try {
                                this(param1String);
                                MainActivity.listKH = jSONObject.getJSONArray("listKHs").getJSONObject(0);
                                param1String = MainActivity.listKH.getString("date").replaceAll("-", "");
                                StringBuilder stringBuilder = new StringBuilder();
                                this();
                                stringBuilder.append(param1String.substring(6));
                                stringBuilder.append("/");
                                stringBuilder.append(param1String.substring(4, 6));
                                stringBuilder.append("/");
                                stringBuilder.append(param1String.substring(0, 4));
                                param1String = stringBuilder.toString();
                                Frag_Home.this.tvHansd.setText(param1String);
                                Frag_Home.this.TK = "";
                                Frag_Home.this.TK = MainActivity.listKH.getString("acc");
                                MainActivity.myDate = param1String;
                                MainActivity.Acc_manager = MainActivity.listKH.getString("acc");
                                Frag_Home.this.tvTaiKhoan.setText(Frag_Home.this.TK);
                                Frag_Home.this.tv_sodienthoai.setText(MainActivity.listKH.getString("k_tra"));
                                try {
                                    MainActivity.listKH.getString("date");
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                                    this("yyyy-MM-dd");
                                    Date date = new Date();
                                    this();
                                    long l = (simpleDateFormat.parse(MainActivity.listKH.getString("date")).getTime() - date.getTime()) / 1000L / 60L / 60L / 24L;
                                    float f = (float)l;
                                    if (f < 6.0F && f > 0.0F) {
                                        if (Frag_Home.this.getActivity() != null) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder();
                                            this((Context)Frag_Home.this.getActivity());
                                            builder.setTitle("Thbhsd);
                                                    StringBuilder stringBuilder1 = new StringBuilder();
                                            this();
                                            stringBuilder1.append("Hsdcl");
                                            stringBuilder1.append((int)f);
                                            stringBuilder1.append(" ng\nHlihlhoS0936.023.645 gia h);
                                                    builder.setMessage(stringBuilder1.toString());
                                            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                                                    param2DialogInterface.cancel();
                                                }
                                            };
                                            super(this);
                                            builder.setNegativeButton(", onClickListener);
                                                    builder.create().show();
                                        }
                                    } else if (f < 0.0F && Frag_Home.this.getActivity() != null) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder();
                                        this((Context)Frag_Home.this.getActivity());
                                        builder.setTitle("Thbhsd);
                                                builder.setMessage("hhsdphm\n\nHlihlhoS0936.023.645 gia h);
                                                        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                                                                param2DialogInterface.cancel();
                                                            }
                                                        };
                                        super(this);
                                        builder.setNegativeButton(", onClickListener);
                                                builder.create().show();
                                    }
                                } catch (ParseException parseException) {
                                    parseException.printStackTrace();
                                }
                            } catch (JSONException null) {}
                        } catch (JSONException jSONException) {}
                        jSONException.printStackTrace();
                    }
                };
                super(this);
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError param1VolleyError) {}
                };
                super(this);
                super(this, 1, str, listener, errorListener);
                Volley.newRequestQueue((Context)getActivity()).add((Request)stringRequest);
            } catch (Exception exception) {
                Toast.makeText((Context)getActivity(), "Kitra knm, 1).show();
            }
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View view = paramLayoutInflater.inflate(2131427406, paramViewGroup, false);
        this.db = new Database((Context)getActivity());
        String str = (new MainActivity()).Get_link();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("json_date1.php");
        this.viewDate = stringBuilder.toString();
        this.tvTaiKhoan = (TextView)view.findViewById(2131231531);
        this.tvHansd = (TextView)view.findViewById(2131231466);
        this.edtImei = (TextView)view.findViewById(2131230957);
        this.tv_sodienthoai = (TextView)view.findViewById(2131231527);
        this.button_default = (Button)view.findViewById(2131230836);
        this.bt_trangchu = (Button)view.findViewById(2131230799);
        this.db = new Database((Context)getActivity());
        Kiemtra();
        this.bt_trangchu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.ldpro.me"));
                Frag_Home.this.startActivity(intent);
            }
        });
        this.button_default.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                MainActivity.myDate = "";
                Frag_Home.this.Kiemtra();
                if (MainActivity.myDate.length() > 5) {
                    FragmentActivity fragmentActivity = Frag_Home.this.getActivity();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Sd");
                    stringBuilder.append(MainActivity.myDate);
                    Toast.makeText((Context)fragmentActivity, stringBuilder.toString(), 1).show();
                }
            }
        });
        return view;
    }
}
