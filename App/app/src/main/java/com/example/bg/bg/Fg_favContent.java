package com.example.bg.bg;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bbbia on 11/22/2016.
 */

public class Fg_favContent extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener{

//    private String content;
    private Context mContext;


    private ArrayList<Legislator> flegislators;
    private ArrayList<Bill> fbills;
    private ArrayList<Committee> fcomms;

    private ListView list_fcomm;
    private CommAdapter commfadapter;

    private ListView list_fbill;
    private BillAdapter billfadapter;

    private ListView list_flegislator;
    private LegiAdapter legifadapter;
    private Map<String, Integer>mapIndexFavorite;


    public static Fg_favContent newInstance(ArrayList<Legislator>flegislators, ArrayList<Bill>fbills, ArrayList<Committee>fcomms, Context mContext)
    {
        Fg_favContent newFragment = new Fg_favContent();
//        Bundle bundle = new Bundle();
//        bundle.putString("content", content);
//        newFragment.setArguments(bundle);
//        newFragment.content = content;
        newFragment.flegislators = flegislators;
        newFragment.fbills = fbills;
        newFragment.fcomms = fcomms;
        newFragment.mContext = mContext;
        return  newFragment;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fg_fav_content, container, false);
//        TextView txt_content = (TextView) view.findViewById(R.id.fav_content);
//        txt_content.setText(content);

 //     初始化favorite内容
        TabHost ltabHost = (TabHost) view.findViewById(R.id.ftabhost);
        ltabHost.setup();
//      By States 选项卡
        TabHost.TabSpec spec1 = ltabHost.newTabSpec("ftab1");
        spec1.setContent(R.id.ftab01);
        spec1.setIndicator("LEGISLATORS");
        ltabHost.addTab(spec1);
//      House 选项卡
        TabHost.TabSpec spec2 = ltabHost.newTabSpec("ftab2");
        spec2.setContent(R.id.ftab02);
        spec2.setIndicator("BILLS");
        ltabHost.addTab(spec2);
//      Senate 选项卡
        TabHost.TabSpec spec3 = ltabHost.newTabSpec("ftab3");
        spec3.setContent(R.id.ftab03);
        spec3.setIndicator("COMMITTEES");
        ltabHost.addTab(spec3);


        if (flegislators.size() != 0)
        {
            list_flegislator = (ListView) view.findViewById(R.id.list_flegi);
            legifadapter = new LegiAdapter((ArrayList<Legislator>)flegislators, mContext);
            list_flegislator.setAdapter(legifadapter);
//          favorite legislator的sideindex
            mapIndexFavorite = new LinkedHashMap<String, Integer>();
            for (int i = 0; i < flegislators.size(); i++)
            {
                String lastname = flegislators.get(i).getLast_name();
                String indexLname = lastname.substring(0,1);
                if (mapIndexFavorite.get(indexLname) == null)
                    mapIndexFavorite.put(indexLname,i);
            }
            LinearLayout indexLayoutFavorite = (LinearLayout) view.findViewById(R.id.legi_fav_sideindex);
            TextView textView;
            List<String> indexList = new ArrayList<String>(mapIndexFavorite.keySet());
            for (String index: indexList){
                textView = (TextView) inflater.inflate(R.layout.side_index_item, null);
                textView.setText(index);
                textView.setOnClickListener(this);
                indexLayoutFavorite.addView(textView);
            }

            list_flegislator.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Bundle bd = new Bundle();

                    bd.putString("legi_id",flegislators.get(position).getBioguide_id());
                    bd.putString("legi_facebook", flegislators.get(position).getFacebook_id());
                    bd.putString("legi_twitter", flegislators.get(position).getTwitter_id());
                    bd.putString("legi_website",flegislators.get(position).getWebsite());

                    bd.putString("legi_party", flegislators.get(position).getPartyName());

                    bd.putString("legi_name", flegislators.get(position).getLegislatorName());
                    bd.putString("legi_email", flegislators.get(position).getOc_email());
                    bd.putString("legi_chamber", flegislators.get(position).getLegislatorChamber());
                    bd.putString("legi_contact", flegislators.get(position).getPhone());
                    bd.putString("legi_start", flegislators.get(position).getStartTerm());
                    bd.putString("legi_end", flegislators.get(position).getEndTerm());

                    bd.putInt("legi_termNum", flegislators.get(position).getTermInt());
                    bd.putString("legi_term", flegislators.get(position).getTerm());

                    bd.putString("legi_office", flegislators.get(position).getOffice());
                    bd.putString("legi_state", flegislators.get(position).getState());
                    bd.putString("legi_fax", flegislators.get(position).getFax());
                    bd.putString("legi_bday", flegislators.get(position).getBirthdayDate());
                    bd.putString("isfav", "y");

                    Intent intent = new Intent(mContext, LegiDetailActivity.class);
                    intent.putExtras(bd);
                    startActivity(intent);
                }
            });


        }

        if (fbills.size() !=0 )
        {
            list_fbill = (ListView) view.findViewById(R.id.list_fbill);
            billfadapter = new BillAdapter((ArrayList<Bill>)fbills, mContext);
            list_fbill.setAdapter(billfadapter);
            list_fbill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(mContext, BillDetailAvtivity.class);

                    intent.putExtra("isfav", "y");

                    intent.putExtra("bill_id", fbills.get(position).getBill_id().toUpperCase());
                    intent.putExtra("bill_title", fbills.get(position).getBilltitle());
                    intent.putExtra("bill_type", fbills.get(position).getBill_type().toUpperCase());
                    intent.putExtra("bill_sponsor", fbills.get(position).getSponsorNmae());
                    intent.putExtra("bill_chamber", fbills.get(position).getChamber());
                    if (fbills.get(position).getBillStatus())
                        intent.putExtra("bill_status", "true");
                    else
                        intent.putExtra("bill_status", "false");
                    intent.putExtra("bill_introduced_on", fbills.get(position).getIntroducedonDate());
                    intent.putExtra("bill_congressurl", fbills.get(position).getCongressUrl());
                    intent.putExtra("bill_versionstatus", fbills.get(position).getVersionstatus());
                    intent.putExtra("bill_billurl", fbills.get(position).getBillUrl());

                    startActivity(intent);
                }
            });
        }

        if (fcomms.size()!=0)
        {
            list_fcomm = (ListView) view.findViewById(R.id.list_fcomm);
            commfadapter = new CommAdapter((ArrayList<Committee>) fcomms, mContext);
            list_fcomm.setAdapter(commfadapter);
            list_fcomm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(mContext, CommDetailActivity.class);

                    intent.putExtra("isfav", "y");
                    intent.putExtra("comm_id", fcomms.get(position).getCommittee_id());
                    intent.putExtra("comm_name", fcomms.get(position).getName());
                    intent.putExtra("comm_chamber", fcomms.get(position).getChamber());
                    intent.putExtra("comm_parent_comm", fcomms.get(position).getParent_committee_id());
                    intent.putExtra("comm_contact", fcomms.get(position).getPhone());
                    intent.putExtra("comm_office",fcomms.get(position).getOffice());

                    startActivity(intent);
                }
            });
        }

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        TextView selectedIndex = (TextView) v;
        list_flegislator.setSelection(mapIndexFavorite.get(selectedIndex.getText()));
    }
}
