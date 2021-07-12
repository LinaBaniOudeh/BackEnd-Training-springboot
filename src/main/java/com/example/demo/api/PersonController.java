package com.example.demo.api;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@RestController
@Validated
@RequiredArgsConstructor
public class PersonController {
    @Autowired
    PersonService service;
   // @Autowired
    //PersonRepository repository;

    @Value("${app.owner}")
    private  String appOwner;

    @GetMapping(value = "/owner")
    public String getAppOwner() {
        return appOwner ;
    }

    @GetMapping(value = "/persons/pagination")
    public ResponseEntity<List<Person>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Person> list = service.getAllEmployees(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Person>>(list, new HttpHeaders(), HttpStatus.OK);
    }




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


    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }
}
