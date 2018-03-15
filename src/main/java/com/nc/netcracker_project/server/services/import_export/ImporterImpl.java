package com.nc.netcracker_project.server.services.import_export;



import com.nc.netcracker_project.server.model.entities.*;
import com.nc.netcracker_project.server.services.data.*;
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
    private DrugDataControl drugDataControl;
    private DrugstoreDataControl drugstoreDataControl;
    private TherapeuticEffectDataControl therapeuticEffectDataControl;
    private PharmachologicEffectDataControl pharmachologicEffectDataControl;
    private PriceDataControl priceDataControl;
    private EventService eventService;

    @Autowired
    public ImporterImpl(DrugDataControl drugDataControl, DrugstoreDataControl drugstoreDataControl,
                        TherapeuticEffectDataControl therapeuticEffectDataControl,
                        PharmachologicEffectDataControl pharmachologicEffectDataControl, PriceDataControl priceDataControl,
                        EventService eventService) {
        this.drugDataControl = drugDataControl;
        this.drugstoreDataControl = drugstoreDataControl;
        this.therapeuticEffectDataControl = therapeuticEffectDataControl;
        this.pharmachologicEffectDataControl = pharmachologicEffectDataControl;
        this.priceDataControl = priceDataControl;
        this.eventService = eventService;
    }

    @Override
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
            importEntities(entityWrapper.getPharmachologicEffects(), (a) -> {
                try {
                    pharmachologicEffectDataControl.saveOrUpdate((PharmachologicEffectEntity) a);
                } catch (Exception e) {
                    throw new ImportException(e);
                }
            });
            importEntities(entityWrapper.getTherapeuticEffects(), (a) -> {
                try {
                    therapeuticEffectDataControl.saveOrUpdate((TherapeuticEffectEntity) a);
                } catch (Exception e) {
                    throw new ImportException(e);
                }
            });
            importEntities(entityWrapper.getDrugstores(), (a) -> {
                try {
                    drugstoreDataControl.saveOrUpdate((DrugstoreEntity) a);
                } catch (Exception e) {
                    throw new ImportException(e);
                }
            });
            importEntities(entityWrapper.getDrugs(), (a) -> {
                try {
                    drugDataControl.saveOrUpdate((DrugEntity) a);
                } catch (Exception e) {
                    throw new ImportException(e);
                }
            });
            importEntities(entityWrapper.getPrice(), (a) -> {
                try {
                    priceDataControl.saveOrUpdate((PriceEntity) a);
                } catch (Exception e) {
                    throw new ImportException(e);
                }
            });
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
        void save(Object o) throws ImportException;
    }

}
