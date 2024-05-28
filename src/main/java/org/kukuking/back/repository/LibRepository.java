package org.kukuking.back.repository;

import org.kukuking.back.DO.Lib;
import org.kukuking.back.mapper.LibResultSetExtractor;
import org.kukuking.back.mapper.LibRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibRepository extends CrudRepository<Lib, String> {
    @Query(value = "select * from lib as l where l.id = :id",
            rowMapperClass = LibRowMapper.class)
    Lib findLibById(String id);

    @Query(value = "select * from lib as l where l.type = :type",
            resultSetExtractorClass = LibResultSetExtractor.class)
    List<Lib> findLibsByType(String type);

    @Query(value = "select * from lib",
    resultSetExtractorClass = LibResultSetExtractor.class)
    List<Lib> findLibs();
}
