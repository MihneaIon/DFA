package tema3;

public class Tranzitii {
	private String stanga;
	private String simbol;
	private String dreapta;
	
	
	public String getSimbol() {
		return simbol;
	}
	public void setSimbol(String simbol) {
		this.simbol = simbol;
	}
	public String getStanga() {
		return stanga;
	}
	public void setStanga(String stanga) {
		this.stanga = stanga;
	}
	public String getDreapta() {
		return dreapta;
	}
	public void setDreapta(String dreapta) {
		this.dreapta = dreapta;
	}
	
	public Tranzitii ()
	{
		stanga=null;
		simbol=null;
		dreapta=null;
	}
	
	public Tranzitii(String stanga ,  String simbol , String dreapta )
	{
		this.stanga=stanga;
		this.simbol=simbol;
		this.dreapta=dreapta;
	}
	
	public void afisez()
	{
		System.out.println("("+stanga + " " + simbol + " " + dreapta +")");
	}

}
