package com.ruoyi.common.mq;

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
    void send(T message) throws UnsupportedEncodingException;

}
