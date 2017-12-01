package cc.sion.core.sys.security;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LoginCacheManager {
    private static LoadingCache<String, Integer> cache = CacheBuilder
            .newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(new CacheLoader<String, Integer>(){
                @Override
                public Integer load(String key) throws Exception {
                    return 1;
                }
            });

    public static void setCache(String k,Integer v){
        cache.put(k, v);
    }
    public static void removeCache(String k) {
        cache.invalidate(k);
    }
    public static Integer getCache(String k) {
        try {
            return cache.get(k);
        } catch (ExecutionException e) {
            log.warn("get cache error!",e);
            return 9999;
        }
    }
}
