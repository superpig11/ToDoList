package com.practice.superpig.todolist;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class ToDoListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        ListView myListView = (ListView) findViewById(R.id.myListView);

        final AutoCompleteTextView myAutoEditText = (AutoCompleteTextView) findViewById(R.id.myAutoCompleteTextView);
        final String[] todoAutoItems = {"eat","walk","work","sleep"};
        final ArrayAdapter<String> todoAutoAA = new ArrayAdapter<String>(this,
                                                            android.R.layout.simple_list_item_1,
                                                            todoAutoItems);
        myAutoEditText.setAdapter(todoAutoAA);

        final ArrayList<String> todoItems = new ArrayList<String>();
        final ArrayAdapter<String> itemAA = new ArrayAdapter<String>(this,
                                                    android.R.layout.simple_list_item_1,
                                                    todoItems);
        myListView.setAdapter(itemAA);

        myAutoEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER)
                            || (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        todoItems.add(0, myAutoEditText.getText().toString());
                        itemAA.notifyDataSetChanged();
                        myAutoEditText.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
    }
}
