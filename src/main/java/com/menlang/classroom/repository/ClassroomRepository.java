package com.menlang.classroom.repository;
import com.menlang.classroom.model.entities.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassRoom,Long>, JpaSpecificationExecutor<ClassRoom> {

    @Query("""
            SELECT CASE WHEN COUNT(c)>0 THEN true ELSE false END FROM ClassRoom c WHERE c.name=?1 and c.id!=?2
            """)
    Boolean findClassroomByNameAndIdNotIn(String classroom,Long id);
}
