package cc.sion.core.web;

import cc.sion.core.biz.IBaseBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import static org.springframework.util.StringUtils.uncapitalize;

@Slf4j
public abstract class AdministrationAction<T ,ID extends Serializable> implements BasicAction {
    private final IBaseBiz<T, ID> baseBiz;

    public AdministrationAction(IBaseBiz baseBiz) {
        Assert.notNull(baseBiz, "BusinessImpl must not be null!");
        this.baseBiz = baseBiz;
    }

    @Override
    public IBaseBiz<T, ID> getBaseBiz() {
        return baseBiz;
    }

    @Override
    public Class<T> getDomainType() {
//        //getSuperclass()获得该类的父类
//        //getGenericSuperclass()获得带有泛型的父类
//        //Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
        Type type=getClass().getGenericSuperclass();
//        //ParameterizedType参数化类型，即泛型
        ParameterizedType p=(ParameterizedType)type;
//        //getActualTypeArguments获取参数化类型的数组，泛型可能有多个
        return (Class<T>) p.getActualTypeArguments()[0];
    }

    @Override
    public String getDomainTypeName() {
        return uncapitalize(getDomainType().getSimpleName());
    }


    @RequestMapping(value = "")
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                       @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                       @RequestParam(value = "sortType", defaultValue = "lastUpdateTime") String sortType, Model model,
                       ServletRequest request) throws Exception {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Page<T> pb = getBaseBiz().getByPage(searchParams, pageNumber, pageSize, sortType);
        model.addAttribute("pagebean", pb);
        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        addOtherModel(model);
        return getAction().concat(_list_);
    }


    @RequestMapping(value = "/input/{id}",method = RequestMethod.GET)
    public String input(@PathVariable ID id, Model model)throws Exception{
        if(log.isDebugEnabled())
            log.debug("input from _:{}",id);
        T obj = null;
        if(id!=null && !"0".equals(id) && !"".equals(id) && !"null".equals(id))
            obj = getBaseBiz().getObj(id);
        else
            obj = createInstance(getDomainType());
        model.addAttribute("obj",obj);
        addOtherModel(model);
        return getAction().concat(_input_);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("obj") T obj){
        try {
            preHandle(obj);
            int result = getBaseBiz().save(obj);
            log.info("save result:{}",result);
        } catch (Exception e) {
            log.error("save is error!!!",e);
        }
        return _view_admin_.concat(getAction());
    }

    protected abstract void addOtherModel(Model model)throws Exception;
    protected abstract void preHandle(T obj)throws Exception;

    protected static <T> T createInstance(Class<T> cls){
        T obj = null;
        try{
            obj = cls.newInstance();
        }catch (Exception e){
            obj = null;
        }
        return obj;
    }

}
