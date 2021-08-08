package ua.com.alevel.brackets;

public class StackDataStructure {
    private final int[] stackDataStructureElements;
    private int headElementInStack;
    private static final int ONE_INDEX = -1;

    public StackDataStructure(int maximalDataStructure) {
        stackDataStructureElements = new int[maximalDataStructure];
        headElementInStack = ONE_INDEX;
    }

    public void addElementInHeadPosition(int element) {
        stackDataStructureElements[++headElementInStack] = element;
    }

    public int deleteElementFromHeadPosition() {
        return stackDataStructureElements[headElementInStack--];
    }

    public boolean isEmpty() {
        return (headElementInStack != ONE_INDEX);
    }
}
