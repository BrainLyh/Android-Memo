package lyh.zzti.edu.com.finalexamapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class PlayingMusicServices extends Service {
    //用于播放音乐等媒体资源
    private MediaPlayer mediaPlayer;
    //标志判断播放歌曲是否是停止之后重新播放，还是继续播放
    private boolean isStop=true;

    public PlayingMusicServices() {
}

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    public void onCreate() {
        super.onCreate();
        if (mediaPlayer==null){
            mediaPlayer=new MediaPlayer();

            //为播放器添加播放完成时的监听器
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    //发送广播到MainActivity
                    Intent intent=new Intent();
                    intent.setAction("com.complete");
                    sendBroadcast(intent);
                }
            });
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        switch (intent.getIntExtra("type",-1)){
            case MainActivity.PLAT_MUSIC:
                if (isStop){
                    //重置mediaplayer
                    mediaPlayer.reset();
                    //将需要播放的资源与之绑定
                    mediaPlayer=MediaPlayer.create(this,R.raw.fixu);
                    //开始播放
                    mediaPlayer.start();
                    //是否循环播放
                    mediaPlayer.setLooping(false);
                    isStop=false;
                }else if (!isStop&&mediaPlayer.isPlaying()&&mediaPlayer!=null){
                    mediaPlayer.start();
                }
                break;
            case MainActivity.STOP_MUSIC:
                if (mediaPlayer!=null){
                    //停止之后要开始播放音乐
                    mediaPlayer.stop();
                    isStop=true;
                }
                break;
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
