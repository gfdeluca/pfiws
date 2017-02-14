package ar.com.uade.pfi.dao.dbo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Codons")
public class CodonEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idCodon")
	Long idCodon;

	@Column(name = "name", length = 3)
	String name;
	
	@Column(name = "aminoacidFullName", length = 100)
	String aminoacidFullName;

	@Column(name = "aminoacidShotName", length = 3)
	String aminoacidShotName;
	
	@Column(name = "aminoacidLetter", length = 1)
	String aminoacidLetter;
	
	public CodonEntity(Long id, String name, String aminoacidFullName, String aminoacidShotName,
			String aminoacidLetter) {
		this.idCodon = id;
		this.name = name;
		this.aminoacidFullName = aminoacidFullName;
		this.aminoacidShotName = aminoacidShotName;
		this.aminoacidLetter = aminoacidLetter;
	}

	public CodonEntity() {
		
	}

	public Long getId() {
		return idCodon;
	}

	public void setId(Long id) {
		this.idCodon = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAminoacidFullName() {
		return aminoacidFullName;
	}

	public void setAminoacidFullName(String aminoacidFullName) {
		this.aminoacidFullName = aminoacidFullName;
	}

	public String getAminoacidShotName() {
		return aminoacidShotName;
	}

	public void setAminoacidShotName(String aminoacidShotName) {
		this.aminoacidShotName = aminoacidShotName;
	}

	public String getAminoacidLetter() {
		return aminoacidLetter;
	}

	public void setAminoacidLetter(String aminoacidLetter) {
		this.aminoacidLetter = aminoacidLetter;
	}
}
