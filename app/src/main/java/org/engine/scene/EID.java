package org.engine.scene;

public record EID(int value) {
    @Override
    public int hashCode() {
        return value;
    }
}
