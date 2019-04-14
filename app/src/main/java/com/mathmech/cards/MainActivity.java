package com.mathmech.cards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        String[] cardNames = getResources().getStringArray(R.array.card_names);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, cardNames);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                String text = textView.getText().toString();
                Intent intent = new Intent(getApplicationContext(), CardsScrollActivity.class);
                intent.putExtra("Cards name", text);
                startActivity(intent);
            }
        });
    }
}
