package com.Introbe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.Introbe.IntuDatabase.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            String myName = user.getDisplayName();
            String myEmail = user.getEmail();
            // Uri photoUrl = user.getPhotoUrl();// User is signed in

            //User's data
            DataBaseUser mine = MyID.getInstance(myName, myEmail);

        } else {
            Toast myToast = Toast.makeText(this.getApplicationContext(),"login failed! stop running application", Toast.LENGTH_SHORT);
            myToast.show();

            finish();
        }
    }
}