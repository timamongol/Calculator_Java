import com.sun.jdi.InvalidTypeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int a, b;
        boolean check1, check2;

        String[] Input;
        try {
            String ConsoleInput = new String(scanner.nextLine());
            Input = new String[4];
            Input = ConsoleInput.split(" ");
        } catch (Exception e) {
            throw new IOException("Выражение должно состоять из двух операндов и оператора");
        }

        if (Input.length != 3){
            throw new IOException("Выражение должно состоять из двух операндов и оператора");
        }

        String input = Input[0];
        try {
            a = Integer.parseInt(input);
            check1 = false;
        } catch (Exception e) {
            try {
                a = ConvertIntoArabic(input);
                check1 = true;
            } catch (Exception e1) {
                throw new IOException("Необходимо вводить либо арабские, либо римские числа!");
            }
        }

        String act = Input[1];
        if (!Objects.equals(act, "+") && !Objects.equals(act, "-") && !Objects.equals(act, "*") && !Objects.equals(act, "/")) {
            throw new IOException("Неверный знак операции!");
        }

        input = Input[2];
        try {
            b = Integer.parseInt(input);
            check2 = false;
        } catch (Exception e) {
            try {
                b = ConvertIntoArabic(input);
                check2 = true;
            } catch (Exception e1) {
                throw new IOException("Необходимо вводить либо арабские, либо римские числа!");
            }
        }

        if (check1 != check2) {
            throw new IOException("Числа должны быть одной системы счисления");
        }

        if (!(check1 && check2)) {
            System.out.println(Action(a, act, b));

        } else {
            if (Action(a, act, b) > 0) {
                System.out.println(ConvertIntoRoman(Action(a, act, b)));
            } else {
                throw new IOException("hимских чисел меньше единицы не существует!");
            }
        }

    }

    public static int Action(int a, String operation, int b){
        switch (operation){
            case "+":
                a += b;
                break;
            case "-":
                a -= b;
                break;
            case "*":
                a *= b;
                break;
            case "/":
                a /= b;
        }

        return a;
    }

    public static int ConvertIntoArabic(String romeNum){
        int num = 0;

        for (int i = 0; i < romeNum.length()-1; i++){
            if (RomeNums.valueOf(romeNum.substring(i, i+1)).getValue() < RomeNums.valueOf(romeNum.substring(i+1, i+2)).getValue()){
                num -= RomeNums.valueOf(romeNum.substring(i, i+1)).getValue();
            }
            else {
                num += RomeNums.valueOf(romeNum.substring(i, i+1)).getValue();
            }
        }
        num += RomeNums.valueOf(romeNum.substring(romeNum.length()-1, romeNum.length())).getValue();

        return num;
    }

    public static StringBuilder ConvertIntoRoman(int arabNum){
        StringBuilder RomanNum = new StringBuilder();
        for (int i = 6; i >= 0; i--){
            while (arabNum >= RomeNums.M.getValue()){
                arabNum -= 1000;
                RomanNum.append("M");
            }
            if (arabNum >= 900){
                arabNum -= 900;
                RomanNum.append("CM");
            }
            while (arabNum >= RomeNums.D.getValue()){
                arabNum -= 500;
                RomanNum.append("D");
            }
            if (arabNum >= 400 && arabNum < 500){
                RomanNum.append("CV");
            }
            while (arabNum >= RomeNums.C.getValue()){
                arabNum -= 100;
                RomanNum.append("C");
            }
            if (arabNum >= 90){
                arabNum -= 90;
                RomanNum.append("XC");
            }
            while (arabNum >= RomeNums.L.getValue()){
                arabNum -= 50;
                RomanNum.append("L");
            }
            if (arabNum >= 40 && arabNum < 50){
                RomanNum.append("XL");
            }
            while (arabNum >= RomeNums.X.getValue()){
                arabNum -= 10;
                RomanNum.append("X");
            }
            if (arabNum == 9){
                arabNum -= 9;
                RomanNum.append("IX");
            }
            while (arabNum >= RomeNums.V.getValue()){
                arabNum -= 5;
                RomanNum.append("V");
            }
            if (arabNum == 4){
                arabNum -= 4;
                RomanNum.append("IV");
            }
            while (arabNum >= RomeNums.I.getValue()){
                arabNum -= 1;
                RomanNum.append("I");
            }
        }
        return RomanNum;
    }
}
