package com.example.objLoader.bean;

import java.io.Serializable;
import java.util.List;

public class MeasureInfo extends BaseRequestBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Data data;

	public void setData(Data data) {
		this.data = data;
	}

	public Data getData() {
		return this.data;
	}

	public class Data implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private List<Info> info;

		private String objname;

		private String objurl;
		
		private String threeshowurl;
		
		public void setThreeshowurl(String threeshowurl) {
			this.threeshowurl = threeshowurl;
		}
		
		public String getThreeshowurl() {
			return threeshowurl;
		}

		public void setInfo(List<Info> info) {
			this.info = info;
		}

		public List<Info> getInfo() {
			return this.info;
		}

		public void setObjname(String objname) {
			this.objname = objname;
		}

		public String getObjname() {
			return this.objname;
		}

		public void setObjurl(String objurl) {
			this.objurl = objurl;
		}

		public String getObjurl() {
			return this.objurl;
		}

	}

	public class Info implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String name;

		private String value;

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

	}
}
