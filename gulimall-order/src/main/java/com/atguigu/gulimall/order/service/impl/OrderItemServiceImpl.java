package com.atguigu.gulimall.order.service.impl;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.entity.OrderReturnReasonEntity;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.order.dao.OrderItemDao;
import com.atguigu.gulimall.order.entity.OrderItemEntity;
import com.atguigu.gulimall.order.service.OrderItemService;

@RabbitListener(queues = {"hello-java-queue"})//类+方法上（监听哪些队列即可）
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderItemEntity> page = this.page(
                new Query<OrderItemEntity>().getPage(params),
                new QueryWrapper<OrderItemEntity>()
        );

        return new PageUtils(page);
    }

    @RabbitHandler
    public void receiveMessage(Message message, OrderReturnReasonEntity content, Channel channel) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            if (deliveryTag % 2 == 0) {
                channel.basicAck(deliveryTag, false);
                System.out.println("签收了货物：=》" + deliveryTag);
            } else {
                channel.basicNack(deliveryTag,false,true);
                System.out.println("拒绝签收货物：=》" + deliveryTag);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("OrderReturnReasonEntity接受到消息。。。内容：" + content + "==>类型：" + content.getClass());
    }

    @RabbitHandler//标注在方法上（重载区分不同的消息）
    public void receiveMessage2(Message message, OrderEntity content, Channel channel) {
        System.out.println();
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println(deliveryTag);
        try {
            if (deliveryTag % 2 == 0) {
                channel.basicAck(deliveryTag, false);
                System.out.println("签收了货物：=》" + deliveryTag);
            } else {
                System.out.println("拒绝签收货物：=》" + deliveryTag);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("OrderEntity接受到消息。。。内容：" + content + "==>类型：" + content.getClass());
    }
}