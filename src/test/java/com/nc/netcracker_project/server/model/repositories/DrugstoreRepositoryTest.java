package com.nc.netcracker_project.server.model.repositories;

import com.nc.netcracker_project.server.model.entities.DrugstoreEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.nc.netcracker_project.DataGenerator.createDrugstoreEntity;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class DrugstoreRepositoryTest {

    @Autowired
    private DrugstoreRepository drugstoreRepository;

    @Test
    public void saveTest(){
        DrugstoreEntity drugstore = createDrugstoreEntity();

        DrugstoreEntity res = drugstoreRepository.save(drugstore);

        assertThat(res).isEqualTo(drugstore);
    }

    @Test
    public void findAllTest(){
        DrugstoreEntity drugstoreEntity = createDrugstoreEntity();

        DrugstoreEntity res = drugstoreRepository.save(drugstoreEntity);

        int found = ((List<DrugstoreEntity>) drugstoreRepository.findAll()).size();
        assertThat(found).isEqualTo(1);
    }

    @Test
    public void deleteByObjectTest(){
        DrugstoreEntity drugstore = createDrugstoreEntity();

        DrugstoreEntity res = drugstoreRepository.save(drugstore);

        drugstoreRepository.delete(drugstore);
    }

    @Test
    public void deleteByUUIDTest(){
        DrugstoreEntity drugstore = createDrugstoreEntity();

        DrugstoreEntity res = drugstoreRepository.save(drugstore);

        drugstoreRepository.delete(drugstore.getId());
    }

    @Test
    public void findOneTest(){
        DrugstoreEntity drugstore = createDrugstoreEntity();
        drugstoreRepository.save(drugstore);

        DrugstoreEntity res = drugstoreRepository.findOne(drugstore.getId());

        assertThat(res).isEqualTo(drugstore);
    }
}
