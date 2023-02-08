package com.ruoyi.common.mq.producer;

import com.ruoyi.common.mq.AbstractProducer;
import com.ruoyi.common.mq.IProducer;
import com.ruoyi.common.mq.enums.ProducerEnum;
import com.ruoyi.common.mq.manager.ProducerManager;
import com.ruoyi.common.mq.msg.TestMsg;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 *
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-02-03
 */
@Component
public class ParamProducer extends AbstractProducer<TestMsg> {

    @Override
    protected ProducerEnum getProducerCode() {
        return ProducerEnum.TEST;
    }

    @Override
    public void send(TestMsg message) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        for (int i = 0; i < 100; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("TopicTest", "TagA",
                    message.toString().getBytes(StandardCharsets.UTF_8));
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg);
            // 通过sendResult返回消息是否成功送达
            if (!sendResult.getSendStatus().equals(SendStatus.SEND_OK)) {
                throw new RuntimeException("发送失败");
            }
//            System.out.printf("%s%n", sendResult);
        }
    }

}
