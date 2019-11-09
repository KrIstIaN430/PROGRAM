import javax.print.attribute.EnumSyntax;
import javax.swing.text.NumberFormatter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.math.BigDecimal;

class Calculator {
    LinkedList<String> equations = new LinkedList<>();
    LinkedList<String> results = new LinkedList<>();
    private DecimalFormat df = new DecimalFormat("#,###.#");
    Calculator(){

        df.setMaximumFractionDigits(15);
        df.setMinimumFractionDigits(-10);
    }

    String calculate(StringBuilder input){
        Stack<BigDecimal> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ',')
                input.deleteCharAt(i);
        }
        List<String> organized = new ArrayList<>(Arrays.asList(input.toString().split(" ")));

        if (validate(organized)) {
            for (Object x : organized) {
                if (x.equals("*") || x.equals("/") || x.equals("+") || x.equals("-"))
                    if (operators.empty())
                        operators.push(x.toString());
                    else {
                        if (!hasPrecedence(operators.peek(), x.toString())) {
                            numbers.push(applyOp(numbers.pop(), numbers.pop(), operators.pop()));
                            operators.push(x.toString());
                        } else
                            operators.push(x.toString());
                    }
                else if (!x.equals("")) {
                    numbers.push(new BigDecimal(x.toString()));
                }
            }
            while (!operators.empty())
                numbers.push(applyOp(numbers.pop(), numbers.pop(), operators.pop()));

            //if (numbers.peek() % 1 == 0) {
            //    output.append((int)Math.round(numbers.pop()));
            //}
            //else
                //output.append(numbers.pop());
            //TODO IMPROVE FORMATTING
            //TODO IMPROVE ACCURACY - USE CLASS VARIABLE
            return formatter(new BigDecimal(numbers.pop().toString())
                    .stripTrailingZeros()
                    .toPlainString());
        }
        return "";
    }

    private BigDecimal applyOp(BigDecimal secondNum, BigDecimal firstNum, String operator){
        firstNum = firstNum.setScale(50, RoundingMode.HALF_UP);
        secondNum = secondNum.setScale(50, RoundingMode.HALF_UP);
        switch (operator) {
            case "*":
                return firstNum.multiply(secondNum);
            case "/":
                return firstNum.divide(secondNum, RoundingMode.HALF_UP);
            case "+":
                return firstNum.add(secondNum);
            default:
                return firstNum.subtract(secondNum);
        }
    }

    private boolean hasPrecedence(String stackOp, String listOp){
        return ((stackOp.equals("+") || stackOp.equals("-")) && (listOp.equals("*") || listOp.equals("/")));
    }

    private boolean validate(List input){
        int numCount = 0;
        int opCount = 0;
        for (Object x : input) {
            if (x.equals("*") || x.equals("/") || x.equals("+") || x.equals("-"))
                opCount++;
            else
                numCount++;
        }
        return numCount > opCount;
    }
    String formatter(String input) {
        BigDecimal in = new BigDecimal(input.replaceAll(",", ""));
        if (input.length() > 20)
            df.applyPattern("0.000E0");
        else
            df.applyPattern("#,###.###############");

            return df.format(in);
    }
    String negate(StringBuilder input){
        BigDecimal in = new BigDecimal(input.toString().replaceAll(",", ""));
        return formatter(in.negate().toString());

    }
}
