package kr.ac.baekseok.moodio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    //1. 생성자로 DB생성하고 2. onCreate()테이블 생성 3. onUpgrade()테이블 초기화
    public MyDBHelper(Context context){
        super(context,"LoginDB",null,1);
        //LoginDB가 DB명이고 버전1
    } //생성자
    //code-override 2.onCreate()테이블 생성 3.onUpgrade() 테이블 초기화

    @Override //테이블 생성
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE RegisterInfo(uId TEXT PRIMARY KEY, uPassword TEXT, uName TEXT);"); //테이블 생성 쿼리문 수행 메소드
    }//onCreate()

    @Override //테이블 초기화 => 테이블이 있으면 삭제하고 다시 생성
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //1.테이블 삭제 2.테이블 생성 int i테이블구버전, int i1 신버전
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS RegisterInfo"); //테이블 삭제
        onCreate(sqLiteDatabase); //2테이블 생성
    }
}//MyDBHelper
