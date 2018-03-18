package com.nc.netcracker_project.server.model.repositories;

import com.nc.netcracker_project.server.model.entities.ProducerEntity;
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
public class ProducerRepositoryTest {

    @Autowired
    private ProducerRepository pEffectRepository;
    
    @Test
    public void saveTest(){
        ProducerEntity pEffect = createPharmachologicEffectEntity();

        ProducerEntity res = pEffectRepository.save(pEffect);

        assertThat(res).isEqualTo(pEffect);
    }

    @Test
    public void findAllTest(){
        ProducerEntity ProducerEntity = createPharmachologicEffectEntity();

        ProducerEntity res = pEffectRepository.save(ProducerEntity);

        int found = ((List<ProducerEntity>) pEffectRepository.findAll()).size();
        assertThat(found).isEqualTo(1);
    }

    @Test
    public void deleteByObjectTest(){
        ProducerEntity pEffect = createPharmachologicEffectEntity();

        ProducerEntity res = pEffectRepository.save(pEffect);

        pEffectRepository.delete(pEffect);
    }

    @Test
    public void deleteByUUIDTest(){
        ProducerEntity pEffect = createPharmachologicEffectEntity();

        ProducerEntity res = pEffectRepository.save(pEffect);

        pEffectRepository.delete(pEffect.getId());
    }

    @Test
    public void findOneTest(){
        ProducerEntity pEffect = createPharmachologicEffectEntity();
        pEffectRepository.save(pEffect);

        ProducerEntity res = pEffectRepository.findOne(pEffect.getId());

        assertThat(res).isEqualTo(pEffect);
    }
}
