package comp127;

import java.util.Scanner;

public class MoneyConverter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("How much money do you have in dollars: $");
        double allMyMoney = Math.round(in.nextDouble() * 100);

        System.out.print("What is the value of a shmeckel in cents: ¢");
        double value = in.nextDouble();

        double shmeckels = allMyMoney / value;
        shmeckels = Math.round(shmeckels * 100) / 100.0;
        System.out.println("You have " + shmeckels + " shmeckels!");

    }
}
