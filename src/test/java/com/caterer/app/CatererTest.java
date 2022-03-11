package com.caterer.app;


import com.caterer.app.entity.Caterer;
import com.caterer.app.entity.ContactInfo;
import com.caterer.app.exception.CityNameException;
import com.caterer.app.exception.EmailAddressException;
import com.caterer.app.exception.NotFoundException;
import com.caterer.app.exception.NotValidGuestsException;
import com.caterer.app.repository.CatererRepository;
import com.caterer.app.service.CatererImpl;
import com.caterer.app.service.CatererTemplate;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


@RunWith(MockitoJUnitRunner.class)
public class CatererTest {

    @Mock
    private Caterer caterer;
    @Mock
    private ContactInfo contactInfo;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @InjectMocks
    private CatererImpl catererImpl;


    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    // Test validation cityName
    @Test
    public void testInvalidCityName() {
        Mockito.when(caterer.getCityName()).thenReturn("Athens");
        Mockito.when(caterer.getName()).thenReturn("Lukas");
        Mockito.when(caterer.getMinGuests()).thenReturn(7);
        Mockito.when(caterer.getMaxGuests()).thenReturn(100);
        Mockito.when(caterer.getContactInfo()).thenReturn(contactInfo);
        Mockito.when(caterer.getContactInfo().getEmailAddress()).thenReturn("makis@gmail.com");

        CityNameException thrown = Assertions.assertThrows(CityNameException.class, () -> {
            catererImpl.validateInput(caterer);
        }, "CityNameException was expected");
    }

    // Test validation email
    @Test
    public void testInvalidMail()  {
        Mockito.when(caterer.getCityName()).thenReturn("Berlin");
        Mockito.when(caterer.getName()).thenReturn("Lukas");
        Mockito.when(caterer.getMinGuests()).thenReturn(7);
        Mockito.when(caterer.getMaxGuests()).thenReturn(100);
        Mockito.when(caterer.getContactInfo()).thenReturn(contactInfo);
        Mockito.when(caterer.getContactInfo().getEmailAddress()).thenReturn("makisgmail.com");

        EmailAddressException thrown = Assertions.assertThrows(EmailAddressException.class, () -> {
            catererImpl.validateInput(caterer);
        }, "invalid email was expected");
    }

    // Test minGuests number
    @Test
    public void testMinGuests()  {
        Mockito.when(caterer.getCityName()).thenReturn("Luda");
        Mockito.when(caterer.getName()).thenReturn("Lukas");
        Mockito.when(caterer.getMinGuests()).thenReturn(-1);
        Mockito.when(caterer.getMaxGuests()).thenReturn(100);
        Mockito.when(caterer.getContactInfo()).thenReturn(contactInfo);
        Mockito.when(caterer.getContactInfo().getEmailAddress()).thenReturn("makisgmail.com");

        NotValidGuestsException thrown = Assertions.assertThrows(NotValidGuestsException.class, () -> {
            catererImpl.validateInput(caterer);
        }, "invalid email was expected");
    }

}
