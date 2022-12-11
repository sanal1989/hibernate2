import dao.*;
import dateConverter.Feature;
import dateConverter.Rating;
import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import config.UtilSessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = UtilSessionFactory.getSessionFactory();
        Main main = new Main();
        ActorDAO actorDAO = new ActorDAO(sessionFactory);
        AddressDAO addressDAO = new AddressDAO(sessionFactory);
        CategoryDAO categoryDAO = new CategoryDAO(sessionFactory);
        CityDAO cityDAO = new CityDAO(sessionFactory);
        CountryDAO countryDAO = new CountryDAO(sessionFactory);
        CustomerDAO customerDAO = new CustomerDAO(sessionFactory);
        FilmDAO filmDAO = new FilmDAO(sessionFactory);
        FilmTextDAO filmTextDAO = new FilmTextDAO(sessionFactory);
        InventoryDAO inventoryDAO = new InventoryDAO(sessionFactory);
        LanguageDAO languageDAO = new LanguageDAO(sessionFactory);
        PaymentDAO paymentDAO = new PaymentDAO(sessionFactory);
        RentalDAO rentalDAO = new RentalDAO(sessionFactory);
        StaffDAO staffDAO = new StaffDAO(sessionFactory);
        StoreDAO storeDAO = new StoreDAO(sessionFactory);

        Customer customer = main.creatCustomer(sessionFactory,storeDAO,cityDAO,addressDAO,customerDAO);

        main.customerReturnFilm(sessionFactory, rentalDAO);
        
        main.customerRentInventory(sessionFactory, customer,filmDAO,storeDAO,rentalDAO,paymentDAO);

        main.newFilm(sessionFactory,languageDAO,categoryDAO,actorDAO,filmDAO,filmTextDAO);
    }

    private void newFilm(SessionFactory sessionFactory,
                         LanguageDAO languageDAO,
                         CategoryDAO categoryDAO,
                         ActorDAO actorDAO,
                         FilmDAO filmDAO,
                         FilmTextDAO filmTextDAO) {
        try(Session session = sessionFactory.getCurrentSession();) {
            Transaction transaction = session.beginTransaction();

            Language language = languageDAO.getById(1);

            List<Category> categories = categoryDAO.getItems(1,5);

            List<Actor> actors = actorDAO.getItems(1,5);

            Film film = new Film();
            film.setActorHashSet(actors.stream().collect(Collectors.toSet()));
            film.setRating(Rating.NC17);
            film.setSpecialFeatures(Set.of(Feature.TRAILERS,Feature.COMMENTARIES));
            film.setLength((short) 152);
            film.setReplacementCost(BigDecimal.valueOf(10.45));
            film.setRentalRate(BigDecimal.valueOf(11.10));
            film.setLanguage(language);
            film.setDescription("new Film");
            film.setTitle("from Sanal");
            film.setRentalDuration((byte) 5);
            film.setLanguage(language);
            film.setCategoryHashSet(categories.stream().collect(Collectors.toSet()));
            film.setReleaseYear(Year.now());
            filmDAO.save(film);

            FilmText filmText = new FilmText();
            filmText.setFilmId(film.getFilm_id());
            filmText.setTitle("from Sanal");
            filmText.setDescription("new Film");
            filmTextDAO.save(filmText);

            transaction.commit();
        }
    }

    private void customerRentInventory( SessionFactory sessionFactory,
                                        Customer customer,
                                        FilmDAO filmDAO,
                                        StoreDAO storeDAO,
                                        RentalDAO rentalDAO,
                                        PaymentDAO paymentDAO) {
        try(Session session = sessionFactory.getCurrentSession();) {
            Transaction transaction = session.beginTransaction();
            Film film = filmDAO.getFirstAvailableFilmForRent();

            Store store = storeDAO.getById(1);

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            session.save(inventory);

            Staff staff = store.getManagerStaffId();

            Rental rental = new Rental();
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staff);
            rentalDAO.save(rental);
            System.out.println(rental.getRentalId());

            Payment payment = new Payment();
            payment.setRental(rental);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setCustomer(customer);
            payment.setAmount(BigDecimal.valueOf(55.55));
            payment.setStaff(staff);
            paymentDAO.save(payment);
            System.out.println(payment.getPaymentId());
            transaction.commit();
        }

    }

    private void customerReturnFilm(SessionFactory sessionFactory,
                                    RentalDAO rentalDAO) {
        try(Session session = sessionFactory.getCurrentSession();) {
            Transaction transaction = session.beginTransaction();
            Rental rental = rentalDAO.unreturnedRental();
            rental.setReturnDate(LocalDateTime.now());
            System.out.println(rental.getRentalId());
            rentalDAO.save(rental);
            transaction.commit();
        }
    }

    public Customer creatCustomer(SessionFactory sessionFactory,
                                  StoreDAO storeDAO,
                                  CityDAO cityDAO,
                                  AddressDAO addressDAO,
                                  CustomerDAO customerDAO){
        Customer customer = new Customer();
        try(Session session = sessionFactory.getCurrentSession();){
            Transaction transaction = session.beginTransaction();

            Store store = storeDAO.getById(1);
            Address address = addressDAO.getById(2);
            customer.setActive(true);
            customer.setEmail("Neo@matrix.oo");
            customer.setFirstName("Neo");
            customer.setLastName("Pupkin");
            customer.setStore(store);
            customer.setAddress(address);
            customerDAO.save(customer);

            transaction.commit();
        }
        return customer;
    }


}
