package hello.services.impl;


import hello.dao.CountryRepository;
import hello.entities.Country;
import hello.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Iterable<Country> findCountryByFilter(String nameFilter){
        return countryRepository.findByNameContainingIgnoreCase(nameFilter);
    }

    @Override
    public void add(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void deleteByName(String name) {
        for (Country country: countryRepository.findByName(name)) {
            countryRepository.delete(country);
        }
    }

    @Override
    public Country getCountryByName(String name) {
        for (Country country: countryRepository.findByName(name)) {
            return country;
        }
        return null;
    }

}
