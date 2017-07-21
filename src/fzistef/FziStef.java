package fzistef;

import java.util.ArrayList;
import java.util.Scanner;


class FziStef {
    public static void main(String[] args) throws java.lang.Exception {
        TripAnalyser analyser = new TripAnalyser();
        System.out.println(analyser.getMaxSalary());
    }

}


class TripAnalyser {
    private Scanner sc = new Scanner(System.in);


    long getMaxSalary() {
        int numberOfCities = sc.nextInt();
        long maxSalary = 0;
        long currentSalary;

        ArrayList<Integer> profitability = new ArrayList<>(numberOfCities);

        for (int i = 0; i < numberOfCities; i++ ){

            profitability.add(i, sc.nextInt());
        }

        for (int i = 1; i <= profitability.size(); i++) {
            for (int j = 0; j <= profitability.size() - i; j++) {
                currentSalary = profitability
                        .subList(j, j + i)
                        .stream()
                        .reduce(0, Integer::sum);


                if (currentSalary > maxSalary) {
                    maxSalary = currentSalary;
                }
            }
        }

        return maxSalary > 0 ? maxSalary : 0;
    }
}
