package ru.vdudvdud.testdata.utils;


import ru.vdudvdud.adaptors.selenide.utils.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {
    private static final Logger logger = Logger.getInstance();

    private RegexMatcher() {
        throw new IllegalStateException("Regex matcher class. All methods are static, don't create instance of this class");
    }

    private static Matcher getMatcher(String pat, String text) {
        Pattern pattern = Pattern.compile(pat);
        return pattern.matcher(text);
    }

    public static Matcher getRegexMatcher(String pat, String text) {
        Matcher matcher = getMatcher(pat, text);
        matcher.find();
        return matcher;
    }

    public static List<String> getStringList(String pat, String text) {
        Matcher matcher = getMatcher(pat, text);
        List<String> listMatches = new ArrayList<>();
        while (matcher.find()) {
            listMatches.add(matcher.group());
            logger.info(String.format("Matcher : %s", matcher.group()));
        }
        return listMatches;
    }

    public static boolean isTextInteger(String text) {
        return RegexMatcher.regexIsMatch(text, "[-+]?\\d+");
    }

    public static String regexGetMatch(String text, String regex) {
        return regexGetMatchGroup(text, regex, 0, false);
    }

    public static String regexGetMatchGroup(String text, String regex, int groupIndex) {
        return regexGetMatchGroup(text, regex, groupIndex, false);
    }

    public static String regexGetMatchGroup(String text, String regex, int groupIndex, boolean matchCase) {
        Pattern p = regexGetPattern(regex, matchCase);
        Matcher m = p.matcher(text);
        List<String> results = new ArrayList<>();
        while (m.find()) {
            results.add(m.group());
        }
        if (results.size() > groupIndex) {
            return results.get(groupIndex);
        } else {
            return null;
        }
    }

    private static Pattern regexGetPattern(String regex, boolean matchCase) {
        int flags = matchCase ? 0 : Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;
        return Pattern.compile(regex, flags);
    }

    public static boolean regexIsMatch(String text, String regex) {
        return regexIsMatch(text, regex, false);
    }

    private static boolean regexIsMatch(String text, String regex, boolean matchCase) {
        Pattern p = regexGetPattern(regex, matchCase);
        Matcher m = p.matcher(text);
        return m.find();
    }

    /**
     * Получение первой группы из совпадения по регулярному выражению.
     *
     * @param text  исходный текст.
     * @param regex регулярное выражение.
     * @return первое совпадение.
     */
    public static String regexGetFirstMatchGroup(String text, String regex) {
        return regexGetMatchGroup(text, regex, 0, false);
    }
}
