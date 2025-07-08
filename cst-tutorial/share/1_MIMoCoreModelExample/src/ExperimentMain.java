import java.io.*;
import java.net.*;
import java.util.*;

public class ExperimentMain {
    public static void main(String[] args) throws Exception{
        new SimpleMind();
	Socket socket = new Socket("localhost", 5000);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Exemplo de ação de 3 dimensões
        String action = "[0.0, 0.5]";
        out.println(action);

        // Recebe observação
        String obs = in.readLine();
        System.out.println("Obs from MIMo: " + obs);

        in.close();
        out.close();
        socket.close();
    }
}

