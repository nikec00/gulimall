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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.ExecutionException;

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
    public String toTrade(Model model) throws ExecutionException, InterruptedException {
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
    public String submitOrder(OrderSubmitVo orderSubmitVo, Model model, RedirectAttributes attributes) {
        try {
            //�µ���ȥ���������������ƣ���۸������
            SubmitOrderResponseVo responseVo = orderService.submitOrder(orderSubmitVo);
            //�µ��ɹ�����֧��ѡ��ҳ
            //�µ�ʧ�ܻص�����ȷ��ҳ����ȷ�϶�����Ϣ
            System.out.println("�����ύ�����ݣ�" + orderSubmitVo);
            if (responseVo.getCode() == 0) {
                model.addAttribute("submitOrderResponseVo", responseVo);
                return "pay";
            } else {
                String msg = "�µ�ʧ�ܣ�";
                switch (responseVo.getCode()) {
                    case 1:
                        msg += "������Ϣ���ڣ���ˢ���ٴ��ύ";
                        break;
                    case 2:
                        msg += "������Ʒ�۸����仯����ȷ�Ϻ��ٴ��ύ";
                        break;
                    default:
                        msg += "�������ʧ�ܣ���Ʒ��治��";
                        break;
                }
                attributes.addFlashAttribute("msg",msg);
                return "redirect:http://order.gulimall.com/toTrade";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:http://order.gulimall.com/toTrade";
        }

    }

}
