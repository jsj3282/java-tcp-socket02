package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient01 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner input = new Scanner(System.in);
		Socket sock = new Socket("127.0.0.1", 12345);
		OutputStream out = sock.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		String readData;
		
		ChatReceiver rvc = new ChatReceiver(sock);
		rvc.start();
		
		while(true) {
			System.out.print("송신 문자열 입력 : ");
			String data = input.next();
			dos.writeUTF(data);
		}
//		dos.close();
//		out.close();
//		sock.close();
	}
}
