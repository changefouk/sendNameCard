package com.mbox.administrator.namecardexchange.feature.collection.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.base.BaseActivity;
import com.mbox.administrator.namecardexchange.feature.collection.view.fragment.FragmentCardDetail;

import jp.s64.android.animatedtoolbar.AnimatedToolbar;

public class CardDetailActivity extends BaseActivity {

    private static final String TAG = CardDetailActivity.class.getSimpleName();
    private static final String CARD_RESULT = "CARD_RESULT";

    private AnimatedToolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        setUi();
        initInstance();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_card_detail, FragmentCardDetail.newInstance()).commit();
        }
    }

    @Override
    protected void setUi() {
        mToolbar = findViewById(R.id.toolbar_detail);
    }

    @Override
    protected void initInstance() {
        setToolbar();
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        setTitle(R.string.detail_toolbar);
        mToolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

}
