package com.Introbe.mainpage.mainpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Introbe.R;

public class frag1 extends Fragment {


    ViewFlipper v_fllipper;


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
        ts1.setContent(R.id.content1_1) ;
        ts1.setIndicator("TAB 1") ;
        tabHost1.addTab(ts1)  ;


        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content1_2) ;
        ts2.setIndicator("TAB 2") ;
        tabHost1.addTab(ts2) ;

        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.content1_3) ;
        ts3.setIndicator("TAB 3") ;
        tabHost1.addTab(ts3) ;

        int images[] = {
                R.drawable.b1,
                R.drawable.b2,
                R.drawable.wallpaper_alena_velichko_08_1920x1200
        };

        v_fllipper = getView().findViewById(R.id.image_slide);

        for(int image : images) {
            fllipperImages(image);
        }

    }
}

