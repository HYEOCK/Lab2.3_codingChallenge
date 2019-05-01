package com.example.shoppinglist;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mLinearLayout;
    public static final int ITEMS_REQUEST = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLinearLayout = findViewById(R.id.linear_container);
    }

    public void goToList(View view) {
        Intent intent = new Intent(this, ItemListActivity.class);
        startActivityForResult(intent, ITEMS_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ITEMS_REQUEST){
            if (resultCode == RESULT_OK){
                // data
                ArrayList<String> items = data.getExtras().getStringArrayList(ItemListActivity.ITEMS_EXTRA);
                for (int i = 0; i < items.size(); i++){
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    TextView tv = new TextView(this);
                    tv.setLayoutParams(params);
                    tv.setGravity(Gravity.CENTER);
                    tv.setText(items.get(i));
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    mLinearLayout.addView(tv, 0);


                }

            }
        }
    }
}
