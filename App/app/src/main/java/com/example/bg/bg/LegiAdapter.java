package com.example.bg.bg;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by bbbia on 11/25/2016.
 */

public class LegiAdapter extends BaseAdapter {
    private ArrayList<Legislator> mLegislators;
    private Context mContext;

    public LegiAdapter(ArrayList<Legislator> mLegislators, Context mContext){
        this.mLegislators = mLegislators;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mLegislators.size();
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
        ImageView legi_img_icon;
        TextView legi_fullname;
        TextView legi_secline;
    }

//  设置apapter的样式
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return null;
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.legi_list_item, parent, false);
            holder = new ViewHolder();
            holder.legi_img_icon = (ImageView) convertView.findViewById(R.id.legi_simg);
            holder.legi_fullname = (TextView) convertView.findViewById(R.id.legi_fullname);
            holder.legi_secline = (TextView) convertView.findViewById(R.id.legi_secline);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.legi_fullname.setText(mLegislators.get(position).getFullname());
        holder.legi_secline.setText(mLegislators.get(position).getSecline());
        Picasso.with(mContext).load("https://theunitedstates.io/images/congress/original/" + mLegislators.get(position).getBioguide_id() + ".jpg").into(holder.legi_img_icon);

        return convertView;
    }
}
