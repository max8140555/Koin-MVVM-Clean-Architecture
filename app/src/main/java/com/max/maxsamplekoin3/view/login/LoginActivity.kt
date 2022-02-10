package com.max.maxsamplekoin3.view.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.max.maxsamplekoin3.databinding.ActivityLoginBinding
import com.max.maxsamplekoin3.view.main.MainActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        collectViewModel()
    }

    private fun initView() {
        binding.buttonLogin.setOnClickListener {
            val name = binding.editTextInputName.text.toString()
            viewModel.login(name)
        }
    }

    private fun collectViewModel() {
        lifecycleScope.launch {
            viewModel.isLoginSuccess.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { isLoginSuccess ->
                    if (isLoginSuccess) {
                        goToMainPage()
                    } else {
                        // 登入失敗
                    }
                }
        }
    }

    private fun goToMainPage() {
        startActivity(MainActivity.createIntent(this))
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}