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
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bbbia on 11/22/2016.
 */

public class Fg_legiContent extends Fragment implements AdapterView.OnItemClickListener,View.OnClickListener{

    private ListView list_legi;
    private LegiAdapter legiapater;
    private ArrayList<Legislator> fg_legislators;

    private ListView list_legi_house;
    private LegiAdapter legihadapter;
    private ArrayList<Legislator> fg_legislators_house;

    private ListView list_legi_senate;
    private LegiAdapter legisadapter;
    private ArrayList<Legislator> fg_legislators_senate;

    private Context mContext;
    private ArrayList<Legislator> fav_legislator;

//  处理side index的变量
    Map<String, Integer> mapIndexAll;
    Map<String, Integer> mapIndexHouse;
    Map<String, Integer> getMapIndexSenate;


    public static Fg_legiContent newInstance (ArrayList<Legislator> fg_legislators, ArrayList<Legislator>fg_legislators_house, ArrayList<Legislator>fg_legislators_senate, ArrayList<Legislator> fav_legislator, Context mContext)
    {
        Fg_legiContent newFragment = new Fg_legiContent();
        newFragment.fg_legislators = fg_legislators;
        newFragment.fg_legislators_house = fg_legislators_house;
        newFragment.fg_legislators_senate = fg_legislators_senate;
        newFragment.mContext = mContext;
        newFragment.fav_legislator = fav_legislator;
        return  newFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fg_legis_content, container, false);
//        TextView txt_content = (TextView) view.findViewById(R.id.legi_content);
//        txt_content.setText(content);

//     初始化legislator内容
        TabHost ltabHost = (TabHost) view.findViewById(R.id.ltabhost);
        ltabHost.setup();
//      By States 选项卡
        TabHost.TabSpec spec1 = ltabHost.newTabSpec("ltab1");
        spec1.setContent(R.id.ltab01);
        spec1.setIndicator("BY STATES");
        ltabHost.addTab(spec1);
//      House 选项卡
        TabHost.TabSpec spec2 = ltabHost.newTabSpec("ltab2");
        spec2.setContent(R.id.ltab02);
        spec2.setIndicator("HOUSE");
        ltabHost.addTab(spec2);
//      Senate 选项卡
        TabHost.TabSpec spec3 = ltabHost.newTabSpec("ltab3");
        spec3.setContent(R.id.ltab03);
        spec3.setIndicator("SENATE");
        ltabHost.addTab(spec3);

//      all States的legislator listview
        list_legi = (ListView) view.findViewById(R.id.legi_list);
        legiapater = new LegiAdapter((ArrayList<Legislator>)fg_legislators, mContext);
        list_legi.setAdapter(legiapater);

//      all states的sideindex
        mapIndexAll = new LinkedHashMap<String, Integer>();
        for (int i = 0 ; i < fg_legislators.size(); i++)
        {
            String stateAbbr = fg_legislators.get(i).getState();
            String indexState = stateAbbr.substring(0,1);

            if (mapIndexAll.get(indexState)==null)
                mapIndexAll.put(indexState,i);
        }
        LinearLayout indexLayout = (LinearLayout) view.findViewById(R.id.side_index_all);
        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndexAll.keySet());
        for (String index : indexList) {
            textView = (TextView) inflater.inflate(
                    R.layout.side_index_item, null);
            textView.setText(index);
            textView.setOnClickListener(this);
            indexLayout.addView(textView);
        }
