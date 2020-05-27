package com.example.myappworld.ui.notifications;

import android.icu.text.CaseMap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myappworld.Api;
import com.example.myappworld.CustomListview;
import com.example.myappworld.Hero;
import com.example.myappworld.MainActivity;
import com.example.myappworld.R;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.jsoup.nodes.Document;
import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationsFragment extends Fragment {



    String url;
    private NotificationsViewModel notificationsViewModel;
    WebView webView;
TextView textView;
    public ListView listView;
    public String [] haberbaslik = {};
    public  String [] habericerik = {};
    public  String [] habertarih = {};
    public   String [] image = {};
    public   String [] sitelink = {};
    ImageView productimage;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        listView = (ListView)root.findViewById(R.id.listView);
        productimage = (ImageView)root.findViewById(R.id.imageView2);



        getHeroes();



        final SwipeRefreshLayout maSwipeRefreshLayout;

        maSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipeToRefreshh);
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



        return root;
    }

    private void getHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Hero>> call = api.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroList = response.body();

                //Creating an String array for the ListView
                String[] heroestitle = new String[heroList.size()];
                final String[] link = new String[heroList.size()];
                habericerik = new String[heroList.size()];
                haberbaslik= new String[heroList.size()];
                habertarih= new String[heroList.size()];
                image= new String[heroList.size()];
                sitelink= new String[heroList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    habericerik[i] = heroList.get(i).getDescription();
                    haberbaslik[i] = heroList.get(i).getTitle();
                    habertarih[i] = heroList.get(i).getPublishedAt();
                    image[i] = heroList.get(i).getUrlToImage();
                    sitelink[i] = heroList.get(i).getUrl();


                }


                //displaying the string array into listview

                CustomListview customListview = new CustomListview(getActivity(),haberbaslik,habericerik,image,habertarih,sitelink);
                listView.setAdapter(customListview);


                // Set an item click listener for ListView



            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onResume(){
        super.onResume();

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("Koronavirüs(Covid19) Haberler");

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
