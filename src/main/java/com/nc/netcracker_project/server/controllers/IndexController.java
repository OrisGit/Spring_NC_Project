package com.nc.netcracker_project.server.controllers;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import com.nc.netcracker_project.server.model.entities.PharmachologicEffectEntity;
import com.nc.netcracker_project.server.model.entities.TherapeuticEffectEntity;
import com.nc.netcracker_project.server.model.repositories.DrugRepository;
import com.nc.netcracker_project.server.model.repositories.PharmachologicEffectRepository;
import com.nc.netcracker_project.server.services.import_export.ExportException;
import com.nc.netcracker_project.server.services.import_export.Exporter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    private final DrugRepository drugRepository;
    private final Exporter exporter;
    private final PharmachologicEffectRepository pEffectRepository;

    public IndexController(DrugRepository drugRepository, Exporter exporter, PharmachologicEffectRepository pEffectRepository) {
        this.drugRepository = drugRepository;
        this.exporter = exporter;
        this.pEffectRepository = pEffectRepository;
    }

    @GetMapping("/")
    public ModelAndView index() throws ExportException {
        TherapeuticEffectEntity therapeuticEffectEntity = new TherapeuticEffectEntity("1","1");
        PharmachologicEffectEntity pharmachologicEffectEntity = new PharmachologicEffectEntity("1","1");
        DrugEntity drug = new DrugEntity("1","1","1","1",pharmachologicEffectEntity,therapeuticEffectEntity,"1");

        DrugEntity res = drugRepository.save(drug);
        Map<String,String> model = new HashMap<>();
        model.put("name","save ok");
        return new ModelAndView("index",model);
    }
}
