package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private ArrayList<Department> departments;
    private ArrayList<Person> personList;

    @PostConstruct
    void init() {
        departments = new ArrayList<>(Arrays.asList(
                new Department(1,"CS"),
                new Department(2,"ECS")));
        personList = new ArrayList<Person>(Arrays.asList(

                new Person.Builder().setId(55).setName("leena").setDep(1).build(),
                new Person.Builder().setId(1182351).setName("yara").setDep(2).build(),
                new Person.Builder().setId(1172351).setName("raghad").setDep(1).build(),
                new Person.Builder().setId(1162351).setName("leena").setDep(1).build(),
                new Person.Builder().setId(1152351).setName("beeeeep").setDep(2).build(),
                new Person.Builder().setId(1132351).setName("shaima'a").setDep(1).build(),
                new Person.Builder().setId(1122351).setName("noor").setDep(2).build(),
                new Person.Builder().setId(1112351).setName("wafa'a").setDep(2).build()));
    }


//    @Autowired
//    PersonRepository repository;
//
//    public List<Person> getAllPersons(Integer pageNo, Integer pageSize, String sortBy)
//    {
//        Pageable paging = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//
//        Page<Person> pagedResult = repository.findAll((org.springframework.data.domain.Pageable) paging);
//
//        if(pagedResult.hasContent()) {
//            return pagedResult.getContent();
//        } else {
//            return new ArrayList<Person>();
//        }
//    }

    //TODO: what is the Optional

    @Autowired
    PersonRepository repository;

    public List<Person> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Person> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Person>();
        }
    }
    //GET (return Person by Id).
    public Person getById(int personId) {
        return personList.stream().filter(p -> p.getId() == personId).findFirst().get();
    }
    //GET (return Person by DepId).

    public List<Person> getByDepId(int depId) {
        return personList.stream().filter(p -> p.getDep() == depId).collect(Collectors.toList());
    }

     public List<Person> getAll(){
        return personList;
     }

    //GET (return Person by Name).

    public List<Person> getName(String personName) {
        return personList.stream().filter(p -> p.getName().contains(personName)).collect(Collectors.toList());
    }
    //POST (add new Person).

    public boolean addToList(Person person) {
        return personList.add(person);
    }

    //DELETE (delete person).
//    public boolean deletePersonFromPersonList(String personName) {
//
//        Iterator<Person> itr = personList.iterator();
//        while (itr.hasNext()) {
//            Person element = itr.next();
//            if (element.getName().equals(personName)) {
//                itr.remove(); // REMOVE THIS FROM Iterator
//                return true;
//            }
//        }

//        return false;
//
//    }

    //PUT (Update person by Id).
    public boolean UpdatePersonList(Person newPerson, int personId) {
        for (Person person : personList)
            if (person.getId()==personId) {
                person.setName(newPerson.getName());
                person.setId(newPerson.getId());
                return true;
            }
        return false;


    }
}
