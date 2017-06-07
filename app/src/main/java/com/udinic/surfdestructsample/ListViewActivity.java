package com.udinic.surfdestructsample;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class ListViewActivity extends BaseActivity {

    private static class Model {
        public DemoSurfaceTextureListener listener;
        public MediaPlayer mediaPlayer;
    }

    Model list[] = new Model[LIST_SIZE];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListView listView = (ListView) findViewById(R.id.list);
        initListView(this, listView);
    }

    private void initListView(final Context context, final ListView listView) {
        final BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return LIST_SIZE;
            }

            @Override
            public Object getItem(int i) {
                return i;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup viewGroup) {
                if (convertView == null) {
//                    convertView = new TextureView(context);
//                    convertView.setLayoutParams(new ListView.LayoutParams(1000, 500));

                    FrameLayout itemLayout = new FrameLayout(context);

                    TextureView textureView = new TextureView(context);
                    itemLayout.addView(textureView, new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));

                    TextView textPosition = new TextView(context);
                    textPosition.setTextSize(30);
                    textPosition.setTextColor(Color.WHITE);
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
                    itemLayout.addView(textPosition, params);

                    itemLayout.setLayoutParams(new RecyclerView.LayoutParams(1000, 500));

                    convertView = itemLayout;
                }

//                if (list[position] == null) {
//                    Model model = new Model();
//
//                    model.listener = new DemoSurfaceTextureListener(context, position, null);
//                    list[position] = model;
//                }x

                if (mSurfaceTextureListeners[position] == null) {
                    mSurfaceTextureListeners[position] =
                            new DemoSurfaceTextureListener(context, position, null);
                }

                ViewGroup layoutView = (ViewGroup) convertView;
                TextureView textureView = (TextureView) layoutView.getChildAt(0);
                TextView positionText = (TextView) layoutView.getChildAt(1);
                textureView.setSurfaceTextureListener(mSurfaceTextureListeners[position]);
                positionText.setText(String.valueOf(position));

                return convertView;
            }

        };

        listView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (DemoSurfaceTextureListener listener : mSurfaceTextureListeners) {
            if (listener != null) {
                listener.cleanup();
            }
        }
    }
}
