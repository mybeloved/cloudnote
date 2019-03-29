package com.tedu.cloudnote.dao;

import java.util.List;

import com.tedu.cloudnote.annotation.MyBatisRepository;
import com.tedu.cloudnote.entity.Book;

@MyBatisRepository
public interface AssociationDao {
	public List<Book> findAllBook();

	public Book findById(String bookId);
}
