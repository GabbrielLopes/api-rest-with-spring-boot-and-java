package br.com.gabriel;

import br.com.gabriel.exception.UnsupportedMathOperationException;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {


    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> sum(@PathVariable(value = "numberOne") String numberOne,
                                      @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a number valid!");
        }

        return ResponseEntity.ok(convertToDouble(numberOne) + convertToDouble(numberTwo));
    }

    private Double convertToDouble(String strNumber) {
        if (StringUtils.isBlank(strNumber)) return 0D;

        String number = strNumber.replace(",", ".");
        return Double.valueOf(number);
    }

    private boolean isNumeric(String strNumber) {
        if (StringUtils.isBlank(strNumber)) return false;

        String number = strNumber.replace(",", ".");

        return number.matches("-?\\d+(\\.\\d+)?");
    }


}
