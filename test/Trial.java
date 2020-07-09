import com.tm.orm.annotation.*;

@MapTable("test")
public class Trial {

@MapColumn("header")
public int header;
@MapColumn("footer")
public String footer;

}