package com.lrn.nettymysqlprotocol.protocol;

public enum CharacterSetEnum {
    big5_chinese_ci(1),
    latin2_czech_cs(2),
    dec8_swedish_ci(3),
    cp850_general_ci(4),
    latin1_german1_ci(5),
    hp8_english_ci(6),
    koi8r_general_ci(7),
    latin1_swedish_ci(8),
    latin2_general_ci(9),
    swe7_swedish_ci(10),
    utf8_general_ci(33),
    binary(63);

    protected int value;

    CharacterSetEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
