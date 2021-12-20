
package tests.model.headlessDelivery.blog_post_created;

import lombok.Data;

@Data
public class Actions {

    private GetRenderedContentByDisplayPage getRenderedContentByDisplayPage;
    private Get get;
    private Replace replace;
    private Update update;
    private Delete delete;
}
