package cc.sion.core.biz;

import cc.sion.core.persistence.DynamicSpecifications;
import cc.sion.core.persistence.SearchFilter;
import cc.sion.core.repository.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;


@Slf4j
public abstract class BaseBizImpl<T,ID extends Serializable> implements IBaseBiz<T,ID> {

    @Autowired
    protected BaseRepository<T,ID> repository;

    @Override
    public int save(T t) {
        try {
            repository.save(t);
            return 1;
        }catch (Exception e){
            log.error("save obj is error!!!",e);
            return 0;
        }
    }

    /**
     * 按照主键查询
     *
     * @param id 主键
     * @return 返回id对应的实体
     */
    @Override
    public T getObj(ID id) {
        return repository.findOne(id);
    }

    /**
     * 实体是否存在
     *
     * @param id 主键
     * @return 存在 返回true，否则false
     */
    @Override
    public boolean exists(ID id) {
        return repository.exists(id);
    }

    public int delete(ID id) {
//        try {
//            repository.deleteById(id);
//            return 1;
//        }catch (Exception e){
//            log.error("delete obj is error!!!",e);
//            return 0;
//        }
        return -1;
    }


    @Override
    public Page<T> getByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType){
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<T> spec = buildSpecification(searchParams);

        return repository.findAll(spec, pageRequest);
    }

    /**
     * 创建分页请求.
     */
    protected PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
        Sort sort = null;
        if ("auto".equals(sortType)) {
            sort = new Sort(Sort.Direction.DESC, "id");
        }

        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }

    /**
     * 创建动态查询条件组合.
     */
    protected Specification<T> buildSpecification(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("deleteState", new SearchFilter("deleteState", SearchFilter.Operator.EQ, 0));

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        Specification<T> spec = DynamicSpecifications.bySearchFilter(filters.values(), (Class) params[0]);
        return spec;
    }
}
