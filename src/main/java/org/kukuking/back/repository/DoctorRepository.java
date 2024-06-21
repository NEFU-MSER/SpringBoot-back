package org.kukuking.back.repository;

import org.kukuking.back.DO.Doctor;
import org.kukuking.back.extractor.DoctorResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DoctorRepository extends CrudRepository<Doctor, String> {
    @Query(value = "select * from doctor as d",
            resultSetExtractorClass = DoctorResultSetExtractor.class)
    List<Doctor> findAllDoctors();
}
