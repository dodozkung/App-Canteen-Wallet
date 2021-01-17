package dodoz.cs.rmutt.canteenwallet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dodoz.cs.rmutt.canteenwallet.Login.LoginActivity
import dodoz.cs.rmutt.canteenwallet.Retrofit.SharedPrefManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        btnlogout.setOnClickListener {

            SharedPrefManager.getInstance(this).clear()

            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }



    }
    override fun onStart() {
        super.onStart()

        if(!SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

}

