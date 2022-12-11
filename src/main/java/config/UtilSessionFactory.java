package config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UtilSessionFactory {

    private static SessionFactory sessionFactory;

    static {

    }

    public static SessionFactory getSessionFactory(){
        sessionFactory = new Configuration()
                .addAnnotatedClass(entity.Actor.class)
                .addAnnotatedClass(entity.Address.class)
                .addAnnotatedClass(entity.Category.class)
                .addAnnotatedClass(entity.City.class)
                .addAnnotatedClass(entity.Country.class)
                .addAnnotatedClass(entity.Customer.class)
                .addAnnotatedClass(entity.Film.class)
                .addAnnotatedClass(entity.FilmText.class)
                .addAnnotatedClass(entity.Inventory.class)
                .addAnnotatedClass(entity.Language.class)
                .addAnnotatedClass(entity.Payment.class)
                .addAnnotatedClass(entity.Rental.class)
                .addAnnotatedClass(entity.Staff.class)
                .addAnnotatedClass(entity.Store.class)
                .buildSessionFactory();
        return sessionFactory;
    }
}
