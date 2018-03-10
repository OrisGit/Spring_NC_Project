package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.*;
import com.nc.netcracker_project.server.model.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("prototype")
public class DataControlImpl implements DataControl {

    private DrugRepository drugRepository;
    private DrugStoreRepository drugStoreRepository;
    private PharmachologicEffectRepository pEffectRepository;
    private TherapeuticEffectRepository tEffectRepository;
    private PriceRepository priceRepository;

    @Autowired
    public DataControlImpl(DrugRepository drugRepository, DrugStoreRepository drugStoreRepository,
                           PharmachologicEffectRepository pEffectRepository, TherapeuticEffectRepository tEffectRepository, PriceRepository priceRepository) {
        this.drugRepository = drugRepository;
        this.drugStoreRepository = drugStoreRepository;
        this.pEffectRepository = pEffectRepository;
        this.tEffectRepository = tEffectRepository;
        this.priceRepository = priceRepository;
    }

    //region Drug
    @Override
    public Iterable<DrugEntity> getAllDrug(){
        return drugRepository.findAll();
    }

    @Override
    public boolean saveDrug(DrugEntity drug){
        try {
            if(!pEffectRepository.exists(drug.getPharmachologicEffect().getId()))
                pEffectRepository.save(drug.getPharmachologicEffect());
            if(!tEffectRepository.exists(drug.getTherapeuticEffect().getId()))
                tEffectRepository.save(drug.getTherapeuticEffect());
            drugRepository.save(drug);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteDrug(DrugEntity drug){
        try{
            drugRepository.delete(drug);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean updateDrug(DrugEntity drug){
        try{
            if(drugRepository.exists(drug.getId())){
                drugRepository.save(drug);
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    //endregion

    //region Drugstore
    @Override
    public Iterable<DrugstoreEntity> getAllDrugstore(){
        return drugStoreRepository.findAll();
    }

    @Override
    @Transactional
    public boolean saveDrugstore(DrugstoreEntity drugstore){
        try {
            drugStoreRepository.save(drugstore);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteDrugstore(DrugstoreEntity drugstore){
        try{
            drugStoreRepository.delete(drugstore);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean updateDrugstore(DrugstoreEntity drugstore){
        try{
            if(drugStoreRepository.exists(drugstore.getId())){
                drugStoreRepository.save(drugstore);
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
    //endregion

    //region TherapeuticEffect
    @Override
    public Iterable<TherapeuticEffectEntity> getAllTherapeuticEffect(){
        return tEffectRepository.findAll();
    }

    @Override
    @Transactional
    public boolean saveTherapeuticEffect(TherapeuticEffectEntity teffect){
        try {
            tEffectRepository.save(teffect);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteTherapeuticEffect(TherapeuticEffectEntity teffect){
        try{
            tEffectRepository.delete(teffect);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean updateTherapeuticEffect(TherapeuticEffectEntity teffect){
        try{
            if(tEffectRepository.exists(teffect.getId())){
                tEffectRepository.save(teffect);
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
    //endregion

    //region PharmachologicEffect
    @Override
    public Iterable<PharmachologicEffectEntity> getAllPharmachologicEffect(){
        return pEffectRepository.findAll();
    }

    @Override
    @Transactional
    public boolean savePharmachologicEffect(PharmachologicEffectEntity peffect){
        try {
            pEffectRepository.save(peffect);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deletePharmachologicEffect(PharmachologicEffectEntity peffect){
        try{
            pEffectRepository.delete(peffect);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean updatePharmachologicEffect(PharmachologicEffectEntity peffect){
        try{
            if(pEffectRepository.exists(peffect.getId())){
                pEffectRepository.save(peffect);
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
    //endregion

    //region Price
    @Override
    public Iterable<PriceEntity> getAllPrice(){
        return priceRepository.findAll();
    }

    @Override
    @Transactional
    public boolean savePrice(PriceEntity price){
        try{
            drugRepository.save(price.getDrug());
            drugStoreRepository.save(price.getDrugstore());
            priceRepository.save(price);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deletePrice(PriceEntity price){
        try{
            priceRepository.delete(price);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean updatePrice(PriceEntity price){
        try{
            if(priceRepository.exists(price.getId())){
                drugRepository.save(price.getDrug());
                drugStoreRepository.save(price.getDrugstore());
                priceRepository.save(price);
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
    //endregion
}
