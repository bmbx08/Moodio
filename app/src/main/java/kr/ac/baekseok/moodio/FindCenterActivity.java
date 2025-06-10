package kr.ac.baekseok.moodio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FindCenterActivity extends AppCompatActivity {
    Button btnBack,btnHome;
    WebView web;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_center);
        btnBack=(Button)findViewById(R.id.btnBack);
        btnHome=(Button)findViewById(R.id.btnHome);
        web=(WebView)findViewById(R.id.webView);
        web.setWebViewClient(new WebViewClient1()); //웹뷰에 URL로딩을 설정하는 메소드
        WebSettings webSet=web.getSettings();//웹뷰를 확대축소할 수 있는 클래스=>인스턴스 생성
        webSet.setBuiltInZoomControls(true); //확대축소기능 설정

        web.loadUrl("https://counselors.or.kr/KOR/license/supervisor_6.php");

        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                web.goBack();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });


    }

    class WebViewClient1 extends WebViewClient {
        //shouldOverrideUrlLoading() WebView에 URL을 로딩하는 메소드 code-override


        public boolean shouldOverrideUrlLoading(WebView view, String url){
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
