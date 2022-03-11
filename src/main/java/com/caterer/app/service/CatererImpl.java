package com.caterer.app.service;

import com.caterer.app.entity.Caterer;
import com.caterer.app.exception.CityNameException;
import com.caterer.app.exception.EmailAddressException;
import com.caterer.app.exception.NotFoundException;
import com.caterer.app.exception.NotValidGuestsException;
import com.caterer.app.repository.CatererRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.datatables.DataTablesInput;
import org.springframework.data.mongodb.datatables.DataTablesOutput;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import repositoryDataTables.CatererRepositoryDataTables;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CatererImpl implements CatererTemplate {

    private static final Logger logger = LogManager.getLogger(CatererImpl.class);

    @Autowired
    private CatererRepository catererRepository;

    @Autowired
    private CatererRepositoryDataTables catererRepositoryDataTables;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void validateInput(Caterer caterer) throws CityNameException, NotValidGuestsException, EmailAddressException {
        if (!caterer.getCityName().matches("(?<![\\S&&[^,]])[a-zA-Z]+(?![\\S&&[^,]])")) {
            logger.error("invalid city Name : " + caterer.getCityName());
            throw new CityNameException("invalid city Name");
        }
        if (caterer.getMaxGuests() < 0 || caterer.getMaxGuests() == null || caterer.getMinGuests() == null || caterer.getMinGuests() < 0) {
            logger.error("invalid number of Guests Name");
            throw new NotValidGuestsException("invalid number of Guests Name");
        }
        if (caterer.getContactInfo() != null && caterer.getContactInfo().getEmailAddress() != null && !caterer.getContactInfo().getEmailAddress().matches("(^(.+)@(\\S+)$)")) {
            logger.error("invalid email");
            throw new EmailAddressException("invalid email");
        }

    }

    //Find by criteria
    public Caterer findCaterer(Caterer caterer) throws NotFoundException {
        Optional<Caterer> findCateter =  catererRepository.findByName(caterer.getName());
        if(findCateter.isEmpty()){
            throw new NotFoundException("no record found");
        }
        return findCateter.get();
    }

    @Override
    public void addCaterer(Caterer caterer) {
        String uuid = caterer.getItemId();
        if(uuid == null || uuid.isEmpty()) {
             uuid = generateKey();
            Optional<List<Caterer>> catererList = catererRepository.findByItemId(uuid);
            if (catererList.isPresent() && !catererList.get().isEmpty()) {
                addCaterer(caterer);
            }
        }
        caterer.setItemId(uuid);
        logger.info("starting to Save item with uuid " + uuid);
        catererRepository.save(caterer);
        kafkaTemplate.send("caterer", "Saved uuid " + uuid);
        logger.info("kafka message is sent fot uuid : " + uuid);

    }

    public String generateKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public DataTablesOutput<Caterer> filterByNameOrId(DataTablesInput dataTablesInput) {
        return catererRepositoryDataTables.findAll(dataTablesInput);
    }

}
