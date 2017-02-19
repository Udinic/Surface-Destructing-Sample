package com.udinic.surfdestructsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


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
            case R.id.action_stableid:
                Log.d("udinic", "stable ID checked=" + item.isChecked());
                if (item.isChecked()) {
                    item.setChecked(true);
                } else {
                    item.setChecked(false);
                }
                return true;
            case R.id.action_switch_to_lv:
                Log.d("udinic", "Switch" + item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
