package Exercice3;

public class Article implements Exportable {

	private int Code;
	private String Ref;
	private double Qte;
	private double PHT;
	private double TVA;
	public Article(int Code, String Ref)
	{
	this.Code=Code;
	this.Ref=Ref;
	}
	public Article(int Code, String Ref, double PHT, double Qte, double TVA)
	{
	this(Code,Ref);
	this.PHT=PHT;
	this.Qte=Qte;
	this.TVA=TVA;
	}
	public String toString()
	{
	return Code +" "+Ref +" "+PHT+" "+Qte+" "+TVA;
	}
	public int getCode() {
	return Code;
	}
	public void setCode(int code) {
	Code = code;
	}
	public String getRef() {
	return Ref;
	}
	public void setRef(String ref) {
	Ref = ref;
	}
	public double getPHT() {
	return PHT;
	}
	public void setPHT(double pHT) {
		PHT = pHT;
	}
	public double getQte() {
	return Qte;
	}
	public void setQte(double qte) {
	Qte = qte;
	}
	public double getTVA() {
	return TVA;
	}
	public void setTVA(double tVA) {
	TVA = tVA;
	}
	public double prixTransport()
	{
	return 2*PHT;
	}
	public double prixNet(double fraisDouane)
	{
	return this.Qte*this.PHT*this.TVA+this.Qte*this.PHT+this.Qte*this.prixTransport() +
	defaultMethod(fraisDouane,this.Qte);
	}
	public static void afficherMontant(double montant)
	{
	System.out.println("Montant est : "+montant);
	}
	
	}


