
package tests.model.headlessDelivery.blogPostCreated;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Actions {

    @SerializedName("get-rendered-content-by-display-page")
    @Expose
    private GetRenderedContentByDisplayPage getRenderedContentByDisplayPage;
    @SerializedName("get")
    @Expose
    private Get get;
    @SerializedName("replace")
    @Expose
    private Replace replace;
    @SerializedName("update")
    @Expose
    private Update update;
    @SerializedName("delete")
    @Expose
    private Delete delete;

    public GetRenderedContentByDisplayPage getGetRenderedContentByDisplayPage() {
        return getRenderedContentByDisplayPage;
    }

    public void setGetRenderedContentByDisplayPage(GetRenderedContentByDisplayPage getRenderedContentByDisplayPage) {
        this.getRenderedContentByDisplayPage = getRenderedContentByDisplayPage;
    }

    public Get getGet() {
        return get;
    }

    public void setGet(Get get) {
        this.get = get;
    }

    public Replace getReplace() {
        return replace;
    }

    public void setReplace(Replace replace) {
        this.replace = replace;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public Delete getDelete() {
        return delete;
    }

    public void setDelete(Delete delete) {
        this.delete = delete;
    }

}
