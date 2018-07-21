package ru.itis.grant.dao.impl;

import org.springframework.stereotype.Repository;
import ru.itis.grant.dao.interfaces.PatternDao;
import ru.itis.grant.model.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class PatternDaoImpl implements PatternDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public void addPattern(Pattern pattern) {
        em.persist(pattern);
    }

    @Override
    public void deletePattern(long id) {
        em.createQuery("delete from Pattern p where p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void deletePattern(Pattern pattern) {
        em.remove(pattern);
    }

    @Override
    public Pattern updatePattern(Pattern pattern) {
        return em.merge(pattern);
    }

    @Override
    public Pattern getPattern(long id) {
        return em.find(Pattern.class, id);
    }

    @Override
    public Pattern getEventPattern(long eventId) {
        Pattern pattern = (Pattern) em.createQuery("from Pattern p where p.event.id = :eventId")
                .setParameter("eventId", eventId)
                .getSingleResult();
        return pattern;
    }

    @Override
    public List<Pattern> getAllPatterns() {
        List<Pattern> patterns = em.createQuery("from Pattern")
                .getResultList();
        return patterns;
    }

    @Override
    public boolean patternExistence(long id) {
        return !em.createQuery("select p.id from Pattern p where p.id = :id")
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public boolean patternTimeCorrect(long id, Date date) {
        return !em.createQuery("select p.id from Pattern p where p.id = :id and" +
                " p.event.startDate < :date and p.event.endDate > :date")
                .setParameter("id", id)
                .setParameter("date", date)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }
}
