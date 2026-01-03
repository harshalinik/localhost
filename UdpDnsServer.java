import java.net.*;
public class UdpDnsServer {
public static void main(String[] args) {
try (DatagramSocket server = new DatagramSocket(7070)) {
System.out.println("UDP DNS Server is running on port 7070...");
byte[] buffer = new byte[1024];
while (true) {
DatagramPacket request = new DatagramPacket(buffer,
buffer.length);
server.receive(request);
String domain = new String(request.getData(), 0,
request.getLength());
System.out.println("Received domain:" + domain);
String ip;
try {
ip = InetAddress.getByName(domain).getHostAddress();
} catch (Exception e) {
ip = "Invalid Domain";
}
byte[] responseData = ip.getBytes();
DatagramPacket response = new DatagramPacket(
responseData,
responseData.length,
request.getAddress(),
request.getPort()
);
server.send(response);
System.out.println("Sent IP:" + ip);
}
} catch (Exception e) {
System.out.println("Server error");
}
}
}
