package com.example.bg.bg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bbbia on 11/28/2016.
 */

public class BillDetailAvtivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billdetail);

        setTitle("Bill info");

        intent = getIntent();

        final ImageView bill_favorite = (ImageView) findViewById(R.id.bill_favicon);

        bill_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.getStringExtra("isfav").equals("n")){
                    SharedPreferences bill_sp = getSharedPreferences("sp_AddBill", MODE_PRIVATE);
                    SharedPreferences.Editor editor = bill_sp.edit();
                    editor.putString(intent.getStringExtra("bill_id"),intent.getStringExtra("bill_id"));
                    editor.commit();
                    bill_favorite.setImageDrawable(getResources().getDrawable(R.drawable.goldstar));
                }
                else
                {
                    SharedPreferences bill_sp = getSharedPreferences("sp_AddBill", MODE_PRIVATE);
                    SharedPreferences.Editor editor = bill_sp.edit();
                    editor.putString(intent.getStringExtra("bill_id"),"0");
                    editor.commit();
                    bill_favorite.setImageDrawable(getResources().getDrawable(R.drawable.star48));
                }
            }
        });

        if (intent.getStringExtra("isfav").equals("n"))
            bill_favorite.setImageDrawable(getResources().getDrawable(R.drawable.star48));
        else
            bill_favorite.setImageDrawable(getResources().getDrawable(R.drawable.goldstar));

        TextView billdetail_id = (TextView) findViewById(R.id.bill_detail_id);
        TextView billdetail_title = (TextView) findViewById(R.id.bill_detail_title);
        TextView billdetail_billtype = (TextView) findViewById(R.id.bill_detail_billtype);
        TextView billdetail_sponsor = (TextView) findViewById(R.id.bill_detail_sponsor);
        TextView billdetail_chamber = (TextView) findViewById(R.id.bill_detail_chamber);
        TextView billdetail_status = (TextView) findViewById(R.id.bill_detail_status);
        TextView billdetail_introducedon = (TextView) findViewById(R.id.bill_detail_introduced_on);
        TextView billdetail_congressurl = (TextView) findViewById(R.id.bill_detail_congressUrl);
        TextView billdetail_versionstatus = (TextView) findViewById(R.id.bill_detail_versionstatus);
        TextView billdetail_billurl = (TextView) findViewById(R.id.bill_detail_billurl);

        billdetail_id.setText(intent.getStringExtra("bill_id"));
        billdetail_title.setText(intent.getStringExtra("bill_title"));
        billdetail_billtype.setText(intent.getStringExtra("bill_type"));
        billdetail_sponsor.setText(intent.getStringExtra("bill_sponsor"));

        if (intent.getStringExtra("bill_chamber").equals("house"))
            billdetail_chamber.setText("House");
        else
            billdetail_chamber.setText("Senate");

        if (intent.getStringExtra("bill_status").equals("true"))
            billdetail_status.setText("Active");
        else
            billdetail_status.setText("New");

        billdetail_introducedon.setText(intent.getStringExtra("bill_introduced_on"));

        if (intent.getStringExtra("bill_congressurl") == null)
            billdetail_congressurl.setText("N.A.");
        else
            billdetail_congressurl.setText(intent.getStringExtra("bill_congressurl"));
        billdetail_versionstatus.setText(intent.getStringExtra("bill_versionstatus"));

        if (intent.getStringExtra("bill_billurl") == null)
            billdetail_billurl.setText("N.A.");
        else
            billdetail_billurl.setText(intent.getStringExtra("bill_billurl"));

    }
}
