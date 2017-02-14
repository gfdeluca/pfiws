package ar.com.uade.pfi.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.com.uade.pfi.dao.dbo.DBManager;
import ar.com.uade.pfi.dao.dbo.entities.CalculatedCodonPoblationEntity;
import ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationEntity;
import ar.com.uade.pfi.filter.CalculatedCodonPoblationFilter;

public class CalculatedCodonPoblationDao {

	public static List<CalculatedCodonPoblationFilter> getAllByOrganismPoblation(OrganismPoblationEntity ope) {
		final Session session = DBManager.getInstance().getSession();
		Criteria criteria = session.createCriteria(CalculatedCodonPoblationEntity.class);
		criteria.add( Restrictions.eq("idOrganismPoblation", ope) );
		criteria.addOrder(Order.asc("idCodon"));
		@SuppressWarnings("unchecked")
		List<CalculatedCodonPoblationFilter> res =  criteria.list();
//		session.close();
		return res;
	}
	
	public static void truncate() {
		final Session session = DBManager.getInstance().getSessionWithTransaction();
		session.createQuery("DELETE FROM CalculatedCodonPoblationEntity").executeUpdate();
		session.createSQLQuery("ALTER TABLE CalculatedCodonsPoblations AUTO_INCREMENT = 1").executeUpdate();
		DBManager.getInstance().closeSessionWithTransaction(session);
	}
	
	public static void deleteByOrganismPoblation(Long idOrganismPoblation) {
		final Session session = DBManager.getInstance().getSessionWithTransaction();
		session.createQuery("DELETE FROM CalculatedCodonPoblationEntity WHERE idOrganismPoblation = " + idOrganismPoblation).executeUpdate();
		DBManager.getInstance().closeSessionWithTransaction(session);
	}
}
