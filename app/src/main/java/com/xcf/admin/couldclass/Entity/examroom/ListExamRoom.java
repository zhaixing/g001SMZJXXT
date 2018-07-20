package com.xcf.admin.couldclass.Entity.examroom;

import java.util.List;

public class ListExamRoom {
    public List<Examlist> getList() {
        return list;
    }

    public void setList(List<Examlist> list) {
        this.list = list;
    }

    private List<Examlist> list;
    private String bool;

    public String getBool() {
        return bool;
    }

    public void setBool(String bool) {
        this.bool = bool;
    }

    public class Examlist {
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private String type;
        private String examroomname;
        private String examroomid;
        private String paperid;

        public String getExamroomname() {
            return examroomname;
        }

        public void setExamroomname(String examroomname) {
            this.examroomname = examroomname;
        }

        public String getExamroomid() {
            return examroomid;
        }

        public void setExamroomid(String examroomid) {
            this.examroomid = examroomid;
        }

        public String getPaperid() {
            return paperid;
        }

        public void setPaperid(String paperid) {
            this.paperid = paperid;
        }

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getComplete() {
            return complete;
        }

        public void setComplete(String complete) {
            this.complete = complete;
        }

        private String startdate;
        private String enddate;
        private String starttime;
        private String endtime;
        private String complete;

    }
}
