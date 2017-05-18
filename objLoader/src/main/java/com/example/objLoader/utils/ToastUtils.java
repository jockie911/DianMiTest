package com.example.objLoader.utils;

import com.example.objLoader.global.BaseApp;

public class ToastUtils {

	private static android.widget.Toast toast;

	public static void show(CharSequence msg) {

		if (toast == null) {
			toast = android.widget.Toast.makeText(BaseApp.getInstance(), msg,android.widget.Toast.LENGTH_LONG);
		} else {
			toast.setText(msg);
		}
		toast.setDuration(5000);
		toast.show();
	}

	public static void show(int stringId) {

		if (toast == null) {
			toast = android.widget.Toast.makeText(BaseApp.getInstance(), stringId,android.widget.Toast.LENGTH_LONG);
		} else {
			toast.setText(stringId);
		}
		toast.setDuration(5000);
		toast.show();
	}

}
