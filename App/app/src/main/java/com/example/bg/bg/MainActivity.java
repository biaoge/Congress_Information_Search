package com.example.bg.bg;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.Type;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//  UI object
    private MenuItem nav_legi;
    private MenuItem nav_bill;
    private MenuItem nav_comm;
    private MenuItem nav_fav;
    private FrameLayout fg_layout;

//  Fragment object
    private Fg_legiContent fg_legi;
    private Fg_billContent fg_bill;
    private Fg_commContent fg_comm;
    private Fg_favContent fg_fav;

    private FragmentManager fmanager;
    private FragmentTransaction fTransaction;

//  发送请求的队列
    private RequestQueue mQueue;

//  存放legislator的ArrayList
    private ArrayList<Legislator> alegislators;
    private ArrayList<Legislator> hlegislators;
    private ArrayList<Legislator> slegislators;


//  存放bill的ArrayList
    private ArrayList<Bill> actbills;
    private ArrayList<Bill> newbills;

//  存放committee的ArrayList
    private ArrayList<Committee> housecomms;
    private ArrayList<Committee> senatecomms;
    private ArrayList<Committee> jointcomms;

//  存放favorite的Arraylist
    private ArrayList<Legislator> fav_legsislator;
    private ArrayList<Bill> fav_bills;
    private ArrayList<Committee> fav_comms;

    public ArrayList<Committee> getFav_comms() {
        return fav_comms;
    }

    //  app初始化
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     //设置要显示的视图
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Legislators");

        fav_comms = new ArrayList<>();
        fav_bills = new ArrayList<>();
        fav_legsislator = new ArrayList<>();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//      绑定侧边导航栏，且设置监听
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fmanager = getFragmentManager();
        bindViews();   //绑定视图

//      创建请求序列
        mQueue = Volley.newRequestQueue(this);
//      刚登陆系统，就发送json请求，获取all legislator的数据
//        String allLegislatorUrl = "http://congress.api.sunlightfoundation.com/legislators?apikey=2604449d386949c086c6b12faa3479f6&per_page=all";
        String allLegislatorUrl = "http://biaoge1-env.us-west-2.elasticbeanstalk.com/?lt=1";
         final JsonObjectRequest jor = new JsonObjectRequest(allLegislatorUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JsonObject jo = new JsonParser().parse(jsonObject.toString()).getAsJsonObject();
//                        Log.d("JSON!", jsonObject.toString());
                        JsonArray jsonArray = jo.getAsJsonArray("results");
//                        Log.d("JsonArray", jsonArray.toString());

                        alegislators = new ArrayList<Legislator>();
                        hlegislators = new ArrayList<Legislator>();
                        slegislators = new ArrayList<Legislator>();
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JsonObject temp = (JsonObject) jsonArray.get(i);
                            Legislator legi = new Gson().fromJson(temp, Legislator.class);
                            alegislators.add(legi);
                            if (legi.getChamber().equals("house"))
                            {
                                hlegislators.add(legi);
                            }
                            else
                            {
                                slegislators.add(legi);
                            }
//                            Log.d("All_legislator!", legi.toString());
                        }
                        Collections.sort(alegislators, new LegiSortByState());
                        Collections.sort(hlegislators, new LegiSortByLastname());
                        Collections.sort(slegislators, new LegiSortByLastname());

                        fTransaction = fmanager.beginTransaction();
                        hideAllFragment(fTransaction);
                        fg_legi = Fg_legiContent.newInstance(alegislators, hlegislators, slegislators, fav_legsislator,  getApplicationContext() );
                        fTransaction.add(R.id.fg_content,fg_legi);
                        fTransaction.commit();

                        for (int i = 0; i < hlegislators.size(); i++) {
//                                Log.d("House_legislator!", hlegislators.get(i).toString());

                        }
                        for (int i = 0; i < slegislators.size(); i++){
//                            Log.d("Senate_legislator!", slegislators.get(i).toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Legislator_ERR!", volleyError.getMessage(), volleyError);
            }
        });
        mQueue.add(jor);

