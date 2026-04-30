package Exercice3;

public class Vente {

public static void main (String[] args)
{
  Article obj1= new Article(11,"papier");
  obj1.setPHT(Double.parseDouble(args[0]));
  obj1.setQte(Double.parseDouble(args[1]));
  obj1.setTVA(Double.parseDouble(args[2]));
System.out.println("Caractéristiques de l'article :"+obj1);
Article.afficherMontant(obj1.prixTransport());
Exportable.afficherMontant(obj1.prixTransport());
System.out.println("Prix Net de l'article : "+obj1.prixNet(450));
java.util.Scanner sc = new java.util.Scanner(System.in);
System.out.println("Entrez le nom de l'emballage : ");
String name = sc.next();
System.out.println("Entrez le prix de l'emballage : ");
double moy = sc.nextDouble();
ArticleFragile obj2= new ArticleFragile(obj1.getCode(),obj1.getRef(),obj1.getPHT(),obj1.getQte(),obj1.getTVA(),name,moy);

System.out.println("Caractéristiques de l'article fragile :"+obj2);
System.out.println("Prix Net de l'article fragile :"+obj2.prixNet(450));
sc.close();
}
}