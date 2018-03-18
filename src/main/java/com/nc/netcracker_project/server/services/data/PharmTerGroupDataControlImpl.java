package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.PharmTerGroupEntity;
import com.nc.netcracker_project.server.model.repositories.PharmTerGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PharmTerGroupDataControlImpl extends AbstractDataControl<PharmTerGroupEntity, UUID> implements PharmTerGroupDataControl {

    @Autowired
    protected PharmTerGroupDataControlImpl(PharmTerGroupRepository repository) {
        super(repository);
    }
}
