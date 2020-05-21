package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class UserThread01 extends Thread{
	Socket sock = null;
	public UserThread01(Socket sock) {
		System.out.println(sock.getInetAddress() + "���� �����߽��ϴ�.");
		this.sock = sock;
		start();
	}
	public void run() {
		InputStream in;
		String readData = null;
		try {
			in = sock.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			while(true) {
				readData = dis.readUTF();
				for(Socket s : ChatServer.s) {
					OutputStream out = s.getOutputStream();
					DataOutputStream dos = new DataOutputStream(out);
					dos.writeUTF(readData);
				}
			
			}
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("���� ������ : " + readData);
		}
	}
}
public class ChatServer {
	public static ArrayList<Socket> s = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		ServerSocket server = new ServerSocket(12345);
		int i = 0;
		while(true) {
			System.out.println("������ ��ٸ��ϴ�.");
			s.add(server.accept());
			new UserThread01(s.get(i));
			i++;
		}
	}
}
