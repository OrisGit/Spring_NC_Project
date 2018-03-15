package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.TherapeuticEffectEntity;
import com.nc.netcracker_project.server.model.repositories.TherapeuticEffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TherapeuticEffectDataControlImpl extends AbstractDataControl<TherapeuticEffectEntity, UUID> implements TherapeuticEffectDataControl {

    @Autowired
    protected TherapeuticEffectDataControlImpl(TherapeuticEffectRepository repository) {
        super(repository);
    }
}
