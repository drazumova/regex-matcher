# regex-matcher

Tiny project fixes the following matches method
```aidl
public boolean matches(String text, String regex) {
    return Pattern.compile(regex).matcher(text).matches();
}
```

In all incorrect input cases `false` is returned.

[SafeRegex](https://github.com/jkutner/saferegex) is used to validate the regular expression.
This slows down the solution significantly, but can be useful.