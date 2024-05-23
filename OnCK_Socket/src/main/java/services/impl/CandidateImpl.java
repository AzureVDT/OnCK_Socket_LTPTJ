package services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entity.Candidate;
import entity.Certificate;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import services.CandidateServices;

public class CandidateImpl implements CandidateServices {

	private static final String PERSISTENCE_UNIT_NAME = "jpa-mssql";
	private EntityManager em;

	public CandidateImpl() {
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}

	@Override
	public Map<Candidate, Long> getListCadidatesByCompanies() {
		Map<Candidate, Long> list = new HashMap<>();
		String query = "select c.candidate_id, COUNT(*) as quatity from candidates c join experiences e "
				+ "on c.candidate_id = e.candidate_id " + "group by c.candidate_id " + "order by c.candidate_id asc";
		Query q = em.createNativeQuery(query);
		@SuppressWarnings("unchecked")
		List<Object[]> result = q.getResultList();
		for (Object[] o : result) {
			Candidate candidate = getCandidateById((String) o[0]);
			list.put(candidate, ((Number) o[1]).longValue());
		}
		return list;
	}

	@Override
	public Candidate getCandidateById(String id) {
		return em.find(Candidate.class, id);
	}

	@Override
	public Map<Candidate, Position> getListCandidatesWithLongestWorking() {
		Map<Candidate, Position> list = new HashMap<>();
		String query = "WITH PositionMaxDays AS ("
				+ "    SELECT position_id, MAX(DATEDIFF(DAY, from_date, to_date)) AS NumberOfDays "
				+ "    FROM experiences " + "    GROUP BY position_id " + "), " + "CandidateDays AS ("
				+ "    SELECT candidate_id, position_id, DATEDIFF(DAY, from_date, to_date) as numberOfDays "
				+ "    FROM experiences " + "    GROUP BY candidate_id, position_id, from_date, to_date "
				+ ") " + "SELECT cd.candidate_id, cd.position_id, cd.numberOfDays " + "FROM CandidateDays cd "
				+ "JOIN PositionMaxDays pmd ON cd.position_id = pmd.position_id AND cd.numberOfDays = pmd.NumberOfDays";
		Query q = em.createNativeQuery(query);
		@SuppressWarnings("unchecked")
		List<Object[]> result = q.getResultList();
		for (Object[] o : result) {
			Candidate candidate = getCandidateById((String) o[0]);
			Position position = em.find(Position.class, (String) o[1]);
			list.put(candidate, position);
		}
		return list;
	}

	@Override
	public boolean addCandidate(Candidate candidate) {
		EntityTransaction tx = em.getTransaction();
		String regex = "^C\\d{3,}$";
		try {
			tx.begin();
			if (candidate.getId().matches(regex)) {
				em.persist(candidate);
				tx.commit();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates() {
		Map<Candidate, Set<Certificate>> list = new HashMap<>();
		List<Candidate> candidates = getListCandidates();
		for (Candidate c : candidates) {
			String query = "select * from certificates where candidate_id = '" + c.getId() + "'";
			Query q = em.createNativeQuery(query, Certificate.class);
			List<Certificate> result = q.getResultList();
			list.put(c, new HashSet<>(result));
		}
		
		return list;
	}

	@Override
	public List<Candidate> getListCandidates() {
		List<Candidate> list = new ArrayList<>();
		list = em.createNamedQuery("getAllCandidates", Candidate.class).getResultList();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
