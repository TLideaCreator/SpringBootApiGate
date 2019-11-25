package org.idea.creator.api.result.data;

import lombok.Data;
import org.idea.creator.api.result.meta.BaseMeta;


/**
 * 集合数据类型
 * @author lqh
 */
@Data
public class ItemData<T> {
    private T item;
    private BaseMeta meta;
}
