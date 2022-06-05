package jp.co.yumemi.android.codeCheck.di.data.repository

import jp.co.yumemi.android.codeCheck.di.data.api.GithubInterface
import jp.co.yumemi.android.codeCheck.di.domain.api.GitResponse
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

interface GithubRepository {
    suspend fun searchRepository(query: String): Response<GitResponse>
}