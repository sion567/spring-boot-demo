package cc.sion;

import cc.sion.core.sys.domain.SysResource;

/**
 * Created by h2g on 2017/4/14.
 */
public class Test {
    public static void main(String[] args){
        SysResource r1 = new SysResource();
        System.out.println(r1.getRootDefaultIcon());
        D d = new D();
        d.setFlag(100);
        System.out.println(d.getFlag());
    }
}
