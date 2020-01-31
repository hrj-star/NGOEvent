package code.slash.Model;

import java.util.List;

public class Event {
    private String address;
    private String description;
    private String city;


    private String pincode;
    private String  event_name;
    private String  ngo_name;
    private String  ngo_logo;
    private String  date;
    private String  time;
    private String  location;
    private String  organize_by;
    private String  vol_req;
    private String  ngo_id;
    private String  approval_req;
    private List<String>  photos;
    private List<String> annoucement;

    public Event(String address, String description, String city, String pincode, String event_name, String ngo_name, String ngo_logo, String date, String time, String location, String organize_by, String vol_req, String ngo_id, String approval_req, List<String> photos, List<String> annoucement, List<String> comment) {
        this.address = address;
        this.description = description;
        this.city = city;
        this.pincode = pincode;
        this.event_name = event_name;
        this.ngo_name = ngo_name;
        this.ngo_logo = ngo_logo;
        this.date = date;
        this.time = time;
        this.location = location;
        this.organize_by = organize_by;
        this.vol_req = vol_req;
        this.ngo_id = ngo_id;
        this.approval_req = approval_req;
        this.photos = photos;
        this.annoucement = annoucement;
        this.comment = comment;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNgo_id() {
        return ngo_id;
    }

    public void setNgo_id(String ngo_id) {
        this.ngo_id = ngo_id;
    }

    private List<String> comment;

    public Event() {
    }

    public String getApproval_req() {
        return approval_req;
    }

    public void setApproval_req(String approval_req) {
        this.approval_req = approval_req;
    }



    public Event(String event_name, String ngo_name, String date, String time) {
        this.event_name = event_name;
        this.ngo_name = ngo_name;
        this.date = date;
        this.time = time;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getNgo_name() {
        return ngo_name;
    }

    public void setNgo_name(String ngo_name) {
        this.ngo_name = ngo_name;
    }

    public String getNgo_logo() {
        return ngo_logo;
    }

    public void setNgo_logo(String ngo_logo) {
        this.ngo_logo = ngo_logo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganize_by() {
        return organize_by;
    }

    public void setOrganize_by(String organize_by) {
        this.organize_by = organize_by;
    }

    public String getVol_req() {
        return vol_req;
    }

    public void setVol_req(String vol_req) {
        this.vol_req = vol_req;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<String> getAnnoucement() {
        return annoucement;
    }

    public void setAnnoucement(List<String> annoucement) {
        this.annoucement = annoucement;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }
}
