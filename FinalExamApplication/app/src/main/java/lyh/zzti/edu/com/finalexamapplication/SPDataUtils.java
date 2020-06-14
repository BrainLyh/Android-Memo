package lyh.zzti.edu.com.finalexamapplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 使用SharedPreferences 存储登录信息
 */
public class SPDataUtils {
    private static final String mFileName = "logininfo"; //存储文件名称

    /**
     * 保存用户信息
     * @param context 上下文
     * @param uname 用户名
     * @param upass 密码
     * @return 返回值
     */
    public static boolean saveUserInfo(Context context, String uname, String upass){
        boolean flag = false;
        SharedPreferences sp = context.getSharedPreferences(mFileName, Context.MODE_PRIVATE); //设置读取方式
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("uname",uname);
        editor.putString("upass",upass);
        editor.commit();

        flag = true;
        return flag;
    }

    /**
     * 获取用户信息
     * @param context 调用的上下文
     * @return Userinfo 的实例
     */
    public static Userinfo getUserinfo(Context context){
        Userinfo user = null;
        SharedPreferences sp = context.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        String uname = sp.getString("uname",null);
        String upass = sp.getString("upass",null);

        user = new Userinfo();
        user.setUname(uname);
        user.setUpass(upass);

        return user;
    }
}
