
package com.example.pritamsankadiya.jetpackdemo.data.model;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MultiView {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("image_name1")
    @Expose
    private String imageName1;
    @SerializedName("image_name2")
    @Expose
    private String imageName2;
    @SerializedName("image_name3")
    @Expose
    private String imageName3;
    @SerializedName("image_name4")
    @Expose
    private String imageName4;
    @SerializedName("image_name5")
    @Expose
    private String imageName5;
    @SerializedName("vedio_name")
    @Expose
    private String vedioName;
    @SerializedName("image_name6")
    @Expose
    private String imageName6;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

/*    @BindingAdapter("imageUrl")
    public static void loadImage2(BlurImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }*/

    public String getImageName1() {
        return imageName1;
    }

    public void setImageName1(String imageName1) {
        this.imageName1 = imageName1;
    }

    public String getImageName2() {
        return imageName2;
    }

    public void setImageName2(String imageName2) {
        this.imageName2 = imageName2;
    }

    public String getImageName3() {
        return imageName3;
    }

    public void setImageName3(String imageName3) {
        this.imageName3 = imageName3;
    }

    public String getImageName4() {
        return imageName4;
    }

    public void setImageName4(String imageName4) {
        this.imageName4 = imageName4;
    }

    public String getImageName5() {
        return imageName5;
    }

    public void setImageName5(String imageName5) {
        this.imageName5 = imageName5;
    }

    public String getVedioName() {
        return vedioName;
    }

    public void setVedioName(String vedioName) {
        this.vedioName = vedioName;
    }

    public String getImageName6() {
        return imageName6;
    }

    public void setImageName6(String imageName6) {
        this.imageName6 = imageName6;
    }

    @Override
    public String toString() {
        return "MultiView{" +
                "id=" + id +
                ", type=" + type +
                ", text='" + text + '\'' +
                ", imageName1='" + imageName1 + '\'' +
                ", imageName2='" + imageName2 + '\'' +
                ", imageName3='" + imageName3 + '\'' +
                ", imageName4='" + imageName4 + '\'' +
                ", imageName5='" + imageName5 + '\'' +
                ", vedioName='" + vedioName + '\'' +
                ", imageName6='" + imageName6 + '\'' +
                '}';
    }
}
