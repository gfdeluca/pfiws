package ar.com.uade.pfi.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.com.uade.pfi.dao.dbo.DBManager;
import ar.com.uade.pfi.dao.dbo.entities.ExperimentalCodonPoblationEntity;
import ar.com.uade.pfi.dao.dbo.entities.OrganismEntity;
import ar.com.uade.pfi.filter.ExperimentalCodonPoblationFilter;

public class ExperimentalCodonPoblationDao {

	public static List<ExperimentalCodonPoblationEntity> getAllByOrganism(OrganismEntity v) {
		final Session session = DBManager.getInstance().getSession();
		Criteria criteria = session.createCriteria(ExperimentalCodonPoblationEntity.class);
		criteria.add( Restrictions.eq("idOrganism", v) );
		criteria.addOrder(Order.asc("idCodon"));
		@SuppressWarnings("unchecked")
		List<ExperimentalCodonPoblationEntity> res = criteria.list();
		session.close();
		return res;
	}
	
	public static List<ExperimentalCodonPoblationFilter> getAllByOrganismFilter(OrganismEntity v) {
		final Session session = DBManager.getInstance().getSession();
		Criteria criteria = session.createCriteria(ExperimentalCodonPoblationEntity.class);
		criteria.add( Restrictions.eq("idOrganism", v) );
		criteria.addOrder(Order.asc("idCodon"));
		@SuppressWarnings("unchecked")
		List<ExperimentalCodonPoblationFilter> res = criteria.list();
		session.close();
		return res;
	}
}
