package com.max.maxsamplekoin3.view.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.max.maxsamplekoin3.databinding.ActivityMainBinding
import com.max.maxsamplekoin3.di.closeDomainScope
import com.max.maxsamplekoin3.view.login.LoginActivity

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.buttonLogout.setOnClickListener {
            closeDomainScope()
            startActivity(LoginActivity.createIntent(this))
            finishAfterTransition()
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}