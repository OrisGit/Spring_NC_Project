package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import com.nc.netcracker_project.server.model.repositories.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DrugDataControlImpl extends AbstractDataControl<DrugEntity, UUID> implements DrugDataControl {

    private TherapeuticEffectDataControl tEffectDataControl;
    private PharmachologicEffectDataControl pEffectDataControl;

    @Autowired
    public DrugDataControlImpl(DrugRepository repository, TherapeuticEffectDataControl tEffectDataControl,
                               PharmachologicEffectDataControl pEffectDataControl) {
        super(repository);
        this.pEffectDataControl = pEffectDataControl;
        this.tEffectDataControl = tEffectDataControl;
    }

    @Override
    public DrugEntity saveOrUpdate(DrugEntity entity) throws Exception {
        if(!tEffectDataControl.exists(entity.getTherapeuticEffect().getId()))
            tEffectDataControl.saveOrUpdate(entity.getTherapeuticEffect());
        if(!pEffectDataControl.exists(entity.getPharmachologicEffect().getId()))
            pEffectDataControl.saveOrUpdate(entity.getPharmachologicEffect());
        return super.saveOrUpdate(entity);
    }
}
