package jp.co.yumemi.android.codeCheck.di.data.api

import jp.co.yumemi.android.codeCheck.di.data.GitResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubInterface {
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/search/repositories")
    suspend fun getSearchRepositories(@Query("q") query: String): Response<GitResponse>

}