package services;

import java.util.List;
import java.util.Map;

import entity.Position;

public interface PositionServices {
	public List<Position> getListPositionByNameAndSalary(String name, double salaryFrom, double salaryTo);
	public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateId);
}
