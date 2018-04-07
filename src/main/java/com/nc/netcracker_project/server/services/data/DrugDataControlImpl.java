package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import com.nc.netcracker_project.server.model.entities.PharmTerGroupEntity;
import com.nc.netcracker_project.server.model.entities.ProducerEntity;
import com.nc.netcracker_project.server.services.parameters.Parameters;
import com.nc.netcracker_project.server.model.repositories.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DrugDataControlImpl extends AbstractDataControl<DrugEntity, UUID> implements DrugDataControl {

    private PharmTerGroupDataControl pharmTerGroupDataControl;
    private ProducerDataControl producerDataControl;

    @Autowired
    public DrugDataControlImpl(DrugRepository repository, PharmTerGroupDataControl pharmTerGroupDataControl,
                               ProducerDataControl producerDataControl) {
        super(repository);
        this.producerDataControl = producerDataControl;
        this.pharmTerGroupDataControl = pharmTerGroupDataControl;
    }

    @Override
    public DrugEntity saveOrUpdate(DrugEntity entity) throws Exception {
        if(!pharmTerGroupDataControl.exists(entity.getPharmTerGroup().getId()))
            pharmTerGroupDataControl.saveOrUpdate(entity.getPharmTerGroup());
        if(!producerDataControl.exists(entity.getProducer().getId()))
            producerDataControl.saveOrUpdate(entity.getProducer());
        return super.saveOrUpdate(entity);
    }

    @Override
    public List<DrugEntity> findByProducer(ProducerEntity producer) {
        DrugRepository drugRepository = (DrugRepository) repository;
        return drugRepository.findByProducer(producer);
    }

    @Override
    public Page<DrugEntity> findByProducer(ProducerEntity producer, Pageable pageable) {
        DrugRepository drugRepository = (DrugRepository) repository;
        return drugRepository.findByProducer(producer, pageable);
    }

    @Override
    public List<DrugEntity> findByPharmTerGroup(PharmTerGroupEntity pharmTerGroup) {
        DrugRepository drugRepository = (DrugRepository) repository;
        return drugRepository.findByPharmTerGroup(pharmTerGroup);
    }

    @Override
    public Page<DrugEntity> findByPharmTerGroup(PharmTerGroupEntity pharmTerGroup, Pageable pageable) {
        DrugRepository drugRepository = (DrugRepository) repository;
        return drugRepository.findByPharmTerGroup(pharmTerGroup, pageable);
    }

    @Override
    public Page<DrugEntity> findByParameters(Parameters parameters, Pageable pageable) {
        DrugRepository drugRepository = (DrugRepository) repository;
        return drugRepository.findByParameters(parameters.getName(), parameters.getReleaseForm(),
                parameters.getActiveIngredient(), parameters.getIndicationsForUse(), parameters.getProducer(),
                parameters.getPharmTerGroup(), pageable);
    }
}
