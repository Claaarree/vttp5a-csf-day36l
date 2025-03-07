package sg.edu.nus.iss.day36l.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day36l.model.City;

@Repository
public class CitiesRepository {
    @Autowired
    private JdbcTemplate template;

    private static final String SELECT_ALL_CITIES = "select code, city_name from cities";
    
    public List<City> getAllCities() {
        return template.query(SELECT_ALL_CITIES, (rs, rowNum) -> {
            return City.populate(rs);
        });
    }
}
