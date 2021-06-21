package tamhoang.ldpro4.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import org.json.JSONException;
import tamhoang.ldpro4.MainActivity;
import tamhoang.ldpro4.data.Database;

public class Frag_Setting3 extends Fragment {
    String[] bc_apman;

    String[] bor_tintrung;

    Database db;

    String[] dv_chuyenXien;

    String[] hetgio;

    String[] kytu;

    String[] lamtron;

    String[] luachonBCao;

    String[] mCanhbao;

    String[] mTachxien;

    String[] mTinThieu;

    String[] sapxepBaocao;

    Spinner sp_BC_apman;

    Spinner sp_ChuyenXien;

    Spinner sp_LuachonBC;

    Spinner sp_TinThieu;

    Spinner sp_bo_tintrung;

    Spinner sp_canhbao;

    Spinner sp_chotTachXien;

    Spinner sp_hetgio;

    Spinner sp_kytu;

    Spinner sp_lamtron;

    Spinner sp_sapxepbaocao;

    Spinner sp_trathuonglo;

    String[] trathuong_lo;

    View v;

    public void init() {
        this.sp_BC_apman = (Spinner)this.v.findViewById(2131231240);
        this.sp_bo_tintrung = (Spinner)this.v.findViewById(2131231246);
        this.sp_sapxepbaocao = (Spinner)this.v.findViewById(2131231256);
        this.sp_ChuyenXien = (Spinner)this.v.findViewById(2131231242);
        this.sp_LuachonBC = (Spinner)this.v.findViewById(2131231243);
        this.sp_lamtron = (Spinner)this.v.findViewById(2131231254);
        this.sp_trathuonglo = (Spinner)this.v.findViewById(2131231258);
        this.sp_kytu = (Spinner)this.v.findViewById(2131231253);
        this.sp_hetgio = (Spinner)this.v.findViewById(2131231250);
        this.sp_canhbao = (Spinner)this.v.findViewById(2131231247);
        this.sp_chotTachXien = (Spinner)this.v.findViewById(2131231248);
        this.sp_TinThieu = (Spinner)this.v.findViewById(2131231245);
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.v = paramLayoutInflater.inflate(2131427428, paramViewGroup, false);
        this.db = new Database((Context)getActivity());
        init();
        this.bc_apman = new String[] {
                "0 trth, "Nh1 l, "Nh2 l, "Nh3 l, "Nh4 l, "Nh5 l, "Nh6 l, "Nh7 l, "Nh8 l, "Nh9 l,
                "Nh10 l};
                ArrayAdapter arrayAdapter = new ArrayAdapter((Context)getActivity(), 2131427455, (Object[])this.bc_apman);
        this.sp_BC_apman.setAdapter((SpinnerAdapter)arrayAdapter);
        try {
            this.sp_BC_apman.setSelection(MainActivity.jSon_Setting.getInt("ap_man"));
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        this.sp_BC_apman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_Setting3.this.db.Save_Setting("ap_man", param1Int);
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        this.hetgio = new String[] { "1. Khnhhgi, "2. Nhbhgi};
        arrayAdapter = new ArrayAdapter((Context)getActivity(), 2131427455, (Object[])this.hetgio);
        this.sp_hetgio.setAdapter((SpinnerAdapter)arrayAdapter);
        try {
            this.sp_hetgio.setSelection(MainActivity.jSon_Setting.getInt("tin_qua_gio"));
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        this.sp_hetgio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_Setting3.this.db.Save_Setting("tin_qua_gio", param1Int);
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        this.bor_tintrung = new String[] { "1. Cnhtin tr, "2. Khnhtin tr};
        arrayAdapter = new ArrayAdapter((Context)getActivity(), 2131427455, (Object[])this.bor_tintrung);
        this.sp_bo_tintrung.setAdapter((SpinnerAdapter)arrayAdapter);
        try {
            this.sp_bo_tintrung.setSelection(MainActivity.jSon_Setting.getInt("tin_trung"));
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        this.sp_bo_tintrung.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_Setting3.this.db.Save_Setting("tin_trung", param1Int);
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        this.kytu = new String[] { "1. Khgih, "2. 160 kt, "3. 320 kt, "4. 480 kt, "5. 1000 kt, "6. 2000 kt(Zalo)" };
                arrayAdapter = new ArrayAdapter((Context)getActivity(), 2131427455, (Object[])this.kytu);
        this.sp_kytu.setAdapter((SpinnerAdapter)arrayAdapter);
        try {
            this.sp_kytu.setSelection(MainActivity.jSon_Setting.getInt("gioi_han_tin") - 1);
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        this.sp_kytu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_Setting3.this.db.Save_Setting("gioi_han_tin", param1Int + 1);
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        this.sapxepBaocao = new String[] { "1. Theo ttinh, "2. Theo ttit};
        arrayAdapter = new ArrayAdapter((Context)getActivity(), 2131427455, (Object[])this.sapxepBaocao);
        this.sp_sapxepbaocao.setAdapter((SpinnerAdapter)arrayAdapter);
        try {
            this.sp_sapxepbaocao.setSelection(MainActivity.jSon_Setting.getInt("bao_cao_so"));
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        this.sp_sapxepbaocao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_Setting3.this.db.Save_Setting("bao_cao_so", param1Int);
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        this.dv_chuyenXien = new String[] { "1. Chuytheo ti, "2. Chuytheo };
        arrayAdapter = new ArrayAdapter((Context)getActivity(), 2131427455, (Object[])this.dv_chuyenXien);
        this.sp_ChuyenXien.setAdapter((SpinnerAdapter)arrayAdapter);
        try {
            this.sp_ChuyenXien.setSelection(MainActivity.jSon_Setting.getInt("chuyen_xien"));
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        this.sp_ChuyenXien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_Setting3.this.db.Save_Setting("chuyen_xien", param1Int);
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        this.lamtron = new String[] { "1. Khltr, "2. Ltr10", "3. Ltr50", "4. Ltr100" };
                arrayAdapter = new ArrayAdapter((Context)getActivity(), 2131427455, (Object[])this.lamtron);
        this.sp_lamtron.setAdapter((SpinnerAdapter)arrayAdapter);
        try {
            this.sp_lamtron.setSelection(MainActivity.jSon_Setting.getInt("lam_tron"));
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        this.sp_lamtron.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_Setting3.this.db.Save_Setting("lam_tron", param1Int);
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        this.luachonBCao = new String[] { "1. Bckic, "2. Bckim};
        arrayAdapter = new ArrayAdapter((Context)getActivity(), 2131427455, (Object[])this.luachonBCao);
        this.sp_LuachonBC.setAdapter((SpinnerAdapter)arrayAdapter);
        try {
            this.sp_LuachonBC.setSelection(MainActivity.jSon_Setting.getInt("kieu_bao_cao"));
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        this.sp_LuachonBC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_Setting3.this.db.Save_Setting("kieu_bao_cao", param1Int);
                MainActivity mainActivity = (MainActivity)Frag_Setting3.this.getActivity();
                MainActivity.setListFragment(param1Int);
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        this.trathuong_lo = new String[] { "1. Tr, "2. Nhinh2 nh, "3. Nhinh3 nh, "4. Nhinh4 nh};
        arrayAdapter = new ArrayAdapter((Context)getActivity(), 2131427455, (Object[])this.trathuong_lo);
        this.sp_trathuonglo.setAdapter((SpinnerAdapter)arrayAdapter);
        try {
            this.sp_trathuonglo.setSelection(MainActivity.jSon_Setting.getInt("tra_thuong_lo"));
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        this.sp_trathuonglo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_Setting3.this.db.Save_Setting("tra_thuong_lo", param1Int);
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        this.mCanhbao = new String[] { "1. Khcb, "2. Ccb};
        arrayAdapter = new ArrayAdapter((Context)getActivity(), 2131427455, (Object[])this.mCanhbao);
        this.sp_canhbao.setAdapter((SpinnerAdapter)arrayAdapter);
        try {
            this.sp_canhbao.setSelection(MainActivity.jSon_Setting.getInt("canhbaodonvi"));
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        this.sp_canhbao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_Setting3.this.db.Save_Setting("canhbaodonvi", param1Int);
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        this.mTachxien = new String[] { "1. Khtxi, "2. Trixi2-3-4" };
                arrayAdapter = new ArrayAdapter((Context)getActivity(), 2131427455, (Object[])this.mTachxien);
        this.sp_chotTachXien.setAdapter((SpinnerAdapter)arrayAdapter);
        try {
            this.sp_chotTachXien.setSelection(MainActivity.jSon_Setting.getInt("tachxien_tinchot"));
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        this.sp_chotTachXien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_Setting3.this.db.Save_Setting("tachxien_tinchot", param1Int);
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        this.mTinThieu = new String[] { "1. Khbthitin", "2. Cbthitin" };
        arrayAdapter = new ArrayAdapter((Context)getActivity(), 2131427455, (Object[])this.mTinThieu);
        this.sp_TinThieu.setAdapter((SpinnerAdapter)arrayAdapter);
        try {
            this.sp_TinThieu.setSelection(MainActivity.jSon_Setting.getInt("baotinthieu"));
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }
        this.sp_TinThieu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                Frag_Setting3.this.db.Save_Setting("baotinthieu", param1Int);
            }

            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        });
        return this.v;
  }
    }
