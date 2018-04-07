package com.nc.netcracker_project.server.model.repositories;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import com.nc.netcracker_project.server.model.entities.PharmTerGroupEntity;
import com.nc.netcracker_project.server.model.entities.ProducerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DrugRepository extends JpaRepository<DrugEntity, UUID> {

	List<DrugEntity> findByProducer(ProducerEntity producer);

	Page<DrugEntity> findByProducer(ProducerEntity producer, Pageable pageable);

	List<DrugEntity> findByPharmTerGroup(PharmTerGroupEntity pharmTerGroup);

	Page<DrugEntity> findByPharmTerGroup(PharmTerGroupEntity pharmTerGroup, Pageable pageable);

    @Query(value =  "SELECT d " +
                    "FROM DrugEntity d " +
                    "WHERE lower(d.name) LIKE concat('%', lower(coalesce(?1, d.name)),'%') " +
                    "AND lower(d.releaseForm) LIKE concat('%', lower(coalesce(?2, d.releaseForm)),'%') " +
                    "AND lower(d.activeIngredient) LIKE concat('%', lower(coalesce(?3, d.activeIngredient)),'%') " +
                    "AND lower(d.indicationsForUse) LIKE concat('%', lower(coalesce(?4, d.indicationsForUse)),'%') " +
                    "AND d.producer.id = coalesce(?5, d.producer) " +
                    "AND d.pharmTerGroup.id = coalesce(?6, d.pharmTerGroup.id)")
	Page<DrugEntity> findByParameters(String name, String releaseForm, String activeIngredient, String indicationsForUse,
                                      ProducerEntity producer, PharmTerGroupEntity pharmTerGroup, Pageable pageable);
}
