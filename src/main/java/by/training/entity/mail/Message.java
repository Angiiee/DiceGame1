package by.training.entity.mail;

import by.training.entity.Entity;

/**
 * Created by angelina on 26.03.2017.
 */
public class Message extends Entity {
    private String senderLogin;
    private String recipientLogin;
    private String theme;
    private String text;

    public Message (){}

    public Message(String senderLogin, String recipientLogin, String theme, String text) {
        this.senderLogin = senderLogin;
        this.recipientLogin = recipientLogin;
        this.theme = theme;
        this.text = text;
    }

    public String getSenderLogin() {
        return senderLogin;
    }

    public void setSenderLogin(String senderLogin) {
        this.senderLogin = senderLogin;
    }

    public String getRecipientLogin() {
        return recipientLogin;
    }

    public void setRecipientLogin(String recipientLogin) {
        this.recipientLogin = recipientLogin;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message message = (Message) o;

        if (getSenderLogin() != null ? !getSenderLogin().equals(message.getSenderLogin()) : message.getSenderLogin() != null)
            return false;
        if (getRecipientLogin() != null ? !getRecipientLogin().equals(message.getRecipientLogin()) : message.getRecipientLogin() != null)
            return false;
        if (getTheme() != null ? !getTheme().equals(message.getTheme()) : message.getTheme() != null) return false;
        return getText() != null ? getText().equals(message.getText()) : message.getText() == null;
    }

    @Override
    public int hashCode() {
        int result = getSenderLogin() != null ? getSenderLogin().hashCode() : 0;
        result = 31 * result + (getRecipientLogin() != null ? getRecipientLogin().hashCode() : 0);
        result = 31 * result + (getTheme() != null ? getTheme().hashCode() : 0);
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "senderLogin='" + senderLogin + '\'' +
                ", recipientLogin='" + recipientLogin + '\'' +
                ", theme='" + theme + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
