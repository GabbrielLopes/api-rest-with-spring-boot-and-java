package br.com.gabriel.service;

import br.com.gabriel.exception.UnsupportedMathOperationException;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MathServiceImpl {


    public Double sum(String numberOne, String numberTwo) {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a number valid!");
        }

        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    public Double subtract(String numberOne, String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a number valid!");
        }

        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    public Double multiplication(String numberOne, String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a number valid!");
        }

        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    public Double division(String numberOne, String numberTwo) {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a number valid!");
        }
        if (isZero(numberOne) || isZero(numberTwo)) {
            throw new UnsupportedMathOperationException("Division by 0!");
        }

        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    public Double squareRoot(String number) {
        if (!isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a number valid!");
        }

        return Math.sqrt(Double.parseDouble(number));
    }

    private boolean isZero(String strNumber) {
        String number = Objects.nonNull(strNumber) ? strNumber.replace(",", ".") : "";
        return Double.valueOf(number).equals(0D) || StringUtils.isBlank(number);
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
