package com.Introbe.mainpage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.Introbe.IntuDatabase.DBUser.myID;
import com.Introbe.IntuDatabase.Util.photo;
import com.Introbe.R;
import com.Introbe.mainpage.Board.UpdatingBoard;
import com.Introbe.mainpage.Board.contentDTO;
import com.Introbe.mainpage.Board.listViewAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class frag2 extends fragParent {

    private ListView listview;
    private listViewAdapter adapter;

    //스크롤 밑에 닿으면 증가
    int start_ = 0, end_ = 50;

    private photo photoDownload = new photo();
    ArrayList<contentDTO> cont = photoDownload.getContentDTO();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        refreshView(); // 뷰 설정
        /*
        =============================================================================================
         */

        TabHost tabHost1 = (TabHost) getView().findViewById(R.id.tabHost1);
        tabHost1.setup();

        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("게시판");
        tabHost1.addTab(ts1);

        Button button_uploadgesi = (Button) getActivity().findViewById(R.id.uploadgesi);

        button_uploadgesi.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), UpdatingBoard.class));

                refreshView(); // 뷰 설정
            }
        });



        //시간상 문제로 인하여 어뎁터뷰를 재활용 하지 않고 그대로 전부 삭제한후 재 출력 -> (오버헤드 무시)
        //+ 리사이클러 뷰 사용가능

        /*
        =============================================================================================
         */

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("구독");
        tabHost1.addTab(ts2);

          /*
        =============================================================================================
         */
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3");
        ts3.setContent(R.id.content3);
        ts3.setIndicator("내가 쓴글");
        tabHost1.addTab(ts3);



        /*
        if(listview !=null)
        {
            listview.setOnScrollListener(new AbsListView.OnScrollListener() {
                boolean lastItemVisibleFlag =false;
                @Override
                public void onScroll(AbsListView view,int firstVisibleItem,int visibleItemCount,int totalItemCount) {
                    // 현재 화면에 보이는 첫번째 리스트 아이템의 번호(firstVisibleItem) +
                    // 현재 화면에 보이는 리스트 아이템의 갯수(visibleItemCount)가
                    // 리스트 전체의 갯수(totalItemCount) -1 보다 크거나 같을때
                    lastItemVisibleFlag = (totalItemCount >0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
                }
                @Override
                public void onScrollStateChanged(AbsListView view,int scrollState) {
                    //OnScrollListener.SCROLL_STATE_IDLE은 스크롤이 이동하다가 멈추었을때 발생되는 스크롤 상태입니다.
                    //즉 스크롤이 바닥에 닿아 멈춘 상태에 처리를 하겠다는 뜻
                    if(scrollState == SCROLL_STATE_IDLE && lastItemVisibleFlag) {
                        //화면이 바닥에 닿을때 처리
                    }
                }
            });

         */
    }


    //리사이클러 뷰로 대체 가능, 최근 50개만 보여줌
    private void refreshView()
    {

        adapter = new listViewAdapter();
        listview=null;

        listview = (ListView) getActivity().findViewById(R.id.listview1);
        listview.setAdapter(adapter);

            for (int i = cont.size()-1; i >= 0 && i>= cont.size()-4; i--) {

                String thisId = cont.get(i).getUserId();
                String thisExp = cont.get(i).getExplain();

                start_++;

                adapter.addItem(getContext(), thisId, thisExp, cont.get(i).getImageUrl());

                if(0 == cont.size()) {
                        cont = photoDownload.getContentDTO();
                }
            }
        }


    }


