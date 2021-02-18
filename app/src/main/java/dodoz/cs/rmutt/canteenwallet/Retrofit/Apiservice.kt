package dodoz.cs.rmutt.canteenwallet.Retrofit

import dodoz.cs.rmutt.canteenwallet.model.userRecy
import retrofit2.Call
import retrofit2.http.GET

interface Apiservice {

    @GET("Report")
    fun fetchAllUsers():Call<List<userRecy>>
}