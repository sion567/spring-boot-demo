package cc.sion.biz;


import cc.sion.core.biz.BaseBizImpl;
import cc.sion.domain.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class HotelBizImpl extends BaseBizImpl<Hotel,String> implements IHotelBiz {
}
