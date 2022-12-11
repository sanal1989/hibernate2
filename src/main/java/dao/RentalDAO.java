package dao;

import entity.Rental;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class RentalDAO extends GenericDAO<Rental>{

    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public Rental unreturnedRental() {
        //String hql = new String("from Rental where returnDate is NULL");
        Query<Rental> query = getCurrentSession().createQuery("from Rental where returnDate is null", Rental.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
