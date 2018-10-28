package Diffie_Hellman;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {
    private int p;
    private int g;
    private BigInteger big_p;
    private BigInteger big_g;
    private BigInteger big_secret;
    private BigInteger big_open;

    private Random random = new Random();

    public void generateParameters() {
        p = getPrime();
        big_p = BigInteger.valueOf(p);
        //  1 < g < (p - 1)
        g = 2 + random.nextInt((p - 1) - 2);
        big_g = BigInteger.valueOf(g);
    }

    public int[] getParameters() {
        return new int[]{p, g};
    }

    public void setParameters(int[] parameters) {
        p = parameters[0];
        big_p = BigInteger.valueOf(p);
        g = parameters[1];
        big_g = BigInteger.valueOf(g);
    }

    public void generateSecretKey() {
        //  SecretKey < p
        big_secret = BigInteger.valueOf(random.nextInt(p));
    }

    public int getSecretKey() {
        return big_secret.intValue();
    }

    public int calculateOpenKey() {
        //  g^SecretKey % p
        return big_g.modPow(big_secret, big_p).intValue();
    }

    public void setOpenKey(int key) {
        big_open = BigInteger.valueOf(key);
    }

    public int getOpenKey() {
        return big_open.intValue();
    }

    public int getSecretSharedKey() {
        //  OpenKey^SecretKey % p
        return big_open.modPow(big_secret, big_p).intValue();
    }

    private int getPrime() {
        List<Integer> listPrime = new ArrayList<>();
        int n = 1000;
        boolean[] isPrime = new boolean[n + 1];

        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        for (int factor = 2; (factor * factor) <= n; factor++) {
            if (isPrime[factor]) {
                for (int j = factor; (factor * j) <= n; j++) {
                    isPrime[factor * j] = false;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                listPrime.add(i);
            }
        }

        return listPrime.get(random.nextInt(listPrime.size()));
    }
}
