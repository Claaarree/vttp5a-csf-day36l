package sg.edu.nus.iss.day36l.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day36l.model.City;
import sg.edu.nus.iss.day36l.repository.CitiesRepository;

@Service
public class CitiesService {
    
    @Autowired
    private CitiesRepository citiesRepository;

    public Optional<List<City>> getAllCities() {
        List<City> cc = this.citiesRepository.getAllCities();
        if(cc != null && !cc.isEmpty()) {
            return Optional.of(cc);
        } else return Optional.empty();
    }
}
