package pow;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        PowerCalculator powerCalculator = new PowerCalculator();
        powerCalculator.getLastDigitOfPowerResult();
        powerCalculator.printResult();

    }
}

class PowerCalculator {
    StringBuilder sb = new StringBuilder();

    void getLastDigitOfPowerResult() {

        Scanner input = new Scanner(System.in);
        final int DATASETS = input.nextInt();

        for (int i = 0; i < DATASETS; i++) {
            int a = input.nextInt();
            int b = input.nextInt();

            int lastDigitOfa = a % 10;

            int sequenceNumber = b % 4;

            if (sequenceNumber == 0) {
                sequenceNumber = 4;
            }

            sb.append((int) (((Math.pow(lastDigitOfa, sequenceNumber) / 10) % 1) * 10))
                    .append("\n");
        }
    }

    void printResult() {
        System.out.print(sb);
    }

}




