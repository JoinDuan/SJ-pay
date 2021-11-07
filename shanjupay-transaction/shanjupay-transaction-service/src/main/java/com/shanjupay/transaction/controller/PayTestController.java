package com.shanjupay.transaction.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 支付宝接口对接测试类
 * @author Administrator
 * @version 1.0
 **/

@Slf4j
@Controller
//@RestController//请求方法响应统一json格式
public class PayTestController {

    //应用id
    String APP_ID = "2021000118608467";
    //应用私钥
    String APP_PRIVATE_KEY = "MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCL3RDYKE5TYYbMYVLMDhlhet/eXbgS80ebXf05mUFi/4/sEdsOM7k862MdYQdTJ3r6ev41h6vREgszv4wGyk4/mYi6mgxEQElFRGXSxfBvcxz2Qrhb4f7t3rhWaXm/ahVro1UDu3HfeeExdEheoK23a0UYjmRdLgOPbZbDS3BBCSzu0vpQ9y2KZkYYLbReg3QrUaQ/Dte4OBn8DCZriLt9UcoC+C+RXIUR2+a2Tu01x1baltCMV8t14+ALBAR+cg7q0KqKvFKXtVThT/M4KmoeKSE9Kg4sGhTmO3BCap8lJZRzbiOzcb21ZgfGfiyJ0C2sQTIa4Phaylpcq2Ts0lKfAgMBAAECggEAUT1IEnZfAJmuhbqmMDakUXXg4TXIvOEon7tZYgJ983YEcBfzCG3M0/DZ8m+wpIFTMnkMfaVfjKIRda52NF+AUfrpa5IdO+unY5+WEXa98M6JbRVWJrC/tgVfR3oeqKuC47HMqM1RGICf6GzluGlWXirlyJy0+VbH5KDAKJICExPXs17PGXNgNbb/wykwQbKNOVR+sGVS8oIrxbWr2OQ+PRbP9SmbKSEGgVhV7/bjxjdMoLvOIFCfLANrwUb78+SmGb9qnu1gBev5bL8kBxG2aCOvpDbhR0ceL+7u96sbfmhoMXK2MKHktZjOAooPcIiAwg+zeyRvIz7ZEN+687nOGQKBgQDN/G7gj7vZTidZX/OfMlE8vPTTQGdeSYp/K31BBNa4mzdCqWPgbJqngtXlH5AqNyMqY+6FSJUfuYA2pT5/I83CZy2Jw+wGro0ArpY9pv9a6H/v4cU1qU/DQRX9MFxo2XX68Vexg1dmuasPCNYtMe4a94zyOE4QgRjkA1WN9nj8swKBgQCt0qAf6cRK2L3UI4SDFHAlvsS0Lc2P9lBGBSh1FxIoUahHEptVp7fogSTn1jymAKiGmuAQs+T/pV9Wz1548MlXE5UAyk+ImFR4KJSRlIu/f9Y8/DR58W8OyPME/e8RTgUqEAvIXAsa+s46WPSyLv0lWbf8KKYVXEkplBKcdx7gZQKBgCVWFybfBraZwuohSUBW14DB40KTTuk5PUBVuqtZXC4z/C06FN9t8A5i2bBhKzlrqxUY7ff8swMAFaxSAWT8MWSGXSu2doazht1Tx+m+5lQtBluNubhDSzDiEEFHTci3efoVWsbTPrlg+A+Ok3u3rCf5LPQOnp/BNqjFrfFAdqefAn8qxqqhL+YehTgx/Iuftmfi4g+GR79Ork8S7DIInqTZRz8iPnH7okqf7Sgn5ZEHSSIv/e0mLx8ryoar1lxpZtgllawVhbQM6W/gIhxttyCs/8i5OVewqrmp6cnkcJlkqM6ci1Za+EvDV/VoZnvRXb7uciXU6Cv3Fb7rfIUX/DidAoGBAIMArleD6HKWI67z3+vcEFLuep5jBQVvw8wL0/VE2kj2DKdKcK8T3wa6/MMt1zjPlS7nstP8ZhWxuJydqTq8blNr2DGefIMYxy/zdHgw9ga3Kmhh0pTiF8PH6WACtGVVoRnMbsvPmS5x7IVCA/SULABf1Oo+lGW3NBsfrJI85eQZ";

    //支付宝公钥
    String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAi90Q2ChOU2GGzGFSzA4ZYXrf3l24EvNHm139OZlBYv+P7BHbDjO5POtjHWEHUyd6+nr+NYer0RILM7+MBspOP5mIupoMREBJRURl0sXwb3Mc9kK4W+H+7d64Vml5v2oVa6NVA7tx33nhMXRIXqCtt2tFGI5kXS4Dj22Ww0twQQks7tL6UPctimZGGC20XoN0K1GkPw7XuDgZ/Awma4i7fVHKAvgvkVyFEdvmtk7tNcdW2pbQjFfLdePgCwQEfnIO6tCqirxSl7VU4U/zOCpqHikhPSoOLBoU5jtwQmqfJSWUc24js3G9tWYHxn4sidAtrEEyGuD4WspaXKtk7NJSnwIDAQAB";
    String CHARSET = "utf-8";
    //支付宝接口的网关地址，正式"https://openapi.alipay.com/gateway.do"
    String serverUrl = "https://openapi.alipaydev.com/gateway.do";
    //签名算法类型
    String sign_type = "RSA2";

    @GetMapping("/alipaytest")
    public void alipaytest(HttpServletRequest httpRequest,
                       HttpServletResponse httpResponse) throws ServletException, IOException {
        //构造sdk的客户端对象
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, sign_type); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
//        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
//        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                " \"out_trade_no\":\"20150420010101233\"," +
                " \"total_amount\":\"88.88\"," +
                " \"subject\":\"Iphone6 16G\"," +
                " \"product_code\":\"QUICK_WAP_PAY\"" +
                " }");//填充业务参数
        String form="";
        try {
            //请求支付宝下单接口,发起http请求
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

}
