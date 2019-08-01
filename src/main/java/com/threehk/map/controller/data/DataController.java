package com.threehk.map.controller.data;

import com.threehk.map.service.scout.IScoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class DataController {
    private final IScoutService iScoutService;

    @Autowired
    public DataController(IScoutService iScoutService) {
        this.iScoutService = iScoutService;
    }

    @RequestMapping("/selectAllWithin")
    public List<Map<String,Object>> selectAllWithin(@RequestParam(value = "minute")int minute){
        return iScoutService.selectAllWithin(minute);
    }
}
