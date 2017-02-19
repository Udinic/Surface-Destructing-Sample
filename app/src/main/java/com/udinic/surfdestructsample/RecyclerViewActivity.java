package com.udinic.surfdestructsample;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class RecyclerViewActivity extends AppCompatActivity {

    private static final int LIST_SIZE = 15;
    private RecyclerView.Adapter<DemoViewHolder> adapter;
    private DemoSurfaceTextureListener[] mSurfaceTextureListeners =
            new DemoSurfaceTextureListener[LIST_SIZE];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        initRV(this, recyclerView);
    }

    private void initRV(final Context context, RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setItemPrefetchEnabled(false);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(null);
        adapter = new RecyclerView.Adapter<DemoViewHolder>() {
            @Override
            public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                FrameLayout itemLayout = new FrameLayout(context);

                TextureView textureView = new TextureView(context);
                itemLayout.addView(textureView, new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));

                TextView textPosition = new TextView(context);
                textPosition.setTextSize(30);
                textPosition.setTextColor(Color.WHITE);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
                itemLayout.addView(textPosition, params);

                itemLayout.setLayoutParams(new RecyclerView.LayoutParams(1000, 500));
                return new DemoViewHolder(itemLayout, textureView, textPosition);
            }

            @Override
            public void onBindViewHolder(DemoViewHolder holder, int position) {
                if (mSurfaceTextureListeners[position] == null) {
                    mSurfaceTextureListeners[position] =
                            new DemoSurfaceTextureListener(context, position);
                }
                holder.textureView.setSurfaceTextureListener(mSurfaceTextureListeners[position]);
                holder.textPosition.setText(String.valueOf(position));
            }

            @Override
            public int getItemCount() {
                return LIST_SIZE;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }
        };
//        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);
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
//                Log.d("udinic", "stable ID checked=" + item.isChecked());
//                if (item.isChecked()) {
//                    item.setChecked(true);
//                } else {
//                    item.setChecked(false);
//                }
                return true;
            case R.id.action_switch_to_lv:
                Log.d("udinic", "Switch" + item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private static class DemoViewHolder extends RecyclerView.ViewHolder {
        FrameLayout itemLayout;
        TextureView textureView;
        TextView textPosition;

        public DemoViewHolder(FrameLayout itemLayout, TextureView textureView, TextView textPosition) {
            super(itemLayout);
            this.itemLayout = itemLayout;
            this.textureView = textureView;
            this.textPosition = textPosition;
        }
    }

}
