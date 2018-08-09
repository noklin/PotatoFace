package com.noklin.client.component;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.noklin.client.Log;
import com.noklin.client.http.TextRequest;
import com.noklin.client.util.Json;
import com.noklin.client.util.Resource;

public class Html extends Component{

	private HTMLPanel root = new HTMLPanel("div", ""); 	
	Html(Json config) {
		super(config);
		SafeHtmlBuilder builder = new SafeHtmlBuilder();
		Json jSon = config.getJson("html");
		if(jSon.isNull()) {
			Log.error("Html component. html property not presented or null. Config: " + config);
		}else {
			String html = config.asString();
			builder.appendHtmlConstant(html);
			root.add(new HTML(builder.toSafeHtml()));
			Json htmlSource = config.getJson("htmlSource");
			if(!htmlSource.isNull()) {
				String link = htmlSource.asString();
				String dd = Resource.getResourcePath() + link;
				TextRequest tr = new TextRequest(dd);
				tr.onStatusCode(200, t -> {
					root.add(new HTML(t));
				});
				tr.send();
			}
		}
	}
	
	@Override
	public Widget asWidget() {
		return root;
	}
}