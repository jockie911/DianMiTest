package com.example.objLoader.present.view;

import com.example.objLoader.base.IBaseView;

/**
 * Created by yc on 2017/5/23.
 */

public interface IFrontSideView extends IBaseView {

    int getGender();

    boolean isFrontPic();

    void openAlbumChoosePic();

    void resultAlbumPicSuccess(String picPath);

    void resultAlbumPicError();
}
