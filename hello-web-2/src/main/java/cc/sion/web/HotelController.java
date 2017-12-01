package cc.sion.web;

import cc.sion.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/admin/hotel")
@Slf4j
public class HotelController {


    @Autowired
    private HotelService hotelService;



}