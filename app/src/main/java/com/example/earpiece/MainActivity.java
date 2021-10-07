package com.example.earpiece;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextToSpeech tts;
    Button button;
    EditText text;
    AudioManager audioManager;

    final static int FOR_MEDIA = 1;
    final static int FORCE_NONE = 0;
    final static int FORCE_SPEAKER = 1;
    private static final String TAG = "ahmed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.hello_world);
        text = findViewById(R.id.et);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        audioManager.setSpeakerphoneOn(false);


        Log.i(TAG, "speaker : " + audioManager.isSpeakerphoneOn());
        Log.i(TAG, "Mode : " + audioManager.getMode());


        tts = new TextToSpeech(this, this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);

                audioManager.setSpeakerphoneOn(false);

                Log.i(TAG, "Mode : " + audioManager.getMode());
                speak(text.getText().toString());
            }
        });

    }

    @Override
    protected void onResume() {

        super.onResume();
        // set true to use bluetooth SCO for communications; false to not use bluetooth SCO for communications
    }

    private void speak(String text) {
        Log.i(TAG, "speaker : " + audioManager.isSpeakerphoneOn());

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "");
    }

    @Override
    public void onInit(int status) {
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);

        audioManager.setSpeakerphoneOn(false);

        if (status == TextToSpeech.SUCCESS) {
            Log.i(TAG, "status : " + status);
            tts.setLanguage(Locale.ENGLISH);
        }

    }
}