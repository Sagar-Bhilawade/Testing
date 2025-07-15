Java Regex Cheat Sheet / Notes
Regular expressions (regex) are a powerful tool for pattern matching and manipulation in strings. Java's java.util.regex package provides robust support for using them. This cheat sheet covers the most commonly used regex patterns, characters, and concepts in Java, along with illustrative examples.

1. Core Regex Classes in Java
The java.util.regex package provides three key classes:

Pattern:

A compiled representation of a regular expression.

It's immutable and thread-safe.

Usage: Use Pattern.compile(regex) to create a Pattern object. It's best practice to compile a pattern once and reuse it for multiple matching operations for performance.

Matcher:

An engine that performs match operations on a character sequence by interpreting a Pattern.

It's mutable and not thread-safe.

Usage: Use pattern.matcher(inputString) to create a Matcher object. A new Matcher object should be created for each new input string.

PatternSyntaxException:

An unchecked exception thrown to indicate a syntax error in a regular-expression pattern.

2. Basic Matching (Literals and Wildcards)
Character(s)

Description

Example Regex

Matches

a

Matches the literal character a.

cat

"cat"

.

Matches any single character (except line terminators like \n by default). Use Pattern.DOTALL flag to include line terminators.

c.t

"cat", "cut", "c@t"

3. Character Classes (Bracket Expressions [])
Match any one of the characters or ranges defined inside the brackets.

Pattern

Description

Example Regex

Matches

[abc]

Matches any single character: a, b, or c.

[aeiou]

Any lowercase vowel

[^abc]

Matches any single character except a, b, or c.

[^0-9]

Any single non-digit character

[a-z]

Matches any lowercase letter from a through z.

[A-Z]

Any uppercase letter

[A-Za-z]

Matches any single letter (case-sensitive).

[0-9]

Any single digit

[a-zA-Z0-9]

Matches any single alphanumeric character.





[a-z&&[^bc]]

Intersection: Matches a through z but not b and c. Effectively matches a, and then d through z.





4. Predefined Character Classes (Shorthand Escapes)
These are convenient shorthand notations for common character sets.

Shorthand

Description

Equivalent Character Class

Example Regex

Matches

\d

A digit.

[0-9]

\d{3}

"123", "789"

\D

A non-digit.

[^0-9]

\D

"a", "#", " "

\s

A whitespace character. Includes space, tab (\t), newline (\n), carriage return (\r), form feed (\f), and vertical tab (\x0B).

[\t\n\x0B\f\r]

hello\sworld

"hello world"

\S

A non-whitespace character.

[^\s]

\S+

Any sequence of non-space characters

\w

A "word" character. Includes letters (a-z, A-Z), digits (0-9), and underscore (_).

[a-zA-Z_0-9]

\w+

"word", "java_1", "count3"

\W

A non-word character.

[^\w]

\W

"$", "@", " "

5. Quantifiers (Repetition)
Specify how many times a preceding character, group, or character class must appear.

Quantifier

Description

Type

Example Regex

Matches

*

Zero or more times.

Greedy

a*

"", "a", "aa", "aaa"

+

One or more times.

Greedy

a+

"a", "aa", "aaa" (but not "")

?

Zero or one time (optional).

Greedy

colou?r

"color", "colour"

{n}

Exactly n times.

Greedy

\d{3}

"123" (not "12" or "1234")

{n,}

At least n times.

Greedy

a{2,}

"aa", "aaa", "aaaa"

{n,m}

At least n times, but no more than m times.

Greedy

\d{3,5}

"123", "1234", "12345"

Understanding Quantifier Types:

Greedy (Default): Tries to match the longest possible string that satisfies the pattern.

Example: Regex .*html on input "<html><head></html>" matches <html><head></html>.

Reluctant (Lazy) (? suffix): Tries to match the shortest possible string.

Example: Regex .*?html on input "<html><head></html>" matches <html>.

Possessive (+ suffix): Similar to greedy but never gives back characters once matched, even if it leads to a failure. Can sometimes improve performance by preventing backtracking, but use with caution as it can cause unexpected failures if not understood.

Example: Regex .*+X on input "abcXdefX" will match abcXdefX (the whole string if X is found at the end of the input). If X is not found at the end of the input it will fail completely even if X is present in the middle.

6. Boundary Matchers
Match positions within the string, not actual characters.

