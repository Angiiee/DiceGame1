package by.training.entity.response;

/**
 * Created by angelina on 01.06.2017.
 */
public class ResponseInfo {
    private String nextPage;
    private ResponseType type;

    public ResponseInfo() {
    }

    public ResponseInfo(String nextPage, ResponseType type) {
        this.nextPage = nextPage;
        this.type = type;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "nextPage='" + nextPage + '\'' +
                ", type=" + type +
                '}';
    }
}
