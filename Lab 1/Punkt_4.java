import java.util.Scanner;

public class Punkt_4 {
    public static void main(String[] args){
        System.out.print("Введите количество дорог: ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int numberPath = 0;
        int max = 0;
        for (int i=0; i<number; i++){
            int min = Integer.MAX_VALUE;
            int Tunnel = scanner.nextInt();
            for(int q=0; q<Tunnel; q++){
                int height = scanner.nextInt();
                if(min>height){
                    min=height;
                }
            }
            if (max<min){
                max=min;
                numberPath = i+1;
            }
        }
        System.out.printf(numberPath + " " + max);
    }
}
