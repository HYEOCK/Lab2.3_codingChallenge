package com.example.shoppinglist;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity {
    public static final String ITEMS_EXTRA = "ITEMS_EXTRA";
    private ArrayList<String> items;
    private EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        items = new ArrayList<>();
        mLocationEditText = findViewById(R.id.location_edittext);
    }

    public void itemSelected(View view) {
        CheckBox selectedCheckBox = (CheckBox) view;
        String item = selectedCheckBox.getText().toString();
        Toast.makeText(this, item + "Added. ", Toast.LENGTH_LONG).show();
        items.add(item);
    }

    public void finishAdding(View view){
        Intent replyIntent = new Intent();
        replyIntent.putStringArrayListExtra(ITEMS_EXTRA, items);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else {
            Log.d("ImplicitIntents", "Can't handle this intent");
        }
    }
}
