package Models;


public class Emprunt {
	private int id;
	private int id_adherent;
	private int id_document;
	private String date_emp;
	private String date_retour_p;
	private String date_retour_r;
	private String status;
	
	public Emprunt(int id ,int ida , int idd , String de ,String drp , String drr , String s)
	{
		this.setId(id);
		this.setId_adherent(ida) ;
		this.setId_document(idd);
		this.setDate_emp(de);
		this.setDate_retour_p(drp);
		this.setDate_retour_r(drr);
		this.setStatus(s) ;
	}
	
	public Emprunt()
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_adherent() {
		return id_adherent;
	}

	public void setId_adherent(int id_adherent) {
		this.id_adherent = id_adherent;
	}

	public int getId_document() {
		return id_document;
	}

	public void setId_document(int id_document) {
		this.id_document = id_document;
	}

	public String getDate_emp() {
		return date_emp;
	}

	public void setDate_emp(String date_emp) {
		this.date_emp = date_emp;
	}

	public String getDate_retour_p() {
		return date_retour_p;
	}

	public void setDate_retour_p(String date_retour_p) {
		this.date_retour_p = date_retour_p;
	}

	public String getDate_retour_r() {
		return date_retour_r;
	}

	public void setDate_retour_r(String date_retour_r) {
		this.date_retour_r = date_retour_r;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

