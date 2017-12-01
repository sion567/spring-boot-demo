package cc.sion.web;

import cc.sion.biz.GroupBiz;
import cc.sion.biz.IGroupBiz;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    @Autowired
//    IGroupBiz groupBiz;
    GroupBiz groupBiz;

    @ApiOperation(value="say hello",notes = "")
    @RequestMapping(value = "get",method=RequestMethod.GET)
//    @GetMapping("get")
    public String home() {
        return "Hello World!";
    }

    @ApiOperation(value="a+b", notes="加法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "aaaa", value = "小a", required = true, dataType = "int"),
            @ApiImplicitParam(name = "bbbb", value = "小b", required = true, dataType = "int")
    })
    @PostMapping("add")
//    @GetMapping("add")
    public int add(@RequestParam(value = "aaaa") int aaaa, @RequestParam(value = "bbbb") int bbbb) {
        return aaaa+bbbb;
    }

    @ApiOperation(value="x+10", notes="加法")
    @ApiImplicitParam(name = "x", value = "x", required = true, dataType = "int")
    @PostMapping("add10")
    public int add(@RequestBody int x) {
        return x+10;
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}