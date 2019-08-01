package com.threehk.map.controller.scout;

import com.threehk.map.model.Data;
import com.threehk.map.service.scout.IScoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scout")
public class ScoutRestController {
    private final IScoutService iScoutService;

    @Autowired
    public ScoutRestController(IScoutService iScoutService) {
        this.iScoutService = iScoutService;
    }

    @RequestMapping("/input")
    public boolean input(HttpServletRequest request,@RequestParam(value = "dataType")long dataType,
                      @RequestParam(value = "amount")long amount,
                      @RequestParam(value = "latitude")double latitude,
                      @RequestParam(value = "longitude")double longitude){
        String username = "hello world";
        return iScoutService.input(username,dataType, amount, latitude, longitude);
    }

    @RequestMapping("/selectAllWithin")
    public List<Map<String,Object>> selectAllWithin(@RequestParam(value = "minute")int minute){
        return iScoutService.selectAllWithin(minute);
    }
}
