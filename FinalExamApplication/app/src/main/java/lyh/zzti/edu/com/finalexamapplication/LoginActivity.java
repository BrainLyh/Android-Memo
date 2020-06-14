package lyh.zzti.edu.com.finalexamapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_Login;
    private EditText et_Uname,et_Pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initview(); //初始化控件

        initData(); //初始化登陆数据
    }

    private void initview(){
        btn_Login = findViewById(R.id.bt_login);
        et_Uname = findViewById(R.id.et_uname);
        et_Pass = findViewById(R.id.et_pwd);

        btn_Login.setOnClickListener(this);
    }

    private void initData(){
        Userinfo user = SPDataUtils.getUserinfo(this);
        if(user!=null && !TextUtils.isEmpty(user.getUname())){
            et_Uname.setText(user.getUname());
            et_Pass.setText(user.getUpass());
        }
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
            checkandSaveData();
            break;
        }
    }

    /**
     * 检查登陆数据并保存
     */
    private void checkandSaveData(){
        String uname = et_Uname.getText().toString().trim();
        String upass = et_Uname.getText().toString().trim();

        if(TextUtils.isEmpty(uname)){
            showMsg("Username can not be empty! ");
        }else if(TextUtils.isEmpty(upass)){
            showMsg("Password can not be empty! ");
        }else{
            boolean flag = SPDataUtils.saveUserInfo(this,uname,upass);
            if(flag){
                showMsg("Login successfully! ");
            }else {
                showMsg("Login failed! ");
            }
        }
    }

    //显示通知信息
    private void showMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
