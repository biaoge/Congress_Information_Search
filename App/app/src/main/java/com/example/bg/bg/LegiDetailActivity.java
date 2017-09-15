package com.example.bg.bg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by bbbia on 11/28/2016.
 */

public class LegiDetailActivity extends AppCompatActivity {

    private Intent intent;
    private Bundle bd;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.legidetail);
        setTitle("Legislator info");


        intent = getIntent();
        bd = getIntent().getExtras();

        mContext = this;


        final ImageView legi_favorite = (ImageView) findViewById(R.id.legi_favicon);

        legi_favorite.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (bd.getString("isfav").equals("n")){
                    SharedPreferences legi_sp = getSharedPreferences("sp_LegiAdd", MODE_PRIVATE);
                    SharedPreferences.Editor editorAdd = legi_sp.edit();
                    editorAdd.putString(bd.getString("legi_id"), bd.getString("legi_id"));
                    editorAdd.commit();
                    legi_favorite.setImageDrawable(getResources().getDrawable(R.drawable.goldstar));
                }
                else
                {
                    SharedPreferences legi_sp = getSharedPreferences("sp_LegiAdd", MODE_PRIVATE);
                    SharedPreferences.Editor editorDel = legi_sp.edit();
                    editorDel.putString(bd.getString("legi_id"), "0");
                    editorDel.commit();
                    legi_favorite.setImageDrawable(getResources().getDrawable(R.drawable.star48));
                }
            }
        });

        if (bd.getString("isfav").equals("y"))
            legi_favorite.setImageDrawable(getResources().getDrawable(R.drawable.goldstar));
        else
            legi_favorite.setImageDrawable(getResources().getDrawable(R.drawable.star48));



        ImageView legi_img = (ImageView) findViewById(R.id.legi_img);
        Picasso.with(getApplicationContext()).load("https://theunitedstates.io/images/congress/original/" + bd.getString("legi_id") + ".jpg").into(legi_img);
        ImageView legi_facebook = (ImageView) findViewById(R.id.legi_facebook);
        ImageView legi_twitter = (ImageView) findViewById(R.id.legi_twitter);
        ImageView legi_website = (ImageView) findViewById(R.id.legi_wrold);

        legi_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bd.getString("legi_facebook") == null)
                    Toast.makeText(getApplicationContext(), "No facebook", Toast.LENGTH_SHORT ).show();
                else {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.facebook.com/" + bd.getString("legi_facebook")));
                    startActivity(intent);
                }

            }
        });

        legi_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bd.getString("legi_twitter") == null)
                    Toast.makeText(getApplicationContext(), "No twitter", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://twitter.com/" + bd.getString("legi_twitter")));
                    startActivity(intent);
                }

            }
        });

        legi_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bd.getString("legi_website") == null)
                    Toast.makeText(getApplicationContext(), "No personal website", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(bd.getString("legi_website")));
                    startActivity(intent);
                }
            }
        });


        TextView legi_party = (TextView) findViewById(R.id.legi_party);
        ImageView legi_party_img = (ImageView) findViewById(R.id.legi_party_img);

        TextView legi_name = (TextView) findViewById(R.id.legi_name);
        TextView legi_email = (TextView) findViewById(R.id.legi_email);
        TextView legi_chamber = (TextView) findViewById(R.id.legi_chamber);
        TextView legi_contact = (TextView) findViewById(R.id.legi_contact);
        TextView legi_start = (TextView) findViewById(R.id.legi_startterm);
        TextView legi_end = (TextView) findViewById(R.id.legi_endterm);

        ProgressBar legi_progress = (ProgressBar) findViewById(R.id.legi_progressbar);

        TextView legi_term = (TextView) findViewById(R.id.legi_progress_value);
        TextView legi_office = (TextView) findViewById(R.id.legi_office);
        TextView legi_state = (TextView) findViewById(R.id.legi_state);
        TextView legi_fax = (TextView) findViewById(R.id.legi_fax);
        TextView legi_bday = (TextView) findViewById(R.id.legi_bday);

        legi_party.setText(bd.getString("legi_party"));
        if (bd.getString("legi_party").equals("Democrat")) {
            legi_party_img.setImageDrawable(getResources().getDrawable(R.drawable.dometic));
        }
        else if (bd.getString("legi_party").equals("Republican"))
            legi_party_img.setImageDrawable(getResources().getDrawable(R.drawable.republicican));


        legi_name.setText(bd.getString("legi_name"));

        if (bd.getString("legi_email") == null)
            legi_email.setText("N.A.");
        else
            legi_email.setText(bd.getString("legi_email"));

        legi_chamber.setText(bd.getString("legi_chamber"));
        legi_contact.setText(bd.getString("legi_contact"));
        legi_start.setText(bd.getString("legi_start"));
        legi_end.setText(bd.getString("legi_end"));


        legi_progress.setProgress(bd.getInt("legi_termNum"));
        legi_term.setText(bd.getString("legi_term"));

        legi_office.setText(bd.getString("legi_office"));
        legi_state.setText(bd.getString("legi_state"));

        if (bd.getString("legi_fax") == null)
            legi_fax.setText("N.A.");
        else
            legi_fax.setText(bd.getString("legi_fax"));

        legi_bday.setText(bd.getString("legi_bday"));
    }
}
