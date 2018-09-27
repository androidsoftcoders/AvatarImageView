package imageview.avatar.com.avatarimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

public class AvatarImageView extends FrameLayout {

    private final int DEFAULT_IMAGE_SHAPE = 0;
    private final int DEFAULT_IMAGE_RADIUS = 40;
    private final int DEFAULT_IMAGE_MARGIN = 0;
    private final float DEFAULT_TEXT_SIZE = 20f;
    private final int DEFAULT_FONT_STYLE = -1;

    private final boolean DEFAULT_GRADIENT_ENABLED = false;
    private final int DEFAULT_BG_COLOR = -1;
    private final int DEFAULT_START_COLOR = -1;
    private final int DEFAULT_CENTER_COLOR = -1;
    private final int DEFAULT_END_COLOR = -1;
    private final int DEFAULT_GRADIENT_ANGLE = 0;

    private int mImageShape = DEFAULT_IMAGE_SHAPE;
    private int mImageRadius = DEFAULT_IMAGE_RADIUS;
    private int mImageMargin = DEFAULT_IMAGE_MARGIN;
    private float mTextSize = DEFAULT_TEXT_SIZE;
    private int mFontStyle = DEFAULT_FONT_STYLE;

    private boolean mGradientEnabled = DEFAULT_GRADIENT_ENABLED;
    private int mBgColor = DEFAULT_BG_COLOR;
    private int mStartColor = DEFAULT_START_COLOR;
    private int mCenterColor = DEFAULT_CENTER_COLOR;
    private int mEndColor = DEFAULT_END_COLOR;
    private int mGradientAngle = DEFAULT_GRADIENT_ANGLE;

    private AppCompatImageView mAppCompatImageView;
    private AppCompatTextView mAppCompatTextView;
    private Context mContext;

    private UserAvatar mUserAvatar;

    public AvatarImageView(@NonNull Context context) {
        this(context, null);
    }

