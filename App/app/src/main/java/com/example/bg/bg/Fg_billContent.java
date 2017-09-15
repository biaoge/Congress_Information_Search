package com.example.bg.bg;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbbia on 11/22/2016.
 */

public class Fg_billContent extends Fragment implements AdapterView.OnItemClickListener{

    private ListView list_bill_act;
    private BillAdapter billactadapter;
    private ArrayList<Bill> fg_bill_act;

    private ListView list_bill_new;
    private BillAdapter billnewadapter;
    private ArrayList<Bill> fg_bill_new;

    private Context mContext;
    private ArrayList<Bill> fav_bill;

    public static Fg_billContent newInstance (ArrayList<Bill> fg_bill_act, ArrayList<Bill> fg_bill_new, ArrayList<Bill> fav_bill, Context mContext)
    {
        Fg_billContent newFragment = new Fg_billContent();
//        Bundle bundle = new Bundle();
//        bundle.putString("content", content);
//        newFragment.setArguments(bundle);
        newFragment.fg_bill_act = fg_bill_act;
        newFragment.fg_bill_new = fg_bill_new;
        newFragment.fav_bill = fav_bill;
        newFragment.mContext = mContext;
        return  newFragment;
    }


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fg_bills_content, container, false);
//        TextView txt_content = (TextView) view.findViewById(R.id.bill_content);
//        txt_content.setText(content);

//     初始化bill内容
        TabHost ltabHost = (TabHost) view.findViewById(R.id.btabhost);
        ltabHost.setup();
//      By States 选项卡
        TabHost.TabSpec spec1 = ltabHost.newTabSpec("btab1");
        spec1.setContent(R.id.btab01);
        spec1.setIndicator("ACTIVE BILLS");
        ltabHost.addTab(spec1);
//      House 选项卡
        TabHost.TabSpec spec2 = ltabHost.newTabSpec("ltab2");
        spec2.setContent(R.id.btab02);
        spec2.setIndicator("NEW BILLS");
        ltabHost.addTab(spec2);

//      actbills的listview
        list_bill_act = (ListView) view.findViewById(R.id.list_bill_act);
        billactadapter = new BillAdapter((ArrayList<Bill>)fg_bill_act, mContext);
        list_bill_act.setAdapter(billactadapter);
        list_bill_act.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(mContext, BillDetailAvtivity.class);

                if (fav_bill.size()> 0 )
                {
                    for (int i = 0; i < fav_bill.size(); i++)
                    {
                        if (fav_bill.get(i).getBill_id().equals(fg_bill_act.get(position).getBill_id())) {
                            intent.putExtra("isfav", "y");
                            break;
                        }
                        else
                            intent.putExtra("isfav", "n");
                    }
                }
                else
                intent.putExtra("isfav", "n");

                intent.putExtra("bill_id", fg_bill_act.get(position).getBill_id().toUpperCase());
                intent.putExtra("bill_title", fg_bill_act.get(position).getBilltitle());
                intent.putExtra("bill_type", fg_bill_act.get(position).getBill_type().toUpperCase());
                intent.putExtra("bill_sponsor", fg_bill_act.get(position).getSponsorNmae());
                intent.putExtra("bill_chamber", fg_bill_act.get(position).getChamber());
                intent.putExtra("bill_status", "true");
                intent.putExtra("bill_introduced_on", fg_bill_act.get(position).getIntroducedonDate());
                intent.putExtra("bill_congressurl", fg_bill_act.get(position).getCongressUrl());
                intent.putExtra("bill_versionstatus", fg_bill_act.get(position).getVersionstatus());
                intent.putExtra("bill_billurl", fg_bill_act.get(position).getBillUrl());

                startActivity(intent);

            }
        });

//      newbills的listview
        list_bill_new = (ListView) view.findViewById(R.id.list_bill_new);
        billnewadapter = new BillAdapter((ArrayList<Bill>)fg_bill_new, mContext);
        list_bill_new.setAdapter(billnewadapter);
        list_bill_new.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(mContext, BillDetailAvtivity.class);

                if (fav_bill.size()> 0 )
                {
                    for (int i = 0; i < fav_bill.size(); i++)
                    {
                        if (fav_bill.get(i).getBill_id().equals(fg_bill_new.get(position).getBill_id())) {
                            intent.putExtra("isfav", "y");
                            break;
                        }
                        else
                            intent.putExtra("isfav", "n");
                    }
                }
                else
                    intent.putExtra("isfav", "n");

                intent.putExtra("bill_id", fg_bill_new.get(position).getBill_id().toUpperCase());
                intent.putExtra("bill_title", fg_bill_new.get(position).getBilltitle());
                intent.putExtra("bill_type", fg_bill_new.get(position).getBill_type().toUpperCase());
                intent.putExtra("bill_sponsor", fg_bill_new.get(position).getSponsorNmae());
                intent.putExtra("bill_chamber", fg_bill_new.get(position).getChamber());
                intent.putExtra("bill_status", "false");
                intent.putExtra("bill_introduced_on", fg_bill_new.get(position).getIntroducedonDate());
                intent.putExtra("bill_congressurl", fg_bill_new.get(position).getCongressUrl());
                intent.putExtra("bill_versionstatus", fg_bill_new.get(position).getVersionstatus());
                intent.putExtra("bill_billurl", fg_bill_new.get(position).getBillUrl());

                startActivity(intent);

            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
