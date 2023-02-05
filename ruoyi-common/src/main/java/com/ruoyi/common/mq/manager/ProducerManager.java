package com.ruoyi.common.mq.manager;

import com.ruoyi.common.mq.IProducer;
import com.ruoyi.common.mq.enums.ProducerEnum;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 *  生产者管理
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-02-03
 */
@Component
public class ProducerManager {


    private Map<ProducerEnum, IProducer> producerMap;

    public void registerProducer(ProducerEnum producer,IProducer iProducer) {
        producerMap.put(producer,iProducer);
    }

    public Map<ProducerEnum, IProducer> getProduces() {
        return producerMap;
    }

}
