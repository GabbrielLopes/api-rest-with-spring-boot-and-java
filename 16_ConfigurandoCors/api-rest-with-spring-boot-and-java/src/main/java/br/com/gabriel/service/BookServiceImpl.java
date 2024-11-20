package br.com.gabriel.service;

import br.com.gabriel.controller.BookController;
import br.com.gabriel.exception.RequiredObjectIsNullException;
import br.com.gabriel.exception.ResourceNotFoundException;
import br.com.gabriel.mapper.Mapper;
import br.com.gabriel.model.Book;
import br.com.gabriel.repository.BookRepository;
import br.com.gabriel.service.interfaces.BookService;
import br.com.gabriel.vo.v1.BookVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    private Logger logger = Logger.getLogger(BookService.class.getName());


    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }


    @Override
    public BookVO findById(Long id) {
        logger.info("m=findById, finding book by id...");
        BookVO vo = repository.findById(id).map(book -> Mapper.parseObject(book, BookVO.class))
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        vo.add(linkTo(
                methodOn(BookController.class).findById(vo.getKey()))
                .withSelfRel());

        return vo;
    }

    @Override
    public List<BookVO> findAll() {
        logger.info("m=findAll, finding books");
        List<BookVO> books = repository.findAll()
                .stream().map(book -> Mapper.parseObject(book, BookVO.class))
                .collect(Collectors.toList());

        books.forEach(bookVO -> bookVO.add(linkTo(
                methodOn(BookController.class).findById(bookVO.getKey()))
                .withSelfRel()));

        return books;
    }

    @Override
    @Transactional
    public BookVO create(BookVO bookVO) {
        logger.info("Creating one bookVO!");

        validaInputNull(bookVO);

        // Realiza conversões de VO para entity / entity para VO
        Book bookEntity = Mapper.parseObject(bookVO, Book.class);
        bookVO = Mapper.parseObject(repository.save(bookEntity), BookVO.class);
        bookVO.add(
                linkTo(
                        methodOn(BookController.class).findById(bookVO.getKey()))
                        .withSelfRel());
        return bookVO;
    }

    @Override
    public BookVO update(BookVO bookVO) {
        logger.info("Updating one bookVO!");

        validaInputNull(bookVO);

        Book bookEntity = repository.findById(bookVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        BeanUtils.copyProperties(bookVO, bookEntity);

        BookVO vo = Mapper.parseObject(repository.save(bookEntity), BookVO.class);
        vo.add(
                linkTo(
                        methodOn(BookController.class).findById(bookVO.getKey()))
                        .withSelfRel());
        return vo;
    }

    private static void validaInputNull(BookVO personVO) {
        if(Objects.isNull(personVO)){
            throw new RequiredObjectIsNullException();
        }
    }

    @Override
    public void delete(long id) {
        logger.info("Deleting one person!");

        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(book);
    }
}
