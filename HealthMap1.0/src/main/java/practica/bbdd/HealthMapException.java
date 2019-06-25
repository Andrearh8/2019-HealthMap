package practica.bbdd;

public class HealthMapException extends RuntimeException {
	public HealthMapException(){
		super();
	}
	public HealthMapException(String err){
		super("\nError en HealthMap:\n\t"+err); 
	}
}
