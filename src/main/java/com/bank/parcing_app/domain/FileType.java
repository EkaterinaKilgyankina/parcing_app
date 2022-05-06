package com.bank.parcing_app.domain;

import java.util.Arrays;

public enum FileType {
    CSV(".csv"),
    JSON(".json");

    private final String endWith;

    FileType(String endWith) {
        this.endWith = endWith;
    }

    public static FileType resolveByFileName(String fileName) {
        return Arrays.stream(values())
                .filter(e -> fileName.endsWith(e.endWith))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("file format not supported"));
    }
}
