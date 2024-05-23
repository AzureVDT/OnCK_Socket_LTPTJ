package server;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entity.Candidate;
import entity.Certificate;
import entity.Position;
import services.CandidateServices;
import services.PositionServices;
import services.impl.CandidateImpl;
import services.impl.PositionImpl;

public class ClientHandler implements Runnable {
	private Socket socket;
	private CandidateServices candidateServices;
	private PositionServices positionServices;

	public ClientHandler(Socket socket) {
		super();
		this.socket = socket;
		this.candidateServices = new CandidateImpl();
		this.positionServices = new PositionImpl();
	}



	@Override
	public void run() {
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			
			int choice = 0;
			
			while(true) {
				choice = in.readInt();
				switch(choice) {
					case 1:
						String positionName = in.readUTF();
						double salaryFrom = in.readDouble();
						double salaryTo = in.readDouble();
						List<Position> list = positionServices.getListPositionByNameAndSalary(positionName, salaryFrom, salaryTo);
						out.writeObject(list);
						break;
					case 2:
						Map<Candidate, Long> list1 = candidateServices.getListCadidatesByCompanies();
						out.writeObject(list1);
						break;
					case 3: 
						Map<Candidate, Position> list2 = candidateServices.getListCandidatesWithLongestWorking();
						out.writeObject(list2);
						break;
					case 4:
		                String id = in.readUTF();
		                String name = in.readUTF();
		                int birthYear = in.readInt();
		                String gender = in.readUTF();
		                String email = in.readUTF();
		                String phone = in.readUTF();
		                String desc = in.readUTF();
		                Candidate candidate = new Candidate(id,name, birthYear, gender, email, phone, desc);
		                boolean result = candidateServices.addCandidate(candidate);
		                out.writeBoolean(result);
		                List<Candidate> lst = candidateServices.getListCandidates();
		                out.writeObject(lst);
		                break;
					case 5:
						String candidateId = in.readUTF();
						Map<Position, Integer> list3 = positionServices.listYearsOfExperienceByPosition(candidateId);
						out.writeObject(list3);
					    break;
					case 6:
						Map<Candidate, Set<Certificate>> list4 = candidateServices.listCadidatesAndCertificates();
						out.writeObject(list4);
						break;
					case 7:
						socket.close();
						break;
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}
