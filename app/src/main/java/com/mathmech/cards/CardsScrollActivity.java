package com.mathmech.cards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.Objects;

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
        questionView.setText(Objects.requireNonNull(finalCycler).currentCard.getQuestion());

        StringBuilder sb = new StringBuilder();
        final int[] i = {0};
        nextButton.setOnClickListener(v -> {
            tipsView.setText("");
            sb.delete(0, sb.length());
            i[0] = 0;
            finalCycler.setNextCard();
            questionView.setText(finalCycler.currentCard.getQuestion());
        });

        showTipsButton.setOnClickListener(v -> {
            if (finalCycler.currentCard.getTips().length > i[0]) {
                sb.append(finalCycler.currentCard.getTips()[i[0]]).append('\n');
                tipsView.setText(sb.toString());
                i[0]++;
            } else {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Подсказок больше нет", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_left, R.anim.slideback_right);
    }
}
