package ua.nure.riznyk.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Explain {
    private String id;
    private String selectType;
    private String table;
    private String type;
    private String possibleKeys;
    private String key;
    private String key_len;
    private String ref;
    private String rows;
    private String extra;
}
