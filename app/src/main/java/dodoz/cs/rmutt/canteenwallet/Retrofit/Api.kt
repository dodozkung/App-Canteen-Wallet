package dodoz.cs.rmutt.canteenwallet.Retrofit

import dodoz.cs.rmutt.canteenwallet.model.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface Api {
    @POST("createuser")
    @FormUrlEncoded
    fun createuser(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("address") address: String,
        @Field("idcard") idcard: String,
        @Field("passconfirm") pw : String,
        @Field("phone") phone: String,
    ):Call<DefaultResponse>


    @POST("userlogin")
    @FormUrlEncoded
    fun userLogin(@Field("username") username:String,
                 @Field("password") password:String):Call<LoginResponse>


    @POST("getDataUser")
    @FormUrlEncoded
    fun getDataUser(@Field("wallet_id") wallet_id: String):Call<getData>

    @POST("SeachUser")
    @FormUrlEncoded
    fun SeachUser(
        @Field("wallet_id") wallet_id: Int
    ):Call<getSearch>

    @POST("Transfer")
    @FormUrlEncoded
    fun postTransferT(
        @Field("wallet_id") wallet_id: String,
        @Field("EndAccID") EndAccID : String,
        @Field("Amout") Amout: Float
    ):Call<Transfer>

    @POST("TransferQR")
    @FormUrlEncoded
    fun postTransferP(
        @Field("wallet_id") wallet_id: String,
        @Field("EndAccID") EndAccID : String,
        @Field("Amout") Amout: Float
    ):Call<TransferA>

    @POST ("Reportip")
    @FormUrlEncoded
    fun Report(@Field("wallet_id") wallet_id: String):Call<List<userRecy>>

    @POST ("Reportdw")
    @FormUrlEncoded
    fun Reportdw(@Field("wallet_id") EndAccID: String):Call<List<userRecy>>

    @GET("Report")
    fun fetchAllUsers():Call<List<userRecy>>

}