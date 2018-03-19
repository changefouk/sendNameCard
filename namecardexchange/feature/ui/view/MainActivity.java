package com.mbox.administrator.namecardexchange.feature.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.auth.FirebaseUser;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.base.BaseActivity;
import com.mbox.administrator.namecardexchange.feature.collection.view.fragment.FragmentCollection;
import com.mbox.administrator.namecardexchange.feature.exchange.view.fragment.FragmentExchange;
import com.mbox.administrator.namecardexchange.feature.profile.view.fragment.FragmentProfile;
import com.mbox.administrator.namecardexchange.feature.search.view.FragmentSearch;
import com.mbox.administrator.namecardexchange.util.CustomViewPager;
import com.mbox.administrator.namecardexchange.util.ViewPagerAdapter;

import jp.s64.android.animatedtoolbar.AnimatedToolbar;



public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private AnimatedToolbar mToolbar;
    private CustomViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private MenuItem prevMenuItem;
    private FrameLayout searchlayout;

    private boolean isSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUi();
        initInstance();
    }

    @Override
    protected void setUi() {
        mToolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.navigation_main);
        searchlayout = findViewById(R.id.content_search);
    }

    @Override
    protected void initInstance() {
        setToolbar();
//        logoutButton();
        setupViewPager(viewPager);
        setButtomNavigation();
        setViewpagerChangeListenner(viewPager);

    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        switch (viewPager.getCurrentItem()) {
            case 0:
                setTitle("  " + getString(R.string.menu_string_card));
                isSearch = true;
                supportInvalidateOptionsMenu();
                break;
            case 1:
                setTitle("  " + getString(R.string.menu_string_exchange));
                break;
            case 2:
                setTitle("  " + getString(R.string.menu_string_profile));
                break;
        }
    }

    private void setViewpagerChangeListenner(CustomViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setTitle("  " + getString(R.string.menu_string_card));
                        isSearch = true;
                        supportInvalidateOptionsMenu();
                        break;
                    case 1:
                        setTitle("  " + getString(R.string.menu_string_exchange));
                        supportInvalidateOptionsMenu();
                        isSearch = false;
                        break;
                    case 2:
                        setTitle("  " + getString(R.string.menu_string_profile));
                        supportInvalidateOptionsMenu();
                        isSearch = false;
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setButtomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itemCard:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.itemExchange:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.itemProfile:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });
    }

    private void setupViewPager(CustomViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(FragmentCollection.newInstance());
        adapter.addFragment(FragmentExchange.newInstance());
        adapter.addFragment(FragmentProfile.newInstance());
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);
    }

    public void searchHide() {
        searchlayout.setVisibility(View.GONE);
    }

//    private void logoutButton() {
//        buttonLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
////            public void onClick(View view) {
//////                FirebaseAuth.getInstance().signOut();
//////                if (LoginManager.getInstance().getLoginBehavior() != null) {
//////                    LoginManager.getInstance().logOut();
//////
//////                }
//////                Intent i = new Intent(view.getContext(), LoginActivity.class);
//////                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//////                startActivity(i);
////                hidetitle();
//            }
//        });
//    }

    public void hideBotttomNav(){
        bottomNavigationView.setVisibility(View.GONE);
        slideToButtom(bottomNavigationView);
    }

    public void showBottomNav(){
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUI(mFirebaseUser);
    }

    @Override
    protected void updateUI(FirebaseUser user) {
        super.updateUI(user);
        if (user != null) {

        } else {
            Intent i = new Intent(getBaseContext(), LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    public void crashButton() {
        Crashlytics.log("Hello Crash");
        Crashlytics.getInstance().crash();
    }

    private void hidetitle() {
        getSupportActionBar().hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //  return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        MenuItem itemSearch = menu.findItem(R.id.action_search);

        if (isSearch) {
            itemSearch.setVisible(true);
            Log.d(TAG, "SEARCH VISIBLE");
        } else {
            itemSearch.setVisible(false);
            Log.d(TAG, "SEARCH GONE");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                //TODO : SEARCH
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.anim_slide_from_right, R.anim.anim_slide_to_left)
                        .replace(R.id.content_search, FragmentSearch.newInstance())
                        .commit();
                searchlayout.setVisibility(View.VISIBLE);
                searchlayout.setClickable(true);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (searchlayout.getVisibility() == View.VISIBLE) {
            slideToTop(searchlayout);
        } else {
            super.onBackPressed();
        }
    }

}