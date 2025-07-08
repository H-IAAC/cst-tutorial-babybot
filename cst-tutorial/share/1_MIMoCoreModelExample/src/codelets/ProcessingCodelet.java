package codelets;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.MemoryObject;
import memory.ValueHolder;

public class ProcessingCodelet extends Codelet {

    private MemoryObject in;
    private MemoryObject out;

    @Override
    public void accessMemoryObjects() {
        in = (MemoryObject) getInput("INPUT");
        out = (MemoryObject) getOutput("PROCESSED");
    }

    @Override
    public void proc() {
        ValueHolder inputVal = (ValueHolder) in.getI();
        ValueHolder outputVal = (ValueHolder) out.getI();
        outputVal.value = inputVal.value * 2;
    }

    @Override
    public void calculateActivation() {}
}

