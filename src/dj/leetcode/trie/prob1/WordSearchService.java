package dj.leetcode.trie.prob1;

// https://www.geeksforgeeks.org/trie-insert-and-search/
// Design a data structure that supports the following two operations:

// void addWord(word)
// bool search(word)
// search(word) can search a literal word or a regular expression string containing only letters a-z or .. A .
// means it can represent any one letter.
// For example:

// addWord("bad")
// addWord("dad")
// addWord("mad")
// search("pad") -> false
// search("bad") -> true
// search(".ad") -> true
// search("b..") -> true
// Note:
// You may assume that all words are consist of lowercase letters a-z.
//

import sun.text.normalizer.Trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class WordSearchService {
    TrieNode root;

    WordSearchService() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        addWordHelper2(word);
    }


    public List<String> findWord(String key) {
        return findMatchingTrieNode(key, 0, root)
                .map(trie -> findAllPaths(key, trie))
                .orElseGet(() -> new ArrayList());
    }

    public List<String> findAllPaths(String key, TrieNode current) {
        List<String> words = new ArrayList<>();
        if (current.isWord) {
            words.add(key);
        }

        List<String> wordsDownstream = current.children.entrySet().stream()
                                                       .map(v -> findAllPaths(key + v.getKey(), v.getValue()))
                                                       .flatMap(List::stream)
                                                       .collect(Collectors.toList());
        words.addAll(wordsDownstream);

        return words;
    }


    public Optional<TrieNode> findMatchingTrieNode(String key, Integer cur, TrieNode current) {
        if (cur == key.length()) {
            return Optional.of(current);
        }
        else {
            Character c = key.charAt(cur);
            return current.getChild(c)
                          .map(t -> findMatchingTrieNode(key, cur + 1, t))
                          .filter(Optional::isPresent)
                          .map(Optional::get);
        }
    }

    private void addWordHelper2(final String word) {
        TrieNode start = root;
        for (Integer i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            start.putIfAbsent(c, new TrieNode());
            start = start.getChild(c).get();
        }
        start.setIsWord(true);
    }

    public static void main(String[] args) {
        WordSearchService s = new WordSearchService();
        s.addWord("pub");
        s.addWord("pus");
        s.addWord("pusju");
        s.addWord("subbu");

        s.findWord("pu").stream()
         .forEach(w -> System.out.println(w));
    }
}


class TrieNode {
    Boolean                  isWord;
    Map<Character, TrieNode> children;

    public TrieNode() {
        this(false);
    }

    public TrieNode(Boolean isWord) {
        this.isWord = isWord;
        children = new ConcurrentHashMap<>();
    }

    public TrieNode putIfAbsent(Character child, TrieNode trieNode) {
        return children.putIfAbsent(child, trieNode);
    }

    public void setIsWord(Boolean isWord) {
        this.isWord = isWord;
    }

    public Optional<TrieNode> getChild(Character child) {
        return Optional.ofNullable(children.get(child));
    }
}