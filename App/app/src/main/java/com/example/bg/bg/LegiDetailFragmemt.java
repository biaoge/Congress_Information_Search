package com.example.bg.bg;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.style.IconMarginSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by bbbia on 11/27/2016.
 */

public class LegiDetailFragmemt extends Fragment {

    private Context mContext;
    private WebView webView;

    public  LegiDetailFragmemt() {

    }

    public static LegiDetailFragmemt newInstance (Context mContext){
        LegiDetailFragmemt ldf = new LegiDetailFragmemt();
        ldf.mContext = mContext;
        return ldf;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.legidetail, container, false);

        ImageView legi_img = (ImageView) view.findViewById(R.id.legi_img);

        Picasso.with(mContext).load("https://theunitedstates.io/images/congress/original/" + getArguments().getString("legi_id") + ".jpg").into(legi_img);

        ImageView legi_facebook = (ImageView) view.findViewById(R.id.legi_facebook);
        ImageView legi_twitter = (ImageView) view.findViewById(R.id.legi_twitter);
        ImageView legi_website = (ImageView) view.findViewById(R.id.legi_wrold);



        legi_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments().getString("legi_facebook") == null)
                    Toast.makeText(mContext.getApplicationContext(), "No facebook", Toast.LENGTH_SHORT ).show();
                else {
//                    Toast.makeText(mContext.getApplicationContext(), getArguments().getString("legi_facebook"), Toast.LENGTH_SHORT).show();
                    webView = new WebView(mContext.getApplicationContext());
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.loadUrl("https://www.facebook.com/" + getArguments().getString("legi_facebook"));
                }

            }
        });

        legi_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments().getString("legi_twitter") == null)
                    Toast.makeText(mContext.getApplicationContext(), "No twitter", Toast.LENGTH_SHORT).show();
                else {
//                    Toast.makeText(mContext.getApplicationContext(), getArguments().getString("legi_twitter"), Toast.LENGTH_SHORT).show();
                    webView = new WebView(mContext.getApplicationContext());
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.loadUrl("https://twitter.com/" + getArguments().getString("legi_twitter"));

                }

            }
        });

        legi_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments().getString("legi_website") == null)
                    Toast.makeText(mContext.getApplicationContext(), "No personal website", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(mContext.getApplicationContext(), getArguments().getString("legi_website"), Toast.LENGTH_SHORT).show();
                    webView = new WebView(mContext.getApplicationContext());
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.loadUrl(getArguments().getString("legi_webskte"));

                }
            }
        });


        TextView legi_party = (TextView) view.findViewById(R.id.legi_party);
        ImageView legi_party_img = (ImageView) view.findViewById(R.id.legi_party_img);

        TextView legi_name = (TextView) view.findViewById(R.id.legi_name);
        TextView legi_email = (TextView) view.findViewById(R.id.legi_email);
        TextView legi_chamber = (TextView) view.findViewById(R.id.legi_chamber);
        TextView legi_contact = (TextView) view.findViewById(R.id.legi_contact);
        TextView legi_start = (TextView) view.findViewById(R.id.legi_startterm);
        TextView legi_end = (TextView) view.findViewById(R.id.legi_endterm);

        ProgressBar legi_progress = (ProgressBar) view.findViewById(R.id.legi_progressbar);

        TextView legi_term = (TextView) view.findViewById(R.id.legi_progress_value);
        TextView legi_office = (TextView) view.findViewById(R.id.legi_office);
        TextView legi_state = (TextView) view.findViewById(R.id.legi_state);
        TextView legi_fax = (TextView) view.findViewById(R.id.legi_fax);
        TextView legi_bday = (TextView) view.findViewById(R.id.legi_bday);

        legi_party.setText(getArguments().getString("legi_party"));
        if (getArguments().getString("legi_party").equals("Democrat")) {
            legi_party_img.setImageDrawable(getResources().getDrawable(R.drawable.dometic));
        }
        else if (getArguments().getString("legi_party").equals("Republican"))
            legi_party_img.setImageDrawable(getResources().getDrawable(R.drawable.republicican));


        legi_name.setText(getArguments().getString("legi_name"));
        legi_email.setText(getArguments().getString("legi_email"));
        legi_chamber.setText(getArguments().getString("legi_chamber"));
        legi_contact.setText(getArguments().getString("legi_contact"));
        legi_start.setText(getArguments().getString("legi_start"));
        legi_end.setText(getArguments().getString("legi_end"));

        legi_progress.setProgress(getArguments().getInt("legi_termNum"));
        legi_term.setText(getArguments().getString("legi_term"));

        legi_office.setText(getArguments().getString("legi_office"));
        legi_state.setText(getArguments().getString("legi_state"));

        if (getArguments().getString("legi_fax") == null)
            legi_fax.setText("N.A.");
        else
            legi_bday.setText(getArguments().getString("legi_office"));

        legi_bday.setText(getArguments().getString("legi_bday"));

        return  view;

    }

}