Pattern

Description

Example Regex

Input String

Matches at

^

Beginning of the line (or input if MULTILINE is not set).

^Hello

"Hello World\nHi"

Before 'H' in "Hello"

$

End of the line (or input if MULTILINE is not set).

World$

"Hello World\nHi"

After 'd' in "World" (when MULTILINE is enabled)

\b

Word boundary. Position between a word character (\w) and a non-word character (\W), or at the start/end of the string if it's a word character.

\bcat\b

"cat", "a cat."

Before 'c' and after 't' in "cat"

\B

Non-word boundary. A position that is not a word boundary.

\Bcat\B

"wildcat"

No match (e.g., cat is not at a boundary)

\A

Beginning of the entire input.

\Aabc

"abc\ndef"

Before 'a' (only at absolute start)

\Z

End of the entire input, ignoring final line terminator if present.

xyz\Z

"abc\nxyz\n"

Before the final \n (if present)

\z

End of the entire input.

xyz\z

"abc\nxyz"

After 'z' (absolute end)

7. Logical Operators & Grouping
Operator

Description

Example Regex

Matches

Purpose

XY

Concatenation: Pattern X followed by pattern Y.

abc

"abc"

Basic sequence matching

`X

Y`

Alternation: Matches X or Y.

`cat

dog`

(X)

Capturing Group: Groups X as a single unit and captures the matched text.

(ab)+

"ab", "abab", "ababab"

Allows applying quantifiers to multiple characters; captures substring for later retrieval.

(?:X)

Non-Capturing Group: Groups X as a single unit but does not capture the matched text.

(?:ab)+

"ab", "abab"

For grouping without the overhead of capturing.

(?=X)

Positive Lookahead: Matches X only if it's followed by Y, but Y is not included in the match.

Java(?=Script)

"JavaScript"

Matches "Java" only if "Script" follows, but "Script" isn't part of the match.

(?!X)

Negative Lookahead: Matches X only if it's not followed by Y.

Java(?!Script)

"Java Rocks"

Matches "Java" if "Script" does not follow.

(?<=X)

Positive Lookbehind: Matches X only if it's preceded by Y, but Y is not included in the match.

(?<=re)ad

"bread", "read"

Matches "ad" only if preceded by "re". Lookbehind patterns must have a fixed length in Java.

(?<!X)

Negative Lookbehind: Matches X only if it's not preceded by Y.

(?<!un)happy

"happy", "unhappy"

Matches "happy" if not preceded by "un".

8. Backreferences
Refer back to a previously captured group within the same regex.

Pattern

Description

Example Regex

Input String

Matches

\n

Refers to the text matched by the n-th capturing group (e.g., \1 for the first group, \2 for the second).

(\w+)\s\1

"word word"

"word word" (matches a word, then space, then the same word again)





([a-z])\1

"apple"

"pp"

9. Special Characters & Escaping
Many characters have special meaning in regex. To match them literally, you must escape them with a backslash \.

Special Char

Description

To Match Literally (in Regex)

In Java String Literal

.

Any char

\.

\\.

*

Zero or more

\*

\\*

+

One or more

\+

\\+

?

Zero or one

\?

\\?

`

`

OR

|

(

Group start

\(

\\(

)

Group end

\)

\\)

[

Character class start

\[

\\[

]

Character class end

\]

\\]

{

Quantifier start

\{

\\{

}

Quantifier end

\}

\\}

^

Start of line / Negation

\^

\\^

$

End of line

\$

\\$

\

Escape character

\\

\\\\

Crucial Java Note on Backslashes:
In Java String literals, the backslash \ itself is an escape character. Therefore, to represent a literal backslash in a regex, you must escape it twice within the Java String literal.

Regex: \d (matches a digit) -> Java String: "\\d"

Regex: \. (matches a literal dot) -> Java String: "\\."

Regex: \\ (matches a literal backslash) -> Java String: "\\\\"

10. Flags (Pattern Compile Flags)
These flags modify how the Pattern is matched. They are applied when compiling the Pattern using Pattern.compile(regex, flags).

Flag (Constant)

Shorthand

Description

Pattern.CASE_INSENSITIVE

(?i)

Ignores case during matching.

Pattern.MULTILINE

(?m)

^ and $ match the beginning/end of each line, not just the entire input.

