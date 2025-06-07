package kr.ac.baekseok.moodio;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edtLoginId, edtLoginPw;
    Button btnLogin, btnGoToSignup;
    SQLiteDatabase sqlDB;
    MyDBHelper myHelper;  // myHelper 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLoginId = (EditText) findViewById(R.id.loginId);
        edtLoginPw = (EditText) findViewById(R.id.loginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnGoToSignup = (Button) findViewById(R.id.btnGoToSignup);

        myHelper = new MyDBHelper(this); // DB 헬퍼 객체 생성

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputId = edtLoginId.getText().toString().trim();
                String inputPw = edtLoginPw.getText().toString().trim();

                sqlDB = myHelper.getReadableDatabase(); // DB 읽기모드
                Cursor cursor = sqlDB.rawQuery(
                        "SELECT uPassword FROM RegisterInfo WHERE uId = ?",
                        new String[]{inputId}
                );

                if (cursor.moveToFirst()) {
                    String dbPw = cursor.getString(0);
                    if (inputPw.equals(dbPw)) {
                        Toast.makeText(getApplicationContext(), "정상회원입니다.", Toast.LENGTH_LONG).show();
                        // 로그인 성공 후 이동할 화면 설정 (지금은 임시로 MainActivity 자신으로 설정됨)
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "비밀번호가 틀립니다.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "아이디가 존재하지 않습니다. 회원가입해주세요", Toast.LENGTH_LONG).show();
                }

                cursor.close();
                sqlDB.close();
            }
        });

        btnGoToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}