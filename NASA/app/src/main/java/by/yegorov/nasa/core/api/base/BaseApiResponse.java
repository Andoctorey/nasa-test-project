package by.yegorov.nasa.core.api.base;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class BaseApiResponse<Data> {

    @SerializedName("status")
    private String status;

    @SerializedName("items")
    private List<Data> items;

    public List<Data> getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }
}
