package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.*;
import org.springframework.transaction.annotation.Transactional;

public interface DataControl {
    //region Drug
    Iterable<DrugEntity> getAllDrug();

    @Transactional
    boolean saveDrug(DrugEntity drug);

    @Transactional
    boolean deleteDrug(DrugEntity drug);

    @Transactional
    boolean updateDrug(DrugEntity drug);

    //region Drugstore
    Iterable<DrugstoreEntity> getAllDrugstore();

    @Transactional
    boolean saveDrugstore(DrugstoreEntity drugstore);

    @Transactional
    boolean deleteDrugstore(DrugstoreEntity drugstore);

    @Transactional
    boolean updateDrugstore(DrugstoreEntity drugstore);

    //region TherapeuticEffect
    Iterable<TherapeuticEffectEntity> getAllTherapeuticEffect();

    @Transactional
    boolean saveTherapeuticEffect(TherapeuticEffectEntity teffect);

    @Transactional
    boolean deleteTherapeuticEffect(TherapeuticEffectEntity teffect);

    @Transactional
    boolean updateTherapeuticEffect(TherapeuticEffectEntity teffect);

    //region PharmachologicEffect
    Iterable<PharmachologicEffectEntity> getAllPharmachologicEffect();

    @Transactional
    boolean savePharmachologicEffect(PharmachologicEffectEntity peffect);

    @Transactional
    boolean deletePharmachologicEffect(PharmachologicEffectEntity peffect);

    @Transactional
    boolean updatePharmachologicEffect(PharmachologicEffectEntity peffect);

    //region Price
    Iterable<PriceEntity> getAllPrice();

    @Transactional
    boolean savePrice(PriceEntity price);

    @Transactional
    boolean deletePrice(PriceEntity price);

    @Transactional
    boolean updatePrice(PriceEntity price);
}
