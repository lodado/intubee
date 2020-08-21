package com.Introbe.mainpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Introbe.IntuDatabase.Util.photo;
import com.Introbe.R;

public class frag1 extends Fragment {

    private photo photoing = new photo();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] stcArray = getResources().getStringArray(R.array.frag1slide)

        AutoScrollAdapter scrolling = new AutoScrollAdapter(this, srcArray)



    }
}

