package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
public class PersonController {
    @Autowired
    PersonService service;


    //GET (return Person List).
    @GetMapping(value = "/persons")
    public List<Person> getAll() {
        return service.getAll();
    }

    //GET (return Person by id).
    @GetMapping(value = "/persons/{id}")
    public Person getById(@PathVariable Integer id) {
        return service.getById(id);
    }


    //GET (return Person by name).
    @GetMapping (value = "/persons/search")
    public List<Person> searchPersonName(@RequestParam("name") @NotEmpty String name) {

        return service.getName(name);
    }
    //GET (return Person by name).
    @RequestMapping(method = RequestMethod.GET, value = "/departments/{id}/persons")
    public List<Person> searchPersonDep(@PathVariable Integer id) {
         return service.getByDepId(id);

    }

    //POST (add new Person).
    @PostMapping( value = "/persons",consumes = {MediaType.ALL_VALUE})
    public String addPerson(@RequestBody @Valid Person person, Errors error, Model model) {
        if(error.hasErrors()){
          model.addAttribute("Title","add Person");
          return "Unvalid Argument";
        }
        service.addToList(person);
        return "redirect" ;

    }
    //DELETE (delete person).
//    @RequestMapping(method = RequestMethod.DELETE, value = "/persons/{PersonName}")
//    public boolean deletePerson(@PathVariable String PersonName) {
//        // delete with username from the UserService arrayList
//        return service.deletePersonFromPersonList(PersonName);
//    }

    //Put (Update Person by Id).
    @RequestMapping(method = RequestMethod.PUT, value = "/persons/{id}")
    public boolean updatePerson(@RequestBody Person person, @PathVariable int id) {
        // update with username in the UserService arrayList
        return service.UpdatePersonList(person, id);
    }
}
