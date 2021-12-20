
package tests.model.headlessDelivery.blogPostCreated;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class BlogPostCreated {

    @SerializedName("actions")
    @Expose
    private Actions actions;
    @SerializedName("alternativeHeadline")
    @Expose
    private String alternativeHeadline;
    @SerializedName("articleBody")
    @Expose
    private String articleBody;
    @SerializedName("creator")
    @Expose
    private Creator creator;
    @SerializedName("customFields")
    @Expose
    private List<Object> customFields = null;
    @SerializedName("dateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("dateModified")
    @Expose
    private String dateModified;
    @SerializedName("datePublished")
    @Expose
    private String datePublished;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("encodingFormat")
    @Expose
    private String encodingFormat;
    @SerializedName("externalReferenceCode")
    @Expose
    private String externalReferenceCode;
    @SerializedName("friendlyUrlPath")
    @Expose
    private String friendlyUrlPath;
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("keywords")
    @Expose
    private List<Object> keywords = null;
    @SerializedName("numberOfComments")
    @Expose
    private Integer numberOfComments;
    @SerializedName("relatedContents")
    @Expose
    private List<Object> relatedContents = null;
    @SerializedName("renderedContents")
    @Expose
    private List<Object> renderedContents = null;
    @SerializedName("siteId")
    @Expose
    private Integer siteId;
    @SerializedName("taxonomyCategoryBriefs")
    @Expose
    private List<Object> taxonomyCategoryBriefs = null;

    public Actions getActions() {
        return actions;
    }

    public void setActions(Actions actions) {
        this.actions = actions;
    }

    public String getAlternativeHeadline() {
        return alternativeHeadline;
    }

    public void setAlternativeHeadline(String alternativeHeadline) {
        this.alternativeHeadline = alternativeHeadline;
    }

    public String getArticleBody() {
        return articleBody;
    }

    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public List<Object> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<Object> customFields) {
        this.customFields = customFields;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEncodingFormat() {
        return encodingFormat;
    }

    public void setEncodingFormat(String encodingFormat) {
        this.encodingFormat = encodingFormat;
    }

    public String getExternalReferenceCode() {
        return externalReferenceCode;
    }

    public void setExternalReferenceCode(String externalReferenceCode) {
        this.externalReferenceCode = externalReferenceCode;
    }

    public String getFriendlyUrlPath() {
        return friendlyUrlPath;
    }

    public void setFriendlyUrlPath(String friendlyUrlPath) {
        this.friendlyUrlPath = friendlyUrlPath;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Object> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Object> keywords) {
        this.keywords = keywords;
    }

    public Integer getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(Integer numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public List<Object> getRelatedContents() {
        return relatedContents;
    }

    public void setRelatedContents(List<Object> relatedContents) {
        this.relatedContents = relatedContents;
    }

    public List<Object> getRenderedContents() {
        return renderedContents;
    }

    public void setRenderedContents(List<Object> renderedContents) {
        this.renderedContents = renderedContents;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public List<Object> getTaxonomyCategoryBriefs() {
        return taxonomyCategoryBriefs;
    }

    public void setTaxonomyCategoryBriefs(List<Object> taxonomyCategoryBriefs) {
        this.taxonomyCategoryBriefs = taxonomyCategoryBriefs;
    }

}
