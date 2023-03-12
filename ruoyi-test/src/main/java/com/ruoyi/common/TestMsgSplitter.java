package com.ruoyi.common;

import com.ruoyi.common.mq.util.MsgSplitter;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-02-05
 */
public class TestMsgSplitter {

    @Test
    public void test1() {
        //===========准备消息==========================================================
        List<Message> messages = new ArrayList<>();

        for (long i = 0; i < 1000000; i++) {
            //添加内容
            byte[] bytes = ("批量消息".getBytes(StandardCharsets.UTF_8));
            Message message = new Message("topic-order-batch", "product-order-batch", bytes);
            message.setKeys("key-" + i);
            messages.add(message);
        }
        //===========切割消息==========================================================

        //把大的消息分裂成若干个小的消息
        MsgSplitter splitter = new MsgSplitter(messages);

        while (splitter.hasNext()) {
            try {
                //安装4m切割消息
                List<Message> listItem = splitter.next();
                System.out.println(listItem);
            } catch (Exception e) {
                e.printStackTrace();
                //处理error
            }
        }
    }

}
