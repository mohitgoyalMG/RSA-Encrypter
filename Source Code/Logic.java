package rsa;

import java.math.*;;  
import java.util.Random;

public class Logic 
{
    boolean f1=false, f2=false;
    Random rng = new Random();  
    public BigInteger p, q, n, v, x, k, d;            
    int bitlength = 256;
    //Public and Private key generation
    public void rsa()
    {
        p = BigInteger.probablePrime(bitlength, rng);
        q = BigInteger.probablePrime(bitlength, rng);
        n=p.multiply(q);
        v=((p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE))));
        while(f1!=true&&f2!=true)
        {
            k = BigInteger.probablePrime(bitlength/2, rng);
            odevn(k);
            f2=(k.gcd(v)).equals(BigInteger.ONE);
        }
        d = k.modInverse(v);
        System.out.println(n);
        System.out.println(k);
        System.out.println(d);
        System.out.println("Public Key : "+k+","+n);
        System.out.println("Private Key : "+d+","+n);
    }
    
    //Checks whether k is odd or not
    public Boolean odevn(BigInteger a)
    {
	if(a!=BigInteger.ZERO)
	{
            BigInteger b=a.mod(BigInteger.ONE.add(BigInteger.ONE));
            if(b==BigInteger.ZERO)
            {
                f1=false;
            }
            else
            {
                f1=true;
            }
	}
	else
	{
            f1=false;
	}
        return f1;
    }
    
    //Encryption
    public BigInteger encrypt(byte[] message, BigInteger publicK, BigInteger publicN)
    {  
        k=publicK;
        n=publicN;
        return (new BigInteger(message)).modPow(k, n);
    }
    
    //Decryption
    public byte[] decrypt(byte[] message, BigInteger privateD, BigInteger privateN)
    {
        d=privateD;
        n=privateN;
        return (new BigInteger(message)).modPow(d, n).toByteArray();
    }
}