//      all states的item监听
        list_legi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bd = new Bundle();
                bd.putString("legi_id", fg_legislators.get(position).getBioguide_id());
                bd.putString("legi_facebook", fg_legislators.get(position).getFacebook_id());
                bd.putString("legi_twitter", fg_legislators.get(position).getTwitter_id());
                bd.putString("legi_website",fg_legislators.get(position).getWebsite());

                bd.putString("legi_party", fg_legislators.get(position).getPartyName());

                bd.putString("legi_name", fg_legislators.get(position).getLegislatorName());
                bd.putString("legi_email", fg_legislators.get(position).getOc_email());
                bd.putString("legi_chamber", fg_legislators.get(position).getLegislatorChamber());
                bd.putString("legi_contact", fg_legislators.get(position).getPhone());
                bd.putString("legi_start", fg_legislators.get(position).getStartTerm());
                bd.putString("legi_end", fg_legislators.get(position).getEndTerm());

                bd.putInt("legi_termNum", fg_legislators.get(position).getTermInt());
                bd.putString("legi_term", fg_legislators.get(position).getTerm());

                bd.putString("legi_office", fg_legislators.get(position).getOffice());
                bd.putString("legi_state", fg_legislators.get(position).getState());
                bd.putString("legi_fax", fg_legislators.get(position).getFax());
                bd.putString("legi_bday", fg_legislators.get(position).getBirthdayDate());

                if (fav_legislator.size() > 0)
                {
                    for (int i = 0; i < fav_legislator.size(); i++)
                    {
                        if (fav_legislator.get(i).getBioguide_id().equals(fg_legislators.get(position).getBioguide_id()))
                            bd.putString("isfav", "y");
                        else
                            bd.putString("isfav", "n");
                    }
                }
                else
                    bd.putString("isfav", "n");

                Intent intent = new Intent(mContext, LegiDetailActivity.class);
                intent.putExtras(bd);
                startActivity(intent);

            }
        });

//      house的legislator的listview
        list_legi_house = (ListView) view.findViewById(R.id.legi_list_house);
        legihadapter = new LegiAdapter((ArrayList<Legislator>)fg_legislators_house, mContext);
        list_legi_house.setAdapter(legihadapter);
//      house的sideIndex
        mapIndexHouse = new LinkedHashMap<String, Integer>();
        for (int j = 0; j < fg_legislators_house.size(); j++)
        {
            String lastname = fg_legislators_house.get(j).getLast_name();
            String indexLname = lastname.substring(0,1);
            if (mapIndexHouse.get(indexLname)==null)
                mapIndexHouse.put(indexLname, j);
        }
        LinearLayout indexLayoutHouse = (LinearLayout) view.findViewById(R.id.side_index_house);
        TextView textViewHouse;
        List<String> indexListHouse = new ArrayList<String>(mapIndexHouse.keySet());
        for (String index : indexListHouse) {
            textViewHouse = (TextView) inflater.inflate(
                    R.layout.side_index_item, null);
            textViewHouse.setText(index);
            textViewHouse.setOnClickListener(this);
            indexLayoutHouse.addView(textViewHouse);
        }
//      house的item监听
        list_legi_house.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bd = new Bundle();

                bd.putString("legi_id",fg_legislators_house.get(position).getBioguide_id());
                bd.putString("legi_facebook", fg_legislators_house.get(position).getFacebook_id());
                bd.putString("legi_twitter", fg_legislators_house.get(position).getTwitter_id());
                bd.putString("legi_website",fg_legislators_house.get(position).getWebsite());

                bd.putString("legi_party", fg_legislators_house.get(position).getPartyName());

                bd.putString("legi_name", fg_legislators_house.get(position).getLegislatorName());
                bd.putString("legi_email", fg_legislators_house.get(position).getOc_email());
                bd.putString("legi_chamber", fg_legislators_house.get(position).getLegislatorChamber());
                bd.putString("legi_contact", fg_legislators_house.get(position).getPhone());
                bd.putString("legi_start", fg_legislators_house.get(position).getStartTerm());
                bd.putString("legi_end", fg_legislators_house.get(position).getEndTerm());

                bd.putInt("legi_termNum", fg_legislators_house.get(position).getTermInt());
                bd.putString("legi_term", fg_legislators_house.get(position).getTerm());

                bd.putString("legi_office", fg_legislators_house.get(position).getOffice());
                bd.putString("legi_state", fg_legislators_house.get(position).getState());
                bd.putString("legi_fax", fg_legislators_house.get(position).getFax());
                bd.putString("legi_bday", fg_legislators_house.get(position).getBirthdayDate());

                if (fav_legislator.size() > 0)
                {
                    for (int i = 0; i < fav_legislator.size(); i++)
                    {
                        if (fav_legislator.get(i).getBioguide_id().equals(fg_legislators_house.get(position).getBioguide_id()))
                            bd.putString("isfav", "y");
                        else
                            bd.putString("isfav", "n");
                    }
                }
                else
                    bd.putString("isfav", "n");

                Intent intent = new Intent(mContext, LegiDetailActivity.class);
                intent.putExtras(bd);
                startActivity(intent);
            }
        });

