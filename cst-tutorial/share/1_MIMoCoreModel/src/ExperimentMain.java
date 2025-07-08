import java.io.*;
import java.net.*;
import java.util.*;

public class ExperimentMain {
    public static void main(String[] args) throws Exception{
        Mind mind = new Mind();

        // Memória compartilhada
        MemoryObject obsMO = mind.createMemoryObject("OBSERVATION", "");
        MemoryObject actionMO = mind.createMemoryObject("ACTION", "");

        // Adiciona os codelets
        mind.insertCodelet(new SensorCodelet("localhost", 5000, obsMO));
        mind.insertCodelet(new ActuatorCodelet("localhost", 5000, actionMO));

        // Codelet de controle (gera ações a partir de observações)
        mind.insertCodelet(new ProcessingCodelet(obsMO, actionMO));

        // Inicia execução
        mind.startMind();
    }
}

