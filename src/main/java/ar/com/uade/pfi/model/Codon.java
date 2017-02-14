package ar.com.uade.pfi.model;

public interface Codon {

	boolean isNeighbour(Codon c);

	String toString();

	char getFisrtNucleotide();

	void setFisrtNucleotide(char fisrtNucleotide);

	char getSecondNucleotide();

	void setSecondNucleotide(char secondNucleotide);

	char getThirdNucleotide();

	void setThirdNucleotide(char thirdNucleotide);

	double getPa();

	void setPa(double pa);

}