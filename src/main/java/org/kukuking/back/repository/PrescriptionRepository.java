package org.kukuking.back.repository;

import org.kukuking.back.DO.Prescription;
import org.kukuking.back.extractor.PrescriptionResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription, String> {
    @Query(value = "select * from prescription as p",
            resultSetExtractorClass = PrescriptionResultSetExtractor.class)
    List<Prescription> findAllPrescriptions();

    @Query(value = """
            select * from prescription as p
            where p.to_finish = :toFinish and p.doctor_id = :doctorId""",
            resultSetExtractorClass = PrescriptionResultSetExtractor.class)
    List<Prescription> findAllByToFinish(boolean toFinish, String doctorId);

    @Query(value = """
            select * from prescription as p
            where p.to_finish = :toFinish and p.finish = :finish and p.doctor_id = :doctorId""",
            resultSetExtractorClass = PrescriptionResultSetExtractor.class)
    List<Prescription> findAllByToFinishAndFinishAndDoctorId(boolean toFinish, boolean finish, String doctorId);

    @Query(value = """
            select * from prescription as p
            where p.to_finish = :toFinish and p.finish = :finish and p.patient_id = :patientId""",
            resultSetExtractorClass = PrescriptionResultSetExtractor.class)
    List<Prescription> findAllByToFinishAndFinishAndPatientId(boolean toFinish, boolean finish, String patientId);

    @Query(value = "select * from prescription as p where p.patient_id = :patientId",
            resultSetExtractorClass = PrescriptionResultSetExtractor.class)
    List<Prescription> findAllByPatientId(String patientId);

    @Query(value = "select * from prescription as p where p.doctor_id = :doctorIdId",
            resultSetExtractorClass = PrescriptionResultSetExtractor.class)
    List<Prescription> findAllByDoctorId(String doctorId);

    @Modifying
    @Query(value = "update prescription as p set p.cost = :cost, p.description = :description where p.id = :id")
    void updateById(String id, double cost, String description);

    @Modifying
    @Query(value = "update prescription as p set p.finish = true,p.title = '已清缴' where p.id = :id")
    void finishById(String id);

    @Modifying
    @Query(value = "update prescription as p set p.to_finish = true, p.title = '待缴费' where p.id = :id")
    void toFinishById(String id);
}
