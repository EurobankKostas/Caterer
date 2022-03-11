package com.caterer.app.controller;

import com.caterer.app.entity.Caterer;
import com.caterer.app.exception.CityNameException;
import com.caterer.app.exception.EmailAddressException;
import com.caterer.app.exception.NotValidGuestsException;
import com.caterer.app.service.CatererTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.datatables.DataTablesInput;
import org.springframework.data.mongodb.datatables.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/caterer/v1/")
public class CatererController {
    private static final Logger logger = LogManager.getLogger(CatererController.class);

    @Autowired
    private CatererTemplate catererTemplate;

    @GetMapping("/")
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("datatables.html");
        return modelAndView;
    }

    @GetMapping("register")
    public ModelAndView register () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register.html");
        return modelAndView;
    }

    @PostMapping("/create")
    @CrossOrigin
    public ResponseEntity<Void> saveCaterer(@RequestBody Caterer caterer) throws CityNameException, NotValidGuestsException, EmailAddressException {
        logger.info("Invoking saveCaterer");
        catererTemplate.validateInput(caterer);
        logger.info("passed validations successfully");
        catererTemplate.addCaterer(caterer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find")
    @CrossOrigin
    @Cacheable(value = "customer")
    public DataTablesOutput<Caterer> find(@Valid DataTablesInput input){
        logger.info("Invoking find : with Datatables input");
        return catererTemplate.filterByNameOrId(input);
    }

}
