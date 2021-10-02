package ua.com.alevel.util;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class MathSetTest {

    MathSet<Integer> uniqueValues;

    @Test
    public void TestMathSetSortingDescendingShouldReturnSortingMathSetDescending() {
        uniqueValues = new MathSet<>(new Integer[]{2, 5, 8, 7}, new Integer[]{1, 5, 9});
        uniqueValues.sortDesc();
        Assertions.assertEquals(9, uniqueValues.get(0));
    }

    @Test
    public void TestMathSetSortingAscendingShouldReturnSortingMathSetAscending() {
        uniqueValues = new MathSet<>(new Integer[]{2, 5, 8, 7}, new Integer[]{1, 5, 9});
        uniqueValues.sortAsc();
        Assertions.assertEquals(1, uniqueValues.get(0));
    }

    @Test
    public void TestMathSetSortingDescendingByValueShouldReturnSortingMathSetDescendingByValue() {
        uniqueValues = new MathSet<>(new Integer[]{2, 5, 8, 7}, new Integer[]{1, 5, 9});
        uniqueValues.sortDesc(2);
        Assertions.assertEquals(1, uniqueValues.get(uniqueValues.getSizeMathSet() - 1));
    }

    @Test
    public void TestGettingAverageValueInMathSetShouldReturnAverageValueInMathSet() {
        uniqueValues = new MathSet<>(new Integer[]{2, 24, 15, 7}, new Integer[]{1, 4, 9, 10});
        Assertions.assertEquals(9, uniqueValues.getAverage());
    }

    @Test
    public void TestGettingMedianValueInMathSetShouldReturnMedianValueInMathSetI() {
        uniqueValues = new MathSet<>(new Integer[]{2, 24, 15, 7}, new Integer[]{1, 4, 9, 10});
        Assertions.assertEquals(8, uniqueValues.getMedian());
    }

    @Test
    public void TestGetMaxValueFromMathSetShouldReturnMaxValueFromMathSet() {
        uniqueValues = new MathSet<>(new Integer[]{2, 24, 15, 7}, new Integer[]{1, 4, 9, 10});
        Assertions.assertEquals(24, uniqueValues.getMax());
    }

    @Test
    public void TestGetMinValueFromMathSetShouldReturnMinValueFromMathSet() {
        uniqueValues = new MathSet<>(new Integer[]{2, 24, 15, 7}, new Integer[]{1, 4, 9, 10});
        Assertions.assertEquals(1, uniqueValues.getMin());
    }

    @Test
    public void TestAddingValueToMathSetShouldReturnMathSetWithAddingValue() {
        uniqueValues = new MathSet<>(new Integer[]{2, 24, 15, 7}, new Integer[]{1, 4, 9, 10});
        MathSet<Integer> newValuesInMathSet = new MathSet<>(uniqueValues);
        newValuesInMathSet.add(121);
        newValuesInMathSet.add(11);
        MathSet<Integer> newMathSet = new MathSet<>(uniqueValues, newValuesInMathSet);
        Assertions.assertEquals(newValuesInMathSet.getSizeMathSet(), newMathSet.getSizeMathSet());
    }

    @Test
    public void TestAddingConsistsValueToMathSetShouldReturnInitialMathSet() {
        uniqueValues = new MathSet<>(new Integer[]{2, 24, 15, 7}, new Integer[]{1, 4, 9, 10});
        MathSet<Integer> newValuesInMathSet = new MathSet<>(uniqueValues);
        newValuesInMathSet.add(2);
        Assertions.assertEquals(newValuesInMathSet.getSizeMathSet(), uniqueValues.getSizeMathSet());
    }

    @Test
    public void TestJoinInitialMathSetWithNewInputMathSetShouldReturnJoinNewMathSet() {
        uniqueValues = new MathSet<>(new Integer[]{2, 24, 15, 7}, new Integer[]{1, 4, 9, 10});
        MathSet<Integer> newMathSet = new MathSet<>(new Integer[]{555, 222, 999, 777, 123});
        uniqueValues.join(newMathSet);
        Assertions.assertEquals(13, uniqueValues.getSizeMathSet());
    }

    @Test
    public void TestJoinInitialMathSetWithNewInputMathSetWithOneConsistsValueShouldReturnJoinNewMathSetWithoutExistingValue() {
        uniqueValues = new MathSet<>(new Integer[]{2, 24, 15, 7}, new Integer[]{1, 4, 9, 10});
        MathSet<Integer> newMathSet = new MathSet<>(new Integer[]{555, 2, 999, 777, 123});
        uniqueValues.join(newMathSet);
        Assertions.assertEquals(12, uniqueValues.getSizeMathSet());
    }
}
