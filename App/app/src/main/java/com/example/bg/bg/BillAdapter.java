package com.example.bg.bg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bbbia on 11/25/2016.
 */

public class BillAdapter extends BaseAdapter {

    private ArrayList<Bill> mbills;
    private Context mContext;

    public BillAdapter (ArrayList<Bill> mbills, Context mContext)
    {
        this.mbills = mbills;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mbills.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

        static class ViewHolder{
            TextView bill_id;
            TextView bill_title;
            TextView bill_introduced_on;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return null;
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.bill_list_item, parent, false);
            holder = new ViewHolder();
            holder.bill_id = (TextView) convertView.findViewById(R.id.bill_id);
            holder.bill_title = (TextView) convertView.findViewById(R.id.bill_title);
            holder.bill_introduced_on = (TextView) convertView.findViewById(R.id.introduced_on);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bill_id.setText(mbills.get(position).getBill_id().toUpperCase());
        holder.bill_title.setText(mbills.get(position).getBilltitle());
        holder.bill_introduced_on.setText(mbills.get(position).getIntroducedonDate());

        return  convertView;
    }

}
