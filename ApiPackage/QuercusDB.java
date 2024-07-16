package ApiPackage;

import CalenderPackage.Course;
import CalenderPackage.Assignment;
import ApiPackage.SyllabusConversion;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class QuercusDB implements UserDB{
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

            if (responseBody.getInt("status_code") == 200) {
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
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course[] getCourses() {
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

            if (responseBody.getInt("status_code") == 200) {
                int numCourses = responseBody.length();
                Course[] courses = new Course[numCourses];

                for(int i = 0; i < numCourses; i++;){
                    JSONObject curCourse = responseBody.getJSONObject
                    courses[i] = new Course()
                }

                return courses;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private RawSyllabus getSyllabus(String courseId) {
        return null;
    }
}