//      senate的listview
        list_legi_senate = (ListView) view.findViewById(R.id.legi_list_senate);
        legisadapter = new LegiAdapter((ArrayList<Legislator>)fg_legislators_senate, mContext);
        list_legi_senate.setAdapter(legisadapter);

        getMapIndexSenate = new LinkedHashMap<String, Integer>();
        for (int j = 0; j < fg_legislators_senate.size(); j++)
        {
            String lastname = fg_legislators_senate.get(j).getLast_name();
            String indexLname = lastname.substring(0,1);
            if (getMapIndexSenate.get(indexLname)==null)
                getMapIndexSenate.put(indexLname, j);
        }

        LinearLayout indexLayoutSenate = (LinearLayout) view.findViewById(R.id.side_index_senate);
        TextView textViewSenate;
        List<String> indexListSenate = new ArrayList<String>(getMapIndexSenate.keySet());
        for (String index : indexListSenate) {
            textViewSenate = (TextView) inflater.inflate(
                    R.layout.side_index_item, null);
            textViewSenate.setText(index);
            textViewSenate.setOnClickListener(this);
            indexLayoutSenate.addView(textViewSenate);
        }
//      senate的item监听
        list_legi_senate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bd = new Bundle();
                bd.putString("legi_id", fg_legislators_senate.get(position).getBioguide_id());
                bd.putString("legi_facebook", fg_legislators_senate.get(position).getFacebook_id());
                bd.putString("legi_twitter", fg_legislators_senate.get(position).getTwitter_id());
                bd.putString("legi_website",fg_legislators_senate.get(position).getWebsite());

                bd.putString("legi_party", fg_legislators_senate.get(position).getPartyName());

                bd.putString("legi_name", fg_legislators_senate.get(position).getLegislatorName());
                bd.putString("legi_email", fg_legislators_senate.get(position).getOc_email());
                bd.putString("legi_chamber", fg_legislators_senate.get(position).getLegislatorChamber());
                bd.putString("legi_contact", fg_legislators_senate.get(position).getPhone());
                bd.putString("legi_start", fg_legislators_senate.get(position).getStartTerm());
                bd.putString("legi_end", fg_legislators_senate.get(position).getEndTerm());

                bd.putInt("legi_termNum", fg_legislators_senate.get(position).getTermInt());
                bd.putString("legi_term", fg_legislators_senate.get(position).getTerm());

                bd.putString("legi_office", fg_legislators_senate.get(position).getOffice());
                bd.putString("legi_state", fg_legislators_senate.get(position).getState());
                bd.putString("legi_fax", fg_legislators_senate.get(position).getFax());
                bd.putString("legi_bday", fg_legislators_senate.get(position).getBirthdayDate());

                if (fav_legislator.size() > 0)
                {
                    for (int i = 0; i < fav_legislator.size(); i++)
                    {
                        if (fav_legislator.get(i).getBioguide_id().equals(fg_legislators_senate.get(position).getBioguide_id()))
                            bd.putString("isfav", "y");
                        else
                            bd.putString("isfav", "n");
                    }
                }
                else
                    bd.putString("isfav", "n");

                Intent intent = new Intent(mContext, LegiDetailActivity.class);
                intent.putExtras(bd);
                startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        TextView selectedIndex = (TextView) v;
        if (mapIndexAll.get(selectedIndex.getText()) != null)
            list_legi.setSelection(mapIndexAll.get(selectedIndex.getText()));
        if (mapIndexHouse.get(selectedIndex.getText()) != null)
            list_legi_house.setSelection(mapIndexHouse.get(selectedIndex.getText()));
        if (getMapIndexSenate.get(selectedIndex.getText()) != null)
            list_legi_senate.setSelection(getMapIndexSenate.get(selectedIndex.getText()));
    }
}
