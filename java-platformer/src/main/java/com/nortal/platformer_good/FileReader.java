package com.nortal.platformer_good;

import java.io.IOException;
import java.util.List;

public interface FileReader<T> {

    List<T> read() throws IOException;

}
