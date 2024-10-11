import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void punkt_1 (String stroka){
        String temp=""; // временная строка
        String podstroka=""; // максимальная подстрока
        for(int i=0; i<stroka.length(); i++){
            boolean flag = true;
            for (int j=0; j<temp.length(); j++){
               if (stroka.charAt(i)==temp.charAt(j)){
                   flag = false;
                   break;
               }
            }
            if(flag==true){
                temp+=stroka.charAt(i);
            }
            else {
                if(temp.length()>podstroka.length()){
                    podstroka=temp;
                }
                i-=temp.length();
                temp="";
            }
        }
        if(temp.length()>podstroka.length()){ // если нынешний темп больше прошлых
            podstroka=temp;
        }
        System.out.print(podstroka);
    }

    public static void punkt_2 (int[] mass1, int[] mass2){
        int[] newmass = new int[mass1.length + mass2.length];
        for (int i=0; i< mass1.length; i++){
            newmass[i]=mass1[i];
        }
        for (int j=0; j< mass2.length; j++){
            newmass[j+mass1.length]=mass2[j];
        }
        Arrays.sort(newmass);
        for(int i=0; i< newmass.length; i++){
            System.out.print(newmass[i]);
            if (i!= newmass.length-1){
                System.out.print(", ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String stroka = scanner.nextLine();
        System.out.print("1.Подстрока максимальной длины:");
        punkt_1(stroka);

        int[] mass1 = {3, 9, 5, 7, 1};
        int[] mass2 = {8, 4, 2, 0, 6};
        Arrays.sort(mass1);
        Arrays.sort(mass2);
        System.out.print("\n2. Отсортированный массив: ");
        punkt_2(mass1, mass2);
    }
}