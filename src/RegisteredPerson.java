//Jake Kistler
//Advanced java
//OCCC Spring
//RegisteredPerson

import java.io.Serializable;
import java.util.Objects;//Auto imported

public class RegisteredPerson extends Person implements Serializable
{
    private String govID;

    public RegisteredPerson(String first_name,String last_name,OCCCDate dob,String govID)
    {
        super(first_name,last_name,dob); // this is how we have to do it for children
        this.govID = govID;
    }

    public RegisteredPerson(Person p,String govID)
    {
        super(p);
        this.govID = govID;
    }

    public RegisteredPerson(RegisteredPerson r)
    {
        super(r.getFirst_name(), r.getLast_name());
        this.govID = r.govID;
    }

    public String getGovID()
    {
        return govID;
    }

    public void setGovID(String govID)
    {
        this.govID = govID;
    }

    @Override //Auto generated
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof RegisteredPerson))
        {
            return false;
        }
        RegisteredPerson that = (RegisteredPerson) o;
        return Objects.equals(getGovID(), that.getGovID());
    }

    @Override
    public boolean equals(Person p)
    {
        return super.equals(p);
    }

    @Override
    public String toString()
    {
        return super.toString() + " [" + govID + "]"; //super here takes care of first and last name
    }





}
