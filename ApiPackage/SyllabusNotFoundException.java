package ApiPackage;

public class SyllabusNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Syllabus not found.";
    }
}
