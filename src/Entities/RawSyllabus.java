package src.Entities;

public class RawSyllabus {
    //dataFormat is either "html" or "pdf file"
    public String dataFormat;
    public String rawSyllabusData;
    public boolean syllabusFound;
    public String courseId;
    
    public RawSyllabus(String dataFormat, String rawSyllabusData, String courseId, boolean syllabusFound) {
        this.dataFormat = dataFormat;
        this.rawSyllabusData = rawSyllabusData;
        this.courseId = courseId;
        this.syllabusFound = false;
    }
}