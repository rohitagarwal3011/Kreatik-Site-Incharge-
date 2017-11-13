package com.app.rbc.siteincharge.activities;

import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.app.rbc.siteincharge.R;
import com.app.rbc.siteincharge.fragments.SiteOverviewFragment;
import com.app.rbc.siteincharge.fragments.SiteOverviewListFragment;
import com.app.rbc.siteincharge.models.db.models.Site;
import com.app.rbc.siteincharge.utils.AppUtil;
import com.app.rbc.siteincharge.utils.TagsPreferences;
import com.orm.SugarContext;

import java.util.List;

public class SiteOverviewActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public static final int ACTIVITY = 2;
    private SiteOverviewListFragment siteOverviewListFragment;
    private SiteOverviewFragment siteOverviewFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_overview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SugarContext.init(this);
        MultiDex.install(this);
        findSiteOfIncharge();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private void findSiteOfIncharge() {
        String incharge_id = AppUtil.getString(getApplicationContext(), TagsPreferences.USER_ID);
        List<Site> siteList = Site.find(Site.class,"incharge = ?",incharge_id);
        if(siteList.size() != 0) {
            setFragment(2,siteList.get(0).getId());
        }
        else {
            onBackPressed();
            Toast.makeText(getApplicationContext(),
                    "No site found for this incharge",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void setFragment(int code,Object... data) {
        FragmentManager fm = getSupportFragmentManager();

        switch (code) {
            case 1 :
                siteOverviewListFragment = new SiteOverviewListFragment();
                fm.beginTransaction()
                        .replace(R.id.fragment_container,siteOverviewListFragment)
                        .commit();
                break;

            case 2 :
                siteOverviewFragment = new SiteOverviewFragment();
                siteOverviewFragment.site = (long)data[0];
                fm.beginTransaction()
                        .replace(R.id.fragment_container,siteOverviewFragment)
                        .commit();
                break;
        }
    }

    public void publishAPIResponse(int status,int code,String... message) {
        switch (code) {

            case 11 :
                siteOverviewListFragment.publishAPIResponse(status,code,message[0]);
                break;
            case 21 :
                siteOverviewFragment.publishAPIResponse(status,code,message);
                break;

        }
    }
}
