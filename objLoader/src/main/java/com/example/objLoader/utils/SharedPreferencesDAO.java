package com.example.objLoader.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
/**
 * SharedPreferences操作类
 * 轻量级
 * @author 
 *
 */
@SuppressLint("CommitPrefEdits")
public class SharedPreferencesDAO {

	private SharedPreferences sharedPreference;
	private SharedPreferences.Editor editor;
	
	private static SharedPreferencesDAO single=null;
	
	public static SharedPreferencesDAO getInstance(Context context){
		if(single==null){
			single=new SharedPreferencesDAO(context);
		}
		return single;
	}
	public SharedPreferencesDAO(Context context) {
		// TODO Auto-generated constructor stub
		sharedPreference=context.getSharedPreferences(Constants.SharedPreferences_Config, Context.MODE_PRIVATE);
		editor=sharedPreference.edit();
	}
	
	/**
	 * 判断SharedPreferences中是否已存在key
	 * @param keyName
	 * @return
	 */
	public boolean contains(String keyName){
		
		if(sharedPreference.contains(keyName)){
			
			return true;
		}else{
			return false;
		}
		
		
	}
	
	
	
	
	/**
	 * String
	 * Set数据
	 * @param name
	 * @param value
	 */
	public void putString(String name,String value){
		editor.putString(name, value).commit();
	}
	
	/**
	 * String
	 * Get数据
	 * 默认:""
	 * @param name
	 * @return
	 */
	public String getString(String name){
		return sharedPreference.getString(name, "");
	}
	
	/**
	 * boolean
	 * Set数据
	 * @param name
	 * @param value
	 */
	public void putBoolean(String name,boolean value){
		editor.putBoolean(name, value).commit();
	}
	
	/**
	 * boolean
	 * Get数据
	 * @param name
	 * @return
	 */
	public boolean getBoolean(String name){
		return sharedPreference.getBoolean(name, false);
	}
	
	/**
	 * float
	 * Set数据
	 * @param name
	 * @param value
	 */
	public void putFloat(String name,float value){
		editor.putFloat(name, value).commit();
	}
	
	/**
	 * float
	 * Get数据
	 * @param name
	 * @return
	 */
	public float getFloat(String name){
		return sharedPreference.getFloat(name, 0.0f);
	}
	
	/**
	 * int
	 * Set值
	 * @param name
	 * @param value
	 */
	public void putInt(String name,int value){
		editor.putInt(name, value).commit();
	}
	
	/**
	 * int
	 * Get值
	 * @param name
	 * @return
	 */
	public int getInt(String name){
		return sharedPreference.getInt(name, 0);
	}
	
	/**
	 * long
	 * Set
	 * @param name
	 * @param value
	 */
	public void putLong(String name,long value){
		editor.putLong(name, value).commit();
	}
	
	/**
	 * long
	 * Get
	 * @param name
	 * @return
	 */
	public long getLong(String name){
		return sharedPreference.getLong(name, 0L);
	}
	
	
	/**
	 * Object
	 * Set
	 * @param name
	 * @param obj
	 */
//	public void putObject(String name,Object obj){
//		try{
//			ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
//			ObjectOutputStream oos = new ObjectOutputStream(baos);
//			oos.writeObject(obj);
//			String obj_Base64 = new String(Base64.encodeBase64(baos  
//	                .toByteArray())); 
//			editor.putString(name, obj_Base64).commit();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	/**Object
	 * Get
	 * @param name
	 * @return
	 */
//	public Object getObject(String name){
//		
//		Object obj=null;
//		try{
//			String data=sharedPreference.getString(name, " ");
//			if((null==data ||" ".equals(data))){
//				return null;
//			}
//			byte[] base64 = Base64.decodeBase64(data.getBytes());
//			ByteArrayInputStream bais = new ByteArrayInputStream(base64);  
//			ObjectInputStream bis = new ObjectInputStream(bais);
//			obj=bis.readObject(); 
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return obj;
//	}
	
	public void onDestory(){
		sharedPreference.edit().clear().commit();
	}
}
