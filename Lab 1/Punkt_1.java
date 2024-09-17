import java.util.Scanner;

public class Punkt_1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in); // просим у пользователя ввести наше число
        System.out.print("Введите любое одно натуральное число: ");
        int number = scanner.nextInt(); // забираем число у пользователя)
        int count =0;
        while (number != 1){
            if (number % 2 == 0){
                number/=2;
                count++;
            }
            else{
                number = number*3+1;
                count++;
            }
        }
        System.out.printf("Количество шагов: " + count);
    }
}