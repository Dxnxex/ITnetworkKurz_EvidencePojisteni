package cz.dxnxex.evidencepojisteni;

import lombok.Getter;

public class EvidenceConfiguration {


    public static final String PATH_USER = "user";
    public static final String PATH_INSURANCE = "insurance";
    public static final String PATH_ACCOUNT = "account";

    public static final String CZECH_ALPHABET_REGEX = "^[a-zA-Zá-žÁ-Ž ]+$";
    public static final  String CZECH_ALPHABET_REGEX_MESSAGE= "Text může obsahovat pouze písmena a mezery, bez čísel a speciálních znaků";

    public static final int SIZE_MAX_NAME = 50;
    public static final int SIZE_MIN_NAME = 2;


}
