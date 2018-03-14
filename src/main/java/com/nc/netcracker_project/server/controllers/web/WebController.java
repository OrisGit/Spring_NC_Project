package com.nc.netcracker_project.server.controllers.web;

import com.nc.netcracker_project.server.model.entities.*;
import com.nc.netcracker_project.server.services.data.DataControl;
import com.nc.netcracker_project.server.services.event_service.EventListener;
import com.nc.netcracker_project.server.services.event_service.EventService;
import com.nc.netcracker_project.server.services.import_export.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class WebController {

    private static final Logger LOG = Logger.getLogger(WebController.class);

    private DataControl dataControl;
    private Importer importer;
    private Exporter exporter;
    private EventService eventService;

    @Autowired
    public WebController(DataControl dataControl, Importer importer, Exporter exporter, EventService eventService) {
        this.dataControl = dataControl;
        this.importer = importer;
        this.exporter = exporter;
        this.eventService = eventService;
    }

    //region Drugs
    @GetMapping("/drug")
    public Iterable<DrugEntity> getAllDrugs() {
        return dataControl.getAllDrug();
    }

    @GetMapping("/drug/{id}")
    public ResponseEntity<DrugEntity> getDrug(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        DrugEntity drug = dataControl.getDrug(uuid);
        if (drug == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(drug);
    }

    @PostMapping("/drug/new")
    public ResponseEntity<DrugEntity> createDrug(@Valid @RequestBody DrugEntity drugEntity) {
        Boolean success = dataControl.saveDrug(drugEntity);
        if (success) {
            eventService.updateDrugs();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/drug/{id}")
    public ResponseEntity<DrugEntity> updateDrug(@PathVariable String id,
                                                 @Valid @RequestBody DrugEntity drugEntity) {
        UUID uuid = UUID.fromString(id);
        DrugEntity drug = dataControl.getDrug(uuid);
        if (drug == null) {
            return ResponseEntity.notFound().build();
        }
        drugEntity.setId(drug.getId());
        Boolean success = dataControl.saveDrug(drugEntity);
        if (success) {
            eventService.updateDrugs();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(drugEntity);
    }

    @DeleteMapping("/drug/{id}")
    public ResponseEntity<DrugEntity> deleteDrug(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        DrugEntity drug = dataControl.getDrug(uuid);
        if (drug == null) {
            return ResponseEntity.notFound().build();
        }
        Boolean success = dataControl.deleteDrug(drug);
        if (success) {
            eventService.updateDrugs();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
    //endregion

    //region Drugstore
    @GetMapping("/drugstore")
    public Iterable<DrugstoreEntity> getAllDrugstore() {
        return dataControl.getAllDrugstore();
    }

    @GetMapping("/drugstore/{id}")
    public ResponseEntity<DrugstoreEntity> getDrugstore(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        DrugstoreEntity drugstore = dataControl.getDrugstore(uuid);
        if (drugstore == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(drugstore);
    }

    @PostMapping("/drugstore/new")
    public ResponseEntity<DrugstoreEntity> createDrugstore(@Valid @RequestBody DrugstoreEntity drugstoreEntity) {
        Boolean success = dataControl.saveDrugstore(drugstoreEntity);
        if (success) {
            eventService.updateDrugstores();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/drugstore/{id}")
    public ResponseEntity<DrugstoreEntity> updateDrugstore(@PathVariable String id,
                                                           @Valid @RequestBody DrugstoreEntity drugstoreEntity) {
        UUID uuid = UUID.fromString(id);
        DrugstoreEntity drugstore = dataControl.getDrugstore(uuid);
        if (drugstore == null) {
            return ResponseEntity.notFound().build();
        }
        drugstoreEntity.setId(drugstore.getId());
        Boolean success = dataControl.saveDrugstore(drugstoreEntity);
        if (success) {
            eventService.updateDrugstores();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(drugstoreEntity);
    }

    @DeleteMapping("/drugstore/{id}")
    public ResponseEntity<DrugstoreEntity> deleteDrugstore(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        DrugstoreEntity drugstore = dataControl.getDrugstore(uuid);
        if (drugstore == null) {
            return ResponseEntity.notFound().build();
        }
        Boolean success = dataControl.deleteDrugstore(drugstore);
        if (success) {
            eventService.updateDrugstores();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
    //endregion

    //region TherapeuticEffect
    @GetMapping("/tEffect")
    public Iterable<TherapeuticEffectEntity> getAllTherapeuticEffect() {
        return dataControl.getAllTherapeuticEffect();
    }

    @GetMapping("/tEffect/{id}")
    public ResponseEntity<TherapeuticEffectEntity> getTherapeuticEffect(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        TherapeuticEffectEntity therapeuticEffect = dataControl.getTherapeuticEffect(uuid);
        if (therapeuticEffect == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(therapeuticEffect);
    }

    @PostMapping("/tEffect/new")
    public ResponseEntity<TherapeuticEffectEntity> createTherapeuticEffect(@Valid @RequestBody TherapeuticEffectEntity therapeuticEffectEntity) {
        Boolean success = dataControl.saveTherapeuticEffect(therapeuticEffectEntity);
        if (success) {
            eventService.updateTherapheuticEffects();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/tEffect/{id}")
    public ResponseEntity<TherapeuticEffectEntity> updateTherapeuticEffect(@PathVariable String id,
                                                                           @Valid @RequestBody TherapeuticEffectEntity therapeuticEffectEntity) {
        UUID uuid = UUID.fromString(id);
        TherapeuticEffectEntity therapeuticEffect = dataControl.getTherapeuticEffect(uuid);
        if (therapeuticEffect == null) {
            return ResponseEntity.notFound().build();
        }
        therapeuticEffectEntity.setId(therapeuticEffect.getId());
        Boolean success = dataControl.saveTherapeuticEffect(therapeuticEffectEntity);
        if (success) {
            eventService.updateTherapheuticEffects();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(therapeuticEffectEntity);
    }

    @DeleteMapping("/tEffect/{id}")
    public ResponseEntity<TherapeuticEffectEntity> deleteTherapeuticEffect(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        TherapeuticEffectEntity therapeuticEffect = dataControl.getTherapeuticEffect(uuid);
        if (therapeuticEffect == null) {
            return ResponseEntity.notFound().build();
        }
        Boolean success = dataControl.deleteTherapeuticEffect(therapeuticEffect);
        if (success) {
            eventService.updateTherapheuticEffects();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
    //endregion

    //region PharmacologicEffect
    @GetMapping("/pEffect")
    public Iterable<PharmachologicEffectEntity> getAllPharmachologicEffect() {
        return dataControl.getAllPharmachologicEffect();
    }

    @GetMapping("/pEffect/{id}")
    public ResponseEntity<PharmachologicEffectEntity> getPharmachologicEffect(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        PharmachologicEffectEntity pharmachologicEffect = dataControl.getPharmachologicEffect(uuid);
        if (pharmachologicEffect == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pharmachologicEffect);
    }

    @PostMapping("/pEffect/new")
    public ResponseEntity<PharmachologicEffectEntity> createPharmachologicEffect(@Valid @RequestBody PharmachologicEffectEntity pharmachologicEffectEntity) {
        Boolean success = dataControl.savePharmachologicEffect(pharmachologicEffectEntity);
        if (success) {
            eventService.updatePharmachologicEffects();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/pEffect/{id}")
    public ResponseEntity<PharmachologicEffectEntity> updatePharmachologicEffect(@PathVariable String id,
                                                                                 @Valid @RequestBody PharmachologicEffectEntity pharmachologicEffectEntity) {
        UUID uuid = UUID.fromString(id);
        PharmachologicEffectEntity pharmachologicEffect = dataControl.getPharmachologicEffect(uuid);
        if (pharmachologicEffect == null) {
            return ResponseEntity.notFound().build();
        }
        pharmachologicEffectEntity.setId(pharmachologicEffect.getId());
        Boolean success = dataControl.savePharmachologicEffect(pharmachologicEffectEntity);
        if (success) {
            eventService.updatePharmachologicEffects();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(pharmachologicEffectEntity);
    }

    @DeleteMapping("/pEffect/{id}")
    public ResponseEntity<PharmachologicEffectEntity> deletePharmachologicEffect(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        PharmachologicEffectEntity pharmachologicEffect = dataControl.getPharmachologicEffect(uuid);
        if (pharmachologicEffect == null) {
            return ResponseEntity.notFound().build();
        }
        Boolean success = dataControl.deletePharmachologicEffect(pharmachologicEffect);
        if (success) {
            eventService.updatePharmachologicEffects();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
    //endregion

    //region Price
    @GetMapping("/price")
    public Iterable<PriceEntity> getAllPrices() {
        return dataControl.getAllPrice();
    }

    @GetMapping("/price/{id}")
    public ResponseEntity<PriceEntity> getPrice(@PathVariable String id) {
		String[] uuids = id.split("[&]");
        UUID drugId = UUID.fromString(uuids[0]);
        UUID drugstoreId = UUID.fromString(uuids[1]);

        PriceEntityPK pk = new PriceEntityPK(drugId, drugstoreId);

        PriceEntity price = dataControl.getPrice(pk);
        if (price == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(price);
    }

    @PostMapping("/price/new")
    public ResponseEntity<PriceEntity> createPrice(@Valid @RequestBody PriceEntity priceEntity) {
        Boolean success = dataControl.savePrice(priceEntity);
        if (success) {
            eventService.updatePrices();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/price/{id}")
    public ResponseEntity<PriceEntity> updatePrice(@PathVariable String id,
                                                   @Valid @RequestBody PriceEntity priceEntity) {
        String[] uuids = id.split("[&]");
        UUID drugId = UUID.fromString(uuids[0]);
        UUID drugstoreId = UUID.fromString(uuids[1]);

        PriceEntityPK pk = new PriceEntityPK(drugId, drugstoreId);

        PriceEntity price = dataControl.getPrice(pk);
        if (price == null) {
            return ResponseEntity.notFound().build();
        }
        priceEntity.setId(price.getId());
        Boolean success = dataControl.savePrice(priceEntity);
        if (success) {
            eventService.updatePrices();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(priceEntity);
    }

    @DeleteMapping("/price/{id}")
    public ResponseEntity<PriceEntity> deletePrice(@PathVariable String id) {
        String[] uuids = id.split("[&]");
        UUID drugId = UUID.fromString(uuids[0]);
        UUID drugstoreId = UUID.fromString(uuids[1]);

        PriceEntityPK pk = new PriceEntityPK(drugId, drugstoreId);

        PriceEntity price = dataControl.getPrice(pk);
        if (price == null) {
            return ResponseEntity.notFound().build();
        }
        Boolean success = dataControl.deletePrice(price);
        if (success) {
            eventService.updatePrices();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
    //endregion

    @GetMapping("/export")
    public @ResponseBody ResponseEntity<String> exportFromDB(@RequestParam FormatType format) {
        try {
            return ResponseEntity.ok(exporter.export(format, true));//todo
        } catch (ExportException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping("/import")
    public ResponseEntity importInDB(@RequestBody String data, @RequestParam FormatType format) {
        try {
            importer._import(data, format);
            return ResponseEntity.ok().build();
        } catch (ImportException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public void addEventListener(EventListener listener) {
        eventService.addWebListener(listener);
    }

    public void removeEventListener(EventListener listener) {
        eventService.removeWebListener(listener);
    }
}
