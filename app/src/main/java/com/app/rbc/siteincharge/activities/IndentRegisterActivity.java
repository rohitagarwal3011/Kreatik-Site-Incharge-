package com.app.rbc.siteincharge.activities;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.rbc.siteincharge.Manifest;
import com.app.rbc.siteincharge.R;
import com.app.rbc.siteincharge.fragments.UpdatePlaceholderFragment;

public class IndentRegisterActivity extends AppCompatActivity {
    private UpdatePlaceholderFragment updatePlaceholderFragment;
    private Toolbar toolbar;


    public static final int ACTIVITY = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent_registers);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setFragment(0);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home :
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
    public void popBackStack() {
        getSupportFragmentManager().popBackStackImmediate();
    }

    public void checkPermission(int permissionCode) {
        switch (permissionCode) {
            case 6: if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        permissionCode);
                break;

            }

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 6: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {

                    Toast.makeText(this,
                            "Permission Denied!",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            }

        }
    }





    public void setFragment(int code,Object... data) {
        FragmentManager fm = getSupportFragmentManager();
        switch (code) {
            case 0 :
                getSupportActionBar().setTitle("Indents & Registers");
                updatePlaceholderFragment = new UpdatePlaceholderFragment();
                fm.beginTransaction()
                        .replace(R.id.fragment_container, updatePlaceholderFragment)
                        .commit();
                break;

        }
    }
}