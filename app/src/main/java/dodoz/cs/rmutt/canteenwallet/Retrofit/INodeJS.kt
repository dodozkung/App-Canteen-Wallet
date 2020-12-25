package dodoz.cs.rmutt.canteenwallet.Retrofit

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface INodeJS {
    @POST("register")
    @FormUrlEncoded
    fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("idcard") idcard: String,
        @Field("address") address: String,
        @Field("passconfirm") passconfirm: String,
        @Field("phone") phone: String,
    ):Observable<String>


    @POST("login")
    @FormUrlEncoded
    fun login(@Field("username") username:String,
                 @Field("password") password:String):Observable<String>




}