package kr.ac.baekseok.moodio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewDiaryActivity extends AppCompatActivity {
    RadioGroup emotionGroup;
    EditText titleEdit, contentEdit;
    Button saveBtn;
    TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diary);

        emotionGroup = findViewById(R.id.emotion_group);
        titleEdit = findViewById(R.id.edit_title);
        contentEdit = findViewById(R.id.edit_content);
        saveBtn = findViewById(R.id.btn_save);
        dateText = findViewById(R.id.text_date);

        String today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        dateText.setText(today);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = emotionGroup.getCheckedRadioButtonId();
                if (checkedId == -1) return; // 감정 미선택 시 저장 안함

                RadioButton selected = findViewById(checkedId);
                String emotion = selected.getTag().toString();
                String title = titleEdit.getText().toString();
                String content = contentEdit.getText().toString();

                Intent result = new Intent();
                result.putExtra("title", title);
                result.putExtra("content", content);
                result.putExtra("emotion", emotion);
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
    }
}
