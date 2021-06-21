package tamhoang.ldpro4.Fragment;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import tamhoang.ldpro4.Activity.Activity_AddKH;
import tamhoang.ldpro4.Activity.Activity_AddKH2;
import tamhoang.ldpro4.data.Database;

public class Frag_Setting1 extends Fragment {
    Button btn_themKH;

    Database db;

    ListView lview;

    public List<String> mAddress = new ArrayList<String>();

    public List<String> mAppuse = new ArrayList<String>();

    public List<String> mDate = new ArrayList<String>();

    public List<String> mPerson = new ArrayList<String>();

    int mPoint;

    int mPoistion = 0;

    public List<Integer> mtype = new ArrayList<Integer>();

    TextView tv_Sodt;

    TextView tv_tenKH;

    TextView tv_xoaKH;

    int type = 1;

    View v;

    public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        String str1 = null;
        String str2 = null;
        if (paramInt2 == -1) {
            if (paramInt1 == 1)
                try {
                    Uri uri = paramIntent.getData();
                    ContentResolver contentResolver = getActivity().getContentResolver();
                    Cursor cursor = contentResolver.query(uri, null, null, null, null);
                    if (cursor.getCount() > 0) {
                        while (cursor.moveToNext()) {
                            str1 = cursor.getString(cursor.getColumnIndex("_id"));
                            str2 = cursor.getString(cursor.getColumnIndex("display_name"));
                            cursor.getString(cursor.getColumnIndex("display_name"));
                            if (Integer.parseInt(cursor.getString(cursor.getColumnIndex("has_phone_number"))) <= 0 || Integer.parseInt(cursor.getString(cursor.getColumnIndex("has_phone_number"))) <= 0) {
                                str1 = null;
                                continue;
                            }
                            Cursor cursor1 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = ?", new String[] { str1 }, null);
                            str1 = null;
                            uri = null;
                            try {
                                while (true) {
                                    boolean bool = cursor1.moveToNext();
                                    if (bool) {
                                        try {
                                            if (cursor1.getInt(cursor1.getColumnIndex("data2")) != 2)
                                                continue;
                                            String str = cursor1.getString(cursor1.getColumnIndex("data1"));
                                        } catch (Exception exception1) {

                                        } finally {
                                            String str;
                                            uri = null;
                                        }
                                        continue;
                                    }
                                    if (cursor1 != null)
                                        cursor1.close();
                                }
                            } catch (Exception exception) {
                                // Byte code: goto -> 435
                            }
                        }
                        if (exception.length() > 0) {
                            String str3 = exception.replaceAll(" ", "");
                            String str4 = str3;
                            String str5 = str3;
                            try {
                                String str;
                                if (str3.length() < 12) {
                                    str5 = str3;
                                    StringBuilder stringBuilder = new StringBuilder();
                                    str5 = str3;
                                    this();
                                    str5 = str3;
                                    stringBuilder.append("+84");
                                    str5 = str3;
                                    stringBuilder.append(str3.substring(1));
                                    str5 = str3;
                                    str = stringBuilder.toString();
                                }
                                str5 = str;
                                this.tv_Sodt.setText(str);
                                str5 = str;
                                this.tv_tenKH.setText(str2);
                            } catch (Exception exception1) {}
                        }
                    }
                } catch (Exception exception) {}
            getActivity().setResult(-1, paramIntent);
            return;
        }
    }

    public boolean onContextItemSelected(MenuItem paramMenuItem) {
        Intent intent;
        super.onContextItemSelected(paramMenuItem);
        if (paramMenuItem.getItemId() == 1) {
            intent = new Intent((Context)getActivity(), Activity_AddKH.class);
            intent.putExtra("tenKH", this.mPerson.get(this.mPoistion));
            intent.putExtra("kh_new", "");
            intent.putExtra("use_app", this.mAppuse.get(this.mPoistion));
            startActivity(intent);
        } else if (intent.getItemId() == 2) {
            intent = new Intent((Context)getActivity(), Activity_AddKH2.class);
            intent.putExtra("tenKH", this.mPerson.get(this.mPoistion));
            startActivity(intent);
        }
        return true;
    }

    public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo) {
        super.onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
        paramContextMenu.add(0, 1, 0, "Clgi);
                paramContextMenu.add(0, 2, 0, "Cthgian, gi%");
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.v = paramLayoutInflater.inflate(2131427426, paramViewGroup, false);
        this.db = new Database((Context)getActivity());
        this.lview = (ListView)this.v.findViewById(2131231099);
        Button button = (Button)this.v.findViewById(2131230830);
        this.btn_themKH = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                Intent intent = new Intent((Context)Frag_Setting1.this.getActivity(), Activity_AddKH.class);
                intent.putExtra("tenKH", "");
                intent.putExtra("use_app", "sms");
                intent.putExtra("kh_new", "");
                Frag_Setting1.this.startActivity(intent);
            }
        });
        this.lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_Setting1.this.mPoistion = param1Int;
                Frag_Setting1.this.lview.showContextMenuForChild(param1View);
            }
        });
        this.lview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_Setting1.this.mPoistion = param1Int;
                return false;
            }
        });
        xem_lv();
        registerForContextMenu((View)this.lview);
        return this.v;
    }

    public void onResume() {
        xem_lv();
        super.onResume();
    }

    public void xem_lv() {
        this.mAddress.clear();
        this.mPerson.clear();
        this.mtype.clear();
        this.mAppuse.clear();
        Cursor cursor = this.db.GetData("select * from tbl_kh_new Order by type_kh DESC, ten_kh");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                this.mPerson.add(cursor.getString(0));
                this.mAddress.add(cursor.getString(1));
                this.mtype.add(Integer.valueOf(cursor.getInt(3)));
                this.mAppuse.add(cursor.getString(2));
            }
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        if (getActivity() != null)
            this.lview.setAdapter((ListAdapter)new KHAdapter((Context)getActivity(), 2131427427, this.mPerson));
    }

    class KHAdapter extends ArrayAdapter {
        public KHAdapter(Context param1Context, int param1Int, List<String> param1List) {
            super(param1Context, param1Int, param1List);
        }

        public View getView(final int position, View param1View, ViewGroup param1ViewGroup) {
            param1View = ((Activity)getContext()).getLayoutInflater().inflate(2131427427, null);
            TextView textView = (TextView)param1View.findViewById(2131231275);
            textView.setText(Frag_Setting1.this.mPerson.get(position));
            ((TextView)param1View.findViewById(2131231274)).setText(Frag_Setting1.this.mAddress.get(position));
            Frag_Setting1.this.tv_xoaKH = (TextView)param1View.findViewById(2131231543);
            Frag_Setting1.this.tv_xoaKH.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param2View) {
                    AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_Setting1.this.getActivity());
                    builder.setTitle("XoKh);
                            StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Xob");
                    stringBuilder.append(Frag_Setting1.this.mPerson.get(position));
                    stringBuilder.append(" ra khdanh s);
                            builder.setMessage(stringBuilder.toString());
                    builder.setNegativeButton("C, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface param3DialogInterface, int param3Int) {
                        Database database2 = Frag_Setting1.this.db;
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("Delete FROM tbl_kh_new where ten_kh = '");
                        stringBuilder3.append(Frag_Setting1.this.mPerson.get(position));
                        stringBuilder3.append("'");
                        database2.QueryData(stringBuilder3.toString());
                        Database database3 = Frag_Setting1.this.db;
                        StringBuilder stringBuilder1 = new StringBuilder();
                        stringBuilder1.append("Delete FROM tbl_tinnhanS where ten_kh = '");
                        stringBuilder1.append(Frag_Setting1.this.mPerson.get(position));
                        stringBuilder1.append("'");
                        database3.QueryData(stringBuilder1.toString());
                        Database database1 = Frag_Setting1.this.db;
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Delete FROM tbl_soctS where ten_kh = '");
                        stringBuilder2.append(Frag_Setting1.this.mPerson.get(position));
                        stringBuilder2.append("'");
                        database1.QueryData(stringBuilder2.toString());
                        database1 = Frag_Setting1.this.db;
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Delete FROM tbl_chuyenthang where kh_nhan = '");
                        stringBuilder2.append(Frag_Setting1.this.mPerson.get(position));
                        stringBuilder2.append("'");
                        database1.QueryData(stringBuilder2.toString());
                        database1 = Frag_Setting1.this.db;
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Delete FROM tbl_chuyenthang where kh_chuyen = '");
                        stringBuilder2.append(Frag_Setting1.this.mPerson.get(position));
                        stringBuilder2.append("'");
                        database1.QueryData(stringBuilder2.toString());
                        Frag_Setting1.this.db.LayDanhsachKH();
                        Frag_Setting1.this.xem_lv();
                        param3DialogInterface.dismiss();
                        Toast.makeText((Context)Frag_Setting1.this.getActivity(), "Xothc, 1).show();
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
      if (((Integer)Frag_Setting1.this.mtype.get(position)).intValue() != 1)
            textView.setTextColor(-16776961);
      return param1View;
}
  }

class null implements View.OnClickListener {
public void onClick(View param1View) {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)Frag_Setting1.this.getActivity());
        builder.setTitle("XoKh);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Xob");
        stringBuilder.append(Frag_Setting1.this.mPerson.get(position));
        stringBuilder.append(" ra khdanh s);
        builder.setMessage(stringBuilder.toString());
        builder.setNegativeButton("C, new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param3DialogInterface, int param3Int) {
        Database database2 = Frag_Setting1.this.db;
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Delete FROM tbl_kh_new where ten_kh = '");
        stringBuilder3.append(Frag_Setting1.this.mPerson.get(position));
        stringBuilder3.append("'");
        database2.QueryData(stringBuilder3.toString());
        Database database3 = Frag_Setting1.this.db;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Delete FROM tbl_tinnhanS where ten_kh = '");
        stringBuilder1.append(Frag_Setting1.this.mPerson.get(position));
        stringBuilder1.append("'");
        database3.QueryData(stringBuilder1.toString());
        Database database1 = Frag_Setting1.this.db;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Delete FROM tbl_soctS where ten_kh = '");
        stringBuilder2.append(Frag_Setting1.this.mPerson.get(position));
        stringBuilder2.append("'");
        database1.QueryData(stringBuilder2.toString());
        database1 = Frag_Setting1.this.db;
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Delete FROM tbl_chuyenthang where kh_nhan = '");
        stringBuilder2.append(Frag_Setting1.this.mPerson.get(position));
        stringBuilder2.append("'");
        database1.QueryData(stringBuilder2.toString());
        database1 = Frag_Setting1.this.db;
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Delete FROM tbl_chuyenthang where kh_chuyen = '");
        stringBuilder2.append(Frag_Setting1.this.mPerson.get(position));
        stringBuilder2.append("'");
        database1.QueryData(stringBuilder2.toString());
        Frag_Setting1.this.db.LayDanhsachKH();
        Frag_Setting1.this.xem_lv();
        param3DialogInterface.dismiss();
        Toast.makeText((Context)Frag_Setting1.this.getActivity(), "Xothc, 1).show();
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
        Database database2 = Frag_Setting1.this.db;
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Delete FROM tbl_kh_new where ten_kh = '");
        stringBuilder3.append(Frag_Setting1.this.mPerson.get(position));
        stringBuilder3.append("'");
        database2.QueryData(stringBuilder3.toString());
        Database database3 = Frag_Setting1.this.db;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Delete FROM tbl_tinnhanS where ten_kh = '");
        stringBuilder1.append(Frag_Setting1.this.mPerson.get(position));
        stringBuilder1.append("'");
        database3.QueryData(stringBuilder1.toString());
        Database database1 = Frag_Setting1.this.db;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Delete FROM tbl_soctS where ten_kh = '");
        stringBuilder2.append(Frag_Setting1.this.mPerson.get(position));
        stringBuilder2.append("'");
        database1.QueryData(stringBuilder2.toString());
        database1 = Frag_Setting1.this.db;
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Delete FROM tbl_chuyenthang where kh_nhan = '");
        stringBuilder2.append(Frag_Setting1.this.mPerson.get(position));
        stringBuilder2.append("'");
        database1.QueryData(stringBuilder2.toString());
        database1 = Frag_Setting1.this.db;
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Delete FROM tbl_chuyenthang where kh_chuyen = '");
        stringBuilder2.append(Frag_Setting1.this.mPerson.get(position));
        stringBuilder2.append("'");
        database1.QueryData(stringBuilder2.toString());
        Frag_Setting1.this.db.LayDanhsachKH();
        Frag_Setting1.this.xem_lv();
        param1DialogInterface.dismiss();
        Toast.makeText((Context)Frag_Setting1.this.getActivity(), "Xothc, 1).show();
        }
        }

class null implements DialogInterface.OnClickListener {
public void onClick(DialogInterface param1DialogInterface, int param1Int) {
        param1DialogInterface.dismiss();
        }
        }
        }
