package com.shanjupay.merchant;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author dxt
 * @date 2021年10月13日 22:14
 */
public class TestAliPay {
    @Test
    public void upLoad() {
        //将接口返回的对账单下载地址传入urlStr
        //String urlStr = "http://dwbillcenter.alipay.com/downloadBillFile.resource?bizType=X&userId=X&fileType=X&bizDates=X&downloadFileName=X&fileId=X";
        String urlStr = "http://dwbillcenter-2-1-1.daily.alipay.net/downloadBillFile.resource?bizType=trade&userId=20886219563660040156&fileType=csv.zip&bizDates=20160405&downloadFileName=20886219563660040156_20160405.csv.zip&fileId=%2Ftrade%2F20886219563660040156%2F20160405.csv.zip&timestamp=1634134817&token=d416c456a79c8140e42eedf578fb34aa";
//指定希望保存的文件路径
        String filePath = "D:\\fund_bill_20190101.csv.zip";
        URL url = null;
        HttpURLConnection httpUrlConnection = null;
        InputStream fis = null;
        FileOutputStream fos = null;
        try {
            url = new URL(urlStr);
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setConnectTimeout(5 * 1000);
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.setRequestProperty("Charsert", "UTF-8");
            httpUrlConnection.connect();
            fis = httpUrlConnection.getInputStream();
            byte[] temp = new byte[1024];
            int b;
            fos = new FileOutputStream(new File(filePath));
            while ((b = fis.read(temp)) != -1) {
                fos.write(temp, 0, b);
                fos.flush();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
                httpUrlConnection.disconnect();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
