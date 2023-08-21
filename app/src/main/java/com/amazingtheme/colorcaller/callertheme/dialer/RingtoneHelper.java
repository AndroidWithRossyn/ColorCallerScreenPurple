package com.amazingtheme.colorcaller.callertheme.dialer;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

public class RingtoneHelper {
    private static MediaPlayer mediaPlayer;
    public static void setDefaultRingtone(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        int ringerMode = audioManager.getRingerMode();
        boolean isSilentMode = ringerMode == AudioManager.RINGER_MODE_SILENT || ringerMode == AudioManager.RINGER_MODE_VIBRATE;

        if (!isSilentMode) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }
        if (defaultRingtoneUri != null) {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }

            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(context, defaultRingtoneUri);
                mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                        .build());
                mediaPlayer.setLooping(true);
                mediaPlayer.prepare();
                mediaPlayer.start();

                // Set the ringtone volume to maximum
                audioManager.setStreamVolume(AudioManager.STREAM_RING, audioManager.getStreamMaxVolume(AudioManager.STREAM_RING), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

   /* public static void setDefaultRingtone(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        int ringerMode = audioManager.getRingerMode();
        boolean isSilentMode = ringerMode == AudioManager.RINGER_MODE_SILENT || ringerMode == AudioManager.RINGER_MODE_VIBRATE;

        if (!isSilentMode) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }
        if (defaultRingtoneUri != null) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }

            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(context, defaultRingtoneUri);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
                mediaPlayer.setLooping(true);
                mediaPlayer.prepare();
                mediaPlayer.start();

                // Set the ringtone volume to maximum
                audioManager.setStreamVolume(AudioManager.STREAM_RING, audioManager.getStreamMaxVolume(AudioManager.STREAM_RING), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    public static void stopRingtone() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
