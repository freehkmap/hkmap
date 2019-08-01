package com.threehk.map.dao;

import com.threehk.map.model.Data;
import com.threehk.map.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class DataDAO {
    private final DataRepository dataRepository;

    @Autowired
    public DataDAO(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public List<Data> selectAllWithin(int minute)throws Exception{
        List<Data> data = dataRepository.findDataByCreateDtAfter(new Timestamp(System.currentTimeMillis()-(minute*60*1000))).orElseThrow(()->new Exception(""));
        return data;
    }
}
