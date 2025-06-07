package kr.ac.baekseok.moodio;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText rName, rId,rPw;
    Button registerBtn, btnGoToLogin; //import class
    MyDBHelper myHelper;
    SQLiteDatabase sqlDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        rName=(EditText)findViewById(R.id.regName);
        rId=(EditText)findViewById(R.id.regId);
        rPw=(EditText)findViewById(R.id.regPassword);
        registerBtn=(Button)findViewById(R.id.registerBtn);
        btnGoToLogin=(Button)findViewById(R.id.btnGoToLogin);
        myHelper=new MyDBHelper(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName=rName.getText().toString().trim();
                String inputId=rId.getText().toString().trim(); //임시변수에 저장, 양쪽여백제거
                String inputPw=rPw.getText().toString().trim();
                if(inputId.isEmpty()||inputPw.isEmpty()||inputName.isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "정보를 모두 입력해주세요.",
                            Toast.LENGTH_LONG).show(); //import class
                    return;
                }//if
                //DB를 쓰기 전용으로 열어서 회원레코드 1건 insert
                try{
                    sqlDB=myHelper.getWritableDatabase();
                    sqlDB.execSQL("INSERT INTO RegisterInfo(uId,uPassword,uName) VALUES('"+inputId+"','"+inputPw+"','"+inputName+"')"); //Insert 쿼리문 수행
                    //DB내부적으로 수행되서 사용자는 확인이 어려움 토스트메세지로 가입됨
                    Toast.makeText(getApplicationContext(), "회원가입 성공했습니다.",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }catch(Exception e){ //DB예외처리구문(입력안됨, 이미가입)
                    Toast.makeText(getApplicationContext(),
                            "회원가입 오류",
                            Toast.LENGTH_LONG).show();
                }finally{
                    sqlDB.close(); //정상이나 예외처리나 DB는 닫아야함.
                }
            }//onClick()
        });

        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),
                        LoginActivity.class); //import class 화면이동 Intent
                startActivity(intent); //메인화면 시작
            }
        });

    }
//    public class myDBHelper extends SQLiteOpenHelper {
//        //1. 생성자로 DB생성하고 2. onCreate()테이블 생성 3. onUpgrade()테이블 초기화
//        public myDBHelper(Context context){
//            super(context,"LoginDB",null,1);
//            //LoginDB가 DB명이고 버전1
//        } //생성자
//        //code-override 2.onCreate()테이블 생성 3.onUpgrade() 테이블 초기화
//
//        @Override //테이블 생성
//        public void onCreate(SQLiteDatabase sqLiteDatabase) {
//            sqLiteDatabase.execSQL("CREATE TABLE RegisterInfo(uId TEXT PRIMARY KEY, uPassword TEXT, uName TEXT);"); //테이블 생성 쿼리문 수행 메소드
//        }//onCreate()
//
//        @Override //테이블 초기화 => 테이블이 있으면 삭제하고 다시 생성
//        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//            //1.테이블 삭제 2.테이블 생성 int i테이블구버전, int i1 신버전
//            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS RegisterInfo"); //테이블 삭제
//            onCreate(sqLiteDatabase); //2테이블 생성
//        }
//    }//myDBHelper
}
