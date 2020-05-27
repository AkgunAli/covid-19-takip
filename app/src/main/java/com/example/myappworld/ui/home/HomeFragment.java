package com.example.myappworld.ui.home;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.myappworld.MainActivity;
import com.example.myappworld.R;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;

import static android.content.ContentValues.TAG;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import android.app.Application;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment  {

    private HomeViewModel homeViewModel;
    public TextView text,text2,text3,text4,text5,text6,text7,text8,text9;
    TextView toplamvaka,iyilesen,olu,ulke;

    private RequestQueue mQueue,mQueue2;
    private ArrayList<String> mEntries;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);


        text = (TextView) root.findViewById(R.id.textView);
        text2 = (TextView) root.findViewById(R.id.textView2);
        text3 = (TextView) root.findViewById(R.id.textView3);
        text4 = (TextView) root.findViewById(R.id.textView4);
        text5 = (TextView) root.findViewById(R.id.textView5);
        text6 = (TextView) root.findViewById(R.id.textView6);
        text7 = (TextView) root.findViewById(R.id.textView7);
        text8 = (TextView) root.findViewById(R.id.textView8);
        text9 = (TextView) root.findViewById(R.id.textView9);






        final SwipeRefreshLayout maSwipeRefreshLayout;

        maSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipeToRefresh);
        maSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);

        maSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        maSwipeRefreshLayout.setRefreshing(false);
                        // burada ise Swipe Reflesh olduğunda ne yapacaksanız onu eklemeniz yeterlidir. Örneğin bir listeyi clear edebilir yada yeniden veri doldurabilirsiniz.

                        sayfayenile();

                    }
                }, 2500);
            }

        });


        mQueue = Volley.newRequestQueue(getContext());

        String url = "https://coronavirus-19-api.herokuapp.com/all";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String data = response.getString("cases");
                            text4.setText( data);



                            String data2 = response.getString("deaths");
                            text5.setText( data2);

                            String data3 = response.getString("recovered");
                            text6.setText(  data3);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

// Access the RequestQueue through your singleton class.
        mQueue.add(jsonObjectRequest);


        mQueue2 = Volley.newRequestQueue(getContext());

        String url2 = "https://coronavirus-19-api.herokuapp.com/countries/turkey";
        final JsonObjectRequest request2 = new JsonObjectRequest
                (Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response2) {
                        try {

                            String datahasta = response2.getString("cases");
                            text.setText( datahasta);


                            String dataolu = response2.getString("deaths");
                            text2.setText( dataolu);

                            String dataiyilesen = response2.getString("recovered");
                            text3.setText(dataiyilesen);
                            String databugun = response2.getString("todayCases");
                            text8.setText(databugun);
                            String databugunolen = response2.getString("todayDeaths");
                            text7.setText(databugunolen);



                        } catch (JSONException e) {
                            e.printStackTrace();
                            text7.setText("Gelmedi");


                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        text7.setText("Null");

                        // TODO: Handle error

                    }
                });




        mQueue2.add(request2);


        return root;

    }
    public void onResume(){
        super.onResume();

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("Koronavirüs(Covid19) Takip");

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu items for use in the action bar
        inflater.inflate(R.menu.mymenu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {

            new Handler().postDelayed(new Runnable() {
                @Override public void run() {

                    sayfayenile();

                }
            }, 2500);


        }
        return super.onOptionsItemSelected(item);
    }


    public void sayfayenile ()

    {


        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false);
        }
        ft.detach(this).attach(this).commit();

    }



}
