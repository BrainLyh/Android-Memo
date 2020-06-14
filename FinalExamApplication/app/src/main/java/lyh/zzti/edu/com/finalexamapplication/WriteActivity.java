package lyh.zzti.edu.com.finalexamapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.litepal.LitePal;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 提交评论页面，负责将文本存储到数据库中，需要展示文本时提取数据库内容
 */
public class WriteActivity extends AppCompatActivity {

    private EditText edtContent;
    private Button btnSubmit, btnCheckout;
    private TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        edtContent = findViewById(R.id.editText);
        btnSubmit = findViewById(R.id.btn_submit);
        tvDisplay = findViewById(R.id.Tv_display);
        btnCheckout = findViewById(R.id.btn_checkout);


        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String content = edtContent.getText().toString();
                java.util.Date date = new java.util.Date();

                Publishdata publishdata = new Publishdata();
                publishdata.setMemo(content);
                publishdata.setPublishdata(date);

                publishdata.saveThrows();
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Date date = new java.util.Date();
                List<Publishdata> dataes = LitePal.where("datetime() - publishdata < 7").find(Publishdata.class);
                String output = new String();
                for (Publishdata tmp : dataes) {

                    output += tmp.getPublishdata() + "  " + tmp.getMemo() + "\r\n";
                }
                tvDisplay.setText(output);
            }
        });
    }
}
