package client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import entity.Candidate;
import entity.Certificate;
import entity.Position;

public class Client {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		try(Socket socket = new Socket("AzureVDT", 7878);
				Scanner sc = new Scanner(System.in);
				){
			
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			int choice = 0;
			
			
			while(true) {
				System.out.println("Enter your choice: \n"
						+ "1. Liệt kê danh sách các vị trí công việc khi biết tên vị trí (tìm tương đối) và mức lương khoảng từ, kết quả sắp xếp theo tên vị trí công việc.\n"
						+ "2. Liệt kê danh sách các ứng viên và số công ty mà các ứng viên này từng làm.\n"
						+ "3. Tìm danh sách các ứng viên đã làm việc trên một vị trí công việc nào đó có thời gian làm lâu nhất.\n"
						+ "4. Thêm một ứng viên mới. Trong đó mã số ứng viên phải bắt đầu là C, theo sau ít nhất là 3 ký số."
						+ "5. Tính số năm làm việc trên một vị trí công việc nào đó khi biết mã số ứng viên.\n"
						+ "6. Liệt kê danh sách các ứng viên và danh sách bằng cấp của từng ứng viên.\n"
						+ "7. Thoát chương trình.");
				
				choice = sc.nextInt();
				out.writeInt(choice);
				out.flush();
				switch (choice) {
					case 1:
						sc.nextLine();
						System.out.println("Enter position name: ");
						String positionName = sc.next();
						out.writeUTF(positionName);
						out.flush();
						System.out.println("Enter salary from: ");
						double salaryFrom = sc.nextDouble();
						out.writeDouble(salaryFrom);
						out.flush();
						System.out.println("Enter salary to: ");
						double salaryTo = sc.nextDouble();
						out.writeDouble(salaryTo);
						out.flush();
						List<Position> list = (List<Position>) in.readObject();
						list.forEach(System.out::println);
						break;
	
					case 2:
						Map<Candidate, Long> list1 = (Map<Candidate, Long>) in.readObject();
						list1.forEach((k, v) -> {
							System.out.println(k);
							System.out.println(v);
						});
						break;
						
					case 3:
						Map<Candidate, Position> list2 = (Map<Candidate, Position>) in.readObject();
						list2.forEach((k, v) -> {
							System.out.println(k);
							System.out.println(v);
						});
						break;
					case 4:
                        sc.nextLine();
                        System.out.println("Enter candidate id: ");
                        String id = sc.nextLine();
                        out.writeUTF(id);
                        out.flush();
                        System.out.println("Enter candidate name: ");
                        String name = sc.nextLine();
                        out.writeUTF(name);
                        out.flush();
                        System.out.println("Enter candidate birth year: ");
                        int birthYear = sc.nextInt();
                        out.writeInt(birthYear);
                        out.flush();
                        sc.nextLine();
                        System.out.println("Enter candidate gender: ");
                        String gender = sc.nextLine();
                        out.writeUTF(gender);
                        out.flush();
                        System.out.println("Enter candidate email: ");
                        String email = sc.nextLine();
                        out.writeUTF(email);
                        out.flush();
                        System.out.println("Enter candidate phone: ");
                        String phone = sc.nextLine();
                        out.writeUTF(phone);
                        out.flush();
                        System.out.println("Enter candidate description: ");
                        String desc = sc.nextLine();
                        out.writeUTF(desc);
                        out.flush();
                        boolean result = in.readBoolean();
						if (result) {
							System.out.println("Add candidate successfully!");
							List<Candidate> lst = (List<Candidate>) in.readObject();
							lst.forEach(System.out::println);
						} else {
							System.out.println("Add candidate failed!");
						}
                        break;
                   case 5:
						sc.nextLine();
						System.out.println("Enter candidate id: ");
						String id1 = sc.nextLine();
						out.writeUTF(id1);
						out.flush();
						long year = in.readLong();
						System.out.println("Year: " + year);
						break;
				    case 6:
				    	Map<Candidate, Set<Certificate>> list4 = (Map<Candidate, Set<Certificate>>) in.readObject();
				    	list4.forEach((k, v) -> {
							System.out.println(k);
							System.out.println(v);
						});
				}
			}
			
			
		}catch(

	Exception e)
	{
		e.printStackTrace();
	}

}}
