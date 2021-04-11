package com.test.mather;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import org.saferegex.RegexTester;

import java.util.concurrent.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Main {
    private static boolean validRegex(String regex) {
        if (regex == null) {
            return false;
        }
        try {
            Pattern.compile(regex);
            return true;
        }
        catch (PatternSyntaxException e){
            return false;
        }
    }

    public static boolean matches(String text, String regex) {
        if (text == null || !validRegex(regex)) {
            return false;
        }

        try {
            if (RegexTester.isVulnerable(regex))
                return false; // can be reported
        } catch (IllegalStateException e) {
            // means that result is unknown, so we should try to run matcher
        } catch (StackOverflowError e) {
            return false;
        }

        ExecutorService es = Executors.newSingleThreadExecutor();
        TimeLimiter limiter = SimpleTimeLimiter.create(es);
        try {
            return limiter.callWithTimeout(() -> Pattern.compile(regex).matcher(text).matches(), 1, TimeUnit.SECONDS);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            return false;
        }
    }
}
