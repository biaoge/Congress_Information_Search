package com.example.bg.bg;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by bbbia on 11/26/2016.
 */

public class BillDetailFragment extends Fragment {

    public BillDetailFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.billdetail, container, false);

        TextView billdetail_id = (TextView) view.findViewById(R.id.bill_detail_id);
        TextView billdetail_title = (TextView) view.findViewById(R.id.bill_detail_title);
        TextView billdetail_billtype = (TextView) view.findViewById(R.id.bill_detail_billtype);
        TextView billdetail_sponsor = (TextView) view.findViewById(R.id.bill_detail_sponsor);
        TextView billdetail_chamber = (TextView) view.findViewById(R.id.bill_detail_chamber);
        TextView billdetail_status = (TextView) view.findViewById(R.id.bill_detail_status);
        TextView billdetail_introducedon = (TextView) view.findViewById(R.id.bill_detail_introduced_on);
        TextView billdetail_congressurl = (TextView) view.findViewById(R.id.bill_detail_congressUrl);
        TextView billdetail_versionstatus = (TextView) view.findViewById(R.id.bill_detail_versionstatus);
        TextView billdetail_billurl = (TextView) view.findViewById(R.id.bill_detail_billurl);

        billdetail_id.setText(getArguments().getString("bill_id"));
        billdetail_title.setText(getArguments().getString("bill_title"));
        billdetail_billtype.setText(getArguments().getString("bill_type"));
        billdetail_sponsor.setText(getArguments().getString("bill_sponsor"));

        if (getArguments().getString("bill_chamber").equals("house"))
            billdetail_chamber.setText("House");
        else
            billdetail_chamber.setText("Senate");

        if (getArguments().getBoolean("bill_status"))
            billdetail_status.setText("Active");
        else
            billdetail_status.setText("New");

        billdetail_introducedon.setText(getArguments().getString("bill_introduced_on"));

        if (getArguments().getString("bill_congressurl") == null)
            billdetail_congressurl.setText("N.A.");
        else
            billdetail_congressurl.setText(getArguments().getString("bill_congressurl"));
        billdetail_versionstatus.setText(getArguments().getString("bill_versionstatus"));

        if (getArguments().getString("bill_billurl") == null)
            billdetail_billurl.setText("N.A.");
        else
            billdetail_billurl.setText(getArguments().getString("bill_billurl"));

        return view;
    }
}
