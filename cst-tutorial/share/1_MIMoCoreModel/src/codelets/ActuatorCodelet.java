import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.MemoryObject;

public class ActuatorCodelet extends Codelet {

    private MemoryObject actionMO;

    public ActuatorCodelet() {
        super();
        // Inicialize a memória necessária
        actionMO = new MemoryObject();
        actionMO.setI(new ValueHolder(0));
    }

    @Override
    public void proc() {
        // Implementar a lógica do codelet
        System.out.println("ActuatorCodelet processando...");

        // Acessar a memória e fazer algo com os dados
        if (actionMO != null) {
            System.out.println("Ação: " + actionMO.getI());
        }
    }
@Override
    public void calculateActivation(){}

    @Override
    public void accessMemoryObjects(){}
}
