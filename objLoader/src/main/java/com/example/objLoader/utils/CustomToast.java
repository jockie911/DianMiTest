package com.example.objLoader.utils;

import android.content.Context;

import android.os.Handler;

import android.widget.Toast;

/** 设计原理：在Toast显示消失之前，再次调用Toast.show()进行接力。 */

public class CustomToast {

	/** 用于测试 */

	private int showCount = 1;

	private Toast toast = null;

	private Context context;

	private Handler handler = null;

	private Runnable toastThread = new Runnable() {

		public void run() {

			// 递增的count明显地表明是不断运行新的Toast.show()的结果。

//			toast.setText(String.valueOf(showCount++) + "CustomToast");

			toast.show();

			// 3.3秒后再度重启，设为4s的话将会看到Toast是断断续续地显示着的。

			handler.postDelayed(toastThread, 3300);

		}

	};

	public CustomToast(Context context) {

		this.context = context;

		handler = new Handler(this.context.getMainLooper());

		toast = Toast.makeText(this.context, "", Toast.LENGTH_LONG);

	}

	public void setText(String text) {

		toast.setText(text);

	}

	public void showToast(final long length) {

		handler.post(toastThread);

		// SodinoOut.out("Handler post at:" + System.currentTimeMillis());

		Thread timeThread = new Thread() {

			public void run() {

//				SodinoOut.out("TimeThread start at:"+ System.currentTimeMillis());

				try {

					Thread.sleep(length);

				} catch (InterruptedException e) {

					e.printStackTrace();

				}

				CustomToast.this.stopToast();

//				SodinoOut.out("Toast canceled at:" + System.currentTimeMillis());

			}

		};

		timeThread.start();

	}

	public void stopToast() {

		// 删除Handler队列中的仍处理等待的消息元素删除

		handler.removeCallbacks(toastThread);

		// 撤掉仍在显示的Toast

		toast.cancel();

	}

}