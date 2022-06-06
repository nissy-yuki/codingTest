package jp.co.yumemi.android.codeCheck.di.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jp.co.yumemi.android.codeCheck.di.domain.api.GetGitApiUseCaseImpl
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
abstract class GithubRepositoryModule {
    @Binds
    abstract fun bindGithubRepository(Impl: GithubRepositoryImpl
    ): GithubRepository
}