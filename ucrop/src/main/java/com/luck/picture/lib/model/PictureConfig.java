package com.luck.picture.lib.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.luck.picture.lib.ui.PictureAlbumDirectoryActivity;
import com.luck.picture.lib.ui.PictureImageGridActivity;
import com.yalantis.ucrop.R;
import com.yalantis.ucrop.entity.LocalMedia;
import com.yalantis.ucrop.util.Utils;

import java.util.List;


/**
 * author：luck
 * project：PictureSelector
 * package：com.luck.picture.util
 * email：893855882@qq.com
 * data：17/1/5
 */
public class PictureConfig {

    public static FunctionConfig config;
    public static PictureConfig pictureConfig;


    public static PictureConfig getPictureConfig() {
        if (pictureConfig == null) {
            pictureConfig = new PictureConfig();
        }

        return pictureConfig;
    }

    public PictureConfig() {
        super();
    }


    public OnSelectResultCallback resultCallback;

    public OnSelectResultCallback getResultCallback() {
        return resultCallback;
    }

    public static void init(FunctionConfig functionConfig) {
        config = functionConfig;
    }

    /**
     * 启动相册
     */
    public void openPhoto(Context mContext, OnSelectResultCallback resultCall) {
        if (Utils.isFastDoubleClick()) {
            return;
        }
        if (config == null) {
            config = new FunctionConfig();

        }
        // 这里仿ios微信相册启动模式
        Intent intent1 = new Intent(mContext, PictureAlbumDirectoryActivity.class);
        Intent intent2 = new Intent(mContext, PictureImageGridActivity.class);
        Intent[] intents = new Intent[2];
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intents[0] = intent1;
        intents[1] = intent2;
        intent1.putExtra(FunctionConfig.EXTRA_THIS_CONFIG, config);
        intent2.putExtra(FunctionConfig.EXTRA_THIS_CONFIG, config);
        mContext.startActivities(intents);
        ((Activity) mContext).overridePendingTransition(R.anim.ucrop_slide_bottom_in, 0);
        // 绑定图片接口回调函数事件
        resultCallback = resultCall;
    }

    /**
     * 启动相册
     */
    public void openPhoto(Context mContext, OnSelectResultCallback resultCall,int num) {
        if (Utils.isFastDoubleClick()) {
            return;
        }
        if (config == null) {
            config = new FunctionConfig();
            config.setType(LocalMediaLoader.TYPE_IMAGE);
            config.setCopyMode(FunctionConfig.COPY_MODEL_DEFAULT);
            config.setCompress(true);
            config.setEnablePixelCompress(true);
            config.setEnableQualityCompress(false);
            if (9 - num > 0) {
                config.setMaxSelectNum(9 - num);
            } else {
                config.setMaxSelectNum(0);
            }

            config.setSelectMode(FunctionConfig.MODE_MULTIPLE);
            config.setShowCamera(true);
            config.setEnablePreview(false);
            config.setEnableCrop(false);
            config.setPreviewVideo(false);
            config.setRecordVideoDefinition(FunctionConfig.HIGH);
            config.setCheckNumMode(true);
            config.setCompressQuality(80);
            config.setImageSpanCount(4);
//            config.setSelectMedia(selectMedia);
            config.setCompressFlag(2);
//            config.setThemeStyle(R.color.white);
//            config.setCompleteColor( R.color.black);
//            config.setPreviewBottomBgColor(R.color.white);
//            config.setBottomBgColor(R.color.white);
        }
        Intent intent2 = new Intent(mContext, PictureImageGridActivity.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent2.putExtra(FunctionConfig.EXTRA_THIS_CONFIG, config);
        intent2.putExtra("is_save",false);
        mContext.startActivity(intent2);
        ((Activity) mContext).overridePendingTransition(R.anim.ucrop_slide_bottom_in, 0);
        // 绑定图片接口回调函数事件
        resultCallback = resultCall;
    }


    /**
     * 处理结果
     */
    public interface OnSelectResultCallback {
        /**
         * 处理成功
         *
         * @param resultList
         */
        public void onSelectSuccess(List<LocalMedia> resultList);

    }
}
