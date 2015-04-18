package models;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;

public class Result {

    @Expose
    private String GsearchResultClass;
    @Expose
    private String width;
    @Expose
    private String height;
    @Expose
    private String imageId;
    @Expose
    private String tbWidth;
    @Expose
    private String tbHeight;
    @Expose
    private String unescapedUrl;
    @Expose
    private String url;
    @Expose
    private String visibleUrl;
    @Expose
    private String title;
    @Expose
    private String titleNoFormatting;
    @Expose
    private String originalContextUrl;
    @Expose
    private String content;
    @Expose
    private String contentNoFormatting;
    @Expose
    private String tbUrl;

    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     *
     * @return
     * The GsearchResultClass
     */
    public String getGsearchResultClass() {
        return GsearchResultClass;
    }

    /**
     *
     * @param GsearchResultClass
     * The GsearchResultClass
     */
    public void setGsearchResultClass(String GsearchResultClass) {
        this.GsearchResultClass = GsearchResultClass;
    }

    /**
     *
     * @return
     * The width
     */
    public String getWidth() {
        return width;
    }

    /**
     *
     * @param width
     * The width
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     *
     * @return
     * The height
     */
    public String getHeight() {
        return height;
    }

    /**
     *
     * @param height
     * The height
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     *
     * @return
     * The imageId
     */
    public String getImageId() {
        return imageId;
    }

    /**
     *
     * @param imageId
     * The imageId
     */
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    /**
     *
     * @return
     * The tbWidth
     */
    public String getTbWidth() {
        return tbWidth;
    }

    /**
     *
     * @param tbWidth
     * The tbWidth
     */
    public void setTbWidth(String tbWidth) {
        this.tbWidth = tbWidth;
    }

    /**
     *
     * @return
     * The tbHeight
     */
    public String getTbHeight() {
        return tbHeight;
    }

    /**
     *
     * @param tbHeight
     * The tbHeight
     */
    public void setTbHeight(String tbHeight) {
        this.tbHeight = tbHeight;
    }

    /**
     *
     * @return
     * The unescapedUrl
     */
    public String getUnescapedUrl() {
        return unescapedUrl;
    }

    /**
     *
     * @param unescapedUrl
     * The unescapedUrl
     */
    public void setUnescapedUrl(String unescapedUrl) {
        this.unescapedUrl = unescapedUrl;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The visibleUrl
     */
    public String getVisibleUrl() {
        return visibleUrl;
    }

    /**
     *
     * @param visibleUrl
     * The visibleUrl
     */
    public void setVisibleUrl(String visibleUrl) {
        this.visibleUrl = visibleUrl;
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
     * The titleNoFormatting
     */
    public String getTitleNoFormatting() {
        return titleNoFormatting;
    }

    /**
     *
     * @param titleNoFormatting
     * The titleNoFormatting
     */
    public void setTitleNoFormatting(String titleNoFormatting) {
        this.titleNoFormatting = titleNoFormatting;
    }

    /**
     *
     * @return
     * The originalContextUrl
     */
    public String getOriginalContextUrl() {
        return originalContextUrl;
    }

    /**
     *
     * @param originalContextUrl
     * The originalContextUrl
     */
    public void setOriginalContextUrl(String originalContextUrl) {
        this.originalContextUrl = originalContextUrl;
    }

    /**
     *
     * @return
     * The content
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @param content
     * The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     *
     * @return
     * The contentNoFormatting
     */
    public String getContentNoFormatting() {
        return contentNoFormatting;
    }

    /**
     *
     * @param contentNoFormatting
     * The contentNoFormatting
     */
    public void setContentNoFormatting(String contentNoFormatting) {
        this.contentNoFormatting = contentNoFormatting;
    }

    /**
     *
     * @return
     * The tbUrl
     */
    public String getTbUrl() {
        return tbUrl;
    }

    /**
     *
     * @param tbUrl
     * The tbUrl
     */
    public void setTbUrl(String tbUrl) {
        this.tbUrl = tbUrl;
    }

}
