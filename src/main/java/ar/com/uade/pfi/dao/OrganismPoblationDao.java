package ar.com.uade.pfi.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.com.uade.pfi.dao.dbo.DBManager;
import ar.com.uade.pfi.dao.dbo.entities.OrganismEntity;
import ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationEntity;
import ar.com.uade.pfi.filter.OrganismPoblationFilter;

public class OrganismPoblationDao {

	public static List<OrganismPoblationFilter> getAllByOrganism(OrganismEntity oe) {
		final Session session = DBManager.getInstance().getSession();
		Criteria criteria = session.createCriteria(OrganismPoblationEntity.class);
		criteria.add( Restrictions.eq("idOrganism", oe) );
		@SuppressWarnings("unchecked")
		List<OrganismPoblationFilter> res =  criteria.list();
//		session.close();
		return res;
	}
	
	public static List<OrganismPoblationFilter> getAll() {
		final Session session = DBManager.getInstance().getSession();
		Criteria criteria = session.createCriteria(OrganismPoblationEntity.class);
		criteria.addOrder(Order.asc("gamma"));
		@SuppressWarnings("unchecked")
		List<OrganismPoblationFilter> res =  criteria.list();
//		session.close();
		return res;
	}
	
	public static void truncate() {
		final Session session = DBManager.getInstance().getSessionWithTransaction();
		session.createQuery("DELETE FROM OrganismPoblationEntity").executeUpdate();
		session.createSQLQuery("ALTER TABLE OrganismsPoblations AUTO_INCREMENT = 1").executeUpdate();
		DBManager.getInstance().closeSessionWithTransaction(session);
	}
}
