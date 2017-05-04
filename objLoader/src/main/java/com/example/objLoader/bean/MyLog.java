package com.example.objLoader.bean;

import java.io.Serializable;
import java.util.List;

public class MyLog extends BaseRequestBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Data> data;

	public void setData(List<Data> data) {
		this.data = data;
	}

	public List<Data> getData() {
		return this.data;
	}

	public class Data implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String id;

		private List<Info> info;

		private String objname;

		private String objurl;

		private String name;

		private String threeshowurl;
		
		private boolean isSelected = false;
		
	    public void setIsSelected(Boolean isSelected) {
			this.isSelected = isSelected;
		}
	    
	    public boolean isSelected(){
	    	return isSelected;
	    }

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

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

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		@Override
		public String toString() {
			return "Data [id=" + id + ", info=" + info + ", objname=" + objname
					+ ", objurl=" + objurl + ", name=" + name
					+ ", threeshowurl=" + threeshowurl + ", isSelected="
					+ isSelected + "]";
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

		@Override
		public String toString() {
			return "Info [name=" + name + ", value=" + value + "]";
		}
		
		
		

	}

}
