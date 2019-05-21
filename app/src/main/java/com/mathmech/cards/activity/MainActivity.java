package com.mathmech.cards.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mathmech.cards.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        String[] cardNames = getResources().getStringArray(R.array.card_names);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, cardNames);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener((parent, view, position, id) -> {
            TextView textView = (TextView) view;
            String text = textView.getText().toString();
            Intent intent = new Intent(getApplicationContext(), CardsScrollActivity.class);
            intent.putExtra("Cards name", text);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_right, R.anim.slideback_left);
        });
    }
}