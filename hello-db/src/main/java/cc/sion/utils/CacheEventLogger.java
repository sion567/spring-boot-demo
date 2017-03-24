package cc.sion.utils;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Slf4j
public class CacheEventLogger implements CacheEventListener<Object, Object> {

    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object> event) {
        log.debug("Event:{} , Key:{} , old vlaue:{} , new value:{}" , event.getType(), event.getKey(),event.getOldValue(),event.getNewValue());
    }

}
