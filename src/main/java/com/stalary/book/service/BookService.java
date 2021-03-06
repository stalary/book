package com.stalary.book.service;

import com.stalary.book.data.Page;
import com.stalary.book.data.ResultEnum;
import com.stalary.book.data.entity.Book;
import com.stalary.book.exception.MyException;
import com.stalary.book.mapper.BookDao;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BookService
 *
 * @author hawk
 * @since 2018/02/09
 */
@Service
@Slf4j
public class BookService {

    @Autowired
    private BookDao bookDao;

    public void saveBook(Book book) {
        bookDao.save(book);
    }

    public Pair<List<Book>, Double> findAll(int pageIndex, int pageSize) {
        Page page = new Page(pageIndex, pageSize);
        List<Book> bookList = bookDao.findAll(page.getStart(), pageSize);
        Integer total = bookDao.findAllTotal();
        return new Pair<>(bookList, Math.ceil((float) total / pageSize));
    }

    public Pair<List<Book>, Double> findByKey(int pageIndex, int pageSize, String key) {
        Page page = new Page(pageIndex, pageSize);
        List<Book> bookList = bookDao.findByKey(page.getStart(), pageSize, "%" + key + "%");
        Integer total = bookDao.findByKeyTotal("%" + key + "%");
        return new Pair<>(bookList, Math.ceil((float) total / pageSize));
    }

    public String downloadBook(int id) {
        Book book = getInfo(id);
        bookDao.downloadBook(id, book.getDownloadCount() + 1);
        return book.getPdfUrl();
    }

    public Book getInfo(int id) {
        Book book = bookDao.getInfo(id);
        if (null == book) {
            throw new MyException(ResultEnum.BOOK_NOT_FOUND);
        }
        return book;
    }

    public void delete(int id) {
        getInfo(id);
        bookDao.delete(id);
    }

    public List<Book> findByUserId(int userId) {
        return bookDao.findByUserId(userId);
    }
}
