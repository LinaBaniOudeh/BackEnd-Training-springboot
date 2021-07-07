package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    @RequestMapping(method = RequestMethod.GET, value = "/persons/search")
    public List<Person> searchPersonName(@RequestParam("name") String name) {
         return service.getName(name);
    }
    //GET (return Person by name).
    @RequestMapping(method = RequestMethod.GET, value = "/departments/{id}/persons")
    public List<Person> searchPersonDep(@PathVariable Integer id) {
         return service.getByDepId(id);

    }

    //POST (add new Person).
    @RequestMapping(method = RequestMethod.POST, value = "/persons")
    public boolean addPerson(@RequestBody Person person) {
        return service.addToList(person);

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
