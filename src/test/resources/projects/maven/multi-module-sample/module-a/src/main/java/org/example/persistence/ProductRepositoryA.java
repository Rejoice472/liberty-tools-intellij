package org.example.persistence;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Sample EJB for Module A using:
 *  - Jakarta Persistence 3.1  (EE 10)
 *  - Jakarta Annotation 2.1   (EE 10)
 *  - Jakarta EJB 4.0          (EE 10)
 */
@Stateless
public class ProductRepositoryA {

    @PersistenceContext(unitName = "samplePU")
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        System.out.println("ProductRepositoryA initialized.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("ProductRepositoryA destroyed.");
    }

    public void save(ProductA product) {
        entityManager.persist(product);
    }

    public ProductA findById(Long id) {
        return entityManager.find(ProductA.class, id);
    }

    public List<ProductA> findAll() {
        TypedQuery<ProductA> query = entityManager.createQuery(
                "SELECT p FROM ProductA p", ProductA.class);
        return query.getResultList();
    }

    public void delete(Long id) {
        ProductA product = findById(id);
        if (product != null) {
            entityManager.remove(product);
        }
    }
}
