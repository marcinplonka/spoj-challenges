package scardfi;

import java.util.*;
import java.lang.*;

class Main {


    public static void main(String[] args) throws java.lang.Exception {
        StepCounter stepCounter = new StepCounter();
        StepReducer stepReducer = new StepReducer();

        stepReducer.reduceSteps(stepCounter.countAllStepsForAllDatasets());
        stepReducer.printResults();

    }
}

class StepCounter {

    private Scanner scanner = new Scanner(System.in);
    private final int NUMBER_OF_DATASETS = scanner.nextInt();

    ArrayList<List<Integer>> countAllStepsForAllDatasets() {

        ArrayList<List<Integer>> allSteps = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_DATASETS; i++) {
            List<Integer> stepsForCurrentDataset = Arrays.asList(0, 0, 0, 0);
            int numberOfInstruction = scanner.nextInt();

            for (int j = 0; j < numberOfInstruction; j++) {
                Integer direction = scanner.nextInt();
                Integer steps = scanner.nextInt();


                stepsForCurrentDataset.set(direction, (stepsForCurrentDataset.get(direction) + steps));

            }

            allSteps.add(i, stepsForCurrentDataset);

        }

        return allSteps;

    }
}

class StepReducer {

    private StringBuilder stringBuilder = new StringBuilder();

    void reduceSteps(ArrayList<List<Integer>> listOfAllSteps) {
        for (List<Integer> step : listOfAllSteps) {


            if (stepsAfterReductionEqualZero(step)) {
                stringBuilder
                        .append("studnia\n");
            } else {
                reduceStepsNorthSouth(step);
                reduceStepsEastWest(step);

            }

        }

    }


    private void reduceStepsNorthSouth(List<Integer> step) {

        int northSteps = step.get(Directions.NORTH.ordinal()) - step.get(Directions.SOUTH.ordinal());
        int southSteps;

        if ((northSteps > 0)) {

            stringBuilder
                    .append(Directions.NORTH.ordinal())
                    .append(" ")
                    .append(northSteps)
                    .append("\n");

        } else if (northSteps != 0) {

            southSteps = northSteps * -1;

            stringBuilder
                    .append(Directions.SOUTH.ordinal())
                    .append(" ")
                    .append(southSteps)
                    .append("\n");
        }


    }

    private void reduceStepsEastWest(List<Integer> step) {

        int westSteps = step.get(Directions.WEST.ordinal()) - step.get(Directions.EAST.ordinal());
        int eastSteps;

        if (westSteps > 0) {
            stringBuilder
                    .append(Directions.WEST.ordinal())
                    .append(" ")
                    .append(westSteps)
                    .append("\n");

        } else if (westSteps != 0) {

            eastSteps = westSteps * -1;
            stringBuilder
                    .append(Directions.EAST.ordinal())
                    .append(" ")
                    .append(eastSteps)
                    .append("\n");
        }

    }

    private boolean stepsAfterReductionEqualZero(List<Integer> step) {

        return step.stream()
                .mapToInt(i -> i)
                .sum() == 0;

    }


    void printResults() {
        System.out.println(stringBuilder.toString());
    }
}

enum Directions {
    NORTH,
    SOUTH,
    WEST,
    EAST

}


