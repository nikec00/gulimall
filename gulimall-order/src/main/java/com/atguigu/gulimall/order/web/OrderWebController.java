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
 * @Description：
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
     * 下单功能
     *
     * @param orderSubmitVo
     * @return
     */
    @PostMapping("/submitOrder")
    public String submitOrder(OrderSubmitVo orderSubmitVo, Model model, RedirectAttributes attributes) {
        try {
            //下单：去创建订单，验令牌，验价格，锁库存
            SubmitOrderResponseVo responseVo = orderService.submitOrder(orderSubmitVo);
            //下单成功来到支付选择页
            //下单失败回到订单确认页重新确认订单信息
            System.out.println("订单提交的数据：" + orderSubmitVo);
            if (responseVo.getCode() == 0) {
                model.addAttribute("submitOrderResponseVo", responseVo);
                return "pay";
            } else {
                String msg = "下单失败：";
                switch (responseVo.getCode()) {
                    case 1:
                        msg += "订单信息过期，请刷新再次提交";
                        break;
                    case 2:
                        msg += "订单商品价格发生变化，请确认后再此提交";
                        break;
                    default:
                        msg += "库存锁定失败，商品库存不足";
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
