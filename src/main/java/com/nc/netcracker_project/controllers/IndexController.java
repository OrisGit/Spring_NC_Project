package com.nc.netcracker_project.controllers;

import com.nc.netcracker_project.model.repositories.PriceRepository;
import com.nc.netcracker_project.model.entities.PriceEntity;
import com.nc.netcracker_project.services.import_export.ExportException;
import com.nc.netcracker_project.services.import_export.Exporter;
import com.nc.netcracker_project.services.import_export.FormatType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    private final PriceRepository drugRepository;
    private final Exporter exporter;

    public IndexController(PriceRepository drugRepository, Exporter exporter) {
        this.drugRepository = drugRepository;
        this.exporter = exporter;
    }

    @GetMapping("/")
    public ModelAndView index() throws ExportException {
        String export = exporter.export(FormatType.JSON,true);
        Map<String,String> model = new HashMap<>();
        model.put("name",export);
        return new ModelAndView("index",model);
    }
}
