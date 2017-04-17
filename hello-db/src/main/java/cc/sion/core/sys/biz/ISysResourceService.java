package cc.sion.core.sys.biz;


import cc.sion.core.biz.IBaseBiz;
import cc.sion.core.sys.domain.SysResource;

public interface ISysResourceService extends IBaseBiz<SysResource,String> {
    /**
     * 得到真实的资源标识  即 父亲:儿子
     * @param resource
     * @return
     */
    String findActualResourceIdentity(SysResource resource);

}
