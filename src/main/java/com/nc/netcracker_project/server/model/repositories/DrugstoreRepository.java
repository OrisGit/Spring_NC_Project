package com.nc.netcracker_project.server.model.repositories;

import com.nc.netcracker_project.server.model.entities.DrugstoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface DrugstoreRepository extends JpaRepository<DrugstoreEntity, UUID> {

    @Query(value =  "SELECT d " +
                    "FROM DrugstoreEntity d " +
                    "WHERE lower(d.name) LIKE concat('%', lower(coalesce(?1, d.name)),'%') " +
                    "AND lower(d.district) LIKE concat('%', lower(coalesce(?2, d.district)),'%') " +
                    "AND lower(d.street) LIKE concat('%', lower(coalesce(?3, d.street)),'%') " +
                    "AND d.isRoundTheClock BETWEEN ?4 AND 1")
    Page<DrugstoreEntity> findByParameters(String name, String district, String street, Short isRoundTheClock,
                                           Pageable pageable);
}
