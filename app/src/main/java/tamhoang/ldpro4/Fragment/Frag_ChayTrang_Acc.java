package tamhoang.ldpro4.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import tamhoang.ldpro4.Activity.Activity_AccWeb;
import tamhoang.ldpro4.data.Database;

public class Frag_ChayTrang_Acc extends Fragment {
    public List<String> Account = new ArrayList<String>();

    Button btn_them_trang;

    Database db;

    ListView lv_account;

    View v;

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View view = paramLayoutInflater.inflate(2131427399, paramViewGroup, false);
        this.v = view;
        this.btn_them_trang = (Button)view.findViewById(2131230831);
        this.lv_account = (ListView)this.v.findViewById(2131231091);
        this.db = new Database((Context)getActivity());
        this.btn_them_trang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                Intent intent = new Intent((Context)Frag_ChayTrang_Acc.this.getActivity(), Activity_AccWeb.class);
                intent.putExtra("new_web", "");
                Frag_ChayTrang_Acc.this.startActivity(intent);
            }
        });
        xem_lv();
        return this.v;
    }

    public void onResume() {
        super.onResume();
        xem_lv();
    }

    public void xem_lv() {
        this.Account.clear();
        Cursor cursor = this.db.GetData("select * from tbl_chaytrang_acc");
        if (cursor != null) {
            while (cursor.moveToNext())
                this.Account.add(cursor.getString(0));
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        if (getActivity() != null)
            this.lv_account.setAdapter((ListAdapter)new KHAdapter((Context)getActivity(), 2131427427, this.Account));
    }

    class KHAdapter extends ArrayAdapter {
        public KHAdapter(Context param1Context, int param1Int, List<String> param1List) {
            super(param1Context, param1Int, param1List);
        }

        public View getView(final int position, View param1View, ViewGroup param1ViewGroup) {
            param1View = ((Activity)getContext()).getLayoutInflater().inflate(2131427400, null);
            ((TextView)param1View.findViewById(2131231397)).setText(Frag_ChayTrang_Acc.this.Account.get(position));
            ((TextView)param1View.findViewById(2131231464)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View param2View) {
                    AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_ChayTrang_Acc.this.getActivity());
                    builder.setTitle("Sthtin");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Sthtin trang ");
                    stringBuilder.append(Frag_ChayTrang_Acc.this.Account.get(position));
                    stringBuilder.append("?");
                    builder.setMessage(stringBuilder.toString());
                    builder.setNegativeButton("C, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface param3DialogInterface, int param3Int) {
                        Intent intent = new Intent((Context)Frag_ChayTrang_Acc.this.getActivity(), Activity_AccWeb.class);
                        intent.putExtra("new_web", Frag_ChayTrang_Acc.this.Account.get(position));
                        intent.putExtra("kh_new", "");
                        Frag_ChayTrang_Acc.this.startActivity(intent);
                    }
                });
              builder.setPositiveButton("Kh, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface param3DialogInterface, int param3Int) {
                    param3DialogInterface.dismiss();
                }
            });
            builder.show();
        }
    });
      ((TextView)param1View.findViewById(2131231419)).setOnClickListener(new View.OnClickListener() {
        public void onClick(View param2View) {
            AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_ChayTrang_Acc.this.getActivity());
            builder.setTitle("Xotkho);
                    StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Xo");
            stringBuilder.append(Frag_ChayTrang_Acc.this.Account.get(position));
            stringBuilder.append(" ra khdanh s);
                    builder.setMessage(stringBuilder.toString());
            builder.setNegativeButton("C, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param3DialogInterface, int param3Int) {
                Database database = Frag_ChayTrang_Acc.this.db;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Delete FROM tbl_chaytrang_acc where Username = '");
                stringBuilder.append(Frag_ChayTrang_Acc.this.Account.get(position));
                stringBuilder.append("'");
                database.QueryData(stringBuilder.toString());
                Frag_ChayTrang_Acc.this.xem_lv();
                param3DialogInterface.dismiss();
                Toast.makeText((Context)Frag_ChayTrang_Acc.this.getActivity(), "Xothc, 1).show();
            }
        });
        builder.setPositiveButton("Kh, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface param3DialogInterface, int param3Int) {
            param3DialogInterface.dismiss();
        }
    });
              builder.show();
}
          });
                  return param1View;
                  }
                  }

