package com.nc.netcracker_project.server.model.repositories;

import com.nc.netcracker_project.server.model.entities.PharmachologicEffectEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.nc.netcracker_project.DataGenerator.createPharmachologicEffectEntity;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PharmachologicEffectRepositoryTest {

    @Autowired
    private PharmachologicEffectRepository pEffectRepository;
    
    @Test
    public void saveTest(){
        PharmachologicEffectEntity pEffect = createPharmachologicEffectEntity();

        PharmachologicEffectEntity res = pEffectRepository.save(pEffect);

        assertThat(res).isEqualTo(pEffect);
    }

    @Test
    public void findAllTest(){
        PharmachologicEffectEntity PharmachologicEffectEntity = createPharmachologicEffectEntity();

        PharmachologicEffectEntity res = pEffectRepository.save(PharmachologicEffectEntity);

        int found = ((List<PharmachologicEffectEntity>) pEffectRepository.findAll()).size();
        assertThat(found).isEqualTo(1);
    }

    @Test
    public void deleteByObjectTest(){
        PharmachologicEffectEntity pEffect = createPharmachologicEffectEntity();

        PharmachologicEffectEntity res = pEffectRepository.save(pEffect);

        pEffectRepository.delete(pEffect);
    }

    @Test
    public void deleteByUUIDTest(){
        PharmachologicEffectEntity pEffect = createPharmachologicEffectEntity();

        PharmachologicEffectEntity res = pEffectRepository.save(pEffect);

        pEffectRepository.delete(pEffect.getId());
    }

    @Test
    public void findOneTest(){
        PharmachologicEffectEntity pEffect = createPharmachologicEffectEntity();
        pEffectRepository.save(pEffect);

        PharmachologicEffectEntity res = pEffectRepository.findOne(pEffect.getId());

        assertThat(res).isEqualTo(pEffect);
    }
}
