package cc.sion.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class WelcomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String gotoWel() {
        return "redirect:/admin/wel";
    }

    @RequestMapping(value = "/admin/wel", method = RequestMethod.GET)
    public String wel() {
        return "main";
    }

}
