package com.example.communihelp.server;

import java.util.List;

public class Notify1ApiResponse {
    public boolean status;
    public List<Notify1Data> data;

    public boolean isStatus() {
        return status;
    }

    public List<Notify1Data> getData() {
        return data;
    }

    public static class Notify1Data {
        public String ref_id;
        public String offer_owner_id;
        public String offerrequest;
        public String type;
        public String category;
        public String details;
        public String date_time; // Ensure alias in PHP: `date&time` AS date_time
        public String accept_status;
        public String proceed_status;
        public String accept_user_id;
        public String yesno;
        public String accepted_by_name;
        public String email;
        public String phone_number;

        public String getRef_id() {
            return ref_id;
        }

        public String getOffer_owner_id() {
            return offer_owner_id;
        }

        public String getOfferrequest() {
            return offerrequest;
        }

        public String getType() {
            return type;
        }

        public String getCategory() {
            return category;
        }

        public String getDetails() {
            return details;
        }

        public String getDate_time() {
            return date_time;
        }

        public String getAccept_status() {
            return accept_status;
        }

        public String getProceed_status() {
            return proceed_status;
        }

        public String getAccept_user_id() {
            return accept_user_id;
        }

        public String getYesno() {
            return yesno;
        }

        public String getAccepted_by_name() {
            return accepted_by_name;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone_number() {
            return phone_number;
        }
    }
}
