package io.github.tlideacreator.api.result.data;

import io.github.tlideacreator.api.result.meta.BaseMeta;
import lombok.Data;

import java.util.List;

/**
 * 集合数据类型
 * @author lqh
 */
@Data
public class CollectionData<T> {
    private List<T> list;
    private BaseMeta meta;
}
