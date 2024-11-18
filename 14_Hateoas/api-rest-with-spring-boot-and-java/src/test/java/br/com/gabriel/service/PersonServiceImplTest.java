package br.com.gabriel.service;

import br.com.gabriel.mapper.custom.PersonMapper;
import br.com.gabriel.mapper.mocks.MockPerson;
import br.com.gabriel.model.Person;
import br.com.gabriel.repository.PersonRepository;
import br.com.gabriel.vo.v1.PersonVO;
import br.com.gabriel.vo.v2.PersonVOV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {


    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonRepository repository;

    @Mock
    private PersonMapper personMapper;

    MockPerson input;
    PersonVO personVO;
    PersonVOV2 personVOV2;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        personVO = input.mockVO();
        personVOV2 = new PersonVOV2();
        personVOV2.setId(7L);
        personVOV2.setFirstName("name vo v2");
        personVOV2.setLastName("last name vo v2");

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);

        when(repository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(person));

        var response = personService.findById(1L);
        assertNotNull(response);
        assertNotNull(response.getKey());
        assertNotNull(response.getLinks());
        assertTrue(response.toString().contains("</person/1>;rel=\"self\""));
        assertEquals("First Name Test1", response.getFirstName());
    }

    @Test
    void findAll() {
        Person person = input.mockEntity(1);

        when(repository.findAll())
                .thenReturn(Collections.singletonList(person));

        var response = personService.findAll();
        assertNotNull(response);
        assertFalse(response.isEmpty());
    }

    @Test
    void create() {
        Person person = input.mockEntity(15);

        when(repository.save(Mockito.any()))
                .thenReturn(person);

        personVO.setKey(15L);
        var response = personService.create(personVO);

        assertNotNull(response);
        assertNotNull(response.getKey());
        assertEquals(15L, response.getKey());

        verify(repository, times(1)).save(Mockito.any());
    }

    @Test
    void createV2() {
        Person person = input.mockEntity(7);
        personVOV2.setId(7L);

        when(personMapper.convertVoToEntity(Mockito.any(PersonVOV2.class)))
                .thenReturn(person);

        when(personMapper.convertEntityToVo(Mockito.any(Person.class)))
                .thenReturn(personVOV2);

        when(repository.save(Mockito.any()))
                .thenReturn(person);

        var response = personService.createV2(personVOV2);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(personVOV2.getId(), response.getId());

        verify(personMapper, times(1)).convertVoToEntity(Mockito.any());
        verify(repository, times(1)).save(Mockito.any());
    }

    @Test
    void update() {
        Person person = input.mockEntity(7);
        person.setFirstName("Gabriel Antigo Teste");

        personVO.setKey(7L);
        personVO.setFirstName("Gabriel Novo Testado");

        Person personNomeEditado = input.mockEntity(7);
        personNomeEditado.setFirstName("Gabriel Novo Testado");

        when(repository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(person));

        when(repository.save(Mockito.any()))
                .thenReturn(personNomeEditado);

        var response = personService.update(personVO);

        assertNotNull(response);
        assertEquals(personVO.getFirstName(), response.getFirstName());
    }

    @Test
    void delete() {
        Person person = input.mockEntity(1);
        when(repository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(person));

        doNothing()
                .when(repository).delete(Mockito.any());

        personService.delete(1L);

        verify(repository, times(1)).delete(Mockito.any());
    }
}