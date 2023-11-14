package com.general.component.log.model;

import java.util.List;

/**
 * @description: 脱敏正则
 * @author: liuyan
 * @create: 2023−02-03 6:05 PM
 */
public class SensitiveRegex {
    /**
     * 是否忽略大小写匹配，默认为true
     */
    private boolean ignore = true;

    /**
     * 是否开启脱敏，默认为false
     */
    private boolean open = false;

    /**
     * 正则
     */
    private PatternReg patternReg;

    /**
     * 正则组
     */
    private List<PatternRegs> patternRegs;

    public List<PatternRegs> getPatternRegs() {
        return patternRegs;
    }

    public void setPatternRegs(List<PatternRegs> patternRegs) {
        this.patternRegs = patternRegs;
    }

    public boolean isIgnore() {
        return ignore;
    }

    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public PatternReg getPatternReg() {
        return patternReg;
    }

    public void setPatternReg(PatternReg patternReg) {
        this.patternReg = patternReg;
    }

    /**
     * 正则
     */
    public static class PatternReg {
        /**
         * 邮箱 - @前第4-7位脱敏
         */
        private String emailReg;

        /**
         * qq邮箱 - @后1-3位脱敏
         */
        private String qqEmailReg;

        /**
         * 姓名 - 姓脱敏，如*杰伦
         */
        private String nameReg;

        /**
         * 密码 - 所有需要完全脱敏的都可以使用内置的password
         */
        private String passwordReg;

        public String getEmailReg() {
            return emailReg;
        }

        public void setEmailReg(String emailReg) {
            this.emailReg = emailReg;
        }

        public String getQqEmailReg() {
            return qqEmailReg;
        }

        public void setQqEmailReg(String qqEmailReg) {
            this.qqEmailReg = qqEmailReg;
        }

        public String getNameReg() {
            return nameReg;
        }

        public void setNameReg(String nameReg) {
            this.nameReg = nameReg;
        }

        public String getPasswordReg() {
            return passwordReg;
        }

        public void setPasswordReg(String passwordReg) {
            this.passwordReg = passwordReg;
        }
    }

    /**
     * 规则组
     */
    public static class PatternRegs {
        /**
         * key的组合
         */
        private String key;

        /**
         * 每个key的规则
         */
        private List<CustomRegs> customRegs;
//        private String custom;

        /**
         * @return
         */
        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<CustomRegs> getCustomRegs() {
            return customRegs;
        }

        public void setCustomRegs(List<CustomRegs> customRegs) {
            this.customRegs = customRegs;
        }

    }


    /**
     * 定义规则的标识
     */
    public static class CustomRegs {
//        /**
//         * defaultRegex表示使用组件内置的规则
//         */
//        private String defaultRegex;

        /**
         * 正则
         */
        private String customRegex;

        /**
         * 脱敏位
         */
        private String position;

        public String getCustomRegex() {
            return customRegex;
        }

        public void setCustomRegex(String customRegex) {
            this.customRegex = customRegex;
        }

//        public String getDefaultRegex() {
//            return defaultRegex;
//        }
//
//        public void setDefaultRegex(String defaultRegex) {
//            this.defaultRegex = defaultRegex;
//        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }
    }

}
