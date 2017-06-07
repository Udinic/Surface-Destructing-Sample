package com.udinic.surfdestructsample;

import android.content.Context;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;

class DemoSurfaceTextureListener implements TextureView.SurfaceTextureListener {

    private final Context mContext;

    private MediaPlayer mMediaPlayer;
    private final int mPosition;

    DemoSurfaceTextureListener(Context context, int position, MediaPlayer mediaPlayer) {
        mContext = context;
        mPosition = position;
        mMediaPlayer = mediaPlayer;
    }

    @Override
    public void onSurfaceTextureAvailable(
            SurfaceTexture surfaceTexture,
            int width,
            int height) {
        if (mMediaPlayer == null) {
            Surface surface = new Surface(surfaceTexture);
            mMediaPlayer = MediaPlayer.create(mContext, R.raw.cat);
            mMediaPlayer.setSurface(surface);
            mMediaPlayer.setLooping(true);
            mMediaPlayer.start();
            Log.d("udinic", "onSurfaceTextureAvailable position[" + mPosition
                    + "] surfaceTexture[" + Integer.toHexString(surfaceTexture.hashCode())
                    + "] surface[" + Integer.toHexString(surface.hashCode())
                    + "] mplayer[" + Integer.toHexString(mMediaPlayer.hashCode()) + "]");
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(
            SurfaceTexture surfaceTexture,
            int width,
            int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        /**
         * Note: This never gets called while scrolling the ListView, but gets called when one of
         * the TextureViews in the RecyclerView goes offscreen! This is because (if memory serves
         * me) RecyclerView calls removeView(child) while ListView calls
         * child.dispatchStartTemporaryDetach()
         */
        Log.d("udinic", "onSurfaceTextureDestroyed position[" + mPosition
                + "] surfaceTexture[" + Integer.toHexString(surfaceTexture.hashCode())
                + "] mplayer[" + (mMediaPlayer == null ? "NA" : Integer.toHexString(mMediaPlayer.hashCode())) + "]");

        cleanup();
        surfaceTexture.release();
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

    public void cleanup() {
        if (mMediaPlayer != null) {
            Log.d("udinic", "cleanup mplayer[" + Integer.toHexString(mMediaPlayer.hashCode()) + "]");
            mMediaPlayer.release();
        }
        mMediaPlayer = null;
    }
}
