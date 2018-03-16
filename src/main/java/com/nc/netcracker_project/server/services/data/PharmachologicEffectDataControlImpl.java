package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.PharmachologicEffectEntity;
import com.nc.netcracker_project.server.model.repositories.PharmachologicEffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PharmachologicEffectDataControlImpl extends AbstractDataControl<PharmachologicEffectEntity, UUID> implements PharmachologicEffectDataControl {

    @Autowired
    protected PharmachologicEffectDataControlImpl(PharmachologicEffectRepository repository) {
        super(repository);
    }
}
