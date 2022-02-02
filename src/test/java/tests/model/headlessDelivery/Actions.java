
package tests.model.headlessDelivery;

import lombok.Data;

@Data
public class Actions {

    private GetRenderedContentByDisplayPage getRenderedContentByDisplayPage;
    private Get get;
    private Replace replace;
    private Update update;
    private Delete delete;
}
