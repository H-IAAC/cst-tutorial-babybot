import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.MemoryObject;

public class ProcessingCodelet extends Codelet {

    private MemoryObject obsMO;

    public ProcessingCodelet() {
        super();
        // Inicialize a memória necessária
        obsMO = new MemoryObject();
        obsMO.setI(new ValueHolder(0)); // Defina o valor da memória conforme necessário
    }

    @Override
    public void proc() {
        // Implementar a lógica do codelet
        System.out.println("ProcessingCodelet processando...");

        // Acessar a memória e fazer algo com os dados
        if (obsMO != null) {
            System.out.println("Obs: " + obsMO.getI());
        }
    }

    @Override
    public void calculateActivation(){}

    @Override
    public void accessMemoryObjects(){}
}