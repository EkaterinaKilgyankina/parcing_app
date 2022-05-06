package com.bank.parcing_app.service;

import com.bank.parcing_app.domain.FileType;

import java.io.File;

public interface ParsingService {

    void parse(File file);

    FileType getType();
}
