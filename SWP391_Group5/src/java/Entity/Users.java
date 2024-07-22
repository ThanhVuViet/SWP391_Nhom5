/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Admin
 */
import java.util.Date;

public class Users {

    private int userId;
    private int roleId;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private Date birthDate;
    private String image;
    private String phoneNumber;
    private String address;
    private Date createdAt;
    private boolean banned;
    private int failedAttempt;
    private long lockTime;

    // Constructor
    public Users() {
    }

    public Users(int userId, int roleId, String username, String password, String fullName, String email, Date birthDate, String image, String phoneNumber, String address, Date createdAt, boolean banned, int failedAttempt, long lockTime) {
        this.userId = userId;
        this.roleId = roleId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
        this.image = image;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.createdAt = createdAt;
        this.banned = banned;
    }

    public int getFailedAttempt() {
        return failedAttempt;
    }

    public long getLockTime() {
        return lockTime;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFailedAttempt(int failedAttempt) {
        this.failedAttempt = failedAttempt;
    }

    public void setLockTime(long lockTime) {
        this.lockTime = lockTime;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @Override
    public String toString() {
        return "Users{"
                + "userId=" + userId
                + ", roleId=" + roleId
                + ", username='" + username + '\''
                + ", password='" + password + '\''
                + ", fullName='" + fullName + '\''
                + ", email='" + email + '\''
                + ", birthDate=" + birthDate
                + ", image='" + image + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + ", address='" + address + '\''
                + ", createdAt=" + createdAt
                + ", banned=" + banned
                + '}';
    }
}
