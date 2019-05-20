package com.mathmech.cards;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.Objects;

public class CardsScrollActivity extends AppCompatActivity {
    TextView tipsView;
    TextView themeView;
    TextView questionView;
    LinearLayout swipeableView;
    Animation fade;
    int cardsCount = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_scroll);

        swipeableView = findViewById(R.id.swipeable);
        themeView = findViewById(R.id.cards_theme);
        tipsView = findViewById(R.id.tips);
        questionView = findViewById(R.id.question);
        fade = AnimationUtils.loadAnimation(this, R.anim.fade);

        String theme = getIntent().getStringExtra("Cards name");
        themeView.setText(theme);

        CardCycler cardCycler = new CardCycler(getAssets());
        Cycler cycler = null;
        try {
            Packet packet = cardCycler.getPacket(theme);
            ArrayList<Card> cards = packet.getCards(cardCycler.unpacker);
            cycler = new Cycler(cards);
        } catch (KeyException e) {
            e.printStackTrace();
        }

        Cycler finalCycler = cycler;
        questionView.setText(Objects.requireNonNull(finalCycler).currentCard.getQuestion());

        swipeableView.setOnTouchListener(new OnSwipeTouchListener(CardsScrollActivity.this) {
            StringBuilder sb = new StringBuilder();

            @Override
            public void onSwipeLeft() {
                fade.reset();
                questionView.clearAnimation();
                questionView.startAnimation(fade);
                TextViewCompat.setAutoSizeTextTypeWithDefaults(tipsView, TextViewCompat.AUTO_SIZE_TEXT_TYPE_NONE);
                tipsView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                tipsView.setText("");
                sb.delete(0, sb.length());
                cardsCount = 0;
                finalCycler.setNextCard();
                questionView.setText(finalCycler.currentCard.getQuestion());
            }

            @Override
            public void onSwipeRight() {
                TextViewCompat.setAutoSizeTextTypeWithDefaults(tipsView, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
                if (finalCycler.currentCard.getTips().length > cardsCount) {
                    sb.append("\t\t");
                    sb.append(finalCycler.currentCard.getTips()[cardsCount]).append('\n');
                    tipsView.setText(sb.toString());
                    cardsCount++;
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Подсказок больше нет", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_left, R.anim.slideback_right);
    }
}