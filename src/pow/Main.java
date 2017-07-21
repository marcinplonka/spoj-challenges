package pow;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.getLastDigitsOfPowerResults();
        calculator.printResult();

    }
}

    class Calculator {
        StringBuilder sb = new StringBuilder();
        void getLastDigitsOfPowerResults() {

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

                sb.append((int) (((Math.pow(lastDigitOfa, sequenceNumber) / 10) % 1) * 10));
                sb.append("\n");


            }


            }

            void printResult() {
                System.out.println(sb.toString());
        }


    }




