package com.xcf.admin.couldclass.Entity.organization;

public class Organization {

    private Long Org_Id;

    private String Org_Name;

    private Long Pre_Level;

    private Integer Org_Count;

    private String All_Pre;

    public Long getOrg_Id() {
        return Org_Id;
    }

    public void setOrg_Id(Long org_Id) {
        Org_Id = org_Id;
    }

    public String getOrg_Name() {
        return Org_Name;
    }

    public void setOrg_Name(String org_Name) {
        Org_Name = org_Name;
    }

    public Long getPre_Level() {
        return Pre_Level;
    }

    public void setPre_Level(Long pre_Level) {
        Pre_Level = pre_Level;
    }

    public Integer getOrg_Count() {
        return Org_Count;
    }

    public void setOrg_Count(Integer org_Count) {
        Org_Count = org_Count;
    }

    public String getAll_Pre() {
        return All_Pre;
    }

    public void setAll_Pre(String all_Pre) {
        All_Pre = all_Pre;
    }

    @Override
    public String toString() {
        return Org_Name;
    }
}
