package com.Introbe.mainpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.Introbe.R;
import com.Introbe.User.userPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class mainActivity extends AppCompatActivity {

    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private Fragment frag_1, frag_2, frag_3, frag_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getHashKey();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            //dataBaseUser mine = myID.getInstance(user);
        } else {
            finish();
        }


        frag_1 = new frag1();
        frag_2 = new frag2();
        frag_3 = new frag3();
        frag_4 = new frag4();
        setContentView(R.layout.itsmainpage);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, frag_1).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());


    }



    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

             switch(menuItem.getItemId())
            {
                case R.id.frag1:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, frag_1).commitAllowingStateLoss();
                    break;
                case R.id.frag2:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, frag_2).commitAllowingStateLoss();
                    break;
                case R.id.frag3:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, frag_3).commitAllowingStateLoss();
                    break;
                case R.id.frag4:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, frag_4).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_btn1:
                return true;
            case R.id.action_btn2:
                //stopBtn();
                return true;
            case R.id.action_btn3:
                startActivity( new Intent(getApplicationContext(), userPage.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //뒤로가기 막기
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

}