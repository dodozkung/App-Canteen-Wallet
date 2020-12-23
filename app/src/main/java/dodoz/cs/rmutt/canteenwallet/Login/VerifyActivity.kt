//package dodoz.cs.rmutt.canteenwallet.Login
//
//import android.annotation.SuppressLint
//import android.content.Intent
//import android.graphics.drawable.Drawable
//import android.os.Bundle
//import android.os.CountDownTimer
//import android.util.Log
//import android.view.View
//import android.widget.Toast
//import androidx.annotation.DrawableRes
//import androidx.core.content.ContextCompat
//import com.google.firebase.FirebaseException
//import com.google.firebase.FirebaseTooManyRequestsException
//import com.google.firebase.auth.*
//import com.google.firebase.auth.UserInfo
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//import dodoz.cs.rmutt.canteenwallet.*
//import dodoz.cs.rmutt.canteenwallet.R
//import kotlinx.android.synthetic.main.activity_verify.*
//import java.util.HashMap
//import java.util.concurrent.TimeUnit
//
//class VerifyActivity : BaseActivity() {
//
//    private var mVerificationInProgress = false
//    private var mVerificationId: String? = null
//    private var mResendToken: PhoneAuthProvider.ForceResendingToken? = null
//    private var firebaseAuthListener: FirebaseAuth.AuthStateListener? = null
//    private var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null
//
//    private var timerstate = false
//    private var time: Long = 60000
//    private var timer: CountDownTimer? = null
//
//
//    private val KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress"
//
//    //firebase auth object
//    private var mAuth: FirebaseAuth? = null
//
//    @SuppressLint("SetTextI18n")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_verify)
//
//        mAuth = FirebaseAuth.getInstance()
//
//        val mobile = intent.getStringExtra("Phone")
//        phoneshow!!.text = " $mobile"
//
//
//
//
//
//
//        reotp!!.setOnClickListener {
//            if (mobile != null) {
//                resendVerificationCode(mobile, mResendToken)
//            }
//            time = 60000
//            timerstate = false
//            reotp.isEnabled = false
//            timestart()
//
//            time_countdown.visibility = View.VISIBLE
//            reotp.background = getCompatDrawable(R.drawable.wait_otp)
//        }
//
//        firebaseAuthListener = FirebaseAuth.AuthStateListener {
//            val user = FirebaseAuth.getInstance().currentUser
//            if (user != null) {
//                checknameisnotempty(user.uid)
//            } else {
//                if (mobile != null) {
//                    sendVerificationCode(mobile)
//                }
//            }
//        }
//
//        btnconfirmotp!!.setOnClickListener(View.OnClickListener {
////            showDialog()
//            val code = otpconfirm!!.text.toString().trim()
//            if (code.isEmpty() || code.length < 6) {
////                hideDialog()
//                otpconfirm!!.error = "รหัส OTP ผิดกรุณาลองอีกครั้ง"
//                otpconfirm!!.requestFocus()
//                return@OnClickListener
//            } else {
//                verifyPhoneNumberWithCode(mVerificationId, code)
//            }
//        })
//
//        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                mVerificationInProgress = false
//                signInWithPhoneAuthCredential(credential)
//            }
//
//            override fun onVerificationFailed(e: FirebaseException) {
//                mVerificationInProgress = false
//                if (e is FirebaseTooManyRequestsException) {
//                    SnackBar.snackbar(findViewById(android.R.id.content), "Quota exceeded.")
//                }
//            }
//
//            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
//                SnackBar.snackbar(findViewById(android.R.id.content), "ระบบกำลังจัดส่งรหัสยืนยัน(OTP)ไปที่เบอร์โทรศัพท์ของท่าน")
//                mVerificationId = verificationId
//                mResendToken = token
//            }
//        }
//
//
//
//
//    }
//
//
//
//
//    private fun getCompatDrawable(@DrawableRes drawableRes: Int): Drawable? {
//        return ContextCompat.getDrawable(this, drawableRes)
//    }
//
//    private fun timestart() {
//        timer = object : CountDownTimer(time, 1000) {
//
//            @SuppressLint("SetTextI18n")
//            override fun onTick(millisUntilFinished: Long) {
//                time = millisUntilFinished
//                time_countdown.text =
//                    "เหลือเวลาอีก " + millisUntilFinished / 1000 + " วินาที เพื่อขอรหัส OTP อีกครั้ง..."
//            }
//
//            override fun onFinish() {
//                if (!timerstate) {
//                    timerstate = true
//                    reotp.isEnabled = true
//                    time_countdown.visibility = View.GONE
//                    time = 60000
//                    reotp.background = getCompatDrawable(R.drawable.btnotp)
//                }
//            }
//        }.start()
//    }
//
//
//    private fun checknameisnotempty(uid: String) {
//        val checknameEmpty = FirebaseDatabase.getInstance().getReference(Common.USERS).child(Common.PHONE).child(uid)
//        checknameEmpty.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    val info = dataSnapshot.getValue(dodoz.cs.rmutt.canteenwallet.UserInfo::class.java)
//                    if (info!!.name == null || info.name.equals("")) {
////                        hideDialog()
//                        timerstate = true
//                        val setup = Intent(this@VerifyActivity, RegisterActivity::class.java)
//                        startActivity(setup)
//                        finishAffinity()
//                    } else {
////                        hideDialog()
//                        timerstate = true
//                        val intentGo = Intent(this@VerifyActivity, MainActivity::class.java)
//                        startActivity(intentGo)
//                        finishAffinity()
//                    }
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//
//            }
//        })
//    }
//
//    override fun onStart() {
//        super.onStart()
//        mAuth!!.addAuthStateListener(firebaseAuthListener!!)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        mAuth!!.removeAuthStateListener(firebaseAuthListener!!)
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, mVerificationInProgress)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        mVerificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS)
//    }
//
//    private fun sendVerificationCode(mobile: String) {
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//            "+66$mobile",
//            60,
//            TimeUnit.SECONDS,
//            this@VerifyActivity,
//            mCallbacks!!)
//
//        mVerificationInProgress = true
//
//    }
//
//    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
//        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
//        signInWithPhoneAuthCredential(credential)
//    }
//
//    private fun resendVerificationCode(mobile: String, token: PhoneAuthProvider.ForceResendingToken?) {
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//            "+66$mobile",
//            60,
//            TimeUnit.SECONDS,
//            this,
//            mCallbacks!!,
//            token)
//    }
//
////    private fun clearCustomerReq(userID: String) {
////        val cusreqref = FirebaseDatabase.getInstance().getReference(Common.CUS_REQUEST_REF).child(userID)
////        cusreqref.removeValue()
////    }
//
//    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
////        showDialog()
//        mAuth!!.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    val intent = intent
//                    val mobile = intent.getStringExtra("Phone")
//                    val userID = mAuth!!.currentUser?.uid
//                    val currentUserDB = FirebaseDatabase.getInstance().getReference(Common.USERS).child(Common.PHONE).child(userID!!)
//                    val info = HashMap<String, Any>()
//                    info["phone"] = mobile!!
//                    currentUserDB.updateChildren(info)
//                        .addOnCompleteListener {
////                            clearCustomerReq(userID)
//                        }
//                        .addOnFailureListener {
//                            return@addOnFailureListener
//                        }
//                } else {
////                    hideDialog()
//                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
//                        Toast.makeText(this@VerifyActivity, "รหัสยืนยัน(OTP)ไม่ถูกต้อง", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//            .addOnFailureListener {
////                hideDialog()
//                Log.d("Error", it.message.toString())
//            }
//    }
//
//}