package edu.mum.example.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.mum.example.domain.Student;

public interface StudentDAO extends JpaRepository<Student, Long> {

    List<Student> findByLastName(String lastName);
    
    List<Student> findByLastNameOrderByLastNameAsc(String lastName);

    List<Student> findByLastName(String lastName, Sort sort);

    List<Student> findByAddressCity(String city);
    
    Page <Student> findAll(Pageable pageable);
    
    Slice<Student> findByFirstName(String firstName, Pageable pageable);
    
    List<Student> findByFirstNameLike(String firstName);
    
    @Query("from Student s where LOWER(s.lastName) = LOWER(:name)")
    List<Student> findByLastNameLowerCase(@Param("name") String name);
    
    Long countByLastName(String lastName);
    
    Long deleteByLastName(String lastName);

    List<Student> removeByLastName(String lastName);
    
}
