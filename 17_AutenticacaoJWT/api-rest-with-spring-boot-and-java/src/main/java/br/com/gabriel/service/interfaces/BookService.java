package br.com.gabriel.service.interfaces;

import br.com.gabriel.vo.v1.BookVO;

import java.util.List;

public interface BookService {

    BookVO findById(Long id);

    List<BookVO> findAll();

    BookVO create(BookVO bookVO);


    BookVO update(BookVO bookVO);

    void delete(long id);

}
