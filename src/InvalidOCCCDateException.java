public class InvalidOCCCDateException extends IllegalArgumentException
{
    private String message;
    private static String DEFAULT_INVALID_OCCCDATE_MESSAGE = "Date is invalid";

    public InvalidOCCCDateException()
    {
        super();
        message = DEFAULT_INVALID_OCCCDATE_MESSAGE;
    }

    public InvalidOCCCDateException(String message)
    {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    @Override
    public String toString()
    {
        return message;
    }

}//end class
