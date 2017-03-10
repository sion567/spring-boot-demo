package cc.sion.repository;

import cc.sion.domain.Hotel;
import cc.sion.domain.HotelSummary;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelRepositoryTests {
    @Autowired
    HotelRepository repository;

    @Test
    public void exeQuery() {
        Page<HotelSummary> hotels = this.repository.findByName("The Langham",
                new PageRequest(0, 10, Direction.ASC, "name"));

        Hotel hotel = this.repository.findByName(hotels.getContent().get(0).getName());

        assertThat(hotel.getAddress()).isEqualTo("1 Southgate Ave, Southbank");
    }
}
