package com.threehk.map.service.scout;

import com.threehk.map.dao.DataDAO;
import com.threehk.map.model.Data;
import com.threehk.map.model.DataType;
import com.threehk.map.repository.DataRepository;
import com.threehk.map.repository.DataTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoutService implements IScoutService{
    private final DataRepository dataRepository;
    private final DataTypeRepository dataTypeRepository;
    private final DataDAO dataDAO;

    @Autowired
    public ScoutService(DataRepository dataRepository, DataTypeRepository dataTypeRepository, DataDAO dataDAO) {
        this.dataRepository = dataRepository;
        this.dataTypeRepository = dataTypeRepository;
        this.dataDAO = dataDAO;
    }

    @Override
    public boolean input(String username, long dataType, long amount, double latitude, double longitude) {
        Data data = new Data();
        data.setUsername(username);
        data.setCreateDt(new Timestamp(System.currentTimeMillis()));
        data.setAmount(amount);
        data.setLatitude(latitude);
        data.setLongitude(longitude);
        data.setDataType(dataTypeRepository.getOne(dataType));
        dataRepository.save(data);
        return false;
    }

    @Override
    public List<Data> select() {
        return dataRepository.findAll();
    }

    @Override
    public List<Map<String,Object>> selectAllWithin(int minute) {
        try{
            List<Data> datas = dataDAO.selectAllWithin(minute);
            List<Map<String,Object>> result = new ArrayList<>();
            for(Data data : datas){
                Map<String,Object> temp = new HashMap<>();
                temp.put("createDt",data.getCreateDt());
                temp.put("amount",data.getAmount());
                temp.put("latitude",data.getLatitude());
                temp.put("longitude",data.getLongitude());
                temp.put("dataType",data.getDataType().getName());
                result.add(temp);
            }
            return result;
        }catch (Exception e){
            return null;
        }
    }
}
