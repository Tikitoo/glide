package me.tikitoo.android.hello;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String[] mStrings = new String[] {"Normal", "Diff"};
    Class<?>[] mClasses = new Class<?>[] {NormalActivity.class, LoadDiffImageActivity.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout topLayot = (LinearLayout) findViewById(R.id.layout_top);
        for (int i = 0; i < mStrings.length; i++) {
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setId(i);
            button.setText(mStrings[i]);

            button.setOnClickListener(this);
            topLayot.addView(button);
        }
    }

    @Override
    public void onClick(View v) {
       Intent intent = new Intent(MainActivity.this, mClasses[v.getId()]);
        startActivity(intent);
    }
}
