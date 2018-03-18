package com.nc.netcracker_project;

import com.nc.netcracker_project.server.model.entities.*;
import org.fluttercode.datafactory.impl.DataFactory;

public class DataGenerator {

    private static final DataFactory df = new DataFactory();

    public static DrugEntity createDrugEntity(){
        PharmTerGroupEntity pharmTerGroupEntity = createTherapeuticEffectEntity();
        ProducerEntity producerEntity = createPharmachologicEffectEntity();
        return new DrugEntity(df.getRandomText(1,255),df.getRandomText(1,255),df.getRandomText(1,255),
                df.getRandomText(1,255), producerEntity, pharmTerGroupEntity,df.getRandomText(1,255));
    }

    public static DrugstoreEntity createDrugstoreEntity(){
        return new DrugstoreEntity(df.getRandomText(1,255),df.getRandomText(1,255),df.getRandomText(1,255),df.getRandomText(1,255),
                df.getRandomText(1,255),df.getRandomText(1,255),(short)1);
    }

    public static ProducerEntity createPharmachologicEffectEntity(){
        return new ProducerEntity(df.getRandomText(1,255),df.getRandomText(1,255));

    }

    public static PharmTerGroupEntity createTherapeuticEffectEntity(){
        return new PharmTerGroupEntity(df.getRandomText(1,255),df.getRandomText(1,255));

    }

    public static PriceEntity createPriceEntity(){
        return new PriceEntity(createDrugEntity(),createDrugstoreEntity(),(long)df.getNumberBetween(1,10000));
    }
}
