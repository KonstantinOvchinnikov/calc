
import java.util.Scanner;
class Ex extends Throwable {
    public Ex(String message) {
        super(message);
    }
}
public class Main {

    public static String calc(String input){
        System.out.print(input);
        return input;
    }

    static boolean isNum(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    static int calcGet(int[] a, String b)throws Ex{
        int n = 0;
        switch (b) {
            case "+": n = a[0] + a[1]; break;
            case "-": n = a[0] - a[1]; break;
            case "*": n = a[0] * a[1]; break;
            case "/": n = a[0] / a[1]; break;
            default: throw  new Ex("Некорректно введен оператор");
        }
        return n;
    }

    static String inArGetRo(int i)throws Ex {
        if (i < 1) throw  new Ex("Результат вычисления с Римскими цифрами меньше 1");
        else {
            int[] counter = {1, 5, 10, 50, 100};
            String[] rom = {"I", "V", "X", "L", "C"};
            StringBuilder q = new StringBuilder();
            for (int x = 5; x > 0; --x) {
                if ((i / counter[x - 1]) > 0) {
                    int w = i / counter[x - 1];
                    i %= counter[x - 1];
                    if (w < 4) {
                        for (; w != 0; --w) {
                            q.append(rom[x - 1]);
                        }
                    } else {
                        q.append(rom[x - 1]);
                        q.append(rom[x]);
                    }
                }
            }
            String getRom = q.toString();
            return getRom;
        }
    }

    public static void main(String[] args) throws Ex {
        int getCalc;
        String numFinaly = new String();
        String[] romNum = {"0","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        Scanner text = new Scanner(System.in);
        System.out.print("Введите математическое выражение :");
        String[] str = text.nextLine().split(" ");
        text.close();
        if (str.length != 3) throw  new Ex("Некорректный ввод. Пример ввода: x + y");
        else {
            int[] arabNum = new int[]{0, 0, 0, 0};
            String sign = new String();
            int count1 = 0;
            for (int x = 0; x <= str.length - 1; ++x) {
                if (x == 0 || x == 2) {
                    if (isNum(str[x])) { // Проверка на принадлежность к Арабским цифрам
                        arabNum[count1] = Integer.parseInt(str[x]);
                        arabNum[count1 + 2] = 1;
                        ++count1;
                    } else
                        for (int y = 0; y <= romNum.length - 1; ++y) {  // Проверка на вхождение в список Римских цифр
                            if (romNum[y].equals(str[x])) {
                                arabNum[count1] = y;
                                arabNum[count1 + 2] = 2;
                                ++count1;
                            }
                        }
                } else if (x == 1) sign = str[x];
            }

            if ((arabNum[2] == arabNum[3]) & arabNum[2] != 0 & arabNum[0] < 11 & arabNum[1] < 11) {
                if (arabNum[3] == 1) {
                    getCalc = calcGet(arabNum, sign);
                    numFinaly = Integer.toString(getCalc);
                } else if (arabNum[3] == 2) {
                    getCalc = calcGet(arabNum, sign);
                    numFinaly = inArGetRo(getCalc);
                }
            }
            else throw  new Ex("Некорректный ввод, либо операнд больше 10");
        }
        calc(numFinaly);
    }
}