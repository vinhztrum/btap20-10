package com.thanhvinh.btap26_10
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Nhận dữ liệu từ Intent
        val fullName = intent.getStringExtra("EXTRA_FULLNAME") ?: "Trống"
        val contact = intent.getStringExtra("EXTRA_CONTACT") ?: "Trống"
        val gender = intent.getStringExtra("EXTRA_GENDER") ?: "Chưa chọn"
        val social = intent.getStringExtra("EXTRA_SOCIAL") ?: "Không có"

        // Hiển thị thông tin
        val displayInfo = """
            👤 Họ và tên: $fullName
            📧 Liên hệ: $contact
            ⚧ Giới tính: $gender
            🌐 Mạng xã hội: $social
        """.trimIndent()

        tvResult.text = displayInfo

        // Xử lý nút Quay lại
        btnBack.setOnClickListener {
            finish()
        }
    }
}