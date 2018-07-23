package com.xcf.admin.couldclass.Entity.examroom;

import java.util.List;

public class Appques {
    private List<quesyhs> s;

    public List<quesyhs> getS() {
        return s;
    }

    public void setS(List<quesyhs> s) {
        this.s = s;
    }

    private List<quesyhs> d;

    public List<quesyhs> getD() {
        return d;
    }

    public void setD(List<quesyhs> d) {
        this.d = d;
    }

    public List<quesyhs> getP() {
        return p;
    }

    public void setP(List<quesyhs> p) {
        this.p = p;
    }

    private List<quesyhs> p;

    public class quesyhs {
        private String quesyhsname;
        private Long quesyhsid;
        private Long quesyhsAid;
        private String quesyhsAname;
        private Long quesyhsBid;

        public String getQuesyhsname() {
            return quesyhsname;
        }

        public void setQuesyhsname(String quesyhsname) {
            this.quesyhsname = quesyhsname;
        }

        public Long getQuesyhsid() {
            return quesyhsid;
        }

        public void setQuesyhsid(Long quesyhsid) {
            this.quesyhsid = quesyhsid;
        }

        public Long getQuesyhsAid() {
            return quesyhsAid;
        }

        public void setQuesyhsAid(Long quesyhsAid) {
            this.quesyhsAid = quesyhsAid;
        }

        public String getQuesyhsAname() {
            return quesyhsAname;
        }

        public void setQuesyhsAname(String quesyhsAname) {
            this.quesyhsAname = quesyhsAname;
        }

        public Long getQuesyhsBid() {
            return quesyhsBid;
        }

        public void setQuesyhsBid(Long quesyhsBid) {
            this.quesyhsBid = quesyhsBid;
        }

        public String getQuesyhsBname() {
            return quesyhsBname;
        }

        public void setQuesyhsBname(String quesyhsBname) {
            this.quesyhsBname = quesyhsBname;
        }

        public Long getQuesyhsCid() {
            return quesyhsCid;
        }

        public void setQuesyhsCid(Long quesyhsCid) {
            this.quesyhsCid = quesyhsCid;
        }

        public String getQuesyhsCname() {
            return quesyhsCname;
        }

        public void setQuesyhsCname(String quesyhsCname) {
            this.quesyhsCname = quesyhsCname;
        }

        public Long getQuesyhsDid() {
            return quesyhsDid;
        }

        public void setQuesyhsDid(Long quesyhsDid) {
            this.quesyhsDid = quesyhsDid;
        }

        public String getQuesyhsDname() {
            return quesyhsDname;
        }

        public void setQuesyhsDname(String quesyhsDname) {
            this.quesyhsDname = quesyhsDname;
        }

        private String quesyhsBname;
        private Long quesyhsCid;
        private String quesyhsCname;
        private Long quesyhsDid;
        private String quesyhsDname;
        private List quesyhsselected;

        public List getQuesyhsselected() {
            return quesyhsselected;
        }

        public void setQuesyhsselected(List quesyhsselected) {
            this.quesyhsselected = quesyhsselected;
        }

        public List getQuesyhstrue() {
            return quesyhstrue;
        }

        public void setQuesyhstrue(List quesyhstrue) {
            this.quesyhstrue = quesyhstrue;
        }

        private List quesyhstrue;
    }
}
