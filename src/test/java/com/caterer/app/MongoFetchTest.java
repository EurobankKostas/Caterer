package com.caterer.app;

import com.caterer.app.entity.Caterer;
import com.caterer.app.entity.ContactInfo;
import com.caterer.app.exception.NotFoundException;
import com.caterer.app.repository.CatererRepository;
import com.caterer.app.service.CatererImpl;
import com.caterer.app.service.CatererTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoFetchTest {

    private static final Logger logger = LogManager.getLogger(MongoFetchTest.class);

    @Autowired
    private CatererTemplate catererTemplate;

    @Autowired
    private CatererRepository catererRepository;

    // Test testSaveCaterer
    @Test
    public void testSaveCaterer()  {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setEmailAddress("makis@gmail.com");
        Caterer caterer = Caterer.builder().cityName("Praga").itemId("70e46f713cda4fe489017cde3d481e7a").name("Lucas").minGuests(10).maxGuests(100).contactInfo(contactInfo).build();
        catererTemplate.addCaterer(caterer);
        Optional<List<Caterer>> result = catererRepository.findByItemId("70e46f713cda4fe489017cde3d481e7a");
        logger.info("Name in database : Name in request : " +caterer.getName());
    }
}
