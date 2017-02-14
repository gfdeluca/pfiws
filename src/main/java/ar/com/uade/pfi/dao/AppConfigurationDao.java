package ar.com.uade.pfi.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ar.com.uade.pfi.dao.dbo.DBManager;
import ar.com.uade.pfi.dao.dbo.entities.AppConfigurationEntity;

public class AppConfigurationDao {
	public static AppConfigurationEntity get (String clave) {
		final Session session = DBManager.getInstance().getSession();
		Criteria criteria = session.createCriteria(AppConfigurationEntity.class);
		criteria.add( Restrictions.eq("clave", clave) );
		AppConfigurationEntity res = (AppConfigurationEntity) criteria.uniqueResult();
		session.close();
		return res;
	}
}
