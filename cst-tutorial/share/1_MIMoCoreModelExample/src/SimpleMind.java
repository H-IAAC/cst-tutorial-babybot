import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.entities.Mind;
import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.util.viewer.MindViewer;
import memory.ValueHolder;
import codelets.*;

public class SimpleMind extends Mind {

    public SimpleMind() {
        super();
	
        // Criar memória
        MemoryObject inputMO = createMemoryObject("INPUT", new ValueHolder(0));
        MemoryObject processedMO = createMemoryObject("PROCESSED", new ValueHolder(0));
        MemoryObject outputMO = createMemoryObject("OUTPUT", new ValueHolder(0));

        // Visualização
	System.out.println("codelets:"+getCodeRack().getAllCodelets());
        //MindViewer mv = new MindViewer(this, "MIMo Core Model (Sem WS3D)", getCodeRack().getAllCodelets());
        //mv.setVisible(true);

        // Codelets
        Codelet sensor = new SensorCodelet();
        sensor.addOutput(inputMO);
        insertCodelet(sensor);

        Codelet processor = new ProcessingCodelet();
        processor.addInput(inputMO);
        processor.addOutput(processedMO);
        insertCodelet(processor);

        Codelet actuator = new ActuatorCodelet();
        actuator.addInput(processedMO);
        actuator.addOutput(outputMO);
        insertCodelet(actuator);

        start();
    }
}

