package com.example.shuangzhecheng.loweskevinapp.model;

import java.util.List;

/**
 * Created by shuangzhecheng on 11/28/17.
 */

public class SubCategories {
    private List<SubCategoryBean> SubCategory;

    public List<SubCategoryBean> getSubCategory() {
        return SubCategory;
    }

    public void setSubCategory(List<SubCategoryBean> SubCategory) {
        this.SubCategory = SubCategory;
    }

    public static class SubCategoryBean {

        private String Id;
        private String SubCatagoryName;
        private String SubCatagoryDiscription;
        private String CatagoryImage;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getSubCatagoryName() {
            return SubCatagoryName;
        }

        public void setSubCatagoryName(String SubCatagoryName) {
            this.SubCatagoryName = SubCatagoryName;
        }

        public String getSubCatagoryDiscription() {
            return SubCatagoryDiscription;
        }

        public void setSubCatagoryDiscription(String SubCatagoryDiscription) {
            this.SubCatagoryDiscription = SubCatagoryDiscription;
        }

        public String getCatagoryImage() {
            return CatagoryImage;
        }

        public void setCatagoryImage(String CatagoryImage) {
            this.CatagoryImage = CatagoryImage;
        }
    }
}
