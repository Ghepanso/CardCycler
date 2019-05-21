package com.mathmech.cards;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mathmech.cards.cycling.DefaultCycler;
import com.mathmech.cards.cycling.DefaultHolder;
import com.mathmech.cards.cycling.Packet;
import com.mathmech.cards.cycling.interfaces.Cycler;
import com.mathmech.cards.cycling.interfaces.Holder;

public class CardsScrollActivity extends AppCompatActivity {
    TextView tipsView;
    TextView themeView;
    TextView questionView;
    LinearLayout swipeableView;
    Animation fade;

    Holder packetHolder;
    Cycler currentCycler;

    StringBuilder tipsBuilder;

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

        Intent intent = getIntent();
        String theme = intent.getStringExtra("Cards name");
        themeView.setText(theme);

        initHolder();
        initCycler(theme);
        setNextCard();
        tipsBuilder = new StringBuilder();
        updateQuestion();

        tipsView.setMovementMethod(new ScrollingMovementMethod());
        swipeableView.setOnTouchListener(new OnSwipeTouchListener(CardsScrollActivity.this) {
            @Override
            public void onSwipeLeft() {
                fade.reset();
                questionView.clearAnimation();
                questionView.startAnimation(fade);
                flushTips();
                setNextCard();
                updateQuestion();
            }

            @Override
            public void onSwipeRight() {
                askForTip();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_left, R.anim.slideback_right);
    }

    void askForTip() {
        //TODO: extremly important. Check if tip is empty!!! -Jenya eto tebe
        String tip = currentCycler.askForNextTip();

        if (tip != null) appendTipToView(tip);
        else tellNoMoreTips();
    }

    void appendTipToView(String tip) {
        tipsBuilder.append('\t');
        tipsBuilder.append(tip);
        tipsBuilder.append('\n');
        tipsView.setText(tipsBuilder.toString());
    }

    void tellNoMoreTips() {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Подсказок больше нет", Toast.LENGTH_SHORT);
        toast.show();
    }

    void initHolder() {
        AssetManager manager = getAssets();
        packetHolder = new DefaultHolder(manager);
    }

    void initCycler(String packetName) {
        Packet packet = packetHolder.getPacketByName(packetName);
        currentCycler = new DefaultCycler(packet.getCards());
    }

    boolean cyclerIsNull() {
        return currentCycler == null;
    }

    void flushTips() {
        tipsView.setText("");
        tipsBuilder.setLength(0); //flush
    }

    void updateQuestion() {
        if (!cyclerIsNull())
            questionView.setText(currentCycler.getQuestion());
    }

    void setNextCard() {
        if (!cyclerIsNull())
            currentCycler.setNextCard();
    }
}