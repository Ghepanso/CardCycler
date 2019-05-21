package com.mathmech.cards.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mathmech.cards.R;
import com.mathmech.cards.cycling.DefaultCycler;
import com.mathmech.cards.cycling.DefaultHolder;
import com.mathmech.cards.cycling.Packet;
import com.mathmech.cards.cycling.interfaces.Cycler;
import com.mathmech.cards.cycling.interfaces.Holder;
import com.mathmech.cards.ui.utils.OnSwipeTouchListener;

public class CardsScrollActivity extends AppCompatActivity {
    private TextView tipsView;
    private TextView themeView;
    private TextView questionView;
    private LinearLayout swipeableView;
    private Animation fade;

    private Holder packetHolder;
    private Cycler currentCycler;

    private StringBuilder tipsBuilder;

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

        swipeableView.setOnTouchListener(new OnSwipeTouchListener(CardsScrollActivity.this) {
            @Override
            public void onSwipeLeft() {
                fade.reset();
                questionView.clearAnimation();
                questionView.startAnimation(fade);
                flushTips();
                setNextCard();
                updateQuestion();
                resetSize();
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

    private void resetSize() {
        TextViewCompat.setAutoSizeTextTypeWithDefaults(tipsView, TextViewCompat.AUTO_SIZE_TEXT_TYPE_NONE);
        tipsView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(tipsView, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
    }

    private void askForTip() {
        String tip = currentCycler.askForNextTip();
        if (tip != null) appendTipToView(tip);
        else tellNoMoreTips();
    }

    private void appendTipToView(String tip) {
        tipsBuilder.append('\t');
        tipsBuilder.append(tip);
        tipsBuilder.append('\n');
        tipsView.setText(tipsBuilder.toString());
    }

    private void tellNoMoreTips() {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Подсказок больше нет", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void initHolder() {
        AssetManager manager = getAssets();
        packetHolder = new DefaultHolder(manager);
    }

    private void initCycler(String packetName) {
        Packet packet = packetHolder.getPacketByName(packetName);
        currentCycler = new DefaultCycler(packet.getCards());
    }

    private boolean cyclerIsNull() {
        return currentCycler == null;
    }

    private void flushTips() {
        tipsView.setText("");
        tipsBuilder.setLength(0);
    }

    private void updateQuestion() {
        if (!cyclerIsNull())
            questionView.setText(currentCycler.getQuestion());
    }

    private void setNextCard() {
        if (!cyclerIsNull())
            currentCycler.setNextCard();
    }
}