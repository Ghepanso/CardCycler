package com.mathmech.cards.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
    private CardView cardView;

    private Holder packetHolder;
    private Cycler currentCycler;

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
        cardView = findViewById(R.id.card_view);

        Intent intent = getIntent();
        String theme = intent.getStringExtra("Cards name");
        themeView.setText(theme);

        initHolder();
        initCycler(theme);
        setNextCard();
        //tipsBuilder = new StringBuilder();
        updateQuestion();
        cardView.setVisibility(View.GONE);
        swipeableView.setOnTouchListener(new OnSwipeTouchListener(CardsScrollActivity.this) {
            @Override
            public void onSwipeLeft() {
                fade.reset();
                questionView.clearAnimation();
                questionView.startAnimation(fade);
                cardView.setVisibility(View.GONE);
                flushTips();
                setNextCard();
                updateQuestion();
            }

            @Override
            public void onSwipeRight() {
                cardView.setVisibility(View.VISIBLE);
                askForTip();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_left, R.anim.slideback_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.show_help) {
            showHelp();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showHelp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Help");
        builder.setMessage("SwipeLeft for new card \n" +
                "SwipeRight for a tip");

        builder.setPositiveButton("Ok", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void askForTip() {
        String tip = currentCycler.askForNextTip();
        if (tip != null) {
            appendTipToView(tip);
            fade.reset();
            cardView.clearAnimation();
            cardView.startAnimation(fade);
        } else tellNoMoreTips();
    }

    private void appendTipToView(String tip) {
        tipsView.setText(tip);
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