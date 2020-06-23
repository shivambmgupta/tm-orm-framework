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
orm.begin();

s.setClazz("8A");
s.name = "Shivam Gupta";
s.gender = "Male";
s.setPercentage(new BigDecimal(90.0));
s.setPancard(args[0]);
s.age = 13;
s.setCourseID(6);
s.departmentID = 3;
s.setIsIndian(true);
s.setDateOfBirth(new Date());

orm.save(s);
orm.commit();
System.out.println("Roll Number added: " + s.getRollNumber());
	
orm.remove(s);
orm.commit();
System.out.println("Roll Number removed: " + s.getRollNumber());
}
}