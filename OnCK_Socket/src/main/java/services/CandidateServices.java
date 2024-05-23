package services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import entity.Candidate;
import entity.Certificate;
import entity.Position;

public interface CandidateServices {
	public List<Candidate> getListCandidates();
	public Candidate getCandidateById(String id);
	public Map<Candidate, Long> getListCadidatesByCompanies();
	public Map<Candidate, Position> getListCandidatesWithLongestWorking();
	public boolean addCandidate(Candidate candidate);
	public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates();
}