package jp.co.yumemi.android.codeCheck.api

import retrofit2.Response
import retrofit2.Retrofit

class GithubRepository(private val retrofit: Retrofit) {

    suspend fun searchRepository(query: String) : Response<GitResponse> {
        val service = retrofit.create(GithubInterface::class.java)
        return service.getSearchRepositories(query)
    }

}