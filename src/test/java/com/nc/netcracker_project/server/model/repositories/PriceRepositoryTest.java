package com.nc.netcracker_project.server.model.repositories;

import com.nc.netcracker_project.server.model.entities.PriceEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.nc.netcracker_project.DataGenerator.createPriceEntity;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private DrugStoreRepository drugStoreRepository;


    private PriceEntity save(PriceEntity price){
        drugRepository.save(price.getDrug());
        drugStoreRepository.save(price.getDrugstore());
        return priceRepository.save(price);
    }

    @Test
    public void saveTest(){
        PriceEntity price = createPriceEntity();

        PriceEntity res = save(price);

        assertThat(res).isEqualTo(price);
    }

    @Test
    public void findAllTest(){
        PriceEntity priceEntity = createPriceEntity();

        save(priceEntity);

        int found = ((List<PriceEntity>) priceRepository.findAll()).size();
        assertThat(found).isEqualTo(1);
    }

    @Test
    public void deleteByObjectTest(){
        PriceEntity priceEntity = createPriceEntity();

        save(priceEntity);

        priceRepository.delete(priceEntity);
    }

    @Test
    public void deleteByUUIDTest(){
        PriceEntity priceEntity = createPriceEntity();

        save(priceEntity);

        priceRepository.delete(priceEntity.getId());
    }

    @Test
    public void findOneTest(){
        PriceEntity priceEntity = createPriceEntity();
        save(priceEntity);

        PriceEntity res = priceRepository.findOne(priceEntity.getId());

        assertThat(res).isEqualTo(priceEntity);
    }
}
