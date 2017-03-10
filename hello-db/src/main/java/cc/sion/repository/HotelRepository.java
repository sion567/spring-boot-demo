package cc.sion.repository;

import java.util.List;


import cc.sion.domain.Hotel;
import cc.sion.domain.HotelSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

interface HotelRepository extends Repository<Hotel, Long> {

    Hotel findByName(String name);

    @Query("select h.name as name, avg(r.rating) as averageRating "
            + "from Hotel h left outer join h.reviews r where h.name = ?1 group by h")
    Page<HotelSummary> findByName(String name, Pageable pageable);

}