package com.imayi.trainbuilder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.imayi.trainbuilder.databinding.ActivityFilterMePleeeaseStrongBinding
import com.imayi.trainbuilder.gameefolder.GamerActivityyyyy

class FilterMePleeeaseStrong : AppCompatActivity() {

    private var _binding: ActivityFilterMePleeeaseStrongBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFilterMePleeeaseStrongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharPref = getSharedPreferences("SP", MODE_PRIVATE)
        val nameTest: String? = sharPref.getString(MainClassApp.C1, "null")
        val deepTest: String? = sharPref.getString(MainClassApp.D1, "null")
        if (nameTest!!.contains("tdb2")){
            Log.d("zero_filter", "nameWeb")
            Intent(this, WeeeeebLoadingActivity::class.java)
                .also { startActivity(it) }
            finish()
        }
        else if(deepTest!!.contains("tdb2")){
            Log.d("zero_filter", "deepWeb")
            Intent(this, WeeeeebLoadingActivity::class.java)
                .also { startActivity(it) }
            finish()
        }
        else{
            Log.d("zero_filter", "toGame")
            Intent(this, GamerActivityyyyy::class.java)
                .also { startActivity(it) }
            finish()
        }

    }
}