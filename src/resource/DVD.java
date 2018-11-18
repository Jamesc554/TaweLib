package resource;
/* need to add class hierarchy*/
import java.util.ArrayList;

public class DVD extends Resource {
	private String director;
	private String runtime;
	private String language;
	ArrayList<String> subLang = new ArrayList<String>();
	
	//constructors with optional attributes
	//not sure if ArrayList is right
	public DVD (String director, String runtime) {
		
	}
	public DVD (String director, String runtime, String language, ArrayList<String> subLang) {
		
	}
	public DVD (String director, String runtime, String language) {
		
	}
	public DVD (String director, String runtime, ArrayList<String> subLang) {
		
	}
	public String getDirector(){
		return director;
	}
	public String getRuntime() {
		return runtime;
	}
	public String getLanguage() {
		return language;
	}
	// not 100% sure this works
	public ArrayList<String> getSubLang(){
		return subLang;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public void setRuntime (String runtime) {
		this.runtime = runtime;
	}
	public void setLanguage (String language) {
		this.language = language;
	}
	
}
