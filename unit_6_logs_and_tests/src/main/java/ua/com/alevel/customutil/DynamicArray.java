package ua.com.alevel.customutil;

public class DynamicArray<T> {

    private final int INIT_SIZE = 1;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;
    private static final int SOURCE_POSITION = 0;
    private static final int DESTINATION_POSITION = 0;
    private static final int ONE_INDEX = 1;
    private static final int DOUBLE_INCREASE = 2;

    public void addAdditionalPoint(T item) {
        if (pointer == array.length - ONE_INDEX)
            resizeInitialArray(array.length * DOUBLE_INCREASE);
        array[pointer++] = item;
    }

    public T get(int index) {
        return (T) array[index];
    }

    public boolean isEmpty() {
        return pointer == DESTINATION_POSITION;
    }

    public int getSize() {
        return pointer;
    }

    private void resizeInitialArray(int newLengthOfArray) {
        Object[] newArrayOfObjects = new Object[newLengthOfArray];
        System.arraycopy(array, SOURCE_POSITION, newArrayOfObjects, DESTINATION_POSITION, pointer);
        array = newArrayOfObjects;
    }
}
