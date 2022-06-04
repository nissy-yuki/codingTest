package jp.co.yumemi.android.codeCheck

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitItem(
    val name: String,
    val ownerIconUrl: String,
    val language: String,
    val stargazersCount: Long,
    val watchersCount: Long,
    val forksCount: Long,
    val openIssuesCount: Long,
) : Parcelable