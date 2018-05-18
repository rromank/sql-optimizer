package ua.nure.riznyk.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExplainPlan {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPossibleKeys() {
        return possibleKeys;
    }

    public void setPossibleKeys(String possibleKeys) {
        this.possibleKeys = possibleKeys;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey_len() {
        return key_len;
    }

    public void setKey_len(String key_len) {
        this.key_len = key_len;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
