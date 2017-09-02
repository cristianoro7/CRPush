package com.desperado.server.security;


import java.util.Base64;

/**
 * Created by desperado on 17-8-31.
 */
public class KeyStore {

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtCLZmm4FaKDIrjo9Gl7SF7AzlHiPkaOotGHeAIyT3LpAQIdwJk4Z+FAUzR/8xPmnXv03Jpml9Ku3feEPFCNx4tLMDcFp79Nb/9fYlhIubEuV9wphRXlIzauCSIFPHW82b5gyQIbQUqkg8uTYu1mY4lRolgV/HTllTJfcqBQvJKQIDAQAB"; //公开的公钥

    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK0ItmabgVooMiuOj0aXtIXsDOUeI+Ro6i0Yd4AjJPcukBAh3AmThn4UBTNH/zE+ade/TcmmaX0q7d94Q8UI3Hi0swNwWnv01v/19iWEi5sS5X3CmFFeUjNq4JIgU8dbzZvmDJAhtBSqSDy5Ni7WZjiVGiWBX8dOWVMl9yoFC8kpAgMBAAECgYEAnVBhfLH+jTgkPMN0cWq1Xlh4dMnNOflEhlFLO/03u17i7TfLdA1i5jdPbwsS8PNciSDNGPspxvmDoHsXqghfYAKZiO11Zd9t6r8UaxiPSEtW8uqDag+nxz7LaB13npKvZEAVoFpW5zrBHiwr6PMtNiYltRUiHaSLj7NBLS4mX/0CQQDjXi+cxgwx7f89i3eiPySqeasK8y+cVi2tqzDkBMFpRxqriwCJd12a5HHkqo3vZGjtSuHyWsFUErsunEXxTP6rAkEAwtLtosCwew9r7oAtpUccFE6Y11W2XrsnZC6Bei4IyTtMdN/89WkatxpwIRraTbk1AB3kxyg+MeKEkXWJP0RHewJAVMP7ejY3u7GgE+DYFCv4TnoXs3hgiqdyVinZi1jehpJUthmXdKiExEdg0sBnNBW6LKVbQVBmfSKSmFj4lnJ/CwJAeCJQ3rcrG/caeusXOn9q1SrSHJaB6lBFrgTvWkPgrxWdw8QK29l/28C1u/QvARZflD7av+QU1fX0AS4U/6o+2QJBALqy3PgkzerZl9g70saivs7MGkxV1Y/WjOMozWwuRwF2BPXg1TN1ba7K+rpzKXLzbriR8BbvV3oIZaZlb7/4W+E=";

    public static byte[] getPublicKey() {
        return Base64.getDecoder().decode(PUBLIC_KEY);
    }

    public static byte[] getPrivateKey() {
        return Base64.getDecoder().decode(PRIVATE_KEY);
    }
}
