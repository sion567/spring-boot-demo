package cc.sion.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/login")
@Slf4j
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username, HttpServletRequest request, Model model) {
        log.info("{} login fail....", username);
        String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        log.info(" login fail reason:{}", error);
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
        model.addAttribute("error", error);
        return "login";
    }

}