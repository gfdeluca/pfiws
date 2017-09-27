package ar.com.uade.pfi.dao.dbo.entities;

public class CodonEntity {
	Integer id;
	String name;
	String aminoacid;
	String aminoacidLetter;
	
	public CodonEntity() {}
	
	public CodonEntity(Integer id, String name, String aminoacid, String aminoacidLetter) {
		this.id = id;
		this.name = name;
		this.aminoacid = aminoacid;
		this.aminoacidLetter = aminoacidLetter;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAminoacid() {
		return aminoacid;
	}

	public void setAminoacid(String aminoacid) {
		this.aminoacid = aminoacid;
	}

	public String getAminoacidLetter() {
		return aminoacidLetter;
	}

	public void setAminoacidLetter(String aminoacidLetter) {
		this.aminoacidLetter = aminoacidLetter;
	}
}
