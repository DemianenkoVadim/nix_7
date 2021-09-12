package ua.com.alevel.controller;

import ua.com.alevel.util.MathSet;

import java.util.Scanner;

import static ua.com.alevel.util.Constant.*;
import static ua.com.alevel.util.RequestAndInformationMessages.*;

public class Collections {

    Scanner userInputData = new Scanner(System.in);
    MathSet uniqueValuesMathSet;

    @SuppressWarnings("unchecked")
    public void runApplicationPrintsMenuAndRequestToDo() {
        printsMenuBarMathSetConstructorsAndRequestToChooseActionToDoWithMathSet();
        Scanner userChooseActionToDoWithMathSet = new Scanner(System.in);
        while (true) {
            Scanner userChooseValue = new Scanner(System.in);
            printsMethodMathSetMenu();
            String userChoose = userChooseActionToDoWithMathSet.nextLine();
            switch (userChoose) {
                case USER_CHOOSE_FIRST_POINT: {
                    printsMessageUserInputValue();
                    while (!userChooseValue.hasNextInt()) {
                        printsMessageWrongValue();
                        userChooseValue.next();
                    }
                    Integer number = userChooseValue.nextInt();
                    uniqueValuesMathSet.add(number);
                    uniqueValuesMathSet.printsSetValues();
                }
                break;
                case USER_CHOOSE_SECOND_POINT: {
                    printsMassageUserAmountValues();
                    int setSize = getSize();
                    Integer[] numbers = new Integer[setSize];
                    printsMessageUserInputValue();
                    addsValuesAndChecksCorrectInput(userChooseValue, numbers, uniqueValuesMathSet);
                    uniqueValuesMathSet.printsSetValues();
                }
                break;
                case USER_CHOOSE_THIRD_POINT: {
                    MathSet mathSetInteger = new MathSet<Integer>();
                    printMessageUserInputSizeMathSet();
                    int setSize = getSize();
                    printMessageUserCompositionMathSet();
                    Integer[] numbers = new Integer[setSize];
                    addsValuesAndChecksCorrectInput(userChooseValue, numbers, mathSetInteger);
                    uniqueValuesMathSet.join(mathSetInteger);
                    uniqueValuesMathSet.printsSetValues();
                }
                break;
                case USER_CHOOSE_FOURS_POINT: {
                    printMessageUserAmountMathSets();
                    int setSize = getSize();
                    MathSet[] mathSets = createArrayOfMathSets(setSize);
                    uniqueValuesMathSet.join(mathSets);
                    uniqueValuesMathSet.printsSetValues();
                }
                break;
                case USER_CHOOSE_FIVES_POINT: {
                    printMessageUserInputMathSetForIntersection();
                    MathSet mathSetForIntersection = createMathSet();
                    uniqueValuesMathSet.intersection(mathSetForIntersection);
                    printMessageUserInputIntersectionIs();
                    if (uniqueValuesMathSet.checksForTheExistenceOfValue())
                        printMessageUserNoValue();
                    else {
                        uniqueValuesMathSet.printsSetValues();
                    }
                }
                break;
                case USER_CHOOSE_SIX_POINT: {
                    printMessageUserAmountMathSetsForIntersection();
                    int setSize = getSize();
                    MathSet[] mathSets = createArrayOfMathSets(setSize);
                    uniqueValuesMathSet.intersection(mathSets);
                    printMessageUserInputIntersectionIs();
                    if (uniqueValuesMathSet.checksForTheExistenceOfValue())
                        printMessageUserNoValue();
                    else {
                        uniqueValuesMathSet.printsSetValues();
                    }
                }
                break;
                case USER_CHOOSE_SEVENTH_POINT: {
                    uniqueValuesMathSet.sortDesc();
                    uniqueValuesMathSet.printsSetValues();
                }
                break;
                case USER_CHOOSE_EIGHTS_POINT: {
                    int firstIndex;
                    int lastIndex;
                    printMessageUserFirstIndex();
                    while (!userChooseValue.hasNextInt()) {
                        printMessageUserWrongIndex();
                        userChooseValue.next();
                    }
                    firstIndex = userChooseValue.nextInt();
                    printMessageUserLastIndex();
                    while (!userChooseValue.hasNextInt()) {
                        printMessageUserWrongIndex();
                        userChooseValue.next();
                    }
                    lastIndex = userChooseValue.nextInt();
                    uniqueValuesMathSet.sortDesc(firstIndex, lastIndex);
                    uniqueValuesMathSet.printsSetValues();
                }
                break;
                case USER_CHOOSE_NINE_POINT: {
                    int value;
                    printsMessageUserInputValue();
                    while (!userChooseValue.hasNextInt()) {
                        printsMessageWrongValue();
                        userChooseValue.next();
                    }
                    value = userChooseValue.nextInt();
                    uniqueValuesMathSet.sortDesc(value);
                    uniqueValuesMathSet.printsSetValues();
                }
                break;
                case USER_CHOOSE_TEN_POINT: {
                    uniqueValuesMathSet.sortAsc();
                    uniqueValuesMathSet.printsSetValues();
                }
                break;
                case USER_CHOOSE_ELEVEN_POINT: {
                    int firstIndex;
                    int lastIndex;
                    printMessageUserFirstIndex();
                    while (!userChooseValue.hasNextInt()) {
                        printsMessageWrongValue();
                        userChooseValue.next();
                    }
                    firstIndex = userChooseValue.nextInt();
                    printMessageUserLastIndex();
                    while (!userChooseValue.hasNextInt()) {
                        printsMessageWrongValue();
                        userChooseValue.next();
                    }
                    lastIndex = userChooseValue.nextInt();
                    uniqueValuesMathSet.sortAsc(firstIndex, lastIndex);
                    uniqueValuesMathSet.printsSetValues();
                }
                break;
                case USER_CHOOSE_TWELVE_POINT: {
                    int value;
                    printsMessageWrongValue();
                    while (!userChooseValue.hasNextInt()) {
                        printsMessageWrongValue();
                        userChooseValue.next();
                    }
                    value = userChooseValue.nextInt();
                    uniqueValuesMathSet.sortAsc(value);
                    uniqueValuesMathSet.printsSetValues();
                }
                break;
                case USER_CHOOSE_THIRTY_POINT: {
                    if (uniqueValuesMathSet.checksForTheExistenceOfValue()) {
                        printMessageUserNoValue();
                    } else {
                        uniqueValuesMathSet.printsSetValues();
                    }
                    System.out.println("Math Set size is: " + uniqueValuesMathSet.getSizeMathSet());
                    System.out.println("Math Set capacity is: " + uniqueValuesMathSet.getCapacity());
                }
                break;
                case USER_CHOOSE_FOURTEEN_POINT: {
                    if (!uniqueValuesMathSet.checksForTheExistenceOfValue()) {
                        Number maxValue = uniqueValuesMathSet.getMax();
                        System.out.println(maxValue);
                    } else {
                        printMessageUserNoValue();
                    }
                }
                break;
                case USER_CHOOSE_FIFTEEN_POINT: {
                    if (!uniqueValuesMathSet.checksForTheExistenceOfValue()) {
                        Number minValue = uniqueValuesMathSet.getMin();
                        System.out.println(minValue);
                    } else {
                        printMessageUserNoValue();
                    }
                }
                break;
                case USER_CHOOSE_SIXTEEN_POINT: {
                    if (!uniqueValuesMathSet.checksForTheExistenceOfValue()) {
                        Number average = uniqueValuesMathSet.getAverage();
                        System.out.println(average);
                    } else {
                        printMessageUserNoValue();
                    }
                }
                break;
                case USER_CHOOSE_SEVENTEEN_POINT: {
                    if (!uniqueValuesMathSet.checksForTheExistenceOfValue()) {
                        Number median = uniqueValuesMathSet.getMedian();
                        System.out.println(median);
                    } else {
                        printMessageUserNoValue();
                    }
                }
                break;
                case USER_CHOOSE_EIGHTEEN_POINT: {
                    if (!uniqueValuesMathSet.checksForTheExistenceOfValue()) {
                        Number[] numbers = uniqueValuesMathSet.toArray();
                        printMessageUserArray();
                        for (Number number : numbers) {
                            System.out.print(number + SPACE);
                        }
                        System.out.println();
                    } else {
                        printMessageUserNoValue();
                    }
                }
                break;
                case USER_CHOOSE_NINETEEN_POINT: {
                    if (!uniqueValuesMathSet.checksForTheExistenceOfValue()) {
                        int firstIndex;
                        int lastIndex;
                        printMessageUserFirstIndex();
                        while (!userChooseValue.hasNextInt()) {
                            printsMessageWrongValue();
                            userChooseValue.next();
                        }
                        firstIndex = userChooseValue.nextInt();
                        printMessageUserLastIndex();
                        while (!userChooseValue.hasNextInt()) {
                            printsMessageWrongValue();
                            userChooseValue.next();
                        }
                        lastIndex = userChooseValue.nextInt();
                        Number[] numbers = uniqueValuesMathSet.toArray(firstIndex, lastIndex);
                        if (numbers.length == EMPTY_MATH_SET) {
                            printMessageUserWrongIndex();
                        } else {
                            printMessageUserArray();
                            for (Number number : numbers) {
                                System.out.print(number + SPACE);
                            }
                            System.out.println();
                        }
                    }
                }
                break;
                case USER_CHOOSE_TWENTY_POINT: {
                    if (!uniqueValuesMathSet.checksForTheExistenceOfValue()) {
                        int firstIndex;
                        int lastIndex;
                        printMessageUserFirstIndex();
                        while (!userChooseValue.hasNextInt()) {
                            printsMessageWrongValue();
                            userChooseValue.next();
                        }
                        firstIndex = userChooseValue.nextInt();
                        printMessageUserLastIndex();
                        while (!userChooseValue.hasNextInt()) {
                            printsMessageWrongValue();
                            userChooseValue.next();
                        }
                        lastIndex = userChooseValue.nextInt();
                        MathSet clippedMathSet = uniqueValuesMathSet.cut(firstIndex, lastIndex);
                        if (clippedMathSet.getSizeMathSet() == EMPTY_MATH_SET) {
                            printMessageUserWrongIndex();
                        } else {
                            printMessageUserClippedMathSet();
                            clippedMathSet.printsSetValues();
                        }
                    }
                }
                break;
                case USER_CHOOSE_TWENTY_ONE_POINT: {
                    uniqueValuesMathSet.clear();
                    uniqueValuesMathSet.printsSetValues();
                }
                break;
                case USER_CHOOSE_TWENTY_TWO_POINT: {
                    printMessageUserNumbersToClear();
                    int setSize = getSize();
                    printMessageUserComponentsToClear();
                    Integer[] numbers = new Integer[setSize];
                    for (int value = FIRST_INDEX_ELEMENT; value < numbers.length; value++) {
                        while (!userChooseValue.hasNextInt()) {
                            printsMessageWrongValue();
                            userChooseValue.next();
                        }
                        numbers[value] = userChooseValue.nextInt();
                    }
                    uniqueValuesMathSet.clear(numbers);
                    uniqueValuesMathSet.printsSetValues();
                }
                break;
                case USER_CHOOSE_TWENTY_THREE_POINT: {
                    int pointer;
                    printsMessageUserInputValue();
                    while (!userChooseValue.hasNextInt()) {
                        printsMessageWrongValue();
                        userChooseValue.next();
                    }
                    pointer = userChooseValue.nextInt();
                    if (pointer >= ZERO && pointer < uniqueValuesMathSet.getSizeMathSet()) {
                        System.out.println(uniqueValuesMathSet.get(pointer));
                    } else {
                        printsMessageWrongValue();
                    }
                }
                break;
                case USER_CHOOSE_ZERO_POINT: {
                    return;
                }
                default:
                    printsMessageWrongValue();
                    break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void addsValuesAndChecksCorrectInput(Scanner scanValue, Integer[] numbers, MathSet mathSet) {
        for (int userInputValue = FIRST_INDEX_ELEMENT; userInputValue < numbers.length; userInputValue++) {
            while (!scanValue.hasNextInt()) {
                printsMessageUserInputValue();
                scanValue.next();
            }
            numbers[userInputValue] = scanValue.nextInt();
        }
        mathSet.add(numbers);
    }

    public void printsMenuBarMathSetConstructorsAndRequestToChooseActionToDoWithMathSet() {
        printsMenuBarMathSetConstructors();
        String userChoice = userInputData.nextLine();
        switch (userChoice) {
            case USER_CHOOSE_FIRST_POINT: {
                uniqueValuesMathSet = new MathSet<Integer>();
            }
            break;
            case USER_CHOOSE_SECOND_POINT: {
                printMessageUserInputVolume();
                int capacity = getSize();
                uniqueValuesMathSet = new MathSet<>(capacity);
                System.out.println("Math Set size is: " + uniqueValuesMathSet.getSizeMathSet());
                System.out.println("Math Set capacity is: " + uniqueValuesMathSet.getCapacity());
            }
            break;
            case USER_CHOOSE_THIRD_POINT: {
                printMessageUserInputSizeOfArray();
                Integer[] elements = createArrayForMathSet();
                uniqueValuesMathSet = new MathSet<>(elements);
                uniqueValuesMathSet.printsSetValues();
                System.out.println("Math Set size is: " + uniqueValuesMathSet.getSizeMathSet());
                System.out.println("Math Set capacity is: " + uniqueValuesMathSet.getCapacity());
            }
            break;
            case USER_CHOOSE_FOURS_POINT: {
                printMessageUserInputQuantityOfArray();
                Integer[][] numbers = createArraysForMathSet();
                uniqueValuesMathSet = new MathSet<>(numbers);
                uniqueValuesMathSet.printsSetValues();
                System.out.println("Math Set size is: " + uniqueValuesMathSet.getSizeMathSet());
                System.out.println("Math Set capacity is: " + uniqueValuesMathSet.getCapacity());
            }
            break;
            case USER_CHOOSE_FIVES_POINT: {
                MathSet mathSetForConstructor = createMathSet();
                uniqueValuesMathSet = new MathSet<>(mathSetForConstructor);
                uniqueValuesMathSet.printsSetValues();
                System.out.println("Math Set size is: " + uniqueValuesMathSet.getSizeMathSet());
                System.out.println("Math Set capacity is: " + uniqueValuesMathSet.getCapacity());
            }
            break;
            case USER_CHOOSE_SIX_POINT: {
                printMessageUserAmountMathSets();
                int size = getSize();
                MathSet[] mathSets = createArrayOfMathSets(size);
                uniqueValuesMathSet = new MathSet(mathSets);
                uniqueValuesMathSet.printsSetValues();
                System.out.println("Math Set size is: " + uniqueValuesMathSet.getSizeMathSet());
                System.out.println("Math Set capacity is: " + uniqueValuesMathSet.getCapacity());
            }
            break;
            default:
                uniqueValuesMathSet = new MathSet<Integer>();
                break;
        }
    }

    public int getSize() {
        return getSiMathSet();
    }

    private int getSiMathSet() {
        int setMathSize;
        do {
            while (!userInputData.hasNextInt()) {
                userInputData.next();
                printsMessageWrongValue();
            }
            setMathSize = userInputData.nextInt();
            if (setMathSize <= EMPTY_MATH_SET) {
                printsMessageWrongValue();
            }
        } while (setMathSize <= EMPTY_MATH_SET);
        return setMathSize;
    }

    public Integer[] createArrayForMathSet() {
        int setMathSize = getSize();
        Integer[] value = new Integer[setMathSize];
        printMessageUserInputMultiplicityValues();
        for (int userValue = FIRST_INDEX_ELEMENT; userValue < value.length; userValue++) {
            while (!userInputData.hasNextInt()) {
                printsMessageWrongValue();
                userInputData.next();
            }
            value[userValue] = userInputData.nextInt();
        }
        return value;
    }

    public Integer[][] createArraysForMathSet() {
        int size = getSize();
        Integer[][] values = new Integer[size][];
        int counter = START_ZERO_POINT;
        while (counter < size) {
            System.out.println(SIZE_ARRAY + (counter + ONE_POSITION_SOURCE_ARRAY) + ARRAY);
            int sizeIntegerValuesArray = getSize();
            values[counter] = new Integer[sizeIntegerValuesArray];
            counter++;
        }
        for (int oneValue = FIRST_INDEX_ELEMENT; oneValue < values.length; oneValue++) {
            System.out.println(VALUES_OF_SET + (oneValue + ONE_POSITION_SOURCE_ARRAY) + ARRAY);
            for (int anotherValue = FIRST_ELEMENT_IN_ARRAY; anotherValue < values[oneValue].length; anotherValue++) {
                while (!userInputData.hasNextInt()) {
                    printsMessageUserInputValue();
                    userInputData.next();
                }
                values[oneValue][anotherValue] = userInputData.nextInt();
            }
        }
        return values;
    }

    @SuppressWarnings("unchecked")
    public MathSet createMathSet() {
        Scanner scanValue = new Scanner(System.in);
        printsRequestForCreateMathSet();
        String userChoice = scanValue.nextLine();
        MathSet mathSetForConstructor = new MathSet<Integer>();
        while (!(userChoice.equals(USER_CHOOSE_FIRST_POINT) || userChoice.equals(USER_CHOOSE_SECOND_POINT))) {
            printsMessageWrongValue();
            userChoice = userInputData.nextLine();
        }
        if (userChoice.equals(USER_CHOOSE_FIRST_POINT)) {
            printMessageUserInputSizeMathSet();
            int mathSetSize = getSize();
            printMessageUserCompositionMathSet();
            Integer[] numbers = new Integer[mathSetSize];
            addsValuesAndChecksCorrectInput(userInputData, numbers, mathSetForConstructor);
        } else {
            int size = (int) ((Math.random() * FACTOR_TWENTY) + ONE_POSITION_SOURCE_ARRAY);
            Integer[] numbers = new Integer[size];
            for (int mathSetValue = FIRST_INDEX_ELEMENT; mathSetValue < numbers.length; mathSetValue++) {
                numbers[mathSetValue] = (int) ((Math.random() * FACTOR_TWO_HUNDRED_ONE) - FACTOR_HUNDRED);
            }
            mathSetForConstructor.add(numbers);
        }
        return mathSetForConstructor;
    }

    @SuppressWarnings("unchecked")
    public MathSet[] createArrayOfMathSets(int size) {
        MathSet[] uniqueValuesMathSet = new MathSet[size];
        for (int oneValue = FIRST_INDEX_ELEMENT; oneValue < size; oneValue++) {
            uniqueValuesMathSet[oneValue] = new MathSet<Integer>();
        }
        int mathSetSize;
        for (int oneValue = FIRST_INDEX_ELEMENT; oneValue < uniqueValuesMathSet.length; oneValue++) {
            printMessageUserInputSizeMathSet();
            mathSetSize = getSiMathSet();
            System.out.println(VALUES_OF_SET + (oneValue + ONE_POSITION_SOURCE_ARRAY) + MATH_SET);
            for (int anotherOneValue = FIRST_INDEX_ELEMENT; anotherOneValue < mathSetSize; anotherOneValue++) {
                while (!userInputData.hasNextInt()) {
                    printsMessageWrongValue();
                    userInputData.next();
                }
                uniqueValuesMathSet[oneValue].add(userInputData.nextInt());
            }
        }
        return uniqueValuesMathSet;
    }
}
