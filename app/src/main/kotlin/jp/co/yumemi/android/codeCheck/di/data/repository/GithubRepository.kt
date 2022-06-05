package jp.co.yumemi.android.codeCheck.di.data.repository

import jp.co.yumemi.android.codeCheck.di.data.GitResponse
import retrofit2.Response

interface GithubRepository {
    suspend fun searchRepository(query: String): Response<GitResponse>
}