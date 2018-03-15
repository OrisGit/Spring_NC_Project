package com.nc.netcracker_project.server.controllers.web;

import com.nc.netcracker_project.server.model.entities.*;
import com.nc.netcracker_project.server.services.data.*;
import com.nc.netcracker_project.server.services.event_service.EventListener;
import com.nc.netcracker_project.server.services.event_service.EventService;
import com.nc.netcracker_project.server.services.import_export.Exporter;
import com.nc.netcracker_project.server.services.import_export.Importer;
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

    private DrugDataControl drugDataControl;
    private DrugstoreDataControl drugstoreDataControl;
    private PriceDataControl priceDataControl;
    private PharmachologicEffectDataControl pEffectDataControl;
    private TherapeuticEffectDataControl tEffectDataControl;
    private Importer importer;
    private Exporter exporter;
    private EventService eventService;

    @Autowired
    public WebController(DrugDataControl drugDataControl, DrugstoreDataControl drugstoreDataControl,
                         PriceDataControl priceDataControl, PharmachologicEffectDataControl pEffectDataControl,
                         TherapeuticEffectDataControl tEffectDataControl, Importer importer, Exporter exporter,
                         EventService eventService) {
        this.drugDataControl = drugDataControl;
        this.drugstoreDataControl = drugstoreDataControl;
        this.priceDataControl = priceDataControl;
        this.pEffectDataControl = pEffectDataControl;
        this.tEffectDataControl = tEffectDataControl;
        this.importer = importer;
        this.exporter = exporter;
        this.eventService = eventService;
    }

    //region Drugs
    @GetMapping("/drug")
    public Iterable<DrugEntity> getAllDrugs() {
        return drugDataControl.getAll();
    }

    @GetMapping("/drug/{id}")
    public ResponseEntity<DrugEntity> getDrug(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        DrugEntity drug = drugDataControl.get(uuid);
        if (drug == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(drug);
    }

    @PostMapping("/drug/new")//todo ModelAndView
    public ResponseEntity<DrugEntity> createDrug(@Valid @RequestBody DrugEntity drugEntity) {
        try {
            drugDataControl.saveOrUpdate(drugEntity);
            eventService.updateDrugs();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/drug/{id}")//todo ModelAndView
    public ResponseEntity<DrugEntity> updateDrug(@PathVariable String id,
                                                 @Valid @RequestBody DrugEntity drugEntity) {
        UUID uuid = UUID.fromString(id);
        DrugEntity drug = drugDataControl.get(uuid);
        if (drug == null) {
            return ResponseEntity.notFound().build();
        }
        drugEntity.setId(drug.getId());
        try {
            drugDataControl.saveOrUpdate(drugEntity);
            eventService.updateDrugs();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(drugEntity);
    }

    @DeleteMapping("/drug/{id}")
    public ResponseEntity<DrugEntity> deleteDrug(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        DrugEntity drug = drugDataControl.get(uuid);
        if (drug == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            drugDataControl.delete(drug);
            eventService.updateDrugs();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
    //endregion

    //region Drugstore
    @GetMapping("/drugstore")
    public Iterable<DrugstoreEntity> getAllDrugstore() {
        return drugstoreDataControl.getAll();
    }

    @GetMapping("/drugstore/{id}")
    public ResponseEntity<DrugstoreEntity> getDrugstore(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        DrugstoreEntity drugstore = drugstoreDataControl.get(uuid);
        if (drugstore == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(drugstore);
    }

    @PostMapping("/drugstore/new")
    public ResponseEntity<DrugstoreEntity> createDrugstore(@Valid @RequestBody DrugstoreEntity drugstoreEntity) {
        try {
            drugstoreDataControl.saveOrUpdate(drugstoreEntity);
            eventService.updateDrugstores();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/drugstore/{id}")
    public ResponseEntity<DrugstoreEntity> updateDrugstore(@PathVariable String id,
                                                           @Valid @RequestBody DrugstoreEntity drugstoreEntity) {
        UUID uuid = UUID.fromString(id);
        DrugstoreEntity drugstore = drugstoreDataControl.get(uuid);
        if (drugstore == null) {
            return ResponseEntity.notFound().build();
        }
        drugstoreEntity.setId(drugstore.getId());
        try {
            drugstoreDataControl.saveOrUpdate(drugstoreEntity);
            eventService.updateDrugstores();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(drugstoreEntity);
    }

    @DeleteMapping("/drugstore/{id}")
    public ResponseEntity<DrugstoreEntity> deleteDrugstore(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        DrugstoreEntity drugstore = drugstoreDataControl.get(uuid);
        if (drugstore == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            drugstoreDataControl.delete(drugstore);
            eventService.updateDrugstores();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
    //endregion

    //region TherapeuticEffect
    @GetMapping("/tEffect")
    public Iterable<TherapeuticEffectEntity> getAllTherapeuticEffect() {
        return tEffectDataControl.getAll();
    }

    @GetMapping("/tEffect/{id}")
    public ResponseEntity<TherapeuticEffectEntity> getTherapeuticEffect(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        TherapeuticEffectEntity therapeuticEffect = tEffectDataControl.get(uuid);
        if (therapeuticEffect == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(therapeuticEffect);
    }

    @PostMapping("/tEffect/new")
    public ResponseEntity<TherapeuticEffectEntity> createTherapeuticEffect(@Valid @RequestBody TherapeuticEffectEntity therapeuticEffectEntity) {
        try {
            tEffectDataControl.saveOrUpdate(therapeuticEffectEntity);
            eventService.updateTherapheuticEffects();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/tEffect/{id}")
    public ResponseEntity<TherapeuticEffectEntity> updateTherapeuticEffect(@PathVariable String id,
                                                                           @Valid @RequestBody TherapeuticEffectEntity therapeuticEffectEntity) {
        UUID uuid = UUID.fromString(id);
        TherapeuticEffectEntity therapeuticEffect = tEffectDataControl.get(uuid);
        if (therapeuticEffect == null) {
            return ResponseEntity.notFound().build();
        }
        therapeuticEffectEntity.setId(therapeuticEffect.getId());
        try {
            tEffectDataControl.saveOrUpdate(therapeuticEffectEntity);
            eventService.updateTherapheuticEffects();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(therapeuticEffectEntity);
    }

    @DeleteMapping("/tEffect/{id}")
    public ResponseEntity<TherapeuticEffectEntity> deleteTherapeuticEffect(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        TherapeuticEffectEntity therapeuticEffect = tEffectDataControl.get(uuid);
        if (therapeuticEffect == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            tEffectDataControl.delete(therapeuticEffect);
            eventService.updateTherapheuticEffects();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
    //endregion

    //region PharmacologicEffect
    @GetMapping("/pEffect")
    public Iterable<PharmachologicEffectEntity> getAllPharmachologicEffect() {
        return pEffectDataControl.getAll();
    }

    @GetMapping("/pEffect/{id}")
    public ResponseEntity<PharmachologicEffectEntity> getPharmachologicEffect(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        PharmachologicEffectEntity pharmachologicEffect = pEffectDataControl.get(uuid);
        if (pharmachologicEffect == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pharmachologicEffect);
    }

    @PostMapping("/pEffect/new")
    public ResponseEntity<PharmachologicEffectEntity> createPharmachologicEffect(@Valid @RequestBody PharmachologicEffectEntity pharmachologicEffectEntity) {
        try {
            pEffectDataControl.saveOrUpdate(pharmachologicEffectEntity);
            eventService.updatePharmachologicEffects();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/pEffect/{id}")
    public ResponseEntity<PharmachologicEffectEntity> updatePharmachologicEffect(@PathVariable String id,
                                                                                 @Valid @RequestBody PharmachologicEffectEntity pharmachologicEffectEntity) {
        UUID uuid = UUID.fromString(id);
        PharmachologicEffectEntity pharmachologicEffect = pEffectDataControl.get(uuid);
        if (pharmachologicEffect == null) {
            return ResponseEntity.notFound().build();
        }
        pharmachologicEffectEntity.setId(pharmachologicEffect.getId());
        try {
            pEffectDataControl.saveOrUpdate(pharmachologicEffectEntity);
            eventService.updatePharmachologicEffects();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(pharmachologicEffectEntity);
    }

    @DeleteMapping("/pEffect/{id}")
    public ResponseEntity<PharmachologicEffectEntity> deletePharmachologicEffect(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        PharmachologicEffectEntity pharmachologicEffect = pEffectDataControl.get(uuid);
        if (pharmachologicEffect == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            pEffectDataControl.delete(pharmachologicEffect);
            eventService.updatePharmachologicEffects();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
    //endregion

    //region Price
    @GetMapping("/price")
    public Iterable<PriceEntity> getAllPrices() {
        return priceDataControl.getAll();
    }

    @GetMapping("/price/{id}")
    public ResponseEntity<PriceEntity> getPrice(@PathVariable String id) {
        UUID drugId = UUID.fromString(id.split("[&]")[0]);
        UUID drugstoreId = UUID.fromString(id.split("[&]")[1]);

        PriceEntityPK pk = new PriceEntityPK(drugId, drugstoreId);

        PriceEntity price = priceDataControl.get(pk);
        if (price == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(price);
    }

    @PostMapping("/price/new")
    public ResponseEntity<PriceEntity> createPrice(@Valid @RequestBody PriceEntity priceEntity) {
        try {
            priceDataControl.saveOrUpdate(priceEntity);
            eventService.updatePrices();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/price/{id}")
    public ResponseEntity<PriceEntity> updatePrice(@PathVariable String id,
                                                   @Valid @RequestBody PriceEntity priceEntity) {
        UUID drugId = UUID.fromString(id.split("[&]")[0]);
        UUID drugstoreId = UUID.fromString(id.split("[&]")[1]);

        PriceEntityPK pk = new PriceEntityPK(drugId, drugstoreId);

        PriceEntity price = priceDataControl.get(pk);
        if (price == null) {
            return ResponseEntity.notFound().build();
        }
        priceEntity.setId(price.getId());
        try {
            priceDataControl.saveOrUpdate(priceEntity);
            eventService.updatePrices();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(priceEntity);
    }

    @DeleteMapping("/price/{id}")
    public ResponseEntity<PriceEntity> deletePrice(@PathVariable String id) {
        UUID drugId = UUID.fromString(id.split("[&]")[0]);
        UUID drugstoreId = UUID.fromString(id.split("[&]")[1]);

        PriceEntityPK pk = new PriceEntityPK(drugId, drugstoreId);

        PriceEntity price = priceDataControl.get(pk);
        if (price == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            priceDataControl.delete(price);
            eventService.updatePrices();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
    //endregion

    public void addEventListener(EventListener listener) {
        eventService.addWebListener(listener);
    }

    public void removeEventListener(EventListener listener) {
        eventService.removeWebListener(listener);
    }
}
