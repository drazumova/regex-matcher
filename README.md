# regex-matcher

Tiny project fixes matches method
```aidl
public boolean matches(String text, String regex) {
    return Pattern.compile(regex).matcher(text).matches();
}
```

All incorrect input cases returns `false`.