package unicode;

import java.util.Arrays;

public class UnicodeDemo {
    /*
    java 的 char 采用 UTF-16.
    术语:
    字面量 (literal): constant value which can be assigned to the variable. i.e. notation of a char.
        e.g. 'A'
    码点 (code point): 与一个编码表中的某个字符对应的代码值.
        e.g. U+0041 => A.
    代码平面 (code plane): 码点的group.
        第一个代码平面: U+0000 - U+FFFF, 基本多语言平面(basic multilingual plane)
        其余16个代码平面: U+10000 - U+10FFFF.
        其余16个代码平面: U+10000 - U+10FFFF.
    代码单元 (code unit): 16位. 一个字符可以对应一个或两个码点.
     */
    final static String stringComplex = "A\uD86D\uDF74山后";

    public static void main(String[] args) {
        printStringInfo(stringComplex);
    }

    public static void printStringInfo(String str) {
        // print the string
        System.out.println("The String is:\t" + stringComplex);

        // string info
        System.out.println("str.length:\t"
                + str.length());
        System.out.println("str.codePointCount:\t"
                + str.codePointCount(0, str.length()));
        /* NOTE: codePointCount is different from length.
            The length return the code unit count.
            But the codePointCount is what **length** actually means for people.
         */
        char[] chars = str.toCharArray();

        // print string again according to code point type.
        // ignore this if it makes you confuse.
        System.out.print("the string:\t");
        for (int i = 0; i < chars.length; i++) {
            if (getCodeUnitType(chars[i]) == CodeUnitType.SINGLE_CODE_UNIT) {
                System.out.print(chars[i] + "\t\t");
            } else {
                System.out.print(String.valueOf(chars, i, 2) + "\t\t\t\t");
                i++;
            }
        }
        System.out.println();

        // print the chars i.e. code units.
        System.out.print("code unit:\t");
        for (char ch : chars) {
            System.out.printf("%04X\t", (int)ch);
        }
        System.out.println();

        // print the code unit type of the chars
        System.out.print("unit type:\t");
        for (char ch : chars) {
            System.out.print(getCodeUnitType(ch) + "\t");
        }
        System.out.println();

        // calculate the unicode code point:
        System.out.print("code point:\t");
        for (int i = 0; i < chars.length; i++) {
            if (getCodeUnitType(chars[i]) == CodeUnitType.SINGLE_CODE_UNIT) {
                System.out.printf("%05X\t", codeUnitToPoint(chars[i]));
            } else {
                System.out.printf("%05X\t\t\t", codeUnitToPoint(chars[i], chars[i + 1]));
                i++;
            }
        }
        System.out.println();

        // print by the codePoint
        System.out.print("codePoints\t");
        str.codePoints().forEach(x -> {
            System.out.printf("%05X\t", x);
            if (x > 0xFFFF) System.out.print("\t\t");
        });
    }

    enum CodeUnitType {
        HIGH_SURROGATE("high"), LOW_SURROGATE("low "), SINGLE_CODE_UNIT("sigle");
        private final String value;
        CodeUnitType(String value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return value;
        }
    }

    public static CodeUnitType getCodeUnitType(char ch) {
        return switch (ch >> 10) {
            case 0b110110 -> CodeUnitType.HIGH_SURROGATE;
            case 0b110111 -> CodeUnitType.LOW_SURROGATE;
            default -> CodeUnitType.SINGLE_CODE_UNIT;
        };
    }

    public static int codeUnitToPoint(char ch) {
        return ch;
    }

    public static int codeUnitToPoint(char high, char low) {
        // U = (C_H - D800_{16}) \times 400_{16} + (C_L - DC00_{16}) + 10000_{16}
        return (high - 0xD800) * 0x400 +
                (low - 0xDC00) +
                0x10000;
    }
}
