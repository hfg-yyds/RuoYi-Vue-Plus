package com.ruoyi.common.mq;

import com.ruoyi.common.mq.config.RocketProperties;
import com.ruoyi.common.mq.enums.ConsumerEnum;
import com.ruoyi.common.mq.manager.ConsumerManager;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 *  抽象消费者,所有消费者都需要集成该类
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-02-04
 */
public abstract class AbstractConsumer implements IConsumer {

    protected abstract ConsumerEnum getConsumerCode();

    protected abstract IConsumer getConsumer();

    protected DefaultMQPushConsumer consumer;

    @Resource
    private RocketProperties rocketProperties;

    @Resource
    private ConsumerManager consumerManager;

    private void buildConsumer() {

    }


    @PostConstruct
    public void register() {
        //构建生产者
        buildConsumer();
        consumerManager.registerProducer(getConsumerCode(),getConsumer());
    }

}
