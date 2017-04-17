package cc.sion.repository;


import cc.sion.core.repository.BaseRepository;
import cc.sion.domain.Hotel;
import cc.sion.domain.HotelSummary;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

@CacheConfig(cacheNames = "ooxx")
interface HotelRepository extends BaseRepository<Hotel,String> {
    @Cacheable(unless="#result == null")
    Hotel findByName(String name);

    @Query("select h.name as name, avg(r.rating) as averageRating "
            + "from Hotel h left outer join h.reviews r where h.name = ?1 group by h")
    Page<HotelSummary> findByName(String name, Pageable pageable);

}