package ua.com.zmina.division;

import java.util.Scanner;

import static java.lang.String.format;

public class Main {
    static final String DEFAULT_DIVIDER = "12345";
    static final String DEFAULT_DIVISOR = "56";

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.print("Input divider and press <Enter> [" + DEFAULT_DIVIDER + "]: ");
        var divider = scanner.nextLine();
        if (divider.isBlank()) {
            divider = DEFAULT_DIVIDER;
        }

        System.out.print("Input divider and press <Enter> [" + DEFAULT_DIVISOR + "]: ");
        var divisor = scanner.nextLine();
        if (divisor.isBlank()) {
            divisor = DEFAULT_DIVISOR;
        }

        var divisionResult = new Division(divider).divide(divisor);
        printResult(divisionResult);
    }

    private static void printResult(DivisionResult divisionResult) {
        System.out.println();
        var dividend = divisionResult.dividend;
        var divisor = divisionResult.divisor;
        var result = divisionResult.result;
        var divisionRemainder = divisionResult.remainders.get(divisionResult.remainders.size() - 1);

        System.out.println("Divider: " + dividend);
        System.out.println("Divisor: " + divisor);
        System.out.println("Result: " + result);
        System.out.println("Remainder: " + divisionRemainder);

        System.out.println();
        System.out.println(format("%s|%s", dividend, divisor));

        var leftIndent = "";
        var partialDividend = divisionResult.partialDividends.get(0);
        var remainder = divisionResult.remainders.get(1);
        var subtraction = divisionResult.subtractions.get(0);
        var rightIndent = " ".repeat(dividend.length() - leftIndent.length() - partialDividend.length());
        System.out.println(format("%s%s%s+%s",
                leftIndent,
                partialDividend,
                rightIndent,
                "-".repeat(partialDividend.length())));

        leftIndent = "";
        rightIndent = " ".repeat(dividend.length() - leftIndent.length() - partialDividend.length());
        System.out.println(format("%s%s%s|%s", leftIndent, "-".repeat(partialDividend.length()), rightIndent, result));

        leftIndent = " ".repeat(partialDividend.length() - subtraction.length());
        System.out.println(format("%s%s", leftIndent, remainder));

        // loop for other subtractions
        for (int i = 1; i < divisionResult.subtractions.size(); i++) {
            partialDividend = divisionResult.partialDividends.get(i);
            var dividendIndent = " ".repeat(remainder.length() - partialDividend.length());
            System.out.println(format("%s%s%s", leftIndent, dividendIndent, partialDividend));
            var line = "-".repeat(remainder.length());
            System.out.println(format("%s%s", leftIndent, line));

            subtraction = divisionResult.subtractions.get(i);
            remainder = divisionResult.remainders.get(i + 1);
            leftIndent = leftIndent + " ".repeat(partialDividend.length() - subtraction.length());
            System.out.println(format("%s%s", leftIndent, remainder));
        }
    }
}
