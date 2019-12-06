package io.github.tlideacreator.api.result.data;

import io.github.tlideacreator.api.result.meta.BaseMeta;
import lombok.Data;


/**
 * 集合数据类型
 * @author lqh
 */
@Data
public class ItemData<T> {
    private T item;
    private BaseMeta meta;
}
