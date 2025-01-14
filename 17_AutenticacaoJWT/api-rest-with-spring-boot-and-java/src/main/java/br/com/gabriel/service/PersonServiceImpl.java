package br.com.gabriel.service;

import br.com.gabriel.controller.PersonController;
import br.com.gabriel.exception.RequiredObjectIsNullException;
import br.com.gabriel.exception.ResourceNotFoundException;
import br.com.gabriel.mapper.Mapper;
import br.com.gabriel.mapper.custom.PersonMapper;
import br.com.gabriel.model.Person;
import br.com.gabriel.repository.PersonRepository;
import br.com.gabriel.service.interfaces.PersonService;
import br.com.gabriel.vo.v1.PersonVO;
import br.com.gabriel.vo.v2.PersonVOV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonMapper personMapper;

    private Logger logger = Logger.getLogger(PersonService.class.getName());


    @Override
    public PersonVO findById(Long id) {
        logger.info("Finding a person...");

        Person personEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        PersonVO vo = Mapper.parseObject(personEntity, PersonVO.class);
        vo.add(
                linkTo(
                        methodOn(PersonController.class).findById(id))
                        .withSelfRel());
        return vo;
    }

    @Override
    public List<PersonVO> findAll() {
        logger.info("Finding all people!");
        List<PersonVO> personVOs = Mapper.parseListObjects(repository.findAll(), PersonVO.class);
        personVOs.forEach(vo -> vo.add(
                linkTo(
                        methodOn(PersonController.class).findById(vo.getKey()))
                        .withSelfRel()));
        return personVOs;
    }

    @Override
    @Transactional
    public PersonVO create(PersonVO personVO) {
        logger.info("Creating one personVO!");

        validaInputNull(personVO);

        // Realiza conversões de VO para entity / entity para VO
        Person personEntity = Mapper.parseObject(personVO, Person.class);
        personVO = Mapper.parseObject(repository.save(personEntity), PersonVO.class);
        personVO.add(
                linkTo(
                        methodOn(PersonController.class).findById(personVO.getKey()))
                        .withSelfRel());
        return personVO;
    }

    @Override
    @Transactional
    public PersonVOV2 createV2(PersonVOV2 personVO) {
        logger.info("Creating one personVOV2!");

        validaInputNull(personVO);

        // Realiza conversões customizadas
        Person personEntity = personMapper.convertVoToEntity(personVO);
        personVO = personMapper.convertEntityToVo(repository.save(personEntity));

        return personVO;
    }

    @Override
    public PersonVO update(PersonVO personVO) {
        logger.info("Updating one personVO!");

        validaInputNull(personVO);

        Person personEntity = repository.findById(personVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        personEntity.setFirstName(personVO.getFirstName());
        personEntity.setLastName(personVO.getLastName());
        personEntity.setAddress(personVO.getAddress());
        personEntity.setGender(personVO.getGender());

        PersonVO vo = Mapper.parseObject(repository.save(personEntity), PersonVO.class);
        vo.add(
                linkTo(
                        methodOn(PersonController.class).findById(personVO.getKey()))
                        .withSelfRel());
        return vo;
    }

    private static void validaInputNull(PersonVO personVO) {
        if(Objects.isNull(personVO)){
            throw new RequiredObjectIsNullException();
        }
    }

    private static void validaInputNull(PersonVOV2 personVO) {
        if(Objects.isNull(personVO)){
            throw new RequiredObjectIsNullException();
        }
    }

    @Override
    public void delete(long id) {
        logger.info("Deleting one person!");

        Person personDBResponse = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(personDBResponse);
    }

}
