package com.softkoki.hyive.pojo;

/**
 * Created by gulkush on 10/16/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("salt")
    @Expose
    private String salt;
    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("ip_address")
    @Expose
    private String ipAddress;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("activation_code")
    @Expose
    private String activationCode;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("last_login")
    @Expose
    private String lastLogin;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("forgotten_password_code")
    @Expose
    private String forgottenPasswordCode;
    @SerializedName("remember_code")
    @Expose
    private String rememberCode;
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("group_description")
    @Expose
    private String groupDescription;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("subscription_end")
    @Expose
    private Object subscriptionEnd;
    @SerializedName("plan")
    @Expose
    private Object plan;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("updated_on")
    @Expose
    private String updatedOn;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("profile_id")
    @Expose
    private String profileId;

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
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     * The salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     *
     * @param salt
     * The salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     *
     * @return
     * The groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     *
     * @param groupId
     * The group_id
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     *
     * @return
     * The ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     *
     * @param ipAddress
     * The ip_address
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     *
     * @return
     * The active
     */
    public String getActive() {
        return active;
    }

    /**
     *
     * @param active
     * The active
     */
    public void setActive(String active) {
        this.active = active;
    }

    /**
     *
     * @return
     * The activationCode
     */
    public String getActivationCode() {
        return activationCode;
    }

    /**
     *
     * @param activationCode
     * The activation_code
     */
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
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
     * The lastLogin
     */
    public String getLastLogin() {
        return lastLogin;
    }

    /**
     *
     * @param lastLogin
     * The last_login
     */
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The forgottenPasswordCode
     */
    public String getForgottenPasswordCode() {
        return forgottenPasswordCode;
    }

    /**
     *
     * @param forgottenPasswordCode
     * The forgotten_password_code
     */
    public void setForgottenPasswordCode(String forgottenPasswordCode) {
        this.forgottenPasswordCode = forgottenPasswordCode;
    }

    /**
     *
     * @return
     * The rememberCode
     */
    public String getRememberCode() {
        return rememberCode;
    }

    /**
     *
     * @param rememberCode
     * The remember_code
     */
    public void setRememberCode(String rememberCode) {
        this.rememberCode = rememberCode;
    }

    /**
     *
     * @return
     * The group
     */
    public String getGroup() {
        return group;
    }

    /**
     *
     * @param group
     * The group
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     *
     * @return
     * The groupDescription
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     *
     * @param groupDescription
     * The group_description
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    /**
     *
     * @return
     * The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     * The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     * The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     * The mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     *
     * @param mobile
     * The mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     *
     * @return
     * The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     * The avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     *
     * @param avatar
     * The avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     *
     * @return
     * The subscriptionEnd
     */
    public Object getSubscriptionEnd() {
        return subscriptionEnd;
    }

    /**
     *
     * @param subscriptionEnd
     * The subscription_end
     */
    public void setSubscriptionEnd(Object subscriptionEnd) {
        this.subscriptionEnd = subscriptionEnd;
    }

    /**
     *
     * @return
     * The plan
     */
    public Object getPlan() {
        return plan;
    }

    /**
     *
     * @param plan
     * The plan
     */
    public void setPlan(Object plan) {
        this.plan = plan;
    }

    /**
     *
     * @return
     * The dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     *
     * @param dateOfBirth
     * The date_of_birth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     *
     * @return
     * The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     * The display_name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
     * The userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The user_id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     * The profileId
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     *
     * @param profileId
     * The profile_id
     */
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

}
