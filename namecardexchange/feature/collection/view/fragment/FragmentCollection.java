package com.mbox.administrator.namecardexchange.feature.collection.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.feature.collection.viewmodel.FragmentCollectionViewmodel;
import com.mbox.administrator.namecardexchange.util.ViewPagerAdapter;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentCollection extends Fragment {

    private static final String TAG = "FragmentCollection";

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String[] title;

    private FragmentCollectionViewmodel viewmodel;

    public FragmentCollection() {
        super();
    }

    public static FragmentCollection newInstance() {
        FragmentCollection fragment = new FragmentCollection();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_collection, container, false);
        viewmodel = ViewModelProviders.of(getActivity()).get(FragmentCollectionViewmodel.class);
        setUi(rootView);
        initInstances(rootView);
        return rootView;
    }

    private void setUi(View rootView) {
        tabLayout = rootView.findViewById(R.id.collection_Tablayout);
        viewPager = rootView.findViewById(R.id.collection_ViewPager);
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        setupViewpager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewpager(ViewPager viewPager) {
        title = new String[]{getString(R.string.myCard),getString(R.string.friendCard)};
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(),title);
        adapter.addFragment(FragmentMyCard.newInstance());
        adapter.addFragment(FragmentFriendcard.newInstance());
        viewPager.setAdapter(adapter);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}