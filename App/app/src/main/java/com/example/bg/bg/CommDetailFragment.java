package com.example.bg.bg;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bbbia on 11/26/2016.
 */

public class CommDetailFragment extends Fragment {

    public CommDetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.commdetail, container, false);

        TextView commdetail_id = (TextView) view.findViewById(R.id.comm_detail_id);
        TextView commdetail_name = (TextView) view.findViewById(R.id.comm_detail_name);
        ImageView comm_chamber_img = (ImageView) view.findViewById(R.id.comm_chamber_img);
        TextView commdetail_chamber = (TextView) view.findViewById(R.id.comm_detail_chamber);
        TextView commdetail_parent_comm = (TextView) view.findViewById(R.id.comm_detial_parent_comm);
        TextView commdetail_contact = (TextView) view.findViewById(R.id.comm_detail_contact);
        TextView commdetail_office = (TextView) view.findViewById(R.id.comm_detail_office);

        commdetail_id.setText(getArguments().getString("comm_id"));
        commdetail_name.setText(getArguments().getString("comm_name"));

        if (getArguments().getString("comm_chamber").equals("house"))
        {
            commdetail_chamber.setText("House");
            comm_chamber_img.setImageDrawable(getResources().getDrawable(R.drawable.house));
        }
        else if (getArguments().getString("comm_chamber").equals("house"))
        {
            commdetail_chamber.setText("Senate");
            comm_chamber_img.setImageDrawable(getResources().getDrawable(R.drawable.senate));
        }
        else
        {
            commdetail_chamber.setText("Joint");
            comm_chamber_img.setImageDrawable(getResources().getDrawable(R.drawable.senate));
        }

//      判断parent commi是否为空
        if (getArguments().getString("comm_parent_comm") == null)
            commdetail_parent_comm.setText("N.A.");
        else
            commdetail_parent_comm.setText(getArguments().getString("comm_parent_comm"));

//      判断contact是否为空
        if (getArguments().getString("comm_contact") == null)
            commdetail_contact.setText("N.A.");
        else
            commdetail_contact.setText(getArguments().getString("comm_contact"));

//      判断office是否为空
        if (getArguments().getString("comm_office") == null)
            commdetail_office.setText("N.A.");
        else
            commdetail_office.setText(getArguments().getString("comm_office"));

        return view;

    }
}
