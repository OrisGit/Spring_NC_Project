package com.nc.netcracker_project.model.repositories;

import com.nc.netcracker_project.model.entities.DrugstoreEntity;
import com.nc.netcracker_project.model.entities.DrugstoreEntity;
import com.nc.netcracker_project.model.entities.PharmachologicEffectEntity;
import com.nc.netcracker_project.model.entities.TherapeuticEffectEntity;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

import static com.nc.netcracker_project.DataGenerator.createDrugstoreEntity;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class DrugStoreRepositoryTest {

    @Autowired
    private DrugStoreRepository drugStoreRepository;

    @Test
    public void saveTest(){
        DrugstoreEntity drugstore = createDrugstoreEntity();

        DrugstoreEntity res = drugStoreRepository.save(drugstore);

        assertThat(res).isEqualTo(drugstore);
    }

    @Test
    public void findAllTest(){
        DrugstoreEntity drugstoreEntity = createDrugstoreEntity();

        DrugstoreEntity res = drugStoreRepository.save(drugstoreEntity);

        int found = ((List<DrugstoreEntity>)drugStoreRepository.findAll()).size();
        assertThat(found).isEqualTo(1);
    }

    @Test
    public void deleteByObjectTest(){
        DrugstoreEntity drugstore = createDrugstoreEntity();

        DrugstoreEntity res = drugStoreRepository.save(drugstore);

        drugStoreRepository.delete(drugstore);
    }

    @Test
    public void deleteByUUIDTest(){
        DrugstoreEntity drugstore = createDrugstoreEntity();

        DrugstoreEntity res = drugStoreRepository.save(drugstore);

        drugStoreRepository.delete(drugstore.getId());
    }

    @Test
    public void findOneTest(){
        DrugstoreEntity drugstore = createDrugstoreEntity();
        drugStoreRepository.save(drugstore);

        DrugstoreEntity res = drugStoreRepository.findOne(drugstore.getId());

        assertThat(res).isEqualTo(drugstore);
    }
}
