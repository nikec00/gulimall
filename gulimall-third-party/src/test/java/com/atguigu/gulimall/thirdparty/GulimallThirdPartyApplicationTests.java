package com.atguigu.gulimall.thirdparty;


import com.aliyun.oss.OSSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GulimallThirdPartyApplicationTests {

    @Autowired
    OSSClient ossClient;
    @Test
    public void contextLoads() throws FileNotFoundException {

        FileInputStream inputStream = new FileInputStream("C:\\Users\\X1 Carbon\\Pictures\\logo.jpg");
        ossClient.putObject("gulimall-nkc001","logo.jpg",inputStream);
        ossClient.shutdown();
        System.out.println("上传完成");
    }

}
