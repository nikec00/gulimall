package com.atguigu.gulimall.order.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atguigu.gulimall.order.vo.PayVo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {

    //在支付宝创建的应用的id
    private String app_id = "2021000119606313";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCcxJWD3AI8MF1mQmrqYBJ0yHEDv+Y0uucX6RHQphwpOjPrRqpLM7qY+2a9NT+Y4QUbtQZmQBjY8uQ49d+7guY1KIOZz1X6hsz4YxYl2zj14kMXZxfHs7bv9kb1V8DSqoOp3oLGeWh4n1O4TqCRv5cbekCNJjBKmTrgCcEe98JNhHQf+8ysM3BmhyfxxM/fDzRuZQMt987RlF0BoCInSjRgN8q0IRses4cJRqFEyKpSlVl0kyInJf/X3eEmspsu2TIW3fmCQciLWyYa8Oa592uc8+dTPJgatm0vf1fHUlSb/YLcs2EJuYi8YqgxMu72pDOaY96jZ+SJqrP4pEUdtomfAgMBAAECggEAUA1qtCzSz117NE7SPeDVS+FXJK0HpwGSJHmV6VAJrVvVS12IdGJz9M7cBd59HVo6u41TOT+d8JsHw7Z6i0wqthZ8LTzdENNpM6WVl7s8eQMTc43nbMjur9a5IWuwQWNlXIcNjMBOgtoJbGJqSELnrNZexDUuD8nzcJhKj7/A7sfZqNiWekWb7D8YhhTvyqXcaCzjybqWwRmyRCxvxNFRynAB/sKAwjQ+czzMUA8lU90lQ4zWFow4Fne8UTx/5SPmo7CeGFYb6zsW0zP5rkRXnoUO0NbY3GQWswO1M/AVzPZTDLrygnUIFtBkYoxuY8aTf2/lQGTPxHAdiOEQGQ2+8QKBgQDQlezKxxdNRIW0bzsNEnLfPpZcJvIJWELHDaF0Wj8CwVHKu3z0KNhync8fsWFVIAIwuOtTh2LIGbtKSOjAKyXDR1rOJjwlGJD6iCLCqfO8Gxnm4BOg1CUgksWTPBc/XRHzQ2KH7Pa2hKyV5lUAQXn9bd/UoGkKoWC3e2E8dmEeSwKBgQDAZ0Q8+TpCe1g8tvzTledZ/YoshdGVGbn2DD0Rsc2Qeh70hYrOJMU+MLlXd1GRHc9xvCzGQpqb6oD+fr6CnnP1Yns3Kb9iDLypIYow1eCqslwgMj+cY7SCbRDZPwvyzKpbJT3SYqK0Q12Ok0GNllxCtCmODly9mC8LdaNpJOzdfQKBgQC9ITQ0KZHXXepeUP0aPUArbHSp2iZcksr58kITXkEEHoYrbRU/zkl0o+LKueunY/6YbxPyCTBof+xyzuk8VeNHzctoYQLoH/5VLFtYZwG57ljuHv+tNK71eh/sAPCTY7CDAva0iQirNmHHCbLJKJWUVaw+/7Q/D1TDCfyA9lXZQQKBgQCHSxZM2eQBw8I0T2hRUGOyf/qdfCv38HM3dTusKZ8nLPh/cTg95NVd+d0aiCivJossqunBMCyP3sJ+Mpm8N7fKoUmLbKvLQrGXLKKD3KFy5qCpcywPyHyyCgeC/DZmsUxJc1KZkgpfP8+V5i5+ZXDS5uUhoy/LkivinwIdeX2l8QKBgDchqwjU8iq0+PR9NLswReWHxiVfXj4C6Vn6qcU/NSYAXGMz1uGmwcA6jf9MA1/Wy4+LsRXYcpBlVeUM7AbYumOvugwoBtqsX/Hap0kl2P0r5aEcMoVG7e+XpJZscOphvDHv4PTdS97hEsZ7v9rmPkSBr/OgaZ8cUvRZ5pHmxDvh";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn9m0klLiujQecHfbNpdKQpqo2ZW1KD1VE5YAYTPpszlRxe0+m5URP1/Q0TRcw8vOuwFP+p1UHryUv5xcqS2JbyM8g7SIx4KRSrrtKazMWLEiK4xu8s6c+pD+FrU9Ez+zWxniT+fJumn6vxzQNE5xQvr5Lc+6WFbX1ymcsYN4ZXN0pqp0f/z8D+yl1kpyX+bPcvcgFRRlHFjERkLRLn8dBDRlmUkhoBGM2tQZsJBuk/IaH02OkiZB4pwhr8ggGsEUtvhRIZXxOBlxou3e24Sic2UOpNfWv5gG631N6xJTbmjDBTlXf1qFagi9o6VhGTc1c9S0yKTwGVCb5Wtj33E1RwIDAQAB";
    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息（异步回调）
    private String notify_url;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    private String return_url;

    // 签名方式
    private String sign_type = "RSA2";

    // 字符编码格式
    private String charset = "utf-8";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    private String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        System.out.println("支付宝的响应：" + result);

        return result;

    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getMerchant_private_key() {
        return merchant_private_key;
    }

    public void setMerchant_private_key(String merchant_private_key) {
        this.merchant_private_key = merchant_private_key;
    }

    public String getAlipay_public_key() {
        return alipay_public_key;
    }

    public void setAlipay_public_key(String alipay_public_key) {
        this.alipay_public_key = alipay_public_key;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }
}
