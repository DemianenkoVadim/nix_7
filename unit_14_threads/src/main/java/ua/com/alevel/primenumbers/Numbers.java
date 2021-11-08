package ua.com.alevel.primenumbers;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Numbers {

    public static final int ZERO = 0;
    public static final int FIRST_LIST_VALUE = 0;
    public static final int ONE = 1;
    public static final int DELIMITER = 2;
    public static final int FIRST_PRIME_NUMBER = 2;
    public static final int SET_SIZE_REQUIRED = 10;
    public static final int RANGE = 100;

    public static List<Integer> createListOfNumbers() {
        Random random = new Random();
        List<Integer> randomNumbers = new ArrayList<>();
        while (randomNumbers.size() < SET_SIZE_REQUIRED)
            randomNumbers.add(random.nextInt(RANGE));
        assert randomNumbers.size() == SET_SIZE_REQUIRED;
        return randomNumbers;
    }

    @SneakyThrows
    public static long countPrimeNumbers(List<Integer> randomNumbers) {
        long primeNumbersResult = 0;
        List<Integer> firsPartGeneratedNumbers = randomNumbers.subList(FIRST_LIST_VALUE, randomNumbers.size() / DELIMITER);
        List<Integer> secondPartGeneratedNumbers = randomNumbers.subList(firsPartGeneratedNumbers.size(), randomNumbers.size());
        List<PrimeNumbers> numbers = new ArrayList<>();
        for (List<Integer> integers : List.of(firsPartGeneratedNumbers, secondPartGeneratedNumbers)) {
            PrimeNumbers primeNumbers = new PrimeNumbers(integers);
            primeNumbers.start();
            numbers.add(primeNumbers);
        }
        for (PrimeNumbers primeNumbers : numbers) {
            primeNumbers.join();
            primeNumbersResult += primeNumbers.getCounter();
        }
        return primeNumbersResult;
    }

    public static boolean isPrime(Integer value) {
        int wholeNumber;
        int flag = 0;
        wholeNumber = value / DELIMITER;
        if (value.equals(ZERO) || value.equals(ONE)) {
            return false;
        } else {
            for (int delimiter = FIRST_PRIME_NUMBER; delimiter <= wholeNumber; delimiter++) {
                if (value % delimiter == ZERO) {
                    flag = ONE;
                    break;
                }
            }
            return flag == ZERO;
        }
    }

    public static void printCountResultPrimeNumber() {
        System.out.println("Number of prime number(-s) in random generated list is " + countPrimeNumbers(createListOfNumbers()));
    }

    public static void printsRandomGeneratedNumbers() {
        System.out.println("Random generated list of numbers: " + createListOfNumbers());
    }
}
