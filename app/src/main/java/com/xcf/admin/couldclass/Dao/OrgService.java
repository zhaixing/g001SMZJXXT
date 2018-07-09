package com.xcf.admin.couldclass.Dao;

import com.xcf.admin.couldclass.Entity.organization.Organization;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrgService {
    @GET("/app/org/alllist.do")
    Call<List<Organization>> GetOrgAll(@Query("orgid") String orgid);
}
