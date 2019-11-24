package lv.gusevs.rsser.notifiers;

public class NotificationBuilder {

    public static class Builder {
        private String imageUrl;
        private String subject;
        private String message;
        private String action;

        public Builder(String subject) {
            this.subject = subject;
        }

        public Builder withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withAction(String action) {
            this.action = action;
            return this;
        }

        public Notification build() {
            Notification notification = new Notification();
            notification.setImageUrl(imageUrl);
            notification.setSubject(subject);
            notification.setMessage(message);
            notification.setAction(action);
            return notification;
        }
    }

}
