package by.training.entity.profile;

import by.training.entity.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by angelina on 02.03.2017.
 */
public class User extends Entity {
    private int userId;
    private String login;
    private BigDecimal score;
    private RoleType roleType;
    private boolean ban;
    private String avatar;
    private LocalDate registrationDate;
    private String cardNumber;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthdate;
    private static final String STANDART_AVATAR = "user.png";

    public User() {
        avatar = STANDART_AVATAR;
        roleType = RoleType.USER;
        score = BigDecimal.ZERO;
    }

    public User(int userId, String login, BigDecimal score, RoleType roleType,
                boolean ban, String avatar, LocalDate registrationDate, String cardNumber,
                String firstName, String lastName, String email, LocalDate birthdate) {
        this.userId = userId;
        this.login = login;
        this.score = score;
        this.roleType = roleType;
        this.ban = ban;
        this.avatar = avatar;
        this.registrationDate = registrationDate;
        this.cardNumber = cardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdate = birthdate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getUserId() != user.getUserId()) return false;
        if (isBan() != user.isBan()) return false;
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) return false;
        if (getScore() != null ? !getScore().equals(user.getScore()) : user.getScore() != null) return false;
        if (getRoleType() != user.getRoleType()) return false;
        if (getAvatar() != null ? !getAvatar().equals(user.getAvatar()) : user.getAvatar() != null) return false;
        if (getRegistrationDate() != null ? !getRegistrationDate().equals(user.getRegistrationDate()) : user.getRegistrationDate() != null)
            return false;
        if (getCardNumber() != null ? !getCardNumber().equals(user.getCardNumber()) : user.getCardNumber() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        return getBirthdate() != null ? getBirthdate().equals(user.getBirthdate()) : user.getBirthdate() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId();
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getScore() != null ? getScore().hashCode() : 0);
        result = 31 * result + (getRoleType() != null ? getRoleType().hashCode() : 0);
        result = 31 * result + (isBan() ? 1 : 0);
        result = 31 * result + (getAvatar() != null ? getAvatar().hashCode() : 0);
        result = 31 * result + (getRegistrationDate() != null ? getRegistrationDate().hashCode() : 0);
        result = 31 * result + (getCardNumber() != null ? getCardNumber().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getBirthdate() != null ? getBirthdate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", score=" + score +
                ", roleType=" + roleType +
                ", ban=" + ban +
                ", avatar='" + avatar + '\'' +
                ", registrationDate=" + registrationDate +
                ", cardNumber='" + cardNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
