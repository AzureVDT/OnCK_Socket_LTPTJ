package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import entity.Candidate;
import entity.Certificate;
import entity.Position;
import services.CandidateServices;
import services.impl.CandidateImpl;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CandidateServicesTest {
	private CandidateServices candidateServices;
	
	@BeforeAll
	public void setUp() {
		candidateServices = new CandidateImpl();
	}
	
	@Test
	public void testGetListCadidatesByCompanies() {
		Map<Candidate, Long> lst = candidateServices.getListCadidatesByCompanies();
		for (Candidate c : lst.keySet()) {
			if (c.getId() == "C101") {
				assertEquals(lst.get(c), 5);
			}
		}
	}
	
	@Test
	public void testGetListCandidatesWithLongestWorking() {
		Map<Candidate, Position> lst = candidateServices.getListCandidatesWithLongestWorking();
		for (Candidate c : lst.keySet()) {
			if (c.getId() == "C103") {
				assertEquals(lst.get(c).getId(), "P101");
			}
		}
	}
	
	@Test
	public void testAddCandidate() {
		Candidate candidate = new Candidate(
				"C999", "Vo Dinh Thong", 2003, "Male", "vodinhthong17112003@gmail.com", "0346549617", "Internship Fullstack");
		assertEquals(candidateServices.addCandidate(candidate), true);
	}
	
	@Test
	public void testListCadidatesAndCertificates() {
		Map<Candidate, Set<Certificate>> lst = candidateServices.listCadidatesAndCertificates();
		for (Candidate c : lst.keySet()) {
			if (c.getId() == "C101") {
				assertEquals(lst.get(c).size(), 3);
			}
		}
	}
	
	@AfterAll
	public void tearDown() {
		candidateServices = null;
	}
}
