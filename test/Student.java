import java.util.*;
import java.math.*;
import com.tm.orm.annotation.*;
import java.text.*;

@MapTable("student")
public class Student {
	
	@MapColumn("roll_number")
	@AutoGenerate
	@PrimaryKey 
	private int rollNumber;

	@MapColumn("name")
	public String name;

	@MapColumn("gender")
	public String gender;

	@MapColumn("class")
	@PrimaryKey
	private String clazz;

	@MapColumn("pancard")
	private String pancard;

	@MapColumn("age")
	public int age;

	@MapColumn("course_id")
	@ForeignKey
	private int courseID;

	@MapColumn("department_id")
	@ForeignKey
	public int departmentID;

	@MapColumn("percentage")
	private BigDecimal percentage;

	@MapColumn("indian")
	private boolean isIndian;

	@MapColumn("dob")
	public Date dateOfBirth;


	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public int getRollNumber() {
		return this.rollNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return this.gender;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getClazz() {
		return this.clazz;
	}

	public void setPancard(String pancard) {
		this.pancard = pancard;
	}

	public String getPancard() {
		return this.pancard;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getCourseID() {
		return this.courseID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public int getDepartmentID() {
		return this.departmentID;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public BigDecimal getPercentage() {
		return this.percentage;
	}

	public void setIsIndian(boolean isIndian) {
		this.isIndian = isIndian;
	}

	public boolean getIsIndian() {
		return this.isIndian;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("rollNumber: " + rollNumber + "    ");
		stringBuilder.append("name: " + name + "    ");
		stringBuilder.append("gender: " + gender + "    ");
		stringBuilder.append("clazz: " + clazz + "    ");
		stringBuilder.append("pancard: " + pancard + "    ");
		stringBuilder.append("age: " + age + "    ");
		stringBuilder.append("courseID: " + courseID + "    ");
		stringBuilder.append("departmentID: " +departmentID+ "    ");
		stringBuilder.append("percentage: " + percentage + "    ");
		stringBuilder.append("isIndian: " + isIndian + "    ");
		stringBuilder.append("dateOfBirth: " + dateOfBirth + ",           ");

		return stringBuilder.toString();
	}

}

