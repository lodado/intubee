package com.Introbe.mainpage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.Introbe.IntuDatabase.Util.photo;
import com.Introbe.R;
import com.Introbe.mainpage.Board.UpdatingBoard;
import com.Introbe.mainpage.Board.contentDTO;
import com.Introbe.mainpage.Board.listViewAdapter;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class frag2 extends Fragment {

    ListView listview ;
    listViewAdapter adapter;

    private photo photoDownload = new photo();
    ArrayList<contentDTO> cont=photoDownload.getContentDTO();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



         /*
        =============================================================================================
         */

        TabHost tabHost1 = (TabHost) getView().findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("TAB 1") ;
        tabHost1.addTab(ts1)  ;

        Button button_uploadgesi = (Button) getActivity().findViewById(R.id.uploadgesi) ;

        button_uploadgesi.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getActivity().getApplicationContext(), UpdatingBoard.class));

                refreshView(); // 뷰 설정
            }
        });

        refreshView(); //뷰 설정
        //시간상 문제로 인하여 어뎁터뷰를 재활용 하지 않고 그대로 전부 삭제한후 재 출력 -> (오버헤드 무시)
        //+ 리사이클러 뷰 사용가능



        /*
        =============================================================================================
         */

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("TAB 2") ;
        tabHost1.addTab(ts2) ;

          /*
        =============================================================================================
         */

        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.content3) ;
        ts3.setIndicator("TAB 3") ;
        tabHost1.addTab(ts3) ;

    }

    private void refreshView()
    {
        adapter = new listViewAdapter();
        listview=null;

        listview = (ListView) getActivity().findViewById(R.id.listview1);
        listview.setAdapter(adapter);

            for (int i = 0; i < cont.size(); i++) {
                String thisId = cont.get(i).getUserId();
                String thisExp = cont.get(i).getExplain();


                adapter.addItem(getConText(), thisId, thisExp, Uri.parse(cont.get(i).getImageUrl()));


                if(i-1 == cont.size()) cont=photoDownload.getContentDTO();
            }

    }

}

