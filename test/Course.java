import com.tm.orm.annotation.*;

@MapTable("course")
public class Course {

	@MapColumn("cname")
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}