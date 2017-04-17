package cc.sion.core.biz;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface IBaseBiz<T,ID extends Serializable> {
    T save(T t);
    T getObj(ID id);
    boolean exists(ID id);
    long count();
    long count(Map<String, Object> searchParams);
    int delete(ID id);
    int delete(ID[] ids);
    List<T> findAll();
    List<T> findAll(Map<String, Object> searchParams);
    List<T> findAll(Map<String, Object> searchParams,String... sortType);

    //FIXME:排序字段问题
    Page<T> getByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType);
}
