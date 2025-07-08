package codelets;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.MemoryObject;
import memory.ValueHolder;

public class ActuatorCodelet extends Codelet {

    private MemoryObject in;
    private MemoryObject out;

    @Override
    public void accessMemoryObjects() {
        in = (MemoryObject) getInput("PROCESSED");
        out = (MemoryObject) getOutput("OUTPUT");
    }

    @Override
    public void proc() {
        ValueHolder processed = (ValueHolder) in.getI();
        ValueHolder result = (ValueHolder) out.getI();
        result.value = processed.value + 1;
    }

    @Override
    public void calculateActivation() {}
}

