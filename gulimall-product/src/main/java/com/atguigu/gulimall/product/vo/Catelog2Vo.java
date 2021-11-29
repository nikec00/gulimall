package com.atguigu.gulimall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/11/29 14:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Catelog2Vo {

    private String catalog1Id; // 一级父分类

    private List<Catelog2Vo.Catelog3Vo> catalog3List; // 三级子分类

    private String id;

    private String name;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Catelog3Vo {

        private String catalog2Id;

        private String id;

        private String name;
    }

}
