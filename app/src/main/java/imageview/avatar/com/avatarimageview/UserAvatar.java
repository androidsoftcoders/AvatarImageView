package imageview.avatar.com.avatarimageview;

public class UserAvatar {
    private String avatarImageUrl;
    private String avatarName;

    public UserAvatar() {
    }

    public UserAvatar(String avatarImageUrl, String avatarName) {
        this.avatarImageUrl = avatarImageUrl;
        this.avatarName = avatarName;
    }

    public String getAvatarImageUrl() {
        return avatarImageUrl;
    }

    public void setAvatarImageUrl(String avatarImageUrl) {
        this.avatarImageUrl = avatarImageUrl;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }
}