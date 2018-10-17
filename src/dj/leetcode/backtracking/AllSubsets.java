package dj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllSubsets {
    List<List<Character>> powerset;
    Character[]           data;
    List<Character>       tempSet;

    private void init(Character[] data) {
        this.powerset = new ArrayList<>();
        this.data = data;
        this.tempSet = new ArrayList<>();
    }

    public List<List<Character>> subsets(Character[] data) {
        init(data);
        backtrack(0);
        return powerset;
    }

    private void backtrack(int candidateIndex) {
        powerset.add(new ArrayList<>(tempSet));
        for (int i = candidateIndex; i < data.length; i++) {
            Character candidate = data[i];
            tempSet.add(candidate);
            backtrack(i + 1);
            tempSet.remove(candidate);
        }
    }


    public static void main(String[] args) {
        AllSubsets allSubsets = new AllSubsets();
        List<List<Character>> subsets = allSubsets.subsets(new Character[]{'A', 'B', 'C'});

        subsets.stream()
               .forEach(set -> {
                   String setString = set.stream()
                                         .map(String::valueOf)
                                         .collect(Collectors.joining(","));
                   System.out.println(setString);
               });
    }

}