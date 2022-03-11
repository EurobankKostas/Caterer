package com.caterer.app.service;

import com.caterer.app.entity.Caterer;
import com.caterer.app.exception.CityNameException;
import com.caterer.app.exception.EmailAddressException;
import com.caterer.app.exception.NotValidGuestsException;
import org.springframework.data.mongodb.datatables.DataTablesInput;
import org.springframework.data.mongodb.datatables.DataTablesOutput;


public interface CatererTemplate {
    void validateInput(Caterer caterer) throws CityNameException, NotValidGuestsException, EmailAddressException;
    void addCaterer(Caterer caterer);

     DataTablesOutput<Caterer> filterByNameOrId (DataTablesInput dataTablesInput);
}
