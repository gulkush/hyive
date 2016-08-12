package com.softkoki.hyive.pojo;

/**
 * Created by gulkush on 8/12/2016.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entry {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("updated")
    @Expose
    private Integer updated;
    @SerializedName("ordering_count")
    @Expose
    private String orderingCount;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("parsed")
    @Expose
    private String parsed;
    @SerializedName("keywords")
    @Expose
    private String keywords;
    @SerializedName("author_id")
    @Expose
    private String authorId;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("updated_on")
    @Expose
    private String updatedOn;
    @SerializedName("comments_enabled")
    @Expose
    private String commentsEnabled;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("preview_hash")
    @Expose
    private String previewHash;
    @SerializedName("event_date")
    @Expose
    private String eventDate;
    @SerializedName("event_image")
    @Expose
    private EventImage eventImage;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("address_text")
    @Expose
    private String addressText;
    @SerializedName("event_type")
    @Expose
    private EventType eventType;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("time_available")
    @Expose
    private String timeAvailable;
    @SerializedName("address_original_text")
    @Expose
    private String addressOriginalText;
    @SerializedName("created_by")
    @Expose
    private CreatedBy createdBy;
    @SerializedName("partner")
    @Expose
    private Partner partner;
    @SerializedName("more_photos")
    @Expose
    private List<Object> morePhotos = new ArrayList<Object>();
    @SerializedName("last")
    @Expose
    private String last;
    @SerializedName("odd_even")
    @Expose
    private String oddEven;
    @SerializedName("count")
    @Expose
    private Integer count;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The created
     */
    public Integer getCreated() {
        return created;
    }

    /**
     *
     * @param created
     * The created
     */
    public void setCreated(Integer created) {
        this.created = created;
    }

    /**
     *
     * @return
     * The updated
     */
    public Integer getUpdated() {
        return updated;
    }

    /**
     *
     * @param updated
     * The updated
     */
    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    /**
     *
     * @return
     * The orderingCount
     */
    public String getOrderingCount() {
        return orderingCount;
    }

    /**
     *
     * @param orderingCount
     * The ordering_count
     */
    public void setOrderingCount(String orderingCount) {
        this.orderingCount = orderingCount;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     *
     * @param slug
     * The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     *
     * @return
     * The categoryId
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     *
     * @param categoryId
     * The category_id
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     *
     * @return
     * The body
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * @param body
     * The body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     *
     * @return
     * The parsed
     */
    public String getParsed() {
        return parsed;
    }

    /**
     *
     * @param parsed
     * The parsed
     */
    public void setParsed(String parsed) {
        this.parsed = parsed;
    }

    /**
     *
     * @return
     * The keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     *
     * @param keywords
     * The keywords
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     *
     * @return
     * The authorId
     */
    public String getAuthorId() {
        return authorId;
    }

    /**
     *
     * @param authorId
     * The author_id
     */
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    /**
     *
     * @return
     * The createdOn
     */
    public String getCreatedOn() {
        return createdOn;
    }

    /**
     *
     * @param createdOn
     * The created_on
     */
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    /**
     *
     * @return
     * The updatedOn
     */
    public String getUpdatedOn() {
        return updatedOn;
    }

    /**
     *
     * @param updatedOn
     * The updated_on
     */
    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    /**
     *
     * @return
     * The commentsEnabled
     */
    public String getCommentsEnabled() {
        return commentsEnabled;
    }

    /**
     *
     * @param commentsEnabled
     * The comments_enabled
     */
    public void setCommentsEnabled(String commentsEnabled) {
        this.commentsEnabled = commentsEnabled;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The previewHash
     */
    public String getPreviewHash() {
        return previewHash;
    }

    /**
     *
     * @param previewHash
     * The preview_hash
     */
    public void setPreviewHash(String previewHash) {
        this.previewHash = previewHash;
    }

    /**
     *
     * @return
     * The eventDate
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     *
     * @param eventDate
     * The event_date
     */
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    /**
     *
     * @return
     * The eventImage
     */
    public EventImage getEventImage() {
        return eventImage;
    }

    /**
     *
     * @param eventImage
     * The event_image
     */
    public void setEventImage(EventImage eventImage) {
        this.eventImage = eventImage;
    }

    /**
     *
     * @return
     * The address
     */
    public Address getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The addressText
     */
    public String getAddressText() {
        return addressText;
    }

    /**
     *
     * @param addressText
     * The address_text
     */
    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }

    /**
     *
     * @return
     * The eventType
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     *
     * @param eventType
     * The event_type
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     *
     * @return
     * The endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate
     * The end_date
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * @return
     * The timeAvailable
     */
    public String getTimeAvailable() {
        return timeAvailable;
    }

    /**
     *
     * @param timeAvailable
     * The time_available
     */
    public void setTimeAvailable(String timeAvailable) {
        this.timeAvailable = timeAvailable;
    }

    /**
     *
     * @return
     * The addressOriginalText
     */
    public String getAddressOriginalText() {
        return addressOriginalText;
    }

    /**
     *
     * @param addressOriginalText
     * The address_original_text
     */
    public void setAddressOriginalText(String addressOriginalText) {
        this.addressOriginalText = addressOriginalText;
    }

    /**
     *
     * @return
     * The createdBy
     */
    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    /**
     *
     * @param createdBy
     * The created_by
     */
    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    /**
     *
     * @return
     * The partner
     */
    public Partner getPartner() {
        return partner;
    }

    /**
     *
     * @param partner
     * The partner
     */
    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    /**
     *
     * @return
     * The morePhotos
     */
    public List<Object> getMorePhotos() {
        return morePhotos;
    }

    /**
     *
     * @param morePhotos
     * The more_photos
     */
    public void setMorePhotos(List<Object> morePhotos) {
        this.morePhotos = morePhotos;
    }

    /**
     *
     * @return
     * The last
     */
    public String getLast() {
        return last;
    }

    /**
     *
     * @param last
     * The last
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     *
     * @return
     * The oddEven
     */
    public String getOddEven() {
        return oddEven;
    }

    /**
     *
     * @param oddEven
     * The odd_even
     */
    public void setOddEven(String oddEven) {
        this.oddEven = oddEven;
    }

    /**
     *
     * @return
     * The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     *
     * @param count
     * The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

}
