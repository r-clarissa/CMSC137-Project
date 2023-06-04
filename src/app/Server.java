package app;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;

	public Server(ServerSocket serverSocket){
		this.serverSocket = serverSocket;
	}

	public void startServer(){

		try{
			while(!serverSocket.isClosed()){
				Socket socket=serverSocket.accept(); //waiting for client to connect
				System.out.println("A new Client has connected");

				ClientHandler clientHandler= new ClientHandler(socket); //each object of this class will be responsible with communicating with the client

				Thread thread= new Thread(clientHandler);

				thread.start();

			}
		}catch (IOException e){

		}
	}

	public void closeServerSocket(){
		try{
			if (serverSocket != null){
				serverSocket.close();
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}


	public static void main(String[] args){

		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(9992);
			Server server = new Server(serverSocket);
			server.startServer();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

}

