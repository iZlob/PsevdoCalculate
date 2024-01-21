import java.util.Scanner;
import java.lang.String;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String expression = input.nextLine();

        try {
            char[] expChar = expression.toCharArray();
            String str_num1 = "";
            String str_num2 = "";
            String result = "";
            char operand;

            int i = 0;
            while (expChar[i] != '+' && expChar[i] != '-' && expChar[i] != '*' && expChar[i] != '/') {
                str_num1 += expChar[i];
                i++;
            }
            operand = expChar[i++];
            while (i < expChar.length) {
                str_num2 += expChar[i];
                i++;
            }

            switch (operand){
                case '+': {
                    double rez = Double.parseDouble(str_num1) + Double.parseDouble(str_num2);
                    result += String.format("%.3f", rez);
                    break;
                }
                case '-': {
                    double rez = Double.parseDouble(str_num1) - Double.parseDouble(str_num2);
                    result += String.format("%.3f", rez);
                    break;
                }
                case '*':{
                    double rez = Double.parseDouble(str_num1) * Double.parseDouble(str_num2);
                    result += String.format("%.3f", rez);
                    break;
                }
                case '/': {
                    double rez = Double.parseDouble(str_num1) / Double.parseDouble(str_num2);
                    result += String.format("%.3f", rez);
                    break;
                }
                default: break;
            }

            DBCreator db = new DBCreator(expression + "=" + result);
            db.getPseudoExpression();

        }catch (Exception e){
            e.getMessage();
        }

    }
}