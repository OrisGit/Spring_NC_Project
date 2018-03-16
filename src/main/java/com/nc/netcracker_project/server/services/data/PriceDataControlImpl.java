package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.PriceEntity;
import com.nc.netcracker_project.server.model.entities.PriceEntityPK;
import com.nc.netcracker_project.server.model.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceDataControlImpl extends AbstractDataControl<PriceEntity, PriceEntityPK> implements PriceDataControl {

    private DrugDataControl drugDataControl;
    private DrugstoreDataControl drugstoreDataControl;

    @Autowired
    protected PriceDataControlImpl(PriceRepository repository, DrugDataControl drugDataControl, DrugstoreDataControl drugstoreDataControl) {
        super(repository);
        this.drugDataControl = drugDataControl;
        this.drugstoreDataControl = drugstoreDataControl;
    }

    @Override
    public PriceEntity saveOrUpdate(PriceEntity entity) throws Exception {
        if(!drugDataControl.exists(entity.getDrug().getId()))
            drugDataControl.saveOrUpdate(entity.getDrug());
        if(!drugstoreDataControl.exists(entity.getDrugstore().getId()))
            drugstoreDataControl.saveOrUpdate(entity.getDrugstore());
        return super.saveOrUpdate(entity);
    }
}
