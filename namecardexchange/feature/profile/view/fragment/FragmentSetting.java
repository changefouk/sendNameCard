package com.mbox.administrator.namecardexchange.feature.profile.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.feature.collection.view.activity.CardDetailActivity;
import com.mbox.administrator.namecardexchange.feature.profile.view.activity.ProfileActivity;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentSetting extends Fragment implements View.OnClickListener {

    private Button btnReset;

    public FragmentSetting() {
        super();
    }

    public static FragmentSetting newInstance() {
        FragmentSetting fragment = new FragmentSetting();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_setting, container, false);
        setUi(rootView);
        initInstances(rootView);
        return rootView;
    }

    private void setUi(View rootView) {
        btnReset = rootView.findViewById(R.id.btn_setting_resetdata);
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btnReset.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_setting_resetdata:
                resetSharedPref();
        }
    }

    private void resetSharedPref() {
        SharedPreferences sp = getActivity().getSharedPreferences(((ProfileActivity) getActivity()).SHARED_VALUE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(((ProfileActivity) getActivity()).SHARED_ADDCARD, 0);
        editor.putInt(((ProfileActivity) getActivity()).SHARED_SUGGEST, 0);
        editor.commit();
    }

}
