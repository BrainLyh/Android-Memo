package lyh.zzti.edu.com.finalexamapplication;

/**
 * 用户信息实体类
 */
public class Userinfo {
    private String uname;
    private String upass;

    public Userinfo() {
    }

    public Userinfo(String uname, String upass) {
        this.uname = uname;
        this.upass = upass;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }
}
