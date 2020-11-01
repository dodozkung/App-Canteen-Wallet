package dodoz.cs.rmutt.canteenwallet.Login

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.jakewharton.threetenabp.AndroidThreeTen
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import dodoz.cs.rmutt.canteenwallet.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : BaseActivity() {
    private var addonce = false
    private var dateBirthday = ""
    private var startDate: String? = null
    private var dateTime = ""
    private val userID = FirebaseAuth.getInstance().currentUser!!.uid
    private var key: String? = null
    private var resultUri: Uri? = null


//    lateinit var rb_male: RadioButton
//    lateinit var rb_female: RadioButton


    val myCalendar = Calendar.getInstance()
    val year = myCalendar.get(Calendar.YEAR)
    val month = myCalendar.get(Calendar.MONTH)
    val day = myCalendar.get(Calendar.DAY_OF_MONTH)

    var getSex: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()

        Profileinfo.setOnClickListener {
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setCropShape(CropImageView.CropShape.RECTANGLE)
                .setCropMenuCropButtonTitle("ตกลง")
                .start(this)
        }
        rg_gender.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_male -> {
                    getSex = getString(R.string.male)
                }
                R.id.rb_female -> {
                    getSex = getString(R.string.female)
                }
            }
        }

        btnconprofile.setOnClickListener {
            val name = edtname.text.toString().trim()
            val phone = edtphone.text.toString().trim()
            val email = edtemail.text.toString().trim()
            val idcard = edtidcard.text.toString().trim()
            val birthday = birthday.text.toString().trim()
            val money = "0"
            val password = edtpw.text.toString().trim()

            if (getSex == null) {
                Toast.makeText(this, "กรุณาระบุเพศ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (name.isEmpty()) {
                edtname.error = "กรุณากรอกชื่อ - นามสกุล"
                Log.d("AAAA", "0")
                return@setOnClickListener
            } else if (birthday.isEmpty()) {
                Toast.makeText(this, "กรุณาระบุวันเกิด", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (email.isEmpty()) {
                edtemail.error = "กรุณากรอกอีเมล"

                return@setOnClickListener

            } else {
//                confirmDialog()
                val buyerOrderREF1 =
                    FirebaseDatabase.getInstance().getReference("User").child(Common.USERS)
                val query = buyerOrderREF1.orderByChild("name").equalTo(name)
                query.addListenerForSingleValueEvent(object : ValueEventListener {

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (ds in dataSnapshot.children) {
                            key = ds.key
                        }
                        if (dataSnapshot.exists() && userID != key) {
                            edtname!!.error = "ไม่สามารถใช้งานชื่อนี้ได้"
                            edtname!!.requestFocus()
                            return
                        } else {
                            if (!EmailFormat.isEmailValid(email) && email != "") {
                                edtemail!!.error = "อีเมล์ไม่ถูกต้อง โปรดลองอีกครั้ง"
                                edtemail!!.requestFocus()
                                return
                            } else {
//                                showDialog()
                                val userInfo = HashMap<String, Any>()
                                userInfo["sex"] = getSex!!
                                userInfo["name"] = name
                                userInfo["birthday"] = birthday
                                userInfo["phone"] = phone
                                userInfo["email"] = email
                                userInfo["idcard"] = idcard
                                userInfo["password"] = password
                                userInfo["money"] = money

                                val mCustomerDatabase = FirebaseDatabase.getInstance()
                                    .getReference(Common.USERS)
                                    .child(Common.PHONE)
                                    .child(userID)
                                mCustomerDatabase.updateChildren(userInfo)
                                if (resultUri != null) {
                                    val filePath =
                                        FirebaseStorage.getInstance().getReference("profile_images")
                                            .child(Common.USERS).child(Common.PHONE)
                                            .child(userID)
                                    var bitmap: Bitmap? = null
                                    try {
                                        bitmap = MediaStore.Images.Media.getBitmap(
                                            application.contentResolver,
                                            resultUri
                                        )
                                    } catch (e: IOException) {
                                        e.printStackTrace()
                                    }
                                    val baos = ByteArrayOutputStream()
                                    bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, baos)
                                    val data = baos.toByteArray()
                                    val uploadTask = filePath.putBytes(data)

                                    uploadTask.addOnFailureListener(OnFailureListener {
                                        return@OnFailureListener
                                    })
                                    uploadTask.addOnSuccessListener { taskSnapshot ->
                                        val uri = taskSnapshot.storage.downloadUrl
                                        while (!uri.isComplete);
                                        val downloadUrl = uri.result

                                        val newImage = HashMap<String, Any>()
                                        newImage["profileImageUrl"] = downloadUrl!!.toString()
                                        mCustomerDatabase.updateChildren(newImage)
                                            .addOnCompleteListener {
                                                updateprofile()
                                            }
                                            .addOnFailureListener {
                                                return@addOnFailureListener
                                            }
                                    }
                                } else {
                                    updateprofile()
                                }
                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {

                    }
                })
            }

        }
    }

    private fun confirmDialog() {
        TODO("Not yet implemented")
    }

    private fun init() {
        loadPhoneNumber()
        birthday!!.setOnClickListener {
            val mDateSetListener: DatePickerDialog.OnDateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    myCalendar.set(Calendar.YEAR, year)
                    myCalendar.set(Calendar.MONTH, monthOfYear)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateLabel()
                }
            val d = DatePickerDialog(
                this,
                R.style.DialogTheme,
                mDateSetListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            )
            d.show()
        }

        AndroidThreeTen.init(this)
    }

    private fun loadPhoneNumber() {
//        showDialog()
        val getphoneref =
            FirebaseDatabase.getInstance().getReference(Common.USERS).child(Common.PHONE)
                .child(userID).child("phone")
        getphoneref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
//                    hideDialog()
                    val phonemobile = p0.value.toString()
                    println(phonemobile)
                    edtphone.setText(phonemobile)
//                    edtphone.hint = phonemobile//edittext ใช้ settext
                }
            }
        })
    }

    private fun updateLabel() {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val myFormatshow = "dd/MM/yyyy"
        val sdfShow = SimpleDateFormat(myFormatshow, Locale.US)
        dateTime = sdf.format(myCalendar.time)

        birthday!!.setText(sdfShow.format(myCalendar.time))

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                resultUri = result.uri
                Profileinfo!!.setImageURI(resultUri)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val e: Exception = result.error
            }
        }
    }

    private fun updateprofile() {
        if (!addonce) {
            addonce = true
            val current = org.threeten.bp.LocalDateTime.now()
            val formatter =
                org.threeten.bp.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            val currentDatetime = current.format(formatter)

            runBlocking {
                async {
                    val currentUserDB =
                        FirebaseDatabase.getInstance().getReference(Common.USERS)
                            .child(Common.PHONE).child(userID)
                    currentUserDB.child("create_at").setValue(currentDatetime)
                }.await()
            }
        }
    }

}