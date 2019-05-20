package com.mathmech.cards;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.mathmech.cards.old.Card;
//import com.mathmech.cards.old.CardCycler;
//import com.mathmech.cards.old.Cycler;
//import com.mathmech.cards.old.Packet;

import com.mathmech.cards.cycling.Card;
import com.mathmech.cards.cycling.DefaultCycler;
import com.mathmech.cards.cycling.DefaultHolder;
import com.mathmech.cards.cycling.Packet;
import com.mathmech.cards.cycling.interfaces.Cycler;
import com.mathmech.cards.cycling.interfaces.Holder;

import java.util.ArrayList;

public class CardsScrollActivity extends AppCompatActivity
{
    TextView tipsView;
    TextView themeView;
    TextView questionView;
    LinearLayout swipeableView;
    Animation fade;

    Holder packetHolder;
    Cycler currentCycler;

    StringBuilder tipsBuilder;

    // region Init cycling methods
    void initHolder()
    {
        AssetManager manager = getAssets();
        packetHolder = new DefaultHolder(manager);
    }

    void setCycler(String packetName)
    {
        Packet packet = packetHolder.getPacketByName(packetName);
        ArrayList<Card> cards = packet.getCards();
        currentCycler = new DefaultCycler(cards);
    }
    // endregion

    // region Calls to Cycler
    boolean isCyclerAlive()
    {
        //noinspection RedundantIfStatement TODO make exception in 'else'
        if(currentCycler != null)
            return true;
        else
            return false;
    }

    void updateQuestion()
    {
        if(isCyclerAlive())
            questionView.setText(currentCycler.getQuestion());
    }

    void setNextCard()
    {
        if(isCyclerAlive())
            currentCycler.setNextCard();
    }
    // endregion

    // region Tips stuff
    void flushTips()
    {
        tipsView.setText("");
        tipsBuilder.setLength(0); //flush
    }

    void askForTip()
    {
        String tip = currentCycler.askForNextTip();
        if(tip != null) appendTipToView(tip);
        else tellNoMoreTips();
    }

    void appendTipToView(String tip)
    {
        tipsBuilder.append('\t');
        tipsBuilder.append(tip);
        tipsBuilder.append('\n');

        tipsView.setText(tipsBuilder.toString());
    }

    void tellNoMoreTips()
    {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Подсказок больше нет", Toast.LENGTH_SHORT);
        toast.show();
    }
    // endregion


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // region Set views
        setContentView(R.layout.activity_cards_scroll);

        swipeableView = findViewById(R.id.swipeable);
        themeView = findViewById(R.id.cards_theme);
        tipsView = findViewById(R.id.tips);
        questionView = findViewById(R.id.question);
        fade = AnimationUtils.loadAnimation(this, R.anim.fade);

        Intent intent = getIntent();
        String theme = intent.getStringExtra("Cards name");
        themeView.setText(theme);
        // endregion

        //CardCycler cardCycler = new CardCycler(getAssets());
        //Cycler cycler = null;
        //try
        //{
        //    Packet packet = cardCycler.getPacketByName(theme);
        //    ArrayList<Card> cards = packet.getCards(cardCycler.unpacker);
        //    cycler = new Cycler(cards);
        //}
        //catch (KeyException e)
        //{
        //    e.printStackTrace();
        //}
        initHolder();
        setCycler(theme);

        tipsBuilder = new StringBuilder();
        updateQuestion();

        //questionView.setText(Objects.requireNonNull(finalCycler).currentCard.getQuestion());
        //StringBuilder sb = new StringBuilder();
        //final int[] i = {0};


        //        tipsView.setOnTouchListener(new OnSwipeTouchListener(CardsScrollActivity.this)
        //        {
        //            @Override
        //            public void onSwipeLeft()
        //            {
        //                flushTips();
        //                setNextCard();
        //                updateQuestion();
        //                //sb.delete(0, sb.length());
        //                //i[0] = 0;
        //                //finalCycler.setNextCard();
        //                //questionView.setText(finalCycler.currentCard.getQuestion());
        //            }
        //
        //            @Override
        //            public void onSwipeRight()
        //            {
        //                if (finalCycler.currentCard.getTips().length > i[0])
        //                {
        //                    sb.append(finalCycler.currentCard.getTips()[i[0]]).append('\n');
        //                    tipsView.setText(sb.toString());
        //                    i[0]++;
        //                }
        //                else
        //                {
        //                    tellNoMoreTips();
        //                }
        //            }
        //        });

        tipsView.setMovementMethod(new ScrollingMovementMethod());
        //region Init swiping
        swipeableView.setOnTouchListener(new OnSwipeTouchListener(CardsScrollActivity.this)
        {
            @Override
            public void onSwipeLeft()
            {
                fade.reset();
                questionView.clearAnimation();
                questionView.startAnimation(fade);
                flushTips();
                setNextCard();
                updateQuestion();
                //tipsView.setText("");
                //sb.delete(0, sb.length());
                //i[0] = 0;
                //finalCycler.setNextCard();
                //questionView.setText(finalCycler.currentCard.getQuestion());
            }

            @Override
            public void onSwipeRight()
            {
                askForTip();
                //{
                //
                //    Toast toast = Toast.makeText(getApplicationContext(),
                //            "Подсказок больше нет", Toast.LENGTH_SHORT);
                //    toast.show();
                //}
            }
        });
        //endregion
    }

    @Override
    public void onPause()
    {
        super.onPause();
        overridePendingTransition(R.anim.slide_left, R.anim.slideback_right);
    }
}
