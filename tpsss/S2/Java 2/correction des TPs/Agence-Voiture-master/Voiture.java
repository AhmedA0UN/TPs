public class Voiture {
	
	private int immariculation;
	private String marque;
	private float prixLocation;
	public Voiture(int immariculation, String marque,
	float prixLocation) {
		this.immariculation = immariculation;
		this.marque = marque;
		this.prixLocation = prixLocation;
	}
	public int getImmariculation() {
	 return immariculation;
	}
	public void setImmariculation(int immariculation) {
	 this.immariculation = immariculation;
	}
	public String getMarque() {
	 return marque;
	}
	public void setMarque(String marque) {
	 this.marque = marque;
	}
	public float getPrixLocation() {
	 return prixLocation;
	}
	public void setPrixLocation(float prixLocation) {
	 this.prixLocation = prixLocation;
	}
	@Override
	public int hashCode() {
		//a ? b : c
		//opérateur conditionnel : renvoie la valeur b ou c selon l'évaluation de l'expression a (si a alors b sinon c) : b et c doivent retourner le même type
		int result = 17;
		  result = 31 * result + (marque == null ? 0 : marque.hashCode());
		  result = 31 * result + immariculation;
		  return result;
	}
	@Override
	public boolean equals(Object obj) {
	
		if (obj instanceof Voiture)
		{
		  Voiture v=(Voiture)obj;
		  return  immariculation==v.immariculation && marque.equals(v.marque);
		}
		return false;
		
	}
	@Override
	public String toString(){
	
		return "Voiture {" + "Immatriculation: " + immariculation + " Marque: " + marque + " Prix: " + prixLocation + "}";
		}
}