Pattern.DOTALL

(?s)

The . (dot) matches any character, including line terminators.

Pattern.UNIX_LINES

(?d)

Only \n is recognized as a line terminator.

Pattern.COMMENTS

(?x)

Ignores whitespace and allows comments within the pattern (after #).

Pattern.UNICODE_CASE

(?u)

Used with CASE_INSENSITIVE for Unicode-aware case folding.

Pattern.CANON_EQ

(?c)

Enables canonical equivalence for Unicode characters (matches characters that are canonically equivalent).

You can combine flags using the bitwise OR operator |:
Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE)

11. Useful Matcher Methods
Once you have a Matcher object, these methods are used to perform matching operations and retrieve results:

boolean matches(): Attempts to match the entire input sequence against the pattern. Returns true only if the entire string matches.

boolean find(): Attempts to find the next subsequence of the input sequence that matches the pattern. Can be called repeatedly in a loop to find all matches.

boolean lookingAt(): Attempts to match the input sequence, starting at the beginning, against the pattern. It does not require the entire region to be matched (unlike matches()).

String group(): Returns the input subsequence matched by the previous match. (Equivalent to group(0)).

String group(int group): Returns the input subsequence captured by the given group during the previous match. Group 0 is the entire match, Group 1 is the first capturing group, and so on.

int groupCount(): Returns the number of capturing groups in this matcher's pattern.

int start(): Returns the start index of the previous match in the input string.

int end(): Returns the offset after the last character matched in the input string.

Matcher appendReplacement(StringBuffer sb, String replacement): Used for custom replacement logic. Appends the portion of the input string before the current match, followed by the replacement string, to the StringBuffer.

StringBuffer appendTail(StringBuffer sb): Appends the rest of the input sequence from the last match position to the end of the StringBuffer. Used after appendReplacement loop.

String replaceAll(String replacement): Replaces every subsequence of the input sequence that matches the pattern with the given replacement string.

String replaceFirst(String replacement): Replaces the first subsequence of the input sequence that matches the pattern with the given replacement string.

12. String Class Regex Methods (Convenience)
Java's String class provides several built-in methods that internally use the java.util.regex package for convenience. These are good for simple, one-off regex operations where you don't need to reuse the compiled Pattern or iterate through matches.

boolean matches(String regex): Checks if the entire string matches the given regex.

Internal equivalent: Pattern.compile(regex).matcher(this).matches()

String replaceAll(String regex, String replacement): Replaces every substring that matches the regex with the specified replacement string.

String replaceFirst(String regex, String replacement): Replaces only the first substring that matches the regex.

String[] split(String regex): Splits this string into an array of strings around matches of the given regular expression.

String[] split(String regex, int limit): Splits the string, but limits the number of times the pattern is applied, thus limiting the number of elements in the resulting array.

