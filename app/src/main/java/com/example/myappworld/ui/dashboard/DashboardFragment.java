package com.example.myappworld.ui.dashboard;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myappworld.MainActivity;
import com.example.myappworld.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
private TextView text_dashboard;
WebView webView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
         final WebView webView = root.findViewById(R.id.webwieww);

        webView.getSettings().setJavaScriptEnabled(true);

        final ProgressDialog pd = ProgressDialog.show(getContext(), "Harita Yükleniyor...", "Güncel Verilere Ulaşılıyor. \n Lütfen Bekleyiniz...", true);



        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);


        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getContext(), description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                pd.show();
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();

                String webUrl = webView.getUrl();

            }



    });

                webView .loadUrl("https://www.google.com/maps/d/viewer?mid=1yCPR-ukAgE55sROnmBUFmtLN6riVLTu3&ll=37.41417802249552%2C36.139187375000006&z=5");




        return root;



    }

    public void onResume(){
        super.onResume();


        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("Koronavirüs(Covid19) Harita");

    }
}
