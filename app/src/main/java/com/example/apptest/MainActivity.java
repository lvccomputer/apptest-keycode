package com.example.apptest;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import java.security.Key;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final Pattern KEYCODE_PATTERN = Pattern.compile("KEYCODE_(\\w)");

    private static final String TAG = "MainActivity";
    private PasscodeView passcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passcodeView = findViewById(R.id.passcodeview);
        passcodeView.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_DEL){
            passcodeView.clearPassCodeValue();
            return super.onKeyDown(keyCode, event);
        }
        String key = KeyEvent.keyCodeToString(keyCode);
        Matcher matcher = KEYCODE_PATTERN.matcher(key);
        if (matcher.matches()) {
            // append character to textview
            passcodeView.setPasscodeValue(matcher.group(1));
        }
        return super.onKeyDown(keyCode, event);
    }

//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (passcodeView != null) {
//            if (event.getAction() != KeyEvent.KEYCODE_DEL) {
//                Log.e(TAG, "dispatchKeyEvent: " + event.getKeyCharacterMap());
//                passcodeView.setPasscodeValue(event.getCharacters());
//            } else passcodeView.clearPassCodeValue();
//        }
//        return super.dispatchKeyEvent(event);
//
//    }
}