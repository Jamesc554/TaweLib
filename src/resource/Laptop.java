package resource;

public class Laptop extends Resource {

	private String manufacturer;
	private String model;
	private String operatingSys;
	
	//constructor for laptop - no optional attributes
	public Laptop(String manufacturer, String model,  String operatingSys) {
	}
	
	//getters and setters
	public String getManufacturer() {
		return manufacturer;
	}
	public String getModel() {
		return model;
	}
	public String getOperatingSys() {
		return operatingSys;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setOperatingsys(String operatingSys) {
		this.operatingSys = operatingSys;
	}
	
}
