package lyh.zzti.edu.com.finalexamapplication;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class Publishdata extends LitePalSupport {
    private Date publishdata;
    private String memo;

    public Date getPublishdata() {
        return publishdata;
    }

    public void setPublishdata(Date publishdata) {
        this.publishdata = publishdata;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
