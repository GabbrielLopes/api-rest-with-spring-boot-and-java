package br.com.gabriel.mapper;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    private static ModelMapper mapper = new ModelMapper();

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origins, Class<D> destination) {
        return origins.stream()
                .map(origin -> parseObject(origin, destination))
                .collect(Collectors.toList());
    }

}
