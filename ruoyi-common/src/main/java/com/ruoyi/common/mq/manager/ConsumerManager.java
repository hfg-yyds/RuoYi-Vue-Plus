package com.ruoyi.common.mq.manager;

import com.ruoyi.common.mq.IConsumer;
import com.ruoyi.common.mq.enums.ConsumerEnum;

import java.util.Map;

/**
 * <p>
 *  消费者管理器
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-02-04
 */
public class ConsumerManager {

    private Map<ConsumerEnum, IConsumer> consumerMap;

    public void registerProducer(ConsumerEnum consumerEnum, IConsumer iConsumer) {
        consumerMap.put(consumerEnum,iConsumer);
    }

    public Map<ConsumerEnum, IConsumer> getProduces() {
        return consumerMap;
    }

}
