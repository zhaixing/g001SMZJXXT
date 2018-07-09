package com.xcf.admin.couldclass.Entity.ques;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultTemp implements Serializable {

    private Long epuid = 0l;


    private List<String> resultarray = new ArrayList<String>();

    public Long getEpuid() {
        return epuid;
    }

    public void setEpuid(Long epuid) {
        this.epuid = epuid;
    }

    public List<String> getResultarray() {
        return resultarray;
    }

    public void setResultarray(List<String> resultarray) {
        this.resultarray = resultarray;
    }
}
