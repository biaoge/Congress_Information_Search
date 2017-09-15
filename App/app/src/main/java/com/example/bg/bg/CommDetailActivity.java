package com.example.bg.bg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by bbbia on 11/28/2016.
 */

public class CommDetailActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commdetail);

        setTitle("Committee info");

         intent = getIntent();

        final ImageView comm_favorite = (ImageView) findViewById(R.id.comm_favicon);

        comm_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.getStringExtra("isfav").equals("n")) {
                    SharedPreferences sp = getSharedPreferences("sp_AddComm", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(intent.getStringExtra("comm_id"), intent.getStringExtra("comm_id"));
                    editor.commit();
                    comm_favorite.setImageDrawable(getResources().getDrawable(R.drawable.goldstar));
                }
                else
                {
                    SharedPreferences sp = getSharedPreferences("sp_AddComm", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(intent.getStringExtra("comm_id"), "0");
                    editor.commit();
                    comm_favorite.setImageDrawable(getResources().getDrawable(R.drawable.star48));
                }
            }
        });


        if (intent.getStringExtra("isfav").equals("y"))
            comm_favorite.setImageDrawable(getResources().getDrawable(R.drawable.goldstar));
        else
            comm_favorite.setImageDrawable(getResources().getDrawable(R.drawable.star48));


        TextView commdetail_id = (TextView) findViewById(R.id.comm_detail_id);
        TextView commdetail_name = (TextView) findViewById(R.id.comm_detail_name);
        ImageView comm_chamber_img = (ImageView) findViewById(R.id.comm_chamber_img);
        TextView commdetail_chamber = (TextView) findViewById(R.id.comm_detail_chamber);
        TextView commdetail_parent_comm = (TextView) findViewById(R.id.comm_detial_parent_comm);
        TextView commdetail_contact = (TextView) findViewById(R.id.comm_detail_contact);
        TextView commdetail_office = (TextView) findViewById(R.id.comm_detail_office);

        commdetail_id.setText(intent.getStringExtra("comm_id"));
        commdetail_name.setText(intent.getStringExtra("comm_name"));

        if (intent.getStringExtra("comm_chamber").equals("house"))
        {
            commdetail_chamber.setText("House");
            comm_chamber_img.setImageDrawable(getResources().getDrawable(R.drawable.house));
            //      判断parent commi是否为空
            if (intent.getStringExtra("comm_parent_comm") == null)
                commdetail_parent_comm.setText("N.A.");
            else
                commdetail_parent_comm.setText(intent.getStringExtra("comm_parent_comm"));
            //      判断contact是否为空
            if (intent.getStringExtra("comm_contact") == null)
                commdetail_contact.setText("N.A.");
            else
                commdetail_contact.setText(intent.getStringExtra("comm_contact"));
            //      判断office是否为空
            if (intent.getStringExtra("comm_office") == null)
                commdetail_office.setText("N.A.");
            else
                commdetail_office.setText(intent.getStringExtra("comm_office"));
        }
        else if (intent.getStringExtra("comm_chamber").equals("senate"))
        {
            commdetail_chamber.setText("Senate");
            comm_chamber_img.setImageDrawable(getResources().getDrawable(R.drawable.senate));
            //      判断parent commi是否为空
            if (intent.getStringExtra("comm_parent_comm") == null)
                commdetail_parent_comm.setText("N.A.");
            else
                commdetail_parent_comm.setText(intent.getStringExtra("comm_parent_comm"));
            //      判断contact是否为空
            if (intent.getStringExtra("comm_contact") == null)
                commdetail_contact.setText("N.A.");
            else
                commdetail_contact.setText(intent.getStringExtra("comm_contact"));
            //      判断office是否为空
            if (intent.getStringExtra("comm_office") == null)
                commdetail_office.setText("N.A.");
            else
                commdetail_office.setText(intent.getStringExtra("comm_office"));
        }
        else
        {
            commdetail_chamber.setText("Joint");
            comm_chamber_img.setImageDrawable(getResources().getDrawable(R.drawable.senate));
            commdetail_parent_comm.setText("N.A.");
            commdetail_contact.setText("N.A.");
            commdetail_office.setText("N.A.");
        }

    }
}
