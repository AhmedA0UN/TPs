
public class TempsException extends Exception {
private String message;
public TempsException()
{
    
}
public TempsException(String m)
{
    this.message=m;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

}
