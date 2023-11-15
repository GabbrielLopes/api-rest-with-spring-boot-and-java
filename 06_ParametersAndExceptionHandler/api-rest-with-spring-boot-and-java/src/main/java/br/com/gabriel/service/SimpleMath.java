package br.com.gabriel.service;

import br.com.gabriel.exception.UnsupportedMathOperationException;
import io.micrometer.common.util.StringUtils;

import java.util.Objects;

public class SimpleMath {

    private static final String NUMBER_REGEX = "-?\\d+(\\.\\d+)?";

    public Double sum(Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }

    public Double subtract(Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }

    public Double multiplication(Double numberOne, Double numberTwo) {
        return numberOne * numberTwo;
    }

    public Double division(Double numberOne, Double numberTwo) {
        if (isZero(numberOne) || isZero(numberTwo)) {
            throw new UnsupportedMathOperationException("Division by 0!");
        }

        return numberOne / numberTwo;
    }

    public Double squareRoot(Double number) {
        return Math.sqrt(number);
    }

    private boolean isZero(Double number) {
        return Objects.isNull(number) || number.equals(0D);
    }

    public boolean isNumeric(String strNumber) {
        if (StringUtils.isBlank(strNumber)) return false;

        String number = strNumber.replace(",", ".");

        return number.matches(NUMBER_REGEX);
    }

    public Double convertToDouble(String strNumber) {
        if (StringUtils.isBlank(strNumber)) return 0D;

        String number = strNumber.replace(",", ".");
        return Double.valueOf(number);
    }

}
