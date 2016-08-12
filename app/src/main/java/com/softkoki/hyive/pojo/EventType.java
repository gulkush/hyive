package com.softkoki.hyive.pojo;

/**
 * Created by gulkush on 8/12/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventType {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("val")
    @Expose
    private String val;
    @SerializedName("value")
    @Expose
    private String value;

    /**
     *
     * @return
     * The key
     */
    public String getKey() {
        return key;
    }

    /**
     *
     * @param key
     * The key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     *
     * @return
     * The val
     */
    public String getVal() {
        return val;
    }

    /**
     *
     * @param val
     * The val
     */
    public void setVal(String val) {
        this.val = val;
    }

    /**
     *
     * @return
     * The value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(String value) {
        this.value = value;
    }

}
