package dj.leetcode.regex;

public class WildcardMatcher {

    public boolean match(String text, String pattern){
        boolean results[][] = new boolean[text.length() + 1][pattern.length() + 1];
        results[0][0] = true;

        for(int i = 1; i < text.length(); i ++){
            for(int j = 1; j < pattern.length(); j ++){
                char patternChar = pattern.charAt(j -1);
                switch (patternChar){
                    case '?': {
                        results[i][j] = results[i - 1][j -1];
                        break;
                    }
                    case '*': {
                        results[i][j] = results[i - 1][j] || results[i][j - 1];
                        break;
                    }
                    default: {
                        results[i][j] = text.charAt(i - 1) == patternChar;
                        break;
                    }
                }
            }
        }
        return results[text.length() + 1][pattern.length() + 1];
    }
}
