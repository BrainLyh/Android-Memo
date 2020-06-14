package lyh.zzti.edu.com.finalexamapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity {

    private Button mbtnLogin,mbtnWrite,mbtnPlay,mbtnStop;
    /**
     * 规定开始音乐、结束音乐的标志
     */
    public  static final int PLAT_MUSIC=1;
    public  static final int STOP_MUSIC=3;

    private MyBoradcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbtnLogin = findViewById(R.id.mbtn_login);
        mbtnWrite = findViewById(R.id.mbtn_write);
        mbtnPlay = findViewById(R.id.btn_playmusic);
        mbtnStop = findViewById(R.id.btn_stopmusic);

        setListeners();

        receiver=new MyBoradcastReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.complete");
        registerReceiver(receiver,filter);

    }

    private void setListeners(){
        OnClike onClike = new OnClike();
        mbtnLogin.setOnClickListener(onClike);
        mbtnWrite.setOnClickListener(onClike);
        mbtnPlay.setOnClickListener(onClike);
        mbtnStop.setOnClickListener(onClike);
    }

    private class OnClike implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.mbtn_login:
                    intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mbtn_write:
                    intent = new Intent(MainActivity.this,WriteActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_playmusic:
                    playingmusic(PLAT_MUSIC);
                    break;
                case R.id.btn_stopmusic:
                    playingmusic(STOP_MUSIC);
                    break;
            }
        }
    }

    private void playingmusic(int type) {
        //启动服务，播放音乐
        Intent intent=new Intent(this,PlayingMusicServices.class);
        intent.putExtra("type",type);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
