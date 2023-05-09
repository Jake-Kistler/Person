//Jake Kistler
//Advanced java
//OCCC Spring
//OCCCPerson class

import java.io.Serializable;
import java.util.Objects;//Auto imported

public class OCCCPerson extends RegisteredPerson
{
    private String studentID;

    public OCCCPerson(RegisteredPerson registeredPerson, String studentID)
    {
        super(registeredPerson.getFirst_name(), registeredPerson.getLast_name(), registeredPerson.getDob(), registeredPerson.getGovID());
        this.studentID = studentID;
    }


    public OCCCPerson(OCCCPerson p)
    {
        super(p.getFirst_name(), p.getLast_name(),p.getDob(), p.getGovID());
        this.studentID = p.studentID;
    }

    public String getStudentID()
    {
        return studentID;
    }

    @Override //Auto generated
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof OCCCPerson))
        {
            return false;
        }
        if (!super.equals(o))
        {
            return false;
        }
        OCCCPerson that = (OCCCPerson) o;
        return Objects.equals(getStudentID(), that.getStudentID());
    }


    @Override
    public boolean equals(Person p)
    {
        return super.equals(p);
    }


    public String toString()
    {
        return super.toString() + "{"+studentID + "}";
    }


}

