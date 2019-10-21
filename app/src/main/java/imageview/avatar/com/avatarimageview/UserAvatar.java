package imageview.avatar.com.avatarimageview;

import java.io.File;

public class UserAvatar {
    private String avatarImageUrl;
    private File imageFile;
    private String avatarName;

    public UserAvatar() {
    }

    public UserAvatar(File imageFile, String avatarName) {
        this.imageFile = imageFile;
        this.avatarName = avatarName;
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

    public File getImageFile() {
        return imageFile;
    }

    public void setFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }
}