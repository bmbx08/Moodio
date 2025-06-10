package kr.ac.baekseok.moodio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ComfortCardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfort_cards);

        GridView gv=(GridView)findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter); //그리드뷰에 아답터를 설정하는 메소드(속성 설정)
    }

    public class MyGridAdapter extends BaseAdapter {

        Context context;
        ArrayList<ComfortCard> cardList = new ArrayList<>();

        public MyGridAdapter(Context c){
            context=c;

            cardList.add(new ComfortCard(R.drawable.heart1, "공감 카드", "지금 충분히 잘하고 있어요. 당신의 속도는 틀리지 않았어요."));
            cardList.add(new ComfortCard(R.drawable.book1, "심리 지식 카드", "불안은 뇌의 ‘편도체’가 과도하게 반응할 때 생기는 자연스러운 반응입니다."));
            cardList.add(new ComfortCard(R.drawable.question1, "감정 관리 카드", "불안할 땐 ‘4-7-8 호흡법’: 4초 들이쉬고, 7초 멈추고, 8초 내쉬기."));
            cardList.add(new ComfortCard(R.drawable.tip1, "자기성찰 카드", "오늘 나를 가장 힘들게 한 감정은 무엇이었나요?"));
            cardList.add(new ComfortCard(R.drawable.heart1, "공감 카드", "누구나 무기력할 때가 있어요. 그런 날도 당신의 일부예요."));
            cardList.add(new ComfortCard(R.drawable.book1, "심리 지식 카드", "우울은 ‘도파민’과 ‘세로토닌’ 같은 신경전달물질의 불균형과 관련이 있습니다."));
            cardList.add(new ComfortCard(R.drawable.question1, "감정 관리 카드", "하루 10분 산책은 생각을 정리하고 감정을 정돈하는 데 효과적입니다."));
            cardList.add(new ComfortCard(R.drawable.tip1, "자기성찰 카드", "내가 오늘 나 자신에게 해주고 싶은 말은 무엇인가요?"));
            cardList.add(new ComfortCard(R.drawable.heart1, "공감 카드", "오늘 하루도 살아낸 당신, 정말 대단해요."));
            cardList.add(new ComfortCard(R.drawable.book1, "심리 지식 카드", "감정을 억누르기보다 인식하고 이름 붙이는 것이 더 건강한 대처입니다."));
            cardList.add(new ComfortCard(R.drawable.question1, "감정 관리 카드", "마음이 복잡할 땐, 물 한잔을 천천히 마셔보세요. 그 짧은 순간이 리셋이 될 수 있어요."));
            cardList.add(new ComfortCard(R.drawable.tip1, "자기성찰 카드", "최근 며칠 동안 나는 무엇에 가장 에너지를 쏟았나요?"));
            cardList.add(new ComfortCard(R.drawable.heart1, "공감 카드", "지금 느끼는 감정은 틀린 게 아니에요. 그저 당신의 마음일 뿐이에요."));
            cardList.add(new ComfortCard(R.drawable.book1, "심리 지식 카드", "스트레스를 너무 오래 방치하면 신체 면역력도 떨어질 수 있어요."));
            cardList.add(new ComfortCard(R.drawable.question1, "감정 관리 카드", "부정적인 생각이 반복된다면, 그 생각을 글로 써서 밖으로 꺼내보세요."));
            cardList.add(new ComfortCard(R.drawable.tip1, "자기성찰 카드", "지금 나에게 가장 필요한 것은 무엇이라고 느껴지나요?"));
            cardList.add(new ComfortCard(R.drawable.heart1, "공감 카드", "괜찮지 않아도 괜찮아요. 힘들다고 말할 용기가 더 강한 거예요."));
            cardList.add(new ComfortCard(R.drawable.book1, "심리 지식 카드", "자기비판은 우울을 심화시키고, 자기이해는 회복을 돕습니다."));
            cardList.add(new ComfortCard(R.drawable.question1, "감정 관리 카드", "지금 당장 할 수 있는 작은 목표를 정해보세요. ‘이불 개기’도 괜찮아요."));
            cardList.add(new ComfortCard(R.drawable.tip1, "자기성찰 카드", "오늘 하루 중 감사했던 순간이 있다면 언제였나요?"));

//                심리 지식 카드
//                감정 도우미 카드
//                자기성찰 카드
        }; //생성자
        //상속시 추상메소드 override 4개 메소드 처리 클래스 내부에 커서 두고 ctrl+i

        @Override
        public int getCount() {
            return cardList.size();
        }

        @Override
        public Object getItem(int i) {
            return cardList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public View getView(int i, View view, ViewGroup viewGroup){
            View gridItem = View.inflate(context, R.layout.grid_item, null);

            ImageView imageView = gridItem.findViewById(R.id.imageView1);
            TextView textView = gridItem.findViewById(R.id.textView1);

            ComfortCard card = cardList.get(i);

            imageView.setImageResource(card.getImageResId());
            textView.setText(card.getCardType());

            gridItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View dialogView = View.inflate(context, R.layout.dialog, null);
                    ImageView cardImage = dialogView.findViewById(R.id.cardImage);
                    TextView cardText = dialogView.findViewById(R.id.cardText);

                    cardImage.setImageResource(card.getImageResId());
                    cardText.setText(card.getText());

                    Button favButton = dialogView.findViewById(R.id.favButton);
                    favButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(ComfortCardsActivity.this,HomeActivity.class);
                            intent.putExtra("cardText",card.getText());
                            startActivity(intent);
                        }
                    });

                    AlertDialog.Builder dlg = new AlertDialog.Builder(context);

                    dlg.setTitle(card.getCardType());
                    dlg.setNegativeButton("닫기", null);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            });

            return gridItem;
        }

    }
}
