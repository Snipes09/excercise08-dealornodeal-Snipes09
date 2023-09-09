package application;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Willkommen bei Deal or No Deal!");

        DealOrNoDeal dealOrNoDeal = new DealOrNoDeal();
        dealOrNoDeal.startGame();

        sc.close();
    }
}
