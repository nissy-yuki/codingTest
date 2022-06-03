package jp.co.yumemi.android.codeCheck.api

import android.os.Parcelable
import jp.co.yumemi.android.codeCheck.GitItem
import kotlinx.parcelize.Parcelize

data class GitResponse(
    val items: List<GitParse>
){
    fun toGitItemList(): List<GitItem>{
        return this.items.map { it.toGitItem() }
    }
}

data class OwnerData(
    val avatar_url: String?
)

data class GitParse(
    val full_name: String?,
    val owner: OwnerData?,
    val language: String?,
    val stargazers_count: Long?,
    val watchers_count: Long?,
    val forks_count: Long?,
    val open_issues_count: Long?,
){
    fun toGitItem(): GitItem{
        return GitItem(name = this.full_name ?: "",
            ownerIconUrl = this.owner?.avatar_url ?: "",
            language = this.language ?: "",
            stargazersCount = this.stargazers_count ?:0,
            watchersCount = this.watchers_count ?: 0,
            forksCount = this.forks_count ?: 0 ,
            openIssuesCount = this.open_issues_count ?: 0
        )
    }
}


