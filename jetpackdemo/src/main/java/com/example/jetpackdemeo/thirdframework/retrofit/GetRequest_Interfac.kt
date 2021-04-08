package com.example.jetpackdemeo.thirdframework.retrofit

import com.example.jetpackdemeo.thirdframework.retrofit.data.Translation
import io.reactivex.Observable
import retrofit2.http.GET

interface GetRequestInterface {
    @GET("api/fanyi?q=你好&from=zh&to=en")
    fun translate(): Observable<Translation>
}