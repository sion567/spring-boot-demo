package cc.sion.core.web;

import cc.sion.core.biz.IBaseBiz;

public interface BasicAction {

    String _input_ = "/input";
    String _list_ = "/list";
    String _view_admin_ = "redirect:/admin/";

    String PAGE_SIZE = "10";

    String getAction();

    Class<?> getDomainType();

    String getDomainTypeName();

    IBaseBiz<?, ?> getBaseBiz();

    String defaultViewPrefix();

//    T getObj(ID id);  不能用?
//    List<?> findAll();  可以用?
}
