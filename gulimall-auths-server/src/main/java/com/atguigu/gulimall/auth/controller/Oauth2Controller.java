package com.atguigu.gulimall.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atguigu.common.utils.HttpUtils;
import com.atguigu.common.utils.R;
import com.atguigu.common.vo.MemberRespVo;
import com.atguigu.common.vo.SocialUser;
import com.atguigu.gulimall.auth.feign.MemberFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description：处理社交登录请求回调
 * @Author: nkc
 * @Date: 2021/12/29 21:20
 */
@Controller
@Slf4j
public class Oauth2Controller {

    @Autowired
    private MemberFeignClient memberFeignClient;

    @GetMapping("/oauth2.0/weibo/success")
    public String weibo(@RequestParam("code") String code, HttpSession session) throws Exception {
        //1.根据code换取access_token
        Map<String, String> map = new HashMap<>();
        map.put("client_id", "2360416674");
        map.put("client_secret", "a5bb17642e5d307f283734b6b9714e26");
        map.put("grant_type", "authorization_code");
        map.put("redirect_uri", "http://auth.gulimall.com/oauth2.0/weibo/success");
        map.put("code", code);
        HttpResponse response = HttpUtils
                .doPost("https://api.weibo.com", "/oauth2/access_token", "post", new HashMap<>(), map, new HashMap<>());        //2.处理
        if (response.getStatusLine().getStatusCode() == 200) {
            // 获取到access_token
            String json = EntityUtils.toString(response.getEntity());
            SocialUser socialUser = JSON.parseObject(json, SocialUser.class);
            R login = memberFeignClient.oauthLogin(socialUser);
            if (login.getCode() == 0) {
                MemberRespVo respVo = login.getData(new TypeReference<MemberRespVo>() {
                });
                System.out.println("登录成功，用户信息：" + respVo.toString());
                log.info("登录成功，用户信息{}", respVo.toString());
                //1.第一次使用session：命令浏览器保存JSESSIONID这个cookie
                //以后浏览器访问哪个网站就会带上这个网站的cookie
                //子域之间：gulimall.com auth.gulimall.com...
                //发卡的时候，即使是子域系统发的卡，也能让父类直接使用
                session.setAttribute("loginUser", respVo);
                //3.登录成功跳回首页
                return "redirect:http://gulimall.com";
            } else {
                return "redirect:http://auth.gulimall.com/login.html";
            }
        } else {
            return "redirect:http://auth.gulimall.com/login.html";
        }
    }
}