Example Usage in Java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaRegexCheatSheetExample {

    public static void main(String[] args) {
        // --- 1. Basic Matching and finding all occurrences ---
        String text1 = "My email is test@example.com and my phone is 123-456-7890.";
        // Regex for a simple email pattern: one or more word chars, @, one or more word chars, dot, one or more word chars
        // Note the double backslashes in Java String for regex escapes like \b and \w
        Pattern emailPattern = Pattern.compile("\\b\\w+@\\w+\\.\\w+\\b");
        Matcher emailMatcher = emailPattern.matcher(text1);

        System.out.println("--- Finding all emails ---");
        while (emailMatcher.find()) { // find() iterates through all matches
            System.out.println("Found email: " + emailMatcher.group()); // group() returns the entire matched substring
            System.out.println("  Starts at index: " + emailMatcher.start());
            System.out.println("  Ends at index: " + emailMatcher.end());
        }

        // --- 2. Using group(int) for captured groups ---
        String text2 = "Date: 2023-10-26, Time: 14:30";
        // Capture year, month, day, hour, minute using parentheses ()
        Pattern dateTimePattern = Pattern.compile("Date: (\\d{4})-(\\d{2})-(\\d{2}), Time: (\\d{2}):(\\d{2})");
        Matcher dateTimeMatcher = dateTimePattern.matcher(text2);

        System.out.println("\n--- Extracting date and time components ---");
        if (dateTimeMatcher.find()) { // find the first (and only) match
            System.out.println("Full match (Group 0): " + dateTimeMatcher.group(0));
            System.out.println("Year (Group 1): " + dateTimeMatcher.group(1));
            System.out.println("Month (Group 2): " + dateTimeMatcher.group(2));
            System.out.println("Day (Group 3): " + dateTimeMatcher.group(3));
            System.out.println("Hour (Group 4): " + dateTimeMatcher.group(4));
            System.out.println("Minute (Group 5): " + dateTimeMatcher.group(5));
        }

        // --- 3. Replacing text using String.replaceAll() ---
        String text3 = "Hello World! This is a test. World is great.";
        String replacedText = text3.replaceAll("World", "Java"); // Simple literal replacement
        System.out.println("\n--- Replacing text ---");
        System.out.println("Original: " + text3);
        System.out.println("Replaced (all): " + replacedText); // Output: Hello Java! This is a test. Java is great.

        // Using replaceFirst()
        String replacedFirst = text3.replaceFirst("World", "Java");
        System.out.println("Replaced (first): " + replacedFirst); // Output: Hello Java! This is a test. World is great.

        // --- 4. Splitting strings using String.split() ---
        String csvData = "apple,banana,orange,grape";
        String[] fruits = csvData.split(","); // Split by comma
        System.out.println("\n--- Splitting string by comma ---");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        String sentence = "This is a sentence. It has punctuation! And    extra spaces.";
        // Split by one or more whitespace, period, comma, or exclamation mark
        String[] words = sentence.split("[\\s.,!]+");
        System.out.println("\n--- Splitting string by various delimiters ---");
        for (String word : words) {
            if (!word.isEmpty()) { // split() can return empty strings for consecutive delimiters
                System.out.println(word);
            }
        }

        // --- 5. `matches()` vs `find()` vs `lookingAt()` ---
        Pattern pattern = Pattern.compile("abc"); // Pattern for "abc"
        String inputA = "abc";
        String inputB = "abcdef";
        String inputC = "xyzabc";

        System.out.println("\n--- matches() vs find() vs lookingAt() ---");
        System.out.println("Input: \"" + inputA + "\"");
        System.out.println("  matches(): " + pattern.matcher(inputA).matches()); // true (entire string matches)
        System.out.println("  find(): " + pattern.matcher(inputA).find());     // true (finds a match anywhere)
        System.out.println("  lookingAt(): " + pattern.matcher(inputA).lookingAt()); // true (matches at the beginning)

        System.out.println("Input: \"" + inputB + "\"");
        System.out.println("  matches(): " + pattern.matcher(inputB).matches()); // false (entire string is not "abc")
        System.out.println("  find(): " + pattern.matcher(inputB).find());     // true (finds "abc" at the start)
        System.out.println("  lookingAt(): " + pattern.matcher(inputB).lookingAt()); // true (matches "abc" at the beginning)

        System.out.println("Input: \"" + inputC + "\"");
        System.out.println("  matches(): " + pattern.matcher(inputC).matches()); // false
        System.out.println("  find(): " + pattern.matcher(inputC).find());     // true (finds "abc" later in string)
        System.out.println("  lookingAt(): " + pattern.matcher(inputC).lookingAt()); // false (does not match at the beginning)

        // --- 6. Append replacement example for complex replacements ---
        String text4 = "User: John Doe, ID: 123. User: Jane Smith, ID: 456.";
        // Capture name and ID
        Pattern userPattern = Pattern.compile("User: (\\w+ \\w+), ID: (\\d+)");
        Matcher userMatcher = userPattern.matcher(text4);
        StringBuffer sb = new StringBuffer(); // Use StringBuffer for appendReplacement/appendTail

        System.out.println("\n--- Append Replacement Example ---");
        while (userMatcher.find()) {
            String name = userMatcher.group(1); // Captured name
            String id = userMatcher.group(2);   // Captured ID
            // Custom replacement string using captured groups
            String replacement = "Person: " + name.toUpperCase() + " (Ref ID: " + id + ")";
            userMatcher.appendReplacement(sb, replacement); // Appends text before match + replacement
        }
        userMatcher.appendTail(sb); // Appends any remaining text after the last match
        System.out.println("Transformed String: " + sb.toString());
        // Expected Output: Person: JOHN DOE (Ref ID: 123). Person: JANE SMITH (Ref ID: 456).
    }
}
