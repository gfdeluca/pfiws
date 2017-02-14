package ar.com.uade.pfi.dao.dbo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class DBManager {
	private static DBManager _instance = null;
	private static SessionFactory _sessionFactory = null;
	
	private DBManager() {
		_sessionFactory = new Configuration().configure().buildSessionFactory();	
	}
	
	public static DBManager getInstance() {
		if (_instance == null) {
			_instance = new DBManager();
		}
		
		return _instance;
	}
	
	public Session getSession() {
		return _sessionFactory.openSession();
	}
	
	public Session getSessionWithTransaction() {
		Session session = _sessionFactory.openSession();
		session.beginTransaction();
		return session;
	}
	
	public void closeSessionWithTransaction(Session session) {
		session.getTransaction().commit();
//		session.close();
	}
	
	public void closeFactory() {
        if (_sessionFactory != null) {
            try {
                _sessionFactory.close();
                _instance = null;
            } catch (HibernateException ignored) {
                // TODO
            }
        }
    }
	
	public <T> T Insert(final T c) {
		final Session session = _sessionFactory.openSession();
		final Transaction tx = session.beginTransaction();
		session.save(c);
		tx.commit();
//		session.close();
		return c;
	}
	
	public <T> T Insert(Session session, final T c) {
		session.save(c);
//		session.close();
		return c;
	}
	
	public <T> T Update(final T c) {
		final Session session = _sessionFactory.openSession();
		final Transaction tx = session.beginTransaction();
		session.update(c);
		tx.commit();
//		session.close();
		return c;
	}
	
	public <T> T Update(Session session, final T c) {
		session.update(c);
//		session.close();
		return c;
	}
	
	public <T> T delete(final T c) {
		final Session session = _sessionFactory.openSession();
		final Transaction tx = session.beginTransaction();
		session.delete(c);
		tx.commit();
//		session.close();
		return c;
	}
	
	public <T> T delete(Session session, final T c) {
		session.delete(c);
//		session.close();
		return c;
	}
	
	public <T> List<T> getAll(final Class<T> c) {
		final Session session = _sessionFactory.openSession();
		final Criteria crit = session.createCriteria(c);
		@SuppressWarnings("unchecked")
		List<T> res = crit.list();
//		session.close();
		return res;
	}
	
	public <T> T get(final Class<T> type, final Long id){
		final Session session = _sessionFactory.openSession();
		T crit = session.get(type, id);
//		session.close();
		return crit;
	}
	
	public <T> List<T> getByField(final Class<T> c, final String field, Object v) {
		final Session session = _sessionFactory.openSession();
		Criteria criteria = session.createCriteria(c);
		criteria.add( Restrictions.eq(field, v) );
		@SuppressWarnings("unchecked")
		List<T> res = criteria.list();
//		session.close();
		return res;
	}
}
