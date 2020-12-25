package dodoz.cs.rmutt.canteenwallet.Retrofit

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface INodeJS {
    @POST("register")
    @FormUrlEncoded
    fun register(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("address") address: String,
        @Field("phone") phone: String,
        @Field("balance") balance: String,
        @Field("passcf") passcf: String
    ):Observable<String>


    @POST("login")
    @FormUrlEncoded
    fun login(@Field("username") username:String,
                 @Field("password") password:String):Observable<String>




}