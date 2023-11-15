package br.com.gabriel.controller;

import br.com.gabriel.exception.UnsupportedMathOperationException;
import br.com.gabriel.service.SimpleMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    SimpleMath simpleMath = new SimpleMath();

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> sum(@PathVariable(value = "numberOne") String numberOne,
                                      @PathVariable(value = "numberTwo") String numberTwo) {
        return ResponseEntity.ok(simpleMath.sum(
                simpleMath.convertToDouble(numberOne), simpleMath.convertToDouble(numberTwo)));
    }

    @GetMapping("/subtract/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> subtract(@PathVariable(value = "numberOne") String numberOne,
                                           @PathVariable(value = "numberTwo") String numberTwo) {
        return ResponseEntity.ok(simpleMath.subtract(simpleMath.convertToDouble(numberOne),
                simpleMath.convertToDouble(numberTwo)));
    }

    @GetMapping("/multiplication/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> multiplication(@PathVariable(value = "numberOne") String numberOne,
                                                 @PathVariable(value = "numberTwo") String numberTwo) {
        return ResponseEntity.ok(simpleMath.multiplication(simpleMath.convertToDouble(numberOne),
                simpleMath.convertToDouble(numberTwo)));
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> division(@PathVariable(value = "numberOne") String numberOne,
                                           @PathVariable(value = "numberTwo") String numberTwo) {
        return ResponseEntity.ok(
                simpleMath.division(simpleMath.convertToDouble(numberOne), simpleMath.convertToDouble(numberTwo)));
    }

    @GetMapping("/squareRoot/{number}")
    public ResponseEntity<Double> squareRoot(@PathVariable(value = "number") String number) {
        if(!simpleMath.isNumeric(number)){
            throw new UnsupportedMathOperationException("Please set a number valid!");
        }
        return ResponseEntity.ok(simpleMath.squareRoot(simpleMath.convertToDouble(number)));
    }


}
