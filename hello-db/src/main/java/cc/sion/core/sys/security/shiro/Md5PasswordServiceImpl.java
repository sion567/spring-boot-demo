package cc.sion.core.sys.security.shiro;

import cc.sion.core.utils.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.stereotype.Component;

@Slf4j
public class Md5PasswordServiceImpl implements PasswordService {
    @Override
    public String encryptPassword(Object o) throws IllegalArgumentException {
        String tmp = Md5Utils.hash(o.toString());
        log.debug("encrypt pwd:{}",tmp);
        return tmp;
    }

    @Override
    public boolean passwordsMatch(Object o, String s) {
        log.debug("p1:{},p2:{}",o,s);
        return encryptPassword(o).equals(s);
    }
}
