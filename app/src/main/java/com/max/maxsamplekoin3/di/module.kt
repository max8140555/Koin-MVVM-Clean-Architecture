package com.max.maxsamplekoin3.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.max.maxsamplekoin3.model.service.inventory.InventoryService
import com.max.maxsamplekoin3.model.service.login.LoginService
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://max/"

val appCoreModule = module {
    single { createGson() }
    single { createOkHttpClient(TimeUnit.SECONDS.toMillis(60)) }
    single { provideLoginService(provideMaxRetrofit(BASE_URL, get(), get())) }
    single { provideInventoryService(provideMaxRetrofit(BASE_URL, get(), get())) }
}

fun createGson(): Gson {
    return GsonBuilder()
        .serializeNulls()
        .setLenient()
        .serializeSpecialFloatingPointValues()
        .enableComplexMapKeySerialization()
        .create()
}

private fun createOkHttpClient(
    timeoutMs: Long,
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectionSpecs(
            listOf(
                // allow TLSv1 and TLSv1.1 connections
                ConnectionSpec.COMPATIBLE_TLS,
                // allow http
                ConnectionSpec.CLEARTEXT
            )
        )
        .addLogInterceptor()
        .connectTimeout(timeoutMs, TimeUnit.SECONDS)
        .callTimeout(timeoutMs, TimeUnit.SECONDS)
        .readTimeout(timeoutMs, TimeUnit.SECONDS)
        .writeTimeout(timeoutMs, TimeUnit.SECONDS)
        .build()
}

private fun OkHttpClient.Builder.addLogInterceptor() = apply {
    if (BuildConfig.DEBUG) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        addInterceptor(interceptor)
    }
}

private fun provideMaxRetrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient,
    gson: Gson
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
}

private fun provideLoginService(retrofit: Retrofit): LoginService = retrofit.create()
private fun provideInventoryService(retrofit: Retrofit): InventoryService = retrofit.create()
