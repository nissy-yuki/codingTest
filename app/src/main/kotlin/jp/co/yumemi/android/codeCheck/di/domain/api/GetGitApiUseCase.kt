package jp.co.yumemi.android.codeCheck.di.domain.api

import jp.co.yumemi.android.codeCheck.di.data.GitItem

interface GetGitApiUseCase {
    suspend fun getSearchApi(inputText: String, languageFormat: String): List<GitItem>
}