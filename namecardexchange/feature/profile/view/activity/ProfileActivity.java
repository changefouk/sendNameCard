package com.mbox.administrator.namecardexchange.feature.profile.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.base.BaseActivity;
import com.mbox.administrator.namecardexchange.feature.profile.view.fragment.FragmentDataManager;
import com.mbox.administrator.namecardexchange.feature.profile.view.fragment.FragmentSetting;
import com.mbox.administrator.namecardexchange.feature.profile.viewmodel.ProfileActivityViewmodel;

import jp.s64.android.animatedtoolbar.AnimatedToolbar;

public class ProfileActivity extends BaseActivity {

    private static final String TAG = ProfileActivity.class.getSimpleName();
    private AnimatedToolbar mToolbar;

    private ProfileActivityViewmodel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setUi();
        initInstance();

        if (savedInstanceState == null) {
            if (viewmodel.SAVE_KEY == viewmodel.DATA_MANAGER) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.content_profile, FragmentDataManager.newInstance()).commit();
            }
            if (viewmodel.SAVE_KEY == viewmodel.SETTING) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.content_profile, FragmentSetting.newInstance()).commit();
            }
        }
    }

    @Override
    protected void setUi() {
        mToolbar = findViewById(R.id.toolbar_profile);
    }

    @Override
    protected void initInstance() {
        viewmodel = ViewModelProviders.of(this).get(ProfileActivityViewmodel.class);
        viewmodel.SAVE_KEY = getIntent().getExtras().getInt(viewmodel.FRAGMENT_KEY);
        setToolbar(viewmodel.SAVE_KEY);

    }

    private void setToolbar(int key) {
        setSupportActionBar(mToolbar);
        switch (key) {
            case 1:
                setTitle(" " + getString(R.string.data_manager));
                break;
            case 2:
                setTitle(" " + getString(R.string.setting_profile));
                break;
        }
        mToolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }
}
