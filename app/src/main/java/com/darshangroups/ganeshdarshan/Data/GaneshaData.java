package com.darshangroups.ganeshdarshan.Data;

import java.io.Serializable;

public class GaneshaData implements Serializable {

    public String nimg_id, cimage_name, caddress, ccreated_by, cshared_by, cimg_caption, ctitle, cimg_path, cplace_name;

    public GaneshaData() {
    }

    public GaneshaData(String nimg_id, String cimage_name, String caddress, String ccreated_by, String cshared_by, String cimg_caption, String ctitle, String cimg_path, String cplace_name) {
        this.nimg_id = nimg_id;
        this.cimage_name = cimage_name;
        this.caddress = caddress;
        this.ccreated_by = ccreated_by;
        this.cshared_by = cshared_by;
        this.cimg_caption = cimg_caption;
        this.ctitle = ctitle;
        this.cimg_path = cimg_path;
        this.cplace_name = cplace_name;
    }

    public String getnimg_id() {
        return nimg_id;
    }

    public void setnimg_id(String nimg_id) {
        this.nimg_id = nimg_id;
    }

    public String getcimage_name() {
        return cimage_name;
    }

    public void setcimage_name(String cimage_name) {
        this.cimage_name = cimage_name;
    }

    public String getcaddress() {
        return caddress;
    }

    public void setcaddress(String caddress) {
        this.caddress = caddress;
    }

    public String getccreated_by() {
        return ccreated_by;
    }

    public void setccreated_by(String ccreated_by) {
        this.ccreated_by = ccreated_by;
    }

    public String getcshared_by() {
        return cshared_by;
    }

    public void setcshared_by(String cshared_by) {
        this.cshared_by = cshared_by;
    }

    public String getcimg_caption() {
        return cimg_caption;
    }

    public void setcimg_caption(String cimg_caption) {
        this.cimg_caption = cimg_caption;
    }

    public String getctitle() {
        return ctitle;
    }

    public void setctitle(String ctitle) {
        this.ctitle = ctitle;
    }

    public String getcimg_path() {
        return cimg_path;
    }

    public void setcimg_path(String img_path) {
        this.cimg_path = img_path;
    }

    public String getcplace_name() {
        return cplace_name;
    }

    public void setcplace_name(String cplace_name) {
        this.cplace_name = cplace_name;
    }
}