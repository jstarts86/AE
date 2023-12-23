package edu.handong.csee.plt.defsub;

import edu.handong.csee.plt.ast.NewBox;

public class ARecSub extends DefrdSub{
    String name;

    NewBox valueBox;

    DefrdSub ds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NewBox getValueBox() {
        return valueBox;
    }

    public void setValueBox(NewBox valueBox) {
        this.valueBox = valueBox;
    }

    public DefrdSub getDs() {
        return ds;
    }

    public void setDs(DefrdSub ds) {
        this.ds = ds;
    }

    public ARecSub(String name, NewBox valueBox, DefrdSub ds) {
        this.name = name;
        this.valueBox = valueBox;
        this.ds = ds;
    }
}
