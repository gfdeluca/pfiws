package ar.com.uade.pfi.dao.dbo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.uade.pfi.filter.OrganismPoblationFilter;

@Entity
@Table(name = "OrganismsPoblations")
public class OrganismPoblationEntity implements OrganismPoblationFilter{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idOrganismPoblation")
	Long idOrganismPoblation;

	@ManyToOne
	@JoinColumn(name = "idOrganism", referencedColumnName = "idOrganism")
	OrganismEntity idOrganism;

	@Column(name = "gamma", length = 8, precision = 5)
	Double gamma;

	@Column(name = "pearsonCoefficient", length = 6, precision = 5)
	Double pearsonCoefficient;

	@Column(name = "lastUpdate")
	@Temporal(TemporalType.TIMESTAMP)
	Date lastUpdate;

	@Column(name = "idTransition", nullable = true)
	Long idTransition;
	
	public OrganismPoblationEntity() {

	}

	public OrganismPoblationEntity(OrganismEntity idOrganim, Double gamma, Double pearsonCoefficient, Date lastUpdate,
			Long idTransition) {
		this.idOrganism = idOrganim;
		this.gamma = gamma;
		this.pearsonCoefficient = pearsonCoefficient;
		this.lastUpdate = lastUpdate;
		this.idTransition = idTransition;
	}

	public OrganismPoblationEntity(Long idOrganimPoblation, OrganismEntity idOrganim, Double gamma,
			Double pearsonCoefficient, Date lastUpdate, Long idTransition) {
		this.idOrganismPoblation = idOrganimPoblation;
		this.idOrganism = idOrganim;
		this.gamma = gamma;
		this.pearsonCoefficient = pearsonCoefficient;
		this.lastUpdate = lastUpdate;
		this.idTransition = idTransition;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationFilter#getIdOrganimPoblation()
	 */
	public Long getIdOrganimPoblation() {
		return idOrganismPoblation;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationFilter#setIdOrganimPoblation(java.lang.Long)
	 */
	public void setIdOrganimPoblation(Long idOrganimPoblation) {
		this.idOrganismPoblation = idOrganimPoblation;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationFilter#getIdOrganim()
	 */
	public OrganismEntity getIdOrganim() {
		return idOrganism;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationFilter#setIdOrganim(ar.com.uade.pfi.dao.dbo.entities.OrganismEntity)
	 */
	public void setIdOrganim(OrganismEntity idOrganim) {
		this.idOrganism = idOrganim;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationFilter#getGamma()
	 */
	public Double getGamma() {
		return gamma;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationFilter#setGamma(java.lang.Double)
	 */
	public void setGamma(Double gamma) {
		this.gamma = gamma;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationFilter#getPearsonCoefficient()
	 */
	public Double getPearsonCoefficient() {
		return pearsonCoefficient;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationFilter#setPearsonCoefficient(java.lang.Double)
	 */
	public void setPearsonCoefficient(Double pearsonCoefficient) {
		this.pearsonCoefficient = pearsonCoefficient;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationFilter#getLastUpdate()
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationFilter#setLastUpdate(java.util.Date)
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationFilter#getIdTransition()
	 */
	public Long getIdTransition() {
		return idTransition;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationFilter#setIdTransition(java.lang.Long)
	 */
	public void setIdTransition(Long idTransition) {
		this.idTransition = idTransition;
	}
}
