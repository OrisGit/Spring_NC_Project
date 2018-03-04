package com.nc.netcracker_project.services.import_export;


import com.nc.netcracker_project.model.entities.*;
import com.nc.netcracker_project.model.repositories.*;
import com.nc.netcracker_project.services.import_export.marshalling.AbstractMarshaller;
import com.nc.netcracker_project.services.import_export.marshalling.JsonMarshaller;
import com.nc.netcracker_project.services.import_export.marshalling.MarshallingException;
import com.nc.netcracker_project.services.import_export.marshalling.XmlMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Exporter {

    private DrugRepository drugRepository;
    private DrugStoreRepository drugStoreRepository;
    private TherapeuticEffectRepository tEffectRepository;
    private PharmachologicEffectRepository pEffectRepository;
    private PriceRepository priceRepository;

    @Autowired
    public Exporter(DrugRepository drugRepository, DrugStoreRepository drugStoreRepository, TherapeuticEffectRepository tEffectRepository, PharmachologicEffectRepository pEffectRepository, PriceRepository priceRepository) {
        this.drugRepository = drugRepository;
        this.drugStoreRepository = drugStoreRepository;
        this.tEffectRepository = tEffectRepository;
        this.pEffectRepository = pEffectRepository;
        this.priceRepository = priceRepository;
    }

    public String export(FormatType type, boolean format) throws ExportException {
        AbstractMarshaller marshaller;

        EntityWrapper wrapper;

        try{
            wrapper = new EntityWrapper((List<PharmachologicEffectEntity>) pEffectRepository.findAll(),(List<TherapeuticEffectEntity>)tEffectRepository.findAll(),
                    (List<DrugstoreEntity>) drugStoreRepository.findAll(),(List<DrugEntity>)drugRepository.findAll(),(List<PriceEntity>)priceRepository.findAll());
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
