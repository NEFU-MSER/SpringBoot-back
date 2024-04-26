package org.kukuking.back.utils;

public enum Gender {
    Male, Female;

    public int toInt() {
        return this.ordinal();
    }
}
