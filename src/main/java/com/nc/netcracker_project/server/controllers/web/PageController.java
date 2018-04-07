package com.nc.netcracker_project.server.controllers.web;

import com.nc.netcracker_project.desktop_rmi_client.entity.Drugstore;
import com.nc.netcracker_project.server.model.entities.*;
import com.nc.netcracker_project.server.services.data.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.function.Consumer;

@Controller
public class PageController {

    private static final Logger LOG = Logger.getLogger(WebController.class);

    private DrugDataControl drugDataControl;
    private DrugstoreDataControl drugstoreDataControl;
    private PriceDataControl priceDataControl;
    private ProducerDataControl producerDataControl;
    private PharmTerGroupDataControl pharmTerGroupDataControl;

    @Autowired
    public PageController(DrugDataControl drugDataControl, DrugstoreDataControl drugstoreDataControl,
                          PriceDataControl priceDataControl, ProducerDataControl producerDataControl,
                          PharmTerGroupDataControl pharmTerGroupDataControl) {
        this.drugDataControl = drugDataControl;
        this.drugstoreDataControl = drugstoreDataControl;
        this.priceDataControl = priceDataControl;
        this.producerDataControl = producerDataControl;
        this.pharmTerGroupDataControl = pharmTerGroupDataControl;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/drugstore/{drugstore}")
    public ModelAndView drugstorePage(@PathVariable DrugstoreEntity drugstore) {

        List<DrugEntity> drugs = new LinkedList<>();

        Iterable<PriceEntity> prices = priceDataControl.findByDrugstore(drugstore);
        prices.forEach(priceEntity -> {
            drugs.add(priceEntity.getDrug());
        });

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "drugstore");
        model.put("object", drugstore);
        model.put("tableType", "drug");
        model.put("tableContent", drugs);
        model.put("prices", prices);

        return new ModelAndView("object", model);
    }

    @GetMapping("/drug/{drug}")
    public ModelAndView drugPage(@PathVariable DrugEntity drug) {

        List<DrugstoreEntity> drugstores = new LinkedList<>();

        Iterable<PriceEntity> prices = priceDataControl.findByDrug(drug);
        prices.forEach(priceEntity -> {
            drugstores.add(priceEntity.getDrugstore());
        });

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "drug");
        model.put("object", drug);
        model.put("tableType", "drugstore");
        model.put("tableContent", drugstores);
        model.put("prices", prices);

        return new ModelAndView("object", model);
    }

    @GetMapping("/manufacturer/{manufacturer}")
    public ModelAndView manufacturerPage(@PathVariable ProducerEntity manufacturer) {

        Iterable<DrugEntity> drugs = drugDataControl.findByProducer(manufacturer);

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "manufacturer");
        model.put("object", manufacturer);
        model.put("tableType", "drug");
        model.put("tableContent", drugs);

        return new ModelAndView("object", model);
    }

    @GetMapping("/pharmTerGroup/{pharmTerGroup}")
    public ModelAndView pharmTerGroupPage(@PathVariable PharmTerGroupEntity pharmTerGroup) {

        Iterable<DrugEntity> drugs = drugDataControl.findByPharmTerGroup(pharmTerGroup);

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "pharmTerGroup");
        model.put("object", pharmTerGroup);
        model.put("tableType", "drug");
        model.put("tableContent", drugs);

        return new ModelAndView("object", model);
    }

    @GetMapping("/drugstore/{drugstore}/edit")
    public ModelAndView editDrugstorePage(@PathVariable DrugstoreEntity drugstore) {

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "drugstore");
        model.put("object", drugstore);

        return new ModelAndView("new", model);
    }

    @GetMapping("/drug/{drug}/edit")
    public ModelAndView editDrugPage(@PathVariable DrugEntity drug) {

        Iterable<ProducerEntity> manufacturers = producerDataControl.getAll();
        Iterable<PharmTerGroupEntity> pharmTerGroups = pharmTerGroupDataControl.getAll();

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "drug");
        model.put("object", drug);
        model.put("manufacturerList", manufacturers);
        model.put("pharmTerGroupList", pharmTerGroups);

        return new ModelAndView("new", model);
    }

    @GetMapping("/manufacturer/{manufacturer}/edit")
    public ModelAndView editManufacturerPage(@PathVariable ProducerEntity manufacturer) {

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "manufacturer");
        model.put("object", manufacturer);

        return new ModelAndView("new", model);
    }

    @GetMapping("/pharmTerGroup/{pharmTerGroup}/edit")
    public ModelAndView editPharmTerGroupPage(@PathVariable PharmTerGroupEntity pharmTerGroup) {

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "pharmTerGroup");
        model.put("object", pharmTerGroup);

        return new ModelAndView("new", model);
    }

    @GetMapping("/price/{id}/edit")
    public ModelAndView editPricePage(@PathVariable String id) {

        String[] uuids = id.split("[&]");
        //todo обработка неполного id для добавления по аптеке или лекарству
        UUID drugId = UUID.fromString(uuids[0]);
        UUID drugstoreId = UUID.fromString(uuids[1]);

        Iterable<DrugEntity> drugs = drugDataControl.getAll();
        Iterable<DrugstoreEntity> drugstores = drugstoreDataControl.getAll();

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "price");
        model.put("object", priceDataControl.get(new PriceEntityPK(drugId, drugstoreId)));
        model.put("drugList", drugs);
        model.put("drugstoreList", drugstores);

        return new ModelAndView("new", model);
    }

    @GetMapping("/{type}")
    public ModelAndView create(@PathVariable String type) throws Exception {

        if (!type.equalsIgnoreCase("drugstore") && !type.equalsIgnoreCase("manufacturer")
                && !type.equalsIgnoreCase("pharmTerGroup")) {
            throw new Exception();
        }

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", type);

        return new ModelAndView("new", model);
    }

    @GetMapping("/drug")
    public ModelAndView createDrug() {

        Iterable<ProducerEntity> manufacturers = producerDataControl.getAll();
        Iterable<PharmTerGroupEntity> pharmTerGroups = pharmTerGroupDataControl.getAll();

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "drug");
        model.put("manufacturerList", manufacturers);
        model.put("pharmTerGroupList", pharmTerGroups);

        return new ModelAndView("new", model);
    }

    @GetMapping("/price")
    public ModelAndView createPrice() {

        Iterable<DrugEntity> drugs = drugDataControl.getAll();
        Iterable<DrugstoreEntity> drugstores = drugstoreDataControl.getAll();

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "price");
        model.put("drugList", drugs);
        model.put("drugstoreList", drugstores);

        return new ModelAndView("new", model);
    }

    @GetMapping("/drugstores")
    public ModelAndView drugstoresPage() {
        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "drugstore");
        model.put("tableType", "drugstore");
        return new ModelAndView("search", model);
    }

    @GetMapping("/drugs")
    public ModelAndView drugsPage() {
        Iterable<ProducerEntity> manufacturers = producerDataControl.getAll();
        Iterable<PharmTerGroupEntity> pharmTerGroups = pharmTerGroupDataControl.getAll();

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "drug");
        model.put("tableType", "drug");
        model.put("manufacturerList", manufacturers);
        model.put("pharmTerGroupList", pharmTerGroups);

        return new ModelAndView("search", model);
    }
}
