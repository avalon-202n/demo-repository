package com.example.demo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

interface PersonRepository extends JpaRepository<Person, Long> {
	List<Person> findAllByName(String name);
}

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

@RequiredArgsConstructor
@RestController
@Slf4j
class HelloController {

	private final PersonRepository personRepository;

	@GetMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		log.info("Hello for : {}", name);
		Person p = new Person();
		p.setName(name);
		personRepository.save(p);

		return "Hello " + name;
	}
}

@Entity
@Data
class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String name;
}
