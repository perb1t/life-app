package com.shijiwei.life.data.model;

/**
 * Created by shijiwei on 2020-03-03.
 *
 * @Desc:
 */
public class Holiday {


    /**
     * code : 0
     * type : {"type":0,"name":"周二","week":2}
     * holiday : null
     */

    private int code;
    private TypeBean type;
    private Object holiday;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public TypeBean getType() {
        return type;
    }

    public void setType(TypeBean type) {
        this.type = type;
    }

    public Object getHoliday() {
        return holiday;
    }

    public void setHoliday(Object holiday) {
        this.holiday = holiday;
    }

    public static class TypeBean {
        /**
         * type : 0
         * name : 周二
         * week : 2
         */

        private int type;
        private String name;
        private int week;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }
    }
}
