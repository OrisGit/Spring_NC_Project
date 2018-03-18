package com.nc.netcracker_project.server.model.repositories;

import com.nc.netcracker_project.server.model.entities.PharmTerGroupEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.nc.netcracker_project.DataGenerator.createTherapeuticEffectEntity;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PharmTerGroupRepositoryTest {

    @Autowired
    private PharmTerGroupRepository tEffectRepository;

    @Test
    public void saveTest(){
        PharmTerGroupEntity pEffect = createTherapeuticEffectEntity();

        PharmTerGroupEntity res = tEffectRepository.save(pEffect);

        assertThat(res).isEqualTo(pEffect);
    }

    @Test
    public void findAllTest(){
        PharmTerGroupEntity PharmTerGroupEntity = createTherapeuticEffectEntity();

        PharmTerGroupEntity res = tEffectRepository.save(PharmTerGroupEntity);

        int found = ((List<PharmTerGroupEntity>) tEffectRepository.findAll()).size();
        assertThat(found).isEqualTo(1);
    }

    @Test
    public void deleteByObjectTest(){
        PharmTerGroupEntity pEffect = createTherapeuticEffectEntity();

        PharmTerGroupEntity res = tEffectRepository.save(pEffect);

        tEffectRepository.delete(pEffect);
    }

    @Test
    public void deleteByUUIDTest(){
        PharmTerGroupEntity pEffect = createTherapeuticEffectEntity();

        PharmTerGroupEntity res = tEffectRepository.save(pEffect);

        tEffectRepository.delete(pEffect.getId());
    }

    @Test
    public void findOneTest(){
        PharmTerGroupEntity pEffect = createTherapeuticEffectEntity();
        tEffectRepository.save(pEffect);

        PharmTerGroupEntity res = tEffectRepository.findOne(pEffect.getId());

        assertThat(res).isEqualTo(pEffect);
    }
}
