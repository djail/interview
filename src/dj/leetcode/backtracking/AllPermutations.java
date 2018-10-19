package dj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AllPermutations {
    public void permutations(List<Character> data) {
        backtrack(data, 0);
    }

    private void backtrack(List<Character> inputList, int startIndex) {
        if (startIndex == inputList.size() - 1) {
            String stringValue = inputList.stream()
                                          .map(String::valueOf)
                                          .collect(Collectors.joining(","));

            System.out.println(stringValue);
        }
        else {
            IntStream.range(startIndex, inputList.size())
                     .mapToObj(i -> swapElements(inputList, startIndex, i))
                     .forEach(permutedList -> backtrack(permutedList, startIndex + 1));
        }
    }

    private List<Character> swapElements(List<Character> list, Integer leftCursor, Integer rightCursor) {
        List<Character> newList = new ArrayList<>(list);
        newList.set(leftCursor, list.get(rightCursor));
        newList.set(rightCursor, list.get(leftCursor));
        return newList;
    }


    public static void main(String[] args) {
        Character[] data = new Character[]{'A', 'B', 'C'};
        AllPermutations permutations = new AllPermutations();
        permutations.permutations(Arrays.asList(data));
    }

}