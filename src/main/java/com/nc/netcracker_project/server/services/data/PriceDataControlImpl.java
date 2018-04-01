package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import com.nc.netcracker_project.server.model.entities.DrugstoreEntity;
import com.nc.netcracker_project.server.model.entities.PriceEntity;
import com.nc.netcracker_project.server.model.entities.PriceEntityPK;
import com.nc.netcracker_project.server.model.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<PriceEntity> findByDrug(DrugEntity drugEntity) {
        PriceRepository priceRepository = (PriceRepository) repository;
        return priceRepository.findByDrug(drugEntity);
    }

    @Override
    public Page<PriceEntity> findByDrug(DrugEntity drugEntity, Pageable pageable) {
        PriceRepository priceRepository = (PriceRepository) repository;
        return priceRepository.findByDrug(drugEntity, pageable);
    }

    @Override
    public List<PriceEntity> findByDrugstore(DrugstoreEntity drugstoreEntity) {
        PriceRepository priceRepository = (PriceRepository) repository;
        return priceRepository.findByDrugstore(drugstoreEntity);
    }

    @Override
    public Page<PriceEntity> findByDrugstore(DrugstoreEntity drugstoreEntity, Pageable pageable) {
        PriceRepository priceRepository = (PriceRepository) repository;
        return priceRepository.findByDrugstore(drugstoreEntity, pageable);
    }
}
