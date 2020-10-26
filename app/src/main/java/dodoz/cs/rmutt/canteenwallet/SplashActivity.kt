package dodoz.cs.rmutt.canteenwallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import dodoz.cs.rmutt.canteenwallet.Login.LoginActivity

class SplashActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        },4000)

    }


    }
