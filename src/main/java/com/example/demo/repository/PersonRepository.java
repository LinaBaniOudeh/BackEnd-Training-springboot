package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PersonRepository  extends PagingAndSortingRepository<Person,Integer> {
    //List<Person> findByName(String name, Pageable pageable);


}
