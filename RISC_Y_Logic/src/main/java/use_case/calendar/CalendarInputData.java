package use_case.calendar;

public class CalendarInputData {
    private int year;
    private int month;

    public CalendarInputData(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }
}
