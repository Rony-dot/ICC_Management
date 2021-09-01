package com.rony.services;

import com.rony.models.Country;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class CountryService extends BaseService<Country> {

    public CountryService(){
        super(new Country());
    }

    public void saveCountry(Country country){
        save(country);
    }

    public void deleteCountry(Country country){
        delete(country);
    }

}
