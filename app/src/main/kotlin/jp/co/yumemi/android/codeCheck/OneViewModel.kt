/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codeCheck

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import jp.co.yumemi.android.codeCheck.api.GithubRepository
import jp.co.yumemi.android.codeCheck.api.GithubRetrofitProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.parcelize.Parcelize
import org.json.JSONObject
import javax.inject.Inject

/**
 * TwoFragment で使う
 */
@HiltViewModel
class OneViewModel @Inject constructor() : ViewModel() {

    private var languageFormat: String = ""

    private var _searchResult: MutableLiveData<List<GitItem>> = MutableLiveData()
    val searchResult: LiveData<List<GitItem>> get() = _searchResult

    private val provider: GithubRetrofitProvider = GithubRetrofitProvider()
    private val repository: GithubRepository = GithubRepository(provider.retrofit)

    fun setLanguageFormat(text: String){
        languageFormat = text
    }

    // 検索結果
    fun searchResults(inputText: String) = runBlocking {

        val client = HttpClient(Android)

        return@runBlocking viewModelScope.async {
            val response: HttpResponse = client.get("https://api.github.com/search/repositories") {
                header("Accept", "application/vnd.github.v3+json")
                parameter("q", inputText)
            } ?: return@async emptyList()

            val jsonBody = JSONObject(response.receive<String>())
            val jsonItems = jsonBody.optJSONArray("items") ?: return@async emptyList()
            val items = mutableListOf<GitItem>()

            // jsonItemsの数分ループ
            for (i in 0 until jsonItems.length()) {
                val jsonItem = jsonItems.optJSONObject(i) ?: continue
                val name = jsonItem.optString("full_name")
                val ownerIconUrl = jsonItem.optJSONObject("owner")!!.optString("avatar_url")
                val language = jsonItem.optString("language")
                val stargazersCount = jsonItem.optLong("stargazers_count")
                val watchersCount = jsonItem.optLong("watchers_count")
                val forksCount = jsonItem.optLong("forks_conut")
                val openIssuesCount = jsonItem.optLong("open_issues_count")

                items.add(
                    GitItem(
                        name = name,
                        ownerIconUrl = ownerIconUrl,
                        language = (languageFormat.format(language)),
                        stargazersCount = stargazersCount,
                        watchersCount = watchersCount,
                        forksCount = forksCount,
                        openIssuesCount = openIssuesCount
                    )
                )
            }


            return@async items.toList()
        }.await()
    }
}


@Parcelize
data class GitItem(
    val name: String,
    val ownerIconUrl: String,
    val language: String,
    val stargazersCount: Long,
    val watchersCount: Long,
    val forksCount: Long,
    val openIssuesCount: Long,
): Parcelable