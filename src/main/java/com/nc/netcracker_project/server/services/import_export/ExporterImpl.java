package com.nc.netcracker_project.server.services.import_export;


import com.nc.netcracker_project.server.model.entities.*;
import com.nc.netcracker_project.server.model.repositories.*;
import com.nc.netcracker_project.server.services.import_export.marshalling.AbstractMarshaller;
import com.nc.netcracker_project.server.services.import_export.marshalling.JsonMarshaller;
import com.nc.netcracker_project.server.services.import_export.marshalling.MarshallingException;
import com.nc.netcracker_project.server.services.import_export.marshalling.XmlMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExporterImpl implements Exporter {

    private DrugRepository drugRepository;
    private DrugstoreRepository drugstoreRepository;
    private TherapeuticEffectRepository tEffectRepository;
    private PharmachologicEffectRepository pEffectRepository;
    private PriceRepository priceRepository;

    @Autowired
    public ExporterImpl(DrugRepository drugRepository, DrugstoreRepository drugstoreRepository, TherapeuticEffectRepository tEffectRepository, PharmachologicEffectRepository pEffectRepository, PriceRepository priceRepository) {
        this.drugRepository = drugRepository;
        this.drugstoreRepository = drugstoreRepository;
        this.tEffectRepository = tEffectRepository;
        this.pEffectRepository = pEffectRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public String export(FormatType type, boolean format) throws ExportException {
        AbstractMarshaller marshaller;

        EntityWrapper wrapper;

        try{
            wrapper = new EntityWrapper((List<PharmachologicEffectEntity>) pEffectRepository.findAll(),(List<TherapeuticEffectEntity>)tEffectRepository.findAll(),
                    (List<DrugstoreEntity>) drugstoreRepository.findAll(),(List<DrugEntity>)drugRepository.findAll(),(List<PriceEntity>)priceRepository.findAll());
        } catch (Exception e) {
            throw new ExportException(e);
        }


        if(type.equals(FormatType.XML)){
            marshaller = new XmlMarshaller(format);
        }else{
            marshaller = new JsonMarshaller(format);
        }

        String result;
        try {
            result = marshaller.exportToString(wrapper);
        } catch (MarshallingException e) {
            throw new ExportException(e);
        }

        return result;
    }
}
