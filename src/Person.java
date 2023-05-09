import java.io.Serializable;

//Jake Kistler
//Advanced java
//OCCC Spring
//Person Class
public class Person implements Serializable, Comparable<Person>
{
    private String first_name;
    private String last_name;
    private OCCCDate dob;

    Person(String first_name,String last_name)
    {
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = new OCCCDate(1,1,1900);
    }


    Person(String first_name,String last_name , OCCCDate dob)
    {
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
    }

    Person(Person p)
    {
        first_name = p.first_name;
        last_name = p.last_name;
        dob = p.dob;
    }


    public String getFirst_name()
    {
        return first_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }

    //these have been added to be able to get birthday information

    public OCCCDate getDob()
    {
        return dob;
    }

    public int getBirth_Month()
    {
        return dob.getMonthNumber();
    }

    public int getBirth_day()
    {
        return dob.getDayOfMonth();
    }

    public int getBirth_Year()
    {
        return dob.getYear();
    }



    public boolean equals(Person p)
    {
        return first_name.equalsIgnoreCase(p.first_name) && last_name.equalsIgnoreCase(p.last_name);
    }


    @Override
    public String toString()
    {
        return last_name + ", " + first_name + "DOB: " + dob.toString();
    }

    @Override
    public int compareTo(Person other)
    {
        int lastNameComparison = this.last_name.compareToIgnoreCase(other.getLast_name());
        if(lastNameComparison != 0)
        {
            return lastNameComparison;
        }
        else
        {
            return this.first_name.compareToIgnoreCase(other.getFirst_name());
        }
    }










}
