package cc.sion.core.web;

import cc.sion.core.biz.IBaseBiz;
import cc.sion.core.utils.Reflections;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
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
    private String viewPrefix;

    public AdministrationAction(IBaseBiz baseBiz) {
        Assert.notNull(baseBiz, "BusinessImpl must not be null!");
        this.baseBiz = baseBiz;
        setViewPrefix(defaultViewPrefix());
    }

    @Override
    public IBaseBiz<T, ID> getBaseBiz() {
        return baseBiz;
    }

    @Override
    public Class<T> getDomainType() {
        return Reflections.findParameterizedType(getClass(), 0);
    }
    @Override
    public String getDomainTypeName() {
        return uncapitalize(getDomainType().getSimpleName());
    }
    @Override
    public String defaultViewPrefix() {
        String currentViewPrefix = "";
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(getClass(), RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            currentViewPrefix = requestMapping.value()[0];
        }
        if (StringUtils.isEmpty(currentViewPrefix)) {
            currentViewPrefix = getDomainTypeName();
        }
        return currentViewPrefix;
    }

    /**
     * 当前模块 视图的前缀
     * 默认
     * 1、获取当前类头上的@RequestMapping中的value作为前缀
     * 2、如果没有就使用当前模型小写的简单类名
     */
    public void setViewPrefix(String viewPrefix) {
        if (viewPrefix.startsWith("/")) {
            viewPrefix = viewPrefix.substring(1);
        }
        this.viewPrefix = viewPrefix;
    }
    public String getViewPrefix() {
        return viewPrefix;
    }
    /**
     * 获取视图名称：即prefixViewName + "/" + suffixName
     * @return
     */
    public String viewName(String suffixName) {
        if (!suffixName.startsWith("/")) {
            suffixName = "/" + suffixName;
        }
        return getViewPrefix() + suffixName;
    }
    /**
     * @param backURL null 将重定向到默认getViewPrefix()
     * @return
     */
    protected String redirectToUrl(String backURL) {
        if (StringUtils.isEmpty(backURL)) {
            backURL = getViewPrefix();
        }
        if (!backURL.startsWith("/") && !backURL.startsWith("http")) {
            backURL = "/" + backURL;
        }
        return "redirect:" + backURL;
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
            obj = newModel(getDomainType());
        model.addAttribute("obj",obj);
        addOtherModel(model);
        return getAction().concat(_input_);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("obj") T obj){
        try {
            preHandle(obj);
            T result = getBaseBiz().save(obj);
            log.info("save result:{}",result);
        } catch (Exception e) {
            log.error("save is error!!!",e);
        }
        return _view_admin_.concat(getAction());
    }

    protected abstract void addOtherModel(Model model)throws Exception;
    protected abstract void preHandle(T obj)throws Exception;

    protected static <T> T newModel(Class<T> cls){
        T obj = null;
        try{
            obj = cls.newInstance();
        }catch (Exception e){
            obj = null;
        }
        return obj;
    }

}
