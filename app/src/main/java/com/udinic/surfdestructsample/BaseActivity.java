package com.udinic.surfdestructsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public abstract class BaseActivity extends AppCompatActivity {

    protected static final int LIST_SIZE = 15;
    protected DemoSurfaceTextureListener[] mSurfaceTextureListeners =
            new DemoSurfaceTextureListener[LIST_SIZE];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_switch:
                Class activityToOpen = getClass().equals(RecyclerViewActivity.class) ?
                        ListViewActivity.class : RecyclerViewActivity.class;
                Log.d("udinic", "Switching to " + activityToOpen.getSimpleName());
                startActivity(new Intent(this, activityToOpen));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