    public AvatarImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AvatarImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AvatarImageView, defStyleAttr, 0);

        mImageShape = typedArray.getInteger(R.styleable.AvatarImageView_image_shape, DEFAULT_IMAGE_SHAPE);
        mImageMargin = typedArray.getInteger(R.styleable.AvatarImageView_image_margin, DEFAULT_IMAGE_MARGIN);
        mImageRadius = typedArray.getInteger(R.styleable.AvatarImageView_image_radius, DEFAULT_IMAGE_RADIUS);
        mTextSize = typedArray.getFloat(R.styleable.AvatarImageView_text_size, DEFAULT_TEXT_SIZE);

        mFontStyle = typedArray.getResourceId(R.styleable.AvatarImageView_font_family, DEFAULT_FONT_STYLE);

        mGradientEnabled = typedArray.getBoolean(R.styleable.AvatarImageView_gradient_enabled, DEFAULT_GRADIENT_ENABLED);
        mBgColor = typedArray.getResourceId(R.styleable.AvatarImageView_bg_color, DEFAULT_BG_COLOR);
        mStartColor = typedArray.getResourceId(R.styleable.AvatarImageView_start_color, DEFAULT_START_COLOR);
        mCenterColor = typedArray.getResourceId(R.styleable.AvatarImageView_center_color, DEFAULT_CENTER_COLOR);
        mEndColor = typedArray.getResourceId(R.styleable.AvatarImageView_end_color, DEFAULT_END_COLOR);
        mGradientAngle = typedArray.getInteger(R.styleable.AvatarImageView_gradient_angle, DEFAULT_GRADIENT_ANGLE);

        typedArray.recycle();

        init();
    }

    private void init() {
        mAppCompatImageView = new AppCompatImageView(mContext);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mAppCompatImageView.setLayoutParams(params);

        mAppCompatTextView = new AppCompatTextView(mContext);
        mAppCompatTextView.setLayoutParams(params);
        mAppCompatTextView.setTextColor(ContextCompat.getColor(mContext, android.R.color.white));
        mAppCompatTextView.setTextSize(mTextSize);
        mAppCompatTextView.setGravity(Gravity.CENTER);
        if (mFontStyle != DEFAULT_FONT_STYLE) {
            mAppCompatTextView.setTypeface(ResourcesCompat.getFont(mContext, mFontStyle));
        }

        GradientDrawable shortNameDrawable;

        if (mGradientEnabled) {
            if (mStartColor == DEFAULT_START_COLOR && mCenterColor == DEFAULT_CENTER_COLOR && mEndColor == DEFAULT_END_COLOR) {
                shortNameDrawable = new GradientDrawable(getGradientOrientation(),
                        new int[]{ColorUtils.getRandomColor(),
                                ColorUtils.getRandomColor(),
                                ColorUtils.getRandomColor()});
            } else {
                shortNameDrawable = new GradientDrawable(getGradientOrientation(),
                        new int[]{mStartColor != DEFAULT_START_COLOR ? ContextCompat.getColor(mContext, mStartColor) :
                                ColorUtils.getRandomColor(),
                                mCenterColor != DEFAULT_CENTER_COLOR ? ContextCompat.getColor(mContext, mCenterColor) :
                                        ColorUtils.getRandomColor(),
                                mEndColor != DEFAULT_END_COLOR ? ContextCompat.getColor(mContext, mEndColor) :
                                        ColorUtils.getRandomColor()});
            }
        } else {
            shortNameDrawable = new GradientDrawable();
            shortNameDrawable.setColor(mBgColor != DEFAULT_BG_COLOR ?
                    ContextCompat.getColor(mContext, mBgColor) :
                    ColorUtils.getRandomColor());
        }

        shortNameDrawable.setShape(GradientDrawable.OVAL);
        shortNameDrawable.setSize(mAppCompatTextView.getWidth(), mAppCompatTextView.getHeight());
        mAppCompatTextView.setBackgroundDrawable(shortNameDrawable);

        addView(mAppCompatImageView);
        addView(mAppCompatTextView);

        hideTextView();
    }

    private GradientDrawable.Orientation getGradientOrientation() {
        switch (mGradientAngle) {
            case 45:
                return GradientDrawable.Orientation.BL_TR;
            case 90:
                return GradientDrawable.Orientation.BOTTOM_TOP;
            case 135:
                return GradientDrawable.Orientation.BR_TL;
            case 180:
                return GradientDrawable.Orientation.RIGHT_LEFT;
            case 225:
                return GradientDrawable.Orientation.TR_BL;
            case 270:
                return GradientDrawable.Orientation.TOP_BOTTOM;
            case 315:
                return GradientDrawable.Orientation.TL_BR;
            default:
                return GradientDrawable.Orientation.LEFT_RIGHT;
        }
    }

    public void setAvatar(UserAvatar userAvatar) {
        mUserAvatar = userAvatar;

        switch (mImageShape) {
            case 1:
                setRoundImage();
                break;
            case 2:
                setCurvedImage();
                break;
            default:
                setNormalImage();
                break;
        }
    }

    private void setNormalImage() {
        GlideApp.with(mAppCompatImageView)
                .load(mUserAvatar.getAvatarImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        setAvatarText();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(mAppCompatImageView);
    }

    private void setCurvedImage() {
        GlideApp.with(mAppCompatImageView)
                .load(mUserAvatar.getAvatarImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .transform(new RoundedCornersTransformation(mContext, mImageRadius, mImageMargin))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        setAvatarText();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(mAppCompatImageView);
    }

    private void setRoundImage() {
        GlideApp.with(mAppCompatImageView)
                .load(mUserAvatar.getAvatarImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .apply(RequestOptions.circleCropTransform())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        setAvatarText();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(mAppCompatImageView);
    }

    private void hideImageView() {
        mAppCompatImageView.setVisibility(GONE);
    }

    private void hideTextView() {
        mAppCompatTextView.setVisibility(GONE);
    }

    private void showImageView() {
        mAppCompatImageView.setVisibility(VISIBLE);
    }

    private void showTextView() {
        mAppCompatTextView.setVisibility(VISIBLE);
    }

    private void setAvatarText() {
        hideImageView();
        showTextView();

        mAppCompatTextView.setText(mUserAvatar.getAvatarName().isEmpty() ? "" : NameUtils.getShortName(mUserAvatar.getAvatarName()));
    }
}