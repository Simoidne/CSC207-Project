package Tests.ApiTests;

import ApiPackage.RawSyllabus;
import ApiPackage.SyllabusConverter;
import ApiPackage.SyllabusNotFoundException;
import entity.Assignment;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


// TODO move this test files into java
public class SyllabusConverterTests {
    SyllabusConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new SyllabusConverter();
    }

    @Test
    @DisplayName("Test if the syllabus converter catches an empty RawSyllabus class")
    public void testSyllabusConverter_EmptyRawSyllabus() {
        RawSyllabus syllabus = new RawSyllabus("",
                "",
                "",
                false);

        // Test if the following code throws a SyllabusNotFound Exception
        Assert.assertThrows(SyllabusNotFoundException.class, () -> converter.getAssignments(syllabus));
    }

    @Test
    @DisplayName("Test using CSC207 Syllabus to see if we get the proper assignments back")
    public void testSyllabusConverter_CSC207TestSyllabus() throws SyllabusNotFoundException {
        RawSyllabus syllabus = new RawSyllabus("HTML",
                "<h2><strong>CSC207H1Y: Software Design (Summer 2024)</strong></h2>\n" +
                        "<table style=\"border-collapse: collapse; width: 54.3228%; height: 193px;\" border=\"1\">\n" +
                        "<tbody>\n" +
                        "<tr style=\"height: 28px;\">\n" +
                        "<td style=\"width: 32.6198%; height: 28px;\"><strong>Class Location</strong></td>\n" +
                        "<td style=\"width: 67.2818%; height: 28px;\"><a class=\"inline_disabled\" href=\"https://www.classfind.com/toronto/room/MP102\" target=\"_blank\">McLennan Physical Labs - MP102</a></td>\n" +
                        "</tr>\n" +
                        "<tr style=\"height: 28px;\">\n" +
                        "<td style=\"width: 32.6198%; height: 28px;\"><strong>Class Hours</strong></td>\n" +
                        "<td style=\"width: 67.2818%; height: 28px;\">Tuesdays 6-8 PM</td>\n" +
                        "</tr>\n" +
                        "<tr style=\"height: 28px;\">\n" +
                        "<td style=\"width: 32.6198%; height: 28px;\"><strong>Instructor</strong></td>\n" +
                        "<td style=\"width: 67.2818%; height: 28px;\">\n" +
                        "<p><a class=\"inline_disabled\" href=\"https://www.cs.toronto.edu/~yasamanro/\" target=\"_blank\">Yasaman Rohanifar </a>(She/Her)</p>\n" +
                        "<p>PhD Candidate<br>Department of Computer Science&nbsp; <br>University of Toronto<br>Email: <a class=\"inline_disabled\" style=\"font-family: inherit; font-size: 1rem;\" href=\"mailto:yasamanro@cs.toronto.edu\" target=\"_blank\">yasamanro@cs.toronto.edu</a></p>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "<tr style=\"height: 28px;\">\n" +
                        "<td style=\"width: 32.6198%; height: 28px;\"><strong>Course Email</strong></td>\n" +
                        "<td style=\"width: 67.2818%; height: 28px;\">\n" +
                        "<p><a class=\"notion-link-token notion-focusable-token notion-enable-hover\" href=\"mailto:csc207-2024-05@cs.toronto.edu\" data-token-index=\"0\"><span class=\"link-annotation-unknown-block-id--126210167\">csc207-2024-05@cs.toronto.edu</span></a></p>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "<tr style=\"height: 28px;\">\n" +
                        "<td style=\"width: 32.6198%; height: 28px;\"><strong>Discussion Board</strong></td>\n" +
                        "<td style=\"width: 67.2818%; height: 28px;\">\n" +
                        "<p><a class=\"inline_disabled\" title=\"Link\" href=\"https://piazza.com/utoronto.ca/summer2024/csc207h1y/info\" target=\"_blank\">Piazza</a></p>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td style=\"width: 32.6198%;\"><strong>Course Schedule</strong></td>\n" +
                        "<td style=\"width: 67.2818%;\">\n" +
                        "<p><a title=\"Weekly Calendar and Important Dates\" href=\"https://q.utoronto.ca/courses/345741/pages/weekly-calendar-and-important-dates\" data-course-type=\"wikiPages\" data-published=\"true\" data-api-endpoint=\"https://q.utoronto.ca/api/v1/courses/345741/pages/weekly-calendar-and-important-dates\" data-api-returntype=\"Page\">Weekly Calendar and Important Dates</a></p>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</tbody>\n" +
                        "</table>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<h3><strong>Course Description</strong></h3>\n" +
                        "<p>An introduction to software design and development concepts, methods, and tools using a statically typed object-oriented programming language such as Java. Topics from: version control, unit testing, refactoring, object-oriented design and development, design patterns, advanced IDE usage, regular expressions, and reflection. Representation of floating-point numbers and introduction to numerical computation.</p>\n" +
                        "<p><em><strong>Prerequisite:</strong></em> 60% or higher in&nbsp;<a href=\"https://artsci.calendar.utoronto.ca/course/CSC148H1\">CSC148H1</a>/ 60% or higher in&nbsp;<a href=\"https://artsci.calendar.utoronto.ca/course/CSC111H1\">CSC111H1</a></p>\n" +
                        "<p><em><strong>Distribution Requirement:</strong> Science</em></p>\n" +
                        "<p><em><strong>Breadth Requirements:</strong></em>&nbsp;The Physical and Mathematical Universes (5)</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<h3><strong><span>Learning Objectives</span></strong></h3>\n" +
                        "<p>By the end of this course, you will:</p>\n" +
                        "<ul>\n" +
                        "<li>be able to effectively communicate about software design with your peers</li>\n" +
                        "<li>be comfortable using version control</li>\n" +
                        "<li>be able to write Java code to satisfy program specifications</li>\n" +
                        "<li>be able to apply the SOLID design principles and Clean Architecture to design object-oriented software solutions</li>\n" +
                        "<li>have experienced what it is like to work in a collaborative software development environment</li>\n" +
                        "<li>have a deeper understanding of how to approach testing your code</li>\n" +
                        "<li>be confident in your ability to learn and apply new concepts, software tools, and features of your IDE</li>\n" +
                        "</ul>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<h3><strong>Weekly Schedule</strong></h3>\n" +
                        "<table style=\"border-collapse: collapse; float: left; width: 97.9393%; height: 230px;\" border=\"1\">\n" +
                        "<thead>\n" +
                        "<tr style=\"height: 27px;\">\n" +
                        "<th style=\"width: 29.0021%; height: 27px;\">Activity</th>\n" +
                        "<th style=\"width: 25.7829%; height: 27px;\">Time</th>\n" +
                        "<th style=\"width: 16.2139%; height: 27px;\">Location</th>\n" +
                        "<th style=\"width: 28.9046%; height: 27px;\">Instructor/TAs</th>\n" +
                        "</tr>\n" +
                        "</thead>\n" +
                        "<tbody>\n" +
                        "<tr style=\"height: 29px;\">\n" +
                        "<td style=\"width: 29.0021%; height: 29px; text-align: center;\">Lecture (LEC 5101)</td>\n" +
                        "<td style=\"width: 25.7829%; height: 29px; text-align: center;\">Tuesdays, 6-8 PM</td>\n" +
                        "<td style=\"width: 16.2139%; height: 29px; text-align: center;\">MP 102</td>\n" +
                        "<td style=\"width: 28.9046%; height: 29px; text-align: center;\">Yasaman Rohanifar</td>\n" +
                        "</tr>\n" +
                        "<tr style=\"height: 29px;\">\n" +
                        "<td style=\"height: 116px; width: 29.0021%; text-align: center;\" rowspan=\"4\">Lab/Tutorial (TUT 5101)</td>\n" +
                        "<td style=\"height: 116px; width: 25.7829%; text-align: center;\" rowspan=\"4\">Thursdays, 6-8 PM</td>\n" +
                        "<td style=\"width: 16.2139%; height: 29px; text-align: center;\">BA 2200</td>\n" +
                        "<td style=\"width: 28.9046%; height: 29px; text-align: center;\">Tian Yu</td>\n" +
                        "</tr>\n" +
                        "<tr style=\"height: 29px;\">\n" +
                        "<td style=\"width: 16.2139%; height: 29px; text-align: center;\">BA 3175</td>\n" +
                        "<td style=\"width: 28.9046%; height: 29px; text-align: center;\">Yu Hau (Howard) Chen</td>\n" +
                        "</tr>\n" +
                        "<tr style=\"height: 29px;\">\n" +
                        "<td style=\"width: 16.2139%; height: 29px; text-align: center;\">BA 3185</td>\n" +
                        "<td style=\"width: 28.9046%; height: 29px; text-align: center;\">Jack Pitcher</td>\n" +
                        "</tr>\n" +
                        "<tr style=\"height: 29px;\">\n" +
                        "<td style=\"width: 16.2139%; height: 29px; text-align: center;\">BA 3195</td>\n" +
                        "<td style=\"width: 28.9046%; height: 29px; text-align: center;\">Ahraz Ahmad Arfi</td>\n" +
                        "</tr>\n" +
                        "<tr style=\"height: 29px;\">\n" +
                        "<td style=\"width: 29.0021%; height: 29px; text-align: center;\">TA Office Hours</td>\n" +
                        "<td style=\"width: 25.7829%; height: 29px; text-align: center;\">Mondays 5-6 PM</td>\n" +
                        "<td style=\"width: 16.2139%; height: 29px; text-align: center;\">Zoom</td>\n" +
                        "<td style=\"width: 28.9046%; height: 29px; text-align: center;\">Guy Khazma</td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td style=\"width: 29.0021%; text-align: center;\">TA Office Hours</td>\n" +
                        "<td style=\"width: 25.7829%; text-align: center;\">Wednesdays 6-7 PM</td>\n" +
                        "<td style=\"width: 16.2139%; text-align: center;\"><span>BA2270</span></td>\n" +
                        "<td style=\"width: 28.9046%; text-align: center;\">Pritish Mishra</td>\n" +
                        "</tr>\n" +
                        "<tr style=\"height: 29px;\">\n" +
                        "<td style=\"width: 29.0021%; height: 29px; text-align: center;\">Instructor Office Hours</td>\n" +
                        "<td style=\"width: 25.7829%; height: 29px; text-align: center;\">By Appointment Only</td>\n" +
                        "<td style=\"width: 16.2139%; height: 29px; text-align: center;\">Zoom</td>\n" +
                        "<td style=\"width: 28.9046%; height: 29px; text-align: center;\">Yasaman Rohanifar</td>\n" +
                        "</tr>\n" +
                        "</tbody>\n" +
                        "</table>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<h3><strong>Course Project and Final Exam</strong></h3>\n" +
                        "<p>The primary piece of term work in this course is a group project (teams of around 4–6 students). Between Weeks 2-4, we will be meeting during the Thursday lab/tutorial time slots to work on weekly activities in small groups. We strongly encourage you to actively participate in these sessions, as it will help you find potential members for your project team. Later sessions will be dedicated to working on the project milestones and consulting with your TAs. The final exam is a comprehensive exam.</p>\n" +
                        "<p><span style=\"background-color: #f1c40f;\"><strong><span class=\"notion-enable-hover\" data-token-index=\"0\">You need to achieve at least 40% on the final exam; otherwise, your course grade will be no higher than 47%, and you cannot pass the course.</span></strong></span></p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<h3><strong>Assessment Scheme</strong></h3>\n" +
                        "<table style=\"border-collapse: collapse; float: left; width: 93.1051%; height: 248px;\" border=\"1\">\n" +
                        "<thead>\n" +
                        "<tr style=\"height: 26px;\">\n" +
                        "<th style=\"width: 17.0224%; height: 28px; text-align: center;\">Assessment</th>\n" +
                        "<th style=\"width: 35.6556%; height: 28px; text-align: center;\">Description</th>\n" +
                        "<th style=\"width: 41.9222%; height: 28px; text-align: center;\">Due Date(s)</th>\n" +
                        "<th style=\"width: 5.2448%; height: 28px; text-align: center;\">Weight</th>\n" +
                        "</tr>\n" +
                        "</thead>\n" +
                        "<tbody>\n" +
                        "<tr style=\"height: 53px;\">\n" +
                        "<td style=\"width: 17.0224%; text-align: center; height: 53px;\">Study Notes Review</td>\n" +
                        "<td style=\"width: 35.6556%; text-align: center; height: 53px;\">10 Auto-graded Quizzes on Quercus (equal weight)</td>\n" +
                        "<td style=\"width: 41.9222%; text-align: center; height: 53px;\">Before Each Lecture (except for week 0 and 1)<br>Tuesdays by 6 PM</td>\n" +
                        "<td style=\"width: 5.2448%; text-align: center; height: 53px;\">5%</td>\n" +
                        "</tr>\n" +
                        "<tr style=\"height: 29px;\">\n" +
                        "<td style=\"width: 17.0224%; text-align: center; height: 42px;\">Quizzes</td>\n" +
                        "<td style=\"width: 35.6556%; text-align: center; height: 42px;\">10 Auto-graded Quizzes on Quercus (equal weight)</td>\n" +
                        "<td style=\"width: 41.9222%; text-align: center; height: 42px;\">\n" +
                        "<div class=\"date-container ng-star-inserted\">\n" +
                        "<div class=\"date-entry-line\">\n" +
                        "<div class=\"date-value\">Every Sunday by 11:59 PM<br>(except for week 0)</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</td>\n" +
                        "<td style=\"width: 5.2448%; text-align: center; height: 42px;\">10%</td>\n" +
                        "</tr>\n" +
                        "<tr style=\"height: 76px;\">\n" +
                        "<td style=\"width: 17.0224%; height: 63px; text-align: center;\">Midterm Exam</td>\n" +
                        "<td style=\"width: 35.6556%; height: 63px; text-align: center;\">Java Basics (topics will be announced)</td>\n" +
                        "<td style=\"width: 41.9222%; height: 63px; text-align: center;\"><span> During the first hour of<br>the tutorial on </span>Week 5 (June 13)</td>\n" +
                        "<td style=\"width: 5.2448%; height: 63px; text-align: center;\">15%</td>\n" +
                        "</tr>\n" +
                        "<tr style=\"height: 322px;\">\n" +
                        "<td style=\"width: 17.0224%; height: 10px; text-align: center;\">Course Project</td>\n" +
                        "<td style=\"width: 35.6556%; height: 10px; text-align: center;\">\n" +
                        "<p>Milestones completed throughout the term contribute to your final project grade:</p>\n" +
                        "<ol>\n" +
                        "<li style=\"text-align: left;\">Blueprint and Design - 5%</li>\n" +
                        "<li style=\"text-align: left;\">Phase 1 - 10%</li>\n" +
                        "<li style=\"text-align: left;\">Phase 2 - 10%</li>\n" +
                        "<li style=\"text-align: left;\">Presentation - 5%</li>\n" +
                        "</ol>\n" +
                        "<p style=\"text-align: left;\"><strong>* The total grade for the course project will be multiplied by a contribution coefficient (a number between 0 and 1) to ensure equal participation from all group members.&nbsp;</strong></p>\n" +
                        "</td>\n" +
                        "<td style=\"width: 41.9222%; height: 10px; text-align: center;\">\n" +
                        "<ol>\n" +
                        "<li style=\"text-align: left;\">Blueprint and Design (Week 5 - June 9)</li>\n" +
                        "<li style=\"text-align: left;\">Phase 1 (Week 8 - July 18)</li>\n" +
                        "<li style=\"text-align: left;\">Phase 2 (Week 11 - August 8)</li>\n" +
                        "<li style=\"text-align: left;\">Presentation (Week 11 - August 8)</li>\n" +
                        "</ol>\n" +
                        "</td>\n" +
                        "<td style=\"width: 5.2448%; height: 10px; text-align: center;\">30%</td>\n" +
                        "</tr>\n" +
                        "<tr style=\"height: 52px;\">\n" +
                        "<td style=\"width: 17.0224%; height: 52px; text-align: center;\">Final Exam</td>\n" +
                        "<td style=\"width: 35.6556%; height: 52px; text-align: center;\"></td>\n" +
                        "<td style=\"width: 41.9222%; height: 52px; text-align: center;\">To be scheduled by the Faculty of Arts &amp; Science Between Aug 15 to Aug 23</td>\n" +
                        "<td style=\"width: 5.2448%; height: 52px; text-align: center;\">40%</td>\n" +
                        "</tr>\n" +
                        "</tbody>\n" +
                        "</table>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p><span>*Throughout the semester, your tutorial TAs will monitor the individual contributions to group projects. The TA will calculate a contribution coefficient using these assessments, coding activities, and presentations. If all group members contribute equally, they will each receive a coefficient of 1. However, if a member's involvement differs significantly from their peers, their grade may be adjusted accordingly to reflect their contribution.</span></p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<h3><strong>Lectures</strong></h3>\n" +
                        "<h4>You are expected to keep up with the lecture slides and complete all term work. If you do so, then you should feel sufficiently prepared for the final exam. While participation isn't graded, we highly recommend attending lectures and actively participating in discussions on course topics throughout the semester.</h4>\n" +
                        "<ul>\n" +
                        "<li>Throughout the slides, you will see \"light bulbs\", which we have included to draw your attention to some of the most important concepts covered.</li>\n" +
                        "</ul>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<h3><strong>Tutorials/Labs</strong></h3>\n" +
                        "<p>These Thursday evening sessions feature tutorial instruction by your TAs and offer an opportunity to work on coursework and seek assistance to enhance your understanding. Attendance is crucial as it's where many project groups are formed and subsequently collaborate with their TAs on milestones.</p>\n" +
                        "<ul>\n" +
                        "<li>The terms \"lab\" and \"tutorial\" might be used interchangeably to mean the same sessions on<strong><span>&nbsp;</span>Thursdays from 6-8 PM</strong>.</li>\n" +
                        "<li>Lab numbering aligns with week numbers (e.g., Lab 1 = Week 1) and will begin after week 0 (i.e.,<span>&nbsp;</span><strong>no tutorials in week 0</strong>).</li>\n" +
                        "<li>Initial room assignments will be made at the beginning of Week 2.</li>\n" +
                        "<li>Please also use these opportunities to interact with your classmates and potential group members.</li>\n" +
                        "<li>Starting from Lab 5, your project group should be formed and remain in the same lab room with the same TA unless you're moved for balancing reasons.</li>\n" +
                        "<li>Later labs will primarily be co-working and TA consultation sessions for your projects.</li>\n" +
                        "<li><strong>The final session (Lab 11) is reserved for scheduled presentations of your projects.</strong></li>\n" +
                        "</ul>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<h3><strong>Textbook and Other Materials</strong></h3>\n" +
                        "<p>All course readings, assignments, and other important information will be available on Quercus. Please make sure to check it regularly.</p>\n" +
                        "<p>Most of the core design concepts discussed in this course can be found in&nbsp;<strong>Clean Architecture</strong>&nbsp;by <strong>Robert Martin</strong>. The textbook is optional but highly recommended. Past students have indicated that they found the textbook to be very useful. You may also find the following optional books to be interesting reads:</p>\n" +
                        "<ul>\n" +
                        "<li><strong>Effective Java</strong><span>&nbsp;</span>by <strong>Joshua Bloch</strong> (highly recommended if you plan to code more in Java beyond this course; its emphasis is on how you can best use Java — not learning the syntax)</li>\n" +
                        "<li><strong>Program Development in Java</strong><span>&nbsp;</span>by <strong>Barbara Liskov with John Guttag</strong> (takes a very formal approach to software design; in particular, their UI–FP (user interface – functional part) partitioning of the design of a system and their subsequent discussion fits well with our discussion of Clean Architecture in this course)</li>\n" +
                        "</ul>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<h3><strong>Piazza</strong></h3>\n" +
                        "<p>We will be using Piazza for class discussion. The system is highly catered to getting you help fast and efficiently from both classmates and instructors. Rather than emailing course content-specific questions to the teaching staff, we encourage you to post such questions on Piazza — don't be shy! If you have any problems or feedback for the developers, you can email <a href=\"mailto:team@piazza.com\">team@piazza.com</a>. From experience, they are very responsive and even open to implementing missing features!</p>\n" +
                        "<p>Find our class page at: <a href=\"https://piazza.com/utoronto.ca/summer2024/csc207h1y/info\">https://piazza.com/utoronto.ca/summer2024/csc207h1y/info</a></p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<h3><strong>Late Policy</strong></h3>\n" +
                        "<p>You are responsible for meeting all deadlines. No late work will be accepted, barring exceptional circumstances.</p>\n" +
                        "<p>If you’re experiencing illness, mental health crises, family/personal emergencies, or other exceptional circumstances beyond your control that prevent you from being able to complete an assessment on time, you can apply for special consideration by downloading and filling out the <a id=\"31693156\" class=\"instructure_file_link instructure_scribd_file inline_disabled\" title=\"Link\" href=\"https://q.utoronto.ca/courses/345741/files/31693156?verifier=k2jhcC98PZA6DOUWN1qqmEjSLfRDdbf26QKh4jQt&amp;wrap=1\" target=\"_blank\" data-canvas-previewable=\"true\" data-api-endpoint=\"https://q.utoronto.ca/api/v1/courses/345741/files/31693156\" data-api-returntype=\"File\">Special Consideration Form.pdf</a>&nbsp;and emailing it to&nbsp;<a href=\"mailto:csc207-2024-05@cs.toronto.edu\">csc207-2024-05@cs.toronto.edu</a> in advance of the due date (the sooner, the better). We'll make appropriate accommodations, for example, re-weighing missed term work.</p>\n" +
                        "<p>Requests can be made for any individual assessments, provided the reasons are acceptable. For example, if a quiz is available for three weeks, we would only provide an extension if you were sick for most of that timeframe (i.e. perhaps two weeks). In your project groups, everyone is collectively responsible for every piece of work, so the expectation is that the group tries to make up for exceptional circumstances. If this isn't possible, please contact your assigned TA.</p>\n" +
                        "<p>Please note that special consideration cannot be granted to accommodate full-time jobs, heavy course load, multiple assignments and/or tests scheduled during the same period, or challenges with time management.</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<h3><strong>Remark Requests</strong></h3>\n" +
                        "<p>All<span>&nbsp;</span>remark requests<span>&nbsp;</span>should be submitted through <a class=\"inline_disabled\" href=\"https://forms.office.com/r/dkKJSqTAaH\" target=\"_blank\">this form</a>. All remark requests will be handled before final course grades are submitted. You are responsible for submitting any such requests within two weeks of the work being returned.</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<h3><strong>Accessibility</strong></h3>\n" +
                        "<p>If you have an acute or ongoing disability issue or accommodation need, you should register with Accessibility Services (AS) at the beginning of the academic year by visiting<span>&nbsp;</span><a href=\"http://www.studentlife.utoronto.ca/as/new-registration\" target=\"_blank\">http://www.studentlife.utoronto.ca/as/new-registration</a><strong><a href=\"http://www.studentlife.utoronto.ca/as/new-registration\" target=\"_blank\"></a></strong>. Without registration, you will not be able to verify your situation with your instructors. AS will assess your situation, develop an accommodation plan with you, and support you in requesting accommodation for your coursework. Remember that the process of accommodation is private: AS will not share details of your needs or conditions with any instructor, and your instructors will not reveal that you are registered with AS. Please reach out to the course address if you have any questions or concerns.</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<h3><strong>Academic Integrity</strong></h3>\n" +
                        "<p>Please familiarize yourself with the Rules and Regulations from the U of T Calendar (especially the Code of Behaviour on Academic Matters):<span>&nbsp;</span><a class=\"inline_disabled\" href=\"http://www.artsci.utoronto.ca/osai\" target=\"_blank\">http://www.artsci.utoronto.ca/osai</a><strong>. </strong>All suspected cases of academic dishonesty will be investigated following procedures outlined in the&nbsp;<a href=\"https://governingcouncil.utoronto.ca/secretariat/policies/code-behaviour-academic-matters-july-1-2019\" target=\"_blank\">Code of Behaviour on Academic Matters (https://governingcouncil.utoronto.ca/secretariat/policies/code-behaviour-academic-matters-july-1-2019)</a>. If you have questions or concerns about what constitutes appropriate academic behavior or appropriate research and citation methods, please reach out to the teaching team. Note that you are expected to seek out additional information on academic integrity from the instructor or from other institutional resources. For example, to learn more about how to cite and use source material appropriately and for other writing support, see the U of T writing support website at&nbsp;<a href=\"http://www.writing.utoronto.ca/\" target=\"_blank\">http://www.writing.utoronto.ca</a>. Consult the Code of Behaviour on Academic Matters for a complete outline of the University's policy and expectations. For more information, please see&nbsp;<a href=\"https://www.artsci.utoronto.ca/current/academic-advising-and-support/student-academic-integrity\" target=\"_blank\">A&amp;S Student Academic Integrity (https://www.artsci.utoronto.ca/current/academic-advising-and-support/student-academic-integrity)</a>&nbsp;and the&nbsp;<a href=\"https://www.academicintegrity.utoronto.ca/\" target=\"_blank\">University of Toronto Website on Academic Integrity (https://www.academicintegrity.utoronto.ca)</a>.</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<h3><strong>Use of Generative AI</strong></h3>\n" +
                        "<p><span class=\"textLayer--absolute\" dir=\"ltr\" role=\"presentation\">The use of Generative AI is allowed throughout the course, and it will not be required to complete any aspect of this course. With this said, we<span>&nbsp;</span></span><span class=\"textLayer--absolute\" dir=\"ltr\" role=\"presentation\">cau</span><span class=\"textLayer--absolute\" dir=\"ltr\" role=\"presentation\">tion you not to rely entirely on these tools to complete your coursework. Instead, we recommend<span>&nbsp;</span></span><span class=\"textLayer--absolute\" dir=\"ltr\" role=\"presentation\">treating the use of generative AI as a supplementary tool only for exploration and engaging with the course material.</span><span>&nbsp;</span><span class=\"textLayer--absolute\" dir=\"ltr\" role=\"presentation\">Ultimately, you<span>&nbsp;</span></span><span class=\"textLayer--absolute\" dir=\"ltr\" role=\"presentation\">(and not any AI tool) are responsible for your own lear</span><span class=\"textLayer--absolute\" dir=\"ltr\" role=\"presentation\">ning in this course and for all the work you<span>&nbsp;</span></span><span class=\"textLayer--absolute\" dir=\"ltr\" role=\"presentation\">submit for credit.</span><span>&nbsp;</span><span class=\"textLayer--absolute\" dir=\"ltr\" role=\"presentation\">It is your responsibility to critically evaluate the content generated, and to regularly<span>&nbsp;</span></span><span class=\"textLayer--absolute\" dir=\"ltr\" role=\"presentation\">assess your own learning independent of generative AI tools. Over-reliance on generative AI may give<span>&nbsp;</span></span><span class=\"textLayer--absolute\" dir=\"ltr\" role=\"presentation\">yo</span><span class=\"textLayer--absolute\" dir=\"ltr\" role=\"presentation\">u a false sense of how much you’ve actually learned, which can lead to poor performance on the<span>&nbsp;</span></span><span class=\"textLayer--absolute\" dir=\"ltr\" role=\"presentation\">midterm test or final exam, in later courses, or in future work or studies after graduation.</span></p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<h3><strong><span class=\"textLayer--absolute\" dir=\"ltr\" role=\"presentation\">Mental Health and Well-being</span></strong></h3>\n" +
                        "<div class=\"ng-scope\" data-ng-include=\"'app/ajs/cis-frame/partials/header-partial.html'\">\n" +
                        "<div id=\"cis-main-content\" class=\"ng-scope\" role=\"main\" data-cis-main-content=\">\n" +
                        "<div class=\"cis-main-content role-instructor\" data-ng-class=\"{ 'role-instructor': CisUserRoleHelper.isInstructor(), 'app-dashboard-view': $ctrl.isViewType(CisCourseViewTypes.DASHBOARD_VIEW) }\">\n" +
                        "<div class=\"application-body-view ng-scope application-background\" data-ui-view=\" data-ng-class=\"{'application-background': showBackground}\">\n" +
                        "<div class=\"cis-syllabi-shell ng-scope\">\n" +
                        "<div>\n" +
                        "<div class=\"ng-scope\" data-ui-view=\"syllabiBuilderViewport\">\n" +
                        "<div class=\"ng-scope ng-isolate-scope\" data-cis-syllabi-builder-view=\" data-course-group=\"SyllabiBuilderCourse.courseGroup\">\n" +
                        "<div class=\"cis-syllabi-view cis-course-view\">\n" +
                        "<div>\n" +
                        "<div class=\"wizard-container\">\n" +
                        "<div class=\"wizard-step-content\">\n" +
                        "<div class=\"wizard-step-content-inner-wrapper ng-star-inserted\">\n" +
                        "<div class=\"selected-statement-accordion ng-star-inserted\">\n" +
                        "<div>\n" +
                        "<div id=\"cdk-accordion-child-9\" class=\"mat-expansion-panel-content ng-tns-c51-26 ng-trigger ng-trigger-bodyExpansion\" role=\"region\" aria-labelledby=\"mat-expansion-panel-header-9\">\n" +
                        "<div class=\"mat-expansion-panel-body ng-tns-c51-26\">\n" +
                        "<div class=\"template-body ng-tns-c51-26\">\n" +
                        "<div class=\"statementDesc ng-star-inserted\">As a student, you may experience challenges that can interfere with learning, such as strained relationships, increased anxiety, substance use, feeling down, difficulty concentrating and/or lack of motivation, financial concerns, family worries, and so forth. These factors may affect your academic performance and/or reduce your ability to participate fully in daily activities. Everyone feels stressed now and then – it is a normal part of university life. Some days are better than others, and there is no wrong time to reach out. There are resources for every situation and every level of stress.<br>There are many helpful resources available through your College Registrar or through Student Life (<a href=\"http://studentlife.utoronto.ca/\" target=\"_blank\">http://studentlife.utoronto.ca</a><span>&nbsp;</span>and<span>&nbsp;</span><a href=\"http://www.studentlife.utoronto.ca/feeling-distressed\" target=\"_blank\">http://www.studentlife.utoronto.ca/feeling-distressed</a>). An important part of the University experience is learning how and when to ask for help. Please take the time to inform yourself of available resources.</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "<div class=\"ng-scope\" data-ng-include=\"'app/ajs/cis-frame/partials/footer-partial.html'\">\n" +
                        "<div class=\"application-footer ng-scope\">\n" +
                        "<div class=\"application-footer-content\"><i class=\"fa fa-copyright\" aria-hidden=\"true\"></i></div>\n" +
                        "</div>\n" +
                        "</div>",
                "345741",
                true);

        LocalDateTime expectedDueDate = LocalDateTime.of(2024,
                6,
                13,
                18,
                0,
                0);

        Assignment expectedAssignment = new Assignment("0", "Midterm Exam", expectedDueDate, syllabus.courseId);
        int count = 0;

        List<Assignment> assignmentsActual = converter.getAssignments(syllabus);
        for (Assignment assignmentActual : assignmentsActual) {
            System.out.println("Assignment Name: " + assignmentActual.getName());
            if (assignmentActual.getDueDate().toLocalDate() == expectedAssignment.getDueDate().toLocalDate() &&
                    Objects.equals(assignmentActual.getCourseId(), expectedAssignment.getCourseId())) {
                count++;
            }
            System.out.println(assignmentActual.getDueDate());
            System.out.println(assignmentActual.getCourseId());
        }

        Assertions.assertEquals(1, count);
    }
}