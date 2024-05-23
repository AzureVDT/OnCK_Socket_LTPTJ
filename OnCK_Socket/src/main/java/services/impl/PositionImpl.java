package services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import services.PositionServices;

public class PositionImpl implements PositionServices {
	
	private static final String PERSISTENCE_UNIT_NAME = "jpa-mssql";
	private EntityManager em;
	
	public PositionImpl() {
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}


	@Override
	public List<Position> getListPositionByNameAndSalary(String name, double salaryFrom, double salaryTo) {
		List<Position> positions = em.createNamedQuery("getListPositionByNameAndSalary", Position.class)
				.setParameter("name", "%" + name + "%")
				.setParameter("salaryFrom", salaryFrom)
				.setParameter("salaryTo", salaryTo)
				.getResultList();
		if (positions != null) {
			return positions;
		} else {
			return null;
		}
	}


	@Override
	public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateId) {
		Map<Position, Integer> list = new HashMap<>();
		String query = "select position_id, DATEDIFF(YEAR, from_date, to_date) as numberOfYears from experiences "
				+ "where candidate_id = '" + candidateId + "'";
		System.out.println(query);
		@SuppressWarnings("unchecked")
		List<Object[]> result = em.createNativeQuery(query).getResultList();
		for (Object[] o : result) {
			Position position = em.find(Position.class, (String) o[0]);
			list.put(position, ((Number) o[1]).intValue());
		}
		return list;
	}

}
