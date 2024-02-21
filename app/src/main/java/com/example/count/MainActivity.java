package com.example.count;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private Button button;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                List<String> matches = countVietnameseLetters(input);
                textView.setText(matches.size() + " " + matches.toString());
            }
        });
    }
    public static List<String> countVietnameseLetters(String input) {
        String[] vietnameseLetters = {"aw", "aa", "dd", "ee", "oo", "ow"};
        List<String> matches = new ArrayList<>();

        for (String letter : vietnameseLetters) {
            Pattern pattern = Pattern.compile(letter);
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                matches.add(letter);
                input = input.replaceFirst(letter, "");
            }
        }

        // count "w" separately
        Pattern pattern = Pattern.compile("w");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            matches.add("w");
        }

        return matches;
    }
}