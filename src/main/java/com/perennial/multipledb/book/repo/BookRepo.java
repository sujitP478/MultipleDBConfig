package com.perennial.multipledb.book.repo;

import com.perennial.multipledb.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book,Integer> {
}
