package com.decurtis.youva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFrameLayout = findViewById(R.id.frame_container);

        addLoginFragment();
    }

    private void addLoginFragment() {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_container, new LoginFragment()).commit();
    }
}
