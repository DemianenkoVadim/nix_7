package ua.com.alevel.util;

import static ua.com.alevel.util.Constant.*;

public class DynamicArray {

    public static int[] changeSizeOfArray(int[] someElements) {
        int sizeOfArray = someElements.length * DOUBLE_INCREASE + ONE_MORE_SIZE_IN_ARRAY;
        int[] newArray = new int[sizeOfArray];
        System.arraycopy(someElements, FIRST_INDEX, newArray, FIRST_INDEX, someElements.length);
        return newArray;
    }

    public static int getNextIndexOfArray(int[] someElements) {
        int indexInArray = someElements.length;
        for (int element = FIRST_INDEX; element < someElements.length; element++) {
            if (someElements[element] == FIRST_INDEX) return element;
        }
        return indexInArray;
    }
}
