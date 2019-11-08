import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.*;

class testing extends JPanel {
    private String input = "123456782222222.22222222222222222222222222222222222222229";
    DecimalFormat df = new DecimalFormat();
    BigDecimal bd;

    private testing() {
        bd = new BigDecimal(input);
        System.out.println(bd.toString());
        df.setRoundingMode(RoundingMode.HALF_UP);
        System.out.println(df.getMaximumFractionDigits());
        System.out.println(df.getMaximumIntegerDigits());
        StringBuilder x = new StringBuilder("1");
        for (int i = 2; i <= 30; i++){
            x.append(i);
            bd = new BigDecimal(x.toString());
            if (x.length() > 15)
                df.applyPattern("0.000E0");
            else
                df.applyPattern("#,###.0000000000000000000");
            try {
                System.out.println(String.format("length %d = %s", x.length(), df.format(df.parse(bd.toString() + .0))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        BigDecimal a = new BigDecimal(1);
        BigDecimal b = new BigDecimal("3333333333333333333333333333333333333333333");
        a = a.setScale(50, RoundingMode.HALF_UP);
        System.out.println(a.divide(b, RoundingMode.HALF_UP));
        try{
        System.out.println(df.parse(a.divide(b, RoundingMode.HALF_UP).toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
        public static void main(String[] args) {
        new testing();
    }
}