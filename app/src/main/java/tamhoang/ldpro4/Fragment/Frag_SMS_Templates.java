package tamhoang.ldpro4.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import tamhoang.ldpro4.data.Database;

public class Frag_SMS_Templates extends Fragment {
    String Chu_y1 = "Csphgnhau hocbd, hod. ";

    Database db;

    ArrayList<HashMap<String, String>> formArray = new ArrayList<HashMap<String, String>>();

    TextView giaithich;

    ListView lv_Template;

    public List<String> mGiaiThich = new ArrayList<String>();

    public List<String> mNoiDung = new ArrayList<String>();

    TextView mauTin;

    private void addtoListview() {
        this.mNoiDung.clear();
        this.mGiaiThich.clear();
        this.mNoiDung.add("Vitcd);
        this.mGiaiThich.add("dea: 2 sgi(2 scugi8 scuginhtrth80.000 (8/ 8)dec: 2 sginh(nhnh2 scuginh(nhnh);
        this.mNoiDung.add(");
        this.mGiaiThich.add("2 sgibi);
        this.mNoiDung.add("nh);
        this.mGiaiThich.add("2 sginh);
        this.mNoiDung.add("nh);
        this.mGiaiThich.add("2 scuginh);
        this.mNoiDung.add("Tchia 3");
        this.mGiaiThich.add("Cschia hcho 3");
        this.mNoiDung.add("Chia 3 d1");
        this.mGiaiThich.add("Cschia cho 3 d1");
        this.mNoiDung.add("Chia 3 d2");
        this.mGiaiThich.add("Cschia cho 3 d2");
        this.mNoiDung.add("Khchia 3");
        this.mGiaiThich.add("Cskhchia hcho 3");
        this.mNoiDung.add("Ttr10");
        this.mGiaiThich.add("Csctlh10");
        this.mNoiDung.add("Td10");
        this.mGiaiThich.add("Csctbh10");
        this.mNoiDung.add("T10");
        this.mGiaiThich.add("Phmsblvkhct10, chct0 hot1 v0 thghi t01");
        this.mNoiDung.add("xg2 010,030,78,89,60 x 10");
        this.mGiaiThich.add("Phmstghxi2 ctccsvnhau, hkitra cthcsginhau khi phmbl);
        this.mNoiDung.add("xg3 010,030,78,89,60 x 10");
        this.mGiaiThich.add("Phmstghxi3 ctccsvnhau, hkitra cthcsginhau khi phmbl);
        this.mNoiDung.add("xg4 010,030,78,89,60 x 10");
        this.mGiaiThich.add("Phmstghxi4 ctccsvnhau, hkitra cthcsginhau khi phmbl);
        this.mNoiDung.add("De dan 18 bor kep x 10");
        this.mGiaiThich.add("Chbo c2 nghlbvbnchbphthch'r' thbor");
        this.mNoiDung.add("De boj 02,04 x 10");
        this.mGiaiThich.add("Chbo c2 nghlbvbnchbphthch'j' thboj");
        this.mNoiDung.add("de giap ty x 100, de giap chux 100");
        this.mGiaiThich.add("Ccon gisghi bcvigiap + tcon gi);
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View view = paramLayoutInflater.inflate(2131427434, paramViewGroup, false);
        this.lv_Template = (ListView)view.findViewById(2131231101);
        this.mauTin = (TextView)view.findViewById(2131231469);
        this.giaithich = (TextView)view.findViewById(2131231465);
        addtoListview();
        xem_lv();
        this.lv_Template.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_SMS_Templates.this.mauTin.setText(Frag_SMS_Templates.this.mNoiDung.get(param1Int));
                Frag_SMS_Templates.this.giaithich.setText(Frag_SMS_Templates.this.mGiaiThich.get(param1Int));
            }
        });
        return view;
    }

    public void xem_lv() {
        this.lv_Template.setAdapter((ListAdapter)new TNGAdapter((Context)getActivity(), 2131427433, this.mNoiDung));
    }

    class TNGAdapter extends ArrayAdapter {
        public TNGAdapter(Context param1Context, int param1Int, List<String> param1List) {
            super(param1Context, param1Int, param1List);
        }

        public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
            param1View = ((Activity)getContext()).getLayoutInflater().inflate(2131427433, null);
            ((TextView)param1View.findViewById(2131231524)).setText(Frag_SMS_Templates.this.mNoiDung.get(param1Int));
            return param1View;
        }
    }
}
