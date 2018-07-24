package com.noklin.client.component;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class Html extends Component{

	private HTML root; 	
	Html(ComponentConfig config) {
		super(config);
		SafeHtmlBuilder builder = new SafeHtmlBuilder();
		String html = config.getString("html");
		builder.appendHtmlConstant(html);
		root = new HTML(builder.toSafeHtml());
	}
	
	@Override
	public Widget asWidget() {
		return root;
	}
}