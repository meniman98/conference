package com.jack.huncho.conference.model;

public class Mango {
    private boolean isMangoWashed = false;
    private boolean isMangoPeeled = false;
    private String mangoState;

    public void washMango() {
       this.isMangoWashed = true;
    }

    public void peelMango() {
        this.isMangoPeeled = true;
    }

    public void cutMango() {
        this.mangoState = "Your mango is now cut and juicy";
    }

    public String getMangoState() {
        return mangoState;
    }

    public void setMangoState(String mangoState) {
        this.mangoState = mangoState;
    }

    public boolean isMangoWashed() {
        return isMangoWashed;
    }

    public void setMangoWashed(boolean mangoWashed) {
        isMangoWashed = mangoWashed;
    }

    public boolean isMangoPeeled() {
        return isMangoPeeled;
    }

    public void setMangoPeeled(boolean mangoPeeled) {
        isMangoPeeled = mangoPeeled;
    }

    @Override
    public String toString() {
        return "Mango{" +
                "mangoState='" + mangoState + '\'' +
                '}';
    }
}
