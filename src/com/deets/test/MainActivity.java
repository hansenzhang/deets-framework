package com.deets.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Toast;
import com.cardsui.example.R;

import com.fima.cardsui.objects.CardStack;
import com.fima.cardsui.views.CardUI;
import com.noteedit.test.NoteEditorActivity;
import com.noteedit.test.NoteItem;

public class MainActivity extends Activity
{

    // Final colors
    static final String GREY_COLOR = "#808080";
    static final String BLACK_COLOR = "#000000";
    private static final int EDITOR_ACTIVITY_REQUEST = 1000;
    private CardUI mCardView;
    NoteItem editNote = null;
    String myName = "";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("SplashActivity", "MainActivity Launched");

        // init CardView
        mCardView = (CardUI) findViewById(R.id.cardsview);
        mCardView.setSwipeable(false);

        mCardView.addCard(new MyCard("Joshua Shaak"));

        CardStack stack1 = new CardStack();
        // make string id for name in future
        stack1.setTitle("TO REMEMBER");
        mCardView.addStack(stack1);

        MyPlayCard notesCard = new MyPlayCard("Personal Notes",
                "Likes & Dislikes", "#ef597b", BLACK_COLOR, false, true);
        mCardView.addCard(notesCard);

        CardStack stackPlay = new CardStack();
        mCardView.addStack(stackPlay);
        stackPlay.setTitle("TO DISCUSS");

        // add AndroidViews Cards
        MyPlayCard androidViewsCard2 = new MyPlayCard("Philadelphia Eagles",
                "Next Game: Redskins, 9/9\n6:55 PM (ET)", "#ff6d31",
                BLACK_COLOR, true, false);
        androidViewsCard2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.google.com/#q=Eagles"));
                startActivity(intent);

            }
        });
        MyPlayCard androidViewsCard = new MyPlayCard("San Francisco Giants",
                "Next Game: Diamondbacks, 9/9\n4:05 PM (ET)", "#73b66b",
                BLACK_COLOR, false, true);
        final String temp = "San Francisco Giants";
        androidViewsCard.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.google.com/#q=" + temp));
                startActivity(intent);

            }
        });
        androidViewsCard.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v)
            {
                Toast.makeText(v.getContext(), "This is a long click",
                        Toast.LENGTH_SHORT).show();
                return true;
            }

        });

        mCardView.addCardToLastStack(androidViewsCard);
        mCardView.addCardToLastStack(androidViewsCard2);

        CardStack stackStocks = new CardStack();
        mCardView.addStack(stackStocks);

        MyPlayCard company1 = new MyPlayCard("JPMorgan Chase & Co",
                "Current Stock Price: 52.56", "#29a2c6", BLACK_COLOR, true,
                true);
        mCardView.addCardToLastStack(company1);

        company1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com/#q=jpm"));
                startActivity(intent);

            }
        });

        CardStack stack2 = new CardStack();
        mCardView.addStack(stack2);
        stack2.setTitle("TO KNOW");

        // add Cards
        MyPlayCard androidViewsCard4 = new MyPlayCard("Birthdate",
                "September 30, 1992", "#ef597b", BLACK_COLOR, true, false);

        mCardView.addCardToLastStack(androidViewsCard4);

        mCardView
                .addCard(new MyPlayCard(
                        "Education",
                        "Lehigh University\nComputer Science & Business\nClass of 2015",
                        "#ff6d31", BLACK_COLOR, false, true));
        // add one card
        mCardView.addCard(new MyPlayCard("Career",
                "Summer Anaylst Intern\nJPMorgan Chase and Co.", "#73b66b",
                BLACK_COLOR, false, false));

        CardStack stackStealTreat = new CardStack();
        mCardView.addStack(stackStealTreat);
        stackStealTreat.setTitle("TO TREAT");
        MyPlayCard StealCard = new MyPlayCard("Recommended Splurge",
                "Shop: Guitars", "#29a2c6", BLACK_COLOR, true, true);

        final String splurgeItem = "Guitars";
        StealCard.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri
                        .parse("http://www.shopyourway.com/search/products?allowRedirection=true&q="
                                + splurgeItem));
                startActivity(intent);
            }
        });
        mCardView.addCardToLastStack(StealCard);

        CardStack stackSplurgeTreat = new CardStack();
        mCardView.addStack(stackSplurgeTreat);

        MyPlayCard splurgeCard = new MyPlayCard("Recommended Steal",
                "Shop: Water bottles", "#ef597b", BLACK_COLOR, true, true);

        final String stealItem = "water-bottles";
        splurgeCard.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri
                        .parse("http://www.shopyourway.com/search/products?allowRedirection=true&q="
                                + stealItem));
                startActivity(intent);
            }
        });
        mCardView.addCardToLastStack(splurgeCard);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.androidviews.net/"));

        System.out.println("refresh here");
        refreshDisplay();

        final MainActivity context = this;
        notesCard.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v)
            {
                refreshDisplay();
                Intent intent = new Intent(context, NoteEditorActivity.class);
                intent.putExtra("key", editNote.getKey());
                intent.putExtra("text", editNote.getText());
                startActivityForResult(intent, EDITOR_ACTIVITY_REQUEST);
            }
        });

        // draw cards
        mCardView.refresh();
    }

    private void refreshDisplay()
    {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        String body = preferences.getString("savedNote", "");
        System.out.println("body in refresh: " + body);
        editNote = new NoteItem("myKey", body);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;

    }

}
