package jp.co.yumemi.android.codeCheck.di.domain.api

import android.util.Log
import jp.co.yumemi.android.codeCheck.di.data.GitItem
import jp.co.yumemi.android.codeCheck.di.data.api.GithubRepository
import jp.co.yumemi.android.codeCheck.di.data.api.GithubRetrofitProvider
import java.lang.Exception
import javax.inject.Inject

class GetGitApiUseCaseImpl @Inject constructor(): GetGitApiUseCase{

    private val provider: GithubRetrofitProvider = GithubRetrofitProvider()
    private val repository: GithubRepository = GithubRepository(provider.retrofit)

    override suspend fun getSearchApi(inputText: String, languageFormat: String): List<GitItem>{
        try {
            val response = repository.searchRepository(inputText)
            if (response.isSuccessful) {
                return response.body()?.toGitItemList(languageFormat) ?: emptyList()
            } else {
                Log.d("Get api", "not success")
            }
        } catch (e: Exception) {
            Log.d("Get api", e.toString())
        }
        return emptyList()
    }
}