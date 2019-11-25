package org.idea.creator.api.result.data;

import lombok.Data;
import org.idea.creator.api.result.meta.BaseMeta;

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
