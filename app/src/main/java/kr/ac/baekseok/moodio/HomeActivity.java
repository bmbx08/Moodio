package kr.ac.baekseok.moodio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    TextView homeGreeting, quote;
    Button btnEmotionDiary,btnComfortCards,btnFindCenter;
    MyDBHelper myHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MoodieApp app = (MoodieApp) getApplication();

        homeGreeting=(TextView)findViewById(R.id.homeGreeting);
        btnEmotionDiary=(Button)findViewById(R.id.btnEmotionDiary);
        btnComfortCards=(Button)findViewById(R.id.btnComfortCards);
        btnFindCenter=(Button)findViewById(R.id.btnFindCenter);
        quote=(TextView)findViewById(R.id.quote);

        String favQuote = getIntent().getStringExtra("cardText");

        if(favQuote!=null){
            quote.setText("\"" + favQuote + "\"");
        }else{
            quote.setText("\"" +"원하는 카드를 즐겨찾기해주세요."+ "\"");
        }

        myHelper=new MyDBHelper(this);

//        Intent intent = getIntent();
//        String userId = intent.getStringExtra("userId");

        String userId = ((MoodieApp)getApplication()).getUserId();

        if (userId != null){
            sqlDB = myHelper.getReadableDatabase();
            Cursor cursor = sqlDB.rawQuery(
                    "SELECT uName FROM RegisterInfo WHERE uId = ?",
                    new String[]{userId}
            );

            if(cursor.moveToFirst()){
                String userName = cursor.getString(0);
                homeGreeting.setText(userName + "님 환영합니다!");
            }else{
                homeGreeting.setText("환영합니다!");
            }

            cursor.close();
            sqlDB.close();
        }

        btnEmotionDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DiaryListActivity.class);
                startActivity(intent);
            }
        });

        btnComfortCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ComfortCardsActivity.class);
                startActivity(intent);
            }
        });

        btnFindCenter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindCenterActivity.class);
                startActivity(intent);
            }
        });

//        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//        intent.putExtra("userId",inputId);
//        startActivity(intent);
    }
}
