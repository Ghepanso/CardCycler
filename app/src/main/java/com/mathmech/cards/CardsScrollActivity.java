package com.mathmech.cards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.KeyException;

public class CardsScrollActivity extends AppCompatActivity {

    TextView themeView;
    TextView questionView;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_scroll);
        themeView = findViewById(R.id.question);
        Intent intent = getIntent();
        String theme = intent.getStringExtra("Cards name");
        themeView.setText(theme);

        CardCycler cardCycler = new CardCycler();
        Cycler cycler = null;
        try {
            Packet packet = cardCycler.getPacket("Osmin");
            cycler = new Cycler(packet.getCards());
        } catch (KeyException e) {
            e.printStackTrace();
        }

        nextButton = findViewById(R.id.buttonNext);
        Cycler finalCycler = cycler;
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("CLK", finalCycler.currentCard.name);
                if (finalCycler != null) {
                    questionView.setText(finalCycler.currentCard.question);
                    finalCycler.setNextCard();
                }
            }
        });
    }


}
