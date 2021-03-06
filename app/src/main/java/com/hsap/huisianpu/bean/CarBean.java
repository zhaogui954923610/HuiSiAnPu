package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 217/12/16.
 */

public class CarBean {

    /**
     * code : 2
     * msg : null
     * success : true
     * data : {"createName":"王二","nameList":["王三"],"waIntegration":{"id":54,"startTime":{"dayOfMonth":16,"dayOfWeek":"SATURDAY","month":"DECEMBER","year":217,"dayOfYear":35,"monthValue":12,"hour":1,"minute":24,"nano":,"second":,"chronology":{"id":"ISO","calendarType":"iso861"}},"endTime":{"dayOfMonth":16,"dayOfWeek":"SATURDAY","month":"DECEMBER","year":217,"dayOfYear":35,"monthValue":12,"hour":14,"minute":24,"nano":,"second":,"chronology":{"id":"ISO","calendarType":"iso861"}},"totalTime":null,"state":,"reason":null,"type":4,"type2":"151272786","createTime":{"dayOfMonth":16,"dayOfWeek":"SATURDAY","month":"DECEMBER","year":217,"dayOfYear":35,"monthValue":12,"hour":1,"minute":25,"nano":,"second":59,"chronology":{"id":"ISO","calendarType":"iso861"}}},"object":{"leixing":"商务用车","didian":"得","shixiang":"得"},"nameId":[3]}
     */

    private int code;
    private Object msg;
    private boolean success;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createName : 王二
         * nameList : ["王三"]
         * waIntegration : {"id":54,"startTime":{"dayOfMonth":16,"dayOfWeek":"SATURDAY","month":"DECEMBER","year":217,"dayOfYear":35,"monthValue":12,"hour":1,"minute":24,"nano":,"second":,"chronology":{"id":"ISO","calendarType":"iso861"}},"endTime":{"dayOfMonth":16,"dayOfWeek":"SATURDAY","month":"DECEMBER","year":217,"dayOfYear":35,"monthValue":12,"hour":14,"minute":24,"nano":,"second":,"chronology":{"id":"ISO","calendarType":"iso861"}},"totalTime":null,"state":,"reason":null,"type":4,"type2":"151272786","createTime":{"dayOfMonth":16,"dayOfWeek":"SATURDAY","month":"DECEMBER","year":217,"dayOfYear":35,"monthValue":12,"hour":1,"minute":25,"nano":,"second":59,"chronology":{"id":"ISO","calendarType":"iso861"}}}
         * object : {"leixing":"商务用车","didian":"得","shixiang":"得"}
         * nameId : [3]
         */

        private String createName;
        private WaIntegrationBean waIntegration;
        private ObjectBean object;
        private List<String> nameList;
        private List<Integer> nameId;

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public WaIntegrationBean getWaIntegration() {
            return waIntegration;
        }

        public void setWaIntegration(WaIntegrationBean waIntegration) {
            this.waIntegration = waIntegration;
        }

        public ObjectBean getObject() {
            return object;
        }

        public void setObject(ObjectBean object) {
            this.object = object;
        }

        public List<String> getNameList() {
            return nameList;
        }

        public void setNameList(List<String> nameList) {
            this.nameList = nameList;
        }

        public List<Integer> getNameId() {
            return nameId;
        }

        public void setNameId(List<Integer> nameId) {
            this.nameId = nameId;
        }

        public static class WaIntegrationBean {
            /**
             * id : 54
             * startTime : {"dayOfMonth":16,"dayOfWeek":"SATURDAY","month":"DECEMBER","year":217,"dayOfYear":35,"monthValue":12,"hour":1,"minute":24,"nano":,"second":,"chronology":{"id":"ISO","calendarType":"iso861"}}
             * endTime : {"dayOfMonth":16,"dayOfWeek":"SATURDAY","month":"DECEMBER","year":217,"dayOfYear":35,"monthValue":12,"hour":14,"minute":24,"nano":,"second":,"chronology":{"id":"ISO","calendarType":"iso861"}}
             * totalTime : null
             * state : 
             * reason : null
             * type : 4
             * type2 : 151272786
             * createTime : {"dayOfMonth":16,"dayOfWeek":"SATURDAY","month":"DECEMBER","year":217,"dayOfYear":35,"monthValue":12,"hour":1,"minute":25,"nano":,"second":59,"chronology":{"id":"ISO","calendarType":"iso861"}}
             */

            private int id;
            private StartTimeBean startTime;
            private EndTimeBean endTime;
            private Object totalTime;
            private int state;
            private Object reason;
            private int type;
            private String type2;
            private CreateTimeBean createTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public StartTimeBean getStartTime() {
                return startTime;
            }

            public void setStartTime(StartTimeBean startTime) {
                this.startTime = startTime;
            }

