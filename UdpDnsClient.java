import java.net.*;
import java.util.Scanner;
public class UdpDnsClient {
public static void main(String[] args) {
try (DatagramSocket socket = new DatagramSocket();
Scanner sc = new Scanner(System.in)) {
while (true) {
System.out.print("Enter domain name (or 'quit' to exit): ");
String domain = sc.nextLine();
if (domain.equalsIgnoreCase("quit")) {
System.out.println("Client closed.");
break;
}
DatagramPacket request = new DatagramPacket(
domain.getBytes(),
domain.length(),
InetAddress.getByName("127.0.0.1"),
7070
);
socket.send(request);
byte[] buffer = new byte[1024];
DatagramPacket response = new DatagramPacket(buffer,
buffer.length);
socket.receive(response);
String ip = new String(response.getData(), 0,
response.getLength());
System.out.println("Server reply: "+ ip);
}
} catch (Exception e) {
System.out.println("Client error");
}
}
}
