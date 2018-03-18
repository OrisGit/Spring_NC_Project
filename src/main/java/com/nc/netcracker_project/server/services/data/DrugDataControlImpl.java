package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import com.nc.netcracker_project.server.model.repositories.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
}
