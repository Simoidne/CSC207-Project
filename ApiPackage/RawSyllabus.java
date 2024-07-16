package ApiPackage;

public class RawSyllabus {
    public String dataFormat;
    public String rawSyllabusData;
    public boolean syllabusFound;
    
    public RawSyllabus(String dataFormat, String rawSyllabusData) {
        this.dataFormat = dataFormat;
        this.rawSyllabusData = rawSyllabusData;
        this.syllabusFound = false;
    }
}