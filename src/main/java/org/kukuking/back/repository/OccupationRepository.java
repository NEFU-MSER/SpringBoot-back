package org.kukuking.back.repository;

import org.kukuking.back.DO.Occupation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OccupationRepository extends CrudRepository<Occupation, String> {

    void deleteByCourseId(String courseId);

    void deleteByLibId(String libId);
}
