package com.threehk.map.repository;

import com.threehk.map.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
@Repository
public interface DataRepository extends JpaRepository<Data,Long> {
    Optional<List<Data>> findDataByCreateDtAfter(Timestamp timestamp);
}
