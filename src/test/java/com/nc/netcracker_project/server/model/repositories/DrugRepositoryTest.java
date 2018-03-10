package com.nc.netcracker_project.server.model.repositories;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import com.nc.netcracker_project.server.model.entities.PharmachologicEffectEntity;
import com.nc.netcracker_project.server.model.entities.TherapeuticEffectEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.nc.netcracker_project.DataGenerator.createDrugEntity;
import static com.nc.netcracker_project.DataGenerator.createPharmachologicEffectEntity;
import static com.nc.netcracker_project.DataGenerator.createTherapeuticEffectEntity;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DrugRepositoryTest {

    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private TherapeuticEffectRepository tr;
    @Autowired
    private PharmachologicEffectRepository pr;

    @Test
    public void saveTest(){
        DrugEntity drug = createDrugEntity();

        DrugEntity res = drugRepository.save(drug);

        assertThat(res).isEqualTo(drug);
    }

    @Test
    public void findAllTest(){
        DrugEntity drug = createDrugEntity();

        DrugEntity res = drugRepository.save(drug);

        int found = ((List<DrugEntity>)drugRepository.findAll()).size();
        assertThat(found).isEqualTo(1);
    }

    @Test
    public void deleteByObjectTest(){
        DrugEntity drug = createDrugEntity();

        DrugEntity res = drugRepository.save(drug);

        drugRepository.delete(drug);
    }

    @Test
    public void deleteByUUIDTest(){
        DrugEntity drug = createDrugEntity();

        DrugEntity res = drugRepository.save(drug);

        drugRepository.delete(drug.getId());
    }

    @Test
    public void findOneTest(){
        DrugEntity drug = createDrugEntity();
        drugRepository.save(drug);

        DrugEntity res = drugRepository.findOne(drug.getId());

        assertThat(res).isEqualTo(drug);
    }

    @Test
    public void updateTest() {
        DrugEntity drug = createDrugEntity();
        drugRepository.save(drug);
        DrugEntity res = drugRepository.findOne(drug.getId());
        res.setName("Афлубин");
        drugRepository.save(res);
        res = drugRepository.findOne(drug.getId());
        assertThat(res.getName()).isEqualTo("Афлубин");
    }

    @Test
    public void saveTest2(){
        PharmachologicEffectEntity p = createPharmachologicEffectEntity();
        TherapeuticEffectEntity t = createTherapeuticEffectEntity();

        pr.save(p);
        tr.save(t);

        DrugEntity drug = createDrugEntity();
        drug.setTherapeuticEffect(t);
        drug.setPharmachologicEffect(p);
        DrugEntity res = drugRepository.save(drug);

        assertThat(res).isEqualTo(drug);
    }

}
