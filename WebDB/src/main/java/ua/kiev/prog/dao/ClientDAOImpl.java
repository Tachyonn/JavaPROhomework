package ua.kiev.prog.dao;

import ua.kiev.prog.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.concurrent.Callable;

public class ClientDAOImpl implements ClientDAO {
    private EntityManager em;

    public ClientDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public synchronized void add(String name, int age) {
        Client client = new Client(name, age);
        performTransaction(() -> {
            em.persist(client);
            return null;
        });
    }

    @Override
    public synchronized List<Client> selectAll() {
        TypedQuery<Client> typedQuery = em.createQuery(
                "SELECT c FROM Client c", Client.class);
        List<Client> clientsList = typedQuery.getResultList();
        return clientsList;
    }

    @Override
    public synchronized void delete(Long id) {
        performTransaction(() -> {
            Query query = em.createQuery(
                    "DELETE FROM Client c WHERE id=:id");
            query.setParameter("id", id);
            query.executeUpdate();
            return null;
        });
    }

    private synchronized <T> T performTransaction(Callable<T> unit) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            T result = unit.call();
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
