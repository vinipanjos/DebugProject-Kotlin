package com.vinicius.debugproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vinicius.debugproject.databinding.ActivityMainBinding
import java.lang.IndexOutOfBoundsException
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var clicks = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setLogListener()

        val currentThread = Thread.currentThread()
        currentThread.setUncaughtExceptionHandler { thread, throwable ->
            val message = throwable.message
            val cause = throwable.cause
        }
    }

    private fun setLogListener(){
        binding.debugBtn.setOnClickListener{ Log.d("click", "Cliquei no debug") }
        binding.errorBtn.setOnClickListener{
            var message = "Tudo certo"
            try {
                val list = listOf(2,1,4)
                val a = list[5]
                message = "Sobrevivi ao catch"
            } catch (e: IndexOutOfBoundsException){
                message = "Entrei no catch certo"
            }catch (i: NullPointerException){
                message = "Entrei no catch errado"
            }finally {
                binding.inputEdt.setText(message)
            }

            clicks++
            Log.e("click", "Cliquei no error $clicks vezes")
        }
        binding.infoBtn.setOnClickListener{Log.i("click", "Cliquei na info")}
        binding.warningBtn.setOnClickListener{Log.w("click", "Cliquei na warning")}
        binding.verboseBtn.setOnClickListener{Log.v("click", "Cliquei na verbose")}
    }
}