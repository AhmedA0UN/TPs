package Exercice2;
public class CalcFactorielle {
	public static int fact(int k)throws ExceptionNegatif  {      // calcule k!
		if(k<0)
			throw new ExceptionNegatif("l'entier est negatif");
		else
		{
		int f=1;          // f=k!
		for (int i=1; i<=k; i++)
		f=f*i;
		return f;
		}
		 }

public static void main(String[] args)
{      
	try
     {
       int n=fact(Integer.parseInt(args[0]));
       System.out.println(Integer.parseInt(args[0])+"!"+"="+n);

     }
	catch(ExceptionNegatif e)
	{
		System.out.println(e.getMessage());
	}
	catch(NumberFormatException e)
	{
		System.out.println("la valeur doit etre un entier");
	}
	catch(ArrayIndexOutOfBoundsException e)
	{
		System.out.println("Manque d'arguments sur la ligne de commande");
	}
	
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	
}
	
}


