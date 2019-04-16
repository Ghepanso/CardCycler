package com.mathmech.cards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.security.KeyException;
import java.util.ArrayList;

public class CardsScrollActivity extends AppCompatActivity {
    TextView tipsView;
    TextView themeView;
    TextView questionView;
    Button nextButton;
    Button showTipsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_scroll);

        themeView = findViewById(R.id.cards_theme);
        showTipsButton = findViewById(R.id.showTips);
        tipsView = findViewById(R.id.tips);
        nextButton = findViewById(R.id.buttonNext);
        questionView = findViewById(R.id.question);

        Intent intent = getIntent();
        String theme = intent.getStringExtra("Cards name");
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
        questionView.setText(finalCycler.currentCard.question);

        StringBuilder sb = new StringBuilder();
        final int[] i = {0};
        nextButton.setOnClickListener(v -> {
            if (finalCycler != null) {
                questionView.setText(finalCycler.currentCard.question);
                tipsView.setText("");
                sb.delete(0, sb.length());
                i[0] = 0;
                finalCycler.setNextCard();
            }
        });

        showTipsButton.setOnClickListener(v -> {
            sb.append(finalCycler.currentCard.tips[i[0]] + '\n');
            tipsView.setText(sb.toString());
            i[0]++;
        });
    }


}
