package kz.veter420.android_modern.di

import kz.veter420.android_modern.data.api.Api
import kz.veter420.android_modern.BuildConfig
import kz.veter420.android_modern.data.api.OpenApi
import kz.veter420.android_modern.data.local.PrefManager
import kz.veter420.android_modern.data.remote.WebClientBuilder
import kz.veter420.android_modern.data.remote.createWebService
import kz.veter420.android_modern.data.repository.PostRepositoryImpl
import kz.veter420.android_modern.data.repository.ProductRepositoryImpl
import kz.veter420.android_modern.domain.repository.PostRepository
import kz.veter420.android_modern.domain.repository.ProductRepository
import kz.veter420.android_modern.domain.use_case.GetPostsUseCase
import kz.veter420.android_modern.domain.use_case.GetProductByIdUseCase
import kz.veter420.android_modern.domain.use_case.GetProductsUseCase
import kz.veter420.android_modern.presentation.pages.post.PostViewModel
import kz.veter420.android_modern.presentation.pages.products.ProductViewModel
import kz.veter420.android_modern.presentation.pages.products.detail.ProductDetailViewModel
import kz.veter420.android_modern.presentation.pages.calc.CalcViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module


val appModule = module {

    factory { createWebService<OpenApi>(get(named("open")), BuildConfig.API_URL) }
    factory { createWebService<Api>(get(named("closed")), BuildConfig.API_URL) }

    factory(named("open")) { WebClientBuilder.createOpenOkHttpClient() }
    factory(named("closed")) { WebClientBuilder.createClosedOkHttpClient() }

    single { PrefManager(androidContext()) }

    factory<PostRepository> { PostRepositoryImpl(get()) }
    factory<ProductRepository> { ProductRepositoryImpl(get()) }

    factory { GetPostsUseCase(get()) }
    factory { GetProductsUseCase(get()) }
    factory { GetProductByIdUseCase(get()) }

    factory { PostViewModel(get()) }
    factory { ProductViewModel(get()) }
    factory { ProductDetailViewModel(get()) }
    factory { CalcViewModel() }
}
