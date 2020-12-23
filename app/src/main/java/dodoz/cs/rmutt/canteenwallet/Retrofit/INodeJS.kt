package dodoz.cs.rmutt.canteenwallet.Retrofit

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*


interface INodeJS {
    @POST("register")
    @FormUrlEncoded
    fun register(@Field("email") email:String,
                 @Field("name") name:String,
                 @Field("password") password:String):Observable<String>

    @POST("login")
    @FormUrlEncoded
    fun login(@Field("email") email:String,
                 @Field("password") password:String):Observable<String>
}