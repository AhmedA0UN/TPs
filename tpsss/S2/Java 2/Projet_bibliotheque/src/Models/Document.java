package Models;

public class Document {
	private int id;
	private String titre;
	private String auteur;
	private String desc;
	private Boolean dis;
	private String type;
	
	
	public Document(int id ,String ti , String a , String de , Boolean di , String t)
	{
		this.setId(id);
		this.setTitre(ti) ;
		this.setAuteur(a) ; 
		this.setDesc(de) ;
		this.setDis(di) ;
		this.setType(t) ;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getAuteur() {
		return auteur;
	}


	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public Boolean getDis() {
		return dis;
	}


	public void setDis(Boolean dis) {
		this.dis = dis;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
}

