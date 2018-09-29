package com.org.todo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class TodoDao implements ITodoDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public <T> T save(T t) {
		em.persist(t);
		return t;
		
	}

	@Override
	public <T> List<T> getTaskList(Class<T> clazz)  {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> root = cq.from(clazz);
		cq.select(root);
		TypedQuery<T> typedQuery =  em.createQuery(cq);
		return typedQuery.getResultList();
	}

	@Override
	public <T> T deleteTask(Class<T> clazz, long id) {
		T entity = em.find(clazz, id);
		em.remove(entity);
		return entity;
	}

	@Override
	public <T> int updateTask(Class<T> clazz, long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<T> update = cb.createCriteriaUpdate(clazz);
		Root<T> from = update.from(clazz);
		update.set("status", "Completed");
		update.where(cb.equal(from.get("id"), id));
		return this.em.createQuery(update).executeUpdate();
		
		
		
 	}

}
