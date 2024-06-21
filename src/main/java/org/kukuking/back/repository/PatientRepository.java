package org.kukuking.back.repository;

import org.kukuking.back.DO.Patient;
import org.kukuking.back.extractor.PatientResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PatientRepository extends CrudRepository<Patient, String> {
    @Query(value = "select * from patient as p",
            resultSetExtractorClass = PatientResultSetExtractor.class)
    List<Patient> findAllPatients();
}
