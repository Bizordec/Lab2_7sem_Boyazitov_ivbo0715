package Main;

import Diffie_Hellman.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Channel channel = new Channel();
        User Alice = new User();
        User Bob = new User();
        Eavesdropper Eva = new Eavesdropper();

        System.out.println("Eva tried to transfer her p and g: ");
        channel.transferParameters(Eva);

        System.out.println();

        Alice.generateParameters();
        channel.transferParameters(Alice);
        System.out.println("Alice generated and transferred p and g: " + Arrays.toString(Alice.getParameters()));
        Bob.setParameters(channel.readParameters());
        System.out.println("Bob got p and g: " + Arrays.toString(Bob.getParameters()));
        Eva.copyParameters(channel.readParameters());
        System.out.println("Eva eavesdropped p and g: " + Arrays.toString(Eva.getParameters()));

        System.out.println();

        Alice.generateSecretKey();
        System.out.println("Alice generated her secret key: " + Alice.getSecretKey());
        Bob.generateSecretKey();
        System.out.println("Bob generated his secret key: " + Bob.getSecretKey());

        System.out.println();

        System.out.println("Eva tried to transfer her open key: ");
        channel.transferOpenKey(Eva);

        System.out.println();

        channel.transferOpenKey(Alice);
        System.out.println("Alice calculated and transferred her open key: " + Alice.calculateOpenKey());
        Bob.setOpenKey(channel.readOpenKey());
        System.out.println("Bob got Alice's open key: " + Bob.getOpenKey());
        Eva.copyOpenKeyA(channel.readOpenKey());
        System.out.println("Eva eavesdropped Alice's open key: " + Eva.getOpenKeyA());

        System.out.println();

        channel.transferOpenKey(Bob);
        System.out.println("Bob calculated and transferred his open key: " + Bob.calculateOpenKey());
        Alice.setOpenKey(channel.readOpenKey());
        System.out.println("Alice got Bob's open key: " + Alice.getOpenKey());
        Eva.copyOpenKeyB(channel.readOpenKey());
        System.out.println("Eva eavesdropped Bob's open key: " + Eva.getOpenKeyB());

        System.out.println();

        int aliceSecretKey = Alice.getSecretSharedKey();
        int bobSecretKey = Bob.getSecretSharedKey();
        System.out.println("Alice's secret key: " + aliceSecretKey);
        System.out.println("Bob's secret key: " + bobSecretKey);
        if (aliceSecretKey == bobSecretKey)
            System.out.println("Keys matched!");
        else
            System.out.println("Keys didn't match!");
    }
}
