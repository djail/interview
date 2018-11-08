package dj.leetcode.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordLadder {
    private Set<String> words;
    private Set<String> visited = new HashSet<>();
    private List<String> currentPath = new ArrayList<>();

    public WordLadder(Set<String> words) {
        this.words = words;
    }

    private void ladderInternal(String start, String end) {
        if (start.equals(end)) {
            printCurrentPath();
        }
        else {
            List<String> candidates = generateCandidates(start);
            candidates.stream()
                      .filter(candidate -> !visited.contains(candidate))
                      .forEach(candidate -> {
                          currentPath.add(candidate);
                          ladderInternal(candidate, end);
                      });
            currentPath.remove(start);
        }
    }

    private List<String> generateCandidates(String string) {
        return IntStream.range(0, string.length())
                        .mapToObj(i -> generateCandidates(string, i))
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
    }

    private List<String> generateCandidates(String string, Integer index) {
        Function<Character, String> generateNextString = letter -> replaceCharacter(string, index, letter);
        return IntStream.range('a', 'z')
                        .mapToObj(c -> Character.valueOf((char) c))
                        .map(generateNextString)
                        .filter(candidate -> !candidate.equals(string))
                        .collect(Collectors.toList());
    }

    private String replaceCharacter(String string, Integer index, Character letter) {
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.setCharAt(index, letter);
        return stringBuilder.toString();
    }

    private void printCurrentPath() {
        String pathString = currentPath.stream()
                                       .collect(Collectors.joining(" -> "));

        System.out.println(String.format("word path: \n %s", pathString));
    }
}
