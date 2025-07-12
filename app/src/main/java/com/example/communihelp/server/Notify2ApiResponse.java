package com.example.communihelp.server;

import java.util.List;

public class Notify2ApiResponse {
    public boolean status;
    public List<Notify2Data> data;

    public boolean isStatus() {
        return status;
    }

    public List<Notify2Data> getData() {
        return data;
    }

    public static class Notify2Data {
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
        public String owner_name;
        public String owner_email;
        public String owner_phone;

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

        public String getOwner_name() {
            return owner_name;
        }

        public String getOwner_email() {
            return owner_email;
        }

        public String getOwner_phone() {
            return owner_phone;
        }
    }
}
