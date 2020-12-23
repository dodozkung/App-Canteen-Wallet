package dodoz.cs.rmutt.canteenwallet

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

abstract class BaseActivity : AppCompatActivity()
    , BaseMVPView {

    private lateinit var progressDialog: ProgressBarUtils
//    private val REQUEST_LOCATION = 199
//    private val REQUEST_CODE_FLEXI_UPDATE = 8888

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

//        progressDialog = ProgressBarUtils(this)

    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun showDialog() {
        if (!this.isFinishing) {
            progressDialog.showLoadingDialog()
        }
    }

    override fun showLoading() {
        if (!this.isFinishing) {
            progressDialog.showLoading()
        }
    }

    override fun hideDialog() {
        progressDialog.hideLoadingDialog()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CODE_FLEXI_UPDATE) {
//            when (resultCode) {
//                Activity.RESULT_CANCELED -> {
//                    popupSnackbarForState(
//                        "You cancel for update new version.",
//                        Snackbar.LENGTH_SHORT
//                    )
//                    finishAffinity()
//                    exitProcess(0)
//                }
//                Instrumentation.ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> {
//                    popupSnackbarForState("App download failed.", Snackbar.LENGTH_SHORT)
//                }
//            }
//        }
//    }
    private fun popupSnackbarForState(text: String, length: Int) {
//        showELog("$text : $length")
        Snackbar.make(
            findViewById(android.R.id.content),
            text,
            length
        ).show()
    }
//    private fun appIsOnline() {
//        FirebaseDatabase.getInstance().getReference(Common.APP_CONFIG_REF)
//            .child(Common.APP_CONFIG_CUSTOMER_APP)
//            .addListenerForSingleValueEvent(object : ValueEventListener {
//
//                override fun onCancelled(databaseError: DatabaseError) {
//                    Log.e("LogError", databaseError.message)
//                }
//
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    val obj = dataSnapshot.getValue(AppConfig::class.java)
//                    obj?.let {
//                        if (!obj.getIsOnline()!!) {
//                            offlineDialog()
//                        }
//                    }
//                }
//
//            })
//    }
//
//    @SuppressLint("InflateParams")
//    private fun offlineDialog() {
//        val inflater = LayoutInflater.from(this)
//        val subView = inflater.inflate(R.layout.check_app_offline_dialog, null)
//        val ndely = subView.findViewById<Button>(R.id.btnok)
//        val builder = AlertDialog.Builder(this)
//        builder.setView(subView)
//        val dialog = builder.create()
//        dialog.setCancelable(false)
//        ndely.setOnClickListener {
//            dialog.dismiss()
//            exitProcess(1)
//        }
//        if (!this.isFinishing) {
//            dialog.show()
//
//        }
//    }


}