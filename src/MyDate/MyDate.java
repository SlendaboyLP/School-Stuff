package MyDate;

import java.util.ArrayList;
import java.util.Arrays;

public class MyDate {
    private int year;
    private int month;
    private int day;

    private  static String[] strMonth = {
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec"
    };

    private static String[] strDays = {
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday"
    };

    private  static int[] daysInMonth = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public MyDate(int year, int month, int day) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    public void setDate(int year, int month, int day) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    public static boolean isLeapYear(int year){
        boolean out = true;
        if(year % 4 == 0 ) out = true;
        if(year % 100 == 0) out = false;
        if(year % 400 == 0) out = true;
        return out;
    }

    public static boolean isValidDate(int year, int month, int day){
        boolean out = year >= 0;
        if(month < 1 || month > 12) out = false;
        if(day < 1 || day > daysInMonth[month - 1]) out = false;
        if(month == 2 && day == 29 && !isLeapYear(year)) out = false;
        return out;
    }
    public static int getDayOfWeek(int year, int month, int day){
        ArrayList<Integer> centuryTable = new ArrayList<Integer>();
        ArrayList<Integer> centuryNumbers = new ArrayList<Integer>();

        int count = 0;
        for (int i = 0; i < 10000; i+=100){
            centuryTable.add(i);
            if(count == 0) centuryNumbers.add(6);
            if(count == 1) centuryNumbers.add(4);
            if(count == 2) centuryNumbers.add(2);
            if(count == 3) centuryNumbers.add(0);
            count++;
            if(count == 4) count = 0;
        }

        int lastTwoDigits = year % 100;
        int century = year - lastTwoDigits;
        int centuryNumber = centuryNumbers.get(centuryTable.indexOf(century));


        int[] monthTable = {
            0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5
        };

        if(isLeapYear(year)){
            monthTable[0] = 6;
            monthTable[1] = 2;
        }
        int monthNumber = monthTable[month - 1];
        return (lastTwoDigits + lastTwoDigits / 4 + centuryNumber + monthNumber + day) % 7;

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if(year < 0) return;
        if(year > 9999) return;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if(month < 1 || month > 12) return;
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        int max = daysInMonth[this.month - 1];
        if(this.month == 2 && isLeapYear(this.year)) max = 29;


        if(day < 1 || day > max) return;
        this.day = day;
    }

    public static String[] getStrMonth() {
        return strMonth;
    }

    public static void setStrMonth(String[] strMonth) {
        MyDate.strMonth = strMonth;
    }

    public static String[] getStrDays() {
        return strDays;
    }

    public static void setStrDays(String[] strDays) {
        MyDate.strDays = strDays;
    }

    public static int[] getDaysInMonth() {
        return daysInMonth;
    }

    public static void setDaysInMonth(int[] daysInMonth) {
        MyDate.daysInMonth = daysInMonth;
    }

    public String toString(){
        return strDays[getDayOfWeek(year, month, day)] + " " + day + " " + strMonth[month - 1] + " " + year;
    }

    public MyDate nextDay(){
        int nextDay = this.day + 1;
        int nextMonth = this.month;
        int nextYear = this.year;
        int[] currDaysInMonth = Arrays.copyOf(daysInMonth, daysInMonth.length);

        if(this.month == 2 && isLeapYear(nextYear))
            currDaysInMonth[1] = 29;

        if(nextDay > currDaysInMonth[this.month - 1]){
            nextDay = 1;
            nextMonth++;
            if(nextMonth > 12){
                nextMonth = 1;
                nextYear++;
            }
        }

        this.setDate(nextYear, nextMonth, nextDay);
        return this;
    }

    public MyDate nextMonth(){
        int nextDay = day;
        int nextMonth = month + 1;
        int nextYear = year;
        if(nextMonth > 12){
            nextMonth = 1;
            nextYear++;
        }

        this.setDate(nextYear, nextMonth, nextDay);
        return this;
    }

    public MyDate nextYear(){
        int nextDay = day;
        int nextMonth = month;
        int nextYear = year + 1;

        this.setDate(nextYear, nextMonth, nextDay);
        return this;
    }

    public MyDate previousDay(){
        int prevDay = day - 1;
        int prevMonth = month;
        int prevYear = year;
        if(prevDay < 1){
            prevMonth--;
            if(prevMonth < 1){
                prevMonth = 12;
                prevYear--;
            }
            prevDay = daysInMonth[prevMonth - 1];
        }
        this.setDate(prevYear, prevMonth, prevDay);
        return this;
    }

    public MyDate previousMonth(){
        int prevDay = day;
        int prevMonth = month - 1;
        int prevYear = year;
        if(prevMonth < 1){
            prevMonth = 12;
            prevYear--;
        }
        if(prevDay > daysInMonth[prevMonth - 1]){
            prevDay = daysInMonth[prevMonth - 1];
        }

        this.setDate(prevYear, prevMonth, prevDay);
        return this;
    }

    public MyDate previousYear(){
        int prevDay = day;
        int prevMonth = month;
        int prevYear = year - 1;

        if(isLeapYear(year) && (prevDay > daysInMonth[prevMonth - 1])){
            prevDay = daysInMonth[prevMonth - 1];
        }

        this.setDate(prevYear, prevMonth, prevDay);
        return this;
    }



}
