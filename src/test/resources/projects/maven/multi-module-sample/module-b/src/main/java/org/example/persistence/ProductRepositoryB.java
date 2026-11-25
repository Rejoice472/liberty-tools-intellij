package org.example.persistence;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Sample EJB for Module B using:
 *  - Jakarta Persistence 3.1  (EE 10)
 *  - Jakarta Annotation 2.1   (EE 10)
 *  - Jakarta EJB 4.0          (EE 10)
 */
@Stateless
public class ProductRepositoryB {

    @PersistenceContext(unitName = "samplePU")
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        System.out.println("ProductRepositoryB initialized.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("ProductRepositoryB destroyed.");
    }

    public void save(ProductB product) {
        entityManager.persist(product);
    }

    public ProductB findById(Long id) {
        return entityManager.find(ProductB.class, id);
    }

    public List<ProductB> findAll() {
        TypedQuery<ProductB> query = entityManager.createQuery(
                "SELECT p FROM ProductB p", ProductB.class);
        return query.getResultList();
    }

    public void delete(Long id) {
        ProductB product = findById(id);
        if (product != null) {
            entityManager.remove(product);
        }
    }
}
