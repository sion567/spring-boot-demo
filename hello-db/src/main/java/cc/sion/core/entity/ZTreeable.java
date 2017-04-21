package cc.sion.core.entity;


import java.io.Serializable;

public interface ZTreeable<ID extends Serializable> extends Treeable<ID>{
    @Override
    default String getRootDefaultIcon(){return "ztree_setting";}
    @Override
    default String getBranchDefaultIcon(){return "ztree_folder";}
    @Override
    default String getLeafDefaultIcon(){return "ztree_file";}
}
