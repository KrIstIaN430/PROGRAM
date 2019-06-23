
import java.util.*;


public class Main {
    static int num;
    public static void main(String[] args) {
        int [] arr = new int[30];
        
        while(userInput()){
        }
        
        System.out.println("");
        
        for(int i = 0; i <29; i++){
            System.out.print("-");
        }
        System.out.println("");
        int ctr = 1, ctr2 = 1;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 10; j++){
                System.out.format("%-3d",ctr);
                ctr++;
            }
            System.out.println("");
            for(int j = 0; j < 10; j++){
                if(num%ctr2 == 0){
                    System.out.format("%-3s", "x");
                    arr[ctr2 - 1] = 1;
                }
                else
                    System.out.print("   ");
                ctr2++;
            }
            System.out.println("");
        }
        for(int i = 0; i <29; i++){
            System.out.print("-");
        }
        System.out.println("");
        int temp, temp1;
        String temp2;
        for(int i = 0; i < 30; i++){
            if(arr[i] == 1){
                switch(i + 1){
                    
                    case 1:
                        System.out.println("1: " + num + " is divisible by 1");
                       break;
                    case 2:
                        System.out.println("2: the last digit is even, " + (num % 10) + " is even");
                       break;
                    case 3:
                        System.out.println("3: the sum of the digits is divisible by 3");
                       break;
                    case 4:
                        System.out.println("4: the last two digits is divisible by 4");
                       break;
                    case 5:
                        System.out.println("5: the last digit is either 5 or 0");
                       break;
                    case 6:
                        System.out.println("6: " + num + " is divisible by 2 and by 3");
                       break;
                    case 7:
                        temp = num;
                        System.out.print("7: "+ num + " = " + (temp%10));
                        temp = temp / 10;
                        System.out.print("+(3x" + temp + ") = ");
                        temp = (3*temp) + (num%10);
                        System.out.println(temp + " is a multiple of 7");
                       break;
                    case 8:
                        temp = num;
                        System.out.print("8: "+ num + " = " + (temp%10));
                        temp = temp / 10;
                        System.out.print("+(2x" + temp + ") = ");
                        temp = (2*temp) + (num%10);
                        System.out.println(temp + " is a multiple of 8");
                       break;
                    case 9:
                        ctr = 0;
                        temp = 0;
                        temp2 = String.valueOf(num);
                        temp1 = temp2.length();
                        System.out.print("9: " + num + " = ");
                        for(char c : temp2.toCharArray()) {
                            System.out.print(c);
                            ctr++;
                            if(ctr != temp1)
                                System.out.print(" + ");
                        }
                        temp1 = num;
                        while(temp1 != 0){
                            temp += (temp1 % 10);
                            temp1 /= 10;
                        }
                        System.out.println(" = " + temp + " is divisible by 9");
                       break;
                    case 10:
                        System.out.println("10: the last digit is 0");
                       break;
                    case 11:
                        System.out.print("11: " + num + " = ");
                        if (num > 100)
                            System.out.println((num/10) + "-" + num%10 + " = " + ((num/10) - (num%10)) + " is divisible by 11");
                        else
                            System.out.println(num + " is divisible by 11");
                       break;
                    case 12:
                        System.out.println("12: " + num + " is divisible by 3 and by 4");
                       break;
                    case 13:
                        temp = num;
                        System.out.print("13: "+ num + " = "  + (temp/10));
                        System.out.print("+(4x" + (temp%10) + ") = ");
                        temp = (temp/10) + ((temp%10) * 4);
                        System.out.println(temp + " is a multiple of 13");
                       break;
                    case 14:
                        System.out.println("14: " + num + " is divisible by 2 and by 7");
                       break;
                    case 15:
                        System.out.println("15: " + num + " is divisible by 3 and by 5");
                       break;
                    case 16:
                        temp = num;
                        System.out.print("16: "+ num + " = ("  + (temp/100));
                        System.out.print("x4)" + "+" + (temp%100) + " = ");
                        temp = ((temp/100) * 4) + (temp%100);
                        System.out.println(temp + " is a multiple of 16");
                       break;
                    case 17:
                        temp = num;
                        System.out.print("17: "+ num + " = "  + (temp/10));
                        System.out.print("-(5x" + (temp%10) + ") = ");
                        temp = (temp/10) - ((temp%10) * 5);
                        System.out.println(temp + " is a multiple of 17");
                       break;
                    case 18:
                        System.out.println("18: " + num + " is divisible by 2 and by 9");
                       break;
                    case 19:
                        temp = num;
                        System.out.print("19: "+ num + " = "  + (temp/10));
                        System.out.print("+(2x" + (temp%10) + ") = ");
                        temp = (temp/10) + ((temp%10) * 2);
                        System.out.println(temp + " is a multiple of 19");
                       break;
                    case 20:
                        System.out.println("20: " + num + " is divisible by 10, and by the tens digit is even");
                       break;
                    case 21:
                        System.out.println("21: " + num + " is divisible by 3 and by 7");
                       break;
                    case 22:
                        System.out.println("22: " + num + " is divisible by 2 and by 11");
                       break;
                    case 23:
                        temp = num;
                        System.out.print("23: "+ num + " = "  + (temp/10));
                        System.out.print("+(7x" + (temp%10) + ") = ");
                        temp = (temp/10) + ((temp%10) * 7);
                        System.out.println(temp + " is a multiple of 23");
                       break;
                    case 24:
                        System.out.println("24: " + num + " is divisible by 3 and by 8");
                       break;
                    case 25:
                        System.out.println("The number formed by the last two digits is divisible by 25");
                       break;
                    case 26:
                        System.out.println("26: " + num + " is divisible by 2 and by 13");
                       break;
                    case 27:
                        temp = num;
                        System.out.print("27: "+ num + " = "  + (temp/10));
                        System.out.print("-(8x" + (temp%10) + ") = ");
                        temp = (temp/10) - ((temp%10) * 8);
                        System.out.println(temp + " is a multiple of 27");
                       break;
                    case 28:
                        System.out.println("28: " + num + " is divisible by 4 and by 7");
                       break;
                    case 29:
                        temp = num;
                        System.out.print("29: "+ num + " = "  + (temp/10));
                        System.out.print("+(3x" + (temp%10) + ") = ");
                        temp = (temp/10) + ((temp%10) * 3);
                        System.out.println(temp + " is a multiple of 29");
                       break;
                    case 30:
                        System.out.println("30: " + num + " is divisible by 3 and by 10");
                       break;
               }
           }
       }
    }
    
    static boolean userInput(){
        Scanner input = new Scanner(System.in);
        try{
            System.out.print("Enter a number: ");
            num = input.nextInt();
        }catch(Exception e)
        {
            input.nextLine();
            System.out.println("Invalid input. Try again..");
            return true;
        }
        return false;
    }
}