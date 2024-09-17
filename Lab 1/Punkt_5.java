import java.util.Scanner;

public class Punkt_5 {
    public static void main (String[] args){
        System.out.print("Введите трёхзначное число: ");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int units = a%10;
        a/=10;
        int tenth = a%10;
        a/=10;
        int hundredths = a%10;
        if ((units+tenth+hundredths) % 2 == 0 & (units*tenth*hundredths) % 2 == 0){
            System.out.print("Является");
        }
        else {
            System.out.print("Не является");
        }
    }
}
