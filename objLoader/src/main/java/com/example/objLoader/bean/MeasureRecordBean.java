package com.example.objLoader.bean;

import java.util.List;

/**
 * Created by yc on 2017/5/8.
 */

public class MeasureRecordBean extends BaseRequestBean{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String id;
        private String objname;
        private String objurl;
        private String name;
        private String threeshowurl;
        private List<InfoBean> info;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getObjname() {
            return objname;
        }

        public void setObjname(String objname) {
            this.objname = objname;
        }

        public String getObjurl() {
            return objurl;
        }

        public void setObjurl(String objurl) {
            this.objurl = objurl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThreeshowurl() {
            return threeshowurl;
        }

        public void setThreeshowurl(String threeshowurl) {
            this.threeshowurl = threeshowurl;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {

            private String name;
            private String value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
