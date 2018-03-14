package com.nc.netcracker_project.desktop_rmi_client.util;


import com.nc.netcracker_project.desktop_rmi_client.entity.Drug;
import com.nc.netcracker_project.desktop_rmi_client.entity.Drugstore;
import com.nc.netcracker_project.desktop_rmi_client.entity.Price;
import com.nc.netcracker_project.server.model.entities.DrugEntity;
import com.nc.netcracker_project.server.model.entities.DrugstoreEntity;
import com.nc.netcracker_project.server.model.entities.PriceEntity;

import java.util.LinkedList;
import java.util.List;

public class Mapper {

    public static Drug from(DrugEntity drugEntity) {
        return new Drug(drugEntity);
    }

    public static Drugstore from(DrugstoreEntity drugstoreEntity) {
        return new Drugstore(drugstoreEntity);
    }

    public static Price from(PriceEntity priceEntity) {
        return new Price(priceEntity);
    }

    public static LinkedList fromAll(List entities) {
        LinkedList list = new LinkedList<>();
        for (Object entity : entities) {
            if (entity instanceof DrugstoreEntity) list.add(Mapper.from((DrugstoreEntity) entity));
            if (entity instanceof DrugEntity) list.add(Mapper.from((DrugEntity) entity));
            if (entity instanceof PriceEntity) list.add(Mapper.from((PriceEntity) entity));
        }
        return list;
    }
}
