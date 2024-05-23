package server;

import entity.Candidate;
import jakarta.persistence.Persistence;
import services.CandidateServices;
//import jakarta.persistence.Persistence;
import services.PositionServices;
import services.impl.CandidateImpl;
import services.impl.PositionImpl;

public class Test {
	public static void main(String[] args) {
		System.out.print("Creating EntityManagerFactory... ");
		Persistence.createEntityManagerFactory("jpa-mariadb");
		System.out.println("Done!");
		
//		PositionServices positionServices = new PositionImpl();
//		positionServices.listYearsOfExperienceByPosition("C101").forEach((k, v) -> {
//			System.out.println(k + "/n" + v);
//		});
//		System.out.println(positionServices.getListPositionByNameAndSalary("S", 1000, 10000));
		
//		CandidateServices candidateServices = new CandidateImpl();
//		candidateServices.listCadidatesAndCertificates().forEach((k, v) -> {
//			System.out.println(k + "/n" + v);
//		});
//		Candidate candidate = new Candidate(
//				"C999", "Vo Dinh Thong", 2003, "Male", "vodinhthong17112003@gmail.com", "0346549617", "Internship Fullstack");
//		System.out.println(candidateServices.addCandidate(candidate));
		//		candidateServices.getListCandidatesWithLongestWorking().forEach((k, v) -> {
//			System.out.println(k + "/n" + v);
//		});
//		System.out.println(candidateServices.getCandidateById("C101"));
	}
}
