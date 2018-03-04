package com.nc.netcracker_project;

import com.nc.netcracker_project.model.entities.*;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataGenerator {

    private static final DataFactory df = new DataFactory();

    public static DrugEntity createDrugEntity(){
        TherapeuticEffectEntity therapeuticEffectEntity = createTherapeuticEffectEntity();
        PharmachologicEffectEntity pharmachologicEffectEntity = createPharmachologicEffectEntity();
        return new DrugEntity(df.getRandomText(1,255),df.getRandomText(1,255),df.getRandomText(1,255),
                df.getRandomText(1,255),pharmachologicEffectEntity,therapeuticEffectEntity,df.getRandomText(1,255));
    }

    public static DrugstoreEntity createDrugstoreEntity(){
        return new DrugstoreEntity(df.getRandomText(1,255),df.getRandomText(1,255),df.getRandomText(1,255),df.getRandomText(1,255),
                (long)df.getNumberBetween(100000,10000000),df.getRandomText(1,255),(short)1);
    }

    public static PharmachologicEffectEntity createPharmachologicEffectEntity(){
        return new PharmachologicEffectEntity(df.getRandomText(1,255),df.getRandomText(1,255));

    }

    public static TherapeuticEffectEntity createTherapeuticEffectEntity(){
        return new TherapeuticEffectEntity(df.getRandomText(1,255),df.getRandomText(1,255));

    }

    public static PriceEntity createPriceEntity(){
        return new PriceEntity(createDrugEntity(),createDrugstoreEntity(),(long)df.getNumberBetween(1,10000));
    }
}
