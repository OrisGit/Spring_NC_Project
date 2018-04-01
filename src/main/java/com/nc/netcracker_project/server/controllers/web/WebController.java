package com.nc.netcracker_project.server.controllers.web;

import com.nc.netcracker_project.server.model.entities.*;
import com.nc.netcracker_project.server.services.data.*;
import com.nc.netcracker_project.server.services.event_service.EventListener;
import com.nc.netcracker_project.server.services.event_service.EventService;
import com.nc.netcracker_project.server.services.import_export.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class WebController {

    private static final Logger LOG = Logger.getLogger(WebController.class);

    private DrugDataControl drugDataControl;
    private DrugstoreDataControl drugstoreDataControl;
    private PriceDataControl priceDataControl;
    private ProducerDataControl producerDataControl;
    private PharmTerGroupDataControl pharmTerGroupDataControl;
    private Importer importer;
    private Exporter exporter;
    private EventService eventService;

    @Autowired
    public WebController(DrugDataControl drugDataControl, DrugstoreDataControl drugstoreDataControl,
                         PriceDataControl priceDataControl, ProducerDataControl producerDataControl,
                         PharmTerGroupDataControl pharmTerGroupDataControl, Importer importer, Exporter exporter,
                         EventService eventService) {
        this.drugDataControl = drugDataControl;
        this.drugstoreDataControl = drugstoreDataControl;
        this.priceDataControl = priceDataControl;
        this.producerDataControl = producerDataControl;
        this.pharmTerGroupDataControl = pharmTerGroupDataControl;
        this.importer = importer;
        this.exporter = exporter;
        this.eventService = eventService;
    }

    //region Drugs
    @GetMapping("/drug")
    public Iterable<DrugEntity> getAllDrugs(Pageable pageable) {
        return drugDataControl.findAll(pageable);
    }

    @GetMapping("/drug/{drug}")
    public ResponseEntity<DrugEntity> getDrug(@PathVariable DrugEntity drug) {
        if (drug == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(drug);
    }

    @PostMapping("/drug")
    public ResponseEntity<DrugEntity> createDrug(@Valid @RequestBody DrugEntity drugEntity) {
        try {
            drugDataControl.saveOrUpdate(drugEntity);
            eventService.updateDrugs();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/drug/{drug}")
    public ResponseEntity<DrugEntity> updateDrug(@PathVariable DrugEntity drug,
                                                 @Valid @RequestBody DrugEntity drugEntity) {
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

    @DeleteMapping("/drug/{drug}")
    public ResponseEntity<DrugEntity> deleteDrug(@PathVariable DrugEntity drug) {
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
    public Iterable<DrugstoreEntity> getAllDrugstore(Pageable pageable) {
        return drugstoreDataControl.findAll(pageable);
    }

    @GetMapping("/drugstore/{drugstore}")
    public ResponseEntity<DrugstoreEntity> getDrugstore(@PathVariable DrugstoreEntity drugstore) {
        if (drugstore == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(drugstore);
    }

    @PostMapping("/drugstore")
    public ResponseEntity<DrugstoreEntity> createDrugstore(@Valid @RequestBody DrugstoreEntity drugstoreEntity) {
        try {
            drugstoreDataControl.saveOrUpdate(drugstoreEntity);
            eventService.updateDrugstores();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/drugstore/{drugstore}")
    public ResponseEntity<DrugstoreEntity> updateDrugstore(@PathVariable DrugstoreEntity drugstore,
                                                           @Valid @RequestBody DrugstoreEntity drugstoreEntity) {
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

    @DeleteMapping("/drugstore/{drugstore}")
    public ResponseEntity<DrugstoreEntity> deleteDrugstore(@PathVariable DrugstoreEntity drugstore) {
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

    //region PharmTerGroup
    @GetMapping("/pharmTerGroup")
    public Iterable<PharmTerGroupEntity> getAllPharmTerGroup() {
        return pharmTerGroupDataControl.getAll();
    }

    @GetMapping("/pharmTerGroup/{pharmTerGroup}")
    public ResponseEntity<PharmTerGroupEntity> getpharmTerGroup(@PathVariable PharmTerGroupEntity pharmTerGroup) {
        if (pharmTerGroup == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pharmTerGroup);
    }

    @PostMapping("/pharmTerGroup")
    public ResponseEntity<PharmTerGroupEntity> createPharmTerGroup(@Valid @RequestBody PharmTerGroupEntity pharmTerGroup) {
        try {
            pharmTerGroupDataControl.saveOrUpdate(pharmTerGroup);
            eventService.updatePharmTerGroups();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/pharmTerGroup/{pharmTerGroup}")
    public ResponseEntity<PharmTerGroupEntity> updatepharmTerGroup(@PathVariable PharmTerGroupEntity pharmTerGroup,
                                                                   @Valid @RequestBody PharmTerGroupEntity pharmTerGroupEntity) {
        if (pharmTerGroup == null) {
            return ResponseEntity.notFound().build();
        }
        pharmTerGroupEntity.setId(pharmTerGroup.getId());
        try {
            pharmTerGroupDataControl.saveOrUpdate(pharmTerGroupEntity);
            eventService.updatePharmTerGroups();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(pharmTerGroupEntity);
    }

    @DeleteMapping("/pharmTerGroup/{pharmTerGroup}")
    public ResponseEntity<PharmTerGroupEntity> deleteTherapeuticEffect(@PathVariable PharmTerGroupEntity pharmTerGroup) {
        if (pharmTerGroup == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            pharmTerGroupDataControl.delete(pharmTerGroup);
            eventService.updatePharmTerGroups();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
    //endregion

    //region Producer
    @GetMapping("/manufacturer")
    public Iterable<ProducerEntity> getAllProducers() {
        return producerDataControl.getAll();
    }

    @GetMapping("/manufacturer/{producer}")
    public ResponseEntity<ProducerEntity> getProducer(@PathVariable ProducerEntity producer) {
        if (producer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producer);
    }

    @PostMapping("/manufacturer")
    public ResponseEntity<ProducerEntity> createProducer(@Valid @RequestBody ProducerEntity producerEntity) {
        try {
            producerDataControl.saveOrUpdate(producerEntity);
            eventService.updateProducers();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/manufacturer/{producer}")
    public ResponseEntity<ProducerEntity> updateProducer(@PathVariable ProducerEntity producer,
                                                         @Valid @RequestBody ProducerEntity producerEntity) {
        if (producer == null) {
            return ResponseEntity.notFound().build();
        }
        producerEntity.setId(producer.getId());
        try {
            producerDataControl.saveOrUpdate(producerEntity);
            eventService.updateProducers();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(producerEntity);
    }

    @DeleteMapping("/manufacturer/{producer}")
    public ResponseEntity<ProducerEntity> deleteProducer(@PathVariable ProducerEntity producer) {
        if (producer == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            producerDataControl.delete(producer);
            eventService.updateProducers();
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
		String[] uuids = id.split("[&]");
        UUID drugId = UUID.fromString(uuids[0]);
        UUID drugstoreId = UUID.fromString(uuids[1]);

        PriceEntityPK pk = new PriceEntityPK(drugId, drugstoreId);

        PriceEntity price = priceDataControl.get(pk);
        if (price == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(price);
    }

    @PostMapping("/price")
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
        String[] uuids = id.split("[&]");
        UUID drugId = UUID.fromString(uuids[0]);
        UUID drugstoreId = UUID.fromString(uuids[1]);

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
        String[] uuids = id.split("[&]");
        UUID drugId = UUID.fromString(uuids[0]);
        UUID drugstoreId = UUID.fromString(uuids[1]);

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

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportFromDB(@RequestParam FormatType format) {
        try {
            String file = exporter.export(format, true);
            String filename = "export_data." + format.toString().toLowerCase();
            byte[] content = file.getBytes(StandardCharsets.UTF_8);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/" + format));
            headers.setContentDispositionFormData("attachment", filename);
            headers.setCacheControl("must-revalidate");

            return new ResponseEntity<>(content, headers, HttpStatus.OK);
        } catch (ExportException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/import")
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
