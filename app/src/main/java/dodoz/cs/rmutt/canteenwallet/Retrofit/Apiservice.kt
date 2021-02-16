package dodoz.cs.rmutt.canteenwallet.Retrofit

import dodoz.cs.rmutt.canteenwallet.model.user
import retrofit2.Call
import retrofit2.http.GET

interface Apiservice {

    @GET("/users")
    fun fetchAllUsers():Call<List<user>>
}