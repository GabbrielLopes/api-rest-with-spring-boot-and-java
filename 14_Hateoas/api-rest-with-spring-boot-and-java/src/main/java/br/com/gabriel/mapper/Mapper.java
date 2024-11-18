package br.com.gabriel.mapper;

import br.com.gabriel.model.Person;
import br.com.gabriel.vo.v1.PersonVO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    private static ModelMapper mapper = new ModelMapper();

    static {
        mapper.createTypeMap(
                Person.class,
                PersonVO.class)
                .addMapping(Person::getId, PersonVO::setKey);
    }

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origins, Class<D> destination) {
        return origins.stream()
                .map(origin -> parseObject(origin, destination))
                .collect(Collectors.toList());
    }

}
