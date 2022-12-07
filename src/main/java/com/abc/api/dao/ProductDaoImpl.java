package com.abc.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abc.api.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public boolean saveProduct(Product product) {

		Session session = sessionFactory.openSession();
		boolean isAdded = false;
		try {

			Transaction transaction = session.beginTransaction();
			Product product2 = session.get(Product.class, product.getProductId());

			if (product2 == null) {
				session.save(product);
				transaction.commit();
				isAdded = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return isAdded;
	}

	@Override
	public Product getProductById(int productId) {
		Session session = sessionFactory.openSession();
		Product product = null;
		try {
			product = session.get(Product.class, productId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return product;
	}

	@Override
	public boolean deleteProduct(int productid) {
		Session session = sessionFactory.openSession();
		// Product product= new Product();
		boolean isDeleted = false;
		try {
			Transaction transaction = session.beginTransaction();
			Product product1 = session.get(Product.class, productid);
			
			if (product1 != null) {
				session.delete(product1);
				transaction.commit();
				isDeleted = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
			
		}

		return isDeleted;
	}

	@Override
	public boolean updateProduct(Product product) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isUpdated = false;
		try {
			Product prid = session.get(Product.class, product.getProductId());

			if (prid != null) {
				session.evict(prid);
				session.update(product);
				transaction.commit();
				isUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return isUpdated;
	}

	@Override
	public List<Product> getAllProduct() {
		Session session = sessionFactory.openSession();
		List list = null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			// criteria.addOrder(Order.desc("productId"));
			
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
		
	}

	@Override
	public List<Product> sortProductByName() {
		Session session = sessionFactory.openSession();
		List<Product> list=null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.addOrder(Order.desc("productName"));
			 list = criteria.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public List<Product>  getMaxPriceProduct() {
		Session session = sessionFactory.openSession();
		List<Double> list=null;
		List<Product> maxProducts=null;
		double maxPrice=0;
		try {
			Criteria maxcriteria = session.createCriteria(Product.class);
			maxcriteria.setProjection(Projections.max("productPrice"));
			 list = maxcriteria.list();
			maxPrice= list.get(0);
			
			Criteria eqcriteria=session.createCriteria(Product.class);
			eqcriteria.add(Restrictions.eq("productPrice", maxPrice));
			maxProducts = eqcriteria.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return maxProducts;
	}

	@Override
	public int getTotalCountOfProduct() {
		Session session = sessionFactory.openSession();
		int countProduct=0;
		try {
			Criteria criteria = session.createCriteria(Product.class);
//			criteria.setProjection(Projections.rowCount());
//			List<Integer> list = criteria.list();
//			countProduct = list.size();
			List<Product> list = criteria.list();
			countProduct=list.size();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return countProduct;
	}
		
	

}
