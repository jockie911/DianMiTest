package com.example.objLoader.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//import org.apache.http.protocol.HTTP;


import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;


@SuppressWarnings("deprecation")
public class Utils {
	
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}
	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static String getTime() {
		Long time = new Date().getTime();
		time = time/1000;
		return time+"";
	}
	
	/**
     * 将本地图片设置到ImageView图片不模糊
     */
	public static String setImg(String imagePath,ImageView imageView) {

		if(imagePath.equals("") || imagePath.length() <= 0){
			return "";
		}
		
		// Get the dimensions of the View	视图的尺寸
    	
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();
     
        // Get the dimensions of the bitmap	位图的尺寸
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
     
        // Determine how much to scale down the image	确定多少缩减图像
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);
     
        // Decode the image file into a Bitmap sized to fill the View	解码图像文件到一个位图的大小来填充视图
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
     
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmOptions);
        imageView.setImageBitmap(bitmap);
        return imagePath;
    }
	
	
	/**
     * 创建一条图片地址URI,用于保存拍照后的照片
     *
     * @param context
     * @return 图片的uri
     */
	public static Uri createImagePathUri(Context context) {
        Uri imageFilePath = null;
        try {

            String status = Environment.getExternalStorageState();
            SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
            long time = System.currentTimeMillis();
            String imageName = timeFormatter.format(new Date(time));
            // ContentValues是我们希望这条记录被创建时包含的数据信息
            ContentValues values = new ContentValues(3);
            values.put(MediaStore.Images.Media.DISPLAY_NAME, imageName);
            values.put(MediaStore.Images.Media.DATE_TAKEN, time);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/*");
            if (status.equals(Environment.MEDIA_MOUNTED)) {
                // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
                imageFilePath = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        values);
            } else {
                imageFilePath = context.getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                        values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageFilePath;
    }
	

	/**
	 * 获取 MD5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String MD5(String str) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes(/*HTTP.UTF_8*/"UTF-8"));

			byte[] byteArray = messageDigest.digest();
			StringBuffer md5StrBuff = new StringBuffer();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
					md5StrBuff.append("0").append(
							Integer.toHexString(0xFF & byteArray[i]));
				else
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
			return md5StrBuff.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
