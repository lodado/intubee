package com.Introbe.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.Introbe.R;
import com.Introbe.mainpage.Board.UpdatingBoard;
import com.Introbe.mainpage.Board.listViewAdapter;

public class frag2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabHost tabHost1 = (TabHost) getView().findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;
          /*
        =============================================================================================
         */

          String LIST_MENU[];

        LIST_MENU = new String[50];

        for(int i=0; i<50; i++) LIST_MENU[i] = "List"+i;

        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("TAB 1") ;
        tabHost1.addTab(ts1)  ;

        Button button_uploadgesi = (Button) getActivity().findViewById(R.id.uploadgesi) ;

        button_uploadgesi.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getActivity().getApplicationContext(), UpdatingBoard.class));
            }
        });

        ListView listview ;
        listViewAdapter adapter = new listViewAdapter();

        listview = (ListView) getActivity().findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        for(int i=0; i<100; i++)
            adapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ic_launcher_foreground),
                "Box", "Account Box Black 36dp") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ic_launcher_foreground),
                "Circle", "Account Circle Black 36dp") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ic_launcher_foreground),
                "Ind", "Assignment Ind Black 36dp") ;

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
}

