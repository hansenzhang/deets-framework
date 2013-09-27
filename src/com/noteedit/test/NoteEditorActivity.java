package com.noteedit.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cardsui.example.R;

public class NoteEditorActivity extends Activity
{
    private NoteItem note;
    Button tabButton;
    EditText deetsText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        // turn launcher icon into an options button
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = this.getIntent();
        note = new NoteItem("", "");
        note.setKey(intent.getStringExtra("key"));
        note.setText(intent.getStringExtra("text"));

        deetsText = (EditText) findViewById(R.id.noteText);
        deetsText.setText(note.getText());
        deetsText.setSelection(note.getText().length());

        // creating tab button
        tabButton = (Button) findViewById(R.id.button1);
        tabButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                deetsText.append("     ");
            }
        });
    }

    private void saveAndFinish()
    {

        EditText deetsText = (EditText) findViewById(R.id.noteText);
        String body = deetsText.getText().toString();
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("savedNote", body);
        System.out.println("Body after save: " + body);
        editor.commit();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home) {
            saveAndFinish();
        }
        return false;
    }

    @Override
    public void onBackPressed()
    {
        saveAndFinish();

    }
}