//      发送请求获取bill数据
//        String actBillUrl = "http://congress.api.sunlightfoundation.com/bills?history.active=true&last_version.urls.pdf__exists=true&apikey=2604449d386949c086c6b12faa3479f6&per_page=50";
        String actBillUrl = "http://biaoge1-env.us-west-2.elasticbeanstalk.com/?bt=1";
        final JsonObjectRequest billjor_act = new JsonObjectRequest(actBillUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JsonObject billjo_act =  new JsonParser().parse(jsonObject.toString()).getAsJsonObject();
                        JsonArray billjsonarray_act = billjo_act.getAsJsonArray("results");
//                        Log.d("billJsonArray", billjsonarray_act.toString());
                        actbills = new ArrayList<Bill>();
                        for (int i = 0; i < billjsonarray_act.size(); i++ ){
                            JsonObject temp = (JsonObject) billjsonarray_act.get(i);
                            Bill bill_act = new Gson().fromJson(temp, Bill.class);
//                            Log.d("actBill!", bill_act.getBill_id());
                            actbills.add(bill_act);
                        }
                        for (int i = 0; i < actbills.size(); i++){
//                            Log.d("Actbill_ID", actbills.get(i).getBill_id());
//                            Log.d("actbills_Size", String.valueOf(actbills.size()));
                        }
                        Collections.sort(actbills, new BillSortByIntroduce());
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("ACTBILL_ERR!", volleyError.getMessage(), volleyError);
            }
        });
        mQueue.add(billjor_act);

//        String newBillUrl = "http://congress.api.sunlightfoundation.com/bills?history.active=false&last_version.urls.pdf__exists=true&apikey=2604449d386949c086c6b12faa3479f6&per_page=50";
        String newBillUrl = "http://biaoge1-env.us-west-2.elasticbeanstalk.com/?bt=2";
        final JsonObjectRequest billjor_new = new JsonObjectRequest(newBillUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JsonObject billjo_new = new JsonParser().parse(jsonObject.toString()).getAsJsonObject();
                        JsonArray billjsonarray_new = billjo_new.getAsJsonArray("results");
                        newbills = new ArrayList<Bill>();
                        for (int i = 0; i< billjsonarray_new.size(); i++){
                            JsonObject temp = (JsonObject) billjsonarray_new.get(i);
                            Bill bill_new = new Gson().fromJson(temp, Bill.class);
//                            Log.d("newBill!", bill_new.getBill_id());
                            newbills.add(bill_new);
                        }
                        Collections.sort(newbills, new BillSortByIntroduce());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("NEWBILL_ERR!", volleyError.getMessage(), volleyError );
            }
        });
        mQueue.add(billjor_new);


//     发送请求，获取committee数据
//        String CommHouseUrl = "http://congress.api.sunlightfoundation.com/committees?chamber=house&apikey=2604449d386949c086c6b12faa3479f6&per_page=all";
        final String CommHouseUrl = "http://biaoge1-env.us-west-2.elasticbeanstalk.com/?ct=1";
        final JsonObjectRequest commjor_house = new JsonObjectRequest(CommHouseUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JsonObject commjo_house = new JsonParser().parse(jsonObject.toString()).getAsJsonObject();
                        JsonArray commjsonArray_house = commjo_house.getAsJsonArray("results");
//                        Log.d("Comm_houseArray!", commjsonArray_house.toString());
                        housecomms = new ArrayList<Committee>();
                        for (int i = 0 ; i < commjsonArray_house.size(); i++)
                        {
                            JsonObject temp = (JsonObject) commjsonArray_house.get(i);
                            Committee comm_house = new Gson().fromJson(temp, Committee.class);
                            housecomms.add(comm_house);
                        }
                        Collections.sort(housecomms, new CommSortByName());
                        for (int i = 0 ;i < housecomms.size(); i++ )
                        {
//                            Log.d("CommID_House!", housecomms.get(i).getCommittee_id());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("CommERR_House!", volleyError.getMessage(), volleyError );
            }
        });
        mQueue.add(commjor_house);

