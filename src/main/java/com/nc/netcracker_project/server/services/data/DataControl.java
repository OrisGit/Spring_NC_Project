package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface DataControl {
    //region Drug
    Iterable<DrugEntity> getAllDrug();

    DrugEntity getDrug(UUID id);

    @Transactional
    boolean saveDrug(DrugEntity drug);

    @Transactional
    boolean deleteDrug(DrugEntity drug);

    @Transactional
    boolean updateDrug(DrugEntity drug);

    //region Drugstore
    Iterable<DrugstoreEntity> getAllDrugstore();

    DrugstoreEntity getDrugstore(UUID id);

    @Transactional
    boolean saveDrugstore(DrugstoreEntity drugstore);

    @Transactional
    boolean deleteDrugstore(DrugstoreEntity drugstore);

    @Transactional
    boolean updateDrugstore(DrugstoreEntity drugstore);

    //region TherapeuticEffect
    Iterable<TherapeuticEffectEntity> getAllTherapeuticEffect();

    TherapeuticEffectEntity getTherapeuticEffect(UUID id);

    @Transactional
    boolean saveTherapeuticEffect(TherapeuticEffectEntity teffect);

    @Transactional
    boolean deleteTherapeuticEffect(TherapeuticEffectEntity teffect);

    @Transactional
    boolean updateTherapeuticEffect(TherapeuticEffectEntity teffect);

    //region PharmachologicEffect
    Iterable<PharmachologicEffectEntity> getAllPharmachologicEffect();

    PharmachologicEffectEntity getPharmachologicEffect(UUID id);

    @Transactional
    boolean savePharmachologicEffect(PharmachologicEffectEntity peffect);

    @Transactional
    boolean deletePharmachologicEffect(PharmachologicEffectEntity peffect);

    @Transactional
    boolean updatePharmachologicEffect(PharmachologicEffectEntity peffect);

    //region Price
    Iterable<PriceEntity> getAllPrice();

    PriceEntity getPrice(PriceEntityPK pk);

    @Transactional
    boolean savePrice(PriceEntity price);

    @Transactional
    boolean deletePrice(PriceEntity price);

    @Transactional
    boolean updatePrice(PriceEntity price);
}
