package application;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DealOrNoDeal {
    private ArrayList<Double> cases;
    private int chosenCase;
    private int[] rounds = {6, 5, 4, 3, 2, 1, 1, 1, 1};  // Anzahl der in jeder Runde zu entfernenden Koffer

    public DealOrNoDeal() {
        initCases();
    }

    public void initCases() {
        Double[] values = {0.01, 1.0, 5.0, 10.0, 25.0, 50.0, 75.0, 100.0, 200.0, 300.0, 400.0, 500.0, 750.0, 1000.0, 5000.0,
                10000.0, 25000.0, 50000.0, 75000.0, 100000.0, 200000.0, 300000.0, 400000.0, 500000.0, 750000.0, 1000000.0};
        cases = new ArrayList<>();
        Collections.addAll(cases, values);
        Collections.shuffle(cases);
    }

    public void startGame() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Wählen Sie einen Koffer (1-26): ");
        chosenCase = sc.nextInt() - 1;

        while (!handleErrors(chosenCase)) {
            System.out.print("Wählen Sie einen anderen Koffer (1-26): ");
            chosenCase = sc.nextInt() - 1;
        }

        for (int i = 0; i < 9; i++) {
            int numToRemove = rounds[i];
            System.out.println("Runde " + (i + 1));

            for (int j = 0; j < numToRemove; j++) {
                System.out.print("Wählen Sie einen Koffer zum Entfernen (1-26): ");
                int caseToRemove = sc.nextInt() - 1;

                while (!handleErrors(caseToRemove)) {
                    System.out.print("Wählen Sie einen anderen Koffer zum Entfernen (1-26): ");
                    caseToRemove = sc.nextInt() - 1;
                }

                System.out.println("Entfernter Koffer " + (caseToRemove + 1) + ", Wert: $" + cases.get(caseToRemove));
                cases.set(caseToRemove, null);
            }

            double offer = calculateOffer(i + 1);
            System.out.printf("Die Bank bietet Ihnen: $%.2f\n", offer);
            System.out.print("Deal oder nicht? (d/n): ");
            String deal = sc.next().trim();
            if (deal.equalsIgnoreCase("d")) {
                System.out.println("Herzlichen Glückwunsch! Sie haben $" + offer + " gewonnen!");
                return;
            }
        }

        System.out.print("Möchten Sie Ihren gewählten Koffer wechseln? (y/n): ");
        String switchCase = sc.next().trim();
        if (switchCase.equalsIgnoreCase("y")) {
            for (int i = 0; i < 26; i++) {
                if (i != chosenCase && cases.get(i) != null) {
                    chosenCase = i;
                    break;
                }
            }
        }

        System.out.println("Herzlichen Glückwunsch! Sie haben $" + cases.get(chosenCase) + " gewonnen!");
    }

    private double calculateOffer(int round) {
        double total = 0.0;
        int count = 0;
        for (Double value : cases) {
            if (value != null) {
                total += value;
                count++;
            }
        }
        return (total / count) * round / 10;
    }

    private boolean handleErrors(int caseNumber) {
        if (caseNumber < 0 || caseNumber >= 26 || cases.get(caseNumber) == null) {
            System.out.println("Ungültige Eingabe, bitte versuchen Sie es erneut.");
            return false;
        }

        return true;
    }
}
