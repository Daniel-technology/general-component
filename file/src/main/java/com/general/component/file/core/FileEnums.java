package com.general.component.file.core;

/**
 * 文件枚举
 *
 * @author littlesnail
 * @create 2023−03-11 9:43 PM
 */
public class FileEnums {

    public enum fileServiceType {

        /**
         * 服务类型
         */
        MINIO("minio");

        fileServiceType(String type) {
            this.type = type;
        }

        public String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}
