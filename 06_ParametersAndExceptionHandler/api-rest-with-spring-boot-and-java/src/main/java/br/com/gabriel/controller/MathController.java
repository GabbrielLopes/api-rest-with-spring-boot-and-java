package br.com.gabriel.controller;

import br.com.gabriel.exception.UnsupportedMathOperationException;
import br.com.gabriel.service.MathServiceImpl;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static org.apache.tomcat.util.http.parser.HttpParser.isNumeric;

@RestController
public class MathController {

    @Autowired
    private MathServiceImpl mathService;

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> sum(@PathVariable(value = "numberOne") String numberOne,
                                      @PathVariable(value = "numberTwo") String numberTwo) {

        return ResponseEntity.ok(mathService.sum(numberOne,numberTwo));
    }

    @GetMapping("/subtract/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> subtract(@PathVariable(value = "numberOne") String numberOne,
                                      @PathVariable(value = "numberTwo") String numberTwo) {

        return ResponseEntity.ok(mathService.subtract(numberOne, numberTwo));
    }

    @GetMapping("/multiplication/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> multiplication(@PathVariable(value = "numberOne") String numberOne,
                                      @PathVariable(value = "numberTwo") String numberTwo) {

        return ResponseEntity.ok(mathService.multiplication(numberOne,numberTwo));
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> division(@PathVariable(value = "numberOne") String numberOne,
                                      @PathVariable(value = "numberTwo") String numberTwo) {

        return ResponseEntity.ok(mathService.division(numberOne, numberTwo));
    }

    @GetMapping("/squareRoot/{number}")
    public ResponseEntity<Double> squareRoot(@PathVariable(value = "number") String number) {

        return ResponseEntity.ok(mathService.squareRoot(number));
    }


}
