package io.github.tlideacreator.api.result.meta;

import lombok.Data;

import java.util.Objects;

/**
 * 翻页扩展数据
 * @author lqh
 */
@Data
public class PageMeta extends BaseMeta {
    private int total;
    private int page;
    private int perPage;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PageMeta)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PageMeta pageMeta = (PageMeta) o;
        return getTotal() == pageMeta.getTotal() &&
                getPage() == pageMeta.getPage() &&
                getPerPage() == pageMeta.getPerPage();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTotal(), getPage(), getPerPage());
    }
}
