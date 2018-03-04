package com.nc.netcracker_project.services.import_export;



import com.nc.netcracker_project.model.repositories.*;
import com.nc.netcracker_project.services.import_export.marshalling.AbstractUnmarshaller;
import com.nc.netcracker_project.services.import_export.marshalling.JsonUnmarshaller;
import com.nc.netcracker_project.services.import_export.marshalling.UnmarshallingException;
import com.nc.netcracker_project.services.import_export.marshalling.XmlUnmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Service
public class Importer {

    private DrugRepository drugRepository;
    private DrugStoreRepository drugStoreRepository;
    private TherapeuticEffectRepository tEffectRepository;
    private PharmachologicEffectRepository pEffectRepository;
    private PriceRepository priceRepository;

    @Autowired
    public Importer(DrugRepository drugRepository, DrugStoreRepository drugStoreRepository, TherapeuticEffectRepository tEffectRepository, PharmachologicEffectRepository pEffectRepository, PriceRepository priceRepository) {
        this.drugRepository = drugRepository;
        this.drugStoreRepository = drugStoreRepository;
        this.tEffectRepository = tEffectRepository;
        this.pEffectRepository = pEffectRepository;
        this.priceRepository = priceRepository;
    }

    @Transactional
    public void _import(String str, FormatType type) throws ImportException {
        AbstractUnmarshaller unmarshaller;
        if(type.equals(FormatType.XML)){
            unmarshaller = new XmlUnmarshaller();
        }else{
            unmarshaller = new JsonUnmarshaller();
        }

        EntityWrapper entityWrapper;
        try {
            entityWrapper = unmarshaller.importFromString(str);
        } catch (UnmarshallingException e) {
            throw new ImportException(e);
        }

        if(entityWrapper!=null){
            importEntities(entityWrapper.getPharmachologicEffects(), pEffectRepository);
            importEntities(entityWrapper.getTherapeuticEffects(), tEffectRepository);
            importEntities(entityWrapper.getDrugstores(),drugStoreRepository);
            importEntities(entityWrapper.getDrugs(),drugRepository);
            importEntities(entityWrapper.getPrice(),priceRepository);
        }
    }

    private <T,R extends Serializable> void importEntities(List<T> entities, CrudRepository<T,R> repository){
        for(T entity : entities){
            try {
                repository.save(entity);
            } catch (Exception e) {
            }
        }
    }

}
