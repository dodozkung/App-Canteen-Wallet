package dodoz.cs.rmutt.canteenwallet.Retrofit

import dodoz.cs.rmutt.canteenwallet.model.DefaultResponse
import dodoz.cs.rmutt.canteenwallet.model.LoginResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface Api {
    @POST("createuser")
    @FormUrlEncoded
    fun createuser(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("idcard") idcard: String,
        @Field("address") address: String,
        @Field("passconfirm") passconfirm: String,
        @Field("phone") phone: String,
    ):Call<DefaultResponse>


    @POST("userlogin")
    @FormUrlEncoded
    fun userLogin(@Field("username") username:String,
                 @Field("password") password:String):Call<LoginResponse>




}