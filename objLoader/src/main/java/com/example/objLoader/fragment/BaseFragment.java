package com.example.objLoader.fragment;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

@SuppressLint("NewApi") public abstract class BaseFragment extends Fragment implements OnClickListener {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	
	protected abstract void initView();

	protected abstract void initData();

	protected abstract void setEvent();
	
	
	
	
	
	/**
	 * 将文件流转换成byte
	 * 
	 * @param
	 * @param
	 * @throws Exception
	 */

	public byte[] SetImageToByteArray(String fileName) {
		byte[] image = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			int streamLength = (int) fs.available();
			image = new byte[streamLength];
			fs.read(image, 0, streamLength);
			fs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

	/**
	 * 将字节流转换成文件
	 * 
	 * @param
	 * @param data
	 * @throws Exception
	 */
	public static File saveFile(byte[] data) throws Exception {
		File file = null;
		if (data != null) {
			String filepath = Environment.getExternalStorageDirectory()
					+ "/imagesss" + ".jpg";
			file = new File(filepath);
			if (file.exists()) {
				file.delete();
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data, 0, data.length);
			fos.flush();
			fos.close();

		}
		return file;
	}

	/**
	 * 图片按比例大小压缩 根据sd卡获得
	 * 
	 * @param srcPath
	 * @return
	 */
	public Bitmap getimage(String srcPath) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		Bitmap bitMap = BitmapFactory.decodeFile(srcPath, opts);
		opts.inJustDecodeBounds = false;
		int w = opts.outWidth;
		int h = opts.outHeight;
		int be = 1;
		if (w > h && w > 800) {
			be = (int) (w / 800);
		} else if (w < h && h > 600) {
			be = (int) (h / 600);
		}
		if (be < 0) {
			be = 1;
		}
		opts.inSampleSize = be;
		bitMap = BitmapFactory.decodeFile(srcPath, opts);
		int width = bitMap.getWidth();
		int height = bitMap.getHeight();
		if (width <= 800 && height <= 600) {
			return compressImage(bitMap);
		}
		int newWidth = 800;
		int newHeight = 600;
		float scale = 0;
		if (width > height && width > newWidth) {
			scale = ((float) newWidth) / width;
		} else if (width < height && height > newHeight) {
			scale = ((float) newHeight) / height;
		}
		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale);
		bitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix, true);

		return compressImage(bitMap);
	}

	/**
	 * 压缩质量
	 * 
	 * @param image
	 * @return
	 */
	public Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		int options = 100;
		while (baos.toByteArray().length / 1024 > 100) {
			baos.reset();
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);
			options -= 5;
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
		return bitmap;
	}

	@Override
	public void onResume() {
		super.onResume();
	}
	@Override
	public void onPause() {
		super.onPause();
	}
	@Override
	public void onStop() {
		super.onStop();
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	@Override
	public void onDetach() {
		super.onDetach();
	}
}
