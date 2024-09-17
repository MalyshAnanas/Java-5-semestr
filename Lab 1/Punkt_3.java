import java.util.Objects;
import java.util.Scanner;

public class Punkt_3 {
    public static void main (String[] args){
        System.out.print("Введите координаты клада: ");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int X = 0; //Где мы находимся сейчас по х
        int Y = 0; //Где мы находимся сейчас по х
        scanner.nextLine();
        String Path = scanner.nextLine();
        int count = 0;
        while (!Objects.equals(Path, "стоп")){
            int a = scanner.nextInt();
            if(X!=x || Y!=y){
                switch (Path){
                    case "север":
                        Y+=a;
                        break;
                    case "юг":
                        Y-=a;
                        break;
                    case "запад":
                        X-=a;
                        break;
                    case "восток":
                        X+=a;
                        break;
                }
                count++;
            }
            scanner.nextLine();
            Path = scanner.nextLine();
        }
        System.out.printf("минимальное количество указаний карты: " + count);
    }
}
