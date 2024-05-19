package br.com.gabriel.service.interfaces;

import br.com.gabriel.vo.v1.PersonVO;
import br.com.gabriel.vo.v2.PersonVOV2;

import java.util.List;

public interface PersonService {

    PersonVO findById(Long id);

    List<PersonVO> findAll();

    PersonVO create(PersonVO person);

    PersonVOV2 createV2(PersonVOV2 person);

    PersonVO update(PersonVO person);

    void delete(long id);
}
