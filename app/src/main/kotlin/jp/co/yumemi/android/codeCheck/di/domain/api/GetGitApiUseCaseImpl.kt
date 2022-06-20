package jp.co.yumemi.android.codeCheck.di.domain.api

import android.util.Log
import jp.co.yumemi.android.codeCheck.di.data.GitItem
import jp.co.yumemi.android.codeCheck.di.data.repository.GithubRepository
import java.lang.Exception
import javax.inject.Inject

class GetGitApiUseCaseImpl @Inject constructor(
    private val repository: GithubRepository
) : GetGitApiUseCase {

    override suspend fun getSearchApi(inputText: String): List<GitItem> {
        try {
            val response = repository.searchRepository(inputText)
            if (response.isSuccessful) {
                return response.body()?.toGitItemList() ?: emptyList()
            } else {
                Log.d("Get api", "not success")
            }
        } catch (e: Exception) {
            Log.d("Get api", e.toString())
        }
        return emptyList()
    }
}