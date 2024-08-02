package ApiPackage;

public class NoAPITokenException extends Exception{
    @Override
    public String getMessage() {
        return "No API token provided";
    }
}
