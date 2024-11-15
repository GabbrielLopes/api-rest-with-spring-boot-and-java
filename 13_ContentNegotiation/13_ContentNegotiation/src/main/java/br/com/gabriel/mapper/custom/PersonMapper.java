package br.com.gabriel.mapper.custom;

import br.com.gabriel.model.Person;
import br.com.gabriel.vo.v2.PersonVOV2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVo(Person person){
        PersonVOV2 vo = new PersonVOV2();
        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        vo.setBirthDay(LocalDate.now());

        return vo;
    }

    public Person convertVoToEntity(PersonVOV2 personVO){
        Person person = new Person();
        person.setId(personVO.getId());
        person.setFirstName(personVO.getFirstName());
        person.setLastName(personVO.getLastName());
        person.setAddress(personVO.getAddress());
        person.setGender(personVO.getGender());
//        person.setBirthDay();

        return person;
    }

}
