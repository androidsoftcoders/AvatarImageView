[![](https://jitpack.io/v/indra58/AvatarImageView.svg)](https://jitpack.io/#indra58/AvatarImageView)

# AvatarImageView Android

AvatarImageView is android ImageView library for displaying images in multiple styles.

The main goal for developing this library was to make it easy for all programmers to load image in various styles.
Our library is:

- **Easy to implement**: Just add jCenter dependency for project in your app level gradle file and voila! Half of your work is done. The other half is to implement it in your project. We'll talk about that below :)
- **Easy to understand**: Simple and neat code with proper documentation.

## Contents

1. [Installation](#installation-one-step)
1. [Getting Started](#getting-started-two-steps)

## Installation (One Step)
Add the dependency from jCenter to your app's (not project) `build.gradle` file.

```groovy
repositories {
    jcenter()
}

dependencies {
    implementation ''
    implementation 'com.github.bumptech.glide:glide:4.7.1'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.7.1'
}
```

## Getting Started (Two Steps)

### Prerequisites

1. Android API 14+ is required as the `minSdkVersion` in your build.gradle.
2. Add permissions (if necessary) to your `AndroidManifest.xml`

```manifest
<!-- if you want to load images from the internet -->
<uses-permission android:name="android.permission.INTERNET" /> 

<!-- if you want to load images from a file OR from the internet -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

## Usage

### Add AvatarImageView to your layout

In your activity or fragment, insert `AvatarImageView` custom view.

`AvatarImageView` extends `FrameLayout`, so all of its standard attributes are available to you.

```xml

<imageview.avatar.com.avatarimageview.AvatarImageView
       android:id="@+id/imageView"
       android:layout_width="72dp"
       android:layout_height="72dp" />

```

#### Additional properties to customize `AvatarImageView`

1. **Round Image**

![](https://image.ibb.co/goWDTK/round.png)

```xml
<imageview.avatar.com.avatarimageview.AvatarImageView
        android:id="@+id/imageView"
        android:layout_width="72dp"
        android:layout_height="72dp"
        app:image_shape="round" />
```


Then, in your activity:
```java
AvatarImageView avatarImageView = findViewById(R.id.imageView);
avatarImageView.setAvatar("https://yourimageurl or file absolute path", "Avatar Name");
```

2. **Add radius to your image**

![](https://image.ibb.co/eAkJvz/curve.png)

```xml
<imageview.avatar.com.avatarimageview.AvatarImageView
        android:id="@+id/imageView"
        android:layout_width="72dp"
        android:layout_height="72dp"
        app:image_radius="20"
        app:image_shape="curved" />
        
```

Then, in your activity:
```java
AvatarImageView avatarImageView = findViewById(R.id.imageView);
avatarImageView.setAvatar("https://yourimageurl or file absolute path", "Avatar Name");
```

*Note: If you want normal image with no roundness or curve in edges. Don't use `app` provided properties*

3. **If you want to show text(nickname) instead of image like in the preview shown below:**

![](https://image.ibb.co/fhz3vz/textimage.png)

Do this:
```java
AvatarImageView avatarImageView = findViewById(R.id.imageView);
avatarImageView.setAvatar("", "Your Avatar Name");
```

## Thanks

- [Glide](https://github.com/bumptech/glide)

## DemoApp
DemoApp Coming Soon!!!

