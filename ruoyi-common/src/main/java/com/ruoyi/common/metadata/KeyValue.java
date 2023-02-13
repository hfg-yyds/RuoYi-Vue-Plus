package com.ruoyi.common.metadata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *  键值对使用
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-02-12
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KeyValue<K,V> {
    /**
     * Key值
     */
    private String key;
    /**
     * value值
     */
    private String value;

}
