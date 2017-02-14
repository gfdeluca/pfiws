package ar.com.uade.pfi.dao.dbo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.uade.pfi.filter.CalculatedCodonPoblationFilter;

@Entity
@Table(name = "CalculatedCodonsPoblations")
public class CalculatedCodonPoblationEntity implements CalculatedCodonPoblationFilter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCalculatedCodonPoblation")
	Long idCalculatedCodonPoblation;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idOrganismPoblation", referencedColumnName = "idOrganismPoblation")
	OrganismPoblationEntity idOrganismPoblation;

	@ManyToOne
	@JoinColumn(name = "idCodon", referencedColumnName = "idCodon")
	CodonEntity idCodon;

	@Column(name = "poblation", length = 14, precision = 13)
	Double poblation;

	@Column(name = "poblationLn", length = 12, precision = 10)
	Double poblationLn;
	
	@Column(name = "energy", length = 12, precision = 11)
	Double energy;

	public CalculatedCodonPoblationEntity() { }

	public CalculatedCodonPoblationEntity(OrganismPoblationEntity idOrganimPoblation, CodonEntity idCodon,
			Double poblation, Double poblationLn, Double energy) {
		this.idOrganismPoblation = idOrganimPoblation;
		this.idCodon = idCodon;
		this.poblation = poblation;
		this.poblationLn = poblationLn;
		this.energy = energy;
	}

	public CalculatedCodonPoblationEntity(Long idCalculatedCodonPoblation, OrganismPoblationEntity idOrganimPoblation,
			CodonEntity idCodon, Double poblation, Double poblationLn, Double energy) {
		this.idCalculatedCodonPoblation = idCalculatedCodonPoblation;
		this.idOrganismPoblation = idOrganimPoblation;
		this.idCodon = idCodon;
		this.poblation = poblation;
		this.poblationLn = poblationLn;
		this.energy = energy;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.CalculatedCodonPoblationFilter#getIdCalculatedCodonPoblation()
	 */
	public Long getIdCalculatedCodonPoblation() {
		return idCalculatedCodonPoblation;
	}

	public void setIdCalculatedCodonPoblation(Long idCalculatedCodonPoblation) {
		this.idCalculatedCodonPoblation = idCalculatedCodonPoblation;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.CalculatedCodonPoblationFilter#getIdOrganimPoblation()
	 */
	public OrganismPoblationEntity getIdOrganimPoblation() {
		return idOrganismPoblation;
	}

	public void setIdOrganimPoblation(OrganismPoblationEntity idOrganimPoblation) {
		this.idOrganismPoblation = idOrganimPoblation;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.CalculatedCodonPoblationFilter#getIdCodon()
	 */
	public CodonEntity getIdCodon() {
		return idCodon;
	}

	public void setIdCodon(CodonEntity idCodon) {
		this.idCodon = idCodon;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.CalculatedCodonPoblationFilter#getPoblation()
	 */
	public Double getPoblation() {
		return poblation;
	}

	public void setPoblation(Double poblation) {
		this.poblation = poblation;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.CalculatedCodonPoblationFilter#getPoblationLn()
	 */
	public Double getPoblationLn() {
		return poblationLn;
	}

	public void setPoblationLn(Double poblationLn) {
		this.poblationLn = poblationLn;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.CalculatedCodonPoblationFilter#getEnergy()
	 */
	public Double getEnergy() {
		return energy;
	}

	public void setEnergy(Double energy) {
		this.energy = energy;
	}
}
