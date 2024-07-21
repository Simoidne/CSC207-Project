package src.Data_Access;

import src.Entities.Course;
import src.Entities.Assignment;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import src.Entities.CourseModule;
import src.Entities.RawSyllabus;
import src.Use_Case_Interactor.SyllabusConversion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuercusDB implements UserDB {
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    @Override
    public Course getCourse(String courseId) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://q.utoronto.ca/api/v1/courses/%s?", courseId))
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                String courseName = responseBody.getString("name");

                //Finding and Converting the syllabus
                SyllabusConversion syllabusConverter = new SyllabusConversion();
                RawSyllabus syllabus = this.getSyllabus(courseId);
                List<Assignment> assignments = syllabusConverter
                        .getAssignments(syllabus);

                //Setting the assignments found in syllabus into the course class
                Course course  = new Course(courseId, courseName);
                if (syllabus.syllabusFound){
                    course.setAssignments(assignments);
                }
                return course;
            } else {
                throw new RuntimeException(response.message());
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> getCourses() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://q.utoronto.ca/api/v1/courses?enrollment_state=active")
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONArray responseBody = new JSONArray(response.body().string());

            if (response.code() == 200) {
                return this.compileCourses(responseBody);
            } else {
                throw new RuntimeException(response.message());
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


    //Getting Syllabus Stuff Below
    private RawSyllabus getSyllabus(String courseId) {
        boolean syllabusFound = true;
        String dataFormat = "";
        String syllabusData = "";
        try {
            syllabusData = this.getSyllabusHTML(courseId);
            dataFormat = "html";
        } catch (SyllabusNotFoundException e) {
            //Continue to search modules
            try {
                this.getSyllabusFile(courseId);
            } catch (SyllabusNotFoundException e1) {
                syllabusFound = false;
            }
        }
        return new RawSyllabus(dataFormat, syllabusData, courseId, syllabusFound);
    }

    //A method which find a syllabus in HTML form directly from the course. If it doesn't find one,
    //it throws a SyllabusNotFoundException
    private String getSyllabusHTML(String courseId) throws SyllabusNotFoundException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://q.utoronto.ca/api/v1/courses/%s?include[]=syllabus_body", courseId))
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                //Check if the syllabus was found
                if (!responseBody.has("syllabus_body")) {
                    throw new SyllabusNotFoundException();
                }
                return responseBody.getString("syllabus_body");
            } else {
                throw new RuntimeException(response.message());
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    //A method which finds a syllabus attached to a module. Then it will return a file.
    //If a syllabus is not found, a SyllabusNotFoundException is thrown.
    private File getSyllabusFile(String courseId) throws SyllabusNotFoundException {
        throw new SyllabusNotFoundException();
    }

    //This is a method which returns all modules attached to a course given a courseId.
    private List<CourseModule> getCourseModules(String courseId) {
        return null;
    }

    //This is a method which gets a list of Courses
    //given a JSONArray of JSONObject formated courses from the API
    private List<Course> compileCourses(JSONArray courses) throws JSONException {
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < courses.length(); i++) {
            JSONObject course = courses.getJSONObject(i);
            courseList.add(this.getCourse(course.getString("id")));
        }
        return courseList;
    }
}