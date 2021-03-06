package com.company.controller;

import com.company.dto.CountryDTO;
import com.company.dto.EmployementHistoryDTO;
import com.company.dto.ResponseDTO;
import com.company.entity.Country;
import com.company.entity.EmployementHistory;
import com.company.exceptions.countryExceptions.CountryAlreadyExistsException;
import com.company.exceptions.countryExceptions.CountryNotFoundException;
import com.company.service.inter.CountryServiceRestInter;
import com.company.service.inter.EmployementHistorylServiceRestInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployementHistoryRestController {
    @Autowired
    @Qualifier("employementHistoryServiceRest")
    private EmployementHistorylServiceRestInter employementHistoryService;

    //return all users
    //ehistories?name=&surname=&age=
//    @GetMapping("/ehistories")
//    public ResponseEntity<ResponseDTO> getEmployementHistories() {
//        List<EmployementHistory> countries = employementHistoryService.getAll();
//
//        List<CountryDTO> countryDTOS = new ArrayList<>();
//        //skill-i skillDto ya cevirir
//        for (int i = 0; i < countries.size(); i++) {
//            Country s = countries.get(i);
//            countryDTOS.add(new CountryDTO(s));
//        }
//        return ResponseEntity.ok(ResponseDTO.of(countryDTOS));
//    }


    //return specify skill by id
//    @GetMapping("/ehistories/{id}")
//    public ResponseEntity<ResponseDTO> getEmployementHistory(@PathVariable("id") int id) {
//        EmployementHistory employementHistory = null;
//        try {
//            employementHistory = employementHistoryService.getEmployementHistory(id);
//        } catch (CountryNotFoundException ex) {
//            return ResponseEntity.ok(ResponseDTO.of(404, "There is no country found with this id"));
//        }
//        return ResponseEntity.ok(ResponseDTO.of(new CountryDTO(employementHistory)));
//    }

     @GetMapping("/ehistories/{id}")
    public ResponseEntity<ResponseDTO> getEmployementHistoriesByUserId(@PathVariable("id") int id) {
        List<EmployementHistory> employementHistory = employementHistoryService.getAllEmployementHistoryByUserId(id);

        List<EmployementHistoryDTO> eHistoryDTOS = new ArrayList<>();
        //skill-i skillDto ya cevirir
        for (int i = 0; i < employementHistory.size(); i++) {
            EmployementHistory s = employementHistory.get(i);
            eHistoryDTOS.add(new EmployementHistoryDTO(s));
        }
        return ResponseEntity.ok(ResponseDTO.of(eHistoryDTOS));
    }

//    //    delete specify skill by id and return deleted skill
//    @DeleteMapping("/countries/{id}")
//    public ResponseEntity<ResponseDTO> deleteSkill(@PathVariable("id") int id) {
//        Country country;
//        try {
//            country = employementHistoryService.getById(id);
//            employementHistoryService.removeCountry(id);
//        } catch (CountryNotFoundException ex) {
//            return ResponseEntity.ok(ResponseDTO.of(404, "There is no country found with this id"));
//        }
//
//        return ResponseEntity.ok(ResponseDTO.of(new CountryDTO(country), "Successfully deleted"));
//    }
//
//    //    //take json skill data and add skill then return it
//    @PostMapping("/countries")
//    public ResponseEntity<ResponseDTO> addSkill(@RequestBody CountryDTO countryDTO) {
//
//        Country country = new Country(null,countryDTO.getName(),countryDTO.getNationality());
//        try {
//            country = employementHistoryService.insertCountry(country);
//        } catch (CountryAlreadyExistsException ex) {
//            return ResponseEntity.ok(ResponseDTO.of(500, "Country already exists"));
//        }
//        return ResponseEntity.ok(ResponseDTO.of(new CountryDTO(country), "Successfully added"));
//
//    }
//
//    //
//    //take json skill data and update skill then return it
//    @PutMapping("/countries")
//    public ResponseEntity<ResponseDTO> updateUser(@RequestBody CountryDTO countryDTO) {
//
//        Country country = new Country(countryDTO.getId(), countryDTO.getName(),countryDTO.getNationality());
//        try {
//            country = employementHistoryService.updateCountry(country);
//        } catch (CountryNotFoundException ex) {
//            return ResponseEntity.ok(ResponseDTO.of(500, "Country does not exists"));
//        } catch (CountryAlreadyExistsException ex) {
//            return ResponseEntity.ok(ResponseDTO.of(500, "Country already exists"));
//        }
//        return ResponseEntity.ok(ResponseDTO.of(new CountryDTO(country), "Successfully updated"));
//    }
//
//    //take path and json skill name and update skill then return it
//    @PutMapping("/countries/{countryid}")
//    public ResponseEntity<ResponseDTO> updateUser(@PathVariable("countryid") int countryId, @RequestBody CountryDTO countryDTO) {
//
//        Country country = new Country(countryId, countryDTO.getName(),countryDTO.getNationality());
//        try {
//            country = employementHistoryService.updateCountry(country);
//        } catch (CountryNotFoundException ex) {
//            return ResponseEntity.ok(ResponseDTO.of(500, "Country does not exists"));
//        } catch (CountryAlreadyExistsException ex) {
//            return ResponseEntity.ok(ResponseDTO.of(500, "Country already exists"));
//        }
//        return ResponseEntity.ok(ResponseDTO.of(new CountryDTO(country), "Successfully updated"));
//    }

}
