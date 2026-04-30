package Exercice3;

public class ArticleFragile extends Article {


double prixEmballage;
String emballage;
public ArticleFragile(int Code, String Ref, double PHT, double Qte, double
TVA,String emballage, double prixEmballage)
{
super(Code, Ref,PHT, Qte, TVA);
this.emballage=emballage;
this.prixEmballage=prixEmballage;
}
public double prixTransport()
{
return 2*super.prixTransport();
}

public double prixNet(double fraisDouane)
{
return super.prixNet(fraisDouane) +

prixEmballage*super.getQte();
}
public String toString()
{
	return super.toString()+ " " +this.emballage+ " "+this.prixEmballage;
}
}
