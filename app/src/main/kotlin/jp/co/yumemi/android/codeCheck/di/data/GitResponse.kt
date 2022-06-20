package jp.co.yumemi.android.codeCheck.di.data

data class GitResponse(
    val items: List<GitParse>
) {
    // 表示用のList<GitItem>へ変換
    fun toGitItemList(): List<GitItem> =
        this.items.map { it.toGitItem() }
}

data class OwnerData(
    val avatar_url: String?
)

// Jsonを受け取るためのクラス
data class GitParse(
    val full_name: String?,
    val owner: OwnerData?,
    val language: String?,
    val stargazers_count: Long?,
    val watchers_count: Long?,
    val forks_count: Long?,
    val open_issues_count: Long?,
) {
    fun toGitItem(): GitItem {
        return GitItem(
            name = this.full_name ?: "none",
            ownerIconUrl = this.owner?.avatar_url ?: "none",
            language = this.language ?: "none",
            stargazersCount = this.stargazers_count ?: 0,
            watchersCount = this.watchers_count ?: 0,
            forksCount = this.forks_count ?: 0,
            openIssuesCount = this.open_issues_count ?: 0
        )
    }
}