class null implements View.OnClickListener {
public void onClick(View param1View) {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_ChayTrang_Acc.this.getActivity());
        builder.setTitle("Sthtin");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Sthtin trang ");
        stringBuilder.append(Frag_ChayTrang_Acc.this.Account.get(position));
        stringBuilder.append("?");
        builder.setMessage(stringBuilder.toString());
        builder.setNegativeButton("C, new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param3DialogInterface, int param3Int) {
        Intent intent = new Intent((Context)Frag_ChayTrang_Acc.this.getActivity(), Activity_AccWeb.class);
        intent.putExtra("new_web", Frag_ChayTrang_Acc.this.Account.get(position));
        intent.putExtra("kh_new", "");
        Frag_ChayTrang_Acc.this.startActivity(intent);
        }
        });
        builder.setPositiveButton("Kh, new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param3DialogInterface, int param3Int) {
        param3DialogInterface.dismiss();
        }
        });
        builder.show();
        }
        }

class null implements DialogInterface.OnClickListener {
public void onClick(DialogInterface param1DialogInterface, int param1Int) {
        Intent intent = new Intent((Context)Frag_ChayTrang_Acc.this.getActivity(), Activity_AccWeb.class);
        intent.putExtra("new_web", Frag_ChayTrang_Acc.this.Account.get(position));
        intent.putExtra("kh_new", "");
        Frag_ChayTrang_Acc.this.startActivity(intent);
        }
        }

class null implements DialogInterface.OnClickListener {
public void onClick(DialogInterface param1DialogInterface, int param1Int) {
        param1DialogInterface.dismiss();
        }
        }

class null implements View.OnClickListener {
public void onClick(View param1View) {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_ChayTrang_Acc.this.getActivity());
        builder.setTitle("Xotkho);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Xo");
        stringBuilder.append(Frag_ChayTrang_Acc.this.Account.get(position));
        stringBuilder.append(" ra khdanh s);
        builder.setMessage(stringBuilder.toString());
        builder.setNegativeButton("C, new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param3DialogInterface, int param3Int) {
        Database database = Frag_ChayTrang_Acc.this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Delete FROM tbl_chaytrang_acc where Username = '");
        stringBuilder.append(Frag_ChayTrang_Acc.this.Account.get(position));
        stringBuilder.append("'");
        database.QueryData(stringBuilder.toString());
        Frag_ChayTrang_Acc.this.xem_lv();
        param3DialogInterface.dismiss();
        Toast.makeText((Context)Frag_ChayTrang_Acc.this.getActivity(), "Xothc, 1).show();
        }
        });
        builder.setPositiveButton("Kh, new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param3DialogInterface, int param3Int) {
        param3DialogInterface.dismiss();
        }
        });
        builder.show();
        }
        }

class null implements DialogInterface.OnClickListener {
public void onClick(DialogInterface param1DialogInterface, int param1Int) {
        Database database = Frag_ChayTrang_Acc.this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Delete FROM tbl_chaytrang_acc where Username = '");
        stringBuilder.append(Frag_ChayTrang_Acc.this.Account.get(position));
        stringBuilder.append("'");
        database.QueryData(stringBuilder.toString());
        Frag_ChayTrang_Acc.this.xem_lv();
        param1DialogInterface.dismiss();
        Toast.makeText((Context)Frag_ChayTrang_Acc.this.getActivity(), "Xothc, 1).show();
        }
        }

class null implements DialogInterface.OnClickListener {
public void onClick(DialogInterface param1DialogInterface, int param1Int) {
        param1DialogInterface.dismiss();
        }
        }
        }
