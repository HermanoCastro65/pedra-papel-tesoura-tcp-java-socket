package client;

public class AplicacaoClient {

	public static void main(String[] args) throws Exception {
		if(args.length != 1) {
			System.out.println("Argumentos: <porta>\n");
			return;
		}
		
		var client = new TCPClient("00000000", Integer.parseInt(args[0]));
		client.start();
	}
}
