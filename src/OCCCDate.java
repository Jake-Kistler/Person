//Jake Kistler
//Advanced Java
//OCCC Spring
//OCCCDate Class

import javax.swing.*;
import java.text.SimpleDateFormat;//https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
import java.util.Calendar;//https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
import java.util.GregorianCalendar;//https://docs.oracle.com/javase/7/docs/api/java/util/GregorianCalendar.html
import java.time.LocalDate;//https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
import java.time.Period;//https://docs.oracle.com/javase/8/docs/api/java/time/Period.html



public class OCCCDate
{

    private int dayOfMonth;
    private int monthOfYear;
    private int year;
    private GregorianCalendar gc;
    private boolean dateFormat = FORMAT_US;
    private boolean dateStyle = STYLE_NUMBERS;
    private boolean dateDayName = SHOW_DAY_NAME;

    public static final boolean FORMAT_US = true;
    public static final boolean FORMAT_EURO = false;
    public static final boolean STYLE_NUMBERS = true;
    public static final boolean STYLE_NAMES = false;
    public static final boolean SHOW_DAY_NAME = true;
    public static final boolean HIDE_DAY_NAME = false;

    public OCCCDate()
    {
        gc = new GregorianCalendar();
        dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
        monthOfYear = gc.get(Calendar.MONTH) + 1;
        year = gc.get(Calendar.YEAR);
    }

    public OCCCDate(int day, int month, int year) throws InvalidOCCCDateException
    {
        this.dayOfMonth = day;
        this.monthOfYear = month;
        this.year = year;

        if(!isValidDate(day,month,year))
        {
            JOptionPane.showMessageDialog(null, "Invalid date: " + day + "/" + month + "/" + year, "Error", JOptionPane.ERROR_MESSAGE);
            throw new InvalidOCCCDateException("Invalid date: " + day + "/" + month + "/" + year);
        }

        gc = new GregorianCalendar(year, month - 1, day);
    }

    public OCCCDate(GregorianCalendar gc)
    {
        this.gc = gc;
        dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
        monthOfYear = gc.get(Calendar.MONTH) + 1;
        year = gc.get(Calendar.YEAR);
    }

    public OCCCDate(OCCCDate d)
    {
        this.dayOfMonth = d.dayOfMonth;
        this.monthOfYear = d.monthOfYear;
        this.year = d.year;
        gc = new GregorianCalendar(year, monthOfYear - 1, dayOfMonth);
    }

    /*
    public OCCCDate(String date) throws InvalidOCCCDateException
    {
        String[] parts = date.split("/");
        if (parts.length != 3)
        {
            throw new InvalidOCCCDateException("Invalid date format: " + date);
        }
        this.dayOfMonth = Integer.parseInt(parts[1]);
        this.monthOfYear = Integer.parseInt(parts[0]);
        this.year = Integer.parseInt(parts[2]);
        if (!isValidDate(dayOfMonth, monthOfYear, year))
        {
            throw new InvalidOCCCDateException("Invalid date: " + dayOfMonth + "/" + monthOfYear + "/" + year);
        }
        gc = new GregorianCalendar(year, monthOfYear - 1, dayOfMonth);
    }

     */


    public int getDayOfMonth()
    {
        return dayOfMonth;
    }

    public String getDayName()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        return sdf.format(gc.getTime());
    }

    public int getMonthNumber()
    {
        return monthOfYear;
    }

    public String getMonthName()
    {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return months[monthOfYear - 1];
    }

    public int getYear()
    {
        return year;
    }

    public void setDateFormat(boolean df)
    {
        dateFormat = df;
    }

    public void setStyleFormat(boolean sf)
    {
        dateStyle = sf;
    }

    public void setDayName(boolean dn)
    {
        dateDayName = dn;
    }

    public int getDifferenceInYears()
    {
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(year, monthOfYear, dayOfMonth);
        if (date.isAfter(now))
        {
            return 0;
        }
        return Period.between(date, now).getYears();
    }

    public int getDifferenceInYears(OCCCDate d)
    {
        LocalDate date1 = LocalDate.of(year, monthOfYear, dayOfMonth);
        LocalDate date2 = LocalDate.of(d.year, d.monthOfYear, d.dayOfMonth);
        if (date1.isAfter(date2))
        {
            return 0;
        }
        return Period.between(date1, date2).getYears();
    }


    public boolean equals(OCCCDate dob)
    {
        if (this.dayOfMonth == dob.dayOfMonth && this.monthOfYear == dob.monthOfYear && this.year == dob.year)
        {
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        if (dateFormat == FORMAT_EURO)
        {
            if (dateStyle == STYLE_NAMES)
            {
                if (dateDayName)
                {
                    return String.format("%02d %s %d", dayOfMonth, getMonthName(), year);
                }
                else
                {
                    return String.format("%02d %s %04d", dayOfMonth, getMonthName(), year);
                }
            }
            else
            {
                if (dateDayName)
                {
                    return String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear, year);
                }
                else
                {
                    return String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear, year);
                }
            }
        }
        else
        {
            if (dateStyle == STYLE_NAMES)
            {
                if (dateDayName)
                {
                    return String.format("%s %02d, %04d", getMonthName(), dayOfMonth, year);
                }
                else
                {
                    return String.format("%s %02d, %04d", getMonthName(), dayOfMonth, year);
                }
            }
            else
            {
                if (dateDayName)
                {
                    return String.format("%02d/%02d/%04d", monthOfYear, dayOfMonth, year);
                }
                else
                {
                    return String.format("%02d/%02d/%04d", monthOfYear, dayOfMonth, year);
                }
            }
        }
    }//end toString

    private boolean isValidDate(int day, int month, int year) throws InvalidOCCCDateException
    {
        if (month < 1 || month > 12)
        {
            return false;
        }
        if (day < 1 || day > 31)
        {
            return false;
        }
        if (month == 2 && day > 29)
        {
            return false;
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30)
        {
            return false;
        }
        if (year < 0)
        {
            return false;
        }
        return true;
    }
}

