package com.Introbe.mainpage;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Introbe.R;

public class frag1 extends Fragment {


    private ViewFlipper v_fllipper = null;

    private WebView mWebView = null;



    public void fllipperImages(int image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        v_fllipper.addView(imageView);      // 이미지 추가
        v_fllipper.setFlipInterval(4000);       // 자동 이미지 슬라이드 딜레이시간(1000 당 1초)
        v_fllipper.setAutoStart(true);          // 자동 시작 유무 설정

        // animation
        v_fllipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        v_fllipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabHost tabHost1 = (TabHost) getView().findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.브랜드추천) ;
        ts1.setIndicator("추천") ;
        tabHost1.addTab(ts1)  ;

        int images[] = {
                R.drawable.b1,
                R.drawable.b2,
                R.drawable.wallpaper_alena_velichko_08_1920x1200
        };

        v_fllipper = getView().findViewById(R.id.image_slide);

        for(int image : images) {
            fllipperImages(image);
        }



        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.신상품) ;
        ts2.setIndicator("신상품") ;
        tabHost1.addTab(ts2) ;

        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.베스트) ;
        ts3.setIndicator("베스트") ;
        tabHost1.addTab(ts3) ;

        TabHost.TabSpec ts4 = tabHost1.newTabSpec("Tab Spec 4") ;
        ts4.setContent(R.id.베스트1) ;
        ts4.setIndicator("이벤트") ;
        tabHost1.addTab(ts4) ;



        mWebView = (WebView) getView().findViewById(R.id.webView1);

        mWebView.getSettings().setJavaScriptEnabled(true);//자바스크립트 허용

        mWebView.loadUrl("http://chunghoney.gq");
        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.setWebViewClient(new WebViewClient() {//페이지 이동
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("check URL",url);
                view.loadUrl(url);
                return true;
            }
        });


    }




}

