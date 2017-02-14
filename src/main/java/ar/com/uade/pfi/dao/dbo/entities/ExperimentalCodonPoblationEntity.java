package ar.com.uade.pfi.dao.dbo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.uade.pfi.filter.ExperimentalCodonPoblationFilter;

@Entity
@Table(name = "ExperimentalCodonsPoblations")
public class ExperimentalCodonPoblationEntity implements ExperimentalCodonPoblationFilter {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idExperimentalCodonPoblation")
	Long idExperimentalCodonPoblation;
	
	@ManyToOne
	@JoinColumn(name = "idOrganism", referencedColumnName = "idOrganism")
	OrganismEntity idOrganism;
	
	@ManyToOne
	@JoinColumn(name = "idCodon", referencedColumnName = "idCodon")
	CodonEntity idCodon;
	
	@Column(name = "poblation", length = 14, precision = 13)
	Double poblation;
	
	@Column(name = "poblationLn", length = 12, precision = 10 )
	Double poblationLn;
	
	public ExperimentalCodonPoblationEntity() { }

	public ExperimentalCodonPoblationEntity(OrganismEntity idOrganim, CodonEntity idCodon, Double poblation,
			Double poblationLn) {
		this.idOrganism = idOrganim;
		this.idCodon = idCodon;
		this.poblation = poblation;
		this.poblationLn = poblationLn;
	}

	public ExperimentalCodonPoblationEntity(Long idExperimentalCodonPoblation, OrganismEntity idOrganim,
			CodonEntity idCodon, Double poblation, Double poblationLn) {
		this.idExperimentalCodonPoblation = idExperimentalCodonPoblation;
		this.idOrganism = idOrganim;
		this.idCodon = idCodon;
		this.poblation = poblation;
		this.poblationLn = poblationLn;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.ExperimenralCodonPoblationFilter#getIdExperimentalCodonPoblation()
	 */
	public Long getIdExperimentalCodonPoblation() {
		return idExperimentalCodonPoblation;
	}

	public void setIdExperimentalCodonPoblation(Long idExperimentalCodonPoblation) {
		this.idExperimentalCodonPoblation = idExperimentalCodonPoblation;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.ExperimenralCodonPoblationFilter#getIdOrganim()
	 */
	public OrganismEntity getIdOrganim() {
		return idOrganism;
	}

	public void setIdOrganim(OrganismEntity idOrganim) {
		this.idOrganism = idOrganim;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.ExperimenralCodonPoblationFilter#getIdCodon()
	 */
	public CodonEntity getIdCodon() {
		return idCodon;
	}

	public void setIdCodon(CodonEntity idCodon) {
		this.idCodon = idCodon;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.ExperimenralCodonPoblationFilter#getPoblation()
	 */
	public Double getPoblation() {
		return poblation;
	}

	public void setPoblation(Double poblation) {
		this.poblation = poblation;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.ExperimenralCodonPoblationFilter#getPoblationLn()
	 */
	public Double getPoblationLn() {
		return poblationLn;
	}

	public void setPoblationLn(Double poblationLn) {
		this.poblationLn = poblationLn;
	}
}
