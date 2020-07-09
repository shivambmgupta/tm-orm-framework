import com.tm.orm.session.*;
import com.tm.orm.handler.*;
import java.math.*;
import java.util.*;

public class test
{
public static void main(String args[]) throws Exception 
{
 Student s = new Student();
TMORMapper orm = TMORMapper.getInstance();

s.setClazz("8A");
s.name = "Shivam Gupta";
s.gender = "Male";
s.setPercentage(new BigDecimal(90.0));
s.setPancard(args[0]);
s.age = 70;
s.setCourseID(9);
s.departmentID = 3;
s.setIsIndian(true);
s.setDateOfBirth(new Date());
/*
Course c = new Course();
c.setName("English");
Trial t = new Trial();
t.header = 5;
t.footer = "Hello";
*/

orm.begin();

System.out.printf("\nFor: orm.select(Student.class).query()\n");
List<Student> list = orm.select(Student.class).query();
//System.out.println(list);


System.out.printf("\nFor: orm.select(Student.class).orderBy(\"dateOfBirth\").ascending().query()\n");
list = orm.select(Student.class).orderBy("dateOfBirth").ascending().query();
//System.out.println(list);

System.out.printf("\nFor: orm.select(Student.class).orderBy(\"name\").descending().query()\n");
list = orm.select(Student.class).orderBy("name").descending().query();
//System.out.println(list);

System.out.printf("\nFor: orm.select(Student.class).orderBy(\"name\").ascending().orderBy(\"rollNumber\").descending().query()\n");
list = orm.select(Student.class).orderBy("name").ascending().orderBy("rollNumber").descending().query();
//System.out.println(list);

System.out.printf("\nFor: orm.select(Student.class).where(\"age\").le(21).orderBy(\"pancard\").descending().query()\n");
list = orm.select(Student.class).where("age").le(21).orderBy("pancard").descending().query();
//System.out.println(list);

System.out.printf("\nFor: orm.select(Student.class).where(\"isIndian\").eq(true).query()\n");
list = orm.select(Student.class).where("isIndian").eq(true).query();
//System.out.println(list);

System.out.printf("\nFor: orm.select(Student.class).where(\"rollNumber\").gt(100).query()\n");
list = orm.select(Student.class).where("rollNumber").gt(100).query();
//System.out.println(list);

System.out.printf("\nFor: orm.select(Student.class).where(\"rollNumber\").gt(100).and(\"gender\").eq(\"Male\").orderBy(\"gender\").descending().query()\n");
list = orm.select(Student.class).where("rollNumber").gt(100).and("gender").eq("Male").orderBy("gender").descending().query();
//System.out.println(list);

System.out.printf("\nFor: orm.select(Student.class).where(\"rollNumber\").gt(100).or(\"gender\").eq(\"Male\").orderBy(\"gender\").descending().query()\n");
list = orm.select(Student.class).where("rollNumber").gt(100).or("gender").eq("Male").orderBy("gender").descending().query();

orm.commit();
	
//orm.remove(c);
//orm.commit();
//System.out.println("Course removed: " + c.getId() + ", " + c.getName());
}
}