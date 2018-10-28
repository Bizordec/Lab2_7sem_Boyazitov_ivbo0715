package Diffie_Hellman;

public class Channel {

    private int p;
    private int g;
    private int OpenKey;

    public Channel() {

    }

    public void transferParameters(Object object) {
        if (object instanceof User) {
            User user = (User) object;
            p = user.getParameters()[0];
            g = user.getParameters()[1];
        } else
            System.out.println("\"You don't have permission to do that!\"");
    }

    public int[] readParameters() {
        return new int[]{p, g};
    }

    public void transferOpenKey(Object object) {
        if (object instanceof User) {
            User user = (User) object;
            OpenKey = user.calculateOpenKey();
        } else
            System.out.println("\"You don't have permission to do that!\"");
    }

    public int readOpenKey() {
        return OpenKey;
    }
}
