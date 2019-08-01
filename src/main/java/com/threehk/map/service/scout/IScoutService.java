package com.threehk.map.service.scout;

import com.threehk.map.model.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface IScoutService {
    boolean input(String username,long dataType, long amount, double latitude, double longitude);
    List<Data> select();
    List<Map<String,Object>> selectAllWithin(int minute);
}
