package org.kukuking.back.repository;

import org.kukuking.back.DO.PatientCard;
import org.kukuking.back.extractor.PatientCardResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PatientCardRepository extends CrudRepository<PatientCard, String> {
    @Query(value = "select * from patient_card as pc",
            resultSetExtractorClass = PatientCardResultSetExtractor.class)
    List<PatientCard> findAllPatientCards();

    @Query(value = "select * from patient_card as pc where patient_id = :patientId",
            resultSetExtractorClass = PatientCardResultSetExtractor.class)
    List<PatientCard> findAllByPatientId(String patientId);

    @Modifying
    @Query(value = "update patient_card as pc set pc.enable = true where pc.id = :id")
    void enablePatientCard(String id);

    @Modifying
    @Query(value = "update patient_card as pc set pc.enable = false where pc.id = :id")
    void disablePatientCard(String id);

    @Modifying
    @Query(value = "update patient_card as pc set pc.balance = pc.balance + :execute where pc.id = :id")
    void executeBalance(String id, double execute);
}
