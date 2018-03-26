package com.nc.netcracker_project.server.controllers.web;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import com.nc.netcracker_project.server.model.entities.DrugstoreEntity;
import com.nc.netcracker_project.server.services.data.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class PageController {

    private static final Logger LOG = Logger.getLogger(WebController.class);

    private DrugDataControl drugDataControl;
    private DrugstoreDataControl drugstoreDataControl;
    private PriceDataControl priceDataControl;
    private PharmachologicEffectDataControl pEffectDataControl;
    private TherapeuticEffectDataControl tEffectDataControl;

    @Autowired
    public PageController(DrugDataControl drugDataControl, DrugstoreDataControl drugstoreDataControl,
                          PriceDataControl priceDataControl, PharmachologicEffectDataControl pEffectDataControl,
                          TherapeuticEffectDataControl tEffectDataControl) {
        this.drugDataControl = drugDataControl;
        this.drugstoreDataControl = drugstoreDataControl;
        this.priceDataControl = priceDataControl;
        this.pEffectDataControl = pEffectDataControl;
        this.tEffectDataControl = tEffectDataControl;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/drugstore/{drugstore}")
    public ModelAndView drugstorePage(@PathVariable DrugstoreEntity drugstore) {

        Iterable<DrugEntity> drugs = drugDataControl.getAll();

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "drugstore");
        model.put("object", drugstore);
        model.put("tableType", "drug");
        model.put("tableContent", drugs);

        return new ModelAndView("object", model);
    }

    @GetMapping("/drug/{drug}")
    public ModelAndView drugPage(@PathVariable DrugEntity drug) {

        Iterable<DrugstoreEntity> drugstores = drugstoreDataControl.getAll();

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "drug");
        model.put("object", drug);
        model.put("tableType", "drugstore");
        model.put("tableContent", drugstores);

        return new ModelAndView("object", model);
    }

    @GetMapping("/manufacturer/{manufacturer}")
    public ModelAndView manufacturerPage(@PathVariable String manufacturer) {

        Iterable<DrugEntity> drugs = drugDataControl.getAll();

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "manufacturer");
        //model.put("object", manufacturer);
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

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "drug");
        model.put("object", drug);

        return new ModelAndView("new", model);
    }

    @GetMapping("/manufacturer/{manufacturer}/edit")
    public ModelAndView editManufacturerPage(@PathVariable String manufacturer) {

        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "manufacturer");
        //model.put("object", manufacturer);

        return new ModelAndView("new", model);
    }

    @GetMapping("/create/{type}")
    public ModelAndView create(@PathVariable String type) {
        if (!type.equalsIgnoreCase("drug") && !type.equalsIgnoreCase("drugstore")
                && !type.equalsIgnoreCase("price") && !type.equalsIgnoreCase("manufacturer")) {
            return null;
        }
        Map<String, Object> model = new HashMap<>();
        model.put("objectType", type);
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
        Map<String, Object> model = new HashMap<>();
        model.put("objectType", "drug");
        model.put("tableType", "drug");
        return new ModelAndView("search", model);
    }
}
