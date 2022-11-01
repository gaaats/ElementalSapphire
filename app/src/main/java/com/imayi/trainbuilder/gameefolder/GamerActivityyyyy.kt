package com.imayi.trainbuilder.gameefolder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imayi.trainbuilder.R
import com.imayi.trainbuilder.databinding.ActivityGamerActivityyyyyBinding

class GamerActivityyyyy : AppCompatActivity() {
    private var _binding: ActivityGamerActivityyyyyBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGamerActivityyyyyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}