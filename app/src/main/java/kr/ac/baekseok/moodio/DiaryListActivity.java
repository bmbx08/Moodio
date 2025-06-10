package kr.ac.baekseok.moodio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DiaryListActivity extends AppCompatActivity {
    MoodieApp app;
    ArrayList<DiaryEntry> diaries;
    ListView diaryList;
    Button btnNewEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        app = (MoodieApp) getApplication();

        diaries = new ArrayList<>();
        diaries.add(new DiaryEntry("괜찮은 하루", "오늘은 아침부터 날씨가 너무 좋아서 기분이 들떴다. 도서관에서 집중도 잘 되고, 오랜만에 만족스러운 하루였다.","happy"));
        diaries.add(new DiaryEntry("혼자라는 느낌", "사람들 사이에 있어도 어쩐지 외롭다는 생각이 계속 들었다. 누군가 내 마음을 알아줬으면 좋겠다는 바람만 맴돌았다.","sad"));
        diaries.add(new DiaryEntry("짜증 폭발", "과제 팀플에서 또 내가 다 떠맡게 됐다. 왜 항상 책임감 없는 사람들만 만나는지 모르겠다.","angry"));
        diaries.add(new DiaryEntry("계속 불안하다", "취업 준비가 머릿속을 떠나지 않는다. 내가 잘하고 있는 건지, 방향이 맞는 건지도 모르겠다.","anxious"));
        diaries.add(new DiaryEntry("소소한 행복", "편의점에서 산 빵이 생각보다 너무 맛있어서 기분이 좋아졌다. 작은 즐거움도 이렇게 큰 힘이 되는구나 싶었다.","happy"));
        diaries.add(new DiaryEntry("그냥 눈물이 났다", "별일 없었는데 지하철에서 갑자기 눈물이 나왔다. 내가 나를 돌보지 못하고 있다는 신호일지도 모르겠다.","sad"));

        diaryList = findViewById(R.id.diary_list);
        btnNewEntry = findViewById(R.id.btn_new_entry);

        DiaryAdapter adapter = new DiaryAdapter(this, diaries);
        diaryList.setAdapter(adapter);

        btnNewEntry.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), NewDiaryActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            String emotion = data.getStringExtra("emotion");

            diaries.add(new DiaryEntry(title, content, emotion));
            app.setDiaries(diaries);
            ((BaseAdapter) diaryList.getAdapter()).notifyDataSetChanged();
        }
    }

    public class DiaryEntry {
        String title;
        String content;
        String emotion;

        public DiaryEntry(String title, String content, String emotion) {
            this.title = title;
            this.content = content;
            this.emotion = emotion;
        }
    }

    class DiaryAdapter extends BaseAdapter {
        private final Activity context;
        private final ArrayList<DiaryEntry> items;

        public DiaryAdapter(Activity context, ArrayList<DiaryEntry> items) {
            this.context = context;
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // ListView의 각 줄을 생성
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null) {
                LayoutInflater inflater = context.getLayoutInflater();
                view = inflater.inflate(R.layout.diary_item, parent, false);
            }

            TextView title = view.findViewById(R.id.title);
            TextView content = view.findViewById(R.id.content);
            TextView emotion = view.findViewById(R.id.emotion);

            DiaryEntry entry = items.get(position);
            title.setText(entry.title);
            content.setText(entry.content);
            emotion.setText(getEmotionEmoji(entry.emotion)); // 감정에 맞는 이모지 설정

            return view;
        }

        // 감정 텍스트를 이모지로 변환
        private String getEmotionEmoji(String emotion) {
            switch (emotion) {
                case "happy": return "😊";
                case "sad": return "😢";
                case "angry": return "😠";
                case "anxious": return "😰";
                case "neutral": return "😐";
                default: return "📝";
            }
        }
    }
}

