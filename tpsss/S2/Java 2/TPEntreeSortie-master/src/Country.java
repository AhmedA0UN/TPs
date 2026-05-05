public class Country implements java.io.Serializable {
	private String key, name, capital;
	private int area, population;

	public Country(String key) {
		this.key = key;
	}

	public Country(String key, String name, String cap, int area, int pop) {
		this.key = key;
		this.name = name;
		this.capital = cap;
		this.area = area;
		this.population = pop;
	}

	public int getArea() {
		return area;
	}

	public String getCapital() {
		return capital;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public int getPopulation() {
		return population;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPopulation(int population) {
		this.population = population;
	}


	public String toString() {
        return key + ":\tNom : " + name + "\n\tCapitale : " + capital + "\n\tSuperficie : " + area + "\n\tPopulation : "
				+ population;
	}
}
