package com.ruoyi.common.mq.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 消息切割器，按照4M大小写个
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-02-05
 */
@Slf4j
public class MsgSplitter implements Iterator<List<Message>> {
    /**
     * 消息最大4M
     */
    private final int SIZE_LIMIT = 1024 * 1024 * 4;
    /**
     * 消息存储
     */
    private final List<Message> messages;
    /**
     * 当前索引
     */
    private int currIndex;

    public MsgSplitter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean hasNext() {
        return currIndex < messages.size();
    }

    @Override
    public List<Message> next() {
        int startIndex = getStartIndex();
        int nextIndex = startIndex;
        int totalSize = 0;
        for (; nextIndex < messages.size(); nextIndex++) {
            Message message = messages.get(nextIndex);
            int tmpSize = calcMessageSize(message);
            if (tmpSize + totalSize > SIZE_LIMIT) {
                break;
            } else {
                totalSize += tmpSize;
            }
        }
        List<Message> subList = messages.subList(startIndex, nextIndex);
        currIndex = nextIndex;
        return subList;
    }

    /**
     * 获取当前消息的开始索引
     *
     * @return 索引
     */
    private int getStartIndex() {
        Message currMessage = messages.get(currIndex);
        int tmpSize = calcMessageSize(currMessage);
        //若单条消息超过4M,直接丢弃掉
        while (tmpSize > SIZE_LIMIT) {
            log.error("单条消息超过4M,直接丢弃掉,消息{}", currMessage.toString());
            currIndex += 1;
            Message message = messages.get(currIndex);
            tmpSize = calcMessageSize(message);
        }
        return currIndex;
    }

    /**
     * 计算消息的大小
     *
     * @param message 消息
     * @return 大小
     */
    private int calcMessageSize(Message message) {
        int tmpSize = message.getTopic().length() + message.getBody().length;
        Map<String, String> properties = message.getProperties();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            tmpSize += entry.getKey().length() + entry.getValue().length();
        }
        // 增加日志的开销20字节
        tmpSize = tmpSize + 20;
        return tmpSize;
    }

}
