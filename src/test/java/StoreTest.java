import java.util.Arrays;
import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;

public class StoreTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Store.all().size(), 0);
  }

  @Test
  public void stores_haveName() {
    Store store = new Store("Barneys New York", "660 Madison Ave, New York, NY 10065", "212 826 8900");
    store.save();
    assertEquals("Barneys New York", Store.find(store.getId()).getName());
  }

  // @Test
  // public void student_lastFirst_works() {
  //   Student student = new Student("Max Power");
  //   assertEquals("Power, Max", student.getLastFirst());
  // }
  //
  // @Test
  // public void students_haveEnrollmentDate() {
  //   Student student = new Student("Max Power");
  //   student.save();
  //   student.setEnrollmentDate("2016-01-26");
  //   assertEquals("2016-01-26", Student.find(student.getId()).getEnrollmentDate());
  // }
  //
  // @Test
  // public void students_haveAMajorDepartment() {
  //   Student student = new Student("Max Power");
  //   student.save();
  //   Department department = new Department("History", "HST");
  //   department.save();
  //   student.setMajor(department.getId());
  //   assertEquals(department.getId(), student.getDepartmentId());
  // }
  //
  // @Test
  // public void student_deleteWorksProperly() {
  //   Student student = new Student("Lil' Jimmy");
  //   student.save();
  //   student.delete();
  //   assertEquals(Student.all().size(), 0);
  // }
  //
  // @Test
  // public void student_updateWorksProperly() {
  //   Student student = new Student("Lil' Jimmy");
  //   student.save();
  //   student.update("Jim Everyman");
  //   assertEquals(student.getName(), "Jim Everyman");
  //   assertEquals(Student.find(student.getId()).getName(), "Jim Everyman");
  // }
  //
  // @Test
  // public void equals_returnsTrueIfSameNameAndEnrollmentDate() {
  //   Student firstStudent = new Student("Lil' Jimmy");
  //   firstStudent.save();
  //   Student secondStudent = new Student("Lil' Jimmy");
  //   secondStudent.save();
  //   firstStudent.setEnrollmentDate("2016-01-26");
  //   secondStudent.setEnrollmentDate("2016-01-26");
  //   assertTrue(firstStudent.equals(secondStudent));
  // }
  //
  // @Test
  // public void getAllCourses_returnsAllCoursesStudentHasTaken() {
  //   Student student = new Student("Bill Coursetaker");
  //   student.save();
  //   Course firstCourse = new Course(1, 101, "Intro to Programming");
  //   Course secondCourse = new Course(1, 102, "Advanced Programming");
  //   firstCourse.save();
  //   secondCourse.save();
  //   student.enrollIn(firstCourse.getId());
  //   student.enrollIn(secondCourse.getId());
  //   student.passCourse(firstCourse.getId());
  //   Course[] courses = new Course[] {firstCourse, secondCourse};
  //   assertTrue(student.getAllCourses().containsAll(Arrays.asList(courses)));
  // }
}
