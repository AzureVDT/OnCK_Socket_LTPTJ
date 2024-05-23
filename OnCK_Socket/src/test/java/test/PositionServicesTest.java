package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import entity.Position;
import services.PositionServices;
import services.impl.PositionImpl;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PositionServicesTest {
	private PositionServices positionServices;
	
	@BeforeAll
	public void setUp() {
		positionServices = new PositionImpl();
	}
	
	@Test
	public void testGetListPositionByNameAndSalary() {
		List<Position> lst = positionServices.getListPositionByNameAndSalary("S", 1000, 10000);
		lst.forEach(System.out::println);
		assertEquals(lst.size(), 1);
	}
	
	@Test
	public void testListYearsOfExperienceByPosition() {
		assertEquals(2, positionServices.listYearsOfExperienceByPosition("C107").size());
	}
	
	@AfterAll
	public void tearDown() {
		positionServices = null;
	}
}
