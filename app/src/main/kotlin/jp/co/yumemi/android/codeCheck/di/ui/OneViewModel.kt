/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codeCheck.di.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.android.codeCheck.di.data.GitItem
import jp.co.yumemi.android.codeCheck.di.domain.api.GetGitApiUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

    private val useGitApi = GetGitApiUseCase()

    fun setLanguageFormat(text: String) {
        languageFormat = text
    }

    // 入力されたTextでRepositoryを検索
    fun searchResults(inputText: String) = runBlocking {
        viewModelScope.launch {
            _searchResult.value = useGitApi.getSearchApi(inputText, languageFormat)
        }
    }
}

