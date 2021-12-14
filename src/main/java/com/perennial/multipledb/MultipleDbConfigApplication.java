package com.perennial.multipledb;

import com.perennial.multipledb.book.repo.BookRepo;
import com.perennial.multipledb.model.book.Book;
import com.perennial.multipledb.model.user.User;
import com.perennial.multipledb.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "com.perennial.multipledb")
public class MultipleDbConfigApplication {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private BookRepo bookRepo;

	@PostConstruct
	private void addDataToDB(){
		userRepo.saveAll(Stream.of(new User(1,"ABC"),new User(111,"XYZ"))
				.collect(Collectors.toList()));
		bookRepo.saveAll(Stream.of(new Book(9,"Java","Limio"),
						new Book(999,"Spring","Bush"))
				.collect(Collectors.toList()));
	}
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return bookRepo.findAll();
	}
	public static void main(String[] args) {
		SpringApplication.run(MultipleDbConfigApplication.class, args);
	}

}
