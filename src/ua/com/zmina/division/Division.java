package ua.com.zmina.division;

import java.math.BigInteger;
import java.util.ArrayList;

import static java.lang.String.format;

public class Division {
    final String divider;

    public Division(String divider) {
        this.divider = divider;
    }


    public DivisionResult divide(String divisor) {

        var divisorValue = new BigInteger(divisor);
        var resultDigits = "";
        int order = 0;
        var partialDividends = new ArrayList<String>();
        var remainders = new ArrayList<String>();
        var subtractions = new ArrayList<String>();
        var remainderValue = BigInteger.valueOf(0);
        var subtractionValue = BigInteger.valueOf(0);

        do {
            // get sufficient amount of digits that grater or equal to divisor
            BigInteger nextRemainderValue = BigInteger.valueOf(0);
            remainderValue = subtractionValue;
            int i;
            for (i = order; i < divider.length(); i++) {
                remainderValue = new BigInteger(remainderValue.toString() + divider.charAt(i));
                if (remainderValue.compareTo(divisorValue) > 0) {
                    break;
                } else if (!resultDigits.isBlank()) {
                    // add zero digit if it's not first iteration
                    resultDigits = resultDigits + "0";
                }
            }

            order = i + 1;

            // if last remainder too small than exit loop
            if (order >= divider.length() &&
                    divisorValue.multiply(BigInteger.valueOf(1)).compareTo(remainderValue) > 0) {
                subtractionValue = remainderValue;
                break;
            }

            remainders.add(remainderValue.toString());

            // define result digit
            BigInteger partialDividendValue = BigInteger.valueOf(0);
            String digit = "";
            for (Integer nextDigitValue = 1; nextDigitValue <= 9; nextDigitValue++) {
                var nextPartialDividend = divisorValue.multiply(BigInteger.valueOf(nextDigitValue));
                if (remainderValue.compareTo(nextPartialDividend) >= 0) {
                    partialDividendValue = nextPartialDividend;
                    digit = nextDigitValue.toString();
                } else {
                    break;
                }
            }

            subtractionValue = remainderValue.subtract(partialDividendValue);
            subtractions.add(subtractionValue.toString());
            partialDividends.add(partialDividendValue.toString());

            resultDigits = resultDigits + digit;

        } while (order < divider.length());

        remainders.add(subtractionValue.toString());

        return new DivisionResult(divider, divisor, resultDigits, partialDividends, remainders, subtractions);
    }
}
