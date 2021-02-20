package ua.com.zmina.division;

import java.util.List;

public class DivisionResult {
    String dividend;
    String divisor;
    String result;
    List<String> partialDividends;
    List<String> remainders;
    List<String> subtractions;

    public DivisionResult(String dividend, String divisor, String result, List<String> partialDividends,
                          List<String> remainders, List<String> subtractions) {
        this.dividend = dividend;
        this.divisor = divisor;
        this.result = result;
        this.partialDividends = partialDividends;
        this.remainders = remainders;
        this.subtractions = subtractions;
    }

}
