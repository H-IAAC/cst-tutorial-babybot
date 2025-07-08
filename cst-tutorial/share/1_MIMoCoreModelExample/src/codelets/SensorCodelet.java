package codelets;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.MemoryObject;
import memory.ValueHolder;

public class SensorCodelet extends Codelet {

    private MemoryObject out;

    @Override
    public void accessMemoryObjects() {
        out = (MemoryObject) getOutput("INPUT");
    }

    @Override
    public void proc() {
        ValueHolder val = (ValueHolder) out.getI();
        val.value++;
    }

    @Override
    public void calculateActivation() {}
}

