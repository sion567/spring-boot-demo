package cc.sion.core.biz;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.Map;


public interface IBaseBiz<T,ID extends Serializable> {
    int save(T t);
    T getObj(ID id);
    boolean exists(ID id);
    int delete(ID id);
    Page<T> getByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType);
}
