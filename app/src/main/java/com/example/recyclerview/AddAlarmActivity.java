package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlarmManager;
import android.widget.TimePicker;

public class AddAlarmActivity extends AppCompatActivity {
    private AlarmManager alarmMgr = null;
    private PendingIntent alarmIntent = null;
    private TimePicker timePicker = null;
    private EditText editAlarmName = null;
    private int reqCode = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        // タイムピッカーを取得
        timePicker = findViewById(R.id.time_picker);
        // アラーム名を取得
        editAlarmName = findViewById(R.id.editAlarmText);

        // キャンセルボタンの設定
        Button can_b = findViewById(R.id.cancel_b);
        can_b.setOnClickListener(v -> {
            Intent i = new Intent();
            setResult(RESULT_CANCELED, i);
            // 画面終了
            finish();
        });



    }
}