package com.example.daggerstutorial.ui.main;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.daggerstutorial.BaseActivity;
import com.example.daggerstutorial.R;
import com.example.daggerstutorial.ui.main.posts.PostFragment;
import com.example.daggerstutorial.ui.main.profile.ProfileFragment;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testFragment();

    }

    private void testFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new PostFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout: {
                sessionManager.logout();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
