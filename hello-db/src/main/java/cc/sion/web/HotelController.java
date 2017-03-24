package cc.sion.web;

import cc.sion.biz.IHotelBiz;
import cc.sion.common.StateType;
import cc.sion.core.web.AdministrationAction;
import cc.sion.domain.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/hotel")
@Slf4j
public class HotelController extends AdministrationAction<Hotel,String> {

    private static Map<Integer, String> sType = new HashMap<Integer, String>();
    static {
        for (StateType e : StateType.values()) {
            sType.put(e.getFlag(),e.getContent());
        }
    }

    private IHotelBiz hotelBiz;
    @Autowired
    public HotelController(IHotelBiz hotelBiz) {
        super(hotelBiz);
        this.hotelBiz = hotelBiz;
    }


    @Override
    protected void addOtherModel(Model model) {
        model.addAttribute("s", sType);
    }

    @Override
    protected void preHandle(Hotel hotel) { }

    @Override
    public String getAction() {
        return "hotel";
    }

}