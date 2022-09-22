package server;

@SuppressWarnings("resource")
public class AplicacaoServer {

	public static void main(String[] args) throws Exception {
		if(args.length != 1) {
			System.out.println("Argumento: <porta>\n");
			return;
		}

		var server = new TCPServer(Integer.parseInt(args[0]));
			server.startServer();
	}

}
