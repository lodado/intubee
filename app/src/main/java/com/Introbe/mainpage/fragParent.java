package com.Introbe.mainpage;

import android.webkit.WebView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;

import com.Introbe.mainpage.Board.listViewAdapter;

public class fragParent extends Fragment {

    //protected String loadUri = "http://chunghoney.gq";

    protected String loadUri = "http://127.0.0.1:8888";
    //protected String loadUri = "http://naver.com";
    protected ViewFlipper v_fllipper = null;
    protected WebView mWebView = null;

}
