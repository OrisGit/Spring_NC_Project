package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.ProducerEntity;
import com.nc.netcracker_project.server.model.repositories.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProducerDataControlImpl extends AbstractDataControl<ProducerEntity, UUID> implements ProducerDataControl {

    @Autowired
    protected ProducerDataControlImpl(ProducerRepository repository) {
        super(repository);
    }
}
