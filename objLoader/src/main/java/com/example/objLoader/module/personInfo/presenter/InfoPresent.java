package com.example.objLoader.module.personInfo.presenter;

import com.example.objLoader.module.personInfo.AccountInfoActivity;
import com.example.objLoader.module.personInfo.imple.InfoModelImple;

/**
 * Created by yc on 2017/5/22.
 */

public class InfoPresent {

    private InfoModelImple<AccountInfoActivity> infoModelImple;

    public InfoPresent(){
        infoModelImple = new InfoModelImple();
    }

    public void logout(AccountInfoActivity activity){
        infoModelImple.logout(activity);
    }
}
