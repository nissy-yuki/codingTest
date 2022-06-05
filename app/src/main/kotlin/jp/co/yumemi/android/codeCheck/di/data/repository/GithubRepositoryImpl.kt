package jp.co.yumemi.android.codeCheck.di.data.repository

import jp.co.yumemi.android.codeCheck.di.data.api.GithubInterface
import jp.co.yumemi.android.codeCheck.di.domain.api.GitResponse
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
): GithubRepository {

    override suspend fun searchRepository(query: String): Response<GitResponse> {
        val service = retrofit.create(GithubInterface::class.java)
        return service.getSearchRepositories(query)
    }

}