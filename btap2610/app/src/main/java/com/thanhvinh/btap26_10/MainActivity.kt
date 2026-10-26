package com.thanhvinh.btap26_10

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import androidx.coordinatorlayout.widget.CoordinatorLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coordinatorLayout = findViewById<CoordinatorLayout>(R.id.coordinatorLayout)
        val edtName = findViewById<EditText>(R.id.edtName)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        btnCancel.setOnClickListener {
            edtName.text.clear()
            edtEmail.text.clear()
            edtPhone.text.clear()
            edtPassword.text.clear()
            edtName.requestFocus()
            Snackbar.make(coordinatorLayout, "Đã làm mới form đăng ký", Snackbar.LENGTH_SHORT).show()
        }

        btnRegister.setOnClickListener {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val phone = edtPhone.text.toString()
            val password = edtPassword.text.toString()

            // Kiểm tra từng trường và hiển thị Snackbar nếu Fail
            when {
                !FormValidator.isValidName(name) -> {
                    Snackbar.make(coordinatorLayout, "Lỗi: Họ tên phải có ít nhất 3 ký tự!", Snackbar.LENGTH_LONG).show()
                    edtName.requestFocus()
                }
                !FormValidator.isValidEmail(email) -> {
                    Snackbar.make(coordinatorLayout, "Lỗi: Email không đúng định dạng!", Snackbar.LENGTH_LONG).show()
                    edtEmail.requestFocus()
                }
                !FormValidator.isValidPhone(phone) -> {
                    Snackbar.make(coordinatorLayout, "Lỗi: SĐT phải đúng 10 số (đầu 03, 05, 07, 08, 09)!", Snackbar.LENGTH_LONG).show()
                    edtPhone.requestFocus()
                }
                !FormValidator.isValidPassword(password) -> {
                    Snackbar.make(coordinatorLayout, "Lỗi: Mật khẩu phải có ít nhất 6 ký tự!", Snackbar.LENGTH_LONG).show()
                    edtPassword.requestFocus()
                }
                else -> {
                    // Success: Khởi tạo đối tượng User và chuyển sang DetailActivity
                    val user = User(name.trim(), email.trim(), phone.trim(), password)

                    val intent = Intent(this, DetailActivity::class.java).apply {
                        putExtra("EXTRA_NAME", user.name)
                        putExtra("EXTRA_EMAIL", user.email)
                        putExtra("EXTRA_PHONE", user.phone)
                    }
                    startActivity(intent)
                }
            }
        }
    }
}