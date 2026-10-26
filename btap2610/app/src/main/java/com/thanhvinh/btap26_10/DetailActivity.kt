package com.thanhvinh.btap26_10

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvUserInfo = findViewById<TextView>(R.id.tvUserInfo)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Nhận dữ liệu truyền qua Intent
        val name = intent.getStringExtra("EXTRA_NAME") ?: ""
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        val phone = intent.getStringExtra("EXTRA_PHONE") ?: ""

        // Hiển thị thông tin lên màn hình chi tiết
        tvUserInfo.text = """
            Họ và tên: $name
            Email: $email
            Số điện thoại: $phone
        """.trimIndent()

        // Nút Quay lại màn hình đăng ký
        btnBack.setOnClickListener {
            finish()
        }
    }
}