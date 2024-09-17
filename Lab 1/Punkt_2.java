import java.util.Scanner;

public class Punkt_2 {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите натуральное число n>0: ");
        int number = scanner.nextInt();
        int Riad = 0; //сумма ряда
        for (int i=1; i<=number; i++){
            Scanner in = new Scanner(System.in);
            int a = in.nextInt();
            if (i%2==1){
                Riad+=a;
            }
            else{
                Riad-=a;
            }
        }
        System.out.printf("Получившаяся сумма ряда: " + Riad);
    }
}
