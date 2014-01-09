package com.maximilianboehm.uni.rgb.ad;


public class Gemuese {
	
	public static Gemuese createGemuese(int n, int nN, int nG) {
		Gemuese g= new Gemuese();
		g.setNaehrwert(nN);
		g.setGroesse(nG);
		g.setNummer(n);
		return g;
	}

	int Nummer;
	public int getNummer() {
		return Nummer;
	}
	public void setNummer(int nummer) {
		Nummer = nummer;
	}

	int Naehrwert;
	int Groesse;
	
	public int getNaehrwert() {
		return Naehrwert;
	}
	public void setNaehrwert(int naehrwert) {
		Naehrwert = naehrwert;
	}
	public int getGroesse() {
		return Groesse;
	}
	public void setGroesse(int groesse) {
		Groesse = groesse;
	}
	
}
