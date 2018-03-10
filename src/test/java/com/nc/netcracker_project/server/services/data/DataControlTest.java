package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.DataGenerator;
import com.nc.netcracker_project.server.model.entities.DrugEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataControlTest {

    @Test
    public void saveDrugTest(){
        DrugEntity drugEntity = DataGenerator.createDrugEntity();


    }
}
