package com.hefny.hady.gadphasetwoproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.hefny.hady.gadphasetwoproject.R
import com.hefny.hady.gadphasetwoproject.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this,
                MainActivity::class.java))
            finish()
        },1000)
    }
}