package com.noklin.client.component;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.noklin.client.http.TextRequest;
import com.noklin.client.util.Json;
import com.noklin.client.util.Resource;

public class Html extends Component{

	private HTMLPanel root = new HTMLPanel("div", ""); 	
	Html(ComponentConfig config) {
		super(config);
		SafeHtmlBuilder builder = new SafeHtmlBuilder();
		String html = config.getString("html");
		if(html != null) {
			builder.appendHtmlConstant(html);
			root.add(new HTML(builder.toSafeHtml()));
		}
		String link = config.getString("htmlSource");
		String dd = Resource.getResourcePath() + link;
		TextRequest tr = new TextRequest(dd);
		tr.onStatusCode(200, t -> {
			root.add(new HTML(t));
		});
		tr.send();
	}
	
	/*
	 * 
	 * String url = Json.asString(v);
				String dd = Resource.getPath() + url;
				TextRequest tr = new TextRequest(dd);
				tr.onStatusCode(200, comsumer);
				tr.send();
	 * */
	
	@Override
	public Widget asWidget() {
		return root;
	}
}