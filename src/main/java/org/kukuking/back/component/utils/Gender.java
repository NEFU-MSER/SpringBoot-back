package org.kukuking.back.component.utils;

public enum Gender {
    Male, Female;

    public int toInt() {
        return this.ordinal();
    }
}
