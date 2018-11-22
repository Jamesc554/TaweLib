package resources;
/**
 * <h1>Laptop</h1>
 * <p>The laptop class creates laptop objects to be used by other classes
 * @author Deyan Naydenov
 * @version 0.0.0.1
 * @since 18/11/2018
 */
public class Laptop {

	private String manufacturer;
	private String model;
	private String operatingSys;
	
	/**
	 * Constructor used to create laptop objects.
	 * @param manufacturer
	 *  Who manufactured the laptop.
	 * @param model
	 *  What the laptop's model is.
	 * @param operatingSys
	 * The laptop's operating system
	 */
	public Laptop(String manufacturer, String model,  String operatingSys) {
	}
	
	/**
	 * Finds the manufacturer of the laptop
	 * @return manufacturer 
	 */
	public String getManufacturer() {
		return manufacturer;
	}
	/**
	 * Finds the model of the laptop
	 * @return model 
	 */
	public String getModel() {
		return model;
	}
	/**
	 * Finds the operating system of the laptop
	 * @return operatingSys 
	 */
	public String getOperatingSys() {
		return operatingSys;
	}
	/**
	 * Defines a manufacturer for the laptop.
	 * @param manufacturer
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	/**
	 * Defines a model for the laptop.
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * Defines an operating system for the laptop.
	 * @param operatingSys
	 */
	public void setOperatingsys(String operatingSys) {
		this.operatingSys = operatingSys;
	}
	
}
