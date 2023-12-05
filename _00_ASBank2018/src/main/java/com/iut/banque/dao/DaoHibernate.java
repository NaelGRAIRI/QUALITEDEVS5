package com.iut.banque.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.exceptions.IllegalOperationException;
import com.iut.banque.exceptions.TechnicalException;
import com.iut.banque.interfaces.IDao;
import com.iut.banque.modele.Client;
import com.iut.banque.modele.Compte;
import com.iut.banque.modele.CompteAvecDecouvert;
import com.iut.banque.modele.CompteSansDecouvert;
import com.iut.banque.modele.Gestionnaire;
import com.iut.banque.modele.Utilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Implémentation de IDao utilisant Hibernate.
 * <p>
 * Les transactions sont gerés par Spring et utilise le transaction manager
 * défini dans l'application Context.
 *<p></p>
 * Par défaut, la propagation des transactions est REQUIRED, ce qui signifie que
 * si une transaction est déjà commencé elle va être réutilisée. Cela est util
 * pour les tests unitaires de la DAO.
 */
@Transactional
public class DaoHibernate implements IDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(DaoHibernate.class);

	private SessionFactory sessionFactory;

	// Constructeur par défaut ajouté
	public DaoHibernate() {
		// Constructeur par défaut sans arguments
	}

	public DaoHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Setter pour la SessionFactory.
	 * <p></p>
	 * Cette méthode permet à Spring d'injecter la factory au moment de la
	 * construction de la DAO.
	 *
	 * @param sessionFactory : la session factory nécessaire à la gestion des sessions
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 * {@inheritDoc}
	 * @throws IllegalOperationException
	 */


	@Override
	public CompteAvecDecouvert createCompteAvecDecouvert(double solde, String numeroCompte, double decouvertAutorise,
														 Client client) throws TechnicalException, IllegalFormatException, IllegalOperationException {
		Session session = sessionFactory.getCurrentSession();
		if (session.get(CompteAvecDecouvert.class, numeroCompte) != null) {
			throw new TechnicalException("Numéro de compte déjà utilisé.");
		}

		CompteAvecDecouvert compte = new CompteAvecDecouvert(numeroCompte, solde, decouvertAutorise, client);
		client.addAccount(compte);
		session.save(compte);

		return compte;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CompteSansDecouvert createCompteSansDecouvert(double solde, String numeroCompte, Client client)
			throws TechnicalException, IllegalFormatException {
		Session session = sessionFactory.getCurrentSession();
		CompteSansDecouvert compte = session.get(CompteSansDecouvert.class, numeroCompte);
		if (compte != null) {
			throw new TechnicalException("Numéro de compte déjà utilisé.");
		}

		compte = new CompteSansDecouvert(numeroCompte, solde, client);
		session.save(compte);
		client.addAccount(compte);
		session.save(compte);

		return compte;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateAccount(Compte c) {
		Session session = sessionFactory.getCurrentSession();
		session.update(c);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAccount(Compte c) throws TechnicalException {
		Session session = sessionFactory.getCurrentSession();
		if (c == null) {
			throw new TechnicalException("Ce compte n'existe plus");
		}
		session.delete(c);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Compte> getAccountsByClientId(String id) {
		Session session = sessionFactory.getCurrentSession();
		Client client = session.get(Client.class, id);
		return (client != null) ? client.getAccounts() : Collections.emptyMap();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Compte getAccountById(String id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Compte.class, id);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalFormatException
	 * @throws IllegalArgumentException
	 */
	@Override
	public Utilisateur createUser(String nom, String prenom, String adresse, boolean male, String userId,
								  String userPwd, boolean manager, String numClient)
			throws TechnicalException, IllegalArgumentException, IllegalFormatException {
		Session session = sessionFactory.getCurrentSession();

		Utilisateur user = session.get(Utilisateur.class, userId);
		if (user != null) {
			throw new TechnicalException("User Id déjà utilisé.");
		}

		if (manager) {
			user = new Gestionnaire(nom, prenom, adresse, male, userId, userPwd);
		} else {
			user = new Client(nom, prenom, adresse, male, userId, userPwd, numClient);
		}

		// Appel à la méthode setUserPwd pour hacher le mot de passe
		user.setUserPwd(userPwd);

		session.save(user);

		return user;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteUser(Utilisateur u) throws TechnicalException {
		Session session = sessionFactory.getCurrentSession();
		if (u == null) {
			throw new TechnicalException("Cet utilisateur n'existe plus");
		}
		session.delete(u);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUser(Utilisateur u) {
		Session session = sessionFactory.getCurrentSession();
		session.update(u);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUserAllowed(String userId, String userPwd) {
		if (userId == null || userPwd == null || userId.trim().isEmpty() || userPwd.trim().isEmpty()) {
			return false;
		}

		Session session = sessionFactory.getCurrentSession();
		Utilisateur user = session.get(Utilisateur.class, userId);

		return (user != null && userPwd.equals(user.getUserPwd()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Utilisateur getUserById(String id) {
		return sessionFactory.getCurrentSession().get(Utilisateur.class, id);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Client> getAllClients() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Client> clients = session.createQuery("FROM Client").list();
		Map<String, Client> clientMap = new HashMap<>();
		for (Client client : clients) {
			clientMap.put(client.getUserId(), client);
		}
		return clientMap;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Gestionnaire> getAllGestionnaires() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Gestionnaire> criteriaQuery = builder.createQuery(Gestionnaire.class);
		Root<Gestionnaire> root = criteriaQuery.from(Gestionnaire.class);
		criteriaQuery.select(root);

		List<Gestionnaire> gestionnaires = session.createQuery(criteriaQuery).getResultList();

		Map<String, Gestionnaire> gestionnaireMap = new HashMap<>();
		for (Gestionnaire gestionnaire : gestionnaires) {
			gestionnaireMap.put(gestionnaire.getUserId(), gestionnaire);
		}

		return gestionnaireMap;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void disconnect() {
		LOGGER.info("Déconnexion de la DAO.");
	}

}