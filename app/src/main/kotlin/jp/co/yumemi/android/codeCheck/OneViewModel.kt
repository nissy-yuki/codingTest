/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codeCheck

import android.os.Parcelable
import android.util.Log
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
import jp.co.yumemi.android.codeCheck.api.GitResponse
import jp.co.yumemi.android.codeCheck.api.GithubRepository
import jp.co.yumemi.android.codeCheck.api.GithubRetrofitProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.parcelize.Parcelize
import org.json.JSONObject
import java.lang.Exception
import javax.inject.Inject

/**
 * TwoFragment で使う
 */
@HiltViewModel
class OneViewModel @Inject constructor() : ViewModel() {

    //
    private var languageFormat: String = ""

    private var _searchResult: MutableLiveData<List<GitItem>> = MutableLiveData()
    val searchResult: LiveData<List<GitItem>> get() = _searchResult

    private val provider: GithubRetrofitProvider = GithubRetrofitProvider()
    private val repository: GithubRepository = GithubRepository(provider.retrofit)

    fun setLanguageFormat(text: String) {
        languageFormat = text
    }

    // 入力されたTextでRepositoryを検索
    fun searchResults(inputText: String) = runBlocking {
        viewModelScope.launch {
            try {
                val response = repository.searchRepository(inputText)
                if (response.isSuccessful) {
                    _searchResult.value = response.body()?.toGitItemList(languageFormat) ?: emptyList()
                } else {
                    Log.d("Get api", "not success")
                }
            } catch (e: Exception) {
                Log.d("Get api", e.toString())
            }
        }
    }
}

