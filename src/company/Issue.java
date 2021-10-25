package company;

public class Issue{

        private String description;
        public Status status;

        public Issue(String description) {
            this.description = description;
            this.status=Status.PENDING;
        }

    public Issue(String description, Status status) {
        this.description = description;
        this.status= status;
    }

    @Override
        public String toString() {
            return description;
        }}
