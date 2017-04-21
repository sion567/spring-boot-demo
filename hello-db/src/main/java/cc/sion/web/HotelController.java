package cc.sion.web;

import cc.sion.common.StateType;
import cc.sion.core.web.AdministrationAction;
import cc.sion.core.web.Servlets;
import cc.sion.domain.Hotel;
import cc.sion.service.HotelService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/hotel")
@Slf4j
public class HotelController {
    private static Map<Integer, String> sType = Maps.newHashMap();
    static {
        for (StateType e : StateType.values()) {
            sType.put(e.getFlag(),e.getContent());
        }
    }

    @Autowired
    private HotelService hotelService;

    @RequiresRoles("")
    @RequestMapping(value = "")
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                       @RequestParam(value = "page.size", defaultValue =  "10") int pageSize,
                       @RequestParam(value = "sortType", defaultValue = "lastUpdateTime") String sortType, Model model,
                       ServletRequest request) throws Exception {
//        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
//        Page<T> pb = hotelService.getByPage(searchParams, pageNumber, pageSize, sortType);
//        model.addAttribute("pagebean", pb);
//        // 将搜索条件编码成字符串，用于排序，分页的URL
//        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
//        model.addAttribute("s",sType);
        return "hotel/list";
    }

    @RequiresRoles("")
    @GetMapping("/input/{id}")
    public String input(@PathVariable Long id, Model model)throws Exception{
//        if(log.isDebugEnabled())
//            log.debug("input from _:{}",id);
//        Hotel obj = null;
//        if(id!=null && id!=0L)
//            obj = hotelService.getObj(id);
//        else
//            obj = new Hotel();
//        model.addAttribute("obj",obj);
//        model.addAttribute("s",sType);
        return "hotel/input";
    }

    @RequiresRoles("")
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("obj") Hotel obj){
//        try {
//            Hotel result = hotelService
//            log.info("save result:{}",result);
//        } catch (Exception e) {
//            log.error("save is error!!!",e);
//        }
        return "redirect:/hotel";
    }

    @RequiresRoles("")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model)throws Exception{
        return "redirect:/hotel";
    }

}