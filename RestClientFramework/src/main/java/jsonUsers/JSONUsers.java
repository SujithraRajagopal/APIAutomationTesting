package jsonUsers;

public class JSONUsers {

	String Name;
	String Job;
	String createdAt;
	int id;

	public JSONUsers() {

	}

	public JSONUsers(String Name, String Job) {

		this.Name = Name;
		this.Job = Job;

	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getJob() {
		return Job;
	}

	public void setJob(String job) {
		Job = job;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
