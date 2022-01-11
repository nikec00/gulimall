package com.atguigu.gulimall.order.web;

import com.atguigu.gulimall.order.service.OrderService;
import com.atguigu.gulimall.order.vo.OrderConfirmVo;
import com.atguigu.gulimall.order.vo.OrderSubmitVo;
import com.atguigu.gulimall.order.vo.SubmitOrderResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description��
 * @Author: nkc
 * @Date: 2022/1/7 23:27
 */
@Controller
public class OrderWebController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/toTrade")
    public String toTrade(Model model) {
        OrderConfirmVo confirmVo = orderService.confirmOrder();
        model.addAttribute("orderConfirm", confirmVo);
        return "confirm";
    }

    /**
     * �µ�����
     *
     * @param orderSubmitVo
     * @return
     */
    @PostMapping("/submitOrder")
    public String submitOrder(OrderSubmitVo orderSubmitVo) {
        //�µ���ȥ���������������ƣ���۸������
        SubmitOrderResponseVo responseVo = orderService.submitOrder(orderSubmitVo);
        //�µ��ɹ�����֧��ѡ��ҳ
        //�µ�ʧ�ܻص�����ȷ��ҳ����ȷ�϶�����Ϣ
        System.out.println("�����ύ�����ݣ�" + orderSubmitVo);
        if (responseVo.getCode() == 0) {
            return "pay";
        } else {
            return "redirect:http://order.gulimall.com/toTrade";
        }

    }

}
