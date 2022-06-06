package jp.co.yumemi.android.codeCheck.di.domain.api

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class ApiUseCaseModule {
    @Binds
    abstract fun bindGetGitApiUseCase(
        Impl: GetGitApiUseCaseImpl
    ): GetGitApiUseCase
}