//        String CommSenateUrl = "http://congress.api.sunlightfoundation.com/committees?chamber=senate&apikey=2604449d386949c086c6b12faa3479f6&per_page=all";
        String CommSenateUrl = "http://biaoge1-env.us-west-2.elasticbeanstalk.com/?ct=2";
        final JsonObjectRequest commjor_senate = new JsonObjectRequest(CommSenateUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JsonObject commjo_senate = new JsonParser().parse(jsonObject.toString()).getAsJsonObject();
                        JsonArray commjsonArray_senate = commjo_senate.getAsJsonArray("results");
//                        Log.d("Comm_SenateArray", commjsonArray_senate.toString());
                        senatecomms = new ArrayList<Committee>();
                        for (int i = 0; i < commjsonArray_senate.size(); i++)
                        {
                            JsonObject temp = (JsonObject) commjsonArray_senate.get(i);
                            Committee comm_senate = new Gson().fromJson(temp, Committee.class);
                            senatecomms.add(comm_senate);
                        }
                        Collections.sort(senatecomms, new CommSortByName());
                        for (int i = 0; i < senatecomms.size(); i++)
                        {
//                            Log.d("CommID_Senate", senatecomms.get(i).getCommittee_id());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("CommERR_Senate!", volleyError.getMessage(), volleyError );
            }
        });
        mQueue.add(commjor_senate);

//        String CommJointUrl = "http://congress.api.sunlightfoundation.com/committees?chamber=joint&apikey=2604449d386949c086c6b12faa3479f6&per_page=all";
        String CommJointUrl = "http://biaoge1-env.us-west-2.elasticbeanstalk.com/?ct=3";
       final JsonObjectRequest commjor_joint = new JsonObjectRequest(CommJointUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JsonObject commjo_joint = new JsonParser().parse(jsonObject.toString()).getAsJsonObject();
                        JsonArray commjsonArray_joint = commjo_joint.getAsJsonArray("results");
//                        Log.d("Comm_JointArray", commjsonArray_joint.toString());
                        jointcomms = new ArrayList<Committee>();
                        for (int i = 0; i < commjsonArray_joint.size(); i++)
                        {
                            JsonObject tempp = (JsonObject) commjsonArray_joint.get(i);
                            Committee comm_joint = new Gson().fromJson(tempp, Committee.class);
                            jointcomms.add(comm_joint);
                        }
                        Collections.sort(jointcomms, new CommSortByName());
                        for (int i = 0 ; i< jointcomms.size(); i++)
                        {
//                            Log.d("CommID_Joint!", jointcomms.get(i).getCommittee_id());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("CommERR_Joint!", volleyError.getMessage(), volleyError);
            }
        });
        mQueue.add(commjor_joint);



    }


    public void bindViews() {
        nav_legi = (MenuItem) findViewById(R.id.nav_legi);
        nav_bill = (MenuItem) findViewById(R.id.nav_bill);
        nav_comm = (MenuItem) findViewById(R.id.nav_comm);
        nav_fav = (MenuItem) findViewById(R.id.nav_fav);
        fg_layout = (FrameLayout) findViewById(R.id.fg_content);
    }

    //  隐藏所有fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg_legi != null) fragmentTransaction.hide(fg_legi);
        if (fg_bill != null) fragmentTransaction.hide(fg_bill);
        if (fg_comm != null) fragmentTransaction.hide(fg_comm);
        if (fg_fav != null) fragmentTransaction.hide(fg_fav);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (fmanager.getBackStackEntryCount()==0)
        {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
        else {
            fmanager.popBackStack();
        }
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }

    }

