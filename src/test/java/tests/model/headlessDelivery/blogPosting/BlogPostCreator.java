package tests.model.headlessDelivery.blogPosting;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class BlogPostCreator {
    public String articleBody;
    public String dateCreated;
    public String dateModified;
    public String datePublished;
    public String encodingFormat;
    public String friendlyUrlPath;
    public String headline;
    public int numberOfComments;
}
