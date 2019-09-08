package io.github.pps5.kakaosampleapp.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import io.github.pps5.kakaosampleapp.data.converter.EscapedHtmlConverter
import io.github.pps5.kakaosampleapp.data.converter.ZonedDateTimeConverter
import io.github.pps5.kakaosampleapp.data.store.*
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://connpass.com/"

val dataStoreModule = module {

    single {
        ZonedDateTimeConverter()
    }

    single {
        Moshi.Builder()
            .add(get<ZonedDateTimeConverter>())
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    factory {
        TikXml.Builder()
            .exceptionOnUnreadXml(false)
            .addTypeConverter(String::class.java, EscapedHtmlConverter())
            .addTypeConverter(ZonedDateTime::class.java, get<ZonedDateTimeConverter>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(
                AnnotatedConverterFactory.Builder()
                    .add(Xml::class, TikXmlConverterFactory.create(get()))
                    .add(Json::class, MoshiConverterFactory.create(get()))
                    .build()
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(ConnpassService::class.java)
    }

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "database"
        ).build()
    }

    single {
        androidApplication()
            .getSharedPreferences("Preferences", Context.MODE_PRIVATE)
    }
}