            public EndTimeBean getEndTime() {
                return endTime;
            }

            public void setEndTime(EndTimeBean endTime) {
                this.endTime = endTime;
            }

            public Object getTotalTime() {
                return totalTime;
            }

            public void setTotalTime(Object totalTime) {
                this.totalTime = totalTime;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public Object getReason() {
                return reason;
            }

            public void setReason(Object reason) {
                this.reason = reason;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getType2() {
                return type2;
            }

            public void setType2(String type2) {
                this.type2 = type2;
            }

            public CreateTimeBean getCreateTime() {
                return createTime;
            }

            public void setCreateTime(CreateTimeBean createTime) {
                this.createTime = createTime;
            }

            public static class StartTimeBean {
                /**
                 * dayOfMonth : 16
                 * dayOfWeek : SATURDAY
                 * month : DECEMBER
                 * year : 217
                 * dayOfYear : 35
                 * monthValue : 12
                 * hour : 1
                 * minute : 24
                 * nano : 
                 * second : 
                 * chronology : {"id":"ISO","calendarType":"iso861"}
                 */

                private int dayOfMonth;
                private String dayOfWeek;
                private String month;
                private int year;
                private int dayOfYear;
                private int monthValue;
                private int hour;
                private int minute;
                private int nano;
                private int second;
                private ChronologyBean chronology;

                public int getDayOfMonth() {
                    return dayOfMonth;
                }

                public void setDayOfMonth(int dayOfMonth) {
                    this.dayOfMonth = dayOfMonth;
                }

                public String getDayOfWeek() {
                    return dayOfWeek;
                }

                public void setDayOfWeek(String dayOfWeek) {
                    this.dayOfWeek = dayOfWeek;
                }

                public String getMonth() {
                    return month;
                }

                public void setMonth(String month) {
                    this.month = month;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }

                public int getDayOfYear() {
                    return dayOfYear;
                }

                public void setDayOfYear(int dayOfYear) {
                    this.dayOfYear = dayOfYear;
                }

                public int getMonthValue() {
                    return monthValue;
                }

                public void setMonthValue(int monthValue) {
                    this.monthValue = monthValue;
                }

                public int getHour() {
                    return hour;
                }

                public void setHour(int hour) {
                    this.hour = hour;
                }

                public int getMinute() {
                    return minute;
                }

                public void setMinute(int minute) {
                    this.minute = minute;
                }

                public int getNano() {
                    return nano;
                }

                public void setNano(int nano) {
                    this.nano = nano;
                }

                public int getSecond() {
                    return second;
                }

                public void setSecond(int second) {
                    this.second = second;
                }

                public ChronologyBean getChronology() {
                    return chronology;
                }

                public void setChronology(ChronologyBean chronology) {
                    this.chronology = chronology;
                }

                public static class ChronologyBean {
                    /**
                     * id : ISO
                     * calendarType : iso861
                     */

                    private String id;
                    private String calendarType;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getCalendarType() {
                        return calendarType;
                    }

                    public void setCalendarType(String calendarType) {
                        this.calendarType = calendarType;
                    }
                }
            }

            public static class EndTimeBean {
                /**
                 * dayOfMonth : 16
                 * dayOfWeek : SATURDAY
                 * month : DECEMBER
                 * year : 217
                 * dayOfYear : 35
                 * monthValue : 12
                 * hour : 14
                 * minute : 24
                 * nano : 
                 * second : 
                 * chronology : {"id":"ISO","calendarType":"iso861"}
                 */

                private int dayOfMonth;
                private String dayOfWeek;
                private String month;
                private int year;
                private int dayOfYear;
                private int monthValue;
                private int hour;
                private int minute;
                private int nano;
                private int second;
                private ChronologyBeanX chronology;

                public int getDayOfMonth() {
                    return dayOfMonth;
                }

                public void setDayOfMonth(int dayOfMonth) {
                    this.dayOfMonth = dayOfMonth;
                }

                public String getDayOfWeek() {
                    return dayOfWeek;
                }

                public void setDayOfWeek(String dayOfWeek) {
                    this.dayOfWeek = dayOfWeek;
                }

                public String getMonth() {
                    return month;
                }

                public void setMonth(String month) {
                    this.month = month;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }

                public int getDayOfYear() {
                    return dayOfYear;
                }

                public void setDayOfYear(int dayOfYear) {
                    this.dayOfYear = dayOfYear;
                }

                public int getMonthValue() {
                    return monthValue;
                }

                public void setMonthValue(int monthValue) {
                    this.monthValue = monthValue;
                }

                public int getHour() {
                    return hour;
                }

                public void setHour(int hour) {
                    this.hour = hour;
                }

                public int getMinute() {
                    return minute;
                }

                public void setMinute(int minute) {
                    this.minute = minute;
                }

                public int getNano() {
                    return nano;
                }

                public void setNano(int nano) {
                    this.nano = nano;
                }

                public int getSecond() {
                    return second;
                }

                public void setSecond(int second) {
                    this.second = second;
                }

                public ChronologyBeanX getChronology() {
                    return chronology;
                }

                public void setChronology(ChronologyBeanX chronology) {
                    this.chronology = chronology;
                }

                public static class ChronologyBeanX {
                    /**
                     * id : ISO
                     * calendarType : iso861
                     */

                    private String id;
                    private String calendarType;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getCalendarType() {
                        return calendarType;
                    }

                    public void setCalendarType(String calendarType) {
                        this.calendarType = calendarType;
                    }
                }
            }

            public static class CreateTimeBean {
                /**
                 * dayOfMonth : 16
                 * dayOfWeek : SATURDAY
                 * month : DECEMBER
                 * year : 217
                 * dayOfYear : 35
                 * monthValue : 12
                 * hour : 1
                 * minute : 25
                 * nano : 
                 * second : 59
                 * chronology : {"id":"ISO","calendarType":"iso861"}
                 */

                private int dayOfMonth;
                private String dayOfWeek;
                private String month;
                private int year;
                private int dayOfYear;
                private int monthValue;
                private int hour;
                private int minute;
                private int nano;
                private int second;
                private ChronologyBeanXX chronology;

                public int getDayOfMonth() {
                    return dayOfMonth;
                }

                public void setDayOfMonth(int dayOfMonth) {
                    this.dayOfMonth = dayOfMonth;
                }

                public String getDayOfWeek() {
                    return dayOfWeek;
                }

                public void setDayOfWeek(String dayOfWeek) {
                    this.dayOfWeek = dayOfWeek;
                }

                public String getMonth() {
                    return month;
                }

                public void setMonth(String month) {
                    this.month = month;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }

                public int getDayOfYear() {
                    return dayOfYear;
                }

                public void setDayOfYear(int dayOfYear) {
                    this.dayOfYear = dayOfYear;
                }

                public int getMonthValue() {
                    return monthValue;
                }

                public void setMonthValue(int monthValue) {
                    this.monthValue = monthValue;
                }

                public int getHour() {
                    return hour;
                }

                public void setHour(int hour) {
                    this.hour = hour;
                }

                public int getMinute() {
                    return minute;
                }

                public void setMinute(int minute) {
                    this.minute = minute;
                }

                public int getNano() {
                    return nano;
                }

                public void setNano(int nano) {
                    this.nano = nano;
                }

                public int getSecond() {
                    return second;
                }

                public void setSecond(int second) {
                    this.second = second;
                }

                public ChronologyBeanXX getChronology() {
                    return chronology;
                }

                public void setChronology(ChronologyBeanXX chronology) {
                    this.chronology = chronology;
                }

                public static class ChronologyBeanXX {
                    /**
                     * id : ISO
                     * calendarType : iso861
                     */

                    private String id;
                    private String calendarType;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getCalendarType() {
                        return calendarType;
                    }

                    public void setCalendarType(String calendarType) {
                        this.calendarType = calendarType;
                    }
                }
            }
        }

        public static class ObjectBean {
            /**
             * leixing : 商务用车
             * didian : 得
             * shixiang : 得
             */
            private String guige;
            private String shuliang;
            private String danjia;
            private String yongtu;
            private String jine;
            private String name;
            private String piaoju;

            public String getGuige() {
                return guige;
            }

            public void setGuige(String guige) {
                this.guige = guige;
            }

            public String getShuliang() {
                return shuliang;
            }

            public void setShuliang(String shuliang) {
                this.shuliang = shuliang;
            }

            public String getDanjia() {
                return danjia;
            }

            public void setDanjia(String danjia) {
                this.danjia = danjia;
            }

            public String getYongtu() {
                return yongtu;
            }

            public void setYongtu(String yongtu) {
                this.yongtu = yongtu;
            }

            public String getJine() {
                return jine;
            }

            public void setJine(String jine) {
                this.jine = jine;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPiaoju() {
                return piaoju;
            }

            public void setPiaoju(String piaoju) {
                this.piaoju = piaoju;
            }

            private String leixing;
            private String didian;
            private String shixiang;

            public String getLeixing() {
                return leixing;
            }

            public void setLeixing(String leixing) {
                this.leixing = leixing;
            }

            public String getDidian() {
                return didian;
            }

            public void setDidian(String didian) {
                this.didian = didian;
            }

            public String getShixiang() {
                return shixiang;
            }

            public void setShixiang(String shixiang) {
                this.shixiang = shixiang;
            }
        }
    }
}