//  初始化顶部菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//  通过getMenuInflater加载菜单
//  亦可通过menu.add(1,RED,4,"红色")  （菜单项的组号，ID，排序号，标题）
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//   顶部工具栏被选中时
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    侧面菜单栏被选中时
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {    //回调
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        fTransaction = fmanager.beginTransaction();
//        FragmentTransaction fTransaction = fmanager.beginTransaction();
//        hideAllFragment(fTransaction);

        if (id == R.id.nav_legi) {
            // 改变屏幕左下角的文字
            TextView tv = (TextView) findViewById(R.id.content_text);
            String legi = "Legislators";
            tv.setText(legi);
            setTitle(legi);
            //加载对应的Fragment
            hideAllFragment(fTransaction);
            if (fg_legi == null)
            {
                fg_legi = Fg_legiContent.newInstance(alegislators, hlegislators, slegislators, fav_legsislator,  this);
                fTransaction.add(R.id.fg_content,fg_legi);
            }
            else
                fTransaction.show(fg_legi);

        } else if (id == R.id.nav_bill) {
            TextView tv = (TextView) findViewById(R.id.content_text);
            String bills = "Bills";
            tv.setText(bills);
            setTitle(bills);

            hideAllFragment(fTransaction);
            if (fg_bill == null)
            {
                fg_bill = Fg_billContent.newInstance(actbills, newbills, fav_bills, this);
                fTransaction.add(R.id.fg_content,fg_bill);
            }
            else
                fTransaction.show(fg_bill);

        } else if (id == R.id.nav_comm) {
            TextView tv = (TextView) findViewById(R.id.content_text);
            String comm = "Committees";
            tv.setText(comm);
            setTitle(comm);

            hideAllFragment(fTransaction);
            if (fg_comm == null)
            {
                fg_comm = Fg_commContent.newInstance(housecomms, senatecomms, jointcomms, fav_comms, this);
                fTransaction.add(R.id.fg_content,fg_comm);
            }
            else
                fTransaction.show(fg_comm);

        } else if (id == R.id.nav_fav) {
            TextView tv = (TextView) findViewById(R.id.content_text);
            String star = "Favorites";
            tv.setText(star);
            setTitle(star);

            hideAllFragment(fTransaction);

            SharedPreferences mspAdd = getSharedPreferences("sp_AddComm", MODE_PRIVATE);
            SharedPreferences sp_billAdd = getSharedPreferences("sp_AddBill", MODE_PRIVATE);
            SharedPreferences sp_legiAdd = getSharedPreferences("sp_LegiAdd", MODE_PRIVATE);

//            清除sharedpreference数据
//            SharedPreferences.Editor editor = mspAdd.edit();
//            editor.clear();
//            editor.commit();
//            SharedPreferences.Editor editor1 = sp_billAdd.edit();
//            editor1.clear();
//            editor1.commit();
//            SharedPreferences.Editor editor2 = sp_legiAdd.edit();
//            editor2.clear();
//            editor2.commit();

//            SharedPreferences.Editor editor1 = mspRemove.edit();
//            editor1.clear();
//            editor1.commit();


//          存储/删除 favorite committee
            for (int i = 0; i < housecomms.size(); i++ )
            {
                if (mspAdd.getString(housecomms.get(i).getCommittee_id(), "default_id").equals("default_id"))
                    ;
                else if (mspAdd.getString(housecomms.get(i).getCommittee_id(), "default_id").equals("0"))  //表示删除
                    fav_comms.remove(housecomms.get(i));
                else {              //表示添加
                    if (fav_comms.size() == 0)
                        fav_comms.add(housecomms.get(i));
                    else {
                        boolean inFav =  false;
                        for (int j = 0; j < fav_comms.size(); j++) {
                            if (fav_comms.get(j).getCommittee_id().equals(housecomms.get(i).getCommittee_id()))
                                inFav = true;
                        }
                        if (!inFav) fav_comms.add(housecomms.get(i));
                    }
                }
            }

            for (int i = 0; i < senatecomms.size(); i++ )
            {
                if (mspAdd.getString(senatecomms.get(i).getCommittee_id(), "default_id").equals("default_id"))
                    ;
                else if (mspAdd.getString(senatecomms.get(i).getCommittee_id(), "default_id").equals("0"))  //表示删除
                    fav_comms.remove(senatecomms.get(i));
                else {              //表示添加
                    if (fav_comms.size() == 0)
                        fav_comms.add(senatecomms.get(i));
                    else {
                        boolean inFav =  false;
                        for (int j = 0; j < fav_comms.size(); j++) {
                            if (fav_comms.get(j).getCommittee_id().equals(senatecomms.get(i).getCommittee_id()))
                                inFav = true;
                        }
                        if (!inFav) fav_comms.add(senatecomms.get(i));
                    }
                }
            }

            for (int i = 0; i < jointcomms.size(); i++ )
            {
                if (mspAdd.getString(jointcomms.get(i).getCommittee_id(), "default_id").equals("default_id"))
                    ;
                else if (mspAdd.getString(jointcomms.get(i).getCommittee_id(), "default_id").equals("0"))  //表示删除
                    fav_comms.remove(jointcomms.get(i));
                else {              //表示添加
                    if (fav_comms.size() == 0)
                        fav_comms.add(jointcomms.get(i));
                    else {
                        boolean inFav =  false;
                        for (int j = 0; j < fav_comms.size(); j++) {
                            if (fav_comms.get(j).getCommittee_id().equals(jointcomms.get(i).getCommittee_id()))
                                inFav = true;
                        }
                        if (!inFav) fav_comms.add(jointcomms.get(i));
                    }
                }
            }
//          存储/删除 favorite bill
            for (int i = 0; i < actbills.size(); i++)
            {
                if (sp_billAdd.getString(actbills.get(i).getBill_id().toUpperCase(), "bill_id").equals("bill_id"))
                    ;
                else if (sp_billAdd.getString(actbills.get(i).getBill_id().toUpperCase(), "bill_id").equals("0"))
                    fav_bills.remove(actbills.get(i));
                else {
                    if (fav_bills.size() == 0)
                        fav_bills.add(actbills.get(i));
                    else {
                        boolean inFav = false;
                        for (int j = 0; j < fav_bills.size(); j++)
                        {
                            if (fav_bills.get(j).getBill_id().equals(actbills.get(i).getBill_id()))
                                inFav = true;
                        }
                        if (!inFav) fav_bills.add(actbills.get(i));
                    }
                }
            }

            for (int i = 0; i < newbills.size(); i++)
            {
                if (sp_billAdd.getString(newbills.get(i).getBill_id().toUpperCase(), "bill_id").equals("bill_id"))
                    ;
                else if (sp_billAdd.getString(newbills.get(i).getBill_id().toUpperCase(), "bill_id").equals("0"))
                    fav_bills.remove(newbills.get(i));
                else {
                    if (fav_bills.size() == 0)
                        fav_bills.add(newbills.get(i));
                    else {
                        boolean inFav = false;
                        for (int j = 0; j < fav_bills.size(); j++)
                        {
                            if (fav_bills.get(j).getBill_id().equals(newbills.get(i).getBill_id()))
                                inFav = true;
                        }
                        if (!inFav) fav_bills.add(newbills.get(i));
                    }
                }
            }
//          存储删除favorite legislator
            for (int i = 0; i < alegislators.size(); i++)
            {
                if (sp_legiAdd.getString(alegislators.get(i).getBioguide_id(), "legi_id").equals("legi_id"))
                    ;
                else if (sp_legiAdd.getString(alegislators.get(i).getBioguide_id(), "legi_id").equals("0"))
                    fav_legsislator.remove(alegislators.get(i));
                else {
                    if (fav_legsislator.size() == 0)
                        fav_legsislator.add(alegislators.get(i));
                    else {
                        boolean inFav = false;
                        for (int j = 0; j < fav_legsislator.size(); j++)
                        {
                            if (fav_legsislator.get(j).getBioguide_id().equals(alegislators.get(i).getBioguide_id()))
                                inFav = true;
                        }
                        if (!inFav) fav_legsislator.add(alegislators.get(i));
                    }
                }
            }
            Collections.sort(fav_legsislator, new LegiSortByLastname());
            Collections.sort(fav_bills, new BillSortByIntroduce());
            Collections.sort(fav_comms, new CommSortByName());

            fg_fav = Fg_favContent.newInstance(fav_legsislator, fav_bills, fav_comms, this);
            fTransaction.add(R.id.fg_content,fg_fav);

        } else if (id == R.id.nav_about) {

            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
//      执行fragment事务
        fTransaction.commit();

//      选中菜单栏后，菜单栏隐藏
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
