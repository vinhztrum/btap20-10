package com.thanhvinh.btap26_10
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ View
        val edtFullName = findViewById<EditText>(R.id.edtFullName)
        val edtContact = findViewById<EditText>(R.id.edtContact)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val cbFacebook = findViewById<CheckBox>(R.id.cbFacebook)
        val cbInstagram = findViewById<CheckBox>(R.id.cbInstagram)
        val cbTiktok = findViewById<CheckBox>(R.id.cbTiktok)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // 1. Event cho EditText (Khi nhập xong và chuyển ô hoặc bấm Done/Next)
        edtFullName.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && edtFullName.text.isNotEmpty()) {
                Toast.makeText(this, "Đã nhập họ tên: ${edtFullName.text}", Toast.LENGTH_SHORT).show()
            }
        }

        edtContact.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && edtContact.text.isNotEmpty()) {
                Toast.makeText(this, "Đã nhập liên hệ: ${edtContact.text}", Toast.LENGTH_SHORT).show()
            }
        }

        edtContact.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                Toast.makeText(this, "Đã nhập liên hệ: ${edtContact.text}", Toast.LENGTH_SHORT).show()
            }
            false
        }

        // 2. Event cho RadioGroup / RadioButton Giới tính
        rgGender.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = group.findViewById<RadioButton>(checkedId)
            if (selectedRadioButton != null) {
                Toast.makeText(
                    this,
                    "Bạn đã chọn giới tính: ${selectedRadioButton.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // 3. Event cho các CheckBox Mạng xã hội
        val checkBoxListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            val status = if (isChecked) "Đã chọn" else "Đã bỏ chọn"
            Toast.makeText(this, "$status: ${buttonView.text}", Toast.LENGTH_SHORT).show()
        }

        cbFacebook.setOnCheckedChangeListener(checkBoxListener)
        cbInstagram.setOnCheckedChangeListener(checkBoxListener)
        cbTiktok.setOnCheckedChangeListener(checkBoxListener)

        // 4. Event khi bấm Button Đăng ký -> Chuyển sang màn hình chi tiết
        btnRegister.setOnClickListener {
            val fullName = edtFullName.text.toString().trim()
            val contact = edtContact.text.toString().trim()

            // Kiểm tra nhập liệu cơ bản
            if (fullName.isEmpty() || contact.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ Họ tên và Liên hệ!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Lấy giới tính đã chọn
            val selectedGenderId = rgGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else {
                "Chưa chọn"
            }

            // Lấy danh sách mạng xã hội đã chọn
            val socialNetworks = mutableListOf<String>()
            if (cbFacebook.isChecked) socialNetworks.add("Facebook")
            if (cbInstagram.isChecked) socialNetworks.add("Instagram")
            if (cbTiktok.isChecked) socialNetworks.add("TikTok")

            val socialResult = if (socialNetworks.isNotEmpty()) {
                socialNetworks.joinToString(", ")
            } else {
                "Không sử dụng"
            }

            // Đóng gói dữ liệu vào Intent và chuyển màn hình
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_FULLNAME", fullName)
                putExtra("EXTRA_CONTACT", contact)
                putExtra("EXTRA_GENDER", gender)
                putExtra("EXTRA_SOCIAL", socialResult)
            }
            startActivity(intent)
        }
    }
}