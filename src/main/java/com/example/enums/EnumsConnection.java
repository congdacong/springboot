package com.example.enums;

/**
 * @Author:congguangbo
 * @Date:2020/3/7 17:56
 */
public class EnumsConnection {
    public static class BusinessType{
        public enum State {
            INSERT(0,"新增"),
            DELETE(1,"删除"),
            UPDATE(2,"修改"),
            EXPORT(3,"导出"),
            SELECT(4,"查询"),
            LOGON(5,"登录"),
            OTHER(6,"其他");

            private Integer text;
            private String displayName;

            State(Integer text, String displayName) {
                this.text = text;
                this.displayName = displayName;
            }
        }
    }
    public static class BusinessStatus{
        public enum State {
            SUCCESS(0,"成功"),
            FAIL(1,"失败");

            private Integer text;
            private String displayName;

            State(Integer text, String displayName) {
                this.text = text;
                this.displayName = displayName;
            }
        }
    }
}
