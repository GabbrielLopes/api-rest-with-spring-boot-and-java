package br.com.gabriel.service;

import br.com.gabriel.exception.ResourceNotFoundException;
import br.com.gabriel.mapper.Mapper;
import br.com.gabriel.model.Person;
import br.com.gabriel.repository.PersonRepository;
import br.com.gabriel.service.interfaces.PersonService;
import br.com.gabriel.vo.v1.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    private Logger logger = Logger.getLogger(PersonService.class.getName());


    @Override
    public PersonVO findById(Long id) {
        logger.info("Finding a person...");

        Person personEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return Mapper.parseObject(personEntity, PersonVO.class);
    }

    @Override
    public List<PersonVO> findAll() {
        logger.info("Finding all people!");
        return Mapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    @Override
    @Transactional
    public PersonVO create(PersonVO personVO) {
        logger.info("Creating one personVO!");

        // Realiza conversões de VO para entity / entity para VO
        Person personEntity = Mapper.parseObject(personVO, Person.class);
        personVO = Mapper.parseObject(repository.save(personEntity), PersonVO.class);

        return personVO;
    }

    @Override
    public PersonVO update(PersonVO personVO) {
        logger.info("Updating one personVO!");

        Person personEntity = repository.findById(personVO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        personEntity.setFirstName(personVO.getFirstName());
        personEntity.setLastName(personVO.getLastName());
        personEntity.setAddress(personVO.getAddress());
        personEntity.setGender(personVO.getGender());

        return Mapper.parseObject(repository.save(personEntity), PersonVO.class);
    }

    @Override
    public void delete(long id) {
        logger.info("Deleting one person!");

        Person personDBResponse = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(personDBResponse);
    }

}
