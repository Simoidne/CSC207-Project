package Tests.ApiTests;

import ApiPackage.RawSyllabus;
import ApiPackage.SyllabusConverter;
import entity.Assignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


// TODO move this test files into java
public class SyllabusConverterTests {
    SyllabusConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new SyllabusConverter();
    }

    @Test
    @DisplayName("Test if the syllabus converter catchs an empty RawSyllabus class")
    public void testSyllabusConverter_EmptyRawSyllabus() {
        RawSyllabus syllabus = new RawSyllabus("",
                "",
                "",
                false);

        // Test if the following code throws a SyllabusNotFound Exception
        List<Assignment> actual = converter.getAssignments(syllabus);

    }
}
