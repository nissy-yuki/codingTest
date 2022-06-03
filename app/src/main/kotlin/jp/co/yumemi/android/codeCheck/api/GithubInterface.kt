package jp.co.yumemi.android.codeCheck.api

import jp.co.yumemi.android.codeCheck.GitItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubInterface {

    @GET("/search/repositories")
    fun getSearchRepositories(@Query("q") query: String) : Call<GitItem>

}