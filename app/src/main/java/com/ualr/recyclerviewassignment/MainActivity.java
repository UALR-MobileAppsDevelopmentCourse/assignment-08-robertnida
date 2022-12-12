package com.ualr.recyclerviewassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ualr.recyclerviewassignment.Utils.InboxFragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private InboxFragment inboxListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_multi_selection);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inboxListFragment = (InboxFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        FloatingActionButton mFAB = findViewById(R.id.fab);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFABClicked();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_selection_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_action:
                onDeleteClicked();
                return true;
            case R.id.forward_action:
                onForwardClicked();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    public void showBar() {
        CoordinatorLayout parentView = findViewById(R.id.lyt_parent);
        String msg = getResources().getString(R.string.snackbar_message);
        int duration = Snackbar.LENGTH_LONG;
        Snackbar snackbar = Snackbar.make(parentView, msg, duration);
        snackbar.setAction(R.string.snackbar_action, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Bar action tapped");
            }
        });
        snackbar.show();
    }

    public void onFABClicked() {
        if (inboxListFragment != null && inboxListFragment.isInLayout()) {
            inboxListFragment.addEmail();
        }
    }

    public void onDeleteClicked() {
        if (inboxListFragment != null && inboxListFragment.isInLayout()) {
            boolean emailDeleted = inboxListFragment.deleteEmail();
            if (emailDeleted) {
                String deleteMsg = getResources().getString(R.string.snackbar_message);
                showBar();
            }
        }
    }

    public void onForwardClicked() {
        if (inboxListFragment != null && inboxListFragment.isInLayout()) {
            inboxListFragment.forwardEmail();
        }
    }
}