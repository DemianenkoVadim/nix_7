package ua.com.alevel.primenumbers;

import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class PrimeNumbers extends Thread implements Runnable {

    long counter = 0;
    List<Integer> numbers;

    public PrimeNumbers(List<Integer> counter) {
        this.numbers = counter;
    }

    public long getCounter() {
        return counter;
    }

    public void run() {
        this.counter = numbers.stream().filter(Numbers::isPrime).count();
    }
}
