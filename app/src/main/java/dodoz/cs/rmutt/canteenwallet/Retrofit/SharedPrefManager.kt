package dodoz.cs.rmutt.canteenwallet.Retrofit

import android.content.Context
import dodoz.cs.rmutt.canteenwallet.model.*


class SharedPrefManager private constructor(private val mCtx: Context) {


    val isLoggedIn: Boolean
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("N_ID", -1) != -1
        }

    fun saveUser(user: User) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("N_ID", user.N_ID)
        editor.putString("wallet_id", user.wallet_id)
        editor.putFloat("balance", user.balance)
        editor.putString("username", user.username)
        editor.putString("name", user.name)
        editor.putString("idcard", user.idcard)
        editor.putString("passconfirm", user.passconfirm)
        editor.putString("phone", user.phone)
        editor.putString("status", user.status)
        editor.putString("status2", user.status2)

        editor.apply()

    }

    fun getUser(user: GetUser) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putFloat("balance", user.balance)
        editor.putString("name", user.name)
        editor.putString("idcard", user.idcard)
        editor.putString("status", user.status)

        editor.apply()

    }

    fun getSearch(user: getNameUser) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("name1", user.name1)

        editor.apply()

    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }
}

