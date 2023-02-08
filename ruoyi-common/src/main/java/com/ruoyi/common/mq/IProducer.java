package com.ruoyi.common.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 *
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-02-03
 */
public interface IProducer<T> {

    /**
     * 发送消息
     * @param message
     */
    void send(T message) throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException;

}
