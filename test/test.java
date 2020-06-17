import com.tm.orm.session.*;

public class test
{
public static void main(String args[]) throws Exception {
 TMSession t = new TMSession();
 t.setDatabase("shivamdb");
 t.setUsername("shivam");
 t.setPassword("gupta");
 System.out.println(t.getQueryString());
 System.out.println(t);
 t.save();
 TMSession t2 = new TMSession("session.txt");
 System.out.println("T2 IS: ");
 System.out.println(t2);
 t2.setUsername("shyam");
 t2.save();
}
}