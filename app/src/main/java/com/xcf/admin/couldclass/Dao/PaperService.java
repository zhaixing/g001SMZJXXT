package com.xcf.admin.couldclass.Dao;

import com.xcf.admin.couldclass.Entity.result.ResultPaper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Admin on 2018-04-04.
 */

public interface PaperService {

    @GET("/app/getrandpaper.do")
    Call<ResultPaper> GetRamd();

    @GET("/app/finishques.do")
    Call<String> FinishQues(@Query("qid") String qid, @Query("result") int result);

    @GET("/app/finishpaper.do")
    Call<String> FinishPaper(@Query("totalgrade") String totalgrade);

}
