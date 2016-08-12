package com.softkoki.hyive.pojo;

/**
 * Created by gulkush on 8/12/2016.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventStreamResponse {

    @SerializedName("entries")
    @Expose
    private List<Entry> entries = new ArrayList<Entry>();
    @SerializedName("pagination")
    @Expose
    private Object pagination;
    @SerializedName("total")
    @Expose
    private Integer total;

    /**
     *
     * @return
     * The entries
     */
    public List<Entry> getEntries() {
        return entries;
    }

    /**
     *
     * @param entries
     * The entries
     */
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    /**
     *
     * @return
     * The pagination
     */
    public Object getPagination() {
        return pagination;
    }

    /**
     *
     * @param pagination
     * The pagination
     */
    public void setPagination(Object pagination) {
        this.pagination = pagination;
    }

    /**
     *
     * @return
     * The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

}
