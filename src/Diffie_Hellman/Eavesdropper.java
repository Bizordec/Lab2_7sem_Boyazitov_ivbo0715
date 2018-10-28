package Diffie_Hellman;

public class Eavesdropper {
    private int p;
    private int g;
    private int OpenKeyA;
    private int OpenKeyB;

    public void copyParameters(int[] parameters) {
        p = parameters[0];
        g = parameters[1];
    }

    public int[] getParameters() {
        return new int[]{p, g};
    }

    public void copyOpenKeyA(int key) {
        OpenKeyA = key;
    }

    public int getOpenKeyA() {
        return OpenKeyA;
    }

    public void copyOpenKeyB(int key) {
        OpenKeyB = key;
    }

    public int getOpenKeyB() {
        return OpenKeyB;
    }
}
