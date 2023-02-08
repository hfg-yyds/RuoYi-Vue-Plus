package com.ruoyi.common.mq;

import com.ruoyi.common.mq.config.RocketProperties;
import com.ruoyi.common.mq.enums.ProducerEnum;
import com.ruoyi.common.mq.manager.ProducerManager;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * <p>
 *  生产者抽象类
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-02-03
 */
public abstract class AbstractProducer<T> implements IProducer<T> {

    protected abstract ProducerEnum getProducerCode();

    @Resource
    private RocketProperties rocketProperties;

    @Resource
    private ProducerManager producerManager;
    protected DefaultMQProducer producer;
    private void buildProducer() {
        try {
            //构造参数
            producer = new DefaultMQProducer("please_rename_unique_group_name");
            // 设置NameServer的地址
            producer.setNamesrvAddr("localhost:9876");
            //
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void register() {
        //构建生产者
        buildProducer();
        producerManager.registerProducer(getProducerCode(),null);
    }

}