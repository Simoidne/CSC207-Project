package use_case;

import ApiPackage.SyllabusConverter;
import ApiPackage.RawSyllabus;
import ApiPackage.SyllabusNotFoundException;
import entity.Assignment;
import entity.Course;

import java.util.List;
import java.util.Scanner;

public class GetAssignmentsUseCase {
    public List<Assignment> getAssignments(Course course) throws SyllabusNotFoundException {
        if (course.getAssignments() != null) {
            return course.getAssignments();
        } else {
            SyllabusConverter syllabusConverter = new SyllabusConverter();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Syllabus: ");
            String syllabusData = scanner.nextLine();
            System.out.println("Enter data format used for syllabus (TEXT or HTML): ");
            String syllabusFormat = scanner.nextLine();

            if (!syllabusFormat.equals("HTML") && !syllabusFormat.equals("TEXT")) {
                throw new IllegalArgumentException("Syllabus format is not supported");
            }

            RawSyllabus rawSyllabus = new RawSyllabus(syllabusFormat,
                    syllabusData,
                    course.getId(),
                    true);
            List<Assignment> assignments = syllabusConverter.getAssignments(rawSyllabus);
            return assignments;
        }
    }
}
