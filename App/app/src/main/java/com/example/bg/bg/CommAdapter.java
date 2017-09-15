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

public class CommAdapter extends BaseAdapter {

    private ArrayList<Committee> mComms;
    private Context mContext;

    public  CommAdapter (ArrayList<Committee> mComms, Context mContext)
    {
        this.mComms = mComms;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mComms.size();
    }

    @Override
    public Object getItem(int position) {
        return  null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        TextView comm_id;
        TextView comm_name;
        TextView comm_chamber;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return null;
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.comm_list_item, parent, false);
            holder = new ViewHolder();
            holder.comm_id = (TextView) convertView.findViewById(R.id.comm_id);
            holder.comm_name = (TextView) convertView.findViewById(R.id.comm_name);
            holder.comm_chamber = (TextView) convertView.findViewById(R.id.comm_chamber);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.comm_id.setText(mComms.get(position).getCommittee_id());
        holder.comm_name.setText(mComms.get(position).getName());
        holder.comm_chamber.setText(mComms.get(position).getchamberCapt());

        return convertView;
    }
}
