package codelets;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.MemoryObject;
import memory.ValueHolder;

import java.io.IOException;  // Para a classe IOException
import java.net.Socket;      // Para a classe Socket
import java.io.BufferedReader;  // Para a classe BufferedReader
import java.io.InputStreamReader; // Para a classe InputStreamReader

public class SensorCodelet extends Codelet {
    private Socket socket;
    private BufferedReader in;
    private MemoryObject obsMO;

    public SensorCodelet(String host, int port, MemoryObject obsMO) {
        this.obsMO = obsMO;
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SensorCodelet() {
        // Construtor padrão sem parâmetros
        super();
    }

    public void proc() {
         // Certifique-se de que a variável `in` foi inicializada corretamente
        try {
            // Verifique se a memória foi configurada corretamente
            if (this.socket == null) {
                // Inicialize a variável `in` com uma fonte válida de entrada, por exemplo, um Socket
                socket = new Socket("localhost", 8080);  // Exemplo de inicialização
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            }

            // Processar a linha lida
            String line = in.readLine();
            if (line != null) {
                System.out.println("Received: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	
    @Override
    public void calculateActivation() {}

    @Override
    public void	accessMemoryObjects() {}
}

