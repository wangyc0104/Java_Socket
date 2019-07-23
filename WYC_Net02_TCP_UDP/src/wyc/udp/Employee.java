package wyc.udp;

/**
 * 雇员类（需要序列化）
 * @author 王以诚
 */
public class Employee implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private transient String name; // 该数据不需要序列化
	private double salary;

	public Employee() { }

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}