package client;

public class AplicacaoClient {

	public static void main(String[] args) throws Exception {
		if(args.length != 2) {
			System.out.println("Argumentos: <host> <porta>\n");
			return;
		}
		
		var client = new TCPClient(args[0], Integer.parseInt(args[1]));
		client.start();
	}
}
