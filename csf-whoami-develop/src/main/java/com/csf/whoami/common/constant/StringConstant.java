/**
 *
 */
package com.csf.whoami.common.constant;

/**
 * @author tuan
 *
 */
public class StringConstant {

    /** Character constant. */
    public static final String SPACE = " ";
    public static final String COMMA = ",";
    public static final String DOT = ".";
    public static final String BLANK = "";
    public static final String SEPARATOR = "|";
    public static final String COLON = ":";
    public static final String DASH = "_";
    public static final String T = "T";
    public static final String Z = "Z";
    public static final String APPEND_LINK = "/";

    /** Fullwidth space character. */
    public static final String FULL_SPACE = "　";
    public static final String NEW_LINE = "\n";
    public static final String DOUBLE_QUOTE = "\"";

    /** Pattern regex constant. */
    public static final String PASSWORD_PATTERN = "(?=.*\\d)(?=.*[A-Za-z])[\\w]{4,32}";
    public static final String POSTCODE_PATTERN = "^(\\s*$)|([0-9]{7}$)";
    public static final String NUMBER_PATTERN = "^[0-9]+$";
    public static final String NUMBER_PATTERN_OR_NULL = "^[0-9]*$";
    public static final String PHONE_PATTERN = "^(\\s|[0-9-])*$";
    public static final String BIT_PATTERN = "^(1|0)$";
    public static final String BIT_OR_NULL_PATTERN = "^(|1|0|\\s)$";
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&\\'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    public static final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]+$";
    public static final String FULL_WIDTH_PATTERN = "^[ァ-ン]+$";
    public static final String FULL_WIDTH_ALL_PATTERN = "^[ぁ-んァ-ン一-龥Ａ-ｚ０-９]+$";
    public static final String NUMBER__PATTERN = "^[0-9]+$";
    public static final String DATE_PATTERN = "^\\d{4}-(0\\d|10|11|12)-(0\\d|1\\d|2\\d|30|31)$";
    public static final String yyyyMMddNEW = "yyyy/MM/dd";

    public static final String SUBJECT_FORMAT = "%s;%s";

    /** Pattern for Fullwidth Katakana characters. */
    public static final String FULL_KANA_NAME_PATTERN = "^[ァ-ン]{0,30}$";

    /** Math constant. */
    public static final String PLUS = "+";
    public static final String MINUS = "-";
}
