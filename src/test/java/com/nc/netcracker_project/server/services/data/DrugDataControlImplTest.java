package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import com.nc.netcracker_project.server.model.repositories.DrugRepository;
import com.nc.netcracker_project.server.model.repositories.ProducerRepository;
import com.nc.netcracker_project.server.model.repositories.PharmTerGroupRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.nc.netcracker_project.DataGenerator.createDrugEntity;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("com.nc.netcracker_project.server.services.data.DrugDataControlImpl")
public class DrugDataControlImplTest {

    @Autowired
    private DrugRepository repository;
    @Autowired
    private PharmTerGroupRepository trepo;
    @Autowired
    private ProducerRepository prepo;

    private PharmTerGroupDataControl tEffectDataControl = new PharmTerGroupDataControlImpl(trepo);
    private ProducerDataControl pEffectDataControl = new ProducerDataControlImpl(prepo);
    private DrugDataControl drugDataControl = new DrugDataControlImpl(repository, tEffectDataControl, pEffectDataControl);

    @Test
    public void saveOrUpdate() throws Exception {
        DrugEntity drug = createDrugEntity();

        DrugEntity res = drugDataControl.saveOrUpdate(drug);

        assertThat(res).isEqualTo(drug);
    }

    @Test
    public void saveOrUpdate1() throws Exception {
        drugDataControl.saveOrUpdate(null);
    }

    @Test
    public void getAll() throws Exception {
        drugDataControl.saveOrUpdate(createDrugEntity());
        drugDataControl.saveOrUpdate(createDrugEntity());
        drugDataControl.saveOrUpdate(createDrugEntity());

        List<DrugEntity> res = (List<DrugEntity>) drugDataControl.getAll();

        assertThat(res.size()).isEqualTo(3);
    }

    @Test
    public void get() throws Exception {
        DrugEntity drug = createDrugEntity();

        drugDataControl.saveOrUpdate(drug);
        DrugEntity res = drugDataControl.get(drug.getId());

        assertThat(res).isEqualTo(drug);
    }

    @Test
    public void get1() throws Exception {
        drugDataControl.get(null);
    }

    @Test
    public void delete() throws Exception {
        DrugEntity drugEntity = drugDataControl.saveOrUpdate(createDrugEntity());
        drugDataControl.saveOrUpdate(createDrugEntity());

        drugDataControl.delete(drugEntity);

        assertThat(((List)drugDataControl.getAll()).size()).isEqualTo(1);

    }

    @Test
    public void delete1() throws Exception {
        drugDataControl.delete(null);
    }

    @Test
    public void exists() throws Exception {
        DrugEntity drug = createDrugEntity();

        drugDataControl.saveOrUpdate(drug);
        boolean res = drugDataControl.exists(drug.getId());

        assertThat(res).isEqualTo(true);
    }

    @Test
    public void exists1() throws Exception {
        drugDataControl.exists(null);
    }

    @Test
    public void deleteById() throws Exception {
        DrugEntity drugEntity = drugDataControl.saveOrUpdate(createDrugEntity());
        drugDataControl.saveOrUpdate(createDrugEntity());

        drugDataControl.deleteById(drugEntity.getId());

        assertThat(((List)drugDataControl.getAll()).size()).isEqualTo(1);
    }

    @Test
    public void deleteById1() throws Exception {
        drugDataControl.delete(null);
    }

}