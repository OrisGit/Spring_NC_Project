package com.nc.netcracker_project.server.services.import_export;



import com.nc.netcracker_project.server.model.entities.*;
import com.nc.netcracker_project.server.model.repositories.*;
import com.nc.netcracker_project.server.services.data.DataControl;
import com.nc.netcracker_project.server.services.event_service.EventService;
import com.nc.netcracker_project.server.services.import_export.marshalling.AbstractUnmarshaller;
import com.nc.netcracker_project.server.services.import_export.marshalling.JsonUnmarshaller;
import com.nc.netcracker_project.server.services.import_export.marshalling.UnmarshallingException;
import com.nc.netcracker_project.server.services.import_export.marshalling.XmlUnmarshaller;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
public class ImporterImpl implements Importer {

    private static final Logger LOG = Logger.getLogger(ImporterImpl.class);
    private DrugRepository drugRepository;
    private DrugStoreRepository drugStoreRepository;
    private TherapeuticEffectRepository tEffectRepository;
    private PharmachologicEffectRepository pEffectRepository;
    private PriceRepository priceRepository;
    private DataControl dataControl;
    private EventService eventService;

    @Autowired
    public ImporterImpl(DrugRepository drugRepository, DrugStoreRepository drugStoreRepository, TherapeuticEffectRepository tEffectRepository, PharmachologicEffectRepository pEffectRepository, PriceRepository priceRepository, DataControl dataControl, EventService eventService) {
        this.drugRepository = drugRepository;
        this.drugStoreRepository = drugStoreRepository;
        this.tEffectRepository = tEffectRepository;
        this.pEffectRepository = pEffectRepository;
        this.priceRepository = priceRepository;
        this.dataControl = dataControl;
        this.eventService = eventService;
    }

    @Override
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
            importEntities(entityWrapper.getPharmachologicEffects(), (a)-> dataControl.savePharmachologicEffect((PharmachologicEffectEntity)a));
            importEntities(entityWrapper.getTherapeuticEffects(), (a)-> dataControl.saveTherapeuticEffect((TherapeuticEffectEntity) a));
            importEntities(entityWrapper.getDrugstores(),(a)-> dataControl.saveDrugstore((DrugstoreEntity) a));
            importEntities(entityWrapper.getDrugs(),(a)-> dataControl.saveDrug((DrugEntity) a));
            importEntities(entityWrapper.getPrice(),(a)-> dataControl.savePrice((PriceEntity) a));
        }
        eventService.updateTherapheuticEffects();
        eventService.updatePharmachologicEffects();
        eventService.updateDrugs();
        eventService.updateDrugstores();
        eventService.updatePrices();
    }

    private <T,R extends Serializable> void importEntities(List<T> entities, Action action){
        for(T entity : entities){
            try {
                action.save(entity);
            } catch (Exception e) {
                LOG.error(e);
            }
        }
    }

    private interface Action{
        void save(Object o);
    }

}
