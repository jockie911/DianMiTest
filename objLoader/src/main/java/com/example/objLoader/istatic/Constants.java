package com.example.objLoader.istatic;

public class Constants {

	/**	登录what	*/
	public static final int LOGIN_WHAT = 0;
	
	/**	注册what	*/
	public static final int REGISTER_WHAT = 1;
	
	/**	忘记密码what	*/
	public static final int FORGET_PWD_WHAT = 2;
	
	/**	修改密码what	*/
	public static final int CHANGE_PWD_WHAT = 3;
	
	/**	获取人体测量信息what	*/
	public static final int GET_MEASURE_INFO_WHAT = 5;
	
	/**	保存名称what	*/
	public static final int SAVE_NAME_WHAT = 6;
	
	/**	获取所有保存信息what	*/
	public static final int GET_ALL_MEASURE_INFO_WHAT = 7;
	
	/** 下载obj文件what */
	public static final int DOWNLOAD_OBJ_WHAT = 8;
	/** 删除我的记录what */
	public static final int DELETE_MY_LOG_WHAT = 9;
	
	public static final String MD5_KEY = "QW!!22*&90)";
	public static final String SharedPreferences_Config = "ObjLoaser";
	public static final String SERVER = "http://114.55.145.129/somatometry/";

	/**	登录接口	*/
	public static final String LOGIN = SERVER + "loginuser.php";
	
	/**	注册	*/
	public static final String REGISTER = SERVER + "registeruser.php";
	
	/**	忘记密码	*/
	public static final String FORGET_PWD = SERVER + "forgetpwd.php";
	
	/**	修改密码	*/
	public static final String CHANGE_PWD = SERVER + "changepwdsetup.php";
	
	/**	获取人体测量信息	*/
	public static final String GET_MEASURE_INFO = SERVER + "makedatainfo.php";
	
	/**	保存名称	*/
	public static final String SAVE_NAME = SERVER + "savename.php";

	/**	获取所有保存信息	*/
	public static final String GET_ALL_MEASURE_INFO = SERVER + "searchobj.php";
	
	/**	获取所有保存信息	*/
	public static final String DELETE_MY_LOG = SERVER + "deleteobj.php";

	/** 获取所有的测量记录 */
	public static final String GET_MEASURE_RECORD = SERVER + "searchobj.php";

}
