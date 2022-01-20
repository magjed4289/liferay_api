
package tests.model.headlessDelivery;

import java.util.List;

import lombok.Data;

@Data
public class BlogPostCreated {

    private Actions actions;
    private String alternativeHeadline;
    private String articleBody;
    private Creator creator;
    private List<Object> customFields = null;
    private String dateCreated;
    private String dateModified;
    private String datePublished;
    private String description;
    private String encodingFormat;
    private String externalReferenceCode;
    private String friendlyUrlPath;
    private String headline;
    private Integer id;
    private List<Object> keywords = null;
    private Integer numberOfComments;
    private List<Object> relatedContents = null;
    private List<Object> renderedContents = null;
    private Integer siteId;
    private List<Object> taxonomyCategoryBriefs = null;

}
