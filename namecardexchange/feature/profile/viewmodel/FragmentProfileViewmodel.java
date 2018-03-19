package com.mbox.administrator.namecardexchange.feature.profile.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.mbox.administrator.namecardexchange.MyApplication;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.feature.collection.viewmodel.FragmentCardDetailViewmodel;
import com.mbox.administrator.namecardexchange.feature.profile.model.ProfileMenuModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 11/1/2561.
 */

public class FragmentProfileViewmodel extends ViewModel {

    public static final int PROFILE_DATA = 1;
    public static final int PROFILE_SETTING = 2;
    public static final int PROFILE_SIGNOUT = 3;

    private static final String TAG = FragmentCardDetailViewmodel.class.getSimpleName();
    public MutableLiveData<List<ProfileMenuModel>> profleMenu;

    public LiveData<List<ProfileMenuModel>> getMenuList() {
        if (profleMenu == null) {
            profleMenu = new MutableLiveData<>();
            setvalueModel();
        }
        return profleMenu;
    }

    private void setvalueModel() {
        try {
            List<ProfileMenuModel> modelList = new ArrayList<>();
            modelList.add(new ProfileMenuModel(PROFILE_DATA,
                    MyApplication.getContext().getString(R.string.data_manager),
                    "http://192.168.0.3/card/img/ic_manage_data.png"));
            modelList.add(new ProfileMenuModel(PROFILE_SETTING,
                    MyApplication.getContext().getString(R.string.setting_profile),
                    "http://192.168.0.3/card/img/ic_settings.png"));
            modelList.add(new ProfileMenuModel(PROFILE_SIGNOUT,
                    MyApplication.getContext().getString(R.string.signout_user),
                    "http://192.168.0.3/card/img/ic_logout.png"));
            profleMenu.setValue(modelList);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "on cleared called");
    }
}
