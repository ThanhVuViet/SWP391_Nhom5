package Entity;

/**
 *
 * @author Admin
 */
public class Expert {
    private int expertId;
    private Users user;
    private String description;
    private String certification;

    // Constructor
    public Expert(int expertId, Users user, String description, String certification) {
        this.expertId = expertId;
        this.user = user;
        this.description = description;
        this.certification = certification;
    }

    // Getters and Setters
    public int getExpertId() {
        return expertId;
    }

    public void setExpertId(int expertId) {
        this.expertId = expertId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    @Override
    public String toString() {
        return "Expert{" +
                "expertId=" + expertId +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", certification='" + certification + '\'' +
                '}';
    }
}
