package com.mbox.administrator.namecardexchange.feature.profile.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mbox.administrator.namecardexchange.feature.profile.adapter.ProfileMenuAdapter;
import com.mbox.administrator.namecardexchange.feature.profile.view.activity.ProfileActivity;
import com.mbox.administrator.namecardexchange.feature.profile.viewmodel.FragmentProfileViewmodel;
import com.mbox.administrator.namecardexchange.feature.profile.viewmodel.ProfileActivityViewmodel;
import com.mbox.administrator.namecardexchange.feature.ui.view.LoginActivity;
import com.mbox.administrator.namecardexchange.feature.ui.view.MainActivity;
import com.mbox.administrator.namecardexchange.R;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentProfile extends Fragment {

    private FragmentProfileViewmodel viewmodel;

    private FirebaseUser mFirebaseuser;
    private TextView textViewUsername, textViewUserEmail;
    private ImageView proFileImage;
    private RecyclerView recyclerView;
    private ProfileMenuAdapter adapter;

    public FragmentProfile() {
        super();
    }

    public static FragmentProfile newInstance() {
        FragmentProfile fragment = new FragmentProfile();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        setUi(rootView);
        initInstances(rootView);
        return rootView;
    }

    private void setUi(View rootView) {
        textViewUsername = rootView.findViewById(R.id.txt_username);
        textViewUserEmail = rootView.findViewById(R.id.txt_useremail);
        proFileImage = rootView.findViewById(R.id.profile_image);

        recyclerView = rootView.findViewById(R.id.recycler_profile_menu);
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        viewmodel = ViewModelProviders.of(getActivity()).get(FragmentProfileViewmodel.class);
        mFirebaseuser = ((MainActivity) getActivity()).getmFirebaseUser();
        if (mFirebaseuser != null) {
            setUserProfile();
        }
//        myDataButton.setOnClickListener(view -> {
//            UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
//                    .setDisplayName("Anonymous")
//                    .setPhotoUri(Uri.parse("https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg"))
//                    .build();
//
//            mFirebaseuser.updateProfile(profileUpdate)
//                    .addOnCompleteListener(task -> {
//                        if (task.isSuccessful()) {
//                            Log.d("FLUKE", "User profile updated.");
//                        }
//                    });
//
//        });

        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutmanager);

        viewmodel.getMenuList().observe(this, profileModels -> {

            adapter = new ProfileMenuAdapter(getContext(), profileModels, (view, position) -> {
                switch (profileModels.get(position).getMenuID()){
                    case 1:
                        /** DATA MANAGER */
                        Intent intentDataManager = new Intent(getActivity(), ProfileActivity.class);
                        intentDataManager.putExtra(ProfileActivityViewmodel.FRAGMENT_KEY, ProfileActivityViewmodel.DATA_MANAGER);
                        getActivity().startActivity(intentDataManager);
                        overridePendingTransitionEnter();
                        break;
                    case 2:
                        /** SETTING */
                        Intent intentSetting = new Intent(getActivity(), ProfileActivity.class);
                        intentSetting.putExtra(ProfileActivityViewmodel.FRAGMENT_KEY, ProfileActivityViewmodel.SETTING);
                        getActivity().startActivity(intentSetting);
                        overridePendingTransitionEnter();
                        break;
                    case 3:
                        signout();
                        break;
                }
            });
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        });

    }

    private void signIn() {
        Intent i = new Intent(getContext(), LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(i);
    }

    private void signout() {
        FirebaseAuth.getInstance().signOut();
        if (LoginManager.getInstance().getLoginBehavior() != null) {
            LoginManager.getInstance().logOut();
        }
        Intent i = new Intent(getContext(), LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(i);
        overridePendingTransitionExit();
    }

    private void setUserProfile() {
        textViewUsername.setText(mFirebaseuser.getDisplayName());
        textViewUserEmail.setText(mFirebaseuser.getEmail());
        Glide.with(getContext())
                .load(mFirebaseuser.getPhotoUrl())
                .into(proFileImage);
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


    protected void overridePendingTransitionEnter() {
        getActivity().overridePendingTransition(R.anim.anim_slide_from_right, R.anim.anim_slide_to_left);
    }
    protected void overridePendingTransitionExit() {
        getActivity().overridePendingTransition(R.anim.anim_slide_from_left, R.anim.anim_slide_to_right);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }
}
