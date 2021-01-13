package model;


public class Meal {
	
	private int id;
	private int cost;
	private String name;
	private String type;
	public Meal(int id, String name, String type, int cost) {
		super();
		this.id = id;
		this.cost = cost;
		this.name = name;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

	
}
