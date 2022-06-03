package jp.co.yumemi.android.codeCheck.api

import jp.co.yumemi.android.codeCheck.GitItem
import retrofit2.Response
import retrofit2.Retrofit

class GithubRepository(private val retrofit: Retrofit) {

    fun searchRepository(query: String) : Response<GitItem> {
        val service = retrofit.create(GithubInterface::class.java)
        return service.getSearchRepositories(query).execute()
    }

}