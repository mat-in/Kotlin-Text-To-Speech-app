package com.example.fleesh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private var btnSpeak: Button? = null
    private var etSpeak: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSpeak = findViewById(R.id.btn_speak)
        etSpeak = findViewById(R.id.et_input)

        btnSpeak!!.isEnabled = false

        tts = TextToSpeech(this, this)

        btnSpeak!!.setOnClickListener { speakOut() }
    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.ENGLISH)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The Language is not supported")
            } else {
                btnSpeak!!.isEnabled = true
            }
        }
    } private fun speakOut() {
        val text = etSpeak!!.text.toString()
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }
    public override fun onDestroy(){
        if(tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}

