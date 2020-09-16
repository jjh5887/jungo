package sample;

import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        String buf;
        Scanner scanner = new Scanner(System.in);
        buf = scanner.nextLine();
        buf = buf.trim();
        String [] subject = buf.split(",");
        buf = null;
        buf = scanner.nextLine();
        buf = buf.trim();
        String [] compare_subject = buf.split(",");

        for (int i = 0; i < subject.length; i++){
            if (subject[i].compareTo(compare_subject[i]) == 1)
                System.out.println(subject[i] + " 과 " + compare_subject[i] + " 일치하지 않습니다");
             else
                System.out.println(subject[i] + " 과 " + compare_subject[i] + " 일치");
        }
    }
}
