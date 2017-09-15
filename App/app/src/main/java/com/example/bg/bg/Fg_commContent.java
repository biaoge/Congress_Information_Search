package com.example.bg.bg;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by bbbia on 11/22/2016.
 */

public class Fg_commContent extends Fragment implements AdapterView.OnItemClickListener {

//  house committee信息
    private ListView list_comm_house;
    private CommAdapter commhadapter;
    private ArrayList<Committee> fg_comm_house;

//  Senate committee信息
    private ListView list_comm_senate;
    private CommAdapter commsadapter;
    private ArrayList<Committee> fg_comm_senate;

//  Joint committee信息
    private ListView list_comm_joint;
    private CommAdapter commjadapter;
    private ArrayList<Committee> fg_comm_joint;

    private Context mContext;
    private ArrayList<Committee> fav_comm;

//    private String content;

//  构造函数变体
    public static Fg_commContent newInstance (ArrayList<Committee> fg_comm_house, ArrayList<Committee> fg_comm_senate, ArrayList<Committee> fg_comm_joint, ArrayList<Committee> fav_comm, Context mContext)
    {
        Fg_commContent newFragment = new Fg_commContent();
//        Bundle bundle = new Bundle();
//        bundle.putString("content", content);
//        newFragment.setArguments(bundle);
//        newFragment.content = content;
        newFragment.fg_comm_house = fg_comm_house;
        newFragment.fg_comm_senate = fg_comm_senate;
        newFragment.fg_comm_joint = fg_comm_joint;
        newFragment.fav_comm = fav_comm;
        newFragment.mContext = mContext;
        return  newFragment;
    }

//  重写onCreateView方法
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fg_comm_content, container, false);
//        TextView txt_content = (TextView) view.findViewById(R.id.comm_content);
//        txt_content.setText(content);

//     初始化committees内容
        TabHost ltabHost = (TabHost) view.findViewById(R.id.ctabhost);
        ltabHost.setup();
//      By States 选项卡
        TabHost.TabSpec spec1 = ltabHost.newTabSpec("ctab1");
        spec1.setContent(R.id.ctab01);
        spec1.setIndicator("HOUSE");
        ltabHost.addTab(spec1);
//      House 选项卡
        TabHost.TabSpec spec2 = ltabHost.newTabSpec("ctab2");
        spec2.setContent(R.id.ctab02);
        spec2.setIndicator("SENATE");
        ltabHost.addTab(spec2);
//      Senate 选项卡
        TabHost.TabSpec spec3 = ltabHost.newTabSpec("ctab3");
        spec3.setContent(R.id.ctab03);
        spec3.setIndicator("JOINT");
        ltabHost.addTab(spec3);

//      house comm的listview
        list_comm_house = (ListView) view.findViewById(R.id.list_comm_house);
        commhadapter = new CommAdapter((ArrayList<Committee>) fg_comm_house, mContext);
        list_comm_house.setAdapter(commhadapter);
        list_comm_house.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(mContext, CommDetailActivity.class);


                if (fav_comm.size()>0)
                {
                    for (int i = 0; i < fav_comm.size(); i++)
                    {
                        if (fav_comm.get(i).getCommittee_id().equals(fg_comm_house.get(position).getCommittee_id())) {
                            intent.putExtra("isfav", "y");
                            break;
                        }
                        else
                            intent.putExtra("isfav", "n");
                    }
                }
                else
                    intent.putExtra("isfav", "n");


                intent.putExtra("comm_id", fg_comm_house.get(position).getCommittee_id());
                intent.putExtra("comm_name", fg_comm_house.get(position).getName());
                intent.putExtra("comm_chamber", fg_comm_house.get(position).getChamber());
                intent.putExtra("comm_parent_comm", fg_comm_house.get(position).getParent_committee_id());
                intent.putExtra("comm_contact", fg_comm_house.get(position).getPhone());
                intent.putExtra("comm_office",fg_comm_house.get(position).getOffice());

                startActivity(intent);




            }
        });

//      senate comm的listview
        list_comm_senate = (ListView) view.findViewById(R.id.list_comm_senate);
        commsadapter = new CommAdapter((ArrayList<Committee>) fg_comm_senate, mContext);
        list_comm_senate.setAdapter(commsadapter);
        list_comm_senate.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(mContext, CommDetailActivity.class);

                if (fav_comm.size()>0)
                {
                    for (int i = 0; i < fav_comm.size(); i++)
                    {
                        if (fav_comm.get(i).getCommittee_id().equals(fg_comm_senate.get(position).getCommittee_id())) {
                            intent.putExtra("isfav", "y");
                            break;
                        }
                        else
                            intent.putExtra("isfav", "n");
                    }
                }
                else
                    intent.putExtra("isfav", "n");

                intent.putExtra("comm_id", fg_comm_senate.get(position).getCommittee_id());
                intent.putExtra("comm_name", fg_comm_senate.get(position).getName());
                intent.putExtra("comm_chamber", fg_comm_senate.get(position).getChamber());
                intent.putExtra("comm_parent_comm", fg_comm_senate.get(position).getParent_committee_id());
                intent.putExtra("comm_contact", fg_comm_senate.get(position).getPhone());
                intent.putExtra("comm_office",fg_comm_senate.get(position).getOffice());


                startActivity(intent);

            }
        });

//      joint comm 的listview
        list_comm_joint = (ListView) view.findViewById(R.id.list_comm_joint);
        commjadapter = new CommAdapter((ArrayList<Committee>) fg_comm_joint, mContext);
        list_comm_joint.setAdapter(commjadapter);
        list_comm_joint.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(mContext, CommDetailActivity.class);

                if (fav_comm.size()>0)
                {
                    for (int i = 0; i < fav_comm.size(); i++)
                    {
                        if (fav_comm.get(i).getCommittee_id().equals(fg_comm_joint.get(position).getCommittee_id())) {
                            intent.putExtra("isfav", "y");
                            break;
                        }
                        else
                            intent.putExtra("isfav", "n");
                    }
                }
                else
                    intent.putExtra("isfav", "n");

                intent.putExtra("comm_id", fg_comm_joint.get(position).getCommittee_id());
                intent.putExtra("comm_name", fg_comm_joint.get(position).getName());
                intent.putExtra("comm_chamber", fg_comm_joint.get(position).getChamber());
                intent.putExtra("comm_parent_comm", fg_comm_joint.get(position).getParent_committee_id());
                intent.putExtra("comm_contact", fg_comm_joint.get(position).getPhone());
                intent.putExtra("comm_office",fg_comm_joint.get(position).getOffice());

                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
