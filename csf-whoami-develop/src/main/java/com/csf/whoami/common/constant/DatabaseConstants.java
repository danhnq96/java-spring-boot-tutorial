/**
 *
 */
package com.csf.whoami.common.constant;

/**
 * @author mba0051
 *
 */
public class DatabaseConstants {

    public static final String SORT_ASC = "ASC";
    public static final String SORT_DESC = "DESC";

    /**
     * Constant for User roles.
     *
     * @author tuan
     *
     */
    public enum UserRolesConstant {

        USER("USER"), ADMIN("ADMIN"), EDITOR("EDITOR");

        String value;

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        UserRolesConstant(String value) {
            this.value = value;
        }

        ;
    }

    /**
     * Question type mapping with constants.
     *
     * @author mba0051
     */
    public enum QuestionTypeConstant {

        SELECT_TYPE(CommonConstants.SelectOption), PICTURE_CHOICE(CommonConstants.PictureChoiceType),
        SINGLE_TEXT(CommonConstants.TextType), MULTILINE_TEXT(CommonConstants.MultiLineTextType),
        YES_NO(CommonConstants.YesNoType), UPLOAD(CommonConstants.UploadType);

        String value;

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        QuestionTypeConstant(String value) {
            this.value = value;
        }

        ;
    }

    /**
     * Group conditions required.
     *
     * @author mba0051
     *
     */
    public enum GroupRequireTypeConstant {

        Direct("Invite direct"), WaitAccept("Waiting accept"), RequireAuthen("pass to quiz test"),
        RequireExp("Keep time in group");

        String value;

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        GroupRequireTypeConstant(String value) {
            this.value = value;
        }

        ;
    }

    /**
     * Notification status.
     *
     * @author mba0051
     *
     */
    public enum NotifyStatusConstant {

        NEW("1"), READED("2"), ACCEPT("3"), DENY("0"), OVER_EXPIRE("4");

        String value;

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        NotifyStatusConstant(String value) {
            this.value = value;
        }

        ;
    }

    /**
     * Notifications type.
     *
     * @author mba0051
     *
     */
    public enum NotificationsTypeConstant {

        GROUP("1"), QUIZ("2"), CHANNEL("3"), KICK_OUT("4"), REPORT("5");

        String value;

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        NotificationsTypeConstant(String value) {
            this.value = value;
        }

        ;
    }

    /**
     * Constant for YES NO consider.
     *
     * @author tuan
     *
     */
    public enum YesNoConstant {

        YES("1"), NO("0");
        String value;

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        YesNoConstant(String value) {
            this.value = value;
        }

        ;
    }

    /**
     * The enum Is deleted.
     */
    public enum IsDeleted {

        /**
         * True is deleted.
         */
        TRUE(1),

        /**
         * False is deleted.
         */
        FALSE(0);

        /**
         * The Value.
         */
        Integer value;

        /**
         * Gets value.
         *
         * @return the value
         */
        public Integer getValue() {
            return value;
        }

        /**
         * Sets value.
         *
         * @param value the value to set
         */
        public void setValue(Integer value) {
            this.value = value;
        }

        IsDeleted(Integer value) {
            this.value = value;
        }
    }

    public enum RoleName {

        CMS_OBSERVER("CMS_OBSERVER", "Observer"), CMS_OPERATOR("CMS_OPERATOR", "Operator"),
        SYS_ADMIN("SYS_ADMIN", "Admin"), CMS_OWNER("CMS_OWNER", "Owner");

        private String code;
        private String name;

        RoleName(String code, String name) {
            this.code = code;
            this.name = name;
        }

        /**
         * Gets code.
         *
         * @return the code
         */
        public String getCode() {
            return code;
        }

        /**
         * Sets code.
         *
         * @param code the code
         */
        public void setCode(String code) {
            this.code = code;
        }

        /**
         * Gets name.
         *
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * Sets name.
         *
         * @param name the name
         */
        public void setName(String name) {
            this.name = name;
        }
    }
}
