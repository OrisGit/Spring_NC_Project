package com.nc.netcracker_project.model.repositories;

import com.nc.netcracker_project.model.entities.TherapeuticEffectEntity;
import org.fluttercode.datafactory.impl.DataFactory;
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
public class TherapeuticEffectRepositoryTest {

    @Autowired
    private TherapeuticEffectRepository tEffectRepository;

    @Test
    public void saveTest(){
        TherapeuticEffectEntity pEffect = createTherapeuticEffectEntity();

        TherapeuticEffectEntity res = tEffectRepository.save(pEffect);

        assertThat(res).isEqualTo(pEffect);
    }

    @Test
    public void findAllTest(){
        TherapeuticEffectEntity TherapeuticEffectEntity = createTherapeuticEffectEntity();

        TherapeuticEffectEntity res = tEffectRepository.save(TherapeuticEffectEntity);

        int found = ((List<TherapeuticEffectEntity>) tEffectRepository.findAll()).size();
        assertThat(found).isEqualTo(1);
    }

    @Test
    public void deleteByObjectTest(){
        TherapeuticEffectEntity pEffect = createTherapeuticEffectEntity();

        TherapeuticEffectEntity res = tEffectRepository.save(pEffect);

        tEffectRepository.delete(pEffect);
    }

    @Test
    public void deleteByUUIDTest(){
        TherapeuticEffectEntity pEffect = createTherapeuticEffectEntity();

        TherapeuticEffectEntity res = tEffectRepository.save(pEffect);

        tEffectRepository.delete(pEffect.getId());
    }

    @Test
    public void findOneTest(){
        TherapeuticEffectEntity pEffect = createTherapeuticEffectEntity();
        tEffectRepository.save(pEffect);

        TherapeuticEffectEntity res = tEffectRepository.findOne(pEffect.getId());

        assertThat(res).isEqualTo(pEffect);
    }
}
