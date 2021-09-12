package ua.com.alevel.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ua.com.alevel.util.Constant.*;
import static ua.com.alevel.util.RequestAndInformationMessages.printsMessageSetOutOfRange;
import static ua.com.alevel.util.RequestAndInformationMessages.printsMessageWrongIndexes;

public class MathSet<UserValues extends Number & Comparable<UserValues>> {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    private UserValues[] numbers;
    private int sizeMathSet;
    private int capacity = CAPACITY_SIZE_MATH_SET_EQUALS_TEN;

    public UserValues get(int userValue) {
        return numbers[userValue];
    }

    public int getSizeMathSet() {
        return sizeMathSet;
    }

    public int getCapacity() {
        return capacity;
    }

    @SuppressWarnings("unchecked")
    public MathSet() {
        numbers = (UserValues[]) new Number[capacity];
        sizeMathSet = EMPTY_MATH_SET;
    }

    @SuppressWarnings("unchecked")
    public MathSet(int capacity) {
        this.capacity = capacity;
        numbers = (UserValues[]) new Number[capacity];
        sizeMathSet = EMPTY_MATH_SET;
    }

    @SuppressWarnings("unchecked")
    public MathSet(UserValues[] numbers) {
        capacity += numbers.length;
        this.numbers = (UserValues[]) new Number[capacity];
        sizeMathSet = numbers.length;
        System.arraycopy(numbers, STARTING_POSITION_SOURCE_ARRAY, this.numbers, STARTING_POSITION_DESTINATION_DATA, sizeMathSet);
        removeDuplicateValues();
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public MathSet(UserValues[]... numbers) {
        int lengthMathSet = ORIGINAL_LENGTH_MATH_SET;
        for (UserValues[] inputValue : numbers) {
            lengthMathSet = lengthMathSet + inputValue.length;
        }
        sizeMathSet = lengthMathSet;
        capacity += lengthMathSet;
        this.numbers = (UserValues[]) new Number[capacity];
        int enumerator = START_ZERO_POINT;
        for (UserValues[] value : numbers) {
            for (UserValues userValues : value) {
                this.numbers[enumerator] = userValues;
                enumerator++;
            }
        }
        removeDuplicateValues();
    }

    @SuppressWarnings("unchecked")
    public MathSet(MathSet<? extends UserValues> numbers) {
        capacity = numbers.getCapacity();
        sizeMathSet = numbers.getSizeMathSet();
        this.numbers = (UserValues[]) new Number[capacity];
        for (int mathSetValue = FIRST_INDEX_ELEMENT; mathSetValue < numbers.getSizeMathSet(); mathSetValue++) {
            this.numbers[mathSetValue] = numbers.get(mathSetValue);
        }
    }

    @SuppressWarnings("unchecked")
    public MathSet(MathSet... numbers) {
        int lengthMathSet = ORIGINAL_LENGTH_MATH_SET;
        for (MathSet element : numbers) {
            lengthMathSet = lengthMathSet + element.getSizeMathSet();
        }
        sizeMathSet = lengthMathSet;
        capacity = capacity + lengthMathSet;
        this.numbers = (UserValues[]) new Number[capacity];
        int enumerator = START_ZERO_POINT;
        for (MathSet oneValue : numbers) {
            for (int value = FIRST_INDEX_ELEMENT; value < oneValue.getSizeMathSet(); value++) {
                this.numbers[enumerator] = (UserValues) oneValue.get(value);
                enumerator++;
            }
        }
        removeDuplicateValues();
    }

    @SuppressWarnings("unchecked")
    public void add(UserValues number) {
        if (sizeMathSet == capacity - ONE_ELEMENT_IN_ARRAY) {
            capacity = capacity * INCREASE_SIZE_ARRAY_BY_TWO_TIMES;
            UserValues[] newNumbers = (UserValues[]) new Number[capacity];
            System.arraycopy(numbers, STARTING_POSITION_SOURCE_ARRAY, newNumbers, STARTING_POSITION_DESTINATION_DATA, numbers.length);
            numbers = newNumbers;
        }
        LOGGER_INFO.info("Add userValue number");
        if (!checksUniquenessOfValue(number)) {
            numbers[sizeMathSet] = number;
            sizeMathSet++;
        }
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final void add(UserValues... number) {
        if (number.length >= capacity - sizeMathSet) {
            capacity = capacity * INCREASE_SIZE_ARRAY_BY_TWO_TIMES + number.length;
            UserValues[] newNumbers = (UserValues[]) new Number[capacity];
            System.arraycopy(numbers, STARTING_POSITION_SOURCE_ARRAY, newNumbers, STARTING_POSITION_DESTINATION_DATA, numbers.length);
            numbers = newNumbers;
        }
        LOGGER_INFO.info("Add UserValues... number");
        for (UserValues userValues : number) {
            add(userValues);
        }
    }

    public void removeDuplicateValues() {
        LOGGER_INFO.info("Remove duplicate values");
        for (int oneCheckingElement = FIRST_INDEX_ELEMENT; oneCheckingElement < sizeMathSet; oneCheckingElement++)
            for (int anotherOneCheckingElement = FIRST_INDEX_ELEMENT; anotherOneCheckingElement < sizeMathSet; anotherOneCheckingElement++)
                if (oneCheckingElement != anotherOneCheckingElement) {
                    if (numbers[oneCheckingElement].equals(numbers[anotherOneCheckingElement])) {
                        System.arraycopy(numbers, anotherOneCheckingElement + ONE_POSITION_SOURCE_ARRAY, numbers, anotherOneCheckingElement, sizeMathSet - anotherOneCheckingElement);
                        sizeMathSet--;
                    }
                }
    }

    public boolean checksUniquenessOfValue(UserValues value) {
        LOGGER_INFO.info("Checks unique values");
        for (int userInputValue = FIRST_INDEX_ELEMENT; userInputValue < sizeMathSet; userInputValue++)
            if (numbers[userInputValue].equals(value)) {
                return true;
            }
        return false;
    }

    @SuppressWarnings("unchecked")
    public void join(MathSet ms) {
        if (ms.getSizeMathSet() >= capacity - sizeMathSet) {
            capacity = capacity * INCREASE_SIZE_ARRAY_BY_TWO_TIMES + ms.getSizeMathSet();
            UserValues[] newNumbers = (UserValues[]) new Number[capacity];
            System.arraycopy(numbers, STARTING_POSITION_SOURCE_ARRAY, newNumbers, STARTING_POSITION_DESTINATION_DATA, numbers.length);
            numbers = newNumbers;
        }
        LOGGER_INFO.info("Join MathSet ms");
        for (int mathSetValue = FIRST_INDEX_ELEMENT; mathSetValue < ms.getSizeMathSet(); mathSetValue++) {
            numbers[sizeMathSet] = (UserValues) ms.get(mathSetValue);
            sizeMathSet++;
        }
        removeDuplicateValues();
        sortAsc();
    }

    @SuppressWarnings("unchecked")
    public void join(MathSet... ms) {
        LOGGER_INFO.info("Join MathSet... ms");
        int lengthMathSet = ORIGINAL_LENGTH_MATH_SET;
        for (MathSet value : ms) {
            lengthMathSet = lengthMathSet + value.getSizeMathSet();
        }
        if (lengthMathSet >= capacity - sizeMathSet) {
            capacity = capacity * INCREASE_SIZE_ARRAY_BY_TWO_TIMES + lengthMathSet;
            UserValues[] newNumbers = (UserValues[]) new Number[capacity];
            System.arraycopy(numbers, STARTING_POSITION_SOURCE_ARRAY, newNumbers, STARTING_POSITION_DESTINATION_DATA, numbers.length);
            numbers = newNumbers;
        }
        for (MathSet value : ms) {
            for (int mathSetValue = FIRST_INDEX_ELEMENT; mathSetValue < value.getSizeMathSet(); mathSetValue++) {
                numbers[sizeMathSet] = (UserValues) value.get(mathSetValue);
                sizeMathSet++;
            }
        }
        LOGGER_WARN.warn("Remove duplicate value");
        removeDuplicateValues();
        sortAsc();
    }

    @SuppressWarnings("unchecked")
    public void intersection(MathSet ms) {
        if (ms.getSizeMathSet() >= capacity - sizeMathSet) {
            capacity = capacity * INCREASE_SIZE_ARRAY_BY_TWO_TIMES + ms.getSizeMathSet();
            UserValues[] newNumbers = (UserValues[]) new Number[capacity];
            System.arraycopy(numbers, STARTING_POSITION_SOURCE_ARRAY, newNumbers, STARTING_POSITION_DESTINATION_DATA, numbers.length);
            numbers = newNumbers;
        }
        LOGGER_INFO.info("Intersection MathSet ms");
        UserValues[] intersectionNumbers = (UserValues[]) new Number[capacity];
        int enumerator = START_ZERO_POINT;
        for (int oneMathSetValue = FIRST_INDEX_ELEMENT; oneMathSetValue < sizeMathSet; oneMathSetValue++) {
            for (int comperedMathSetValue = FIRST_INDEX_ELEMENT; comperedMathSetValue < ms.getSizeMathSet(); comperedMathSetValue++) {
                if (numbers[oneMathSetValue].equals(ms.get(comperedMathSetValue))) {
                    intersectionNumbers[enumerator] = numbers[oneMathSetValue];
                    enumerator++;
                    break;
                }
            }
        }
        numbers = intersectionNumbers;
        sizeMathSet = enumerator;
    }

    public void intersection(MathSet... ms) {
        LOGGER_INFO.info("Intersection MathSet... ms");
        for (MathSet mathSetValue : ms) {
            intersection(mathSetValue);
        }
    }

    public UserValues getMax() {
        LOGGER_INFO.info("Getting max value");
        UserValues maxNumber = numbers[FIRST_ELEMENT_IN_ARRAY];
        for (int mathSetValue = FIRST_INDEX_ELEMENT; mathSetValue < sizeMathSet; mathSetValue++) {
            if (numbers[mathSetValue].compareTo(maxNumber) > ZERO) {
                maxNumber = numbers[mathSetValue];
            }
        }
        return maxNumber;
    }

    public UserValues getMin() {
        LOGGER_INFO.info("Getting min value");
        UserValues maxNumber = numbers[FIRST_ELEMENT_IN_ARRAY];
        for (int mathSetValue = FIRST_INDEX_ELEMENT; mathSetValue < sizeMathSet; mathSetValue++) {
            if (numbers[mathSetValue].compareTo(maxNumber) < ZERO) {
                maxNumber = numbers[mathSetValue];
            }
        }
        return maxNumber;
    }

    public void sortAsc() {
        LOGGER_INFO.info("Ascending sorting set");
        int anotherOneComparedValue;
        for (anotherOneComparedValue = SECOND_VALUE_IN_ARRAY; anotherOneComparedValue < sizeMathSet; anotherOneComparedValue++) {
            comparesValuesForSortAsc(anotherOneComparedValue, ZERO);
        }
    }

    private void comparesValuesForSortAsc(int anotherOneComparedValue, int zero) {
        LOGGER_INFO.info("Compares two both values");
        UserValues temporaryValues;
        int oneComperedValue;
        temporaryValues = numbers[anotherOneComparedValue];
        oneComperedValue = anotherOneComparedValue;
        while (oneComperedValue > zero && (numbers[oneComperedValue - ONE_ELEMENT_IN_ARRAY].compareTo(temporaryValues)) >= ZERO) {
            numbers[oneComperedValue] = numbers[oneComperedValue - ONE_ELEMENT_IN_ARRAY];
            --oneComperedValue;
        }
        numbers[oneComperedValue] = temporaryValues;
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        LOGGER_INFO.info("Ascending sorting between two indexes");
        if (firstIndex >= sizeMathSet || lastIndex >= sizeMathSet || firstIndex < ZERO || lastIndex < ZERO || firstIndex > lastIndex) {
            printsMessageWrongIndexes();
        } else {
            int anotherOneComparableValue;
            for (anotherOneComparableValue = firstIndex; anotherOneComparableValue <= lastIndex; anotherOneComparableValue++) {
                comparesValuesForSortAsc(anotherOneComparableValue, firstIndex);
            }
        }
    }

    public void sortAsc(UserValues value) {
        LOGGER_INFO.info("Ascending sorting from value");
        int setValue = checksSetIncludesValue(value);
        if (setValue == SET_DOESNT_INCLUDE_VALUE) {
            printsMessageSetOutOfRange();
        } else {
            int anotherOneComparableValue;
            for (anotherOneComparableValue = setValue + ONE_ELEMENT_IN_ARRAY; anotherOneComparableValue < sizeMathSet; anotherOneComparableValue++) {
                comparesValuesForSortAsc(anotherOneComparableValue, setValue);
            }
        }
    }

    public void sortDesc() {
        LOGGER_INFO.info("Descending sorting set");
        int anotherOneComparableValue;
        for (anotherOneComparableValue = ONE_ELEMENT_IN_ARRAY; anotherOneComparableValue < sizeMathSet; anotherOneComparableValue++) {
            comparesValuesForSortDesc(ZERO, anotherOneComparableValue);
        }
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        LOGGER_INFO.info("Descending sorting set between two indexes");
        if (firstIndex >= sizeMathSet || lastIndex >= sizeMathSet || firstIndex < ZERO || lastIndex < ZERO || firstIndex > lastIndex) {
            printsMessageWrongIndexes();
        } else {
            int anotherOneComparableValue;
            for (anotherOneComparableValue = firstIndex; anotherOneComparableValue <= lastIndex; anotherOneComparableValue++) {
                comparesValuesForSortDesc(firstIndex, anotherOneComparableValue);
            }
        }
    }

    public void sortDesc(UserValues value) {
        LOGGER_INFO.info("Descending sorting from value");
        int setValue = checksSetIncludesValue(value);
        if (setValue == SET_DOESNT_INCLUDE_VALUE) {
            printsMessageSetOutOfRange();
        } else {
            int anotherOneComparableValue;
            for (anotherOneComparableValue = setValue + ONE_ELEMENT_IN_ARRAY; anotherOneComparableValue < sizeMathSet; anotherOneComparableValue++) {
                comparesValuesForSortDesc(setValue, anotherOneComparableValue);
            }
        }
    }

    private void comparesValuesForSortDesc(int index, int anotherOneComparableValue) {
        LOGGER_INFO.info("Compares values for sort by descending");
        UserValues temporaryValueForCompared;
        int oneComparableValue;
        temporaryValueForCompared = numbers[anotherOneComparableValue];
        oneComparableValue = anotherOneComparableValue;
        while (oneComparableValue > index && (numbers[oneComparableValue - ONE_ELEMENT_IN_ARRAY].compareTo(temporaryValueForCompared)) <= ZERO) {
            numbers[oneComparableValue] = numbers[oneComparableValue - ONE_ELEMENT_IN_ARRAY];
            --oneComparableValue;
        }
        numbers[oneComparableValue] = temporaryValueForCompared;
    }

    public Double getAverage() {
        LOGGER_INFO.info("Getting average");
        double sumOfDigits = DEFAULT_SUM_NUMBERS;
        double numberOfDigits = DEFAULT_AMOUNT_NUMBERS;
        for (int mathSetValues = FIRST_ELEMENT_IN_ARRAY; mathSetValues < sizeMathSet; mathSetValues++) {
            sumOfDigits += numbers[mathSetValues].intValue();
            numberOfDigits++;
        }
        return sumOfDigits / numberOfDigits;
    }

    public Integer getMedian() {
        LOGGER_INFO.info("Ascending sort set");
        sortAsc();
        LOGGER_INFO.info("Getting median");
        if (sizeMathSet % HALVING == NO_REMAINDER) {
            return ((Integer) numbers[(sizeMathSet / HALVING) - ONE_ELEMENT_IN_ARRAY] + (Integer) numbers[sizeMathSet / HALVING]) / HALVING;
        } else {
            return (Integer) numbers[sizeMathSet / HALVING];
        }
    }

    @SuppressWarnings("unchecked")
    public Number[] toArray() {
        LOGGER_INFO.info("To  array");
        UserValues[] numbersArray = (UserValues[]) new Number[sizeMathSet];
        System.arraycopy(numbers, STARTING_POSITION_SOURCE_ARRAY, numbersArray, STARTING_POSITION_DESTINATION_DATA, sizeMathSet);
        return numbersArray;
    }

    @SuppressWarnings("unchecked")
    public Number[] toArray(int firstIndex, int lastIndex) {
        LOGGER_INFO.info("To array between two indexes");
        UserValues[] numbersArray = (UserValues[]) new Number[FIRST_ELEMENT_IN_ARRAY];
        if (firstIndex >= sizeMathSet || lastIndex >= sizeMathSet || firstIndex < ZERO || lastIndex < ZERO || firstIndex > lastIndex) {
            return numbersArray;
        } else {
            numbersArray = (UserValues[]) new Number[lastIndex - firstIndex];
            for (int value = firstIndex, element = FIRST_INDEX_ELEMENT; value < lastIndex; value++, element++) {
                numbersArray[element] = numbers[value];
            }
        }
        return numbersArray;
    }

    @SuppressWarnings("unchecked")
    public MathSet cut(int firstIndex, int lastIndex) {
        LOGGER_WARN.warn("Cutting from set");
        MathSet clippedMathSet = new MathSet();
        if (firstIndex >= sizeMathSet || lastIndex >= sizeMathSet || firstIndex < ZERO || lastIndex < ZERO || firstIndex > lastIndex) {
            return clippedMathSet;
        } else {
            clippedMathSet = new MathSet(capacity);
            for (int value = firstIndex; value <= lastIndex; value++) {
                clippedMathSet.add(numbers[value]);
            }
        }
        return clippedMathSet;
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        LOGGER_INFO.info("Clear full set");
        capacity = CAPACITY_SIZE_MATH_SET_EQUALS_TEN;
        numbers = (UserValues[]) new Number[capacity];
        sizeMathSet = EMPTY_MATH_SET;
    }

    public void clear(UserValues[] number) {
        LOGGER_INFO.info("Clear numbers from set");
        for (int userInputValue = FIRST_INDEX_ELEMENT; userInputValue < sizeMathSet; userInputValue++) {
            for (UserValues value : number) {
                if (numbers[userInputValue].equals(value)) {
                    deleteValue(numbers[userInputValue]);
                }
            }
        }
    }

    public void deleteValue(UserValues value) {
        LOGGER_WARN.warn("Delete value");
        int userValue;
        for (userValue = FIRST_INDEX_ELEMENT; userValue < sizeMathSet; userValue++) {
            if (numbers[userValue].equals(value)) {
                break;
            }
        }
        if (sizeMathSet - userValue >= ZERO)
            System.arraycopy(numbers, userValue + ONE_POSITION_SOURCE_ARRAY, numbers, userValue, sizeMathSet - userValue);
        sizeMathSet--;
    }

    public void printsSetValues() {
        for (int userInputValue = FIRST_INDEX_ELEMENT; userInputValue < sizeMathSet; userInputValue++) {
            System.out.print(numbers[userInputValue] + SPACE);
        }
        System.out.println();
    }

    public boolean checksForTheExistenceOfValue() {
        LOGGER_INFO.info("Checking existence of value ");
        return sizeMathSet == EMPTY_MATH_SET;
    }

    public int checksSetIncludesValue(UserValues value) {
        LOGGER_INFO.info("Checking includes value");
        for (int userValue = FIRST_INDEX_ELEMENT; userValue < sizeMathSet; userValue++) {
            if (numbers[userValue].equals(value)) {
                return userValue;
            }
        }
        return SET_DOESNT_INCLUDE_VALUE;
    }
}
